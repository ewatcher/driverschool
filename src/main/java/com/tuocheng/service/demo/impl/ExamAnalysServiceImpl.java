package com.tuocheng.service.demo.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.collections.map.HashedMap;
import org.apache.xmlbeans.impl.xb.xsdschema.TotalDigitsDocument.TotalDigits;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.TexamAnalys;
import com.tuocheng.model.demo.Torganization;
import com.tuocheng.model.demo.Tprogress;
import com.tuocheng.model.demo.Tstudent;
import com.tuocheng.model.demo.TstudentExam;
import com.tuocheng.model.demo.TstudentFile;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.ExamAnalys;
import com.tuocheng.service.demo.ExamAnalysServiceI;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 考试管理管理实体类service实现类
 * 
 * @author MEI702
 * 
 */
@Service("examAnalysService")
public class ExamAnalysServiceImpl implements ExamAnalysServiceI {
	private BaseDaoI<TexamAnalys> examAnalysDao;
	private BaseDaoI<TstudentExam> studentExamDao;
	private BaseDaoI<Torganization> organizationDao;
	private BaseDaoI<Tstudent> studentDao;
	private BaseDaoI<Tprogress> progressDao;
	private BaseDaoI<TstudentFile> studentFileDao;

	@Autowired
	public void setProgressDao(BaseDaoI<Tprogress> progressDao) {
		this.progressDao = progressDao;
	}

	@Autowired
	public void setStudentFileDao(BaseDaoI<TstudentFile> studentFileDao) {
		this.studentFileDao = studentFileDao;
	}

	@Autowired
	public void setExamAnalysDao(BaseDaoI<TexamAnalys> examAnalysDao) {
		this.examAnalysDao = examAnalysDao;
	}

	@Autowired
	public void setStudentExamDao(BaseDaoI<TstudentExam> studentExamDao) {
		this.studentExamDao = studentExamDao;
	}

	@Autowired
	public void setOrganizationDao(BaseDaoI<Torganization> organizationDao) {
		this.organizationDao = organizationDao;
	}

	@Autowired
	public void setStudentDao(BaseDaoI<Tstudent> studentDao) {
		this.studentDao = studentDao;
	}

	@Override
	public ExamAnalys save(ExamAnalys examAnalys) {
		// 1.数据模型转换
		TexamAnalys entry = new TexamAnalys();
		BeanUtils.copyProperties(examAnalys, entry, new String[] { "id" });
		entry.setId(UUID.randomUUID().toString());
		entry.setTotal(0L);
		// 2.保存数据
		examAnalysDao.save(entry);
		// 3.将保存后数据模型转换
		BeanUtils.copyProperties(entry, examAnalys);
		// 4.将转换后的数据返回给前台
		return examAnalys;
	}

	@Override
	public ExamAnalys udpate(ExamAnalys examAnalys) {
		// 1.数据模型转换
		TexamAnalys entry = new TexamAnalys();
		BeanUtils.copyProperties(examAnalys, entry, new String[] { "id" });
		entry.setId(examAnalys.getId());
		String sql = "select * from tb_studentExams where batch=?";
		List<TstudentExam> studentExams = studentExamDao.findAllBySQL(TstudentExam.class, sql, examAnalys.getBatch());
		if (ValidateUtil.isValidListObject(studentExams)) {
			for (TstudentExam tstudentExam : studentExams) {
				if (TstudentExam.EXAMFALG_TRUE == tstudentExam.getSub1ExamFlag()) {
					tstudentExam.setSub1ExamDate(examAnalys.getSubject1ExamDate());
				}
				if (TstudentExam.EXAMFALG_TRUE == tstudentExam.getSub2ExamFlag()) {
					tstudentExam.setSub2ExamDate(examAnalys.getSubject2ExamDate());
				}
				if (TstudentExam.EXAMFALG_TRUE == tstudentExam.getSub3ExamFlag()) {
					tstudentExam.setSub3ExamDate(examAnalys.getSubject3ExamDate());
				}
				if (TstudentExam.EXAMFALG_TRUE == tstudentExam.getSub4ExamFlag()) {
					tstudentExam.setSub4ExamDate(examAnalys.getSubject4ExamDate());
				}
				if (TstudentExam.EXAMFALG_TRUE == tstudentExam.getSub5ExamFlag()) {
					tstudentExam.setSub5ExamDate(examAnalys.getSubject5ExamDate());
				}
				updateStudentFileAndProgress(tstudentExam, examAnalys);
				studentExamDao.update(tstudentExam);
			}
		}
		// 2.保存数据
		examAnalysDao.update(entry);

		// 3.将保存后数据模型转换
		BeanUtils.copyProperties(entry, examAnalys);
		// 4.将转换后的数据返回给前台
		return examAnalys;
	}

	@Override
	public void delete(String ids) {
		// 1.判断参数是否为空
		if (ids != null) {
			// 2.对参数的字符串进行分割
			List<TstudentExam> retLists = null;
			for (String id : ids.split(",")) {
				// 排除id为0的情况
				if (!id.trim().equals("0")) {
					// 3.1根据ID从数据库中查找数据
					TexamAnalys entry = examAnalysDao.get(TexamAnalys.class, id);
					// 3.2 删除教练明细信息
					String sql = "select * from tb_studentExams where batch=? and schoolArea=?";
					retLists = studentExamDao.findAllBySQL(TstudentExam.class, sql, entry.getBatch(),
							entry.getSchoolArea());
					if (ValidateUtil.isValidListObject(retLists)) {
						for (TstudentExam studentExam : retLists) {
							if (studentExam != null) {
								studentExam.setBatch(null);
								studentExam.setSub1ExamDate(null);
								studentExam.setSub1ExamFlag(0);
								studentExam.setSub1Score(null);
								studentExam.setSub1MakeupTimes(0);
								studentExam.setSub1MakeupScore(null);
								studentExam.setSub1MakeupDate(null);
								studentExam.setSub1FishedFlag(0);
								studentExam.setSub1MissExamFlag(0);
								studentExam.setSub2ExamDate(null);
								studentExam.setSub2ExamFlag(0);
								studentExam.setSub2Score(null);
								studentExam.setSub2MakeupTimes(0);
								studentExam.setSub2MakeupScore(null);
								studentExam.setSub2MakeupDate(null);
								studentExam.setSub2FishedFlag(0);
								studentExam.setSub2MissExamFlag(0);
								studentExam.setSub3ExamDate(null);
								studentExam.setSub3ExamFlag(0);
								studentExam.setSub3Score(null);
								studentExam.setSub3MakeupTimes(0);
								studentExam.setSub3MakeupScore(null);
								studentExam.setSub3MakeupDate(null);
								studentExam.setSub3FishedFlag(0);
								studentExam.setSub3MissExamFlag(0);
								studentExam.setSub4ExamDate(null);
								studentExam.setSub4ExamFlag(null);
								studentExam.setSub4Score(null);
								studentExam.setSub4MakeupTimes(0);
								studentExam.setSub4MakeupScore(null);
								studentExam.setSub4MakeupDate(null);
								studentExam.setSub4FishedFlag(0);
								studentExam.setSub4MissExamFlag(0);
								studentExam.setSub5ExamDate(null);
								studentExam.setSub5ExamFlag(0);
								studentExam.setSub5Score(null);
								studentExam.setSub5MakeupTimes(0);
								studentExam.setSub5MakeupScore(null);
								studentExam.setSub5MakeupDate(null);
								studentExam.setSub5FishedFlag(0);
								studentExam.setSub5MissExamFlag(0);
								studentExam.setSub1ApplyFlag(0);
								studentExam.setSub2ApplyFlag(0);
								studentExam.setSub3ApplyFlag(0);
								studentExam.setSub4ApplyFlag(0);
								studentExam.setSub5ApplyFlag(0);
								studentExam.setSub1EditAbleFlag(Tstudent.EXAMSELECTED_FALSE);
								studentExam.setSub2EditAbleFlag(Tstudent.EXAMSELECTED_FALSE);
								studentExam.setSub3EditAbleFlag(Tstudent.EXAMSELECTED_FALSE);
								studentExam.setSub4EditAbleFlag(Tstudent.EXAMSELECTED_FALSE);
								studentExam.setSub5EditAbleFlag(Tstudent.EXAMSELECTED_FALSE);
								studentExam.setExamAnalys(null);
								studentExamDao.update(studentExam);
								// 还原学员信息表中的批次信息为null
								Tstudent student = null;
								if (studentExam.getStudent() != null) {
									student = studentExam.getStudent();
									if (student != null) {
										student.setBatch(null);
										student.setExamPhone(null);
										student.setOtherNo(null);
										studentDao.update(student);
									}
								}
							}
						}

					}
					// 4.调用DAO层删除数据
					examAnalysDao.delete(entry);
				}
			}

		}

	}

	@Override
	public DataGrid dataGrid(ExamAnalys examAnalys) throws ParseException {
		DataGrid dataGrid = new DataGrid();
		// 设置返回前台的数据
		dataGrid.setRows(this.changedModel(this.find(examAnalys)));
		// 设置总记录数
		dataGrid.setTotal(this.total(examAnalys));
		return dataGrid;
	}

	// 取最大考试批次
	public Integer getMaxBatchByschoolArea(String schoolArea) {
		if (!ValidateUtil.isValid(schoolArea)) {
			return null;
		}
		String sql = "select * from tb_examAnalys where schoolArea=? order by batch desc";
		List<TexamAnalys> retVals = examAnalysDao.findAllBySQL(TexamAnalys.class, sql, schoolArea);
		if (retVals != null) {
			Integer retVal = null;
			if (retVals.size() > 0) {
				retVal = retVals.get(0).getBatch() + 1;
			} else {
				retVal = 1;
			}
			return retVal;
		}
		return null;
	}

	/**
	 * 从数据库存中查找出该实体信息并将此实体类信息转换成action专用的实体类信息，即模型转换
	 * 
	 * @param students
	 * @return
	 * @throws ParseException
	 */
	private List<ExamAnalys> changedModel(List<TexamAnalys> analysLists) throws ParseException {
		List<ExamAnalys> rets = new ArrayList<ExamAnalys>();
		if (analysLists != null && analysLists.size() > 0) {
			String sql = "select count(*) from tb_studentExams where schoolArea=? and batch=?";

			Map<String, Map<String, Integer>> totalMap = null;
			Long total = null;
			for (TexamAnalys t : analysLists) {
				ExamAnalys entry = new ExamAnalys();
				BeanUtils.copyProperties(t, entry);
				// 将数据转换成教练考试管理页面中需要展现的教练数据信息
				entry.setSchoolAreaName(organizationDao.get(Torganization.class, t.getSchoolArea()).getName());
				total = studentExamDao.countBySQL(sql, t.getSchoolArea(), t.getBatch());
				entry.setTotal(total);
				// 获取该条批次记录各科统计数量
				totalMap = this.getTotalCountBySubject(t.getBatch(), t.getSchoolArea());
				// 科目一
				entry.setSubject1HandupTotal(totalMap.get("subject0").get("handupTotal00"));// 科一报名人数
				entry.setSubject1ApproveTotal(totalMap.get("subject0").get("applyTotal01"));// 科一通过审批人数
				entry.setSubject1RealExamTotal(
						totalMap.get("subject0").get("applyTotal01") - totalMap.get("subject0").get("missTotal02"));// 科一实考人数(审批人数－缺考人数)
				entry.setSubject1MissExamTotal(totalMap.get("subject0").get("missTotal02"));// 科一缺考人数
				entry.setSubject1PassExamTotal(totalMap.get("subject0").get("passTotal03"));// 科一合格人数
				// 科目二
				entry.setSubject2HandupTotal(totalMap.get("subject1").get("handupTotal10"));// 科二一报名人数
				entry.setSubject2ApproveTotal(totalMap.get("subject1").get("applyTotal11"));// 科二通过审批人数
				entry.setSubject2RealExamTotal(
						totalMap.get("subject1").get("applyTotal11") - totalMap.get("subject1").get("missTotal12"));// 科二实考人数(审批人数－缺考人数)
				entry.setSubject2MissExamTotal(totalMap.get("subject1").get("missTotal12"));// 科二缺考人数
				entry.setSubject2PassExamTotal(totalMap.get("subject1").get("passTotal13"));// 科二一合格人数
				// 科目三
				entry.setSubject3HandupTotal(totalMap.get("subject2").get("handupTotal20"));// 科三报名人数
				entry.setSubject3ApproveTotal(totalMap.get("subject2").get("applyTotal21"));// 科三通过审批人数
				entry.setSubject3RealExamTotal(
						totalMap.get("subject2").get("applyTotal21") - totalMap.get("subject2").get("missTotal22"));// 科三实考人数(审批人数－缺考人数)
				entry.setSubject3MissExamTotal(totalMap.get("subject2").get("missTotal22"));// 科三缺考人数
				entry.setSubject3PassExamTotal(totalMap.get("subject2").get("passTotal23"));// 科三合格人数
				// 科目四
				entry.setSubject4HandupTotal(totalMap.get("subject3").get("handupTotal30"));// 科四报名人数
				entry.setSubject4ApproveTotal(totalMap.get("subject3").get("applyTotal31"));// 科四通过审批人数
				entry.setSubject4RealExamTotal(
						totalMap.get("subject3").get("applyTotal31") - totalMap.get("subject3").get("missTotal32"));// 科四实考人数(审批人数－缺考人数)
				entry.setSubject4MissExamTotal(totalMap.get("subject3").get("missTotal32"));// 科四缺考人数
				entry.setSubject4PassExamTotal(totalMap.get("subject3").get("passTotal33"));// 科四一合格人数
				// 科目五
				entry.setSubject5HandupTotal(totalMap.get("subject4").get("handupTotal40"));// 科五报名人数
				entry.setSubject5ApproveTotal(totalMap.get("subject4").get("applyTotal41"));// 科五通过审批人数
				entry.setSubject5RealExamTotal(
						totalMap.get("subject4").get("applyTotal41") - totalMap.get("subject4").get("missTotal42"));// 科五实考人数(审批人数－缺考人数)
				entry.setSubject5MissExamTotal(totalMap.get("subject4").get("missTotal42"));// 科五缺考人数
				entry.setSubject5PassExamTotal(totalMap.get("subject4").get("passTotal43"));// 科五合格人数

				rets.add(entry);
			}
		}
		return rets;
	}

	/**
	 * 根据action专用实体类的信息查询所有dao专用的实体类信息
	 * 
	 * @param student
	 * @return
	 */
	private List<TexamAnalys> find(ExamAnalys examAnalys) {
		String hql = "from TexamAnalys t where 1=1";
		List<Object> paramList = new ArrayList<Object>();
		hql = this.addWhere(examAnalys, hql, paramList);
		if (examAnalys.getSort() != null && examAnalys.getOrder() != null) {
			hql += " order by " + examAnalys.getSort() + " " + examAnalys.getOrder();
		}
		return examAnalysDao.find(hql, paramList, examAnalys.getPage(), examAnalys.getRows());
	}

	/**
	 * 统计符合当前查询条件的总记录数
	 * 
	 * @param student
	 * @return
	 */
	private Long total(ExamAnalys examAnalys) {
		// 拼接查询条件
		String hql = "select count(*) from TexamAnalys t where 1=1 ";
		List<Object> paramsList = new ArrayList<Object>();
		hql = this.addWhere(examAnalys, hql, paramsList);
		return examAnalysDao.count(hql, paramsList);
	}

	/**
	 * 添加查询约束条件
	 * 
	 * @param examAnalys
	 * @param hql
	 * @param params
	 * @return
	 */
	private String addWhere(ExamAnalys examAnalys, String hql, List<Object> params) {

		// 按批次查询
		if (examAnalys.getBatch() != null && examAnalys.getBatch() > 0) {
			hql += " and t.batch= ?";
			params.add(examAnalys.getBatch());
		}

		// 所属校区精确查询s
		if (examAnalys.getSchoolArea() != null && !examAnalys.getSchoolArea().trim().equals("")) {
			hql += " and t.schoolArea =?";
			params.add(examAnalys.getSchoolArea().trim());
		}

		return hql;
	}

	/**
	 * 
	 * @param countType
	 *            (1:统计报名人数，2：审批人数，3：实考人数 4：缺考人数 5：合格人数)
	 * @param subjectType
	 *            （1：科目一，2：科目二，。。。）
	 * @param batch
	 * @param schoolArea
	 * @return Map<科目，map<handupTotal,applyTotal,missTotal,passTotal>>
	 */
	private Map<String, Map<String, Integer>> getTotalCountBySubject(Integer batch, String schoolArea) {
		if (!ValidateUtil.isValid(batch) || !ValidateUtil.isValid(schoolArea)) {
			return null;
		}
		Map<String, Map<String, Integer>> retMap = new HashMap<String, Map<String, Integer>>(0);
		// 数组格式：{报名人数，审批人数，缺考人数，合格人数}
		String[][] arr = { { "sub1ExamFlag", "sub1ApplyFlag", "sub1MissExamFlag", "sub1FishedFlag" },
				{ "sub2ExamFlag", "sub2ApplyFlag", "sub2MissExamFlag", "sub2FishedFlag" },
				{ "sub3ExamFlag", "sub3ApplyFlag", "sub3MissExamFlag", "sub3FishedFlag" },
				{ "sub4ExamFlag", "sub4ApplyFlag", "sub4MissExamFlag", "sub4FishedFlag" },
				{ "sub5ExamFlag", "sub5ApplyFlag", "sub5MissExamFlag", "sub5FishedFlag" } };

		String sql = null;
		Integer totalTemp = null;
		Map<String, Integer> tempMap = null;
		// 按科目统计该条批次记录各科目（科1，科2.……）人数
		for (int i = 0; i < 5; i++) {
			// 统计审批人数，缺考人数，合格人数
			tempMap = new HashMap<String, Integer>(0);
			for (int j = 0; j < 4; j++) {
				sql = "select count(*) from tb_studentExams where batch=? and schoolArea=? and " + arr[i][j] + "=1";
				totalTemp = studentExamDao.countBySQL(sql, batch, schoolArea).intValue();
				if (0 == j) {// 统计审批人数
					tempMap.put("handupTotal" + i + j, totalTemp);
				} else if (1 == j) {
					tempMap.put("applyTotal" + i + j, totalTemp);
				} else if (2 == j) {
					tempMap.put("missTotal" + i + j, totalTemp);
				} else if (3 == j) {
					tempMap.put("passTotal" + i + j, totalTemp);
				}
			}
			// 行遍历返回各科目统计对象map{applyTotal,missTotal,passTotal}
			if (0 == i) {
				retMap.put("subject" + i, tempMap);
			} else if (1 == i) {
				retMap.put("subject" + i, tempMap);
			} else if (2 == i) {
				retMap.put("subject" + i, tempMap);
			} else if (3 == i) {
				retMap.put("subject" + i, tempMap);
			} else if (4 == i) {
				retMap.put("subject" + i, tempMap);
			}
		}
		// 返回结果
		if (ValidateUtil.isValid(retMap)) {
			return retMap;
		}
		return null;
	}

	// 根据批次时间修改学员进度，学员档案信息（只修改考试时间）
	private void updateStudentFileAndProgress(TstudentExam studentExam, ExamAnalys examAnalys) {
		// 1.获取该考试学员的批次信息

		Tstudent student = studentExam.getStudent();
		if (student != null && examAnalys != null) {
			// 1.修改学员进度
			Tprogress progress = student.getProgress();
			TstudentFile file = student.getStudentFile();
			if (progress != null && file != null) {
				if (Tprogress.PROGRESS_ZIGE == progress.getState()) {// 资格考试
					if (ValidateUtil.isValid(examAnalys.getSubject5ExamDate())) {
						file.setQualificationDate(examAnalys.getSubject5ExamDate());
					}
				} else if (Tprogress.PROGRESS_SUBJECT4 == progress.getState()) {
					if (ValidateUtil.isValid(examAnalys.getSubject4ExamDate())
							&& ValidateUtil.isValid(examAnalys.getSubject3ExamDate())
							&& ValidateUtil.isValid(examAnalys.getSubject2ExamDate())) {
						progress.setSubject4Date(examAnalys.getSubject4ExamDate());
						progress.setSubject3Date(examAnalys.getSubject3ExamDate());
						progress.setSubject2Date(examAnalys.getSubject2ExamDate());
						file.setSubjectFourDate(examAnalys.getSubject4ExamDate());
						file.setSubjectThreeDate(examAnalys.getSubject3ExamDate());
						file.setSubjectTwoDate(examAnalys.getSubject2ExamDate());
					}
				} else if (Tprogress.PROGRESS_SUBJECT3 == progress.getState()) {
					if (ValidateUtil.isValid(examAnalys.getSubject3ExamDate())
							&& ValidateUtil.isValid(examAnalys.getSubject2ExamDate())) {
						progress.setSubject3Date(examAnalys.getSubject3ExamDate());
						progress.setSubject2Date(examAnalys.getSubject2ExamDate());
						file.setSubjectThreeDate(examAnalys.getSubject3ExamDate());
						file.setSubjectTwoDate(examAnalys.getSubject2ExamDate());
					}
				} else if (Tprogress.PROGRESS_SUBJECT2 == progress.getState()) {
					if (ValidateUtil.isValid(examAnalys.getSubject2ExamDate())) {
						progress.setSubject2Date(examAnalys.getSubject2ExamDate());
						file.setSubjectTwoDate(examAnalys.getSubject2ExamDate());
					}
				} else if (Tprogress.PROGRESS_SUBJECT1 == progress.getState()) {
					if (ValidateUtil.isValid(examAnalys.getSubject1ExamDate())) {
						progress.setSubject1Date(examAnalys.getSubject1ExamDate());
						file.setSubjectThreeDate(examAnalys.getSubject1ExamDate());
					}
				}
				progressDao.update(progress);
				studentFileDao.update(file);
			}
		}

	}

}
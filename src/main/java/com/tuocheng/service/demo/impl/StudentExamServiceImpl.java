package com.tuocheng.service.demo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.activation.FileDataSource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.components.Select;
import org.aspectj.weaver.ast.Var;
import org.hibernate.cache.spi.GeneralDataRegion;
import org.hibernate.sql.Update;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.TexamAnalys;
import com.tuocheng.model.demo.Tperson;
import com.tuocheng.model.demo.Tprogress;
import com.tuocheng.model.demo.TstudentExam;
import com.tuocheng.model.demo.TstudentFile;
import com.tuocheng.model.demo.Tstudent;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.base.ImportReturnModel;
import com.tuocheng.pageModel.demo.StudentExam;
import com.tuocheng.service.demo.StudentExamServiceI;
import com.tuocheng.util.base.StringUtil;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 学员考试实体类service实现类
 * 
 * @author 农峰
 * 
 */
@Service("studentExamService")
public class StudentExamServiceImpl implements StudentExamServiceI {

	private BaseDaoI<TstudentExam> studentExamDao;
	private BaseDaoI<Tstudent> studentDao;
	private BaseDaoI<TexamAnalys> examAnanlysDao;
	private BaseDaoI<Tprogress> progressDao;
	private BaseDaoI<TstudentFile> studentFileDao;

	@Autowired
	public void setStudentFileDao(BaseDaoI<TstudentFile> studentFileDao) {
		this.studentFileDao = studentFileDao;
	}

	@Autowired
	public void setStudentExamDao(BaseDaoI<TstudentExam> studentExamDao) {
		this.studentExamDao = studentExamDao;
	}

	@Autowired
	public void setStudentDao(BaseDaoI<Tstudent> studentDao) {
		this.studentDao = studentDao;
	}

	@Autowired
	public void setExamAnanlysDao(BaseDaoI<TexamAnalys> examAnanlysDao) {
		this.examAnanlysDao = examAnanlysDao;
	}

	@Autowired
	public void setProgressDao(BaseDaoI<Tprogress> progressDao) {
		this.progressDao = progressDao;
	}

	@Override
	public StudentExam save(StudentExam studentExam) {
		if (!ValidateUtil.isValid(studentExam.getStudentId())) {
			return null;
		}
		// 1.数据模型转换
		Tstudent student = studentDao.get(Tstudent.class, studentExam.getStudentId());
		TstudentExam entry = new TstudentExam();
		BeanUtils.copyProperties(studentExam, entry, new String[] { "id" });
		entry.setId(UUID.randomUUID().toString());
		entry.setStudent(student);

		// 2.保存数据
		studentExamDao.save(entry);
		// 3.将保存后数据模型转换
		BeanUtils.copyProperties(entry, studentExam);
		// 4.将转换后的数据返回给前台
		return studentExam;
	}

	@Override
	public StudentExam udpate(StudentExam studentExam) {
		if (!ValidateUtil.isValid(studentExam.getStudentId()) || !ValidateUtil.isValid(studentExam.getId())) {
			return null;
		}
		// 根据标识查找出记录
		TstudentExam entry = studentExamDao.get(TstudentExam.class, studentExam.getId());
		// 复制修改过的属性
		BeanUtils
				.copyProperties(studentExam, entry,
						new String[] { "id", "sub1Score", "sub1MakeupScore", "sub2Score", "sub2MakeupScore",
								"sub3Score", "sub3MakeupScore", "sub4Score", "sub4MakeupScore", "sub5Score",
								"sub5MakeupScore" });
		entry.setId(studentExam.getId());

		// 限制修改成绩（1：补考成绩合格则不允许修改初考成绩，2.初考成绩合格不允许修改补考成绩）
		this.updateScoreForRowEdit(entry, studentExam);
		// 修改学员进度，学员档案信息
		updateStudentFileAndProgress(entry);
		// 保存修改内容
		studentExamDao.update(entry);
		// 返回结果
		BeanUtils.copyProperties(entry, studentExam);
		return studentExam;
	}

	@Override
	public void delete(String ids) {
		// 1.判断参数是否为空
		if (ids != null) {
			// 2.对参数的字符串进行分割
			for (String id : ids.split(",")) {
				// 排除id为0的情况
				if (!id.trim().equals("0")) {
					// 3.根据ID从数据库中查找数据
					TstudentExam studentExam = studentExamDao.get(TstudentExam.class, id);
					// TODO (将删除学员所有信息)
					// 4.调用DAO层删除数据
					studentExamDao.delete(studentExam);
				}
			}

		}

	}

	public void deleteStudentExam(String ids) {
		// 1.判断参数是否为空
		if (ids != null) {
			// 2.对参数的字符串进行分割
			for (String id : ids.split(",")) {
				// 排除id为0的情况
				if (!id.trim().equals("0")) {
					// 3.根据ID从数据库中查找数据
					TstudentExam studentExam = studentExamDao.get(TstudentExam.class, id);
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
						
						studentExam.setDriverGrantFlag(0);
						studentExam.setDriverLicenseFinishFlag(0);
						studentExam.setDriverLicenseFlag(0);
						studentExam.setqGrantFlag(0);
						studentExam.setqLicenseFinishFlag(0);
						studentExam.setqLicenseFlag(0);
						
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
								// 恢复学员进度，学员档案信息
								Tprogress progerss = student.getProgress();
								if (progerss != null) {
									progerss.setState(1);
									if (ValidateUtil.isValid(progerss.getSubject1Date())) {
										progerss.setSubject1Date(null);
									}
									progressDao.update(progerss);
								}
								TstudentFile file = student.getStudentFile();
								if (file != null) {
									if (ValidateUtil.isValid(file.getTheoryDate())) {
										file.setTheoryDate(null);
										studentFileDao.update(file);
									}
								}
							}
						}
						TexamAnalys examAnalys = studentExam.getExamAnalys();
						if (examAnalys != null && examAnalys.getTotal() <= 0) {
							examAnalys.setSubject1ExamDate(null);
							examAnalys.setSubject2ExamDate(null);
							examAnalys.setSubject3ExamDate(null);
							examAnalys.setSubject4ExamDate(null);
							examAnalys.setSubject5ExamDate(null);
							examAnanlysDao.update(examAnalys);
						}
					}

				}
			}

		}

	}

	@Override
	public DataGrid dataGrid(StudentExam studentFile) throws Exception {
		// fixStudentExam();
		DataGrid dataGrid = new DataGrid();
		// 设置返回前台的数据
		dataGrid.setRows(this.changedModel(this.find(studentFile)));
		// 设置总记录数
		dataGrid.setTotal(this.total(studentFile));
		return dataGrid;
	}

	@Override
	public ImportReturnModel importDatasFromExcel(Workbook wb, String schoolArea, String operatorName,
			String examAnalysId) throws Exception {
		if (!ValidateUtil.isValid(schoolArea) || wb == null || !ValidateUtil.isValid(operatorName)
				|| !ValidateUtil.isValid(examAnalysId)) {
			return null;
		}
		ImportReturnModel model = new ImportReturnModel();
		String repeatIdentity = "";
		String notExistIdentity = "";
		boolean repeatFlag = false;
		boolean notExistFlag = false;

		List<Tstudent> reVals = new ArrayList<Tstudent>();
		// 从Excel表中读取数据
		Sheet sheet = wb.getSheetAt(0);
		int rowNum = sheet.getLastRowNum() + 1;
		// 遍历行
		Tstudent entry = null;
		for (int i = 2; i < rowNum; i++) {
			entry = new Tstudent();
			Row row = sheet.getRow(i);
			int cellNum = row.getLastCellNum();
			// 遍历列
			Cell cell = null;
			String cellValue = null;
			for (int j = 0; j < cellNum; j++) {
				cell = row.getCell(j);
				cellValue = StringUtil.getCellValueByCellType(cell);

				switch (j) {// 通过列数来判断对应插如的字段
				case 0: {
					entry.setExaminationTable(Integer.parseInt(cellValue));
				} // 序号
					break;
				case 1:// 姓名
					break;
				case 2: // 性别
					entry.setSex(cellValue);
					break;
				case 3:// 身份证
				{
					// 如果该学员在本系统中不存在，则不能导入
					if (this.getSingleStudentByIdentity(cellValue, schoolArea) == null) {
						notExistFlag = true;
						notExistIdentity = notExistIdentity + "," + cellValue;
					}
					entry.setIdentityId(cellValue);

				}
					break;
				case 4:// 报考车型
					break;
				case 5:// 原有车型
				{
					entry.setPrimaryDriver(cellValue);
				}
					break;
				case 6:// 地址
					break;
				case 7:// 编号
					entry.setOtherNo(cellValue);
					break;
				case 8:// 手机
					entry.setExamPhone(cellValue);
					break;
				}// end switch
			}
			reVals.add(entry);

		}
		// 导入数据
		if (reVals.size() > 0 && repeatFlag == false) {
			TstudentExam studentExam = null;
			Tstudent student = null;
			TexamAnalys examAnalys = examAnanlysDao.get(TexamAnalys.class, examAnalysId);
			// 遍历从excel表中导入的学员信息
			for (Tstudent stu : reVals) {

				// 根据excel中的身份证标识查找出该学员信息
				student = this.getSingleStudentByIdentity(stu.getIdentityId(), schoolArea);
				if (student != null && examAnalys != null) {

					// 如果TstudentExam对象存在则修改，如果不存在则添加
					if (student.getStudentExam() != null) {
						studentExam = student.getStudentExam();
						// 如果该学员已经参加报考则不允许导入
						if (1 == studentExam.getSub1ExamFlag()) {
							repeatFlag = true;
							repeatIdentity = repeatIdentity + "," + student.getName() + "(" + student.getIdentityId()
									+ ")";
							continue;
						}
					} else {
						studentExam = new TstudentExam();
						studentExam.setId(UUID.randomUUID().toString());
					}
					if (!repeatFlag || !notExistFlag) {

						student.setPrimaryDriver(stu.getPrimaryDriver());
						student.setExamPhone(stu.getExamPhone());
						student.setBatch(examAnalys.getBatch());
						student.setOtherNo(stu.getOtherNo());
						studentDao.update(student);

						studentExam.setBatch(examAnalys.getBatch());
						studentExam.setSchoolArea(student.getOrganization().getId());
						studentExam.setStudent(student);
						studentExam.setUpdateTime(new Date());
						studentExam.setSub1ExamFlag(TstudentExam.EXAMFALG_TRUE);// 默认报名考科目一
						studentExam.setSub1ApplyFlag(TstudentExam.EXAMFALG_TRUE);// 默认审核通过
						studentExam.setSub1MissExamFlag(0);// 默认参加考试
						studentExam.setSub1FishedFlag(0);// 默认未完成
						studentExam.setSub1EditAbleFlag(TstudentExam.EDITABLE_TRUE);
						studentExam.setImportOrder(stu.getExaminationTable());// 保证导出的顺序
						if (ValidateUtil.isValid(examAnalys.getSubject1ExamDate())) {
							studentExam.setSub1ExamDate(examAnalys.getSubject1ExamDate());
						}
						studentExamDao.saveOrUpdate(studentExam);
					}
				}
			}
		}
		// 组织返回参数
		model.setFailDatas(notExistIdentity);
		model.setSuccessDatas(repeatIdentity);
		model.setSize(reVals.size());
		if (repeatFlag || notExistFlag) {
			model.setSuccess(false);
		} else {
			model.setSuccess(true);
		}
		return model;
	}

	// 导入学员成绩信息
	public ImportReturnModel importStudentScore(Workbook wb, String schoolArea, String operatorName,
			String examAnalysId, Integer subjectType) throws Exception {
		if (!ValidateUtil.isValid(schoolArea) || wb == null || !ValidateUtil.isValid(operatorName)
				|| !ValidateUtil.isValid(examAnalysId)) {
			return null;
		}
		ImportReturnModel model = new ImportReturnModel();
		String repeatIdentity = "";// 判断身份证重复存储变量
		String notExistIdentity = "";// 身份证不存在存储变量
		boolean repeatFlag = false;// 重复标志
		boolean notExistFlag = false;// 导入的信息已经存在

		List<StudentExam> reVals = new ArrayList<StudentExam>();
		// 1. 从Excel表中读取数据
		Sheet sheet = wb.getSheetAt(0);
		int rowNum = sheet.getLastRowNum() + 1;
		// 遍历行
		StudentExam studentExam = null;
		boolean makeupFlag = false;
		// 1.1遍历行存在三个数据（学员身份证，编号，成绩）备注：如果编号为空则表示正考 ，否则表示补考
		for (int i = 2; i < rowNum; i++) {// 除去表头从第二行开始遍历
			studentExam = new StudentExam();
			Row row = sheet.getRow(i);
			int cellNum = row.getLastCellNum();
			// 遍历列
			Cell cell = null;
			String cellValue = null;
			for (int j = 0; j < cellNum; j++) {
				cell = row.getCell(j);
				cellValue = StringUtil.getCellValueByCellType(cell);

				switch (j) {// 通过列数来判断对应插如的字段
				case 0: {
					break;
				} // 序号
				case 1:// 姓名
					break;
				case 2: // 身份证
					studentExam.setIdentityId(cellValue);
					break;
				case 3:// 报考车型
					break;
				case 4:// 编号
					break;
				case 5:// 正考
					break;
				case 6:// 补考
					break;
				case 7:// 发票号(有发票号表示补考)
				{
					if (ValidateUtil.isValid(cellValue)) {
						studentExam.setRepeartExamFlag(1);
						makeupFlag = true;
					} else {
						studentExam.setRepeartExamFlag(0);
					}
				}
					break;
				case 8:// 成绩
				{
					// 用户表考试成绩单元格不为空，则表示合格，不合格
					if (ValidateUtil.isValid(cellValue)) {
						if ("不合格".equals(cellValue.trim())) {
							// 记录是正考成绩还是补考成绩
							if (!makeupFlag) {
								if (1 == subjectType) {
									studentExam.setSub1Score(2);
								} else if (2 == subjectType) {
									studentExam.setSub2Score(2);
								} else if (3 == subjectType) {
									studentExam.setSub3Score(2);
								} else if (4 == subjectType) {
									studentExam.setSub4Score(2);
								} else if (5 == subjectType) {
									studentExam.setSub5Score(2);
								}
							} else {
								if (1 == subjectType) {
									studentExam.setSub1MakeupScore(2);
								} else if (2 == subjectType) {
									studentExam.setSub2MakeupScore(2);
								} else if (3 == subjectType) {
									studentExam.setSub3MakeupScore(2);
								} else if (4 == subjectType) {
									studentExam.setSub4MakeupScore(2);
								} else if (5 == subjectType) {
									studentExam.setSub5MakeupScore(2);
								}
							}

						} else {// 用户表考试成绩单元格为空，则表示合格
							if (1 == subjectType) {
								studentExam.setSub1Score(1);
							} else if (2 == subjectType) {
								studentExam.setSub2Score(1);
							} else if (3 == subjectType) {
								studentExam.setSub3Score(1);
							} else if (4 == subjectType) {
								studentExam.setSub4Score(1);
							} else if (5 == subjectType) {
								studentExam.setSub5Score(1);
							}
						}
					} else {// 空表示合格
						if (!makeupFlag) {
							if (1 == subjectType) {
								studentExam.setSub1Score(1);
							} else if (2 == subjectType) {
								studentExam.setSub2Score(1);
							} else if (3 == subjectType) {
								studentExam.setSub3Score(1);
							} else if (4 == subjectType) {
								studentExam.setSub4Score(1);
							} else if (5 == subjectType) {
								studentExam.setSub5Score(1);
							}
						} else {// 补考成绩
							if (1 == subjectType) {
								studentExam.setSub1MakeupScore(1);
							} else if (2 == subjectType) {
								studentExam.setSub2MakeupScore(1);
							} else if (3 == subjectType) {
								studentExam.setSub3MakeupScore(1);
							} else if (4 == subjectType) {
								studentExam.setSub4MakeupScore(1);
							} else if (5 == subjectType) {
								studentExam.setSub5MakeupScore(1);
							}
						}

					}
					makeupFlag = false;
				}
					break;
				}// end switch
			}
			reVals.add(studentExam);

		}
		// 2.根据考试学员身份证修改考试成绩信息
		if (ValidateUtil.isValid(reVals)) {
			TstudentExam studentExamTemp = null;
			Tstudent student = null;
			TexamAnalys examAnalys = examAnanlysDao.get(TexamAnalys.class, examAnalysId);
			// 遍历从excel表中导入的学员信息
			if (examAnalys != null) {
				// 补考员号
				for (StudentExam entry : reVals) {
					// 较验该学员是否为本批次
					// 根据excel中的身份证标识查找出该学员信息
					student = this.getSingleStudentByIdentity(entry.getIdentityId(), schoolArea);
					if (student != null) {

						studentExamTemp = this.getByStudentIdentityId(schoolArea, student.getId());
						// 已参加考试学员
						if (studentExamTemp != null) {
							// 如果是非本批次考试学员，则移动当前学员到本批次
							if (examAnalys.getBatch() != studentExamTemp.getBatch()) {
								studentExamTemp.setExamAnalys(examAnalys);
								studentExamTemp.setBatch(examAnalys.getBatch());
							}
							// 修改成绩
							if (1 == entry.getRepeartExamFlag()) {// 补考成绩
								if (1 == subjectType) {
									studentExamTemp.setSub1MakeupScore(entry.getSub1MakeupScore());
									studentExamTemp.setSub1Score(2);
									studentExamTemp.setSub1FishedFlag(TstudentExam.EXAMFALG_TRUE);
								} else if (2 == subjectType) {
									studentExamTemp.setSub2MakeupScore(entry.getSub2MakeupScore());
									studentExamTemp.setSub2Score(2);
									studentExamTemp.setSub2FishedFlag(TstudentExam.EXAMFALG_TRUE);
								} else if (3 == subjectType) {
									studentExamTemp.setSub3MakeupScore(entry.getSub3MakeupScore());
									studentExamTemp.setSub3Score(2);
									studentExamTemp.setSub3FishedFlag(TstudentExam.EXAMFALG_TRUE);
								} else if (4 == subjectType) {
									studentExamTemp.setSub4MakeupScore(entry.getSub4MakeupScore());
									studentExamTemp.setSub4Score(2);
									studentExamTemp.setSub4FishedFlag(TstudentExam.EXAMFALG_TRUE);
								} else if (5 == subjectType) {
									studentExamTemp.setSub5MakeupScore(entry.getSub5MakeupScore());
									studentExamTemp.setSub5Score(2);
									studentExamTemp.setSub5FishedFlag(TstudentExam.EXAMFALG_TRUE);
								}
							} else {// 正考成绩
								if (1 == subjectType) {
									studentExamTemp.setSub1Score(entry.getSub1Score());
									studentExamTemp.setSub1FishedFlag(TstudentExam.EXAMFALG_TRUE);
									studentExamTemp.setSub1MakeupScore(null);
								} else if (2 == subjectType) {
									studentExamTemp.setSub2Score(entry.getSub2Score());
									studentExamTemp.setSub2FishedFlag(TstudentExam.EXAMFALG_TRUE);
									studentExamTemp.setSub2MakeupScore(null);
								} else if (3 == subjectType) {
									studentExamTemp.setSub3Score(entry.getSub3Score());
									studentExamTemp.setSub3FishedFlag(TstudentExam.EXAMFALG_TRUE);
									studentExamTemp.setSub3MakeupScore(null);
								} else if (4 == subjectType) {
									studentExamTemp.setSub4Score(entry.getSub4Score());
									studentExamTemp.setSub4FishedFlag(TstudentExam.EXAMFALG_TRUE);
									studentExamTemp.setSub4MakeupScore(null);
								} else if (5 == subjectType) {
									studentExamTemp.setSub5Score(entry.getSub5Score());
									studentExamTemp.setSub5FishedFlag(TstudentExam.EXAMFALG_TRUE);
									studentExamTemp.setSub5MakeupScore(null);
								}
							}
							studentExamDao.update(studentExamTemp);
						} else {// 学员为空，表示该学员没参加考试
							// 记录非法数据，提示用户
							if (ValidateUtil.isValid(notExistIdentity)) {
								notExistIdentity = notExistIdentity + "," + entry.getIdentityId();
							} else {
								notExistIdentity = notExistIdentity + entry.getIdentityId();
							}

						}
					} // 学员为空，非本系统学员
				}
			}
		}
		// 组织返回参数
		model.setFailDatas(notExistIdentity);
		model.setSuccessDatas(repeatIdentity);
		model.setSize(reVals.size());
		if (repeatFlag || notExistFlag) {
			model.setSuccess(false);
		} else {
			model.setSuccess(true);
		}
		return model;
	}

	// 从后台选择学员输入考试批次中
	public boolean addStudentToExamAnalys(StudentExam studentExam) throws Exception {
		if (!ValidateUtil.isValid(studentExam.getIds()) || !ValidateUtil.isValid(studentExam.getExamAnalysId())) {
			return false;
		}
		// 获取批次信息
		TexamAnalys eTexamAnalys = examAnanlysDao.get(TexamAnalys.class, studentExam.getExamAnalysId());
		// 根据学员标识查找出学员信息
		Tstudent student = null;
		for (String id : studentExam.getIds().split(",")) {
			// 排除id为0的情况
			if (!id.trim().equals("0")) {
				student = studentDao.get(Tstudent.class, id);
			}
			TstudentExam studentExamObj = null;
			if (student != null && eTexamAnalys != null) {
				student.setBatch(studentExam.getStudentBatch());
				student.setExamPhone(student.getPhone());
				studentDao.update(student);
				if (student.getStudentExam() != null) {
					studentExamObj = student.getStudentExam();

				} else {
					studentExamObj = new TstudentExam();
					studentExamObj.setId(UUID.randomUUID().toString());
				}
				// 修改考试时间
				if (ValidateUtil.isValid(eTexamAnalys.getSubject1ExamDate())) {
					studentExamObj.setSub1ExamDate(eTexamAnalys.getSubject1ExamDate());
				}

				studentExamObj.setBatch(studentExam.getStudentBatch());
				studentExamObj.setStudent(student);
				studentExamObj.setSchoolArea(student.getOrganization().getId());
				studentExamObj.setUpdateTime(new Date());
				studentExamObj.setSub1ExamFlag(TstudentExam.EXAMFALG_TRUE);
				studentExamObj.setSub1ApplyFlag(1);// 审批默认通过
				studentExamObj.setSub1MissExamFlag(0);// 默认参加考试
				studentExamObj.setSub1FishedFlag(0);// 默认未完成
				studentExamObj.setSub1EditAbleFlag(TstudentExam.EDITABLE_TRUE);
				studentExam.setExamAnalys(eTexamAnalys);// 关联关系

				// 修改学员进度，修改学员档案
				Tprogress progress = student.getProgress();
				if (progress != null) {
					progress.setState(Tprogress.PROGRESS_SUBJECT1);
					progressDao.update(progress);
				}
				TstudentFile file = student.getStudentFile();
				if (file != null) {
					if (ValidateUtil.isValid(eTexamAnalys.getSubject1ExamDate())) {
						file.setTheoryDate(eTexamAnalys.getSubject1ExamDate());
						studentFileDao.update(file);
					}

				}

				studentExamDao.saveOrUpdate(studentExamObj);
			}
		}

		return true;
	}

	// 移动学员到目标批次
	@Override
	public boolean saveStudentToNewBatch(StudentExam studentExam) throws Exception {
		if (!ValidateUtil.isValid(studentExam.getExamAnalysId())) {
			return false;
		}
		TexamAnalys examAnalys = examAnanlysDao.get(TexamAnalys.class, studentExam.getExamAnalysId());
		if (examAnalys != null) {
			// 1.判断参数是否为空
			if (studentExam.getIds() != null) {
				// 2.对参数的字符串进行分割
				TstudentExam entry = null;
				for (String id : studentExam.getIds().split(",")) {
					entry = studentExamDao.get(TstudentExam.class, id);
					if (entry != null) {
						// 移动学员到目标批次
						Tstudent student = entry.getStudent();
						if (student != null) {
							student.setBatch(examAnalys.getBatch());
							studentDao.update(student);
						}
						entry.setBatch(examAnalys.getBatch());
						entry.setExamAnalys(examAnalys);
						// 移动后修改考试时间，移动前该条记录已经考试完的科目不修改考试时间
						if (ValidateUtil.isValid(entry.getSub1FishedFlag())
								&& ValidateUtil.isValid(entry.getSub1ApplyFlag()) && 1 == entry.getSub1ApplyFlag()
								&& 1 != entry.getSub1FishedFlag()
								&& ValidateUtil.isValid(examAnalys.getSubject1ExamDate())) {
							entry.setSub1ExamDate(examAnalys.getSubject1ExamDate());
						}
						if (ValidateUtil.isValid(entry.getSub2FishedFlag())
								&& ValidateUtil.isValid(entry.getSub2ApplyFlag()) && 1 == entry.getSub2ApplyFlag()
								&& 1 != entry.getSub2FishedFlag()
								&& ValidateUtil.isValid(examAnalys.getSubject2ExamDate())) {
							entry.setSub2ExamDate(examAnalys.getSubject2ExamDate());
						}
						if (ValidateUtil.isValid(entry.getSub3FishedFlag())
								&& ValidateUtil.isValid(entry.getSub3ApplyFlag()) && 1 == entry.getSub3ApplyFlag()
								&& 1 != entry.getSub3FishedFlag()
								&& ValidateUtil.isValid(examAnalys.getSubject3ExamDate())) {
							entry.setSub3ExamDate(examAnalys.getSubject3ExamDate());
						}
						if (ValidateUtil.isValid(entry.getSub4FishedFlag())
								&& ValidateUtil.isValid(entry.getSub4ApplyFlag()) && 1 == entry.getSub4ApplyFlag()
								&& 1 != entry.getSub4FishedFlag()
								&& ValidateUtil.isValid(examAnalys.getSubject4ExamDate())) {
							entry.setSub4ExamDate(examAnalys.getSubject4ExamDate());
						}
						if (ValidateUtil.isValid(entry.getSub5FishedFlag())
								&& ValidateUtil.isValid(entry.getSub5ApplyFlag()) && 1 == entry.getSub5ApplyFlag()
								&& 1 != entry.getSub5FishedFlag()
								&& ValidateUtil.isValid(examAnalys.getSubject5ExamDate())) {
							entry.setSub5ExamDate(examAnalys.getSubject5ExamDate());
						}
						studentExamDao.update(entry);

					}
				}
				return true;
			}
		}
		return false;
	}

	// 批量修改学员考试信息
	public void batchHandupExam(StudentExam model) throws Exception {
		if (!ValidateUtil.isValid(model.getIds()) || !ValidateUtil.isValid(model.getSubjectType())
				|| !ValidateUtil.isValid(model.getBatchFlag()) || !ValidateUtil.isValid(model.getExamAnalysId())) {
			return;
		}
		TexamAnalys exmAnalys = examAnanlysDao.get(TexamAnalys.class, model.getExamAnalysId());
		TstudentExam entry = null;
		for (String id : model.getIds().split(",")) {
			entry = studentExamDao.get(TstudentExam.class, id);
			// ==================== 批量报考（默认修改报考成功，并修改审批状态为通过）===========
			if (1 == model.getBatchFlag()) {// 报考科目
				switch (model.getSubjectType()) {
				case 1: {
					entry.setSub1ExamFlag(TstudentExam.EXAMFALG_TRUE);
					entry.setSub1ApplyFlag(TstudentExam.EXAMFALG_TRUE);
					// 修改考试日期
					if (ValidateUtil.isValid(exmAnalys.getSubject1ExamDate())) {
						entry.setSub1ExamDate(exmAnalys.getSubject1ExamDate());
					}
				}
					break;
				case 2: {// 考试科目1合格对能报考科目2,缺考不能参加一下科考试
					if (entry.getSub1FishedFlag() != null && entry.getSub1MissExamFlag() != null
							&& 1 == entry.getSub1FishedFlag() && 1 != entry.getSub1MissExamFlag()) {
						entry.setSub2ExamFlag(TstudentExam.EXAMFALG_TRUE);
						entry.setSub2ApplyFlag(TstudentExam.EXAMFALG_TRUE);
						entry.setSub2FishedFlag(0);
						entry.setSub2MissExamFlag(0);
						// 标记科目一不能修改成绩
						entry.setSub1EditAbleFlag(TstudentExam.EDITABLE_TRUE);
						entry.setSub2EditAbleFlag(TstudentExam.EDITABLE_TRUE);
						// 修改考试日期
						if (ValidateUtil.isValid(exmAnalys.getSubject2ExamDate())) {
							entry.setSub2ExamDate(exmAnalys.getSubject2ExamDate());
						}

					}
				}
					break;
				case 3: {// 考试科目1合格对能报考科目3，缺考不能参加一下科考试
					if (entry.getSub1FishedFlag() != null && entry.getSub1MissExamFlag() != null
							&& 1 == entry.getSub1FishedFlag() && 1 != entry.getSub1MissExamFlag()) {
						entry.setSub3ExamFlag(TstudentExam.EXAMFALG_TRUE);
						entry.setSub3ApplyFlag(TstudentExam.EXAMFALG_TRUE);
						entry.setSub3FishedFlag(0);
						entry.setSub3MissExamFlag(0);
						// 标记科目二不能修改成绩
						entry.setSub2EditAbleFlag(TstudentExam.EDITABLE_TRUE);
						entry.setSub3EditAbleFlag(TstudentExam.EDITABLE_TRUE);
						// 修改考试日期
						if (ValidateUtil.isValid(exmAnalys.getSubject3ExamDate())) {
							entry.setSub3ExamDate(exmAnalys.getSubject3ExamDate());
						}
					}
				}
					break;
				case 4: {// 考试科目2,3合格对能报考科目4，缺考不能参加一下科考试
					if (entry.getSub2FishedFlag() != null && entry.getSub2MissExamFlag() != null
							&& 1 == entry.getSub2FishedFlag() && 1 != entry.getSub2MissExamFlag()
							&& entry.getSub3FishedFlag() != null && entry.getSub3MissExamFlag() != null
							&& 1 == entry.getSub3FishedFlag() && 1 != entry.getSub3MissExamFlag()) {
						entry.setSub4ExamFlag(TstudentExam.EXAMFALG_TRUE);
						entry.setSub4ApplyFlag(TstudentExam.EXAMFALG_TRUE);
						entry.setSub4FishedFlag(0);
						entry.setSub4MissExamFlag(0);
						// 标记科目三不能修改成绩
						entry.setSub2EditAbleFlag(TstudentExam.EDITABLE_FALSE);
						entry.setSub3EditAbleFlag(TstudentExam.EDITABLE_FALSE);
						entry.setSub4EditAbleFlag(TstudentExam.EDITABLE_TRUE);
						// 修改考试日期
						if (ValidateUtil.isValid(exmAnalys.getSubject4ExamDate())) {
							entry.setSub4ExamDate(exmAnalys.getSubject4ExamDate());
						}
					}
				}
					break;
				case 5: {// 考试科目4合格对能报考科目5，缺考不能参加一下科考试
					if (entry.getSub4FishedFlag() != null && entry.getSub4MissExamFlag() != null
							&& 1 == entry.getSub4FishedFlag() && 1 != entry.getSub4MissExamFlag()) {
						entry.setSub5ExamFlag(TstudentExam.EXAMFALG_TRUE);
						entry.setSub5ApplyFlag(TstudentExam.EXAMFALG_TRUE);
						entry.setSub5FishedFlag(0);
						entry.setSub5MissExamFlag(0);
						// 标记科目四不能修改成绩
						entry.setSub4EditAbleFlag(TstudentExam.EDITABLE_FALSE);
						entry.setSub5EditAbleFlag(TstudentExam.EDITABLE_TRUE);
						// 修改考试日期
						if (ValidateUtil.isValid(exmAnalys.getSubject5ExamDate())) {
							entry.setSub5ExamDate(exmAnalys.getSubject5ExamDate());
						}
					}
				}
					break;

				}
				// 修改学员进度，学员档案
				updateStudentFileAndProgress(entry);
				// ==========批量修改成绩=======================
			} else if (2 == model.getBatchFlag()) {// 批量修改成绩
				this.batchUpdateScore(entry, model);
			}
			studentExamDao.update(entry);
		} // end for
	}

	// 批量取消科目报考
	public boolean batchCancelHandup(String ids, Integer subjectType) throws Exception {
		if (!ValidateUtil.isValid(ids) || !ValidateUtil.isValid(subjectType)) {
			return false;
		}
		TstudentExam studentExam = null;
		for (String id : ids.split(",")) {
			studentExam = studentExamDao.get(TstudentExam.class, id);
			if (studentExam != null) {
				switch (subjectType) {
				case 1: {// 如果本科目已经出成绩，则不允许取消报考
					if (ValidateUtil.isValid(studentExam.getSub1EditAbleFlag())
							&& 1 != studentExam.getSub1EditAbleFlag()) {
						studentExam.setSub1ExamFlag(0);
						studentExam.setSub1ExamDate(null);
						studentExam.setSub1FishedFlag(0);
						studentExam.setSub1ApplyFlag(0);
						studentExam.setSub1Score(null);
						studentExam.setSub1MakeupScore(null);
						studentExam.setSub1MakeupTimes(0);
						studentExam.setSub1MakeupDate(null);
						studentExam.setSub1EditAbleFlag(TstudentExam.EDITABLE_TRUE);
						studentExam.setSub2EditAbleFlag(TstudentExam.EDITABLE_TRUE);
					}

				}
					break;
				case 2: {
					if (ValidateUtil.isValid(studentExam.getSub2EditAbleFlag())
							&& 1 != studentExam.getSub2EditAbleFlag()) {
						studentExam.setSub2ExamFlag(0);
						studentExam.setSub2ExamDate(null);
						studentExam.setSub2FishedFlag(0);
						studentExam.setSub2ApplyFlag(0);
						studentExam.setSub2Score(null);
						studentExam.setSub2MakeupScore(null);
						studentExam.setSub2MakeupTimes(0);
						studentExam.setSub2MakeupDate(null);
						studentExam.setSub1EditAbleFlag(TstudentExam.EDITABLE_TRUE);
						studentExam.setSub2EditAbleFlag(TstudentExam.EDITABLE_TRUE);
					}
				}
					break;
				case 3: {
					if (ValidateUtil.isValid(studentExam.getSub3EditAbleFlag())
							&& 1 != studentExam.getSub3EditAbleFlag()) {
						studentExam.setSub3ExamFlag(0);
						studentExam.setSub3ExamDate(null);
						studentExam.setSub3FishedFlag(0);
						studentExam.setSub3ApplyFlag(0);
						studentExam.setSub3Score(null);
						studentExam.setSub3MakeupScore(null);
						studentExam.setSub3MakeupTimes(0);
						studentExam.setSub3MakeupDate(null);
						studentExam.setSub2EditAbleFlag(TstudentExam.EDITABLE_TRUE);
						studentExam.setSub3EditAbleFlag(TstudentExam.EDITABLE_TRUE);
					}
				}
					break;
				case 4: {
					if (ValidateUtil.isValid(studentExam.getSub4EditAbleFlag())
							&& 1 != studentExam.getSub4EditAbleFlag()) {
						studentExam.setSub4ExamFlag(0);
						studentExam.setSub4ExamDate(null);
						studentExam.setSub4FishedFlag(0);
						studentExam.setSub4ApplyFlag(0);
						studentExam.setSub4Score(null);
						studentExam.setSub4MakeupScore(null);
						studentExam.setSub4MakeupTimes(0);
						studentExam.setSub4MakeupDate(null);
						studentExam.setSub3EditAbleFlag(TstudentExam.EDITABLE_TRUE);
						studentExam.setSub4EditAbleFlag(TstudentExam.EDITABLE_TRUE);
					}
				}
					break;
				case 5: {
					if (ValidateUtil.isValid(studentExam.getSub5EditAbleFlag())
							&& 1 != studentExam.getSub5EditAbleFlag()) {
						studentExam.setSub5ExamFlag(0);
						studentExam.setSub5ExamDate(null);
						studentExam.setSub5FishedFlag(0);
						studentExam.setSub5ApplyFlag(0);
						studentExam.setSub5Score(null);
						studentExam.setSub5MakeupScore(null);
						studentExam.setSub5MakeupTimes(0);
						studentExam.setSub5MakeupDate(null);
						studentExam.setSub4EditAbleFlag(TstudentExam.EDITABLE_TRUE);
						studentExam.setSub5EditAbleFlag(TstudentExam.EDITABLE_TRUE);
					}
				}
					break;
				}
				// 回退学员进度，修改学员档案
				roolbackStudentFileAndProgress(studentExam, subjectType);
				studentExamDao.update(studentExam);
			}
		} // end for
		return true;
	}

	// 根据学员信息查找学员考试信息（累加查询）
	public StudentExam getStudentExamForRepeatSearch(String schoolArea, String studentNo, String studentName,
			String studentIdentityId) throws Exception {
		if (!ValidateUtil.isValid(schoolArea)) {
			return null;
		}
		// 1.根据用户输入的学员信息，查找出学员信息
		Tstudent student = null;
		TstudentExam studentExam = null;
		StudentExam retEntry = null;
		List<Object> params = new ArrayList<Object>();
		String sql = " from Tstudent t where 1=1";
		if (ValidateUtil.isValid(schoolArea)) {
			sql += " and t.organization.id = ?";
			params.add(schoolArea);
		}
		if (ValidateUtil.isValid(studentNo)) {
			sql += " and t.studentNo like ?";
			params.add(studentNo);
		}
		if (ValidateUtil.isValid(studentName)) {
			sql += " and t.name = ? ";
			params.add("%%" + studentName + "%%");
		}
		if (ValidateUtil.isValid(studentIdentityId)) {
			sql += " and t.identityId = ?";
			params.add(studentIdentityId);
		}
		List<Tstudent> studentLists = studentDao.find(sql, params);
		if (ValidateUtil.isValid(studentLists)) {
			student = studentLists.get(0);
		}
		// 2.根据学员标识，查找出考试学员信息，并返回结果
		if (student != null) {
			studentExam = studentExamDao.get(TstudentExam.class, student.getStudentExam().getId());
			if (studentExam != null) {
				retEntry = new StudentExam();
				BeanUtils.copyProperties(studentExam, retEntry);
				return retEntry;
			}
		}

		return null;
	}

	/**
	 * 从数据库存中查找出该实体信息并将此实体类信息转换成action专用的实体类信息，即模型转换
	 * 
	 * @param students
	 * @return
	 */
	private List<StudentExam> changedModel(List<TstudentExam> studentExams) {
		List<StudentExam> files = new ArrayList<StudentExam>();
		if (studentExams != null && studentExams.size() > 0) {
			for (TstudentExam temp : studentExams) {
				StudentExam entry = new StudentExam();
				BeanUtils.copyProperties(temp, entry);
				// 给页面展现学员属性信息
				Tstudent student = temp.getStudent();
				if (student != null) {
					entry.setStudentId(student.getId());
					entry.setStudentName(student.getName());
					entry.setStudentSex(student.getSex());
					entry.setStudentCreateTime(student.getCreateTime());
					entry.setStudentIdentityId(student.getIdentityId());
					entry.setStudentOrganization(student.getOrganization().getId());
					entry.setStudentAddress(student.getAddress());
					entry.setStudentBirthdate(student.getBirthdate());
					entry.setStudentPhone(student.getPhone());
					entry.setStudentEmail(student.getEmail());
					entry.setStudentTelephone(student.getTelephone());
					entry.setStudentMailCode(student.getMailCode());
					entry.setStudentContry(student.getContry());
					entry.setStudentNativeNation(student.getNativeNation());
					entry.setStudentResidenceId(student.getResidenceId());
					entry.setStudentResidenceAddr(student.getResidenceAddr());
					entry.setStudentImageId(student.getImageId());
					entry.setStudentNowstate(student.getNowState());
					if (student.getClazz() != null) {
						entry.setStudentClazz(student.getClazz().getName());
					}
					entry.setStudentApplyType(student.getApplyType());
					entry.setStudentDriverType(student.getDriverType());
					entry.setSchoolArea(student.getOrganization().getId());
					entry.setSchoolAreaName(student.getOrganization().getName());
					entry.setStudentNo(student.getStudentNo());
					entry.setStudentFeed(student.getFeed());
					entry.setStudentRealFeed(student.getRealFeed());
					entry.setStudentOwnFeed(student.getOwnFeed());
					entry.setComment(temp.getComment());

					entry.setPrimaryDriver(student.getPrimaryDriver());
					entry.setExamPhone(student.getExamPhone());
					entry.setStudentBatch(student.getBatch());
					entry.setOtherNo(student.getOtherNo());
					files.add(entry);

				}

			}
		}
		return files;
	}

	/**
	 * 根据action专用实体类的信息查询所有dao专用的实体类信息
	 * 
	 * @param student
	 * @return
	 */
	private List<TstudentExam> find(StudentExam studentExams) {
		String hql = "from TstudentExam t where 1=1 ";
		List<Object> paramList = new ArrayList<Object>();
		hql = this.addWhere(studentExams, hql, paramList);
		if (studentExams.getSort() != null && studentExams.getOrder() != null) {
			hql += " order by " + studentExams.getSort() + " " + studentExams.getOrder();
		}
		return studentExamDao.find(hql, paramList, studentExams.getPage(), studentExams.getRows());
	}

	/**
	 * 统计符合当前查询条件的总记录数
	 * 
	 * @param student
	 * @return
	 */
	private Long total(StudentExam studentExam) {
		// 拼接查询条件
		String hql = "select count(*) from TstudentExam t where 1=1 ";
		List<Object> paramsList = new ArrayList<Object>();
		hql = this.addWhere(studentExam, hql, paramsList);
		return studentExamDao.count(hql, paramsList);
	}

	/**
	 * 添加查询约束条件
	 * 
	 * @param studentExam
	 * @param hql
	 * @param params
	 * @return
	 */
	private String addWhere(StudentExam studentExam, String hql, List<Object> params) {
		// 姓名模糊查询
		if (studentExam.getStudentName() != null

				&& !studentExam.getStudentName().trim().equals("")) {
			hql += " and t.student.name like ?";
			params.add("%%" + studentExam.getStudentName().trim() + "%%");
		}
		// 身份证精确查询
		if (studentExam.getStudentIdentityId() != null && !studentExam.getStudentIdentityId().trim().equals("")) {
			hql += " and t.student.identityId =?";
			params.add(studentExam.getStudentIdentityId().trim());
		}
		// 所属校区精确查询
		if (studentExam.getSchoolArea() != null && !studentExam.getSchoolArea().trim().equals("")) {
			hql += " and t.schoolArea =?";
			params.add(studentExam.getSchoolArea().trim());
		}
		// 学员编号查询
		if (ValidateUtil.isValid(studentExam.getStudentNo())) {
			hql += " and t.student.studentNo like ?";
			params.add("%" + studentExam.getStudentNo().trim());
		}
		if (ValidateUtil.isValid(studentExam.getBatch())) {
			hql += " and t.batch =?";
			params.add(studentExam.getBatch());
		}
		// 科目查询（）查找出已经报考该科目的学员信息）
		if (ValidateUtil.isValid(studentExam.getSubjectType())) {
			if (1 == studentExam.getSubjectType()) {
				hql += " and t.sub1ExamFlag =?";
				params.add(TstudentExam.EXAMPASS_TRUE);
			} else if (2 == studentExam.getSubjectType()) {
				hql += " and t.sub2ExamFlag =?";
				params.add(TstudentExam.EXAMPASS_TRUE);
			} else if (3 == studentExam.getSubjectType()) {
				hql += " and t.sub3ExamFlag =?";
				params.add(TstudentExam.EXAMPASS_TRUE);
			} else if (4 == studentExam.getSubjectType()) {
				hql += " and t.sub4ExamFlag =?";
				params.add(TstudentExam.EXAMPASS_TRUE);
			} else if (5 == studentExam.getSubjectType()) {
				hql += " and t.sub5ExamFlag =?";
				params.add(TstudentExam.EXAMPASS_TRUE);
			}
		}
		// 成绩查询(同时输入科目类型，成绩结果方可查询)
		if (ValidateUtil.isValid(studentExam.getExamScore()) && ValidateUtil.isValid(studentExam.getSubjectType())) {
			if (1 == studentExam.getSubjectType()) {
				hql += " and t.sub1Score =?";
				params.add(studentExam.getExamScore());
			} else if (2 == studentExam.getSubjectType()) {
				hql += " and t.sub2Score =?";
				params.add(studentExam.getExamScore());
			} else if (3 == studentExam.getSubjectType()) {
				hql += " and t.sub3Score =?";
				params.add(studentExam.getExamScore());
			} else if (4 == studentExam.getSubjectType()) {
				hql += " and t.sub4Score =?";
				params.add(studentExam.getExamScore());
			} else if (5 == studentExam.getSubjectType()) {
				hql += " and t.sub5Score =?";
				params.add(studentExam.getExamScore());
			}
		}
		// 根据科目类型过虑数据
		if (ValidateUtil.isValid(studentExam.getDatagridSubjectType())) {
			if (1 == studentExam.getDatagridSubjectType()) {
				hql += " and t.sub1ExamFlag =?";
				params.add(TstudentExam.EXAMPASS_TRUE);
			} else if (2 == studentExam.getDatagridSubjectType()) {
				hql += " and t.sub2ExamFlag =?";
				params.add(TstudentExam.EXAMPASS_TRUE);
			} else if (3 == studentExam.getDatagridSubjectType()) {
				hql += " and t.sub3ExamFlag =?";
				params.add(TstudentExam.EXAMPASS_TRUE);
			} else if (4 == studentExam.getDatagridSubjectType()) {
				hql += " and t.sub4ExamFlag =?";
				params.add(TstudentExam.EXAMPASS_TRUE);
			} else if (5 == studentExam.getDatagridSubjectType()) {
				hql += " and t.sub5ExamFlag =?";
				params.add(TstudentExam.EXAMPASS_TRUE);
			}
		}
		// 学员驾驶证，资格证，状态查询
		if (ValidateUtil.isValid(studentExam.getDriverLicenseFlag())) {
			hql += " and t.driverLicenseFlag =?";
			params.add(studentExam.getDriverLicenseFlag());
		}
		if (ValidateUtil.isValid(studentExam.getQualificationFlag())) {
			hql += " and t.qLicenseFlag =?";
			params.add(studentExam.getQualificationFlag());
		}
		if (ValidateUtil.isValid(studentExam.getDriverGrantFlag())) {
			hql += " and t.driverGrantFlag =?";
			params.add(studentExam.getDriverGrantFlag());
		}
		if (ValidateUtil.isValid(studentExam.getqGrantFlag())) {
			hql += " and t.qGrantFlag =?";
			params.add(studentExam.getqGrantFlag());
		}
		if (ValidateUtil.isValid(studentExam.getDriverLicenseFinishFlag())) {
			hql += " and t.driverLicenseFinishFlag =?";
			params.add(studentExam.getDriverLicenseFinishFlag());
		}
		if (ValidateUtil.isValid(studentExam.getQualificationFinishFlag())) {
			hql += " and t.qLicenseFinishFlag =?";
			params.add(studentExam.getQualificationFinishFlag());
		}

		// TODO
		return hql;
	}

	// 修复考试学员列表数据
	private void fixStudentExam() {
		String sql = "select * from tb_student where 1=?";
		List<Tstudent> students = studentDao.findAllBySQL(Tstudent.class, sql, 1);
		TstudentExam studentExam = null;
		if (ValidateUtil.isValidListObject(students)) {
			for (Tstudent stu : students) {
				studentExam = new TstudentExam();
				studentExam.setId(UUID.randomUUID().toString());
				studentExam.setSchoolArea(stu.getOrganization().getId());
				studentExam.setStudent(stu);

				studentExam.setBatch(null);
				studentExam.setStudent(stu);
				studentExam.setSchoolArea(stu.getOrganization().getId());
				studentExam.setUpdateTime(new Date());
				studentExam.setSub1ExamFlag(TstudentExam.EXAMFALG_TRUE);
				studentExam.setSub1ApplyFlag(1);// 审批默认通过
				studentExam.setSub1MissExamFlag(0);// 默认参加考试
				studentExam.setSub1FishedFlag(0);// 默认未完成
				studentExam.setSub1EditAbleFlag(TstudentExam.EXAMFALG_FALSE);
				studentExam.setSub2ExamFlag(TstudentExam.EXAMFALG_TRUE);
				studentExam.setSub2ApplyFlag(0);// 审批默认通过
				studentExam.setSub2MissExamFlag(0);// 默认参加考试
				studentExam.setSub2FishedFlag(0);// 默认未完成
				studentExam.setSub2EditAbleFlag(TstudentExam.EDITABLE_TRUE);
				studentExam.setSub3ExamFlag(TstudentExam.EXAMFALG_FALSE);
				studentExam.setSub3ApplyFlag(0);// 审批默认通过
				studentExam.setSub3MissExamFlag(0);// 默认参加考试
				studentExam.setSub3FishedFlag(0);// 默认未完成
				studentExam.setSub3EditAbleFlag(TstudentExam.EDITABLE_TRUE);
				studentExam.setSub4ExamFlag(TstudentExam.EXAMFALG_FALSE);
				studentExam.setSub4ApplyFlag(0);// 审批默认通过
				studentExam.setSub4MissExamFlag(0);// 默认参加考试
				studentExam.setSub4FishedFlag(0);// 默认未完成
				studentExam.setSub4EditAbleFlag(TstudentExam.EDITABLE_TRUE);
				studentExam.setSub5ExamFlag(TstudentExam.EXAMFALG_FALSE);
				studentExam.setSub5ApplyFlag(0);// 审批默认通过
				studentExam.setSub5MissExamFlag(0);// 默认参加考试
				studentExam.setSub5FishedFlag(0);// 默认未完成
				studentExam.setSub5EditAbleFlag(TstudentExam.EDITABLE_TRUE);
				studentExam.setExamAnalys(null);// 关联关系

				studentExamDao.saveOrUpdate(studentExam);
			}
		}
		System.out.println("修改成功，共修复" + students.size() + "条记录");
	}

	private Tstudent getSingleStudentByIdentity(String identityId, String schoolArea) {
		if (!ValidateUtil.isValid(schoolArea) || !ValidateUtil.isValid(identityId)) {
			return null;
		}
		String sql = "select * from tb_student where organizationId=? and identityId=?";
		List<Tstudent> students = studentDao.findAllBySQL(Tstudent.class, sql, schoolArea, identityId);
		if (ValidateUtil.isValidListObject(students)) {
			return students.get(0);
		}
		return null;
	}

	// 行修改时，修改各科目成绩，并修改科目完成状态
	/**
	 * 
	 * @param entry
	 * @param studentExam
	 * @param subjectFlag
	 * @param invokeFlag
	 *            (1:表示选编辑调用，2：表示批量修改成绩调用)
	 */
	private void updateScoreForRowEdit(TstudentExam entry, StudentExam studentExam) {
		Tstudent student = entry.getStudent();
		// 科目一
		// 校验：通过审批且不缺考，则修改科目成绩，否则返回
		if (ValidateUtil.isValid(entry.getSub1ApplyFlag()) || ValidateUtil.isValid(entry.getSub1MissExamFlag())) {
			if (1 != entry.getSub1ApplyFlag() && 1 != entry.getSub1MissExamFlag()) {
				return;
			}
			// 已经报考下一科后，不能修改当前科目成绩
			if (ValidateUtil.isValid(entry.getSub1EditAbleFlag())) {
				if (TstudentExam.EDITABLE_FALSE != entry.getSub1EditAbleFlag()) {
					// 科目1
					if (ValidateUtil.isValid(studentExam.getSub1Score())) {
						// 1用户输入值为合格(初考成绩－－〉合格)
						if (TstudentExam.EXAMPASS_TRUE == studentExam.getSub1Score()) {
							// 1.1如果原初考成绩有效，
							if (ValidateUtil.isValid(entry.getSub1Score())) {
								// 1.1.1如果原补考成绩有效，则不能修改初考值
								if (ValidateUtil.isValid(entry.getSub1MakeupScore())) {
									entry.setSub1Score(entry.getSub1Score());
									entry.setSub1FishedFlag(entry.getSub1FishedFlag());
								} else {// 1.1.2如果原补考成绩无效，则修改初考值（初考成绩－－〉合格）
									entry.setSub1Score(TstudentExam.EXAMPASS_TRUE);
									entry.setSub1FishedFlag(TstudentExam.EXAMFALG_TRUE);
									entry.setSub1MakeupScore(null);
								}
							} else {// 1.2如果原初考成绩无效，则修改初考成绩(初考成绩－－〉合格),且补考成绩为null
								entry.setSub1Score(TstudentExam.EXAMPASS_TRUE);
								entry.setSub1FishedFlag(TstudentExam.EXAMFALG_TRUE);
								entry.setSub1MakeupScore(null);
							}
						} else if (TstudentExam.EXAMPASS_FALSE == studentExam.getSub1Score()) {// 2.用户输入值为不合格(初考成绩－－〉不合格)
							// 1.1如果原初考成绩有效，
							if (ValidateUtil.isValid(entry.getSub1Score())) {
								// 1.1.1如果原补考成绩有效，则不能修改初考值
								if (ValidateUtil.isValid(entry.getSub1MakeupScore())) {
									entry.setSub1Score(entry.getSub1Score());
									entry.setSub1FishedFlag(entry.getSub1FishedFlag());
								} else {// 1.1.2如果原补考成绩无效，则修改初考值（初考成绩－－〉合格）
									entry.setSub1Score(TstudentExam.EXAMPASS_FALSE);
									entry.setSub1FishedFlag(TstudentExam.EXAMPASS_FALSE);
									entry.setSub1MakeupScore(null);
								}
							} else {// 1.2如果原初考成绩无效，则修改初考成绩(初考成绩－－〉合格),且补考成绩为null
								entry.setSub1Score(TstudentExam.EXAMPASS_FALSE);
								entry.setSub1FishedFlag(TstudentExam.EXAMPASS_FALSE);
								entry.setSub1MakeupScore(null);
							}
						}

					} else {// 用户输入null(初考成绩－－〉null)
						// 1.1如果原初考成绩有效，
						if (ValidateUtil.isValid(entry.getSub1Score())) {
							// 1.1.1如果原补考成绩有效，则不能修改初考值
							if (ValidateUtil.isValid(entry.getSub1MakeupScore())) {
								entry.setSub1Score(entry.getSub1Score());
								entry.setSub1FishedFlag(entry.getSub1FishedFlag());
							} else {// 1.1.2如果原补考成绩无效，则修改初考值（初考成绩－－〉合格）
								entry.setSub1Score(null);
								entry.setSub1FishedFlag(TstudentExam.EXAMFALG_FALSE);
								entry.setSub1MakeupScore(null);
							}
						} else {// 1.2如果原初考成绩无效，则修改初考成绩(初考成绩－－〉合格),且补考成绩为null
							entry.setSub1Score(null);
							entry.setSub1FishedFlag(TstudentExam.EXAMFALG_FALSE);
							entry.setSub1MakeupScore(null);
						}
					}
					// B修改补考成绩
					// 1.用户输入有效补考成绩值
					if (ValidateUtil.isValid(studentExam.getSub1MakeupScore())) {
						// 1.1用户输入补考成绩为合格
						if (TstudentExam.EXAMPASS_TRUE == studentExam.getSub1MakeupScore()) {
							// 1.1.1如果原初考成绩不为null
							if (ValidateUtil.isValid(entry.getSub1Score())) {
								// a.原初考成绩为合格，则不允许修改补考成绩
								if (TstudentExam.EXAMPASS_TRUE == entry.getSub1Score()) {
									entry.setSub1MakeupScore(entry.getSub1MakeupScore());
									entry.setSub1FishedFlag(entry.getSub1FishedFlag());
								} else if (TstudentExam.EXAMPASS_FALSE == entry.getSub1Score()) {// b.原初考成绩为不合格，则修改补考成绩（不合格－－〉合格）
									entry.setSub1MakeupScore(TstudentExam.EXAMPASS_TRUE);
									entry.setSub1FishedFlag(TstudentExam.EXAMFALG_TRUE);
								}

							} else {// 1.1.2 如果原初考成绩为null,则不允许修改补考成绩
								entry.setSub1MakeupScore(entry.getSub1MakeupScore());
								entry.setSub1FishedFlag(entry.getSub1FishedFlag());
							}

						} else if (TstudentExam.EXAMPASS_FALSE == studentExam.getSub1MakeupScore()) {// 1.2用户输入补考成绩为不合格
																										// (补考成绩--->不合格)
							// 1.2.1如果原初考成绩不为null
							if (ValidateUtil.isValid(entry.getSub1Score())) {
								// a.原初考成绩为合格，则不允许修改补考成绩
								if (TstudentExam.EXAMPASS_TRUE == entry.getSub1Score()) {
									entry.setSub1MakeupScore(entry.getSub1MakeupScore());
									entry.setSub1FishedFlag(entry.getSub1FishedFlag());
								} else if (TstudentExam.EXAMPASS_FALSE == entry.getSub1Score()) {// b.原初考成绩为不合格，则修改补考成绩（不合格－－〉不合格）
									entry.setSub1MakeupScore(TstudentExam.EXAMPASS_FALSE);
									entry.setSub1FishedFlag(TstudentExam.EXAMFALG_FALSE);
								}

							} else {// 1.2.2 如果原初考成绩为null,则不允许修改补考成绩
								entry.setSub1MakeupScore(entry.getSub1MakeupScore());
								entry.setSub1FishedFlag(entry.getSub1FishedFlag());
							}
						}
						// 2.用户输入补考成绩值为null(补考成绩--->null)
					} else {
						// 1.3.1如果原初考成绩不为null
						if (ValidateUtil.isValid(entry.getSub1Score())) {
							// a.原初考成绩为合格，则不允许修改补考成绩
							if (TstudentExam.EXAMPASS_TRUE == entry.getSub1Score()) {
								entry.setSub1MakeupScore(entry.getSub1MakeupScore());
								entry.setSub1FishedFlag(entry.getSub1FishedFlag());
							} else if (TstudentExam.EXAMPASS_FALSE == entry.getSub1Score()) {// b.原初考成绩为不合格，则修改补考成绩（不合格－－〉）
								entry.setSub1MakeupScore(null);
								entry.setSub1FishedFlag(TstudentExam.EXAMFALG_FALSE);
							}

						} else {// 1.3.2 如果原初考成绩为null,则不允许修改补考成绩
							entry.setSub1MakeupScore(entry.getSub1MakeupScore());
							entry.setSub1FishedFlag(entry.getSub1FishedFlag());
						}
					}
				}
			}

		} else {
			return;
		}

		if (ValidateUtil.isValid(entry.getSub2ApplyFlag()) || ValidateUtil.isValid(entry.getSub2MissExamFlag())) {
			if (1 != entry.getSub2ApplyFlag() && 1 != entry.getSub2MissExamFlag()) {
				return;
			}
			// 已经报考下一科后，不能修改当前科目成绩
			if (ValidateUtil.isValid(entry.getSub2EditAbleFlag())) {
				if (TstudentExam.EDITABLE_FALSE != entry.getSub2EditAbleFlag()) {
					// 科目2
					if (ValidateUtil.isValid(studentExam.getSub2Score())) {
						// 1用户输入值为合格(初考成绩－－〉合格)
						if (TstudentExam.EXAMPASS_TRUE == studentExam.getSub2Score()) {
							// 1.1如果原初考成绩有效，
							if (ValidateUtil.isValid(entry.getSub2Score())) {
								// 1.1.1如果原补考成绩有效，则不能修改初考值
								if (ValidateUtil.isValid(entry.getSub2MakeupScore())) {
									entry.setSub2Score(entry.getSub2Score());
									entry.setSub2FishedFlag(entry.getSub2FishedFlag());
								} else {// 1.1.2如果原补考成绩无效，则修改初考值（初考成绩－－〉合格）
									entry.setSub2Score(TstudentExam.EXAMPASS_TRUE);
									entry.setSub2FishedFlag(TstudentExam.EXAMFALG_TRUE);
									entry.setSub2MakeupScore(null);
								}
							} else {// 1.2如果原初考成绩无效，则修改初考成绩(初考成绩－－〉合格),且补考成绩为null
								entry.setSub2Score(TstudentExam.EXAMPASS_TRUE);
								entry.setSub2FishedFlag(TstudentExam.EXAMFALG_TRUE);
								entry.setSub2MakeupScore(null);
							}
						} else if (TstudentExam.EXAMPASS_FALSE == studentExam.getSub2Score()) {// 2.用户输入值为不合格(初考成绩－－〉不合格)
							// 1.1如果原初考成绩有效，
							if (ValidateUtil.isValid(entry.getSub2Score())) {
								// 1.1.1如果原补考成绩有效，则不能修改初考值
								if (ValidateUtil.isValid(entry.getSub2MakeupScore())) {
									entry.setSub2Score(entry.getSub2Score());
									entry.setSub2FishedFlag(entry.getSub2FishedFlag());
								} else {// 1.1.2如果原补考成绩无效，则修改初考值（初考成绩－－〉合格）
									entry.setSub2Score(TstudentExam.EXAMPASS_FALSE);
									entry.setSub2FishedFlag(TstudentExam.EXAMPASS_FALSE);
									entry.setSub2MakeupScore(null);
								}
							} else {// 1.2如果原初考成绩无效，则修改初考成绩(初考成绩－－〉合格),且补考成绩为null
								entry.setSub2Score(TstudentExam.EXAMPASS_FALSE);
								entry.setSub2FishedFlag(TstudentExam.EXAMPASS_FALSE);
								entry.setSub2MakeupScore(null);
							}
						}

					} else {// 用户输入null(初考成绩－－〉null)
						// 1.1如果原初考成绩有效，
						if (ValidateUtil.isValid(entry.getSub2Score())) {
							// 1.1.1如果原补考成绩有效，则不能修改初考值
							if (ValidateUtil.isValid(entry.getSub2MakeupScore())) {
								entry.setSub2Score(entry.getSub2Score());
								entry.setSub2FishedFlag(entry.getSub2FishedFlag());
							} else {// 1.1.2如果原补考成绩无效，则修改初考值（初考成绩－－〉合格）
								entry.setSub2Score(null);
								entry.setSub2FishedFlag(TstudentExam.EXAMFALG_FALSE);
								entry.setSub2MakeupScore(null);
							}
						} else {// 1.2如果原初考成绩无效，则修改初考成绩(初考成绩－－〉合格),且补考成绩为null
							entry.setSub2Score(null);
							entry.setSub2FishedFlag(TstudentExam.EXAMFALG_FALSE);
							entry.setSub2MakeupScore(null);
						}
					}
					// B修改补考成绩
					// 1.用户输入有效补考成绩值
					if (ValidateUtil.isValid(studentExam.getSub2MakeupScore())) {
						// 1.1用户输入补考成绩为合格
						if (TstudentExam.EXAMPASS_TRUE == studentExam.getSub2MakeupScore()) {
							// 1.1.1如果原初考成绩不为null
							if (ValidateUtil.isValid(entry.getSub2Score())) {
								// a.原初考成绩为合格，则不允许修改补考成绩
								if (TstudentExam.EXAMPASS_TRUE == entry.getSub2Score()) {
									entry.setSub2MakeupScore(entry.getSub2MakeupScore());
									entry.setSub2FishedFlag(entry.getSub2FishedFlag());
								} else if (TstudentExam.EXAMPASS_FALSE == entry.getSub2Score()) {// b.原初考成绩为不合格，则修改补考成绩（不合格－－〉合格）
									entry.setSub2MakeupScore(TstudentExam.EXAMPASS_TRUE);
									entry.setSub2FishedFlag(TstudentExam.EXAMFALG_TRUE);
								}

							} else {// 1.1.2 如果原初考成绩为null,则不允许修改补考成绩
								entry.setSub2MakeupScore(entry.getSub2MakeupScore());
								entry.setSub2FishedFlag(entry.getSub2FishedFlag());
							}

						} else if (TstudentExam.EXAMPASS_FALSE == studentExam.getSub2MakeupScore()) {// 1.2用户输入补考成绩为不合格
																										// (补考成绩--->不合格)
							// 1.2.1如果原初考成绩不为null
							if (ValidateUtil.isValid(entry.getSub2Score())) {
								// a.原初考成绩为合格，则不允许修改补考成绩
								if (TstudentExam.EXAMPASS_TRUE == entry.getSub2Score()) {
									entry.setSub2MakeupScore(entry.getSub2MakeupScore());
									entry.setSub2FishedFlag(entry.getSub2FishedFlag());
								} else if (TstudentExam.EXAMPASS_FALSE == entry.getSub2Score()) {// b.原初考成绩为不合格，则修改补考成绩（不合格－－〉不合格）
									entry.setSub2MakeupScore(TstudentExam.EXAMPASS_FALSE);
									entry.setSub2FishedFlag(TstudentExam.EXAMFALG_FALSE);
								}

							} else {// 1.2.2 如果原初考成绩为null,则不允许修改补考成绩
								entry.setSub2MakeupScore(entry.getSub2MakeupScore());
								entry.setSub2FishedFlag(entry.getSub2FishedFlag());
							}
						}
						// 2.用户输入补考成绩值为null(补考成绩--->null)
					} else {
						// 1.3.1如果原初考成绩不为null
						if (ValidateUtil.isValid(entry.getSub2Score())) {
							// a.原初考成绩为合格，则不允许修改补考成绩
							if (TstudentExam.EXAMPASS_TRUE == entry.getSub2Score()) {
								entry.setSub2MakeupScore(entry.getSub2MakeupScore());
								entry.setSub2FishedFlag(entry.getSub2FishedFlag());
							} else if (TstudentExam.EXAMPASS_FALSE == entry.getSub2Score()) {// b.原初考成绩为不合格，则修改补考成绩（不合格－－〉）
								entry.setSub2MakeupScore(null);
								entry.setSub2FishedFlag(TstudentExam.EXAMFALG_FALSE);
							}

						} else {// 1.3.2 如果原初考成绩为null,则不允许修改补考成绩
							entry.setSub2MakeupScore(entry.getSub2MakeupScore());
							entry.setSub2FishedFlag(entry.getSub2FishedFlag());
						}
					}
				}
			}

		} else {
			return;
		}

		// 科目三
		if (ValidateUtil.isValid(entry.getSub3ApplyFlag()) || ValidateUtil.isValid(entry.getSub3MissExamFlag())) {
			if (1 != entry.getSub3ApplyFlag() && 1 != entry.getSub3MissExamFlag()) {
				return;
			}
			// 已经报考下一科后，不能修改当前科目成绩
			if (ValidateUtil.isValid(entry.getSub3EditAbleFlag())) {
				if (TstudentExam.EDITABLE_FALSE != entry.getSub3EditAbleFlag()) {
					// 科目3
					if (ValidateUtil.isValid(studentExam.getSub3Score())) {
						// 1用户输入值为合格(初考成绩－－〉合格)
						if (TstudentExam.EXAMPASS_TRUE == studentExam.getSub3Score()) {
							// 1.1如果原初考成绩有效，
							if (ValidateUtil.isValid(entry.getSub3Score())) {
								// 1.1.1如果原补考成绩有效，则不能修改初考值
								if (ValidateUtil.isValid(entry.getSub3MakeupScore())) {
									entry.setSub3Score(entry.getSub3Score());
									entry.setSub3FishedFlag(entry.getSub3FishedFlag());
								} else {// 1.1.2如果原补考成绩无效，则修改初考值（初考成绩－－〉合格）
									entry.setSub3Score(TstudentExam.EXAMPASS_TRUE);
									entry.setSub3FishedFlag(TstudentExam.EXAMFALG_TRUE);
									entry.setSub3MakeupScore(null);
								}
							} else {// 1.2如果原初考成绩无效，则修改初考成绩(初考成绩－－〉合格),且补考成绩为null
								entry.setSub3Score(TstudentExam.EXAMPASS_TRUE);
								entry.setSub3FishedFlag(TstudentExam.EXAMFALG_TRUE);
								entry.setSub3MakeupScore(null);
							}
						} else if (TstudentExam.EXAMPASS_FALSE == studentExam.getSub3Score()) {// 2.用户输入值为不合格(初考成绩－－〉不合格)
							// 1.1如果原初考成绩有效，
							if (ValidateUtil.isValid(entry.getSub3Score())) {
								// 1.1.1如果原补考成绩有效，则不能修改初考值
								if (ValidateUtil.isValid(entry.getSub3MakeupScore())) {
									entry.setSub3Score(entry.getSub3Score());
									entry.setSub3FishedFlag(entry.getSub3FishedFlag());
								} else {// 1.1.2如果原补考成绩无效，则修改初考值（初考成绩－－〉合格）
									entry.setSub3Score(TstudentExam.EXAMPASS_FALSE);
									entry.setSub3FishedFlag(TstudentExam.EXAMPASS_FALSE);
									entry.setSub3MakeupScore(null);
								}
							} else {// 1.2如果原初考成绩无效，则修改初考成绩(初考成绩－－〉合格),且补考成绩为null
								entry.setSub3Score(TstudentExam.EXAMPASS_FALSE);
								entry.setSub3FishedFlag(TstudentExam.EXAMPASS_FALSE);
								entry.setSub3MakeupScore(null);
							}
						}

					} else {// 用户输入null(初考成绩－－〉null)
						// 1.1如果原初考成绩有效，
						if (ValidateUtil.isValid(entry.getSub3Score())) {
							// 1.1.1如果原补考成绩有效，则不能修改初考值
							if (ValidateUtil.isValid(entry.getSub3MakeupScore())) {
								entry.setSub3Score(entry.getSub3Score());
								entry.setSub3FishedFlag(entry.getSub3FishedFlag());
							} else {// 1.1.2如果原补考成绩无效，则修改初考值（初考成绩－－〉合格）
								entry.setSub3Score(null);
								entry.setSub3FishedFlag(TstudentExam.EXAMFALG_FALSE);
								entry.setSub3MakeupScore(null);
							}
						} else {// 1.2如果原初考成绩无效，则修改初考成绩(初考成绩－－〉合格),且补考成绩为null
							entry.setSub3Score(null);
							entry.setSub3FishedFlag(TstudentExam.EXAMFALG_FALSE);
							entry.setSub3MakeupScore(null);
						}
					}
					// B修改补考成绩
					// 1.用户输入有效补考成绩值
					if (ValidateUtil.isValid(studentExam.getSub3MakeupScore())) {
						// 1.1用户输入补考成绩为合格
						if (TstudentExam.EXAMPASS_TRUE == studentExam.getSub3MakeupScore()) {
							// 1.1.1如果原初考成绩不为null
							if (ValidateUtil.isValid(entry.getSub3Score())) {
								// a.原初考成绩为合格，则不允许修改补考成绩
								if (TstudentExam.EXAMPASS_TRUE == entry.getSub3Score()) {
									entry.setSub3MakeupScore(entry.getSub3MakeupScore());
									entry.setSub3FishedFlag(entry.getSub3FishedFlag());
								} else if (TstudentExam.EXAMPASS_FALSE == entry.getSub3Score()) {// b.原初考成绩为不合格，则修改补考成绩（不合格－－〉合格）
									entry.setSub3MakeupScore(TstudentExam.EXAMPASS_TRUE);
									entry.setSub3FishedFlag(TstudentExam.EXAMFALG_TRUE);
								}

							} else {// 1.1.2 如果原初考成绩为null,则不允许修改补考成绩
								entry.setSub3MakeupScore(entry.getSub3MakeupScore());
								entry.setSub3FishedFlag(entry.getSub3FishedFlag());
							}

						} else if (TstudentExam.EXAMPASS_FALSE == studentExam.getSub3MakeupScore()) {// 1.2用户输入补考成绩为不合格
																										// (补考成绩--->不合格)
							// 1.2.1如果原初考成绩不为null
							if (ValidateUtil.isValid(entry.getSub3Score())) {
								// a.原初考成绩为合格，则不允许修改补考成绩
								if (TstudentExam.EXAMPASS_TRUE == entry.getSub3Score()) {
									entry.setSub3MakeupScore(entry.getSub3MakeupScore());
									entry.setSub3FishedFlag(entry.getSub3FishedFlag());
								} else if (TstudentExam.EXAMPASS_FALSE == entry.getSub3Score()) {// b.原初考成绩为不合格，则修改补考成绩（不合格－－〉不合格）
									entry.setSub3MakeupScore(TstudentExam.EXAMPASS_FALSE);
									entry.setSub3FishedFlag(TstudentExam.EXAMFALG_FALSE);
								}

							} else {// 1.2.2 如果原初考成绩为null,则不允许修改补考成绩
								entry.setSub3MakeupScore(entry.getSub3MakeupScore());
								entry.setSub3FishedFlag(entry.getSub3FishedFlag());
							}
						}
						// 2.用户输入补考成绩值为null(补考成绩--->null)
					} else {
						// 1.3.1如果原初考成绩不为null
						if (ValidateUtil.isValid(entry.getSub3Score())) {
							// a.原初考成绩为合格，则不允许修改补考成绩
							if (TstudentExam.EXAMPASS_TRUE == entry.getSub3Score()) {
								entry.setSub3MakeupScore(entry.getSub3MakeupScore());
								entry.setSub3FishedFlag(entry.getSub3FishedFlag());
							} else if (TstudentExam.EXAMPASS_FALSE == entry.getSub3Score()) {// b.原初考成绩为不合格，则修改补考成绩（不合格－－〉）
								entry.setSub3MakeupScore(null);
								entry.setSub3FishedFlag(TstudentExam.EXAMFALG_FALSE);
							}

						} else {// 1.3.2 如果原初考成绩为null,则不允许修改补考成绩
							entry.setSub3MakeupScore(entry.getSub3MakeupScore());
							entry.setSub3FishedFlag(entry.getSub3FishedFlag());
						}
					}
				}
			}

		} else {
			return;
		}

		// 科目四
		if (ValidateUtil.isValid(entry.getSub4ApplyFlag()) || ValidateUtil.isValid(entry.getSub4MissExamFlag())) {
			if (1 != entry.getSub4ApplyFlag() && 1 != entry.getSub4MissExamFlag()) {
				return;
			}
			// 已经报考下一科后，不能修改当前科目成绩
			if (ValidateUtil.isValid(entry.getSub4EditAbleFlag())) {
				if (TstudentExam.EDITABLE_FALSE != entry.getSub4EditAbleFlag()) {
					// 科目4
					if (ValidateUtil.isValid(studentExam.getSub4Score())) {
						// 1用户输入值为合格(初考成绩－－〉合格)
						if (TstudentExam.EXAMPASS_TRUE == studentExam.getSub4Score()) {
							// 1.1如果原初考成绩有效，
							if (ValidateUtil.isValid(entry.getSub4Score())) {
								// 1.1.1如果原补考成绩有效，则不能修改初考值
								if (ValidateUtil.isValid(entry.getSub4MakeupScore())) {
									entry.setSub4Score(entry.getSub4Score());
									entry.setSub4FishedFlag(entry.getSub4FishedFlag());
								} else {// 1.1.2如果原补考成绩无效，则修改初考值（初考成绩－－〉合格）
									entry.setSub4Score(TstudentExam.EXAMPASS_TRUE);
									entry.setSub4FishedFlag(TstudentExam.EXAMFALG_TRUE);
									entry.setSub4MakeupScore(null);
								}
							} else {// 1.2如果原初考成绩无效，则修改初考成绩(初考成绩－－〉合格),且补考成绩为null
								entry.setSub4Score(TstudentExam.EXAMPASS_TRUE);
								entry.setSub4FishedFlag(TstudentExam.EXAMFALG_TRUE);
								entry.setSub4MakeupScore(null);
							}
							// 考试科目四合格则出资格证
							if (ValidateUtil.isValid(entry.getDriverLicenseFlag())) {
								entry.setDriverLicenseFlag(1);
							}
						} else if (TstudentExam.EXAMPASS_FALSE == studentExam.getSub4Score()) {// 2.用户输入值为不合格(初考成绩－－〉不合格)
							// 1.1如果原初考成绩有效，
							if (ValidateUtil.isValid(entry.getSub4Score())) {
								// 1.1.1如果原补考成绩有效，则不能修改初考值
								if (ValidateUtil.isValid(entry.getSub4MakeupScore())) {
									entry.setSub4Score(entry.getSub4Score());
									entry.setSub4FishedFlag(entry.getSub4FishedFlag());
								} else {// 1.1.2如果原补考成绩无效，则修改初考值（初考成绩－－〉合格）
									entry.setSub4Score(TstudentExam.EXAMPASS_FALSE);
									entry.setSub4FishedFlag(TstudentExam.EXAMPASS_FALSE);
									entry.setSub4MakeupScore(null);
								}
							} else {// 1.2如果原初考成绩无效，则修改初考成绩(初考成绩－－〉合格),且补考成绩为null
								entry.setSub4Score(TstudentExam.EXAMPASS_FALSE);
								entry.setSub4FishedFlag(TstudentExam.EXAMPASS_FALSE);
								entry.setSub4MakeupScore(null);
							}
							// 考试科目四合格则出资格证
							if (ValidateUtil.isValid(entry.getDriverLicenseFlag())) {
								entry.setDriverLicenseFlag(0);
							}
						}

					} else {// 用户输入null(初考成绩－－〉null)
						// 1.1如果原初考成绩有效，
						if (ValidateUtil.isValid(entry.getSub4Score())) {
							// 1.1.1如果原补考成绩有效，则不能修改初考值
							if (ValidateUtil.isValid(entry.getSub4MakeupScore())) {
								entry.setSub4Score(entry.getSub4Score());
								entry.setSub4FishedFlag(entry.getSub4FishedFlag());
							} else {// 1.1.2如果原补考成绩无效，则修改初考值（初考成绩－－〉合格）
								entry.setSub4Score(null);
								entry.setSub4FishedFlag(TstudentExam.EXAMFALG_FALSE);
								entry.setSub4MakeupScore(null);
							}
						} else {// 1.2如果原初考成绩无效，则修改初考成绩(初考成绩－－〉合格),且补考成绩为null
							entry.setSub4Score(null);
							entry.setSub4FishedFlag(TstudentExam.EXAMFALG_FALSE);
							entry.setSub4MakeupScore(null);
						}
						// 考试科目四合格则出资格证
						if (ValidateUtil.isValid(entry.getDriverLicenseFlag())) {
							entry.setDriverLicenseFlag(0);
						}
					}
					// B修改补考成绩
					// 1.用户输入有效补考成绩值
					if (ValidateUtil.isValid(studentExam.getSub4MakeupScore())) {
						// 1.1用户输入补考成绩为合格
						if (TstudentExam.EXAMPASS_TRUE == studentExam.getSub4MakeupScore()) {
							// 1.1.1如果原初考成绩不为null
							if (ValidateUtil.isValid(entry.getSub4Score())) {
								// a.原初考成绩为合格，则不允许修改补考成绩
								if (TstudentExam.EXAMPASS_TRUE == entry.getSub4Score()) {
									entry.setSub4MakeupScore(entry.getSub4MakeupScore());
									entry.setSub4FishedFlag(entry.getSub4FishedFlag());
								} else if (TstudentExam.EXAMPASS_FALSE == entry.getSub4Score()) {// b.原初考成绩为不合格，则修改补考成绩（不合格－－〉合格）
									entry.setSub4MakeupScore(TstudentExam.EXAMPASS_TRUE);
									entry.setSub4FishedFlag(TstudentExam.EXAMFALG_TRUE);
								}

							} else {// 1.1.2 如果原初考成绩为null,则不允许修改补考成绩
								entry.setSub4MakeupScore(entry.getSub4MakeupScore());
								entry.setSub4FishedFlag(entry.getSub4FishedFlag());
							}
							// 考试科目四合格则出资格证
							if (ValidateUtil.isValid(entry.getDriverLicenseFlag())) {
								entry.setDriverLicenseFlag(1);
							}

						} else if (TstudentExam.EXAMPASS_FALSE == studentExam.getSub4MakeupScore()) {// 1.2用户输入补考成绩为不合格
																										// (补考成绩--->不合格)
							// 1.2.1如果原初考成绩不为null
							if (ValidateUtil.isValid(entry.getSub4Score())) {
								// a.原初考成绩为合格，则不允许修改补考成绩
								if (TstudentExam.EXAMPASS_TRUE == entry.getSub4Score()) {
									entry.setSub4MakeupScore(entry.getSub4MakeupScore());
									entry.setSub4FishedFlag(entry.getSub4FishedFlag());
								} else if (TstudentExam.EXAMPASS_FALSE == entry.getSub4Score()) {// b.原初考成绩为不合格，则修改补考成绩（不合格－－〉不合格）
									entry.setSub4MakeupScore(TstudentExam.EXAMPASS_FALSE);
									entry.setSub4FishedFlag(TstudentExam.EXAMFALG_FALSE);
								}

							} else {// 1.2.2 如果原初考成绩为null,则不允许修改补考成绩
								entry.setSub4MakeupScore(entry.getSub4MakeupScore());
								entry.setSub4FishedFlag(entry.getSub4FishedFlag());
							}
							// 考试科目四合格则出资格证
							if (ValidateUtil.isValid(entry.getDriverLicenseFlag())) {
								entry.setDriverLicenseFlag(0);
							}
						}
						// 2.用户输入补考成绩值为null(补考成绩--->null)
					} else {
						// 1.3.1如果原初考成绩不为null
						if (ValidateUtil.isValid(entry.getSub4Score())) {
							// a.原初考成绩为合格，则不允许修改补考成绩
							if (TstudentExam.EXAMPASS_TRUE == entry.getSub4Score()) {
								entry.setSub4MakeupScore(entry.getSub4MakeupScore());
								entry.setSub4FishedFlag(entry.getSub4FishedFlag());
							} else if (TstudentExam.EXAMPASS_FALSE == entry.getSub4Score()) {// b.原初考成绩为不合格，则修改补考成绩（不合格－－〉）
								entry.setSub4MakeupScore(null);
								entry.setSub4FishedFlag(TstudentExam.EXAMFALG_FALSE);
							}

						} else {// 1.3.2 如果原初考成绩为null,则不允许修改补考成绩
							entry.setSub4MakeupScore(entry.getSub4MakeupScore());
							entry.setSub4FishedFlag(entry.getSub4FishedFlag());
						}
						// 考试科目四合格则出资格证
						if (ValidateUtil.isValid(entry.getDriverLicenseFlag())) {
							entry.setDriverLicenseFlag(0);
						}
					}
				}
			}

		} else {
			return;
		}

		// 科目五
		if (ValidateUtil.isValid(entry.getSub5ApplyFlag()) || ValidateUtil.isValid(entry.getSub5MissExamFlag())) {
			if (1 != entry.getSub5ApplyFlag() && 1 != entry.getSub5MissExamFlag()) {
				return;
			}
			// 科目5
			if (ValidateUtil.isValid(studentExam.getSub5Score())) {
				// 1用户输入值为合格(初考成绩－－〉合格)
				if (TstudentExam.EXAMPASS_TRUE == studentExam.getSub5Score()) {
					// 1.1如果原初考成绩有效，
					if (ValidateUtil.isValid(entry.getSub5Score())) {
						// 1.1.1如果原补考成绩有效，则不能修改初考值
						if (ValidateUtil.isValid(entry.getSub5MakeupScore())) {
							entry.setSub5Score(entry.getSub5Score());
							entry.setSub5FishedFlag(entry.getSub5FishedFlag());
						} else {// 1.1.2如果原补考成绩无效，则修改初考值（初考成绩－－〉合格）
							entry.setSub5Score(TstudentExam.EXAMPASS_TRUE);
							entry.setSub5FishedFlag(TstudentExam.EXAMFALG_TRUE);
							entry.setSub5MakeupScore(null);
						}
					} else {// 1.2如果原初考成绩无效，则修改初考成绩(初考成绩－－〉合格),且补考成绩为null
						entry.setSub5Score(TstudentExam.EXAMPASS_TRUE);
						entry.setSub5FishedFlag(TstudentExam.EXAMFALG_TRUE);
						entry.setSub5MakeupScore(null);
					}
					// 考试科目四合格则出资格证
					if (ValidateUtil.isValid(entry.getDriverLicenseFlag())) {
						entry.setDriverLicenseFlag(1);
					}
				} else if (TstudentExam.EXAMPASS_FALSE == studentExam.getSub5Score()) {// 2.用户输入值为不合格(初考成绩－－〉不合格)
					// 1.1如果原初考成绩有效，
					if (ValidateUtil.isValid(entry.getSub5Score())) {
						// 1.1.1如果原补考成绩有效，则不能修改初考值
						if (ValidateUtil.isValid(entry.getSub5MakeupScore())) {
							entry.setSub5Score(entry.getSub5Score());
							entry.setSub5FishedFlag(entry.getSub5FishedFlag());
						} else {// 1.1.2如果原补考成绩无效，则修改初考值（初考成绩－－〉合格）
							entry.setSub5Score(TstudentExam.EXAMPASS_FALSE);
							entry.setSub5FishedFlag(TstudentExam.EXAMPASS_FALSE);
							entry.setSub5MakeupScore(null);
						}
					} else {// 1.2如果原初考成绩无效，则修改初考成绩(初考成绩－－〉合格),且补考成绩为null
						entry.setSub5Score(TstudentExam.EXAMPASS_FALSE);
						entry.setSub5FishedFlag(TstudentExam.EXAMPASS_FALSE);
						entry.setSub5MakeupScore(null);
					}
					// 考试科目四合格则出资格证
					if (ValidateUtil.isValid(entry.getDriverLicenseFlag())) {
						entry.setDriverLicenseFlag(0);
					}
				}

			} else {// 用户输入null(初考成绩－－〉null)
				// 1.1如果原初考成绩有效，
				if (ValidateUtil.isValid(entry.getSub5Score())) {
					// 1.1.1如果原补考成绩有效，则不能修改初考值
					if (ValidateUtil.isValid(entry.getSub5MakeupScore())) {
						entry.setSub5Score(entry.getSub5Score());
						entry.setSub5FishedFlag(entry.getSub5FishedFlag());
					} else {// 1.1.2如果原补考成绩无效，则修改初考值（初考成绩－－〉合格）
						entry.setSub5Score(null);
						entry.setSub5FishedFlag(TstudentExam.EXAMFALG_FALSE);
						entry.setSub5MakeupScore(null);
					}
				} else {// 1.2如果原初考成绩无效，则修改初考成绩(初考成绩－－〉合格),且补考成绩为null
					entry.setSub5Score(null);
					entry.setSub5FishedFlag(TstudentExam.EXAMFALG_FALSE);
					entry.setSub5MakeupScore(null);
				}
				// 考试科目四合格则出资格证
				if (ValidateUtil.isValid(entry.getDriverLicenseFlag())) {
					entry.setDriverLicenseFlag(0);
				}
			}
			// B修改补考成绩
			// 1.用户输入有效补考成绩值
			if (ValidateUtil.isValid(studentExam.getSub5MakeupScore())) {
				// 1.1用户输入补考成绩为合格
				if (TstudentExam.EXAMPASS_TRUE == studentExam.getSub5MakeupScore()) {
					// 1.1.1如果原初考成绩不为null
					if (ValidateUtil.isValid(entry.getSub5Score())) {
						// a.原初考成绩为合格，则不允许修改补考成绩
						if (TstudentExam.EXAMPASS_TRUE == entry.getSub5Score()) {
							entry.setSub5MakeupScore(entry.getSub5MakeupScore());
							entry.setSub5FishedFlag(entry.getSub5FishedFlag());
						} else if (TstudentExam.EXAMPASS_FALSE == entry.getSub5Score()) {// b.原初考成绩为不合格，则修改补考成绩（不合格－－〉合格）
							entry.setSub5MakeupScore(TstudentExam.EXAMPASS_TRUE);
							entry.setSub5FishedFlag(TstudentExam.EXAMFALG_TRUE);
						}

					} else {// 1.1.2 如果原初考成绩为null,则不允许修改补考成绩
						entry.setSub5MakeupScore(entry.getSub5MakeupScore());
						entry.setSub5FishedFlag(entry.getSub5FishedFlag());
					}
					// 考试科目四合格则出资格证
					if (ValidateUtil.isValid(entry.getDriverLicenseFlag())) {
						entry.setDriverLicenseFlag(1);
					}

				} else if (TstudentExam.EXAMPASS_FALSE == studentExam.getSub5MakeupScore()) {// 1.2用户输入补考成绩为不合格
																								// (补考成绩--->不合格)
					// 1.2.1如果原初考成绩不为null
					if (ValidateUtil.isValid(entry.getSub5Score())) {
						// a.原初考成绩为合格，则不允许修改补考成绩
						if (TstudentExam.EXAMPASS_TRUE == entry.getSub5Score()) {
							entry.setSub5MakeupScore(entry.getSub5MakeupScore());
							entry.setSub5FishedFlag(entry.getSub5FishedFlag());
						} else if (TstudentExam.EXAMPASS_FALSE == entry.getSub5Score()) {// b.原初考成绩为不合格，则修改补考成绩（不合格－－〉不合格）
							entry.setSub5MakeupScore(TstudentExam.EXAMPASS_FALSE);
							entry.setSub5FishedFlag(TstudentExam.EXAMFALG_FALSE);
						}

					} else {// 1.2.2 如果原初考成绩为null,则不允许修改补考成绩
						entry.setSub5MakeupScore(entry.getSub5MakeupScore());
						entry.setSub5FishedFlag(entry.getSub5FishedFlag());
					}
					// 考试科目四合格则出资格证
					if (ValidateUtil.isValid(entry.getDriverLicenseFlag())) {
						entry.setDriverLicenseFlag(0);
					}
				}
				// 2.用户输入补考成绩值为null(补考成绩--->null)
			} else {
				// 1.3.1如果原初考成绩不为null
				if (ValidateUtil.isValid(entry.getSub5Score())) {
					// a.原初考成绩为合格，则不允许修改补考成绩
					if (TstudentExam.EXAMPASS_TRUE == entry.getSub5Score()) {
						entry.setSub5MakeupScore(entry.getSub5MakeupScore());
						entry.setSub5FishedFlag(entry.getSub5FishedFlag());
					} else if (TstudentExam.EXAMPASS_FALSE == entry.getSub5Score()) {// b.原初考成绩为不合格，则修改补考成绩（不合格－－〉）
						entry.setSub5MakeupScore(null);
						entry.setSub5FishedFlag(TstudentExam.EXAMFALG_FALSE);
					}

				} else {// 1.3.2 如果原初考成绩为null,则不允许修改补考成绩
					entry.setSub5MakeupScore(entry.getSub5MakeupScore());
					entry.setSub5FishedFlag(entry.getSub5FishedFlag());
				}
				// 考试科目四合格则出资格证
				if (ValidateUtil.isValid(entry.getDriverLicenseFlag())) {
					entry.setDriverLicenseFlag(0);
				}
			}
		} else {
			return;
		}
		if (student != null) {
			studentDao.update(student);
		}

	}

	// 批量修改成绩
	private void batchUpdateScore(TstudentExam entry, StudentExam model) {

		Tstudent student = entry.getStudent();
		switch (model.getSubjectType()) {
		case 1: {
			// 报考完下一科目后，限制修改本科目成绩
			if (ValidateUtil.isValid(entry.getSub1EditAbleFlag())) {
				if (Tstudent.EXAMSELECTED_FALSE != entry.getSub1EditAbleFlag()) {
					return;
				}
			}
			// 审批通过后才能修改成绩
			if (TstudentExam.EXAMFALG_TRUE == entry.getSub1ApplyFlag() && 1 != entry.getSub1MissExamFlag()) {
				// 修改成绩 修改初考成绩(完成本科目考试后不能修改该科目的成绩)
				if (1 == model.getExamScoreFlag() && TstudentExam.EDITABLE_FALSE != entry.getSub1EditAbleFlag()) {// 修改初考成绩
					// 科目1
					if (ValidateUtil.isValid(model.getExamScore())) {
						// 1用户输入值为合格(初考成绩－－〉合格)
						if (TstudentExam.EXAMPASS_TRUE == model.getExamScore()) {
							// 1.1如果原初考成绩有效，
							if (ValidateUtil.isValid(entry.getSub1Score())) {
								// 1.1.1如果原补考成绩有效，则不能修改初考值
								if (ValidateUtil.isValid(entry.getSub1MakeupScore())) {
									entry.setSub1Score(entry.getSub1Score());
									entry.setSub1FishedFlag(entry.getSub1FishedFlag());
								} else {// 1.1.2如果原补考成绩无效，则修改初考值（初考成绩－－〉合格）
									entry.setSub1Score(TstudentExam.EXAMPASS_TRUE);
									entry.setSub1FishedFlag(TstudentExam.EXAMFALG_TRUE);
									entry.setSub1MakeupScore(null);
								}
							} else {// 1.2如果原初考成绩无效，则修改初考成绩(初考成绩－－〉合格),且补考成绩为null
								entry.setSub1Score(TstudentExam.EXAMPASS_TRUE);
								entry.setSub1FishedFlag(TstudentExam.EXAMFALG_TRUE);
								entry.setSub1MakeupScore(null);
							}
						} else if (TstudentExam.EXAMPASS_FALSE == model.getExamScore()) {// 2.用户输入值为不合格(初考成绩－－〉不合格)
							// 1.1如果原初考成绩有效，
							if (ValidateUtil.isValid(entry.getSub1Score())) {
								// 1.1.1如果原补考成绩有效，则不能修改初考值
								if (ValidateUtil.isValid(entry.getSub1MakeupScore())) {
									entry.setSub1Score(entry.getSub1Score());
									entry.setSub1FishedFlag(entry.getSub1FishedFlag());
								} else {// 1.1.2如果原补考成绩无效，则修改初考值（初考成绩－－〉合格）
									entry.setSub1Score(TstudentExam.EXAMPASS_FALSE);
									entry.setSub1FishedFlag(TstudentExam.EXAMPASS_FALSE);
									entry.setSub1MakeupScore(null);
								}
							} else {// 1.2如果原初考成绩无效，则修改初考成绩(初考成绩－－〉合格),且补考成绩为null
								entry.setSub1Score(TstudentExam.EXAMPASS_FALSE);
								entry.setSub1FishedFlag(TstudentExam.EXAMPASS_FALSE);
								entry.setSub1MakeupScore(null);
							}
						}

					} else {// 用户输入null(初考成绩－－〉null)
						// 1.1如果原初考成绩有效，
						if (ValidateUtil.isValid(entry.getSub1Score())) {
							// 1.1.1如果原补考成绩有效，则不能修改初考值
							if (ValidateUtil.isValid(entry.getSub1MakeupScore())) {
								entry.setSub1Score(entry.getSub1Score());
								entry.setSub1FishedFlag(entry.getSub1FishedFlag());
							} else {// 1.1.2如果原补考成绩无效，则修改初考值（初考成绩－－〉合格）
								entry.setSub1Score(null);
								entry.setSub1FishedFlag(TstudentExam.EXAMFALG_FALSE);
								entry.setSub1MakeupScore(null);
							}
						} else {// 1.2如果原初考成绩无效，则修改初考成绩(初考成绩－－〉合格),且补考成绩为null
							entry.setSub1Score(null);
							entry.setSub1FishedFlag(TstudentExam.EXAMFALG_FALSE);
							entry.setSub1MakeupScore(null);
						}
					}
				} else if (2 == model.getExamScoreFlag()) {// 修改补考成绩
					if (ValidateUtil.isValid(model.getExamScore())) {
						// 1.1用户输入补考成绩为合格
						if (TstudentExam.EXAMPASS_TRUE == model.getExamScore()) {
							// 1.1.1如果原初考成绩不为null
							if (ValidateUtil.isValid(entry.getSub1Score())) {
								// a.原初考成绩为合格，则不允许修改补考成绩
								if (TstudentExam.EXAMPASS_TRUE == entry.getSub1Score()) {
									entry.setSub1MakeupScore(entry.getSub1MakeupScore());
									entry.setSub1FishedFlag(entry.getSub1FishedFlag());
								} else if (TstudentExam.EXAMPASS_FALSE == entry.getSub1Score()) {// b.原初考成绩为不合格，则修改补考成绩（不合格－－〉合格）
									entry.setSub1MakeupScore(TstudentExam.EXAMPASS_TRUE);
									entry.setSub1FishedFlag(TstudentExam.EXAMFALG_TRUE);
								}

							} else {// 1.1.2 如果原初考成绩为null,则不允许修改补考成绩
								entry.setSub1MakeupScore(entry.getSub1MakeupScore());
								entry.setSub1FishedFlag(entry.getSub1FishedFlag());
							}

						} else if (TstudentExam.EXAMPASS_FALSE == model.getExamScore()) {// 1.2用户输入补考成绩为不合格
							// (补考成绩--->不合格)
							// 1.2.1如果原初考成绩不为null
							if (ValidateUtil.isValid(entry.getSub1Score())) {
								// a.原初考成绩为合格，则不允许修改补考成绩
								if (TstudentExam.EXAMPASS_TRUE == entry.getSub1Score()) {
									entry.setSub1MakeupScore(entry.getSub1MakeupScore());
									entry.setSub1FishedFlag(entry.getSub1FishedFlag());
								} else if (TstudentExam.EXAMPASS_FALSE == entry.getSub1Score()) {// b.原初考成绩为不合格，则修改补考成绩（不合格－－〉不合格）
									entry.setSub1MakeupScore(TstudentExam.EXAMPASS_FALSE);
									entry.setSub1FishedFlag(TstudentExam.EXAMFALG_FALSE);
								}

							} else {// 1.2.2 如果原初考成绩为null,则不允许修改补考成绩
								entry.setSub1MakeupScore(entry.getSub1MakeupScore());
								entry.setSub1FishedFlag(entry.getSub1FishedFlag());
							}
						}
						// 2.用户输入补考成绩值为null(补考成绩--->null)
					} else {
						// 1.3.1如果原初考成绩不为null
						if (ValidateUtil.isValid(entry.getSub1Score())) {
							// a.原初考成绩为合格，则不允许修改补考成绩
							if (TstudentExam.EXAMPASS_TRUE == entry.getSub1Score()) {
								entry.setSub1MakeupScore(entry.getSub1MakeupScore());
								entry.setSub1FishedFlag(entry.getSub1FishedFlag());
							} else if (TstudentExam.EXAMPASS_FALSE == entry.getSub1Score()) {// b.原初考成绩为不合格，则修改补考成绩（不合格－－〉）
								entry.setSub1MakeupScore(null);
								entry.setSub1FishedFlag(TstudentExam.EXAMFALG_FALSE);
							}

						} else {// 1.3.2 如果原初考成绩为null,则不允许修改补考成绩
							entry.setSub1MakeupScore(entry.getSub1MakeupScore());
							entry.setSub1FishedFlag(entry.getSub1FishedFlag());
						}
					}
				}
			}
		}
			break;
		case 2: {
			// 报考完下一科目后，限制修改本科目成绩
			if (ValidateUtil.isValid(entry.getSub2EditAbleFlag())) {
				if (Tstudent.EXAMSELECTED_FALSE != entry.getSub2EditAbleFlag()) {
					return;
				}
			}
			// 审批通过后才能修改成绩
			if (TstudentExam.EXAMFALG_TRUE == entry.getSub2ApplyFlag() && 1 != entry.getSub2MissExamFlag()) {
				// 修改成绩 修改初考成绩(完成本科目考试后不能修改该科目的成绩)
				if (1 == model.getExamScoreFlag() && TstudentExam.EDITABLE_FALSE != entry.getSub2EditAbleFlag()) {// 修改初考成绩
					// 科目5
					if (ValidateUtil.isValid(model.getExamScore())) {
						// 1用户输入值为合格(初考成绩－－〉合格)
						if (TstudentExam.EXAMPASS_TRUE == model.getExamScore()) {
							// 1.1如果原初考成绩有效，
							if (ValidateUtil.isValid(entry.getSub2Score())) {
								// 1.1.1如果原补考成绩有效，则不能修改初考值
								if (ValidateUtil.isValid(entry.getSub2MakeupScore())) {
									entry.setSub2Score(entry.getSub2Score());
									entry.setSub2FishedFlag(entry.getSub2FishedFlag());
								} else {// 1.1.2如果原补考成绩无效，则修改初考值（初考成绩－－〉合格）
									entry.setSub2Score(TstudentExam.EXAMPASS_TRUE);
									entry.setSub2FishedFlag(TstudentExam.EXAMFALG_TRUE);
									entry.setSub2MakeupScore(null);
								}
							} else {// 1.2如果原初考成绩无效，则修改初考成绩(初考成绩－－〉合格),且补考成绩为null
								entry.setSub2Score(TstudentExam.EXAMPASS_TRUE);
								entry.setSub2FishedFlag(TstudentExam.EXAMFALG_TRUE);
								entry.setSub2MakeupScore(null);
							}
						} else if (TstudentExam.EXAMPASS_FALSE == model.getExamScore()) {// 2.用户输入值为不合格(初考成绩－－〉不合格)
							// 1.1如果原初考成绩有效，
							if (ValidateUtil.isValid(entry.getSub2Score())) {
								// 1.1.1如果原补考成绩有效，则不能修改初考值
								if (ValidateUtil.isValid(entry.getSub2MakeupScore())) {
									entry.setSub2Score(entry.getSub2Score());
									entry.setSub2FishedFlag(entry.getSub2FishedFlag());
								} else {// 1.1.2如果原补考成绩无效，则修改初考值（初考成绩－－〉合格）
									entry.setSub2Score(TstudentExam.EXAMPASS_FALSE);
									entry.setSub2FishedFlag(TstudentExam.EXAMPASS_FALSE);
									entry.setSub2MakeupScore(null);
								}
							} else {// 1.2如果原初考成绩无效，则修改初考成绩(初考成绩－－〉合格),且补考成绩为null
								entry.setSub2Score(TstudentExam.EXAMPASS_FALSE);
								entry.setSub2FishedFlag(TstudentExam.EXAMPASS_FALSE);
								entry.setSub2MakeupScore(null);
							}
						}

					} else {// 用户输入null(初考成绩－－〉null)
						// 1.1如果原初考成绩有效，
						if (ValidateUtil.isValid(entry.getSub2Score())) {
							// 1.1.1如果原补考成绩有效，则不能修改初考值
							if (ValidateUtil.isValid(entry.getSub2MakeupScore())) {
								entry.setSub2Score(entry.getSub2Score());
								entry.setSub2FishedFlag(entry.getSub2FishedFlag());
							} else {// 1.1.2如果原补考成绩无效，则修改初考值（初考成绩－－〉合格）
								entry.setSub2Score(null);
								entry.setSub2FishedFlag(TstudentExam.EXAMFALG_FALSE);
								entry.setSub2MakeupScore(null);
							}
						} else {// 1.2如果原初考成绩无效，则修改初考成绩(初考成绩－－〉合格),且补考成绩为null
							entry.setSub2Score(null);
							entry.setSub2FishedFlag(TstudentExam.EXAMFALG_FALSE);
							entry.setSub2MakeupScore(null);
						}
					}
				} else if (2 == model.getExamScoreFlag()) {// 修改补考成绩
					if (ValidateUtil.isValid(model.getExamScore())) {
						// 1.1用户输入补考成绩为合格
						if (TstudentExam.EXAMPASS_TRUE == model.getExamScore()) {
							// 1.1.1如果原初考成绩不为null
							if (ValidateUtil.isValid(entry.getSub2Score())) {
								// a.原初考成绩为合格，则不允许修改补考成绩
								if (TstudentExam.EXAMPASS_TRUE == entry.getSub2Score()) {
									entry.setSub2MakeupScore(entry.getSub2MakeupScore());
									entry.setSub2FishedFlag(entry.getSub2FishedFlag());
								} else if (TstudentExam.EXAMPASS_FALSE == entry.getSub2Score()) {// b.原初考成绩为不合格，则修改补考成绩（不合格－－〉合格）
									entry.setSub2MakeupScore(TstudentExam.EXAMPASS_TRUE);
									entry.setSub2FishedFlag(TstudentExam.EXAMFALG_TRUE);
								}

							} else {// 1.1.2 如果原初考成绩为null,则不允许修改补考成绩
								entry.setSub2MakeupScore(entry.getSub2MakeupScore());
								entry.setSub2FishedFlag(entry.getSub2FishedFlag());
							}

						} else if (TstudentExam.EXAMPASS_FALSE == model.getExamScore()) {// 1.2用户输入补考成绩为不合格
							// (补考成绩--->不合格)
							// 1.2.1如果原初考成绩不为null
							if (ValidateUtil.isValid(entry.getSub2Score())) {
								// a.原初考成绩为合格，则不允许修改补考成绩
								if (TstudentExam.EXAMPASS_TRUE == entry.getSub2Score()) {
									entry.setSub2MakeupScore(entry.getSub2MakeupScore());
									entry.setSub2FishedFlag(entry.getSub2FishedFlag());
								} else if (TstudentExam.EXAMPASS_FALSE == entry.getSub2Score()) {// b.原初考成绩为不合格，则修改补考成绩（不合格－－〉不合格）
									entry.setSub2MakeupScore(TstudentExam.EXAMPASS_FALSE);
									entry.setSub2FishedFlag(TstudentExam.EXAMFALG_FALSE);
								}

							} else {// 1.2.2 如果原初考成绩为null,则不允许修改补考成绩
								entry.setSub2MakeupScore(entry.getSub2MakeupScore());
								entry.setSub2FishedFlag(entry.getSub2FishedFlag());
							}
						}
						// 2.用户输入补考成绩值为null(补考成绩--->null)
					} else {
						// 1.3.1如果原初考成绩不为null
						if (ValidateUtil.isValid(entry.getSub2Score())) {
							// a.原初考成绩为合格，则不允许修改补考成绩
							if (TstudentExam.EXAMPASS_TRUE == entry.getSub2Score()) {
								entry.setSub2MakeupScore(entry.getSub2MakeupScore());
								entry.setSub2FishedFlag(entry.getSub2FishedFlag());
							} else if (TstudentExam.EXAMPASS_FALSE == entry.getSub2Score()) {// b.原初考成绩为不合格，则修改补考成绩（不合格－－〉）
								entry.setSub2MakeupScore(null);
								entry.setSub2FishedFlag(TstudentExam.EXAMFALG_FALSE);
							}

						} else {// 1.3.2 如果原初考成绩为null,则不允许修改补考成绩
							entry.setSub2MakeupScore(entry.getSub2MakeupScore());
							entry.setSub2FishedFlag(entry.getSub2FishedFlag());
						}
					}
				}
			}
		}
			break;
		case 3: {
			// 报考完下一科目后，限制修改本科目成绩
			if (ValidateUtil.isValid(entry.getSub3EditAbleFlag())) {
				if (Tstudent.EXAMSELECTED_FALSE != entry.getSub3EditAbleFlag()) {
					return;
				}
			}
			// 审批通过后才能修改成绩
			if (TstudentExam.EXAMFALG_TRUE == entry.getSub3ApplyFlag() && 1 != entry.getSub3MissExamFlag()) {
				// 修改成绩 修改初考成绩(完成本科目考试后不能修改该科目的成绩)
				if (1 == model.getExamScoreFlag() && TstudentExam.EDITABLE_FALSE != entry.getSub3EditAbleFlag()) {// 修改初考成绩
					// 科目5
					if (ValidateUtil.isValid(model.getExamScore())) {
						// 1用户输入值为合格(初考成绩－－〉合格)
						if (TstudentExam.EXAMPASS_TRUE == model.getExamScore()) {
							// 1.1如果原初考成绩有效，
							if (ValidateUtil.isValid(entry.getSub3Score())) {
								// 1.1.1如果原补考成绩有效，则不能修改初考值
								if (ValidateUtil.isValid(entry.getSub3MakeupScore())) {
									entry.setSub3Score(entry.getSub3Score());
									entry.setSub3FishedFlag(entry.getSub3FishedFlag());
								} else {// 1.1.2如果原补考成绩无效，则修改初考值（初考成绩－－〉合格）
									entry.setSub3Score(TstudentExam.EXAMPASS_TRUE);
									entry.setSub3FishedFlag(TstudentExam.EXAMFALG_TRUE);
									entry.setSub3MakeupScore(null);
								}
							} else {// 1.2如果原初考成绩无效，则修改初考成绩(初考成绩－－〉合格),且补考成绩为null
								entry.setSub3Score(TstudentExam.EXAMPASS_TRUE);
								entry.setSub3FishedFlag(TstudentExam.EXAMFALG_TRUE);
								entry.setSub3MakeupScore(null);
							}
						} else if (TstudentExam.EXAMPASS_FALSE == model.getExamScore()) {// 2.用户输入值为不合格(初考成绩－－〉不合格)
							// 1.1如果原初考成绩有效，
							if (ValidateUtil.isValid(entry.getSub3Score())) {
								// 1.1.1如果原补考成绩有效，则不能修改初考值
								if (ValidateUtil.isValid(entry.getSub3MakeupScore())) {
									entry.setSub3Score(entry.getSub3Score());
									entry.setSub3FishedFlag(entry.getSub3FishedFlag());
								} else {// 1.1.2如果原补考成绩无效，则修改初考值（初考成绩－－〉合格）
									entry.setSub3Score(TstudentExam.EXAMPASS_FALSE);
									entry.setSub3FishedFlag(TstudentExam.EXAMPASS_FALSE);
									entry.setSub3MakeupScore(null);
								}
							} else {// 1.2如果原初考成绩无效，则修改初考成绩(初考成绩－－〉合格),且补考成绩为null
								entry.setSub3Score(TstudentExam.EXAMPASS_FALSE);
								entry.setSub3FishedFlag(TstudentExam.EXAMPASS_FALSE);
								entry.setSub3MakeupScore(null);
							}
						}

					} else {// 用户输入null(初考成绩－－〉null)
						// 1.1如果原初考成绩有效，
						if (ValidateUtil.isValid(entry.getSub3Score())) {
							// 1.1.1如果原补考成绩有效，则不能修改初考值
							if (ValidateUtil.isValid(entry.getSub3MakeupScore())) {
								entry.setSub3Score(entry.getSub3Score());
								entry.setSub3FishedFlag(entry.getSub3FishedFlag());
							} else {// 1.1.2如果原补考成绩无效，则修改初考值（初考成绩－－〉合格）
								entry.setSub3Score(null);
								entry.setSub3FishedFlag(TstudentExam.EXAMFALG_FALSE);
								entry.setSub3MakeupScore(null);
							}
						} else {// 1.2如果原初考成绩无效，则修改初考成绩(初考成绩－－〉合格),且补考成绩为null
							entry.setSub3Score(null);
							entry.setSub3FishedFlag(TstudentExam.EXAMFALG_FALSE);
							entry.setSub3MakeupScore(null);
						}
					}
				} else if (2 == model.getExamScoreFlag()) {// 修改补考成绩
					if (ValidateUtil.isValid(model.getExamScore())) {
						// 1.1用户输入补考成绩为合格
						if (TstudentExam.EXAMPASS_TRUE == model.getExamScore()) {
							// 1.1.1如果原初考成绩不为null
							if (ValidateUtil.isValid(entry.getSub3Score())) {
								// a.原初考成绩为合格，则不允许修改补考成绩
								if (TstudentExam.EXAMPASS_TRUE == entry.getSub3Score()) {
									entry.setSub3MakeupScore(entry.getSub3MakeupScore());
									entry.setSub3FishedFlag(entry.getSub3FishedFlag());
								} else if (TstudentExam.EXAMPASS_FALSE == entry.getSub3Score()) {// b.原初考成绩为不合格，则修改补考成绩（不合格－－〉合格）
									entry.setSub3MakeupScore(TstudentExam.EXAMPASS_TRUE);
									entry.setSub3FishedFlag(TstudentExam.EXAMFALG_TRUE);
								}

							} else {// 1.1.2 如果原初考成绩为null,则不允许修改补考成绩
								entry.setSub3MakeupScore(entry.getSub3MakeupScore());
								entry.setSub3FishedFlag(entry.getSub3FishedFlag());
							}

						} else if (TstudentExam.EXAMPASS_FALSE == model.getExamScore()) {// 1.2用户输入补考成绩为不合格
							// (补考成绩--->不合格)
							// 1.2.1如果原初考成绩不为null
							if (ValidateUtil.isValid(entry.getSub3Score())) {
								// a.原初考成绩为合格，则不允许修改补考成绩
								if (TstudentExam.EXAMPASS_TRUE == entry.getSub3Score()) {
									entry.setSub3MakeupScore(entry.getSub3MakeupScore());
									entry.setSub3FishedFlag(entry.getSub3FishedFlag());
								} else if (TstudentExam.EXAMPASS_FALSE == entry.getSub3Score()) {// b.原初考成绩为不合格，则修改补考成绩（不合格－－〉不合格）
									entry.setSub3MakeupScore(TstudentExam.EXAMPASS_FALSE);
									entry.setSub3FishedFlag(TstudentExam.EXAMFALG_FALSE);
								}

							} else {// 1.2.2 如果原初考成绩为null,则不允许修改补考成绩
								entry.setSub3MakeupScore(entry.getSub3MakeupScore());
								entry.setSub3FishedFlag(entry.getSub3FishedFlag());
							}
						}
						// 2.用户输入补考成绩值为null(补考成绩--->null)
					} else {
						// 1.3.1如果原初考成绩不为null
						if (ValidateUtil.isValid(entry.getSub3Score())) {
							// a.原初考成绩为合格，则不允许修改补考成绩
							if (TstudentExam.EXAMPASS_TRUE == entry.getSub3Score()) {
								entry.setSub3MakeupScore(entry.getSub3MakeupScore());
								entry.setSub3FishedFlag(entry.getSub3FishedFlag());
							} else if (TstudentExam.EXAMPASS_FALSE == entry.getSub3Score()) {// b.原初考成绩为不合格，则修改补考成绩（不合格－－〉）
								entry.setSub3MakeupScore(null);
								entry.setSub3FishedFlag(TstudentExam.EXAMFALG_FALSE);
							}

						} else {// 1.3.2 如果原初考成绩为null,则不允许修改补考成绩
							entry.setSub3MakeupScore(entry.getSub3MakeupScore());
							entry.setSub3FishedFlag(entry.getSub3FishedFlag());
						}
					}
				}
			}

		}
			break;
		case 4: {
			// 报考完下一科目后，限制修改本科目成绩
			if (ValidateUtil.isValid(entry.getSub4EditAbleFlag())) {
				if (Tstudent.EXAMSELECTED_FALSE != entry.getSub4EditAbleFlag()) {
					return;
				}
			}
			// 审批通过后才能修改成绩
			if (TstudentExam.EXAMFALG_TRUE == entry.getSub4ApplyFlag() && 1 != entry.getSub4MissExamFlag()) {
				// 修改成绩 修改初考成绩(完成本科目考试后不能修改该科目的成绩)
				if (1 == model.getExamScoreFlag() && TstudentExam.EDITABLE_FALSE != entry.getSub4EditAbleFlag()) {// 修改初考成绩
					// 科目5
					if (ValidateUtil.isValid(model.getExamScore())) {
						// 1用户输入值为合格(初考成绩－－〉合格)
						if (TstudentExam.EXAMPASS_TRUE == model.getExamScore()) {
							// 1.1如果原初考成绩有效，
							if (ValidateUtil.isValid(entry.getSub4Score())) {
								// 1.1.1如果原补考成绩有效，则不能修改初考值
								if (ValidateUtil.isValid(entry.getSub4MakeupScore())) {
									entry.setSub4Score(entry.getSub4Score());
									entry.setSub4FishedFlag(entry.getSub4FishedFlag());
								} else {// 1.1.2如果原补考成绩无效，则修改初考值（初考成绩－－〉合格）
									entry.setSub4Score(TstudentExam.EXAMPASS_TRUE);
									entry.setSub4FishedFlag(TstudentExam.EXAMFALG_TRUE);
									entry.setSub4MakeupScore(null);
								}
							} else {// 1.2如果原初考成绩无效，则修改初考成绩(初考成绩－－〉合格),且补考成绩为null
								entry.setSub4Score(TstudentExam.EXAMPASS_TRUE);
								entry.setSub4FishedFlag(TstudentExam.EXAMFALG_TRUE);
								entry.setSub4MakeupScore(null);
							}
							// 考试科目四合格则出驾驶证
							entry.setDriverLicenseFlag(1);
						} else if (TstudentExam.EXAMPASS_FALSE == model.getExamScore()) {// 2.用户输入值为不合格(初考成绩－－〉不合格)
							// 1.1如果原初考成绩有效，
							if (ValidateUtil.isValid(entry.getSub4Score())) {
								// 1.1.1如果原补考成绩有效，则不能修改初考值
								if (ValidateUtil.isValid(entry.getSub4MakeupScore())) {
									entry.setSub4Score(entry.getSub4Score());
									entry.setSub4FishedFlag(entry.getSub4FishedFlag());
								} else {// 1.1.2如果原补考成绩无效，则修改初考值（初考成绩－－〉合格）
									entry.setSub4Score(TstudentExam.EXAMPASS_FALSE);
									entry.setSub4FishedFlag(TstudentExam.EXAMPASS_FALSE);
									entry.setSub4MakeupScore(null);
								}
							} else {// 1.2如果原初考成绩无效，则修改初考成绩(初考成绩－－〉合格),且补考成绩为null
								entry.setSub4Score(TstudentExam.EXAMPASS_FALSE);
								entry.setSub4FishedFlag(TstudentExam.EXAMPASS_FALSE);
								entry.setSub4MakeupScore(null);
							}
							// 考试科目四合格则出驾驶证
							entry.setDriverLicenseFlag(0);
						}

					} else {// 用户输入null(初考成绩－－〉null)
						// 1.1如果原初考成绩有效，
						if (ValidateUtil.isValid(entry.getSub4Score())) {
							// 1.1.1如果原补考成绩有效，则不能修改初考值
							if (ValidateUtil.isValid(entry.getSub4MakeupScore())) {
								entry.setSub4Score(entry.getSub4Score());
								entry.setSub4FishedFlag(entry.getSub4FishedFlag());
							} else {// 1.1.2如果原补考成绩无效，则修改初考值（初考成绩－－〉合格）
								entry.setSub4Score(null);
								entry.setSub4FishedFlag(TstudentExam.EXAMFALG_FALSE);
								entry.setSub4MakeupScore(null);
							}
						} else {// 1.2如果原初考成绩无效，则修改初考成绩(初考成绩－－〉合格),且补考成绩为null
							entry.setSub4Score(null);
							entry.setSub4FishedFlag(TstudentExam.EXAMFALG_FALSE);
							entry.setSub4MakeupScore(null);
						}
						// 考试科目四合格则出驾驶证
						entry.setDriverLicenseFlag(0);
					}
				} else if (2 == model.getExamScoreFlag()) {// 修改补考成绩
					if (ValidateUtil.isValid(model.getExamScore())) {
						// 1.1用户输入补考成绩为合格
						if (TstudentExam.EXAMPASS_TRUE == model.getExamScore()) {
							// 1.1.1如果原初考成绩不为null
							if (ValidateUtil.isValid(entry.getSub4Score())) {
								// a.原初考成绩为合格，则不允许修改补考成绩
								if (TstudentExam.EXAMPASS_TRUE == entry.getSub4Score()) {
									entry.setSub4MakeupScore(entry.getSub4MakeupScore());
									entry.setSub4FishedFlag(entry.getSub4FishedFlag());
								} else if (TstudentExam.EXAMPASS_FALSE == entry.getSub4Score()) {// b.原初考成绩为不合格，则修改补考成绩（不合格－－〉合格）
									entry.setSub4MakeupScore(TstudentExam.EXAMPASS_TRUE);
									entry.setSub4FishedFlag(TstudentExam.EXAMFALG_TRUE);
								}

							} else {// 1.1.2 如果原初考成绩为null,则不允许修改补考成绩
								entry.setSub4MakeupScore(entry.getSub4MakeupScore());
								entry.setSub4FishedFlag(entry.getSub4FishedFlag());
							}
							// 考试科目四合格则出驾驶证
							entry.setDriverLicenseFlag(0);

						} else if (TstudentExam.EXAMPASS_FALSE == model.getExamScore()) {// 1.2用户输入补考成绩为不合格
							// (补考成绩--->不合格)
							// 1.2.1如果原初考成绩不为null
							if (ValidateUtil.isValid(entry.getSub4Score())) {
								// a.原初考成绩为合格，则不允许修改补考成绩
								if (TstudentExam.EXAMPASS_TRUE == entry.getSub4Score()) {
									entry.setSub4MakeupScore(entry.getSub4MakeupScore());
									entry.setSub4FishedFlag(entry.getSub4FishedFlag());
								} else if (TstudentExam.EXAMPASS_FALSE == entry.getSub4Score()) {// b.原初考成绩为不合格，则修改补考成绩（不合格－－〉不合格）
									entry.setSub4MakeupScore(TstudentExam.EXAMPASS_FALSE);
									entry.setSub4FishedFlag(TstudentExam.EXAMFALG_FALSE);
								}

							} else {// 1.2.2 如果原初考成绩为null,则不允许修改补考成绩
								entry.setSub4MakeupScore(entry.getSub4MakeupScore());
								entry.setSub4FishedFlag(entry.getSub4FishedFlag());
							}
							// 考试科目四合格则出驾驶证
							entry.setDriverLicenseFlag(0);
						}
						// 2.用户输入补考成绩值为null(补考成绩--->null)
					} else {
						// 1.3.1如果原初考成绩不为null
						if (ValidateUtil.isValid(entry.getSub4Score())) {
							// a.原初考成绩为合格，则不允许修改补考成绩
							if (TstudentExam.EXAMPASS_TRUE == entry.getSub4Score()) {
								entry.setSub4MakeupScore(entry.getSub4MakeupScore());
								entry.setSub4FishedFlag(entry.getSub4FishedFlag());
							} else if (TstudentExam.EXAMPASS_FALSE == entry.getSub4Score()) {// b.原初考成绩为不合格，则修改补考成绩（不合格－－〉）
								entry.setSub4MakeupScore(null);
								entry.setSub4FishedFlag(TstudentExam.EXAMFALG_FALSE);
							}

						} else {// 1.3.2 如果原初考成绩为null,则不允许修改补考成绩
							entry.setSub4MakeupScore(entry.getSub4MakeupScore());
							entry.setSub4FishedFlag(entry.getSub4FishedFlag());
						}
						// 考试科目四合格则出驾驶证
						entry.setDriverLicenseFlag(0);
					}
				}
			}
		}
			break;
		case 5: {
			// 报考完下一科目后，限制修改本科目成绩
			if (ValidateUtil.isValid(entry.getSub5EditAbleFlag())) {
				if (Tstudent.EXAMSELECTED_FALSE != entry.getSub5EditAbleFlag()) {
					return;
				}
			}
			// 审批通过后才能修改成绩
			if (TstudentExam.EXAMFALG_TRUE == entry.getSub5ApplyFlag() && 1 != entry.getSub5MissExamFlag()) {
				// 修改成绩 修改初考成绩(完成本科目考试后不能修改该科目的成绩)
				if (1 == model.getExamScoreFlag() && TstudentExam.EDITABLE_FALSE != entry.getSub5EditAbleFlag()) {// 修改初考成绩
					// 科目5
					if (ValidateUtil.isValid(model.getExamScore())) {
						// 1用户输入值为合格(初考成绩－－〉合格)
						if (TstudentExam.EXAMPASS_TRUE == model.getExamScore()) {
							// 1.1如果原初考成绩有效，
							if (ValidateUtil.isValid(entry.getSub5Score())) {
								// 1.1.1如果原补考成绩有效，则不能修改初考值
								if (ValidateUtil.isValid(entry.getSub5MakeupScore())) {
									entry.setSub5Score(entry.getSub5Score());
									entry.setSub5FishedFlag(entry.getSub5FishedFlag());
								} else {// 1.1.2如果原补考成绩无效，则修改初考值（初考成绩－－〉合格）
									entry.setSub5Score(TstudentExam.EXAMPASS_TRUE);
									entry.setSub5FishedFlag(TstudentExam.EXAMFALG_TRUE);
									entry.setSub5MakeupScore(null);
								}
							} else {// 1.2如果原初考成绩无效，则修改初考成绩(初考成绩－－〉合格),且补考成绩为null
								entry.setSub5Score(TstudentExam.EXAMPASS_TRUE);
								entry.setSub5FishedFlag(TstudentExam.EXAMFALG_TRUE);
								entry.setSub5MakeupScore(null);
							}
							// 考试科目四合格则出资格证
							entry.setqLicenseFlag(1);
						} else if (TstudentExam.EXAMPASS_FALSE == model.getExamScore()) {// 2.用户输入值为不合格(初考成绩－－〉不合格)
							// 1.1如果原初考成绩有效，
							if (ValidateUtil.isValid(entry.getSub5Score())) {
								// 1.1.1如果原补考成绩有效，则不能修改初考值
								if (ValidateUtil.isValid(entry.getSub5MakeupScore())) {
									entry.setSub5Score(entry.getSub5Score());
									entry.setSub5FishedFlag(entry.getSub5FishedFlag());
								} else {// 1.1.2如果原补考成绩无效，则修改初考值（初考成绩－－〉合格）
									entry.setSub5Score(TstudentExam.EXAMPASS_FALSE);
									entry.setSub5FishedFlag(TstudentExam.EXAMPASS_FALSE);
									entry.setSub5MakeupScore(null);
								}
							} else {// 1.2如果原初考成绩无效，则修改初考成绩(初考成绩－－〉合格),且补考成绩为null
								entry.setSub5Score(TstudentExam.EXAMPASS_FALSE);
								entry.setSub5FishedFlag(TstudentExam.EXAMPASS_FALSE);
								entry.setSub5MakeupScore(null);
							}
							// 考试科目四合格则出资格证
							entry.setqLicenseFlag(0);
						}

					} else {// 用户输入null(初考成绩－－〉null)
						// 1.1如果原初考成绩有效，
						if (ValidateUtil.isValid(entry.getSub5Score())) {
							// 1.1.1如果原补考成绩有效，则不能修改初考值
							if (ValidateUtil.isValid(entry.getSub5MakeupScore())) {
								entry.setSub5Score(entry.getSub5Score());
								entry.setSub5FishedFlag(entry.getSub5FishedFlag());
							} else {// 1.1.2如果原补考成绩无效，则修改初考值（初考成绩－－〉合格）
								entry.setSub5Score(null);
								entry.setSub5FishedFlag(TstudentExam.EXAMFALG_FALSE);
								entry.setSub5MakeupScore(null);
							}
						} else {// 1.2如果原初考成绩无效，则修改初考成绩(初考成绩－－〉合格),且补考成绩为null
							entry.setSub5Score(null);
							entry.setSub5FishedFlag(TstudentExam.EXAMFALG_FALSE);
							entry.setSub5MakeupScore(null);
						}
						// 考试科目四合格则出资格证
						entry.setqLicenseFlag(0);
					}
				} else if (2 == model.getExamScoreFlag()) {// 修改补考成绩
					if (ValidateUtil.isValid(model.getExamScore())) {
						// 1.1用户输入补考成绩为合格
						if (TstudentExam.EXAMPASS_TRUE == model.getExamScore()) {
							// 1.1.1如果原初考成绩不为null
							if (ValidateUtil.isValid(entry.getSub5Score())) {
								// a.原初考成绩为合格，则不允许修改补考成绩
								if (TstudentExam.EXAMPASS_TRUE == entry.getSub5Score()) {
									entry.setSub5MakeupScore(entry.getSub5MakeupScore());
									entry.setSub5FishedFlag(entry.getSub5FishedFlag());
								} else if (TstudentExam.EXAMPASS_FALSE == entry.getSub5Score()) {// b.原初考成绩为不合格，则修改补考成绩（不合格－－〉合格）
									entry.setSub5MakeupScore(TstudentExam.EXAMPASS_TRUE);
									entry.setSub5FishedFlag(TstudentExam.EXAMFALG_TRUE);
								}

							} else {// 1.1.2 如果原初考成绩为null,则不允许修改补考成绩
								entry.setSub5MakeupScore(entry.getSub5MakeupScore());
								entry.setSub5FishedFlag(entry.getSub5FishedFlag());
							}
							// 考试科目四合格则出资格证
							entry.setqLicenseFlag(1);

						} else if (TstudentExam.EXAMPASS_FALSE == model.getExamScore()) {// 1.2用户输入补考成绩为不合格
							// (补考成绩--->不合格)
							// 1.2.1如果原初考成绩不为null
							if (ValidateUtil.isValid(entry.getSub5Score())) {
								// a.原初考成绩为合格，则不允许修改补考成绩
								if (TstudentExam.EXAMPASS_TRUE == entry.getSub5Score()) {
									entry.setSub5MakeupScore(entry.getSub5MakeupScore());
									entry.setSub5FishedFlag(entry.getSub5FishedFlag());
								} else if (TstudentExam.EXAMPASS_FALSE == entry.getSub5Score()) {// b.原初考成绩为不合格，则修改补考成绩（不合格－－〉不合格）
									entry.setSub5MakeupScore(TstudentExam.EXAMPASS_FALSE);
									entry.setSub5FishedFlag(TstudentExam.EXAMFALG_FALSE);
								}

							} else {// 1.2.2 如果原初考成绩为null,则不允许修改补考成绩
								entry.setSub5MakeupScore(entry.getSub5MakeupScore());
								entry.setSub5FishedFlag(entry.getSub5FishedFlag());
							}
							// 考试科目四合格则出资格证
							entry.setqLicenseFlag(0);
						}
						// 2.用户输入补考成绩值为null(补考成绩--->null)
					} else {
						// 1.3.1如果原初考成绩不为null
						if (ValidateUtil.isValid(entry.getSub5Score())) {
							// a.原初考成绩为合格，则不允许修改补考成绩
							if (TstudentExam.EXAMPASS_TRUE == entry.getSub5Score()) {
								entry.setSub5MakeupScore(entry.getSub5MakeupScore());
								entry.setSub5FishedFlag(entry.getSub5FishedFlag());
							} else if (TstudentExam.EXAMPASS_FALSE == entry.getSub5Score()) {// b.原初考成绩为不合格，则修改补考成绩（不合格－－〉）
								entry.setSub5MakeupScore(null);
								entry.setSub5FishedFlag(TstudentExam.EXAMFALG_FALSE);
							}

						} else {// 1.3.2 如果原初考成绩为null,则不允许修改补考成绩
							entry.setSub5MakeupScore(entry.getSub5MakeupScore());
							entry.setSub5FishedFlag(entry.getSub5FishedFlag());
						}
						// 考试科目四合格则出资格证
						entry.setqLicenseFlag(0);
					}
				}
			}
		}
			break;
		}
		// 修改学员证件状态
		if (student != null) {
			studentDao.update(student);
		}
	}

	// 修改学员进度，学员档案
	private void updateStudentFileAndProgress(TstudentExam studentExam) {
		// 1.获取该考试学员的批次信息
		TexamAnalys examanlys = null;
		String sql = "select * from tb_examanalys where batch=? and schoolArea=?";
		if (ValidateUtil.isValid(studentExam.getBatch()) && ValidateUtil.isValid(studentExam.getSchoolArea())) {
			examanlys = examAnanlysDao.getSingleBySQL(TexamAnalys.class, sql, studentExam.getBatch(),
					studentExam.getSchoolArea());
		}
		Tstudent student = studentExam.getStudent();
		if (student != null && examanlys != null) {
			// 1.修改学员进度
			Tprogress progress = student.getProgress();
			if (progress != null) {
				if (ValidateUtil.isValid(studentExam.getSub5ExamFlag()) && 1 == studentExam.getSub5ExamFlag()) {
					progress.setState(8);// 参加资格考试
				} else {// 没有参加科目五考试
					if (ValidateUtil.isValid(studentExam.getSub4ExamFlag()) && 1 == studentExam.getSub4ExamFlag()) {
						progress.setState(5);
						if (ValidateUtil.isValid(examanlys.getSubject4ExamDate())) {
							progress.setSubject4Date(examanlys.getSubject4ExamDate());
						}
					} else {// 没有参加科目四考试
						if (ValidateUtil.isValid(studentExam.getSub3ExamFlag()) && 1 == studentExam.getSub3ExamFlag()) {
							progress.setState(4);
							if (ValidateUtil.isValid(examanlys.getSubject3ExamDate())) {
								progress.setSubject3Date(examanlys.getSubject3ExamDate());
							}
						} else {// 没有参加科目三考试
							if (ValidateUtil.isValid(studentExam.getSub2ExamFlag())
									&& 1 == studentExam.getSub2ExamFlag()) {
								progress.setState(3);
								if (ValidateUtil.isValid(examanlys.getSubject2ExamDate())) {
									progress.setSubject2Date(examanlys.getSubject2ExamDate());
								}
							} else {// 没有参加科目二考试
								if (ValidateUtil.isValid(studentExam.getSub1ExamFlag())
										&& 1 == studentExam.getSub1ExamFlag()) {
									progress.setState(2);
									if (ValidateUtil.isValid(examanlys.getSubject1ExamDate())) {
										progress.setSubject1Date(examanlys.getSubject1ExamDate());
									}
								} else {// 没有参加科目一考试

								}
							}
						}
					}
				}
				progressDao.update(progress);
			}
			// 2.修改学员档案
			TstudentFile file = student.getStudentFile();
			if (file != null) {
				if (ValidateUtil.isValid(studentExam.getSub5ExamFlag()) && 1 == studentExam.getSub5ExamFlag()) {
					if (ValidateUtil.isValid(examanlys.getSubject5ExamDate())) {
						file.setQualificationDate(examanlys.getSubject5ExamDate());
					}
				} else {// 没有参加科目五考试
					if (ValidateUtil.isValid(studentExam.getSub4ExamFlag()) && 1 == studentExam.getSub4ExamFlag()) {
						if (ValidateUtil.isValid(examanlys.getSubject4ExamDate())) {
							file.setSubjectFourDate(examanlys.getSubject4ExamDate());
						}
					} else {// 没有参加科目四考试
						if (ValidateUtil.isValid(studentExam.getSub3ExamFlag()) && 1 == studentExam.getSub3ExamFlag()) {
							if (ValidateUtil.isValid(examanlys.getSubject3ExamDate())) {
								file.setSubjectThreeDate(examanlys.getSubject3ExamDate());
							}
						} else {// 没有参加科目三考试
							if (ValidateUtil.isValid(studentExam.getSub2ExamFlag())
									&& 1 == studentExam.getSub2ExamFlag()) {
								if (ValidateUtil.isValid(examanlys.getSubject2ExamDate())) {
									file.setSubjectTwoDate(examanlys.getSubject2ExamDate());
								}
							} else {// 没有参加科目二考试
								if (ValidateUtil.isValid(studentExam.getSub1ExamFlag())
										&& 1 == studentExam.getSub1ExamFlag()) {
									if (ValidateUtil.isValid(examanlys.getSubject1ExamDate())) {
										file.setTheoryDate(examanlys.getSubject1ExamDate());
									}
								} else {// 没有参加科目一考试

								}
							}
						}
					}
				}
				studentFileDao.update(file);
			}
		}

	}

	private void roolbackStudentFileAndProgress(TstudentExam studentExam, Integer subjectFlag) {
		// 1.获取该考试学员的批次信息
		TexamAnalys examanlys = null;
		String sql = "select * from tb_examanalys where batch=? and schoolArea=?";
		if (ValidateUtil.isValid(studentExam.getBatch()) && ValidateUtil.isValid(studentExam.getSchoolArea())) {
			examanlys = examAnanlysDao.getSingleBySQL(TexamAnalys.class, sql, studentExam.getBatch(),
					studentExam.getSchoolArea());
		}
		Tstudent student = studentExam.getStudent();
		if (student != null && examanlys != null) {
			// 1.修改学员进度
			Tprogress progress = student.getProgress();
			if (progress != null) {
				switch (subjectFlag) {
				case 1: {// 科目1
					progress.setState(1);
					progress.setSubject1Date(null);
				}
					break;
				case 2: {// 科目2
					progress.setState(2);
					progress.setSubject2Date(null);
				}
					break;
				case 3: {// 科目3
					progress.setState(3);
					progress.setSubject3Date(null);
				}
					break;
				case 4: {// 科目4
					progress.setState(4);
					progress.setSubject4Date(null);
				}
					break;
				case 5: {// 资格
					progress.setState(5);
				}
					break;

				}
				progressDao.update(progress);
			}
			// 2.修改学员档案
			TstudentFile file = student.getStudentFile();
			if (file != null) {
				switch (subjectFlag) {
				case 1: {// 科目1
					file.setTheoryDate(null);
				}
					break;
				case 2: {// 科目2
					file.setSubjectTwoDate(null);
				}
					break;
				case 3: {// 科目3
					file.setSubjectThreeDate(null);
				}
					break;
				case 4: {// 科目4
					file.setSubjectFourDate(null);
				}
					break;
				case 5: {// 资格
					file.setQualificationDate(null);
				}
					break;

				}
				studentFileDao.update(file);
			}
		}

	}

	@Override
	public List<TstudentExam> getStudentExamByBatch(Integer batch, String SchoolArea) throws Exception {
		if (!ValidateUtil.isValid(batch) || !ValidateUtil.isValid(SchoolArea)) {
			return null;
		}
		String sql = "select * from tb_studentExams where batch=? and schoolArea=?";
		List<TstudentExam> retLists = studentExamDao.findAllBySQL(TstudentExam.class, sql, batch, SchoolArea);
		if (ValidateUtil.isValidListObject(retLists)) {
			return retLists;
		}
		return null;
	}

	@Override
	public TstudentExam getByStudentIdentityId(String schoolArea, String identityId) {
		if (!ValidateUtil.isValid(schoolArea) || !ValidateUtil.isValid(identityId)) {
			return null;
		}
		StudentExam retVal = new StudentExam();
		String sql = "select * from tb_studentExams where schoolArea=? and studentId=?";
		List<TstudentExam> studentExams = studentExamDao.findAllBySQL(TstudentExam.class, sql, schoolArea, identityId);
		if (ValidateUtil.isValidListObject(studentExams)) {
			return studentExams.get(0);
		}
		return null;
	}

}

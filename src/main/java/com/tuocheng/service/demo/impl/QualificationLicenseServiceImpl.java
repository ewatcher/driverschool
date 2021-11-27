package com.tuocheng.service.demo.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.TqualificationLicense;
import com.tuocheng.model.demo.Tstudent;
import com.tuocheng.model.demo.TstudentExam;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.QualificationLicense;
import com.tuocheng.service.demo.QualificationLicenseServiceI;
import com.tuocheng.util.base.GeneraterIdByDateUtil;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 资格证实体类service实现类
 * 
 * @author MEI702
 * 
 */
@Service("qualificationSerivce")
public class QualificationLicenseServiceImpl implements QualificationLicenseServiceI {

	private BaseDaoI<TqualificationLicense> qualificationDao;
	private BaseDaoI<Tstudent> studentDao;
	private BaseDaoI<TstudentExam> studentExamDao;

	@Autowired
	public void setStudentExamDao(BaseDaoI<TstudentExam> studentExamDao) {
		this.studentExamDao = studentExamDao;
	}

	@Autowired
	public void setQualificationDao(BaseDaoI<TqualificationLicense> qualificationDao) {
		this.qualificationDao = qualificationDao;
	}

	@Autowired
	public void setStudentDao(BaseDaoI<Tstudent> studentDao) {
		this.studentDao = studentDao;
	}

	@Override
	public QualificationLicense save(QualificationLicense qualification) {
		// 1.数据模型转换
		TqualificationLicense t = new TqualificationLicense();
		BeanUtils.copyProperties(qualification, t, new String[] { "id" });
		t.setId(UUID.randomUUID().toString());
		t.setNumbering(GeneraterIdByDateUtil.getGeneraterIdByDate());

		// 2.保存关联关系
		if ( ValidateUtil.isValid(qualification.getStudentId())) {
			Tstudent student = studentDao.get(Tstudent.class, qualification.getStudentId());
			t.setStudent(student);
			t.setSchoolArea(student.getOrganization().getId());
			// 标记该学员已经出资格证
			if (student != null) {
				TstudentExam studentExam = student.getStudentExam();
				if (studentExam != null) {
					studentExam.setqLicenseFinishFlag(1);// 标记该学员已经出驾驶证
					studentExamDao.update(studentExam);
				}
			}

		}

		// 2.保存数据
		qualificationDao.save(t);
		// 3.将保存后数据模型转换
		BeanUtils.copyProperties(t, qualification);
		// 4.将转换后的数据返回给前台
		return qualification;
	}

	@Override
	public QualificationLicense udpate(QualificationLicense qualification) {
		// 1.数据模型转换
		TqualificationLicense t = new TqualificationLicense();
		BeanUtils.copyProperties(qualification, t, new String[] { "id" });
		t.setId(qualification.getId());

		// 修改考试学员信息中的驾驶证出状态，必证标记状态
		if (ValidateUtil.isValid(qualification.getGrantState()) && 2 == qualification.getGrantState()) {// 当证件发放状态为领取时，修改证件发放状态
			String sql = "select * from tb_studentExams where studentId=? and schoolArea=?";
			if (ValidateUtil.isValid(qualification.getStudentId())
					&& ValidateUtil.isValid(qualification.getSchoolArea())) {
				TstudentExam studentExam = studentExamDao.getSingleBySQL(TstudentExam.class, sql,
						qualification.getStudentId(), qualification.getSchoolArea());
				if (studentExam != null && ValidateUtil.isValid(qualification.getGrantState())) {
					if (2 == qualification.getGrantState()) {
						studentExam.setqGrantFlag(1);
					} else {
						studentExam.setqGrantFlag(0);
					}
					studentExamDao.update(studentExam);
				}
			}
		}
		// 2.保存数据
		qualificationDao.update(t);
		// 3.将保存后数据模型转换
		BeanUtils.copyProperties(t, qualification);
		// 4.将转换后的数据返回给前台
		return qualification;
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
					TqualificationLicense qualification = qualificationDao.get(TqualificationLicense.class, id);
					// 还原该学员的出证资格
					String sql = "select * from tb_studentExams where studentId=? and schoolArea=?";
					if (ValidateUtil.isValid(qualification.getStudent().getId())) {
						TstudentExam studentExam = studentExamDao.getSingleBySQL(TstudentExam.class, sql,
								qualification.getStudent().getId(), qualification.getSchoolArea());
						if (studentExam != null) {
							if (ValidateUtil.isValid(studentExam.getqGrantFlag())) {
								studentExam.setqGrantFlag(0);
								studentExam.setqLicenseFlag(1);
								studentExam.setqLicenseFinishFlag(0);
								studentExamDao.update(studentExam);
							}

						}
					}
					// 4.调用DAO层删除数据
					qualificationDao.delete(qualification);
				}
			}

		}

	}

	@Override
	public DataGrid dataGrid(QualificationLicense qualification) {
		DataGrid dataGrid = new DataGrid();
		// 设置返回前台的数据
		dataGrid.setRows(this.changedModel(this.find(qualification)));
		// 设置总记录数
		dataGrid.setTotal(this.total(qualification));
		return dataGrid;
	}

	/**
	 * 从数据库存中查找出该实体信息并将此实体类信息转换成action专用的实体类信息，即模型转换
	 * 
	 * @param students
	 * @return
	 */
	private List<QualificationLicense> changedModel(List<TqualificationLicense> qualificationList) {
		List<QualificationLicense> retQualificationLicenses = new ArrayList<QualificationLicense>();
		if (qualificationList != null && qualificationList.size() > 0) {
			for (TqualificationLicense t : qualificationList) {
				QualificationLicense entry = new QualificationLicense();
				BeanUtils.copyProperties(t, entry);
				// 给页面展现学员属性信息
				Tstudent student = studentDao.get(Tstudent.class, t.getStudent().getId());
				entry.setStudentId(student.getId());
				entry.setStudentName(student.getName());
				entry.setStudentNo(student.getStudentNo());
				entry.setStudentSex(student.getSex());
				entry.setStudentCreateTime(student.getCreateTime());
				entry.setStudentIdentityId(student.getIdentityId());
				entry.setStudentOrganization(student.getOrganization().getName());
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
				entry.setSchoolArea(student.getOrganization().getId());
				if (student.getClazz() != null) {
					entry.setStudentClazz(student.getClazz().getName());
				}
				entry.setStudentApplyType(student.getApplyType());
				entry.setStudentDriverType(student.getDriverType());
				retQualificationLicenses.add(entry);
			}
		}
		return retQualificationLicenses;
	}

	/**
	 * 根据action专用实体类的信息查询所有dao专用的实体类信息
	 * 
	 * @param student
	 * @return
	 */
	private List<TqualificationLicense> find(QualificationLicense qualification) {
		String hql = "from TqualificationLicense t where 1=1";
		List<Object> paramList = new ArrayList<Object>();
		hql = this.addWhere(qualification, hql, paramList);
		if (qualification.getSort() != null && qualification.getOrder() != null) {
			hql += " order by " + qualification.getSort() + " " + qualification.getOrder();
		}
		return qualificationDao.find(hql, paramList, qualification.getPage(), qualification.getRows());
	}

	/**
	 * 统计符合当前查询条件的总记录数
	 * 
	 * @param student
	 * @return
	 */
	private Long total(QualificationLicense qualification) {
		// 拼接查询条件
		String hql = "select count(*) from TqualificationLicense t where 1=1 ";
		List<Object> paramsList = new ArrayList<Object>();
		hql = this.addWhere(qualification, hql, paramsList);
		return qualificationDao.count(hql, paramsList);
	}

	/**
	 * 添加查询约束条件
	 * 
	 * @param qualification
	 * @param hql
	 * @param params
	 * @return
	 */
	private String addWhere(QualificationLicense qualification, String hql, List<Object> params) {
		// 姓名模糊查询
		if (qualification.getStudentName() != null && !qualification.getStudentName().trim().equals("")) {
			hql += " and t.student.name like ?";
			params.add("%%" + qualification.getStudentName().trim() + "%%");
		}
		// 身份证精确查询
		if (qualification.getStudentIdentityId() != null && !qualification.getStudentIdentityId().trim().equals("")) {
			hql += " and t.student.identityId =?";
			params.add(qualification.getStudentIdentityId().trim());
		}
		if (ValidateUtil.isValid(qualification.getStudentNo())) {
			hql += " and t.student.studentNo =?";
			params.add(qualification.getStudentNo().trim());
		}
		// 所属校区精确查询
		if (qualification.getSchoolArea() != null && !qualification.getSchoolArea().trim().equals("")) {
			hql += " and t.schoolArea =?";
			params.add(qualification.getSchoolArea().trim());
		}
		// 领取情况查询
		if (qualification.getGrantState() != null && qualification.getGrantState() > 0) {
			hql += " and t.grantState =?";
			params.add(qualification.getGrantState());
		}
		// 驾照类型
		if (qualification.getDriverType() != null && qualification.getDriverType() > 0) {
			hql += " and t.driverType =?";
			params.add(qualification.getDriverType());
		}

		// 初领日期
		if (qualification.getFirstGetDateStart() != null) {
			hql += " and t.firstGetDate >=?";
			params.add(qualification.getFirstGetDateStart());
		}
		if (qualification.getFirstGetDateEnd() != null) {
			hql += " and t.firstGetDate <=?";
			params.add(qualification.getFirstGetDateEnd());
		}
		// 驾驶证发放日期
		if (qualification.getGrantDateStart() != null) {
			hql += " and t.grantDate >=?";
			params.add(qualification.getGrantDateStart());
		}
		if (qualification.getGrantDateEnd() != null) {
			hql += " and t.grantDate <=?";
			params.add(qualification.getGrantDateEnd());
		}

		// 编号查询
		if (qualification.getNumbering() != null && !qualification.getNumbering().trim().equals("")) {
			hql += " and t.numbering =?";
			params.add(qualification.getNumbering().trim());
		}

		return hql;
	}

}

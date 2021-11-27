package com.tuocheng.service.demo.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.collections.list.SetUniqueList;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.TdriverLicense;
import com.tuocheng.model.demo.Tstudent;
import com.tuocheng.model.demo.TstudentExam;
import com.tuocheng.model.demo.TstudentTimerCard;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.DriverLicense;
import com.tuocheng.pageModel.demo.StudentTimerCard;
import com.tuocheng.service.demo.DriverLicenseServiceI;
import com.tuocheng.util.base.GeneraterIdByDateUtil;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 人员管理实体类service实现类
 * 
 * @author MEI702
 * 
 */
@Service("driverLicenseService")
public class DriverLicenseServiceImpl implements DriverLicenseServiceI {

	private BaseDaoI<TdriverLicense> driverLicenseDao;
	private BaseDaoI<Tstudent> studentDao;
	private BaseDaoI<TstudentExam> studentExamDao;

	@Autowired
	public void setStudentExamDao(BaseDaoI<TstudentExam> studentExamDao) {
		this.studentExamDao = studentExamDao;
	}

	@Autowired
	public void setDriverLicenseDao(BaseDaoI<TdriverLicense> driverLicenseDao) {
		this.driverLicenseDao = driverLicenseDao;
	}

	@Autowired
	public void setStudentDao(BaseDaoI<Tstudent> studentDao) {
		this.studentDao = studentDao;
	}

	@Override
	public DriverLicense save(DriverLicense driverLicense) {
		// 1.数据模型转换
		TdriverLicense t = new TdriverLicense();
		BeanUtils.copyProperties(driverLicense, t, new String[] { "id" });
		t.setId(UUID.randomUUID().toString());
		t.setNumbering(GeneraterIdByDateUtil.getGeneraterIdByDate());

		// 2.保存关联关系
		if (driverLicense.getStudentId() != null && !driverLicense.getStudentId().trim().equals("")) {
			Tstudent student = studentDao.get(Tstudent.class, driverLicense.getStudentId());
			t.setStudent(student);
			t.setSchoolArea(student.getOrganization().getId());
			if (student != null) {
				TstudentExam studentExam = student.getStudentExam();
				if (studentExam != null) {
					studentExam.setDriverLicenseFinishFlag(1);// 标记该学员已经出驾驶证
					studentExamDao.update(studentExam);
				}
			}

		}
		// 修改科目四出证标记

		// 2.保存数据
		driverLicenseDao.save(t);
		// 3.将保存后数据模型转换
		BeanUtils.copyProperties(t, driverLicense);
		// 4.将转换后的数据返回给前台
		return driverLicense;
	}

	@Override
	public DriverLicense udpate(DriverLicense driverLicense) {
		// 1.数据模型转换
		TdriverLicense entry = new TdriverLicense();
		BeanUtils.copyProperties(driverLicense, entry, new String[] { "id" });
		entry.setId(driverLicense.getId());

		// 修改考试学员信息中的驾驶证出状态，必证标记状态
		if (ValidateUtil.isValid(driverLicense.getGrantState()) && 2 == driverLicense.getGrantState()) {// 当证件发放状态为领取时，修改证件发放状态
			String sql = "select * from tb_studentExams where studentId=? and schoolArea=?";
			if (ValidateUtil.isValid(driverLicense.getStudentId())
					&& ValidateUtil.isValid(driverLicense.getSchoolArea())) {
				TstudentExam studentExam = studentExamDao.getSingleBySQL(TstudentExam.class, sql,
						driverLicense.getStudentId(), driverLicense.getSchoolArea());
				if (studentExam != null && ValidateUtil.isValid(driverLicense.getGrantState())) {
					if (2 == driverLicense.getGrantState()) {
						studentExam.setDriverGrantFlag(1);
					} else {
						studentExam.setDriverGrantFlag(0);
					}
					studentExamDao.update(studentExam);
				}
			}
		}

		// 2.保存数据
		driverLicenseDao.update(entry);
		// 3.将保存后数据模型转换
		BeanUtils.copyProperties(entry, driverLicense);
		// 4.将转换后的数据返回给前台
		return driverLicense;
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
					// 还原该学员的出证资格
					TdriverLicense license = driverLicenseDao.get(TdriverLicense.class, id);
					// 修改考试学员信息中的驾驶证出状态，必证标记状态
					String sql = "select * from tb_studentExams where studentId=? and schoolArea=?";
					if (ValidateUtil.isValid(license.getStudent().getId())) {
						TstudentExam studentExam = studentExamDao.getSingleBySQL(TstudentExam.class, sql,
								license.getStudent().getId(), license.getSchoolArea());
						if (studentExam != null) {
							if (ValidateUtil.isValid(studentExam.getDriverGrantFlag())  ) {
								studentExam.setDriverGrantFlag(0);
								studentExam.setDriverLicenseFlag(1);
								studentExam.setDriverLicenseFinishFlag(0);
								studentExamDao.update(studentExam);
							}

						}
					}
					// 4.调用DAO层删除数据
					driverLicenseDao.delete(license);
				}
			}

		}
 
	}

	@Override
	public DataGrid dataGrid(DriverLicense driverLicense) {
		DataGrid dataGrid = new DataGrid();
		// 设置返回前台的数据
		dataGrid.setRows(this.changedModel(this.find(driverLicense)));
		// 设置总记录数
		dataGrid.setTotal(this.total(driverLicense));
		return dataGrid;
	}

	// 获取学员个人数据列表
	public DataGrid personalDataGrid(DriverLicense driverLicense) {
		DataGrid dataGrid = new DataGrid();
		if (ValidateUtil.isValid(driverLicense.getStudentIdentityId())) {
			// 1.根据用登录信息查找学员对象
			String hql = "from Tstudent t where t.identityId=?";
			Tstudent stu = (Tstudent) studentDao.uniqueResult(hql, driverLicense.getStudentIdentityId());
			if (stu != null) {
				// 2.根据学员对象查找该学员的驾驶证信息
				String hqlLicense = "from TdriverLicense t where t.student.id=?";
				TdriverLicense t = (TdriverLicense) driverLicenseDao.uniqueResult(hqlLicense, stu.getId());
				if (t != null) {
					DriverLicense entry = new DriverLicense();
					BeanUtils.copyProperties(t, entry);
					// 3.组织返回参数
					List<DriverLicense> retStudents = new ArrayList<DriverLicense>();
					entry.setSchoolArea(stu.getOrganization().getName());
					entry.setStudentNo(stu.getStudentNo());
					entry.setStudentName(stu.getName());
					entry.setStudentIdentityId(stu.getIdentityId());
					entry.setStudentPhone(stu.getPhone());
					retStudents.add(entry);
					// 4.将参数组成Datagrid数据模型传递给前台
					dataGrid.setRows(retStudents);
					dataGrid.setTotal(1L);
					return dataGrid;
				}
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
	private List<DriverLicense> changedModel(List<TdriverLicense> driverLicenseList) {
		List<DriverLicense> retDriverLicenses = new ArrayList<DriverLicense>();
		if (driverLicenseList != null && driverLicenseList.size() > 0) {
			for (TdriverLicense t : driverLicenseList) {
				DriverLicense entry = new DriverLicense();
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
				retDriverLicenses.add(entry);
			}
		}
		return retDriverLicenses;
	}

	/**
	 * 根据action专用实体类的信息查询所有dao专用的实体类信息
	 * 
	 * @param student
	 * @return
	 */
	private List<TdriverLicense> find(DriverLicense driverLicense) {
		String hql = "from TdriverLicense t where 1=1";
		List<Object> paramList = new ArrayList<Object>();
		hql = this.addWhere(driverLicense, hql, paramList);
		if (driverLicense.getSort() != null && driverLicense.getOrder() != null) {
			hql += " order by " + driverLicense.getSort() + " " + driverLicense.getOrder();
		}
		return driverLicenseDao.find(hql, paramList, driverLicense.getPage(), driverLicense.getRows());
	}

	/**
	 * 统计符合当前查询条件的总记录数
	 * 
	 * @param student
	 * @return
	 */
	private Long total(DriverLicense driverLicense) {
		// 拼接查询条件
		String hql = "select count(*) from TdriverLicense t where 1=1 ";
		List<Object> paramsList = new ArrayList<Object>();
		hql = this.addWhere(driverLicense, hql, paramsList);
		return driverLicenseDao.count(hql, paramsList);
	}

	/**
	 * 添加查询约束条件
	 * 
	 * @param driverLicense
	 * @param hql
	 * @param params
	 * @return
	 */
	private String addWhere(DriverLicense driverLicense, String hql, List<Object> params) {
		// 姓名模糊查询
		if (driverLicense.getStudentName() != null && !driverLicense.getStudentName().trim().equals("")) {
			hql += " and t.student.name like ?";
			params.add("%%" + driverLicense.getStudentName().trim() + "%%");
		}
		// 身份证精确查询
		if (driverLicense.getStudentIdentityId() != null && !driverLicense.getStudentIdentityId().trim().equals("")) {
			hql += " and t.student.identityId =?";
			params.add(driverLicense.getStudentIdentityId().trim());
		}
		// 学员编号查询
		if (driverLicense.getStudentNo() != null && !driverLicense.getStudentNo().trim().equals("")) {
			hql += " and t.student.studentNo like ?";
			params.add("%" + driverLicense.getStudentNo().trim());
		}

		// 所属校区精确查询
		if (driverLicense.getSchoolArea() != null && !driverLicense.getSchoolArea().trim().equals("")) {
			hql += " and t.schoolArea =?";
			params.add(driverLicense.getSchoolArea().trim());
		}
		// 领取情况查询
		if (driverLicense.getGrantState() != null && driverLicense.getGrantState() > 0) {
			hql += " and t.grantState =?";
			params.add(driverLicense.getGrantState());
		}
		// 驾照类型
		if (driverLicense.getDriverType() != null && driverLicense.getDriverType() > 0) {
			hql += " and t.driverType =?";
			params.add(driverLicense.getDriverType());
		}

		// 初领日期
		if (driverLicense.getFirstGetDateStart() != null) {
			hql += " and t.firstGetDate >=?";
			params.add(driverLicense.getFirstGetDateStart());
		}
		if (driverLicense.getFirstGetDateEnd() != null) {
			hql += " and t.firstGetDate <=?";
			params.add(driverLicense.getFirstGetDateEnd());
		}
		// 驾驶证发放日期
		if (driverLicense.getGrantDateStart() != null) {
			hql += " and t.grantDate >=?";
			params.add(driverLicense.getGrantDateStart());
		}
		if (driverLicense.getGrantDateEnd() != null) {
			hql += " and t.grantDate <=?";
			params.add(driverLicense.getGrantDateEnd());
		}

		// 编号查询
		if (driverLicense.getNumbering() != null && !driverLicense.getNumbering().trim().equals("")) {
			hql += " and t.numbering =?";
			params.add(driverLicense.getNumbering().trim());
		}

		return hql;
	}

}

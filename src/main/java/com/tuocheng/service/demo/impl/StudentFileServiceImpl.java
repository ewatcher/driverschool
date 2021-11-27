package com.tuocheng.service.demo.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.TstudentFile;
import com.tuocheng.model.demo.Tstudent;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.StudentFile;
import com.tuocheng.service.demo.StudentFileServiceI;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 预约管理实体类service实现类
 * 
 * @author 农峰
 * 
 */
@Service("studentFileService")
public class StudentFileServiceImpl implements StudentFileServiceI {

	private BaseDaoI<TstudentFile> studentFileDao;
	private BaseDaoI<Tstudent> studentDao;

	@Autowired
	public void setStudentFileDao(BaseDaoI<TstudentFile> studentFileDao) {
		this.studentFileDao = studentFileDao;
	}

	@Autowired
	public void setStudentDao(BaseDaoI<Tstudent> studentDao) {
		this.studentDao = studentDao;
	}

	@Override
	public StudentFile save(StudentFile studentFile) {
		// 1.数据模型转换
		TstudentFile t = new TstudentFile();
		BeanUtils.copyProperties(studentFile, t, new String[] { "id" });
		t.setId(UUID.randomUUID().toString());

		// 2.保存数据
		studentFileDao.save(t);
		// 3.将保存后数据模型转换
		BeanUtils.copyProperties(t, studentFile);
		// 4.将转换后的数据返回给前台
		return studentFile;
	}

	@Override
	public StudentFile udpate(StudentFile studentFile) {
		if (!ValidateUtil.isValid(studentFile.getStudentId()) || !ValidateUtil.isValid(studentFile.getId())) {
			return null;
		}
		// 1.数据模型转换
		TstudentFile entrty = studentFileDao.get(TstudentFile.class, studentFile.getId());
		Tstudent student = null;
		if (entrty != null) {
			if (studentFile.getStudentId() != null) {
				student = studentDao.get(Tstudent.class, studentFile.getStudentId());
				if (student != null) {
					entrty.setStudent(student);
					// 1.修改基本信息
					entrty.setContributionTime(studentFile.getContributionTime());
					entrty.setLearnTime(studentFile.getLearnTime());
					entrty.setTheoryDate(studentFile.getTheoryDate());
					entrty.setSubjectTwoDate(studentFile.getSubjectTwoDate());
					entrty.setSubjectThreeDate(studentFile.getSubjectThreeDate());
					entrty.setSubjectFourDate(studentFile.getSubjectFourDate());
					entrty.setQualificationDate(studentFile.getQualificationDate());
					// 2.保存数据
					studentFileDao.update(entrty);

					// 3.将保存后数据模型转换
					BeanUtils.copyProperties(entrty, studentFile);
					// 4.将转换后的数据返回给前台
					return studentFile;
				}
			}
		}

		return null;
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
					TstudentFile person = studentFileDao.get(TstudentFile.class, id);
					// TODO (将删除学员所有信息)
					// 4.调用DAO层删除数据
					studentFileDao.delete(person);
				}
			}

		}

	}

	@Override
	public DataGrid dataGrid(StudentFile studentFile) throws Exception {
		DataGrid dataGrid = new DataGrid();
		// 设置返回前台的数据
		dataGrid.setRows(this.changedModel(this.find(studentFile)));
		// 设置总记录数
		dataGrid.setTotal(this.total(studentFile));
		return dataGrid;
	}

	/**
	 * 从数据库存中查找出该实体信息并将此实体类信息转换成action专用的实体类信息，即模型转换
	 * 
	 * @param students
	 * @return
	 */
	private List<StudentFile> changedModel(List<TstudentFile> studentFiles) {
		List<StudentFile> files = new ArrayList<StudentFile>();
		if (studentFiles != null && studentFiles.size() > 0) {
			for (TstudentFile t : studentFiles) {
				StudentFile entry = new StudentFile();
				BeanUtils.copyProperties(t, entry);
				// 给页面展现学员属性信息
				Tstudent student = t.getStudent();
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
					entry.setComment(t.getComment());
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
	private List<TstudentFile> find(StudentFile studentFile) {
		String hql = "from TstudentFile t where 1=1";
		List<Object> paramList = new ArrayList<Object>();
		hql = this.addWhere(studentFile, hql, paramList);
		if (studentFile.getSort() != null && studentFile.getOrder() != null) {
			hql += " order by " + studentFile.getSort() + " " + studentFile.getOrder();
		}
		return studentFileDao.find(hql, paramList, studentFile.getPage(), studentFile.getRows());
	}

	/**
	 * 统计符合当前查询条件的总记录数
	 * 
	 * @param student
	 * @return
	 */
	private Long total(StudentFile driverLicense) {
		// 拼接查询条件
		String hql = "select count(*) from TstudentFile t where 1=1 ";
		List<Object> paramsList = new ArrayList<Object>();
		hql = this.addWhere(driverLicense, hql, paramsList);
		return studentFileDao.count(hql, paramsList);
	}

	/**
	 * 添加查询约束条件
	 * 
	 * @param studentFile
	 * @param hql
	 * @param params
	 * @return
	 */
	private String addWhere(StudentFile studentFile, String hql, List<Object> params) {
		// 姓名模糊查询
		if (studentFile.getStudentName() != null

				&& !studentFile.getStudentName().trim().equals("")) {
			hql += " and t.student.name like ?";
			params.add("%%" + studentFile.getStudentName().trim() + "%%");
		}
		// 身份证精确查询
		if (studentFile.getStudentIdentityId() != null && !studentFile.getStudentIdentityId().trim().equals("")) {
			hql += " and t.student.identityId =?";
			params.add(studentFile.getStudentIdentityId().trim());
		}
		// 所属校区精确查询
		if (studentFile.getSchoolArea() != null && !studentFile.getSchoolArea().trim().equals("")) {
			hql += " and t.schoolArea =?";
			params.add(studentFile.getSchoolArea().trim());
		}
		// 学员编号查询
		if (ValidateUtil.isValid(studentFile.getStudentNo())) {
			hql += " and t.student.studentNo like ?";
			params.add("%" + studentFile.getStudentNo().trim());
		}

		// 编号查询
		if (studentFile.getNumbering() != null && !studentFile.getNumbering().trim().equals("")) {
			hql += " and t.numbering =?";
			params.add(studentFile.getNumbering().trim());
		}
		// 交费情况（精确查询）
		if (studentFile.getFeeState() != null && studentFile.getFeeState() > 0) {
			hql += " and t.feeState =?";
			params.add(studentFile.getFeeState());
		}

		return hql;
	}

}

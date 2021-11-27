package com.tuocheng.service.demo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.Tprogress;
import com.tuocheng.model.demo.TquitSchool;
import com.tuocheng.model.demo.Tstudent;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.QuitSchool;
import com.tuocheng.service.demo.QuitschoolServiceI;
import com.tuocheng.util.base.GeneraterIdByDateUtil;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 科目二考试service实现类
 * 
 * @author MEI702
 * 
 */
@Service("quitschoolService")
public class QuitschoolServiceImpl implements QuitschoolServiceI {

	private BaseDaoI<TquitSchool> quitschoolDao;
	private BaseDaoI<Tstudent> studentDao;
	private BaseDaoI<Tprogress> progressDao;

	@Autowired
	public void setQuitschoolDao(BaseDaoI<TquitSchool> quitschoolDao) {
		this.quitschoolDao = quitschoolDao;
	}

	@Autowired
	public void setStudentDao(BaseDaoI<Tstudent> studentDao) {
		this.studentDao = studentDao;
	}

	@Autowired
	public void setProgressDao(BaseDaoI<Tprogress> progressDao) {
		this.progressDao = progressDao;
	}

	@Override
	public QuitSchool save(QuitSchool quitschool) {
		// 1.根据学员ID查找学员信息
		Tstudent student = studentDao.get(Tstudent.class,
				quitschool.getStudentId());
		// 1.1修改学员状态为退学
		student.setId(quitschool.getStudentId());
		student.setNowState(Tstudent.NOWSTATE_QUITSCHOOL);
		// 1.2更新学员信息
		studentDao.update(student);

		// 2.1保存退学登记信息
		TquitSchool t = new TquitSchool();
		BeanUtils.copyProperties(quitschool, t);
		t.setId(UUID.randomUUID().toString());
		t.setNumbering(GeneraterIdByDateUtil.getGeneraterIdByDate());
		t.setSchoolArea(student.getOrganization().getId());
		t.setStudent(student);
		quitschoolDao.save(t);
		// 2.2修改学员进度
		Tprogress progress=student.getProgress();
		progress.setState(Tprogress.PROGRESS_QUITSCHOOL);
		progress.setQuitSchoolDate(new Date());
		progress.setSchoolArea(student.getOrganization().getId());
		progressDao.update(progress);
		// 3.返回结果集
		BeanUtils.copyProperties(t, quitschool);
		return quitschool;
	}

	@Override
	public QuitSchool udpate(QuitSchool quitSchool) {
		TquitSchool t = new TquitSchool();
		BeanUtils.copyProperties(quitSchool, t, new String[] { "id" });
		t.setId(quitSchool.getId());

		quitschoolDao.update(t);
		BeanUtils.copyProperties(t, quitSchool);
		return quitSchool;
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
					TquitSchool subjectTwo = quitschoolDao.get(
							TquitSchool.class, id);

					// 4.调用DAO层删除数据
					quitschoolDao.delete(subjectTwo);
				}
			}

		}

	}

	@Override
	public DataGrid dataGrid(QuitSchool subjectTwo) {
		DataGrid retDataGrid = new DataGrid();
		retDataGrid.setRows(this.changedModel(this.find(subjectTwo)));
		retDataGrid.setTotal(this.total(subjectTwo));
		return retDataGrid;
	}

	/**
	 * 从数据库存中查找出该实体信息并将此实体类信息转换成action专用的实体类信息，即模型转换
	 * 
	 * @param students
	 * @return
	 */
	private List<QuitSchool> changedModel(List<TquitSchool> quitschoolList) {
		List<QuitSchool> retSubjects = new ArrayList<QuitSchool>();
		if (quitschoolList != null && quitschoolList.size() > 0) {
			for (TquitSchool t : quitschoolList) {
				QuitSchool entry = new QuitSchool();
				BeanUtils.copyProperties(t, entry);
				// 设置action数据模型中的student属性显示值
				Tstudent student = studentDao.get(Tstudent.class, t
						.getStudent().getId());// TODO
				entry.setStudentId(student.getId());
				entry.setStudentName(student.getName());
				entry.setStudentSex(student.getSex());
				entry.setStudentCreateTime(student.getCreateTime());
				entry.setStudentIdentityId(student.getIdentityId());
				entry.setStudentOrganization(student.getOrganization()
						.getName());
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
				if(student.getClazz()!=null){
					entry.setStudentClazz(student.getClazz().getName());
				}
				entry.setStudentApplyType(student.getApplyType());
				entry.setStudentDriverType(student.getDriverType());
				entry.setStudentNo(student.getStudentNo());
				retSubjects.add(entry);
			}
		}
		return retSubjects;
	}

	/**
	 * 根据action专用实体类的信息查询所有dao专用的实体类信息
	 * 
	 * @param student
	 * @return
	 */
	private List<TquitSchool> find(QuitSchool quitschool) {
		String hql = "from TquitSchool t where 1=1";
		List<Object> paramList = new ArrayList<Object>();
		hql = this.addWhere(quitschool, hql, paramList);
		if (quitschool.getSort() != null && quitschool.getOrder() != null) {
			hql += " order by " + quitschool.getSort() + " "
					+ quitschool.getOrder();
		}
		return quitschoolDao.find(hql, paramList, quitschool.getPage(),
				quitschool.getRows());
	}

	/**
	 * 统计符合当前查询条件的总记录数
	 * 
	 * @param student
	 * @return
	 */
	private Long total(QuitSchool subject) {
		// 拼接查询条件
		String hql = "select count(*) from TquitSchool t where 1=1 ";
		List<Object> paramsList = new ArrayList<Object>();
		hql = this.addWhere(subject, hql, paramsList);
		return quitschoolDao.count(hql, paramsList);
	}

	/**
	 * 添加查询约束条件
	 * 
	 * @param quitschool
	 * @param hql
	 * @param params
	 * @return
	 */
	private String addWhere(QuitSchool quitschool, String hql,
			List<Object> params) {

		// 所属部门精确查询(采用违反第三范式，添加冗余字段，解决关联关系ognl达到三层问题)
		if (quitschool.getSchoolArea() != null
				&& !quitschool.getSchoolArea().trim().equals("")) {
			hql += " and t.schoolArea like ?";
			params.add("%%" + quitschool.getSchoolArea().trim() + "%%");

		}
		// 编号
		if (quitschool.getNumbering() != null
				&& !quitschool.getNumbering().trim().equals("")) {
			hql += " and t.numbering =?";
			params.add(quitschool.getNumbering().trim());
		}
		// 退学日期(范围查询)
		if (quitschool.getQuitDateStart() != null) {
			hql += " and t.quitDate >= ?";
			params.add(quitschool.getQuitDateStart());
		}
		if (quitschool.getQuitDateEnd() != null) {
			hql += " and t.quitDate <= ?";
			params.add(quitschool.getQuitDateEnd());
		}
		// 根据学员姓名查询
		if (quitschool.getStudentName() != null
				&& !quitschool.getStudentName().trim().equals("")) {
			hql += " and t.student.name like ?";
			params.add("%%" + quitschool.getStudentName().trim() + "%%");
		}
		// 根据学员身份证号查询
		if (quitschool.getStudentIdentityId() != null
				&& !quitschool.getStudentIdentityId().trim().equals("")) {
			hql += " and t.student.identityId=?";
			params.add(quitschool.getStudentIdentityId().trim());
		}
		if(ValidateUtil.isValid(quitschool.getStudentNo())){
			hql += " and t.student.studentNo=?";
			params.add(quitschool.getStudentNo().trim());
		}

		return hql;
	}

}

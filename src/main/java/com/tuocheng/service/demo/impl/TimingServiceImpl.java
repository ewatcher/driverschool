package com.tuocheng.service.demo.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.TpersonalTiming;
import com.tuocheng.model.demo.Tstudent;
import com.tuocheng.model.demo.Ttiming;
import com.tuocheng.model.demo.Ttrainer;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.Timing;
import com.tuocheng.service.demo.TimingServiceI;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 学员学时管理实体类service实现类
 * 
 * @author 农峰
 * 
 */
@Service("timingService")
public class TimingServiceImpl implements TimingServiceI {

	private BaseDaoI<Ttiming> timingDao;
	private BaseDaoI<Tstudent> studentDao;
	private BaseDaoI<TpersonalTiming> personalTimingDao;

	// 注入DAO
	@Autowired
	public void setTimingDao(BaseDaoI<Ttiming> timingDao) {
		this.timingDao = timingDao;
	}

	// 注入DAO
	@Autowired
	public void setStudentDao(BaseDaoI<Tstudent> studentDao) {
		this.studentDao = studentDao;
	}

	@Autowired
	public void setPersonalTimingDao(BaseDaoI<TpersonalTiming> personalTimingDao) {
		this.personalTimingDao = personalTimingDao;
	}

	@Override
	public Timing save(Timing timing) {
		// 1.数据模型转换
		Ttiming t = new Ttiming();
		BeanUtils.copyProperties(timing, t, new String[] { "id" });
		t.setId(UUID.randomUUID().toString());

		// 2.保存数据
		timingDao.save(t);
		// 3.将保存后数据模型转换
		BeanUtils.copyProperties(t, timing);
		// 4.将转换后的数据返回给前台
		return timing;
	}

	@Override
	public Timing udpate(Timing timing) {
		// 1.获取学员信息
		Tstudent student = studentDao
				.get(Tstudent.class, timing.getStudentId());
		if (student != null) {
			if (timing.getRestTiming() != null && timing.getRestTiming() > 0) {
				student.setRestTiming(1);
			} else {
				student.setRestTiming(0);
			}
			studentDao.update(student);
		}
		// 2.修改该学员的学时信息
		Ttiming entry = timingDao.get(Ttiming.class, timing.getId());
		BeanUtils.copyProperties(timing, entry, new String[] { "id" });
		entry.setId(timing.getId());
		entry.setNumbering(timing.getNumbering());
		// 可预约学时
		entry.setReservationTiming(timing.getTotalTiming());
		// 3.更新数据
		timingDao.update(entry);

		// 4.将保存后数据模型转换
		BeanUtils.copyProperties(entry, timing);
		// 5.将转换后的数据返回给前台
		return timing;
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
					Ttiming timing = timingDao.get(Ttiming.class, id);
					// 4.1级联删除学员学时明细信息
					Set<TpersonalTiming> personalTimings = timing
							.getPersonalTimings();
					if (personalTimings != null && personalTimings.size() > 0) {
						for (TpersonalTiming t : personalTimings) {
							personalTimingDao.delete(t);
						}
					}
					// 4.2.调用DAO层删除数据
					timingDao.delete(timing);
				}
			}

		}

	}

	@Override
	public DataGrid dataGrid(Timing timing) throws Exception {
		DataGrid dataGrid = new DataGrid();
		// 设置返回前台的数据
		dataGrid.setRows(this.changedModel(this.find(timing)));
		// 设置总记录数
		dataGrid.setTotal(this.total(timing));
		return dataGrid;
	}

	public DataGrid personalDataGrid(Timing timing) throws Exception {
		DataGrid dataGrid = new DataGrid();
		if (ValidateUtil.isValid(timing.getStudentIdentityId())) {
			// 1.通过登录用户信息查找学员
			String hql = "from Tstudent t where t.identityId=?";
			Tstudent stu = (Tstudent) studentDao.uniqueResult(hql,
					timing.getStudentIdentityId());
			if (stu != null) {
				// 2.要所学员与理论考试关联关系查找该学员的理论考试信息
				Ttiming tim = stu.getTiming();
				Timing entry = new Timing();
				BeanUtils.copyProperties(tim, entry);
				// 3.组织返回前台的参数
				List<Timing> rets = new ArrayList<Timing>();
				entry.setSchoolArea(stu.getOrganization().getName());
				entry.setStudentNo(stu.getStudentNo());
				entry.setStudentName(stu.getName());
				entry.setStudentIdentityId(stu.getIdentityId());
				entry.setStudentDriverType(stu.getDriverType());
				entry.setStudentCreateTime(stu.getCreateTime());
				entry.setStudentPhone(stu.getPhone());
				if (stu.getClazz() != null) {
					entry.setStudentClazz(stu.getClazz().getName());
				}
				if (stu.getTrainer() != null) {
					entry.setTrainerName(stu.getTrainer().getName());
				}
				rets.add(entry);
				// 4.将组织后的数据通过Datagrid模型传递到前台
				dataGrid.setRows(rets);
				dataGrid.setTotal(1L);
				return dataGrid;
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
	private List<Timing> changedModel(List<Ttiming> timings) {
		List<Timing> files = new ArrayList<Timing>();
		if (timings != null && timings.size() > 0) {
			for (Ttiming t : timings) {
				Timing entry = new Timing();
				BeanUtils.copyProperties(t, entry);
				// 给页面展现学员属性信息
				Tstudent student = t.getStudent();
				Ttrainer trainer = null;
				if (student.getTrainer() != null) {
					trainer = student.getTrainer();
				}
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
				entry.setTimingFlag(student.getTimingFlag());
				if (student.getClazz() != null) {
					entry.setStudentClazz(student.getClazz().getName());
				}
				if (student.getTrainer() != null) {
					entry.setTrainerName(student.getTrainer().getName());
				}
				entry.setStudentApplyType(student.getApplyType());
				entry.setStudentDriverType(student.getDriverType());
				entry.setSchoolArea(student.getOrganization().getId());
				entry.setSchoolAreaName(student.getOrganization().getName());
				entry.setStudentNo(student.getStudentNo());
				if (trainer != null) {
					entry.setTrainerId(trainer.getId());
					entry.setTrainerName(trainer.getName());
				}
				files.add(entry);

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
	private List<Ttiming> find(Timing studentFile) {
		String hql = "from Ttiming t where 1=1";
		List<Object> paramList = new ArrayList<Object>();
		hql = this.addWhere(studentFile, hql, paramList);
		if (studentFile.getSort() != null && studentFile.getOrder() != null) {
			hql += " order by " + studentFile.getSort() + " "
					+ studentFile.getOrder();
		}
		return timingDao.find(hql, paramList, studentFile.getPage(),
				studentFile.getRows());
	}

	/**
	 * 统计符合当前查询条件的总记录数
	 * 
	 * @param student
	 * @return
	 */
	private Long total(Timing driverLicense) {
		// 拼接查询条件
		String hql = "select count(*) from Ttiming t where 1=1 ";
		List<Object> paramsList = new ArrayList<Object>();
		hql = this.addWhere(driverLicense, hql, paramsList);
		return timingDao.count(hql, paramsList);
	}

	/**
	 * 添加查询约束条件
	 * 
	 * @param timing
	 * @param hql
	 * @param params
	 * @return
	 */
	private String addWhere(Timing timing, String hql, List<Object> params) {
		// 姓名模糊查询
		if (timing.getStudentName() != null

		&& !timing.getStudentName().trim().equals("")) {
			hql += " and t.student.name like ?";
			params.add("%%" + timing.getStudentName().trim() + "%%");
		}
		// 身份证精确查询
		if (timing.getStudentIdentityId() != null
				&& !timing.getStudentIdentityId().trim().equals("")) {
			hql += " and t.student.identityId =?";
			params.add(timing.getStudentIdentityId().trim());
		}
		// 所属校区精确查询
		if (timing.getSchoolArea() != null
				&& !timing.getSchoolArea().trim().equals("")) {
			hql += " and t.schoolArea =?";
			params.add(timing.getSchoolArea().trim());
		}

		// 学员编号查询
		if (ValidateUtil.isValid(timing.getStudentNo())) {
			hql += " and t.student.studentNo like ?";
			params.add("%%" + timing.getStudentNo().trim()+"%%");
		}

		return hql;
	}

	@Override
	public Timing getSingleByStudentId(String studentId) throws Exception {
		String hql = " from Ttiming t where t.student.id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(studentId);
		Ttiming entry = timingDao.get(hql, params);
		if (entry != null) {
			Timing retEntry = new Timing();
			BeanUtils.copyProperties(entry, retEntry);
			return retEntry;
		}
		return null;
	}

}

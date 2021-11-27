package com.tuocheng.service.demo.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.dialect.lock.PessimisticEntityLockException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.TpersonalTiming;
import com.tuocheng.model.demo.Tstudent;
import com.tuocheng.model.demo.Ttiming;
import com.tuocheng.model.demo.Ttrainer;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.PersonalTiming;
import com.tuocheng.pageModel.demo.Timing;
import com.tuocheng.pageModel.demo.Trainer;
import com.tuocheng.service.demo.PersonalTimingServiceI;
import com.tuocheng.util.base.GeneraterIdByDateUtil;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 学员学时管理实体类service实现类
 * 
 * @author 农峰
 * 
 */
@Service("personalTimingService")
public class PersonalTimingServiceImpl implements PersonalTimingServiceI {

	private BaseDaoI<TpersonalTiming> personalTimingDao;
	private BaseDaoI<Ttiming> timingDao;
	private BaseDaoI<Ttrainer> trainerDao;
	private BaseDaoI<Tstudent> studentDao;

	@Autowired
	public void setPersonalTimingDao(BaseDaoI<TpersonalTiming> personalTimingDao) {
		this.personalTimingDao = personalTimingDao;
	}

	@Autowired
	public void setTimingDao(BaseDaoI<Ttiming> timingDao) {
		this.timingDao = timingDao;
	}

	@Autowired
	public void setTrainerDao(BaseDaoI<Ttrainer> trainerDao) {
		this.trainerDao = trainerDao;
	}

	@Autowired
	public void setStudentDao(BaseDaoI<Tstudent> studentDao) {
		this.studentDao = studentDao;
	}

	@Override
	public PersonalTiming save(PersonalTiming personTiming) {
		Ttiming tempTiming = null;
		Tstudent student = null;
		boolean addTimingUsingRecord = false;// 处理当前用户输入学时数大于剩余学时数
		// 1.数据模型转换
		TpersonalTiming entry = new TpersonalTiming();
		BeanUtils.copyProperties(personTiming, entry, new String[] { "id" });
		entry.setId(UUID.randomUUID().toString());
		entry.setNumbering(GeneraterIdByDateUtil.getGeneraterIdByDate());
		entry.setSchoolArea(personTiming.getSchoolArea());
		entry.setCreateWay(0);
		if (personTiming.getTimingId() != null) {
			tempTiming = timingDao.get(Ttiming.class,
					personTiming.getTimingId());
			student = tempTiming.getStudent();
			// 一.计时类学员
			if (student.getTimingFlag() == Tstudent.TIMING_COUNTING) {
				// 1.判断是购买学时还是消费学时记录
				// 1)购买学时
				// 计时类学员，学员算法(不计时学员，不存在购买学)
				if (personTiming.getTimingType() == TpersonalTiming.TIMINGTYPE_BUY) {
					if (student.getTimingFlag() == Tstudent.TIMING_COUNTING) {
						// 总学时＝原总学时+新购买学时
						tempTiming.setTotalTiming(tempTiming.getTotalTiming()
								+ personTiming.getUseTiming());
						// 剩余学时
						Integer rest = tempTiming.getRestTiming()
								+ personTiming.getUseTiming();
						tempTiming.setRestTiming(rest);
						tempTiming.setReservationTiming(rest);

						if (rest > 0) {
							student.setRestTiming(1);// 有剩余学时
						} else {
							student.setRestTiming(0);
						}
						// 购买学时
						tempTiming.setBuyTiming(tempTiming.getBuyTiming()
								+ personTiming.getUseTiming());
						addTimingUsingRecord = true;
					}
				} else if (personTiming.getTimingType() == TpersonalTiming.TIMINGTYPE_USING// 消费学时
						&& tempTiming.getRestTiming() > 0) {
					// 计时学员
					if ((tempTiming.getRestTiming() - personTiming
							.getUseTiming()) >= 0) {
						// 剩余学时减+params
						if (personTiming.getUseTiming() > 0) {
							Integer rest = tempTiming.getRestTiming()
									- personTiming.getUseTiming();
							tempTiming.setRestTiming(rest);
							tempTiming.setReservationTiming(rest);

							// 记录学员消费的学时类型电车，模拟，五项，路训
							if (personTiming.getTimingUsingType() == Ttiming.USINGTYPE_TRAMCAR) {// 电车
								tempTiming.setTramTiming(tempTiming
										.getTramTiming()
										+ personTiming.getUseTiming());
							} else if (personTiming.getTimingUsingType() == Ttiming.USINGTYPE_SIMULATION) {// 模拟
								tempTiming.setSimulationTiming(tempTiming
										.getSimulationTiming()
										+ personTiming.getUseTiming());
							} else if (personTiming.getTimingUsingType() == Ttiming.USINGTYPE_FIVEITERM
									|| personTiming.getTimingUsingType() == Ttiming.USINGTYPE_ROADTRAINING) {// 实车
								tempTiming.setCarTiming(tempTiming
										.getCarTiming()
										+ personTiming.getUseTiming());
							}

							if (rest > 0) {
								student.setRestTiming(1);// 有剩余学时
							} else {
								student.setRestTiming(0);
							}
							// 已经使用的学时+params
							tempTiming.setUsingTiming(tempTiming
									.getUsingTiming()
									+ personTiming.getUseTiming());
							addTimingUsingRecord = true;
						} else {
							addTimingUsingRecord = false;
						}

					}
				}
			} else if (student.getTimingFlag() == Tstudent.TIMING_UNCOUNTING) {// 不计时学员
				// 总学时
				if (tempTiming.getTotalTiming() == null) {
					tempTiming.setTotalTiming(personTiming.getUseTiming());
				}
				tempTiming.setTotalTiming(tempTiming.getTotalTiming()
						+ personTiming.getUseTiming());

				// 已经使用的学时+params
				// 记录学员消费的学时类型电车，模拟，五项，路训
				if (personTiming.getTimingUsingType() == Ttiming.USINGTYPE_TRAMCAR) {// 电车
					if (tempTiming.getTramTiming() == null) {
						tempTiming.setTramTiming(personTiming.getUseTiming());
					}
					tempTiming.setTramTiming(tempTiming.getTramTiming()
							+ personTiming.getUseTiming());

				} else if (personTiming.getTimingUsingType() == Ttiming.USINGTYPE_SIMULATION) {// 模拟
					if (tempTiming.getSimulationTiming() == null) {
						tempTiming.setSimulationTiming(personTiming
								.getUseTiming());
					}
					tempTiming.setSimulationTiming(tempTiming
							.getSimulationTiming()
							+ personTiming.getUseTiming());
				} else if (personTiming.getTimingUsingType() == Ttiming.USINGTYPE_FIVEITERM
						|| personTiming.getTimingUsingType() == Ttiming.USINGTYPE_ROADTRAINING) {// 实车
					if (tempTiming.getCarTiming() == null) {
						tempTiming.setCarTiming(personTiming.getUseTiming());
					}
					tempTiming.setCarTiming(tempTiming.getCarTiming()
							+ personTiming.getUseTiming());
				}
				// 已经使用的学时+params
				if (tempTiming.getUsingTiming() == null) {
					tempTiming.setUsingTiming(personTiming.getUseTiming());
				}
				tempTiming.setUsingTiming(tempTiming.getUsingTiming()
						+ personTiming.getUseTiming());

				addTimingUsingRecord = true;
			}
			// 2.修改个人学时总的统计信息
			timingDao.update(tempTiming);
			studentDao.update(student);

			entry.setTiming(tempTiming);
		}
		if (ValidateUtil.isValid(personTiming.getTrainerId())) {
			Ttrainer trainer = trainerDao.get(Ttrainer.class,
					personTiming.getTrainerId());
			entry.setTrainer(trainer);
		}
		if (addTimingUsingRecord) {
			// 2.保存数据
			personalTimingDao.save(entry);
			// 3.将保存后数据模型转换
			BeanUtils.copyProperties(entry, personTiming);
			// 4.将转换后的数据返回给前台
			return personTiming;
		}
		return null;
	}

	@Override
	public PersonalTiming udpate(PersonalTiming timing) {
		// 1.数据模型转换
		TpersonalTiming t = new TpersonalTiming();
		BeanUtils.copyProperties(timing, t, new String[] { "id" });
		t.setId(timing.getId());
		t.setNumbering(timing.getNumbering());
		if (ValidateUtil.isValid(timing.getTimingId())) {
			Ttiming ti = timingDao.get(Ttiming.class, timing.getTimingId());
			t.setTiming(ti);
		}
		if (ValidateUtil.isValid(timing.getTrainerId())) {
			Ttrainer trainer = trainerDao.get(Ttrainer.class,
					timing.getTrainerId());
			t.setTrainer(trainer);
		}
		// 2.保存数据
		personalTimingDao.update(t);

		// 3.将保存后数据模型转换
		BeanUtils.copyProperties(t, timing);
		// 4.将转换后的数据返回给前台
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
					TpersonalTiming personTiming = personalTimingDao.get(
							TpersonalTiming.class, id);

					if (personTiming.getCreateWay() != TpersonalTiming.CREATE_RESERVATION) {
						// 修改当前学员总学时的统计情况
						Ttiming timing = personTiming.getTiming();
						Tstudent student = timing.getStudent();
						// 计时学员
						if (student.getTimingFlag() == Tstudent.TIMING_COUNTING) {
							// 3.1判断当前删除记录的学时类型（购买/消费）
							if (personTiming.getTimingType() == TpersonalTiming.TIMINGTYPE_BUY) {
								// 3.1.1删除记录为购买信息
								// 3.1.1.1　购买总学时＝购买总学时－当前记录的购买数
								timing.setTotalTiming(timing.getTotalTiming()
										- personTiming.getUseTiming());
								// 3.1.1.1　购买学时数＝总购买学时数－当前记录的购买数
								timing.setBuyTiming(timing.getBuyTiming()
										- personTiming.getUseTiming());
								// 3.1.1.1　剩余学时数＝总剩余学时数－当前记录的购买数
								Integer rest = timing.getRestTiming()
										- personTiming.getUseTiming();
								timing.setRestTiming(rest);
								timing.setReservationTiming(rest);
								if (rest > 0) {
									student.setRestTiming(1);
								} else {
									student.setRestTiming(0);
								}
							} else {
								// 3.1.2删除记录为消费
								// 已用学时=已用学时-当前记录学时数
								timing.setUsingTiming(timing.getUsingTiming()
										- personTiming.getUseTiming());
								// 剩余学时＝剩余学时+当前记录学时数
								Integer rest = timing.getRestTiming()
										+ personTiming.getUseTiming();
								timing.setRestTiming(rest);
								timing.setReservationTiming(rest);
								// 删除记录时判断该学时记录类型并恢复其学时
								if (personTiming.getTimingUsingType() == Ttiming.USINGTYPE_TRAMCAR) {// 电车
									timing.setTramTiming(timing.getTramTiming()
											- personTiming.getUseTiming());
								} else if (personTiming.getTimingUsingType() == Ttiming.USINGTYPE_SIMULATION) {// 模拟
									timing.setSimulationTiming(timing
											.getSimulationTiming()
											- personTiming.getUseTiming());
								} else if (personTiming.getTimingUsingType() == Ttiming.USINGTYPE_FIVEITERM
										|| personTiming.getTimingUsingType() == Ttiming.USINGTYPE_ROADTRAINING) {// 实车
									timing.setCarTiming(timing.getCarTiming()
											- personTiming.getUseTiming());
								}

								if (rest > 0) {
									student.setRestTiming(1);
								} else {
									student.setRestTiming(0);
								}
							}
						} else if (student.getTimingFlag() == Tstudent.TIMING_UNCOUNTING) {// 不计时学员
							timing.setTotalTiming(timing.getTotalTiming()
									- personTiming.getUseTiming());
							timing.setUsingTiming(timing.getUsingTiming()
									- personTiming.getUseTiming());
							timing.setCarTiming(timing.getCarTiming()
									- personTiming.getUseTiming());
						}

						timingDao.update(timing);
						studentDao.update(student);

						// 4.调用DAO层删除数据
						personalTimingDao.delete(personTiming);
					}
					continue;
				}
			}

		}

	}

	@Override
	public DataGrid dataGrid(PersonalTiming timing) throws Exception {
		DataGrid dataGrid = new DataGrid();
		// 设置返回前台的数据
		dataGrid.setRows(this.changedModel(this.find(timing)));
		// 设置总记录数
		dataGrid.setTotal(this.total(timing));
		return dataGrid;
	}

	/**
	 * 从数据库存中查找出该实体信息并将此实体类信息转换成action专用的实体类信息，即模型转换
	 * 
	 * @param students
	 * @return
	 */
	private List<PersonalTiming> changedModel(List<TpersonalTiming> studentFiles) {
		List<PersonalTiming> files = new ArrayList<PersonalTiming>();
		if (studentFiles != null && studentFiles.size() > 0) {
			for (TpersonalTiming t : studentFiles) {
				PersonalTiming entry = new PersonalTiming();
				BeanUtils.copyProperties(t, entry);
				// 给页面展现学员属性信息
				if (t.getTrainer() != null) {
					entry.setTrainerName(t.getTrainer().getName());
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
	private List<TpersonalTiming> find(PersonalTiming personalTiming) {
		String hql = "from TpersonalTiming t where 1=1";
		List<Object> paramList = new ArrayList<Object>();
		hql = this.addWhere(personalTiming, hql, paramList);
		if (personalTiming.getSort() != null
				&& personalTiming.getOrder() != null) {
			hql += " order by " + personalTiming.getSort() + " "
					+ personalTiming.getOrder();
		}
		return personalTimingDao.find(hql, paramList, personalTiming.getPage(),
				personalTiming.getRows());
	}

	/**
	 * 统计符合当前查询条件的总记录数
	 * 
	 * @param student
	 * @return
	 */
	private Long total(PersonalTiming personalTiming) {
		// 拼接查询条件
		String hql = "select count(*) from TpersonalTiming t where 1=1 ";
		List<Object> paramsList = new ArrayList<Object>();
		hql = this.addWhere(personalTiming, hql, paramsList);
		return personalTimingDao.count(hql, paramsList);
	}

	/**
	 * 添加查询约束条件
	 * 
	 * @param personalTiming
	 * @param hql
	 * @param params
	 * @return
	 */
	private String addWhere(PersonalTiming personalTiming, String hql,
			List<Object> params) {
		// 姓名模糊查询
		if (ValidateUtil.isValid(personalTiming.getStudentName())) {
			hql += " and t.student.name like ?";
			params.add("%%" + personalTiming.getStudentName().trim() + "%%");
		}

		// 编号查询
		if (ValidateUtil.isValid(personalTiming.getNumbering())) {
			hql += " and t.numbering =?";
			params.add(personalTiming.getNumbering().trim());
		}

		if (ValidateUtil.isValid(personalTiming.getStudentId())) {
			hql += " and t.studentId =?";
			params.add(personalTiming.getStudentId().trim());
		}
		if (ValidateUtil.isValid(personalTiming.getSchoolArea())) {
			hql += " and t.schoolArea =?";
			params.add(personalTiming.getSchoolArea().trim());
		}

		return hql;
	}

}

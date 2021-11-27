package com.tuocheng.service.demo.impl;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.aop.framework.autoproxy.target.QuickTargetSourceCreator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.Tclass;
import com.tuocheng.model.demo.TnetPiont;
import com.tuocheng.model.demo.Torganization;
import com.tuocheng.model.demo.Tperson;
import com.tuocheng.model.demo.TpersonalTiming;
import com.tuocheng.model.demo.Tprogress;
import com.tuocheng.model.demo.TquitSchool;
import com.tuocheng.model.demo.Treservation;
import com.tuocheng.model.demo.Tstudent;
import com.tuocheng.model.demo.TstudentFile;
import com.tuocheng.model.demo.TstudentTimerCard;
import com.tuocheng.model.demo.Ttiming;
import com.tuocheng.model.demo.Ttrainer;
import com.tuocheng.model.demo.TtrainerReservation;
import com.tuocheng.model.demo.TtrainerReservationDetail;
import com.tuocheng.model.demo.Tuser;
import com.tuocheng.model.demo.TusingCar;
import com.tuocheng.pageModel.base.ComboboxJson;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.base.ImportReturnModel;
import com.tuocheng.pageModel.demo.Student;
import com.tuocheng.pageModel.demo.Timing;
import com.tuocheng.pageModel.demo.User;
import com.tuocheng.service.demo.StudentServiceI;
import com.tuocheng.util.base.DateUtil;
import com.tuocheng.util.base.GeneraterIdByDateUtil;
import com.tuocheng.util.base.StringUtil;
import com.tuocheng.util.base.Util;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 学生实体类serivce实现类
 * 
 * @author MEI702
 * 
 */
@Service("studentService")
public class StudentServiceImpl implements StudentServiceI {

	private BaseDaoI<Tstudent> studentDao;
	private BaseDaoI<Tclass> clazzDao;
	private BaseDaoI<Torganization> organizationDao;
	private BaseDaoI<TstudentTimerCard> ttcDao;
	private BaseDaoI<TstudentFile> studentFileDao;
	private BaseDaoI<Ttiming> timingDaoI;
	private BaseDaoI<Ttrainer> trainerDao;
	private BaseDaoI<Tperson> personDao;
	private BaseDaoI<TpersonalTiming> personalTimingDao;
	private BaseDaoI<Tprogress> progressDao;

	private BaseDaoI<TtrainerReservationDetail> trainerReservationDetailDao;

	private BaseDaoI<TusingCar> usingCarDao;
	private BaseDaoI<Treservation> reservationDao;
	private BaseDaoI<TnetPiont> netPiontDao;
	private BaseDaoI<TquitSchool> quitschoolDao;

	@Autowired
	public void setQuitschoolDao(BaseDaoI<TquitSchool> quitschoolDao) {
		this.quitschoolDao = quitschoolDao;
	}

	@Autowired
	public void setNetPiontDao(BaseDaoI<TnetPiont> netPiontDao) {
		this.netPiontDao = netPiontDao;
	}

	// 给当前的service注入相应的Dao对象
	@Autowired
	public void setStudentDao(BaseDaoI<Tstudent> studentDao) {
		this.studentDao = studentDao;
	}

	// 给当前的service注入相应的Dao对象
	@Autowired
	public void setClazzDao(BaseDaoI<Tclass> clazzDao) {
		this.clazzDao = clazzDao;
	}

	// 给当前的service注入相应的Dao对象
	@Autowired
	public void setOrganizationDao(BaseDaoI<Torganization> organizationDao) {
		this.organizationDao = organizationDao;
	}

	// 给当前的service注入相应的Dao对象
	@Autowired
	public void setStudentTimerCardDao(BaseDaoI<TstudentTimerCard> ttcDao) {
		this.ttcDao = ttcDao;
	}

	@Autowired
	public void setStudentFileDao(BaseDaoI<TstudentFile> studentFileDao) {
		this.studentFileDao = studentFileDao;
	}

	@Autowired
	public void setTimingDaoI(BaseDaoI<Ttiming> timingDaoI) {
		this.timingDaoI = timingDaoI;
	}

	@Autowired
	public void setTrainerDao(BaseDaoI<Ttrainer> trainerDao) {
		this.trainerDao = trainerDao;
	}

	@Autowired
	public void setPersonDao(BaseDaoI<Tperson> personDao) {
		this.personDao = personDao;
	}

	@Autowired
	public void setPersonalTimingDao(BaseDaoI<TpersonalTiming> personalTimingDao) {
		this.personalTimingDao = personalTimingDao;
	}

	@Autowired
	public void setTtcDao(BaseDaoI<TstudentTimerCard> ttcDao) {
		this.ttcDao = ttcDao;
	}

	@Autowired
	public void setTrainerReservationDetailDao(BaseDaoI<TtrainerReservationDetail> trainerReservationDetailDao) {
		this.trainerReservationDetailDao = trainerReservationDetailDao;
	}

	@Autowired
	public void setProgressDao(BaseDaoI<Tprogress> progressDao) {
		this.progressDao = progressDao;
	}

	@Autowired
	public void setUsingCarDao(BaseDaoI<TusingCar> usingCarDao) {
		this.usingCarDao = usingCarDao;
	}

	@Autowired
	public void setReservationDao(BaseDaoI<Treservation> reservationDao) {
		this.reservationDao = reservationDao;
	}

	@Override
	public Student save(Student student) {
		Ttrainer trainer = new Ttrainer();
		Torganization organization = null;
		// 1.添加用记信息
		Tstudent s = new Tstudent();
		String studentIdTemp = UUID.randomUUID().toString();
		// 模型数据转换：将action中的model数据拷贝给dao中的model
		BeanUtils.copyProperties(student, s, new String[] { "id" });
		s.setId(studentIdTemp);
		s.setExamSelected(Tstudent.EXAMSELECTED_FALSE);// 默认还没有参加考试
		// 设置各校区学员编号排序问题(格式："校区代号"+"驾照代号"+学号后4位)
		String studentNo = student.getStudentNo();
		String startSrt = studentNo.substring(0, studentNo.length() - 7);
		String lastStr = studentNo.substring(studentNo.length() - 4, studentNo.length());
		s.setSorting(startSrt + lastStr);

		// 保存学员与班级关联数据
		if (student.getClazzName() != null && !student.getClazzName().trim().equals("")) {
			Tclass clazz = clazzDao.get(Tclass.class, student.getClazzName().trim());
			s.setClazz(clazz);
		}
		// 保存学员与组织机构关联数据
		if (student.getOrganizationName() != null && !student.getOrganizationName().trim().equals("")) {
			organization = organizationDao.get(Torganization.class, student.getOrganizationName().trim());
			s.setOrganization(organization);
		}

		// 添加学员的同时添加计时卡表
		TstudentTimerCard ttc = new TstudentTimerCard();
		ttc.setId(UUID.randomUUID().toString());
		ttcDao.save(ttc);
		s.setStudentTimerCard(ttc);

		// 给当前学员选配教练员
		if (student.getTrainerName() != null && !student.getTrainerName().trim().equals("")) {
			trainer = trainerDao.get(Ttrainer.class, student.getTrainerName().trim());
			if (trainer != null) {
				s.setTrainer(trainer);
			}
		}

		// 给当前学员选配业务员
		if (student.getPersonName() != null && !student.getPersonName().trim().equals("")) {
			Tperson person = personDao.get(Tperson.class, student.getPersonName().trim());
			s.setPerson(person);
		}

		if (student.getCreateTime() == null) {
			Date date = new Date();
			date.setHours(0);
			date.setMinutes(0);
			date.setSeconds(0);
			s.setCreateTime(date);
		}

		// 学员费用三个字段设置默认值
		if (student.getFeed() == null) {
			s.setFeed(0.00);
		}
		if (student.getRealFeed() == null) {
			s.setRealFeed(0.00);
		}
		if (student.getOwnFeed() == null) {
			s.setOwnFeed(0.00);
		}
		if (student.getApplyType() == null) {
			s.setApplyType(1);
		}
		// 非计时学员时，设置预约学时永远有交
		if (student.getTimingFlag() != null && student.getTimingFlag() == Tstudent.TIMING_UNCOUNTING) {
			s.setRestTiming(1);
		} else {
			if (student.getLearningTime() != null && student.getLearningTime() > 0) {
				s.setRestTiming(1);
			}
		}

		studentDao.save(s);

		// 添加学员档案信息（同步创建）
		TstudentFile stuFile = new TstudentFile();
		stuFile.setId(UUID.randomUUID().toString());
		if (ValidateUtil.isValid(student.getOrganizationName())) {
			stuFile.setSchoolArea(student.getOrganizationName());
		}
		stuFile.setStudent(s);
		studentFileDao.save(stuFile);

		// 生成学员学时管理
		if (s.getTimingFlag() != null) {
			// 添加学员学时信息（同步创建）
			Ttiming timing = new Ttiming();
			timing.setId(UUID.randomUUID().toString());
			timing.setNumbering(GeneraterIdByDateUtil.getGeneraterIdByDate());
			if (ValidateUtil.isValid(student.getOrganizationName())) {
				timing.setSchoolArea(student.getOrganizationName());
			}
			if (student.getLearningTime() != null) {
				timing.setHandselTiming(student.getLearningTime());// 报名所含学时
				timing.setTotalTiming(student.getLearningTime());// 创建学时信息时学员默认总学时
				timing.setRestTiming(student.getLearningTime());// 创建学时信息时学员剩余学时值
				timing.setReservationTiming(student.getLearningTime());// 创建学员信息时记录学员的可预约学时
			}
			if (student.getTimingFlag() == Tstudent.TIMING_UNCOUNTING) {
				timing.setHandselTiming(0);// 报名所含学时
				timing.setTotalTiming(0);// 创建学时信息时学员默认总学时
				timing.setRestTiming(0);// 创建学时信息时学员剩余学时值
				timing.setReservationTiming(0);// 创建学员信息时记录学员的可预约学时
			}

			timing.setUsingTiming(0);// 创建学时信息时学员默认使用学时为0
			timing.setBuyTiming(0);// 创建学时信息时学员默认购买学时为0
			timing.setSimulationTiming(0);
			timing.setTramTiming(0);
			timing.setCarTiming(0);
			timing.setStudent(s);
			// 学员编号（冗余字段）
			timingDaoI.save(timing);
		}

		// 添加学员进度管理信息
		Tprogress progress = new Tprogress();
		progress.setId(UUID.randomUUID().toString());
		progress.setState(Tprogress.PROGRESS_SINUP);
		progress.setSinupdateDate(new Date());
		if (ValidateUtil.isValid(student.getOrganizationName())) {
			progress.setSchoolArea(student.getOrganizationName());
		}
		progress.setStudent(s);
		progressDao.save(progress);

		// 3.将修改后的数据传给前台
		BeanUtils.copyProperties(s, student);
		return student;
	}

	@Override
	public DataGrid datagrid(Student student) {
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(this.changeModel(this.find(student)));
		dataGrid.setTotal(this.total(student));
		return dataGrid;
	}

	public DataGrid studentDatagrid(Student student) {
		DataGrid dataGrid = new DataGrid();
		if (ValidateUtil.isValid(student.getIdentityId())) {
			String hql = "from Tstudent t where t.identityId=?";
			Tstudent stu = (Tstudent) studentDao.uniqueResult(hql, student.getIdentityId());
			if (stu != null) {

				Student studentTemp = new Student();
				BeanUtils.copyProperties(stu, studentTemp);
				List<Student> retStudents = new ArrayList<Student>();
				studentTemp.setOrganizationName(stu.getOrganization().getName());
				if (stu.getClazz() != null) {
					studentTemp.setClazzName(stu.getClazz().getName());
				}
				if (stu.getTrainer() != null) {
					studentTemp.setTrainerName(stu.getTrainer().getName());
					studentTemp.setTrainerPhone(stu.getTrainer().getPhone());
				}
				retStudents.add(studentTemp);
				dataGrid.setRows(retStudents);
				dataGrid.setTotal(1L);
				return dataGrid;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void delete(String ids) {
		// 1.判断参数是否为空
		if (ids != null) {
			// 2.对参数的字符串进行分割
			for (String id : ids.split(",")) {
				// 排除id为0的情况
				if (!id.trim().equals("0")) {
					// 3.根据ID从数据库中查找数据

					Tstudent student = studentDao.get(Tstudent.class, id);
					// 3.1将教练预约中有关该学没的预约信息清除
					Set<Treservation> reservations = student.getReservations();
					if (ValidateUtil.isValid(reservations)) {
						for (Treservation reservation : reservations) {
							TtrainerReservationDetail detail = trainerReservationDetailDao
									.get(TtrainerReservationDetail.class, reservation.getTrainerReservationDetailId());
							switch (reservation.getFieldOptionFlag()) {
							case 8: {
								detail.setItem8Studentid(null);
								detail.setItem8TrainContent(detail.getItem8TrainContent());
								deleteCarusingByReservationId(detail.getReservationFieldId8());// 删除车辆使用情况
								detail.setReservationFieldId8(null);
							}
								break;
							case 9: {

								detail.setItem9Studentid(null);
								detail.setItem9TrainContent(detail.getItem9TrainContent());
								deleteCarusingByReservationId(detail.getReservationFieldId9());// 删除车辆使用情况
								detail.setReservationFieldId9(null);
							}
								break;
							case 10: {
								detail.setItem10Studentid(null);
								detail.setItem10TrainContent(detail.getItem10TrainContent());
								deleteCarusingByReservationId(detail.getReservationFieldId10());// 删除车辆使用情况
								detail.setReservationFieldId10(null);
							}
								break;
							case 11: {
								detail.setItem11Studentid(null);
								detail.setItem11TrainContent(detail.getItem11TrainContent());
								deleteCarusingByReservationId(detail.getReservationFieldId11());// 删除车辆使用情况
								detail.setReservationFieldId11(null);
							}
								break;
							case 12: {
								detail.setItem12Studentid(null);
								detail.setItem12TrainContent(detail.getItem12TrainContent());
								deleteCarusingByReservationId(detail.getReservationFieldId12());// 删除车辆使用情况
								detail.setReservationFieldId12(null);
							}
								break;
							case 13: {
								detail.setItem13Studentid(null);
								detail.setItem13TrainContent(detail.getItem13TrainContent());
								deleteCarusingByReservationId(detail.getReservationFieldId13());// 删除车辆使用情况
								detail.setReservationFieldId13(null);
							}
								break;
							case 14: {
								detail.setItem14Studentid(null);
								detail.setItem14TrainContent(detail.getItem14TrainContent());
								deleteCarusingByReservationId(detail.getReservationFieldId14());// 删除车辆使用情况
								detail.setReservationFieldId14(null);
							}
								break;
							case 15: {
								detail.setItem15Studentid(null);
								detail.setItem15TrainContent(detail.getItem15TrainContent());
								deleteCarusingByReservationId(detail.getReservationFieldId15());// 删除车辆使用情况
								detail.setReservationFieldId15(null);
							}
								break;
							case 16: {
								detail.setItem16Studentid(null);
								detail.setItem16TrainContent(detail.getItem16TrainContent());
								deleteCarusingByReservationId(detail.getReservationFieldId16());// 删除车辆使用情况
								detail.setReservationFieldId16(null);
							}
								break;
							case 17: {
								detail.setItem17Studentid(null);
								detail.setItem17TrainContent(detail.getItem17TrainContent());
								deleteCarusingByReservationId(detail.getReservationFieldId17());// 删除车辆使用情况
								detail.setReservationFieldId17(null);
							}
								break;
							case 18: {
								detail.setItem18Studentid(null);
								detail.setItem18TrainContent(detail.getItem18TrainContent());
								deleteCarusingByReservationId(detail.getReservationFieldId18());// 删除车辆使用情况
								detail.setReservationFieldId18(null);
							}
							case 19: {
								detail.setItem19Studentid(null);
								detail.setItem19TrainContent(detail.getItem19TrainContent());
								deleteCarusingByReservationId(detail.getReservationFieldId19());// 删除车辆使用情况
								detail.setReservationFieldId19(null);
							}
							case 20: {
								detail.setItem20Studentid(null);
								detail.setItem20TrainContent(detail.getItem20TrainContent());
								deleteCarusingByReservationId(detail.getReservationFieldId20());// 删除车辆使用情况
								detail.setReservationFieldId20(null);
							}
							case 21: {
								detail.setItem21Studentid(null);
								detail.setItem21TrainContent(detail.getItem21TrainContent());
								deleteCarusingByReservationId(detail.getReservationFieldId21());// 删除车辆使用情况
								detail.setReservationFieldId21(null);
							}
								break;
							}
							trainerReservationDetailDao.saveOrUpdate(detail);
						}
					}
					// 3.2//删除退学信息
					if (student.getQuitSchool() != null) {
						TquitSchool quiSchool = student.getQuitSchool();
						quitschoolDao.delete(quiSchool);
					}
					// 4.调用DAO层删除数据
					studentDao.delete(student);
				}
			}

		}

	}

	@Override
	public Student update(Student student) {
		// 1.取出修改之前学员的信息
		Tstudent s = studentDao.get(Tstudent.class, student.getId());
		// 模型数据转换：将action中的model数据拷贝给dao中的model
		BeanUtils.copyProperties(student, s, new String[] { "id" });
		s.setId(student.getId());
		String temp = s.getStudentNo().substring(s.getStudentNo().length() - 3, s.getStudentNo().length());

		// 获取修改之前的教练ID
		String trainerId = null;
		if (s.getTrainer() != null) {
			trainerId = s.getTrainer().getId();
		}

		// 保存学员与班级关联数据
		if (student.getClazzName() != null && !student.getClazzName().trim().equals("")) {
			Tclass clazz = clazzDao.get(Tclass.class, student.getClazzName().trim());
			s.setClazz(clazz);
		}
		// 保存学员与组织机构关联数据
		if (student.getOrganizationName() != null && !student.getOrganizationName().trim().equals("")) {
			Torganization organization = organizationDao.get(Torganization.class, student.getOrganizationName().trim());
			s.setOrganization(organization);
		}

		// 保存学员与教练关联数据
		Ttrainer trainer = null;
		if (ValidateUtil.isValid(student.getTrainerName())) {
			trainer = trainerDao.get(Ttrainer.class, student.getTrainerName().trim());
			// 1.原来选配教练
			if (s.getTrainer() != null) {
				// 1.1判斷是否存在沒有完成的预约
				if (this.hasReservationUnfinished(s.getId(), s.getTrainer().getId()) > 0) {// 存在预约不允许更换教练
					return null;
				}

			}
			s.setTrainer(trainer);
			// 修改学员学时管理中的教练信息
			if (null != s.getTiming()) {
				if (Tstudent.TIMING_UNCOUNTING != s.getTimingFlag()) {
					// 1.修改学时信息
					Ttiming timing = timingDaoI.get(Ttiming.class, s.getTiming().getId());
					timingDaoI.update(timing);
					// 2.修改学员学时明细信息
					Set<TpersonalTiming> lists = timing.getPersonalTimings();
					if (lists != null && lists.size() > 0) {
						for (TpersonalTiming t : lists) {
							t.setTrainer(trainer);
							personalTimingDao.update(t);
						}
					}
				}
			} else {
				if (1 == student.getTimingFlag() && student.getOrganizationName().equals(Torganization.BS_SCHOOLAREA)) {
					Ttiming timing = new Ttiming();
					timing.setId(UUID.randomUUID().toString());
					timing.setNumbering(GeneraterIdByDateUtil.getGeneraterIdByDate());
					timing.setHandselTiming(student.getLearningTime());// 报名所含学时
					timing.setTotalTiming(student.getLearningTime());// 创建学时信息时学员默认总学时
					timing.setRestTiming(student.getLearningTime());// 创建学时信息时学员剩余学时值
					timing.setReservationTiming(student.getLearningTime());// 创建学员信息时记录学员的可预约学时

					timing.setUsingTiming(0);// 创建学时信息时学员默认使用学时为0
					timing.setBuyTiming(0);// 创建学时信息时学员默认购买学时为0
					timing.setSimulationTiming(0);
					timing.setTramTiming(0);
					timing.setCarTiming(0);
					timing.setStudent(s);
					// 学员编号（冗余字段）
					timingDaoI.save(timing);
				}
			}

		} else {// 选择教练为输入为空（或者取消）
			// 1.原来选配教练
			if (s.getTrainer() != null) {
				// 1.1判斷是否存在沒有完成的预约
				if (this.hasReservationUnfinished(s.getId(), s.getTrainer().getId()) > 0) {// 存在预约不允许更换教练
					return null;
				}

			}
			s.setTrainer(null);
		}

		// 保存学员与业务关联数据
		if (student.getPersonName() != null && !student.getPersonName().trim().equals("")) {
			Tperson person = personDao.get(Tperson.class, student.getPersonName().trim());
			s.setPerson(person);
		}
		// 由非计时学员转为计时学员时，确保存在计时管理的关联

		studentDao.update(s);
		// 3.将修改后的数据传给前台
		BeanUtils.copyProperties(s, student);
		return student;
	}

	@Override
	public List<Student> combobox(Student student) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 从数据库存中查找出该实体信息并将此实体类信息转换成action专用的实体类信息，即模型转换
	 * 
	 * @param students
	 * @return
	 */
	private List<Student> changeModel(List<Tstudent> students) {

		List<Student> studentList = new ArrayList<Student>();
		if (students != null && students.size() > 0) {
			for (Tstudent studentTemp : students) {
				Student student = new Student();
				BeanUtils.copyProperties(studentTemp, student);
				// 在视图中展现班级标识和名称
				if (studentTemp.getClazz() != null) {
					student.setClazzId(studentTemp.getClazz().getId());
					student.setClazzName(studentTemp.getClazz().getName());
				}
				// 在视图中展现教练标识和名称
				if (studentTemp.getTrainer() != null) {
					student.setTrainerId(studentTemp.getTrainer().getId());
					student.setTrainerName(studentTemp.getTrainer().getName());
				}
				// 在视图中展现业务员标识和名称
				if (studentTemp.getPerson() != null) {
					student.setPersonId(studentTemp.getPerson().getId());
					student.setPersonName(studentTemp.getPerson().getName());
				}
				if (studentTemp.getOrganization() != null) {
					student.setOrganizationName(studentTemp.getOrganization().getName());
					student.setOrganizationId(studentTemp.getOrganization().getId());
				}
				if (studentTemp.getStudentTimerCard() != null) {
					student.setTimerCardId(studentTemp.getStudentTimerCard().getId());
				}
				if (ValidateUtil.isValid(studentTemp.getNetPiont())) {
					// 报名网点名称
					student.setNetPiontName(getSingleNetPion(studentTemp.getNetPiont()).getName());
				}
				studentList.add(student);
			}
		}
		return studentList;
	}

	/**
	 * 根据action专用实体类的信息查询所有dao专用的实体类信息
	 * 
	 * @param student
	 * @return
	 */
	private List<Tstudent> find(Student student) {
		String hql = "from Tstudent t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(student, hql, values);

		if (student.getSort() != null && student.getOrder() != null) {
			hql += " order by " + student.getSort() + " " + student.getOrder();
		}
		return studentDao.find(hql, values, student.getPage(), student.getRows());
	}

	/**
	 * 统计符合当前查询条件的总记录数
	 * 
	 * @param student
	 * @return
	 */
	private Long total(Student student) {
		String hql = "select count(*) from Tstudent t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(student, hql, values);
		return studentDao.count(hql, values);
	}

	/**
	 * 添加查询约束条件where
	 * 
	 * @param student
	 * @param hql
	 * @param values
	 * @return
	 */
	private String addWhere(Student student, String hql, List<Object> values) {
		// 改名模糊查询
		if (ValidateUtil.isValid(student.getName())) {
			hql += " and t.name like ? ";
			values.add("%%" + student.getName().trim() + "%%");
		}
		// 注册时间（范围查询）
		if (student.getCreateTimeStart() != null) {
			hql += " and t.createTime>=? ";
			values.add(student.getCreateTimeStart());
		}
		if (student.getCreateTimeEnd() != null) {
			hql += " and t.createTime<=? ";
			values.add(student.getCreateTimeEnd());
		}
		// 身份证（精确查询）
		if (ValidateUtil.isValid(student.getIdentityId())) {
			hql += " and t.identityId=? ";
			values.add(student.getIdentityId().trim());
		}
		// 状态：精确查询
		if (student.getNowState() != null && student.getNowState() > 0) {
			hql += " and t.nowState=? ";
			values.add(student.getNowState());
		}
		// 所属校区：精确查询
		if (ValidateUtil.isValid(student.getOrganizationName())) {
			hql += " and t.organization.id=?";
			values.add(student.getOrganizationName().trim());
		}
		// 考试类型精确查询(类型：C1，A1...)
		if (student.getDriverType() != null) {
			hql += " and t.driverType=?";
			values.add(student.getDriverType());
		}
		// 学员编号查询
		if (ValidateUtil.isValid(student.getStudentNo())) {
			hql += " and t.studentNo like ?";
			values.add("%" + student.getStudentNo().trim());
		}
		if (student.getExamSelected() != null) {
			hql += " and t.examSelected=?";
			values.add(student.getExamSelected());
		}
		if (student.getTrainerIdentity() != null) {
			hql += " and t.trainer.identity=?";
			values.add(student.getTrainerIdentity().trim());
		}

		// 剩余学时过虑
		if (student.getRestTiming() != null) {
			hql += " and t.restTiming=?";
			values.add(student.getRestTiming());
		}

		if (ValidateUtil.isValid(student.getTrainerName())) {
			hql += " and t.trainer.name=?";
			values.add(student.getTrainerName().trim());
		}
		if (ValidateUtil.isValid(student.getPersonName())) {
			hql += " and t.person.name=?";
			values.add(student.getPersonName().trim());
		}
		if (student.getStudentType() != null) {
			hql += " and t.studentType=?";
			values.add(student.getStudentType());
		}
		if (ValidateUtil.isValid(student.getPhone())) {
			hql += " and t.phone like ? ";
			values.add("%%" + student.getPhone().trim() + "%%");
		}
		if (student.getBatch() != null) {// 在当前添加考试批次中，只能选择batch为空的学员
			if (student.getBatch() == 0) {
				hql += " and t.batch is null ";
			} else {
				hql += " and t.batch >= ?";
				values.add(student.getBatch());
			}
		}
		// 驾驶证，资格证状态查询
		if (ValidateUtil.isValid(student.getDriverLicenseFlag())) {
			hql += " and t.driverLicenseFlag=?";
			values.add(student.getDriverLicenseFlag());
		}
		if (ValidateUtil.isValid(student.getqLicenseFlag())) {
			hql += " and t.qLicenseFlag=?";
			values.add(student.getqLicenseFlag());
		}
		return hql;
	}

	@Override
	public Tstudent getSingleById(String id) {
		if (!ValidateUtil.isValid(id)) {
			return null;
		}
		String sql = "select * from tb_student where id=?";
		List<Tstudent> students = studentDao.findAllBySQL(Tstudent.class, sql, id);
		if (students != null && students.size() > 0) {
			return students.get(0);
		}
		return null;
	}

	@Override
	public List<Student> getAllStudentByNowState(String nowstate) {
		List<Student> retStudents = new ArrayList<Student>();
		// 1.根据学员状态从数据库中查找出学员信息
		String hql = "form Tstudent t where t.nowState=?";
		List<Object> params = new ArrayList<Object>();
		params.add(nowstate);
		List<Tstudent> studentLists = studentDao.find(hql, params);

		// 2.遍历转换数据类型
		if (studentLists != null && studentLists.size() > 0) {
			for (Tstudent t : studentLists) {
				Student student = new Student();
				BeanUtils.copyProperties(t, student);
				retStudents.add(student);
			}
		}
		return retStudents;
	}

	@Override
	public Tstudent getStudentByNameAndKey(String name, String keyStr) {
		// 1.根据学员状态从数据库中查找出学员信息
		String hql = "from Tstudent t where t.name = ? and t.identityId = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(name);
		params.add(keyStr);
		return studentDao.get(hql, params);
	}

	// 统计所有学员人数
	public Long getAllStudentCount() {
		Student student = new Student();
		return this.total(student);
	}

	@Override
	public Map<String, Long> getStudentBySchoolArea(String schoolIds) {
		Map<String, Long> retMap = new HashMap<String, Long>(0);
		if (schoolIds != null) {
			for (String s : schoolIds.split(",")) {
				Student student = new Student();
				student.setOrganizationName(s);
				Long total = this.total(student);
				retMap.put(s, total);
			}
		}

		return retMap;
	}

	// 从数据库存中获取最大的学员编号
	public String getTheBiggerStudentNo(User user, Integer studentNoTypeVal) {
		String sql = "select *,(cast(substring(sorting,-4) as unsigned)) as a from tb_student t where t.organizationId=?  order by a desc";
		List<Tstudent> stusList = studentDao.findAllBySQL(Tstudent.class, sql, user.getSchoolArea());
		// 返回学员编号前缀（前三个字母）如：BSF
		String retStart = StringUtil.getFirstLetterBySchoolArea(user.getSchoolArea())
				+ this.getFistLetterByDriverType(studentNoTypeVal);
		// 2.1如果有记录存在，则加1后返回
		if (stusList != null && stusList.size() > 0) {
			String str = stusList.get(0).getStudentNo();
			String startStr = str.substring(0, str.length() - 7);
			String startEnd = str.substring(str.length() - 4, str.length());
			String value = Util.getPatternValue(startStr + startEnd, "\\D+(\\d+)", 34, 1);
			Integer temp = Util.objToInt(value, 0);
			return retStart + "000" + toAddByString(temp + 1);
		}
		// 2.2系统不存在当前相关记录，
		return retStart + "0000001";
	}

	private String toAddByString(Integer value) {
		// 得到一个NumberFormat的实例
		NumberFormat nf = NumberFormat.getInstance();
		// 设置是否使用分组
		nf.setGroupingUsed(false);
		// 设置最大整数位数
		nf.setMaximumIntegerDigits(4);// 编号暂时定为四位
		// 设置最小整数位数
		nf.setMinimumIntegerDigits(4);
		// 输出测试语句
		return nf.format(value);
	}

	@Override
	public boolean studentNoExistOrNot(String studentNo) {
		// TODO

		String hql = "select count(*) from Tstudent t where t.studentNo=?";
		Long temp = (Long) studentDao.uniqueResult(hql, studentNo);
		String studentNoStart = studentNo.substring(0, studentNo.length() - 7);
		String studentNoEnd = studentNo.substring(studentNo.length() - 4, studentNo.length());
		String studentNoTemp = studentNoStart + "000" + studentNoEnd;
		Long tempVal = (Long) studentDao.uniqueResult(hql, studentNoTemp);
		if (temp != 0 || tempVal != 0) {
			return true;
		}
		return false;
	}

	public boolean identityIdExistOrNot(String identityId) {
		String hql = "select count(*) from Tstudent t where t.identityId=?";
		Long temp = (Long) studentDao.uniqueResult(hql, identityId);
		return temp != 0;
	}

	@Override
	public Ttrainer getTrainerByStudentId(String studentId) throws Exception {
		Tstudent student = studentDao.get(Tstudent.class, studentId);
		Ttrainer trainer = student.getTrainer();
		return trainer;
	}

	public Tperson getPersonByStudentId(String studentId) throws Exception {
		Tstudent student = studentDao.get(Tstudent.class, studentId);
		Tperson person = student.getPerson();
		return person;
	}

	public List<Tperson> getAllPersons(String schoolArea) throws Exception {
		if (!ValidateUtil.isValid(schoolArea)) {
			return null;
		}
		String sql = "select * from tb_persons where organizationId=? and propertyType=0";
		List<Tperson> retVals = personDao.findAllBySQL(Tperson.class, sql, schoolArea);
		if (ValidateUtil.isValid(retVals)) {
			return retVals;
		}
		return null;
	}

	@Override
	public List<ComboboxJson> getStudentByItTrainerId(String trainerId) {
		List<ComboboxJson> retJsons = new ArrayList<ComboboxJson>(0);
		String hql = "from Tstudent t where t.trainer.id=?";
		List<Object> params = new ArrayList<Object>(0);
		params.add(trainerId);
		List<Tstudent> students = studentDao.find(hql, params);

		if (ValidateUtil.isValid(students)) {
			for (Tstudent student : students) {
				ComboboxJson json = new ComboboxJson();
				json.setValue(student.getStudentNo());
				json.setText(student.getName() + "-" + student.getStudentNo());
				retJsons.add(json);
			}
			if (retJsons.size() > 0) {
				ComboboxJson json = new ComboboxJson();
				json.setText("取消");
				json.setValue(null);
				retJsons.add(json);
			}
		}

		return retJsons;
	}

	/**
	 * 根据所属校区及驾照类型统计学员总人数
	 * 
	 * @return Map<校区标识, Map<驾驶类型, 总人数>>
	 */
	public Map<String, Map<Integer, Long>> getCountByDriverType(String schoolIds) {
		Map<String, Map<Integer, Long>> retMap = new HashMap<String, Map<Integer, Long>>(0);
		if (ValidateUtil.isValid(schoolIds)) {
			String[] schoolArea = schoolIds.split(",");
			for (String s : schoolArea) {
				Map<Integer, Long> countMap = new HashMap<Integer, Long>();
				Student stu1 = new Student();
				stu1.setOrganizationName(s);
				stu1.setDriverType(Ttrainer.DRIVERTYPE_A1);
				countMap.put(Ttrainer.DRIVERTYPE_A1, this.total(stu1));

				Student stu2 = new Student();
				stu2.setOrganizationName(s);
				stu2.setDriverType(Ttrainer.DRIVERTYPE_A2);
				countMap.put(Ttrainer.DRIVERTYPE_A2, this.total(stu2));

				Student stu3 = new Student();
				stu3.setOrganizationName(s);
				stu3.setDriverType(Ttrainer.DRIVERTYPE_A3);
				countMap.put(Ttrainer.DRIVERTYPE_A3, this.total(stu3));

				Student stu4 = new Student();
				stu4.setOrganizationName(s);
				stu4.setDriverType(Ttrainer.DRIVERTYPE_B1);
				countMap.put(Ttrainer.DRIVERTYPE_B1, this.total(stu4));

				Student stu5 = new Student();
				stu5.setOrganizationName(s);
				stu5.setDriverType(Ttrainer.DRIVERTYPE_B2);
				countMap.put(Ttrainer.DRIVERTYPE_B2, this.total(stu5));

				Student stu6 = new Student();
				stu6.setOrganizationName(s);
				stu6.setDriverType(Ttrainer.DRIVERTYPE_C1);
				countMap.put(Ttrainer.DRIVERTYPE_C1, this.total(stu6));

				Student stu7 = new Student();
				stu7.setOrganizationName(s);
				stu7.setDriverType(Ttrainer.DRIVERTYPE_C2);
				countMap.put(Ttrainer.DRIVERTYPE_C2, this.total(stu7));

				Student stu8 = new Student();
				stu8.setOrganizationName(s);
				stu8.setDriverType(Ttrainer.DRIVERTYPE_C3);
				countMap.put(Ttrainer.DRIVERTYPE_C3, this.total(stu8));

				Student stu9 = new Student();
				stu9.setOrganizationName(s);
				stu9.setDriverType(Ttrainer.DRIVERTYPE_C4);
				countMap.put(Ttrainer.DRIVERTYPE_C4, this.total(stu9));

				// 返回数制格式：map<schoolid <type,totals>>
				retMap.put(s, countMap);
			}
		}

		return retMap;
	}

	// 根据年，月，周，日统计学员报名统计报名情况
	public Map<String, Map<Integer, Long>> getStudentCountByYYWD(String schoolIds, Date date) {
		Map<String, Map<Integer, Long>> retMap = new HashMap<String, Map<Integer, Long>>();

		// 1.对日期进行处理
		// 1.1Date转化为Calendar
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);

		if (ValidateUtil.isValid(schoolIds)) {
			String[] schoolArea = schoolIds.split(",");
			for (String s : schoolArea) {
				Map<Integer, Long> countMap = new HashMap<Integer, Long>();
				// 今日报名统计
				Student stu1 = new Student();
				stu1.setOrganizationName(s);
				stu1.setCreateTimeStart(date);
				stu1.setCreateTimeEnd(date);
				countMap.put(1, this.total(stu1));

				// 本周报名统计
				Student stu2 = new Student();
				stu2.setOrganizationName(s);
				stu2.setCreateTimeStart(DateUtil.getMondayOfThisWeek());
				stu2.setCreateTimeEnd(DateUtil.getSundayOfThisWeek());
				countMap.put(2, this.total(stu2));

				// 本月报名统计
				Student stu3 = new Student();
				stu3.setOrganizationName(s);
				stu3.setCreateTimeStart(DateUtil.getMonthFirstDate(year, month));
				stu3.setCreateTimeEnd(DateUtil.getMontLastDate(year, month));
				countMap.put(3, this.total(stu3));

				// 本年度报名统计
				Student stu4 = new Student();
				stu4.setOrganizationName(s);
				stu4.setCreateTimeStart(DateUtil.getYearFirstDate(year));
				stu4.setCreateTimeEnd(DateUtil.getYearLastDates(year));
				countMap.put(4, this.total(stu4));

				// 返回数制格式：map<schoolid <key,totals>>
				retMap.put(s, countMap);
			}
		}

		return retMap;
	}

	@Override
	public Tstudent getSingleByUser(User user) throws Exception {
		String hql = "from Tstudent t where t.identityId=?";
		List<Object> param = new ArrayList<Object>();
		param.add(user.getCname());
		List<Tstudent> students = studentDao.find(hql, param);
		if (students != null && students.size() > 0) {
			return students.get(0);
		}
		return null;
	}

	@Override
	public Student getStudentIdentityId(String identityId) throws Exception {
		String hql = "from Tstudent t where t.identityId=?";
		Tstudent t = (Tstudent) studentDao.uniqueResult(hql, identityId);
		Student s = new Student();
		Ttrainer trainer = t.getTrainer();
		BeanUtils.copyProperties(t, s);
		s.setTrainerName(trainer.getName());
		s.setTrainerId(trainer.getId());
		s.setTrainerPhone(trainer.getPhone());
		s.setTrainerIdentity(trainer.getIdentity());
		return s;
	}

	public ImportReturnModel importDatasFromExcel(Workbook wb, String schoolArea, String operatorName)
			throws ParseException {
		ImportReturnModel model = new ImportReturnModel();
		String studentNo = "";
		String identity = "";
		List<Student> reVals = new ArrayList<Student>();
		boolean repeatFlag = false;
		Sheet sheet = wb.getSheetAt(0);
		int rowNum = sheet.getLastRowNum() + 1;
		for (int i = 1; i < rowNum; i++) {// 遍历表中的行，从第二行开始，除去表头
			Student student = new Student();
			Row row = sheet.getRow(i);
			int cellNum = row.getLastCellNum();
			for (int j = 0; j < cellNum; j++) {// 遍历excle表中的列
				Cell cell = row.getCell(j);
				String cellValue = StringUtil.getCellValueByCellType(cell);

				switch (j) {// 通过列数来判断对应插如的字段
				case 0:// 学员编
				{
					if (studentNoExistOrNot(cellValue)) {
						studentNo = studentNo + ", " + cellValue;
						repeatFlag = true;
					}
					student.setStudentNo(cellValue);
				}
					break;
				case 1:// 姓名
					student.setName(cellValue);
					break;
				case 2: // 性别
					student.setSex(cellValue);
					break;
				case 3:// 身份证
				{
					if (this.identityIdExistOrNot(cellValue)) {
						identity = identity + ", " + cellValue;
						repeatFlag = true;
					}
					student.setIdentityId(cellValue);
				}
					break;
				case 4:// 地址
					student.setAddress(cellValue);
					break;
				case 5:// 驾照类型
					student.setDriverType(StringUtil.getCarTypeValue(cellValue));
					break;
				case 6:// 手机
					student.setPhone(cellValue);
					break;
				case 7:// 学员计时类型（计时，非计时）
					student.setTimingFlag(getTimingFlagByString(cellValue));
					break;
				case 8:// 学员类型（本校，承包）
					student.setStudentType(getStudentTypeByString(cellValue));
					break;
				case 9:// 状态
					student.setNowState(this.getStudentStateTpyeByString(cellValue));
					break;
				case 10:// 申领类型
					student.setApplyType(getApplyTypeeByString(cellValue));

					break;
				case 11:// 出生日期
					student.setBirthdate(DateUtil.getDateByString(cellValue));
					break;
				case 12:// 报名日期
					student.setCreateTime(DateUtil.getDateByString(cellValue));
					break;
				case 13:// 毕业日期
					student.setGraduateDate(DateUtil.getDateByString(cellValue));
					break;
				case 14:// 应交学费
				{
					if (cellValue != null) {
						student.setFeed(Double.valueOf(cellValue));
					}
				}
					break;
				case 15:// 实交学费
				{
					if (cellValue != null) {
						student.setRealFeed(Double.valueOf(cellValue));
					}
				}
					break;
				case 16:// 欠学费
				{
					if (cellValue != null) {
						student.setOwnFeed(Double.valueOf(cellValue));
					}
				}
					break;
				case 17:// 教练关联
					student.setTrainerName(getTrainerIdByIdentity(cellValue));
					break;
				}// end switch

			}
			reVals.add(student);

		}
		// 导入数据
		if (reVals.size() > 0 && repeatFlag == false) {
			for (Student entry : reVals) {
				entry.setLearningTime(1);// 大于0表示有预约学时
				entry.setOrganizationName(schoolArea);
				entry.setOrganizationId(schoolArea);
				entry.setOperator(operatorName);
				this.save(entry);
			}
		}

		model.setMsg(studentNo + identity);
		// 统计导入信息总记录数
		model.setSize(reVals.size());

		// 导入成功失败结果参数
		if (repeatFlag) {
			// 如果存在重复字段不允许导入
			model.setSuccess(false);
		} else {
			model.setSuccess(true);
		}
		return model;
	}

	// 学同报名统计(wechat use)
	public Map<String, Integer> getStudentCountForWechat() throws ParseException {
		// 1.根安排序获取校区标识
		List<ComboboxJson> orgs = this.getOrganizationIds();

		// 2.设置时间参数，根据当天获取日期，日，周，月，年
		Map<String, Date> dateMaps = DateUtil.getFisrtLastDateByDate(new Date());

		Date today = new Date();// 当
		// 当前日期所在的周
		Date firstDateOfWeek = dateMaps.get("firstDateOfWeek");
		Date lastDateOfWeek = dateMaps.get("lastDateOfWeek");
		// 月份日期
		Date firstDateOfMonth = dateMaps.get("firstDateOfMonth");
		Date lastDateOfMonth = dateMaps.get("lastDateOfMonth");
		// 年份日期
		Date firstDateOfYear = dateMaps.get("firstDateOfYear");
		Date lastDateOfYear = dateMaps.get("lastDateOfYear");

		Map<String, Integer> totalMap = new HashMap<String, Integer>(0);
		// 3.根据校区统计学员人数
		for (ComboboxJson comboboxJson : orgs) {
			// 3.1根据校区按日统计
			String dateSql = "select count(*) from tb_student where organizationId=? and createTime=?";
			Long dateTotal = studentDao.countBySQL(dateSql, comboboxJson.getValue(), today);
			// 3.2根据校区按周统计
			String weekSql = "select count(*) from tb_student where organizationId=? and createTime>=? and createTime<=?";
			Long weekTotal = studentDao.countBySQL(weekSql, comboboxJson.getValue(), firstDateOfWeek, lastDateOfWeek);
			// 3.3根据校区按月统计
			String monthSql = "select count(*) from tb_student where organizationId=? and createTime>=? and createTime<=?";
			Long monthTotal = studentDao.countBySQL(monthSql, comboboxJson.getValue(), firstDateOfMonth,
					lastDateOfMonth);
			// 3.4根据校区按年统计
			String yearSql = "select count(*) from tb_student where organizationId=? and createTime>=? and createTime<=?";
			Long yearTotal = studentDao.countBySQL(yearSql, comboboxJson.getValue(), firstDateOfYear, lastDateOfYear);
			totalMap.put("dateCount", dateTotal.intValue());
			totalMap.put("weekCount", weekTotal.intValue());
			totalMap.put("monthCount", monthTotal.intValue());
			totalMap.put("yearCount", yearTotal.intValue());

		}
		if (totalMap != null && totalMap.size() > 0) {
			return totalMap;
		}
		return null;
	}

	// 获取一周的日期
	public List<String> getWeekDays(Integer weekFlag) throws ParseException {
		// 2.设置时间参数，根据当天获取日期，日，周，月，年
		Map<String, Date> dateMaps = DateUtil.getFisrtLastDateByDate(new Date());

		List<String> retVal = new ArrayList<String>();
		// 当前日期所在的周
		Date firstDateOfWeek = null;
		Date secondDateOfWeek = null;
		Date thirdDateOfWeek = null;
		Date fourthDateOfWeek = null;
		Date fifthDateOfWeek = null;
		Date sixthDateOfWeek = null;
		Date lastDateOfWeek = null;
		if (weekFlag == 3) {// 上周
			firstDateOfWeek = dateMaps.get("firstDateOfLastWeek");
			secondDateOfWeek = dateMaps.get("secondDateOfLastWeek");
			thirdDateOfWeek = dateMaps.get("thirdDateOfLastWeek");
			fourthDateOfWeek = dateMaps.get("fourthDateOfLastWeek");
			fifthDateOfWeek = dateMaps.get("fifthDateOfLastWeek");
			sixthDateOfWeek = dateMaps.get("sixthDateOfLastWeek");
			lastDateOfWeek = dateMaps.get("lastDateOfLastWeek");
		} else if (weekFlag == 2) {// 本同
			firstDateOfWeek = dateMaps.get("firstDateOfWeek");
			secondDateOfWeek = dateMaps.get("secondDateOfWeek");
			thirdDateOfWeek = dateMaps.get("thirdDateOfWeek");
			fourthDateOfWeek = dateMaps.get("fourthDateOfWeek");
			fifthDateOfWeek = dateMaps.get("fifthDateOfWeek");
			sixthDateOfWeek = dateMaps.get("sixthDateOfWeek");
			lastDateOfWeek = dateMaps.get("lastDateOfWeek");
		} else if (weekFlag == 1) {// 下周
			firstDateOfWeek = dateMaps.get("firstDateOfNextWeek");
			secondDateOfWeek = dateMaps.get("secondDateOfNextWeek");
			thirdDateOfWeek = dateMaps.get("thirdDateOfNextWeek");
			fourthDateOfWeek = dateMaps.get("fourthDateOfNextWeek");
			fifthDateOfWeek = dateMaps.get("fifthDateOfNextWeek");
			sixthDateOfWeek = dateMaps.get("sixthDateOfNextWeek");
			lastDateOfWeek = dateMaps.get("lastDateOfNextWeek");
		}
		retVal.add(DateUtil.dateToString(firstDateOfWeek));
		retVal.add(DateUtil.dateToString(secondDateOfWeek));
		retVal.add(DateUtil.dateToString(thirdDateOfWeek));
		retVal.add(DateUtil.dateToString(fourthDateOfWeek));
		retVal.add(DateUtil.dateToString(fifthDateOfWeek));
		retVal.add(DateUtil.dateToString(sixthDateOfWeek));
		retVal.add(DateUtil.dateToString(lastDateOfWeek));

		return retVal;
	}

	// 按周详细统计本周内每一天的学员报名数量
	public Map<String, Map<String, Integer>> getWeekCountByOrgIds(Integer weekFlag) throws ParseException {

		// 1.根安排序获取校区标识
		List<ComboboxJson> orgs = this.getOrganizationIds();

		// 2.设置时间参数，根据当天获取日期，日，周，月，年
		Map<String, Date> dateMaps = DateUtil.getFisrtLastDateByDate(new Date());

		// 当前日期所在的周
		Date firstDateOfWeek = null;
		Date secondDateOfWeek = null;
		Date thirdDateOfWeek = null;
		Date fourthDateOfWeek = null;
		Date fifthDateOfWeek = null;
		Date sixthDateOfWeek = null;
		Date lastDateOfWeek = null;
		if (weekFlag == 3) {// 上周
			firstDateOfWeek = dateMaps.get("firstDateOfLastWeek");
			secondDateOfWeek = dateMaps.get("secondDateOfLastWeek");
			thirdDateOfWeek = dateMaps.get("thirdDateOfLastWeek");
			fourthDateOfWeek = dateMaps.get("fourthDateOfLastWeek");
			fifthDateOfWeek = dateMaps.get("fifthDateOfLastWeek");
			sixthDateOfWeek = dateMaps.get("sixthDateOfLastWeek");
			lastDateOfWeek = dateMaps.get("lastDateOfLastWeek");
		} else if (weekFlag == 2) {// 本同
			firstDateOfWeek = dateMaps.get("firstDateOfWeek");
			secondDateOfWeek = dateMaps.get("secondDateOfWeek");
			thirdDateOfWeek = dateMaps.get("thirdDateOfWeek");
			fourthDateOfWeek = dateMaps.get("fourthDateOfWeek");
			fifthDateOfWeek = dateMaps.get("fifthDateOfWeek");
			sixthDateOfWeek = dateMaps.get("sixthDateOfWeek");
			lastDateOfWeek = dateMaps.get("lastDateOfWeek");
		}

		Map<String, Map<String, Integer>> retMap = new HashMap<String, Map<String, Integer>>(0);
		Map<String, Integer> totalMap = null;
		// 3.根据校区统计学员人数
		for (ComboboxJson comboboxJson : orgs) {
			// 3.1根据校区按日统计
			String dateSql = "select count(*) from tb_student where organizationId=? and createTime=?";
			Long mondayTotal = studentDao.countBySQL(dateSql, comboboxJson.getValue(), firstDateOfWeek);
			Long tuesdayTotal = studentDao.countBySQL(dateSql, comboboxJson.getValue(), secondDateOfWeek);
			Long wednesdayTotal = studentDao.countBySQL(dateSql, comboboxJson.getValue(), thirdDateOfWeek);
			Long thursdayTotal = studentDao.countBySQL(dateSql, comboboxJson.getValue(), fourthDateOfWeek);
			Long fridayTotal = studentDao.countBySQL(dateSql, comboboxJson.getValue(), fifthDateOfWeek);
			Long saturdayTotal = studentDao.countBySQL(dateSql, comboboxJson.getValue(), sixthDateOfWeek);
			Long sundayTotal = studentDao.countBySQL(dateSql, comboboxJson.getValue(), lastDateOfWeek);

			totalMap = new HashMap<String, Integer>(0);
			totalMap.put("Monday", mondayTotal.intValue());
			totalMap.put("Tuesday", tuesdayTotal.intValue());
			totalMap.put("Wednesday", wednesdayTotal.intValue());
			totalMap.put("Thursday", thursdayTotal.intValue());
			totalMap.put("Friday", fridayTotal.intValue());
			totalMap.put("Saturday", saturdayTotal.intValue());
			totalMap.put("Sunday", sundayTotal.intValue());

			retMap.put(comboboxJson.getValue(), totalMap);
		}
		if (retMap != null && retMap.size() > 0) {
			return retMap;
		}
		return null;
	}

	// 按日，周，月，年统计报名总数
	public List<ComboboxJson> getStudentCoutByDateType(Integer type, String sql, Integer functionFlag,
			Integer firstDateFlay, Integer lastDateFlay) throws ParseException {
		List<ComboboxJson> retVal = new ArrayList<ComboboxJson>();
		ComboboxJson json = null;

		// 1.根安排序获取校区标识
		List<ComboboxJson> orgs = this.getOrganizationIds();

		// 2.设置时间参数，根据当天获取日期，日，周，月，年
		Map<String, Date> dateMaps = DateUtil.getFisrtLastDateByDate(new Date());
		Map<String, Date> retMonthMap = DateUtil.getLastDateOfMonth(new Date());
		Date firstDate = null;
		Date lastDate = null;
		if (type == 1) {// 按日统计报名
			Date date = new Date();
			date.setHours(0);
			date.setMinutes(0);
			date.setSeconds(0);
			firstDate = date;
			lastDate = date;
		} else {// 按周月年统计报名
			if (firstDateFlay == Tuser.FIRSTDATE_OF_WEEK) {// 本周
				firstDate = dateMaps.get("firstDateOfWeek");
				lastDate = dateMaps.get("lastDateOfWeek");
			} else if (firstDateFlay == Tuser.FIRSTDATE_OF_LASTWEEK) {// 上一周
				firstDate = dateMaps.get("firstDateOfLastWeek");
				lastDate = dateMaps.get("lastDateOfLastWeek");
			} else if (firstDateFlay == Tuser.FIRSTDATE_OF_MONTH) {// 本月
				firstDate = retMonthMap.get("firstDateOfMonth");
				lastDate = retMonthMap.get("lastDateOfMonth");
			} else if (firstDateFlay == Tuser.FIRSTDATE_OF_LASTMONTH) {// 上月
				firstDate = retMonthMap.get("firstDateOfLastMonth");
				lastDate = retMonthMap.get("lastDateOfLastMonth");
			} else if (firstDateFlay == Tuser.FIRSTDATE_OF_YEAR) {// 今年
				firstDate = dateMaps.get("firstDateOfYear");
				lastDate = dateMaps.get("lastDateOfYear");
			} else if (firstDateFlay == Tuser.FIRSTDATE_OF_LASTYEAR) {// 去年
				firstDate = dateMaps.get("firstDateOfLastYear");
				lastDate = dateMaps.get("lastDateOfLastYear");
			}
		}

		for (ComboboxJson comboboxJson : orgs) {
			// 3.1根据校区按日统计
			json = new ComboboxJson();
			json.setText(comboboxJson.getValue());
			if (Tuser.FUNCTION_COUNT == functionFlag) {
				json.setValue(studentDao.countBySQL(sql, comboboxJson.getValue(), firstDate, lastDate).toString());
			} else if (Tuser.FUNCTION_SUM == functionFlag) {
				json.setValue(studentDao.columSum(sql, comboboxJson.getValue(), firstDate, lastDate).toString());
			}

			retVal.add(json);
		}

		if (retVal != null && retVal.size() > 0) {
			return retVal;
		}
		return null;
	}

	// 根据校区标识查找出所有该校区的所有内部学员
	public List<Student> getInsideBySchoolArea(String schoolArea) throws Exception {
		if (!ValidateUtil.isValid(schoolArea)) {
			return null;
		}
		String sql = "select * from tb_student where organizationId=? and studentType='0'";
		List<Tstudent> studens = studentDao.findAllBySQL(Tstudent.class, sql, schoolArea);
		if (studens != null && studens.size() > 0) {
			Student student = null;
			List<Student> retVal = new ArrayList<Student>();
			for (Tstudent entry : studens) {
				student = new Student();
				BeanUtils.copyProperties(entry, student);
				// 处理学员编号(删除编号前边两个字母)
				student.setStudentNo(StringUtil.removeFisrtTwoLetter(entry.getStudentNo()));
				retVal.add(student);
			}
			return retVal;
		}
		return null;

	}

	// 按周明细统计学员财务状况
	public Map<String, Map<String, Double>> getMoneyWeekCountByOrgIds(Integer weekFlag) throws ParseException {

		// 1.根安排序获取校区标识
		List<ComboboxJson> orgs = this.getOrganizationIds();

		// 2.设置时间参数，根据当天获取日期，日，周，月，年
		Map<String, Date> dateMaps = DateUtil.getFisrtLastDateByDate(new Date());

		// 当前日期所在的周
		Date firstDateOfWeek = null;
		Date secondDateOfWeek = null;
		Date thirdDateOfWeek = null;
		Date fourthDateOfWeek = null;
		Date fifthDateOfWeek = null;
		Date sixthDateOfWeek = null;
		Date lastDateOfWeek = null;
		if (weekFlag == 3) {// 上周
			firstDateOfWeek = dateMaps.get("firstDateOfLastWeek");
			secondDateOfWeek = dateMaps.get("secondDateOfLastWeek");
			thirdDateOfWeek = dateMaps.get("thirdDateOfLastWeek");
			fourthDateOfWeek = dateMaps.get("fourthDateOfLastWeek");
			fifthDateOfWeek = dateMaps.get("fifthDateOfLastWeek");
			sixthDateOfWeek = dateMaps.get("sixthDateOfLastWeek");
			lastDateOfWeek = dateMaps.get("lastDateOfLastWeek");
		} else if (weekFlag == 2) {// 本同
			firstDateOfWeek = dateMaps.get("firstDateOfWeek");
			secondDateOfWeek = dateMaps.get("secondDateOfWeek");
			thirdDateOfWeek = dateMaps.get("thirdDateOfWeek");
			fourthDateOfWeek = dateMaps.get("fourthDateOfWeek");
			fifthDateOfWeek = dateMaps.get("fifthDateOfWeek");
			sixthDateOfWeek = dateMaps.get("sixthDateOfWeek");
			lastDateOfWeek = dateMaps.get("lastDateOfWeek");
		}

		Map<String, Map<String, Double>> retMap = new HashMap<String, Map<String, Double>>(0);
		Map<String, Double> totalMap = null;
		// 3.根据校区统计学员人数
		for (ComboboxJson comboboxJson : orgs) {
			// 3.1根据校区按日统计
			String dateSql = "select sum(realFeed) from tb_student where organizationId=? and createTime=?";
			Double mondayTotal = studentDao.columSum(dateSql, comboboxJson.getValue(), firstDateOfWeek);
			Double tuesdayTotal = studentDao.columSum(dateSql, comboboxJson.getValue(), secondDateOfWeek);
			Double wednesdayTotal = studentDao.columSum(dateSql, comboboxJson.getValue(), thirdDateOfWeek);
			Double thursdayTotal = studentDao.columSum(dateSql, comboboxJson.getValue(), fourthDateOfWeek);
			Double fridayTotal = studentDao.columSum(dateSql, comboboxJson.getValue(), fifthDateOfWeek);
			Double saturdayTotal = studentDao.columSum(dateSql, comboboxJson.getValue(), sixthDateOfWeek);
			Double sundayTotal = studentDao.columSum(dateSql, comboboxJson.getValue(), lastDateOfWeek);

			totalMap = new HashMap<String, Double>(0);
			totalMap.put("Monday", mondayTotal);
			totalMap.put("Tuesday", tuesdayTotal);
			totalMap.put("Wednesday", wednesdayTotal);
			totalMap.put("Thursday", thursdayTotal);
			totalMap.put("Friday", fridayTotal);
			totalMap.put("Saturday", saturdayTotal);
			totalMap.put("Sunday", sundayTotal);

			retMap.put(comboboxJson.getValue(), totalMap);
		}
		if (retMap != null && retMap.size() > 0) {
			return retMap;
		}
		return null;
	}

	public String getStudentNoAndName(String studentId) {
		if (!ValidateUtil.isValid(studentId)) {
			return null;
		}
		Tstudent student = this.getSingleById(studentId);
		if (student != null) {
			return StringUtil.getFormatterStudentNo(student.getStudentNo()) + ":" + student.getName();
		}
		return null;
	}

	/**
	 * 根据教练信息，查找出该教练有关的学员信息
	 * 
	 * @param schoolArea
	 * @param trainerId
	 * @param trainerType
	 * @return
	 * @throws Exception
	 */
	public List<Student> getStudentsByTrainer(String schoolArea, String trainerId, Integer trainerType)
			throws Exception {
		if (!ValidateUtil.isValid(schoolArea) || !ValidateUtil.isValid(trainerId)
				|| !ValidateUtil.isValid(trainerType)) {
			return null;
		}
		// 1.根据教练信息查找出所有该教练的学员(非承包教练不列出学员信息)
		if (Ttrainer.TRAINERTYPE_INSIDE == trainerType) {
			return null;
		}
		List<Student> retLists = new ArrayList<Student>(0);
		String sql = "select * from tb_student where organizationId=? and trainerId=? order by createTime desc";
		List<Tstudent> students = studentDao.findAllBySQL(Tstudent.class, sql, schoolArea, trainerId);
		if (ValidateUtil.isValidListObject(students)) {
			// 2.数据模型转换
			Student stu = null;
			for (Tstudent entry : students) {
				stu = new Student();
				BeanUtils.copyProperties(entry, stu);
				retLists.add(stu);
			}
		}

		// 3.返回结果集合
		if (ValidateUtil.isValidListObject(retLists)) {
			return retLists;
		}
		return null;

	}

	private Integer getStudentStateTpyeByString(String str) {
		if (!ValidateUtil.isValid(str)) {
			return null;
		}
		Integer reVal = null;
		if (str.trim().equals("在培")) {
			reVal = 1;
		} else if (str.trim().equals("毕业")) {
			reVal = 2;
		} else if (str.trim().equals("退学")) {
			reVal = 3;
		} else if (str.trim().equals("到期")) {
			reVal = 4;
		} else if (str.trim().equals("不计时")) {
			reVal = 5;
		}
		if (reVal > 0) {
			return reVal;
		}
		return null;
	}

	private Integer getApplyTypeeByString(String str) {
		if (!ValidateUtil.isValid(str)) {
			return null;
		}
		Integer reVal = null;
		if (str.trim().equals("初次申领")) {
			reVal = 1;
		} else if (str.trim().equals("增加准驾车型")) {
			reVal = 2;
		} else if (str.trim().equals("持军官驾驶证")) {
			reVal = 3;
		} else if (str.trim().equals("持境外驾驶证")) {
			reVal = 4;
		}
		if (reVal > 0) {
			return reVal;
		}
		return null;
	}

	private Integer getTimingFlagByString(String str) {
		if (!ValidateUtil.isValid(str)) {
			return null;
		}
		Integer reVal = null;
		if (str.trim().equals("计时")) {
			reVal = 1;
		} else if (str.trim().equals("非计时")) {
			reVal = 0;
		}
		if (reVal != null) {
			return reVal;
		}
		return null;
	}

	private Integer getStudentTypeByString(String str) {
		if (!ValidateUtil.isValid(str)) {
			return null;
		}
		Integer reVal = null;
		if (str.trim().equals("本校")) {
			reVal = 0;
		} else if (str.trim().equals("承包")) {
			reVal = 1;
		}
		if (reVal != null) {
			return reVal;
		}
		return null;
	}

	private String getTrainerIdByIdentity(String identityId) {
		if (!ValidateUtil.isValid(identityId)) {
			return null;
		}
		String hql = "from Ttrainer t where t.identity=?";
		List<Object> params = new ArrayList<Object>();
		params.add(identityId);
		Ttrainer trainer = (Ttrainer) trainerDao.uniqueResult(hql, identityId);
		if (trainer != null) {
			return trainer.getId();
		}
		return null;
	}

	private void deleteCarusingByReservationId(String reservationId) {
		String hql = "from TusingCar t where t.reservationId=?";
		TusingCar usingCar = (TusingCar) usingCarDao.uniqueResult(hql, reservationId);
		if (usingCar != null) {
			usingCarDao.delete(usingCar);
		}
	}

	private Long hasReservationUnfinished(String studentId, String trainerId) {
		if (!ValidateUtil.isValid(studentId) || !ValidateUtil.isValid(trainerId)) {
			return null;
		}
		String hql = "select count(*) from Treservation t where t.student.id=? and t.trainer.id=? and t.reservationState=?";
		List<Object> params = new ArrayList<Object>();
		params.add(studentId);
		params.add(trainerId);
		params.add(1);
		Long retVal = reservationDao.count(hql, params);
		return retVal;
	}

	/**
	 * 根据驾照类型选配该驾照类型编号的字母代号
	 * 
	 * @param type
	 * @return
	 */
	private String getFistLetterByDriverType(Integer type) {
		if (type < 0) {
			return null;
		}
		String retStr = null;
		if (type > 0 && type == 6) {// C1:编号F开头
			retStr = "F";
		} else if (type > 0 && type == 7) {// C2:编号B开头
			retStr = "B";
		} else if (type > 0 && type == 8) {// C3:编号R开头
			retStr = "R";
		} else if (type > 0 && type == 9) {// C4:编号S开头
			retStr = "S";
		} else if (type > 0 && type == 10) {// C5:编号T开头
			retStr = "T";
		} else if (type > 0 && type == 4) {// B1:编号U开头
			retStr = "U";
		} else if (type > 0 && type == 5) {// B2:编号V开头
			retStr = "V";
		} else if (type > 0 && type == 1) {// A1:编号W开头
			retStr = "W";
		} else if (type > 0 && type == 2) {// A2:编号X开头
			retStr = "X";
		} else if (type > 0 && type == 3) {// A3:编号Y开头
			retStr = "Y";
		}
		return retStr;
	}

	// 按排序获取校区标识
	private List<ComboboxJson> getOrganizationIds() {
		List<ComboboxJson> retLists = new ArrayList<ComboboxJson>();
		String sql = "select * from tb_organizations  where prentId is not null order by sequence asc";
		List<Torganization> orgs = organizationDao.findAllBySQL(Torganization.class, sql);
		if (orgs != null && orgs.size() > 0) {
			for (Torganization t : orgs) {
				ComboboxJson json = new ComboboxJson();
				json.setText(t.getName());
				json.setValue(t.getId());
				retLists.add(json);
			}
		}
		return retLists;
	}

	private void fixTiming() {
		String sql = "select * from tb_student t,tb_organizations o where o.id=?";
		List<Tstudent> rets = studentDao.findAllBySQL(Tstudent.class, sql, Torganization.BS_SCHOOLAREA);
		if (ValidateUtil.isValidListObject(rets)) {
			Ttiming timing = null;
			for (Tstudent tstudent : rets) {
				if (tstudent.getTiming() == null) {
					timing = new Ttiming();
					timing.setId(UUID.randomUUID().toString());
					timing.setNumbering(GeneraterIdByDateUtil.getGeneraterIdByDate());
					if (ValidateUtil.isValid(tstudent.getOrganization().getId())) {
						timing.setSchoolArea(tstudent.getOrganization().getId());
					}
					if (tstudent.getLearningTime() != null) {
						timing.setHandselTiming(tstudent.getLearningTime());// 报名所含学时
						timing.setTotalTiming(tstudent.getLearningTime());// 创建学时信息时学员默认总学时
						timing.setRestTiming(tstudent.getLearningTime());// 创建学时信息时学员剩余学时值
						timing.setReservationTiming(tstudent.getLearningTime());// 创建学员信息时记录学员的可预约学时
					}
					timing.setUsingTiming(0);// 创建学时信息时学员默认使用学时为0
					timing.setBuyTiming(0);// 创建学时信息时学员默认购买学时为0
					timing.setSimulationTiming(0);
					timing.setTramTiming(0);
					timing.setCarTiming(0);
					timing.setStudent(tstudent);
					// 学员编号（冗余字段）
					timingDaoI.save(timing);
				}
			}
		}
	}

	private TnetPiont getSingleNetPion(String id) {
		if (!ValidateUtil.isValid(id)) {
			return null;
		}
		String sql = "select * from tb_netPionts where id=?";
		TnetPiont netPiont = netPiontDao.getSingleBySQL(TnetPiont.class, sql, id);
		if (null != netPiont) {
			return netPiont;
		}
		return null;
	}

}

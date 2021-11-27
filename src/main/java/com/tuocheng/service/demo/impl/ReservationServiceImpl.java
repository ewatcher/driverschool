package com.tuocheng.service.demo.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.TautoCarArrange;
import com.tuocheng.model.demo.Tcar;
import com.tuocheng.model.demo.TcarusingTemp;
import com.tuocheng.model.demo.Torganization;
import com.tuocheng.model.demo.TpersonalTiming;
import com.tuocheng.model.demo.Treservation;
import com.tuocheng.model.demo.Tstudent;
import com.tuocheng.model.demo.TstudentExam;
import com.tuocheng.model.demo.Ttiming;
import com.tuocheng.model.demo.Ttrainer;
import com.tuocheng.model.demo.TtrainerReservation;
import com.tuocheng.model.demo.TtrainerReservationDetail;
import com.tuocheng.model.demo.Tuser;
import com.tuocheng.model.demo.TusingCar;
import com.tuocheng.model.demo.TweixinUser;
import com.tuocheng.pageModel.base.ComboboxJson;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.Reservation;
import com.tuocheng.pageModel.demo.TrainerReservationDetail;
import com.tuocheng.service.demo.ReservationServiceI;
import com.tuocheng.service.demo.WeixinUserServiceI;
import com.tuocheng.util.base.DateUtil;
import com.tuocheng.util.base.GeneraterIdByDateUtil;
import com.tuocheng.util.base.RadomUtil;
import com.tuocheng.util.base.StringUtil;
import com.tuocheng.util.base.Util;
import com.tuocheng.util.base.ValidateUtil;
import com.tuocheng.util.base.WeixinUtil;
import com.tuocheng.weixin.base.Weixin;
import com.tuocheng.weixin.message.WeixinMessageService;

/**
 * 预约管理实体类service实现类
 * 
 * @author MEI702
 * 
 */
@Service("reservationService")
public class ReservationServiceImpl implements ReservationServiceI {

	private BaseDaoI<Treservation> reservationDao;
	private BaseDaoI<Tstudent> studentDao;
	private BaseDaoI<Ttrainer> trainerDao;
	private BaseDaoI<TtrainerReservationDetail> reservationDetailDao;
	private BaseDaoI<TtrainerReservation> trainerReservationDao;
	private BaseDaoI<TpersonalTiming> personalTimingDao;
	private BaseDaoI<Ttiming> timingDao;
	private BaseDaoI<TusingCar> usingCarDao;
	private BaseDaoI<Tcar> carDao;
	private BaseDaoI<TcarusingTemp> carusingTempDao;
	private WeixinUserServiceI wUserService;
	private BaseDaoI<Torganization> organizationDao;
	private BaseDaoI<TautoCarArrange> autocarArrangeDao;
	private BaseDaoI<TstudentExam> studentExamDao;
	private BaseDaoI<TautoCarArrange> autoCarArrangeDao;

	@Autowired
	public void setAutoCarArrangeDao(BaseDaoI<TautoCarArrange> autoCarArrangeDao) {
		this.autoCarArrangeDao = autoCarArrangeDao;
	}

	@Autowired
	public void setReservationDao(BaseDaoI<Treservation> reservationDao) {
		this.reservationDao = reservationDao;
	}

	@Autowired
	public void setStudentDao(BaseDaoI<Tstudent> studentDao) {
		this.studentDao = studentDao;
	}

	@Autowired
	public void setTrainerDao(BaseDaoI<Ttrainer> trainerDao) {
		this.trainerDao = trainerDao;
	}

	@Autowired
	public void setReservationDetailDao(BaseDaoI<TtrainerReservationDetail> reservationDetailDao) {
		this.reservationDetailDao = reservationDetailDao;
	}

	@Autowired
	public void setTrainerReservationDao(BaseDaoI<TtrainerReservation> trainerReservationDao) {
		this.trainerReservationDao = trainerReservationDao;
	}

	@Autowired
	public void setPersonalTimingDao(BaseDaoI<TpersonalTiming> personalTimingDao) {
		this.personalTimingDao = personalTimingDao;
	}

	@Autowired
	public void setTimingDao(BaseDaoI<Ttiming> timingDao) {
		this.timingDao = timingDao;
	}

	@Autowired
	public void setUsingCarDao(BaseDaoI<TusingCar> usingCarDao) {
		this.usingCarDao = usingCarDao;
	}

	@Autowired
	public void setCarDao(BaseDaoI<Tcar> carDao) {
		this.carDao = carDao;
	}

	@Autowired
	public void setCarusingTempDao(BaseDaoI<TcarusingTemp> carusingTempDao) {
		this.carusingTempDao = carusingTempDao;
	}

	@Autowired
	public void setwUserService(WeixinUserServiceI wUserService) {
		this.wUserService = wUserService;
	}

	@Autowired
	public void setOrganizationDao(BaseDaoI<Torganization> organizationDao) {
		this.organizationDao = organizationDao;
	}

	@Autowired
	public void setAutocarArrangeDao(BaseDaoI<TautoCarArrange> autocarArrangeDao) {
		this.autocarArrangeDao = autocarArrangeDao;
	}

	@Autowired
	public void setStudentExamDao(BaseDaoI<TstudentExam> studentExamDao) {
		this.studentExamDao = studentExamDao;
	}

	@Override
	public Integer save(Reservation reservation) throws Exception {
		boolean updateFlag = false;// 标记用户当前操作是更新操作或者添加操作，false表示添加
		Ttrainer trainer = null;
		Ttiming timing = null;

		// 1.数据模型转换
		Treservation entry = new Treservation();
		BeanUtils.copyProperties(reservation, entry, new String[] { "id" });
		String reservationId = UUID.randomUUID().toString();
		entry.setNumbering(GeneraterIdByDateUtil.getGeneraterIdByDate());
		entry.setTrainerReservationDetailId(reservation.getReservationDetailId());

		// 2.1保存学员关联关系
		Tstudent student = null;
		if (ValidateUtil.isValid(reservation.getStudentId())) {
			student = studentDao.get(Tstudent.class, reservation.getStudentId().trim());
			if (student != null) {
				entry.setStudent(student);
				entry.setSchoolArea(student.getOrganization().getId());
				entry.setReservationWay(reservation.getReservationWay());
				entry.setCreateTime(new Date());
				// 2.2保存教练关联关系
				if (ValidateUtil.isValid(reservation.getTrainerId())) {
					trainer = trainerDao.get(Ttrainer.class, reservation.getTrainerId().trim());
					if (trainer != null) {
						entry.setTrainer(trainer);
						// 3.修改教练预约明细信息
						TtrainerReservationDetail trainerRservationDetail = null;
						if (ValidateUtil.isValid(reservation.getReservationDetailId())) {
							// 3.1获取教练预约明细信息
							trainerRservationDetail = reservationDetailDao.get(TtrainerReservationDetail.class,
									reservation.getReservationDetailId());

							// 3.2 关联教练与教练预约明细的关系
							trainerRservationDetail.setTrainerId(reservation.getTrainerId());

							// 3.3保存教练预约与教练预约明细关联关系
							if (ValidateUtil.isValid(reservation.getTrainerReservationId())) {// TODO
								TtrainerReservation trainerReservation = trainerReservationDao
										.get(TtrainerReservation.class, reservation.getTrainerReservationId());
								trainerRservationDetail.setTrainerReservation(trainerReservation);
							}

							// 4,如果学员是计时学员，则统修改学员可预约学时值
							if (ValidateUtil.isValid(student.getTimingFlag())) {
								// 创建当前预约信息时，学员可预约学时减一，
								timing = student.getTiming();
								if (timing != null) {
									// 1.计时类学员
									if (Tstudent.TIMING_COUNTING == student.getTimingFlag()) {
										if (timing.getRestTiming() - 1 < 0) {// 学时不够不允许预约
											return -1;
										}
										// 剩余学时-1＝0表示预约学时为0，不能参加一下次预约
										if (timing.getRestTiming() - 1 == 0) {
											student.setRestTiming(0);// 可预约学时为０，取消学员预约资格
										}
										// 剩余学时减去1
										timing.setReservationTiming(timing.getRestTiming() - 1);// 可预约学时减去1
										timing.setRestTiming(timing.getRestTiming() - 1);// 剩余学时送去1
										timingDao.update(timing);
									}
									// 添加学时明细信息
									this.addPersonalTiming(reservation, timing, trainer, student);

								}
							}

							// 根据用户操作“列值”修改其列相应的学员标识，预约类型
							if (reservation.getFieldOptionFlag() > 0) {

								this.configTrainerReservationDetail(reservation, student, trainerRservationDetail,
										reservationId);
								if (!updateFlag) {
									entry.setId(reservationId);
									entry.setReservationState(1);// 创建预约时，使该预约记录处于预约状态
								}
								studentDao.update(student);
								String carId = null;
								if (ValidateUtil.isValid(reservation.getCarId())) {
									if (3 == reservation.getReservationType()
											|| 4 == reservation.getReservationType()) {
										carId = reservation.getCarId();
									}
								} else {
									// 根据预约类型，获取预约车辆（电车，模拟）
									String sql = "select * from tb_cars where useType=?";
									List<Tcar> cars = null;
									if (1 == reservation.getReservationType()) {// 电车预约默认选配车辆
										cars = carDao.findAllBySQL(Tcar.class, sql, 12);
										if (ValidateUtil.isValidListObject(cars)) {
											carId = cars.get(0).getId();
										}
									} else if (2 == reservation.getReservationType()) {// 模拟器预约默认选配车辆
										cars = carDao.findAllBySQL(Tcar.class, sql, 13);
										if (ValidateUtil.isValidListObject(cars)) {
											carId = cars.get(0).getId();
										}
									} else {
										carId = null;
									}
								}
								// 添加预约有关的车辆信息
								addCarUsering(trainer, reservation.getFieldOptionFlag(), reservationId,
										reservation.getDate(), student.getDriverType(), carId, entry);// 添加车辆使用

							}

							// 2.保存数据
							if (3 == reservation.getReservationType() || 4 == reservation.getReservationType()) {
								if (checkReservationExist(reservation)) {
									return -2;// -2表示该教练在该时间段已经存在预约，不可重复。
								}
								reservationDao.save(entry);
								reservationDetailDao.saveOrUpdate(trainerRservationDetail);
							} else {
								reservationDao.save(entry);
								reservationDetailDao.saveOrUpdate(trainerRservationDetail);
							}

							// 3.将保存后数据模型转换
							BeanUtils.copyProperties(entry, reservation);
							// 如果取消的是今天的预约 通知教练学员预约
							if (Util.isNotNull(trainer.getId())) {
								String text = "学员[" + student.getName() + "]成功于["
										+ DateUtil.dateToString(reservation.getDate()) + " "
										+ reservation.getFieldOptionFlag() + ":00]预约教练[" + trainer.getName() + "]训练[ "
										+ StringUtil.getStringByReservationType(reservation.getReservationType())
										+ "],请登录微信公众号学员端查看详情！";
								TweixinUser wTrainerUser = wUserService.getByTrainerId(trainer.getId());
								TweixinUser wStudentUser = wUserService.getByStudentId(student.getId());

								if (wTrainerUser != null) {

									Weixin weixinTrainer = new WeixinUtil()
											.getWeixinByOrganizationId(trainer.getSchoolArea());
									WeixinMessageService wxmsgTrainer = new WeixinMessageService(weixinTrainer);
									wxmsgTrainer.postCustomServiceMessage(ServletActionContext.getServletContext(),
											wTrainerUser.getOpenId(), text);
								}
								if (wStudentUser != null) {
									Weixin weixinStudent = new WeixinUtil()
											.getWeixinByOrganizationId(student.getOrganization().getId());
									WeixinMessageService wxmsgStudent = new WeixinMessageService(weixinStudent);
									wxmsgStudent.postCustomServiceMessage(ServletActionContext.getServletContext(),
											wStudentUser.getOpenId(), text);
								}
							}

							// 4.将转换后的数据返回给前台
							return 1;// 1：表示预约成功

						}
					}
				} // 教练信息不为空
			} // 学员信息不为空

		}
		return -3;// 表示预约失败
	}

	/**
	 * return ((1：预约成绩
	 * -1:表示学时已经用完，-2：表示该教练在该时间段已经存在预约，不可重复，-3：表示预约失败)-1:无自动档车辆无法预约，－2：)
	 * 
	 */
	public Integer saveForWexin(Reservation reservation) throws Exception {

		Integer feildOptionFlag = null;
		if (ValidateUtil.isValid(reservation.getTimeStart()) && ValidateUtil.isValid(reservation.getTimeEnd())) {
			if (reservation.getTimeStart().trim().equals("7:00")) {
				feildOptionFlag = 7;
			} else if (reservation.getTimeStart().trim().equals("8:00")) {
				feildOptionFlag = 8;
			} else if (reservation.getTimeStart().trim().equals("9:00")) {
				feildOptionFlag = 9;
			} else if (reservation.getTimeStart().trim().equals("10:00")) {
				feildOptionFlag = 10;
			} else if (reservation.getTimeStart().trim().equals("11:00")) {
				feildOptionFlag = 11;
			} else if (reservation.getTimeStart().trim().equals("12:00")) {
				feildOptionFlag = 12;
			} else if (reservation.getTimeStart().trim().equals("13:00")) {
				feildOptionFlag = 13;
			} else if (reservation.getTimeStart().trim().equals("14:00")) {
				feildOptionFlag = 14;
			} else if (reservation.getTimeStart().trim().equals("15:00")) {
				feildOptionFlag = 15;
			} else if (reservation.getTimeStart().trim().equals("16:00")) {
				feildOptionFlag = 16;
			} else if (reservation.getTimeStart().trim().equals("17:00")) {
				feildOptionFlag = 17;
			} else if (reservation.getTimeStart().trim().equals("18:00")) {
				feildOptionFlag = 18;
			} else if (reservation.getTimeStart().trim().equals("19:00")) {
				feildOptionFlag = 19;
			} else if (reservation.getTimeStart().trim().equals("20:00")) {
				feildOptionFlag = 20;
			} else if (reservation.getTimeStart().trim().equals("21:00")) {
				feildOptionFlag = 21;
			}

			reservation.setFieldOptionFlag(feildOptionFlag);

			switch (feildOptionFlag) {
			case 7: {
				reservation.setItem7Studentid(reservation.getStudentNo());
				reservation.setItem7TrainContent(reservation.getReservationType());
			}
				break;
			case 8: {
				reservation.setItem8Studentid(reservation.getStudentNo());
				reservation.setItem8TrainContent(reservation.getReservationType());
			}
				break;
			case 9: {
				reservation.setItem9Studentid(reservation.getStudentNo());
				reservation.setItem9TrainContent(reservation.getReservationType());
			}
				break;
			case 10: {
				reservation.setItem10Studentid(reservation.getStudentNo());
				reservation.setItem10TrainContent(reservation.getReservationType());
			}
				break;
			case 11: {
				reservation.setItem11Studentid(reservation.getStudentNo());
				reservation.setItem11TrainContent(reservation.getReservationType());
			}
				break;
			case 12: {
				reservation.setItem12Studentid(reservation.getStudentNo());
				reservation.setItem12TrainContent(reservation.getReservationType());
			}
				break;
			case 13: {
				reservation.setItem13Studentid(reservation.getStudentNo());
				reservation.setItem13TrainContent(reservation.getReservationType());
			}
				break;
			case 14: {
				reservation.setItem14Studentid(reservation.getStudentNo());
				reservation.setItem14TrainContent(reservation.getReservationType());
			}
				break;
			case 15: {
				reservation.setItem15Studentid(reservation.getStudentNo());
				reservation.setItem15TrainContent(reservation.getReservationType());
			}
				break;
			case 16: {
				reservation.setItem16Studentid(reservation.getStudentNo());
				reservation.setItem16TrainContent(reservation.getReservationType());
			}
				break;
			case 17: {
				reservation.setItem17Studentid(reservation.getStudentNo());
				reservation.setItem17TrainContent(reservation.getReservationType());
			}
				break;
			case 18: {
				reservation.setItem18Studentid(reservation.getStudentNo());
				reservation.setItem18TrainContent(reservation.getReservationType());
			}
				break;
			case 19: {
				reservation.setItem19Studentid(reservation.getStudentNo());
				reservation.setItem19TrainContent(reservation.getReservationType());
			}
				break;
			case 20: {
				reservation.setItem20Studentid(reservation.getStudentNo());
				reservation.setItem20TrainContent(reservation.getReservationType());
			}
				break;
			case 21: {
				reservation.setItem21Studentid(reservation.getStudentNo());
				reservation.setItem21TrainContent(reservation.getReservationType());
			}
				break;
			}
		}

		// 2. C2系统自动选配预约车辆

		if (ValidateUtil.isValid(reservation.getStudentId()) && ValidateUtil.isValid(reservation.getTrainerId())) {
			Tstudent student = studentDao.get(Tstudent.class, reservation.getStudentId());
			// 限制毕业学员预约
			if (Tstudent.NOWSTATE_INTRAINING != student.getNowState()) {
				return -7;
			}
			// 2.1非电车，模拟预约则通过教练获取车辆ID
			if (3 == reservation.getReservationType() || 4 == reservation.getReservationType()) {
				// 2.1.1 C2学员预约需要自动选配车辆
				if (student != null && 7 == student.getDriverType()) {
					List<ComboboxJson> carIds = this.getAutoCarsForReservation(reservation.getSchoolArea(),
							reservation.getDate(), feildOptionFlag, 1);
					// 获取预约当天该学员预约过的车辆ID
					String continuityCarId = this.getContinuityCarForWechat(reservation.getSchoolArea(),
							reservation.getStudentId(), reservation.getDate());
					if (ValidateUtil.isValidListObject(carIds)) {
						if (ValidateUtil.isValid(continuityCarId)) {
							for (ComboboxJson comboboxJson : carIds) {
								if (continuityCarId.equals(comboboxJson.getValue())) {
									reservation.setCarId(continuityCarId);// 连续预约使用同一步车
								}
							}
						} else {
							reservation.setCarId(carIds.get(RadomUtil.getRandomByRand(0, carIds.size())).getValue());
						}

					} else {
						return -1;// 没有与预约匹配的车辆
					}
				} else {// 2.1.2 非C2预约则通过教练获取车辆
					Tcar car = null;
					if (null != student.getTrainer()) {
						String sql = "select * from tb_cars where schoolArea=? and trainerId=?";
						car = carDao.getSingleBySQL(Tcar.class, sql, reservation.getSchoolArea(),
								reservation.getTrainerId());
					}
					if (null != car) {
						reservation.setCarId(car.getId());
					}
				}

			} // 2.2电车，模块无需要选择车辆

		}
		try {
			if (checkTotalByDate(reservation.getStudentId(), reservation.getReservationDate(),
					reservation.getReservationType())) {
				return -5;// 预约数达到上限
			}
			if (reservationExit(reservation.getStudentId(), reservation.getDate(), reservation.getFieldOptionFlag(),
					reservation.getSchoolArea())) {
				return -6;// 预约已经存在
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		// 标记预约创建方式为微信端
		reservation.setReservationWay(2);
		return save(reservation);
	}

	@Override
	public Reservation udpate(Reservation reservation) {
		// 1.数据模型转换
		Treservation t = new Treservation();
		BeanUtils.copyProperties(reservation, t, new String[] { "id" });
		t.setId(reservation.getId());

		// 2.保存数据
		reservationDao.update(t);

		// 3.将保存后数据模型转换
		BeanUtils.copyProperties(t, reservation);
		// 4.将转换后的数据返回给前台
		return reservation;
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
					Treservation reservation = reservationDao.get(Treservation.class, id);
					// 3.1清空教练明细信息中的该学员预约信息
					TtrainerReservationDetail trainerReservationDetail = reservationDetailDao
							.get(TtrainerReservationDetail.class, reservation.getTrainerReservationDetailId());
					Integer fieldFlag = reservation.getFieldOptionFlag();
					// 如果是删除已经取消或者预约完成的记录，则不用修改与该预约有关的教练预约明细信息
					if (reservation.getReservationState() == Treservation.RESERVATIONSTATE_CANCEL
							|| reservation.getReservationState() == Treservation.RESERVATIONSTATE_FINISH) {
						reservationDao.delete(reservation);
						return;
					}
					// 获取该预约明细的所属教练
					String sql = "select * from tb_trainers where id=? and schoolArea=?";
					Ttrainer trainer = trainerDao.getSingleBySQL(Ttrainer.class, sql,
							trainerReservationDetail.getTrainerId(), trainerReservationDetail.getSchoolArea());

					switch (fieldFlag) {
					case 7: {
						// 清空教练预约明细表中该时段内容
						trainerReservationDetail.setItem7Studentid(null);
						trainerReservationDetail.setReservationFieldId7(null);
						// 修改电车预约单个时间段的预约次数
						if (trainer != null && (trainer.getArrangeType() == Ttrainer.ARRANGETYPE_ELECTRIC
								|| trainer.getArrangeType() == Ttrainer.ARRANGETYPE_SIMULATION)) {
							trainerReservationDetail
									.setReservation7MaxTotal(trainerReservationDetail.getReservation7MaxTotal() - 1);
						}

					}
					case 8: {
						// 清空教练预约明细表中该时段内容
						trainerReservationDetail.setItem8Studentid(null);
						trainerReservationDetail.setReservationFieldId8(null);
						// 修改电车预约单个时间段的预约次数
						if (trainer != null && (trainer.getArrangeType() == Ttrainer.ARRANGETYPE_ELECTRIC
								|| trainer.getArrangeType() == Ttrainer.ARRANGETYPE_SIMULATION)) {
							trainerReservationDetail
									.setReservation8MaxTotal(trainerReservationDetail.getReservation8MaxTotal() - 1);
						}

					}
						break;
					case 9: {
						// 清空教练预约明细表中该时段内容
						trainerReservationDetail.setItem9Studentid(null);
						trainerReservationDetail.setReservationFieldId9(null);
						// 修改电车预约单个时间段的预约次数
						if (trainer != null && (trainer.getArrangeType() == Ttrainer.ARRANGETYPE_ELECTRIC
								|| trainer.getArrangeType() == Ttrainer.ARRANGETYPE_SIMULATION)) {
							trainerReservationDetail
									.setReservation9MaxTotal(trainerReservationDetail.getReservation9MaxTotal() - 1);
						}
					}
						break;
					case 10: {
						// 清空教练预约明细表中该时段内容
						trainerReservationDetail.setItem10Studentid(null);
						trainerReservationDetail.setReservationFieldId10(null);
						// 修改电车预约单个时间段的预约次数
						if (trainer != null && (trainer.getArrangeType() == Ttrainer.ARRANGETYPE_ELECTRIC
								|| trainer.getArrangeType() == Ttrainer.ARRANGETYPE_SIMULATION)) {
							trainerReservationDetail
									.setReservation10MaxTotal(trainerReservationDetail.getReservation10MaxTotal() - 1);
						}
					}
						break;
					case 11: {
						// 清空教练预约明细表中该时段内容
						trainerReservationDetail.setItem11Studentid(null);
						trainerReservationDetail.setReservationFieldId11(null);
						// 修改电车预约单个时间段的预约次数
						if (trainer != null && (trainer.getArrangeType() == Ttrainer.ARRANGETYPE_ELECTRIC
								|| trainer.getArrangeType() == Ttrainer.ARRANGETYPE_SIMULATION)) {
							trainerReservationDetail
									.setReservation11MaxTotal(trainerReservationDetail.getReservation11MaxTotal() - 1);
						}
					}
						break;
					case 12: {
						// 清空教练预约明细表中该时段内容
						trainerReservationDetail.setItem12Studentid(null);
						trainerReservationDetail.setReservationFieldId12(null);
						// 修改电车预约单个时间段的预约次数
						if (trainer != null && (trainer.getArrangeType() == Ttrainer.ARRANGETYPE_ELECTRIC
								|| trainer.getArrangeType() == Ttrainer.ARRANGETYPE_SIMULATION)) {
							trainerReservationDetail
									.setReservation12MaxTotal(trainerReservationDetail.getReservation12MaxTotal() - 1);
						}
					}
						break;
					case 13: {
						// 清空教练预约明细表中该时段内容
						trainerReservationDetail.setItem13Studentid(null);
						trainerReservationDetail.setReservationFieldId13(null);
						// 修改电车预约单个时间段的预约次数
						if (trainer != null && (trainer.getArrangeType() == Ttrainer.ARRANGETYPE_ELECTRIC
								|| trainer.getArrangeType() == Ttrainer.ARRANGETYPE_SIMULATION)) {
							trainerReservationDetail
									.setReservation13MaxTotal(trainerReservationDetail.getReservation13MaxTotal() - 1);
						}
					}
						break;
					case 14: {
						// 清空教练预约明细表中该时段内容
						trainerReservationDetail.setItem14Studentid(null);
						trainerReservationDetail.setReservationFieldId14(null);
						// 修改电车预约单个时间段的预约次数
						if (trainer != null && (trainer.getArrangeType() == Ttrainer.ARRANGETYPE_ELECTRIC
								|| trainer.getArrangeType() == Ttrainer.ARRANGETYPE_SIMULATION)) {
							trainerReservationDetail
									.setReservation14MaxTotal(trainerReservationDetail.getReservation14MaxTotal() - 1);
						}
					}
						break;
					case 15: {
						// 清空教练预约明细表中该时段内容
						trainerReservationDetail.setItem15Studentid(null);
						trainerReservationDetail.setReservationFieldId15(null);
						// 修改电车预约单个时间段的预约次数
						if (trainer != null && (trainer.getArrangeType() == Ttrainer.ARRANGETYPE_ELECTRIC
								|| trainer.getArrangeType() == Ttrainer.ARRANGETYPE_SIMULATION)) {
							trainerReservationDetail
									.setReservation15MaxTotal(trainerReservationDetail.getReservation15MaxTotal() - 1);
						}
					}
						break;
					case 16: {
						// 清空教练预约明细表中该时段内容
						trainerReservationDetail.setItem16Studentid(null);
						trainerReservationDetail.setReservationFieldId16(null);
						// 修改电车预约单个时间段的预约次数
						if (trainer != null && (trainer.getArrangeType() == Ttrainer.ARRANGETYPE_ELECTRIC
								|| trainer.getArrangeType() == Ttrainer.ARRANGETYPE_SIMULATION)) {
							trainerReservationDetail
									.setReservation16MaxTotal(trainerReservationDetail.getReservation16MaxTotal() - 1);
						}
					}
						break;
					case 17: {
						// 清空教练预约明细表中该时段内容
						trainerReservationDetail.setItem17Studentid(null);
						trainerReservationDetail.setReservationFieldId17(null);
						// 修改电车预约单个时间段的预约次数
						if (trainer != null && (trainer.getArrangeType() == Ttrainer.ARRANGETYPE_ELECTRIC
								|| trainer.getArrangeType() == Ttrainer.ARRANGETYPE_SIMULATION)) {
							trainerReservationDetail
									.setReservation17MaxTotal(trainerReservationDetail.getReservation17MaxTotal() - 1);
						}
					}
						break;
					case 18: {
						// 清空教练预约明细表中该时段内容
						trainerReservationDetail.setItem18Studentid(null);
						trainerReservationDetail.setReservationFieldId18(null);
						// 修改电车预约单个时间段的预约次数
						if (trainer != null && (trainer.getArrangeType() == Ttrainer.ARRANGETYPE_ELECTRIC
								|| trainer.getArrangeType() == Ttrainer.ARRANGETYPE_SIMULATION)) {
							trainerReservationDetail
									.setReservation18MaxTotal(trainerReservationDetail.getReservation18MaxTotal() - 1);
						}
					}
						break;
					case 19: {
						// 清空教练预约明细表中该时段内容
						trainerReservationDetail.setItem19Studentid(null);
						trainerReservationDetail.setReservationFieldId19(null);
						// 修改电车预约单个时间段的预约次数
						if (trainer != null && (trainer.getArrangeType() == Ttrainer.ARRANGETYPE_ELECTRIC
								|| trainer.getArrangeType() == Ttrainer.ARRANGETYPE_SIMULATION)) {
							trainerReservationDetail
									.setReservation19MaxTotal(trainerReservationDetail.getReservation19MaxTotal() - 1);
						}
					}
						break;
					case 20: {
						// 清空教练预约明细表中该时段内容
						trainerReservationDetail.setItem20Studentid(null);
						trainerReservationDetail.setReservationFieldId20(null);
						// 修改电车预约单个时间段的预约次数
						if (trainer != null && (trainer.getArrangeType() == Ttrainer.ARRANGETYPE_ELECTRIC
								|| trainer.getArrangeType() == Ttrainer.ARRANGETYPE_SIMULATION)) {
							trainerReservationDetail
									.setReservation20MaxTotal(trainerReservationDetail.getReservation20MaxTotal() - 1);
						}
					}
						break;
					case 21: {
						// 清空教练预约明细表中该时段内容
						trainerReservationDetail.setItem21Studentid(null);
						trainerReservationDetail.setReservationFieldId21(null);
						// 修改电车预约单个时间段的预约次数
						if (trainer != null && (trainer.getArrangeType() == Ttrainer.ARRANGETYPE_ELECTRIC
								|| trainer.getArrangeType() == Ttrainer.ARRANGETYPE_SIMULATION)) {
							trainerReservationDetail
									.setReservation21MaxTotal(trainerReservationDetail.getReservation21MaxTotal() - 1);
						}
					}
						break;
					}
					// 修改教练预约明细表
					reservationDetailDao.update(trainerReservationDetail);

					// 删除预约状态为预约中…，正常，挂起预约信息时，修改学员可预约学时，+1
					Ttiming timing = null;
					Tstudent student = null;
					if (reservation.getStudent() != null) {
						student = reservation.getStudent();
						if (student.getTiming() != null) {
							timing = student.getTiming();
							if (Tstudent.TIMING_COUNTING == student.getTimingFlag()) {
								// 1. 如果学员当前可预约学时为0,删除当前预约后，可用学员加1时，学员有资格参加预约
								if (timing.getReservationTiming() == 0) {
									student.setRestTiming(1);
									studentDao.update(student);
								}
								// 2.修改学员
								timing.setReservationTiming(timing.getReservationTiming() + 1);
								timing.setRestTiming(timing.getRestTiming() + 1);
							}

							// 删除学时明细信息
							String sqlPTM = "select * from tb_personalTimings where timingId=?";
							TpersonalTiming perTpersonalTiming = personalTimingDao.getSingleBySQL(TpersonalTiming.class,
									sqlPTM, timing.getId());
							if (null != perTpersonalTiming) {
								personalTimingDao.delete(perTpersonalTiming);
							}
							// 3.修改学员学时信息
							timingDao.update(timing);
						}
					}
					// 删除与此条预约有关的车辆使用信息
					String usingCarSql = "select * from tb_usingCars where reservationId=?";
					TusingCar usingcar = usingCarDao.getSingleBySQL(TusingCar.class, usingCarSql, reservation.getId());
					if (usingcar != null) {
						Tcar car = carDao.get(Tcar.class, usingcar.getCarId());
						if (7 == car.getUseType()) {// 自动档车辆，则修改自动档排班信息（该预约时间状态和标记）
							String mysql = "select * from tb_autoCarArrange where carId=? and arrangeDate=? and schoolArea=?";
							TautoCarArrange auArrange = autocarArrangeDao.getSingleBySQL(TautoCarArrange.class, mysql,
									usingcar.getCarId(), reservation.getDate(), usingcar.getSchoolArea());
							if (null != auArrange) {
								switch (fieldFlag) {
								case 7:
									auArrange.setItemTime7(1);// 预约状态为'可约'
									auArrange.setItemTime7Flag(0);// 标记为可约
									break;
								case 8:
									auArrange.setItemTime8(1);// 预约状态为'可约'
									auArrange.setItemTime8Flag(0);// 标记为可约
									break;
								case 9:
									auArrange.setItemTime9(1);// 预约状态为'可约'
									auArrange.setItemTime9Flag(0);// 标记为可约
									break;
								case 10:
									auArrange.setItemTime10(1);// 预约状态为'可约'
									auArrange.setItemTime10Flag(0);// 标记为可约
									break;
								case 11:
									auArrange.setItemTime11(1);// 预约状态为'可约'
									auArrange.setItemTime11Flag(0);// 标记为可约
									break;
								case 12:
									auArrange.setItemTime12(1);// 预约状态为'可约'
									auArrange.setItemTime12Flag(0);// 标记为可约
									break;
								case 13:
									auArrange.setItemTime13(1);// 预约状态为'可约'
									auArrange.setItemTime13Flag(0);// 标记为可约
									break;
								case 14:
									auArrange.setItemTime14(1);// 预约状态为'可约'
									auArrange.setItemTime14Flag(0);// 标记为可约
									break;
								case 15:
									auArrange.setItemTime15(1);// 预约状态为'可约'
									auArrange.setItemTime15Flag(0);// 标记为可约
									break;
								case 16:
									auArrange.setItemTime16(1);// 预约状态为'可约'
									auArrange.setItemTime16Flag(0);// 标记为可约
									break;
								case 17:
									auArrange.setItemTime17(1);// 预约状态为'可约'
									auArrange.setItemTime17Flag(0);// 标记为可约
									break;
								case 18:
									auArrange.setItemTime18(1);// 预约状态为'可约'
									auArrange.setItemTime18Flag(0);// 标记为可约
									break;
								case 19:
									auArrange.setItemTime19(1);// 预约状态为'可约'
									auArrange.setItemTime19Flag(0);// 标记为可约
									break;
								case 20:
									auArrange.setItemTime20(1);// 预约状态为'可约'
									auArrange.setItemTime20Flag(0);// 标记为可约
									break;
								case 21:
									auArrange.setItemTime21(1);// 预约状态为'可约'
									auArrange.setItemTime21Flag(0);// 标记为可约
									break;
								}
								autocarArrangeDao.update(auArrange);
							}
						}
						usingCarDao.delete(usingcar);
					}

					// 4.调用DAO层删除数据
					reservationDao.delete(reservation);

					// 取消预约后，如果是今天的预约。则发送提醒信息给教练员
					String resDay = Util.getDateStr(reservation.getDate().getTime(), "yyyy-MM-dd");
					String nowDay = Util.getDateStr(new Date().getTime(), "yyyy-MM-dd");
					if (nowDay.compareTo(resDay) == 0) {
						// 如果取消的是今天的预约
						String trainerId = reservation.getTrainer().getId();
						if (Util.isNotNull(trainerId)) {
							Tstudent tstudent = reservation.getStudent();
							String str = "学员[%s]已取消了您今天%d点时段的预约,请登录微信端查看最新预约情况！";
							String text = String.format(str, tstudent != null ? tstudent.getName() : "未知", fieldFlag);
							TweixinUser wUser = wUserService.getByTrainerId(trainerId);
							if (wUser != null) {
								Weixin weixin = new WeixinUtil().getWeixinByOrganizationId(wUser.getOrganizationId());
								WeixinMessageService sm = new WeixinMessageService(weixin);
								sm.postCustomServiceMessage(ServletActionContext.getServletContext(), wUser.getOpenId(),
										text);
							}
						}
					}

				}
			}

		}

	}

	@Override
	public DataGrid dataGrid(Reservation reservation) {
		DataGrid dataGrid = new DataGrid();
		// 设置返回前台的数据
		dataGrid.setRows(this.changedModel(this.find(reservation)));
		// 设置总记录数
		dataGrid.setTotal(this.total(reservation));
		return dataGrid;
	}

	/**
	 * 从数据库存中查找出该实体信息并将此实体类信息转换成action专用的实体类信息，即模型转换
	 * 
	 * @param students
	 * @return
	 */
	private List<Reservation> changedModel(List<Treservation> reservationLists) {
		List<Reservation> retDriverLicenses = new ArrayList<Reservation>();
		if (reservationLists != null && reservationLists.size() > 0) {
			for (Treservation reservation : reservationLists) {
				Reservation entry = new Reservation();
				BeanUtils.copyProperties(reservation, entry);
				// 给页面展现学员属性信息
				Tstudent student = studentDao.get(Tstudent.class, reservation.getStudent().getId());
				entry.setStudentId(student.getId());
				entry.setStudentName(student.getName());
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
				entry.setStudentType(student.getStudentType());
				if (student.getClazz() != null) {
					entry.setStudentClazz(student.getClazz().getName());
				}
				entry.setStudentApplyType(student.getApplyType());
				entry.setStudentDriverType(student.getDriverType());
				entry.setSchoolArea(student.getOrganization().getName());
				entry.setStudentNo(student.getStudentNo());

				// 给页面展现教练信息属性信息
				Ttrainer trainer = trainerDao.get(Ttrainer.class, reservation.getTrainer().getId());
				if (trainer != null) {
					entry.setTrainerId(trainer.getId());
					entry.setTrainerName(trainer.getName());
					entry.setTrainerPhone(trainer.getPhone());
					entry.setTrainerIdentityId(trainer.getIdentity());
					entry.setTrainerPhone(trainer.getPhone());
				}
				// 展现车辆相关信息
				Tcar car = null;
				if (ValidateUtil.isValid(reservation.getCarId())) {
					car = carDao.get(Tcar.class, reservation.getCarId());
					if (car != null) {
						entry.setCarNo(car.getCarNo());
					}
				}
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
	private List<Treservation> find(Reservation driverLicense) {
		String hql = "from Treservation t where 1=1";
		List<Object> paramList = new ArrayList<Object>();
		hql = this.addWhere(driverLicense, hql, paramList);
		if (driverLicense.getSort() != null && driverLicense.getOrder() != null) {
			hql += " order by " + driverLicense.getSort() + " " + driverLicense.getOrder();
		}
		return reservationDao.find(hql, paramList, driverLicense.getPage(), driverLicense.getRows());
	}

	/**
	 * 统计符合当前查询条件的总记录数
	 * 
	 * @param student
	 * @return
	 */
	private Long total(Reservation driverLicense) {
		// 拼接查询条件
		String hql = "select count(*) from Treservation t where 1=1 ";
		List<Object> paramsList = new ArrayList<Object>();
		hql = this.addWhere(driverLicense, hql, paramsList);
		return reservationDao.count(hql, paramsList);
	}

	/**
	 * 添加查询约束条件
	 * 
	 * @param reservation
	 * @param hql
	 * @param params
	 * @return
	 */
	private String addWhere(Reservation reservation, String hql, List<Object> params) {
		// 姓名模糊查询
		if (ValidateUtil.isValid(reservation.getStudentName())) {
			hql += " and t.student.name like ?";
			params.add("%%" + reservation.getStudentName().trim() + "%%");
		}
		if (ValidateUtil.isValid(reservation.getTrainerName())) {
			hql += " and t.trainer.name like ?";
			params.add("%%" + reservation.getTrainerName().trim() + "%%");
		}
		// 身份证精确查询
		if (ValidateUtil.isValid(reservation.getStudentIdentityId())) {
			hql += " and t.student.identityId =?";
			params.add(reservation.getStudentIdentityId().trim());
		}
		if (ValidateUtil.isValid(reservation.getTrainerIdentityId())) {
			hql += " and t.trainer.identity =?";
			params.add(reservation.getTrainerIdentityId().trim());
		}
		// 所属校区精确查询
		if (ValidateUtil.isValid(reservation.getSchoolArea())) {
			hql += " and t.schoolArea =?";
			params.add(reservation.getSchoolArea().trim());
		}

		// 预约日期
		if (reservation.getDateStart() != null) {
			hql += " and t.date >=?";
			params.add(reservation.getDateStart());
		}
		if (reservation.getDateEnd() != null) {
			hql += " and t.date <=?";
			params.add(reservation.getDateEnd());
		}

		// 编号查询
		if (ValidateUtil.isValid(reservation.getStudentNo())) {
			hql += " and t.student.studentNo like ?";
			params.add("%%" + reservation.getStudentNo().trim() + "%%");
		}

		// 预约状态查询
		if (reservation.getReservationState() != null && reservation.getReservationState() > 0) {
			hql += " and t.reservationState=?";
			params.add(reservation.getReservationState());
		}
		if (reservation.getReservationType() != null && reservation.getReservationType() > 0) {
			hql += " and t.reservationType=?";
			params.add(reservation.getReservationType());
		}

		return hql;
	}

	@Override
	public List<Treservation> listByStudent(String studentId) {
		String sql = "select * from tb_reservations where studentId=? and date>=? and reservationState <>'4' order by date asc";
		Date date = new Date();
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		List<Treservation> reservations = reservationDao.findAllBySQL(Treservation.class, sql, studentId, date);
		if (ValidateUtil.isValidListObject(reservations)) {

			return reservations;
		}
		return null;
	}

	@Override
	public Integer cancel(String reservationId, Integer invokeFlag) throws Exception {
		if (!reservationId.trim().equals("0")) {
			// 1.根据ID从数据库中查找reservation对象数据
			Treservation reservation = reservationDao.get(Treservation.class, reservationId);
			// 微信取消取约时间限制百色校区提前一天
			if (1 == invokeFlag) {
				Date date = new Date();
				Date reservationDate = reservation.getDate();
				if (Torganization.BS_SCHOOLAREA.equals(reservation.getSchoolArea())) {
					int now = date.getDay();
					int reDate = reservationDate.getDay();
					if (reDate - now <= 0) {
						return -1;//
					}
				}
			}
			// 2.清空教练明细信息中的该学员预约信息
			TtrainerReservationDetail trainerReservationDetail = reservationDetailDao
					.get(TtrainerReservationDetail.class, reservation.getTrainerReservationDetailId());
			Integer fieldFlag = reservation.getFieldOptionFlag();
			switch (fieldFlag) {
			case 7: {
				trainerReservationDetail.setItem7Studentid(null);
				trainerReservationDetail.setReservationFieldId7(null);
			}
				break;
			case 8: {
				trainerReservationDetail.setItem8Studentid(null);
				trainerReservationDetail.setReservationFieldId8(null);
			}
				break;
			case 9: {
				trainerReservationDetail.setItem9Studentid(null);
				trainerReservationDetail.setReservationFieldId9(null);
			}
				break;
			case 10: {
				trainerReservationDetail.setItem10Studentid(null);
				trainerReservationDetail.setReservationFieldId10(null);
			}
				break;
			case 11: {
				trainerReservationDetail.setItem11Studentid(null);
				trainerReservationDetail.setReservationFieldId11(null);
			}
				break;
			case 12: {
				trainerReservationDetail.setItem12Studentid(null);
				trainerReservationDetail.setReservationFieldId12(null);
			}
				break;
			case 13: {
				trainerReservationDetail.setItem13Studentid(null);
				trainerReservationDetail.setReservationFieldId13(null);
			}
				break;
			case 14: {
				trainerReservationDetail.setItem14Studentid(null);
				trainerReservationDetail.setReservationFieldId14(null);
			}
				break;
			case 15: {
				trainerReservationDetail.setItem15Studentid(null);
				trainerReservationDetail.setReservationFieldId15(null);
			}
				break;
			case 16: {
				trainerReservationDetail.setItem16Studentid(null);
				trainerReservationDetail.setReservationFieldId16(null);
			}
				break;
			case 17: {
				trainerReservationDetail.setItem17Studentid(null);
				trainerReservationDetail.setReservationFieldId17(null);
			}
				break;
			case 18: {
				trainerReservationDetail.setItem18Studentid(null);
				trainerReservationDetail.setReservationFieldId18(null);
			}
				break;
			case 19: {
				trainerReservationDetail.setItem19Studentid(null);
				trainerReservationDetail.setReservationFieldId19(null);
			}
				break;
			case 20: {
				trainerReservationDetail.setItem20Studentid(null);
				trainerReservationDetail.setReservationFieldId20(null);
			}
				break;
			case 21: {
				trainerReservationDetail.setItem21Studentid(null);
				trainerReservationDetail.setReservationFieldId21(null);
			}
				break;
			}
			reservationDetailDao.update(trainerReservationDetail);

			// 3. 修改学员的可预约学时
			// 删除预约状态为预约中…，正常，挂起预约信息时，修改学员可预约学时，+1
			Ttiming timing = null;
			Tstudent student = null;
			if (reservation.getStudent() != null) {
				student = reservation.getStudent();
				if (student.getTiming() != null) {
					timing = student.getTiming();
					if (Tstudent.TIMING_COUNTING == student.getTimingFlag()) {
						// 1. 如果学员当前可预约学时为0,删除当前预约后，可用学员加1时，学员有资格参加预约
						if (timing.getReservationTiming() == 0) {
							student.setRestTiming(1);
							studentDao.update(student);
						}
						// 2.修改学员
						timing.setReservationTiming(timing.getReservationTiming() + 1);
						timing.setRestTiming(timing.getRestTiming() + 1);
					}

					// 删除学时明细信息
					String sqlPTM = "select * from tb_personalTimings where timingId=?";
					List<TpersonalTiming> personalTingList = personalTimingDao.findAllBySQL(TpersonalTiming.class,
							sqlPTM, timing.getId());
					if (ValidateUtil.isValid(personalTingList)) {
						personalTimingDao.delete(personalTingList.get(0));
					}
					// 3.修改学员学时信息
					timingDao.update(timing);
				}
			}
			// 删除与此条预约有关的车辆使用信息
			String hql = "from TusingCar t where t.reservationId=?";
			TusingCar usingcar = (TusingCar) usingCarDao.uniqueResult(hql, reservationId);
			if (usingcar != null) {
				Tcar car = carDao.get(Tcar.class, usingcar.getCarId());
				if (7 == car.getUseType()) {// 自动档车辆，则修改自动档排班信息（该预约时间状态和标记）
					String sql = "select * from tb_autoCarArrange where carId=? and arrangeDate=? and schoolArea=?";
					List<TautoCarArrange> rets = autocarArrangeDao.findAllBySQL(TautoCarArrange.class, sql,
							usingcar.getCarId(), reservation.getDate(), usingcar.getSchoolArea());
					if (rets != null && rets.size() > 0) {
						TautoCarArrange auArrange = rets.get(0);
						if (auArrange != null) {
							switch (fieldFlag) {
							case 7:
								auArrange.setItemTime7(1);// 预约状态为'已约'
								auArrange.setItemTime7Flag(0);// 标记为已经有约
								break;
							case 8:
								auArrange.setItemTime8(1);// 预约状态为'已约'
								auArrange.setItemTime8Flag(0);// 标记为已经有约
								break;
							case 9:
								auArrange.setItemTime9(1);// 预约状态为'已约'
								auArrange.setItemTime9Flag(0);// 标记为已经有约
								break;
							case 10:
								auArrange.setItemTime10(1);// 预约状态为'已约'
								auArrange.setItemTime10Flag(0);// 标记为已经有约
								break;
							case 11:
								auArrange.setItemTime11(1);// 预约状态为'已约'
								auArrange.setItemTime11Flag(0);// 标记为已经有约
								break;
							case 12:
								auArrange.setItemTime12(1);// 预约状态为'已约'
								auArrange.setItemTime12Flag(0);// 标记为已经有约
								break;
							case 13:
								auArrange.setItemTime13(1);// 预约状态为'已约'
								auArrange.setItemTime13Flag(0);// 标记为已经有约
								break;
							case 14:
								auArrange.setItemTime14(1);// 预约状态为'已约'
								auArrange.setItemTime14Flag(0);// 标记为已经有约
								break;
							case 15:
								auArrange.setItemTime15(1);// 预约状态为'已约'
								auArrange.setItemTime15Flag(0);// 标记为已经有约
								break;
							case 16:
								auArrange.setItemTime16(1);// 预约状态为'已约'
								auArrange.setItemTime16Flag(0);// 标记为已经有约
								break;
							case 17:
								auArrange.setItemTime17(1);// 预约状态为'已约'
								auArrange.setItemTime17Flag(0);// 标记为已经有约
								break;
							case 18:
								auArrange.setItemTime18(1);// 预约状态为'已约'
								auArrange.setItemTime18Flag(0);// 标记为已经有约
								break;
							case 19:
								auArrange.setItemTime19(1);// 预约状态为'已约'
								auArrange.setItemTime19Flag(0);// 标记为已经有约
								break;
							case 20:
								auArrange.setItemTime20(1);// 预约状态为'已约'
								auArrange.setItemTime20Flag(0);// 标记为已经有约
								break;
							case 21:
								auArrange.setItemTime21(1);// 预约状态为'已约'
								auArrange.setItemTime21Flag(0);// 标记为已经有约
								break;
							}
							autocarArrangeDao.update(auArrange);
						}
					}
				}
				usingCarDao.delete(usingcar);
			}

			reservation.setReservationState(Treservation.RESERVATIONSTATE_CANCEL);// 修改预约状态
			// 4.调用DAO层删除数据
			reservation.setCancelFlag(Treservation.CANCELFLAG_TRUE);// 标记预约
			if (1 == invokeFlag) {
				reservation.setOperator(student.getName());
			}
			reservationDao.update(reservation);

			// 取消预约后，如果是今天的预约。则发送提醒信息给教练员
			String resDay = Util.getDateStr(reservation.getDate().getTime(), "yyyy-MM-dd");
			String nowDay = Util.getDateStr(new Date().getTime(), "yyyy-MM-dd");
			if (nowDay.compareTo(resDay) == 0) {
				// 如果取消的是今天的预约
				String trainerId = reservation.getTrainer().getId();
				if (Util.isNotNull(trainerId)) {
					Tstudent tstudent = reservation.getStudent();
					String str = "学员[%s]已取消了您今天%d点时段的预约,请登录微信端查看最新预约情况！";
					String text = String.format(str, tstudent != null ? tstudent.getName() : "未知", fieldFlag);
					TweixinUser wUser = wUserService.getByTrainerId(trainerId);
					if (wUser != null) {
						Weixin weixin = new WeixinUtil().getWeixinByOrganizationId(wUser.getOrganizationId());
						WeixinMessageService sm = new WeixinMessageService(weixin);
						sm.postCustomServiceMessage(ServletActionContext.getServletContext(), wUser.getOpenId(), text);
					}
				}
			}

		}
		return 1;
	}

	// 确认预约记录生效
	public void confirmReservation(String reservationId, Integer confirmFlag) throws Exception {
		Treservation reservation = null;
		TusingCar usingCar = null;
		if (ValidateUtil.isValid(reservationId)) {
			reservation = reservationDao.get(Treservation.class, reservationId);
			if (confirmFlag > 0) {
				// 单击学员确认
				if (confirmFlag == 1) {
					reservation.setStudentConfirm(1);// 确认状态
					reservation.setReservationState(Treservation.RESERVATIONSTATE_CONFIRMING);
					if (reservation.getTrainerConfirm() != null && reservation.getTrainerConfirm() == 1) {
						reservation.setReservationState(Treservation.RESERVATIONSTATE_FINISH);
						// 预约成功后更新学时明细信息，该学员使用学时加１
						updatePersonalTiming(reservation);
					}
				} else if (confirmFlag == 2) {// 教练确认事件
					reservation.setTrainerConfirm(1);
					reservation.setReservationState(Treservation.RESERVATIONSTATE_CONFIRMING);
					if (reservation.getStudentConfirm() != null && reservation.getStudentConfirm() == 1) {
						reservation.setReservationState(Treservation.RESERVATIONSTATE_FINISH);
						// 预约成功后更新学时明细信息，该学员使用学时加１
						updatePersonalTiming(reservation);
					}
				}
				reservationDao.update(reservation);
			}
			// 修改车辆预约状态为完成
			String hql = "from TusingCar t where t.reservationId=?";
			usingCar = (TusingCar) usingCarDao.uniqueResult(hql, reservationId);
			if (usingCar != null) {
				usingCar.setUsingcarState(Treservation.RESERVATIONSTATE_FINISH);
				usingCarDao.update(usingCar);
			}
		}
	}

	private void updatePersonalTiming(Treservation reservation) {
		// 1获取学员信息
		Tstudent student = null;
		if (reservation.getStudent() != null) {
			student = reservation.getStudent();
		}

		// 2.获取教练信息
		Ttrainer trainer = null;
		if (reservation.getTrainer() != null) {
			trainer = reservation.getTrainer();
		}

		// 根据学员预约成功后，修改学员的学时计时
		TpersonalTiming personTiming = new TpersonalTiming();
		personTiming.setId(reservation.getId());
		personTiming.setSchoolArea(reservation.getSchoolArea());
		personTiming.setUseTiming(1);
		personTiming.setTimingType(3);
		personTiming.setNumbering(GeneraterIdByDateUtil.getGeneraterIdByDate());
		personTiming.setDate(reservation.getDate());
		personTiming.setTimingType(1);// 学时类型为消费
		personTiming.setTimingUsingType(reservation.getReservationType());// 学时用途
		personTiming.setCreateWay(1);// 预约方式创建
		personTiming.setOperator(reservation.getOperator());// 操作员

		if (trainer != null) {
			personTiming.setTrainer(trainer);
		}

		Ttiming timing = null;
		if (student != null) {
			// 创建当前预约信息时，学员可预约学时减一，
			timing = student.getTiming();
			// 预约成功则根据预约类型修改学员学时属性
			if (reservation.getReservationType() == Ttiming.USINGTYPE_TRAMCAR) {
				if (timing.getTramTiming() == null) {
					timing.setTramTiming(1);
				}
				timing.setTramTiming(timing.getTramTiming() + 1);
			} else if (reservation.getReservationType() == Ttiming.USINGTYPE_SIMULATION) {
				if (timing.getSimulationTiming() == null) {
					timing.setSimulationTiming(1);
				}
				timing.setSimulationTiming(timing.getSimulationTiming() + 1);
			} else if (reservation.getReservationType() == Ttiming.USINGTYPE_FIVEITERM
					|| reservation.getReservationType() == Ttiming.USINGTYPE_ROADTRAINING) {
				if (timing.getCarTiming() == null) {
					timing.setCarTiming(1);
				}
				timing.setCarTiming(timing.getCarTiming() + 1);

			}
			// 已使用学时+1
			if (timing.getUsingTiming() == null) {
				timing.setUsingTiming(1);
			}
			timing.setUsingTiming(timing.getUsingTiming() + 1);

			// 剩余学时减1
			if (timing.getRestTiming() != null) {
				timing.setRestTiming(timing.getRestTiming() - 1);
			}

			personTiming.setTiming(student.getTiming());

		}
		// 根据用户操作列值修改其列相应的学员标识，预约类型
		if (reservation.getFieldOptionFlag() > 0) {
			if (reservation.getFieldOptionFlag() == 7) {
				// 用预约标识作为学员由预约创建的学员个人学时明细信息标识，区别于其它方式创建的预约明细记录
				personTiming.setTimeItem("7:00-8:00");
			} else if (reservation.getFieldOptionFlag() == 8) {
				// 用预约标识作为学员由预约创建的学员个人学时明细信息标识，区别于其它方式创建的预约明细记录
				personTiming.setTimeItem("8:00-9:00");
			} else if (reservation.getFieldOptionFlag() == 9) {
				personTiming.setTimeItem("9:00-10:00");
			} else if (reservation.getFieldOptionFlag() == 10) {
				personTiming.setTimeItem("10:00-11:00");
			} else if (reservation.getFieldOptionFlag() == 11) {
				personTiming.setTimeItem("11:00-12:00");
			} else if (reservation.getFieldOptionFlag() == 12) {
				personTiming.setTimeItem("12:00-13:00");
			} else if (reservation.getFieldOptionFlag() == 13) {
				personTiming.setTimeItem("13:00-14:00");
			} else if (reservation.getFieldOptionFlag() == 14) {
				personTiming.setTimeItem("14:00-15:00");
			} else if (reservation.getFieldOptionFlag() == 15) {
				personTiming.setTimeItem("15:00-16:00");
			} else if (reservation.getFieldOptionFlag() == 16) {
				personTiming.setTimeItem("16:00-17:00");
			} else if (reservation.getFieldOptionFlag() == 17) {
				personTiming.setTimeItem("17:00-18:00");
			} else if (reservation.getFieldOptionFlag() == 18) {
				personTiming.setTimeItem("18:00-19:00");
			} else if (reservation.getFieldOptionFlag() == 19) {
				personTiming.setTimeItem("19:00-20:00");
			} else if (reservation.getFieldOptionFlag() == 20) {
				personTiming.setTimeItem("20:00-21:00");
			} else if (reservation.getFieldOptionFlag() == 21) {
				personTiming.setTimeItem("21:00-22:00");
			}
		}

		personTiming.setStudentId(student.getId());

		studentDao.update(student);
		timingDao.update(timing);
		personalTimingDao.saveOrUpdate(personTiming);
	}

	private void addPersonalTiming(Reservation reservation, Ttiming timing, Ttrainer trainer, Tstudent student) {
		TpersonalTiming personTiming = new TpersonalTiming();
		personTiming.setId(UUID.randomUUID().toString());
		personTiming.setSchoolArea(reservation.getSchoolArea());
		personTiming.setUseTiming(1);
		personTiming.setTimingType(3);
		personTiming.setNumbering(GeneraterIdByDateUtil.getGeneraterIdByDate());
		personTiming.setDate(reservation.getDate());
		personTiming.setTimingType(1);// 学时类型为消费
		personTiming.setTimingUsingType(reservation.getReservationType());// 学时用途
		personTiming.setCreateWay(1);// 预约方式创建
		personTiming.setOperator(reservation.getOperator());// 操作员
		personTiming.setTrainer(trainer);
		personTiming.setStudentId(student.getId());
		// 根据用户操作列值修改其列相应的学员标识，预约类型
		if (reservation.getFieldOptionFlag() > 0) {
			if (reservation.getFieldOptionFlag() == 7) {
				// 用预约标识作为学员由预约创建的学员个人学时明细信息标识，区别于其它方式创建的预约明细记录
				personTiming.setTimeItem("7:00-8:00");
			} else if (reservation.getFieldOptionFlag() == 8) {
				// 用预约标识作为学员由预约创建的学员个人学时明细信息标识，区别于其它方式创建的预约明细记录
				personTiming.setTimeItem("8:00-9:00");
			} else if (reservation.getFieldOptionFlag() == 9) {
				personTiming.setTimeItem("9:00-10:00");
			} else if (reservation.getFieldOptionFlag() == 10) {
				personTiming.setTimeItem("10:00-11:00");
			} else if (reservation.getFieldOptionFlag() == 11) {
				personTiming.setTimeItem("11:00-12:00");
			} else if (reservation.getFieldOptionFlag() == 12) {
				personTiming.setTimeItem("12:00-13:00");
			} else if (reservation.getFieldOptionFlag() == 13) {
				personTiming.setTimeItem("13:00-14:00");
			} else if (reservation.getFieldOptionFlag() == 14) {
				personTiming.setTimeItem("14:00-15:00");
			} else if (reservation.getFieldOptionFlag() == 15) {
				personTiming.setTimeItem("15:00-16:00");
			} else if (reservation.getFieldOptionFlag() == 16) {
				personTiming.setTimeItem("16:00-17:00");
			} else if (reservation.getFieldOptionFlag() == 17) {
				personTiming.setTimeItem("17:00-18:00");
			} else if (reservation.getFieldOptionFlag() == 18) {
				personTiming.setTimeItem("18:00-19:00");
			} else if (reservation.getFieldOptionFlag() == 19) {
				personTiming.setTimeItem("19:00-20:00");
			} else if (reservation.getFieldOptionFlag() == 20) {
				personTiming.setTimeItem("20:00-21:00");
			} else if (reservation.getFieldOptionFlag() == 21) {
				personTiming.setTimeItem("21:00-22:00");
			}
		}
		personTiming.setTiming(timing);
		personalTimingDao.save(personTiming);
	}

	public boolean reservationExit(String studentId, Date date, Integer fieldOptionFlag, String schoolArea)
			throws Exception {
		String sql = "select * from tb_reservations where studentId=? and date=? and fieldOptionFlag=? and schoolArea=? and reservationState <>4";
		List<Treservation> rets = reservationDao.findAllBySQL(Treservation.class, sql, studentId, date, fieldOptionFlag,
				schoolArea);
		if (rets != null && rets.size() > 0) {
			return true;
		}
		return false;
	}

	public boolean checkExit(String studentId, Date date, Integer fieldOptionFlag, String schoolArea,
			Integer reservationType) throws Exception {
		String sql = "select * from tb_reservations where studentId=? and date=? and fieldOptionFlag=? "
				+ "and schoolArea=? and reservationType=?";
		List<Treservation> rets = reservationDao.findAllBySQL(Treservation.class, sql, studentId, date, fieldOptionFlag,
				schoolArea, reservationType);
		if (rets != null && rets.size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判斷學員預約在一天內是否達到上限（五項、路訓：2小時，電車模擬：3小時）
	 * 
	 * @param itemStudentid
	 * @param reservationDate
	 * @return
	 */
	public boolean checkTotalByDate(String studentId, Date reservationDate, Integer reservationType) throws Exception {
		Tstudent student = studentDao.get(Tstudent.class, studentId);
		String sql = "select count(*) from tb_reservations  where studentId= ? and date =?  and reservationState <>4";
		Long reVals = reservationDao.countBySQL(sql, studentId, reservationDate);
		if (reservationType == 1) {// 电车2人/小时
			if (reVals != null && reVals >= 3) {
				return true;
			}
		} else if (reservationType == 2) {// 模拟6人/小时
			if (reVals != null && reVals >= 2) {
				return true;
			}
		} else {// 五项、路训 一天最多2个小时
			// 西林校区一天只能预约一个小时
			if (Torganization.XL_SCHOOLAREA.equals(student.getOrganization().getId())) {
				if (reVals != null && reVals >= 1) {
					return true;
				}
			} else {
				if (reVals != null && reVals >= 2) {
					return true;
				}
			}

		}
		return false;
	}

	/**
	 * 限制新学员预约 （百色校区：新学员允许预约电车，模块，及5个小时的五项，科目成绩合格后才能正常预约）
	 * 
	 * @param studentId
	 * @param reservationType
	 * @return -1:表示参数无效，-2:达到预约限制5小时，-3：不允许科目一不合格的学员预约路训 ，1：解除限制
	 * @throws Exception
	 */
	public Integer limitNewStudentReservation(String studentId, Integer reservationType) throws Exception {
		if (!ValidateUtil.isValid(studentId) || !ValidateUtil.isValid(reservationType)) {
			return -1;
		}

		Tstudent student = studentDao.get(Tstudent.class, studentId);
		// 获取学员科目考试成绩
		String sqlExam = "select * from tb_studentExams where studentId=?";
		TstudentExam studentExam = studentExamDao.getSingleBySQL(TstudentExam.class, sqlExam, studentId);
		if (null != studentExam) {
			if (ValidateUtil.isValid(studentExam.getSub1Score())) {
				if (1 == studentExam.getSub1Score()) {
					return 1;// 如果科目一合格后，解除限限制
				}
			}
			if (ValidateUtil.isValid(studentExam.getSub1MakeupScore())) {
				if (1 == studentExam.getSub1MakeupScore()) {
					return 1;// 如果科目一合格后，解除限限制
				}
			}
		}
		// 科目成绩没有合格
		if (4 == reservationType) {// 不允许预约路训
			return -3;
		}
		String sql = "select count(*) from tb_reservations  where studentId= ? and schoolArea=? and reservationType= 3";
		Long reVals = reservationDao.countBySQL(sql, studentId, student.getOrganization().getId());

		if (Torganization.BS_SCHOOLAREA.equals(student.getOrganization().getId())) {
			if (reVals != null && reVals >= 5) {
				return -2;
			} else {
				return 1;
			}
		}
		return 0;
	}

	public List<Integer> getStateByReservationDetailId(TrainerReservationDetail detail) {
		if (detail == null) {
			return null;
		}
		List<Integer> retVal = new ArrayList<Integer>();
		if (detail.getReservationFieldId7() != null
				&& reservationDao.get(Treservation.class, detail.getReservationFieldId7()) != null) {
			retVal.add(reservationDao.get(Treservation.class, detail.getReservationFieldId7()).getReservationState());
		} else {
			retVal.add(0);
		}
		if (detail.getReservationFieldId8() != null
				&& reservationDao.get(Treservation.class, detail.getReservationFieldId8()) != null) {
			retVal.add(reservationDao.get(Treservation.class, detail.getReservationFieldId8()).getReservationState());
		} else {
			retVal.add(0);
		}
		if (detail.getReservationFieldId9() != null
				&& reservationDao.get(Treservation.class, detail.getReservationFieldId9()) != null) {
			retVal.add(reservationDao.get(Treservation.class, detail.getReservationFieldId9()).getReservationState());
		} else {
			retVal.add(0);
		}
		if (detail.getReservationFieldId10() != null
				&& reservationDao.get(Treservation.class, detail.getReservationFieldId10()) != null) {
			retVal.add(reservationDao.get(Treservation.class, detail.getReservationFieldId10()).getReservationState());
		} else {
			retVal.add(0);
		}
		if (detail.getReservationFieldId11() != null
				&& reservationDao.get(Treservation.class, detail.getReservationFieldId11()) != null) {
			retVal.add(reservationDao.get(Treservation.class, detail.getReservationFieldId11()).getReservationState());
		} else {
			retVal.add(0);
		}
		if (detail.getReservationFieldId12() != null
				&& reservationDao.get(Treservation.class, detail.getReservationFieldId12()) != null) {
			retVal.add(reservationDao.get(Treservation.class, detail.getReservationFieldId12()).getReservationState());
		} else {
			retVal.add(0);
		}
		if (detail.getReservationFieldId13() != null
				&& reservationDao.get(Treservation.class, detail.getReservationFieldId13()) != null) {
			retVal.add(reservationDao.get(Treservation.class, detail.getReservationFieldId13()).getReservationState());
		} else {
			retVal.add(0);
		}
		if (detail.getReservationFieldId14() != null
				&& reservationDao.get(Treservation.class, detail.getReservationFieldId14()) != null) {
			retVal.add(reservationDao.get(Treservation.class, detail.getReservationFieldId14()).getReservationState());
		} else {
			retVal.add(0);
		}
		if (detail.getReservationFieldId15() != null
				&& reservationDao.get(Treservation.class, detail.getReservationFieldId15()) != null) {
			retVal.add(reservationDao.get(Treservation.class, detail.getReservationFieldId15()).getReservationState());
		} else {
			retVal.add(0);
		}
		if (detail.getReservationFieldId16() != null
				&& reservationDao.get(Treservation.class, detail.getReservationFieldId16()) != null) {
			retVal.add(reservationDao.get(Treservation.class, detail.getReservationFieldId16()).getReservationState());
		} else {
			retVal.add(0);
		}
		if (detail.getReservationFieldId17() != null
				&& reservationDao.get(Treservation.class, detail.getReservationFieldId17()) != null) {
			retVal.add(reservationDao.get(Treservation.class, detail.getReservationFieldId17()).getReservationState());
		} else {
			retVal.add(0);
		}
		if (detail.getReservationFieldId18() != null
				&& reservationDao.get(Treservation.class, detail.getReservationFieldId18()) != null) {
			retVal.add(reservationDao.get(Treservation.class, detail.getReservationFieldId18()).getReservationState());
		} else {
			retVal.add(0);
		}
		if (detail.getReservationFieldId19() != null
				&& reservationDao.get(Treservation.class, detail.getReservationFieldId19()) != null) {
			retVal.add(reservationDao.get(Treservation.class, detail.getReservationFieldId19()).getReservationState());
		} else {
			retVal.add(0);
		}
		if (detail.getReservationFieldId20() != null
				&& reservationDao.get(Treservation.class, detail.getReservationFieldId20()) != null) {
			retVal.add(reservationDao.get(Treservation.class, detail.getReservationFieldId20()).getReservationState());
		} else {
			retVal.add(0);
		}
		if (detail.getReservationFieldId21() != null
				&& reservationDao.get(Treservation.class, detail.getReservationFieldId21()) != null) {
			retVal.add(reservationDao.get(Treservation.class, detail.getReservationFieldId21()).getReservationState());
		} else {
			retVal.add(0);
		}
		return retVal;
	}

	// 按日，周，月，年统计预约总数（总体分析统计）
	public List<ComboboxJson> getReservationCoutByDateType(Integer type, String sql, Integer functionFlag,
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
			} else if (firstDateFlay == Tuser.FIRSTDATE_OF_NEXTWEEK) {// 下一周
				firstDate = dateMaps.get("firstDateOfNextWeek");
				lastDate = dateMaps.get("lastDateOfNextWeek");
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
				json.setValue(reservationDao.countBySQL(sql, comboboxJson.getValue(), firstDate, lastDate).toString());
			} else if (Tuser.FUNCTION_SUM == functionFlag) {
				json.setValue(reservationDao.columSum(sql, comboboxJson.getValue(), firstDate, lastDate).toString());
			}

			retVal.add(json);
		}

		if (retVal != null && retVal.size() > 0) {
			return retVal;
		}
		return null;
	}

	// 按周详细统计本周内每一天的学员报名数量
	public Map<String, Map<String, Integer>> getReservationCountByOrgIds(Integer weekFlag) throws ParseException {

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
		} else if (weekFlag == 1) {// 下周
			firstDateOfWeek = dateMaps.get("firstDateOfNextWeek");
			secondDateOfWeek = dateMaps.get("secondDateOfNextWeek");
			thirdDateOfWeek = dateMaps.get("thirdDateOfNextWeek");
			fourthDateOfWeek = dateMaps.get("fourthDateOfNextWeek");
			fifthDateOfWeek = dateMaps.get("fifthDateOfNextWeek");
			sixthDateOfWeek = dateMaps.get("sixthDateOfNextWeek");
			lastDateOfWeek = dateMaps.get("lastDateOfNextWeek");
		}

		Map<String, Map<String, Integer>> retMap = new HashMap<String, Map<String, Integer>>(0);
		Map<String, Integer> totalMap = null;
		// 3.根据校区统计学员人数
		for (ComboboxJson comboboxJson : orgs) {
			// 3.1根据校区按日统计
			String dateSql = "select count(*) from tb_reservations where schoolArea=? and date=? and reservationState<>4";
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

	public boolean getUnfinishedReservation(String schoolArea, String trainerId) throws ParseException {
		if (!ValidateUtil.isValid(schoolArea) || !ValidateUtil.isValid(trainerId)) {
			return false;
		}
		Date date = new Date();
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		String sql = "select count(*) from tb_reservations where schoolArea=? and trainerId=? and date>=?";
		Long total = reservationDao.countBySQL(sql, schoolArea, trainerId, date);
		if (total != null && total > 0) {
			return true;
		}
		return false;
	}

	private void addCarUsering(Ttrainer trainer, Integer feildOptionFlag, String reservationId, Date reservationDate,
			Integer driverType, String carId, Treservation reservation) {
		if (trainer == null || carId == null) {
			return;
		}
		// 1.数据模型转换
		TusingCar usingCar = new TusingCar();
		usingCar.setId(UUID.randomUUID().toString());
		usingCar.setNumbering(GeneraterIdByDateUtil.getGeneraterIdByDate());
		usingCar.setUsingcarState(1);// 车辆使用状态与预约状态相同

		// 车辆使用与车的关联
		String hql = "from Tcar t where t.trainer.id=?";
		Tcar car = null;
		if (7 != driverType) {// 非自动档车已经与教练捆绑，从教练中获取
			car = (Tcar) carDao.uniqueResult(hql, trainer.getId());
		} else {// 自动档车辆，根据用户选择车辆查找预约车辆
			car = carDao.get(Tcar.class, carId);
			// 修改自动档车辆的使用状态
			if (car != null) {
				String sql = "select * from tb_autoCarArrange where carId=? and arrangeDate=? and schoolArea=?";
				List<TautoCarArrange> autoCarArranges = autocarArrangeDao.findAllBySQL(TautoCarArrange.class, sql,
						carId, reservationDate, trainer.getSchoolArea());
				if (ValidateUtil.isValidListObject(autoCarArranges)) {
					TautoCarArrange auArrange = autoCarArranges.get(0);
					if (auArrange != null) {
						switch (feildOptionFlag) {
						case 7:
							auArrange.setItemTime7(3);// 预约状态为'已约'
							auArrange.setItemTime7Flag(1);// 标记为已经有约
							break;
						case 8:
							auArrange.setItemTime8(3);// 预约状态为'已约'
							auArrange.setItemTime8Flag(1);// 标记为已经有约
							break;
						case 9:
							auArrange.setItemTime9(3);// 预约状态为'已约'
							auArrange.setItemTime9Flag(1);// 标记为已经有约
							break;
						case 10:
							auArrange.setItemTime10(3);// 预约状态为'已约'
							auArrange.setItemTime10Flag(1);// 标记为已经有约
							break;
						case 11:
							auArrange.setItemTime11(3);// 预约状态为'已约'
							auArrange.setItemTime11Flag(1);// 标记为已经有约
							break;
						case 12:
							auArrange.setItemTime12(3);// 预约状态为'已约'
							auArrange.setItemTime12Flag(1);// 标记为已经有约
							break;
						case 13:
							auArrange.setItemTime13(3);// 预约状态为'已约'
							auArrange.setItemTime13Flag(1);// 标记为已经有约
							break;
						case 14:
							auArrange.setItemTime14(3);// 预约状态为'已约'
							auArrange.setItemTime14Flag(1);// 标记为已经有约
							break;
						case 15:
							auArrange.setItemTime15(3);// 预约状态为'已约'
							auArrange.setItemTime15Flag(1);// 标记为已经有约
							break;
						case 16:
							auArrange.setItemTime16(3);// 预约状态为'已约'
							auArrange.setItemTime16Flag(1);// 标记为已经有约
							break;
						case 17:
							auArrange.setItemTime17(3);// 预约状态为'已约'
							auArrange.setItemTime17Flag(1);// 标记为已经有约
							break;
						case 18:
							auArrange.setItemTime18(3);// 预约状态为'已约'
							auArrange.setItemTime18Flag(1);// 标记为已经有约
							break;
						case 19:
							auArrange.setItemTime19(3);// 预约状态为'已约'
							auArrange.setItemTime19Flag(1);// 标记为已经有约
							break;
						case 20:
							auArrange.setItemTime20(3);// 预约状态为'已约'
							auArrange.setItemTime20Flag(1);// 标记为已经有约
							break;
						case 21:
							auArrange.setItemTime21(3);// 预约状态为'已约'
							auArrange.setItemTime21Flag(1);// 标记为已经有约
							break;
						}
						autocarArrangeDao.update(auArrange);
					}
				}
			}

		}

		TcarusingTemp carusingTemp = null;
		if (car != null) {
			usingCar.setCarId(car.getId());
			reservation.setCarId(car.getId());
			String carHql = "from TcarusingTemp t where t.car.id=?";
			carusingTemp = (TcarusingTemp) carusingTempDao.uniqueResult(carHql, car.getId());
		}
		if (carusingTemp != null) {
			usingCar.setUsingcarTemp(carusingTemp);
		}
		// 车辆使用与教练的关联
		usingCar.setTrainer(trainer);
		// 车辆使用时间段
		switch (feildOptionFlag) {
		case 7:
			usingCar.setStartTime("7:00");
			usingCar.setEndTime("8:00");
			break;
		case 8:
			usingCar.setStartTime("8:00");
			usingCar.setEndTime("9:00");
			break;
		case 9:
			usingCar.setStartTime("9:00");
			usingCar.setEndTime("10:00");
			break;
		case 10:
			usingCar.setStartTime("10:00");
			usingCar.setEndTime("11:00");
			break;
		case 11:
			usingCar.setStartTime("11:00");
			usingCar.setEndTime("12:00");
			break;
		case 12:
			usingCar.setStartTime("12:00");
			usingCar.setEndTime("13:00");
			break;
		case 13:
			usingCar.setStartTime("13:00");
			usingCar.setEndTime("14:00");
			break;
		case 14:
			usingCar.setStartTime("14:00");
			usingCar.setEndTime("15:00");
			break;
		case 15:
			usingCar.setStartTime("15:00");
			usingCar.setEndTime("16:00");
			break;
		case 16:
			usingCar.setStartTime("16:00");
			usingCar.setEndTime("17:00");
			break;
		case 17:
			usingCar.setStartTime("17:00");
			usingCar.setEndTime("18:00");
			break;
		case 18:
			usingCar.setStartTime("18:00");
			usingCar.setEndTime("19:00");
			break;
		case 19:
			usingCar.setStartTime("19:00");
			usingCar.setEndTime("20:00");
			break;
		case 20:
			usingCar.setStartTime("20:00");
			usingCar.setEndTime("21:00");
			break;
		case 21:
			usingCar.setStartTime("21:00");
			usingCar.setEndTime("22:00");
			break;
		}
		reservation.setCarId(car.getId());
		usingCar.setReason("车辆预约");
		usingCar.setUsingDate(reservationDate);
		usingCar.setReservationId(reservationId);
		usingCar.setSchoolArea(trainer.getSchoolArea());
		usingCarDao.save(usingCar);
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

	private void configTrainerReservationDetail(Reservation reservation, Tstudent student,
			TtrainerReservationDetail trainerRservationDetail, String reservationId) {
		if (reservation.getFieldOptionFlag() == 7) {
			trainerRservationDetail.setItem7Studentid(student.getId());
			trainerRservationDetail.setItem7TrainContent(reservation.getItem7TrainContent());
			// 该教练预约明细时段没有预约，则添加
			trainerRservationDetail.setReservationFieldId7(reservationId);
			// 如果预约类型为电车、模拟则修改相应时段允许的预约数
			if (reservation.getReservationType() == 1 || reservation.getReservationType() == 2) {
				trainerRservationDetail.setReservation7MaxTotal(trainerRservationDetail.getReservation7MaxTotal() + 1);
			} else {
				trainerRservationDetail.setReservation7MaxTotal(trainerRservationDetail.getReservation7MaxTotal());
			}
		} else if (reservation.getFieldOptionFlag() == 8) {
			trainerRservationDetail.setItem8Studentid(student.getId());
			trainerRservationDetail.setItem8TrainContent(reservation.getItem8TrainContent());
			// 该教练预约明细时段没有预约，则添加
			trainerRservationDetail.setReservationFieldId8(reservationId);
			// 如果预约类型为电车、模拟则修改相应时段允许的预约数
			if (reservation.getReservationType() == 1 || reservation.getReservationType() == 2) {
				trainerRservationDetail.setReservation8MaxTotal(trainerRservationDetail.getReservation8MaxTotal() + 1);
			} else {
				trainerRservationDetail.setReservation8MaxTotal(trainerRservationDetail.getReservation8MaxTotal());
			}
		} else if (reservation.getFieldOptionFlag() == 9) {
			trainerRservationDetail.setItem9Studentid(student.getId());
			trainerRservationDetail.setItem9TrainContent(reservation.getItem9TrainContent());
			trainerRservationDetail.setReservationFieldId9(reservationId);
			// 如果预约类型为电车、模拟则修改相应时段允许的预约数
			if (reservation.getReservationType() == 1 || reservation.getReservationType() == 2) {
				trainerRservationDetail.setReservation9MaxTotal(trainerRservationDetail.getReservation9MaxTotal() + 1);
			} else {
				trainerRservationDetail.setReservation9MaxTotal(trainerRservationDetail.getReservation9MaxTotal());
			}
		} else if (reservation.getFieldOptionFlag() == 10) {
			// 校验该学员是否在同一时段内与别的教练存在预约
			trainerRservationDetail.setItem10Studentid(student.getId());
			trainerRservationDetail.setItem10TrainContent(reservation.getItem10TrainContent());
			trainerRservationDetail.setReservationFieldId10(reservationId);
			// 如果预约类型为电车、模拟则修改相应时段允许的预约数
			if (reservation.getReservationType() == 1 || reservation.getReservationType() == 2) {
				trainerRservationDetail
						.setReservation10MaxTotal(trainerRservationDetail.getReservation10MaxTotal() + 1);
			} else {
				trainerRservationDetail.setReservation10MaxTotal(trainerRservationDetail.getReservation10MaxTotal());
			}
		} else if (reservation.getFieldOptionFlag() == 11) {
			trainerRservationDetail.setItem11Studentid(student.getId());
			trainerRservationDetail.setItem11TrainContent(reservation.getItem11TrainContent());
			trainerRservationDetail.setReservationFieldId11(reservationId);
			// 如果预约类型为电车、模拟则修改相应时段允许的预约数
			if (reservation.getReservationType() == 1 || reservation.getReservationType() == 2) {
				trainerRservationDetail
						.setReservation11MaxTotal(trainerRservationDetail.getReservation11MaxTotal() + 1);
			} else {
				trainerRservationDetail.setReservation11MaxTotal(trainerRservationDetail.getReservation11MaxTotal());
			}
		} else if (reservation.getFieldOptionFlag() == 12) {
			trainerRservationDetail.setItem12Studentid(student.getId());
			trainerRservationDetail.setItem12TrainContent(reservation.getItem12TrainContent());
			trainerRservationDetail.setReservationFieldId12(reservationId);
			// 如果预约类型为电车、模拟则修改相应时段允许的预约数
			if (reservation.getReservationType() == 1 || reservation.getReservationType() == 2) {
				trainerRservationDetail
						.setReservation12MaxTotal(trainerRservationDetail.getReservation12MaxTotal() + 1);
			} else {
				trainerRservationDetail.setReservation12MaxTotal(trainerRservationDetail.getReservation12MaxTotal());
			}
		} else if (reservation.getFieldOptionFlag() == 13) {
			trainerRservationDetail.setItem13Studentid(student.getId());
			trainerRservationDetail.setItem13TrainContent(reservation.getItem13TrainContent());
			trainerRservationDetail.setReservationFieldId13(reservationId);
			// 如果预约类型为电车、模拟则修改相应时段允许的预约数
			if (reservation.getReservationType() == 1 || reservation.getReservationType() == 2) {
				trainerRservationDetail
						.setReservation13MaxTotal(trainerRservationDetail.getReservation13MaxTotal() + 1);
			} else {
				trainerRservationDetail.setReservation13MaxTotal(trainerRservationDetail.getReservation13MaxTotal());
			}
		} else if (reservation.getFieldOptionFlag() == 14) {
			// 校验该学员是否在同一时段内与别的教练存在预约
			trainerRservationDetail.setItem14Studentid(student.getId());
			trainerRservationDetail.setItem14TrainContent(reservation.getItem14TrainContent());
			trainerRservationDetail.setReservationFieldId14(reservationId);
			// 如果预约类型为电车、模拟则修改相应时段允许的预约数
			if (reservation.getReservationType() == 1 || reservation.getReservationType() == 2) {
				trainerRservationDetail
						.setReservation14MaxTotal(trainerRservationDetail.getReservation14MaxTotal() + 1);
			} else {
				trainerRservationDetail.setReservation14MaxTotal(trainerRservationDetail.getReservation14MaxTotal());
			}
		} else if (reservation.getFieldOptionFlag() == 15) {
			// 校验该学员是否在同一时段内与别的教练存在预约
			trainerRservationDetail.setItem15Studentid(student.getId());
			trainerRservationDetail.setItem15TrainContent(reservation.getItem15TrainContent());
			trainerRservationDetail.setReservationFieldId15(reservationId);
			// 如果预约类型为电车、模拟则修改相应时段允许的预约数
			if (reservation.getReservationType() == 1 || reservation.getReservationType() == 2) {
				trainerRservationDetail
						.setReservation15MaxTotal(trainerRservationDetail.getReservation15MaxTotal() + 1);
			} else {
				trainerRservationDetail.setReservation15MaxTotal(trainerRservationDetail.getReservation15MaxTotal());
			}

		} else if (reservation.getFieldOptionFlag() == 16) {
			// 校验该学员是否在同一时段内与别的教练存在预约
			trainerRservationDetail.setItem16Studentid(student.getId());
			trainerRservationDetail.setItem16TrainContent(reservation.getItem16TrainContent());
			trainerRservationDetail.setReservationFieldId16(reservationId);
			// 如果预约类型为电车、模拟则修改相应时段允许的预约数
			if (reservation.getReservationType() == 1 || reservation.getReservationType() == 2) {
				trainerRservationDetail
						.setReservation16MaxTotal(trainerRservationDetail.getReservation16MaxTotal() + 1);
			} else {
				trainerRservationDetail.setReservation16MaxTotal(trainerRservationDetail.getReservation16MaxTotal());
			}
		} else if (reservation.getFieldOptionFlag() == 17) {
			trainerRservationDetail.setItem17Studentid(student.getId());
			trainerRservationDetail.setItem17TrainContent(reservation.getItem17TrainContent());
			trainerRservationDetail.setReservationFieldId17(reservationId);
			// 如果预约类型为电车、模拟则修改相应时段允许的预约数
			if (reservation.getReservationType() == 1 || reservation.getReservationType() == 2) {
				trainerRservationDetail
						.setReservation17MaxTotal(trainerRservationDetail.getReservation17MaxTotal() + 1);
			} else {
				trainerRservationDetail.setReservation17MaxTotal(trainerRservationDetail.getReservation17MaxTotal());
			}
		} else if (reservation.getFieldOptionFlag() == 18) {
			trainerRservationDetail.setItem18Studentid(student.getId());
			trainerRservationDetail.setItem18TrainContent(reservation.getItem18TrainContent());
			trainerRservationDetail.setReservationFieldId18(reservationId);
			// 如果预约类型为电车、模拟则修改相应时段允许的预约数
			if (reservation.getReservationType() == 1 || reservation.getReservationType() == 2) {
				trainerRservationDetail
						.setReservation18MaxTotal(trainerRservationDetail.getReservation18MaxTotal() + 1);
			} else {
				trainerRservationDetail.setReservation18MaxTotal(trainerRservationDetail.getReservation18MaxTotal());
			}
		} else if (reservation.getFieldOptionFlag() == 19) {
			trainerRservationDetail.setItem19Studentid(student.getId());
			trainerRservationDetail.setItem19TrainContent(reservation.getItem19TrainContent());
			trainerRservationDetail.setReservationFieldId19(reservationId);
			// 如果预约类型为电车、模拟则修改相应时段允许的预约数
			if (reservation.getReservationType() == 1 || reservation.getReservationType() == 2) {
				trainerRservationDetail
						.setReservation19MaxTotal(trainerRservationDetail.getReservation19MaxTotal() + 1);
			} else {
				trainerRservationDetail.setReservation19MaxTotal(trainerRservationDetail.getReservation19MaxTotal());
			}
		} else if (reservation.getFieldOptionFlag() == 20) {
			trainerRservationDetail.setItem20Studentid(student.getId());
			trainerRservationDetail.setItem20TrainContent(reservation.getItem20TrainContent());
			trainerRservationDetail.setReservationFieldId20(reservationId);
			// 如果预约类型为电车、模拟则修改相应时段允许的预约数
			if (reservation.getReservationType() == 1 || reservation.getReservationType() == 2) {
				trainerRservationDetail
						.setReservation20MaxTotal(trainerRservationDetail.getReservation20MaxTotal() + 1);
			} else {
				trainerRservationDetail.setReservation20MaxTotal(trainerRservationDetail.getReservation20MaxTotal());
			}
		} else if (reservation.getFieldOptionFlag() == 21) {
			trainerRservationDetail.setItem21Studentid(student.getId());
			trainerRservationDetail.setItem21TrainContent(reservation.getItem21TrainContent());
			trainerRservationDetail.setReservationFieldId21(reservationId);
			// 如果预约类型为电车、模拟则修改相应时段允许的预约数
			if (reservation.getReservationType() == 1 || reservation.getReservationType() == 2) {
				trainerRservationDetail
						.setReservation21MaxTotal(trainerRservationDetail.getReservation21MaxTotal() + 1);
			} else {
				trainerRservationDetail.setReservation21MaxTotal(trainerRservationDetail.getReservation21MaxTotal());
			}
		}
	}

	@Override
	public void createConfirmReservation() {

		Date date = new Date();
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		String sql = "select * from tb_reservations where date=? and reservationState <>'4'";
		List<Treservation> reservations = reservationDao.findAllBySQL(Treservation.class, sql, date);
		if (ValidateUtil.isValid(reservations)) {
			for (Treservation treservation : reservations) {
				if (Treservation.RESERVATIONSTATE_HANDUP != treservation.getReservationState()) {
					treservation.setStudentConfirm(Treservation.RESERVATIONSTATE_FINISH);
					treservation.setTrainerConfirm(Treservation.RESERVATIONSTATE_FINISH);
					treservation.setReservationState(Treservation.RESERVATIONSTATE_FINISH);
					reservationDao.update(treservation);
				}
			}

		}
	}

	// 根据预约时间查找可预约的自动档车辆信息
	private List<ComboboxJson> getAutoCarsForReservation(String schoolArea, Date arrangeDate, Integer itemTime,
			Integer invokeFlag) throws Exception {
		if (!ValidateUtil.isValid(schoolArea) || itemTime <= 0) {
			return null;
		}
		List<ComboboxJson> ret = new ArrayList<ComboboxJson>(0);
		List<ComboboxJson> wechatRets = new ArrayList<ComboboxJson>(0);
		String itemTimeFlag = null;
		switch (itemTime) {
		case 7:
			itemTimeFlag = "ItemTime7";
			break;
		case 8:
			itemTimeFlag = "ItemTime8";
			break;
		case 9:
			itemTimeFlag = "ItemTime9";
			break;
		case 10:
			itemTimeFlag = "ItemTime10";
			break;
		case 11:
			itemTimeFlag = "ItemTime11";
			break;
		case 12:
			itemTimeFlag = "ItemTime12";
			break;
		case 13:
			itemTimeFlag = "ItemTime13";
			break;
		case 14:
			itemTimeFlag = "ItemTime14";
			break;
		case 15:
			itemTimeFlag = "ItemTime15";
			break;
		case 16:
			itemTimeFlag = "ItemTime16";
			break;
		case 17:
			itemTimeFlag = "ItemTime17";
			break;
		case 18:
			itemTimeFlag = "ItemTime18";
			break;
		case 19:
			itemTimeFlag = "ItemTime19";
			break;
		case 20:
			itemTimeFlag = "ItemTime20";
			break;
		case 21:
			itemTimeFlag = "ItemTime21";
			break;
		}

		String sql = "select * from tb_autoCarArrange where schoolArea=? and arrangeDate=? and " + itemTimeFlag
				+ "=1 and " + itemTimeFlag + "Flag=0";
		List<TautoCarArrange> arranges = autoCarArrangeDao.findAllBySQL(TautoCarArrange.class, sql, schoolArea,
				arrangeDate);
		if (arranges != null && arranges.size() > 0) {
			Tcar car = null;
			ComboboxJson json = null;
			for (TautoCarArrange temp : arranges) {
				car = carDao.get(Tcar.class, temp.getCarId());
				if (car != null) {
					json = new ComboboxJson();
					json.setValue(car.getId());
					json.setText(car.getCarNo());
					ret.add(json);
					if (1 == car.getReceiveWechatFlag()) {
						wechatRets.add(json);
					}
				}
			}
		}
		// 返回结果
		if (0 == invokeFlag) {
			if (ret.size() > 0) {// 默认后台调用
				return ret;
			}
		} else if (1 == invokeFlag) {// 微信调用
			if (wechatRets.size() > 0) {
				return wechatRets;
			}
		}
		return null;
	}

	private String getContinuityCarForWechat(String schoolArea, String studentId, Date reservationDate) {
		if (!ValidateUtil.isValid(schoolArea) || !ValidateUtil.isValid(studentId)
				|| !ValidateUtil.isValid(reservationDate)) {
			return null;

		}
		String sql = "select * from tb_reservations where schoolArea=? and date=? and studentId=?";
		List<Treservation> rets = reservationDao.findAllBySQL(Treservation.class, sql, schoolArea, reservationDate,
				studentId);
		if (ValidateUtil.isValidListObject(rets)) {
			return rets.get(0).getCarId();
		}
		return null;
	}

	private boolean checkReservationExist(Reservation reservation) {
		String sql = "select count(*) from tb_reservations t where schoolArea=? and date=? and fieldOptionFlag=? and trainerId=? and reservationState <>4";
		Long total = reservationDao.countBySQL(sql, reservation.getSchoolArea(), reservation.getDate(),
				reservation.getFieldOptionFlag(), reservation.getTrainerId());
		if (total != null && total > 0) {
			return true;
		}
		return false;
	}
}

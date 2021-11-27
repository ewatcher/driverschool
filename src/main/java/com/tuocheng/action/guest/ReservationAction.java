package com.tuocheng.action.guest;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.objectweb.asm.commons.Remapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.GuestAction;
import com.tuocheng.action.base.SimpleAction;
import com.tuocheng.model.demo.Tcar;
import com.tuocheng.model.demo.Torganization;
import com.tuocheng.model.demo.Treservation;
import com.tuocheng.model.demo.Tstudent;
import com.tuocheng.model.demo.Ttrainer;
import com.tuocheng.model.demo.TtrainerReservationDetail;
import com.tuocheng.model.demo.Tuser;
import com.tuocheng.model.demo.TweixinUser;
import com.tuocheng.pageModel.base.ComboboxJson;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.base.TrainerJson;
import com.tuocheng.pageModel.demo.Assess;
import com.tuocheng.pageModel.demo.Reservation;
import com.tuocheng.pageModel.demo.Student;
import com.tuocheng.pageModel.demo.Trainer;
import com.tuocheng.pageModel.demo.TrainerArrange;
import com.tuocheng.pageModel.demo.TrainerReservationDetail;
import com.tuocheng.service.demo.AssessServiceI;
import com.tuocheng.service.demo.AutoCarArrangeServiceI;
import com.tuocheng.service.demo.CarServiceI;
import com.tuocheng.service.demo.ReservationServiceI;
import com.tuocheng.service.demo.StudentServiceI;
import com.tuocheng.service.demo.TrainerArrangeServiceI;
import com.tuocheng.service.demo.TrainerReservationDetailServiceI;
import com.tuocheng.service.demo.TrainerSerivceI;
import com.tuocheng.service.demo.WeixinUserServiceI;
import com.tuocheng.util.base.DateUtil;
import com.tuocheng.util.base.Util;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 微信预约action
 * 
 * @author 李杰
 * 
 */

@Namespace("/guest")
@Action(value = "reservationAction", results = { @Result(name = "addSuccess", location = "/m/success.jsp"),
		@Result(name = "addErr", location = "/m/error.jsp") })
public class ReservationAction extends GuestAction<Reservation> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 82347590280981L;

	/**
	 * 序列号
	 */
	private static final Logger log = Logger.getLogger(ReservationAction.class);

	private ReservationServiceI reservationService;
	private WeixinUserServiceI weixinUserService;
	private StudentServiceI studentService;
	private TrainerSerivceI trainerService;
	private TrainerReservationDetailServiceI reservationDetailService;
	private TrainerArrangeServiceI trainerArrangeService;

	@Autowired
	public void setReservationService(ReservationServiceI reservationService) {
		this.reservationService = reservationService;
	}

	@Autowired
	public void setWeixinUserService(WeixinUserServiceI weixinUserService) {
		this.weixinUserService = weixinUserService;
	}

	@Autowired
	public void setStudentService(StudentServiceI studentService) {
		this.studentService = studentService;
	}

	@Autowired
	public void setTrainerService(TrainerSerivceI trainerService) {
		this.trainerService = trainerService;
	}

	@Autowired
	public void setReservationDetailService(TrainerReservationDetailServiceI reservationDetailService) {
		this.reservationDetailService = reservationDetailService;
	}

	@Autowired
	public void setTrainerArrangeService(TrainerArrangeServiceI trainerArrangeService) {
		this.trainerArrangeService = trainerArrangeService;
	}

	/**
	 * 通过微信用户信息获取学员对应的教练列表
	 * 
	 * @throws Exception
	 */
	public void getTrainerListByWeixin() throws Exception {

		HashMap<String, Object> hm = new HashMap<String, Object>();
		HttpSession session = ServletActionContext.getRequest().getSession();
		String openId = Util.objToString(session.getAttribute("weixinOpenId"), "");
		if (Util.isNotNull(openId)) {
			TweixinUser tUser = weixinUserService.getByOpenId(openId);
			if (tUser != null && Util.isNotNull(tUser.getStudentId())) {
				Tstudent student = studentService.getSingleById(tUser.getStudentId());
				if (student != null) {
					// 1.根据student的model查找出所有符合查询条件的学员信息
					TrainerArrange trainerArr = new TrainerArrange();

					trainerArr.setTeachingType(student.getDriverType());
					// trainerArr.setWorkingType(model.getWorkingType());
					trainerArr.setLastArrangeDateStart(new Date());
					trainerArr.setSchoolArea(student.getOrganization().getId());
					if (student.getTrainer() != null) {
						trainerArr.setTrainerId(student.getTrainer().getId());
					}
					// 分页与排序属性配置
					trainerArr.setRows(model.getRows());
					trainerArr.setSort(model.getSort());
					trainerArr.setOrder(model.getOrder());
					trainerArr.setPage(model.getPage());

					DataGrid myDataGrid = trainerArrangeService.dataGrid(trainerArr);
					List<TrainerArrange> arrangeTemps = myDataGrid.getRows();

					// 3.将trainerArrage数据转换成reservation展示
					List<Ttrainer> trainerLists = new ArrayList<Ttrainer>();
					if (arrangeTemps != null && arrangeTemps.size() > 0) {
						for (TrainerArrange taTemp : arrangeTemps) {
							Ttrainer trainer = trainerService.getSingleById(taTemp.getTrainerId());
							if (trainer != null) {

								boolean exists = false; // 判断列表里是否已经存在该教练
								for (Ttrainer t : trainerLists) {
									if (t.getId().equals(trainer.getId())) {
										exists = true;
										break;
									}
								}

								// 如果不存在则添加
								if (!exists) {
									trainerLists.add(trainer);
								}
							}
						}

						hm.put("code", 1);
						hm.put("msg", "读取教练信息成功!");
						hm.put("rows", trainerLists);

					} else {
						hm.put("code", -4);
						hm.put("msg", "读取教练信息失败！");
					}
				} else {
					hm.put("code", -5);
					hm.put("msg", "读取不到学员信息！");
				}
			} else {
				hm.put("code", -2);
				hm.put("msg", "读取不到对应的微信用户信息！");
			}
		} else {
			hm.put("code", -1);
			hm.put("msg", "读取openId失败！");
		}

		super.writeJson(hm);
	}

	//
	@SuppressWarnings("unchecked")
	public void getDetailReservationByTrainerId() throws Exception {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		HttpSession session = ServletActionContext.getRequest().getSession();
		String openId = Util.objToString(session.getAttribute("weixinOpenId"), "");

		if (Util.isNotNull(openId)) {
			TweixinUser tUser = weixinUserService.getByOpenId(openId);
			if (tUser != null && Util.isNotNull(tUser.getStudentId())) {
				Tstudent student = studentService.getSingleById(tUser.getStudentId());
				if (student != null) {
					// 根据预约类型返回相应的电车，模拟教练（仅设一名教练）
					String trainerId = null;
					Ttrainer trainer = null;
					if (model.getReservationType() == 1) {// 电车
						trainer = trainerService.getSimulateOrElectricTrainer(1, student.getOrganization().getId());
						trainerId = trainer.getId();
					} else if (model.getReservationType() == 2) {// 模拟
						trainer = trainerService.getSimulateOrElectricTrainer(2, student.getOrganization().getId());
						trainerId = trainer.getId();
					} else {
						trainerId = model.getTrainerId();
					}
					if (Util.isNotNull(trainerId)) {
						hm.put("studentId", student.getId());
						hm.put("studentName", student.getName());
						hm.put("studentNo", student.getStudentNo());
						// 根据预约类型返回相应的电车，模拟教练（仅设一名教练）
						if (model.getReservationType() == 1 || model.getReservationType() == 2) {
							hm.put("trainerId", trainer.getId());
							hm.put("trainerName", trainer.getName());
						} else {
							hm.put("trainerId", trainerId);
						}

						List<TtrainerReservationDetail> tempTrainers = reservationDetailService
								.getTrainerReservationDetailByTrainerId(trainerId, DateUtil.getLocalDate(),
										DateUtil.getTowWeeks());

						// 3.将TrainerReservationDetail数据转换成Reservation展示
						List<Reservation> reservationLists = new ArrayList<Reservation>();
						if (tempTrainers != null && tempTrainers.size() > 0) {
							for (TtrainerReservationDetail reservationDetail : tempTrainers) {
								Reservation r = new Reservation();
								r.setTrainerReservationDetailId(reservationDetail.getId());
								r.setReservationDetailId(reservationDetail.getId());
								r.setReservationDate(reservationDetail.getReservationDate());
								r.setItem7Studentid(reservationDetail.getItem7Studentid());
								r.setItem7TrainContent(reservationDetail.getItem7TrainContent());
								r.setItem8Studentid(reservationDetail.getItem8Studentid());
								r.setItem8TrainContent(reservationDetail.getItem8TrainContent());
								r.setItem9Studentid(reservationDetail.getItem9Studentid());
								r.setItem9TrainContent(reservationDetail.getItem9TrainContent());
								r.setItem10Studentid(reservationDetail.getItem10Studentid());
								r.setItem10TrainContent(reservationDetail.getItem10TrainContent());
								r.setItem11Studentid(reservationDetail.getItem11Studentid());
								r.setItem11TrainContent(reservationDetail.getItem11TrainContent());
								r.setItem12Studentid(reservationDetail.getItem12Studentid());
								r.setItem12TrainContent(reservationDetail.getItem12TrainContent());
								r.setItem13Studentid(reservationDetail.getItem13Studentid());
								r.setItem13TrainContent(reservationDetail.getItem13TrainContent());
								r.setItem14Studentid(reservationDetail.getItem14Studentid());
								r.setItem14TrainContent(reservationDetail.getItem14TrainContent());
								r.setItem15Studentid(reservationDetail.getItem15Studentid());
								r.setItem15TrainContent(reservationDetail.getItem15TrainContent());
								r.setItem16Studentid(reservationDetail.getItem16Studentid());
								r.setItem16TrainContent(reservationDetail.getItem16TrainContent());
								r.setItem17Studentid(reservationDetail.getItem17Studentid());
								r.setItem17TrainContent(reservationDetail.getItem17TrainContent());
								r.setItem17Studentid(reservationDetail.getItem17Studentid());
								r.setItem18TrainContent(reservationDetail.getItem18TrainContent());
								r.setItem18Studentid(reservationDetail.getItem18Studentid());
								r.setItem19TrainContent(reservationDetail.getItem19TrainContent());
								r.setItem19Studentid(reservationDetail.getItem19Studentid());
								r.setItem20TrainContent(reservationDetail.getItem20TrainContent());
								r.setItem21Studentid(reservationDetail.getItem21Studentid());
								r.setItem21TrainContent(reservationDetail.getItem21TrainContent());

								r.setFiveItemTotal(reservationDetail.getFiveItemTotal());
								r.setRaodExamTotal(reservationDetail.getRaodExamTotal());

								r.setTrainerId(reservationDetail.getTrainerId());
								r.setTrainerReservationId(reservationDetail.getTrainerReservation().getId());
								r.setReservationFieldId7(reservationDetail.getReservationFieldId7());
								r.setReservationFieldId8(reservationDetail.getReservationFieldId8());
								r.setReservationFieldId9(reservationDetail.getReservationFieldId9());
								r.setReservationFieldId10(reservationDetail.getReservationFieldId10());
								r.setReservationFieldId11(reservationDetail.getReservationFieldId11());
								r.setReservationFieldId12(reservationDetail.getReservationFieldId12());
								r.setReservationFieldId13(reservationDetail.getReservationFieldId13());
								r.setReservationFieldId14(reservationDetail.getReservationFieldId14());
								r.setReservationFieldId15(reservationDetail.getReservationFieldId15());
								r.setReservationFieldId16(reservationDetail.getReservationFieldId16());
								r.setReservationFieldId17(reservationDetail.getReservationFieldId17());
								r.setReservationFieldId18(reservationDetail.getReservationFieldId18());
								r.setReservationFieldId19(reservationDetail.getReservationFieldId19());
								r.setReservationFieldId20(reservationDetail.getReservationFieldId20());
								r.setReservationFieldId21(reservationDetail.getReservationFieldId21());
								reservationLists.add(r);
							}

							hm.put("retData", reservationLists);

							// 获取下拉列表数据
							List<HashMap<String, Object>> dateSelect = new ArrayList<HashMap<String, Object>>();
							Calendar cal = Calendar.getInstance();
							int weekNow = cal.get(Calendar.WEEK_OF_YEAR);
							cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 0, 0, 0);

							for (int i = 0; i < 7; i++) {
								cal.add(Calendar.DAY_OF_YEAR, 1);
								HashMap<String, Object> day = new HashMap<String, Object>();
								if (i == 0) {
									day.put("key", "明天");
								} else if (i == 1) {
									day.put("key", "后天");
								} else if (i == 2) {
									day.put("key", "大后天");
								} else {
									int weekNext = cal.get(Calendar.WEEK_OF_YEAR);
									int d = cal.get(Calendar.DAY_OF_WEEK);
									String ds = "";
									switch (d) {
									case 1: {
										ds = "日";
										break;
									}
									case 2: {
										ds = "一";
										break;
									}
									case 3: {
										ds = "二";
										break;
									}
									case 4: {
										ds = "三";
										break;
									}
									case 5: {
										ds = "四";
										break;
									}
									case 6: {
										ds = "五";
										break;
									}
									case 7: {
										ds = "六";
										break;
									}
									}
									day.put("key", (weekNext > weekNow ? "下周" : "周") + ds);
								}
								day.put("date", cal.getTime());

								dateSelect.add(day);
							}

							hm.put("dateSelect", dateSelect);
							hm.put("code", 1);
							hm.put("msg", "读取教练员排班信息成功！");
						} else {
							hm.put("code", -3);
							hm.put("msg", "当前教练没有排班，无法预约！");
						}
					} else {
						hm.put("code", -4);
						hm.put("msg", "读取不到学员对应的教练员信息！");
					}
				} else {
					hm.put("code", -5);
					hm.put("msg", "读取不到学员信息！");
				}
			} else {
				hm.put("code", -2);
				hm.put("msg", "读取不到对应的微信用户信息！");
			}
		} else {
			hm.put("code", -1);
			hm.put("msg", "读取openId失败！");
		}

		super.writeJson(hm);
	}

	// 根据教练标识查找出该教练所有的预约明细信息列表
	@SuppressWarnings("unchecked")
	public void getDetailReservationToWeixin() throws Exception {
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		HttpSession session = ServletActionContext.getRequest().getSession();
		String openId = Util.objToString(session.getAttribute("weixinOpenId"), "");
		if (Util.isNotNull(openId)) {
			TweixinUser tUser = weixinUserService.getByOpenId(openId);
			if (tUser != null && Util.isNotNull(tUser.getTrainerId())) {
				Ttrainer trainer = trainerService.get(tUser.getTrainerId());
				if (trainer != null) {
					// 4.2返回三个星期日期字符串（上周，本周，下周）
					retMap.put("lastWeekDatestring", studentService.getWeekDays(3));
					retMap.put("thisWeekDatestring", studentService.getWeekDays(2));
					retMap.put("nextWeekDatestring", studentService.getWeekDays(1));
					// 4.3返回三个日期的date
					Map<String, Date> dateMap = DateUtil.getFisrtLastDateByDate(new Date());
					// 上周dates
					List<Date> datesOfLastWeek = new ArrayList<Date>();
					datesOfLastWeek.add(dateMap.get("firstDateOfLastWeek"));
					datesOfLastWeek.add(dateMap.get("secondDateOfLastWeek"));
					datesOfLastWeek.add(dateMap.get("thirdDateOfLastWeek"));
					datesOfLastWeek.add(dateMap.get("fourthDateOfLastWeek"));
					datesOfLastWeek.add(dateMap.get("fifthDateOfLastWeek"));
					datesOfLastWeek.add(dateMap.get("sixthDateOfLastWeek"));
					datesOfLastWeek.add(dateMap.get("lastDateOfLastWeek"));

					List<Date> datesOfThisWeek = new ArrayList<Date>();
					datesOfThisWeek.add(dateMap.get("firstDateOfWeek"));
					datesOfThisWeek.add(dateMap.get("secondDateOfWeek"));
					datesOfThisWeek.add(dateMap.get("thirdDateOfWeek"));
					datesOfThisWeek.add(dateMap.get("fourthDateOfWeek"));
					datesOfThisWeek.add(dateMap.get("fifthDateOfWeek"));
					datesOfThisWeek.add(dateMap.get("sixthDateOfWeek"));
					datesOfThisWeek.add(dateMap.get("lastDateOfWeek"));
					List<Date> datesOfNextWeek = new ArrayList<Date>();
					datesOfNextWeek.add(dateMap.get("firstDateOfNextWeek"));
					datesOfNextWeek.add(dateMap.get("secondDateOfNextWeek"));
					datesOfNextWeek.add(dateMap.get("thirdDateOfNextWeek"));
					datesOfNextWeek.add(dateMap.get("fourthDateOfNextWeek"));
					datesOfNextWeek.add(dateMap.get("fifthDateOfNextWeek"));
					datesOfNextWeek.add(dateMap.get("sixthDateOfNextWeek"));
					datesOfNextWeek.add(dateMap.get("lastDateOfNextWeek"));
					retMap.put("datesOfLastWeek", datesOfLastWeek);
					retMap.put("datesOfThisWeek", datesOfThisWeek);
					retMap.put("datesOfNextWeek", datesOfNextWeek);

					retMap.put("code", 1);
					retMap.put("msg", "读取教练员排班信息成功！");

				} else {
					retMap.put("code", -4);
					retMap.put("msg", "读取不到学员对应的教练员信息！");
				}
			} else {
				retMap.put("code", -2);
				retMap.put("msg", "读取不到对应的微信用户信息！");
			}
		} else {
			retMap.put("code", -1);
			retMap.put("msg", "读取openId失败！");
		}

		super.writeJson(retMap);
	}

	public void getTrainerReservationDetailForWechat() throws Exception {
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		TrainerReservationDetail retVal = null;
		HttpSession session = ServletActionContext.getRequest().getSession();
		String openId = Util.objToString(session.getAttribute("weixinOpenId"), "");
		if (Util.isNotNull(openId)) {
			TweixinUser tUser = weixinUserService.getByOpenId(openId);
			if (tUser != null && Util.isNotNull(tUser.getTrainerId())) {
				Ttrainer trainer = trainerService.get(tUser.getTrainerId());
				if (trainer != null) {
					retVal = reservationDetailService.getDetailByTrainerIdDate(trainer.getId(), model.getDate());

					if (retVal != null) {

						retMap.put("trainerName", trainer.getName());
						retMap.put("trainerReservationDetail", retVal);
						retMap.put("reservationStates", reservationService.getStateByReservationDetailId(retVal));
						retMap.put("code", 1);
					} else {
						retMap.put("code", -5);
						retMap.put("msg", "当前选择日期无预约排班！");
					}
				} else {
					retMap.put("code", -4);
					retMap.put("msg", "读取不到学员对应的教练员信息！");
				}
			} else {
				retMap.put("code", -2);
				retMap.put("msg", "读取不到对应的微信用户信息！");
			}
		} else {
			retMap.put("code", -1);
			retMap.put("msg", "读取openId失败！");
		}
		super.writeJson(retMap);
	}

	/**
	 * 增加微信学时预约信息。 1.绑定学员至微信 2.判断数据完整性 3.格式化日期，填充实体对象 4.保存
	 * 
	 * @return
	 * @throws Exception
	 */
	public void add() throws Exception {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = ServletActionContext.getRequest().getSession();
		long key1 = Util.objToLong(request.getParameter("key"), 0);
		if (key1 > 0) {
			long key2 = Util.objToLong(session.getAttribute("reservationKey"), 0);
			if (key1 == key2) {
				session.removeAttribute("reservationKey");
				String openId = Util.objToString(session.getAttribute("weixinOpenId"), "");
				if (Util.isNotNull(openId)) {
					TweixinUser tUser = weixinUserService.getByOpenId(openId); // 通过openId得到微信用户信息
					if (tUser != null && Util.isNotNull(tUser.getStudentId())) {

						if (model != null) {
							if (model.getDate() != null && Util.isNotNull(model.getTimeStart())
									&& Util.isNotNull(model.getTimeEnd()) && Util.isNotNull(model.getTrainerId())
									&& model.getReservationType() > 0 && Util.isNotNull(model.getReservationDetailId())
									&& Util.isNotNull(model.getTrainerReservationId())) {
								try {

									model.setStudentId(tUser.getStudentId());
									Tstudent student = studentService.getSingleById(tUser.getStudentId());
									// 超级管理员有查找所有校区选项
									if (null != student) {
										this.model.setSchoolArea(student.getOrganization().getId());
										model.setOperator(student.getName());
									}

									/*
									 * // 百色校区限制新学员预约 if
									 * (Torganization.BS_SCHOOLAREA.equals(
									 * student.getOrganization().getId())) {
									 * Integer retFlag = reservationService.
									 * limitNewStudentReservation(model.
									 * getStudentId(),
									 * model.getReservationType()); if (1 !=
									 * retFlag&&0!=retFlag) { if(-1==retFlag){
									 * hm.put("code", -10); hm.put("msg",
									 * "预约失败，参数无效"); }else if(-2==retFlag){
									 * hm.put("code", -11); hm.put("msg",
									 * "预约失败，科目一未合格达到预约限制5小时"); }else
									 * if(-3==retFlag){ hm.put("code", -12);
									 * hm.put("msg", "预约失败，科目一不合格不可预约路训"); }
									 * super.writeJson(hm); return ; } }
									 */
									Integer ret = reservationService.saveForWexin(model);
									if (ret != null) {
										if (ret < 0) {
											if (ret == -1) {
												hm.put("code", -9);
												hm.put("msg", "当前已经约满，稍后再试!");
											} else if (ret == -2) {
												hm.put("code", -10);
												hm.put("msg", "预约数达到上限!");
											} else if (ret == -3) {
												hm.put("code", -11);
												hm.put("msg", "预约已经存在，不可重复预约!");
											}else if(ret == -7){
												hm.put("code", -7);
												hm.put("msg", "非在培学员无法操作!");
											}
											super.writeJson(hm);
											return;
										}
										hm.put("code", 1);
										hm.put("msg", "恭喜您,预约成功!您你务必按时到场!");
									}

								} catch (Exception ex) {
									ex.printStackTrace();
									hm.put("code", -2);
									hm.put("msg", "错误:" + ex.toString());
								}
							} else {
								hm.put("code", -3);
								hm.put("msg", "表单填写不全哦，请检查重新填写哦！");
							}
						} else {
							hm.put("code", -4);
							hm.put("msg", "获取参数错误，提交失败！");
						}
					} else {
						hm.put("code", -5);
						hm.put("msg", "读取微信用户信息失败，提交失败！");
					}
				} else {
					hm.put("code", -6);
					hm.put("msg", "未通过微信授权，提交失败！");
				}
			} else {
				hm.put("code", -7);
				hm.put("msg", "验证失败，禁止重复提交！");
			}
		} else {
			hm.put("code", -8);
			hm.put("msg", "验证参数丢失！");
		}

		super.writeJson(hm);
	}

	/**
	 * 验证微信openId是否已经与学员绑定
	 */
	public void getReservationList() {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		try {
			HttpSession session = ServletActionContext.getRequest().getSession();
			String openId = Util.objToString(session.getAttribute("weixinOpenId"), "");
			if (Util.isNotNull(openId)) {
				TweixinUser tUser = weixinUserService.getByOpenId(openId);
				if (tUser != null && Util.isNotNull(tUser.getStudentId())) {
					hm.put("code", 1);
					hm.put("msg", "学员已绑定微信号！");
					List<Treservation> list = reservationService.listByStudent(tUser.getStudentId());
					List<HashMap<String, Object>> rows = new ArrayList<HashMap<String, Object>>();
					for (int i = 0; list != null && i < list.size(); i++) {
						Treservation t = list.get(i);
						HashMap<String, Object> row = new HashMap<String, Object>();
						row.put("id", t.getId());
						row.put("date", Util.getDateStr(t.getDate().getTime(), "MM月dd"));
						row.put("timeStart", t.getTimeStart());
						row.put("timeEnd", t.getTimeEnd());
						row.put("state", t.getReservationState());
						rows.add(row);
					}
					hm.put("rows", rows);
				} else {
					hm.put("code", -2);
					hm.put("msg", "该学员未绑定微信号！");
				}
			} else {
				hm.put("code", -1);
				hm.put("msg", "读取openId失败！");
			}
		} catch (Exception ex) {
			hm.put("code", -10);
			hm.put("msg", "错误:" + ex.toString());
		}
		super.writeJson(hm);
	}

	/**
	 * 
	 */
	public void delete() {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		try {
			HttpSession session = ServletActionContext.getRequest().getSession();
			HttpServletRequest request = ServletActionContext.getRequest();
			String openId = Util.objToString(session.getAttribute("weixinOpenId"), "");
			if (Util.isNotNull(openId)) {
				TweixinUser tUser = weixinUserService.getByOpenId(openId);
				if (tUser != null && Util.isNotNull(tUser.getStudentId())) {
					String id = Util.objToString(request.getParameter("id"), "");
					Integer flag=reservationService.cancel(id, 1);
					if(flag>0){
						hm.put("code", 1);
						hm.put("msg", "已取消该预约！");
					}else{
						hm.put("code", -3);
						hm.put("msg", "微信取消预约需提前一天，请与后人预约员联系！");
					}
				} else {
					hm.put("code", -2);
					hm.put("msg", "该学员未绑定微信号！");
				}
			} else {
				hm.put("code", -1);
				hm.put("msg", "读取openId失败！");
			}
		} catch (Exception ex) {
			hm.put("code", -4);
			hm.put("msg", "错误:" + ex.toString());
		}
		super.writeJson(hm);
	}

	// 将三个星期的日期以字条串形式返回给前台
	public void getWeekDates() throws ParseException {
		Map<String, List<String>> retMap = new HashMap<String, List<String>>();
		List<String> nextWeek = studentService.getWeekDays(1);
		List<String> thisWeek = studentService.getWeekDays(2);
		List<String> lastWeek = studentService.getWeekDays(3);
		retMap.put("lastWeek", lastWeek);
		retMap.put("thisWeek", thisWeek);
		retMap.put("nextWeek", nextWeek);
		super.writeJson(retMap);
	}

	public void getDatesOfWeekByMap() throws ParseException {
		Map<String, Object> retMap = new HashMap<String, Object>();
		Map<String, Date> dateMap = DateUtil.getFisrtLastDateByDate(new Date());
		List<String> nextWeek = studentService.getWeekDays(1);
		List<String> thisWeek = studentService.getWeekDays(2);
		List<String> lastWeek = studentService.getWeekDays(3);

		retMap.put("lastWeek", lastWeek);
		Map<String, Date> retMap1 = new HashMap<String, Date>();
		if (dateMap != null && dateMap.size() > 0) {
			retMap.put(lastWeek.get(0), dateMap.get("firstDateOfLastWeek"));
			retMap.put(lastWeek.get(1), dateMap.get("secondDateOfLastWeek"));
			retMap.put(lastWeek.get(2), dateMap.get("thirdDateOfLastWeek"));
			retMap.put(lastWeek.get(3), dateMap.get("fourthDateOfLastWeek"));
			retMap.put(lastWeek.get(4), dateMap.get("fifthDateOfLastWeek"));
			retMap.put(lastWeek.get(5), dateMap.get("sixthDateOfLastWeek"));
			retMap.put(lastWeek.get(6), dateMap.get("lastDateOfLastWeek"));
			retMap.put(thisWeek.get(0), dateMap.get("firstDateOfWeek"));
			retMap.put(thisWeek.get(1), dateMap.get("secondDateOfWeek"));
			retMap.put(thisWeek.get(2), dateMap.get("thirdDateOfWeek"));
			retMap.put(thisWeek.get(3), dateMap.get("fourthDateOfWeek"));
			retMap.put(thisWeek.get(4), dateMap.get("fifthDateOfWeek"));
			retMap.put(thisWeek.get(5), dateMap.get("sixthDateOfWeek"));
			retMap.put(thisWeek.get(6), dateMap.get("lastDateOfWeek"));
			retMap.put(nextWeek.get(0), dateMap.get("firstDateOfNextWeek"));
			retMap.put(nextWeek.get(1), dateMap.get("secondDateOfNextWeek"));
			retMap.put(nextWeek.get(2), dateMap.get("thirdDateOfNextWeek"));
			retMap.put(nextWeek.get(3), dateMap.get("fourthDateOfNextWeek"));
			retMap.put(nextWeek.get(4), dateMap.get("fifthDateOfNextWeek"));
			retMap.put(nextWeek.get(5), dateMap.get("sixthDateOfNextWeek"));
			retMap.put(nextWeek.get(6), dateMap.get("lastDateOfNextWeek"));
			super.writeJson(retMap);
		} else {

		}
	}

	public void getInsideStudentsForWechat() {
	}

}

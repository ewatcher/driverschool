package com.tuocheng.action.guest;

import java.text.ParseException;
import java.util.ArrayList;
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
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.GuestAction;
import com.tuocheng.model.demo.Tuser;
import com.tuocheng.model.demo.TweixinUser;
import com.tuocheng.pageModel.base.ComboboxJson;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.demo.StudentAnalysis;
import com.tuocheng.pageModel.demo.User;
import com.tuocheng.service.demo.OrganizationServiceI;
import com.tuocheng.service.demo.ReservationServiceI;
import com.tuocheng.service.demo.StudentServiceI;
import com.tuocheng.service.demo.TrainerSerivceI;
import com.tuocheng.service.demo.UserServiceI;
import com.tuocheng.service.demo.UsingCarServiceI;
import com.tuocheng.service.demo.WeixinUserServiceI;
import com.tuocheng.util.base.ExceptionUtil;
import com.tuocheng.util.base.Util;

/**
 * 微信校长端
 * 
 * @author nongfeng
 * 
 */

@Namespace("/guest")
@Action(value = "userAction", results = {
		@Result(name = "bindSuccess", location = "/m/success.jsp"),
		@Result(name = "bindErr", location = "/m/error.jsp") })
public class UserAction extends GuestAction<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 82347590280981L;

	/**
	 * 序列号
	 */
	private static final Logger log = Logger.getLogger(UserAction.class);

	private UserServiceI userService;
	private StudentServiceI studentService;
	private OrganizationServiceI organizationService;
	private WeixinUserServiceI weixinUserService;
	private ReservationServiceI reservationService;
	private UsingCarServiceI usingCarService;
	private TrainerSerivceI trainerSerivce;

	@Autowired
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}

	@Autowired
	public void setWeixinUserService(WeixinUserServiceI weixinUserService) {
		this.weixinUserService = weixinUserService;
	}

	@Autowired
	public void setOrganizationService(OrganizationServiceI organizationService) {
		this.organizationService = organizationService;
	}

	@Autowired
	public void setStudentService(StudentServiceI studentService) {
		this.studentService = studentService;
	}

	@Autowired
	public void setReservationService(ReservationServiceI reservationService) {
		this.reservationService = reservationService;
	}

	@Autowired
	public void setUsingCarService(UsingCarServiceI usingCarService) {
		this.usingCarService = usingCarService;
	}

	@Autowired
	public void setTrainerSerivce(TrainerSerivceI trainerSerivce) {
		this.trainerSerivce = trainerSerivce;
	}

	/**
	 * 绑定学员-微信用户。
	 * 
	 * @return
	 */
	public String bindStudentToOpenId() {
		String message = "";
		String redirect = "/m/bind.jsp";
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = ServletActionContext.getRequest().getSession();
		long key1 = Util.objToLong(request.getParameter("key"), 0);
		if (key1 > 0) {
			long key2 = Util.objToLong(session.getAttribute("bindKey"), 0);
			if (key1 == key2) {
				session.removeAttribute("bindKey");
				String openId = Util.objToString(
						session.getAttribute("weixinOpenId"), "");
				if (Util.isNotNull(openId) && Util.isNotNull(model.getCname())
						&& Util.isNotNull(model.getCpwd())) {
					try {
						User user = userService.getUserByNamePWD(
								model.getCname(), model.getCpwd());
						if (user != null && 4 == user.getUserType()) {
							TweixinUser tUser = weixinUserService
									.getByOpenId(openId);
							if (tUser != null) {
								tUser.setTrainerId(user.getCid());
								weixinUserService.update(tUser);
								request.setAttribute("message", "用户信息绑定成功！");
								return "bindSuccess";
							} else {
								message = "微信信息读取失败！";
							}
						} else {
							message = "您当前账号不具备校长端权限，请与校方联系！";
						}
					} catch (Exception e) {
						message = "错误:" + e.toString();
					}
				} else {
					message = "参数丢失！请查看填写是否正确！";
				}
			} else {
				message = "验证失败，禁止重复提交！";
			}
		} else {
			message = "验证参数丢失！";
		}
		request.setAttribute("message", message);
		request.setAttribute("redirect", redirect);
		return "bindErr";
	}

	/**
	 * 绑定学员-微信用户。
	 * 
	 * @return
	 */
	public void bindStudentToOpenId2() {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		HttpSession session = ServletActionContext.getRequest().getSession();
		String openId = Util.objToString(session.getAttribute("weixinOpenId"),
				"");
		System.out.println(model);
		if (Util.isNotNull(openId) && Util.isNotNull(model.getCname())
				&& Util.isNotNull(model.getCpwd())) {
			try {
				User user = userService.getUserByNamePWD(model.getCname(),
						model.getCpwd());
				if (user != null
						&& user.getUserType() == Tuser.USERTYPE_MONITOR) {
					TweixinUser tUser = weixinUserService.getByOpenId(openId);
					if (tUser != null) {
						tUser.setMonitoerId(user.getCid());
						weixinUserService.update(tUser);
						hm.put("code", 1);
						hm.put("msg", "校长已绑定微信号！");
					} else {
						hm.put("code", -1);
						hm.put("msg", "获取微信校长信息失败！");
					}
				} else {
					hm.put("code", -2);
					hm.put("msg", "用户名或密码有误，或您没有校长端的用户权限，请与管理员联系！");
				}
			} catch (Exception e) {
				hm.put("code", -3);
				hm.put("msg", "错误:" + e.toString());
			}
		} else {
			hm.put("code", -4);
			hm.put("msg", "参数丢失，绑定失败！");
		}
		super.writeJson(hm);
	}

	/**
	 * 验证微信openId是否已经与教练员绑定
	 */
	public void checkUserBind() {
		System.out.println("check user binding");
		HashMap<String, Object> hm = new HashMap<String, Object>();
		HttpSession session = ServletActionContext.getRequest().getSession();
		String openId = Util.objToString(session.getAttribute("weixinOpenId"),
				"");
		if (Util.isNotNull(openId)) {
			System.out.println("---------userAction-- openId---->" + openId);
			TweixinUser tUser = weixinUserService.getByOpenId(openId);
			if (tUser != null && Util.isNotNull(tUser.getMonitoerId())) {
				hm.put("code", 1);
				hm.put("msg", "校长已绑定微信号！");
				hm.put("studentId", tUser.getStudentId());
			} else {
				hm.put("code", -2);
				hm.put("msg", "您未绑定微信号！");
			}
		} else {
			hm.put("code", -1);
			hm.put("msg", "读取openId失败！");
		}
		super.writeJson(hm);
	}

	/**
	 * @author MEI702
	 * @throws Exception
	 */
	public void getAllOrganization() throws Exception {
		super.writeJson(organizationService.getAllOrganization());
	}

	// 按天，周，月，年统计学员报名
	public void getAllCoutByOrgs() throws ParseException {
		Map<String, List<ComboboxJson>> retMap = new HashMap<String, List<ComboboxJson>>(
				0);
		String sql = "select count(*) from tb_student where organizationId=? and createTime>=? and createTime<=?";
		retMap.put("dateCount", studentService.getStudentCoutByDateType(
				Tuser.COUNT_DAY, sql, Tuser.FUNCTION_COUNT, null, null));
		retMap.put("weekCount", studentService.getStudentCoutByDateType(
				Tuser.COUNT_WMY, sql, Tuser.FUNCTION_COUNT,
				Tuser.FIRSTDATE_OF_WEEK, Tuser.LASTDATE_OF_WEEK));
		retMap.put("monthCount", studentService.getStudentCoutByDateType(
				Tuser.COUNT_WMY, sql, Tuser.FUNCTION_COUNT,
				Tuser.FIRSTDATE_OF_MONTH, Tuser.LASTDATE_OF_MONTH));
		retMap.put("yearCount", studentService.getStudentCoutByDateType(
				Tuser.COUNT_WMY, sql, Tuser.FUNCTION_COUNT,
				Tuser.FIRSTDATE_OF_YEAR, Tuser.LASTDATE_OF_YEAR));
		super.writeJson(retMap);
	}

	public void getWeekCountByOrgs() {
		Map<String, Map<String, Integer>> retMap = new HashMap<String, Map<String, Integer>>(
				0);
		try {
			retMap = studentService.getWeekCountByOrgIds(model.getCountType());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (retMap.size() > 0) {
			super.writeJson(retMap);
		}
	}

	// 获取该星期的日期
	public void getWeekDays() {
		try {
			List<String> dates = studentService.getWeekDays(model
					.getCountType());
			if (dates != null && dates.size() > 0) {
				super.writeJson(dates);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	// 获取按周统计总数
	public void getWeekCountTotal() {
		try {
			String sql = "select count(*) from tb_student where organizationId=? and createTime>=? and createTime<=?";
			if (model.getCountType() == 2) {// 本周
				super.writeJson(studentService.getStudentCoutByDateType(
						Tuser.COUNT_WMY, sql, Tuser.FUNCTION_COUNT,
						Tuser.FIRSTDATE_OF_WEEK, Tuser.LASTDATE_OF_WEEK));
			} else if (model.getCountType() == 3) {// 上周
				super.writeJson(studentService
						.getStudentCoutByDateType(Tuser.COUNT_WMY, sql,
								Tuser.FUNCTION_COUNT,
								Tuser.FIRSTDATE_OF_LASTWEEK,
								Tuser.LASTDATE_OF_LASTWEEK));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 按日，周月年统计财务（基本统计）
	public void getBaseMoneyCount() throws ParseException {
		Map<String, List<ComboboxJson>> retMap = new HashMap<String, List<ComboboxJson>>(
				0);
		String sql = "select sum(realFeed) from tb_student where organizationId=? and createTime>=? and createTime<=?";
		retMap.put("dateCount", studentService.getStudentCoutByDateType(
				Tuser.COUNT_DAY, sql, Tuser.FUNCTION_SUM, null, null));
		retMap.put("weekCount", studentService.getStudentCoutByDateType(
				Tuser.COUNT_WMY, sql, Tuser.FUNCTION_SUM,
				Tuser.FIRSTDATE_OF_WEEK, Tuser.LASTDATE_OF_WEEK));
		retMap.put("monthCount", studentService.getStudentCoutByDateType(
				Tuser.COUNT_WMY, sql, Tuser.FUNCTION_SUM,
				Tuser.FIRSTDATE_OF_MONTH, Tuser.LASTDATE_OF_MONTH));
		retMap.put("yearCount", studentService.getStudentCoutByDateType(
				Tuser.COUNT_WMY, sql, Tuser.FUNCTION_SUM,
				Tuser.FIRSTDATE_OF_YEAR, Tuser.LASTDATE_OF_YEAR));
		super.writeJson(retMap);
	}

	// 获取按周统计(财务)周总数
	public void getMoneyWeekCountTotal() {
		try {
			String sql = "select sum(realFeed) from tb_student where organizationId=? and createTime>=? and createTime<=?";
			if (model.getCountType() == 2) {// 本周
				super.writeJson(studentService.getStudentCoutByDateType(
						Tuser.COUNT_WMY, sql, Tuser.FUNCTION_SUM,
						Tuser.FIRSTDATE_OF_WEEK, Tuser.LASTDATE_OF_WEEK));
			} else if (model.getCountType() == 3) {// 上周
				super.writeJson(studentService
						.getStudentCoutByDateType(Tuser.COUNT_WMY, sql,
								Tuser.FUNCTION_SUM,
								Tuser.FIRSTDATE_OF_LASTWEEK,
								Tuser.LASTDATE_OF_LASTWEEK));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 财务按周明细统计
	public void getMoneyWeekCountByOrgs() {
		Map<String, Map<String, Double>> retMap = new HashMap<String, Map<String, Double>>(
				0);
		try {
			retMap = studentService.getMoneyWeekCountByOrgIds(model
					.getCountType());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (retMap.size() > 0) {
			super.writeJson(retMap);
		}
	}

	// 按日，周月年统计财务（基本统计）
	public void getBaseReservationCount() throws ParseException {
		Map<String, List<ComboboxJson>> retMap = new HashMap<String, List<ComboboxJson>>(
				0);
		String sql = "select count(*) from tb_reservations where schoolArea=? and date>=? and date<=? and reservationState<>'4'";
		retMap.put("dateCount", reservationService
				.getReservationCoutByDateType(Tuser.COUNT_DAY, sql,
						Tuser.FUNCTION_COUNT, null, null));
		retMap.put("weekCount", reservationService
				.getReservationCoutByDateType(Tuser.COUNT_WMY, sql,
						Tuser.FUNCTION_COUNT, Tuser.FIRSTDATE_OF_WEEK,
						Tuser.LASTDATE_OF_WEEK));
		retMap.put("monthCount", reservationService
				.getReservationCoutByDateType(Tuser.COUNT_WMY, sql,
						Tuser.FUNCTION_COUNT, Tuser.FIRSTDATE_OF_MONTH,
						Tuser.LASTDATE_OF_MONTH));
		retMap.put("yearCount", reservationService
				.getReservationCoutByDateType(Tuser.COUNT_WMY, sql,
						Tuser.FUNCTION_COUNT, Tuser.FIRSTDATE_OF_YEAR,
						Tuser.LASTDATE_OF_YEAR));
		super.writeJson(retMap);
	}

	// 获取按周统计总数
	public void getReservationWeekCountTotal() {
		try {
			String sql = "select count(*) from tb_reservations where schoolArea=? and date>=? and date<=? and reservationState <>4";
			if (model.getCountType() == 2) {// 本周
				super.writeJson(reservationService
						.getReservationCoutByDateType(Tuser.COUNT_WMY, sql,
								Tuser.FUNCTION_COUNT, Tuser.FIRSTDATE_OF_WEEK,
								Tuser.LASTDATE_OF_WEEK));
			} else if (model.getCountType() == 3) {// 上周
				super.writeJson(reservationService
						.getReservationCoutByDateType(Tuser.COUNT_WMY, sql,
								Tuser.FUNCTION_COUNT,
								Tuser.FIRSTDATE_OF_LASTWEEK,
								Tuser.LASTDATE_OF_LASTWEEK));
			} else if (model.getCountType() == 1) {// 上周
				super.writeJson(reservationService
						.getReservationCoutByDateType(Tuser.COUNT_WMY, sql,
								Tuser.FUNCTION_COUNT,
								Tuser.FIRSTDATE_OF_NEXTWEEK,
								Tuser.LASTDATE_OF_NEXTWEEK));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 按周明细统计预约信息
	public void getReservationWeekCountByOrgs() {
		Map<String, Map<String, Integer>> retMap = new HashMap<String, Map<String, Integer>>(
				0);
		try {
			retMap = reservationService.getReservationCountByOrgIds(model
					.getCountType());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (retMap.size() > 0) {
			super.writeJson(retMap);
		}
	}

	// 按日，周月年统计财务（基本统计）
	public void getBaseCarCount() throws ParseException {
		Map<String, List<ComboboxJson>> retMap = new HashMap<String, List<ComboboxJson>>(
				0);
		String sql = "select count(*) from tb_usingcars where schoolArea=? and usingDate>=? and usingDate<=? and usingcarState<>4";
		retMap.put("dateCount", usingCarService.getCarCoutByDateType(
				Tuser.COUNT_DAY, sql, Tuser.FUNCTION_COUNT, null, null));
		retMap.put("weekCount", usingCarService.getCarCoutByDateType(
				Tuser.COUNT_WMY, sql, Tuser.FUNCTION_COUNT,
				Tuser.FIRSTDATE_OF_WEEK, Tuser.LASTDATE_OF_WEEK));
		retMap.put("monthCount", usingCarService.getCarCoutByDateType(
				Tuser.COUNT_WMY, sql, Tuser.FUNCTION_COUNT,
				Tuser.FIRSTDATE_OF_MONTH, Tuser.LASTDATE_OF_MONTH));
		retMap.put("yearCount", usingCarService.getCarCoutByDateType(
				Tuser.COUNT_WMY, sql, Tuser.FUNCTION_COUNT,
				Tuser.FIRSTDATE_OF_YEAR, Tuser.LASTDATE_OF_YEAR));
		super.writeJson(retMap);
	}

	// 获取按周统计总数
	public void getCarWeekCountTotal() {
		try {
			String sql = "select count(*) from tb_usingcars where schoolArea=? and usingDate>=? and usingDate<=? and usingcarState <>4";
			if (model.getCountType() == 2) {// 本周
				super.writeJson(usingCarService.getCarCoutByDateType(
						Tuser.COUNT_WMY, sql, Tuser.FUNCTION_COUNT,
						Tuser.FIRSTDATE_OF_WEEK, Tuser.LASTDATE_OF_WEEK));
			} else if (model.getCountType() == 3) {// 上周
				super.writeJson(usingCarService
						.getCarCoutByDateType(Tuser.COUNT_WMY, sql,
								Tuser.FUNCTION_COUNT,
								Tuser.FIRSTDATE_OF_LASTWEEK,
								Tuser.LASTDATE_OF_LASTWEEK));
			} else if (model.getCountType() == 1) {// 上周
				super.writeJson(usingCarService
						.getCarCoutByDateType(Tuser.COUNT_WMY, sql,
								Tuser.FUNCTION_COUNT,
								Tuser.FIRSTDATE_OF_NEXTWEEK,
								Tuser.LASTDATE_OF_NEXTWEEK));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 按周明细统计预约信息
	public void getCarWeekCountByOrgs() {
		Map<String, Map<String, Integer>> retMap = new HashMap<String, Map<String, Integer>>(
				0);
		try {
			retMap = usingCarService.getCarCountByOrgIds(model.getCountType());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (retMap.size() > 0) {
			super.writeJson(retMap);
		}
	}

	// 按校区标识统计各校区教练总数
	public void getTrainerTotalByOrg() {
		try {
			super.writeJson(trainerSerivce.getTtrainerTotalByOrg());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}

	// 教练数据统计信息
	public void getTtrainerDatasByOrg() {
		try {
			super.writeJson(trainerSerivce.getTtrainerDatasByOrg());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}
}

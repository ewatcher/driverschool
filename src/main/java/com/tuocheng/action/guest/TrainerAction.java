package com.tuocheng.action.guest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.GuestAction;
import com.tuocheng.model.demo.Tstudent;
import com.tuocheng.model.demo.Ttrainer;
import com.tuocheng.model.demo.TweixinUser;
import com.tuocheng.pageModel.base.TrainerJson;
import com.tuocheng.pageModel.demo.Student;
import com.tuocheng.pageModel.demo.Trainer;
import com.tuocheng.pageModel.demo.TrainerReservationDetail;
import com.tuocheng.service.demo.StudentServiceI;
import com.tuocheng.service.demo.TrainerSerivceI;
import com.tuocheng.service.demo.WeixinUserServiceI;
import com.tuocheng.util.base.Util;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 教练辅助action（避免session登录认证）
 * 
 * @author 李杰
 * 
 */

@Namespace("/guest")
@Action(value = "trainerAction", results = {
		@Result(name = "bindSuccess", location = "/m/success.jsp"),
		@Result(name = "bindErr", location = "/m/error.jsp") })
public class TrainerAction extends GuestAction<Trainer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 82347590280981L;

	/**
	 * 序列号
	 */
	private static final Logger log = Logger.getLogger(TrainerAction.class);

	private TrainerSerivceI trainerService;
	private WeixinUserServiceI weixinUserService;
	private StudentServiceI studentService;

	@Autowired
	public void setTrainerService(TrainerSerivceI trainerService) {
		this.trainerService = trainerService;
	}

	@Autowired
	public void setWeixinUserService(WeixinUserServiceI weixinUserService) {
		this.weixinUserService = weixinUserService;
	}

	@Autowired
	public void setStudentService(StudentServiceI studentService) {
		this.studentService = studentService;
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
				if (Util.isNotNull(openId) && Util.isNotNull(model.getName())
						&& Util.isNotNull(model.getIdentity())) {
					try {
						Ttrainer tTrainer = trainerService
								.getTrainerByNameAndKey(model.getName(),
										model.getIdentity());
						if (tTrainer != null) {
							TweixinUser tUser = weixinUserService
									.getByOpenId(openId);
							if (tUser != null) {
								tUser.setTrainerId(tTrainer.getId());
								weixinUserService.update(tUser);
								request.setAttribute("message", "教练员信息绑定成功！");
								return "bindSuccess";
							} else {
								message = "微信信息读取失败！";
							}
						} else {
							message = "查询不到对应的教练员信息！";
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
		if (Util.isNotNull(openId) && Util.isNotNull(model.getName().trim())
				&& Util.isNotNull(model.getIdentity().trim())) {
			try {
				Ttrainer tTrainer = trainerService.getTrainerByNameAndKey(model
						.getName().trim(), model.getIdentity().trim());
				if (tTrainer != null) {
					TweixinUser tUser = weixinUserService.getByOpenId(openId);
					if (tUser != null) {
						tUser.setTrainerId(tTrainer.getId());
						tUser.setRealName(tTrainer.getName());
						weixinUserService.update(tUser);
						hm.put("code", 1);
						hm.put("msg", "教练员已绑定微信号！");
					} else {
						hm.put("code", -1);
						hm.put("msg", "获取微信教练员信息失败！");
					}
				} else {
					hm.put("code", -2);
					hm.put("msg", "教练员姓名或教练员编号有误！");
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
	public void checkTrainerBind() {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		HttpSession session = ServletActionContext.getRequest().getSession();
		String openId = Util.objToString(session.getAttribute("weixinOpenId"),
				"");
		if (Util.isNotNull(openId)) {
			TweixinUser tUser = weixinUserService.getByOpenId(openId);
			if (tUser != null && Util.isNotNull(tUser.getTrainerId())) {
				hm.put("code", 1);
				hm.put("msg", "教练员已绑定微信号！");
				hm.put("studentId", tUser.getStudentId());
			} else {
				hm.put("code", -2);
				hm.put("msg", "该教练员未绑定微信号！");
			}
		} else {
			hm.put("code", -1);
			hm.put("msg", "读取openId失败！");
		}
		super.writeJson(hm);
	}

	/**
	 * 根据教练信息，查找出与该教练有关的学员信息
	 * 
	 * 
	 * @throws Exception
	 */
	public void getStudentsByTrainer() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		String openId = Util.objToString(session.getAttribute("weixinOpenId"),
				"");
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		if (Util.isNotNull(openId)) {
			TweixinUser tUser = weixinUserService.getByOpenId(openId);
			if (tUser != null && Util.isNotNull(tUser.getTrainerId())) {
				Ttrainer trainer = trainerService.get(tUser.getTrainerId());
				if (trainer != null) {
					try {
						List<Student> students = studentService
								.getStudentsByTrainer(trainer.getSchoolArea(),
										trainer.getId(), trainer.getTrainerType());
						retMap.put("code", 1);
						if(ValidateUtil.isValidListObject(students)){
							retMap.put("students", students);
							retMap.put("trainerName", trainer.getName());
						}else{
							retMap.put("msg", "非承包教练不能查看自已的学员信息！");
						}
					} catch (Exception e) {
						System.err.println(e);
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
}

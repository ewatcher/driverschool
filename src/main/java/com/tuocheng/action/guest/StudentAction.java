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
import com.tuocheng.model.demo.Tperson;
import com.tuocheng.model.demo.Tstudent;
import com.tuocheng.model.demo.Ttrainer;
import com.tuocheng.model.demo.TweixinUser;
import com.tuocheng.pageModel.base.ComboboxJson;
import com.tuocheng.pageModel.base.TrainerJson;
import com.tuocheng.pageModel.demo.Student;
import com.tuocheng.service.demo.StudentServiceI;
import com.tuocheng.service.demo.WeixinUserServiceI;
import com.tuocheng.util.base.Util;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 学员辅助action（避免session登录认证）
 * 
 * @author 李杰
 * 
 */

@Namespace("/guest")
@Action(value = "studentAction", results = { @Result(name = "bindSuccess", location = "/m/success.jsp"),
		@Result(name = "bindErr", location = "/m/error.jsp") })
public class StudentAction extends GuestAction<Student> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 82347590280981L;

	/**
	 * 序列号
	 */
	private static final Logger log = Logger.getLogger(StudentAction.class);

	private StudentServiceI studentService;
	private WeixinUserServiceI weixinUserService;

	@Autowired
	public void setStudentService(StudentServiceI studentService) {
		this.studentService = studentService;
	}

	@Autowired
	public void setWeixinUserService(WeixinUserServiceI weixinUserService) {
		this.weixinUserService = weixinUserService;
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
				String openId = Util.objToString(session.getAttribute("weixinOpenId"), "");
				if (Util.isNotNull(openId) && Util.isNotNull(model.getName())
						&& Util.isNotNull(model.getIdentityId())) {
					try {
						Tstudent tStudent = studentService.getStudentByNameAndKey(model.getName(),
								model.getIdentityId());
						if (tStudent != null) {
							TweixinUser tUser = weixinUserService.getByOpenId(openId);
							if (tUser != null) {
								tUser.setStudentId(tStudent.getId());
								weixinUserService.update(tUser);
								request.setAttribute("message", "学员信息绑定成功！");
								return "bindSuccess";
							} else {
								message = "微信信息读取失败！";
							}
						} else {
							message = "查询不到对应的学员信息！";
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
		String openId = Util.objToString(session.getAttribute("weixinOpenId"), "");
		if (Util.isNotNull(openId) && Util.isNotNull(model.getName()) && Util.isNotNull(model.getIdentityId())) {
			try {
				Tstudent tStudent = studentService.getStudentByNameAndKey(model.getName().trim(),
						model.getIdentityId().trim());
				if (tStudent != null) {
					TweixinUser tUser = weixinUserService.getByOpenId(openId);
					if (tUser != null) {
						if (ValidateUtil.isValid(tStudent.getVerificationCode())) {
							if (tStudent.getVerificationCode().equals(model.getVerificationCode().trim())) {
								tUser.setStudentId(tStudent.getId());// TODO
								tUser.setRealName(tStudent.getName());
								tUser.setReservationState(1);
								weixinUserService.update(tUser);
								hm.put("code", 1);
								hm.put("msg", "学员已绑定微信号！");
							} else {
								hm.put("code", -5);
								hm.put("msg", "预约验证码不正确！");
							}
						}
					} else {
						hm.put("code", -1);
						hm.put("msg", "获取微信用户信息失败！");
					}
				} else {
					hm.put("code", -2);
					hm.put("msg", "学员姓名或学身份证号有误！");
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
	 * 验证微信openId是否已经与学员绑定
	 * 
	 * @throws Exception
	 */
	public void checkUserBind() throws Exception {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		HttpSession session = ServletActionContext.getRequest().getSession();
		String openId = Util.objToString(session.getAttribute("weixinOpenId"), "");
		if (Util.isNotNull(openId)) {
			TweixinUser tUser = weixinUserService.getByOpenId(openId);
			if (tUser != null && Util.isNotNull(tUser.getStudentId())) {
				Tstudent student = studentService.getSingleById(tUser.getStudentId());
				Ttrainer trainer = null;
				if (student != null) {
					trainer = student.getTrainer();
					if (trainer != null) {
						if (ValidateUtil.isValid(tUser.getReservationState())) {
							if (1 == tUser.getReservationState()) {
								hm.put("code", 1);
								hm.put("msg", "学员已绑定微信号！");
								hm.put("studentId", student.getId());
								hm.put("trainerId", trainer.getId());
								hm.put("trainerName", trainer.getName());
							} else {
								hm.put("code", 3);
								hm.put("msg", "学员没有与教练进行捆绑，无法参与预约！");
								hm.put("studentId", student.getId());
								hm.put("trainerId", trainer.getId());
								hm.put("trainerName", trainer.getName());
							}
						}
					} else {
						hm.put("code", 2);
						hm.put("msg", "学员没有与教练进行捆绑，无法参与预约操作！");
						hm.put("studentId", tUser.getStudentId());
					}

				}
			} else {
				hm.put("code", -2);
				hm.put("msg", "该学员未绑定微信号！");
			}
		} else {
			hm.put("code", -1);
			hm.put("msg", "读取openId失败！");
		}
		super.writeJson(hm);
	}

	// 解除用记微信绑定
	public void cancelUserBind() throws Exception {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		HttpSession session = ServletActionContext.getRequest().getSession();
		String openId = Util.objToString(session.getAttribute("weixinOpenId"), "");
		if (Util.isNotNull(openId)) {
			try {
				TweixinUser tUser = weixinUserService.getByOpenId(openId);
				if (tUser != null) {
					if (ValidateUtil.isValid(tUser.getReservationState())) {
						tUser.setReservationState(0);
						weixinUserService.update(tUser);
						hm.put("code", 1);
						hm.put("msg", "解除绑定微信号！");
					}
				} else {
					hm.put("code", -1);
					hm.put("msg", "获取微信用户信息失败！");
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
	 * 获取学员对应的教练信息
	 */
	public void getTrainerByStudentId() {
		List<TrainerJson> retList = new ArrayList<TrainerJson>();
		HttpSession session = ServletActionContext.getRequest().getSession();
		String openId = Util.objToString(session.getAttribute("weixinOpenId"), "");
		if (Util.isNotNull(openId)) {
			TweixinUser tUser = weixinUserService.getByOpenId(openId);
			if (tUser != null && Util.isNotNull(tUser.getStudentId())) {
				try {
					Ttrainer t = studentService.getTrainerByStudentId(tUser.getStudentId());
					TrainerJson json = new TrainerJson();
					json.setValue(t.getId());
					json.setText(t.getName());
					retList.add(json);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		super.writeJson(retList);
	}

	/**
	 * 获取学员对应的业务员信息
	 */
	public void getPersonByStudentId() {
		List<ComboboxJson> retList = new ArrayList<ComboboxJson>();
		HttpSession session = ServletActionContext.getRequest().getSession();
		String openId = Util.objToString(session.getAttribute("weixinOpenId"), "");
		if (Util.isNotNull(openId)) {
			TweixinUser tUser = weixinUserService.getByOpenId(openId);
			if (tUser != null && Util.isNotNull(tUser.getStudentId())) {
				try {
					List<Tperson> retVals = studentService.getAllPersons(tUser.getOrganizationId());
					if (retVals != null && retVals.size() > 0) {
						for (Tperson tperson : retVals) {
							ComboboxJson json = new ComboboxJson();
							json.setValue(tperson.getId());
							json.setText(tperson.getName());
							retList.add(json);
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		super.writeJson(retList);
	}
}

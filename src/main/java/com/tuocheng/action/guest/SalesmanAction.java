package com.tuocheng.action.guest;

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
import com.tuocheng.model.demo.TweixinUser;
import com.tuocheng.pageModel.base.ComboboxJson;
import com.tuocheng.pageModel.base.TrainerJson;
import com.tuocheng.pageModel.demo.Salesman;
import com.tuocheng.pageModel.demo.Difficulty;
import com.tuocheng.pageModel.demo.Salesman;
import com.tuocheng.service.demo.AssessServiceI;
import com.tuocheng.service.demo.DifficultyServiceI;
import com.tuocheng.service.demo.PersonServiceI;
import com.tuocheng.service.demo.SalesmanServiceI;
import com.tuocheng.service.demo.TrainerSerivceI;
import com.tuocheng.service.demo.WeixinUserServiceI;
import com.tuocheng.util.base.Util;

/**
 * 微信报名action
 * 
 * @author 李杰
 * 
 */

@Namespace("/guest")
@Action(value = "salesmanAction", results = { @Result(name = "addSuccess", location = "/m/success.jsp"),
		@Result(name = "addErr", location = "/m/error.jsp"), @Result(name = "bind", location = "/m/bind.jsp") })
public class SalesmanAction extends GuestAction<Salesman> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 82347590280981L;

	/**
	 * 序列号
	 */
	private static final Logger log = Logger.getLogger(SalesmanAction.class);

	private SalesmanServiceI salesmanService;
	private PersonServiceI personService;
	private WeixinUserServiceI weixinUserService;

	@Autowired
	public void setSalesmanService(SalesmanServiceI salesmanService) {
		this.salesmanService = salesmanService;
	}

	@Autowired
	public void setPersonService(PersonServiceI personService) {
		this.personService = personService;
	}

	@Autowired
	public void setWeixinUserService(WeixinUserServiceI weixinUserService) {
		this.weixinUserService = weixinUserService;
	}

	/**
	 * 增加微信难点反馈信息。
	 * 
	 * @return
	 */
	public String add() {
		String message = "";
		String redirect = "/m/salesman1.jsp";
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = ServletActionContext.getRequest().getSession();
		long key1 = Util.objToLong(request.getParameter("key"), 0);
		if (key1 > 0) {
			long key2 = Util.objToLong(session.getAttribute("assessKey"), 0);
			if (key1 == key2) {
				session.removeAttribute("assessKey");
				if (Util.isNotNull(model.getPersonId()) && Util.isNotNull(model.getEvaluate())) {
					try {
						String openId = Util.objToString(session.getAttribute("weixinOpenId"), "");
						model.setOpenId(openId);

						Salesman assess = salesmanService.save(model);
						if (assess != null) {
							TweixinUser tUser = weixinUserService.getByOpenId(openId);
							if (Util.isNotNull(tUser.getStudentId())) {
								request.setAttribute("message", "感谢您的评论！");
								return "addSuccess";
							} else {
								return "bind";
							}
						} else {
							message = "系统错误：保存失败！";
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
		return "addErr";
	}

	/**
	 * 获取教练员列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public void geTrainerList() throws Exception {
		List<ComboboxJson> list = personService.getAllPersonsForCombobox();
		super.writeJson(list);
	}
}

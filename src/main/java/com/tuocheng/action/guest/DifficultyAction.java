package com.tuocheng.action.guest;

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
import com.tuocheng.pageModel.demo.Difficulty;
import com.tuocheng.service.demo.DifficultyServiceI;
import com.tuocheng.service.demo.WeixinUserServiceI;
import com.tuocheng.util.base.Util;

/**
 * 微信报名action
 * 
 * @author 李杰
 * 
 */

@Namespace("/guest")
@Action(value = "difficultyAction", results = {
		@Result(name = "addSuccess", location = "/m/success.jsp"),
		@Result(name = "addErr", location = "/m/error.jsp"), 
		@Result(name = "bind", location = "/m/bind.jsp") } )
public class DifficultyAction extends GuestAction<Difficulty> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 82347590280981L;

	/**
	 * 序列号
	 */
	private static final Logger log = Logger.getLogger(DifficultyAction.class);

	private DifficultyServiceI difficultyService;
	private WeixinUserServiceI weixinUserService;

	@Autowired
	public void setDifficultyService(DifficultyServiceI difficultyService) {
		this.difficultyService = difficultyService;
	}
	
	@Autowired
	public void setWeixinUserService(WeixinUserServiceI weixinUserService) {
		this.weixinUserService = weixinUserService;
	}
	
	/**
	 * 增加微信难点反馈信息。
	 * @return
	 */
	public String add(){
		String message = "";
		String redirect = "/m/difficulty1.jsp";
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = ServletActionContext.getRequest().getSession();
		long key1 = Util.objToLong(request.getParameter("key"),  0);
		if(key1 > 0){
			long key2 = Util.objToLong(session.getAttribute("difficultyKey"),  0);
			if(key1 == key2){
				session.removeAttribute("difficultyKey");
				if(Util.isNotNull(model.getDifficulty())){
					try {
						String openId = Util.objToString(session.getAttribute("weixinOpenId"), "");
						model.setOpenId(openId);
						Difficulty difficulty = difficultyService.save(model);
						if(difficulty != null){
							TweixinUser tUser = weixinUserService.getByOpenId(openId);
							if(Util.isNotNull(tUser.getStudentId())){
								request.setAttribute("message", "感谢您的反馈！");
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
}

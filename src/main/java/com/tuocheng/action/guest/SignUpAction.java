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
import com.tuocheng.pageModel.demo.SignUp;
import com.tuocheng.service.demo.SignUpServiceI;
import com.tuocheng.util.base.Util;

/**
 * 微信报名action
 * 
 * @author 李杰
 * 
 */

@Namespace("/guest")
@Action(value = "signUpAction", results = {
		@Result(name = "addSuccess", location = "/m/success.jsp"),
		@Result(name = "addErr", location = "/m/error.jsp")} )
public class SignUpAction extends GuestAction<SignUp> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 82347590280981L;

	/**
	 * 序列号
	 */
	private static final Logger log = Logger.getLogger(SignUpAction.class);

	private SignUpServiceI signUpService;

	@Autowired
	public void setSignUpService(SignUpServiceI signUpService) {
		this.signUpService = signUpService;
	}
	
	/**
	 * 增加微信用户报名信息。
	 * @return
	 */
	public String add(){
		String message = "";
		String redirect = "/m/signup.jsp";
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = ServletActionContext.getRequest().getSession();
		long key1 = Util.objToLong(request.getParameter("key"),  0);
		if(key1 > 0){
			long key2 = Util.objToLong(session.getAttribute("signupKey"),  0);
			if(key1 == key2){
				session.removeAttribute("signupKey");
				if(Util.isNotNull(model.getUpMobile()) && Util.isNotNull(model.getUpName())){
					try {
						SignUp signUp = signUpService.save(model);
						if(signUp != null){
							request.setAttribute("message", "稍后我们的工作人员会与您联系");
							return "addSuccess";
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
	* @Description: 推销系统开放接口，学员报名
	* @Author: 农峰
	* @Date: create in 2021/12/2 下午3:21
	* @Param [] 推销系统
	* @return java.lang.String
	*
	*/
	public String wechatadd(){
		String message = "";
		String redirect = "/m/signup.jsp";
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = ServletActionContext.getRequest().getSession();
		if(Util.isNotNull(model.getUpMobile()) && Util.isNotNull(model.getUpName())){
			try {
				SignUp signUp = signUpService.save(model);
				if(signUp != null){
					System.out.println("-----------api:"+signUp.toString());
					return "addSuccess";
				}
			} catch (Exception e) {
				message = "错误:" + e.toString();
			}
		} else {
			message = "参数丢失！请查看填写是否正确！";
		}
		request.setAttribute("message", message);
		request.setAttribute("redirect", redirect);
		return "addErr";
	}
}

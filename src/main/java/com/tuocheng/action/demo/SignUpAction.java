package com.tuocheng.action.demo;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.BaseAction;
import com.tuocheng.action.base.GuestAction;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.demo.SignUp;
import com.tuocheng.service.demo.SignUpServiceI;
import com.tuocheng.util.base.ExceptionUtil;
import com.tuocheng.util.base.Util;

/**
 * 微信报名action
 * 
 * @author 李杰
 * 
 */

@Namespace("/demo")
@Action(value = "signUpAction", results = {
		@Result(name = "signUpList", location = "/demo/weixin/signUp.jsp") })
public class SignUpAction extends BaseAction<SignUp> {

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

	// 到微信用户列表页面
	public String signUpList() {
		return "signUpList";
	}

	// 从后台中查找出所有实体类信息
	public void datagrid() {
		
		if (Util.isNotNull(user.getSchoolArea())) {
			model.setOrganizationId(user.getSchoolArea());
		} 
		
		model.setSort("upTime");
		model.setOrder("desc");
		super.writeJson(signUpService.dataGrid(model));
	}

	// 更新实体类信息
	public void edit() {
		Json json = new Json();
		try {
			SignUp signUp = signUpService.update(model);
			json.setSuccess(true);
			json.setMsg("修改数据成功！");
			json.setObj(signUp);
		} catch (Exception e) {
			json.setMsg("修改数据失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		// 将数据返回给前台
		super.writeJson(json);
	}

	// 删除实体类信息
	public void delete() {
		Json json = new Json();
		try {
			signUpService.delete(model.getIds());
			json.setSuccess(true);
			json.setMsg("删除数据成功！");
		} catch (Exception e) {
			json.setMsg("删除数据失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

}

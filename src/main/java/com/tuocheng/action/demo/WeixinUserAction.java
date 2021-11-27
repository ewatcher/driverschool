package com.tuocheng.action.demo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.BaseAction;
import com.tuocheng.model.demo.TweixinMessage;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.demo.WeixinUser;
import com.tuocheng.service.demo.WeixinMessageServiceI;
import com.tuocheng.service.demo.WeixinUserServiceI;
import com.tuocheng.util.base.ExceptionUtil;
import com.tuocheng.util.base.Util;

/**
 * 微信用户action
 * 
 * @author 李杰
 * 
 */

@Namespace("/demo")
@Action(value = "weixinUserAction", results = {
		@Result(name = "userList", location = "/demo/weixin/user.jsp"),
		@Result(name = "message", location = "/demo/weixin/message.jsp") })
public class WeixinUserAction extends BaseAction<WeixinUser> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 82347590280981L;

	/**
	 * 序列号
	 */
	private static final Logger log = Logger.getLogger(WeixinUserAction.class);

	private WeixinUserServiceI weixinUserService;
	private WeixinMessageServiceI weixinMessageService;

	@Autowired
	public void setWeixinUserService(WeixinUserServiceI weixinUserService) {
		this.weixinUserService = weixinUserService;
	}

	@Autowired
	public void setWeixinMessageService(
			WeixinMessageServiceI weixinMessageService) {
		this.weixinMessageService = weixinMessageService;
	}

	// 到微信用户列表页面
	public String userList() {
		return "userList";
	}

	public String message() {
		return "message";
	}

	// 从后台中查找出所有实体类信息
	public void datagrid() {

		if (Util.isNotNull(user.getSchoolArea())) {
			model.setOrganizationId(user.getSchoolArea());
		}

		super.writeJson(weixinUserService.dataGrid(model));
	}

	public void getMessageListByOpenId() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String openId = Util.objToString(request.getParameter("openId"), "");
		if (Util.isNotNull(openId)) {
			List<TweixinMessage> list = weixinMessageService
					.listByOpendId(openId);
			super.writeJson(list);
		}
	}

	// 删除实体类信息
	public void delete() {
		Json json = new Json();
		try {
			weixinUserService.delete(model.getIds());
			json.setSuccess(true);
			json.setMsg("删除数据成功！");
		} catch (Exception e) {
			json.setMsg("删除数据失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

}

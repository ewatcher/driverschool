package com.tuocheng.action.demo;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.BaseAction;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.demo.SMS;
import com.tuocheng.service.demo.OrganizationServiceI;
import com.tuocheng.service.demo.SMSServiceI;
import com.tuocheng.util.base.ExceptionUtil;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 短信管理action
 * 
 * @author MEI702
 * 
 */
@Namespace("/demo")
@Action(value = "smsAction", results = { @Result(name = "sms", location = "/demo/sms/sms.jsp"),
		@Result(name = "smsAdd", location = "/demo/sms/smsAdd.jsp"),
		@Result(name = "smsEdit", location = "/demo/sms/smsEdit.jsp"), })
public class SMSAction extends BaseAction<SMS> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2331052199665339728L;

	private static final Logger logger = Logger.getLogger(SMSAction.class);

	private SMSServiceI smsService;
	private OrganizationServiceI organizationService;

	@Autowired
	public void setSmsService(SMSServiceI smsService) {
		this.smsService = smsService;
	}

	@Autowired
	public void setOrganizationService(OrganizationServiceI organizationService) {
		this.organizationService = organizationService;
	}

	// 到达短信管理主页面
	public String toSMSPage() {
		return "sms";
	}

	// 到达添加短信页面
	public String toSMSAddPage() {
		return "smsAdd";
	}

	// 到达编辑页面
	public String toSMSEditPage() {
		return "smsEdit";
	}

	public void datagrid() throws Exception {
		// 超级管理员有查找所有校区选项
		if (ValidateUtil.isValid(user.getSchoolArea())) {
			this.model.setSchoolArea(user.getSchoolArea());
		}
		super.writeJson(smsService.dataGrid(model));
	}

	// 添加信息
	public void add() {
		Json json = new Json();
		try {
			if (ValidateUtil.isValid(user.getSchoolArea())) {
				this.model.setSchoolArea(user.getSchoolArea());
			}
			model.setOperator(user.getCname().trim());
			SMS sms = smsService.save(model);
			json.setMsg("添加短信信息成功！");
			json.setSuccess(true);
			json.setObj(sms);
		} catch (Exception e) {
			json.setMsg("添加短信信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 编辑信息
	public void edit() {
		Json json = new Json();
		try {
			SMS sms = smsService.udpate(model);
			json.setMsg("编辑短信信息成功！");
			json.setSuccess(true);
			json.setObj(sms);
		} catch (Exception e) {
			json.setMsg("编辑短信信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 删除信息
	public void delete() {
		Json json = new Json();
		try {
			smsService.delete(model.getIds());
			json.setMsg("删除短信信息成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("删除短信信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);

	}
	
	public void sendMsgByWechat(){
		Json json = new Json();
		try {
			if (ValidateUtil.isValid(user.getSchoolArea())) {
				this.model.setSchoolArea(user.getSchoolArea());
			}
			model.setOperator(user.getCname().trim());
			boolean b = smsService.sendMsgToStudentByWechat(model);
			json.setMsg("微信通知成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("微信通知失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);

	}

}

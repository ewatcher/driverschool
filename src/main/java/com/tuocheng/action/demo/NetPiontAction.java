package com.tuocheng.action.demo;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.BaseAction;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.demo.NetPiont;
import com.tuocheng.service.demo.NetPiontServiceI;
import com.tuocheng.util.base.ExceptionUtil;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 报名网点管理action
 * 
 * @author MEI702
 * 
 */
@Namespace("/demo")
@Action(value = "netPiontAction", results = { @Result(name = "netPiont", location = "/demo/admin/netPiont.jsp"),
		@Result(name = "netPiontAdd", location = "/demo/admin/netPiontAdd.jsp"),
		@Result(name = "netPiontEdit", location = "/demo/admin/netPiontEdit.jsp"), })
public class NetPiontAction extends BaseAction<NetPiont> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2331052199665339728L;

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(NetPiontAction.class);

	private NetPiontServiceI netPiontService;

	@Autowired
	public void setNetPiontService(NetPiontServiceI netPiontService) {
		this.netPiontService = netPiontService;
	}

	// 到达报名网点管理主页面
	public String toNetPiontPage() {
		return "netPiont";
	}

	// 到达添加报名网点页面
	public String toNetPiontAddPage() {
		return "netPiontAdd";
	}

	// 到达编辑页面
	public String toNetPiontEditPage() {
		return "netPiontEdit";
	}

	public void datagrid() throws Exception {
		// 超级管理员有查找所有校区选项
		if (ValidateUtil.isValid(user.getSchoolArea())) {
			this.model.setSchoolArea(user.getSchoolArea());
		}
		super.writeJson(netPiontService.dataGrid(model));
	}

	// 添加信息
	public void add() {
		Json json = new Json();
		try {
			if (ValidateUtil.isValid(user.getSchoolArea())) {
				this.model.setSchoolArea(user.getSchoolArea());
			}
			model.setOperator(user.getCname().trim());
			NetPiont netPiont = netPiontService.save(model);
			json.setMsg("添加报名网点信息成功！");
			json.setSuccess(true);
			json.setObj(netPiont);
		} catch (Exception e) {
			json.setMsg("添加报名网点信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 编辑信息
	public void edit() {
		Json json = new Json();
		try {
			NetPiont sms = netPiontService.udpate(model);
			json.setMsg("编辑报名网点信息成功！");
			json.setSuccess(true);
			json.setObj(sms);
		} catch (Exception e) {
			json.setMsg("编辑报名网点信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 删除信息
	public void delete() {
		Json json = new Json();
		try {
			netPiontService.delete(model.getIds());
			json.setMsg("删除报名网点信息成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("删除报名网点信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);

	}

	public void toFomatterOrderNo() {
		Integer reVal = null;
		try {
			reVal = netPiontService.getOrderNoBySchoolArea(model.getSchoolArea());
		} catch (Exception e) {
		}
		if (reVal != null) {
			super.writeJson(reVal);
		}
	}
}

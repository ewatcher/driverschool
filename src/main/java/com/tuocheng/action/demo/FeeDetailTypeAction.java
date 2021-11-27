package com.tuocheng.action.demo;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.BaseAction;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.demo.FeeDetailType;
import com.tuocheng.pageModel.demo.FeeDetailType;
import com.tuocheng.service.demo.FeeDetailTypeServiceI;
import com.tuocheng.service.demo.OrganizationServiceI;
import com.tuocheng.service.demo.SMSServiceI;
import com.tuocheng.util.base.ExceptionUtil;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 收费明细管理action
 * 
 * @author MEI702
 * 
 */
@Namespace("/demo")
@Action(value = "feeDetailTypeAction", results = {
		@Result(name = "feeDetailType", location = "/demo/fee/feeDetailType.jsp"),
		@Result(name = "feeDetailTypeAdd", location = "/demo/fee/feeDetailTypeAdd.jsp"),
		@Result(name = "feeDetailTypeEdit", location = "/demo/fee/feeDetailTypeEdit.jsp"), })
public class FeeDetailTypeAction extends BaseAction<FeeDetailType> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2331052199665339728L;

	private static final Logger logger = Logger.getLogger(FeeDetailTypeAction.class);

	private FeeDetailTypeServiceI feeDetailTypeService;
	private OrganizationServiceI organizationService;

	@Autowired
	public void setFeeDetailTypeService(FeeDetailTypeServiceI feeDetailTypeService) {
		this.feeDetailTypeService = feeDetailTypeService;
	}

	@Autowired
	public void setOrganizationService(OrganizationServiceI organizationService) {
		this.organizationService = organizationService;
	}

	// 到达收费明细管理主页面
	public String toFeeDetailTypePage() {
		return "feeDetailType";
	}

	// 到达添加收费明细页面
	public String toFeeDetailTypeAddPage() {
		return "feeDetailTypeAdd";
	}

	// 到达编辑页面
	public String toFeeDetailTypeEditPage() {
		return "feeDetailTypeEdit";
	}

	public void datagrid() throws Exception {
		// 超级管理员有查找所有校区选项
		if (ValidateUtil.isValid(user.getSchoolArea())) {
			this.model.setSchoolArea(user.getSchoolArea());
		}
		super.writeJson(feeDetailTypeService.dataGrid(model));
	}

	// 添加信息
	public void add() {
		Json json = new Json();
		try {
			if (ValidateUtil.isValid(user.getSchoolArea())) {
				this.model.setSchoolArea(user.getSchoolArea());
			}
			model.setOperator(user.getCname().trim());
			FeeDetailType feeDetailType = feeDetailTypeService.save(model);
			json.setMsg("添加收费明细信息成功！");
			json.setSuccess(true);
			json.setObj(feeDetailType);
		} catch (Exception e) {
			json.setMsg("添加收费明细信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 编辑信息
	public void edit() {
		Json json = new Json();
		try {
			FeeDetailType sms = feeDetailTypeService.udpate(model);
			json.setMsg("编辑收费明细信息成功！");
			json.setSuccess(true);
			json.setObj(sms);
		} catch (Exception e) {
			json.setMsg("编辑收费明细信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 删除信息
	public void delete() {
		Json json = new Json();
		try {
			feeDetailTypeService.delete(model.getIds());
			json.setMsg("删除收费明细信息成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("删除收费明细信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);

	}

	public void toFomatterOrderNo() {
		Integer reVal = null;
		try {
			reVal = feeDetailTypeService.getOrderNoBySchoolArea(model.getSchoolArea());
		} catch (Exception e) {
		}
		if (reVal != null) {
			super.writeJson(reVal);
		}
	}
}

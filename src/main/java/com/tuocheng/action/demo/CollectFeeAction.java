package com.tuocheng.action.demo;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.BaseAction;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.demo.CollectFee;
import com.tuocheng.pageModel.demo.FeeDetailType;
import com.tuocheng.pageModel.demo.FeeDetailType;
import com.tuocheng.service.demo.CollectFeeServiceI;
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
@Action(value = "collectFeeAction", results = { @Result(name = "collectFee", location = "/demo/fee/collectFee.jsp"),
		@Result(name = "collectFeeAdd", location = "/demo/fee/collectFeeAdd.jsp"),
		@Result(name = "collectFeeEdit", location = "/demo/fee/collectFeeEdit.jsp"),
		@Result(name = "selectHandupMan", location = "/demo/fee/selectHandupMan.jsp"),
		@Result(name = "print", location = "/demo/fee/print.jsp"), })
public class CollectFeeAction extends BaseAction<CollectFee> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2331052199665339728L;

	private static final Logger logger = Logger.getLogger(CollectFeeAction.class);

	private FeeDetailTypeServiceI feeDetailTypeService;
	private OrganizationServiceI organizationService;
	private CollectFeeServiceI collectFeeService;

	@Autowired
	public void setCollectFeeService(CollectFeeServiceI collectFeeService) {
		this.collectFeeService = collectFeeService;
	}

	@Autowired
	public void setFeeDetailTypeService(FeeDetailTypeServiceI feeDetailTypeService) {
		this.feeDetailTypeService = feeDetailTypeService;
	}

	@Autowired
	public void setOrganizationService(OrganizationServiceI organizationService) {
		this.organizationService = organizationService;
	}

	// 到达收费明细管理主页面
	public String toCollectFeePage() {
		return "collectFee";
	}

	// 到达添加收费明细页面
	public String toCollectFeeAddPage() {
		return "collectFeeAdd";
	}

	// 到达添打印页面
	public String toPrintPage() {
		return "print";
	}

	// 到达选择交费人员 页面
	public String toSelectHandupManPage() {
		return "selectHandupMan";
	}

	// 到达编辑页面
	public String toCollectFeeEditPage() {
		return "collectFeeEdit";
	}

	public void datagrid() throws Exception {
		// 超级管理员有查找所有校区选项
		if (ValidateUtil.isValid(user.getSchoolArea())) {
			this.model.setSchoolArea(user.getSchoolArea());
		}
		super.writeJson(collectFeeService.dataGrid(model));
	}

	// 添加信息
	public void add() {
		Json json = new Json();
		try {
			if (ValidateUtil.isValid(user.getSchoolArea())) {
				this.model.setSchoolArea(user.getSchoolArea());
			}
			model.setOperator(user.getCname().trim());
			CollectFee collectFee = collectFeeService.save(model);
			json.setMsg("添加收费明细信息成功！");
			json.setSuccess(true);
			json.setObj(collectFee);
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
			CollectFee collectFee = collectFeeService.udpate(model);
			json.setMsg("编辑收费明细信息成功！");
			json.setSuccess(true);
			json.setObj(collectFee);
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
			collectFeeService.delete(model.getIds());
			json.setMsg("删除收费明细信息成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("删除收费明细信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);

	}

	/**
	 * 初始化收费项目
	 * 
	 * 
	 * 2017年7月10日 void
	 */
	public void toInitFeeItemName() {
		try {
			super.writeJson(feeDetailTypeService.getAllFeeDetailType(model.getSchoolArea()));
		} catch (Exception e) {
		}
	}

	/**
	 * 
	 * 
	 * 初始化收费金额 2017年7月12日 void
	 */
	public void toInitMoney() {
		try {
			super.writeJson(feeDetailTypeService.getMoneyById(model.getSchoolArea(), model.getFeeDetailType()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

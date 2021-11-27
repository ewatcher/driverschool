package com.tuocheng.action.demo;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.BaseAction;
import com.tuocheng.service.demo.RepairServiceI;

/**
 * 修复数据库ACTION
 * 
 * @author 农峰
 * 
 */
@Namespace("/demo")
@Action(value = "repairAction", results = { @Result(name = "success", location = "/", type = "redirect") })
public class RepairAction extends BaseAction {

	private RepairServiceI repairService;

	public RepairServiceI getRepairService() {
		return repairService;
	}

	@Autowired
	public void setRepairService(RepairServiceI repairService) {
		this.repairService = repairService;
	}

	/**
	 * 修复数据库
	 * 
	 * @return
	 */
	public String doNotNeedSession_repairAction() {
		repairService.repair();
		return "success";
	}

	/**
	 * 先清空数据库，然后再修复数据库
	 * 
	 * @return
	 */
	public String doNotNeedSession_deleteAndRepair() {
		repairService.deleteAndRepair();
		return "success";
	}

}

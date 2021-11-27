package com.tuocheng.action.demo;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.BaseAction;
import com.tuocheng.action.base.GuestAction;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.demo.Salesman;
import com.tuocheng.service.demo.AssessServiceI;
import com.tuocheng.service.demo.SalesmanServiceI;
import com.tuocheng.util.base.ExceptionUtil;
import com.tuocheng.util.base.Util;

/**
 * 微信评价业务员action
 * 
 * @author 农峰
 * 
 */

@Namespace("/demo")
@Action(value = "salesmanAction", results = {
		@Result(name = "salesmanList", location = "/demo/weixin/salesmanList.jsp") })
public class SalesmanAction extends BaseAction<Salesman> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7760271099722493152L;

	/**
	 * 序列号
	 */
	private static final Logger log = Logger.getLogger(SalesmanAction.class);

	private SalesmanServiceI salesmanService;

	@Autowired
	public void setSalesmanService(SalesmanServiceI salesmanService) {
		this.salesmanService = salesmanService;
	}

	// 到难点列表页面
	public String toSalesmanList() {
		return "salesmanList";
	}

	// 从后台中查找出所有实体类信息
	public void datagrid() throws Exception {

		if (Util.isNotNull(user.getSchoolArea())) {
			model.setOrganizationId(user.getSchoolArea());
		}

		model.setSort("createTime");
		model.setOrder("desc");
		super.writeJson(salesmanService.dataGrid(model));
	}

	// 更新实体类信息
	public void edit() {
		Json json = new Json();
		try {
			Salesman assess = salesmanService.update(model);
			json.setSuccess(true);
			json.setMsg("修改数据成功！");
			json.setObj(assess);
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
			salesmanService.delete(model.getIds());
			json.setSuccess(true);
			json.setMsg("删除数据成功！");
		} catch (Exception e) {
			json.setMsg("删除数据失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

}

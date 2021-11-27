package com.tuocheng.action.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.BaseAction;
import com.tuocheng.model.demo.TautoCarArrange;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.base.TrainerJson;
import com.tuocheng.pageModel.demo.AutoCarArrange;
import com.tuocheng.pageModel.demo.Trainer;
import com.tuocheng.pageModel.demo.AutoCarArrange;
import com.tuocheng.pageModel.demo.TrainerReservationDetail;
import com.tuocheng.service.demo.AutoCarArrangeServiceI;
import com.tuocheng.service.demo.OrganizationServiceI;
import com.tuocheng.service.demo.TrainerArrangeServiceI;
import com.tuocheng.service.demo.TrainerReservationDetailServiceI;
import com.tuocheng.service.demo.TrainerSerivceI;
import com.tuocheng.util.base.ExceptionUtil;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 教练排班管理action
 * 
 * @author MEI702
 * 
 */
@Namespace("/demo")
@Action(value = "autocarArrangeAction", results = {
		@Result(name = "autocarArrangeDetail", location = "/demo/usingCar/autocarArrangeDetail.jsp"),
		@Result(name = "autocarArrangeAdd", location = "/demo/usingCar/autocarArrangeAdd.jsp"),
		@Result(name = "autocarArrangeEdit", location = "/demo/usingCar/autocarArrangeEdit.jsp"),})
public class AutocarArrangeAction extends BaseAction<AutoCarArrange> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1530300437623778388L;

	/**
	 * 
	 */
	private static final Logger logger = Logger
			.getLogger(AutocarArrangeAction.class);

	private AutoCarArrangeServiceI autocarService;

	@Autowired
	public void setAutocarService(AutoCarArrangeServiceI autocarService) {
		this.autocarService = autocarService;
	}

	// 到达车辆排班管理主页面
	public String toAutocarArrangePage() {
		return "autocarArrangeDetail";
	}

	public String toAutocarArrangeAddPage() {
		return "autocarArrangeAdd";
	}
	
	public String toAutocarArrangeEditPage() {
		return "autocarArrangeEdit";
	}
	
	public String toAutocarArrangeDeletePage() {
		return "autocarArrangeDelete";
	}

	public void datagrid() throws Exception {
		// 超级管理员有查找所有校区选项
		if (ValidateUtil.isValid(user.getSchoolArea())) {
			this.model.setSchoolArea(user.getSchoolArea());
		}
		super.writeJson(autocarService.dataGrid(model));
	}

	public void getAutocarArrangeByCarId() {
		AutoCarArrange entry = new AutoCarArrange();
		if (ValidateUtil.isValid(model.getCarId())) {
			entry.setCarId(model.getCarId());
		}
		if (ValidateUtil.isValid(user.getSchoolArea())) {
			entry.setSchoolArea(user.getSchoolArea());
		}
		//查询字段
		if(model.getArrangeDateBegin()!=null){
			entry.setArrangeDateBegin(model.getArrangeDateBegin());
		}
		if(model.getArrangeDateEnd()!=null){
			entry.setArrangeDateEnd(model.getArrangeDateEnd());
		}

		entry.setRows(model.getRows());
		entry.setSort(model.getSort());
		entry.setOrder(model.getOrder());
		entry.setPage(model.getPage());

		try {
			super.writeJson(autocarService.dataGrid(entry));
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	// 添加信息
	public void add() {
		Json json = new Json();
		try {
			model.setOperator(user.getCname().trim());
			boolean flag = autocarService.save(model);
			if (flag) {
				json.setMsg("添加信息成功！");
				json.setSuccess(true);
			} else {
				json.setMsg("添加信息失败:-->当前选择排班日期重复！");
				json.setSuccess(false);
			}

		} catch (Exception e) {
			json.setMsg("添加自动档排班信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 编辑信息
	public void edit() {
		
		
		Json json = new Json();
		try {
			AutoCarArrange trainerArrange = autocarService.udpate(model);
			json.setMsg("编辑排班信息成功！");
			json.setSuccess(true);
			json.setObj(trainerArrange);

		} catch (Exception e) {
			json.setMsg("编辑排班信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 删除信息
	public void delete() {
		Json json = new Json();
		try {
			autocarService.delete(model.getIds());
			json.setMsg("删除信息成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("删除信息成功！");
			json.setSuccess(false);
			e.printStackTrace();
		}
		super.writeJson(json);

	}

}

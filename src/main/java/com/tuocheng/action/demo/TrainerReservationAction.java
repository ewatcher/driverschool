package com.tuocheng.action.demo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.BaseAction;
import com.tuocheng.model.demo.Tstudent;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.demo.Student;
import com.tuocheng.pageModel.demo.Reservation;
import com.tuocheng.pageModel.demo.Trainer;
import com.tuocheng.pageModel.demo.TrainerReservation;
import com.tuocheng.service.demo.ReservationServiceI;
import com.tuocheng.service.demo.StudentServiceI;
import com.tuocheng.service.demo.TrainerReservationServiceI;
import com.tuocheng.service.demo.TrainerSerivceI;
import com.tuocheng.util.base.ExceptionUtil;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 预约管理action
 * 
 * @author MEI702
 * 
 */
@Namespace("/demo")
@Action(value = "trainerReservationAction", results = {
		@Result(name = "trainerReservation", location = "/demo/trainerReservation/trainerReservation.jsp"),
		@Result(name = "myTrainerReservation", location = "/demo/trainerReservation/myTrainerReservation.jsp"),
		})
public class TrainerReservationAction extends BaseAction<TrainerReservation> {

	/**
	 * 序列化标识
	 */
	private static final long serialVersionUID = -676166711198031916L;

	private static final Logger logger = Logger
			.getLogger(TrainerReservationAction.class);

	private TrainerReservationServiceI trainerReservationService;

	@Autowired
	public void setTrainerReservationService(
			TrainerReservationServiceI trainerReservationService) {
		this.trainerReservationService = trainerReservationService;
	}

	// 到达预约管理主页面
	public String toTrainerReservationPage() {
		return "trainerReservation";
	}
	//达到教练个人预约页面
	public String toMyTrainerReservationPage() {
		return "myTrainerReservation";
	}
	
	//到达教练个人预约明细页面
	public String toMyDetailPage() {
		return "myDetail";
	}

	public void datagrid() throws Exception {
		// 超级管理员有查找所有校区选项
		if (ValidateUtil.isValid(user.getSchoolArea())) {
			this.model.setSchoolArea(user.getSchoolArea());
		}
		super.writeJson(trainerReservationService.dataGrid(model));
	}
	
	public void personalDatagrid() throws Exception {
		// 超级管理员有查找所有校区选项
		if (ValidateUtil.isValid(user.getSchoolArea())) {
			this.model.setSchoolArea(user.getSchoolArea());
			model.setTrainerIdentityId(user.getCname().trim());
		}
		super.writeJson(trainerReservationService.personalDataGrid(model));
	}


	// 添加信息
	public void add() {
		Json json = new Json();
		try {
			TrainerReservation reservation = trainerReservationService
					.save(model);
			json.setMsg("添加预约信息成功！");
			json.setSuccess(true);
			json.setObj(reservation);
		} catch (Exception e) {
			json.setMsg("添加预约信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 编辑信息
	public void edit() {
		Json json = new Json();
		try {
			TrainerReservation reservation = trainerReservationService
					.udpate(model);
			json.setMsg("编辑预约信息成功！");
			json.setSuccess(true);
			json.setObj(reservation);
		} catch (Exception e) {
			json.setMsg("编辑预约信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 删除信息
	public void delete() {
		Json json = new Json();
		try {
			trainerReservationService.delete(model.getIds());
			json.setMsg("删除预约信息成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("删除预约信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

}

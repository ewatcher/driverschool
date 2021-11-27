package com.tuocheng.action.demo;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.TabableView;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.BaseAction;
import com.tuocheng.model.demo.TtrainerReservationDetail;
import com.tuocheng.pageModel.base.ComboboxJson;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.demo.TrainerReservationDetail;
import com.tuocheng.service.demo.StudentServiceI;
import com.tuocheng.service.demo.TrainerReservationDetailServiceI;
import com.tuocheng.util.base.ExceptionUtil;

/**
 * 教练预约明细管理action
 * 
 * @author MEI702
 * 
 */
@Namespace("/demo")
@Action(value = "reservationDetailAction", results = {
		@Result(name = "reservationDetailEdit", location = "/demo/trainerReservation/reservationDetailEdit.jsp"),
		@Result(name = "reservationDetail", location = "/demo/trainerReservation/reservationDetail.jsp"),
		@Result(name = "myDetail", location = "/demo/trainerReservation/myDetail.jsp"), })
public class TrainerReservationDetailAction extends
		BaseAction<TrainerReservationDetail> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 794769370809817909L;
	private static final Logger logger = Logger
			.getLogger(TrainerReservationDetailAction.class);

	private TrainerReservationDetailServiceI reservationDetailService;
	private StudentServiceI studentService;

	@Autowired
	public void setReservationDetailService(
			TrainerReservationDetailServiceI reservationDetailService) {
		this.reservationDetailService = reservationDetailService;
	}

	@Autowired
	public void setStudentService(StudentServiceI studentService) {
		this.studentService = studentService;
	}

	// 到达更新教练明细信息更新页面
	public String toTrainerReservationDetailEditPage() {
		return "trainerReservationDetailEdit";
	}

	// 到达更新教练明细信息更新页面
	public String toTrainerReservationDetailPage() {
		return "reservationDetail";
	}

	public String toMyDetailPage() {
		return "myDetail";
	}

	public void datagrid() {
		try {
			// 实体参数配置
			TrainerReservationDetail entry = new TrainerReservationDetail();
			entry.setTrainerId(model.getTrainerId());
			entry.setReservationDateBegin(model.getReservationDateBegin());
			entry.setReservationDateEnd(model.getReservationDateEnd());
			entry.setTrainerReservationId(model.getTrainerReservationId());
			// 他页查询
			entry.setRows(model.getRows());
			entry.setSort(model.getSort());
			entry.setOrder(model.getOrder());
			entry.setPage(model.getPage());

			DataGrid myDataGrid = reservationDetailService.dataGrid(entry);
			List<TrainerReservationDetail> rets = myDataGrid.getRows();
			
			DataGrid retDatagrid = new DataGrid();
			retDatagrid.setRows(rets);
			retDatagrid.setTotal(myDataGrid.getTotal());
			// 4.将数据返回前台
			super.writeJson(retDatagrid);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void personalDatagrid() {
		try {
			model.setTrainerIdentityId(user.getCname().trim());
			super.writeJson(reservationDetailService.dataGrid(model));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 添加信息
	public void add() {
		Json json = new Json();
		try {
			TrainerReservationDetail reservationDetail = reservationDetailService
					.save(model);
			json.setMsg("添加教练预约明细信息成功！");
			json.setSuccess(true);
			json.setObj(reservationDetail);
		} catch (Exception e) {
			json.setMsg("添加教练预约明细信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 编辑信息
	public void edit() {
		Json json = new Json();
		try {
			TrainerReservationDetail reservationDetail = reservationDetailService
					.udpate(model);
			json.setMsg("编辑教练预约明细信息成功！");
			json.setSuccess(true);
			json.setObj(reservationDetail);
		} catch (Exception e) {
			json.setMsg("编辑教练预约明细信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 删除信息
	public void delete() {
		Json json = new Json();
		try {
			reservationDetailService.delete(model.getIds());
			json.setMsg("删除教练预约明细信息成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("删除教练预约明细信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);

	}

	// 获取该教练所带的学员
	public void getStudentByTrainerId() {
		super.writeJson(studentService.getStudentByItTrainerId(model
				.getTrainerId()));
	}

}

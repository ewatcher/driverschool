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
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.base.TrainerJson;
import com.tuocheng.pageModel.demo.Trainer;
import com.tuocheng.pageModel.demo.TrainerArrange;
import com.tuocheng.pageModel.demo.TrainerReservationDetail;
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
@Action(value = "trainerArrangeAction", results = {
		@Result(name = "trainerArrange", location = "/demo/trainerArrange/trainerArrange.jsp"),
		@Result(name = "trainerArrangeAdd", location = "/demo/trainerArrange/trainerArrangeAdd.jsp"),
		@Result(name = "trainerArrangeEdit", location = "/demo/trainerArrange/trainerArrangeEdit.jsp"),
		@Result(name = "selectTrainer", location = "/demo/trainerArrange/selectTrainer.jsp"),
		@Result(name = "trainerArrangeDetail", location = "/demo/trainerArrange/trainerArrangeDetail.jsp"),
		@Result(name = "myDetail", location = "/demo/trainerArrange/myDetail.jsp"),
		@Result(name = "myTrainerArrange", location = "/demo/trainerArrange/myTrainerArrange.jsp"),
		@Result(name = "trainerArrangeDetailList", location = "/demo/trainerArrange/trainerArrangeDetailList.jsp"), })
public class TrainerArrangeAction extends BaseAction<TrainerArrange> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 794769370809817909L;
	private static final Logger logger = Logger
			.getLogger(TrainerArrangeAction.class);

	private TrainerArrangeServiceI trainerArrangeService;
	private TrainerSerivceI trainerSerivce;
	private OrganizationServiceI organizationService;
	private TrainerReservationDetailServiceI trainerReservationDetailService;

	@Autowired
	public void setTrainerArrangeService(
			TrainerArrangeServiceI trainerArrangeService) {
		this.trainerArrangeService = trainerArrangeService;
	}

	@Autowired
	public void setTrainerSerivce(TrainerSerivceI trainerSerivce) {
		this.trainerSerivce = trainerSerivce;
	}

	@Autowired
	public void setOrganizationService(OrganizationServiceI organizationService) {
		this.organizationService = organizationService;
	}

	@Autowired
	public void setTrainerReservationDetailService(
			TrainerReservationDetailServiceI trainerReservationDetailService) {
		this.trainerReservationDetailService = trainerReservationDetailService;
	}

	// 到达教练排班管理主页面
	public String toTrainerArrangePage() {
		return "trainerArrange";
	}

	// 到达添加教练排班页面
	public String toTrainerArrangeAddPage() {
		return "trainerArrangeAdd";
	}

	// 到达编辑页面
	public String toTrainerArrangeEditPage() {
		return "trainerArrangeEdit";
	}

	// 到达排班明细页面
	public String toTrainerArrangeDetailPage() {
		return "trainerArrangeDetail";
	}

	// 到达排班明细页面
	public String toTrainerArrangeDetailListPage() {
		return "trainerArrangeDetailList";
	}

	// 到达教练个人排班页面
	public String toMyTrainerArrangePage() {
		return "myTrainerArrange";
	}

	// 到达教练排班明细信息页面
	public String toMyDetailPage() {
		return "myDetail";
	}

	public void datagrid() throws Exception {
		// 超级管理员有查找所有校区选项
		if (ValidateUtil.isValid(user.getSchoolArea())) {
			this.model.setSchoolArea(user.getSchoolArea());
		}
		this.getTrainerDatabyType();
	}

	// 教练个人排班信息列表
	@SuppressWarnings("unchecked")
	public void personalDatagrid() throws Exception {
		// 超级管理员有查找所有校区选项
		if (ValidateUtil.isValid(user.getSchoolArea())) {
			this.model.setSchoolArea(user.getSchoolArea());
		}
		// 1.设置班级查询条件
		Trainer trainer = new Trainer();
		trainer.setDriverType(model.getTrainerDriverType());
		trainer.setIdentity(user.getCname().trim());
		trainer.setName(model.getTrainerName());
		trainer.setSchoolArea(user.getSchoolArea());

		trainer.setRows(model.getRows());
		trainer.setSort(model.getSort());
		trainer.setOrder(model.getOrder());
		trainer.setPage(model.getPage());
		// 2.根据查询条件获取班级信息
		DataGrid dataGrid = trainerSerivce.personalDatagrid(trainer);
		// 3.对数据进行数据模型转换
		List<Trainer> trainers = dataGrid.getRows();
		List<TrainerArrange> retArranges = new ArrayList<TrainerArrange>();
		if (trainers != null && trainers.size() > 0) {
			for (Trainer t : trainers) {
				TrainerArrange arrange = new TrainerArrange();
				arrange.setTrainerId(t.getId());
				arrange.setTrainerName(t.getName());
				arrange.setTrainerIdentity(t.getIdentity());
				arrange.setTrainerPhone(t.getPhone());
				arrange.setTrainerDriverCarType(t.getDriverCarType());
				arrange.setTrainerDriverLicenseId(t.getDriverLicenseId());
				arrange.setTrainerDriverType(t.getDriverType());
				// 显示教练所属于机构
				arrange.setTrainerSchoolAreaName(organizationService
						.getSingleById(user.getSchoolArea()).getName());
				retArranges.add(arrange);
			}
		}
		// 4.设置返回前台的数据参数

		DataGrid retDatagrid = new DataGrid();
		retDatagrid.setRows(retArranges);
		retDatagrid.setTotal(dataGrid.getTotal());
		// 5.返回结果集
		super.writeJson(retDatagrid);
	}

	public void detailDatagrid() throws Exception {
		// 超级管理员有查找所有校区选项
		if (ValidateUtil.isValid(user.getSchoolArea())) {
			this.model.setSchoolArea(user.getSchoolArea());
		}
		if (ValidateUtil.isValid(model.getTrainerId())) {
			model.setTrainerId(model.getTrainerId().trim());
		}
		if (ValidateUtil.isValid(model.getTrainerName())) {
			model.setTrainerName(model.getTrainerName().trim());
		}
		super.writeJson(trainerArrangeService.dataGrid(model));
	}

	// 到达选择教练页面
	public String toSelectTrainerPage() {
		return "selectTrainer";
	}

	// 添加信息
	public void add() {
		Json json = new Json();
		try {
			model.setOperator(user.getCname().trim());
			TrainerArrange trainerArrange = trainerArrangeService.save(model);
			if (trainerArrange != null) {
				json.setMsg("编辑教练排班信息成功！");
				json.setSuccess(true);
				json.setObj(trainerArrange);
			} else {
				json.setMsg("当前所选择日期已经存在，无法添加，请查检日期选择！");
				json.setSuccess(false);
			}

		} catch (Exception e) {
			json.setMsg("添加教练排班信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 编辑信息
	public void edit() {
		Json json = new Json();
		try {
			TrainerArrange trainerArrange = trainerArrangeService.udpate(model);
			json.setMsg("编辑教练排班信息成功！");
			json.setSuccess(true);
			json.setObj(trainerArrange);

		} catch (Exception e) {
			json.setMsg("编辑教练排班信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 删除信息
	public void delete() throws Exception {
		Json json = new Json();
		boolean temp = trainerReservationDetailService.delete(model.getIds());
		if (temp) {
			json.setMsg("删除教练排班信息成功！");
			json.setSuccess(true);
		} else {
			json.setMsg("当前教练排班明细信息存在预约不允许删除，取消预约后再删除该信息！");
			json.setSuccess(false);
		}
		super.writeJson(json);

	}

	public void getTrainterNames() throws Exception {
		List<TrainerJson> trainerJsons = trainerSerivce
				.getAllTrainerNameByJson();
		super.writeJson(trainerJsons);
	}

	// 根据查询条件查找教练信息
	@SuppressWarnings("unchecked")
	public void getTrainerDatabyType() throws Exception {
		// 1.设置班级查询条件
		Trainer trainer = new Trainer();
		trainer.setDriverType(model.getTrainerDriverType());
		trainer.setIdentity(model.getTrainerIdentity());
		trainer.setName(model.getTrainerName());
		trainer.setSchoolArea(model.getSchoolArea());

		trainer.setRows(model.getRows());
		trainer.setSort(model.getSort());
		trainer.setOrder(model.getOrder());
		trainer.setPage(model.getPage());
		// 2.根据查询条件获取班级信息
		DataGrid dataGrid = trainerSerivce.datagrid(trainer);
		// 3.对数据进行数据模型转换
		List<Trainer> trainers = dataGrid.getRows();
		List<TrainerArrange> retArranges = new ArrayList<TrainerArrange>();
		if (trainers != null && trainers.size() > 0) {
			for (Trainer entry : trainers) {
				TrainerArrange arrange = new TrainerArrange();
				arrange.setTrainerId(entry.getId());
				arrange.setTrainerName(entry.getName());
				arrange.setTrainerCodeNo(entry.getCodeNo());
				arrange.setTrainerIdentity(entry.getIdentity());
				arrange.setTrainerPhone(entry.getPhone());
				arrange.setTrainerDriverCarType(entry.getDriverCarType());
				arrange.setTrainerDriverLicenseId(entry.getDriverLicenseId());
				arrange.setTrainerDriverType(entry.getDriverType());
				arrange.setTrainerArrangeFlag(entry.getArrangeFlag());
				arrange.setTrainerType(entry.getTrainerType());
				// 显示
				arrange.setArrangeDate(trainerReservationDetailService
						.getLatestDateByTrainerId(entry.getId(),
								entry.getSchoolArea()));
				// 显示教练所属于机构
				arrange.setTrainerSchoolAreaName(organizationService
						.getSingleById(entry.getSchoolArea()).getName());
				arrange.setSchoolArea(entry.getSchoolArea());
				arrange.setArrangeType(entry.getArrangeType());
				retArranges.add(arrange);
			}
		}
		// 4.设置返回前台的数据参数

		DataGrid retDatagrid = new DataGrid();
		retDatagrid.setRows(retArranges);
		retDatagrid.setTotal(dataGrid.getTotal());
		// 5.返回结果集
		super.writeJson(retDatagrid);
	}

	// 判断教练是否可以参加排班
	public void trainerArrangeOrNot() throws Exception {
		super.writeJson(trainerSerivce.trainerCarUsingOrNot(model
				.getTrainerId()));
	}

	@SuppressWarnings("unchecked")
	public void getDetailArrangeByTrainerId() throws Exception {
		TrainerReservationDetail trainerReservationDetail = new TrainerReservationDetail();
		// 实体参数配置
		trainerReservationDetail.setTrainerId(model.getTrainerId());
		trainerReservationDetail.setSchoolArea(model.getSchoolArea());
		// 他页查询
		trainerReservationDetail.setRows(model.getRows());
		trainerReservationDetail.setSort(model.getSort());
		trainerReservationDetail.setOrder(model.getOrder());
		trainerReservationDetail.setPage(model.getPage());
		// 1.设置班级查询条件
		// 3.对数据进行数据模型转换
		DataGrid myDataGrid = trainerReservationDetailService
				.dataGrid(trainerReservationDetail);

		List<TrainerReservationDetail> reservationDetails = myDataGrid
				.getRows();

		List<TrainerArrange> retArranges = new ArrayList<TrainerArrange>();
		if (reservationDetails != null && reservationDetails.size() > 0) {
			for (TrainerReservationDetail entry : reservationDetails) {
				TrainerArrange arrange = new TrainerArrange();
				arrange.setTrainerId(model.getTrainerId());
				arrange.setTrainerReservationDetailId(entry.getId());
				arrange.setReservationDate(entry.getReservationDate());
				arrange.setItem7TrainContent(entry.getItem7TrainContent());
				arrange.setItem8TrainContent(entry.getItem8TrainContent());
				arrange.setItem9TrainContent(entry.getItem9TrainContent());
				arrange.setItem10TrainContent(entry.getItem10TrainContent());
				arrange.setItem11TrainContent(entry.getItem11TrainContent());
				arrange.setItem12TrainContent(entry.getItem12TrainContent());
				arrange.setItem13TrainContent(entry.getItem13TrainContent());
				arrange.setItem14TrainContent(entry.getItem14TrainContent());
				arrange.setItem15TrainContent(entry.getItem15TrainContent());
				arrange.setItem16TrainContent(entry.getItem16TrainContent());
				arrange.setItem17TrainContent(entry.getItem17TrainContent());
				arrange.setItem18TrainContent(entry.getItem18TrainContent());
				arrange.setItem19TrainContent(entry.getItem19TrainContent());
				arrange.setItem20TrainContent(entry.getItem20TrainContent());
				arrange.setItem21TrainContent(entry.getItem21TrainContent());
				arrange.setReservationFieldId7(entry.getReservationFieldId7());
				arrange.setReservationFieldId8(entry.getReservationFieldId8());
				arrange.setReservationFieldId9(entry.getReservationFieldId9());
				arrange.setReservationFieldId10(entry.getReservationFieldId10());
				arrange.setReservationFieldId11(entry.getReservationFieldId11());
				arrange.setReservationFieldId12(entry.getReservationFieldId12());
				arrange.setReservationFieldId13(entry.getReservationFieldId13());
				arrange.setReservationFieldId14(entry.getReservationFieldId14());
				arrange.setReservationFieldId15(entry.getReservationFieldId15());
				arrange.setReservationFieldId16(entry.getReservationFieldId16());
				arrange.setReservationFieldId17(entry.getReservationFieldId17());
				arrange.setReservationFieldId18(entry.getReservationFieldId18());
				arrange.setReservationFieldId19(entry.getReservationFieldId19());
				arrange.setReservationFieldId20(entry.getReservationFieldId20());
				arrange.setReservationFieldId21(entry.getReservationFieldId21());
				arrange.setArrangeId(entry.getArrangeId());
				retArranges.add(arrange);
			}
		}
		// 4.设置返回前台的数据参数

		DataGrid retDatagrid = new DataGrid();
		retDatagrid.setRows(retArranges);
		retDatagrid.setTotal(myDataGrid.getTotal());
		// 5.返回结果集
		super.writeJson(retDatagrid);
	}

	// 更新教练明细信息
	public void arrangeDetailChange() throws Exception {
		TrainerReservationDetail detail = new TrainerReservationDetail();
		detail.setId(model.getTrainerReservationDetailId());
		detail.setItem7TrainContent(model.getItem7TrainContent());
		detail.setItem8TrainContent(model.getItem8TrainContent());
		detail.setItem9TrainContent(model.getItem9TrainContent());
		detail.setItem10TrainContent(model.getItem10TrainContent());
		detail.setItem11TrainContent(model.getItem11TrainContent());
		detail.setItem12TrainContent(model.getItem12TrainContent());
		detail.setItem13TrainContent(model.getItem13TrainContent());
		detail.setItem14TrainContent(model.getItem14TrainContent());
		detail.setItem15TrainContent(model.getItem15TrainContent());
		detail.setItem16TrainContent(model.getItem16TrainContent());
		detail.setItem17TrainContent(model.getItem17TrainContent());
		detail.setItem18TrainContent(model.getItem18TrainContent());
		detail.setItem19TrainContent(model.getItem19TrainContent());
		detail.setItem20TrainContent(model.getItem20TrainContent());
		detail.setItem21TrainContent(model.getItem21TrainContent());

		TrainerReservationDetail retVal = trainerReservationDetailService
				.arrangeDetailUpdate(detail);
		Json json = new Json();
		if (retVal != null) {
			json.setSuccess(true);
			json.setMsg("修改教练排班明细信息成功！！！");
		} else {
			json.setSuccess(false);
			json.setMsg("当前教练排班信息存在预约，不允许修改，请先取消相关预约后方可修改！！！");
		}

		super.writeJson(json);
	}
}

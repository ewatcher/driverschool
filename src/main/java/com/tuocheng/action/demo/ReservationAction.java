package com.tuocheng.action.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.BaseAction;
import com.tuocheng.model.demo.Torganization;
import com.tuocheng.model.demo.Tstudent;
import com.tuocheng.model.demo.Ttrainer;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.demo.Student;
import com.tuocheng.pageModel.demo.Reservation;
import com.tuocheng.pageModel.demo.Trainer;
import com.tuocheng.pageModel.demo.TrainerReservationDetail;
import com.tuocheng.service.demo.AutoCarArrangeServiceI;
import com.tuocheng.service.demo.OrganizationServiceI;
import com.tuocheng.service.demo.ReservationServiceI;
import com.tuocheng.service.demo.StudentServiceI;
import com.tuocheng.service.demo.TrainerReservationDetailServiceI;
import com.tuocheng.service.demo.TrainerSerivceI;
import com.tuocheng.util.base.DateUtil;
import com.tuocheng.util.base.ExceptionUtil;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 预约管理action
 * 
 * @author MEI702
 * 
 */
@Namespace("/demo")
@Action(value = "reservationAction", results = {
		@Result(name = "reservation", location = "/demo/reservation/reservation.jsp"),
		@Result(name = "reservationAdd", location = "/demo/reservation/reservationAdd.jsp"),
		@Result(name = "reservationAddInput", location = "/demo/reservation/reservationAddInput.jsp"),
		@Result(name = "reservationEdit", location = "/demo/reservation/reservationEdit.jsp"),
		@Result(name = "reservationSelectStudentInput", location = "/demo/reservation/reservationSelectStudentInput.jsp"),
		@Result(name = "selectTrainerInput", location = "/demo/reservation/selectTrainerInput.jsp"),
		@Result(name = "reservationDetail", location = "/demo/reservation/reservationDetail.jsp"),
		@Result(name = "myReservation", location = "/demo/reservation/myReservation.jsp"),
		@Result(name = "detail", location = "/demo/reservation/detail.jsp"),
		@Result(name = "selectAutocar", location = "/demo/reservation/selectAutocar.jsp"), })
public class ReservationAction extends BaseAction<Reservation> {

	/**
	 * 序列化标识
	 */
	private static final long serialVersionUID = -676166711198031916L;

	private static final Logger logger = Logger.getLogger(ReservationAction.class);

	private ReservationServiceI reservationService;
	private StudentServiceI studentService;
	private TrainerSerivceI trainerService;
	private TrainerReservationDetailServiceI reservationDetailService;
	private OrganizationServiceI organizationService;
	private AutoCarArrangeServiceI autoCarArrangeService;

	@Autowired
	public void setReservationService(ReservationServiceI reservationService) {
		this.reservationService = reservationService;
	}

	@Autowired
	public void setStudentService(StudentServiceI studentService) {
		this.studentService = studentService;
	}

	@Autowired
	public void setTrainerService(TrainerSerivceI trainerService) {
		this.trainerService = trainerService;
	}

	@Autowired
	public void setReservationDetailService(TrainerReservationDetailServiceI reservationDetailService) {
		this.reservationDetailService = reservationDetailService;
	}

	@Autowired
	public void setOrganizationService(OrganizationServiceI organizationService) {
		this.organizationService = organizationService;
	}

	@Autowired
	public void setAutoCarArrangeService(AutoCarArrangeServiceI autoCarArrangeService) {
		this.autoCarArrangeService = autoCarArrangeService;
	}

	// 到达预约管理主页面
	public String toReservationPage() {
		return "reservation";
	}

	// 到达添加预约页面
	public String toReservationAddPage() {
		return "reservationAdd";
	}

	// 到达添加预约添加输入页面
	public String toReservationAddInputPage() {
		return "reservationAddInput";
	}

	// 到达选择学员页面
	public String toReservationSelectStudentInputPage() {
		return "reservationSelectStudentInput";
	}

	// 到达选择教练页面
	public String toSelectTrainerInputPage() {
		return "selectTrainerInput";
	}

	// 到达编辑页面
	public String toReservationEditPage() {
		return "reservationEdit";
	}

	// 到达教练预约明细信息页面
	public String toTrainerReservationDetailPage() {
		return "reservationDetail";
	}

	public String toReservationDetailPage() {
		return "detail";
	}

	public String toSelectAutocarPage() {
		return "selectAutocar";
	}

	// 到达学员个人预约信息页面
	public String toMyReservationPage() {
		return "myReservation";
	}

	public void datagrid() throws Exception {
		// 超级管理员有查找所有校区选项
		if (ValidateUtil.isValid(user.getSchoolArea())) {
			this.model.setSchoolArea(user.getSchoolArea());
		}
		super.writeJson(reservationService.dataGrid(model));

	}

	// 学员个人预约信息数据列表
	public void personalDatagrid() throws Exception {
		// 超级管理员有查找所有校区选项
		if (ValidateUtil.isValid(user.getSchoolArea())) {
			this.model.setSchoolArea(user.getSchoolArea());
			model.setStudentIdentityId(user.getCname().trim());
		}
		super.writeJson(reservationService.dataGrid(model));
	}

	// 添加信息
	public void add() {
		Json json = new Json();
		try {
			Tstudent student = studentService.getSingleById(model.getStudentId());
			// 超级管理员有查找所有校区选项
			if (null != student) {
				this.model.setSchoolArea(student.getOrganization().getId());
			}
			/*// 百色校区限制新学员预约
			if (Torganization.BS_SCHOOLAREA.equals(student.getOrganization().getId())) {
				Integer retFlag = reservationService.limitNewStudentReservation(model.getStudentId(),
						model.getReservationType());
				if (1 != retFlag&&0!=retFlag) {
					if(-1==retFlag){
						json.setMsg("预约失败，参数无效");
					}else if(-2==retFlag){
						json.setMsg("预约失败，科目一未合格达到预约限制5小时");
					}else if(-3==retFlag){
						json.setMsg("预约失败，科目一不合格不可预约路训");
					}
					json.setSuccess(false);
					super.writeJson(json);
					return ;
				}
			}*/
			// 标记预约创建方式为后台
			model.setReservationWay(1);
			model.setOperator(user.getCname().trim());
			Integer retFlag = null;
			if (reservationService.reservationExit(model.getStudentId(), model.getDate(), model.getFieldOptionFlag(),
					model.getSchoolArea())) {
				json.setMsg("该学员在当前日期此时段已经存在预约！");
			} else {
				retFlag = reservationService.save(model);
				if (retFlag> 0) {
					json.setMsg("添加信息预约成功！");
				} else if(-1==retFlag){
					json.setMsg("该学员学时已经用完,无法预约！");
				}
				else if(-2==retFlag){
					json.setMsg("该教练在该时间段已经存在预约，不可重复！");
				}
				else if(-3==retFlag){
					json.setMsg("预约失败！");
				}
			}

			json.setSuccess(true);
			json.setObj(retFlag);
		} catch (Exception e) {
			json.setMsg("添加预约信息失败！" + e);
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 编辑教练预约明细信息
	public void editReservationDetail() {
		Json json = new Json();
		try {
			// 1.将数据模块进行转换（跨越action数据流）
			TrainerReservationDetail detail = new TrainerReservationDetail();
			detail.setId(model.getReservationDetailId());
			detail.setReservationDate(model.getReservationDate());
			detail.setItem7Studentid(model.getItem7Studentid());
			detail.setItem7TrainContent(model.getItem7TrainContent());
			detail.setItem8Studentid(model.getItem8Studentid());
			detail.setItem8TrainContent(model.getItem8TrainContent());
			detail.setItem9Studentid(model.getItem9Studentid());
			detail.setItem9TrainContent(model.getItem9TrainContent());
			detail.setItem10Studentid(model.getItem10Studentid());
			detail.setItem10TrainContent(model.getItem10TrainContent());
			detail.setItem11Studentid(model.getItem11Studentid());
			detail.setItem11TrainContent(model.getItem11TrainContent());
			detail.setItem12Studentid(model.getItem12Studentid());
			detail.setItem12TrainContent(model.getItem12TrainContent());
			detail.setItem13Studentid(model.getItem13Studentid());
			detail.setItem13TrainContent(model.getItem13TrainContent());
			detail.setItem14Studentid(model.getItem14Studentid());
			detail.setItem14TrainContent(model.getItem14TrainContent());
			detail.setItem15Studentid(model.getItem15Studentid());
			detail.setItem15TrainContent(model.getItem15TrainContent());
			detail.setItem16Studentid(model.getItem16Studentid());
			detail.setItem16TrainContent(model.getItem16TrainContent());
			detail.setItem17Studentid(model.getItem17Studentid());
			detail.setItem17TrainContent(model.getItem17TrainContent());
			detail.setItem18Studentid(model.getItem18Studentid());
			detail.setItem18TrainContent(model.getItem18TrainContent());
			detail.setItem19Studentid(model.getItem19Studentid());
			detail.setItem19TrainContent(model.getItem19TrainContent());
			detail.setItem20Studentid(model.getItem20Studentid());
			detail.setItem20TrainContent(model.getItem20TrainContent());
			detail.setItem21Studentid(model.getItem21Studentid());
			detail.setItem21TrainContent(model.getItem21TrainContent());
			detail.setFiveItemTotal(model.getFiveItemTotal());
			detail.setRaodExamTotal(model.getRaodExamTotal());
			detail.setTrainerId(model.getTrainerId());

			TrainerReservationDetail reservation = reservationDetailService.udpate(detail);
			json.setMsg("编辑教练预约明细信息成功！");
			json.setSuccess(true);
			json.setObj(reservation);
		} catch (Exception e) {
			json.setMsg("编辑教练预约明细信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 编辑信息
	public void edit() {
		Json json = new Json();
		try {
			Reservation reservation = reservationService.udpate(model);
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
			reservationService.delete(model.getIds());
			json.setMsg("删除预约信息成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("删除预约信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 根据查询条件查找出所有参加理考的学员信息
	@SuppressWarnings("unchecked")
	public void getAllReservationStudents() throws Exception {
		// TODO 筛选学员参数设计
		// 1.根据student的model查找出所有符合查询条件的学员信息
		Student s = new Student();

		s.setDriverType(model.getStudentDriverType());
		s.setCreateTimeStart(model.getStudentCreateTimeStart());
		s.setCreateTimeEnd(model.getStudentCreateTimeEnd());
		s.setRestTiming(1);// 学员必须有学时才能预约
		if (model.getSchoolArea() != null) {
			s.setOrganizationName(model.getSchoolArea());
		}
		s.setName(model.getStudentName());
		s.setIdentityId(model.getStudentIdentityId());
		s.setStudentNo(model.getStudentNo());
		s.setNowState(Tstudent.NOWSTATE_INTRAINING);// 查找在培学员信息
		// 分页与排序属性配置
		s.setRows(model.getRows());
		s.setSort(model.getSort());
		s.setOrder(model.getOrder());
		s.setPage(model.getPage());

		DataGrid myDataGrid = studentService.datagrid(s);
		List<Student> tempStudents = myDataGrid.getRows();

		// 3.将student数据转换成subject展示
		List<Reservation> reservationLists = new ArrayList<Reservation>();
		if (tempStudents != null && tempStudents.size() > 0) {
			for (Student student : tempStudents) {
				Reservation reservation = new Reservation();
				reservation.setStudentOrganization(student.getOrganizationName());
				if (student.getClass() != null) {
					reservation.setStudentClazz(student.getClazzName());
				}
				reservation.setStudentSex(student.getSex());
				reservation.setStudentPhone(student.getPhone());
				reservation.setStudentIdentityId(student.getIdentityId());
				reservation.setStudentCreateTime(student.getCreateTime());
				reservation.setStudentDriverType(student.getDriverType());
				reservation.setStudentId(student.getId());
				reservation.setStudentType(student.getStudentType());
				reservation.setStudentName(student.getName());
				reservation.setStudentNo(student.getStudentNo());
				reservation.setStudentNowstate(student.getNowState());
				reservation.setStudentGraduateDate(student.getGraduateDate());
				reservation.setTimingFlag(student.getTimingFlag());
				if (ValidateUtil.isValid(student.getTrainerId())) {
					Ttrainer trainer = trainerService.getSingleById(student.getTrainerId());
					if (trainer != null) {
						reservation.setTrainerId(trainer.getId());
						reservation.setTrainerName(trainer.getName());
						reservation.setTrainerPhone(trainer.getPhone());
						reservation.setTrainerIdentityId(trainer.getIdentity());
						reservation.setTrainerTeachingType(trainer.getDriverType());
						reservation.setArrangeType(trainer.getArrangeType());
					}
				}
				reservationLists.add(reservation);
			}

		}
		// 4.设置返回前台的数据参数

		DataGrid retDatagrid = new DataGrid();
		retDatagrid.setRows(reservationLists);
		retDatagrid.setTotal(myDataGrid.getTotal());
		// 4.将数据返回前台
		super.writeJson(retDatagrid);
	}

	// 查找教练排班信息
	@SuppressWarnings("unchecked")
	public void getTrainerArrangeForReservation() {
		try {
			// 1.根据student的model查找出所有符合查询条件的学员信息
			Trainer trainerModel = new Trainer();
			// TODO 处理田阳威龙驾校：本部学员只能约本部教练，外包学员不通过此系统进行预约
			if (Torganization.TY_SCHOOLAREA.equals(user.getSchoolArea().trim())) {
				trainerModel.setTrainerType(model.getStudentType());
			} else {
				if (model.getSelectReservationType() != null && model.getSelectReservationType() > 0) {
					// 如果预约类型为五项路训，则限制本校约本校，承包约承包
					if (model.getSelectReservationType() == 3 || model.getSelectReservationType() == 4) {
						// trainerModel.setTrainerType(model.getTrainerType());
					}
				}

			}
			// 查询字段
			if (ValidateUtil.isValid(model.getTrainerName())) {
				trainerModel.setName(model.getTrainerName().trim());
			}
			if (ValidateUtil.isValid(model.getTrainerIdentityId())) {
				trainerModel.setIdentity(model.getTrainerIdentityId().trim());
			}
			if (ValidateUtil.isValid(model.getSchoolArea())) {
				trainerModel.setSchoolArea(model.getSchoolArea());
			}
			trainerModel.setArrangeFlag(1);// 有排班的教练
			// 教练排班类型查询
			if (model.getSelectReservationType() != null && model.getSelectReservationType() > 0) {
				if (1 == model.getSelectReservationType()) {
					trainerModel.setArrangeType(Ttrainer.ARRANGETYPE_ELECTRIC);
				} else if (2 == model.getSelectReservationType()) {
					trainerModel.setArrangeType(Ttrainer.ARRANGETYPE_SIMULATION);
				} else {
					trainerModel.setArrangeType(Ttrainer.ARRANGETYPE_OTHER);
				}
			}

			// 分页与排序属性配置
			trainerModel.setRows(model.getRows());
			trainerModel.setSort(model.getSort());
			trainerModel.setOrder(model.getOrder());
			trainerModel.setPage(model.getPage());

			DataGrid myDataGrid;

			myDataGrid = trainerService.datagrid(trainerModel);

			List<Trainer> trainers = myDataGrid.getRows();

			// 3.将trainerArrage数据转换成reservation展示
			List<Reservation> reservationLists = new ArrayList<Reservation>();
			if (trainers != null && trainers.size() > 0) {
				for (Trainer trainer : trainers) {
					Reservation entry = new Reservation();

					entry.setTrainerId(trainer.getId());
					entry.setTrainerCodeNo(trainer.getCodeNo());
					entry.setTrainerName(trainer.getName());
					entry.setTrainerIdentityId(trainer.getIdentity());
					entry.setTrainerPhone(trainer.getPhone());
					entry.setTrainerArrangeFlag(trainer.getArrangeFlag());
					entry.setArrangeType(trainer.getArrangeType());
					entry.setTrainerType(trainer.getTrainerType());
					// 教练最大排班日期
					entry.setTrainerNealyArrageDate(reservationDetailService.getLatestDateByTrainerId(trainer.getId(),
							trainer.getSchoolArea()));
					if (ValidateUtil.isValid(trainer.getSchoolArea())) {
						entry.setSchoolArea(organizationService.getSingleById(trainer.getSchoolArea()).getName());
					}
					reservationLists.add(entry);
				}

			}
			// 4.设置返回前台的数据参数

			DataGrid retDatagrid = new DataGrid();
			retDatagrid.setRows(reservationLists);
			retDatagrid.setTotal(myDataGrid.getTotal());
			// 4.将数据返回前台
			super.writeJson(retDatagrid);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
	}

	// 根据查询条件查找出所有参加理考的学员信息
	@SuppressWarnings("unchecked")
	public void getAllReservationTrainers() throws Exception {
		// TODO 筛选学员参数设计
		// 1.根据trainer的model查找出所有符合查询条件的教练信息
		Trainer trainer = new Trainer();
		trainer.setId(model.getTrainerId());
		trainer.setName(model.getTrainerName());
		trainer.setIdentity(model.getTrainerIdentityId());
		// 分页与排序属性配置
		trainer.setRows(model.getRows());
		trainer.setSort(model.getSort());
		trainer.setOrder(model.getOrder());
		trainer.setPage(model.getPage());

		DataGrid myDataGrid = trainerService.datagrid(trainer);
		List<Trainer> tempTrainers = myDataGrid.getRows();

		// 3.将trainer数据转换成Reservation展示
		List<Reservation> reservationLists = new ArrayList<Reservation>();
		if (tempTrainers != null && tempTrainers.size() > 0) {
			for (Trainer entry : tempTrainers) {
				Reservation r = new Reservation();
				r.setTrainerId(entry.getId());
				r.setTrainerCodeNo(entry.getCodeNo());
				r.setTrainerName(entry.getName());
				r.setTrainerIdentityId(entry.getIdentity());
				r.setTrainerPhone(entry.getPhone());
				reservationLists.add(r);
			}

		}
		// 4.设置返回前台的数据参数

		DataGrid retDatagrid = new DataGrid();
		retDatagrid.setRows(reservationLists);
		retDatagrid.setTotal(myDataGrid.getTotal());
		// 4.将数据返回前台
		super.writeJson(retDatagrid);
	}

	// 根据学员ID查找学员信息
	public void getSingStudentInformation() throws Exception {
		super.writeJson(studentService.getSingleById(model.getStudentId()));
	}

	// 根据学员信息查找该学员教练信息
	public void getTrainerByStudentId() throws Exception {
		super.writeJson(studentService.getTrainerByStudentId(model.getStudentId()));
	}

	// 根据教练标识查找出该教练所有的预约明细信息列表
	@SuppressWarnings("unchecked")
	public void getDetailReservationByTrainerId() throws Exception {
		// 1.根据TrainerReservationDetail的model查找出所有符合查询条件的TrainerReservationDetail信息
		TrainerReservationDetail detail = new TrainerReservationDetail();
		detail.setTrainerId(model.getDetailTrainerId());
		// TODO
		Map<String, Date> map = DateUtil.getDatesOfMonth(new Date());
		// detail.setReservationDateBegin(map.get("firstDateOfMonth"));
		detail.setReservationDateEnd(map.get("lastDateOfNextMonth"));

		// 分页与排序属性配置
		detail.setRows(model.getRows());
		detail.setSort(model.getSort());
		detail.setOrder(model.getOrder());
		detail.setPage(model.getPage());

		DataGrid myDataGrid = reservationDetailService.dataGrid(detail);
		List<TrainerReservationDetail> tempTrainers = myDataGrid.getRows();

		if (myDataGrid.getTotal() > 0) {
			// 3.将TrainerReservationDetail数据转换成Reservation展示
			List<Reservation> reservationLists = new ArrayList<Reservation>();
			if (tempTrainers != null && tempTrainers.size() > 0) {
				for (TrainerReservationDetail trainerReservationDetail : tempTrainers) {
					Ttrainer trainer = trainerService.getSingleById(trainerReservationDetail.getTrainerId());
					Reservation entry = new Reservation();

					entry.setReservationDetailId(trainerReservationDetail.getId());
					entry.setReservationDate(trainerReservationDetail.getReservationDate());
					entry.setDetailReservationType(trainerReservationDetail.getReservationType());

					entry.setItem7TrainContent(trainerReservationDetail.getItem7TrainContent());
					entry.setItem8TrainContent(trainerReservationDetail.getItem8TrainContent());
					entry.setItem9TrainContent(trainerReservationDetail.getItem9TrainContent());
					entry.setItem10TrainContent(trainerReservationDetail.getItem10TrainContent());
					entry.setItem11TrainContent(trainerReservationDetail.getItem11TrainContent());
					entry.setItem12TrainContent(trainerReservationDetail.getItem12TrainContent());
					entry.setItem13TrainContent(trainerReservationDetail.getItem13TrainContent());
					entry.setItem14TrainContent(trainerReservationDetail.getItem14TrainContent());
					entry.setItem15TrainContent(trainerReservationDetail.getItem15TrainContent());
					entry.setItem16TrainContent(trainerReservationDetail.getItem16TrainContent());
					entry.setItem17TrainContent(trainerReservationDetail.getItem17TrainContent());
					entry.setItem18TrainContent(trainerReservationDetail.getItem18TrainContent());
					entry.setItem19TrainContent(trainerReservationDetail.getItem19TrainContent());
					entry.setItem20TrainContent(trainerReservationDetail.getItem20TrainContent());
					entry.setItem21TrainContent(trainerReservationDetail.getItem21TrainContent());
					entry.setFiveItemTotal(trainerReservationDetail.getFiveItemTotal());
					entry.setRaodExamTotal(trainerReservationDetail.getRaodExamTotal());

					entry.setTrainerId(trainerReservationDetail.getTrainerId());
					entry.setTrainerReservationId(trainerReservationDetail.getTrainerReservationId());

					entry.setReservationFieldId7(trainerReservationDetail.getReservationFieldId7());
					entry.setReservationFieldId8(trainerReservationDetail.getReservationFieldId8());
					entry.setReservationFieldId9(trainerReservationDetail.getReservationFieldId9());
					entry.setReservationFieldId10(trainerReservationDetail.getReservationFieldId10());
					entry.setReservationFieldId11(trainerReservationDetail.getReservationFieldId11());
					entry.setReservationFieldId12(trainerReservationDetail.getReservationFieldId12());
					entry.setReservationFieldId13(trainerReservationDetail.getReservationFieldId13());
					entry.setReservationFieldId14(trainerReservationDetail.getReservationFieldId14());
					entry.setReservationFieldId15(trainerReservationDetail.getReservationFieldId15());
					entry.setReservationFieldId16(trainerReservationDetail.getReservationFieldId16());
					entry.setReservationFieldId17(trainerReservationDetail.getReservationFieldId17());
					entry.setReservationFieldId18(trainerReservationDetail.getReservationFieldId18());
					entry.setReservationFieldId19(trainerReservationDetail.getReservationFieldId19());
					entry.setReservationFieldId20(trainerReservationDetail.getReservationFieldId20());
					entry.setReservationFieldId21(trainerReservationDetail.getReservationFieldId21());

					entry.setReservation7MaxTotal(trainerReservationDetail.getReservation7MaxTotal());
					entry.setReservation8MaxTotal(trainerReservationDetail.getReservation8MaxTotal());
					entry.setReservation9MaxTotal(trainerReservationDetail.getReservation9MaxTotal());
					entry.setReservation10MaxTotal(trainerReservationDetail.getReservation10MaxTotal());
					entry.setReservation11MaxTotal(trainerReservationDetail.getReservation11MaxTotal());
					entry.setReservation12MaxTotal(trainerReservationDetail.getReservation12MaxTotal());
					entry.setReservation13MaxTotal(trainerReservationDetail.getReservation13MaxTotal());
					entry.setReservation14MaxTotal(trainerReservationDetail.getReservation14MaxTotal());
					entry.setReservation15MaxTotal(trainerReservationDetail.getReservation15MaxTotal());
					entry.setReservation16MaxTotal(trainerReservationDetail.getReservation16MaxTotal());
					entry.setReservation17MaxTotal(trainerReservationDetail.getReservation17MaxTotal());
					entry.setReservation18MaxTotal(trainerReservationDetail.getReservation18MaxTotal());
					entry.setReservation19MaxTotal(trainerReservationDetail.getReservation19MaxTotal());
					entry.setReservation20MaxTotal(trainerReservationDetail.getReservation20MaxTotal());
					entry.setReservation21MaxTotal(trainerReservationDetail.getReservation21MaxTotal());

					//
					String reservationDate = DateUtil.DateToString(trainerReservationDetail.getReservationDate());
					Date retDate = DateUtil.getDateByString(reservationDate);
					Integer reservationType = model.getReservationType();
					if (trainer.getArrangeType() == Ttrainer.ARRANGETYPE_ELECTRIC) {// 电车预约小于2该时段可以预约，
						if (trainerReservationDetail.getReservation7MaxTotal() < 2) {
							// 如果当前学员与当前教练在该时间已经存在预约则，显示‘您已预约’
							if (reservationService.checkExit(model.getStudentId(), retDate, 7, trainer.getSchoolArea(),
									reservationType)) {
								entry.setItem7Studentid("<div style=color:#0000CD>您已预约</div>");
							} else {
								entry.setItem7Studentid(null);
							}
						} else {// 返回该教练电车预约已满
							entry.setItem7Studentid("<div style=color:red>已约满</div>");
						}
						if (trainerReservationDetail.getReservation8MaxTotal() < 2) {
							// 如果当前学员与当前教练在该时间已经存在预约则，显示‘您已预约’
							if (reservationService.checkExit(model.getStudentId(), retDate, 8, trainer.getSchoolArea(),
									reservationType)) {
								entry.setItem8Studentid("<div style=color:#0000CD>您已预约</div>");
							} else {
								entry.setItem8Studentid(null);
							}
						} else {// 返回该教练电车预约已满
							entry.setItem8Studentid("<div style=color:red>已约满</div>");
						}
						if (trainerReservationDetail.getReservation9MaxTotal() < 2) {
							// 如果当前学员与当前教练在该时间已经存在预约则，显示‘您已预约’
							if (reservationService.checkExit(model.getStudentId(), retDate, 9, trainer.getSchoolArea(),
									reservationType)) {
								entry.setItem9Studentid("<div style=color:#0000CD>您已预约</div>");
							} else {
								entry.setItem9Studentid(null);
							}
						} else {// 返回该教练电车预约已满
							entry.setItem9Studentid("<div style=color:red>已约满</div>");
						}
						if (trainerReservationDetail.getReservation10MaxTotal() < 2) {
							// 如果当前学员与当前教练在该时间已经存在预约则，显示‘您已预约’
							if (reservationService.checkExit(model.getStudentId(), retDate, 10, trainer.getSchoolArea(),
									reservationType)) {
								entry.setItem10Studentid("<div style=color:#0000CD>您已预约</div>");
							} else {
								entry.setItem10Studentid(null);
							}
						} else {// 返回该教练电车预约已满
							entry.setItem10Studentid("<div style=color:red>已约满</div>");
						}
						if (trainerReservationDetail.getReservation11MaxTotal() < 2) {
							// 如果当前学员与当前教练在该时间已经存在预约则，显示‘您已预约’
							if (reservationService.checkExit(model.getStudentId(), retDate, 11, trainer.getSchoolArea(),
									reservationType)) {
								entry.setItem11Studentid("<div style=color:#0000CD>您已预约</div>");
							} else {
								entry.setItem11Studentid(null);
							}
						} else {// 返回该教练电车预约已满
							entry.setItem11Studentid("<div style=color:red>已约满</div>");
						}
						if (trainerReservationDetail.getReservation12MaxTotal() < 2) {
							// 如果当前学员与当前教练在该时间已经存在预约则，显示‘您已预约’
							if (reservationService.checkExit(model.getStudentId(), retDate, 12, trainer.getSchoolArea(),
									reservationType)) {
								entry.setItem12Studentid("<div style=color:#0000CD>您已预约</div>");
							} else {
								entry.setItem12Studentid(null);
							}
						} else {// 返回该教练电车预约已满
							entry.setItem12Studentid("<div style=color:red>已约满</div>");
						}
						if (trainerReservationDetail.getReservation13MaxTotal() < 2) {
							// 如果当前学员与当前教练在该时间已经存在预约则，显示‘您已预约’
							if (reservationService.checkExit(model.getStudentId(), retDate, 13, trainer.getSchoolArea(),
									reservationType)) {
								entry.setItem13Studentid("<div style=color:#0000CD>您已预约</div>");
							} else {
								entry.setItem13Studentid(null);
							}
						} else {// 返回该教练电车预约已满
							entry.setItem13Studentid("<div style=color:red>已约满</div>");
						}
						if (trainerReservationDetail.getReservation14MaxTotal() < 2) {
							// 如果当前学员与当前教练在该时间已经存在预约则，显示‘您已预约’
							if (reservationService.checkExit(model.getStudentId(), retDate, 14, trainer.getSchoolArea(),
									reservationType)) {
								entry.setItem14Studentid("<div style=color:#0000CD>您已预约</div>");
							} else {
								entry.setItem14Studentid(null);
							}
						} else {// 返回该教练电车预约已满
							entry.setItem14Studentid("<div style=color:red>已约满</div>");
						}
						if (trainerReservationDetail.getReservation15MaxTotal() < 2) {
							// 如果当前学员与当前教练在该时间已经存在预约则，显示‘您已预约’
							if (reservationService.checkExit(model.getStudentId(), retDate, 15, trainer.getSchoolArea(),
									reservationType)) {
								entry.setItem15Studentid("<div style=color:#0000CD>您已预约</div>");
							} else {
								entry.setItem15Studentid(null);
							}
						} else {// 返回该教练电车预约已满
							entry.setItem15Studentid("<div style=color:red>已约满</div>");
						}
						if (trainerReservationDetail.getReservation16MaxTotal() < 2) {
							// 如果当前学员与当前教练在该时间已经存在预约则，显示‘您已预约’
							if (reservationService.checkExit(model.getStudentId(), retDate, 16, trainer.getSchoolArea(),
									reservationType)) {
								entry.setItem16Studentid("<div style=color:#0000CD>您已预约</div>");
							} else {
								entry.setItem16Studentid(null);
							}
						} else {// 返回该教练电车预约已满
							entry.setItem16Studentid("<div style=color:red>已约满</div>");
						}
						if (trainerReservationDetail.getReservation17MaxTotal() < 2) {
							// 如果当前学员与当前教练在该时间已经存在预约则，显示‘您已预约’
							if (reservationService.checkExit(model.getStudentId(), retDate, 17, trainer.getSchoolArea(),
									reservationType)) {
								entry.setItem17Studentid("<div style=color:#0000CD>您已预约</div>");
							} else {
								entry.setItem17Studentid(null);
							}
						} else {// 返回该教练电车预约已满
							entry.setItem17Studentid("<div style=color:red>已约满</div>");
						}
						if (trainerReservationDetail.getReservation18MaxTotal() < 2) {
							// 如果当前学员与当前教练在该时间已经存在预约则，显示‘您已预约’
							if (reservationService.checkExit(model.getStudentId(), retDate, 18, trainer.getSchoolArea(),
									reservationType)) {
								entry.setItem18Studentid("<div style=color:#0000CD>您已预约</div>");
							} else {
								entry.setItem18Studentid(null);
							}
						} else {// 返回该教练电车预约已满
							entry.setItem18Studentid("<div style=color:red>已约满</div>");
						}
						if (trainerReservationDetail.getReservation19MaxTotal() < 2) {
							// 如果当前学员与当前教练在该时间已经存在预约则，显示‘您已预约’
							if (reservationService.checkExit(model.getStudentId(), retDate, 19, trainer.getSchoolArea(),
									reservationType)) {
								entry.setItem19Studentid("<div style=color:#0000CD>您已预约</div>");
							} else {
								entry.setItem19Studentid(null);
							}
						} else {// 返回该教练电车预约已满
							entry.setItem19Studentid("<div style=color:red>已约满</div>");
						}
						if (trainerReservationDetail.getReservation20MaxTotal() < 2) {
							// 如果当前学员与当前教练在该时间已经存在预约则，显示‘您已预约’
							if (reservationService.checkExit(model.getStudentId(), retDate, 20, trainer.getSchoolArea(),
									reservationType)) {
								entry.setItem20Studentid("<div style=color:#0000CD>您已预约</div>");
							} else {
								entry.setItem20Studentid(null);
							}
						} else {// 返回该教练电车预约已满
							entry.setItem20Studentid("<div style=color:red>已约满</div>");
						}
						if (trainerReservationDetail.getReservation21MaxTotal() < 2) {
							// 如果当前学员与当前教练在该时间已经存在预约则，显示‘您已预约’
							if (reservationService.checkExit(model.getStudentId(), retDate, 21, trainer.getSchoolArea(),
									reservationType)) {
								entry.setItem21Studentid("<div style=color:#0000CD>您已预约</div>");
							} else {
								entry.setItem21Studentid(null);
							}
						} else {// 返回该教练电车预约已满
							entry.setItem21Studentid("<div style=color:red>已约满</div>");
						}
					} else if (trainer.getArrangeType() == Ttrainer.ARRANGETYPE_SIMULATION) {// 模拟预约小于6该时段可以预约，
						if (trainerReservationDetail.getReservation7MaxTotal() < 6) {
							// 如果当前学员与当前教练在该时间已经存在预约则，显示‘您已预约’
							if (reservationService.checkExit(model.getStudentId(), retDate, 7, trainer.getSchoolArea(),
									reservationType)) {
								entry.setItem7Studentid("<div style=color:#0000CD>您已预约</div>");
							} else {
								entry.setItem7Studentid(null);
							}
						} else {// 返回该教练电车预约已满
							entry.setItem7Studentid("<div style=color:red>已约满</div>");
						}
						if (trainerReservationDetail.getReservation8MaxTotal() < 6) {
							// 如果当前学员与当前教练在该时间已经存在预约则，显示‘您已预约’
							if (reservationService.checkExit(model.getStudentId(), retDate, 8, trainer.getSchoolArea(),
									reservationType)) {
								entry.setItem8Studentid("<div style=color:#0000CD>您已预约</div>");
							} else {
								entry.setItem8Studentid(null);
							}
						} else {// 返回该教练电车预约已满
							entry.setItem8Studentid("<div style=color:red>已约满</div>");
						}
						if (trainerReservationDetail.getReservation9MaxTotal() < 6) {
							// 如果当前学员与当前教练在该时间已经存在预约则，显示‘您已预约’
							if (reservationService.checkExit(model.getStudentId(), retDate, 9, trainer.getSchoolArea(),
									reservationType)) {
								entry.setItem9Studentid("<div style=color:#0000CD>您已预约</div>");
							} else {
								entry.setItem9Studentid(null);
							}
						} else {// 返回该教练电车预约已满
							entry.setItem9Studentid("<div style=color:red>已约满</div>");
						}
						if (trainerReservationDetail.getReservation10MaxTotal() < 6) {
							// 如果当前学员与当前教练在该时间已经存在预约则，显示‘您已预约’
							if (reservationService.checkExit(model.getStudentId(), retDate, 10, trainer.getSchoolArea(),
									reservationType)) {
								entry.setItem10Studentid("<div style=color:#0000CD>您已预约</div>");
							} else {
								entry.setItem10Studentid(null);
							}
						} else {// 返回该教练电车预约已满
							entry.setItem10Studentid("<div style=color:red>已约满</div>");
						}
						if (trainerReservationDetail.getReservation11MaxTotal() < 6) {
							// 如果当前学员与当前教练在该时间已经存在预约则，显示‘您已预约’
							if (reservationService.checkExit(model.getStudentId(), retDate, 11, trainer.getSchoolArea(),
									reservationType)) {
								entry.setItem11Studentid("<div style=color:#0000CD>您已预约</div>");
							} else {
								entry.setItem11Studentid(null);
							}
						} else {// 返回该教练电车预约已满
							entry.setItem11Studentid("<div style=color:red>已约满</div>");
						}
						if (trainerReservationDetail.getReservation12MaxTotal() < 6) {
							// 如果当前学员与当前教练在该时间已经存在预约则，显示‘您已预约’
							if (reservationService.checkExit(model.getStudentId(), retDate, 12, trainer.getSchoolArea(),
									reservationType)) {
								entry.setItem12Studentid("<div style=color:#0000CD>您已预约</div>");
							} else {
								entry.setItem12Studentid(null);
							}
						} else {// 返回该教练电车预约已满
							entry.setItem12Studentid("<div style=color:red>已约满</div>");
						}
						if (trainerReservationDetail.getReservation13MaxTotal() < 6) {
							// 如果当前学员与当前教练在该时间已经存在预约则，显示‘您已预约’
							if (reservationService.checkExit(model.getStudentId(), retDate, 13, trainer.getSchoolArea(),
									reservationType)) {
								entry.setItem13Studentid("<div style=color:#0000CD>您已预约</div>");
							} else {
								entry.setItem13Studentid(null);
							}
						} else {// 返回该教练电车预约已满
							entry.setItem13Studentid("<div style=color:red>已约满</div>");
						}
						if (trainerReservationDetail.getReservation14MaxTotal() < 6) {
							// 如果当前学员与当前教练在该时间已经存在预约则，显示‘您已预约’
							if (reservationService.checkExit(model.getStudentId(), retDate, 14, trainer.getSchoolArea(),
									reservationType)) {
								entry.setItem14Studentid("<div style=color:#0000CD>您已预约</div>");
							} else {
								entry.setItem14Studentid(null);
							}
						} else {// 返回该教练电车预约已满
							entry.setItem14Studentid("<div style=color:red>已约满</div>");
						}
						if (trainerReservationDetail.getReservation15MaxTotal() < 6) {
							// 如果当前学员与当前教练在该时间已经存在预约则，显示‘您已预约’
							if (reservationService.checkExit(model.getStudentId(), retDate, 15, trainer.getSchoolArea(),
									reservationType)) {
								entry.setItem15Studentid("<div style=color:#0000CD>您已预约</div>");
							} else {
								entry.setItem15Studentid(null);
							}
						} else {// 返回该教练电车预约已满
							entry.setItem15Studentid("<div style=color:red>已约满</div>");
						}
						if (trainerReservationDetail.getReservation16MaxTotal() < 6) {
							// 如果当前学员与当前教练在该时间已经存在预约则，显示‘您已预约’
							if (reservationService.checkExit(model.getStudentId(), retDate, 16, trainer.getSchoolArea(),
									reservationType)) {
								entry.setItem16Studentid("<div style=color:#0000CD>您已预约</div>");
							} else {
								entry.setItem16Studentid(null);
							}
						} else {// 返回该教练电车预约已满
							entry.setItem16Studentid("<div style=color:red>已约满</div>");
						}
						if (trainerReservationDetail.getReservation17MaxTotal() < 6) {
							// 如果当前学员与当前教练在该时间已经存在预约则，显示‘您已预约’
							if (reservationService.checkExit(model.getStudentId(), retDate, 17, trainer.getSchoolArea(),
									reservationType)) {
								entry.setItem17Studentid("<div style=color:#0000CD>您已预约</div>");
							} else {
								entry.setItem17Studentid(null);
							}
						} else {// 返回该教练电车预约已满
							entry.setItem17Studentid("<div style=color:red>已约满</div>");
						}
						if (trainerReservationDetail.getReservation18MaxTotal() < 6) {
							// 如果当前学员与当前教练在该时间已经存在预约则，显示‘您已预约’
							if (reservationService.checkExit(model.getStudentId(), retDate, 18, trainer.getSchoolArea(),
									reservationType)) {
								entry.setItem18Studentid("<div style=color:#0000CD>您已预约</div>");
							} else {
								entry.setItem18Studentid(null);
							}
						} else {// 返回该教练电车预约已满
							entry.setItem18Studentid("<div style=color:red>已约满</div>");
						}
						if (trainerReservationDetail.getReservation19MaxTotal() < 6) {
							// 如果当前学员与当前教练在该时间已经存在预约则，显示‘您已预约’
							if (reservationService.checkExit(model.getStudentId(), retDate, 19, trainer.getSchoolArea(),
									reservationType)) {
								entry.setItem19Studentid("<div style=color:#0000CD>您已预约</div>");
							} else {
								entry.setItem19Studentid(null);
							}
						} else {// 返回该教练电车预约已满
							entry.setItem19Studentid("<div style=color:red>已约满</div>");
						}
						if (trainerReservationDetail.getReservation20MaxTotal() < 6) {
							// 如果当前学员与当前教练在该时间已经存在预约则，显示‘您已预约’
							if (reservationService.checkExit(model.getStudentId(), retDate, 20, trainer.getSchoolArea(),
									reservationType)) {
								entry.setItem20Studentid("<div style=color:#0000CD>您已预约</div>");
							} else {
								entry.setItem20Studentid(null);
							}
						} else {// 返回该教练电车预约已满
							entry.setItem20Studentid("<div style=color:red>已约满</div>");
						}
						if (trainerReservationDetail.getReservation21MaxTotal() < 6) {
							// 如果当前学员与当前教练在该时间已经存在预约则，显示‘您已预约’
							if (reservationService.checkExit(model.getStudentId(), retDate, 21, trainer.getSchoolArea(),
									reservationType)) {
								entry.setItem21Studentid("<div style=color:#0000CD>您已预约</div>");
							} else {
								entry.setItem21Studentid(null);
							}
						} else {// 返回该教练电车预约已满
							entry.setItem21Studentid("<div style=color:red>已约满</div>");
						}
					} else {// 五项，路训
						entry.setItem7Studentid(trainerReservationDetail.getItem7Studentid());
						entry.setItem8Studentid(trainerReservationDetail.getItem8Studentid());
						entry.setItem9Studentid(trainerReservationDetail.getItem9Studentid());
						entry.setItem10Studentid(trainerReservationDetail.getItem10Studentid());
						entry.setItem11Studentid(trainerReservationDetail.getItem11Studentid());
						entry.setItem12Studentid(trainerReservationDetail.getItem12Studentid());
						entry.setItem13Studentid(trainerReservationDetail.getItem13Studentid());
						entry.setItem14Studentid(trainerReservationDetail.getItem14Studentid());
						entry.setItem15Studentid(trainerReservationDetail.getItem15Studentid());
						entry.setItem16Studentid(trainerReservationDetail.getItem16Studentid());
						entry.setItem17Studentid(trainerReservationDetail.getItem17Studentid());
						entry.setItem18Studentid(trainerReservationDetail.getItem18Studentid());
						entry.setItem19Studentid(trainerReservationDetail.getItem19Studentid());
						entry.setItem20Studentid(trainerReservationDetail.getItem20Studentid());
						entry.setItem21Studentid(trainerReservationDetail.getItem21Studentid());
					}

					reservationLists.add(entry);
				}

				DataGrid retDatagrid = new DataGrid();
				retDatagrid.setRows(reservationLists);
				retDatagrid.setTotal(myDataGrid.getTotal());
				// 4.将数据返回前台
				super.writeJson(retDatagrid);
				return;
			}
		}
		super.writeJson(myDataGrid);
	}

	// 获取该教练所带的学员
	public void getStudentByTrainerId() {
		super.writeJson(studentService.getStudentByItTrainerId(model.getTrainerId()));
	}

	// 取消预约
	public void cancelReservation() {
		Json json = new Json();
		try {
			reservationService.cancel(model.getId(),0);
			json.setMsg("取消预约信息成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("取消预约信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 确认预约
	public void confirmReservation() {
		Json json = new Json();
		try {
			reservationService.confirmReservation(model.getId(), model.getConfirmFlag());
			json.setMsg("确认成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("确认失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 确认预约
	public void checkTotalByDate() throws Exception {
		Json json = new Json();
		if (reservationService.checkTotalByDate(model.getReservationStudentId(), model.getDate(),
				model.getReservationType())) {
			json.setSuccess(true);
		} else {
			json.setSuccess(false);
		}
		super.writeJson(json);
	}

	// 查找電車、模擬教練
	public void getElectricAndSimulations() {
		try {
			Map<String, List<Ttrainer>> trainerMaps = trainerService
					.getElectricAndSimulationTrainers(user.getSchoolArea());
			Map<String, Trainer> retMap = new HashMap<String, Trainer>();
			if (ValidateUtil.isValid(trainerMaps)) {
				List<Ttrainer> electricTrainers = trainerMaps.get("electricTrainer");
				List<Ttrainer> simulationTrainers = trainerMaps.get("simulationTrainer");
				Trainer trainer = null;
				if (electricTrainers != null && electricTrainers.size() > 0) {
					trainer = new Trainer();
					BeanUtils.copyProperties(electricTrainers.get(0), trainer);
					retMap.put("electricTrainer", trainer);
				}
				if (simulationTrainers != null && simulationTrainers.size() > 0) {
					trainer = new Trainer();
					BeanUtils.copyProperties(simulationTrainers.get(0), trainer);
					retMap.put("simulationTrainer", trainer);
				}
				Json json = new Json();
				if (retMap.size() > 0) {
					json.setObj(retMap);
					json.setMsg("获取教练信息成功！");
					json.setSuccess(true);
					super.writeJson(json);
				} else {
					json.setMsg("获取教练信息失败！");
					json.setSuccess(false);
					super.writeJson(json);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获取可预约的自动档车辆信息
	public void getAutocarForReservation() {
		try {
			super.writeJson(autoCarArrangeService.getAutoCarsForReservation(user.getSchoolArea(), model.getDate(),
					model.getFieldOptionFlag(),0));
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e);
		}
	}

	// 判断是否有C2车辆预约
	public void checkAutocarFree() {
		try {
			super.writeJson(autoCarArrangeService.checkAutocarFree(user.getSchoolArea(), model.getDate(),
					model.getFieldOptionFlag()));
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e);
		}
	}

}

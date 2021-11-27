package com.tuocheng.action.demo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.BaseAction;
import com.tuocheng.model.demo.Tstudent;
import com.tuocheng.model.demo.Ttrainer;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.base.ImportReturnModel;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.demo.ExamAnalys;
import com.tuocheng.pageModel.demo.ExamAnalys;
import com.tuocheng.pageModel.demo.Student;
import com.tuocheng.pageModel.demo.StudentExam;
import com.tuocheng.service.demo.POIUitlServiceI;
import com.tuocheng.service.demo.StudentExamServiceI;
import com.tuocheng.service.demo.StudentFileServiceI;
import com.tuocheng.service.demo.StudentServiceI;
import com.tuocheng.service.demo.TrainerSerivceI;
import com.tuocheng.util.base.ExceptionUtil;
import com.tuocheng.util.base.FileUtil;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 考试学员管理action
 * 
 * @author MEI702
 * 
 */
@Namespace("/demo")
@Action(value = "studentExamAction", results = {
		@Result(name = "studentExam", location = "/demo/studentExam/studentExam.jsp"),
		@Result(name = "uploadFile", location = "/demo/car/uploadFile.jsp"),
		@Result(name = "selectStudent", location = "/demo/studentExam/selectStudent.jsp"),
		@Result(name = "selectSubject", location = "/demo/studentExam/selectSubject.jsp"),
		@Result(name = "selectExamScore", location = "/demo/studentExam/selectExamScore.jsp"),
		@Result(name = "allStudentExam", location = "/demo/studentExam/allStudentExam.jsp"),
		@Result(name = "exportFile", type = "stream", params = { "contentType", "application/vnd.ms-excel",
				"contentDisposition", "attachment;filename=subject1.xls", "inputName",
				"excelFile" }, location = "/demo/car/exportFile.jsp"), })
public class StudentExamAction extends BaseAction<StudentExam> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 794769370809817909L;
	private static final Logger logger = Logger.getLogger(StudentExamAction.class);

	private StudentExamServiceI studentExamService;
	private StudentServiceI studentService;
	private TrainerSerivceI trainerService;
	private InputStream excelFile;
	private File uploadfile;
	private POIUitlServiceI poiService;

	@Autowired
	public void setStudentExamService(StudentExamServiceI studentExamService) {
		this.studentExamService = studentExamService;
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
	public void setPoiService(POIUitlServiceI poiService) {
		this.poiService = poiService;
	}

	// 到达考试学员管理主页面
	public String toStudentExamPage() {
		return "studentExam";
	}

	// 到达导入文件页面
	public String toUploadPage() {
		return "uploadFile";
	}

	// 到达选择考试学员页面
	public String toSelectStudentPage() {
		return "selectStudent";
	}

	public String toSelectSubjectPage() {
		return "selectSubject";
	}

	public String toSelectExamScorePage() {
		return "selectExamScore";
	}

	public String toAllStudentExamPage() {
		return "allStudentExam";
	}

	// 到达导出文件页面
	public String toExportPage() {
		try {
			if (ValidateUtil.isValid(model.getSchoolArea())) {
				Workbook workbook = poiService.exportStudentExamBySubjectType(model.getSchoolArea(), model.getIds(),
						model.getDatagridSubjectType());
				ByteArrayOutputStream output = new ByteArrayOutputStream();
				workbook.write(output);
				byte[] ba = output.toByteArray();
				excelFile = new ByteArrayInputStream(ba);
				output.flush();
				output.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "exportFile";
	}

	public void datagrid() {
		try {
			// 超级管理员有查找所有校区选项
			if (ValidateUtil.isValid(user.getSchoolArea())) {
				this.model.setSchoolArea(user.getSchoolArea());
			}
			super.writeJson(studentExamService.dataGrid(model));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 添加信息
	public void add() {
		Json json = new Json();
		try {
			StudentExam studentFile = studentExamService.save(model);
			json.setMsg("添加考试学员信息成功！");
			json.setSuccess(true);
			json.setObj(studentFile);
		} catch (Exception e) {
			json.setMsg("添加考试学员信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 编辑信息
	public void addStudentToExamAnalys() {
		Json json = new Json();
		try {
			boolean flag = studentExamService.addStudentToExamAnalys(model);
			if (flag) {
				json.setMsg("添加成功！");
				json.setSuccess(true);
			} else {
				json.setMsg("添加失败！");
				json.setSuccess(false);
			}
		} catch (Exception e) {
			json.setMsg("添加失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	/**
	 * 移动学员到目标批次
	 */
	public void addAndMoveStudentToNewBatch() {
		Json json = new Json();
		try {
			boolean flag = studentExamService.saveStudentToNewBatch(model);
			if (flag) {
				json.setMsg("移动学员成功，总共移动:" + model.getMoveTotal() + "个学员！");
				json.setSuccess(true);
			} else {
				json.setMsg("移动学员失败！");
				json.setSuccess(false);
			}
		} catch (Exception e) {
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 编辑信息
	public void edit() {
		Json json = new Json();
		try {
			StudentExam studentFile = studentExamService.udpate(model);
			json.setMsg("编辑考试学员信息成功！");
			json.setSuccess(true);
			json.setObj(studentFile);
		} catch (Exception e) {
			json.setMsg("编辑考试学员信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 删除信息
	public void delete() {
		Json json = new Json();
		try {
			studentExamService.delete(model.getIds());
			json.setMsg("删除考试学员信息成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("删除考试学员信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);

	}

	// 删除考试学员信息
	public void deleteStudentExam() {
		Json json = new Json();
		try {
			studentExamService.deleteStudentExam(model.getIds());
			json.setMsg("删除考试学员信息成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("删除考试学员信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);

	}

	// 按批次批量修改学员信息
	public void batchUpdateStudentExam() {
		Json json = new Json();
		try {
			studentExamService.batchHandupExam(model);
			json.setMsg("操作成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("操作失败！");
			json.setSuccess(false);
		}
		super.writeJson(json);
	}

	public void batchCancelHandup() {
		Json json = new Json();
		try {
			studentExamService.batchCancelHandup(model.getIds(), model.getSubjectType());
			json.setMsg("操作成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("操作失败！");
			json.setSuccess(false);
		}
		super.writeJson(json);
	}

	public void importExamStudentDatas() throws Exception {

		String directory = "/file";
		String fileName = uploadfile.getName();
		String targetDirectory = ServletActionContext.getServletContext().getRealPath(directory);
		File target = FileUtil.UploadFile(uploadfile, fileName, targetDirectory);
		excelFile = new FileInputStream(target);
		Workbook wb = new HSSFWorkbook(excelFile);
		ImportReturnModel reVals = studentExamService.importDatasFromExcel(wb, user.getSchoolArea(), user.getCname(),
				model.getExamAnalysId());

		// 组织返回参数
		Json json = new Json();
		if (reVals.isSuccess()) {// 导入成功，不存在重复字段
			json.setSuccess(true);
			json.setMsg("数据导入成功 ,合计导入：《" + reVals.getSize() + "》 条记录！");
		} else {
			json.setMsg("导入失败学员：本系统中不存在以下学员：[" + reVals.getFailDatas() + "], 已经报考不能重复报考学员如下：]"
					+ reVals.getSuccessDatas() + "]");
			json.setSuccess(false);
		}
		super.writeJson(json);
	}
	
	public void importExamScores() throws Exception {

		String directory = "/file";
		String fileName = uploadfile.getName();
		String targetDirectory = ServletActionContext.getServletContext().getRealPath(directory);
		File target = FileUtil.UploadFile(uploadfile, fileName, targetDirectory);
		excelFile = new FileInputStream(target);
		Workbook wb = new HSSFWorkbook(excelFile);
		ImportReturnModel reVals = studentExamService.importStudentScore(wb, user.getSchoolArea(), user.getCname(),
				model.getExamAnalysId(),model.getSubjectType());

		// 组织返回参数
		Json json = new Json();
		if (reVals.isSuccess()) {// 导入成功，不存在重复字段
			json.setSuccess(true);
			json.setMsg("数据导入成功 ,合计导入：《" + reVals.getSize() + "》 条记录！");
		} else {
			json.setMsg("导入失败学员：本系统中不存在以下学员：[" + reVals.getFailDatas() + "], 已经报考不能重复报考学员如下：]"
					+ reVals.getSuccessDatas() + "]");
			json.setSuccess(false);
		}
		super.writeJson(json);
	}


	@SuppressWarnings("unchecked")
	public void toGetAllReservationStudents() throws Exception {
		// TODO 筛选学员参数设计
		// 1.根据student的model查找出所有符合查询条件的学员信息
		Student entry = new Student();

		entry.setDriverType(model.getStudentDriverType());
		entry.setCreateTimeStart(model.getStudentCreateTimeStart());
		entry.setCreateTimeEnd(model.getStudentCreateTimeEnd());
		if (model.getSchoolArea() != null) {
			entry.setOrganizationName(model.getSchoolArea());
		}
		entry.setName(model.getStudentName());
		entry.setIdentityId(model.getStudentIdentityId());
		entry.setStudentNo(model.getStudentNo());
		entry.setNowState(Tstudent.NOWSTATE_INTRAINING);// 查找在培学员信息

		entry.setBatch(0);
		// 分页与排序属性配置
		entry.setRows(model.getRows());
		entry.setSort(model.getSort());
		entry.setOrder(model.getOrder());
		entry.setPage(model.getPage());

		DataGrid myDataGrid = studentService.datagrid(entry);
		List<Student> tempStudents = myDataGrid.getRows();

		// 3.将student数据转换成subject展示
		List<ExamAnalys> reservationLists = new ArrayList<ExamAnalys>();
		if (tempStudents != null && tempStudents.size() > 0) {
			for (Student student : tempStudents) {
				ExamAnalys examAnalys = new ExamAnalys();
				examAnalys.setStudentOrganization(student.getOrganizationName());
				if (student.getClass() != null) {
					examAnalys.setStudentClazz(student.getClazzName());
				}
				examAnalys.setStudentSex(student.getSex());
				examAnalys.setStudentPhone(student.getPhone());
				examAnalys.setStudentIdentityId(student.getIdentityId());
				examAnalys.setStudentCreateTime(student.getCreateTime());
				examAnalys.setStudentDriverType(student.getDriverType());
				examAnalys.setStudentId(student.getId());
				examAnalys.setStudentType(student.getStudentType());
				examAnalys.setStudentName(student.getName());
				examAnalys.setStudentNo(student.getStudentNo());
				examAnalys.setStudentNowstate(student.getNowState());
				examAnalys.setStudentGraduateDate(student.getGraduateDate());
				examAnalys.setStudentTimingFlag(student.getTimingFlag());
				examAnalys.setStudentBatch(student.getBatch());
				if (ValidateUtil.isValid(student.getTrainerId())) {
					Ttrainer trainer = trainerService.getSingleById(student.getTrainerId());
					if (trainer != null) {
						examAnalys.setStudentTrainerId(trainer.getId());
						examAnalys.setStudentTrainerName(trainer.getName());
						examAnalys.setStudentTrainerPhone(trainer.getPhone());
						examAnalys.setStudentTrainerIdentityId(trainer.getIdentity());
						examAnalys.setStudentTrainerArrangeType(trainer.getArrangeType());
					}
				}
				reservationLists.add(examAnalys);
			}

		}
		// 4.设置返回前台的数据参数

		DataGrid retDatagrid = new DataGrid();
		retDatagrid.setRows(reservationLists);
		retDatagrid.setTotal(myDataGrid.getTotal());
		// 4.将数据返回前台
		super.writeJson(retDatagrid);
	}

	// 根据学员信息查找出学员考试信息（累加查询）
	public void toGetStudentExamForRepeatSearch() {
		StudentExam studentExam=null;
		try {
			studentExam = studentExamService.getStudentExamForRepeatSearch(model.getSchoolArea(),
					model.getStudentNo(), model.getStudentName(), model.getStudentId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(studentExam!=null){
			super.writeJson(studentExam);
		}

	}

	public File getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(File uploadfile) {
		this.uploadfile = uploadfile;
	}

	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}

}

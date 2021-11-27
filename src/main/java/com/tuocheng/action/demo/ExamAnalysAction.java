package com.tuocheng.action.demo;

import java.io.File;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.BaseAction;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.demo.ExamAnalys;
import com.tuocheng.service.demo.ExamAnalysServiceI;
import com.tuocheng.service.demo.OilCardServiceI;
import com.tuocheng.util.base.ExceptionUtil;
import com.tuocheng.util.base.ValidateUtil;

@Namespace("/demo")
@Action(value = "examAnalysAction", results = {
		@Result(name = "examAnalys", location = "/demo/studentExam/examAnalys.jsp"),
		@Result(name = "addExamAnalys", location = "/demo/studentExam/addExamAnalys.jsp"),
		@Result(name = "editExamAnalys", location = "/demo/studentExam/editExamAnalys.jsp"),
		@Result(name = "addStudentExam", location = "/demo/studentExam/addStudentExam.jsp"),
		@Result(name = "moveStudent", location = "/demo/studentExam/moveStudent.jsp"),
		@Result(name = "subject1", location = "/demo/studentExam/subject1.jsp"),
		@Result(name = "subject2", location = "/demo/studentExam/subject2.jsp"),
		@Result(name = "subject3", location = "/demo/studentExam/subject3.jsp"),
		@Result(name = "subject4", location = "/demo/studentExam/subject4.jsp"),
		@Result(name = "subject5", location = "/demo/studentExam/subject5.jsp"),})
public class ExamAnalysAction extends BaseAction<ExamAnalys> {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 5671229218093101057L;
	public static final Logger logger = Logger
			.getLogger(ExamAnalysAction.class);

	private InputStream excelFile;
	private File uploadfile;
	private String uploadfileName;
	private ExamAnalysServiceI examAnalysService;

	@Autowired
	public void setExamAnalysService(ExamAnalysServiceI examAnalysService) {
		this.examAnalysService = examAnalysService;
	}

	// 达到主页面
	public String toExamAnalysPage() {
		return "examAnalys";
	}

	// 到达添加页面
	public String toAddExamAnalysPage() {
		return "addExamAnalys";
	}

	// 到达更新页面
	public String toEditExamAnalysPage() {
		return "editExamAnalys";
	}

	// 到达添加考试学员信息页面
	public String toAddStudentExamPage() {
		return "addStudentExam";
	}

	public String toMoveStudentPage() {
		return "moveStudent";
	}

	public String toSubject1Page() {
		return "subject1";
	}

	public String toSubject2Page() {
		return "subject2";
	}

	public String toSubject3Page() {
		return "subject3";
	}

	public String toSubject4Page() {
		return "subject4";
	}

	public String toSubject5Page() {
		return "subject5";
	}

	// 调用后台数据并传递给前台,
	public void datagrid() throws Exception {
		// 超级管理员有查找所有校区选项
		if (ValidateUtil.isValid(user.getSchoolArea())) {
			this.model.setSchoolArea(user.getSchoolArea());
		}
		super.writeJson(examAnalysService.dataGrid(model));
	}

	// 添加考试批次信息
	public void add() {
		Json json = new Json();
		try {
			ExamAnalys oilcard = examAnalysService.save(model);
			json.setMsg("添加考试批次信息成功！");
			json.setSuccess(true);
			// 将成功添加后的数据回传到前台
			json.setObj(oilcard);
		} catch (Exception e) {
			json.setMsg("添加考试批次信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 更新考试批次信息
	public void edit() {
		Json json = new Json();
		try {
			ExamAnalys oilcard = examAnalysService.udpate(model);
			json.setMsg("修改考试批次信息成功！");
			json.setSuccess(true);
			// 将成功修改后的数据回传到前台
			json.setObj(oilcard);
		} catch (Exception e) {
			json.setMsg("修改考试批次信息失败！");
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
		super.writeJson(json);
	}

	// 删除考试批次信息
	public void delete() throws Exception {
		Json json = new Json();
		try {
			examAnalysService.delete(model.getIds());
			json.setMsg("删除考试批次信息成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("删除考试批次信息失败！");
			e.printStackTrace();
		}
		super.writeJson(json);

	}

	public void doNotNeedSession_getMaxBatchByschoolArea() {
		try {
			super.writeJson(examAnalysService.getMaxBatchByschoolArea(model
					.getSelectSchoolArea()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}

	public File getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(File uploadfile) {
		this.uploadfile = uploadfile;
	}

	public String getUploadfileName() {
		return uploadfileName;
	}

	public void setUploadfileName(String uploadfileName) {
		this.uploadfileName = uploadfileName;
	}

}

package com.tuocheng.action.demo;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.BaseAction;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.demo.StudentFile;
import com.tuocheng.service.demo.StudentFileServiceI;
import com.tuocheng.util.base.ExceptionUtil;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 档案管理action
 * 
 * @author MEI702
 * 
 */
@Namespace("/demo")
@Action(value = "studentFileAction", results = {
		@Result(name = "studentFile", location = "/demo/studentFile/studentFile.jsp"),
		@Result(name = "studentFileEdit", location = "/demo/studentFile/studentFileEdit.jsp"),
		@Result(name = "detail", location = "/demo/studentFile/detail.jsp"),})
public class StudentFileAction extends BaseAction<StudentFile> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 794769370809817909L;
	private static final Logger logger = Logger
			.getLogger(StudentFileAction.class);

	private StudentFileServiceI studentFileService;

	@Autowired
	public void setStudentFileService(StudentFileServiceI studentFileService) {
		this.studentFileService = studentFileService;
	}

	// 到达档案管理主页面
	public String toStudentFilePage() {
		return "studentFile";
	}

	// 到达编辑页面
	public String toStudentFileEditPage() {
		return "studentFileEdit";
	}
	
	// 到达详细信息页面
	public String toStudentFileDetailPage() {
		return "detail";
	}
	

	public void datagrid() {
		try {
			// 超级管理员有查找所有校区选项
			if (ValidateUtil.isValid(user.getSchoolArea())) {
				this.model.setSchoolArea(user.getSchoolArea());
			}
			super.writeJson(studentFileService.dataGrid(model));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 添加信息
	public void add() {
		Json json = new Json();
		try {
			StudentFile studentFile = studentFileService.save(model);
			json.setMsg("添加档案信息成功！");
			json.setSuccess(true);
			json.setObj(studentFile);
		} catch (Exception e) {
			json.setMsg("添加档案信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 编辑信息
	public void edit() {
		Json json = new Json();
		try {
			StudentFile studentFile = studentFileService.udpate(model);
			json.setMsg("编辑档案信息成功！");
			json.setSuccess(true);
			json.setObj(studentFile);
		} catch (Exception e) {
			json.setMsg("编辑档案信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 删除信息
	public void delete() {
		Json json = new Json();
		try {
			studentFileService.delete(model.getIds());
			json.setMsg("删除档案信息成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("删除档案信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);

	}

}

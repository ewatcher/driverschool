package com.tuocheng.action.demo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.BaseAction;
import com.tuocheng.model.demo.Tstudent;
import com.tuocheng.pageModel.base.ComboboxJson;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.demo.Student;
import com.tuocheng.pageModel.demo.DriverLicense;
import com.tuocheng.service.demo.StudentServiceI;
import com.tuocheng.service.demo.DriverLicenseServiceI;
import com.tuocheng.util.base.ExceptionUtil;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 驾驶证管理action
 * 
 * @author MEI702
 * 
 */
@Namespace("/demo")
@Action(value = "driverLicenseAction", results = {
		@Result(name = "driverLicense", location = "/demo/driverLicense/driverLicense.jsp"),
		@Result(name = "driverLicenseAdd", location = "/demo/driverLicense/driverLicenseAdd.jsp"),
		@Result(name = "driverLicenseAddInput", location = "/demo/driverLicense/driverLicenseAddInput.jsp"),
		@Result(name = "driverLicenseEdit", location = "/demo/driverLicense/driverLicenseEdit.jsp"),
		@Result(name = "detail", location = "/demo/driverLicense/detail.jsp"),
		@Result(name = "myDetail", location = "/demo/driverLicense/myDetail.jsp"),
		@Result(name = "myDriverLicense", location = "/demo/driverLicense/myDriverLicense.jsp"), })
public class DriverLicenseAction extends BaseAction<DriverLicense> {

	/**
	 * 序列化标识
	 */
	private static final long serialVersionUID = -676166711198031916L;

	private static final Logger logger = Logger.getLogger(DriverLicenseAction.class);

	private DriverLicenseServiceI driverLicenseService;
	private StudentServiceI studentService;

	@Autowired
	public void setDriverLicenseService(DriverLicenseServiceI driverLicenseService) {
		this.driverLicenseService = driverLicenseService;
	}

	@Autowired
	public void setStudentService(StudentServiceI studentService) {
		this.studentService = studentService;
	}

	// 到达驾驶证管理主页面
	public String toDriverLicensePage() {
		return "driverLicense";
	}

	// 到达添加驾驶证页面
	public String toDriverLicenseAddPage() {
		return "driverLicenseAdd";
	}

	// 到达添加驾驶证添加输入页面
	public String toDriverLicenseAddInputPage() {
		return "driverLicenseAddInput";
	}

	// 到达编辑页面
	public String toDriverLicenseEditPage() {
		return "driverLicenseEdit";
	}

	// 到达详细信息页面
	public String toDriverLicenseDetailPage() {
		return "detail";
	}

	// 到达学员个人驾驶证页面
	public String toMyDriverLicensePage() {
		return "myDriverLicense";
	}

	// 到达学员个人驾驶证详细信息
	public String toMyDetailPage() {
		return "myDetail";
	}

	public void datagrid() {
		// 超级管理员有查找所有校区选项
		if (ValidateUtil.isValid(user.getSchoolArea())) {
			this.model.setSchoolArea(user.getSchoolArea());
		}
		super.writeJson(driverLicenseService.dataGrid(model));
	}

	// 学员个人驾驶证数据列表
	public void personalDatagrid() {
		// 超级管理员有查找所有校区选项
		if (ValidateUtil.isValid(user.getSchoolArea())) {
			this.model.setSchoolArea(user.getSchoolArea());
			model.setStudentIdentityId(user.getCname().trim());
		}
		super.writeJson(driverLicenseService.personalDataGrid(model));
	}

	// 添加信息
	public void add() {
		Json json = new Json();
		try {
			model.setOperator(user.getCname().trim());
			DriverLicense theory = driverLicenseService.save(model);
			json.setMsg("添加驾驶证信息成功！");
			json.setSuccess(true);
			json.setObj(theory);
		} catch (Exception e) {
			json.setMsg("添加驾驶证信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 编辑信息
	public void edit() {
		Json json = new Json();
		try {
			DriverLicense theory = driverLicenseService.udpate(model);
			json.setMsg("编辑驾驶证信息成功！");
			json.setSuccess(true);
			json.setObj(theory);
		} catch (Exception e) {
			json.setMsg("编辑驾驶证信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 删除信息
	public void delete() {
		Json json = new Json();
		try {
			driverLicenseService.delete(model.getIds());
			json.setMsg("删除驾驶证信息成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("删除驾驶证信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}


	// 根据学员ID查找学员信息
	public void getSingStudentInformation() throws Exception {
		Tstudent stu = studentService.getSingleById(model.getStudentId());
		if (stu != null) {
			Student student = new Student();
			BeanUtils.copyProperties(stu, student);
			super.writeJson(student);
		}
	}
}

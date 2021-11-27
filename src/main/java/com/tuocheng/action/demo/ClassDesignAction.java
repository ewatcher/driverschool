package com.tuocheng.action.demo;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.BaseAction;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.demo.ClassDesign;
import com.tuocheng.service.demo.ClassServiceI;
import com.tuocheng.util.base.ExceptionUtil;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 班级开设管理action
 * 
 * @author MEI702
 * 
 */
@Namespace("/demo")
@Action(value = "classDesignAction", results = {
		@Result(name = "clazzDesign", location = "/demo/clazzDesign/clazzDesign.jsp"),
		@Result(name = "clazzDesignAdd", location = "/demo/clazzDesign/clazzDesignAdd.jsp"),
		@Result(name = "clazzDesignEdit", location = "/demo/clazzDesign/clazzDesignEdit.jsp"), })
public class ClassDesignAction extends BaseAction<ClassDesign> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 794769370809817909L;
	private static final Logger logger = Logger
			.getLogger(ClassDesignAction.class);

	private ClassServiceI clazzServiceI;

	@Autowired
	public void setClazzServiceI(ClassServiceI clazzServiceI) {
		this.clazzServiceI = clazzServiceI;
	}

	// 到达班级开设管理主页面
	public String toClassDesignPage() {
		return "clazzDesign";
	}

	// 到达添加班级开设页面
	public String toClassDesignAddPage() {
		return "clazzDesignAdd";
	}

	// 到达编辑页面
	public String toClassDesignEditPage() {
		return "clazzDesignEdit";
	}

	public void datagrid() throws Exception {
		// 超级管理员有查找所有校区选项
		if (ValidateUtil.isValid(user.getSchoolArea())) {
			this.model.setSchoolArea(user.getSchoolArea());
		}
		super.writeJson(clazzServiceI.dataGrid(model));
	}

	// 添加信息
	public void add() {
		Json json = new Json();
		try {
			model.setOperator(user.getCname().trim());
			ClassDesign person = clazzServiceI.save(model);
			json.setMsg("添加班级开设信息成功！");
			json.setSuccess(true);
			json.setObj(person);
		} catch (Exception e) {
			json.setMsg("添加班级开设信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 编辑信息
	public void edit() {
		Json json = new Json();
		try {
			ClassDesign person = clazzServiceI.udpate(model);
			json.setMsg("编辑班级开设信息成功！");
			json.setSuccess(true);
			json.setObj(person);
		} catch (Exception e) {
			json.setMsg("编辑班级开设信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 删除信息
	public void delete() {
		Json json = new Json();
		try {
			clazzServiceI.delete(model.getIds());
			json.setMsg("删除班级开设信息成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("删除班级开设信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);

	}

}

package com.tuocheng.action.demo;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.BaseAction;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.demo.StudentTimerCard;
import com.tuocheng.service.demo.StudentTimerCardServiceI;
import com.tuocheng.util.base.ExceptionUtil;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 学员计时卡action
 * 
 * @author 李杰
 * 
 */

@Namespace("/demo")
@Action(value = "studentTimerCardAction", results = { @Result(name = "studentTimerCard", location = "/demo/timerCard/studentTimerCard.jsp"), 
		@Result(name = "studentPersonalTimerCard", location = "/demo/timerCard/studentPersonalTimerCard.jsp"),})
public class StudentTimerCardAction extends BaseAction<StudentTimerCard> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 82347590280931L;

	/**
	 * 序列号
	 */
	private static final Logger log = Logger
			.getLogger(StudentTimerCardAction.class);

	private StudentTimerCardServiceI stcService;

	@Autowired
	public void setStudentTimerCardService(StudentTimerCardServiceI stcService) {
		this.stcService = stcService;
	}

	// 到达学生计时卡主页面
	public String studentTimerCard() {
		return "studentTimerCard";
	}

	// 到达学员个人计时卡详细信息
	public String toStudentPersonalTimerCardPage() {
		return "studentPersonalTimerCard";
	}

	// 从后台中查找出所有实体类信息
	public void datagrid() throws Exception {
		// 超级管理员有查找所有校区选项
		if (ValidateUtil.isValid(user.getSchoolArea())) {
			this.model.setOrganizationId(user.getSchoolArea());
		}
		super.writeJson(stcService.dataGrid(model));
	}

	// 学员计时表
	public void personalDatagrid() throws Exception {
		// 超级管理员有查找所有校区选项
		if (ValidateUtil.isValid(user.getSchoolArea())) {
			this.model.setOrganizationId(user.getSchoolArea());
			model.setIdentityId(user.getCname());
		}
		super.writeJson(stcService.personalDataGrid(model));
	}

	// 更新实体类信息
	public void edit() {
		Json json = new Json();
		try {
			StudentTimerCard stc = stcService.update(model);
			json.setSuccess(true);
			json.setMsg("修改数据成功！");
			json.setObj(stc);
		} catch (Exception e) {
			json.setMsg("修改数据失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		// 将数据返回给前台
		super.writeJson(json);
	}

}

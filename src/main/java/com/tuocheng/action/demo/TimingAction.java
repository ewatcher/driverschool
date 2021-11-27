package com.tuocheng.action.demo;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.BaseAction;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.demo.Timing;
import com.tuocheng.service.demo.TimingServiceI;
import com.tuocheng.util.base.ExceptionUtil;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 学员学时管理action
 * 
 * @author MEI702
 * 
 */
@Namespace("/demo")
@Action(value = "timingAction", results = {
		@Result(name = "timing", location = "/demo/timing/timing.jsp"),
		@Result(name = "timingEdit", location = "/demo/timing/timingEdit.jsp"),
		@Result(name = "personalTiming", location = "/demo/timing/personalTiming.jsp"), 
		@Result(name = "myTiming", location = "/demo/timing/myTiming.jsp"), })
public class TimingAction extends BaseAction<Timing> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 794769370809817909L;
	private static final Logger logger = Logger.getLogger(TimingAction.class);

	private TimingServiceI timingService;

	@Autowired
	public void setTimingService(TimingServiceI timingService) {
		this.timingService = timingService;
	}

	// 到达学员学时管理主页面
	public String toTimingPage() {
		return "timing";
	}
	
	//到达添加学员个人学时页面
	public String toPersonalTimingPage(){
		return "personalTiming";
	}

	// 到达编辑页面
	public String toTimingEditPage() {
		return "timingEdit";
	}
	
	public String toMyTimingPage() {
		return "myTiming";
	}

	public void datagrid() {
		try {
			// 超级管理员有查找所有校区选项
			if (ValidateUtil.isValid(user.getSchoolArea())) {
				this.model.setSchoolArea(user.getSchoolArea());
			}
			super.writeJson(timingService.dataGrid(model));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//学员个人学时信息
	public void personalDatagrid() {
		try {
			// 超级管理员有查找所有校区选项
			if (ValidateUtil.isValid(user.getSchoolArea())) {
				this.model.setSchoolArea(user.getSchoolArea());
				model.setStudentIdentityId(user.getCname().trim());
			}
			super.writeJson(timingService.personalDataGrid(model));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 添加信息
	public void add() {
		Json json = new Json();
		try {
			Timing timing = timingService.save(model);
			json.setMsg("添加学员学时信息成功！");
			json.setSuccess(true);
			json.setObj(timing);
		} catch (Exception e) {
			json.setMsg("添加学员学时信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 编辑信息
	public void edit() {
		Json json = new Json();
		try {
			model.setSchoolArea(user.getSchoolArea());
			Timing timing = timingService.udpate(model);
			json.setMsg("编辑学员学时信息成功！");
			json.setSuccess(true);
			json.setObj(timing);
		} catch (Exception e) {
			json.setMsg("编辑学员学时信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 删除信息
	public void delete() {
		Json json = new Json();
		try {
			timingService.delete(model.getIds());
			json.setMsg("删除学员学时信息成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("删除学员学时信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);

	}

}

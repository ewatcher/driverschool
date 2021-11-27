package com.tuocheng.action.demo;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.BaseAction;
import com.tuocheng.model.demo.Ttiming;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.demo.PersonalTiming;
import com.tuocheng.service.demo.PersonalTimingServiceI;
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
@Action(value = "personalTimingAction", results = {
		@Result(name = "personalTimingAdd", location = "/demo/timing/personalTimingAdd.jsp"),
		@Result(name = "personalTimingAddInput", location = "/demo/timing/personalTimingAddInput.jsp"),
		@Result(name = "personalTimingUpdateInput", location = "/demo/timing/personalTimingUpdateInput.jsp"),
		@Result(name = "personalTimingEdit", location = "/demo/timing/personalTimingEdit.jsp"),
		@Result(name = "myDetailTiming", location = "/demo/timing/myDetailTiming.jsp"),
		@Result(name = "buyTimingAddInput", location = "/demo/timing/buyTimingAddInput.jsp"), })
public class PersonalTimingAction extends BaseAction<PersonalTiming> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 794769370809817909L;
	private static final Logger logger = Logger
			.getLogger(PersonalTimingAction.class);

	private PersonalTimingServiceI personalTimingService;
	private TimingServiceI timingService;

	@Autowired
	public void setPersonalTimingService(
			PersonalTimingServiceI personalTimingService) {
		this.personalTimingService = personalTimingService;
	}

	@Autowired
	public void setTimingService(TimingServiceI timingService) {
		this.timingService = timingService;
	}

	// 到达添加学员个人学时添加页面
	public String toPersonalTimingAddPage() {
		return "personalTimingAdd";
	}

	// 到达添加学员个人学时添加输入页面
	public String toPersonalTimingAddInputPage() {
		return "personalTimingAddInput";
	}

	// 到达添加学员个人学时添加输入页面
	public String toPersonalBuyTimingPage() {
		return "buyTimingAddInput";
	}

	// 到达添加学员个人学时添加输入页面
	public String toPersonalTimingUpdateInputPage() {
		return "personalTimingUpdateInput";
	}

	// 到达学员个人学时明细信息
	public String toMyDetailTimingPage() {
		return "myDetailTiming";
	}

	public void datagrid() {
		try {
			// 超级管理员有查找所有校区选项
			if (ValidateUtil.isValid(user.getSchoolArea())) {
				this.model.setSchoolArea(user.getSchoolArea());
			}
			super.writeJson(personalTimingService.dataGrid(model));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 添加信息
	public void add() {
		Json json = new Json();
		try {
			// 超级管理员有查找所有校区选项
			if (ValidateUtil.isValid(user.getSchoolArea())) {
				this.model.setSchoolArea(user.getSchoolArea());
			}
			model.setOperator(user.getCname().trim());
			PersonalTiming personalTiming = personalTimingService.save(model);
			if(personalTiming!=null){
				json.setMsg("添加学员学时信息成功！");
				json.setSuccess(true);
				json.setObj(personalTiming);
			}else{
				json.setMsg("当前输入的学时与学员所剩余学时不相符或当前学员学时用完，请核对！");
			}
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
			PersonalTiming personalTiming = personalTimingService.udpate(model);
			json.setMsg("编辑学员学时信息成功！");
			json.setSuccess(true);
			json.setObj(personalTiming);
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
			personalTimingService.delete(model.getIds());
			json.setMsg("删除学员学时信息成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("删除学员学时信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);

	}

}

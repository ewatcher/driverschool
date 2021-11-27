package com.tuocheng.action.demo;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.BaseAction;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.demo.Progress;
import com.tuocheng.service.demo.ProgressServiceI;
import com.tuocheng.util.base.ExceptionUtil;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 档案管理action
 * 
 * @author MEI702
 * 
 */
@Namespace("/demo")
@Action(value = "progressAction", results = {
		@Result(name = "progress", location = "/demo/progress/progress.jsp"),
		@Result(name = "progressEdit", location = "/demo/progress/progressEdit.jsp"),
		@Result(name = "detail", location = "/demo/progress/detail.jsp"),
		@Result(name = "personalProgress", location = "/demo/progress/personalProgress.jsp"),
		@Result(name = "personalDetail", location = "/demo/progress/personalDetail.jsp"),})
public class ProgressAction extends BaseAction<Progress> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 794769370809817909L;
	private static final Logger logger = Logger.getLogger(ProgressAction.class);

	private ProgressServiceI progressService;

	@Autowired
	public void setProgressService(ProgressServiceI progressService) {
		this.progressService = progressService;
	}

	// 到达档案管理主页面
	public String toProgressPage() {
		return "progress";
	}

	// 到达编辑页面
	public String toProgressEditPage() {
		return "progressEdit";
	}

	// 到达编辑页面
	public String toProgressDetailPage() {
		return "detail";
	}
	
	//打开个人进度页面
	public String toPersonalProgressPage() {
		return "personalProgress";
	}
	//到达学员个人进度页面
	public String toPersonalProgressDetailPage() {
		return "personalDetail";
	}
	

	public void datagrid() {
		try {
			// 超级管理员有查找所有校区选项
			if (ValidateUtil.isValid(user.getSchoolArea())) {
				this.model.setSchoolArea(user.getSchoolArea());
			}
			super.writeJson(progressService.dataGrid(model));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//学员个人进度列表
	public void personalDatagrid() {
		try {
			// 超级管理员有查找所有校区选项
			if (ValidateUtil.isValid(user.getSchoolArea())) {
				this.model.setSchoolArea(user.getSchoolArea());
				model.setStudentIdentityId(user.getCname());
			}
			super.writeJson(progressService.personalDataGrid(model));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 编辑信息
	public void edit() {
		Json json = new Json();
		try {
			Progress studentFile = progressService.udpate(model);
			json.setMsg("编辑档案信息成功！");
			json.setSuccess(true);
			json.setObj(studentFile);
		} catch (Exception e) {
			json.setMsg("编辑档案信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}
	
	public void editProgressState(){
		Json json = new Json();
		try {
			boolean b = progressService.updateProsessState(model.getId(), model.getState());
			if(b){
				json.setMsg("修改学员进度成功！");
				json.setSuccess(true);
			}else{
				json.setMsg("修改学员进度失败！");
				json.setSuccess(false);
			}
			
		} catch (Exception e) {
			json.setMsg("修改学员进度失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 删除信息
	public void delete() {
		Json json = new Json();
		try {
			progressService.delete(model.getIds());
			json.setMsg("删除档案信息成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("删除档案信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);

	}

}

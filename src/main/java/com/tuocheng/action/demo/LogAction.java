package com.tuocheng.action.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.BaseAction;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.demo.Car;
import com.tuocheng.pageModel.demo.Log;
import com.tuocheng.service.demo.CarServiceI;
import com.tuocheng.service.demo.LogServiceI;
import com.tuocheng.service.demo.POIUitlServiceI;
import com.tuocheng.util.base.ExceptionUtil;
import com.tuocheng.util.base.ResourceUtil;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 车辆管理action
 * 
 * @author MEI702
 * 
 */

@Namespace("/demo")
@Action(value = "logAction", results = { @Result(name = "log", location = "/demo/log/log.jsp"), })
public class LogAction extends BaseAction<Log> {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -4736894377398372683L;
	private static final Logger log = Logger.getLogger(LogAction.class);

	private LogServiceI logService;

	@Autowired
	public void setLogService(LogServiceI logService) {
		this.logService = logService;
	}

	// 到达car主页面
	public String toLogPage() {
		return "log";
	}

	// 从后台中查找出所有实体类信息
	public void datagrid() throws Exception {
		// 超级管理员有查找所有校区选项
		if (ValidateUtil.isValid(user.getSchoolArea())) {
			this.model.setSchoolArea(user.getSchoolArea());
		}
		super.writeJson(logService.datagrid(model));
	}

	

	// 删除实体类信息
	public void delete() {
		Json json = new Json();
		try {
			logService.delete(model.getIds());
			json.setSuccess(true);
			json.setMsg("删除实体信息成功！");
		} catch (Exception e) {
			json.setMsg("删除信息失败！"+e.getMessage());
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	
}

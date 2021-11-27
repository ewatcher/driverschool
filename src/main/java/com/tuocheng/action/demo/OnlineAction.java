package com.tuocheng.action.demo;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.tuocheng.action.base.BaseAction;
import com.tuocheng.pageModel.demo.Online;
import com.tuocheng.service.demo.OnlineServiceI;

/**
 * 在线列表ACTION
 * 
 * @author 农峰
 * 
 */
@Namespace("/demo")
@Action(value = "onlineAction")
public class OnlineAction extends BaseAction<Online>{
	
	/**
	 * 序列化标识
	 */
	private static final long serialVersionUID = -273659239123314008L;
	private OnlineServiceI demoOnlineService;

	public OnlineServiceI getDemoOnlineService() {
		return demoOnlineService;
	}

	@Autowired
	public void setDemoOnlineService(OnlineServiceI demoOnlineService) {
		this.demoOnlineService = demoOnlineService;
	}

	public void doNotNeedSession_onlineDatagrid() throws Exception {
		super.writeJson(demoOnlineService.datagrid(model));
	}

}

package com.tuocheng.action.demo;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.BaseAction;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.demo.Car;
import com.tuocheng.pageModel.demo.TimerCard;
import com.tuocheng.service.demo.CarServiceI;
import com.tuocheng.service.demo.POIUitlServiceI;
import com.tuocheng.service.demo.TimerCardServiceI;
import com.tuocheng.util.base.ExceptionUtil;
import com.tuocheng.util.base.ResourceUtil;

/**
 * 学时安排表action
 * 
 * @author 李杰
 * 
 */

@Namespace("/demo")
@Action(value = "timerCardAction", results = {
		@Result(name = "timerCard", location = "/demo/timerCard/timerCard.jsp") })
public class TimerCardAction extends BaseAction<TimerCard> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 82347590280981L;

	/**
	 * 序列号
	 */
	private static final Logger log = Logger.getLogger(TimerCardAction.class);

	private TimerCardServiceI timerCardService;

	@Autowired
	public void setTimerCardService(TimerCardServiceI timerCardService) {
		this.timerCardService = timerCardService;
	}

	// 到达计时卡主页面
	public String timerCard() {
		return "timerCard";
	}

	// 从后台中查找出所有实体类信息
	public void datagrid() throws Exception {
		super.writeJson(timerCardService.dataGrid(model));
	}

	// 添加实体类信息
	public void add() {
		Json json = new Json();
		try {
			TimerCard timerCard = timerCardService.save(model);
			json.setSuccess(true);
			json.setMsg("添加数据成功！");
			json.setObj(timerCard);
		} catch (Exception e) {
			json.setMsg("添加数据失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		// 将数据返回给前台
		super.writeJson(json);
	}

	// 更新实体类信息
	public void edit() {
		Json json = new Json();
		try {
			TimerCard timerCard = timerCardService.update(model);
			json.setSuccess(true);
			json.setMsg("修改数据成功！");
			json.setObj(timerCard);
		} catch (Exception e) {
			json.setMsg("修改数据失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		// 将数据返回给前台
		super.writeJson(json);
	}

	// 删除实体类信息
	public void delete() {
		Json json = new Json();
		try {
			timerCardService.delete(model.getIds());
			json.setSuccess(true);
			json.setMsg("删除数据成功！");
		} catch (Exception e) {
			json.setMsg("删除数据失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

}

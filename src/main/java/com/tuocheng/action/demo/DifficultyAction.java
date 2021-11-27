package com.tuocheng.action.demo;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.BaseAction;
import com.tuocheng.action.base.GuestAction;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.demo.Difficulty;
import com.tuocheng.service.demo.DifficultyServiceI;
import com.tuocheng.util.base.ExceptionUtil;
import com.tuocheng.util.base.Util;

/**
 * 微信难点反馈action
 * 
 * @author 李杰
 * 
 */

@Namespace("/demo")
@Action(value = "difficultyAction", results = {
		@Result(name = "difficultyList", location = "/demo/weixin/difficulty.jsp") })
public class DifficultyAction extends BaseAction<Difficulty> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 82347590280981L;

	/**
	 * 序列号
	 */
	private static final Logger log = Logger.getLogger(DifficultyAction.class);

	private DifficultyServiceI difficultyService;

	@Autowired
	public void setDifficultyService(DifficultyServiceI difficultyService) {
		this.difficultyService = difficultyService;
	}

	// 到难点列表页面
	public String difficultyList() {
		return "difficultyList";
	}

	// 从后台中查找出所有实体类信息
	public void datagrid() throws Exception {
		
		if (Util.isNotNull(user.getSchoolArea())) {
			model.setOrganizationId(user.getSchoolArea());
		} 
		
		model.setSort("createTime");
		model.setOrder("desc");
		super.writeJson(difficultyService.dataGrid(model));
	}

	// 更新实体类信息
	public void edit() {
		Json json = new Json();
		try {
			Difficulty difficulty = difficultyService.update(model);
			json.setSuccess(true);
			json.setMsg("修改数据成功！");
			json.setObj(difficulty);
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
			difficultyService.delete(model.getIds());
			json.setSuccess(true);
			json.setMsg("删除数据成功！");
		} catch (Exception e) {
			json.setMsg("删除数据失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

}

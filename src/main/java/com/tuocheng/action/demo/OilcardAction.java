package com.tuocheng.action.demo;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.BaseAction;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.demo.OilCard;
import com.tuocheng.service.demo.OilCardServiceI;
import com.tuocheng.util.base.ExceptionUtil;
import com.tuocheng.util.base.ValidateUtil;

@Namespace("/demo")
@Action(value = "oilcardAction", results = {
		@Result(name = "oilcard", location = "/demo/oilcard/oilcard.jsp"),
		@Result(name = "oilcardAdd", location = "/demo/oilcard/oilcardAdd.jsp"),
		@Result(name = "oilcardEdit", location = "/demo/oilcard/oilcardEdit.jsp"), })
public class OilcardAction extends BaseAction<OilCard> {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 5671229218093101057L;
	public static final Logger logger = Logger.getLogger(OilcardAction.class);

	// 注入相关的service
	private OilCardServiceI oilcardService;

	@Autowired
	public void setOilcardService(OilCardServiceI oilcardService) {
		this.oilcardService = oilcardService;
	}

	// 达到主页面
	public String toOilcardPage() {
		return "oilcard";
	}

	// 达到添加页面
	public String toOilcardAddPage() {
		return "oilcardAdd";
	}

	// 达到更新页面
	public String toOilcardEditPage() {
		return "oilcardEdit";
	}

	// 调用后台数据并传递给前台,
	public void datagrid() throws Exception {
		// 超级管理员有查找所有校区选项
		if (ValidateUtil.isValid(user.getSchoolArea())) {
			this.model.setSchoolArea(user.getSchoolArea());
		}
		super.writeJson(oilcardService.dataGrid(model));
	}

	// 添加加油卡信息
	public void add() {
		Json json = new Json();
		try {
			model.setOperator(user.getCname().trim());
			OilCard oilcard = oilcardService.save(model);
			json.setMsg("添加加油卡信息成功！");
			json.setSuccess(true);
			// 将成功添加后的数据回传到前台
			json.setObj(oilcard);
		} catch (Exception e) {
			json.setMsg("添加加油卡信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 更新加油卡信息
	public void edit() {
		Json json = new Json();
		try {
			OilCard oilcard = oilcardService.udpate(model);
			json.setMsg("修改加油卡信息成功！");
			json.setSuccess(true);
			// 将成功修改后的数据回传到前台
			json.setObj(oilcard);
		} catch (Exception e) {
			json.setMsg("修改加油卡信息失败！");
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
		super.writeJson(json);
	}

	// 删除加油卡信息
	public void delete() throws Exception {
		Json json = new Json();
		try {
			oilcardService.delete(model.getIds());
			json.setMsg("删除加油卡信息成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("删除加油卡信息失败！");
			e.printStackTrace();
		}
		super.writeJson(json);

	}

}

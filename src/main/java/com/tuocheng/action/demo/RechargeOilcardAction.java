package com.tuocheng.action.demo;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.BaseAction;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.demo.RechargeOilCard;
import com.tuocheng.pageModel.demo.RechargeOilCard;
import com.tuocheng.pageModel.demo.RechargeOilCard;
import com.tuocheng.service.demo.OilCardServiceI;
import com.tuocheng.service.demo.RechargeOilCardServiceI;
import com.tuocheng.util.base.ExceptionUtil;
import com.tuocheng.util.base.ValidateUtil;

@Namespace("/demo")
@Action(value = "rechargeOilcardAction", results = {
		@Result(name = "recharge", location = "/demo/oilcard/recharge.jsp"),
		@Result(name = "rechargeAdd", location = "/demo/oilcard/rechargeAdd.jsp"),
		@Result(name = "rechargeEdit", location = "/demo/oilcard/rechargeEdit.jsp"),
		@Result(name = "rechargeList", location = "/demo/oilcard/rechargeList.jsp"),})
public class RechargeOilcardAction extends BaseAction<RechargeOilCard> {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 5671229218093101057L;
	public static final Logger logger = Logger
			.getLogger(RechargeOilcardAction.class);

	// 注入相关的service
	private OilCardServiceI oilcardService;
	private RechargeOilCardServiceI rechargeOilCardService;

	@Autowired
	public void setOilcardService(OilCardServiceI oilcardService) {
		this.oilcardService = oilcardService;
	}

	@Autowired
	public void setRechargeOilCardService(
			RechargeOilCardServiceI rechargeOilCardService) {
		this.rechargeOilCardService = rechargeOilCardService;
	}

	// 达到主页面
	public String toRechargeOilcardPage() {
		return "recharge";
	}

	// 达到添加页面
	public String toRechargeOilcardAddPage() {
		return "rechargeAdd";
	}

	// 达到更新页面
	public String toRechargeOilcardEditPage() {
		return "rechargeEdit";
	}

	// 达到更新页面
	public String toRechargeOilcardListPage() {
		return "rechargeList";
	}

	// 调用后台数据并传递给前台,
	public void getRechargeDetailByCardId() throws Exception {
		RechargeOilCard entry = new RechargeOilCard();
		if (ValidateUtil.isValid(model.getOilCardId())) {
			entry.setOilCardId(model.getOilCardId());
		}
		// 超级管理员有查找所有校区选项
		if (ValidateUtil.isValid(user.getSchoolArea())) {
			entry.setSchoolArea(user.getSchoolArea());
		}
		// 查询字段
		if (model.getOilCardNo() != null) {
			entry.setOilCardNo(model.getOilCardNo());
		}
		if (model.getRechargeDateStart() != null) {
			entry.setRechargeDateStart(model.getRechargeDateStart());
		}
		if (model.getRechargeDateEnd() != null) {
			entry.setRechargeDateEnd(model.getRechargeDateEnd());
		}

		entry.setRows(model.getRows());
		entry.setSort(model.getSort());
		entry.setOrder(model.getOrder());
		entry.setPage(model.getPage());

		try {
			super.writeJson(rechargeOilCardService.dataGrid(entry));
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}

	}

	public void datagrid() throws Exception {
		try {
			// 超级管理员有查找所有校区选项
			if (ValidateUtil.isValid(user.getSchoolArea())) {
				this.model.setSchoolArea(user.getSchoolArea());
			}
			super.writeJson(rechargeOilCardService.dataGrid(model));
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	// 添加加油卡信息
	public void add() {
		Json json = new Json();
		try {
			model.setRechargePerson(user.getCname().trim());
			RechargeOilCard rechargeOilcard = rechargeOilCardService
					.save(model);
			json.setMsg("添加加油卡信息成功！");
			json.setSuccess(true);
			// 将成功添加后的数据回传到前台
			json.setObj(rechargeOilcard);
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
			RechargeOilCard rechargeOilcard = rechargeOilCardService
					.udpate(model);
			json.setMsg("修改加油卡信息成功！");
			json.setSuccess(true);
			// 将成功修改后的数据回传到前台
			json.setObj(rechargeOilcard);
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
			rechargeOilCardService.delete(model.getIds());
			json.setMsg("删除加油卡信息成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("删除加油卡信息失败！");
			e.printStackTrace();
		}
		super.writeJson(json);

	}

	// 根据卡标识，查找该卡最终余额
	public void getLatelyBalanceByOilcardId() {
		try {
			Double ret = oilcardService.getLatelyBalanceByOilcardId(model
					.getOilCardId());
			if (ret != null) {
			} else {
				ret = 0.00;

			}
			super.writeJson(ret);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}

}

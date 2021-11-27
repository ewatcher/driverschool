package com.tuocheng.pageModel.demo;

import java.util.Date;

public class RechargeOilCard extends PageModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1100970818693180031L;
	private String id;
	private Date rechargeDate;// 充值日期
	private Double beforeBalance;// 充值前余额
	private Double money;// 本次充值金额
	private Double afterBalance;// 充值前后额
	private String rechargePerson;// 充值人
	private String schoolArea;// 所属校区
	private String schoolAreaName;
	private String remarks;// 备注

	private Date rechargeDateStart;// 充值日期
	private Date rechargeDateEnd;// 充值日期
	
	private String oilCardId;
	private String oilCardNo;// 卡号
	private Integer oilcardState;
	private Double oilcardbalance;

	private String ids;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getRechargeDate() {
		return rechargeDate;
	}

	public void setRechargeDate(Date rechargeDate) {
		this.rechargeDate = rechargeDate;
	}

	public Double getBeforeBalance() {
		return beforeBalance;
	}

	public void setBeforeBalance(Double beforeBalance) {
		this.beforeBalance = beforeBalance;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Double getAfterBalance() {
		return afterBalance;
	}

	public void setAfterBalance(Double afterBalance) {
		this.afterBalance = afterBalance;
	}

	public String getRechargePerson() {
		return rechargePerson;
	}

	public void setRechargePerson(String rechargePerson) {
		this.rechargePerson = rechargePerson;
	}

	public String getOilCardId() {
		return oilCardId;
	}

	public void setOilCardId(String oilCardId) {
		this.oilCardId = oilCardId;
	}

	public String getOilCardNo() {
		return oilCardNo;
	}

	public void setOilCardNo(String oilCardNo) {
		this.oilCardNo = oilCardNo;
	}

	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	public String getSchoolAreaName() {
		return schoolAreaName;
	}

	public void setSchoolAreaName(String schoolAreaName) {
		this.schoolAreaName = schoolAreaName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getRechargeDateStart() {
		return rechargeDateStart;
	}

	public void setRechargeDateStart(Date rechargeDateStart) {
		this.rechargeDateStart = rechargeDateStart;
	}

	public Date getRechargeDateEnd() {
		return rechargeDateEnd;
	}

	public void setRechargeDateEnd(Date rechargeDateEnd) {
		this.rechargeDateEnd = rechargeDateEnd;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Integer getOilcardState() {
		return oilcardState;
	}

	public void setOilcardState(Integer oilcardState) {
		this.oilcardState = oilcardState;
	}

	public Double getOilcardbalance() {
		return oilcardbalance;
	}

	public void setOilcardbalance(Double oilcardbalance) {
		this.oilcardbalance = oilcardbalance;
	}

}

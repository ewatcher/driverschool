package com.tuocheng.pageModel.demo;

import java.util.Date;

public class OilCard extends PageModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6993477882536511781L;
	private String id;
	private String cardNo;// 卡号
	private Date createTime;// 创建日期
	private Integer state;// 卡状态 1：正常 2：挂失
	private Double balance;// 当前余额
	private String operator;// 创建人
	private String schoolArea;// 所属校区
	private String schoolAreaName;
	private String remarks;// 备注
	private Date prevateDate;// 上一次充值日期

	private Date createTimeStart;
	private Date createTimeEnd;

	private String ids;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Date getPrevateDate() {
		return prevateDate;
	}

	public void setPrevateDate(Date prevateDate) {
		this.prevateDate = prevateDate;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}

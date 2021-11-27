package com.tuocheng.pageModel.demo;

import java.util.Date;

public class OilCardUsing extends PageModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1412538518410431803L;
	private String id;
	private String carNo;// 车牌号
	private Date usingDate;// 使用日期
	private String startTime;// 借出卡时间
	private String endTime;// 还卡时间
	private Integer oilName;// 油名称
	private Integer oilType;// 油号
	private Double oilTotal;// 使用测量
	private Double price;// 单价
	private Double moneyTotal;// 总额
	private String usingPerson;// 领卡人
	private String groupType;// 组别

	private String schoolArea;// 所属校区
	private String schoolAreaName;
	private String remarks;// 备注
	private String operator;// 操作员

	private Date usingDateStart;// 使用日期
	private Date usingDateEnd;// 使用日期

	private String oilCardId;
	private String oilCardNo;// 卡号
	private Integer oilcardState;
	private Double oilcardBalance;

	private String carId;
	private String trainerId;
	private String trianerName;

	private String ids;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public Date getUsingDate() {
		return usingDate;
	}

	public void setUsingDate(Date usingDate) {
		this.usingDate = usingDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getOilName() {
		return oilName;
	}

	public void setOilName(Integer oilName) {
		this.oilName = oilName;
	}

	public Integer getOilType() {
		return oilType;
	}

	public void setOilType(Integer oilType) {
		this.oilType = oilType;
	}

	public Double getOilTotal() {
		return oilTotal;
	}

	public void setOilTotal(Double oilTotal) {
		this.oilTotal = oilTotal;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getMoneyTotal() {
		return moneyTotal;
	}

	public void setMoneyTotal(Double moneyTotal) {
		this.moneyTotal = moneyTotal;
	}

	public String getUsingPerson() {
		return usingPerson;
	}

	public void setUsingPerson(String usingPerson) {
		this.usingPerson = usingPerson;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
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

	public Date getUsingDateStart() {
		return usingDateStart;
	}

	public void setUsingDateStart(Date usingDateStart) {
		this.usingDateStart = usingDateStart;
	}

	public Date getUsingDateEnd() {
		return usingDateEnd;
	}

	public void setUsingDateEnd(Date usingDateEnd) {
		this.usingDateEnd = usingDateEnd;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Integer getOilcardState() {
		return oilcardState;
	}

	public void setOilcardState(Integer oilcardState) {
		this.oilcardState = oilcardState;
	}

	public Double getOilcardBalance() {
		return oilcardBalance;
	}

	public void setOilcardBalance(Double oilcardBalance) {
		this.oilcardBalance = oilcardBalance;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(String trainerId) {
		this.trainerId = trainerId;
	}

	public String getTrianerName() {
		return trianerName;
	}

	public void setTrianerName(String trianerName) {
		this.trianerName = trianerName;
	}

}

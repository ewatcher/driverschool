package com.tuocheng.pageModel.demo;

import java.util.Date;

public class CarusingTemp extends PageModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8002585612462022385L;
	private String id;// 标识
	private String schoolArea;// 校区标识
	private Integer dateCount;// 按日统计
	private Integer weekCount;// 按周统计
	private Integer manthCount;// 按月统计
	private Integer yearCount;// 按年统计

	private Integer dateReservateCount;// 　天预约统计
	private Integer weekReservateCount;// 周预约统计
	private Integer monthReservateCount;// 月预约统计
	private Integer yearReservateCount;// 年预约统计

	private String ids;

	// 车辆基本信息
	private String carId;
	private String schoolAreaName;
	private String carNo;// 车牌号
	private String licenseNo;// 行驶证号
	private Integer carType;// 车辆类型（1－A1,2－A2,... 10-C5,11-电车,12-模拟车，13－C2或者C5）
	private Integer usingState;// 车辆使用状态(0:未使用，1：使用)
	private Integer wechatFalg;//微信预约标记

	// 车辆使用基本信息
	private String usingCarId;
	private String usingCarNumbering;// 编号
	private Date usingCarUsingDate;// 使用日期
	private String usingCarReason;// 使用说明
	private String usingCarPlace;// 使用场地
	private String usingCarStartTime;// 开始时间
	private String usingCarEndTime;// 结束时间
	private Integer usingCarStartMile;// 开始公里数
	private Integer usingCarEndMile;// 结果公理数
	private Integer usingCarUsingMile;// 行程
	private Integer usingCarUsingState;// 行程

	private String trainerId;
	private String trainerName;
	
	private Date lastArrangeDate;//自动档最新排班日期

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getWechatFalg() {
		return wechatFalg;
	}

	public void setWechatFalg(Integer wechatFalg) {
		this.wechatFalg = wechatFalg;
	}

	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	public Integer getDateCount() {
		return dateCount;
	}

	public void setDateCount(Integer dateCount) {
		this.dateCount = dateCount;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getSchoolAreaName() {
		return schoolAreaName;
	}

	public void setSchoolAreaName(String schoolAreaName) {
		this.schoolAreaName = schoolAreaName;
	}

	public Integer getWeekCount() {
		return weekCount;
	}

	public void setWeekCount(Integer weekCount) {
		this.weekCount = weekCount;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Integer getDateReservateCount() {
		return dateReservateCount;
	}

	public Integer getUsingCarUsingState() {
		return usingCarUsingState;
	}

	public void setUsingCarUsingState(Integer usingCarUsingState) {
		this.usingCarUsingState = usingCarUsingState;
	}

	public void setDateReservateCount(Integer dateReservateCount) {
		this.dateReservateCount = dateReservateCount;
	}

	public Integer getWeekReservateCount() {
		return weekReservateCount;
	}

	public void setWeekReservateCount(Integer weekReservateCount) {
		this.weekReservateCount = weekReservateCount;
	}

	public Date getLastArrangeDate() {
		return lastArrangeDate;
	}

	public void setLastArrangeDate(Date lastArrangeDate) {
		this.lastArrangeDate = lastArrangeDate;
	}

	public Integer getMonthReservateCount() {
		return monthReservateCount;
	}

	public void setMonthReservateCount(Integer monthReservateCount) {
		this.monthReservateCount = monthReservateCount;
	}

	public Integer getYearReservateCount() {
		return yearReservateCount;
	}

	public void setYearReservateCount(Integer yearReservateCount) {
		this.yearReservateCount = yearReservateCount;
	}

	public String getUsingCarNumbering() {
		return usingCarNumbering;
	}

	public void setUsingCarNumbering(String usingCarNumbering) {
		this.usingCarNumbering = usingCarNumbering;
	}

	public Date getUsingCarUsingDate() {
		return usingCarUsingDate;
	}

	public void setUsingCarUsingDate(Date usingCarUsingDate) {
		this.usingCarUsingDate = usingCarUsingDate;
	}

	public String getUsingCarReason() {
		return usingCarReason;
	}

	public String getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(String trainerId) {
		this.trainerId = trainerId;
	}

	public String getTrainerName() {
		return trainerName;
	}

	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}

	public void setUsingCarReason(String usingCarReason) {
		this.usingCarReason = usingCarReason;
	}

	public String getUsingCarPlace() {
		return usingCarPlace;
	}

	public void setUsingCarPlace(String usingCarPlace) {
		this.usingCarPlace = usingCarPlace;
	}

	public String getUsingCarStartTime() {
		return usingCarStartTime;
	}

	public void setUsingCarStartTime(String usingCarStartTime) {
		this.usingCarStartTime = usingCarStartTime;
	}

	public String getUsingCarEndTime() {
		return usingCarEndTime;
	}

	public void setUsingCarEndTime(String usingCarEndTime) {
		this.usingCarEndTime = usingCarEndTime;
	}

	public Integer getUsingCarStartMile() {
		return usingCarStartMile;
	}

	public void setUsingCarStartMile(Integer usingCarStartMile) {
		this.usingCarStartMile = usingCarStartMile;
	}

	public Integer getUsingCarEndMile() {
		return usingCarEndMile;
	}

	public void setUsingCarEndMile(Integer usingCarEndMile) {
		this.usingCarEndMile = usingCarEndMile;
	}

	public Integer getUsingCarUsingMile() {
		return usingCarUsingMile;
	}

	public void setUsingCarUsingMile(Integer usingCarUsingMile) {
		this.usingCarUsingMile = usingCarUsingMile;
	}

	public Integer getManthCount() {
		return manthCount;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public Integer getCarType() {
		return carType;
	}

	public void setCarType(Integer carType) {
		this.carType = carType;
	}

	public Integer getUsingState() {
		return usingState;
	}

	public void setUsingState(Integer usingState) {
		this.usingState = usingState;
	}

	public void setManthCount(Integer manthCount) {
		this.manthCount = manthCount;
	}

	public Integer getYearCount() {
		return yearCount;
	}

	public void setYearCount(Integer yearCount) {
		this.yearCount = yearCount;
	}

	public String getUsingCarId() {
		return usingCarId;
	}

	public void setUsingCarId(String usingCarId) {
		this.usingCarId = usingCarId;
	}

}

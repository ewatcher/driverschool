package com.tuocheng.pageModel.demo;

import java.util.Date;

/**
 * 车辆使用情况管理类action专用
 * 
 * @author 农峰
 * 
 */
public class UsingCar extends PageModel {

	/**
	 * 序列
	 */
	private static final long serialVersionUID = 2700381597481496693L;
	private String id;
	private String numbering;// 编号
	private Date usingDate;// 使用日期
	private String reason;// 使用说明
	private String place;// 使用场地
	private String startTime;// 开始时间
	private String endTime;// 结束时间
	private Integer startMile;// 开始公里数
	private Integer endMile;// 结果公理数
	private Integer usingMile;// 行程
	private String comment;// 备注
	private String ids;
	private String schoolArea;// 校区标识
	private String operator;// 操作员
	private String reservationId;// 冗余字段，当预约记录被删除时，则根据预约id删除车辆使用记录
	private Integer usingcarState;// 预约状态
	// 1:表示预约中，2：表示正常，3：表示完成，4：表示取消，5.表示挂起　6：表示确认中

	private String schoolAreaName;// 校区标识

	private String carId;
	private String carNo;// 车牌号
	private String carLicenseNo;// 行驶证号

	private String trainerId; // 标识
	private String trainerName;// 教练名称
	private String trainerIdentity;// 身份证号
	private String trainerPhone;// 手机

	// 查询字段
	private Date usingDateStart;// 使用日期开始
	private Date usingDateEnd;// 使用日期结束

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumbering() {
		return numbering;
	}

	public void setNumbering(String numbering) {
		this.numbering = numbering;
	}

	public Date getUsingDate() {
		return usingDate;
	}

	public void setUsingDate(Date usingDate) {
		this.usingDate = usingDate;
	}

	public String getReason() {
		return reason;
	}

	public String getSchoolAreaName() {
		return schoolAreaName;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public void setSchoolAreaName(String schoolAreaName) {
		this.schoolAreaName = schoolAreaName;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getUsingcarState() {
		return usingcarState;
	}

	public void setUsingcarState(Integer usingcarState) {
		this.usingcarState = usingcarState;
	}

	public String getPlace() {
		return place;
	}

	public String getReservationId() {
		return reservationId;
	}

	public void setReservationId(String reservationId) {
		this.reservationId = reservationId;
	}

	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getTrainerName() {
		return trainerName;
	}

	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}

	public String getStartTime() {
		return startTime;
	}

	public Date getUsingDateStart() {
		return usingDateStart;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
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

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getStartMile() {
		return startMile;
	}

	public void setStartMile(Integer startMile) {
		this.startMile = startMile;
	}

	public Integer getEndMile() {
		return endMile;
	}

	public void setEndMile(Integer endMile) {
		this.endMile = endMile;
	}

	public Integer getUsingMile() {
		return usingMile;
	}

	public void setUsingMile(Integer usingMile) {
		this.usingMile = usingMile;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getCarLicenseNo() {
		return carLicenseNo;
	}

	public void setCarLicenseNo(String carLicenseNo) {
		this.carLicenseNo = carLicenseNo;
	}

	public String getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(String trainerId) {
		this.trainerId = trainerId;
	}

	public String getTrainerIdentity() {
		return trainerIdentity;
	}

	public void setTrainerIdentity(String trainerIdentity) {
		this.trainerIdentity = trainerIdentity;
	}

	public String getTrainerPhone() {
		return trainerPhone;
	}

	public void setTrainerPhone(String trainerPhone) {
		this.trainerPhone = trainerPhone;
	}

}

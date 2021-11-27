package com.tuocheng.pageModel.demo;

import java.util.Date;

import com.tuocheng.pageModel.base.StudentPageModel;

/**
 * 学时管理实体类（action）
 * 
 * @author 农峰
 * 
 */
public class Timing extends StudentPageModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5859937783797540312L;
	private String id;// 唯一标识
	private String numbering;// 编号
	private Integer simulationTiming;// 模拟训练学时
	private Integer tramTiming;// 电车学时
	private Integer carTiming;// 实车学时
	private Integer claimTiming;// 大纲要求学时
	private Integer handselTiming;// 赠送学时（报名时包含学时）

	private Integer totalTiming;// 总学时
	private Integer usingTiming;// 已用学时
	private Integer restTiming;// 剩余学时
	private Integer reservationTiming;// 可预约的学时（如：学员剩余学时为3只有预约3个学时，但学员剩余学时不能减法3,因为存在预约取消情况）
	private Integer buyTiming;// 购买总学时

	private String comment;// 备注
	private String operator;// 操作员
	private String studentId;// 学员编号（冗余字段）

	private String schoolArea;// 所属校区
	private String schoolAreaName;

	private String trainerId;
	private String trainerName;
	private String trainerIdentityId;

	private Integer timingFlag;// 学员计时类型：0:不计时，1：计时

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

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Integer getSimulationTiming() {
		return simulationTiming;
	}

	public void setSimulationTiming(Integer simulationTiming) {
		this.simulationTiming = simulationTiming;
	}

	public Integer getTramTiming() {
		return tramTiming;
	}

	public void setTramTiming(Integer tramTiming) {
		this.tramTiming = tramTiming;
	}

	public Integer getCarTiming() {
		return carTiming;
	}

	public Integer getTimingFlag() {
		return timingFlag;
	}

	public void setTimingFlag(Integer timingFlag) {
		this.timingFlag = timingFlag;
	}

	public String getSchoolAreaName() {
		return schoolAreaName;
	}

	public void setSchoolAreaName(String schoolAreaName) {
		this.schoolAreaName = schoolAreaName;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public void setCarTiming(Integer carTiming) {
		this.carTiming = carTiming;
	}

	public Integer getClaimTiming() {
		return claimTiming;
	}

	public void setClaimTiming(Integer claimTiming) {
		this.claimTiming = claimTiming;
	}

	public Integer getHandselTiming() {
		return handselTiming;
	}

	public Integer getTotalTiming() {
		return totalTiming;
	}

	public void setTotalTiming(Integer totalTimeing) {
		this.totalTiming = totalTimeing;
	}

	public Integer getUsingTiming() {
		return usingTiming;
	}

	public Integer getReservationTiming() {
		return reservationTiming;
	}

	public void setReservationTiming(Integer reservationTiming) {
		this.reservationTiming = reservationTiming;
	}

	public void setUsingTiming(Integer usingTiming) {
		this.usingTiming = usingTiming;
	}

	public Integer getRestTiming() {
		return restTiming;
	}

	public void setRestTiming(Integer restTiming) {
		this.restTiming = restTiming;
	}

	public void setHandselTiming(Integer handselTiming) {
		this.handselTiming = handselTiming;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
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

	public String getTrainerIdentityId() {
		return trainerIdentityId;
	}

	public void setTrainerIdentityId(String trainerIdentityId) {
		this.trainerIdentityId = trainerIdentityId;
	}

	public Integer getBuyTiming() {
		return buyTiming;
	}

	public void setBuyTiming(Integer buyTiming) {
		this.buyTiming = buyTiming;
	}

}

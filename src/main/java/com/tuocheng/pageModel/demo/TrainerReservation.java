package com.tuocheng.pageModel.demo;

import com.tuocheng.pageModel.base.StudentPageModel;

/**
 * 教练预约管理实体类(action专用)
 * 
 * @author 农峰
 * 
 */
public class TrainerReservation extends StudentPageModel {

	/**
	 * 序列化标识
	 */
	private static final long serialVersionUID = 701276310480147103L;
	private String id;// 标识
	private String schoolArea;// 校区标识
	private Integer fiveItem;// 五项总预约课时
	private Integer roadExam;// 路考试总预约课时
	private Integer studentTotal;// 预约学员总人数
	private String comment;// 备注

	private String ids;
	private String trainerId;
	private String trainerName;
	private String trainerCodeNo;
	private String trainerIdentityId;// 教练身份证 查询
	private String trainerPhone;// 电话
	private String schoolAreaName;

	private Integer dateCount;// 按日统计
	private Integer weekCount;// 按周统计
	private Integer monthCount;// 按月统计
	private Integer yearCount;// 按年统计

	private Integer dateReservateCount;// 　天预约统计
	private Integer weekReservateCount;// 周预约统计
	private Integer monthReservateCount;// 月预约统计
	private Integer yearReservateCount;// 年预约统计
	private Integer reservationState;// 预约状态(冗余字段：统计有郊预约数据)

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	public Integer getFiveItem() {
		return fiveItem;
	}

	public void setFiveItem(Integer fiveItem) {
		this.fiveItem = fiveItem;
	}

	public Integer getRoadExam() {
		return roadExam;
	}

	public Integer getReservationState() {
		return reservationState;
	}

	public void setReservationState(Integer reservationState) {
		this.reservationState = reservationState;
	}

	public String getTrainerPhone() {
		return trainerPhone;
	}

	public void setTrainerPhone(String trainerPhone) {
		this.trainerPhone = trainerPhone;
	}

	public void setRoadExam(Integer roadExam) {
		this.roadExam = roadExam;
	}

	public Integer getStudentTotal() {
		return studentTotal;
	}

	public void setStudentTotal(Integer studentTotal) {
		this.studentTotal = studentTotal;
	}

	public String getComment() {
		return comment;
	}

	public String getTrainerCodeNo() {
		return trainerCodeNo;
	}

	public void setTrainerCodeNo(String trainerCodeNo) {
		this.trainerCodeNo = trainerCodeNo;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getTrainerIdentityId() {
		return trainerIdentityId;
	}

	public void setTrainerIdentityId(String trainerIdentityId) {
		this.trainerIdentityId = trainerIdentityId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Integer getDateCount() {
		return dateCount;
	}

	public void setDateCount(Integer dateCount) {
		this.dateCount = dateCount;
	}

	public Integer getWeekCount() {
		return weekCount;
	}

	public void setWeekCount(Integer weekCount) {
		this.weekCount = weekCount;
	}

	public Integer getMonthCount() {
		return monthCount;
	}

	public void setMonthCount(Integer monthCount) {
		this.monthCount = monthCount;
	}

	public Integer getYearCount() {
		return yearCount;
	}

	public void setYearCount(Integer yearCount) {
		this.yearCount = yearCount;
	}

	public Integer getDateReservateCount() {
		return dateReservateCount;
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

	public String getSchoolAreaName() {
		return schoolAreaName;
	}

	public void setSchoolAreaName(String schoolAreaName) {
		this.schoolAreaName = schoolAreaName;
	}

}

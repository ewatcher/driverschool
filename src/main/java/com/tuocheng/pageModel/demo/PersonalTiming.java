package com.tuocheng.pageModel.demo;

import java.util.Date;

/**
 * 学员个人学时实体类action专用
 * 
 * @author 农峰
 * 
 */
public class PersonalTiming extends PageModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -914523013943323287L;
	private String id;
	private String numbering;
	private Date date;
	private String timeItem;// 时段
	private Integer timingType;// (1:模拟,2:电车,3:实训)
	private String teachingContent;// 教学内容
	private Integer useTiming;// 使用课时
	private String studentId;// 学员标识（添加冗余字段）
	private String operator;// 操作员

	private Integer timgingType;// 0:表示购买学时，1.表示消费学时
	private Integer timingUsingType;// 学时用途:1.电车，2.模拟　3.理论　4.五项　5.路训　6.其他

	private String ids;
	private String timingId;
	private String studentName;
	private String trainerName;
	private String trainerId;
	private String reservationAddFlag;// 标记通过预约创建本学时记录
	private String schoolArea;

	private String studentIdTemp;
	private Integer createWay;// 当前学时记录创建方式，0:手动创建　1:为预约创建

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

	public String getIds() {
		return ids;
	}

	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	public String getOperator() {
		return operator;
	}

	public Integer getTimgingType() {
		return timgingType;
	}

	public void setTimgingType(Integer timgingType) {
		this.timgingType = timgingType;
	}

	public Integer getTimingUsingType() {
		return timingUsingType;
	}

	public void setTimingUsingType(Integer timingUsingType) {
		this.timingUsingType = timingUsingType;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Integer getCreateWay() {
		return createWay;
	}

	public void setCreateWay(Integer createWay) {
		this.createWay = createWay;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getStudentIdTemp() {
		return studentIdTemp;
	}

	public void setStudentIdTemp(String studentIdTemp) {
		this.studentIdTemp = studentIdTemp;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTimeItem() {
		return timeItem;
	}

	public String getTrainerName() {
		return trainerName;
	}

	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}

	public void setTimeItem(String timeItem) {
		this.timeItem = timeItem;
	}

	public String getReservationAddFlag() {
		return reservationAddFlag;
	}

	public void setReservationAddFlag(String reservationAddFlag) {
		this.reservationAddFlag = reservationAddFlag;
	}

	public Integer getTimingType() {
		return timingType;
	}

	public String getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(String trainerId) {
		this.trainerId = trainerId;
	}

	public void setTimingType(Integer timingType) {
		this.timingType = timingType;
	}

	public String getTeachingContent() {
		return teachingContent;
	}

	public void setTeachingContent(String teachingContent) {
		this.teachingContent = teachingContent;
	}

	public Integer getUseTiming() {
		return useTiming;
	}

	public void setUseTiming(Integer useTiming) {
		this.useTiming = useTiming;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getTimingId() {
		return timingId;
	}

	public void setTimingId(String timingId) {
		this.timingId = timingId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

}

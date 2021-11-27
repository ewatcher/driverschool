package com.tuocheng.model.demo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.alibaba.fastjson.JSON;

/**
 * 预约实体类
 * 
 * @author MEI702
 * 
 */
@Entity
@Table(name = "tb_reservations", schema = "")
public class Treservation implements Serializable {

	/**
	 * 序列化标识
	 */
	private static final long serialVersionUID = 8156753191775196890L;

	public static final Integer RESERVATIONSTATE_RESERVATIONING = 1;
	public static final Integer RESERVATIONSTATE_NORMARL = 2;
	public static final Integer RESERVATIONSTATE_FINISH = 3;
	public static final Integer RESERVATIONSTATE_CANCEL = 4;
	public static final Integer RESERVATIONSTATE_HANDUP = 5;
	public static final Integer RESERVATIONSTATE_CONFIRMING = 6;

	public static final Integer CANCELFLAG_FAULSE = 0;
	public static final Integer CANCELFLAG_TRUE = 1;

	private String id;
	private String numbering;// 编号
	private Date date;// 预约日期
	private String timeStart;// 开始时段
	private String timeEnd;// 结束时段
	private Integer reservationWay;// 预约方式(1:表示后台，2表示微信端)
	private Integer reservationState;// 预约状态
										// 1:表示预约中，2：表示正常，3：表示完成，4：表示取消，5.表示挂起
										// 6：表示确认中
	private Integer reservationType;// 预约类型 1电车，2模块，3五项目，4路训
	private String schoolArea;// 所属校区
	private String comment;// 备注
	private String operator;// 操作员
	private Integer studentConfirm;// 学员对预约的确认状态1：表示确认 0：表示未确认 默认0
	private Integer trainerConfirm;// 教练对预约的确认状态1：表示确认 0：表示未确认 默认0
	private String carId;// 冗余字段用来表示该条预约的车辆标识
	private Date createTime;// 创建时间

	// =====connection relation=============
	private Tstudent student;
	private Ttrainer trainer;

	//
	private String trainerReservationDetailId;// 教练预约明细信息标识
	private Integer fieldOptionFlag;// 标识用户操作的列
	private Integer cancelFlag;// 取消预约标记
	private Integer carStateFlag;// 车辆状态1：表示车辆正常 0：表示无车辆

	public Treservation() {
	}

	public Treservation(String id, String numbering, Date date, String timeStart, String timeEnd,
			Integer reservationWay, Integer reservationState, Integer reservationType, String schoolArea,
			String comment, String operator, Integer studentConfirm, Integer trainerConfirm, String carId,
			Date createTime, Tstudent student, Ttrainer trainer, String trainerReservationDetailId,
			Integer fieldOptionFlag, Integer cancelFlag, Integer carStateFlag) {
		this.id = id;
		this.numbering = numbering;
		this.date = date;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
		this.reservationWay = reservationWay;
		this.reservationState = reservationState;
		this.reservationType = reservationType;
		this.schoolArea = schoolArea;
		this.comment = comment;
		this.operator = operator;
		this.studentConfirm = studentConfirm;
		this.trainerConfirm = trainerConfirm;
		this.carId = carId;
		this.createTime = createTime;
		this.student = student;
		this.trainer = trainer;
		this.trainerReservationDetailId = trainerReservationDetailId;
		this.fieldOptionFlag = fieldOptionFlag;
		this.cancelFlag = cancelFlag;
		this.carStateFlag = carStateFlag;
	}

	@Id
	@Column(name = "id", nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "numbering", nullable = false, length = 36)
	public String getNumbering() {
		return numbering;
	}

	public void setNumbering(String numbering) {
		this.numbering = numbering;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date", length = 7)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "timeStart", length = 20)
	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	@Column(name = "timeEnd", length = 20)
	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

	@Column(name = "reservationWay", length = 4)
	public Integer getReservationWay() {
		return reservationWay;
	}

	public void setReservationWay(Integer reservationWay) {
		this.reservationWay = reservationWay;
	}

	@Column(name = "reservationState", length = 4)
	public Integer getReservationState() {
		return reservationState;
	}

	public void setReservationState(Integer reservationState) {
		this.reservationState = reservationState;
	}

	@Column(name = "schoolArea", length = 60)
	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	@Column(name = "reservationType", length = 4)
	public Integer getReservationType() {
		return reservationType;
	}

	public void setReservationType(Integer reservationType) {
		this.reservationType = reservationType;
	}

	@Column(name = "comment", length = 300)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name = "trainerReservationDetailId", length = 36)
	public String getTrainerReservationDetailId() {
		return trainerReservationDetailId;
	}

	public void setTrainerReservationDetailId(String trainerReservationDetailId) {
		this.trainerReservationDetailId = trainerReservationDetailId;
	}

	@Column(name = "fieldOptionFlag", length = 8)
	public Integer getFieldOptionFlag() {
		return fieldOptionFlag;
	}

	public void setFieldOptionFlag(Integer fieldOptionFlag) {
		this.fieldOptionFlag = fieldOptionFlag;
	}

	@Column(name = "operator", length = 36, updatable = false)
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "studentConfirm", length = 4, columnDefinition = "INT default 0")
	public Integer getStudentConfirm() {
		return studentConfirm;
	}

	public void setStudentConfirm(Integer studentConfirm) {
		this.studentConfirm = studentConfirm;
	}

	@Column(name = "trainerConfirm", length = 4, columnDefinition = "INT default 0")
	public Integer getTrainerConfirm() {
		return trainerConfirm;
	}

	public void setTrainerConfirm(Integer trainerConfirm) {
		this.trainerConfirm = trainerConfirm;
	}

	@Column(name = "cancelFlag", length = 4, columnDefinition = "INT default 0")
	public Integer getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(Integer cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	@Column(name = "carStateFlag", length = 4, columnDefinition = "INT default 1")
	public Integer getCarStateFlag() {
		return carStateFlag;
	}

	public void setCarStateFlag(Integer carStateFlag) {
		this.carStateFlag = carStateFlag;
	}

	@Column(name = "carId", length = 36)
	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createTime", length = 12)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "studentId", updatable = false)
	public Tstudent getStudent() {
		return student;
	}

	public void setStudent(Tstudent student) {
		this.student = student;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trainerId", updatable = false)
	public Ttrainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Ttrainer trainer) {
		this.trainer = trainer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Treservation other = (Treservation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Treservation [id=" + id + ", numbering=" + numbering + ", date=" + date + ", timeStart=" + timeStart
				+ ", timeEnd=" + timeEnd + ", reservationWay=" + reservationWay + ", reservationState="
				+ reservationState + ", reservationType=" + reservationType + ", schoolArea=" + schoolArea
				+ ", comment=" + comment + ", operator=" + operator + ", studentConfirm=" + studentConfirm
				+ ", trainerConfirm=" + trainerConfirm + ", student=" + student + ", trainer=" + trainer
				+ ", trainerReservationDetailId=" + trainerReservationDetailId + ", fieldOptionFlag=" + fieldOptionFlag
				+ ", cancelFlag=" + cancelFlag + "]";
	}

}

package com.tuocheng.model.demo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;

import com.alibaba.fastjson.JSON;

/**
 * 车辆使用情况实体类(dao专用实体模型)
 * 
 * @author 农峰
 * 
 */
@Entity
@Table(name = "tb_usingcars", schema = "")
public class TusingCar implements Serializable {

	/**
	 * 序列化标识
	 */
	private static final long serialVersionUID = -6652888522913503220L;
	private String id;
	private String numbering;// 编号
	private Date usingDate;// 使用日期
	private String reason;// 使用说明
	private String place;// 使用场地
	private String startTime;// 开始时间
	private String endTime;// 结束时间
	private Integer startMile;// 开始公里数
	private Integer endMile;// 结束公理数
	private Integer usingMile;// 行程
	private String comment;// 备注
	private String schoolArea;// 校区标识
	private String operator;// 操作员
	private Integer usingcarState;// 预约状态
	// 1:表示预约中，2：表示正常，3：表示完成，4：表示取消，5.表示挂起　6：表示确认中

	private String reservationId;// 冗余字段，当预约记录被删除时，则根据预约id删除车辆使用记录
	private String carId;// 冗余字段
	private Ttrainer trainer;
	private TcarusingTemp usingcarTemp;

	public TusingCar() {
	}

	public TusingCar(String id, String numbering, Date usingDate,
			String reason, String place, String startTime, String endTime,
			Integer startMile, Integer endMile, Integer usingMile,
			String comment, String schoolArea, String operator, Tcar car,
			Ttrainer trainer) {
		super();
		this.id = id;
		this.numbering = numbering;
		this.usingDate = usingDate;
		this.reason = reason;
		this.place = place;
		this.startTime = startTime;
		this.endTime = endTime;
		this.startMile = startMile;
		this.endMile = endMile;
		this.usingMile = usingMile;
		this.comment = comment;
		this.schoolArea = schoolArea;
		this.operator = operator;
		this.trainer = trainer;
	}

	@Id
	@Column(name = "id", nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "numbering", length = 36)
	public String getNumbering() {
		return numbering;
	}

	public void setNumbering(String numbering) {
		this.numbering = numbering;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "usingDate", length = 9)
	public Date getUsingDate() {
		return usingDate;
	}

	public void setUsingDate(Date usingDate) {
		this.usingDate = usingDate;
	}

	@Column(name = "reason", length = 300)
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "place", length = 100)
	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	@Column(name = "startTime", length = 20)
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Column(name = "endTime", length = 20)
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Column(name = "startMile", length = 20)
	public Integer getStartMile() {
		return startMile;
	}

	public void setStartMile(Integer startMile) {
		this.startMile = startMile;
	}

	@Column(name = "carId", length = 36)
	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	@Column(name = "endMile", length = 20)
	public Integer getEndMile() {
		return endMile;
	}

	public void setEndMile(Integer endMile) {
		this.endMile = endMile;
	}

	@Column(name = "usingMile", length = 20)
	public Integer getUsingMile() {
		return usingMile;
	}

	public void setUsingMile(Integer usingMile) {
		this.usingMile = usingMile;
	}

	@Column(name = "comment", length = 300)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name = "schoolArea", length = 36)
	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	@Column(name = "operator", length = 36, updatable = false)
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "reservationId", length = 36)
	public String getReservationId() {
		return reservationId;
	}

	public void setReservationId(String reservationId) {
		this.reservationId = reservationId;
	}

	@Column(name = "usingcarState", length = 4)
	public Integer getUsingcarState() {
		return usingcarState;
	}

	public void setUsingcarState(Integer usingcarState) {
		this.usingcarState = usingcarState;
	}

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "trainerId")
	public Ttrainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Ttrainer trainer) {
		this.trainer = trainer;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usingcarTempId")
	public TcarusingTemp getUsingcarTemp() {
		return usingcarTemp;
	}

	public void setUsingcarTemp(TcarusingTemp usingcarTemp) {
		this.usingcarTemp = usingcarTemp;
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
		TusingCar other = (TusingCar) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TusingCar [id=" + id + ", numbering=" + numbering
				+ ", usingDate=" + usingDate + ", reason=" + reason
				+ ", place=" + place + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", startMile=" + startMile
				+ ", endMile=" + endMile + ", usingMile=" + usingMile
				+ ", comment=" + comment + ", schoolArea=" + schoolArea
				+ ", operator=" + operator + ", reservationId=" + reservationId
				+ ", trainer=" + trainer + ", usingcarTemp=" + usingcarTemp
				+ "]";
	}

}

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;

import com.alibaba.fastjson.JSON;

/**
 * 学员人个学时管理实体类
 * 
 * @author 农峰
 * 
 */
@Entity
@Table(name = "tb_personalTimings", schema = "")
public class TpersonalTiming implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5083519974626453067L;

	public static final Integer TIMINGTYPE_BUY = 0;// 购买
	public static final Integer TIMINGTYPE_USING = 1;// 消费

	public static final Integer CREATE_MANUAL = 0;// 手动创建记录标记
	public static final Integer CREATE_RESERVATION = 1;// 预约创建记录标记
	private String id;
	private String numbering;
	private Date date;
	private String timeItem;// 时段
	private Integer timingType;// (0:表示购买学时，1.表示消费学时)
	private String teachingContent;// 教学内容
	private String studentId;// 学员标识（添加冗余字段）
	private String operator;// 操作员

	private Integer timingUsingType;// 学时用途:1.电车，2.模拟　　3.五项　4.路训
	private Integer useTiming;// 使用课时

	private Ttiming timing;
	private Ttrainer trainer;

	private String reservationAddFlag;// 标记通过预约创建该学时记录
	private String schoolArea;// 校区标识（添加冗余字段）

	private Integer createWay;// 当前学时记录创建方式，0:手动创建　1:为预约创建

	public TpersonalTiming() {
	}

	public TpersonalTiming(String id, String numbering, Date date,
			String timeItem, Integer timingType, String teachingContent,
			Integer useTiming, String studentId, String operator,
			Ttiming timing, Ttrainer trainer, String reservationAddFlag,
			String schoolArea) {
		super();
		this.id = id;
		this.numbering = numbering;
		this.date = date;
		this.timeItem = timeItem;
		this.timingType = timingType;
		this.teachingContent = teachingContent;
		this.useTiming = useTiming;
		this.studentId = studentId;
		this.operator = operator;
		this.timing = timing;
		this.trainer = trainer;
		this.reservationAddFlag = reservationAddFlag;
		this.schoolArea = schoolArea;
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

	@Column(name = "timeItem", length = 30)
	public String getTimeItem() {
		return timeItem;
	}

	public void setTimeItem(String timeItem) {
		this.timeItem = timeItem;
	}

	@Column(name = "timingType", length = 15)
	public Integer getTimingType() {
		return timingType;
	}

	public void setTimingType(Integer timingType) {
		this.timingType = timingType;
	}

	@Column(name = "teachingContent", length = 100)
	public String getTeachingContent() {
		return teachingContent;
	}

	public void setTeachingContent(String teachingContent) {
		this.teachingContent = teachingContent;
	}

	@Column(name = "useTiming", length = 4)
	public Integer getUseTiming() {
		return useTiming;
	}

	public void setUseTiming(Integer useTiming) {
		this.useTiming = useTiming;
	}

	@Column(name = "studentId", length = 36)
	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	@Column(name = "reservationAddFlag", length = 36)
	public String getReservationAddFlag() {
		return reservationAddFlag;
	}

	public void setReservationAddFlag(String reservationAddFlag) {
		this.reservationAddFlag = reservationAddFlag;
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

	@Column(name = "timingUsingType", length = 4)
	public Integer getTimingUsingType() {
		return timingUsingType;
	}

	public void setTimingUsingType(Integer timingUsingType) {
		this.timingUsingType = timingUsingType;
	}

	@Column(name = "createWay", length = 4)
	public Integer getCreateWay() {
		return createWay;
	}

	public void setCreateWay(Integer createWay) {
		this.createWay = createWay;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trainerId")
	public Ttrainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Ttrainer trainer) {
		this.trainer = trainer;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "timingId", nullable = false)
	public Ttiming getTiming() {
		return timing;
	}

	public void setTiming(Ttiming timing) {
		this.timing = timing;
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
		TpersonalTiming other = (TpersonalTiming) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TpersonalTiming [id=" + id + ", numbering=" + numbering
				+ ", date=" + date + ", timeItem=" + timeItem + ", timingType="
				+ timingType + ", teachingContent=" + teachingContent
				+ ", studentId=" + studentId + ", operator=" + operator
				+ ", timingUsingType=" + timingUsingType + ", useTiming="
				+ useTiming + ", timing=" + timing + ", trainer=" + trainer
				+ ", reservationAddFlag=" + reservationAddFlag
				+ ", schoolArea=" + schoolArea + ", createWay=" + createWay
				+ "]";
	}

}

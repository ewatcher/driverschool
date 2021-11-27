package com.tuocheng.model.demo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
 * 学员计时管理实体类
 * 
 * @author 农峰
 * 
 */
@Entity
@Table(name = "tb_timings", schema = "")
public class Ttiming implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -8822258470607056843L;

	public static final Integer USINGTYPE_TRAMCAR = 1;// 电车
	public static final Integer USINGTYPE_SIMULATION = 2;// 模拟
	public static final Integer USINGTYPE_FIVEITERM = 3;// 五项
	public static final Integer USINGTYPE_ROADTRAINING = 4;// 路训

	private String id;// 唯一标识
	private String numbering;// 编号

	private Integer claimTiming;// 大纲要求学时
	private Integer handselTiming;// 赠送学时（报名时包含学时）

	private Integer totalTiming;// 总学时
	private Integer simulationTiming;// 模拟训练学时
	private Integer tramTiming;// 电车学时
	private Integer carTiming;// 实车学时
	private Integer usingTiming;// 已用学时
	private Integer restTiming;// 剩余学时
	private Integer reservationTiming;// 可预约的学时（如：学员剩余学时为3只有预约3个学时，但学员剩余学时不能减法3,因为存在预约取消情况）
	private Integer buyTiming;// 购买总学时

	private String comment;// 备注
	private String schoolArea;// 所属校区
	private String operator;// 操作员

	// ====connection relation=======
	private Tstudent student;
	private Set<TpersonalTiming> personalTimings = new HashSet<TpersonalTiming>();

	public Ttiming() {
	}

	public Ttiming(String id, String numbering, Integer simulationTiming,
			Integer tramTiming, Integer carTiming, Integer claimTiming,
			Integer handselTiming, Integer totalTiming, Integer usingTiming,
			Integer restTiming, Integer buyTiming, String comment,
			String schoolArea, String operator, String studentId,
			Tstudent student, Set<TpersonalTiming> personalTimings) {
		super();
		this.id = id;
		this.numbering = numbering;
		this.simulationTiming = simulationTiming;
		this.tramTiming = tramTiming;
		this.carTiming = carTiming;
		this.claimTiming = claimTiming;
		this.handselTiming = handselTiming;
		this.totalTiming = totalTiming;
		this.usingTiming = usingTiming;
		this.restTiming = restTiming;
		this.buyTiming = buyTiming;
		this.comment = comment;
		this.schoolArea = schoolArea;
		this.operator = operator;
		this.student = student;
		this.personalTimings = personalTimings;
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

	@Column(name = "simulationTiming", length = 5, columnDefinition = "INT DEFAULT 0")
	public Integer getSimulationTiming() {
		return simulationTiming;
	}

	public void setSimulationTiming(Integer simulationTiming) {
		this.simulationTiming = simulationTiming;
	}

	@Column(name = "tramTiming", length = 5, columnDefinition = "INT DEFAULT 0")
	public Integer getTramTiming() {
		return tramTiming;
	}

	public void setTramTiming(Integer tramTiming) {
		this.tramTiming = tramTiming;
	}

	@Column(name = "carTiming", length = 5, columnDefinition = "INT DEFAULT 0")
	public Integer getCarTiming() {
		return carTiming;
	}

	public void setCarTiming(Integer carTiming) {
		this.carTiming = carTiming;
	}

	@Column(name = "claimTiming", length = 5, columnDefinition = "INT DEFAULT 0")
	public Integer getClaimTiming() {
		return claimTiming;
	}

	public void setClaimTiming(Integer claimTiming) {
		this.claimTiming = claimTiming;
	}

	@Column(name = "handselTiming", length = 5, columnDefinition = "INT DEFAULT 0")
	public Integer getHandselTiming() {
		return handselTiming;
	}

	public void setHandselTiming(Integer handselTiming) {
		this.handselTiming = handselTiming;
	}

	@Column(name = "schoolArea", length = 36)
	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	@Column(name = "comment", length = 300)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "studentId")
	public Tstudent getStudent() {
		return student;
	}

	public void setStudent(Tstudent student) {
		this.student = student;
	}

	@Column(name = "operator", length = 36, updatable = false)
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "totalTiming", length = 8, columnDefinition = "INT DEFAULT 0")
	public Integer getTotalTiming() {
		return totalTiming;
	}

	public void setTotalTiming(Integer totalTimeing) {
		this.totalTiming = totalTimeing;
	}

	@Column(name = "usingTiming", length = 8, columnDefinition = "INT DEFAULT 0")
	public Integer getUsingTiming() {
		return usingTiming;
	}

	public void setUsingTiming(Integer usingTiming) {
		this.usingTiming = usingTiming;
	}

	@Column(name = "restTiming", length = 8, columnDefinition = "INT DEFAULT 0")
	public Integer getRestTiming() {
		return restTiming;
	}

	public void setRestTiming(Integer restTiming) {
		this.restTiming = restTiming;
	}

	@Column(name = "buyTiming", length = 8, columnDefinition = "INT DEFAULT 0")
	public Integer getBuyTiming() {
		return buyTiming;
	}

	public void setBuyTiming(Integer buyTiming) {
		this.buyTiming = buyTiming;
	}

	@Column(name = "reservationTiming", length = 8, columnDefinition = "INT DEFAULT 0")
	public Integer getReservationTiming() {
		return reservationTiming;
	}

	public void setReservationTiming(Integer reservationTiming) {
		this.reservationTiming = reservationTiming;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "timing", cascade = CascadeType.ALL)
	public Set<TpersonalTiming> getPersonalTimings() {
		return personalTimings;
	}

	public void setPersonalTimings(Set<TpersonalTiming> personalTimings) {
		this.personalTimings = personalTimings;
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
		Ttiming other = (Ttiming) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ttiming [id=" + id + ", numbering=" + numbering
				+ ", claimTiming=" + claimTiming + ", handselTiming="
				+ handselTiming + ", totalTiming=" + totalTiming
				+ ", simulationTiming=" + simulationTiming + ", tramTiming="
				+ tramTiming + ", carTiming=" + carTiming + ", usingTiming="
				+ usingTiming + ", restTiming=" + restTiming
				+ ", reservationTiming=" + reservationTiming + ", buyTiming="
				+ buyTiming + ", comment=" + comment + ", schoolArea="
				+ schoolArea + ", operator=" + operator + ", student="
				+ student + ", personalTimings=" + personalTimings + "]";
	}

}

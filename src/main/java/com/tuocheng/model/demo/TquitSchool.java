package com.tuocheng.model.demo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 退学登记实体类（DAO专用）
 * 
 * @author MEI702
 * 
 */
@Entity
@Table(name = "tb_quitschools", schema = "")
public class TquitSchool implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2327970892210963590L;
	private String id;// 标识
	private String numbering;// 编号
	private Date quitDate;// 记录时间
	private Integer quitCount;// 退学人数
	private Double debitFee;// 扣款金额
	private Double quitFee;// 退款金额
	private String quitReason;// 退学原因
	private String comment;// 备注
	private String schoolArea;// 所属校区
	private String operator;// 操作员

	// ===========conection relation============
	private Tstudent student;// 一对一

	public TquitSchool() {
	}

	public TquitSchool(String id, String numbering, Date quitDate,
			Integer quitCount, Double debitFee, Double quitFee, String comment) {
		this.id = id;
		this.numbering = numbering;
		this.quitDate = quitDate;
		this.quitCount = quitCount;
		this.debitFee = debitFee;
		this.quitFee = quitFee;
		this.comment = comment;
	}

	public TquitSchool(String id, String numbering, Date quitDate,
			Integer quitCount, Double debitFee, Double quitFee,
			String quitReason, String comment, String schoolArea,
			String operator, Tstudent student) {
		super();
		this.id = id;
		this.numbering = numbering;
		this.quitDate = quitDate;
		this.quitCount = quitCount;
		this.debitFee = debitFee;
		this.quitFee = quitFee;
		this.quitReason = quitReason;
		this.comment = comment;
		this.schoolArea = schoolArea;
		this.operator = operator;
		this.student = student;
	}

	@Id
	@Column(name = "id", nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "numbering", nullable = false, updatable = false, length = 36)
	public String getNumbering() {
		return numbering;
	}

	public void setNumbering(String numbering) {
		this.numbering = numbering;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "quitDate", length = 7)
	public Date getQuitDate() {
		return quitDate;
	}

	public void setQuitDate(Date quitDate) {
		this.quitDate = quitDate;
	}

	@Column(name = "quitCount", length = 12)
	public Integer getQuitCount() {
		return quitCount;
	}

	public void setQuitCount(Integer quitCount) {
		this.quitCount = quitCount;
	}

	@Column(name = "debitFee", length = 7)
	public Double getDebitFee() {
		return debitFee;
	}

	public void setDebitFee(Double debitFee) {
		this.debitFee = debitFee;
	}

	@Column(name = "quitFee", length = 7)
	public Double getQuitFee() {
		return quitFee;
	}

	public void setQuitFee(Double quitFee) {
		this.quitFee = quitFee;
	}

	@Column(name = "quitReason", length = 500)
	public String getQuitReason() {
		return quitReason;
	}

	public void setQuitReason(String quitReason) {
		this.quitReason = quitReason;
	}

	@Column(name = "comment", length = 500)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name = "schoolArea", length = 60)
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


	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "studentId")
	public Tstudent getStudent() {
		return student;
	}

	public void setStudent(Tstudent student) {
		this.student = student;
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
		TquitSchool other = (TquitSchool) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TquitSchool [id=" + id + ", Numbering=" + numbering
				+ ", quitDate=" + quitDate + ", quitCount=" + quitCount
				+ ", debitFee=" + debitFee + ", quitFee=" + quitFee
				+ ", comment=" + comment + "]";
	}

}

package com.tuocheng.model.demo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.alibaba.fastjson.JSON;

/**
 * 档案管理实体类
 * 
 * @author 农峰
 * 
 */
@Entity
@Table(name = "tb_studentfiles", schema = "")
public class TstudentFile implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1514309322303704286L;
	private String id;// 标识
	private String numbering;// 编号（自主编号）
	private Date contributionTime;// 资费日期
	private Integer learnTime;// 所交学时
	private Integer feeState;// 交费情况
	private Date theoryDate;// 理论考试日期
	private Date subjectTwoDate;// 科目二考试日期
	private Date subjectThreeDate;// 科目三考试日期
	private Date subjectFourDate;// 科目四考试日期
	private Date qualificationDate;// 资格考试日期
	private String schoolArea;// 所属校区
	private String comment;// 备注信息

	// ===========relaction connect======
	private Tstudent student;// 一个学员对应一份档案信息

	public TstudentFile() {
	}

	public TstudentFile(String id, String numbering, Date contributionTime,
			Integer learnTime, Integer feeState, Date theoryDate,
			Date subjectTwoDate, Date subjectThreeDate, Date subjectFourDate,
			Date qualificationDate, String schoolArea, String comment,
			Tstudent student) {
		super();
		this.id = id;
		this.numbering = numbering;
		this.contributionTime = contributionTime;
		this.learnTime = learnTime;
		this.feeState = feeState;
		this.theoryDate = theoryDate;
		this.subjectTwoDate = subjectTwoDate;
		this.subjectThreeDate = subjectThreeDate;
		this.subjectFourDate = subjectFourDate;
		this.qualificationDate = qualificationDate;
		this.schoolArea = schoolArea;
		this.comment = comment;
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

	@Column(name = "numbering", length = 36)
	public String getNumbering() {
		return numbering;
	}

	public void setNumbering(String numbering) {
		this.numbering = numbering;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "contributionTime", length = 7)
	public Date getContributionTime() {
		return contributionTime;
	}

	public void setContributionTime(Date contributionTime) {
		this.contributionTime = contributionTime;
	}

	@Column(name = "learnTime", length = 4)
	public Integer getLearnTime() {
		return learnTime;
	}

	public void setLearnTime(Integer learnTime) {
		this.learnTime = learnTime;
	}

	@Column(name = "feeState", length = 4)
	public Integer getFeeState() {
		return feeState;
	}

	public void setFeeState(Integer feeState) {
		this.feeState = feeState;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "theoryDate", length = 7)
	public Date getTheoryDate() {
		return theoryDate;
	}

	public void setTheoryDate(Date theoryDate) {
		this.theoryDate = theoryDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "subjectTwoDate", length = 7)
	public Date getSubjectTwoDate() {
		return subjectTwoDate;
	}

	public void setSubjectTwoDate(Date subjectTwoDate) {
		this.subjectTwoDate = subjectTwoDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "subjectThreeDate", length = 7)
	public Date getSubjectThreeDate() {
		return subjectThreeDate;
	}

	public void setSubjectThreeDate(Date subjectThreeDate) {
		this.subjectThreeDate = subjectThreeDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "subjectFourDate", length = 7)
	public Date getSubjectFourDate() {
		return subjectFourDate;
	}

	public void setSubjectFourDate(Date subjectFourDate) {
		this.subjectFourDate = subjectFourDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "qualificationDate", length = 7)
	public Date getQualificationDate() {
		return qualificationDate;
	}

	public void setQualificationDate(Date qualificationDate) {
		this.qualificationDate = qualificationDate;
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
		TstudentFile other = (TstudentFile) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TstudentFile [id=" + id + ", numbering=" + numbering
				+ ", contributionTime=" + contributionTime + ", learnTime="
				+ learnTime + ", feeState=" + feeState + ", theoryDate="
				+ theoryDate + ", subjectTwoDate=" + subjectTwoDate
				+ ", subjectThreeDate=" + subjectThreeDate
				+ ", subjectFourDate=" + subjectFourDate
				+ ", qualificationDate=" + qualificationDate + ", schoolArea="
				+ schoolArea + ", comment=" + comment + ", student=" + student
				+ "]";
	}

}

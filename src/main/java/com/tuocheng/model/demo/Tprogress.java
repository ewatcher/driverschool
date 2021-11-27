package com.tuocheng.model.demo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
 * 学员进度管理
 * 
 * @author 农峰
 * 
 */
@Entity
@Table(name = "tb_progress", schema = "")
public class Tprogress implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7758202238177071264L;
	public static final Integer PROGRESS_SINUP = 1;
	public static final Integer PROGRESS_SUBJECT1 = 2;
	public static final Integer PROGRESS_SUBJECT2 = 3;
	public static final Integer PROGRESS_SUBJECT3 = 4;
	public static final Integer PROGRESS_SUBJECT4 = 5;
	public static final Integer PROGRESS_GRASUATE = 6;
	public static final Integer PROGRESS_QUITSCHOOL = 7;
	public static final Integer PROGRESS_ZIGE = 8;

	private String id;
	private Integer state;// (1.报名，2.科目一，3科目二，4.科目三，5.科目四，6.毕业，7.退学)
	private Date sinupdateDate;
	private Date subject1Date;
	private Date subject2Date;
	private Date subject3Date;
	private Date subject4Date;
	private Date graduateDate;
	private Date quitSchoolDate;
	private String schoolArea;

	private Tstudent student;

	public Tprogress() {
	}

	public Tprogress(String id, Integer state, Date sinupdateDate,
			Date subject1Date, Date subject2Date, Date subject3Date,
			Date subject4Date, Date graduateDate, Date quitSchoolDate,
			String schoolArea, Tstudent student) {
		this.id = id;
		this.state = state;
		this.sinupdateDate = sinupdateDate;
		this.subject1Date = subject1Date;
		this.subject2Date = subject2Date;
		this.subject3Date = subject3Date;
		this.subject4Date = subject4Date;
		this.graduateDate = graduateDate;
		this.quitSchoolDate = quitSchoolDate;
		this.schoolArea = schoolArea;
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

	@Column(name = "state", length = 4)
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "sinupdateDate", length = 7)
	public Date getSinupdateDate() {
		return sinupdateDate;
	}

	public void setSinupdateDate(Date sinupdateDate) {
		this.sinupdateDate = sinupdateDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "subject1Date", length = 7)
	public Date getSubject1Date() {
		return subject1Date;
	}

	public void setSubject1Date(Date subject1Date) {
		this.subject1Date = subject1Date;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "subject2Date", length = 7)
	public Date getSubject2Date() {
		return subject2Date;
	}

	public void setSubject2Date(Date subject2Date) {
		this.subject2Date = subject2Date;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "subject3Date", length = 7)
	public Date getSubject3Date() {
		return subject3Date;
	}

	public void setSubject3Date(Date subject3Date) {
		this.subject3Date = subject3Date;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "subject4Date", length = 7)
	public Date getSubject4Date() {
		return subject4Date;
	}

	public void setSubject4Date(Date subject4Date) {
		this.subject4Date = subject4Date;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "graduateDate", length = 7)
	public Date getGraduateDate() {
		return graduateDate;
	}

	public void setGraduateDate(Date graduateDate) {
		this.graduateDate = graduateDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "quitSchoolDate", length = 7)
	public Date getQuitSchoolDate() {
		return quitSchoolDate;
	}

	public void setQuitSchoolDate(Date quitSchoolDate) {
		this.quitSchoolDate = quitSchoolDate;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "studentId")
	public Tstudent getStudent() {
		return student;
	}

	public void setStudent(Tstudent student) {
		this.student = student;
	}

	@Column(name = "schoolArea", length = 36)
	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
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
		Tprogress other = (Tprogress) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tprogress [id=" + id + ", state=" + state + ", sinupdateDate="
				+ sinupdateDate + ", subject1Date=" + subject1Date
				+ ", subject2Date=" + subject2Date + ", subject3Date="
				+ subject3Date + ", subject4Date=" + subject4Date
				+ ", graduateDate=" + graduateDate + ", quitSchoolDate="
				+ quitSchoolDate + ", schoolArea=" + schoolArea + ", student="
				+ student + "]";
	}

}

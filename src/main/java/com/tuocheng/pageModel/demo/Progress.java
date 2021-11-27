package com.tuocheng.pageModel.demo;

import java.util.Date;

import com.tuocheng.pageModel.base.StudentPageModel;

/**
 * 学员进度管理，数据模型action专用
 * 
 * @author 农峰
 * 
 */
public class Progress extends StudentPageModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1888043943161379285L;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getSinupdateDate() {
		return sinupdateDate;
	}

	public void setSinupdateDate(Date sinupdateDate) {
		this.sinupdateDate = sinupdateDate;
	}

	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	public Date getSubject1Date() {
		return subject1Date;
	}

	public void setSubject1Date(Date subject1Date) {
		this.subject1Date = subject1Date;
	}

	public Date getSubject2Date() {
		return subject2Date;
	}

	public void setSubject2Date(Date subject2Date) {
		this.subject2Date = subject2Date;
	}

	public Date getSubject3Date() {
		return subject3Date;
	}

	public void setSubject3Date(Date subject3Date) {
		this.subject3Date = subject3Date;
	}

	public Date getSubject4Date() {
		return subject4Date;
	}

	public void setSubject4Date(Date subject4Date) {
		this.subject4Date = subject4Date;
	}

	public Date getGraduateDate() {
		return graduateDate;
	}

	public void setGraduateDate(Date graduateDate) {
		this.graduateDate = graduateDate;
	}

	public Date getQuitSchoolDate() {
		return quitSchoolDate;
	}

	public void setQuitSchoolDate(Date quitSchoolDate) {
		this.quitSchoolDate = quitSchoolDate;
	}

}

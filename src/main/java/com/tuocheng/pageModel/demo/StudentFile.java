package com.tuocheng.pageModel.demo;

import java.util.Date;

import com.tuocheng.pageModel.base.StudentPageModel;

/**
 * 档案管理实体类(action)
 * 
 * @author 农峰
 * 
 */
public class StudentFile extends StudentPageModel {
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -4010995420001879913L;
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
	private String schoolAreaName;// 所属校区
	private String comment;// 备注信息

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

	public Date getContributionTime() {
		return contributionTime;
	}

	public void setContributionTime(Date contributionTime) {
		this.contributionTime = contributionTime;
	}

	public Integer getLearnTime() {
		return learnTime;
	}

	public void setLearnTime(Integer learnTime) {
		this.learnTime = learnTime;
	}

	public Integer getFeeState() {
		return feeState;
	}

	public String getSchoolAreaName() {
		return schoolAreaName;
	}

	public void setSchoolAreaName(String schoolAreaName) {
		this.schoolAreaName = schoolAreaName;
	}

	public void setFeeState(Integer feeState) {
		this.feeState = feeState;
	}

	public Date getTheoryDate() {
		return theoryDate;
	}

	public void setTheoryDate(Date theoryDate) {
		this.theoryDate = theoryDate;
	}

	public Date getSubjectTwoDate() {
		return subjectTwoDate;
	}

	public void setSubjectTwoDate(Date subjectTwoDate) {
		this.subjectTwoDate = subjectTwoDate;
	}

	public Date getSubjectThreeDate() {
		return subjectThreeDate;
	}

	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setSubjectThreeDate(Date subjectThreeDate) {
		this.subjectThreeDate = subjectThreeDate;
	}

	public Date getSubjectFourDate() {
		return subjectFourDate;
	}

	public void setSubjectFourDate(Date subjectFourDate) {
		this.subjectFourDate = subjectFourDate;
	}

	public Date getQualificationDate() {
		return qualificationDate;
	}

	public void setQualificationDate(Date qualificationDate) {
		this.qualificationDate = qualificationDate;
	}

}

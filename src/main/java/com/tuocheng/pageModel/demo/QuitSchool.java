package com.tuocheng.pageModel.demo;

import java.util.Date;

import com.tuocheng.pageModel.base.StudentPageModel;

/**
 * 退学登记实体类（action专用）
 * 
 * @author MEI702
 * 
 */
public class QuitSchool extends StudentPageModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8635107473817086403L;
	private String id;// 标识
	private String numbering;// 编号
	private Date quitDate;//
	private Integer quitCount;// 退学人数
	private Double debitFee;// 扣款金额
	private Double quitFee;// 退款金额
	private String quitReason;// 退学原因
	private String comment;// 备注
	private String schoolArea;// 所属校区
	private String operator;// 操作员

	private Date quitDateStart;// 记录时间开始
	private Date quitDateEnd;// 记录时间结束
	private String schoolAreaName;

	private String trainerName;

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

	public Date getQuitDateStart() {
		return quitDateStart;
	}

	public void setQuitDateStart(Date quitDateStart) {
		this.quitDateStart = quitDateStart;
	}

	public Date getQuitDateEnd() {
		return quitDateEnd;
	}

	public void setQuitDateEnd(Date quitDateEnd) {
		this.quitDateEnd = quitDateEnd;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getQuitDate() {
		return quitDate;
	}

	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	public void setQuitDate(Date quitDate) {
		this.quitDate = quitDate;
	}

	public Integer getQuitCount() {
		return quitCount;
	}

	public void setQuitCount(Integer quitCount) {
		this.quitCount = quitCount;
	}

	public Double getDebitFee() {
		return debitFee;
	}

	public void setDebitFee(Double debitFee) {
		this.debitFee = debitFee;
	}

	public Double getQuitFee() {
		return quitFee;
	}

	public void setQuitFee(Double quitFee) {
		this.quitFee = quitFee;
	}

	public String getQuitReason() {
		return quitReason;
	}

	public void setQuitReason(String quitReason) {
		this.quitReason = quitReason;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getSchoolAreaName() {
		return schoolAreaName;
	}

	public void setSchoolAreaName(String schoolAreaName) {
		this.schoolAreaName = schoolAreaName;
	}

	public String getTrainerName() {
		return trainerName;
	}

	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}

}
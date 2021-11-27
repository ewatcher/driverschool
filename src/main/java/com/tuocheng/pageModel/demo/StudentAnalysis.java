package com.tuocheng.pageModel.demo;

import com.tuocheng.pageModel.base.StudentPageModel;

public class StudentAnalysis extends StudentPageModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5243524312191688359L;

	private String schoolId;
	private String organizationIds;
	private String organizationId;
	private Long totals;
	private Integer driverTypeId;
	private Integer dataType;// (年，月，周，日)

	private Long examTotals;
	private Long joinExamTotals;
	private Long passExamTotals;

	

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getOrganizationIds() {
		return organizationIds;
	}

	public void setOrganizationIds(String organizationIds) {
		this.organizationIds = organizationIds;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public Long getTotals() {
		return totals;
	}

	public void setExamTotals(Long examTotals) {
		this.examTotals = examTotals;
	}

	public Long getExamTotals() {
		return examTotals;
	}

	public Long getJoinExamTotals() {
		return joinExamTotals;
	}

	public Long getPassExamTotals() {
		return passExamTotals;
	}

	public void setJoinExamTotals(Long joinExamTotals) {
		this.joinExamTotals = joinExamTotals;
	}

	public void setPassExamTotals(Long passExamTotals) {
		this.passExamTotals = passExamTotals;
	}

	public void setTotals(Long totals) {
		this.totals = totals;
	}

	public Integer getDriverTypeId() {
		return driverTypeId;
	}

	public Integer getDataType() {
		return dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	public void setDriverTypeId(Integer driverTypeId) {
		this.driverTypeId = driverTypeId;
	}


}

package com.tuocheng.pageModel.demo;

import java.util.Date;

/**
 * 人员管理实体类（action专用）
 * 
 * @author MEI702
 * 
 */
public class Person extends PageModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3384133517578975903L;
	// ====================base msg======================
	private String id;// 标识
	private String name;// 姓名
	private String identityId;// 身份证号
	private String sex;// 性别
	private String nation;// 民族
	private String birthPlace;// 出生地
	private Date birthday;// 出生日期
	private Date startedJob;// 参加工作
	private String political;// 政治面貌
	private String profession;// 专业
	private String educationLevel;// 文化程度
	private String address;// 地址
	private String accountNature;// 户口性质
	private String graduateSchool;// 毕业学校
	private Date graduateDay;// 毕业日期
	private String email;// 邮箱
	private String phone;// 手机
	private String department;// 所属部门
	private String duty;// 职务
	private String driverLevel;// 驾照级别
	private Date employDay;// 录用日期
	private String marriageState;// 婚姻状态(已婚，未婚)
	private String languageLevel;// 外语等级
	private String computerLevel;// 计算机等级
	private String comment;// 备注
	private String operator;// 操作员
	private Integer propertyType;// 业务员属性0：非教练 1：教练默认为0

	// ===========add properties========

	private String ids;
	private String departmentId;// 组织机构标识
	private String schoolAreaId;// 校区标识

	public Integer getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(Integer propertyType) {
		this.propertyType = propertyType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentityId() {
		return identityId;
	}

	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSchoolAreaId() {
		return schoolAreaId;
	}

	public void setSchoolAreaId(String schoolAreaId) {
		this.schoolAreaId = schoolAreaId;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getStartedJob() {
		return startedJob;
	}

	public void setStartedJob(Date startedJob) {
		this.startedJob = startedJob;
	}

	public String getPolitical() {
		return political;
	}

	public void setPolitical(String political) {
		this.political = political;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAccountNature() {
		return accountNature;
	}

	public void setAccountNature(String accountNature) {
		this.accountNature = accountNature;
	}

	public String getGraduateSchool() {
		return graduateSchool;
	}

	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}

	public Date getGraduateDay() {
		return graduateDay;
	}

	public void setGraduateDay(Date graduateDay) {
		this.graduateDay = graduateDay;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getDriverLevel() {
		return driverLevel;
	}

	public void setDriverLevel(String driverLevel) {
		this.driverLevel = driverLevel;
	}

	public Date getEmployDay() {
		return employDay;
	}

	public void setEmployDay(Date enployDay) {
		this.employDay = enployDay;
	}

	public String getMarriageState() {
		return marriageState;
	}

	public void setMarriageState(String marriageState) {
		this.marriageState = marriageState;
	}

	public String getLanguageLevel() {
		return languageLevel;
	}

	public void setLanguageLevel(String languageLevel) {
		this.languageLevel = languageLevel;
	}

	public String getComputerLevel() {
		return computerLevel;
	}

	public void setComputerLevel(String computerLevel) {
		this.computerLevel = computerLevel;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
}

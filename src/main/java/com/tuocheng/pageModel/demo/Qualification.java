package com.tuocheng.pageModel.demo;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder.In;

/**
 * 资格考试考试实体类action专用
 * 
 * @author MEI702
 * 
 */
public class Qualification extends PageModel {

	/**
	 * 序列标识
	 */
	private static final long serialVersionUID = -1567519554375319222L;
	private String id;// 标识
	private Date examDate;// 资格考试日期
	private Integer examScoke;// 资格考试成绩(1：缺考 2：合格，3：不合格)
	private Date makeupExameDate;// 补考1日期
	private Integer makeupExameScoke;// 补考1成绩(1：缺考 2：合格，3：不合格)

	private Integer examType;// 考试类型(1:A1,2:A2,3:A3,4:B1,5:B2,6:C1,7:C2,8:C3,9:C4,10:D,11:E,12:F,13:M,14:N)
	private String schoolArea;// 所属校区（采用违反第三范式，添加冗余字段，解决关联关系ognl达到三层问题）
	private String comment;// 备注
	private String arrangedNo;// 考试计划编号

	private Integer passFlag;// (0:表示不通过，1表示通过)
	private Integer examSelected;// （1.表示该学员已经参科目二考试，0.反之）
	private String operator;//操作员

	// ==========reflect relation=======
	private String ids;
	private String studentId;
	private String studentNo;// 学员编号
	private String name;
	private String studentOrganization;// 学员所属校区
	private String studentClazz;// 学员所属班级
	private String studentSex;// 性别
	private Date createTime;// 报名日期
	private String studentPhone;// 手机号
	private String identityId;// 学员身份证号
	private Integer studentDriverType;// 学员考试类型（A1、A2，C，……。）

	private Date studentBirthdate; // 出生日期
	private String studentContry; // 国籍
	private String studentAddress; // 地址－－必填字段
	private String studentNativeNation; // 地域选择属性：本地、异地
	private String studentTelephone; // 固定电话
	private String studentEmail; // 邮箱
	private String studentMailCode;// 邮编
	private String studentResidenceId;// 居住证
	private String studentResidenceAddr;// 记住证地址
	private String studentImageId;// 照片回执编号
	private Integer studentNowState;// 学员在学状态（注册，在培训，毕业，退学，到期，不计时）
	private Integer studentApplyType;// 申请类型：(1:初次申领,2:增加准驾车型,3:持军官驾驶证,4:持境外驾驶证)

	// 查询学员信息字段
	private Integer driverType;// 考试类型（A1、A2，C，……。）查询
	private Date registerTimeStart;// 注册开始
	private Date registerTimeEnd;// 注册结束
	private Integer scokeValue;// 考试成绩查询（1.缺考，2.合格，3.不合格,4.未考试）

	private Date examDateStart;
	private Date examDateEnd;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getExamDate() {
		return examDate;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public String getArrangedNo() {
		return arrangedNo;
	}

	public void setArrangedNo(String arrangedNo) {
		this.arrangedNo = arrangedNo;
	}

	public Integer getExamScoke() {
		return examScoke;
	}

	public void setExamScoke(Integer examScoke) {
		this.examScoke = examScoke;
	}

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public Integer getScokeValue() {
		return scokeValue;
	}

	public void setScokeValue(Integer scokeValue) {
		this.scokeValue = scokeValue;
	}

	public Integer getPassFlag() {
		return passFlag;
	}

	public void setPassFlag(Integer passFlag) {
		this.passFlag = passFlag;
	}

	public Integer getExamSelected() {
		return examSelected;
	}

	public void setExamSelected(Integer examSelected) {
		this.examSelected = examSelected;
	}

	public Integer getExamType() {
		return examType;
	}

	public void setExamType(Integer examType) {
		this.examType = examType;
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

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStudentOrganization() {
		return studentOrganization;
	}

	public void setStudentOrganization(String studentOrganization) {
		this.studentOrganization = studentOrganization;
	}

	public String getStudentClazz() {
		return studentClazz;
	}

	public void setStudentClazz(String studentClazz) {
		this.studentClazz = studentClazz;
	}

	public String getStudentSex() {
		return studentSex;
	}

	public void setStudentSex(String studentSex) {
		this.studentSex = studentSex;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getStudentPhone() {
		return studentPhone;
	}

	public void setStudentPhone(String studentPhone) {
		this.studentPhone = studentPhone;
	}

	public String getIdentityId() {
		return identityId;
	}

	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}

	public Integer getStudentDriverType() {
		return studentDriverType;
	}

	public void setStudentDriverType(Integer studentDriverType) {
		this.studentDriverType = studentDriverType;
	}

	public Date getStudentBirthdate() {
		return studentBirthdate;
	}

	public void setStudentBirthdate(Date studentBirthdate) {
		this.studentBirthdate = studentBirthdate;
	}

	public String getStudentContry() {
		return studentContry;
	}

	public void setStudentContry(String studentContry) {
		this.studentContry = studentContry;
	}

	public String getStudentAddress() {
		return studentAddress;
	}

	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}

	public String getStudentNativeNation() {
		return studentNativeNation;
	}

	public void setStudentNativeNation(String studentNativeNation) {
		this.studentNativeNation = studentNativeNation;
	}

	public String getStudentTelephone() {
		return studentTelephone;
	}

	public void setStudentTelephone(String studentTelephone) {
		this.studentTelephone = studentTelephone;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public String getStudentMailCode() {
		return studentMailCode;
	}

	public void setStudentMailCode(String studentMailCode) {
		this.studentMailCode = studentMailCode;
	}

	public String getStudentResidenceId() {
		return studentResidenceId;
	}

	public void setStudentResidenceId(String studentResidenceId) {
		this.studentResidenceId = studentResidenceId;
	}

	public String getStudentResidenceAddr() {
		return studentResidenceAddr;
	}

	public void setStudentResidenceAddr(String studentResidenceAddr) {
		this.studentResidenceAddr = studentResidenceAddr;
	}

	public String getStudentImageId() {
		return studentImageId;
	}

	public void setStudentImageId(String studentImageId) {
		this.studentImageId = studentImageId;
	}

	public Integer getStudentNowState() {
		return studentNowState;
	}

	public void setStudentNowState(Integer studentNowState) {
		this.studentNowState = studentNowState;
	}

	public Integer getStudentApplyType() {
		return studentApplyType;
	}

	public void setStudentApplyType(Integer studentApplyType) {
		this.studentApplyType = studentApplyType;
	}

	public Integer getDriverType() {
		return driverType;
	}

	public void setDriverType(Integer driverType) {
		this.driverType = driverType;
	}

	public Date getRegisterTimeStart() {
		return registerTimeStart;
	}

	public void setRegisterTimeStart(Date registerTimeStart) {
		this.registerTimeStart = registerTimeStart;
	}

	public Date getRegisterTimeEnd() {
		return registerTimeEnd;
	}

	public void setRegisterTimeEnd(Date registerTimeEnd) {
		this.registerTimeEnd = registerTimeEnd;
	}

	public Date getExamDateStart() {
		return examDateStart;
	}

	public void setExamDateStart(Date examDateStart) {
		this.examDateStart = examDateStart;
	}

	public Date getExamDateEnd() {
		return examDateEnd;
	}

	public void setExamDateEnd(Date examDateEnd) {
		this.examDateEnd = examDateEnd;
	}

	public Date getMakeupExameDate() {
		return makeupExameDate;
	}

	public void setMakeupExameDate(Date makeupExameDate) {
		this.makeupExameDate = makeupExameDate;
	}

	public Integer getMakeupExameScoke() {
		return makeupExameScoke;
	}

	public void setMakeupExameScoke(Integer makeupExameScoke) {
		this.makeupExameScoke = makeupExameScoke;
	}

}

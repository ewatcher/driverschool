package com.tuocheng.pageModel.base;

import java.io.Serializable;
import java.util.Date;

/**
 * 封装学员实体类属性信息，并带分面功能
 * 
 * @author MEI702
 * 
 */
public class StudentPageModel implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -4779168918307731285L;
	// ====分页排序信息＝＝＝＝＝＝＝＝＝＝＝
	private int page;// 当前页
	private int rows;// 每页显示记录数
	private String sort;// 排序字段名
	private String order;// 按什么排序(asc,desc)
	// ===学员属性信息
	private String ids;//
	private String studentId;// 学员标识
	private String studentName;// 学员改名
	private String studentSex;
	private String studentNo;// 学员编号
	private String studentIdentityId;// 学员身份证号
	private Integer studentNowstate;// 学员状态
	private String studentOrganization;// 学员所属于校区
	private Date studentBirthdate; // 出生日期
	private String studentContry; // 国籍
	private String studentAddress; // 地址－－必填字段
	private String studentNativeNation; // 地域选择属性：本地、异地
	private String studentTelephone; // 固定电话
	private String studentPhone; // 手机
	private String studentEmail; // 邮箱
	private String studentMailCode;// 邮编
	private String studentResidenceId;// 居住证
	private String studentResidenceAddr;// 记住证地址
	private String studentImageId;// 照片回执编号
	private Date studentCreateTime;// 注册时间－－－必填字段
	private Date studentGraduateDate;// 毕业日期
	private Integer studentDriverType;// 驾照类型(1:A1,2:A2,3:A3,4:B1,5:B2,6:C1,7:C2,8:C3,9:C4,10:D,11:E,12:F,13:M,14:N)
	private Integer studentApplyType;// 申请类型：(1:初次申领,2:增加准驾车型,3:持军官驾驶证,4:持境外驾驶证)
	private String studentClazz;
	private Integer examSelected;// （1.表示该学员已经参加理论考试，0.反之）
	private Integer studentType;// 0:表示本部学员，1：表示外包学员
	private Double studentFeed;// 学费
	private Double studentRealFeed;// 实收学费
	private Double studentOwnFeed;// 欠学费
	private Integer studentTimingFlag;
	private Integer studentLearningTime;// 学时
	private String primaryDriver;// 原来车型
	private String examPhone;// 考试电话
	private Integer studentBatch;// 第几期
	private String otherNo;// 编号考试专用
	private Integer qualificationFlag;// 0：否 1：是，默认0


	private String verificationCode;// 验证码
	// 查询字段
	private Date studentCreateTimeStart;
	private Date studentCreateTimeEnd;

	private String studentOrganizationName;// 学员所属于校区
	private String organizationIds;
	private String studentPassword;

	private String studentTrainerId;
	private String studentTrainerName;
	private String studentTrainerPhone;
	private String studentTrainerIdentityId;
	private Integer studentTrainerArrangeType;

	private String netPiontId;// 报名网点
	private String netPiontName;// 报名网点

	private String principalType;
	private String principalSn;
	private int permission;


	public String getPrincipalType() {
		return principalType;
	}

	public void setPrincipalType(String principalType) {
		this.principalType = principalType;
	}

	public String getPrincipalSn() {
		return principalSn;
	}

	public void setPrincipalSn(String principalSn) {
		this.principalSn = principalSn;
	}

	public int getPermission() {
		return permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}

	public String getNetPiontId() {
		return netPiontId;
	}

	public void setNetPiontId(String netPiontId) {
		this.netPiontId = netPiontId;
	}

	public String getNetPiontName() {
		return netPiontName;
	}

	public void setNetPiontName(String netPiontName) {
		this.netPiontName = netPiontName;
	}

	public Integer getQualificationFlag() {
		return qualificationFlag;
	}

	public void setQualificationFlag(Integer qualificationFlag) {
		this.qualificationFlag = qualificationFlag;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
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

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentSex() {
		return studentSex;
	}

	public void setStudentSex(String studentSex) {
		this.studentSex = studentSex;
	}

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public String getStudentIdentityId() {
		return studentIdentityId;
	}

	public void setStudentIdentityId(String studentIdentityId) {
		this.studentIdentityId = studentIdentityId;
	}

	public Integer getStudentNowstate() {
		return studentNowstate;
	}

	public void setStudentNowstate(Integer studentNowstate) {
		this.studentNowstate = studentNowstate;
	}

	public String getStudentOrganization() {
		return studentOrganization;
	}

	public void setStudentOrganization(String studentOrganization) {
		this.studentOrganization = studentOrganization;
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

	public String getStudentPhone() {
		return studentPhone;
	}

	public void setStudentPhone(String studentPhone) {
		this.studentPhone = studentPhone;
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

	public Date getStudentCreateTime() {
		return studentCreateTime;
	}

	public void setStudentCreateTime(Date studentCreateTime) {
		this.studentCreateTime = studentCreateTime;
	}

	public Date getStudentGraduateDate() {
		return studentGraduateDate;
	}

	public void setStudentGraduateDate(Date studentGraduateDate) {
		this.studentGraduateDate = studentGraduateDate;
	}

	public Integer getStudentDriverType() {
		return studentDriverType;
	}

	public void setStudentDriverType(Integer studentDriverType) {
		this.studentDriverType = studentDriverType;
	}

	public Integer getStudentApplyType() {
		return studentApplyType;
	}

	public void setStudentApplyType(Integer studentApplyType) {
		this.studentApplyType = studentApplyType;
	}

	public String getStudentClazz() {
		return studentClazz;
	}

	public void setStudentClazz(String studentClazz) {
		this.studentClazz = studentClazz;
	}

	public Integer getExamSelected() {
		return examSelected;
	}

	public void setExamSelected(Integer examSelected) {
		this.examSelected = examSelected;
	}

	public Integer getStudentType() {
		return studentType;
	}

	public void setStudentType(Integer studentType) {
		this.studentType = studentType;
	}

	public Double getStudentFeed() {
		return studentFeed;
	}

	public void setStudentFeed(Double studentFeed) {
		this.studentFeed = studentFeed;
	}

	public Double getStudentRealFeed() {
		return studentRealFeed;
	}

	public void setStudentRealFeed(Double studentRealFeed) {
		this.studentRealFeed = studentRealFeed;
	}

	public Double getStudentOwnFeed() {
		return studentOwnFeed;
	}

	public void setStudentOwnFeed(Double studentOwnFeed) {
		this.studentOwnFeed = studentOwnFeed;
	}

	public Integer getStudentTimingFlag() {
		return studentTimingFlag;
	}

	public void setStudentTimingFlag(Integer studentTimingFlag) {
		this.studentTimingFlag = studentTimingFlag;
	}

	public Integer getStudentLearningTime() {
		return studentLearningTime;
	}

	public void setStudentLearningTime(Integer studentLearningTime) {
		this.studentLearningTime = studentLearningTime;
	}

	public String getPrimaryDriver() {
		return primaryDriver;
	}

	public void setPrimaryDriver(String primaryDriver) {
		this.primaryDriver = primaryDriver;
	}

	public String getExamPhone() {
		return examPhone;
	}

	public void setExamPhone(String examPhone) {
		this.examPhone = examPhone;
	}

	public Integer getStudentBatch() {
		return studentBatch;
	}

	public void setStudentBatch(Integer studentBatch) {
		this.studentBatch = studentBatch;
	}

	public Date getStudentCreateTimeStart() {
		return studentCreateTimeStart;
	}

	public void setStudentCreateTimeStart(Date studentCreateTimeStart) {
		this.studentCreateTimeStart = studentCreateTimeStart;
	}

	public Date getStudentCreateTimeEnd() {
		return studentCreateTimeEnd;
	}

	public void setStudentCreateTimeEnd(Date studentCreateTimeEnd) {
		this.studentCreateTimeEnd = studentCreateTimeEnd;
	}

	public String getStudentOrganizationName() {
		return studentOrganizationName;
	}

	public void setStudentOrganizationName(String studentOrganizationName) {
		this.studentOrganizationName = studentOrganizationName;
	}

	public String getOrganizationIds() {
		return organizationIds;
	}

	public void setOrganizationIds(String organizationIds) {
		this.organizationIds = organizationIds;
	}

	public String getStudentPassword() {
		return studentPassword;
	}

	public void setStudentPassword(String studentPassword) {
		this.studentPassword = studentPassword;
	}

	public String getStudentTrainerId() {
		return studentTrainerId;
	}

	public void setStudentTrainerId(String studentTrainerId) {
		this.studentTrainerId = studentTrainerId;
	}

	public String getStudentTrainerName() {
		return studentTrainerName;
	}

	public void setStudentTrainerName(String studentTrainerName) {
		this.studentTrainerName = studentTrainerName;
	}

	public String getStudentTrainerPhone() {
		return studentTrainerPhone;
	}

	public void setStudentTrainerPhone(String studentTrainerPhone) {
		this.studentTrainerPhone = studentTrainerPhone;
	}

	public String getStudentTrainerIdentityId() {
		return studentTrainerIdentityId;
	}

	public void setStudentTrainerIdentityId(String studentTrainerIdentityId) {
		this.studentTrainerIdentityId = studentTrainerIdentityId;
	}

	public Integer getStudentTrainerArrangeType() {
		return studentTrainerArrangeType;
	}

	public void setStudentTrainerArrangeType(Integer studentTrainerArrangeType) {
		this.studentTrainerArrangeType = studentTrainerArrangeType;
	}

	public String getOtherNo() {
		return otherNo;
	}

	public void setOtherNo(String otherNo) {
		this.otherNo = otherNo;
	}

}

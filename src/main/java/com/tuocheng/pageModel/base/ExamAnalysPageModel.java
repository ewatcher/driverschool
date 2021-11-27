package com.tuocheng.pageModel.base;

import java.io.Serializable;
import java.util.Date;

/**
 * 封装学员实体类属性信息，并带分面功能
 * 
 * @author MEI702
 * 
 */
public class ExamAnalysPageModel implements Serializable {

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

	private Integer batch;// 批次
	private Long total;// 该批学员总数
	private Date createDate;// 创建日期
	private Date subject1ExamDate;// 科一考试日期
	private Integer subject1HandupTotal;// 科一报名人数
	private Integer subject1ApproveTotal;// 科一通过审批人数
	private Integer subject1RealExamTotal;// 科一实考人数
	private Integer subject1MissExamTotal;// 科一缺考
	private Integer subject1PassExamTotal;// 科一合格人数
	private Date subject2ExamDate;// 科二考试日期
	private Integer subject2HandupTotal;// 科二报名人数
	private Integer subject2ApproveTotal;// 科二通过审批人数
	private Integer subject2RealExamTotal;// 科二实考人数
	private Integer subject2MissExamTotal;// 科二缺考
	private Integer subject2PassExamTotal;// 科二合格人数
	private Date subject3ExamDate;// 科三考试日期
	private Integer subject3HandupTotal;// 科三报名人数
	private Integer subject3ApproveTotal;// 科三通过审批人数
	private Integer subject3RealExamTotal;// 科三实考人数
	private Integer subject3MissExamTotal;// 科三缺考
	private Integer subject3PassExamTotal;// 科三合格人数
	private Date subject4ExamDate;// 科四考试日期
	private Integer subject4HandupTotal;// 科四报名人数
	private Integer subject4ApproveTotal;// 科四通过审批人数
	private Integer subject4RealExamTotal;// 科四实考人数
	private Integer subject4MissExamTotal;// 科四缺考
	private Integer subject4PassExamTotal;// 科四合格人数
	private Date subject5ExamDate;// 科五考试日期
	private Integer subject5HandupTotal;// 科五报名人数
	private Integer subject5ApproveTotal;// 科五通过审批人数
	private Integer subject5RealExamTotal;// 科五实考人数
	private Integer subject5MissExamTotal;// 科五缺考
	private Integer subject5PassExamTotal;// 科五合格人数

	private String selectSchoolArea;// 超级用户选择校区值
	private String schoolAreaName;// 校区标识名称

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

	public Integer getBatch() {
		return batch;
	}

	public void setBatch(Integer batch) {
		this.batch = batch;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getSubject1ExamDate() {
		return subject1ExamDate;
	}

	public void setSubject1ExamDate(Date subject1ExamDate) {
		this.subject1ExamDate = subject1ExamDate;
	}

	public Integer getSubject1HandupTotal() {
		return subject1HandupTotal;
	}

	public void setSubject1HandupTotal(Integer subject1HandupTotal) {
		this.subject1HandupTotal = subject1HandupTotal;
	}

	public Integer getSubject1ApproveTotal() {
		return subject1ApproveTotal;
	}

	public void setSubject1ApproveTotal(Integer subject1ApproveTotal) {
		this.subject1ApproveTotal = subject1ApproveTotal;
	}

	public Integer getSubject1RealExamTotal() {
		return subject1RealExamTotal;
	}

	public void setSubject1RealExamTotal(Integer subject1RealExamTotal) {
		this.subject1RealExamTotal = subject1RealExamTotal;
	}

	public Integer getSubject1MissExamTotal() {
		return subject1MissExamTotal;
	}

	public void setSubject1MissExamTotal(Integer subject1MissExamTotal) {
		this.subject1MissExamTotal = subject1MissExamTotal;
	}

	public Integer getSubject1PassExamTotal() {
		return subject1PassExamTotal;
	}

	public void setSubject1PassExamTotal(Integer subject1PassExamTotal) {
		this.subject1PassExamTotal = subject1PassExamTotal;
	}

	public Date getSubject2ExamDate() {
		return subject2ExamDate;
	}

	public void setSubject2ExamDate(Date subject2ExamDate) {
		this.subject2ExamDate = subject2ExamDate;
	}

	public Integer getSubject2HandupTotal() {
		return subject2HandupTotal;
	}

	public void setSubject2HandupTotal(Integer subject2HandupTotal) {
		this.subject2HandupTotal = subject2HandupTotal;
	}

	public Integer getSubject2ApproveTotal() {
		return subject2ApproveTotal;
	}

	public void setSubject2ApproveTotal(Integer subject2ApproveTotal) {
		this.subject2ApproveTotal = subject2ApproveTotal;
	}

	public Integer getSubject2RealExamTotal() {
		return subject2RealExamTotal;
	}

	public void setSubject2RealExamTotal(Integer subject2RealExamTotal) {
		this.subject2RealExamTotal = subject2RealExamTotal;
	}

	public Integer getSubject2MissExamTotal() {
		return subject2MissExamTotal;
	}

	public void setSubject2MissExamTotal(Integer subject2MissExamTotal) {
		this.subject2MissExamTotal = subject2MissExamTotal;
	}

	public Integer getSubject2PassExamTotal() {
		return subject2PassExamTotal;
	}

	public void setSubject2PassExamTotal(Integer subject2PassExamTotal) {
		this.subject2PassExamTotal = subject2PassExamTotal;
	}

	public Date getSubject3ExamDate() {
		return subject3ExamDate;
	}

	public void setSubject3ExamDate(Date subject3ExamDate) {
		this.subject3ExamDate = subject3ExamDate;
	}

	public Integer getSubject3HandupTotal() {
		return subject3HandupTotal;
	}

	public void setSubject3HandupTotal(Integer subject3HandupTotal) {
		this.subject3HandupTotal = subject3HandupTotal;
	}

	public Integer getSubject3ApproveTotal() {
		return subject3ApproveTotal;
	}

	public void setSubject3ApproveTotal(Integer subject3ApproveTotal) {
		this.subject3ApproveTotal = subject3ApproveTotal;
	}

	public Integer getSubject3RealExamTotal() {
		return subject3RealExamTotal;
	}

	public void setSubject3RealExamTotal(Integer subject3RealExamTotal) {
		this.subject3RealExamTotal = subject3RealExamTotal;
	}

	public Integer getSubject3MissExamTotal() {
		return subject3MissExamTotal;
	}

	public void setSubject3MissExamTotal(Integer subject3MissExamTotal) {
		this.subject3MissExamTotal = subject3MissExamTotal;
	}

	public Integer getSubject3PassExamTotal() {
		return subject3PassExamTotal;
	}

	public void setSubject3PassExamTotal(Integer subject3PassExamTotal) {
		this.subject3PassExamTotal = subject3PassExamTotal;
	}

	public Date getSubject4ExamDate() {
		return subject4ExamDate;
	}

	public void setSubject4ExamDate(Date subject4ExamDate) {
		this.subject4ExamDate = subject4ExamDate;
	}

	public Integer getSubject4HandupTotal() {
		return subject4HandupTotal;
	}

	public void setSubject4HandupTotal(Integer subject4HandupTotal) {
		this.subject4HandupTotal = subject4HandupTotal;
	}

	public Integer getSubject4ApproveTotal() {
		return subject4ApproveTotal;
	}

	public void setSubject4ApproveTotal(Integer subject4ApproveTotal) {
		this.subject4ApproveTotal = subject4ApproveTotal;
	}

	public Integer getSubject4RealExamTotal() {
		return subject4RealExamTotal;
	}

	public void setSubject4RealExamTotal(Integer subject4RealExamTotal) {
		this.subject4RealExamTotal = subject4RealExamTotal;
	}

	public Integer getSubject4MissExamTotal() {
		return subject4MissExamTotal;
	}

	public void setSubject4MissExamTotal(Integer subject4MissExamTotal) {
		this.subject4MissExamTotal = subject4MissExamTotal;
	}

	public Integer getSubject4PassExamTotal() {
		return subject4PassExamTotal;
	}

	public void setSubject4PassExamTotal(Integer subject4PassExamTotal) {
		this.subject4PassExamTotal = subject4PassExamTotal;
	}

	public Date getSubject5ExamDate() {
		return subject5ExamDate;
	}

	public void setSubject5ExamDate(Date subject5ExamDate) {
		this.subject5ExamDate = subject5ExamDate;
	}

	public Integer getSubject5HandupTotal() {
		return subject5HandupTotal;
	}

	public void setSubject5HandupTotal(Integer subject5HandupTotal) {
		this.subject5HandupTotal = subject5HandupTotal;
	}

	public Integer getSubject5ApproveTotal() {
		return subject5ApproveTotal;
	}

	public void setSubject5ApproveTotal(Integer subject5ApproveTotal) {
		this.subject5ApproveTotal = subject5ApproveTotal;
	}

	public Integer getSubject5RealExamTotal() {
		return subject5RealExamTotal;
	}

	public void setSubject5RealExamTotal(Integer subject5RealExamTotal) {
		this.subject5RealExamTotal = subject5RealExamTotal;
	}

	public Integer getSubject5MissExamTotal() {
		return subject5MissExamTotal;
	}

	public void setSubject5MissExamTotal(Integer subject5MissExamTotal) {
		this.subject5MissExamTotal = subject5MissExamTotal;
	}

	public Integer getSubject5PassExamTotal() {
		return subject5PassExamTotal;
	}

	public void setSubject5PassExamTotal(Integer subject5PassExamTotal) {
		this.subject5PassExamTotal = subject5PassExamTotal;
	}

	public String getSelectSchoolArea() {
		return selectSchoolArea;
	}

	public void setSelectSchoolArea(String selectSchoolArea) {
		this.selectSchoolArea = selectSchoolArea;
	}

	public String getSchoolAreaName() {
		return schoolAreaName;
	}

	public void setSchoolAreaName(String schoolAreaName) {
		this.schoolAreaName = schoolAreaName;
	}

}

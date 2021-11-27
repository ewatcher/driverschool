package com.tuocheng.pageModel.demo;

import java.util.Date;

import com.alibaba.fastjson.JSON;

/**
 * action专用的学生实体类
 * 
 * @author MEI702
 * 
 */
public class Student extends PageModel {

	/**
	 * 序列化标识
	 */
	private static final long serialVersionUID = -8940665872900534432L;
	private String id; // 标识
	private String name; // 姓名－－必填字段
	private String sex; // 性别－－－必填字段
	private Date birthdate; // 出生日期
	private String contry; // 国籍
	private String identityId; // 身份证号－－－必填字段
	private String address; // 地址－－必填字段
	private String nativeNation; // 地域选择属性：本地、异地
	private String telephone; // 固定电话
	private String phone; // 手机
	private String email; // 邮箱
	private String mailCode;// 邮编
	private String residenceId;// 居住证
	private String residenceAddr;// 记住证地址
	private String imageId;// 照片回执编号
	private Date createTime;// 注册时间
	private Date graduateDate;// 毕业日期
	private Integer nowState;// 学员在学状态（注册，在培训，毕业，退学，到期，不计时）
	private String comment;// 备注
	private Integer driverType;// 驾照类型(1:A1,2:A2,3:A3,4:B1,5:B2,6:C1,7:C2,8:C3,9:C4,10:D,11:E,12:F,13:M,14:N)
	private Integer applyType;// 申请类型：(1:初次申领,2:增加准驾车型,3:持军官驾驶证,4:持境外驾驶证)
	private String studentNo;// 学员编号
	private Integer examSelected;// （1.表示该学员已经参加理论考试，0.反之）
	private Integer timingFlag;// 学员计时类型，0：非计时 1:计时

	private String ids; // 删除专用标识
	private Integer type;// 班级类型
	private String operator;// 创建者

	// 查询功能使用字段
	private Date createTimeStart;// 注册时间－－－范围查询
	private Date createTimeEnd;// 注册时间－－－范围查询
	private Integer exmainationSubject;// 考试科目类型（科目一，科目二，科目三...）
	private String primaryDriver;// 原来车型
	private String examPhone;// 考试电话
	private Integer batch;// 第几期
	private String otherNo;// 编号考试专用

	private Double feed;// 学费
	private Double realFeed;// 提交学费
	private Double ownFeed;// 欠学费
	private Integer learningTime;// 学时d
	private Integer studentNoTypeVal;// 6表示C1,7表示C2
	// ==========学员材料信息=========
	private Integer faceSign;// 面签
	private Integer submitFile;// 提交档案
	private Integer images;// 相片
	private Integer examinationTable;// 体检表
	private Integer foreignStudent;// 外籍
	private Integer timingCar;// 计时卡
	private Integer indentityFlag;// 身份证是否已登记
	private String sorting;// 排序专用
	private Integer studentType;// 0：内部学员，1表示外包学员
	private String password;// 学员登录运管系统密码

	private String verificationCode;// 验证码
	private Integer qualificationFlag;// 运营资格 0：否 1：是，默认0

	private Integer driverLicenseFlag;// 驾驶证资格标志 1：有效 0：无效(默认：0)
	private Integer qLicenseFlag;// 资格证资格标志 1：有效 0：无效(默认：0)

	// =========关联属性============

	private String organizationId;
	private String organizationName;// 与Tstudent属性同名（达到按列排序目的）
	private String timerCardId; // 学员计时卡信息

	private String trainerTechingType;// 教练员准教类型

	// ===展现班级属性===========
	private String clazzId;// 学员班级信息
	private String clazzName;// 学员班级信息
	private Double clazzFee;// 费用
	private String clazzCostTime;// 时长
	private Integer clazzType;// 类型：（A1，B1.……。）
	private Integer clazzClassType;// 开班类（计时，不计时）
	private String clazzOrderNo;// 显示顺序
	private String clazzComment;// 描述
	private String clazzSchoolAreaName;// 班级校区名称

	// ===展现教练属性===========
	private String trainerId;
	private String trainerName;
	private String trainerCodeNo;// 教练编号
	private String trainerSubCodeNo;// 教练编号
	private String trainerSchoolAreaName;
	private String trainerIdentity;
	private String trainerDriverLicenseId;
	private Integer trainerDriverType;
	private String trainerDriverCarType;
	private String trainerPhone;
	private Integer carBanding;// 表示車輛與教練捆綁
	private Integer trainerType;

	// ===展现业务员属性===========
	private String personId;
	private String personName;
	private String personIdentityId;
	private String personPhone;
	private String personDuty;
	private String personalSchoolAreaName;
	private Integer propertyType;

	private String netPiont;// 报名网点
	private String netPiontId;// 报名网点
	private String netPiontName;// 报名网点

	// 冗余字段
	private Integer restTiming;// 学员剩余课时

	public Integer getDriverLicenseFlag() {
		return driverLicenseFlag;
	}

	public void setDriverLicenseFlag(Integer driverLicenseFlag) {
		this.driverLicenseFlag = driverLicenseFlag;
	}

	public Integer getqLicenseFlag() {
		return qLicenseFlag;
	}

	public void setqLicenseFlag(Integer qLicenseFlag) {
		this.qLicenseFlag = qLicenseFlag;
	}

	public String getNetPiont() {
		return netPiont;
	}

	public void setNetPiont(String netPiont) {
		this.netPiont = netPiont;
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

	public Integer getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(Integer propertyType) {
		this.propertyType = propertyType;
	}

	public String getOtherNo() {
		return otherNo;
	}

	public void setOtherNo(String otherNo) {
		this.otherNo = otherNo;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getContry() {
		return contry;
	}

	public void setContry(String contry) {
		this.contry = contry;
	}

	public String getIdentityId() {
		return identityId;
	}

	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNativeNation() {
		return nativeNation;
	}

	public void setNativeNation(String nativeNation) {
		this.nativeNation = nativeNation;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMailCode() {
		return mailCode;
	}

	public void setMailCode(String mailCode) {
		this.mailCode = mailCode;
	}

	public String getResidenceId() {
		return residenceId;
	}

	public void setResidenceId(String residenceId) {
		this.residenceId = residenceId;
	}

	public String getResidenceAddr() {
		return residenceAddr;
	}

	public void setResidenceAddr(String residenceAddr) {
		this.residenceAddr = residenceAddr;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getGraduateDate() {
		return graduateDate;
	}

	public void setGraduateDate(Date graduateDate) {
		this.graduateDate = graduateDate;
	}

	public Integer getNowState() {
		return nowState;
	}

	public void setNowState(Integer nowState) {
		this.nowState = nowState;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getDriverType() {
		return driverType;
	}

	public void setDriverType(Integer driverType) {
		this.driverType = driverType;
	}

	public Integer getApplyType() {
		return applyType;
	}

	public void setApplyType(Integer applyType) {
		this.applyType = applyType;
	}

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public Integer getExamSelected() {
		return examSelected;
	}

	public void setExamSelected(Integer examSelected) {
		this.examSelected = examSelected;
	}

	public Integer getTimingFlag() {
		return timingFlag;
	}

	public void setTimingFlag(Integer timingFlag) {
		this.timingFlag = timingFlag;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public Integer getExmainationSubject() {
		return exmainationSubject;
	}

	public void setExmainationSubject(Integer exmainationSubject) {
		this.exmainationSubject = exmainationSubject;
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

	public Integer getBatch() {
		return batch;
	}

	public void setBatch(Integer batch) {
		this.batch = batch;
	}

	public Double getFeed() {
		return feed;
	}

	public void setFeed(Double feed) {
		this.feed = feed;
	}

	public Double getRealFeed() {
		return realFeed;
	}

	public void setRealFeed(Double realFeed) {
		this.realFeed = realFeed;
	}

	public Double getOwnFeed() {
		return ownFeed;
	}

	public void setOwnFeed(Double ownFeed) {
		this.ownFeed = ownFeed;
	}

	public Integer getLearningTime() {
		return learningTime;
	}

	public void setLearningTime(Integer learningTime) {
		this.learningTime = learningTime;
	}

	public Integer getStudentNoTypeVal() {
		return studentNoTypeVal;
	}

	public void setStudentNoTypeVal(Integer studentNoTypeVal) {
		this.studentNoTypeVal = studentNoTypeVal;
	}

	public Integer getFaceSign() {
		return faceSign;
	}

	public void setFaceSign(Integer faceSign) {
		this.faceSign = faceSign;
	}

	public Integer getSubmitFile() {
		return submitFile;
	}

	public void setSubmitFile(Integer submitFile) {
		this.submitFile = submitFile;
	}

	public Integer getImages() {
		return images;
	}

	public void setImages(Integer images) {
		this.images = images;
	}

	public Integer getExaminationTable() {
		return examinationTable;
	}

	public void setExaminationTable(Integer examinationTable) {
		this.examinationTable = examinationTable;
	}

	public Integer getForeignStudent() {
		return foreignStudent;
	}

	public void setForeignStudent(Integer foreignStudent) {
		this.foreignStudent = foreignStudent;
	}

	public Integer getTimingCar() {
		return timingCar;
	}

	public void setTimingCar(Integer timingCar) {
		this.timingCar = timingCar;
	}

	public Integer getIndentityFlag() {
		return indentityFlag;
	}

	public void setIndentityFlag(Integer indentityFlag) {
		this.indentityFlag = indentityFlag;
	}

	public String getSorting() {
		return sorting;
	}

	public void setSorting(String sorting) {
		this.sorting = sorting;
	}

	public Integer getStudentType() {
		return studentType;
	}

	public void setStudentType(Integer studentType) {
		this.studentType = studentType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getTimerCardId() {
		return timerCardId;
	}

	public void setTimerCardId(String timerCardId) {
		this.timerCardId = timerCardId;
	}

	public String getTrainerTechingType() {
		return trainerTechingType;
	}

	public void setTrainerTechingType(String trainerTechingType) {
		this.trainerTechingType = trainerTechingType;
	}

	public String getClazzId() {
		return clazzId;
	}

	public void setClazzId(String clazzId) {
		this.clazzId = clazzId;
	}

	public String getClazzName() {
		return clazzName;
	}

	public void setClazzName(String clazzName) {
		this.clazzName = clazzName;
	}

	public Double getClazzFee() {
		return clazzFee;
	}

	public void setClazzFee(Double clazzFee) {
		this.clazzFee = clazzFee;
	}

	public String getClazzCostTime() {
		return clazzCostTime;
	}

	public void setClazzCostTime(String clazzCostTime) {
		this.clazzCostTime = clazzCostTime;
	}

	public Integer getClazzType() {
		return clazzType;
	}

	public void setClazzType(Integer clazzType) {
		this.clazzType = clazzType;
	}

	public Integer getClazzClassType() {
		return clazzClassType;
	}

	public void setClazzClassType(Integer clazzClassType) {
		this.clazzClassType = clazzClassType;
	}

	public String getClazzOrderNo() {
		return clazzOrderNo;
	}

	public void setClazzOrderNo(String clazzOrderNo) {
		this.clazzOrderNo = clazzOrderNo;
	}

	public String getClazzComment() {
		return clazzComment;
	}

	public void setClazzComment(String clazzComment) {
		this.clazzComment = clazzComment;
	}

	public String getClazzSchoolAreaName() {
		return clazzSchoolAreaName;
	}

	public void setClazzSchoolAreaName(String clazzSchoolAreaName) {
		this.clazzSchoolAreaName = clazzSchoolAreaName;
	}

	public String getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(String trainerId) {
		this.trainerId = trainerId;
	}

	public String getTrainerName() {
		return trainerName;
	}

	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}

	public String getTrainerCodeNo() {
		return trainerCodeNo;
	}

	public void setTrainerCodeNo(String trainerCodeNo) {
		this.trainerCodeNo = trainerCodeNo;
	}

	public String getTrainerSubCodeNo() {
		return trainerSubCodeNo;
	}

	public void setTrainerSubCodeNo(String trainerSubCodeNo) {
		this.trainerSubCodeNo = trainerSubCodeNo;
	}

	public String getTrainerSchoolAreaName() {
		return trainerSchoolAreaName;
	}

	public void setTrainerSchoolAreaName(String trainerSchoolAreaName) {
		this.trainerSchoolAreaName = trainerSchoolAreaName;
	}

	public String getTrainerIdentity() {
		return trainerIdentity;
	}

	public void setTrainerIdentity(String trainerIdentity) {
		this.trainerIdentity = trainerIdentity;
	}

	public String getTrainerDriverLicenseId() {
		return trainerDriverLicenseId;
	}

	public void setTrainerDriverLicenseId(String trainerDriverLicenseId) {
		this.trainerDriverLicenseId = trainerDriverLicenseId;
	}

	public Integer getTrainerDriverType() {
		return trainerDriverType;
	}

	public void setTrainerDriverType(Integer trainerDriverType) {
		this.trainerDriverType = trainerDriverType;
	}

	public String getTrainerDriverCarType() {
		return trainerDriverCarType;
	}

	public void setTrainerDriverCarType(String trainerDriverCarType) {
		this.trainerDriverCarType = trainerDriverCarType;
	}

	public String getTrainerPhone() {
		return trainerPhone;
	}

	public void setTrainerPhone(String trainerPhone) {
		this.trainerPhone = trainerPhone;
	}

	public Integer getCarBanding() {
		return carBanding;
	}

	public void setCarBanding(Integer carBanding) {
		this.carBanding = carBanding;
	}

	public Integer getTrainerType() {
		return trainerType;
	}

	public void setTrainerType(Integer trainerType) {
		this.trainerType = trainerType;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPersonIdentityId() {
		return personIdentityId;
	}

	public void setPersonIdentityId(String personIdentityId) {
		this.personIdentityId = personIdentityId;
	}

	public String getPersonPhone() {
		return personPhone;
	}

	public void setPersonPhone(String personPhone) {
		this.personPhone = personPhone;
	}

	public String getPersonDuty() {
		return personDuty;
	}

	public void setPersonDuty(String personDuty) {
		this.personDuty = personDuty;
	}

	public String getPersonalSchoolAreaName() {
		return personalSchoolAreaName;
	}

	public void setPersonalSchoolAreaName(String personalSchoolAreaName) {
		this.personalSchoolAreaName = personalSchoolAreaName;
	}

	public Integer getRestTiming() {
		return restTiming;
	}

	public void setRestTiming(Integer restTiming) {
		this.restTiming = restTiming;
	}

}

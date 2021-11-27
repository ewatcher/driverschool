package com.tuocheng.model.demo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 学生实体类
 * 
 * @author 拓程科技
 * 
 */
@Entity
@Table(name = "tb_student", schema = "")
public class Tstudent implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -3237901463346420232L;

	public static final Integer NOWSTATE_INTRAINING = 1;// 在培
	public static final Integer NOWSTATE_GRADUATE = 2;// 毕业
	public static final Integer NOWSTATE_QUITSCHOOL = 3;// 退学
	public static final Integer NOWSTATE_TIMEOUT = 4;// 到期
	public static final Integer NOWSTATE_ALL = 5;// 不计时
	public static final Integer EXAMSELECTED_TRUE = 1;// 已经参数考试
	public static final Integer EXAMSELECTED_FALSE = 0;// 没有参加考试

	public static final Integer DRIVERTYPE_A1 = 1;
	public static final Integer DRIVERTYPE_A2 = 2;
	public static final Integer DRIVERTYPE_A3 = 3;
	public static final Integer DRIVERTYPE_B1 = 4;
	public static final Integer DRIVERTYPE_B2 = 5;
	public static final Integer DRIVERTYPE_C1 = 6;
	public static final Integer DRIVERTYPE_C2 = 7;
	public static final Integer DRIVERTYPE_C3 = 8;
	public static final Integer DRIVERTYPE_C4 = 9;

	public static final Integer TIMING_COUNTING = 1;
	public static final Integer TIMING_UNCOUNTING = 0;

	public static final Integer STUDENTTYPE_INSIDE = 0;// 内部学员
	public static final Integer STUDENTTYPE_ECTOCYST = 1;// 外包学员

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
	private Date createTime;// 注册时间－－－必填字段
	private Date graduateDate;// 毕业日期
	private Integer nowState;// 学员在学状态（1，在培训，2毕业，3退学，4到期）
	private String comment;// 备注
	private Integer driverType;// 驾照类型(1:A1,2:A2,3:A3,4:B1,5:B2,6:C1,7:C2,8:C3,9:C4,10:D,11:E,12:F,13:M,14:N)
	private Integer applyType;// 申请类型：(1:初次申领,2:增加准驾车型,3:持军官驾驶证,4:持境外驾驶证)

	private Integer timingFlag;// 学员计时类型，0：非计时 1:计时
	private String studentNo;// 学员编号
	private String operator;// 创建者
	private Integer examSelected;// （1.表示该学员已经参加理论考试，0.反之）

	private Double feed;// 学费
	private Double realFeed;// 实收学费
	private Double ownFeed;// 欠学费
	private Integer learningTime;// 学时
	private Integer studentType;// 0：内部学员，1表示外包学员

	private String sorting;// 排序专用
	private String password;// 学员登录运管系统密码
	private String primaryDriver;// 原来车型
	private String examPhone;// 考试电话
	private Integer batch;// 第几期
	private String otherNo;// 编号考试专用

	private Integer qualificationFlag;// 运营资格 0：否 1：是，默认0
	// 冗余字段
	private Integer restTiming;// 学员剩余课时 1:表示有剩余课时，0表示无剩余课时

	// ==========学员材料信息=========
	private Integer faceSign;// 面签
	private Integer submitFile;// 提交档案
	private Integer images;// 相片
	private Integer examinationTable;// 体检表
	private Integer foreignStudent;// 外籍
	private Integer timingCar;// 计时卡
	private Integer indentityFlag;// 身份证是否已登记

	private String verificationCode;// 验证码


	// =========用作标志属性===========

	// ============关联关系================
	private TstudentTimerCard studentTimerCard; // 一对一，一个学生对应一条计时卡信息
	private Tclass clazz;// 多对一:多个学员属性一个班
	private Torganization organization;// 多对一，多个学员同属于一个机构
	private Set<TdriverLicense> driverLicenses = new HashSet<TdriverLicense>();// 一个学员拥有一本驾驶证
	private Set<TqualificationLicense> qualificationLicenses = new HashSet<TqualificationLicense>();// 一个学员拥有一本资格证
	private Set<Treservation> reservations = new HashSet<Treservation>();// 一个学员对应一条预约记录
	private Ttrainer trainer;// 一个教练可以带多个学员
	private Tperson person;// 一个业务员可以招收多名学员

	private TstudentFile studentFile;// 一个学员对应一个学员档案
	private Ttiming timing;// 学员学时
	private Tuser user;

	private TquitSchool quitSchool;
	private Tprogress progress;
	private TstudentExam studentExam;
	private String netPiont;// 报名网点

	public Tstudent() {
	}

	public Tstudent(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public Tstudent(String id, String name, String sex, Date birthdate, String contry, String identityId,
			String address, String nativeNation, String telephone, String phone, String email, String mailCode,
			String residenceId, String residenceAddr, String imageId, Date createTime, Date graduateDate,
			Integer nowState, String comment, Integer driverType, Integer applyType, Integer timingFlag,
			String studentNo, String operator, Integer examSelected, Double feed, Double realFeed, Double ownFeed,
			Integer learningTime, Integer studentType, String sorting, String password, String primaryDriver,
			String examPhone, Integer batch, String otherNo, Integer qualificationFlag, Integer restTiming,
			Integer faceSign, Integer submitFile, Integer images, Integer examinationTable, Integer foreignStudent,
			Integer timingCar, Integer indentityFlag, String verificationCode, Integer driverLicenseFlag,
			Integer qLicenseFlag, TstudentTimerCard studentTimerCard, Tclass clazz, Torganization organization,
			Set<TdriverLicense> driverLicenses, Set<TqualificationLicense> qualificationLicenses,
			Set<Treservation> reservations, Ttrainer trainer, Tperson person, TstudentFile studentFile, Ttiming timing,
			Tuser user, TquitSchool quitSchool, Tprogress progress, TstudentExam studentExam, String netPiont) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.birthdate = birthdate;
		this.contry = contry;
		this.identityId = identityId;
		this.address = address;
		this.nativeNation = nativeNation;
		this.telephone = telephone;
		this.phone = phone;
		this.email = email;
		this.mailCode = mailCode;
		this.residenceId = residenceId;
		this.residenceAddr = residenceAddr;
		this.imageId = imageId;
		this.createTime = createTime;
		this.graduateDate = graduateDate;
		this.nowState = nowState;
		this.comment = comment;
		this.driverType = driverType;
		this.applyType = applyType;
		this.timingFlag = timingFlag;
		this.studentNo = studentNo;
		this.operator = operator;
		this.examSelected = examSelected;
		this.feed = feed;
		this.realFeed = realFeed;
		this.ownFeed = ownFeed;
		this.learningTime = learningTime;
		this.studentType = studentType;
		this.sorting = sorting;
		this.password = password;
		this.primaryDriver = primaryDriver;
		this.examPhone = examPhone;
		this.batch = batch;
		this.otherNo = otherNo;
		this.qualificationFlag = qualificationFlag;
		this.restTiming = restTiming;
		this.faceSign = faceSign;
		this.submitFile = submitFile;
		this.images = images;
		this.examinationTable = examinationTable;
		this.foreignStudent = foreignStudent;
		this.timingCar = timingCar;
		this.indentityFlag = indentityFlag;
		this.verificationCode = verificationCode;
		this.studentTimerCard = studentTimerCard;
		this.clazz = clazz;
		this.organization = organization;
		this.driverLicenses = driverLicenses;
		this.qualificationLicenses = qualificationLicenses;
		this.reservations = reservations;
		this.trainer = trainer;
		this.person = person;
		this.studentFile = studentFile;
		this.timing = timing;
		this.user = user;
		this.quitSchool = quitSchool;
		this.progress = progress;
		this.studentExam = studentExam;
		this.netPiont = netPiont;
	}

	@Column(name = "verificationCode", length = 36)
	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	@Id
	@Column(name = "id", nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "sex", nullable = false, length = 8)
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createTime", length = 7)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "birthdate", length = 7)
	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	// TODO
	@Column(name = "sorting", unique = true, length = 14, updatable = false)
	public String getSorting() {
		return sorting;
	}

	public void setSorting(String sorting) {
		this.sorting = sorting;
	}

	@Column(name = "contry", length = 50)
	public String getContry() {
		return contry;
	}

	public void setContry(String contry) {
		this.contry = contry;
	}

	@Column(name = "identityId", nullable = false, length = 200)
	public String getIdentityId() {
		return identityId;
	}

	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}

	@Column(name = "address", nullable = false, length = 200)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "nativeNation", length = 100)
	public String getNativeNation() {
		return nativeNation;
	}

	public void setNativeNation(String nativeNation) {
		this.nativeNation = nativeNation;
	}

	@Column(name = "telephone", length = 150)
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "phone", nullable = true, length = 150)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "email", length = 100)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "mailCode", length = 100)
	public String getMailCode() {
		return mailCode;
	}

	public void setMailCode(String mailCode) {
		this.mailCode = mailCode;
	}

	@Column(name = "residenceId", length = 200)
	public String getResidenceId() {
		return residenceId;
	}

	public void setResidenceId(String residenceId) {
		this.residenceId = residenceId;
	}

	@Column(name = "residenceAddr", length = 200)
	public String getResidenceAddr() {
		return residenceAddr;
	}

	public void setResidenceAddr(String residenceAddr) {
		this.residenceAddr = residenceAddr;
	}

	@Column(name = "imageId", length = 100)
	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	@Column(name = "nowState", nullable = true, length = 4)
	public Integer getNowState() {
		return nowState;
	}

	public void setNowState(Integer nowState) {
		this.nowState = nowState;
	}

	@Column(name = "comment", length = 300)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name = "driverType", length = 4)
	public Integer getDriverType() {
		return driverType;
	}

	public void setDriverType(Integer driverType) {
		this.driverType = driverType;
	}

	@Column(name = "applyType", length = 4)
	public Integer getApplyType() {
		return applyType;
	}

	public void setApplyType(Integer applyType) {
		this.applyType = applyType;
	}

	@Column(name = "studentNo", nullable = false, unique = true, length = 36)
	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "graduateDate", length = 7)
	public Date getGraduateDate() {
		return graduateDate;
	}

	public void setGraduateDate(Date graduateDate) {
		this.graduateDate = graduateDate;
	}

	@Column(name = "examSelected", length = 4)
	public Integer getExamSelected() {
		return examSelected;
	}

	public void setExamSelected(Integer examSelected) {
		this.examSelected = examSelected;
	}

	@Column(name = "operator", length = 36, updatable = false)
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "feed", columnDefinition = "double(10,2) default '0.00'")
	public Double getFeed() {
		return feed;
	}

	public void setFeed(Double feed) {
		this.feed = feed;
	}

	@Column(name = "realFeed", columnDefinition = "double(10,2) default '0.00'")
	public Double getRealFeed() {
		return realFeed;
	}

	public void setRealFeed(Double realFeed) {
		this.realFeed = realFeed;
	}

	@Column(name = "ownFeed", columnDefinition = "double(10,2) default '0.00'")
	public Double getOwnFeed() {
		return ownFeed;
	}

	public void setOwnFeed(Double ownFeed) {
		this.ownFeed = ownFeed;
	}

	@Column(name = "leaningTime", length = 36, columnDefinition = "INT DEFAULT 0")
	public Integer getLearningTime() {
		return learningTime;
	}

	public void setLearningTime(Integer leaningTime) {
		this.learningTime = leaningTime;
	}


	@Column(name = "restTiming", length = 4)
	public Integer getRestTiming() {
		return restTiming;
	}

	public void setRestTiming(Integer restTiming) {
		this.restTiming = restTiming;
	}

	@Column(name = "timingFlag", length = 4, columnDefinition = "INT default 0")
	public Integer getTimingFlag() {
		return timingFlag;
	}

	public void setTimingFlag(Integer timingFlag) {
		this.timingFlag = timingFlag;
	}

	@Column(name = "faceSign", length = 4, columnDefinition = "INT default 0")
	public Integer getFaceSign() {
		return faceSign;
	}

	public void setFaceSign(Integer faceSign) {
		this.faceSign = faceSign;
	}

	@Column(name = "submitFile", length = 4, columnDefinition = "INT default 0")
	public Integer getSubmitFile() {
		return submitFile;
	}

	public void setSubmitFile(Integer submitFile) {
		this.submitFile = submitFile;
	}

	@Column(name = "images", length = 4, columnDefinition = "INT default 0")
	public Integer getImages() {
		return images;
	}

	public void setImages(Integer images) {
		this.images = images;
	}

	@Column(name = "examinationTable", length = 4, columnDefinition = "INT default 0")
	public Integer getExaminationTable() {
		return examinationTable;
	}

	public void setExaminationTable(Integer examinationTable) {
		this.examinationTable = examinationTable;
	}

	@Column(name = "timingCar", length = 4, columnDefinition = "INT default 0")
	public Integer getTimingCar() {
		return timingCar;
	}

	public void setTimingCar(Integer timingCar) {
		this.timingCar = timingCar;
	}

	@Column(name = "foreignStudent", length = 4, columnDefinition = "INT default 0")
	public Integer getForeignStudent() {
		return foreignStudent;
	}

	public void setForeignStudent(Integer foreignStudent) {
		this.foreignStudent = foreignStudent;
	}

	@Column(name = "indentityFlag", length = 4, columnDefinition = "INT default 0")
	public Integer getIndentityFlag() {
		return indentityFlag;
	}

	public void setIndentityFlag(Integer indentityFlag) {
		this.indentityFlag = indentityFlag;
	}

	@Column(name = "studentType", length = 4, columnDefinition = "INT default 0")
	public Integer getStudentType() {
		return studentType;
	}

	public void setStudentType(Integer studentType) {
		this.studentType = studentType;
	}

	@Column(name = "password", length = 36)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "primaryDriver")
	public String getPrimaryDriver() {
		return primaryDriver;
	}

	public void setPrimaryDriver(String primaryDriver) {
		this.primaryDriver = primaryDriver;
	}

	@Column(name = "examPhone", length = 72)
	public String getExamPhone() {
		return examPhone;
	}

	public void setExamPhone(String examPhone) {
		this.examPhone = examPhone;
	}

	@Column(name = "batch", length = 10, columnDefinition = "INT DEFAULT 0")
	public Integer getBatch() {
		return batch;
	}

	public void setBatch(Integer batch) {
		this.batch = batch;
	}

	@Column(name = "otherNo", length = 36)
	public String getOtherNo() {
		return otherNo;
	}

	public void setOtherNo(String otherNo) {
		this.otherNo = otherNo;
	}

	@Column(name = "qualificationFlag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getQualificationFlag() {
		return qualificationFlag;
	}

	public void setQualificationFlag(Integer qualificationFlag) {
		this.qualificationFlag = qualificationFlag;
	}

	@Column(name = "netPiont", length = 36)
	public String getNetPiont() {
		return netPiont;
	}

	public void setNetPiont(String netPiont) {
		this.netPiont = netPiont;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clazzId")
	public Tclass getClazz() {
		return clazz;
	}

	public void setClazz(Tclass clazz) {
		this.clazz = clazz;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trainerId")
	public Ttrainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Ttrainer trainer) {
		this.trainer = trainer;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organizationId", updatable = false)
	public Torganization getOrganization() {
		return organization;
	}

	public void setOrganization(Torganization organization) {
		this.organization = organization;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "student")
	public Set<TdriverLicense> getDriverLicenses() {
		return driverLicenses;
	}

	public void setDriverLicenses(Set<TdriverLicense> driverLicenses) {
		this.driverLicenses = driverLicenses;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "student")
	public Set<Treservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Treservation> reservations) {
		this.reservations = reservations;
	}

	@OneToOne(fetch = FetchType.LAZY, targetEntity = TstudentTimerCard.class)
	@JoinColumn(name = "studentTimerCardId", updatable = false)
	public TstudentTimerCard getStudentTimerCard() {
		return studentTimerCard;
	}

	public void setStudentTimerCard(TstudentTimerCard studentTimerCard) {
		this.studentTimerCard = studentTimerCard;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "student")
	public Set<TqualificationLicense> getQualificationLicenses() {
		return qualificationLicenses;
	}

	public void setQualificationLicenses(Set<TqualificationLicense> qualificationLicenses) {
		this.qualificationLicenses = qualificationLicenses;
	}

	@OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
	public TstudentFile getStudentFile() {
		return studentFile;
	}

	public void setStudentFile(TstudentFile studentFile) {
		this.studentFile = studentFile;
	}

	@OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
	public Ttiming getTiming() {
		return timing;
	}

	public void setTiming(Ttiming timing) {
		this.timing = timing;
	}

	@OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
	public Tprogress getProgress() {
		return progress;
	}

	public void setProgress(Tprogress progress) {
		this.progress = progress;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "personId")
	public Tperson getPerson() {
		return person;
	}

	public void setPerson(Tperson person) {
		this.person = person;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "userId", unique = true)
	public Tuser getUser() {
		return user;
	}

	public void setUser(Tuser user) {
		this.user = user;
	}

	@OneToOne(mappedBy = "student")
	public TquitSchool getQuitSchool() {
		return quitSchool;
	}

	public void setQuitSchool(TquitSchool quitSchool) {
		this.quitSchool = quitSchool;
	}

	@OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
	public TstudentExam getStudentExam() {
		return studentExam;
	}

	public void setStudentExam(TstudentExam studentExam) {
		this.studentExam = studentExam;
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
		Tstudent other = (Tstudent) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tstudent [id=" + id + ", name=" + name + ", sex=" + sex + ", birthdate=" + birthdate + ", contry="
				+ contry + ", identityId=" + identityId + ", address=" + address + ", nativeNation=" + nativeNation
				+ ", telephone=" + telephone + ", phone=" + phone + ", email=" + email + ", mailCode=" + mailCode
				+ ", residenceId=" + residenceId + ", residenceAddr=" + residenceAddr + ", imageId=" + imageId
				+ ", createTime=" + createTime + ", graduateDate=" + graduateDate + ", nowState=" + nowState
				+ ", comment=" + comment + ", driverType=" + driverType + ", applyType=" + applyType + ", timingFlag="
				+ timingFlag + ", studentNo=" + studentNo + ", operator=" + operator + ", examSelected=" + examSelected
				+ ", feed=" + feed + ", realFeed=" + realFeed + ", ownFeed=" + ownFeed + ", learningTime="
				+ learningTime + ", faceSign=" + faceSign + ", submitFile=" + submitFile + ", images=" + images
				+ ", examinationTable=" + examinationTable + ", foreignStudent=" + foreignStudent + ", timingCar="
				+ timingCar + ", studentTimerCard=" + studentTimerCard + ", clazz=" + clazz + ", organization="
				+ ", driverLicenses=" + driverLicenses + ", qualificationLicenses=" + qualificationLicenses
				+ ", reservations=" + reservations + ", trainer=" + trainer + ", person=" + person + ", studentFile="
				+ studentFile + ", timing=" + timing + ", user=" + user + ", quitSchool=" + quitSchool + ", progress="
				+ progress + ", restTiming=" + restTiming + "]";
	}

}

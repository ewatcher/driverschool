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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.tuocheng.action.guest.commentStudentAction;

/**
 * 教练实体类（dao专用的实体类）
 * 
 * @author MEI702
 * 
 */
@Entity
@Table(name = "tb_trainers", schema = "")
public class Ttrainer implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 5854882202156567532L;
	// =================base message===============
	public static final Integer DRIVERTYPE_A1 = 1;
	public static final Integer DRIVERTYPE_A2 = 2;
	public static final Integer DRIVERTYPE_A3 = 3;
	public static final Integer DRIVERTYPE_B1 = 4;
	public static final Integer DRIVERTYPE_B2 = 5;
	public static final Integer DRIVERTYPE_C1 = 6;
	public static final Integer DRIVERTYPE_C2 = 7;
	public static final Integer DRIVERTYPE_C3 = 8;
	public static final Integer DRIVERTYPE_C4 = 9;

	public static final Integer ARRANGETYPE_ELECTRIC = 1;
	public static final Integer ARRANGETYPE_SIMULATION = 2;
	public static final Integer ARRANGETYPE_OTHER = 3;

	public static final Integer TRAINERTYPE_INSIDE = 0;// 内部教练
	public static final Integer TRAINERTYPE_ECTORCYST = 1;// 外包教练
	private String id; // 标识
	private String name;// 姓名
	private String codeNo;// 教练编号
	private String nation;// 民族
	private Date birthday;// 出生日期
	private String sex;// 性别
	private String contry;// 籍贯
	private String identity;// 身份证号
	private String addres;// 地址
	private String phone;// 手机
	private String telephone;// 电话
	private String email;// 邮箱
	private String comment;// 备注
	private String schoolArea;// 所属于校区
	private String operator;// 操作員
	private Integer carBanding;// 0:表示教练未与车辆进行捆绑，1与表教练已经与车辆捆绑

	private Integer arrangeFlag;// 排班标记，默认0,1：表示可排班，0：不可排班
	private Integer trainerType;// 0表示内部学生，1表示外包教练
	private Integer arrangeType;// 排班类型：1:电车，2：模拟器，3其它

	// =================diver licence message============
	private String driverLicenseId;// 驾驶证号
	private Date firstGetDate;// 初次领驾驶证日期
	private Integer driverType;// 准驾类型
	private String driverCarType;// 准驾车型
	private Date driverValidatyStart;// 驾驶证有效日期
	private Date driverValidatyEnd;// 驾驶证有效日期
	private String teachId;// 教练员编号
	private Date teachValidatyStart;// 教练员证有效期
	private Date teachBalidatyEnd;// 教练员证有效期
	private Date firstTeachingDate;// 教练员初次领证日期
	private Date jionDay;// 入职日期

	// =====================关联关系=========================

	private Set<TtrainerArrange> trainerArranges = new HashSet<TtrainerArrange>();
	private Set<Treservation> reservations = new HashSet<Treservation>();
	private Set<Tstudent> student = new HashSet<Tstudent>(0);
	private Set<Tcar> carSet = new HashSet<Tcar>(0);
	private Set<TpersonalTiming> personalTiming = new HashSet<TpersonalTiming>(
			0);
	private Set<TtrainerReservation> trainerReservations = new HashSet<TtrainerReservation>(
			0);
	private Set<TusingCar> usingCars = new HashSet<TusingCar>(0);
	private Tuser user;

	public Ttrainer() {
	}

	public Ttrainer(String name, String id) {
		this.name = name;
		this.id = id;
	}

	public Ttrainer(String id, String name, String codeNo, String nation,
			Date birthday, String sex, String contry, String identity,
			String addres, String phone, String telephone, String email,
			String comment, String schoolArea, String operator,
			String driverLicenseId, Date firstGetDate, Integer driverType,
			String driverCarType, Date driverValidatyStart,
			Date driverValidatyEnd, String teachId, Date teachValidatyStart,
			Date teachBalidatyEnd, Date firstTeachingDate, Date jionDay,
			Set<TtrainerArrange> trainerArranges,
			Set<Treservation> reservations, Set<Tstudent> student,
			Set<TpersonalTiming> personalTiming,
			Set<TtrainerReservation> trainerReservations,
			Set<TusingCar> usingCars, Tuser user) {
		super();
		this.id = id;
		this.name = name;
		this.codeNo = codeNo;
		this.nation = nation;
		this.birthday = birthday;
		this.sex = sex;
		this.contry = contry;
		this.identity = identity;
		this.addres = addres;
		this.phone = phone;
		this.telephone = telephone;
		this.email = email;
		this.comment = comment;
		this.schoolArea = schoolArea;
		this.operator = operator;
		this.driverLicenseId = driverLicenseId;
		this.firstGetDate = firstGetDate;
		this.driverType = driverType;
		this.driverCarType = driverCarType;
		this.driverValidatyStart = driverValidatyStart;
		this.driverValidatyEnd = driverValidatyEnd;
		this.teachId = teachId;
		this.teachValidatyStart = teachValidatyStart;
		this.teachBalidatyEnd = teachBalidatyEnd;
		this.firstTeachingDate = firstTeachingDate;
		this.jionDay = jionDay;
		this.trainerArranges = trainerArranges;
		this.reservations = reservations;
		this.student = student;
		this.personalTiming = personalTiming;
		this.trainerReservations = trainerReservations;
		this.usingCars = usingCars;
		this.user = user;
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

	@Column(name = "codeNo", length = 36)
	public String getCodeNo() {
		return codeNo;
	}

	public void setCodeNo(String codeNo) {
		this.codeNo = codeNo;
	}

	@Column(name = "nation", length = 32)
	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "birthday", length = 7)
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "sex", length = 8)
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "contry", length = 40)
	public String getContry() {
		return contry;
	}

	public void setContry(String contry) {
		this.contry = contry;
	}

	@Column(name = "identity", nullable = false, length = 36)
	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	@Column(name = "address", length = 100)
	public String getAddres() {
		return addres;
	}

	public void setAddres(String addres) {
		this.addres = addres;
	}

	@Column(name = "phone", nullable = false, length = 14)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "telephone", length = 14)
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "email", length = 32)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "comment", length = 300)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "firstGetDate", length = 7)
	public Date getFirstGetDate() {
		return firstGetDate;
	}

	public void setFirstGetDate(Date firstGetDate) {
		this.firstGetDate = firstGetDate;
	}

	@Column(name = "driverType", length = 4)
	public Integer getDriverType() {
		return driverType;
	}

	public void setDriverType(Integer driverType) {
		this.driverType = driverType;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "driverValidatyStart", length = 7)
	public Date getDriverValidatyStart() {
		return driverValidatyStart;
	}

	public void setDriverValidatyStart(Date driverValidatyStart) {
		this.driverValidatyStart = driverValidatyStart;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "driverValidatyEnd", length = 7)
	public Date getDriverValidatyEnd() {
		return driverValidatyEnd;
	}

	public void setDriverValidatyEnd(Date driverValidatyEnd) {
		this.driverValidatyEnd = driverValidatyEnd;
	}

	@Column(name = "teachId", length = 36)
	public String getTeachId() {
		return teachId;
	}

	public void setTeachId(String teachId) {
		this.teachId = teachId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "teachValidatyStart", length = 7)
	public Date getTeachValidatyStart() {
		return teachValidatyStart;
	}

	public void setTeachValidatyStart(Date teachValidatyStart) {
		this.teachValidatyStart = teachValidatyStart;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "teachBalidatyEnd", length = 7)
	public Date getTeachBalidatyEnd() {
		return teachBalidatyEnd;
	}

	public void setTeachBalidatyEnd(Date teachBalidatyEnd) {
		this.teachBalidatyEnd = teachBalidatyEnd;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "firstTeachingDate", length = 7)
	public Date getFirstTeachingDate() {
		return firstTeachingDate;
	}

	public void setFirstTeachingDate(Date firstTeachingDate) {
		this.firstTeachingDate = firstTeachingDate;
	}

	@Column(name = "driverLicenseId", nullable = false, length = 40)
	public String getDriverLicenseId() {
		return driverLicenseId;
	}

	public void setDriverLicenseId(String driverLicenseId) {
		this.driverLicenseId = driverLicenseId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "jionDay", length = 7)
	public Date getJionDay() {
		return jionDay;
	}

	public void setJionDay(Date jionDay) {
		this.jionDay = jionDay;
	}

	@Column(name = "driverCarType", length = 300)
	public String getDriverCarType() {
		return driverCarType;
	}

	public void setDriverCarType(String driverCarType) {
		this.driverCarType = driverCarType;
	}

	@Column(name = "schoolArea", length = 36)
	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	@Column(name = "operator", length = 36, updatable = false)
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "carBanding", length = 4)
	public Integer getCarBanding() {
		return carBanding;
	}

	public void setCarBanding(Integer carBanding) {
		this.carBanding = carBanding;
	}

	@Column(name = "arrangeFlag", length = 4, columnDefinition = "INT default 0")
	public Integer getArrangeFlag() {
		return arrangeFlag;
	}

	public void setArrangeFlag(Integer arrangeFlag) {
		this.arrangeFlag = arrangeFlag;
	}

	@Column(name = "trainerType", length = 4, columnDefinition = "INT default 0")
	public Integer getTrainerType() {
		return trainerType;
	}

	public void setTrainerType(Integer trainerType) {
		this.trainerType = trainerType;
	}

	@Column(name = "arrangeType", length = 4, columnDefinition = "INT default 3")
	public Integer getArrangeType() {
		return arrangeType;
	}

	public void setArrangeType(Integer arrangeType) {
		this.arrangeType = arrangeType;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "trainer")
	public Set<TtrainerArrange> getTrainerArranges() {
		return trainerArranges;
	}

	public void setTrainerArranges(Set<TtrainerArrange> trainerArranges) {
		this.trainerArranges = trainerArranges;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "trainer")
	public Set<Treservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Treservation> reservations) {
		this.reservations = reservations;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "trainer")
	public Set<Tstudent> getStudent() {
		return student;
	}

	public void setStudent(Set<Tstudent> student) {
		this.student = student;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "trainer")
	public Set<Tcar> getCarSet() {
		return carSet;
	}

	public void setCarSet(Set<Tcar> carSet) {
		this.carSet = carSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "trainer")
	public Set<TpersonalTiming> getPersonalTiming() {
		return personalTiming;
	}

	public void setPersonalTiming(Set<TpersonalTiming> personalTiming) {
		this.personalTiming = personalTiming;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "trainer")
	public Set<TtrainerReservation> getTrainerReservations() {
		return trainerReservations;
	}

	public void setTrainerReservations(
			Set<TtrainerReservation> trainerReservations) {
		this.trainerReservations = trainerReservations;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "trainer")
	public Set<TusingCar> getUsingCars() {
		return usingCars;
	}

	public void setUsingCars(Set<TusingCar> usingCars) {
		this.usingCars = usingCars;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "userId", unique = true)
	public Tuser getUser() {
		return user;
	}

	public void setUser(Tuser user) {
		this.user = user;
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
		Ttrainer other = (Ttrainer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ttrainer [id=" + id + ", name=" + name + ", No=" + codeNo
				+ ", nation=" + nation + ", birthday=" + birthday + ", sex="
				+ sex + ", contry=" + contry + ", identity=" + identity
				+ ", addres=" + addres + ", phone=" + phone + ", telephone="
				+ telephone + ", email=" + email + ", comment=" + comment
				+ ", diverLicenseId=" + driverLicenseId + ", firstGetDate="
				+ firstGetDate + ", driverType=" + driverType
				+ ", driverValidatyStart=" + driverValidatyStart
				+ ", driverValidatyEnd=" + driverValidatyEnd + ", teachType="
				+ ", teachCarType=" + ", teachId=" + teachId
				+ ", teachValidatyStart=" + teachValidatyStart
				+ ", teachBalidatyEnd=" + teachBalidatyEnd
				+ ", firstTeachingDate=" + firstTeachingDate + "]";
	}

}

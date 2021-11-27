package com.tuocheng.model.demo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 人员管理实体类(DAO专用)
 * 
 * @author MEI702
 * 
 */
@Entity
@Table(name = "tb_persons", schema = "")
public class Tperson implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 3046892103311039666L;
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
	

	// ====================connection relation==========
	private Torganization organization;// 组织机构（多对一：多个人同属于一个机构）
	private Set<Tstudent> students = new HashSet<Tstudent>(0);// 一个业务员可以招收多名学员

	public Tperson() {
	}

	public Tperson(String id, String name, String identityId, String sex, String nation, String birthPlace,
			Date birthday, Date startedJob, String political, String profession, String educationLevel, String address,
			String accountNature, String graduateSchool, Date graduateDay, String email, String phone,
			String department, String duty, String driverLevel, Date employDay, String marriageState,
			String languageLevel, String computerLevel, String comment, String operator, Integer propertyType,
			Torganization organization, Set<Tstudent> students) {
		this.id = id;
		this.name = name;
		this.identityId = identityId;
		this.sex = sex;
		this.nation = nation;
		this.birthPlace = birthPlace;
		this.birthday = birthday;
		this.startedJob = startedJob;
		this.political = political;
		this.profession = profession;
		this.educationLevel = educationLevel;
		this.address = address;
		this.accountNature = accountNature;
		this.graduateSchool = graduateSchool;
		this.graduateDay = graduateDay;
		this.email = email;
		this.phone = phone;
		this.department = department;
		this.duty = duty;
		this.driverLevel = driverLevel;
		this.employDay = employDay;
		this.marriageState = marriageState;
		this.languageLevel = languageLevel;
		this.computerLevel = computerLevel;
		this.comment = comment;
		this.operator = operator;
		this.propertyType = propertyType;
		this.organization = organization;
		this.students = students;
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

	@Column(name = "identityId", nullable = false, length = 100)
	public String getIdentityId() {
		return identityId;
	}

	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}

	@Column(name = "sex", length = 10)
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "nation", length = 20)
	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	@Column(name = "birthPlace", length = 200)
	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "birthday", length = 7)
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "startedJob", length = 7)
	public Date getStartedJob() {
		return startedJob;
	}

	public void setStartedJob(Date startedJob) {
		this.startedJob = startedJob;
	}

	@Column(name = "political", length = 20)
	public String getPolitical() {
		return political;
	}

	public void setPolitical(String political) {
		this.political = political;
	}

	@Column(name = "profession", length = 100)
	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	@Column(name = "educationLevel", length = 20)
	public String getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}

	@Column(name = "address", length = 150)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "accountNature", length = 30)
	public String getAccountNature() {
		return accountNature;
	}

	public void setAccountNature(String accountNature) {
		this.accountNature = accountNature;
	}

	@Column(name = "graduateSchool", length = 60)
	public String getGraduateSchool() {
		return graduateSchool;
	}

	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "graduateDay", length = 7)
	public Date getGraduateDay() {
		return graduateDay;
	}

	public void setGraduateDay(Date graduateDay) {
		this.graduateDay = graduateDay;
	}

	@Column(name = "email", length = 36)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "phone", length = 20)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "comment", length = 700)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name = "department", length = 100)
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Column(name = "duty", length = 20)
	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	@Column(name = "driverLevel", length = 20)
	public String getDriverLevel() {
		return driverLevel;
	}

	public void setDriverLevel(String driverLevel) {
		this.driverLevel = driverLevel;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "employDay", length = 7)
	public Date getEmployDay() {
		return employDay;
	}

	public void setEmployDay(Date enployDay) {
		this.employDay = enployDay;
	}

	@Column(name = "marriageState", length = 20)
	public String getMarriageState() {
		return marriageState;
	}

	public void setMarriageState(String marriageState) {
		this.marriageState = marriageState;
	}

	@Column(name = "languageLevel", length = 20)
	public String getLanguageLevel() {
		return languageLevel;
	}

	public void setLanguageLevel(String languageLevel) {
		this.languageLevel = languageLevel;
	}

	@Column(name = "computerLevel", length = 20)
	public String getComputerLevel() {
		return computerLevel;
	}

	public void setComputerLevel(String computerLevel) {
		this.computerLevel = computerLevel;
	}

	@Column(name = "operator", length = 36, updatable = false)
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(Integer propertyType) {
		this.propertyType = propertyType;
	}

	// ///====================================connected
	// relation=======================/////
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organizationId")
	public Torganization getOrganization() {
		return organization;
	}

	public void setOrganization(Torganization orgTorganization) {
		this.organization = orgTorganization;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
	public Set<Tstudent> getStudents() {
		return students;
	}

	public void setStudents(Set<Tstudent> students) {
		this.students = students;
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
		Tperson other = (Tperson) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tperson [id=" + id + ", name=" + name + ", identityId=" + identityId + ", sex=" + sex + ", nation="
				+ nation + ", birthPlace=" + birthPlace + ", birthday=" + birthday + ", startedJob=" + startedJob
				+ ", political=" + political + ", profession=" + profession + ", educationLevel=" + educationLevel
				+ ", address=" + address + ", accountNature=" + accountNature + ", graduateSchool=" + graduateSchool
				+ ", graduateDay=" + graduateDay + ", email=" + email + ", phone=" + phone + ", comment=" + comment
				+ "]";
	}

}

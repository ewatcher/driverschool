package com.tuocheng.model.demo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.alibaba.fastjson.JSON;
import com.tuocheng.pageModel.base.Json;

/**
 * 用户实体类（dao专用） Tuser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_user", schema = "")
public class Tuser implements java.io.Serializable {

	// Fields

	/**
	 * 序列化标识
	 */
	private static final long serialVersionUID = 6117219707873612958L;
	
	public static final Integer USERTYPE_STUDENT=1;
	public static final Integer USERTYPE_TRAINER=2;
	public static final Integer USERTYPE_EMPLOYEE=3;
	public static final Integer USERTYPE_MONITOR=4;
	
	public static final Integer FIRSTDATE_OF_WEEK=1;
	public static final Integer LASTDATE_OF_WEEK=2;
	public static final Integer FIRSTDATE_OF_MONTH=3;
	public static final Integer LASTDATE_OF_MONTH=4;
	public static final Integer FIRSTDATE_OF_YEAR=5;
	public static final Integer LASTDATE_OF_YEAR=6;
	public static final Integer FIRSTDATE_OF_LASTWEEK=7;
	public static final Integer LASTDATE_OF_LASTWEEK=8;
	public static final Integer FIRSTDATE_OF_LASTMONTH=9;
	public static final Integer LASTDATE_OF_LASTMONTH=10;
	public static final Integer FIRSTDATE_OF_LASTYEAR=11;
	public static final Integer LASTDATE_OF_LASTYEAR=12;
	public static final Integer FIRSTDATE_OF_NEXTWEEK=13;
	public static final Integer LASTDATE_OF_NEXTWEEK=14;
	
	public static final Integer FUNCTION_COUNT=1;
	public static final Integer FUNCTION_SUM=2;
	
	public static final Integer COUNT_DAY=1;
	public static final Integer COUNT_WMY=2;
	
	public static final Integer WEEKFLAG_THISWEEK=2;
	public static final Integer WEEKFLAG_LASTWEEK=3;
	
	private String cid;
	private Date ccreatedatetime;
	private Date cmodifydatetime;
	private String cname;
	private String cpwd;
	private String schoolArea;
	private Date createDate;
	private Integer userType;//(1.表示学员，2.表示教练，3.表示职员,4.表示超级用户(校长，超级管理员))
	
	private Set<TuserRole> tusertroles = new HashSet<TuserRole>(0);
	private Set<Trole> roles;// 一个用户拥有多个权限
	private Set<Tstudent> students = new HashSet<Tstudent>(0);
	private Set<Ttrainer> trainers = new HashSet<Ttrainer>(0);

	// Constructors

	/** default constructor */
	public Tuser() {
	}

	/** minimal constructor */
	public Tuser(String cid, String cname, String cpwd) {
		this.cid = cid;
		this.cname = cname;
		this.cpwd = cpwd;
	}


	public Tuser(String cid, Date ccreatedatetime, Date cmodifydatetime,
			String cname, String cpwd, String schoolArea, Date createDate,
			Integer userType, Set<TuserRole> tusertroles, Set<Trole> roles,
			Set<Tstudent> students, Set<Ttrainer> trainers) {
		super();
		this.cid = cid;
		this.ccreatedatetime = ccreatedatetime;
		this.cmodifydatetime = cmodifydatetime;
		this.cname = cname;
		this.cpwd = cpwd;
		this.schoolArea = schoolArea;
		this.createDate = createDate;
		this.userType = userType;
		this.tusertroles = tusertroles;
		this.roles = roles;
		this.students = students;
		this.trainers = trainers;
	}

	// Property accessors
	@Id
	@Column(name = "CID", nullable = false, length = 36)
	public String getCid() {
		return this.cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CCREATEDATETIME", length = 7)
	public Date getCcreatedatetime() {
		return this.ccreatedatetime;
	}

	public void setCcreatedatetime(Date ccreatedatetime) {
		this.ccreatedatetime = ccreatedatetime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CMODIFYDATETIME", length = 7)
	public Date getCmodifydatetime() {
		return this.cmodifydatetime;
	}

	public void setCmodifydatetime(Date cmodifydatetime) {
		this.cmodifydatetime = cmodifydatetime;
	}

	@Column(name = "CNAME", unique = true, nullable = false, length = 100)
	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Column(name = "CPWD", nullable = false, length = 100)
	public String getCpwd() {
		return this.cpwd;
	}

	public void setCpwd(String cpwd) {
		this.cpwd = cpwd;
	}

	@Column(name = "schoolArea", length = 36)
	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createDate", length = 7)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name="userType",length=4)
	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	//
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tuser")
	public Set<TuserRole> getTusertroles() {
		return this.tusertroles;
	}

	public void setTusertroles(Set<TuserRole> tusertroles) {
		this.tusertroles = tusertroles;
	}

	@ManyToMany(targetEntity = com.tuocheng.model.demo.Trole.class, cascade = CascadeType.ALL)
	@JoinTable(name = "user_role_link", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	public Set<Trole> getRoles() {
		return roles;
	}

	public void setRoles(Set<Trole> roles) {
		this.roles = roles;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Tstudent> getStudents() {
		return students;
	}

	public void setStudents(Set<Tstudent> students) {
		this.students = students;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Ttrainer> getTrainers() {
		return trainers;
	}

	public void setTrainers(Set<Ttrainer> trainers) {
		this.trainers = trainers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cid == null) ? 0 : cid.hashCode());
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
		Tuser other = (Tuser) obj;
		if (cid == null) {
			if (other.cid != null)
				return false;
		} else if (!cid.equals(other.cid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tuser [cid=" + cid + ", ccreatedatetime=" + ccreatedatetime
				+ ", cmodifydatetime=" + cmodifydatetime + ", cname=" + cname
				+ ", cpwd=" + cpwd + ", schoolArea=" + schoolArea
				+ ", createDate=" + createDate + ", userType=" + userType
				+ ", tusertroles=" + tusertroles + ", roles=" + roles
				+ ", students=" + students + ", trainers=" + trainers + "]";
	}

	
}
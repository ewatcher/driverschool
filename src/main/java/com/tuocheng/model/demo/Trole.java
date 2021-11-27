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

/**
 * Trole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_role", schema = "")
public class Trole implements java.io.Serializable {

	// Fields

	/**
	 * 序列化标识
	 */
	private static final long serialVersionUID = -3504054039609042978L;
	private String cid;
	private String cdesc;
	private String cname;
	private String roleValue;
	private String schoolArea;// 所属校区
	private Set<Troletauth> troletauths = new HashSet<Troletauth>(0);
	private Set<TuserRole> tusertroles = new HashSet<TuserRole>(0);
	private String operator;//创建
	private Date createDate;//创建时间
	private Set<Tuser> users = new HashSet<Tuser>(0);
	

	// Constructors

	/** default constructor */
	public Trole() {
	}

	/** minimal constructor */
	public Trole(String cid, String cname) {
		this.cid = cid;
		this.cname = cname;
	}

	public Trole(String cid, String cdesc, String cname, String roleValue,
			String schoolArea, Set<Troletauth> troletauths,
			Set<TuserRole> tusertroles, Set<Tuser> users) {
		this.cid = cid;
		this.cdesc = cdesc;
		this.cname = cname;
		this.roleValue = roleValue;
		this.schoolArea = schoolArea;
		this.troletauths = troletauths;
		this.tusertroles = tusertroles;
		this.users = users;
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

	@Column(name = "CDESC", length = 200)
	public String getCdesc() {
		return this.cdesc;
	}

	public void setCdesc(String cdesc) {
		this.cdesc = cdesc;
	}

	@Column(name = "CNAME", nullable = false, length = 100)
	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "trole")
	public Set<Troletauth> getTroletauths() {
		return this.troletauths;
	}

	public void setTroletauths(Set<Troletauth> troletauths) {
		this.troletauths = troletauths;
	}

	@Column(name = "roleValue", length = 64)
	public String getRoleValue() {
		return roleValue;
	}

	public void setRoleValue(String roleValue) {
		this.roleValue = roleValue;
	}
	
	@Column(name="schoolArea",length=36)
	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}
	
	@Column(name="operator",length=20)
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="createDate",length=7)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "trole")
	public Set<TuserRole> getTusertroles() {
		return this.tusertroles;
	}

	public void setTusertroles(Set<TuserRole> tusertroles) {
		this.tusertroles = tusertroles;
	}

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "roles", targetEntity = com.tuocheng.model.demo.Tuser.class)
	public Set<Tuser> getUsers() {
		return users;
	}

	public void setUsers(Set<Tuser> users) {
		this.users = users;
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
		Trole other = (Trole) obj;
		if (cid == null) {
			if (other.cid != null)
				return false;
		} else if (!cid.equals(other.cid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Trole [cid=" + cid + ", cdesc=" + cdesc + ", cname=" + cname
				+ ", troletauths=" + troletauths + ", tusertroles="
				+ tusertroles + "]";
	}

}
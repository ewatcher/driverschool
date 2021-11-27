package com.tuocheng.model.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Tusertrole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_user_role", schema = "")
public class TuserRole implements java.io.Serializable {

	// Fields

	/**
	 * 序列化标识号
	 */
	private static final long serialVersionUID = -9217405771656160596L;
	private String cid;
	private Trole trole;
	private Tuser tuser;
	private Integer priority;// 某个角色在用户中的优先级

	// Constructors

	/** default constructor */
	public TuserRole() {
	}

	/** minimal constructor */
	public TuserRole(String cid) {
		this.cid = cid;
	}

	/** full constructor */
	public TuserRole(String cid, Trole trole, Tuser tuser, Integer priority) {
		super();
		this.cid = cid;
		this.trole = trole;
		this.tuser = tuser;
		this.priority = priority;
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
	
	@Column(name="priority",length=4,columnDefinition="INT DEFAULT 0")
	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CROLEID")
	public Trole getTrole() {
		return this.trole;
	}

	public void setTrole(Trole trole) {
		this.trole = trole;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSERID")
	public Tuser getTuser() {
		return this.tuser;
	}

	public void setTuser(Tuser tuser) {
		this.tuser = tuser;
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
		TuserRole other = (TuserRole) obj;
		if (cid == null) {
			if (other.cid != null)
				return false;
		} else if (!cid.equals(other.cid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tusertrole [cid=" + cid + ", trole=" + trole + ", tuser=" + tuser + "]";
	}

}
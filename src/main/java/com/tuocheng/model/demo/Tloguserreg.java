package com.tuocheng.model.demo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Tloguserreg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_loguserreg", schema = "")
public class Tloguserreg implements java.io.Serializable {

	// Fields

	/**
	 * 序列化标识
	 */
	private static final long serialVersionUID = 3558653363370830510L;
	private String cid;
	private Date cdatetime;
	private String cmsg;
	private String cname;

	// Constructors

	/** default constructor */
	public Tloguserreg() {
	}

	/** minimal constructor */
	public Tloguserreg(String cid) {
		this.cid = cid;
	}

	/** full constructor */
	public Tloguserreg(String cid, Date cdatetime, String cmsg, String cname) {
		this.cid = cid;
		this.cdatetime = cdatetime;
		this.cmsg = cmsg;
		this.cname = cname;
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
	@Column(name = "CDATETIME", length = 7)
	public Date getCdatetime() {
		return this.cdatetime;
	}

	public void setCdatetime(Date cdatetime) {
		this.cdatetime = cdatetime;
	}

	@Column(name = "CMSG", length = 200)
	public String getCmsg() {
		return this.cmsg;
	}

	public void setCmsg(String cmsg) {
		this.cmsg = cmsg;
	}

	@Column(name = "CNAME", length = 100)
	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
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
		Tloguserreg other = (Tloguserreg) obj;
		if (cid == null) {
			if (other.cid != null)
				return false;
		} else if (!cid.equals(other.cid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tloguserreg [cid=" + cid + ", cdatetime=" + cdatetime
				+ ", cmsg=" + cmsg + ", cname=" + cname + "]";
	}

}
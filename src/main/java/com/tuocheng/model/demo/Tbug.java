package com.tuocheng.model.demo;

import java.sql.Clob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Tbug entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_bug", schema = "")
public class Tbug implements java.io.Serializable {

	/**
	 * 序列化标识
	 */
	private static final long serialVersionUID = -5685172791283194805L;

	public Tbug(String cid, String cname, Date ccreatedatetime) {
		this.cid = cid;
		this.cname = cname;
		this.ccreatedatetime = ccreatedatetime;
	}

	// Fields

	private String cid;
	private String cname;
	private Clob cdesc;
	private Date ccreatedatetime;

	// Constructors

	/** default constructor */
	public Tbug() {
	}

	/** minimal constructor */
	public Tbug(String cid, String cname) {
		this.cid = cid;
		this.cname = cname;
	}

	/** full constructor */
	public Tbug(String cid, String cname, Clob cdesc, Date ccreatedatetime) {
		this.cid = cid;
		this.cname = cname;
		this.cdesc = cdesc;
		this.ccreatedatetime = ccreatedatetime;
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

	@Column(name = "CNAME", nullable = false, length = 100)
	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Column(name = "CDESC")
	public Clob getCdesc() {
		return this.cdesc;
	}

	public void setCdesc(Clob cdesc) {
		this.cdesc = cdesc;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CCREATEDATETIME", length = 7)
	public Date getCcreatedatetime() {
		return this.ccreatedatetime;
	}

	public void setCcreatedatetime(Date ccreatedatetime) {
		this.ccreatedatetime = ccreatedatetime;
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
		Tbug other = (Tbug) obj;
		if (cid == null) {
			if (other.cid != null)
				return false;
		} else if (!cid.equals(other.cid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tbug [cid=" + cid + ", cname=" + cname + ", cdesc=" + cdesc
				+ ", ccreatedatetime=" + ccreatedatetime + "]";
	}

}
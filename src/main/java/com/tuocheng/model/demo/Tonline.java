package com.tuocheng.model.demo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Tonline entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_online", schema = "")
public class Tonline implements java.io.Serializable {

	// Fields

	/**
	 * 序列化标识号
	 */
	private static final long serialVersionUID = -718092528477030507L;
	private String cid;
	private Date cdatetime;
	private String cip;
	private String cname;
	private String schoolArea;

	// Constructors

	/** default constructor */
	public Tonline() {
	}

	/** full constructor */
	public Tonline(String cid, Date cdatetime, String cip, String cname) {
		this.cid = cid;
		this.cdatetime = cdatetime;
		this.cip = cip;
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
	@Column(name = "CDATETIME", nullable = false, length = 7)
	public Date getCdatetime() {
		return this.cdatetime;
	}

	public void setCdatetime(Date cdatetime) {
		this.cdatetime = cdatetime;
	}

	@Column(name = "CIP", nullable = false, length = 50)
	public String getCip() {
		return this.cip;
	}

	public void setCip(String cip) {
		this.cip = cip;
	}

	@Column(name = "CNAME", nullable = false, length = 100)
	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}
	
	@Column(name = "schoolArea", length = 36)
	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
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
		Tonline other = (Tonline) obj;
		if (cid == null) {
			if (other.cid != null)
				return false;
		} else if (!cid.equals(other.cid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tonline [cid=" + cid + ", cdatetime=" + cdatetime + ", cip="
				+ cip + ", cname=" + cname + "]";
	}

}
package com.tuocheng.model.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Troletauth entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_role_auth", schema = "")
public class Troletauth implements java.io.Serializable {

	// Fields

	/**
	 * 序列化标识符号
	 */
	private static final long serialVersionUID = 5349793852647143912L;
	private String cid;//标识符
	private Tauth tauth;
	private Trole trole;//角色

	// Constructors

	/** default constructor */
	public Troletauth() {
	}

	/** minimal constructor */
	public Troletauth(String cid) {
		this.cid = cid;
	}

	/** full constructor */
	public Troletauth(String cid, Tauth tauth, Trole trole) {
		this.cid = cid;
		this.tauth = tauth;
		this.trole = trole;
	}

	// Property accessors
	@Id
	@Column(name = "CID",  nullable = false, length = 36)
	public String getCid() {
		return this.cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CAUTHID")
	public Tauth getTauth() {
		return this.tauth;
	}

	public void setTauth(Tauth tauth) {
		this.tauth = tauth;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CROLEID")
	public Trole getTrole() {
		return this.trole;
	}

	public void setTrole(Trole trole) {
		this.trole = trole;
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
		Troletauth other = (Troletauth) obj;
		if (cid == null) {
			if (other.cid != null)
				return false;
		} else if (!cid.equals(other.cid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Troletauth [cid=" + cid + ", tauth=" + tauth + ", trole="
				+ trole + "]";
	}

}
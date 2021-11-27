package com.tuocheng.model.demo;

import java.math.BigDecimal;
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
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.validator.annotations.CustomValidator;

/**
 * Tauth entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_auth", schema = "")
public class Tauth implements java.io.Serializable {

	// Fields

	/**
	 * 序列化标识
	 */
	private static final long serialVersionUID = -1540374193157429472L;
	private String cid;
	private Tauth tauth;
	private String cdesc;
	private String cname;
	private BigDecimal cseq;
	private String curl;
	private Set<Troletauth> troletauths = new HashSet<Troletauth>(0);
	private Set<Tauth> tauths = new HashSet<Tauth>(0);
	private String menuId;

	// Constructors

	/** default constructor */
	public Tauth() {
	}

	/** minimal constructor */
	public Tauth(String cid, String cname) {
		this.cid = cid;
		this.cname = cname;
	}

	/** full constructor */
	

	// Property accessors
	@Id
	@Column(name = "CID", nullable = false, length = 36)
	public String getCid() {
		return this.cid;
	}

	public Tauth(String cid, Tauth tauth, String cdesc, String cname,
			BigDecimal cseq, String curl, Set<Troletauth> troletauths,
			Set<Tauth> tauths, String menuId) {
		this.cid = cid;
		this.tauth = tauth;
		this.cdesc = cdesc;
		this.cname = cname;
		this.cseq = cseq;
		this.curl = curl;
		this.troletauths = troletauths;
		this.tauths = tauths;
		this.menuId = menuId;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CPID")
	public Tauth getTauth() {
		return this.tauth;
	}

	public void setTauth(Tauth tauth) {
		this.tauth = tauth;
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

	@Column(name = "CSEQ", precision = 22, scale = 0)
	public BigDecimal getCseq() {
		return this.cseq;
	}

	public void setCseq(BigDecimal cseq) {
		this.cseq = cseq;
	}

	@Column(name = "CURL", length = 200)
	public String getCurl() {
		return this.curl;
	}

	public void setCurl(String curl) {
		this.curl = curl;
	}
	@Column(name="menuId",length=36)
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tauth")
	public Set<Troletauth> getTroletauths() {
		return this.troletauths;
	}

	public void setTroletauths(Set<Troletauth> troletauths) {
		this.troletauths = troletauths;
	}

	@OneToMany( fetch = FetchType.LAZY, mappedBy = "tauth")
	public Set<Tauth> getTauths() {
		return this.tauths;
	}

	public void setTauths(Set<Tauth> tauths) {
		this.tauths = tauths;
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
		Tauth other = (Tauth) obj;
		if (cid == null) {
			if (other.cid != null)
				return false;
		} else if (!cid.equals(other.cid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tauth [cid=" + cid + ", tauth=" + tauth + ", cdesc=" + cdesc
				+ ", cname=" + cname + ", cseq=" + cseq + ", curl=" + curl
				+ ", troletauths=" + troletauths + ", tauths=" + tauths + "]";
	}

}
package com.tuocheng.model.demo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 微信报名实体模型(DAO专用)
 * 
 * @author 李杰
 * 
 */
@Entity
@Table(name = "tb_sign_up", schema = "")
public class TsignUp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9090317034590501716L;
	// ===========base properties==============
	private String id;
	private String openId; // 用户微信ID
	private String upMobile; // 用户提交的联系方式
	private Integer upAge; // 年龄
	private String upName; // 用户提交的名称
	private String upCity; // 用户提交的所在地
	private Date upTime; // 用户提交的时间
	private String comments; // 备注
	private Integer status; // 状态。 1：已联系
	private String organizationId; // 机构ID

	// ==============relation properties==============
	public TsignUp() {
	}

	public TsignUp(String id, String openId, String upMobile, Integer upAge,
			String upName, String upCity, Date upTime, String comments,
			Integer status, String organizationId) {
		super();
		this.id = id;
		this.openId = openId;
		this.upMobile = upMobile;
		this.upAge = upAge;
		this.upName = upName;
		this.upCity = upCity;
		this.upTime = upTime;
		this.comments = comments;
		this.status = status;
		this.organizationId = organizationId;
	}

	@Id
	@Column(name = "id", nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "openId", length = 100)
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@Column(name = "upMobile", length = 36)
	public String getUpMobile() {
		return upMobile;
	}

	public void setUpMobile(String upMobile) {
		this.upMobile = upMobile;
	}

	@Column(name = "upAge", length = 11)
	public Integer getUpAge() {
		return upAge;
	}

	public void setUpAge(Integer upAge) {
		this.upAge = upAge;
	}

	@Column(name = "upName", length = 100)
	public String getUpName() {
		return upName;
	}

	public void setUpName(String upName) {
		this.upName = upName;
	}

	@Column(name = "upCity", length = 100)
	public String getUpCity() {
		return upCity;
	}

	public void setUpCity(String upCity) {
		this.upCity = upCity;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "upTime", length = 7)
	public Date getUpTime() {
		return upTime;
	}

	public void setUpTime(Date upTime) {
		this.upTime = upTime;
	}

	@Column(name = "comments", length = 10000)
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Column(name = "status", length = 11)
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "organizationId", length = 36)
	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
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
		TsignUp other = (TsignUp) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TsignUp [id=" + id + ", openId=" + openId + ", upMobile="
				+ upMobile + ", upAge=" + upAge + ", upName=" + upName
				+ ", upCity=" + upCity + ", upTime=" + upTime + ", comments="
				+ comments + ", status=" + status + ", organizationId="
				+ organizationId + "]";
	}

}

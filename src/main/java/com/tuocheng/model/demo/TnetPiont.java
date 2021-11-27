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
 * 报名网点实体管理类
 * 
 * @author MEI702
 *
 */
@Entity
@Table(name = "tb_netPionts", schema = "")
public class TnetPiont implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4773401315790421880L;
	private String id;
	private String schoolArea;// 校区标识
	private String ownId;// 标识
	private String name;// 报名点名称
	private String address;// 地址
	private String phone;// 电话
	private Integer orderNo;// 序号
	private Date createTime;// 创建时间
	private String operator;// 创建者
	private String comment;// 备注
	private Integer enableFlag;// 是否启用 0：未启用，1：启用 默认为0

	public TnetPiont() {
	}

	public TnetPiont(String id, String schoolArea, String ownId, String name, String address, String phone,
			Integer orderNo, Date crateTIme, String operator, String comment, Integer enableFlag) {
		this.id = id;
		this.schoolArea = schoolArea;
		this.ownId = ownId;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.orderNo = orderNo;
		this.createTime = crateTIme;
		this.operator = operator;
		this.comment = comment;
		this.enableFlag = enableFlag;
	}

	@Id
	@Column(name = "id", nullable = false, updatable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "schoolArea", length = 36)
	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	@Column(name = "ownId")
	public String getOwnId() {
		return ownId;
	}

	public void setOwnId(String ownId) {
		this.ownId = ownId;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "orderNo", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createTimes", length = 12)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "operator")
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "comment", length = 300)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name = "enableFlag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getEnableFlag() {
		return enableFlag;
	}

	public void setEnableFlag(Integer enableFlag) {
		this.enableFlag = enableFlag;
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
		TnetPiont other = (TnetPiont) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TnetPiont [id=" + id + ", schoolArea=" + schoolArea + ", ownId=" + ownId + ", name=" + name
				+ ", address=" + address + ", phone=" + phone + ", orderNo=" + orderNo + ", crateTIme=" + createTime
				+ ", operator=" + operator + ", comment=" + comment + "]";
	}

}

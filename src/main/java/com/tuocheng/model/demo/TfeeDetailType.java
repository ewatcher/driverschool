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
 * 收费项目类别
 * 
 * @author nongfeng
 *
 */
@Entity
@Table(name = "tb_feeDetailTypes", schema = "")
public class TfeeDetailType implements Serializable {
	/**
	 * 序列化标识
	 */
	private static final long serialVersionUID = -5159602459853365686L;
	private String id;// 标识
	private String itemName;// 收费名称
	private String schoolArea;// 校区标识
	private Double money;// 收费金额
	private Integer orderNo;// 排序
	private Date createTime;// 创建日期
	private String operator;// 抢建人
	private String comment;// 备注说明

	public TfeeDetailType() {
	}

	public TfeeDetailType(String id, String itemName, String schoolArea, Double money, Integer orderNo, Date createTime,
			String operator, String comment) {
		this.id = id;
		this.itemName = itemName;
		this.schoolArea = schoolArea;
		this.money = money;
		this.orderNo = orderNo;
		this.createTime = createTime;
		this.operator = operator;
		this.comment = comment;
	}

	@Id
	@Column(name = "id", nullable = false, updatable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "itemName")
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Column(name = "schoolArea", nullable = false, updatable = false, length = 36)
	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	@Column(name = "money", columnDefinition = "double(10,2) default '0.00'")
	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	@Column(name = "orderNo", length = 4)
	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createTime", length = 15)
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
		TfeeDetailType other = (TfeeDetailType) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TfeeDetailType [id=" + id + ", itemName=" + itemName + ", schoolArea=" + schoolArea + ", money=" + money
				+ ", orderNo=" + orderNo + ", createTime=" + createTime + ", operator=" + operator + "]";
	}

}

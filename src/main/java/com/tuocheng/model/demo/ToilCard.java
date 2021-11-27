package com.tuocheng.model.demo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 油卡信息登记实体类
 * 
 * @author 农峰
 * 
 */
@Entity
@Table(name = "tb_oilCards", schema = "")
public class ToilCard implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8651172629084207502L;

	public static final Integer OilCardState_NOMAL = 1;
	public static final Integer OilCardState_MISS = 2;

	private String id;
	private String cardNo;// 卡号
	private Integer state;// 卡状态 1：正常 2：挂失
	private Double balance;// 卡当前余额
	private Date createTime;// 创建日期
	private String operator;// 创建人
	private String schoolArea;// 所属校区
	private String remarks;// 备注
	private Date prevateDate;// 上一次充值日期

	private Set<ToilCardUsing> oilCarUsings = new HashSet<ToilCardUsing>(0);
	private Set<TrechargeOilCard> rechargeOilCars = new HashSet<TrechargeOilCard>(
			0);

	public ToilCard() {
	}

	public ToilCard(String id, String cardNo, Integer state, Double balance,
			Date createTime, String operator, String schoolArea,
			String remarks, Date prevateDate, Set<ToilCardUsing> oilCarUsings,
			Set<TrechargeOilCard> rechargeOilCars) {
		this.id = id;
		this.cardNo = cardNo;
		this.state = state;
		this.balance = balance;
		this.createTime = createTime;
		this.operator = operator;
		this.schoolArea = schoolArea;
		this.remarks = remarks;
		this.prevateDate = prevateDate;
		this.oilCarUsings = oilCarUsings;
		this.rechargeOilCars = rechargeOilCars;
	}

	@Id
	@Column(name = "id", nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "cardNo", updatable = false, length = 36)
	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createTime", length = 7)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "operator", length = 20)
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "remarks", length = 300)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "state", length = 4)
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "balance", columnDefinition = "double(10,2) default '0.00'")
	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Column(name = "schoolArea")
	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "prevateDate", length=7)
	public Date getPrevateDate() {
		return prevateDate;
	}

	public void setPrevateDate(Date prevateDate) {
		this.prevateDate = prevateDate;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "oilCard")
	public Set<ToilCardUsing> getOilCarUsings() {
		return oilCarUsings;
	}

	public void setOilCarUsings(Set<ToilCardUsing> oilCarUsings) {
		this.oilCarUsings = oilCarUsings;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "oilCard")
	public Set<TrechargeOilCard> getRechargeOilCars() {
		return rechargeOilCars;
	}

	public void setRechargeOilCars(Set<TrechargeOilCard> rechargeOilCars) {
		this.rechargeOilCars = rechargeOilCars;
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
		ToilCard other = (ToilCard) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ToilCar [id=" + id + ", carNo=" + cardNo + ", createTime="
				+ createTime + ", operator=" + operator + "]";
	}

}

package com.tuocheng.model.demo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 加油卡充值实体类
 * 
 * @author 农峰
 * 
 */
@Entity
@Table(name = "tb_rechargeOilCards", schema = "")
public class TrechargeOilCard implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1452560228142101669L;
	private String id;
	private Date rechargeDate;// 充值日期
	private Double beforeBalance;// 充值前余额
	private Double money;// 本次充值金额
	private Double afterBalance;// 充值前后额
	private String rechargePerson;// 充值人
	private String remarks;// 备注
	private String schoolArea;// 所属校区
	private ToilCard oilCard;

	public TrechargeOilCard() {
	}

	public TrechargeOilCard(String id, Date rechargeDate, Double beforeBalance,
			Double money, Double afterBalance, String rechargePerson,
			String remarks, String schoolArea, ToilCard oilCard) {
		this.id = id;
		this.rechargeDate = rechargeDate;
		this.beforeBalance = beforeBalance;
		this.money = money;
		this.afterBalance = afterBalance;
		this.rechargePerson = rechargePerson;
		this.remarks = remarks;
		this.schoolArea = schoolArea;
		this.oilCard = oilCard;
	}

	@Id
	@Column(name = "id", nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "rechargeDate", length = 7)
	public Date getRechargeDate() {
		return rechargeDate;
	}

	public void setRechargeDate(Date rechargeDate) {
		this.rechargeDate = rechargeDate;
	}

	@Column(name = "beforeBalance", columnDefinition = "double(10,2) default '0.00'")
	public Double getBeforeBalance() {
		return beforeBalance;
	}

	public void setBeforeBalance(Double beforeBalance) {
		this.beforeBalance = beforeBalance;
	}

	@Column(name = "money", columnDefinition = "double(10,2) default '0.00'")
	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	@Column(name = "afterBalance", columnDefinition = "double(10,2) default '0.00'")
	public Double getAfterBalance() {
		return afterBalance;
	}

	public void setAfterBalance(Double afterBalance) {
		this.afterBalance = afterBalance;
	}

	@Column(name = "rechargePerson", length = 20)
	public String getRechargePerson() {
		return rechargePerson;
	}

	public void setRechargePerson(String rechargePerson) {
		this.rechargePerson = rechargePerson;
	}

	@Column(name = "remarks", length = 300)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "schoolArea")
	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "oilCardId")
	public ToilCard getOilCard() {
		return oilCard;
	}

	public void setOilCard(ToilCard oilCard) {
		this.oilCard = oilCard;
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
		TrechargeOilCard other = (TrechargeOilCard) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TrechargeOilCar [id=" + id + ", rechargeDate=" + rechargeDate
				+ ", beforeBalance=" + beforeBalance + ", money=" + money
				+ ", afterBalance=" + afterBalance + ", rechargePerson="
				+ rechargePerson + ", oilCard=" + oilCard + "]";
	}

}

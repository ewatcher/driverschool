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
 * 卡使用明细实体类
 * 
 * @author 农峰
 * 
 */
@Entity
@Table(name = "tb_oilCardUsings", schema = "")
public class ToilCardUsing implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5155890228393231683L;
	private String id;
	private String carNo;// 车牌号
	private Date usingDate;// 使用日期
	private String startTime;// 借出卡时间
	private String endTime;// 还卡时间
	private Integer oilName;// 油名称
	private Integer oilType;// 油号
	private Double oilTotal;// 使用测量
	private Double price;// 单价
	private Double moneyTotal;// 总额
	private String usingPerson;// 领卡人
	private String groupType;// 组别
	private String schoolArea;// 所属校区
	private String remarks;// 备注
	private String operator;// 操作员

	private ToilCard oilCard;

	public ToilCardUsing() {
	}

	public ToilCardUsing(String id, String carNo, Date usingDate,
			String startTime, String endTime, Integer oilName, Integer oilType,
			Double oilTotal, Double price, Double moneyTotal,
			String usingPerson, String groupType, String schoolArea,
			String remarks, String operator, ToilCard oilCard) {
		this.id = id;
		this.carNo = carNo;
		this.usingDate = usingDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.oilName = oilName;
		this.oilType = oilType;
		this.oilTotal = oilTotal;
		this.price = price;
		this.moneyTotal = moneyTotal;
		this.usingPerson = usingPerson;
		this.groupType = groupType;
		this.schoolArea = schoolArea;
		this.remarks = remarks;
		this.operator = operator;
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

	@Column(name = "carNo")
	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "usingDate", length = 7)
	public Date getUsingDate() {
		return usingDate;
	}

	public void setUsingDate(Date usingDate) {
		this.usingDate = usingDate;
	}

	@Column(name = "")
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Column(name = "endTime")
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Column(name = "oilName")
	public Integer getOilName() {
		return oilName;
	}

	public void setOilName(Integer oilName) {
		this.oilName = oilName;
	}

	@Column(name = "oilType")
	public Integer getOilType() {
		return oilType;
	}

	public void setOilType(Integer oilType) {
		this.oilType = oilType;
	}

	@Column(name = "oilTotal", columnDefinition = "double(10,2) default '0.00'")
	public Double getOilTotal() {
		return oilTotal;
	}

	public void setOilTotal(Double oilTotal) {
		this.oilTotal = oilTotal;
	}

	@Column(name = "price", columnDefinition = "double(10,2) default '0.00'")
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "moneyTotal", columnDefinition = "double(10,2) default '0.00'")
	public Double getMoneyTotal() {
		return moneyTotal;
	}

	public void setMoneyTotal(Double moneyTotal) {
		this.moneyTotal = moneyTotal;
	}

	@Column(name = "usingPerson")
	public String getUsingPerson() {
		return usingPerson;
	}

	public void setUsingPerson(String usingPerson) {
		this.usingPerson = usingPerson;
	}

	@Column(name = "groupType")
	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
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

	@Column(name = "operator")
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
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
		ToilCardUsing other = (ToilCardUsing) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ToilCarUsing [id=" + id + ", carNo=" + carNo + ", usingDate="
				+ usingDate + ", startTime=" + startTime + ", endTime="
				+ endTime + ", oilName=" + oilName + ", oilType=" + oilType
				+ ", oilTotal=" + oilTotal + ", price=" + price
				+ ", moneyTotal=" + moneyTotal + ", usingPerson=" + usingPerson
				+ ", groupType=" + groupType + ", oilCard=" + oilCard + "]";
	}

}

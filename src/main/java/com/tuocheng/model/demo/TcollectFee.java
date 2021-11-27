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
 * 收费明细实体类
 * 
 * @author MEI702
 *
 */
@Entity
@Table(name = "tb_collectFee", schema = "")
public class TcollectFee implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1983451555531457211L;
	private String id;
	private String schoolArea;// 校区标识
	private String collectNo;// 收费序号
	private Date createTime;// 交费日期
	private String handupMan;// 交费人
	private Integer handupManType;// 交费人类别 1：学员，2：教练 3：职员
	private String feeDetailType;// 收费项目
	private Double collectTotal;// 收费金额 默认值0.00
	private Double realTotal;// 实收金额 默认值0.00
	private Double ownTotal;// 欠费金额 默认值0.00
	private Double mustTotal;// 欠费金额 默认值0.00
	private Double favouravleFee;// 优惠金额
	private Double otherFee;// 其它费用
	private Integer payWay;// 付款方式
	private String handupManName;// 交费人名称
	private String comment;// 备注
	private String operator;// 收费员
	private Date createDate;// 创建时间 排序专用

	public TcollectFee() {
	}

	public TcollectFee(String id, String schoolArea, String collectNo, Date createTime, String handupMan,
			Integer handupManType, String feeDetailType, Double collectTotal, Double realTotal, Double ownTotal,
			Double mustTotal, Double favouravleFee, Double otherFee, Integer payWay, String handupManName,
			String comment, String operator, Date createDate) {
		this.id = id;
		this.schoolArea = schoolArea;
		this.collectNo = collectNo;
		this.createTime = createTime;
		this.handupMan = handupMan;
		this.handupManType = handupManType;
		this.feeDetailType = feeDetailType;
		this.collectTotal = collectTotal;
		this.realTotal = realTotal;
		this.ownTotal = ownTotal;
		this.mustTotal = mustTotal;
		this.favouravleFee = favouravleFee;
		this.otherFee = otherFee;
		this.payWay = payWay;
		this.handupManName = handupManName;
		this.comment = comment;
		this.operator = operator;
		this.createDate = createDate;
	}

	@Id
	@Column(name = "id", nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "schoolArea")
	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	@Column(name = "collectNo", length = 36)
	public String getCollectNo() {
		return collectNo;
	}

	public void setCollectNo(String collectNo) {
		this.collectNo = collectNo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createTime", length = 15)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "handupMan", length = 36)
	public String getHandupMan() {
		return handupMan;
	}

	public void setHandupMan(String handupMan) {
		this.handupMan = handupMan;
	}

	@Column(name = "handupManType", columnDefinition = "INT DEFAULT 0")
	public Integer getHandupManType() {
		return handupManType;
	}

	public void setHandupManType(Integer handupManType) {
		this.handupManType = handupManType;
	}

	@Column(name = "feeDetailType", length = 36)
	public String getFeeDetailType() {
		return feeDetailType;
	}

	public void setFeeDetailType(String feeDetailType) {
		this.feeDetailType = feeDetailType;
	}

	@Column(name = "collectTotal", columnDefinition = "double(10,2) default '0.00'")
	public Double getCollectTotal() {
		return collectTotal;
	}

	public void setCollectTotal(Double collectTotal) {
		this.collectTotal = collectTotal;
	}

	@Column(name = "realTotal", columnDefinition = "double(10,2) default '0.00'")
	public Double getRealTotal() {
		return realTotal;
	}

	public void setRealTotal(Double realTotal) {
		this.realTotal = realTotal;
	}

	@Column(name = "ownTotal", columnDefinition = "double(10,2) default '0.00'")
	public Double getOwnTotal() {
		return ownTotal;
	}

	public void setOwnTotal(Double ownTotal) {
		this.ownTotal = ownTotal;
	}

	@Column(name = "operator",updatable=false)
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "handupManName")
	public String getHandupManName() {
		return handupManName;
	}

	public void setHandupManName(String handupManName) {
		this.handupManName = handupManName;
	}

	@Column(name = "comment", length = 400)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name = "favouravleFee", columnDefinition = "double(10,2) default '0.00'")
	public Double getFavouravleFee() {
		return favouravleFee;
	}

	public void setFavouravleFee(Double favouravleFee) {
		this.favouravleFee = favouravleFee;
	}

	@Column(name = "otherFee", columnDefinition = "double(10,2) default '0.00'")
	public Double getOtherFee() {
		return otherFee;
	}

	public void setOtherFee(Double otherFee) {
		this.otherFee = otherFee;
	}

	@Column(name = "mustTotal", columnDefinition = "double(10,2) default '0.00'")
	public Double getMustTotal() {
		return mustTotal;
	}

	public void setMustTotal(Double mustTotal) {
		this.mustTotal = mustTotal;
	}

	@Column(name = "payWay")
	public Integer getPayWay() {
		return payWay;
	}

	public void setPayWay(Integer payWay) {
		this.payWay = payWay;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="createDate",length=20,updatable=false)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
		TcollectFee other = (TcollectFee) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TcollectFee [id=" + id + ", schoolArea=" + schoolArea + ", collectNo=" + collectNo + ", createTime="
				+ createTime + ", handupMan=" + handupMan + ", handupManType=" + handupManType + ", feeDetailType="
				+ feeDetailType + ", collectTotal=" + collectTotal + ", operator=" + operator + "]";
	}

}

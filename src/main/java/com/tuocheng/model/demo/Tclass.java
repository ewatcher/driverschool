package com.tuocheng.model.demo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 班级开设实体类
 * 
 * @author MEI702
 * 
 */
@Entity
@Table(name = "tb_classes", schema = "")
public class Tclass implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 5785948973095059597L;
	public static final Integer CLASSTYPE_COUNTTIME = 1;// 计时
	public static final Integer CLASSTYPE_UNCOUNTTIME = 0;// 不计时
	private String id;// 标识
	private String name;// 开班名称
	private Double fee;// 费用
	private String costTime;// 时长
	private Integer type;// 类型：（A1，B1.……。）
	private Integer classType;// 开班类（计时，不计时）
	private String orderNo;// 显示顺序
	private String comment;// 描述
	private String schoolArea;// 所属校区
	private String operator;// 操作员

	// ===============关联关系======================
	private Set<Tstudent> student = new HashSet<Tstudent>();

	public Tclass() {
	}

	public Tclass(String id, String name, Double fee, String costTime,
			Integer type, Integer classType, String orderNo, String comment,
			String schoolArea, String operator, Set<Tstudent> student) {
		super();
		this.id = id;
		this.name = name;
		this.fee = fee;
		this.costTime = costTime;
		this.type = type;
		this.classType = classType;
		this.orderNo = orderNo;
		this.comment = comment;
		this.schoolArea = schoolArea;
		this.operator = operator;
		this.student = student;
	}

	@Id
	@Column(name = "id", nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "fee", columnDefinition = "double(10,2) default '0.00'", length = 20)
	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	@Column(name = "costTime", length = 20)
	public String getCostTime() {
		return costTime;
	}

	public void setCostTime(String costTime) {
		this.costTime = costTime;
	}

	@Column(name = "classType", length = 10)
	public Integer getClassType() {
		return classType;
	}

	public void setClassType(Integer classType) {
		this.classType = classType;
	}

	@Column(name = "orderNo", length = 36)
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name = "comment", length = 200)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name = "type", length = 4)
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "schoolArea", length = 36)
	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}
	
	@Column(name="operator",length=36,updatable=false)
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "clazz")
	public Set<Tstudent> getStudent() {
		return student;
	}

	public void setStudent(Set<Tstudent> student) {
		this.student = student;
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
		Tclass other = (Tclass) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tclass [id=" + id + ", name=" + name + ", fee=" + fee
				+ ", costTime=" + costTime + ", classType=" + classType
				+ ", order=" + orderNo + "]";
	}

}

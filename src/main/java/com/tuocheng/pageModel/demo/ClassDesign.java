package com.tuocheng.pageModel.demo;

/**
 * 班级开设action专用实体类
 * 
 * @author MEI702
 * 
 */
public class ClassDesign extends PageModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3581506217167581599L;
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

	private String schoolAreaId;// 所属校区标识
	private String schoolAreaName;// 所属校区名称

	private String ids;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getFee() {
		return fee;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public String getCostTime() {
		return costTime;
	}

	public void setCostTime(String costTime) {
		this.costTime = costTime;
	}

	public Integer getClassType() {
		return classType;
	}

	public void setClassType(Integer classType) {
		this.classType = classType;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getSchoolAreaId() {
		return schoolAreaId;
	}

	public void setSchoolAreaId(String schoolAreaId) {
		this.schoolAreaId = schoolAreaId;
	}

	public String getSchoolAreaName() {
		return schoolAreaName;
	}

	public void setSchoolAreaName(String schoolAreaName) {
		this.schoolAreaName = schoolAreaName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}

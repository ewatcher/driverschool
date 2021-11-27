package com.tuocheng.pageModel.demo;

import java.util.Date;

/**
 * 收费明细数据转换模型实体
 * 
 * @author MEI702
 *
 */
public class CollectFee extends PageModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8827441479537617208L;
	private String id;
	private String schoolArea;// 校区标识
	private String collectNo;// 收费序号
	private Date createTime;// 交费日期
	private String handupMan;// 交费人
	private Integer handupManType;// 交费人类别 1：学员，2：教练 3：职员
	private String feeDetailType;// 收费项目
	private Double collectTotal;// 收费金额 默认值0.00
	private Double realTotal;// 实收金额 默认值
	private Double ownTotal;// 欠费金额 默认值
	private Double mustTotal;// 欠费金额 默认值0.00
	private Double favouravleFee;// 优惠金额
	private Double otherFee;// 其它费用
	private Integer payWay;// 付款方式
	private String operator;// 收费员
	private String comment;// 备注

	private Date createTimeStart;
	private Date createTimeEnd;
	private String ids;
	private String schoolAreaName;
	private String feeDetailTypeName;// 收费项目(名称)
	private String handupManName;// 交费人名称
	private String handupManIdentity;// 交费人身份证
	private String handupManPhone;// 交费人手机
	private String handupManNo;// 交费人编号
	private Integer studentDriverType;// 驾照类型
	private String studentSex;
	private Integer applyType;// 申请类型：(1:初次申领,2:增加准驾车型,3:持军官驾驶证,4:持境外驾驶证)
	private Integer qualificationFlag;//运营资格  0：否 1：是，默认0
	
	private Date createDate;// 创建时间 排序专用
	private String ticketMaker;//制表人
	

	public Integer getQualificationFlag() {
		return qualificationFlag;
	}

	public void setQualificationFlag(Integer qualificationFlag) {
		this.qualificationFlag = qualificationFlag;
	}

	public String getStudentSex() {
		return studentSex;
	}

	public void setStudentSex(String studentSex) {
		this.studentSex = studentSex;
	}

	public Integer getApplyType() {
		return applyType;
	}

	public void setApplyType(Integer applyType) {
		this.applyType = applyType;
	}

	public String getTicketMaker() {
		return ticketMaker;
	}

	public void setTicketMaker(String ticketMaker) {
		this.ticketMaker = ticketMaker;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Double getMustTotal() {
		return mustTotal;
	}

	public void setMustTotal(Double mustTotal) {
		this.mustTotal = mustTotal;
	}

	public Double getFavouravleFee() {
		return favouravleFee;
	}

	public void setFavouravleFee(Double favouravleFee) {
		this.favouravleFee = favouravleFee;
	}

	public Double getOtherFee() {
		return otherFee;
	}

	public void setOtherFee(Double otherFee) {
		this.otherFee = otherFee;
	}

	public Integer getPayWay() {
		return payWay;
	}

	public void setPayWay(Integer payWay) {
		this.payWay = payWay;
	}

	public String getHandupManNo() {
		return handupManNo;
	}

	public void setHandupManNo(String handupManNo) {
		this.handupManNo = handupManNo;
	}

	public Integer getStudentDriverType() {
		return studentDriverType;
	}

	public void setStudentDriverType(Integer studentDriverType) {
		this.studentDriverType = studentDriverType;
	}

	public Double getRealTotal() {
		return realTotal;
	}

	public void setRealTotal(Double realTotal) {
		this.realTotal = realTotal;
	}

	public Double getOwnTotal() {
		return ownTotal;
	}

	public void setOwnTotal(Double ownTotal) {
		this.ownTotal = ownTotal;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getHandupManName() {
		return handupManName;
	}

	public void setHandupManName(String handupManName) {
		this.handupManName = handupManName;
	}

	public String getHandupManIdentity() {
		return handupManIdentity;
	}

	public void setHandupManIdentity(String handupManIdentity) {
		this.handupManIdentity = handupManIdentity;
	}

	public String getHandupManPhone() {
		return handupManPhone;
	}

	public void setHandupManPhone(String handupManPhone) {
		this.handupManPhone = handupManPhone;
	}

	public String getFeeDetailTypeName() {
		return feeDetailTypeName;
	}

	public void setFeeDetailTypeName(String feeDetailTypeName) {
		this.feeDetailTypeName = feeDetailTypeName;
	}

	public String getSchoolAreaName() {
		return schoolAreaName;
	}

	public void setSchoolAreaName(String schoolAreaName) {
		this.schoolAreaName = schoolAreaName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	public String getCollectNo() {
		return collectNo;
	}

	public void setCollectNo(String collectNo) {
		this.collectNo = collectNo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getHandupMan() {
		return handupMan;
	}

	public void setHandupMan(String handupMan) {
		this.handupMan = handupMan;
	}

	public Integer getHandupManType() {
		return handupManType;
	}

	public void setHandupManType(Integer handupManType) {
		this.handupManType = handupManType;
	}

	public String getFeeDetailType() {
		return feeDetailType;
	}

	public void setFeeDetailType(String feeDetailType) {
		this.feeDetailType = feeDetailType;
	}

	public Double getCollectTotal() {
		return collectTotal;
	}

	public void setCollectTotal(Double collectTotal) {
		this.collectTotal = collectTotal;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}

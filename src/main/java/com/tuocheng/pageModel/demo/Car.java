package com.tuocheng.pageModel.demo;

import java.io.File;
import java.util.Date;

/**
 * 车辆管理实体类(action专用)
 * 
 * @author MEI702
 * 
 */
public class Car extends PageModel {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 365741094971384357L;
	private String id;
	private String vehicleBrands;// 车辆品牌
	private String carNo;// 车牌号
	private String vehicleType;// 车辆型号
	private String licenseNo;// 行驶证号
	private String transportNo; // 运输证号
	private Date buyDate;// 购买日期
	private Date businessExpireDay;// 商业保险到期日期
	private Date insuranceExpireDay;// 交强保险到期日期
	private Date maintainDay;// 二次维护日期
	private Date examinedDay;// 下次年审日期
	private String comment;// 备注
	private String schoolArea;// 校区标识
	private String operator;// 操作員

	private String ids;

	private String importFile;
	private String exportFile;
	private String schoolAreaName;// 校区标识

	private Integer carType;// 车辆类型（1－A1,2－A2,... 10-C5,11-电车,12-模拟车，13－C2或者C5）
	private Integer usingState;// 车辆使用状态(0:未使用，1：使用)

	// 车辆使用使用类型（A1——C5，特殊C2，C5切换）（实例:车辆类型C2/C5,使用情况：1.使用，使用类型：C5）
	private Integer useType;
	private String carTrainerId;

	private Integer carBanding;// !1:表示查找与车辆捆绑的教练
	private String carusingTempId;// 冗余字段(创建车辆使用明细时使用)
	// 查询字段...
	private String trainerId;
	private String trainerName;
	private String trainerCodeNo;

	private String uploadfileName;
	private String organizationId;

	private Integer receiveWechatFlag;// 是否接受微信预约，0：不开启，1：表示开启

	public Integer getReceiveWechartFlag() {
		return receiveWechatFlag;
	}

	public void setReceiveWecharFlag(Integer receiveWecharFlag) {
		this.receiveWechatFlag = receiveWecharFlag;
	}

	public Integer getReceiveWechatFlag() {
		return receiveWechatFlag;
	}

	public void setReceiveWechatFlag(Integer receiveWechatFlag) {
		this.receiveWechatFlag = receiveWechatFlag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVehicleBrands() {
		return vehicleBrands;
	}

	public void setVehicleBrands(String vehicleBrands) {
		this.vehicleBrands = vehicleBrands;
	}

	public String getTrainerCodeNo() {
		return trainerCodeNo;
	}

	public void setTrainerCodeNo(String trainerCodeNo) {
		this.trainerCodeNo = trainerCodeNo;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getSchoolArea() {
		return schoolArea;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public String getSchoolAreaName() {
		return schoolAreaName;
	}

	public String getCarTrainerId() {
		return carTrainerId;
	}

	public void setCarTrainerId(String carTrainerId) {
		this.carTrainerId = carTrainerId;
	}

	public void setSchoolAreaName(String schoolAreaName) {
		this.schoolAreaName = schoolAreaName;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String getCarusingTempId() {
		return carusingTempId;
	}

	public void setCarusingTempId(String carusingTempId) {
		this.carusingTempId = carusingTempId;
	}

	public String getUploadfileName() {
		return uploadfileName;
	}

	public void setUploadfileName(String uploadfileName) {
		this.uploadfileName = uploadfileName;
	}

	public String getTransportNo() {
		return transportNo;
	}

	public void setTransportNo(String transportNo) {
		this.transportNo = transportNo;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public Date getBusinessExpireDay() {
		return businessExpireDay;
	}

	public void setBusinessExpireDay(Date businessExpireDay) {
		this.businessExpireDay = businessExpireDay;
	}

	public Date getInsuranceExpireDay() {
		return insuranceExpireDay;
	}

	public Integer getCarBanding() {
		return carBanding;
	}

	public void setCarBanding(Integer carBanding) {
		this.carBanding = carBanding;
	}

	public void setInsuranceExpireDay(Date insuranceExpireDay) {
		this.insuranceExpireDay = insuranceExpireDay;
	}

	public Date getMaintainDay() {
		return maintainDay;
	}

	public void setMaintainDay(Date maintainDay) {
		this.maintainDay = maintainDay;
	}

	public Date getExaminedDay() {
		return examinedDay;
	}

	public void setExaminedDay(Date examinedDay) {
		this.examinedDay = examinedDay;
	}

	public String getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(String trainerId) {
		this.trainerId = trainerId;
	}

	public String getTrainerName() {
		return trainerName;
	}

	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getImportFile() {
		return importFile;
	}

	public void setImportFile(String importFile) {
		this.importFile = importFile;
	}

	public String getExportFile() {
		return exportFile;
	}

	public void setExportFile(String exportFile) {
		this.exportFile = exportFile;
	}

	public Integer getCarType() {
		return carType;
	}

	public void setCarType(Integer carType) {
		this.carType = carType;
	}

	public Integer getUsingState() {
		return usingState;
	}

	public void setUsingState(Integer usingState) {
		this.usingState = usingState;
	}

	public Integer getUseType() {
		return useType;
	}

	public void setUseType(Integer useType) {
		this.useType = useType;
	}

}

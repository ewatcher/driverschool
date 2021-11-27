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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 车辆管理实体模型(DAO专用)
 * 
 * @author MEI702
 * 
 */
@Entity
@Table(name = "tb_cars", schema = "")
public class Tcar implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9090317064490501716L;
	// ===========base properties==============
	public static final Integer CAR_NOUSING = 1;
	public static final Integer CAR_USING = 2;

	public static final Integer CAR_USINGTYPE_A1 = 1;
	public static final Integer CAR_USINGTYPE_A2 = 2;
	public static final Integer CAR_USINGTYPE_A3 = 3;
	public static final Integer CAR_USINGTYPE_B1 = 4;
	public static final Integer CAR_USINGTYPE_B2 = 5;
	public static final Integer CAR_USINGTYPE_C1 = 6;
	public static final Integer CAR_USINGTYPE_C2 = 7;
	public static final Integer CAR_USINGTYPE_C3 = 8;
	public static final Integer CAR_USINGTYPE_C4 = 9;
	public static final Integer CAR_USINGTYPE_C5 = 10;

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
	private String schoolArea;
	private String operator;// 操作員

	private Integer carType;// 车辆类型（1－A1,2－A2,... 10-C5,11-电车,12-模拟车，13－C2或者C5）
	private Integer usingState;// 车辆使用状态( 1:未启用,2：启用,3:其它)
	// 车辆使用使用类型（A1——C5，特殊C2，C5切换，1表示C2，2表示C5，3表示备用）（实例:车辆类型C2/C5,使用情况：1.使用，使用类型：C5）
	private Integer useType;
	private Integer receiveWechatFlag;// 是否接受微信预约，0：不开启，1：表示开启 默认值为1

	private String carusingTempId;// 冗余字段(创建车辆使用明细时使用)
	// ==============relation properties==============
	private Set<TcarusingTemp> carusingTemp = new HashSet<TcarusingTemp>(0);
	private Ttrainer trainer;// 一人一车，车与教练捆绑

	public Tcar() {
	}

	public Tcar(String id, String vehicleBrands, String carNo, String vehicleType, String licenseNo, String transportNo,
			Date buyDate, Date businessExpireDay, Date insuranceExpireDay, Date maintainDay, Date examinedDay,
			String comment, String schoolArea, String operator, Integer wechatFlag, Integer carType, Integer usingState,
			Integer useType, Integer receiveWechatFlag, String carusingTempId, Set<TcarusingTemp> carusingTemp,
			Ttrainer trainer) {
		this.id = id;
		this.vehicleBrands = vehicleBrands;
		this.carNo = carNo;
		this.vehicleType = vehicleType;
		this.licenseNo = licenseNo;
		this.transportNo = transportNo;
		this.buyDate = buyDate;
		this.businessExpireDay = businessExpireDay;
		this.insuranceExpireDay = insuranceExpireDay;
		this.maintainDay = maintainDay;
		this.examinedDay = examinedDay;
		this.comment = comment;
		this.schoolArea = schoolArea;
		this.operator = operator;
		this.carType = carType;
		this.usingState = usingState;
		this.useType = useType;
		this.receiveWechatFlag = receiveWechatFlag;
		this.carusingTempId = carusingTempId;
		this.carusingTemp = carusingTemp;
		this.trainer = trainer;
	}

	@Id
	@Column(name = "id", nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "vehicleBrands", length = 36)
	public String getVehicleBrands() {
		return vehicleBrands;
	}

	public void setVehicleBrands(String vehicleBrands) {
		this.vehicleBrands = vehicleBrands;
	}

	@Column(name = "carNo", nullable = false, length = 36)
	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	@Column(name = "vehicleType", length = 36)
	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	@Column(name = "licenseNo", nullable = false, length = 36)
	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	@Column(name = "transportNo", length = 36)
	public String getTransportNo() {
		return transportNo;
	}

	public void setTransportNo(String transportNo) {
		this.transportNo = transportNo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "buyDate", length = 7)
	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "businessExpireDay", length = 7)
	public Date getBusinessExpireDay() {
		return businessExpireDay;
	}

	public void setBusinessExpireDay(Date businessExpireDay) {
		this.businessExpireDay = businessExpireDay;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "insuranceExpireDay", length = 7)
	public Date getInsuranceExpireDay() {
		return insuranceExpireDay;
	}

	public void setInsuranceExpireDay(Date insuranceExpireDay) {
		this.insuranceExpireDay = insuranceExpireDay;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "maintainDay", length = 7)
	public Date getMaintainDay() {
		return maintainDay;
	}

	public void setMaintainDay(Date maintainDay) {
		this.maintainDay = maintainDay;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "examinedDay", length = 7)
	public Date getExaminedDay() {
		return examinedDay;
	}

	public void setExaminedDay(Date examinedDay) {
		this.examinedDay = examinedDay;
	}

	@Column(name = "comment", length = 500)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name = "schoolArea", length = 36)
	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	@Column(name = "operator", length = 36, updatable = false)
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "carType", length = 4)
	public Integer getCarType() {
		return carType;
	}

	public void setCarType(Integer carType) {
		this.carType = carType;
	}

	@Column(name = "usingState", length = 4)
	public Integer getUsingState() {
		return usingState;
	}

	public void setUsingState(Integer usingState) {
		this.usingState = usingState;
	}

	@Column(name = "useType", length = 4)
	public Integer getUseType() {
		return useType;
	}

	public void setUseType(Integer useType) {
		this.useType = useType;
	}

	@Column(name = "carusingTempId", length = 36)
	public String getCarusingTempId() {
		return carusingTempId;
	}

	public void setCarusingTempId(String carusingTempId) {
		this.carusingTempId = carusingTempId;
	}

	@Column(name = "receiveWechatFlag", columnDefinition = "INT DEFAULT 1", length = 4)
	public Integer getReceiveWechatFlag() {
		return receiveWechatFlag;
	}

	public void setReceiveWechatFlag(Integer receiveWechatFlag) {
		this.receiveWechatFlag = receiveWechatFlag;
	}
	

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "car")
	public Set<TcarusingTemp> getCarusingTemp() {
		return carusingTemp;
	}

	public void setCarusingTemp(Set<TcarusingTemp> carusingTemp) {
		this.carusingTemp = carusingTemp;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trainerId")
	public Ttrainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Ttrainer trainer) {
		this.trainer = trainer;
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
		Tcar other = (Tcar) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tcar [id=" + id + ", vehicleBrands=" + vehicleBrands + ", carNo=" + carNo + ", vehicleType="
				+ vehicleType + ", licenseNo=" + licenseNo + ", transportNo=" + transportNo + ", buyDate=" + buyDate
				+ ", businessExpireDay=" + businessExpireDay + ", insuranceExpireDay=" + insuranceExpireDay
				+ ", maintainDay=" + maintainDay + ", examinedDay=" + examinedDay + ", comment=" + comment + "]";
	}

}

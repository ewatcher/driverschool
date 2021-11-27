package com.tuocheng.pageModel.demo;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder.In;

import com.tuocheng.pageModel.base.StudentPageModel;

/**
 * 教练实体类(action专用实体类)
 * 
 * @author MEI702
 * 
 */
public class Trainer extends StudentPageModel {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 4281456755058254925L;
	public static final Integer ARRANGEFLAG_ELECTRIC=1;
	public static final Integer ARRANGEFLAG_SIMULATION=2;
	public static final Integer ARRANGEFLAG_OTHER=3;
	private String id; // 标识
	private String name;// 姓名
	private String codeNo;// 教练编号
	private String nation;// 民族
	private Date birthday;// 出生日期
	private String sex;// 性别
	private String contry;// 籍贯
	private String identity;// 身份证号
	private String addres;// 地址
	private String phone;// 手机
	private String telephone;// 电话
	private String email;// 邮箱
	private String comment;// 备注
	private String schoolArea;// 所属于校区
	private String operator;// 操作员
	private Integer carBanding;// 0:表示教练未与车辆进行捆绑，1与表教练已经与车辆捆绑
	private Integer trainerType;// 0:表示本部教练，1表示外聘教练
	private Integer arrangeType;// 排班类型：1:电车，2：模拟器，3其它

	private String schoolAreaName;// 所属于校区名称－－－在datagrid中展现
	// =================diver licence message============
	private String driverLicenseId;// 驾驶证号
	private Date firstGetDate;// 初次领驾驶证日期
	private Integer driverType;// 准驾类型
	private String driverCarType;// 准教类型
	private Date driverValidatyStart;// 驾驶证有效日期
	private Date driverValidatyEnd;// 驾驶证有效日期
	private String teachId;// 教练员编号
	private Date teachValidatyStart;// 教练员证有效期
	private Date teachBalidatyEnd;// 教练员证有效期
	private Date firstTeachingDate;// 教练员初次领证日期
	private Date jionDay;// 入职日期
	private Integer arrangeFlag;// 排班标记，默认0,1：表示可排班，0：不可排班

	// 驾驶证有效期范围查询
	private Date driverValidatyBegin;// 开始时间
	private Date driverValidatyEndEnd;// 结束时间

	// =============add properties========
	private String ids;

	private DriverType myDriverType;
	private DriverCarType myDriverCarType;

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

	public String getCodeNo() {
		return codeNo;
	}

	public void setCodeNo(String codeNo) {
		this.codeNo = codeNo;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getSchoolAreaName() {
		return schoolAreaName;
	}

	public void setSchoolAreaName(String schoolAreaName) {
		this.schoolAreaName = schoolAreaName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public Integer getTrainerType() {
		return trainerType;
	}

	public void setTrainerType(Integer trainerType) {
		this.trainerType = trainerType;
	}

	public Integer getCarBanding() {
		return carBanding;
	}

	public void setCarBanding(Integer carBanding) {
		this.carBanding = carBanding;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	public Integer getArrangeFlag() {
		return arrangeFlag;
	}

	public void setArrangeFlag(Integer arrangeFlag) {
		this.arrangeFlag = arrangeFlag;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getContry() {
		return contry;
	}

	public void setContry(String contry) {
		this.contry = contry;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getAddres() {
		return addres;
	}

	public void setAddres(String addres) {
		this.addres = addres;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDriverLicenseId() {
		return driverLicenseId;
	}

	public void setDriverLicenseId(String diverLicenseId) {
		this.driverLicenseId = diverLicenseId;
	}

	public Date getFirstGetDate() {
		return firstGetDate;
	}

	public void setFirstGetDate(Date firstGetDate) {
		this.firstGetDate = firstGetDate;
	}

	public Integer getDriverType() {
		return driverType;
	}

	public void setDriverType(Integer driverType) {
		this.driverType = driverType;
	}

	public Date getDriverValidatyStart() {
		return driverValidatyStart;
	}

	public void setDriverValidatyStart(Date driverValidatyStart) {
		this.driverValidatyStart = driverValidatyStart;
	}

	public Integer getArrangeType() {
		return arrangeType;
	}

	public void setArrangeType(Integer arrangeType) {
		this.arrangeType = arrangeType;
	}

	public Date getDriverValidatyEnd() {
		return driverValidatyEnd;
	}

	public void setDriverValidatyEnd(Date driverValidatyEnd) {
		this.driverValidatyEnd = driverValidatyEnd;
	}

	public String getTeachId() {
		return teachId;
	}

	public void setTeachId(String teachId) {
		this.teachId = teachId;
	}

	public Date getTeachValidatyStart() {
		return teachValidatyStart;
	}

	public void setTeachValidatyStart(Date teachValidatyStart) {
		this.teachValidatyStart = teachValidatyStart;
	}

	public Date getTeachBalidatyEnd() {
		return teachBalidatyEnd;
	}

	public void setTeachBalidatyEnd(Date teachBalidatyEnd) {
		this.teachBalidatyEnd = teachBalidatyEnd;
	}

	public Date getFirstTeachingDate() {
		return firstTeachingDate;
	}

	public void setFirstTeachingDate(Date firstTeachingDate) {
		this.firstTeachingDate = firstTeachingDate;
	}

	public Date getDriverValidatyBegin() {
		return driverValidatyBegin;
	}

	public void setDriverValidatyBegin(Date driverValidatyBegin) {
		this.driverValidatyBegin = driverValidatyBegin;
	}

	public Date getDriverValidatyEndEnd() {
		return driverValidatyEndEnd;
	}

	public void setDriverValidatyEndEnd(Date driverValidatyEndEnd) {
		this.driverValidatyEndEnd = driverValidatyEndEnd;
	}

	public Date getJionDay() {
		return jionDay;
	}

	public void setJionDay(Date jionDay) {
		this.jionDay = jionDay;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getDriverCarType() {
		return driverCarType;
	}

	public void setDriverCarType(String driverCarType) {
		this.driverCarType = driverCarType;
	}

	public DriverType getMyDriverType() {
		return myDriverType;
	}

	public void setMyDriverType(DriverType myDriverType) {
		this.myDriverType = myDriverType;
	}

	public DriverCarType getMyDriverCarType() {
		return myDriverCarType;
	}

	public void setMyDriverCarType(DriverCarType myDriverCarType) {
		this.myDriverCarType = myDriverCarType;
	}

}

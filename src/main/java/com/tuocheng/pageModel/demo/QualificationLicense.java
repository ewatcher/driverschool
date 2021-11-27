package com.tuocheng.pageModel.demo;

import java.util.Date;

import com.tuocheng.pageModel.base.StudentPageModel;

/**
 * 资格证实体类（action专用）
 * 
 * @author MEI702
 * 
 */
public class QualificationLicense extends StudentPageModel {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 6437617299740586377L;

	private String id;// 标识
	private String numbering;// 编号
	private Date firstGetDate;// 初领日期
	private Date grantDate;// 资格证发放日期
	private Date recieveDate;// 学员领取日期
	private Integer driverType;// 准驾类型(1:A1,2:A2,3:A3,4:B1,5:B2,6:C1,7:C2,8:C3,9:C4,10:D,11:E,12:F,13:M,14:N)
	private Date validBeginDate;// 有效期开始日期
	private Integer validYear;// 有效年限
	private String schoolArea;// 所属于校区
	private Integer grantState;// 资格领取状态(1.未领取，2.已领取)
	private String comment;// 备注
	private String operator;// 操作员

	// 查询使用字段
	private Date firstGetDateStart;// 初领日期开始
	private Date firstGetDateEnd;// 初领日期结束
	private Date grantDateStart;
	private Date grantDateEnd;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumbering() {
		return numbering;
	}

	public void setNumbering(String numbering) {
		this.numbering = numbering;
	}

	public Date getFirstGetDate() {
		return firstGetDate;
	}

	public void setFirstGetDate(Date firstGetDate) {
		this.firstGetDate = firstGetDate;
	}

	public Date getGrantDate() {
		return grantDate;
	}

	public void setGrantDate(Date grantDate) {
		this.grantDate = grantDate;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getRecieveDate() {
		return recieveDate;
	}

	public void setRecieveDate(Date recieveDate) {
		this.recieveDate = recieveDate;
	}

	public Integer getDriverType() {
		return driverType;
	}

	public Date getGrantDateStart() {
		return grantDateStart;
	}

	public void setGrantDateStart(Date grantDateStart) {
		this.grantDateStart = grantDateStart;
	}

	public Date getGrantDateEnd() {
		return grantDateEnd;
	}

	public void setGrantDateEnd(Date grantDateEnd) {
		this.grantDateEnd = grantDateEnd;
	}

	public void setDriverType(Integer driverType) {
		this.driverType = driverType;
	}

	public Date getValidBeginDate() {
		return validBeginDate;
	}

	public void setValidBeginDate(Date validBeginDate) {
		this.validBeginDate = validBeginDate;
	}

	public Integer getValidYear() {
		return validYear;
	}

	public void setValidYear(Integer validYear) {
		this.validYear = validYear;
	}

	public Date getFirstGetDateStart() {
		return firstGetDateStart;
	}

	public void setFirstGetDateStart(Date firstGetDateStart) {
		this.firstGetDateStart = firstGetDateStart;
	}

	public Date getFirstGetDateEnd() {
		return firstGetDateEnd;
	}

	public void setFirstGetDateEnd(Date firstGetDateEnd) {
		this.firstGetDateEnd = firstGetDateEnd;
	}

	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	public Integer getGrantState() {
		return grantState;
	}

	public void setGrantState(Integer grantState) {
		this.grantState = grantState;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}

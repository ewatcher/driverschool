package com.tuocheng.pageModel.demo;

import java.util.Date;

/**
 * 自动档车辆排班数据模型类
 * 
 * @author 农峰
 * 
 */
public class AutoCarArrange extends PageModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1427557002100927143L;
	private String id;// 标识
	private String year;// 年份
	private String month;// 月份
	private String weekth;// 星期（1：星期一，2：星期二...）
	private String schoolArea;// 校区标识
	private String operator;// 操作员

	private Date arrangeDate;// 排班第一天
	private String noArrangeDates;// 排班日期
	private Integer teachingType;// 施教类型：1:A1,2:A2,3:A3 ....C5
	private Integer workingType;// 排班类型:1.电车，2.模拟 3.理论 4.五项 5.路训

	private Date firstArrangeDate;// 该变量记录教练排班计划中的第一天
	private Date lastArrangeDate;// 该变量记录教练排班计划中的最后一天

	// 教练排班时段(0表示未使用，1：表示启用)
	private Integer ItemTime8;// 表示8：00-9：00时段
	private Integer ItemTime9;
	private Integer ItemTime10;
	private Integer ItemTime11;
	private Integer ItemTime12;
	private Integer ItemTime13;
	private Integer ItemTime14;
	private Integer ItemTime15;
	private Integer ItemTime16;
	private Integer ItemTime17;
	private Integer ItemTime18;
	private Integer ItemTime19;
	private Integer ItemTime20;
	private Integer ItemTime21;
	private Integer ItemTime8Flag;// 表示8：00-9：00时段使用情况 1:表示已经有约，0：默认无预约
	private Integer ItemTime9Flag;
	private Integer ItemTime10Flag;
	private Integer ItemTime11Flag;
	private Integer ItemTime12Flag;
	private Integer ItemTime13Flag;
	private Integer ItemTime14Flag;
	private Integer ItemTime15Flag;
	private Integer ItemTime16Flag;
	private Integer ItemTime17Flag;
	private Integer ItemTime18Flag;
	private Integer ItemTime19Flag;
	private Integer ItemTime20Flag;
	private Integer ItemTime21Flag;
	private String carId;
	private String carNo;

	private Date arrangeDateBegin;// 排班第一天
	private Date arrangeDateEnd;// 排班第一天
	private String ids;
	private String schoolAreaName;
	private Integer receiveWechatFlag;//微信预约标记

	// ==============generating==============================


	public String getIds() {
		return ids;
	}

	public Integer getReceiveWechatFlag() {
		return receiveWechatFlag;
	}

	public void setReceiveWechatFlag(Integer receiveWechatFlag) {
		this.receiveWechatFlag = receiveWechatFlag;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getWeekth() {
		return weekth;
	}

	public void setWeekth(String weekth) {
		this.weekth = weekth;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getArrangeDate() {
		return arrangeDate;
	}

	public void setArrangeDate(Date arrangeDate) {
		this.arrangeDate = arrangeDate;
	}

	public String getNoArrangeDates() {
		return noArrangeDates;
	}

	public void setNoArrangeDates(String noArrangeDates) {
		this.noArrangeDates = noArrangeDates;
	}

	public Integer getTeachingType() {
		return teachingType;
	}

	public void setTeachingType(Integer teachingType) {
		this.teachingType = teachingType;
	}

	public String getSchoolAreaName() {
		return schoolAreaName;
	}

	public void setSchoolAreaName(String schoolAreaName) {
		this.schoolAreaName = schoolAreaName;
	}

	public Integer getWorkingType() {
		return workingType;
	}

	public void setWorkingType(Integer workingType) {
		this.workingType = workingType;
	}

	public Date getFirstArrangeDate() {
		return firstArrangeDate;
	}

	public void setFirstArrangeDate(Date firstArrangeDate) {
		this.firstArrangeDate = firstArrangeDate;
	}

	public Date getLastArrangeDate() {
		return lastArrangeDate;
	}

	public void setLastArrangeDate(Date lastArrangeDate) {
		this.lastArrangeDate = lastArrangeDate;
	}

	public Date getArrangeDateBegin() {
		return arrangeDateBegin;
	}

	public void setArrangeDateBegin(Date arrangeDateBegin) {
		this.arrangeDateBegin = arrangeDateBegin;
	}

	public Date getArrangeDateEnd() {
		return arrangeDateEnd;
	}

	public Integer getItemTime8Flag() {
		return ItemTime8Flag;
	}

	public void setItemTime8Flag(Integer itemTime8Flag) {
		ItemTime8Flag = itemTime8Flag;
	}

	public Integer getItemTime9Flag() {
		return ItemTime9Flag;
	}

	public void setItemTime9Flag(Integer itemTime9Flag) {
		ItemTime9Flag = itemTime9Flag;
	}

	public Integer getItemTime10Flag() {
		return ItemTime10Flag;
	}

	public void setItemTime10Flag(Integer itemTime10Flag) {
		ItemTime10Flag = itemTime10Flag;
	}

	public Integer getItemTime11Flag() {
		return ItemTime11Flag;
	}

	public void setItemTime11Flag(Integer itemTime11Flag) {
		ItemTime11Flag = itemTime11Flag;
	}

	public Integer getItemTime12Flag() {
		return ItemTime12Flag;
	}

	public void setItemTime12Flag(Integer itemTime12Flag) {
		ItemTime12Flag = itemTime12Flag;
	}

	public Integer getItemTime13Flag() {
		return ItemTime13Flag;
	}

	public void setItemTime13Flag(Integer itemTime13Flag) {
		ItemTime13Flag = itemTime13Flag;
	}

	public Integer getItemTime14Flag() {
		return ItemTime14Flag;
	}

	public void setItemTime14Flag(Integer itemTime14Flag) {
		ItemTime14Flag = itemTime14Flag;
	}

	public Integer getItemTime15Flag() {
		return ItemTime15Flag;
	}

	public void setItemTime15Flag(Integer itemTime15Flag) {
		ItemTime15Flag = itemTime15Flag;
	}

	public Integer getItemTime16Flag() {
		return ItemTime16Flag;
	}

	public void setItemTime16Flag(Integer itemTime16Flag) {
		ItemTime16Flag = itemTime16Flag;
	}

	public Integer getItemTime17Flag() {
		return ItemTime17Flag;
	}

	public void setItemTime17Flag(Integer itemTime17Flag) {
		ItemTime17Flag = itemTime17Flag;
	}

	public Integer getItemTime18Flag() {
		return ItemTime18Flag;
	}

	public void setItemTime18Flag(Integer itemTime18Flag) {
		ItemTime18Flag = itemTime18Flag;
	}

	public Integer getItemTime19Flag() {
		return ItemTime19Flag;
	}

	public void setItemTime19Flag(Integer itemTime19Flag) {
		ItemTime19Flag = itemTime19Flag;
	}

	public Integer getItemTime20Flag() {
		return ItemTime20Flag;
	}

	public void setItemTime20Flag(Integer itemTime20Flag) {
		ItemTime20Flag = itemTime20Flag;
	}

	public Integer getItemTime21Flag() {
		return ItemTime21Flag;
	}

	public void setItemTime21Flag(Integer itemTime21Flag) {
		ItemTime21Flag = itemTime21Flag;
	}

	public void setArrangeDateEnd(Date arrangeDateEnd) {
		this.arrangeDateEnd = arrangeDateEnd;
	}

	public Integer getItemTime8() {
		return ItemTime8;
	}

	public void setItemTime8(Integer itemTime8) {
		ItemTime8 = itemTime8;
	}

	public Integer getItemTime9() {
		return ItemTime9;
	}

	public void setItemTime9(Integer itemTime9) {
		ItemTime9 = itemTime9;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public Integer getItemTime10() {
		return ItemTime10;
	}

	public void setItemTime10(Integer itemTime10) {
		ItemTime10 = itemTime10;
	}

	public Integer getItemTime11() {
		return ItemTime11;
	}

	public void setItemTime11(Integer itemTime11) {
		ItemTime11 = itemTime11;
	}

	public Integer getItemTime12() {
		return ItemTime12;
	}

	public void setItemTime12(Integer itemTime12) {
		ItemTime12 = itemTime12;
	}

	public Integer getItemTime13() {
		return ItemTime13;
	}

	public void setItemTime13(Integer itemTime13) {
		ItemTime13 = itemTime13;
	}

	public Integer getItemTime14() {
		return ItemTime14;
	}

	public void setItemTime14(Integer itemTime14) {
		ItemTime14 = itemTime14;
	}

	public Integer getItemTime15() {
		return ItemTime15;
	}

	public void setItemTime15(Integer itemTime15) {
		ItemTime15 = itemTime15;
	}

	public Integer getItemTime16() {
		return ItemTime16;
	}

	public void setItemTime16(Integer itemTime16) {
		ItemTime16 = itemTime16;
	}

	public Integer getItemTime17() {
		return ItemTime17;
	}

	public void setItemTime17(Integer itemTime17) {
		ItemTime17 = itemTime17;
	}

	public Integer getItemTime18() {
		return ItemTime18;
	}

	public void setItemTime18(Integer itemTime18) {
		ItemTime18 = itemTime18;
	}

	public Integer getItemTime19() {
		return ItemTime19;
	}

	public void setItemTime19(Integer itemTime19) {
		ItemTime19 = itemTime19;
	}

	public Integer getItemTime20() {
		return ItemTime20;
	}

	public void setItemTime20(Integer itemTime20) {
		ItemTime20 = itemTime20;
	}

	public Integer getItemTime21() {
		return ItemTime21;
	}

	public void setItemTime21(Integer itemTime21) {
		ItemTime21 = itemTime21;
	}

}

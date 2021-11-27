package com.tuocheng.pageModel.demo;

import java.util.Date;

/**
 * 教练排班实体类
 * 
 * @author MEI702
 * 
 */
public class TrainerArrange extends PageModel {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 2537756204696277164L;
	private String id;// 标识
	private String trainTime;
	private String No;// 编号
	private String year;// 年份
	private String month;// 月份
	private String weekth;// 星期（1：星期一，2：星期二...）
	private Date arrangeDate;// 排班第一天
	private String noArrangeDates;// no arrange date
	private String operator;// 操作员

	private Integer teachingType;// 施教类型：1:A1,2:A2,3:A3 ....C5
	private Integer workingType;// 排班类型:1.电车，2.模拟　3.理论　4.五项　5.路训
	private Integer arrangeType;// 排班类型（1：电车，2：模拟，3：其它）

	private Date startArrangeDate;
	private Date endArrangeDate;

	private String trainerIds;
	private String ids;

	private String trainerId;
	private String trainerName;
	private String trainerCodeNo;
	private String trainerSchoolAreaName;
	private String trainerIdentity;
	private String trainerDriverLicenseId;
	private Integer trainerDriverType;
	private String trainerDriverCarType;
	private String trainerPhone;
	private String organizationId;
	private String organizationName;// 与Tstudent属性同名（达到按列排序目的）
	private Integer trainerArrangeFlag;
	private Integer trainerType;

	private String trainerTechingType;// 教练员准教类型

	private String schoolArea;// 所属校区
	private String schoolAreaId;// 校区标识
	private String schoolAreaName;// 校区标识名称

	private Date firstArrangeDate;// 该变量记录教练排班计划中的第一天
	private Date firstArrangeDateStart;// 该变量记录教练排班计划中的第一天(开始)
	private Date firstArrangeDateEnd;// 该变量记录教练排班计划中的第一天（结束）
	private Date lastArrangeDate;// 该变量记录教练排班计划中的最后一天
	private Date lastArrangeDateStart;// 该变量记录教练排班计划中的最后一天(开始)
	private Date lastArrangeDateEnd;// 该变量记录教练排班计划中的最后一天（结束）

	// 教练排班时段
	private Integer ItemTime7;// 表示7：00-8：00时段
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

	// 教练排班时段的训练内容（1：电车，2：模拟，3：五项，4：路训）
	private Integer ItemTime7flag;
	private Integer ItemTime8flag;
	private Integer ItemTime9flag;
	private Integer ItemTime10flag;
	private Integer ItemTime11flag;
	private Integer ItemTime12flag;
	private Integer ItemTime13flag;
	private Integer ItemTime14flag;
	private Integer ItemTime15flag;
	private Integer ItemTime16flag;
	private Integer ItemTime17flag;
	private Integer ItemTime18flag;
	private Integer ItemTime19flag;
	private Integer ItemTime20flag;
	private Integer ItemTime21flag;

	private Integer selectAll;// 全选择教练排班字段标识
	private Integer cancelAll;

	private String arrangeId;
	private String trainerReservationId;// 教练预约标识
	private String trainerReservationDetailId;
	private Date reservationDate;// 预约日期
	private Integer item7TrainContent;// 该时段训练类型
	private Integer item8TrainContent;// 该时段训练类型
	private Integer item9TrainContent;// 该时段训练类型
	private Integer item10TrainContent;// 该时段训练类型
	private Integer item11TrainContent;// 该时段训练类型
	private Integer item12TrainContent;// 该时段训练类型
	private Integer item13TrainContent;// 该时段训练类型
	private Integer item14TrainContent;// 该时段训练类型
	private Integer item15TrainContent;// 该时段训练类型
	private Integer item16TrainContent;// 该时段训练类型
	private Integer item17TrainContent;// 该时段训练类型
	private Integer item18TrainContent;// 该时段训练类型
	private Integer item19TrainContent;// 该时段训练类型
	private Integer item20TrainContent;// 该时段训练类型
	private Integer item21TrainContent;// 该时段训练类型
	private String reservationFieldId7;// 教练预约明细表中的学员预约标识记录标记（0：表示空，1：表示存在记录）
	private String reservationFieldId8;// 教练预约明细表中的学员预约标识记录标记（0：表示空，1：表示存在记录）
	private String reservationFieldId9;// 教练预约明细表中的学员预约标识记录标记（0：表示空，1：表示存在记录）
	private String reservationFieldId10;// 教练预约明细表中的学员预约标识记录标记（0：表示空，1：表示存在记录）
	private String reservationFieldId11;// 教练预约明细表中的学员预约标识记录标记（0：表示空，1：表示存在记录）
	private String reservationFieldId12;// 教练预约明细表中的学员预约标识记录标记（0：表示空，1：表示存在记录）
	private String reservationFieldId13;// 教练预约明细表中的学员预约标识记录标记（0：表示空，1：表示存在记录）
	private String reservationFieldId14;// 教练预约明细表中的学员预约标识记录标记（0：表示空，1：表示存在记录）
	private String reservationFieldId15;// 教练预约明细表中的学员预约标识记录标记（0：表示空，1：表示存在记录）
	private String reservationFieldId16;// 教练预约明细表中的学员预约标识记录标记（0：表示空，1：表示存在记录）
	private String reservationFieldId17;// 教练预约明细表中的学员预约标识记录标记（0：表示空，1：表示存在记录）
	private String reservationFieldId18;// 教练预约明细表中的学员预约标识记录标记（0：表示空，1：表示存在记录）
	private String reservationFieldId19;// 教练预约明细表中的学员预约标识记录标记（0：表示空，1：表示存在记录）
	private String reservationFieldId20;// 教练预约明细表中的学员预约标识记录标记（0：表示空，1：表示存在记录）
	private String reservationFieldId21;// 教练预约明细表中的学员预约标识记录标记（0：表示空，1：表示存在记录）

	private Date reservationDateTemp;// 预约日期

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTrainTime() {
		return trainTime;
	}

	public String getTrainerIds() {
		return trainerIds;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Integer getTrainerArrangeFlag() {
		return trainerArrangeFlag;
	}

	public void setTrainerArrangeFlag(Integer trainerArrangeFlag) {
		this.trainerArrangeFlag = trainerArrangeFlag;
	}

	public void setTrainerIds(String trainerIds) {
		this.trainerIds = trainerIds;
	}

	public String getTrainerName() {
		return trainerName;
	}

	public String getTrainerReservationDetailId() {
		return trainerReservationDetailId;
	}

	public void setTrainerReservationDetailId(String trainerReservationDetailId) {
		this.trainerReservationDetailId = trainerReservationDetailId;
	}

	public Integer getTrainerType() {
		return trainerType;
	}

	public void setTrainerType(Integer trainerType) {
		this.trainerType = trainerType;
	}

	public String getArrangeId() {
		return arrangeId;
	}

	public void setArrangeId(String arrangeId) {
		this.arrangeId = arrangeId;
	}

	public Integer getCancelAll() {
		return cancelAll;
	}

	public String getTrainerCodeNo() {
		return trainerCodeNo;
	}

	public void setTrainerCodeNo(String trainerCodeNo) {
		this.trainerCodeNo = trainerCodeNo;
	}

	public void setCancelAll(Integer cancelAll) {
		this.cancelAll = cancelAll;
	}

	public Date getStartArrangeDate() {
		return startArrangeDate;
	}

	public Integer getArrangeType() {
		return arrangeType;
	}

	public void setArrangeType(Integer arrangeType) {
		this.arrangeType = arrangeType;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public void setStartArrangeDate(Date startArrangeDate) {
		this.startArrangeDate = startArrangeDate;
	}

	public Date getEndArrangeDate() {
		return endArrangeDate;
	}

	public void setEndArrangeDate(Date endArrangeDate) {
		this.endArrangeDate = endArrangeDate;
	}

	public Integer getTeachingType() {
		return teachingType;
	}

	public void setTeachingType(Integer teachingType) {
		this.teachingType = teachingType;
	}

	public Integer getWorkingType() {
		return workingType;
	}

	public Date getLastArrangeDate() {
		return lastArrangeDate;
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

	public String getTrainerReservationId() {
		return trainerReservationId;
	}

	public void setTrainerReservationId(String trainerReservationId) {
		this.trainerReservationId = trainerReservationId;
	}

	public Integer getItem8TrainContent() {
		return item8TrainContent;
	}

	public void setItem8TrainContent(Integer item8TrainContent) {
		this.item8TrainContent = item8TrainContent;
	}

	public Integer getItem9TrainContent() {
		return item9TrainContent;
	}

	public void setItem9TrainContent(Integer item9TrainContent) {
		this.item9TrainContent = item9TrainContent;
	}

	public Integer getItem10TrainContent() {
		return item10TrainContent;
	}

	public void setItem10TrainContent(Integer item10TrainContent) {
		this.item10TrainContent = item10TrainContent;
	}

	public Integer getItem11TrainContent() {
		return item11TrainContent;
	}

	public void setItem11TrainContent(Integer item11TrainContent) {
		this.item11TrainContent = item11TrainContent;
	}

	public Integer getItem12TrainContent() {
		return item12TrainContent;
	}

	public void setItem12TrainContent(Integer item12TrainContent) {
		this.item12TrainContent = item12TrainContent;
	}

	public Integer getItem13TrainContent() {
		return item13TrainContent;
	}

	public void setItem13TrainContent(Integer item13TrainContent) {
		this.item13TrainContent = item13TrainContent;
	}

	public Integer getItem14TrainContent() {
		return item14TrainContent;
	}

	public void setItem14TrainContent(Integer item14TrainContent) {
		this.item14TrainContent = item14TrainContent;
	}

	public Integer getItem15TrainContent() {
		return item15TrainContent;
	}

	public void setItem15TrainContent(Integer item15TrainContent) {
		this.item15TrainContent = item15TrainContent;
	}

	public Integer getItem16TrainContent() {
		return item16TrainContent;
	}

	public void setItem16TrainContent(Integer item16TrainContent) {
		this.item16TrainContent = item16TrainContent;
	}

	public Integer getItem17TrainContent() {
		return item17TrainContent;
	}

	public void setItem17TrainContent(Integer item17TrainContent) {
		this.item17TrainContent = item17TrainContent;
	}

	public Integer getItem18TrainContent() {
		return item18TrainContent;
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

	public Integer getItemTime19flag() {
		return ItemTime19flag;
	}

	public void setItemTime19flag(Integer itemTime19flag) {
		ItemTime19flag = itemTime19flag;
	}

	public Integer getItemTime20flag() {
		return ItemTime20flag;
	}

	public void setItemTime20flag(Integer itemTime20flag) {
		ItemTime20flag = itemTime20flag;
	}

	public Integer getItemTime21flag() {
		return ItemTime21flag;
	}

	public void setItemTime21flag(Integer itemTime21flag) {
		ItemTime21flag = itemTime21flag;
	}

	public Integer getItem19TrainContent() {
		return item19TrainContent;
	}

	public void setItem19TrainContent(Integer item19TrainContent) {
		this.item19TrainContent = item19TrainContent;
	}

	public Integer getItem20TrainContent() {
		return item20TrainContent;
	}

	public void setItem20TrainContent(Integer item20TrainContent) {
		this.item20TrainContent = item20TrainContent;
	}

	public Integer getItem21TrainContent() {
		return item21TrainContent;
	}

	public void setItem21TrainContent(Integer item21TrainContent) {
		this.item21TrainContent = item21TrainContent;
	}

	public String getReservationFieldId19() {
		return reservationFieldId19;
	}

	public void setReservationFieldId19(String reservationFieldId19) {
		this.reservationFieldId19 = reservationFieldId19;
	}

	public String getReservationFieldId20() {
		return reservationFieldId20;
	}

	public void setReservationFieldId20(String reservationFieldId20) {
		this.reservationFieldId20 = reservationFieldId20;
	}

	public String getReservationFieldId21() {
		return reservationFieldId21;
	}

	public void setReservationFieldId21(String reservationFieldId21) {
		this.reservationFieldId21 = reservationFieldId21;
	}

	public void setItem18TrainContent(Integer item18TrainContent) {
		this.item18TrainContent = item18TrainContent;
	}

	public String getReservationFieldId8() {
		return reservationFieldId8;
	}

	public void setReservationFieldId8(String reservationFieldId8) {
		this.reservationFieldId8 = reservationFieldId8;
	}

	public String getReservationFieldId9() {
		return reservationFieldId9;
	}

	public void setReservationFieldId9(String reservationFieldId9) {
		this.reservationFieldId9 = reservationFieldId9;
	}

	public String getReservationFieldId10() {
		return reservationFieldId10;
	}

	public void setReservationFieldId10(String reservationFieldId10) {
		this.reservationFieldId10 = reservationFieldId10;
	}

	public String getReservationFieldId11() {
		return reservationFieldId11;
	}

	public void setReservationFieldId11(String reservationFieldId11) {
		this.reservationFieldId11 = reservationFieldId11;
	}

	public String getReservationFieldId12() {
		return reservationFieldId12;
	}

	public void setReservationFieldId12(String reservationFieldId12) {
		this.reservationFieldId12 = reservationFieldId12;
	}

	public String getReservationFieldId13() {
		return reservationFieldId13;
	}

	public void setReservationFieldId13(String reservationFieldId13) {
		this.reservationFieldId13 = reservationFieldId13;
	}

	public String getReservationFieldId14() {
		return reservationFieldId14;
	}

	public void setReservationFieldId14(String reservationFieldId14) {
		this.reservationFieldId14 = reservationFieldId14;
	}

	public String getReservationFieldId15() {
		return reservationFieldId15;
	}

	public void setReservationFieldId15(String reservationFieldId15) {
		this.reservationFieldId15 = reservationFieldId15;
	}

	public String getReservationFieldId16() {
		return reservationFieldId16;
	}

	public void setReservationFieldId16(String reservationFieldId16) {
		this.reservationFieldId16 = reservationFieldId16;
	}

	public String getReservationFieldId17() {
		return reservationFieldId17;
	}

	public void setReservationFieldId17(String reservationFieldId17) {
		this.reservationFieldId17 = reservationFieldId17;
	}

	public String getReservationFieldId18() {
		return reservationFieldId18;
	}

	public void setReservationFieldId18(String reservationFieldId18) {
		this.reservationFieldId18 = reservationFieldId18;
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

	public Integer getItemTime8flag() {
		return ItemTime8flag;
	}

	public void setItemTime8flag(Integer itemTime8flag) {
		ItemTime8flag = itemTime8flag;
	}

	public Integer getItemTime9flag() {
		return ItemTime9flag;
	}

	public void setItemTime9flag(Integer itemTime9flag) {
		ItemTime9flag = itemTime9flag;
	}

	public Integer getItemTime10flag() {
		return ItemTime10flag;
	}

	public void setItemTime10flag(Integer itemTime10flag) {
		ItemTime10flag = itemTime10flag;
	}

	public Integer getItemTime11flag() {
		return ItemTime11flag;
	}

	public void setItemTime11flag(Integer itemTime11flag) {
		ItemTime11flag = itemTime11flag;
	}

	public Integer getItemTime12flag() {
		return ItemTime12flag;
	}

	public void setItemTime12flag(Integer itemTime12flag) {
		ItemTime12flag = itemTime12flag;
	}

	public Integer getItemTime13flag() {
		return ItemTime13flag;
	}

	public void setItemTime13flag(Integer itemTime13flag) {
		ItemTime13flag = itemTime13flag;
	}

	public Integer getItemTime14flag() {
		return ItemTime14flag;
	}

	public void setItemTime14flag(Integer itemTime14flag) {
		ItemTime14flag = itemTime14flag;
	}

	public Integer getItemTime15flag() {
		return ItemTime15flag;
	}

	public void setItemTime15flag(Integer itemTime15flag) {
		ItemTime15flag = itemTime15flag;
	}

	public Integer getItemTime16flag() {
		return ItemTime16flag;
	}

	public void setItemTime16flag(Integer itemTime16flag) {
		ItemTime16flag = itemTime16flag;
	}

	public Integer getItemTime17flag() {
		return ItemTime17flag;
	}

	public void setItemTime17flag(Integer itemTime17flag) {
		ItemTime17flag = itemTime17flag;
	}

	public Integer getItemTime18flag() {
		return ItemTime18flag;
	}

	public void setItemTime18flag(Integer itemTime18flag) {
		ItemTime18flag = itemTime18flag;
	}

	public Integer getSelectAll() {
		return selectAll;
	}

	public void setSelectAll(Integer selectAll) {
		this.selectAll = selectAll;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setLastArrangeDate(Date lastArrangeDate) {
		this.lastArrangeDate = lastArrangeDate;
	}

	public Date getLastArrangeDateStart() {
		return lastArrangeDateStart;
	}

	public void setLastArrangeDateStart(Date lastArrangeDateStart) {
		this.lastArrangeDateStart = lastArrangeDateStart;
	}

	public Date getLastArrangeDateEnd() {
		return lastArrangeDateEnd;
	}

	public void setLastArrangeDateEnd(Date lastArrangeDateEnd) {
		this.lastArrangeDateEnd = lastArrangeDateEnd;
	}

	public void setWorkingType(Integer workingType) {
		this.workingType = workingType;
	}

	public String getNoArrangeDates() {
		return noArrangeDates;
	}

	public void setNoArrangeDates(String noArrangeDates) {
		this.noArrangeDates = noArrangeDates;
	}

	public String getNo() {
		return No;
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

	public void setNo(String no) {
		No = no;
	}

	public String getSchoolAreaId() {
		return schoolAreaId;
	}

	public String getSchoolArea() {
		return schoolArea;
	}

	public Date getFirstArrangeDate() {
		return firstArrangeDate;
	}

	public void setFirstArrangeDate(Date firstArrangeDate) {
		this.firstArrangeDate = firstArrangeDate;
	}

	public Date getFirstArrangeDateStart() {
		return firstArrangeDateStart;
	}

	public void setFirstArrangeDateStart(Date firstArrangeDateStart) {
		this.firstArrangeDateStart = firstArrangeDateStart;
	}

	public Date getFirstArrangeDateEnd() {
		return firstArrangeDateEnd;
	}

	public void setFirstArrangeDateEnd(Date firstArrangeDateEnd) {
		this.firstArrangeDateEnd = firstArrangeDateEnd;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	public void setTrainerId(String trainerId) {
		this.trainerId = trainerId;
	}

	public void setTrainerSchoolAreaName(String trainerSchoolAreaName) {
		this.trainerSchoolAreaName = trainerSchoolAreaName;
	}

	public void setTrainerIdentity(String trainerIdentity) {
		this.trainerIdentity = trainerIdentity;
	}

	public void setTrainerDriverLicenseId(String trainerDriverLicenseId) {
		this.trainerDriverLicenseId = trainerDriverLicenseId;
	}

	public void setTrainerDriverType(Integer trainerDriverType) {
		this.trainerDriverType = trainerDriverType;
	}

	public void setTrainerDriverCarType(String trainerDriverCarType) {
		this.trainerDriverCarType = trainerDriverCarType;
	}

	public void setTrainerPhone(String trainerPhone) {
		this.trainerPhone = trainerPhone;
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

	public String getWeekth() {
		return weekth;
	}

	public void setWeekth(String weekth) {
		this.weekth = weekth;
	}

	public void setTrainTime(String trainTime) {
		this.trainTime = trainTime;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getTrainerTechingType() {
		return trainerTechingType;
	}

	public void setTrainerTechingType(String trainerTechingType) {
		this.trainerTechingType = trainerTechingType;
	}

	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}

	
	public Integer getItemTime7() {
		return ItemTime7;
	}

	public void setItemTime7(Integer itemTime7) {
		ItemTime7 = itemTime7;
	}

	public Integer getItemTime7flag() {
		return ItemTime7flag;
	}

	public void setItemTime7flag(Integer itemTime7flag) {
		ItemTime7flag = itemTime7flag;
	}

	public Integer getItem7TrainContent() {
		return item7TrainContent;
	}

	public void setItem7TrainContent(Integer item7TrainContent) {
		this.item7TrainContent = item7TrainContent;
	}

	public String getReservationFieldId7() {
		return reservationFieldId7;
	}

	public void setReservationFieldId7(String reservationFieldId7) {
		this.reservationFieldId7 = reservationFieldId7;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getTrainerId() {
		return trainerId;
	}

	public String getTrainerSchoolAreaName() {
		return trainerSchoolAreaName;
	}

	public String getTrainerIdentity() {
		return trainerIdentity;
	}

	public String getTrainerDriverLicenseId() {
		return trainerDriverLicenseId;
	}

	public Date getArrangeDate() {
		return arrangeDate;
	}

	public void setArrangeDate(Date arrangeDate) {
		this.arrangeDate = arrangeDate;
	}

	public Integer getTrainerDriverType() {
		return trainerDriverType;
	}

	public String getTrainerDriverCarType() {
		return trainerDriverCarType;
	}

	public String getTrainerPhone() {
		return trainerPhone;
	}

	public Date getReservationDateTemp() {
		return reservationDateTemp;
	}

	public void setReservationDateTemp(Date reservationDateTemp) {
		this.reservationDateTemp = reservationDateTemp;
	}

}

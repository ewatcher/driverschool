package com.tuocheng.pageModel.demo;

import java.util.Date;

import com.tuocheng.pageModel.base.StudentPageModel;

/**
 * 教练预约明细信息实体类(action专用)
 */

public class TrainerReservationDetail extends StudentPageModel {
	/**
	 * 序列化标识
	 */
	private static final long serialVersionUID = 7008838416713932866L;
	private String id;// 标识
	private Date reservationDate;// 预约日期
	private Integer reservationType;// 预约类型：1.电车，2.模拟　3.理论　4.五项　5.路训

	private String item7Studentid;// 该时段学员标识（表示7：00－8：00）
	private Integer item7TrainContent;// 该时段训练类型
	private String item8Studentid;// 该时段学员标识（表示8：00－9：00）
	private Integer item8TrainContent;// 该时段训练类型
	private String item9Studentid;// 该时段学员标识（表示9：00－10：00）
	private Integer item9TrainContent;// 该时段训练类型
	private String item10Studentid;// 该时段学员标识（表示10：00－11：00）
	private Integer item10TrainContent;// 该时段训练类型
	private String item11Studentid;// 该时段学员标识（表示11：00－12：00）
	private Integer item11TrainContent;// 该时段训练类型
	private String item12Studentid;// 该时段学员标识（表示12：00－13：00）
	private Integer item12TrainContent;// 该时段训练类型
	private String item13Studentid;// 该时段学员标识（表示13：00－14：00）
	private Integer item13TrainContent;// 该时段训练类型
	private String item14Studentid;// 该时段学员标识（表示14：00－15：00）
	private Integer item14TrainContent;// 该时段训练类型
	private String item15Studentid;// 该时段学员标识（表示15：00－16：00）
	private Integer item15TrainContent;// 该时段训练类型
	private String item16Studentid;// 该时段学员标识（表示16：00－17：00）
	private Integer item16TrainContent;// 该时段训练类型
	private String item17Studentid;// 该时段学员标识（表示17：00－18：00）
	private Integer item17TrainContent;// 该时段训练类型
	private String item18Studentid;// 该时段学员标识（表示18：00－19：00）
	private Integer item18TrainContent;// 该时段训练类型
	private String item19Studentid;// 该时段学员标识（表示18：00－19：00）
	private Integer item19TrainContent;// 该时段训练类型
	private String item20Studentid;// 该时段学员标识（表示18：00－19：00）
	private Integer item20TrainContent;// 该时段训练类型
	private String item21Studentid;// 该时段学员标识（表示18：00－19：00）
	private Integer item21TrainContent;// 该时段训练类型
	private Integer fiveItemTotal;// 五项课时总数
	private Integer raodExamTotal;// 路考课时总数

	private String trainerReservationId;//
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
	private String reservationFieldId18;// 教练预约明细表中的学员预约标识记录标记（0：表示空，1：表示存在记录
	private String reservationFieldId19;// 教练预约明细表中的学员预约标识记录标记（0：表示空，1：表示存在记录）
	private String reservationFieldId20;// 教练预约明细表中的学员预约标识记录标记（0：表示空，1：表示存在记录）
	private String reservationFieldId21;// 教练预约明细表中的学员预约标识记录标记（0：表示空，1：表示存在记录
	private Integer reservation7MaxTotal;// 統計該時段最高允許多個人預約，默認為0（五項，路訓：1，電車:3人，模擬：6人）
	private Integer reservation8MaxTotal;// 統計該時段最高允許多個人預約，默認為0（五項，路訓：1，電車:3人，模擬：6人）
	private Integer reservation9MaxTotal;// 統計該時段最高允許多個人預約，默認為0（五項，路訓：1，電車:3人，模擬：6人）
	private Integer reservation10MaxTotal;// 統計該時段最高允許多個人預約，默認為0（五項，路訓：1，電車:3人，模擬：6人）
	private Integer reservation11MaxTotal;// 統計該時段最高允許多個人預約，默認為0（五項，路訓：1，電車:3人，模擬：6人）
	private Integer reservation12MaxTotal;// 統計該時段最高允許多個人預約，默認為0（五項，路訓：1，電車:3人，模擬：6人）
	private Integer reservation13MaxTotal;// 統計該時段最高允許多個人預約，默認為0（五項，路訓：1，電車:3人，模擬：6人）
	private Integer reservation14MaxTotal;// 統計該時段最高允許多個人預約，默認為0（五項，路訓：1，電車:3人，模擬：6人）
	private Integer reservation15MaxTotal;// 統計該時段最高允許多個人預約，默認為0（五項，路訓：1，電車:3人，模擬：6人）
	private Integer reservation16MaxTotal;// 統計該時段最高允許多個人預約，默認為0（五項，路訓：1，電車:3人，模擬：6人）
	private Integer reservation17MaxTotal;// 統計該時段最高允許多個人預約，默認為0（五項，路訓：1，電車:3人，模擬：6人）
	private Integer reservation18MaxTotal;// 統計該時段最高允許多個人預約，默認為0（五項，路訓：1，電車:3人，模擬：6人）
	private Integer reservation19MaxTotal;// 統計該時段最高允許多個人預約，默認為0（五項，路訓：1，電車:3人，模擬：6人）
	private Integer reservation20MaxTotal;// 統計該時段最高允許多個人預約，默認為0（五項，路訓：1，電車:3人，模擬：6人）
	private Integer reservation21MaxTotal;// 統計該時段最高允許多個人預約，默認為0（五項，路訓：1，電車:3人，模擬：6人）

	private String ids;
	private String trainerSchoolArear;
	private String trainerName;
	private String trainerIdentityId;
	private String trainerPhone;
	private String trainerId;
	private Integer reservationState;// 预约状态(冗余字段：统计有郊预约数据)
	private String studentId;
	private String arrangeId;// 该字段用于删除排班信息

	private Date reservationDateBegin;
	private Date reservationDateEnd;
	private String schoolArea;

	
	
	public Integer getReservation7MaxTotal() {
		return reservation7MaxTotal;
	}

	public void setReservation7MaxTotal(Integer reservation7MaxTotal) {
		this.reservation7MaxTotal = reservation7MaxTotal;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getItem8Studentid() {
		return item8Studentid;
	}

	public void setItem8Studentid(String item8Studentid) {
		this.item8Studentid = item8Studentid;
	}

	public Integer getItem8TrainContent() {
		return item8TrainContent;
	}

	public void setItem8TrainContent(Integer item8TrainContent) {
		this.item8TrainContent = item8TrainContent;
	}

	public Integer getReservationType() {
		return reservationType;
	}

	public void setReservationType(Integer reservationType) {
		this.reservationType = reservationType;
	}

	public String getItem9Studentid() {
		return item9Studentid;
	}

	public String getArrangeId() {
		return arrangeId;
	}

	public Integer getItem21TrainContent() {
		return item21TrainContent;
	}

	public void setItem21TrainContent(Integer item21TrainContent) {
		this.item21TrainContent = item21TrainContent;
	}

	public Integer getReservationState() {
		return reservationState;
	}

	public void setReservationState(Integer reservationState) {
		this.reservationState = reservationState;
	}

	public void setArrangeId(String arrangeId) {
		this.arrangeId = arrangeId;
	}

	public Integer getReservation8MaxTotal() {
		return reservation8MaxTotal;
	}

	public void setReservation8MaxTotal(Integer reservation8MaxTotal) {
		this.reservation8MaxTotal = reservation8MaxTotal;
	}

	public Integer getReservation9MaxTotal() {
		return reservation9MaxTotal;
	}

	public void setReservation9MaxTotal(Integer reservation9MaxTotal) {
		this.reservation9MaxTotal = reservation9MaxTotal;
	}

	public Integer getReservation10MaxTotal() {
		return reservation10MaxTotal;
	}

	public void setReservation10MaxTotal(Integer reservation10MaxTotal) {
		this.reservation10MaxTotal = reservation10MaxTotal;
	}

	public Integer getReservation11MaxTotal() {
		return reservation11MaxTotal;
	}

	public void setReservation11MaxTotal(Integer reservation11MaxTotal) {
		this.reservation11MaxTotal = reservation11MaxTotal;
	}

	public Integer getReservation12MaxTotal() {
		return reservation12MaxTotal;
	}

	public void setReservation12MaxTotal(Integer reservation12MaxTotal) {
		this.reservation12MaxTotal = reservation12MaxTotal;
	}

	public Integer getReservation13MaxTotal() {
		return reservation13MaxTotal;
	}

	public void setReservation13MaxTotal(Integer reservation13MaxTotal) {
		this.reservation13MaxTotal = reservation13MaxTotal;
	}

	public Integer getReservation14MaxTotal() {
		return reservation14MaxTotal;
	}

	public void setReservation14MaxTotal(Integer reservation14MaxTotal) {
		this.reservation14MaxTotal = reservation14MaxTotal;
	}

	public Integer getReservation15MaxTotal() {
		return reservation15MaxTotal;
	}

	public void setReservation15MaxTotal(Integer reservation15MaxTotal) {
		this.reservation15MaxTotal = reservation15MaxTotal;
	}

	public Integer getReservation16MaxTotal() {
		return reservation16MaxTotal;
	}

	public void setReservation16MaxTotal(Integer reservation16MaxTotal) {
		this.reservation16MaxTotal = reservation16MaxTotal;
	}

	public Integer getReservation17MaxTotal() {
		return reservation17MaxTotal;
	}

	public void setReservation17MaxTotal(Integer reservation17MaxTotal) {
		this.reservation17MaxTotal = reservation17MaxTotal;
	}

	public Integer getReservation18MaxTotal() {
		return reservation18MaxTotal;
	}

	public void setReservation18MaxTotal(Integer reservation18MaxTotal) {
		this.reservation18MaxTotal = reservation18MaxTotal;
	}

	public Integer getReservation19MaxTotal() {
		return reservation19MaxTotal;
	}

	public void setReservation19MaxTotal(Integer reservation19MaxTotal) {
		this.reservation19MaxTotal = reservation19MaxTotal;
	}

	public Integer getReservation20MaxTotal() {
		return reservation20MaxTotal;
	}

	public void setReservation20MaxTotal(Integer reservation20MaxTotal) {
		this.reservation20MaxTotal = reservation20MaxTotal;
	}

	public Integer getReservation21MaxTotal() {
		return reservation21MaxTotal;
	}

	public void setReservation21MaxTotal(Integer reservation21MaxTotal) {
		this.reservation21MaxTotal = reservation21MaxTotal;
	}

	public void setItem9Studentid(String item9Studentid) {
		this.item9Studentid = item9Studentid;
	}

	public String getItem19Studentid() {
		return item19Studentid;
	}

	public void setItem19Studentid(String item19Studentid) {
		this.item19Studentid = item19Studentid;
	}

	public Integer getItem19TrainContent() {
		return item19TrainContent;
	}

	public void setItem19TrainContent(Integer item19TrainContent) {
		this.item19TrainContent = item19TrainContent;
	}

	public String getItem20Studentid() {
		return item20Studentid;
	}

	public void setItem20Studentid(String item20Studentid) {
		this.item20Studentid = item20Studentid;
	}

	public Integer getItem20TrainContent() {
		return item20TrainContent;
	}

	public void setItem20TrainContent(Integer item20TrainContent) {
		this.item20TrainContent = item20TrainContent;
	}

	public String getItem21Studentid() {
		return item21Studentid;
	}

	public void setItem21Studentid(String item21Studentid) {
		this.item21Studentid = item21Studentid;
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

	public Integer getItem9TrainContent() {
		return item9TrainContent;
	}

	public String getItem18Studentid() {
		return item18Studentid;
	}

	public void setItem18Studentid(String item18Studentid) {
		this.item18Studentid = item18Studentid;
	}

	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	public Integer getItem18TrainContent() {
		return item18TrainContent;
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

	public Date getReservationDateBegin() {
		return reservationDateBegin;
	}

	public void setReservationDateBegin(Date reservationDateBegin) {
		this.reservationDateBegin = reservationDateBegin;
	}

	public Date getReservationDateEnd() {
		return reservationDateEnd;
	}

	public void setReservationDateEnd(Date reservationDateEnd) {
		this.reservationDateEnd = reservationDateEnd;
	}

	public void setItem9TrainContent(Integer item9TrainContent) {
		this.item9TrainContent = item9TrainContent;
	}

	public String getItem10Studentid() {
		return item10Studentid;
	}

	public void setItem10Studentid(String item10Studentid) {
		this.item10Studentid = item10Studentid;
	}

	public Integer getItem10TrainContent() {
		return item10TrainContent;
	}

	public void setItem10TrainContent(Integer item10TrainContent) {
		this.item10TrainContent = item10TrainContent;
	}

	public String getItem11Studentid() {
		return item11Studentid;
	}

	public void setItem11Studentid(String item11Studentid) {
		this.item11Studentid = item11Studentid;
	}

	public Integer getItem11TrainContent() {
		return item11TrainContent;
	}

	public void setItem11TrainContent(Integer item11TrainContent) {
		this.item11TrainContent = item11TrainContent;
	}

	public String getItem12Studentid() {
		return item12Studentid;
	}

	public void setItem12Studentid(String item12Studentid) {
		this.item12Studentid = item12Studentid;
	}

	public Integer getItem12TrainContent() {
		return item12TrainContent;
	}

	public void setItem12TrainContent(Integer item12TrainContent) {
		this.item12TrainContent = item12TrainContent;
	}

	public String getItem13Studentid() {
		return item13Studentid;
	}

	public void setItem13Studentid(String item13Studentid) {
		this.item13Studentid = item13Studentid;
	}

	public Integer getItem13TrainContent() {
		return item13TrainContent;
	}

	public void setItem13TrainContent(Integer item13TrainContent) {
		this.item13TrainContent = item13TrainContent;
	}

	public String getItem14Studentid() {
		return item14Studentid;
	}

	public void setItem14Studentid(String item14Studentid) {
		this.item14Studentid = item14Studentid;
	}

	public Integer getItem14TrainContent() {
		return item14TrainContent;
	}

	public void setItem14TrainContent(Integer item14TrainContent) {
		this.item14TrainContent = item14TrainContent;
	}

	public String getItem15Studentid() {
		return item15Studentid;
	}

	public void setItem15Studentid(String item15Studentid) {
		this.item15Studentid = item15Studentid;
	}

	public Integer getItem15TrainContent() {
		return item15TrainContent;
	}

	public void setItem15TrainContent(Integer item15TrainContent) {
		this.item15TrainContent = item15TrainContent;
	}

	public String getItem16Studentid() {
		return item16Studentid;
	}

	public void setItem16Studentid(String item16Studentid) {
		this.item16Studentid = item16Studentid;
	}

	public Integer getItem16TrainContent() {
		return item16TrainContent;
	}

	public void setItem16TrainContent(Integer item16TrainContent) {
		this.item16TrainContent = item16TrainContent;
	}

	public String getItem17Studentid() {
		return item17Studentid;
	}

	public void setItem17Studentid(String item17Studentid) {
		this.item17Studentid = item17Studentid;
	}

	public Integer getItem17TrainContent() {
		return item17TrainContent;
	}

	public void setItem17TrainContent(Integer item17TrainContent) {
		this.item17TrainContent = item17TrainContent;
	}

	public Integer getFiveItemTotal() {
		return fiveItemTotal;
	}

	public void setFiveItemTotal(Integer fiveItemTotal) {
		this.fiveItemTotal = fiveItemTotal;
	}

	public Integer getRaodExamTotal() {
		return raodExamTotal;
	}

	public void setRaodExamTotal(Integer raodExamTotal) {
		this.raodExamTotal = raodExamTotal;
	}

	public String getTrainerReservationId() {
		return trainerReservationId;
	}

	public void setTrainerReservationId(String trainerReservationId) {
		this.trainerReservationId = trainerReservationId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getTrainerSchoolArear() {
		return trainerSchoolArear;
	}

	public void setTrainerSchoolArear(String trainerSchoolArear) {
		this.trainerSchoolArear = trainerSchoolArear;
	}

	public String getTrainerName() {
		return trainerName;
	}

	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}

	public String getTrainerIdentityId() {
		return trainerIdentityId;
	}

	public void setTrainerIdentityId(String trainerIdentityId) {
		this.trainerIdentityId = trainerIdentityId;
	}

	public String getTrainerPhone() {
		return trainerPhone;
	}

	public void setTrainerPhone(String trainerPhone) {
		this.trainerPhone = trainerPhone;
	}

	public String getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(String trainerId) {
		this.trainerId = trainerId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getItem7Studentid() {
		return item7Studentid;
	}

	public void setItem7Studentid(String item7Studentid) {
		this.item7Studentid = item7Studentid;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}

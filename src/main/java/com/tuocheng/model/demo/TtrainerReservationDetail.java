package com.tuocheng.model.demo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.alibaba.fastjson.JSON;

/**
 * 教练预约明细信息实体类(DAO专用)
 * 
 * @author TC_NONGFENG
 * 
 */
@Entity
@Table(name = "tb_trainerReservationDetail", schema = "")
public class TtrainerReservationDetail implements Serializable {

	/**
	 * 序列化标识
	 */
	private static final long serialVersionUID = 1L;

	public static final Integer ItemTrainContent_REST = 5;
	public static final Integer ItemTrainContent_ROAD = 4;
	public static final Integer ItemTrainContent_FIVE = 3;

	private String id;// 标识
	private Date reservationDate;// 预约日期
	private Integer reservationType;// 预约类型：1.电车，2.模拟　3.五项　4.路训
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

	private String trainerId;// 教练标识(冗余字段)
	private String arrangeId;// 该字段用于删除排班信息
	private Integer reservationState;// 预约状态(冗余字段：统计有郊预约数据)

	private TtrainerReservation trainerReservation;// 1个教练拥有多条教练预约明细信息

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
	private String schoolArea;

	public TtrainerReservationDetail() {
	}

	public TtrainerReservationDetail(String id, Date reservationDate,
			Integer reservationType, String item8Studentid,
			Integer item8TrainContent, String item9Studentid,
			Integer item9TrainContent, String item10Studentid,
			Integer item10TrainContent, String item11Studentid,
			Integer item11TrainContent, String item12Studentid,
			Integer item12TrainContent, String item13Studentid,
			Integer item13TrainContent, String item14Studentid,
			Integer item14TrainContent, String item15Studentid,
			Integer item15TrainContent, String item16Studentid,
			Integer item16TrainContent, String item17Studentid,
			Integer item17TrainContent, String item18Studentid,
			Integer item18TrainContent, String item19Studentid,
			Integer item19TrainContent, String item20Studentid,
			Integer item20TrainContent, String item21Studentid,
			Integer item21TrainContent, Integer fiveItemTotal,
			Integer raodExamTotal, String trainerId, String arrangeId,
			Integer reservationState, TtrainerReservation trainerReservation,
			String reservationFieldId8, String reservationFieldId9,
			String reservationFieldId10, String reservationFieldId11,
			String reservationFieldId12, String reservationFieldId13,
			String reservationFieldId14, String reservationFieldId15,
			String reservationFieldId16, String reservationFieldId17,
			String reservationFieldId18, String reservationFieldId19,
			String reservationFieldId20, String reservationFieldId21,
			Integer reservation8MaxTotal, Integer reservation9MaxTotal,
			Integer reservation10MaxTotal, Integer reservation11MaxTotal,
			Integer reservation12MaxTotal, Integer reservation13MaxTotal,
			Integer reservation14MaxTotal, Integer reservation15MaxTotal,
			Integer reservation16MaxTotal, Integer reservation17MaxTotal,
			Integer reservation18MaxTotal, Integer reservation19MaxTotal,
			Integer reservation20MaxTotal, Integer reservation21MaxTotal,
			String schoolArea) {
		super();
		this.id = id;
		this.reservationDate = reservationDate;
		this.reservationType = reservationType;
		this.item8Studentid = item8Studentid;
		this.item8TrainContent = item8TrainContent;
		this.item9Studentid = item9Studentid;
		this.item9TrainContent = item9TrainContent;
		this.item10Studentid = item10Studentid;
		this.item10TrainContent = item10TrainContent;
		this.item11Studentid = item11Studentid;
		this.item11TrainContent = item11TrainContent;
		this.item12Studentid = item12Studentid;
		this.item12TrainContent = item12TrainContent;
		this.item13Studentid = item13Studentid;
		this.item13TrainContent = item13TrainContent;
		this.item14Studentid = item14Studentid;
		this.item14TrainContent = item14TrainContent;
		this.item15Studentid = item15Studentid;
		this.item15TrainContent = item15TrainContent;
		this.item16Studentid = item16Studentid;
		this.item16TrainContent = item16TrainContent;
		this.item17Studentid = item17Studentid;
		this.item17TrainContent = item17TrainContent;
		this.item18Studentid = item18Studentid;
		this.item18TrainContent = item18TrainContent;
		this.item19Studentid = item19Studentid;
		this.item19TrainContent = item19TrainContent;
		this.item20Studentid = item20Studentid;
		this.item20TrainContent = item20TrainContent;
		this.item21Studentid = item21Studentid;
		this.item21TrainContent = item21TrainContent;
		this.fiveItemTotal = fiveItemTotal;
		this.raodExamTotal = raodExamTotal;
		this.trainerId = trainerId;
		this.arrangeId = arrangeId;
		this.reservationState = reservationState;
		this.trainerReservation = trainerReservation;
		this.reservationFieldId8 = reservationFieldId8;
		this.reservationFieldId9 = reservationFieldId9;
		this.reservationFieldId10 = reservationFieldId10;
		this.reservationFieldId11 = reservationFieldId11;
		this.reservationFieldId12 = reservationFieldId12;
		this.reservationFieldId13 = reservationFieldId13;
		this.reservationFieldId14 = reservationFieldId14;
		this.reservationFieldId15 = reservationFieldId15;
		this.reservationFieldId16 = reservationFieldId16;
		this.reservationFieldId17 = reservationFieldId17;
		this.reservationFieldId18 = reservationFieldId18;
		this.reservationFieldId19 = reservationFieldId19;
		this.reservationFieldId20 = reservationFieldId20;
		this.reservationFieldId21 = reservationFieldId21;
		this.reservation8MaxTotal = reservation8MaxTotal;
		this.reservation9MaxTotal = reservation9MaxTotal;
		this.reservation10MaxTotal = reservation10MaxTotal;
		this.reservation11MaxTotal = reservation11MaxTotal;
		this.reservation12MaxTotal = reservation12MaxTotal;
		this.reservation13MaxTotal = reservation13MaxTotal;
		this.reservation14MaxTotal = reservation14MaxTotal;
		this.reservation15MaxTotal = reservation15MaxTotal;
		this.reservation16MaxTotal = reservation16MaxTotal;
		this.reservation17MaxTotal = reservation17MaxTotal;
		this.reservation18MaxTotal = reservation18MaxTotal;
		this.reservation19MaxTotal = reservation19MaxTotal;
		this.reservation20MaxTotal = reservation20MaxTotal;
		this.reservation21MaxTotal = reservation21MaxTotal;
		this.schoolArea = schoolArea;
	}

	@Id
	@Column(name = "id", nullable = false, length = 50)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "reservationDate",updatable=false, length = 7)
	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}
	
	@Column(name = "item7Studentid", length = 50)
	public String getItem7Studentid() {
		return item7Studentid;
	}

	public void setItem7Studentid(String item7Studentid) {
		this.item7Studentid = item7Studentid;
	}
	
	@Column(name = "item7TrainContent", length = 8)
	public Integer getItem7TrainContent() {
		return item7TrainContent;
	}

	public void setItem7TrainContent(Integer item7TrainContent) {
		this.item7TrainContent = item7TrainContent;
	}

	@Column(name = "item8Studentid", length = 50)
	public String getItem8Studentid() {
		return item8Studentid;
	}

	public void setItem8Studentid(String item8Studentid) {
		this.item8Studentid = item8Studentid;
	}

	@Column(name = "item8TrainContent", length = 8)
	public Integer getItem8TrainContent() {
		return item8TrainContent;
	}

	public void setItem8TrainContent(Integer item8TrainContent) {
		this.item8TrainContent = item8TrainContent;
	}

	@Column(name = "item9Studentid", length = 50)
	public String getItem9Studentid() {
		return item9Studentid;
	}

	public void setItem9Studentid(String item9Studentid) {
		this.item9Studentid = item9Studentid;
	}

	@Column(name = "item9TrainerContent", length = 8)
	public Integer getItem9TrainContent() {
		return item9TrainContent;
	}

	public void setItem9TrainContent(Integer item9TrainContent) {
		this.item9TrainContent = item9TrainContent;
	}

	@Column(name = "item10Studentid", length = 50)
	public String getItem10Studentid() {
		return item10Studentid;
	}

	public void setItem10Studentid(String item10Studentid) {
		this.item10Studentid = item10Studentid;
	}

	@Column(name = "item10TrainerContent", length = 8)
	public Integer getItem10TrainContent() {
		return item10TrainContent;
	}

	public void setItem10TrainContent(Integer item10TrainContent) {
		this.item10TrainContent = item10TrainContent;
	}

	@Column(name = "item11Studentid", length = 50)
	public String getItem11Studentid() {
		return item11Studentid;
	}

	public void setItem11Studentid(String item11Studentid) {
		this.item11Studentid = item11Studentid;
	}

	@Column(name = "item11TrainerContent", length = 8)
	public Integer getItem11TrainContent() {
		return item11TrainContent;
	}

	public void setItem11TrainContent(Integer item11TrainContent) {
		this.item11TrainContent = item11TrainContent;
	}

	@Column(name = "item12Studentid", length = 50)
	public String getItem12Studentid() {
		return item12Studentid;
	}

	public void setItem12Studentid(String item12Studentid) {
		this.item12Studentid = item12Studentid;
	}

	@Column(name = "item12TrainerContent", length = 8)
	public Integer getItem12TrainContent() {
		return item12TrainContent;
	}

	public void setItem12TrainContent(Integer item12TrainContent) {
		this.item12TrainContent = item12TrainContent;
	}

	@Column(name = "item13Studentid", length = 50)
	public String getItem13Studentid() {
		return item13Studentid;
	}

	public void setItem13Studentid(String item13Studentid) {
		this.item13Studentid = item13Studentid;
	}

	@Column(name = "item13TrainerContent", length = 8)
	public Integer getItem13TrainContent() {
		return item13TrainContent;
	}

	public void setItem13TrainContent(Integer item13TrainContent) {
		this.item13TrainContent = item13TrainContent;
	}

	@Column(name = "item14Studentid", length = 50)
	public String getItem14Studentid() {
		return item14Studentid;
	}

	public void setItem14Studentid(String item14Studentid) {
		this.item14Studentid = item14Studentid;
	}

	@Column(name = "item14TrainerContent", length = 8)
	public Integer getItem14TrainContent() {
		return item14TrainContent;
	}

	public void setItem14TrainContent(Integer item14TrainContent) {
		this.item14TrainContent = item14TrainContent;
	}

	@Column(name = "item15Studentid", length = 50)
	public String getItem15Studentid() {
		return item15Studentid;
	}

	public void setItem15Studentid(String item15Studentid) {
		this.item15Studentid = item15Studentid;
	}

	@Column(name = "item15TrainerContent", length = 8)
	public Integer getItem15TrainContent() {
		return item15TrainContent;
	}

	public void setItem15TrainContent(Integer item15TrainContent) {
		this.item15TrainContent = item15TrainContent;
	}

	@Column(name = "item16Studentid", length = 50)
	public String getItem16Studentid() {
		return item16Studentid;
	}

	public void setItem16Studentid(String item16Studentid) {
		this.item16Studentid = item16Studentid;
	}

	@Column(name = "item16TrainerContent", length = 8)
	public Integer getItem16TrainContent() {
		return item16TrainContent;
	}

	public void setItem16TrainContent(Integer item16TrainContent) {
		this.item16TrainContent = item16TrainContent;
	}

	@Column(name = "item17Studentid", length = 50)
	public String getItem17Studentid() {
		return item17Studentid;
	}

	public void setItem17Studentid(String item17Studentid) {
		this.item17Studentid = item17Studentid;
	}

	@Column(name = "item17TrainerContent", length = 8)
	public Integer getItem17TrainContent() {
		return item17TrainContent;
	}

	public void setItem17TrainContent(Integer item17TrainContent) {
		this.item17TrainContent = item17TrainContent;
	}

	@Column(name = "item18Studentid", length = 50)
	public String getItem18Studentid() {
		return item18Studentid;
	}

	public void setItem18Studentid(String item18Studentid) {
		this.item18Studentid = item18Studentid;
	}

	@Column(name = "item18TrainContent", length = 8)
	public Integer getItem18TrainContent() {
		return item18TrainContent;
	}

	public void setItem18TrainContent(Integer item18TrainContent) {
		this.item18TrainContent = item18TrainContent;
	}

	@Column(name = "fiveItemTotal", length = 8)
	public Integer getFiveItemTotal() {
		return fiveItemTotal;
	}

	public void setFiveItemTotal(Integer fiveItemTotal) {
		this.fiveItemTotal = fiveItemTotal;
	}

	@Column(name = "raodExamTotal", length = 8)
	public Integer getRaodExamTotal() {
		return raodExamTotal;
	}

	public void setRaodExamTotal(Integer raodExamTotal) {
		this.raodExamTotal = raodExamTotal;
	}

	@Column(name = "trainerId", length = 36)
	public String getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(String trainerId) {
		this.trainerId = trainerId;
	}
	
	@Column(name = "reservationFieldId7", length = 36)
	public String getReservationFieldId7() {
		return reservationFieldId7;
	}

	public void setReservationFieldId7(String reservationFieldId7) {
		this.reservationFieldId7 = reservationFieldId7;
	}

	@Column(name = "reservationFieldId8", length = 36)
	public String getReservationFieldId8() {
		return reservationFieldId8;
	}

	public void setReservationFieldId8(String reservationFieldId8) {
		this.reservationFieldId8 = reservationFieldId8;
	}

	@Column(name = "reservationFieldId9", length = 36)
	public String getReservationFieldId9() {
		return reservationFieldId9;
	}

	public void setReservationFieldId9(String reservationFieldId9) {
		this.reservationFieldId9 = reservationFieldId9;
	}

	@Column(name = "reservationFieldId10", length = 36)
	public String getReservationFieldId10() {
		return reservationFieldId10;
	}

	public void setReservationFieldId10(String reservationFieldId10) {
		this.reservationFieldId10 = reservationFieldId10;
	}

	@Column(name = "reservationFieldId11", length = 36)
	public String getReservationFieldId11() {
		return reservationFieldId11;
	}

	public void setReservationFieldId11(String reservationFieldId11) {
		this.reservationFieldId11 = reservationFieldId11;
	}

	@Column(name = "reservationFieldId12", length = 36)
	public String getReservationFieldId12() {
		return reservationFieldId12;
	}

	public void setReservationFieldId12(String reservationFieldId12) {
		this.reservationFieldId12 = reservationFieldId12;
	}

	@Column(name = "reservationFieldId13", length = 36)
	public String getReservationFieldId13() {
		return reservationFieldId13;
	}

	public void setReservationFieldId13(String reservationFieldId13) {
		this.reservationFieldId13 = reservationFieldId13;
	}

	@Column(name = "reservationFieldId14", length = 36)
	public String getReservationFieldId14() {
		return reservationFieldId14;
	}

	public void setReservationFieldId14(String reservationFieldId14) {
		this.reservationFieldId14 = reservationFieldId14;
	}

	@Column(name = "reservationFieldId15", length = 36)
	public String getReservationFieldId15() {
		return reservationFieldId15;
	}

	public void setReservationFieldId15(String reservationFieldId15) {
		this.reservationFieldId15 = reservationFieldId15;
	}

	@Column(name = "reservationFieldId16", length = 36)
	public String getReservationFieldId16() {
		return reservationFieldId16;
	}

	public void setReservationFieldId16(String reservationFieldId16) {
		this.reservationFieldId16 = reservationFieldId16;
	}

	@Column(name = "reservationFieldId17", length = 36)
	public String getReservationFieldId17() {
		return reservationFieldId17;
	}

	public void setReservationFieldId17(String reservationFieldId17) {
		this.reservationFieldId17 = reservationFieldId17;
	}

	@Column(name = "reservationFieldId18", length = 36)
	public String getReservationFieldId18() {
		return reservationFieldId18;
	}

	public void setReservationFieldId18(String reservationFieldId18) {
		this.reservationFieldId18 = reservationFieldId18;
	}

	@Column(name = "reservationType", length = 4)
	public Integer getReservationType() {
		return reservationType;
	}

	public void setReservationType(Integer reservationType) {
		this.reservationType = reservationType;
	}

	@Column(name = "arrangeId", length = 36)
	public String getArrangeId() {
		return arrangeId;
	}

	public void setArrangeId(String arrangeId) {
		this.arrangeId = arrangeId;
	}

	@Column(name = "schoolArea", length = 36)
	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	@Column(name = "item19Studentid", length = 50)
	public String getItem19Studentid() {
		return item19Studentid;
	}

	public void setItem19Studentid(String item19Studentid) {
		this.item19Studentid = item19Studentid;
	}

	@Column(name = "item19TrainContent", length = 4)
	public Integer getItem19TrainContent() {
		return item19TrainContent;
	}

	public void setItem19TrainContent(Integer item19TrainContent) {
		this.item19TrainContent = item19TrainContent;
	}

	@Column(name = "item20Studentid", length = 50)
	public String getItem20Studentid() {
		return item20Studentid;
	}

	public void setItem20Studentid(String item20Studentid) {
		this.item20Studentid = item20Studentid;
	}

	@Column(name = "item20TrainContent", length = 4)
	public Integer getItem20TrainContent() {
		return item20TrainContent;
	}

	public void setItem20TrainContent(Integer item20TrainContent) {
		this.item20TrainContent = item20TrainContent;
	}

	@Column(name = "item21Studentid", length = 50)
	public String getItem21Studentid() {
		return item21Studentid;
	}

	public void setItem21Studentid(String item21Studentid) {
		this.item21Studentid = item21Studentid;
	}

	@Column(name = "item21TrainContent", length = 4)
	public Integer getItem21TrainContent() {
		return item21TrainContent;
	}

	public void setItem21TrainContent(Integer item21TrainContent) {
		this.item21TrainContent = item21TrainContent;
	}

	@Column(name = "reservationFieldId19", length = 36)
	public String getReservationFieldId19() {
		return reservationFieldId19;
	}

	public void setReservationFieldId19(String reservationFieldId19) {
		this.reservationFieldId19 = reservationFieldId19;
	}

	@Column(name = "reservationFieldId20", length = 36)
	public String getReservationFieldId20() {
		return reservationFieldId20;
	}

	public void setReservationFieldId20(String reservationFieldId20) {
		this.reservationFieldId20 = reservationFieldId20;
	}

	@Column(name = "reservationFieldId21", length = 36)
	public String getReservationFieldId21() {
		return reservationFieldId21;
	}

	public void setReservationFieldId21(String reservationFieldId21) {
		this.reservationFieldId21 = reservationFieldId21;
	}

	@Column(name = "reservationState", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getReservationState() {
		return reservationState;
	}

	public void setReservationState(Integer reservationState) {
		this.reservationState = reservationState;
	}
	
	
	@Column(name = "reservation7MaxTotal", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getReservation7MaxTotal() {
		return reservation7MaxTotal;
	}

	public void setReservation7MaxTotal(Integer reservation7MaxTotal) {
		this.reservation7MaxTotal = reservation7MaxTotal;
	}

	@Column(name = "reservation8MaxTotal", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getReservation8MaxTotal() {
		return reservation8MaxTotal;
	}

	public void setReservation8MaxTotal(Integer reservation8MaxTotal) {
		this.reservation8MaxTotal = reservation8MaxTotal;
	}

	@Column(name = "reservation9MaxTotal", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getReservation9MaxTotal() {
		return reservation9MaxTotal;
	}

	public void setReservation9MaxTotal(Integer reservation9MaxTotal) {
		this.reservation9MaxTotal = reservation9MaxTotal;
	}

	@Column(name = "reservation10MaxTotal", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getReservation10MaxTotal() {
		return reservation10MaxTotal;
	}

	public void setReservation10MaxTotal(Integer reservation10MaxTotal) {
		this.reservation10MaxTotal = reservation10MaxTotal;
	}

	@Column(name = "reservation11MaxTotal", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getReservation11MaxTotal() {
		return reservation11MaxTotal;
	}

	public void setReservation11MaxTotal(Integer reservation11MaxTotal) {
		this.reservation11MaxTotal = reservation11MaxTotal;
	}

	@Column(name = "reservation12MaxTotal", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getReservation12MaxTotal() {
		return reservation12MaxTotal;
	}

	public void setReservation12MaxTotal(Integer reservation12MaxTotal) {
		this.reservation12MaxTotal = reservation12MaxTotal;
	}

	@Column(name = "reservation13MaxTotal", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getReservation13MaxTotal() {
		return reservation13MaxTotal;
	}

	public void setReservation13MaxTotal(Integer reservation13MaxTotal) {
		this.reservation13MaxTotal = reservation13MaxTotal;
	}

	@Column(name = "reservation14MaxTotal", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getReservation14MaxTotal() {
		return reservation14MaxTotal;
	}

	public void setReservation14MaxTotal(Integer reservation14MaxTotal) {
		this.reservation14MaxTotal = reservation14MaxTotal;
	}

	@Column(name = "reservation15MaxTotal", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getReservation15MaxTotal() {
		return reservation15MaxTotal;
	}

	public void setReservation15MaxTotal(Integer reservation15MaxTotal) {
		this.reservation15MaxTotal = reservation15MaxTotal;
	}

	@Column(name = "reservation16MaxTotal", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getReservation16MaxTotal() {
		return reservation16MaxTotal;
	}

	public void setReservation16MaxTotal(Integer reservation16MaxTotal) {
		this.reservation16MaxTotal = reservation16MaxTotal;
	}

	@Column(name = "reservation17MaxTotal", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getReservation17MaxTotal() {
		return reservation17MaxTotal;
	}

	public void setReservation17MaxTotal(Integer reservation17MaxTotal) {
		this.reservation17MaxTotal = reservation17MaxTotal;
	}

	@Column(name = "reservation18MaxTotal", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getReservation18MaxTotal() {
		return reservation18MaxTotal;
	}

	public void setReservation18MaxTotal(Integer reservation18MaxTotal) {
		this.reservation18MaxTotal = reservation18MaxTotal;
	}

	@Column(name = "reservation19MaxTotal", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getReservation19MaxTotal() {
		return reservation19MaxTotal;
	}

	public void setReservation19MaxTotal(Integer reservation19MaxTotal) {
		this.reservation19MaxTotal = reservation19MaxTotal;
	}

	@Column(name = "reservation20MaxTotal", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getReservation20MaxTotal() {
		return reservation20MaxTotal;
	}

	public void setReservation20MaxTotal(Integer reservation20MaxTotal) {
		this.reservation20MaxTotal = reservation20MaxTotal;
	}

	@Column(name = "reservation21MaxTotal", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getReservation21MaxTotal() {
		return reservation21MaxTotal;
	}

	public void setReservation21MaxTotal(Integer reservation21MaxTotal) {
		this.reservation21MaxTotal = reservation21MaxTotal;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trainerReservationId")
	public TtrainerReservation getTrainerReservation() {
		return trainerReservation;
	}

	public void setTrainerReservation(TtrainerReservation trainerReservation) {
		this.trainerReservation = trainerReservation;
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
		TtrainerReservationDetail other = (TtrainerReservationDetail) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TtrainerReservationDetail [id=" + id + ", reservationDate=" + reservationDate + ", reservationType="
				+ reservationType + ", item7Studentid=" + item7Studentid + ", item7TrainContent=" + item7TrainContent
				+ ", item8Studentid=" + item8Studentid + ", item8TrainContent=" + item8TrainContent
				+ ", item9Studentid=" + item9Studentid + ", item9TrainContent=" + item9TrainContent
				+ ", item10Studentid=" + item10Studentid + ", item10TrainContent=" + item10TrainContent
				+ ", item11Studentid=" + item11Studentid + ", item11TrainContent=" + item11TrainContent
				+ ", item12Studentid=" + item12Studentid + ", item12TrainContent=" + item12TrainContent
				+ ", item13Studentid=" + item13Studentid + ", item13TrainContent=" + item13TrainContent
				+ ", item14Studentid=" + item14Studentid + ", item14TrainContent=" + item14TrainContent
				+ ", item15Studentid=" + item15Studentid + ", item15TrainContent=" + item15TrainContent
				+ ", item16Studentid=" + item16Studentid + ", item16TrainContent=" + item16TrainContent
				+ ", item17Studentid=" + item17Studentid + ", item17TrainContent=" + item17TrainContent
				+ ", item18Studentid=" + item18Studentid + ", item18TrainContent=" + item18TrainContent
				+ ", item19Studentid=" + item19Studentid + ", item19TrainContent=" + item19TrainContent
				+ ", item20Studentid=" + item20Studentid + ", item20TrainContent=" + item20TrainContent
				+ ", item21Studentid=" + item21Studentid + ", item21TrainContent=" + item21TrainContent
				+ ", fiveItemTotal=" + fiveItemTotal + ", raodExamTotal=" + raodExamTotal + ", trainerId=" + trainerId
				+ ", arrangeId=" + arrangeId + ", reservationState=" + reservationState + ", trainerReservation="
				+ trainerReservation + ", reservationFieldId7=" + reservationFieldId7 + ", reservationFieldId8="
				+ reservationFieldId8 + ", reservationFieldId9=" + reservationFieldId9 + ", reservationFieldId10="
				+ reservationFieldId10 + ", reservationFieldId11=" + reservationFieldId11 + ", reservationFieldId12="
				+ reservationFieldId12 + ", reservationFieldId13=" + reservationFieldId13 + ", reservationFieldId14="
				+ reservationFieldId14 + ", reservationFieldId15=" + reservationFieldId15 + ", reservationFieldId16="
				+ reservationFieldId16 + ", reservationFieldId17=" + reservationFieldId17 + ", reservationFieldId18="
				+ reservationFieldId18 + ", reservationFieldId19=" + reservationFieldId19 + ", reservationFieldId20="
				+ reservationFieldId20 + ", reservationFieldId21=" + reservationFieldId21 + ", reservation7MaxTotal="
				+ reservation7MaxTotal + ", reservation8MaxTotal=" + reservation8MaxTotal + ", reservation9MaxTotal="
				+ reservation9MaxTotal + ", reservation10MaxTotal=" + reservation10MaxTotal + ", reservation11MaxTotal="
				+ reservation11MaxTotal + ", reservation12MaxTotal=" + reservation12MaxTotal
				+ ", reservation13MaxTotal=" + reservation13MaxTotal + ", reservation14MaxTotal="
				+ reservation14MaxTotal + ", reservation15MaxTotal=" + reservation15MaxTotal
				+ ", reservation16MaxTotal=" + reservation16MaxTotal + ", reservation17MaxTotal="
				+ reservation17MaxTotal + ", reservation18MaxTotal=" + reservation18MaxTotal
				+ ", reservation19MaxTotal=" + reservation19MaxTotal + ", reservation20MaxTotal="
				+ reservation20MaxTotal + ", reservation21MaxTotal=" + reservation21MaxTotal + ", schoolArea="
				+ schoolArea + "]";
	}

	

}

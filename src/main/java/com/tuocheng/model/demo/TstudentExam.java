package com.tuocheng.model.demo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.crypto.Data;

/**
 * 学员考试实体类
 * 
 * @author 农峰
 * 
 */
@Entity
@Table(name = "tb_studentExams", schema = "")
public class TstudentExam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4502061731658463764L;

	public static final Integer EXAMFALG_TRUE = 1;
	public static final Integer EXAMFALG_FALSE = 0;
	public static final Integer EXAMPASS_TRUE = 1;
	public static final Integer EXAMPASS_FALSE = 2;
	public static final Integer EDITABLE_TRUE = 0;
	public static final Integer EDITABLE_FALSE = 1;

	public static final Integer STUDENTEXAM_SUBJECT1 = 1;
	public static final Integer STUDENTEXAM_SUBJECT2 = 2;
	public static final Integer STUDENTEXAM_SUBJECT3 = 3;
	public static final Integer STUDENTEXAM_SUBJECT4 = 4;
	public static final Integer STUDENTEXAM_SUBJECT5 = 5;

	private String id;// 标识
	private String schoolArea;
	private Integer batch;
	private Date sub1ExamDate;// 考试日期
	private Integer sub1Score;// 成绩
	private Integer sub1FishedFlag;// 完成科目一考试标记
	private Integer sub1ApplyFlag;// 1：通过审批 0：未通过 默认1
	private Integer sub1ExamFlag;// 0：表示没有参加科一考试，1：表示参加考试
	private Date sub1MakeupDate;// 补考日期
	private Integer sub1MakeupScore;// 补考成绩
	private Integer sub1MakeupTimes;// 补考次数
	private Integer sub1MissExamFlag;// 是否缺考
	private Integer sub1EditAbleFlag;// 1:表示不可编辑，默认值为0

	private Date sub2ExamDate;// 考试日期
	private Integer sub2Score;// 成绩
	private Integer sub2FishedFlag;// 完成科目二考试标记]
	private Integer sub2ExamFlag;// 0：表示没有参加科二考试，1：表示参加考试
	private Integer sub2ApplyFlag;// 1：通过审批 0：未通过 默认1
	private Date sub2MakeupDate;// 补考日期
	private Integer sub2MakeupScore;// 补考成绩
	private Integer sub2MakeupTimes;// 补考次数
	private Integer sub2MissExamFlag;// 是否缺考
	private Date sub3ExamDate;// 考试日期
	private Integer sub3Score;// 成绩
	private Integer sub3FishedFlag;// 完成科目三考试标记
	private Integer sub3ExamFlag;// 0：表示没有参加科三考试，1：表示参加考试
	private Integer sub3ApplyFlag;// 1：通过审批 0：未通过 默认1
	private Date sub3MakeupDate;// 补考日期
	private Integer sub3MakeupScore;// 补考成绩
	private Integer sub3MakeupTimes;// 补考次数
	private Integer sub3MissExamFlag;// 是否缺考
	private Date sub4ExamDate;// 考试日期
	private Integer sub4Score;// 成绩
	private Integer sub4FishedFlag;// 完成科目四考试标记
	private Integer sub4ExamFlag;// 0：表示没有参加科四考试，1：表示参加考试
	private Integer sub4ApplyFlag;// 1：通过审批 0：未通过 默认1
	private Date sub4MakeupDate;// 补考日期
	private Integer sub4MakeupScore;// 补考成绩
	private Integer sub4MakeupTimes;// 补考次数
	private Integer sub4MissExamFlag;// 是否缺考
	private Date sub5ExamDate;// 考试日期
	private Integer sub5Score;// 成绩
	private Integer sub5FishedFlag;// 完成科目五考试标记
	private Integer sub5ExamFlag;// 0：表示没有参加科五考试，1：表示参加考试
	private Integer sub5ApplyFlag;// 1：通过审批 0：未通过 默认1
	private Date sub5MakeupDate;// 补考日期
	private Integer sub5MakeupScore;// 补考成绩
	private Integer sub5MakeupTimes;// 补考次数
	private Integer sub5MissExamFlag;// 是否缺考

	private Integer sub2EditAbleFlag;// 1:表示不可编辑，默认值为0
	private Integer sub3EditAbleFlag;// 1:表示不可编辑，默认值为0
	private Integer sub4EditAbleFlag;// 1:表示不可编辑，默认值为0
	private Integer sub5EditAbleFlag;// 1:表示不可编辑，默认值为0

	private String comment;// 备注
	private Date updateTime;

	private Tstudent student;
	private TexamAnalys examAnalys;
	private Integer importOrder;

	private Integer driverLicenseFlag;// 驾驶证资格标志 1：有效 0：无效(默认：0)
	private Integer qLicenseFlag;// 资格证资格标志 1：有效 0：无效(默认：0)
	private Integer driverLicenseFinishFlag;// 标记该学员是否出驾驶证 0：未出证，1：已出证
	private Integer driverGrantFlag;// 驾驶证资格发放标志 1：有效 0：无效(默认：0)
	private Integer qGrantFlag;// 资格证资格发放标志 1：有效 0：无效(默认：0)
	private Integer qLicenseFinishFlag;// 标记该学员是否出资格证 0：未出证，1：已出证

	public TstudentExam() {
	}

	public TstudentExam(String id, String schoolArea, Integer batch, Date sub1ExamDate, Integer sub1Score,
			Integer sub1FishedFlag, Integer sub1ApplyFlag, Integer sub1ExamFlag, Date sub1MakeupDate,
			Integer sub1MakeupScore, Integer sub1MakeupTimes, Integer sub1MissExamFlag, Integer sub1EditAbleFlag,
			Date sub2ExamDate, Integer sub2Score, Integer sub2FishedFlag, Integer sub2ExamFlag, Integer sub2ApplyFlag,
			Date sub2MakeupDate, Integer sub2MakeupScore, Integer sub2MakeupTimes, Integer sub2MissExamFlag,
			Date sub3ExamDate, Integer sub3Score, Integer sub3FishedFlag, Integer sub3ExamFlag, Integer sub3ApplyFlag,
			Date sub3MakeupDate, Integer sub3MakeupScore, Integer sub3MakeupTimes, Integer sub3MissExamFlag,
			Date sub4ExamDate, Integer sub4Score, Integer sub4FishedFlag, Integer sub4ExamFlag, Integer sub4ApplyFlag,
			Date sub4MakeupDate, Integer sub4MakeupScore, Integer sub4MakeupTimes, Integer sub4MissExamFlag,
			Date sub5ExamDate, Integer sub5Score, Integer sub5FishedFlag, Integer sub5ExamFlag, Integer sub5ApplyFlag,
			Date sub5MakeupDate, Integer sub5MakeupScore, Integer sub5MakeupTimes, Integer sub5MissExamFlag,
			Integer sub2EditAbleFlag, Integer sub3EditAbleFlag, Integer sub4EditAbleFlag, Integer sub5EditAbleFlag,
			String comment, Date updateTime, Tstudent student, TexamAnalys examAnalys, Integer importOrder,
			Integer driverLicenseFlag, Integer qLicenseFlag, Integer driverLicenseFinishFlag, Integer driverGrantFlag,
			Integer qGrantFlag, Integer qLicenseFinishFlag) {
		this.id = id;
		this.schoolArea = schoolArea;
		this.batch = batch;
		this.sub1ExamDate = sub1ExamDate;
		this.sub1Score = sub1Score;
		this.sub1FishedFlag = sub1FishedFlag;
		this.sub1ApplyFlag = sub1ApplyFlag;
		this.sub1ExamFlag = sub1ExamFlag;
		this.sub1MakeupDate = sub1MakeupDate;
		this.sub1MakeupScore = sub1MakeupScore;
		this.sub1MakeupTimes = sub1MakeupTimes;
		this.sub1MissExamFlag = sub1MissExamFlag;
		this.sub1EditAbleFlag = sub1EditAbleFlag;
		this.sub2ExamDate = sub2ExamDate;
		this.sub2Score = sub2Score;
		this.sub2FishedFlag = sub2FishedFlag;
		this.sub2ExamFlag = sub2ExamFlag;
		this.sub2ApplyFlag = sub2ApplyFlag;
		this.sub2MakeupDate = sub2MakeupDate;
		this.sub2MakeupScore = sub2MakeupScore;
		this.sub2MakeupTimes = sub2MakeupTimes;
		this.sub2MissExamFlag = sub2MissExamFlag;
		this.sub3ExamDate = sub3ExamDate;
		this.sub3Score = sub3Score;
		this.sub3FishedFlag = sub3FishedFlag;
		this.sub3ExamFlag = sub3ExamFlag;
		this.sub3ApplyFlag = sub3ApplyFlag;
		this.sub3MakeupDate = sub3MakeupDate;
		this.sub3MakeupScore = sub3MakeupScore;
		this.sub3MakeupTimes = sub3MakeupTimes;
		this.sub3MissExamFlag = sub3MissExamFlag;
		this.sub4ExamDate = sub4ExamDate;
		this.sub4Score = sub4Score;
		this.sub4FishedFlag = sub4FishedFlag;
		this.sub4ExamFlag = sub4ExamFlag;
		this.sub4ApplyFlag = sub4ApplyFlag;
		this.sub4MakeupDate = sub4MakeupDate;
		this.sub4MakeupScore = sub4MakeupScore;
		this.sub4MakeupTimes = sub4MakeupTimes;
		this.sub4MissExamFlag = sub4MissExamFlag;
		this.sub5ExamDate = sub5ExamDate;
		this.sub5Score = sub5Score;
		this.sub5FishedFlag = sub5FishedFlag;
		this.sub5ExamFlag = sub5ExamFlag;
		this.sub5ApplyFlag = sub5ApplyFlag;
		this.sub5MakeupDate = sub5MakeupDate;
		this.sub5MakeupScore = sub5MakeupScore;
		this.sub5MakeupTimes = sub5MakeupTimes;
		this.sub5MissExamFlag = sub5MissExamFlag;
		this.sub2EditAbleFlag = sub2EditAbleFlag;
		this.sub3EditAbleFlag = sub3EditAbleFlag;
		this.sub4EditAbleFlag = sub4EditAbleFlag;
		this.sub5EditAbleFlag = sub5EditAbleFlag;
		this.comment = comment;
		this.updateTime = updateTime;
		this.student = student;
		this.examAnalys = examAnalys;
		this.importOrder = importOrder;
		this.driverLicenseFlag = driverLicenseFlag;
		this.qLicenseFlag = qLicenseFlag;
		this.driverLicenseFinishFlag = driverLicenseFinishFlag;
		this.driverGrantFlag = driverGrantFlag;
		this.qGrantFlag = qGrantFlag;
		this.qLicenseFinishFlag = qLicenseFinishFlag;
	}

	@Id
	@Column(name = "id", nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "schoolArea", nullable = false, length = 36)
	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "contributionTime", length = 7)
	public Date getSub1ExamDate() {
		return sub1ExamDate;
	}

	public void setSub1ExamDate(Date sub1ExamDate) {
		this.sub1ExamDate = sub1ExamDate;
	}

	@Column(name = "sub1Score", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getSub1Score() {
		return sub1Score;
	}

	public void setSub1Score(Integer sub1Score) {
		this.sub1Score = sub1Score;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "sub2ExamDate", length = 7)
	public Date getSub2ExamDate() {
		return sub2ExamDate;
	}

	public void setSub2ExamDate(Date sub2ExamDate) {
		this.sub2ExamDate = sub2ExamDate;
	}

	@Column(name = "sub2Score", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getSub2Score() {
		return sub2Score;
	}

	public void setSub2Score(Integer sub2Score) {
		this.sub2Score = sub2Score;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "sub3ExamDate", length = 7)
	public Date getSub3ExamDate() {
		return sub3ExamDate;
	}

	public void setSub3ExamDate(Date sub3ExamDate) {
		this.sub3ExamDate = sub3ExamDate;
	}

	@Column(name = "sub3Score", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getSub3Score() {
		return sub3Score;
	}

	public void setSub3Score(Integer sub3Score) {
		this.sub3Score = sub3Score;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "sub4ExamDate", length = 7)
	public Date getSub4ExamDate() {
		return sub4ExamDate;
	}

	public void setSub4ExamDate(Date sub4ExamDate) {
		this.sub4ExamDate = sub4ExamDate;
	}

	@Column(name = "sub4Score", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getSub4Score() {
		return sub4Score;
	}

	public void setSub4Score(Integer sub4Score) {
		this.sub4Score = sub4Score;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "sub5ExamDate", length = 7)
	public Date getSub5ExamDate() {
		return sub5ExamDate;
	}

	public void setSub5ExamDate(Date sub5ExamDate) {
		this.sub5ExamDate = sub5ExamDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updateTime", length = 7)
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "sub5Score", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getSub5Score() {
		return sub5Score;
	}

	public void setSub5Score(Integer sub5Score) {
		this.sub5Score = sub5Score;
	}

	@Column(name = "comment", length = 300)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name = "batch", length = 10, columnDefinition = "INT DEFAULT 0")
	public Integer getBatch() {
		return batch;
	}

	public void setBatch(Integer batch) {
		this.batch = batch;
	}

	@Column(name = "sub1ExamFlag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getSub1ExamFlag() {
		return sub1ExamFlag;
	}

	public void setSub1ExamFlag(Integer sub1ExamFlag) {
		this.sub1ExamFlag = sub1ExamFlag;
	}

	@Column(name = "sub2ExamFlag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getSub2ExamFlag() {
		return sub2ExamFlag;
	}

	public void setSub2ExamFlag(Integer sub2ExamFlag) {
		this.sub2ExamFlag = sub2ExamFlag;
	}

	@Column(name = "sub3ExamFlag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getSub3ExamFlag() {
		return sub3ExamFlag;
	}

	public void setSub3ExamFlag(Integer sub3ExamFlag) {
		this.sub3ExamFlag = sub3ExamFlag;
	}

	@Column(name = "sub4ExamFlag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getSub4ExamFlag() {
		return sub4ExamFlag;
	}

	public void setSub4ExamFlag(Integer sub4ExamFlag) {
		this.sub4ExamFlag = sub4ExamFlag;
	}

	@Column(name = "sub5ExamFlag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getSub5ExamFlag() {
		return sub5ExamFlag;
	}

	public void setSub5ExamFlag(Integer sub5ExamFlag) {
		this.sub5ExamFlag = sub5ExamFlag;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "sub1MakeupDate", length = 7)
	public Date getSub1MakeupDate() {
		return sub1MakeupDate;
	}

	public void setSub1MakeupDate(Date sub1MakeupDate) {
		this.sub1MakeupDate = sub1MakeupDate;
	}

	@Column(name = "sub1MakeupScore", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getSub1MakeupScore() {
		return sub1MakeupScore;
	}

	public void setSub1MakeupScore(Integer sub1MakeupScore) {
		this.sub1MakeupScore = sub1MakeupScore;
	}

	@Column(name = "sub1MakeupTimes", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getSub1MakeupTimes() {
		return sub1MakeupTimes;
	}

	public void setSub1MakeupTimes(Integer sub1MakeupTimes) {
		this.sub1MakeupTimes = sub1MakeupTimes;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "sub2MakeupDate", length = 7)
	public Date getSub2MakeupDate() {
		return sub2MakeupDate;
	}

	public void setSub2MakeupDate(Date sub2MakeupDate) {
		this.sub2MakeupDate = sub2MakeupDate;
	}

	@Column(name = "sub2MakeupScore", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getSub2MakeupScore() {
		return sub2MakeupScore;
	}

	public void setSub2MakeupScore(Integer sub2MakeupScore) {
		this.sub2MakeupScore = sub2MakeupScore;
	}

	@Column(name = "sub2MakeupTimes", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getSub2MakeupTimes() {
		return sub2MakeupTimes;
	}

	public void setSub2MakeupTimes(Integer sub2MakeupTimes) {
		this.sub2MakeupTimes = sub2MakeupTimes;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "sub3MakeupDate", length = 7)
	public Date getSub3MakeupDate() {
		return sub3MakeupDate;
	}

	public void setSub3MakeupDate(Date sub3MakeupDate) {
		this.sub3MakeupDate = sub3MakeupDate;
	}

	@Column(name = "sub3MakeupScore", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getSub3MakeupScore() {
		return sub3MakeupScore;
	}

	public void setSub3MakeupScore(Integer sub3MakeupScore) {
		this.sub3MakeupScore = sub3MakeupScore;
	}

	@Column(name = "sub3MakeupTimes", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getSub3MakeupTimes() {
		return sub3MakeupTimes;
	}

	public void setSub3MakeupTimes(Integer sub3MakeupTimes) {
		this.sub3MakeupTimes = sub3MakeupTimes;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "sub4MakeupDate", length = 7)
	public Date getSub4MakeupDate() {
		return sub4MakeupDate;
	}

	public void setSub4MakeupDate(Date sub4MakeupDate) {
		this.sub4MakeupDate = sub4MakeupDate;
	}

	@Column(name = "sub4MakeupScore", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getSub4MakeupScore() {
		return sub4MakeupScore;
	}

	public void setSub4MakeupScore(Integer sub4MakeupScore) {
		this.sub4MakeupScore = sub4MakeupScore;
	}

	@Column(name = "sub4MakeupTimes", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getSub4MakeupTimes() {
		return sub4MakeupTimes;
	}

	public void setSub4MakeupTimes(Integer sub4MakeupTimes) {
		this.sub4MakeupTimes = sub4MakeupTimes;
	}

	@Column(name = "driverLicenseFlag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getDriverLicenseFlag() {
		return driverLicenseFlag;
	}

	public void setDriverLicenseFlag(Integer driverLicenseFlag) {
		this.driverLicenseFlag = driverLicenseFlag;
	}

	@Column(name = "qLicenseFlag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getqLicenseFlag() {
		return qLicenseFlag;
	}

	public void setqLicenseFlag(Integer qLicenseFlag) {
		this.qLicenseFlag = qLicenseFlag;
	}

	@Column(name = "driverGrantFlag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getDriverGrantFlag() {
		return driverGrantFlag;
	}

	public void setDriverGrantFlag(Integer driverGrantFlag) {
		this.driverGrantFlag = driverGrantFlag;
	}

	@Column(name = "qGrantFlag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getqGrantFlag() {
		return qGrantFlag;
	}

	public void setqGrantFlag(Integer qGrantFlag) {
		this.qGrantFlag = qGrantFlag;
	}

	@Column(name = "driverLicenseFinishFlag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getDriverLicenseFinishFlag() {
		return driverLicenseFinishFlag;
	}

	public void setDriverLicenseFinishFlag(Integer driverLicenseFinishFlag) {
		this.driverLicenseFinishFlag = driverLicenseFinishFlag;
	}

	@Column(name = "qLicenseFinishFlag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getqLicenseFinishFlag() {
		return qLicenseFinishFlag;
	}

	public void setqLicenseFinishFlag(Integer qLicenseFinishFlag) {
		this.qLicenseFinishFlag = qLicenseFinishFlag;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "sub5MakeupDate", length = 7)
	public Date getSub5MakeupDate() {
		return sub5MakeupDate;
	}

	public void setSub5MakeupDate(Date sub5MakeupDate) {
		this.sub5MakeupDate = sub5MakeupDate;
	}

	@Column(name = "sub5MakeupScore", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getSub5MakeupScore() {
		return sub5MakeupScore;
	}

	public void setSub5MakeupScore(Integer sub5MakeupScore) {
		this.sub5MakeupScore = sub5MakeupScore;
	}

	@Column(name = "sub5MakeupTimes", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getSub5MakeupTimes() {
		return sub5MakeupTimes;
	}

	public void setSub5MakeupTimes(Integer sub5MakeupTimes) {
		this.sub5MakeupTimes = sub5MakeupTimes;
	}

	@Column(name = "sub1MissExamFlag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getSub1MissExamFlag() {
		return sub1MissExamFlag;
	}

	public void setSub1MissExamFlag(Integer sub1MissExamFlag) {
		this.sub1MissExamFlag = sub1MissExamFlag;
	}

	@Column(name = "sub2MissExamFlag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getSub2MissExamFlag() {
		return sub2MissExamFlag;
	}

	public void setSub2MissExamFlag(Integer sub2MissExamFlag) {
		this.sub2MissExamFlag = sub2MissExamFlag;
	}

	@Column(name = "sub3MissExamFlag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getSub3MissExamFlag() {
		return sub3MissExamFlag;
	}

	public void setSub3MissExamFlag(Integer sub3MissExamFlag) {
		this.sub3MissExamFlag = sub3MissExamFlag;
	}

	@Column(name = "sub4MissExamFlag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getSub4MissExamFlag() {
		return sub4MissExamFlag;
	}

	public void setSub4MissExamFlag(Integer sub4MissExamFlag) {
		this.sub4MissExamFlag = sub4MissExamFlag;
	}

	@Column(name = "sub5MissExamFlag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getSub5MissExamFlag() {
		return sub5MissExamFlag;
	}

	public void setSub5MissExamFlag(Integer sub5MissExamFlag) {
		this.sub5MissExamFlag = sub5MissExamFlag;
	}

	@Column(name = "sub1ApplyFlag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getSub1ApplyFlag() {
		return sub1ApplyFlag;
	}

	public void setSub1ApplyFlag(Integer sub1ApplyFlag) {
		this.sub1ApplyFlag = sub1ApplyFlag;
	}

	@Column(name = "sub2ApplyFlag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getSub2ApplyFlag() {
		return sub2ApplyFlag;
	}

	public void setSub2ApplyFlag(Integer sub2ApplyFlag) {
		this.sub2ApplyFlag = sub2ApplyFlag;
	}

	@Column(name = "sub3ApplyFlag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getSub3ApplyFlag() {
		return sub3ApplyFlag;
	}

	public void setSub3ApplyFlag(Integer sub3ApplyFlag) {
		this.sub3ApplyFlag = sub3ApplyFlag;
	}

	@Column(name = "sub4ApplyFlag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getSub4ApplyFlag() {
		return sub4ApplyFlag;
	}

	public void setSub4ApplyFlag(Integer sub4ApplyFlag) {
		this.sub4ApplyFlag = sub4ApplyFlag;
	}

	@Column(name = "sub5ApplyFlag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getSub5ApplyFlag() {
		return sub5ApplyFlag;
	}

	public void setSub5ApplyFlag(Integer sub5ApplyFlag) {
		this.sub5ApplyFlag = sub5ApplyFlag;
	}

	@Column(name = "sub1FishedFlag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getSub1FishedFlag() {
		return sub1FishedFlag;
	}

	public void setSub1FishedFlag(Integer sub1FishedFlag) {
		this.sub1FishedFlag = sub1FishedFlag;
	}

	@Column(name = "sub2FishedFlag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getSub2FishedFlag() {
		return sub2FishedFlag;
	}

	public void setSub2FishedFlag(Integer sub2FishedFlag) {
		this.sub2FishedFlag = sub2FishedFlag;
	}

	@Column(name = "sub3FishedFlag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getSub3FishedFlag() {
		return sub3FishedFlag;
	}

	public void setSub3FishedFlag(Integer sub3FishedFlag) {
		this.sub3FishedFlag = sub3FishedFlag;
	}

	@Column(name = "sub4FishedFlag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getSub4FishedFlag() {
		return sub4FishedFlag;
	}

	public void setSub4FishedFlag(Integer sub4FishedFlag) {
		this.sub4FishedFlag = sub4FishedFlag;
	}

	@Column(name = "sub5FishedFlag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getSub5FishedFlag() {
		return sub5FishedFlag;
	}

	public void setSub5FishedFlag(Integer sub5FishedFlag) {
		this.sub5FishedFlag = sub5FishedFlag;
	}

	@Column(name = "sub1EditAbleFlag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getSub1EditAbleFlag() {
		return sub1EditAbleFlag;
	}

	public void setSub1EditAbleFlag(Integer sub1EditAbleFlag) {
		this.sub1EditAbleFlag = sub1EditAbleFlag;
	}

	@Column(name = "sub2EditAbleFlag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getSub2EditAbleFlag() {
		return sub2EditAbleFlag;
	}

	public void setSub2EditAbleFlag(Integer sub2EditAbleFlag) {
		this.sub2EditAbleFlag = sub2EditAbleFlag;
	}

	@Column(name = "sub3EditAbleFlag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getSub3EditAbleFlag() {
		return sub3EditAbleFlag;
	}

	public void setSub3EditAbleFlag(Integer sub3EditAbleFlag) {
		this.sub3EditAbleFlag = sub3EditAbleFlag;
	}

	@Column(name = "sub4EditAbleFlag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getSub4EditAbleFlag() {
		return sub4EditAbleFlag;
	}

	public void setSub4EditAbleFlag(Integer sub4EditAbleFlag) {
		this.sub4EditAbleFlag = sub4EditAbleFlag;
	}

	@Column(name = "sub5EditAbleFlag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getSub5EditAbleFlag() {
		return sub5EditAbleFlag;
	}

	public void setSub5EditAbleFlag(Integer sub5EditAbleFlag) {
		this.sub5EditAbleFlag = sub5EditAbleFlag;
	}

	@Column(name = "importOrder", length = 8, columnDefinition = "INT DEFAULT 0")
	public Integer getImportOrder() {
		return importOrder;
	}

	public void setImportOrder(Integer importOrder) {
		this.importOrder = importOrder;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "studentId")
	public Tstudent getStudent() {
		return student;
	}

	public void setStudent(Tstudent student) {
		this.student = student;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "examAnalysId", updatable = false)
	public TexamAnalys getExamAnalys() {
		return examAnalys;
	}

	public void setExamAnalys(TexamAnalys examAnalys) {
		this.examAnalys = examAnalys;
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
		TstudentExam other = (TstudentExam) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TstudentExam [id=" + id + ", schoolArea=" + schoolArea + ", batch=" + batch + ", sub1ExamDate="
				+ sub1ExamDate + ", sub1Score=" + sub1Score + ", sub1FishedFlag=" + sub1FishedFlag + ", sub1ApplyFlag="
				+ sub1ApplyFlag + ", sub1ExamFlag=" + sub1ExamFlag + ", sub1MakeupDate=" + sub1MakeupDate
				+ ", sub1MakeupScore=" + sub1MakeupScore + ", sub1MakeupTimes=" + sub1MakeupTimes
				+ ", sub1MissExamFlag=" + sub1MissExamFlag + ", sub2ExamDate=" + sub2ExamDate + ", sub2Score="
				+ sub2Score + ", sub2FishedFlag=" + sub2FishedFlag + ", sub2ExamFlag=" + sub2ExamFlag
				+ ", sub2ApplyFlag=" + sub2ApplyFlag + ", sub2MakeupDate=" + sub2MakeupDate + ", sub2MakeupScore="
				+ sub2MakeupScore + ", sub2MakeupTimes=" + sub2MakeupTimes + ", sub2MissExamFlag=" + sub2MissExamFlag
				+ ", sub3ExamDate=" + sub3ExamDate + ", sub3Score=" + sub3Score + ", sub3FishedFlag=" + sub3FishedFlag
				+ ", sub3ExamFlag=" + sub3ExamFlag + ", sub3ApplyFlag=" + sub3ApplyFlag + ", sub3MakeupDate="
				+ sub3MakeupDate + ", sub3MakeupScore=" + sub3MakeupScore + ", sub3MakeupTimes=" + sub3MakeupTimes
				+ ", sub3MissExamFlag=" + sub3MissExamFlag + ", sub4ExamDate=" + sub4ExamDate + ", sub4Score="
				+ sub4Score + ", sub4FishedFlag=" + sub4FishedFlag + ", sub4ExamFlag=" + sub4ExamFlag
				+ ", sub4ApplyFlag=" + sub4ApplyFlag + ", sub4MakeupDate=" + sub4MakeupDate + ", sub4MakeupScore="
				+ sub4MakeupScore + ", sub4MakeupTimes=" + sub4MakeupTimes + ", sub4MissExamFlag=" + sub4MissExamFlag
				+ ", sub5ExamDate=" + sub5ExamDate + ", sub5Score=" + sub5Score + ", sub5FishedFlag=" + sub5FishedFlag
				+ ", sub5ExamFlag=" + sub5ExamFlag + ", sub5ApplyFlag=" + sub5ApplyFlag + ", sub5MakeupDate="
				+ sub5MakeupDate + ", sub5MakeupScore=" + sub5MakeupScore + ", sub5MakeupTimes=" + sub5MakeupTimes
				+ ", sub5MissExamFlag=" + sub5MissExamFlag + ", comment=" + comment + ", updateTime=" + updateTime
				+ "]";
	}

}
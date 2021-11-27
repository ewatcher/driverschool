package com.tuocheng.pageModel.demo;

import java.util.Date;

import com.tuocheng.model.demo.TexamAnalys;
import com.tuocheng.pageModel.base.StudentPageModel;

public class StudentExam extends StudentPageModel {

	/**
	 * 
	 */
	private String id;// 标识
	private String schoolArea;
	private Integer batch;
	private Date sub1ExamDate;// 考试日期
	private Integer sub1Score;// 成绩
	private Integer sub1FishedFlag;// 完成科目一考试标记
	private Date sub2ExamDate;// 考试日期
	private Integer sub2Score;// 成绩
	private Integer sub2FishedFlag;// 完成科目一考试标记
	private Date sub3ExamDate;// 考试日期
	private Integer sub3Score;// 成绩
	private Integer sub3FishedFlag;// 完成科目一考试标记
	private Date sub4ExamDate;// 考试日期
	private Integer sub4Score;// 成绩
	private Integer sub4FishedFlag;// 完成科目一考试标记
	private Date sub5ExamDate;// 考试日期
	private Integer sub5Score;// 成绩
	private Integer sub5FishedFlag;// 完成科目一考试标记

	private Date examDateStart;// 考试日期开始
	private Date examDateEnd;// 考试日期结束
	private String schoolAreaName;
	private String comment;// 备注
	private Date updateTime;// 排序用

	private String examAnalysId;
	private String studentExamIds;
	private Integer moveTotal;

	private Integer sub1ExamFlag;// 完成科目1考试标记
	private Integer sub2ExamFlag;// 完成科目2考试标记
	private Integer sub3ExamFlag;// 完成科目3考试标记
	private Integer sub4ExamFlag;// 完成科目4考试标记
	private Integer sub5ExamFlag;// 完成科目考试标记

	private Date sub1MakeupDate;// 补考日期
	private Integer sub1MakeupScore;// 补考成绩
	private Integer sub1MakeupTimes;// 补考次数
	private Date sub2MakeupDate;// 补考日期
	private Integer sub2MakeupScore;// 补考成绩
	private Integer sub2MakeupTimes;// 补考次数
	private Date sub3MakeupDate;// 补考日期
	private Integer sub3MakeupScore;// 补考成绩
	private Integer sub3MakeupTimes;// 补考次数
	private Date sub4MakeupDate;// 补考日期
	private Integer sub4MakeupScore;// 补考成绩
	private Integer sub4MakeupTimes;// 补考次数
	private Date sub5MakeupDate;// 补考日期
	private Integer sub5MakeupScore;// 补考成绩
	private Integer sub5MakeupTimes;// 补考次数

	private Integer sub1MissExamFlag;// 是否缺考
	private Integer sub2MissExamFlag;// 是否缺考
	private Integer sub3MissExamFlag;// 是否缺考
	private Integer sub4MissExamFlag;// 是否缺考
	private Integer sub5MissExamFlag;// 是否缺考

	private Integer sub1ApplyFlag;// 1：通过审批 0：未通过 默认1
	private Integer sub2ApplyFlag;// 1：通过审批 0：未通过 默认1
	private Integer sub3ApplyFlag;// 1：通过审批 0：未通过 默认1
	private Integer sub4ApplyFlag;// 1：通过审批 0：未通过 默认1
	private Integer sub5ApplyFlag;// 1：通过审批 0：未通过 默认1

	private Integer sub1EditAbleFlag;// 1:表示不可编辑，默认值为0
	private Integer sub2EditAbleFlag;// 1:表示不可编辑，默认值为0
	private Integer sub3EditAbleFlag;// 1:表示不可编辑，默认值为0
	private Integer sub4EditAbleFlag;// 1:表示不可编辑，默认值为0
	private Integer sub5EditAbleFlag;// 1:表示不可编辑，默认值为0

	private Integer subjectType;// 1:表示理论，2：表示五项 3：路训 4：文明 5：资格
	private Integer examScore;// 考试成绩
	private Integer batchFlag;// 批量操作标记 1:批量报考 2：批量修改成绩
	private Integer examScoreFlag;// 修改成绩标记：1为初考，2为补考,

	private Integer datagridSubjectType;// 1:表示理论，2：表示五项 3：路训 4：文明 5：资格
	private Integer importOrder;// 保证导入导出的顺序

	private String identityId;// 导入学员考试成绩时记录身份证号
	private Integer repeartExamFlag;// 默认为0：表示没有补考，1：表示补考

	private TexamAnalys examAnalys;

	private Integer driverLicenseFlag;// 驾驶证资格标志 1：有效 0：无效(默认：0)
	private Integer qLicenseFlag;// 资格证资格标志 1：有效 0：无效(默认：0)
	private Integer driverLicenseFinishFlag;// 标记该学员是否出驾驶证 0：未出证，1：已出证
	private Integer driverGrantFlag;// 驾驶证资格发放标志 1：有效 0：无效(默认：0)
	private Integer qGrantFlag;// 资格证资格发放标志 1：有效 0：无效(默认：0)
	private Integer qLicenseFinishFlag;// 标记该学员是否出资格证 0：未出证，1：已出证

	private Integer qualificationFinishFlag;
	private Integer qualificationFlag;

	public Integer getQualificationFinishFlag() {
		return qualificationFinishFlag;
	}

	public void setQualificationFinishFlag(Integer qualificationFinishFlag) {
		this.qualificationFinishFlag = qualificationFinishFlag;
	}

	public Integer getQualificationFlag() {
		return qualificationFlag;
	}

	public void setQualificationFlag(Integer qualificationFlag) {
		this.qualificationFlag = qualificationFlag;
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

	public Integer getBatch() {
		return batch;
	}

	public void setBatch(Integer batch) {
		this.batch = batch;
	}

	public Date getSub1ExamDate() {
		return sub1ExamDate;
	}

	public void setSub1ExamDate(Date sub1ExamDate) {
		this.sub1ExamDate = sub1ExamDate;
	}

	public Integer getSub1Score() {
		return sub1Score;
	}

	public void setSub1Score(Integer sub1Score) {
		this.sub1Score = sub1Score;
	}

	public Integer getSub1FishedFlag() {
		return sub1FishedFlag;
	}

	public void setSub1FishedFlag(Integer sub1FishedFlag) {
		this.sub1FishedFlag = sub1FishedFlag;
	}

	public Date getSub2ExamDate() {
		return sub2ExamDate;
	}

	public void setSub2ExamDate(Date sub2ExamDate) {
		this.sub2ExamDate = sub2ExamDate;
	}

	public Integer getSub2Score() {
		return sub2Score;
	}

	public void setSub2Score(Integer sub2Score) {
		this.sub2Score = sub2Score;
	}

	public Integer getSub2FishedFlag() {
		return sub2FishedFlag;
	}

	public void setSub2FishedFlag(Integer sub2FishedFlag) {
		this.sub2FishedFlag = sub2FishedFlag;
	}

	public Date getSub3ExamDate() {
		return sub3ExamDate;
	}

	public void setSub3ExamDate(Date sub3ExamDate) {
		this.sub3ExamDate = sub3ExamDate;
	}

	public Integer getSub3Score() {
		return sub3Score;
	}

	public void setSub3Score(Integer sub3Score) {
		this.sub3Score = sub3Score;
	}

	public Integer getSub3FishedFlag() {
		return sub3FishedFlag;
	}

	public void setSub3FishedFlag(Integer sub3FishedFlag) {
		this.sub3FishedFlag = sub3FishedFlag;
	}

	public Date getSub4ExamDate() {
		return sub4ExamDate;
	}

	public void setSub4ExamDate(Date sub4ExamDate) {
		this.sub4ExamDate = sub4ExamDate;
	}

	public Integer getSub4Score() {
		return sub4Score;
	}

	public void setSub4Score(Integer sub4Score) {
		this.sub4Score = sub4Score;
	}

	public Integer getSub4FishedFlag() {
		return sub4FishedFlag;
	}

	public void setSub4FishedFlag(Integer sub4FishedFlag) {
		this.sub4FishedFlag = sub4FishedFlag;
	}

	public Date getSub5ExamDate() {
		return sub5ExamDate;
	}

	public void setSub5ExamDate(Date sub5ExamDate) {
		this.sub5ExamDate = sub5ExamDate;
	}

	public Integer getSub5Score() {
		return sub5Score;
	}

	public void setSub5Score(Integer sub5Score) {
		this.sub5Score = sub5Score;
	}

	public Integer getSub5FishedFlag() {
		return sub5FishedFlag;
	}

	public void setSub5FishedFlag(Integer sub5FishedFlag) {
		this.sub5FishedFlag = sub5FishedFlag;
	}

	public Date getExamDateStart() {
		return examDateStart;
	}

	public void setExamDateStart(Date examDateStart) {
		this.examDateStart = examDateStart;
	}

	public Date getExamDateEnd() {
		return examDateEnd;
	}

	public void setExamDateEnd(Date examDateEnd) {
		this.examDateEnd = examDateEnd;
	}

	public String getSchoolAreaName() {
		return schoolAreaName;
	}

	public void setSchoolAreaName(String schoolAreaName) {
		this.schoolAreaName = schoolAreaName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getExamAnalysId() {
		return examAnalysId;
	}

	public void setExamAnalysId(String examAnalysId) {
		this.examAnalysId = examAnalysId;
	}

	public String getStudentExamIds() {
		return studentExamIds;
	}

	public void setStudentExamIds(String studentExamIds) {
		this.studentExamIds = studentExamIds;
	}

	public Integer getMoveTotal() {
		return moveTotal;
	}

	public void setMoveTotal(Integer moveTotal) {
		this.moveTotal = moveTotal;
	}

	public Integer getSub1ExamFlag() {
		return sub1ExamFlag;
	}

	public void setSub1ExamFlag(Integer sub1ExamFlag) {
		this.sub1ExamFlag = sub1ExamFlag;
	}

	public Integer getSub2ExamFlag() {
		return sub2ExamFlag;
	}

	public void setSub2ExamFlag(Integer sub2ExamFlag) {
		this.sub2ExamFlag = sub2ExamFlag;
	}

	public Integer getSub3ExamFlag() {
		return sub3ExamFlag;
	}

	public void setSub3ExamFlag(Integer sub3ExamFlag) {
		this.sub3ExamFlag = sub3ExamFlag;
	}

	public Integer getSub4ExamFlag() {
		return sub4ExamFlag;
	}

	public void setSub4ExamFlag(Integer sub4ExamFlag) {
		this.sub4ExamFlag = sub4ExamFlag;
	}

	public Integer getSub5ExamFlag() {
		return sub5ExamFlag;
	}

	public void setSub5ExamFlag(Integer sub5ExamFlag) {
		this.sub5ExamFlag = sub5ExamFlag;
	}

	public Date getSub1MakeupDate() {
		return sub1MakeupDate;
	}

	public void setSub1MakeupDate(Date sub1MakeupDate) {
		this.sub1MakeupDate = sub1MakeupDate;
	}

	public Integer getSub1MakeupScore() {
		return sub1MakeupScore;
	}

	public void setSub1MakeupScore(Integer sub1MakeupScore) {
		this.sub1MakeupScore = sub1MakeupScore;
	}

	public Integer getSub1MakeupTimes() {
		return sub1MakeupTimes;
	}

	public void setSub1MakeupTimes(Integer sub1MakeupTimes) {
		this.sub1MakeupTimes = sub1MakeupTimes;
	}

	public Date getSub2MakeupDate() {
		return sub2MakeupDate;
	}

	public void setSub2MakeupDate(Date sub2MakeupDate) {
		this.sub2MakeupDate = sub2MakeupDate;
	}

	public Integer getSub2MakeupScore() {
		return sub2MakeupScore;
	}

	public void setSub2MakeupScore(Integer sub2MakeupScore) {
		this.sub2MakeupScore = sub2MakeupScore;
	}

	public Integer getSub2MakeupTimes() {
		return sub2MakeupTimes;
	}

	public void setSub2MakeupTimes(Integer sub2MakeupTimes) {
		this.sub2MakeupTimes = sub2MakeupTimes;
	}

	public Date getSub3MakeupDate() {
		return sub3MakeupDate;
	}

	public void setSub3MakeupDate(Date sub3MakeupDate) {
		this.sub3MakeupDate = sub3MakeupDate;
	}

	public Integer getSub3MakeupScore() {
		return sub3MakeupScore;
	}

	public void setSub3MakeupScore(Integer sub3MakeupScore) {
		this.sub3MakeupScore = sub3MakeupScore;
	}

	public Integer getSub3MakeupTimes() {
		return sub3MakeupTimes;
	}

	public void setSub3MakeupTimes(Integer sub3MakeupTimes) {
		this.sub3MakeupTimes = sub3MakeupTimes;
	}

	public Date getSub4MakeupDate() {
		return sub4MakeupDate;
	}

	public void setSub4MakeupDate(Date sub4MakeupDate) {
		this.sub4MakeupDate = sub4MakeupDate;
	}

	public Integer getSub4MakeupScore() {
		return sub4MakeupScore;
	}

	public void setSub4MakeupScore(Integer sub4MakeupScore) {
		this.sub4MakeupScore = sub4MakeupScore;
	}

	public Integer getSub4MakeupTimes() {
		return sub4MakeupTimes;
	}

	public void setSub4MakeupTimes(Integer sub4MakeupTimes) {
		this.sub4MakeupTimes = sub4MakeupTimes;
	}

	public Date getSub5MakeupDate() {
		return sub5MakeupDate;
	}

	public void setSub5MakeupDate(Date sub5MakeupDate) {
		this.sub5MakeupDate = sub5MakeupDate;
	}

	public Integer getSub5MakeupScore() {
		return sub5MakeupScore;
	}

	public void setSub5MakeupScore(Integer sub5MakeupScore) {
		this.sub5MakeupScore = sub5MakeupScore;
	}

	public Integer getSub5MakeupTimes() {
		return sub5MakeupTimes;
	}

	public void setSub5MakeupTimes(Integer sub5MakeupTimes) {
		this.sub5MakeupTimes = sub5MakeupTimes;
	}

	public Integer getSub1MissExamFlag() {
		return sub1MissExamFlag;
	}

	public void setSub1MissExamFlag(Integer sub1MissExamFlag) {
		this.sub1MissExamFlag = sub1MissExamFlag;
	}

	public Integer getSub2MissExamFlag() {
		return sub2MissExamFlag;
	}

	public void setSub2MissExamFlag(Integer sub2MissExamFlag) {
		this.sub2MissExamFlag = sub2MissExamFlag;
	}

	public Integer getSub3MissExamFlag() {
		return sub3MissExamFlag;
	}

	public void setSub3MissExamFlag(Integer sub3MissExamFlag) {
		this.sub3MissExamFlag = sub3MissExamFlag;
	}

	public Integer getSub4MissExamFlag() {
		return sub4MissExamFlag;
	}

	public void setSub4MissExamFlag(Integer sub4MissExamFlag) {
		this.sub4MissExamFlag = sub4MissExamFlag;
	}

	public Integer getSub5MissExamFlag() {
		return sub5MissExamFlag;
	}

	public void setSub5MissExamFlag(Integer sub5MissExamFlag) {
		this.sub5MissExamFlag = sub5MissExamFlag;
	}

	public Integer getSub1ApplyFlag() {
		return sub1ApplyFlag;
	}

	public void setSub1ApplyFlag(Integer sub1ApplyFlag) {
		this.sub1ApplyFlag = sub1ApplyFlag;
	}

	public Integer getSub2ApplyFlag() {
		return sub2ApplyFlag;
	}

	public void setSub2ApplyFlag(Integer sub2ApplyFlag) {
		this.sub2ApplyFlag = sub2ApplyFlag;
	}

	public Integer getSub3ApplyFlag() {
		return sub3ApplyFlag;
	}

	public void setSub3ApplyFlag(Integer sub3ApplyFlag) {
		this.sub3ApplyFlag = sub3ApplyFlag;
	}

	public Integer getSub4ApplyFlag() {
		return sub4ApplyFlag;
	}

	public void setSub4ApplyFlag(Integer sub4ApplyFlag) {
		this.sub4ApplyFlag = sub4ApplyFlag;
	}

	public Integer getSub5ApplyFlag() {
		return sub5ApplyFlag;
	}

	public void setSub5ApplyFlag(Integer sub5ApplyFlag) {
		this.sub5ApplyFlag = sub5ApplyFlag;
	}

	public Integer getSub1EditAbleFlag() {
		return sub1EditAbleFlag;
	}

	public void setSub1EditAbleFlag(Integer sub1EditAbleFlag) {
		this.sub1EditAbleFlag = sub1EditAbleFlag;
	}

	public Integer getSub2EditAbleFlag() {
		return sub2EditAbleFlag;
	}

	public void setSub2EditAbleFlag(Integer sub2EditAbleFlag) {
		this.sub2EditAbleFlag = sub2EditAbleFlag;
	}

	public Integer getSub3EditAbleFlag() {
		return sub3EditAbleFlag;
	}

	public void setSub3EditAbleFlag(Integer sub3EditAbleFlag) {
		this.sub3EditAbleFlag = sub3EditAbleFlag;
	}

	public Integer getSub4EditAbleFlag() {
		return sub4EditAbleFlag;
	}

	public void setSub4EditAbleFlag(Integer sub4EditAbleFlag) {
		this.sub4EditAbleFlag = sub4EditAbleFlag;
	}

	public Integer getSub5EditAbleFlag() {
		return sub5EditAbleFlag;
	}

	public void setSub5EditAbleFlag(Integer sub5EditAbleFlag) {
		this.sub5EditAbleFlag = sub5EditAbleFlag;
	}

	public Integer getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(Integer subjectType) {
		this.subjectType = subjectType;
	}

	public Integer getExamScore() {
		return examScore;
	}

	public void setExamScore(Integer examScore) {
		this.examScore = examScore;
	}

	public Integer getBatchFlag() {
		return batchFlag;
	}

	public void setBatchFlag(Integer batchFlag) {
		this.batchFlag = batchFlag;
	}

	public Integer getExamScoreFlag() {
		return examScoreFlag;
	}

	public void setExamScoreFlag(Integer examScoreFlag) {
		this.examScoreFlag = examScoreFlag;
	}

	public Integer getDatagridSubjectType() {
		return datagridSubjectType;
	}

	public void setDatagridSubjectType(Integer datagridSubjectType) {
		this.datagridSubjectType = datagridSubjectType;
	}

	public Integer getImportOrder() {
		return importOrder;
	}

	public void setImportOrder(Integer importOrder) {
		this.importOrder = importOrder;
	}

	public String getIdentityId() {
		return identityId;
	}

	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}

	public Integer getRepeartExamFlag() {
		return repeartExamFlag;
	}

	public void setRepeartExamFlag(Integer repeartExamFlag) {
		this.repeartExamFlag = repeartExamFlag;
	}

	public TexamAnalys getExamAnalys() {
		return examAnalys;
	}

	public void setExamAnalys(TexamAnalys examAnalys) {
		this.examAnalys = examAnalys;
	}

	public Integer getDriverLicenseFlag() {
		return driverLicenseFlag;
	}

	public void setDriverLicenseFlag(Integer driverLicenseFlag) {
		this.driverLicenseFlag = driverLicenseFlag;
	}

	public Integer getqLicenseFlag() {
		return qLicenseFlag;
	}

	public void setqLicenseFlag(Integer qLicenseFlag) {
		this.qLicenseFlag = qLicenseFlag;
	}

	public Integer getDriverLicenseFinishFlag() {
		return driverLicenseFinishFlag;
	}

	public void setDriverLicenseFinishFlag(Integer driverLicenseFinishFlag) {
		this.driverLicenseFinishFlag = driverLicenseFinishFlag;
	}

	public Integer getDriverGrantFlag() {
		return driverGrantFlag;
	}

	public void setDriverGrantFlag(Integer driverGrantFlag) {
		this.driverGrantFlag = driverGrantFlag;
	}

	public Integer getqGrantFlag() {
		return qGrantFlag;
	}

	public void setqGrantFlag(Integer qGrantFlag) {
		this.qGrantFlag = qGrantFlag;
	}

	public Integer getqLicenseFinishFlag() {
		return qLicenseFinishFlag;
	}

	public void setqLicenseFinishFlag(Integer qLicenseFinishFlag) {
		this.qLicenseFinishFlag = qLicenseFinishFlag;
	}

}

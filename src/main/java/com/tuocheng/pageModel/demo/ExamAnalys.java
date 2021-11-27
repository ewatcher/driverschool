package com.tuocheng.pageModel.demo;

import java.util.Date;

import com.tuocheng.pageModel.base.StudentPageModel;

public class ExamAnalys extends StudentPageModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8874272591219377415L;
	private String id;// 标识
	private String schoolArea;// 校区标识
	private Integer batch;// 批次
	private Long total;// 该批学员总数
	private Date createDate;// 创建日期
	private Date subject1ExamDate;// 科一考试日期
	private Integer subject1HandupTotal;// 科一报名人数
	private Integer subject1ApproveTotal;// 科一通过审批人数
	private Integer subject1RealExamTotal;// 科一实考人数
	private Integer subject1MissExamTotal;// 科一缺考
	private Integer subject1PassExamTotal;// 科一合格人数
	private Date subject2ExamDate;// 科二考试日期
	private Integer subject2HandupTotal;// 科二报名人数
	private Integer subject2ApproveTotal;// 科二通过审批人数
	private Integer subject2RealExamTotal;// 科二实考人数
	private Integer subject2MissExamTotal;// 科二缺考
	private Integer subject2PassExamTotal;// 科二合格人数
	private Date subject3ExamDate;// 科三考试日期
	private Integer subject3HandupTotal;// 科三报名人数
	private Integer subject3ApproveTotal;// 科三通过审批人数
	private Integer subject3RealExamTotal;// 科三实考人数
	private Integer subject3MissExamTotal;// 科三缺考
	private Integer subject3PassExamTotal;// 科三合格人数
	private Date subject4ExamDate;// 科四考试日期
	private Integer subject4HandupTotal;// 科四报名人数
	private Integer subject4ApproveTotal;// 科四通过审批人数
	private Integer subject4RealExamTotal;// 科四实考人数
	private Integer subject4MissExamTotal;// 科四缺考
	private Integer subject4PassExamTotal;// 科四合格人数
	private Date subject5ExamDate;// 科五考试日期
	private Integer subject5HandupTotal;// 科五报名人数
	private Integer subject5ApproveTotal;// 科五通过审批人数
	private Integer subject5RealExamTotal;// 科五实考人数
	private Integer subject5MissExamTotal;// 科五缺考
	private Integer subject5PassExamTotal;// 科五合格人数

	private String selectSchoolArea;// 超级用户选择校区值
	private String schoolAreaName;// 校区标识名称
	private Integer subjectType;

	private String ids;

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

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Date getSubject1ExamDate() {
		return subject1ExamDate;
	}

	public void setSubject1ExamDate(Date subject1ExamDate) {
		this.subject1ExamDate = subject1ExamDate;
	}

	public Integer getSubject1HandupTotal() {
		return subject1HandupTotal;
	}

	public void setSubject1HandupTotal(Integer subject1HandupTotal) {
		this.subject1HandupTotal = subject1HandupTotal;
	}

	public Integer getSubject1ApproveTotal() {
		return subject1ApproveTotal;
	}

	public void setSubject1ApproveTotal(Integer subject1ApproveTotal) {
		this.subject1ApproveTotal = subject1ApproveTotal;
	}

	public Integer getSubject1RealExamTotal() {
		return subject1RealExamTotal;
	}

	public void setSubject1RealExamTotal(Integer subject1RealExamTotal) {
		this.subject1RealExamTotal = subject1RealExamTotal;
	}

	public Integer getSubject1MissExamTotal() {
		return subject1MissExamTotal;
	}

	public void setSubject1MissExamTotal(Integer subject1MissExamTotal) {
		this.subject1MissExamTotal = subject1MissExamTotal;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getSubject1PassExamTotal() {
		return subject1PassExamTotal;
	}

	public void setSubject1PassExamTotal(Integer subject1PassExamTotal) {
		this.subject1PassExamTotal = subject1PassExamTotal;
	}

	public Date getSubject2ExamDate() {
		return subject2ExamDate;
	}

	public void setSubject2ExamDate(Date subject2ExamDate) {
		this.subject2ExamDate = subject2ExamDate;
	}

	public Integer getSubject2HandupTotal() {
		return subject2HandupTotal;
	}

	public void setSubject2HandupTotal(Integer subject2HandupTotal) {
		this.subject2HandupTotal = subject2HandupTotal;
	}

	public String getSelectSchoolArea() {
		return selectSchoolArea;
	}

	public void setSelectSchoolArea(String selectSchoolArea) {
		this.selectSchoolArea = selectSchoolArea;
	}

	public Integer getSubject2ApproveTotal() {
		return subject2ApproveTotal;
	}

	public void setSubject2ApproveTotal(Integer subject2ApproveTotal) {
		this.subject2ApproveTotal = subject2ApproveTotal;
	}

	public Integer getSubject2RealExamTotal() {
		return subject2RealExamTotal;
	}

	public void setSubject2RealExamTotal(Integer subject2RealExamTotal) {
		this.subject2RealExamTotal = subject2RealExamTotal;
	}

	public Integer getSubject2MissExamTotal() {
		return subject2MissExamTotal;
	}

	public void setSubject2MissExamTotal(Integer subject2MissExamTotal) {
		this.subject2MissExamTotal = subject2MissExamTotal;
	}

	public Integer getSubject2PassExamTotal() {
		return subject2PassExamTotal;
	}

	public void setSubject2PassExamTotal(Integer subject2PassExamTotal) {
		this.subject2PassExamTotal = subject2PassExamTotal;
	}

	public Date getSubject3ExamDate() {
		return subject3ExamDate;
	}

	public void setSubject3ExamDate(Date subject3ExamDate) {
		this.subject3ExamDate = subject3ExamDate;
	}

	public Integer getSubject3HandupTotal() {
		return subject3HandupTotal;
	}

	public void setSubject3HandupTotal(Integer subject3HandupTotal) {
		this.subject3HandupTotal = subject3HandupTotal;
	}

	public Integer getSubject3ApproveTotal() {
		return subject3ApproveTotal;
	}

	public void setSubject3ApproveTotal(Integer subject3ApproveTotal) {
		this.subject3ApproveTotal = subject3ApproveTotal;
	}

	public Integer getSubject3RealExamTotal() {
		return subject3RealExamTotal;
	}

	public void setSubject3RealExamTotal(Integer subject3RealExamTotal) {
		this.subject3RealExamTotal = subject3RealExamTotal;
	}

	public Integer getSubject3MissExamTotal() {
		return subject3MissExamTotal;
	}

	public void setSubject3MissExamTotal(Integer subject3MissExamTotal) {
		this.subject3MissExamTotal = subject3MissExamTotal;
	}

	public Integer getSubject3PassExamTotal() {
		return subject3PassExamTotal;
	}

	public void setSubject3PassExamTotal(Integer subject3PassExamTotal) {
		this.subject3PassExamTotal = subject3PassExamTotal;
	}

	public Date getSubject4ExamDate() {
		return subject4ExamDate;
	}

	public void setSubject4ExamDate(Date subject4ExamDate) {
		this.subject4ExamDate = subject4ExamDate;
	}

	public Integer getSubject4HandupTotal() {
		return subject4HandupTotal;
	}

	public void setSubject4HandupTotal(Integer subject4HandupTotal) {
		this.subject4HandupTotal = subject4HandupTotal;
	}

	public Integer getSubject4ApproveTotal() {
		return subject4ApproveTotal;
	}

	public void setSubject4ApproveTotal(Integer subject4ApproveTotal) {
		this.subject4ApproveTotal = subject4ApproveTotal;
	}

	public Integer getSubject4RealExamTotal() {
		return subject4RealExamTotal;
	}

	public void setSubject4RealExamTotal(Integer subject4RealExamTotal) {
		this.subject4RealExamTotal = subject4RealExamTotal;
	}

	public Integer getSubject4MissExamTotal() {
		return subject4MissExamTotal;
	}

	public void setSubject4MissExamTotal(Integer subject4MissExamTotal) {
		this.subject4MissExamTotal = subject4MissExamTotal;
	}

	public Integer getSubject4PassExamTotal() {
		return subject4PassExamTotal;
	}

	public void setSubject4PassExamTotal(Integer subject4PassExamTotal) {
		this.subject4PassExamTotal = subject4PassExamTotal;
	}

	public Date getSubject5ExamDate() {
		return subject5ExamDate;
	}

	public void setSubject5ExamDate(Date subject5ExamDate) {
		this.subject5ExamDate = subject5ExamDate;
	}

	public Integer getSubject5HandupTotal() {
		return subject5HandupTotal;
	}

	public void setSubject5HandupTotal(Integer subject5HandupTotal) {
		this.subject5HandupTotal = subject5HandupTotal;
	}

	public Integer getSubject5ApproveTotal() {
		return subject5ApproveTotal;
	}

	public void setSubject5ApproveTotal(Integer subject5ApproveTotal) {
		this.subject5ApproveTotal = subject5ApproveTotal;
	}

	public Integer getSubject5RealExamTotal() {
		return subject5RealExamTotal;
	}

	public void setSubject5RealExamTotal(Integer subject5RealExamTotal) {
		this.subject5RealExamTotal = subject5RealExamTotal;
	}

	public Integer getSubject5MissExamTotal() {
		return subject5MissExamTotal;
	}

	public void setSubject5MissExamTotal(Integer subject5MissExamTotal) {
		this.subject5MissExamTotal = subject5MissExamTotal;
	}

	public Integer getSubject5PassExamTotal() {
		return subject5PassExamTotal;
	}

	public void setSubject5PassExamTotal(Integer subject5PassExamTotal) {
		this.subject5PassExamTotal = subject5PassExamTotal;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getSchoolAreaName() {
		return schoolAreaName;
	}

	public void setSchoolAreaName(String schoolAreaName) {
		this.schoolAreaName = schoolAreaName;
	}

	public Integer getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(Integer subjectType) {
		this.subjectType = subjectType;
	}

}

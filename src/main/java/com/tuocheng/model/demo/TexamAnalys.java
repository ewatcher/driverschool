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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author 农峰
 * 
 */
@Entity
@Table(name = "tb_examAnalys", schema = "")
public class TexamAnalys implements Serializable {
	private static final long serialVersionUID = 3854691675246066062L;
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

	// ==================relationtrip config==================
	private Set<TstudentExam> studentExams = new HashSet<TstudentExam>(0);

	public TexamAnalys() {
	}

	public TexamAnalys(String id, String schoolArea, Integer batch, Long total,
			Date createDate, Date subject1ExamDate,
			Integer subject1HandupTotal, Integer subject1ApproveTotal,
			Integer subject1RealExamTotal, Integer subject1MissExamTotal,
			Integer subject1PassExamTotal, Date subject2ExamDate,
			Integer subject2HandupTotal, Integer subject2ApproveTotal,
			Integer subject2RealExamTotal, Integer subject2MissExamTotal,
			Integer subject2PassExamTotal, Date subject3ExamDate,
			Integer subject3HandupTotal, Integer subject3ApproveTotal,
			Integer subject3RealExamTotal, Integer subject3MissExamTotal,
			Integer subject3PassExamTotal, Date subject4ExamDate,
			Integer subject4HandupTotal, Integer subject4ApproveTotal,
			Integer subject4RealExamTotal, Integer subject4MissExamTotal,
			Integer subject4PassExamTotal, Date subject5ExamDate,
			Integer subject5HandupTotal, Integer subject5ApproveTotal,
			Integer subject5RealExamTotal, Integer subject5MissExamTotal,
			Integer subject5PassExamTotal, Set<TstudentExam> studentExams) {
		this.id = id;
		this.schoolArea = schoolArea;
		this.batch = batch;
		this.total = total;
		this.createDate = createDate;
		this.subject1ExamDate = subject1ExamDate;
		this.subject1HandupTotal = subject1HandupTotal;
		this.subject1ApproveTotal = subject1ApproveTotal;
		this.subject1RealExamTotal = subject1RealExamTotal;
		this.subject1MissExamTotal = subject1MissExamTotal;
		this.subject1PassExamTotal = subject1PassExamTotal;
		this.subject2ExamDate = subject2ExamDate;
		this.subject2HandupTotal = subject2HandupTotal;
		this.subject2ApproveTotal = subject2ApproveTotal;
		this.subject2RealExamTotal = subject2RealExamTotal;
		this.subject2MissExamTotal = subject2MissExamTotal;
		this.subject2PassExamTotal = subject2PassExamTotal;
		this.subject3ExamDate = subject3ExamDate;
		this.subject3HandupTotal = subject3HandupTotal;
		this.subject3ApproveTotal = subject3ApproveTotal;
		this.subject3RealExamTotal = subject3RealExamTotal;
		this.subject3MissExamTotal = subject3MissExamTotal;
		this.subject3PassExamTotal = subject3PassExamTotal;
		this.subject4ExamDate = subject4ExamDate;
		this.subject4HandupTotal = subject4HandupTotal;
		this.subject4ApproveTotal = subject4ApproveTotal;
		this.subject4RealExamTotal = subject4RealExamTotal;
		this.subject4MissExamTotal = subject4MissExamTotal;
		this.subject4PassExamTotal = subject4PassExamTotal;
		this.subject5ExamDate = subject5ExamDate;
		this.subject5HandupTotal = subject5HandupTotal;
		this.subject5ApproveTotal = subject5ApproveTotal;
		this.subject5RealExamTotal = subject5RealExamTotal;
		this.subject5MissExamTotal = subject5MissExamTotal;
		this.subject5PassExamTotal = subject5PassExamTotal;
		this.studentExams = studentExams;
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

	@Column(name = "batch", columnDefinition = "INT DEFAULT 0")
	public Integer getBatch() {
		return batch;
	}

	public void setBatch(Integer batch) {
		this.batch = batch;
	}

	@Column(name = "total", columnDefinition = "LONG DEFAULT 0")
	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "subject1ExamDate", length = 7)
	public Date getSubject1ExamDate() {
		return subject1ExamDate;
	}

	public void setSubject1ExamDate(Date subject1ExamDate) {
		this.subject1ExamDate = subject1ExamDate;
	}

	@Column(name = "subject1HandupTotal", columnDefinition = "INT DEFAULT 0")
	public Integer getSubject1HandupTotal() {
		return subject1HandupTotal;
	}

	public void setSubject1HandupTotal(Integer subject1HandupTotal) {
		this.subject1HandupTotal = subject1HandupTotal;
	}

	@Column(name = "subject1ApproveTotal", columnDefinition = "INT DEFAULT 0")
	public Integer getSubject1ApproveTotal() {
		return subject1ApproveTotal;
	}

	public void setSubject1ApproveTotal(Integer subject1ApproveTotal) {
		this.subject1ApproveTotal = subject1ApproveTotal;
	}

	@Column(name = "subject1RealExamTotal", columnDefinition = "INT DEFAULT 0")
	public Integer getSubject1RealExamTotal() {
		return subject1RealExamTotal;
	}

	public void setSubject1RealExamTotal(Integer subject1RealExamTotal) {
		this.subject1RealExamTotal = subject1RealExamTotal;
	}

	@Column(name = "subject1MissExamTotal", columnDefinition = "INT DEFAULT 0")
	public Integer getSubject1MissExamTotal() {
		return subject1MissExamTotal;
	}

	public void setSubject1MissExamTotal(Integer subject1MissExamTotal) {
		this.subject1MissExamTotal = subject1MissExamTotal;
	}

	@Column(name = "subject1PassExamTotal", columnDefinition = "INT DEFAULT 0")
	public Integer getSubject1PassExamTotal() {
		return subject1PassExamTotal;
	}

	public void setSubject1PassExamTotal(Integer subject1PassExamTotal) {
		this.subject1PassExamTotal = subject1PassExamTotal;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "subject2ExamDate", length = 7)
	public Date getSubject2ExamDate() {
		return subject2ExamDate;
	}

	public void setSubject2ExamDate(Date subject2ExamDate) {
		this.subject2ExamDate = subject2ExamDate;
	}

	@Column(name = "subject2HandupTotal", columnDefinition = "INT DEFAULT 0")
	public Integer getSubject2HandupTotal() {
		return subject2HandupTotal;
	}

	public void setSubject2HandupTotal(Integer subject2HandupTotal) {
		this.subject2HandupTotal = subject2HandupTotal;
	}

	@Column(name = "subject2ApproveTotal", columnDefinition = "INT DEFAULT 0")
	public Integer getSubject2ApproveTotal() {
		return subject2ApproveTotal;
	}

	public void setSubject2ApproveTotal(Integer subject2ApproveTotal) {
		this.subject2ApproveTotal = subject2ApproveTotal;
	}

	@Column(name = "subject2RealExamTotal", columnDefinition = "INT DEFAULT 0")
	public Integer getSubject2RealExamTotal() {
		return subject2RealExamTotal;
	}

	public void setSubject2RealExamTotal(Integer subject2RealExamTotal) {
		this.subject2RealExamTotal = subject2RealExamTotal;
	}

	@Column(name = "subject2MissExamTotal", columnDefinition = "INT DEFAULT 0")
	public Integer getSubject2MissExamTotal() {
		return subject2MissExamTotal;
	}

	public void setSubject2MissExamTotal(Integer subject2MissExamTotal) {
		this.subject2MissExamTotal = subject2MissExamTotal;
	}

	@Column(name = "subject2PassExamTotal", columnDefinition = "INT DEFAULT 0")
	public Integer getSubject2PassExamTotal() {
		return subject2PassExamTotal;
	}

	public void setSubject2PassExamTotal(Integer subject2PassExamTotal) {
		this.subject2PassExamTotal = subject2PassExamTotal;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "subject3ExamDate", length = 7)
	public Date getSubject3ExamDate() {
		return subject3ExamDate;
	}

	public void setSubject3ExamDate(Date subject3ExamDate) {
		this.subject3ExamDate = subject3ExamDate;
	}

	@Column(name = "subject3HandupTotal", columnDefinition = "INT DEFAULT 0")
	public Integer getSubject3HandupTotal() {
		return subject3HandupTotal;
	}

	public void setSubject3HandupTotal(Integer subject3HandupTotal) {
		this.subject3HandupTotal = subject3HandupTotal;
	}

	@Column(name = "subject3ApproveTotal", columnDefinition = "INT DEFAULT 0")
	public Integer getSubject3ApproveTotal() {
		return subject3ApproveTotal;
	}

	public void setSubject3ApproveTotal(Integer subject3ApproveTotal) {
		this.subject3ApproveTotal = subject3ApproveTotal;
	}

	@Column(name = "subject3RealExamTotal", columnDefinition = "INT DEFAULT 0")
	public Integer getSubject3RealExamTotal() {
		return subject3RealExamTotal;
	}

	public void setSubject3RealExamTotal(Integer subject3RealExamTotal) {
		this.subject3RealExamTotal = subject3RealExamTotal;
	}

	@Column(name = "subject3MissExamTotal", columnDefinition = "INT DEFAULT 0")
	public Integer getSubject3MissExamTotal() {
		return subject3MissExamTotal;
	}

	public void setSubject3MissExamTotal(Integer subject3MissExamTotal) {
		this.subject3MissExamTotal = subject3MissExamTotal;
	}

	@Column(name = "subject3PassExamTotal", columnDefinition = "INT DEFAULT 0")
	public Integer getSubject3PassExamTotal() {
		return subject3PassExamTotal;
	}

	public void setSubject3PassExamTotal(Integer subject3PassExamTotal) {
		this.subject3PassExamTotal = subject3PassExamTotal;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "subject4ExamDate", length = 7)
	public Date getSubject4ExamDate() {
		return subject4ExamDate;
	}

	public void setSubject4ExamDate(Date subject4ExamDate) {
		this.subject4ExamDate = subject4ExamDate;
	}

	@Column(name = "subject4HandupTotal", columnDefinition = "INT DEFAULT 0")
	public Integer getSubject4HandupTotal() {
		return subject4HandupTotal;
	}

	public void setSubject4HandupTotal(Integer subject4HandupTotal) {
		this.subject4HandupTotal = subject4HandupTotal;
	}

	@Column(name = "subject4ApproveTotal", columnDefinition = "INT DEFAULT 0")
	public Integer getSubject4ApproveTotal() {
		return subject4ApproveTotal;
	}

	public void setSubject4ApproveTotal(Integer subject4ApproveTotal) {
		this.subject4ApproveTotal = subject4ApproveTotal;
	}

	@Column(name = "subject4RealExamTotal", columnDefinition = "INT DEFAULT 0")
	public Integer getSubject4RealExamTotal() {
		return subject4RealExamTotal;
	}

	public void setSubject4RealExamTotal(Integer subject4RealExamTotal) {
		this.subject4RealExamTotal = subject4RealExamTotal;
	}

	@Column(name = "subject4MissExamTotal", columnDefinition = "INT DEFAULT 0")
	public Integer getSubject4MissExamTotal() {
		return subject4MissExamTotal;
	}

	public void setSubject4MissExamTotal(Integer subject4MissExamTotal) {
		this.subject4MissExamTotal = subject4MissExamTotal;
	}

	@Column(name = "subject4PassExamTotal", columnDefinition = "INT DEFAULT 0")
	public Integer getSubject4PassExamTotal() {
		return subject4PassExamTotal;
	}

	public void setSubject4PassExamTotal(Integer subject4PassExamTotal) {
		this.subject4PassExamTotal = subject4PassExamTotal;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "subject5ExamDate", length = 7)
	public Date getSubject5ExamDate() {
		return subject5ExamDate;
	}

	public void setSubject5ExamDate(Date subject5ExamDate) {
		this.subject5ExamDate = subject5ExamDate;
	}

	@Column(name = "subject5HandupTotal", columnDefinition = "INT DEFAULT 0")
	public Integer getSubject5HandupTotal() {
		return subject5HandupTotal;
	}

	public void setSubject5HandupTotal(Integer subject5HandupTotal) {
		this.subject5HandupTotal = subject5HandupTotal;
	}

	@Column(name = "subject5ApproveTotal", columnDefinition = "INT DEFAULT 0")
	public Integer getSubject5ApproveTotal() {
		return subject5ApproveTotal;
	}

	public void setSubject5ApproveTotal(Integer subject5ApproveTotal) {
		this.subject5ApproveTotal = subject5ApproveTotal;
	}

	@Column(name = "subject5RealExamTotal", columnDefinition = "INT DEFAULT 0")
	public Integer getSubject5RealExamTotal() {
		return subject5RealExamTotal;
	}

	public void setSubject5RealExamTotal(Integer subject5RealExamTotal) {
		this.subject5RealExamTotal = subject5RealExamTotal;
	}

	@Column(name = "subject5MissExamTotal", columnDefinition = "INT DEFAULT 0")
	public Integer getSubject5MissExamTotal() {
		return subject5MissExamTotal;
	}

	public void setSubject5MissExamTotal(Integer subject5MissExamTotal) {
		this.subject5MissExamTotal = subject5MissExamTotal;
	}

	@Column(name = "subject5PassExamTotal", columnDefinition = "INT DEFAULT 0")
	public Integer getSubject5PassExamTotal() {
		return subject5PassExamTotal;
	}

	public void setSubject5PassExamTotal(Integer subject5PassExamTotal) {
		this.subject5PassExamTotal = subject5PassExamTotal;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createDate", length = 7)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "examAnalys")
	public Set<TstudentExam> getStudentExams() {
		return studentExams;
	}

	public void setStudentExams(Set<TstudentExam> studentExams) {
		this.studentExams = studentExams;
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
		TexamAnalys other = (TexamAnalys) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TexamAnalys [id=" + id + ", schoolArea=" + schoolArea
				+ ", batch=" + batch + ", total=" + total
				+ ", subject1ExamDate=" + subject1ExamDate
				+ ", subject1HandupTotal=" + subject1HandupTotal
				+ ", subject1ApproveTotal=" + subject1ApproveTotal
				+ ", subject1RealExamTotal=" + subject1RealExamTotal
				+ ", subject1MissExamTotal=" + subject1MissExamTotal
				+ ", subject1PassExamTotal=" + subject1PassExamTotal
				+ ", subject2ExamDate=" + subject2ExamDate
				+ ", subject2HandupTotal=" + subject2HandupTotal
				+ ", subject2ApproveTotal=" + subject2ApproveTotal
				+ ", subject2RealExamTotal=" + subject2RealExamTotal
				+ ", subject2MissExamTotal=" + subject2MissExamTotal
				+ ", subject2PassExamTotal=" + subject2PassExamTotal
				+ ", subject3ExamDate=" + subject3ExamDate
				+ ", subject3HandupTotal=" + subject3HandupTotal
				+ ", subject3ApproveTotal=" + subject3ApproveTotal
				+ ", subject3RealExamTotal=" + subject3RealExamTotal
				+ ", subject3MissExamTotal=" + subject3MissExamTotal
				+ ", subject3PassExamTotal=" + subject3PassExamTotal
				+ ", subject4ExamDate=" + subject4ExamDate
				+ ", subject4HandupTotal=" + subject4HandupTotal
				+ ", subject4ApproveTotal=" + subject4ApproveTotal
				+ ", subject4RealExamTotal=" + subject4RealExamTotal
				+ ", subject4MissExamTotal=" + subject4MissExamTotal
				+ ", subject4PassExamTotal=" + subject4PassExamTotal
				+ ", subject5ExamDate=" + subject5ExamDate
				+ ", subject5HandupTotal=" + subject5HandupTotal
				+ ", subject5ApproveTotal=" + subject5ApproveTotal
				+ ", subject5RealExamTotal=" + subject5RealExamTotal
				+ ", subject5MissExamTotal=" + subject5MissExamTotal
				+ ", subject5PassExamTotal=" + subject5PassExamTotal + "]";
	}
}

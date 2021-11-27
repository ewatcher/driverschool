package com.tuocheng.model.demo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 科目一考试实体类
 * 
 * @author 农峰
 * 
 */
@Entity
@Table(name = "tb_subject1", schema = "")
public class Tsubject1 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4180858264180054798L;
	private String id;
	private String schoolArea;
	private Integer batch;
	private String examAnalysId;
	private Integer subjectType;

	public Tsubject1() {
	}

	public Tsubject1(String id, String schoolArea, Integer batch,
			String examAnalysId, Integer subjectType) {
		super();
		this.id = id;
		this.schoolArea = schoolArea;
		this.batch = batch;
		this.examAnalysId = examAnalysId;
		this.subjectType = subjectType;
	}

	@Id
	@Column(name = "id", nullable = false, updatable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "schoolArea", length = 36)
	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	@Column(name = "batch", length = 8)
	public Integer getBatch() {
		return batch;
	}

	public void setBatch(Integer batch) {
		this.batch = batch;
	}

	@Column(name = "examAnalysId", length = 36)
	public String getExamAnalysId() {
		return examAnalysId;
	}

	public void setExamAnalysId(String examAnalysId) {
		this.examAnalysId = examAnalysId;
	}
	
	@Column(name = "subjectType", length = 4)
	public Integer getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(Integer subjectType) {
		this.subjectType = subjectType;
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
		Tsubject1 other = (Tsubject1) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tsubject1 [id=" + id + ", schoolArea=" + schoolArea
				+ ", batch=" + batch + ", examAnalysId=" + examAnalysId + "]";
	}

}

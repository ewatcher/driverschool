package com.tuocheng.model.demo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 学员点评实体模型(DAO专用)
 * 
 * @author 李杰
 * 
 */
@Entity
@Table(name = "tb_comment_student", schema = "")
public class TcommentStudent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9090317034590501716L;
	// ===========base properties==============
	private String id;
	private String studentId; // 学员ID
	private String trainId; // 教练ID
	private Date commentTime; // 点评的时间
	private String comments; // 点评
	private Integer isRead; // 已读？ 0,未读， 1：已读
	private Integer status; // 状态。

	// ==============relation properties==============
	public TcommentStudent() {
	}

	public TcommentStudent(String id, String studentId, String trainId,
			Date commentTime, String comments, Integer isRead, Integer status) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.trainId = trainId;
		this.commentTime = commentTime;
		this.comments = comments;
		this.isRead = isRead;
		this.status = status;
	}

	@Id
	@Column(name = "id", nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "studentId", nullable = false, length = 36)
	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	@Column(name = "trainId", nullable = false, length = 36)
	public String getTrainId() {
		return trainId;
	}

	public void setTrainId(String trainId) {
		this.trainId = trainId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "commentTime", length = 7)
	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	@Column(name = "comments", length = 10000)
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Column(name = "isRead", length = 11)
	public Integer getIsRead() {
		return isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

	@Column(name = "status", length = 11)
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
		TcommentStudent other = (TcommentStudent) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TcommentStudent [id=" + id + ", studentId=" + studentId
				+ ", trainId=" + trainId + ", commentTime=" + commentTime
				+ ", comments=" + comments + ", isRead=" + isRead + ", status="
				+ status + "]";
	}

}

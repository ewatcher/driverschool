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
 * 微信教练评价实体模型(DAO专用)
 * 
 * @author 李杰
 * 
 */
@Entity
@Table(name = "tb_assess", schema = "")
public class Tassess implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9090317034590501716L;
	// ===========base properties==============
	private String id;
	private String openId; // 用户微信ID
	private String trainerId; // 教练ID
	private String trainerName; // 教练ID
	private Integer stars; // 星级（1，很好； 2：一般； 3：差； 4：极差）
	private String evaluate; // 评价
	private Date createTime; // 创建时间时间
	private String comments; // 备注
	private Integer status; // 状态。

	private Integer serviceMark;
	private Integer efficientMark;
	private Integer dutyMark;
	private Integer groomingMark;

	// ==============relation properties==============
	public Tassess() {
	}

	public Tassess(String id, String openId, String trainerId, String trainerName, Integer stars, String evaluate,
			Date createTime, String comments, Integer status, Integer serviceMark, Integer efficientMark,
			Integer dutyMark, Integer groomingMark) {
		this.id = id;
		this.openId = openId;
		this.trainerId = trainerId;
		this.trainerName = trainerName;
		this.stars = stars;
		this.evaluate = evaluate;
		this.createTime = createTime;
		this.comments = comments;
		this.status = status;
		this.serviceMark = serviceMark;
		this.efficientMark = efficientMark;
		this.dutyMark = dutyMark;
		this.groomingMark = groomingMark;
	}

	@Id
	@Column(name = "id", nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "openId", length = 100)
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@Column(name = "trainerId", length = 36)
	public String getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(String trainerId) {
		this.trainerId = trainerId;
	}

	@Column(name = "stars", length = 11)
	public Integer getStars() {
		return stars;
	}

	public void setStars(Integer stars) {
		this.stars = stars;
	}

	@Column(name = "evaluate", length = 10000)
	public String getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createTime", length = 7)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "comments", length = 10000)
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Column(name = "status", length = 11)
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "trainerName", length = 32)
	public String getTrainerName() {
		return trainerName;
	}

	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}

	@Column(name = "serviceMark", length = 8, columnDefinition = "INT DEFAULT 0")
	public Integer getServiceMark() {
		return serviceMark;
	}

	public void setServiceMark(Integer serviceMark) {
		this.serviceMark = serviceMark;
	}

	@Column(name = "efficientMark", length = 8, columnDefinition = "INT DEFAULT 0")
	public Integer getEfficientMark() {
		return efficientMark;
	}

	public void setEfficientMark(Integer efficientMark) {
		this.efficientMark = efficientMark;
	}

	@Column(name = "dutyMark", length = 8, columnDefinition = "INT DEFAULT 0")
	public Integer getDutyMark() {
		return dutyMark;
	}

	public void setDutyMark(Integer dutyMark) {
		this.dutyMark = dutyMark;
	}

	@Column(name = "groomingMark", length = 8, columnDefinition = "INT DEFAULT 0")
	public Integer getGroomingMark() {
		return groomingMark;
	}

	public void setGroomingMark(Integer groomingMark) {
		this.groomingMark = groomingMark;
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
		Tassess other = (Tassess) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tassess [id=" + id + ", openId=" + openId + ", trainerId=" + trainerId + ", trainerName=" + trainerName
				+ ", stars=" + stars + ", evaluate=" + evaluate + ", createTime=" + createTime + ", comments="
				+ comments + ", status=" + status + "]";
	}

}

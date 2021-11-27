package com.tuocheng.model.demo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 
 * @author MEI702
 *
 */
@Entity
@Table(name = "tb_salesman", schema = "")
public class Tsalesman implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8302475964521125547L;
	private String id;
	private String openId; // 用户微信ID
	private String personId; // 教练ID
	private String personName; // 教练ID
	private Integer stars; // 星级（1，很好； 2：一般； 3：差； 4：极差）
	private String evaluate; // 评价
	private Date createTime; // 创建时间时间
	private String comments; // 备注
	private Integer status; // 状态。

	public Tsalesman() {
	}

	public Tsalesman(String id, String openId, String personId, String personName, Integer stars, String evaluate,
			Date createTime, String comments, Integer status) {
		this.id = id;
		this.openId = openId;
		this.personId = personId;
		this.personName = personName;
		this.stars = stars;
		this.evaluate = evaluate;
		this.createTime = createTime;
		this.comments = comments;
		this.status = status;
	}

	@Id
	@Column(name = "id", nullable = false, updatable = false, length = 36)
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

	@Column(name = "personId", length = 36)
	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	@Column(name = "personName", length = 36)
	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
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
		Tsalesman other = (Tsalesman) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tsalesman [id=" + id + ", openId=" + openId + ", personId=" + personId + ", personName=" + personName
				+ ", stars=" + stars + ", evaluate=" + evaluate + ", createTime=" + createTime + ", comments="
				+ comments + ", status=" + status + "]";
	}

}

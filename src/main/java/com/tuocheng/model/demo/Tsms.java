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
 * 短信平台实体类（记录用户发送的信息）
 * 
 * @author huangqinting
 *
 */
@Entity
@Table(name = "tb_tsms", schema = "")
public class Tsms implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 357990631263932139L;
	private String id;
	private String name;// 姓名
	private String phone;// 手机号
	private Date date;// 发送日期
	private String content;// 短信内容
	private String schoolArea;// 校区标识
	private String operator;// 发送者

	public Tsms() {
	}

	public Tsms(String id, String name, String phone, Date date, String content, String schoolArea, String operator) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.date = date;
		this.content = content;
		this.schoolArea = schoolArea;
		this.operator = operator;
	}

	@Id
	@Column(name = "id", nullable = false, length = 36, updatable = false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "name", length = 36)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "phone", length = 36)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date", length = 7)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "content", length = 300)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "schoolArea", length = 40)
	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	@Column(name = "operator", length = 36)
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
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
		Tsms other = (Tsms) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tsms [id=" + id + ", name=" + name + ", phone=" + phone + ", date=" + date + ", content=" + content
				+ ", schoolArea=" + schoolArea + ", operator=" + operator + "]";
	}

}

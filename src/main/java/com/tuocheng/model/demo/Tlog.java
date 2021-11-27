package com.tuocheng.model.demo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.alibaba.fastjson.JSON;

/**
 * 日志管理类
 * 
 * @author 农峰
 * 
 */
@Entity
@Table(name = "tb_log", schema = "")
public class Tlog implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1683616938516597700L;

	private String id;

	private String operator;// 操作人
	private String operName;// 操作名称,方法名
	private String operParams;// 操作参数
	private String operResult;// 操作结果,成功,失败
	private String resultMsg;// 结果消息
	private Date operTime = new Date(); // 操作时间
	private String schoolArea;// 校区标识

	public Tlog() {
	}

	public Tlog(String id, String operator, String operName, String operParams,
			String operResult, String resultMsg, Date operTime,
			String schoolArea) {
		this.id = id;
		this.operator = operator;
		this.operName = operName;
		this.operParams = operParams;
		this.operResult = operResult;
		this.resultMsg = resultMsg;
		this.operTime = operTime;
		this.schoolArea = schoolArea;
	}

	@Id
	@Column(name = "id", nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "operator", length = 50)
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "operName", length = 600)
	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	@Column(name = "operParams", length = 600)
	public String getOperParams() {
		return operParams;
	}

	public void setOperParams(String operParams) {
		this.operParams = operParams;
	}

	@Column(name = "operResult", length = 50)
	public String getOperResult() {
		return operResult;
	}

	public void setOperResult(String operResult) {
		this.operResult = operResult;
	}

	@Column(name = "resultMsg", length = 600)
	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "operTime", length = 7)
	public Date getOperTime() {
		return operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	@Column(name = "schoolArea", length = 36)
	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
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
		Tlog other = (Tlog) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tlog [id=" + id + ", operator=" + operator + ", operName="
				+ operName + ", operParams=" + operParams + ", operResult="
				+ operResult + ", resultMsg=" + resultMsg + ", operTime="
				+ operTime + ", schoolArea=" + schoolArea + "]";
	}

	
}

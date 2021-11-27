package com.tuocheng.model.demo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 计时卡实体模型(DAO专用)
 * 
 * @author 李杰
 * 
 */
@Entity
@Table(name = "tb_timer_card", schema = "")
public class TtimerCard implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9090317064490501716L;
	// ===========base properties==============
	private String id;
	private Integer driverTypeId; // 驾照类型
	private String driverTypeName; // 驾照类型名
	private Integer totalHours; // 总学时
	private Integer oneHours; // 科目1学时
	private Integer twosTheoryHours; // 科目2理论学时
	private Integer twosOperateHours; // 科目2实操学时
	private Integer twosSimulatorHours; // 科目2模拟学时
	private Integer threesTheoryHours; // 科目3理论学时
	private Integer threesOperateHours; // 科目3实操学时
	private Integer threesSimulatorHours; // 科目3模拟学时

	// ==============relation properties==============
	public TtimerCard() {
	}

	@Id
	@Column(name = "id", nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "driverTypeId", unique = true)
	public Integer getDriverTypeId() {
		return driverTypeId;
	}

	public void setDriverTypeId(Integer driverTypeId) {
		this.driverTypeId = driverTypeId;
	}

	@Column(name = "driverTypeName", length = 11)
	public String getDriverTypeName() {
		return driverTypeName;
	}

	public void setDriverTypeName(String driverTypeName) {
		this.driverTypeName = driverTypeName;
	}

	@Column(name = "totalHours", length = 11)
	public Integer getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(Integer totalHours) {
		this.totalHours = totalHours;
	}

	@Column(name = "oneHours", length = 11)
	public Integer getOneHours() {
		return oneHours;
	}

	public void setOneHours(Integer oneHours) {
		this.oneHours = oneHours;
	}

	@Column(name = "twosTheoryHours", length = 11)
	public Integer getTwosTheoryHours() {
		return twosTheoryHours;
	}

	public void setTwosTheoryHours(Integer twosTheoryHours) {
		this.twosTheoryHours = twosTheoryHours;
	}

	@Column(name = "twosOperateHours", length = 11)
	public Integer getTwosOperateHours() {
		return twosOperateHours;
	}

	public void setTwosOperateHours(Integer twosOperateHours) {
		this.twosOperateHours = twosOperateHours;
	}

	@Column(name = "twosSimulatorHours", length = 11)
	public Integer getTwosSimulatorHours() {
		return twosSimulatorHours;
	}

	public void setTwosSimulatorHours(Integer twosSimulatorHours) {
		this.twosSimulatorHours = twosSimulatorHours;
	}

	@Column(name = "threesTheoryHours", length = 11)
	public Integer getThreesTheoryHours() {
		return threesTheoryHours;
	}

	public void setThreesTheoryHours(Integer threesTheoryHours) {
		this.threesTheoryHours = threesTheoryHours;
	}

	@Column(name = "threesOperateHours", length = 11)
	public Integer getThreesOperateHours() {
		return threesOperateHours;
	}

	public void setThreesOperateHours(Integer threesOperateHours) {
		this.threesOperateHours = threesOperateHours;
	}

	@Column(name = "threesSimulatorHours", length = 11)
	public Integer getThreesSimulatorHours() {
		return threesSimulatorHours;
	}

	public void setThreesSimulatorHours(Integer threesSimulatorHours) {
		this.threesSimulatorHours = threesSimulatorHours;
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
		TtimerCard other = (TtimerCard) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TtimerCard [id=" + id + ", driverTypeId=" + driverTypeId
				+ ", driverTypeName=" + driverTypeName + ", totalHours="
				+ totalHours + ", oneHours=" + oneHours + ", twosTheoryHours="
				+ twosTheoryHours + ", twosOperateHours=" + twosOperateHours
				+ ", twosSimulatorHours=" + twosSimulatorHours
				+ ", threesTheoryHours=" + threesTheoryHours
				+ ", threesOperateHours=" + threesOperateHours
				+ ", threesSimulatorHours=" + threesSimulatorHours + "]";
	}

}

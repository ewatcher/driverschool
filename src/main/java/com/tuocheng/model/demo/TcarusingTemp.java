package com.tuocheng.model.demo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.io.filefilter.FalseFileFilter;
import org.hibernate.annotations.ManyToAny;

/**
 * 车辆与车辆使用中间过度类
 * 
 * @author nongfeng
 * 
 */
@Entity
@Table(name = "tb_carusingTemp", schema = "")
public class TcarusingTemp implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5220523835358650104L;
	private String id;// 标识
	private String schoolArea;// 校区标识
	private Integer dateCount;// 按日统计
	private Integer weekCount;// 按周统计
	private Integer manthCount;// 按月统计
	private Integer yearCount;// 按年统计
	private Integer dateReservateCount;//　天预约统计
	private Integer weekReservateCount;// 周预约统计
	private Integer monthReservateCount;//月预约统计
	private Integer yearReservateCount;// 年预约统计

	private Tcar car;
	private Set<TusingCar> usingCars = new HashSet<TusingCar>(0);

	public TcarusingTemp() {
	}

	public TcarusingTemp(String id, String schoolArea, Integer dateCount,
			Integer weekCount, Integer manthCount, Integer yearCount, Tcar car,
			Set<TusingCar> usingCars) {
		this.id = id;
		this.schoolArea = schoolArea;
		this.dateCount = dateCount;
		this.weekCount = weekCount;
		this.manthCount = manthCount;
		this.yearCount = yearCount;
		this.car = car;
		this.usingCars = usingCars;
	}

	@Id
	@Column(name = "id", nullable = false, length = 36)
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

	@Column(name = "dateCount", length = 8, columnDefinition = "INT DEFAULT 0")
	public Integer getDateCount() {
		return dateCount;
	}

	public void setDateCount(Integer dateCount) {
		this.dateCount = dateCount;
	}

	@Column(name = "weekCount", length = 8, columnDefinition = "INT DEFAULT 0")
	public Integer getWeekCount() {
		return weekCount;
	}

	public void setWeekCount(Integer weekCount) {
		this.weekCount = weekCount;
	}

	@Column(name = "manthCount", length = 8, columnDefinition = "INT DEFAULT 0")
	public Integer getManthCount() {
		return manthCount;
	}

	public void setManthCount(Integer manthCount) {
		this.manthCount = manthCount;
	}

	@Column(name = "yearCount", length = 8, columnDefinition = "INT DEFAULT 0")
	public Integer getYearCount() {
		return yearCount;
	}

	public void setYearCount(Integer yearCount) {
		this.yearCount = yearCount;
	}

	@Column(name = "dateReservateCount", length = 8, columnDefinition = "INT DEFAULT 0")
	public Integer getDateReservateCount() {
		return dateReservateCount;
	}

	public void setDateReservateCount(Integer dateReservateCount) {
		this.dateReservateCount = dateReservateCount;
	}

	@Column(name = "weekReservateCount", length = 8, columnDefinition = "INT DEFAULT 0")
	public Integer getWeekReservateCount() {
		return weekReservateCount;
	}

	public void setWeekReservateCount(Integer weekReservateCount) {
		this.weekReservateCount = weekReservateCount;
	}

	@Column(name = "monthReservateCount", length = 8, columnDefinition = "INT DEFAULT 0")
	public Integer getMonthReservateCount() {
		return monthReservateCount;
	}

	public void setMonthReservateCount(Integer monthReservateCount) {
		this.monthReservateCount = monthReservateCount;
	}

	@Column(name = "yearReservateCount", length = 8, columnDefinition = "INT DEFAULT 0")
	public Integer getYearReservateCount() {
		return yearReservateCount;
	}

	public void setYearReservateCount(Integer yearReservateCount) {
		this.yearReservateCount = yearReservateCount;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "carId")
	public Tcar getCar() {
		return car;
	}

	public void setCar(Tcar car) {
		this.car = car;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "usingcarTemp")
	public Set<TusingCar> getUsingCars() {
		return usingCars;
	}

	public void setUsingCars(Set<TusingCar> usingCars) {
		this.usingCars = usingCars;
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
		TcarusingTemp other = (TcarusingTemp) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TcarusingTemp [id=" + id + ", schoolArea=" + schoolArea
				+ ", dateCount=" + dateCount + ", weekCount=" + weekCount
				+ ", manthCount=" + manthCount + ", yearCount=" + yearCount
				+ ", car=" + car + ", usingCars=" + usingCars + "]";
	}

}

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
 * 自动档车辆排班实体类
 * 
 * @author 农峰
 * 
 */
@Entity
@Table(name = "tb_autoCarArrange", schema = "")
public class TautoCarArrange implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8104323013741018713L;
	private String id;// 标识
	private String year;// 年份
	private String month;// 月份
	private String weekth;// 星期（1：星期一，2：星期二...）
	private String schoolArea;// 校区标识
	private String operator;// 操作员

	private Date arrangeDate;// 排班第一天
	private String noArrangeDates;// 排班日期

	private Date firstArrangeDate;// 该变量记录教练排班计划中的第一天
	private Date lastArrangeDate;// 该变量记录教练排班计划中的最后一天

	// 教练排班时段(0表示未使用，1：表示启用)
	private Integer ItemTime7;// 表示7：00-8：00时段
	private Integer ItemTime8;// 表示8：00-9：00时段
	private Integer ItemTime9;
	private Integer ItemTime10;
	private Integer ItemTime11;
	private Integer ItemTime12;
	private Integer ItemTime13;
	private Integer ItemTime14;
	private Integer ItemTime15;
	private Integer ItemTime16;
	private Integer ItemTime17;
	private Integer ItemTime18;
	private Integer ItemTime19;
	private Integer ItemTime20;
	private Integer ItemTime21;

	private Integer ItemTime7Flag;// 表示8：00-9：00时段使用情况 1:表示已经有约，0：默认无预约
	private Integer ItemTime8Flag;// 表示8：00-9：00时段使用情况 1:表示已经有约，0：默认无预约
	private Integer ItemTime9Flag;
	private Integer ItemTime10Flag;
	private Integer ItemTime11Flag;
	private Integer ItemTime12Flag;
	private Integer ItemTime13Flag;
	private Integer ItemTime14Flag;
	private Integer ItemTime15Flag;
	private Integer ItemTime16Flag;
	private Integer ItemTime17Flag;
	private Integer ItemTime18Flag;
	private Integer ItemTime19Flag;
	private Integer ItemTime20Flag;
	private Integer ItemTime21Flag;


	private String carId;

	public TautoCarArrange() {
	}

	
	public TautoCarArrange(String id, String year, String month, String weekth, String schoolArea, String operator,
			Date arrangeDate, String noArrangeDates, Date firstArrangeDate, Date lastArrangeDate, Integer itemTime7,
			Integer itemTime8, Integer itemTime9, Integer itemTime10, Integer itemTime11, Integer itemTime12,
			Integer itemTime13, Integer itemTime14, Integer itemTime15, Integer itemTime16, Integer itemTime17,
			Integer itemTime18, Integer itemTime19, Integer itemTime20, Integer itemTime21, Integer itemTime7Flag,
			Integer itemTime8Flag, Integer itemTime9Flag, Integer itemTime10Flag, Integer itemTime11Flag,
			Integer itemTime12Flag, Integer itemTime13Flag, Integer itemTime14Flag, Integer itemTime15Flag,
			Integer itemTime16Flag, Integer itemTime17Flag, Integer itemTime18Flag, Integer itemTime19Flag,
			Integer itemTime20Flag, Integer itemTime21Flag, String carId) {
		super();
		this.id = id;
		this.year = year;
		this.month = month;
		this.weekth = weekth;
		this.schoolArea = schoolArea;
		this.operator = operator;
		this.arrangeDate = arrangeDate;
		this.noArrangeDates = noArrangeDates;
		this.firstArrangeDate = firstArrangeDate;
		this.lastArrangeDate = lastArrangeDate;
		ItemTime7 = itemTime7;
		ItemTime8 = itemTime8;
		ItemTime9 = itemTime9;
		ItemTime10 = itemTime10;
		ItemTime11 = itemTime11;
		ItemTime12 = itemTime12;
		ItemTime13 = itemTime13;
		ItemTime14 = itemTime14;
		ItemTime15 = itemTime15;
		ItemTime16 = itemTime16;
		ItemTime17 = itemTime17;
		ItemTime18 = itemTime18;
		ItemTime19 = itemTime19;
		ItemTime20 = itemTime20;
		ItemTime21 = itemTime21;
		ItemTime7Flag = itemTime7Flag;
		ItemTime8Flag = itemTime8Flag;
		ItemTime9Flag = itemTime9Flag;
		ItemTime10Flag = itemTime10Flag;
		ItemTime11Flag = itemTime11Flag;
		ItemTime12Flag = itemTime12Flag;
		ItemTime13Flag = itemTime13Flag;
		ItemTime14Flag = itemTime14Flag;
		ItemTime15Flag = itemTime15Flag;
		ItemTime16Flag = itemTime16Flag;
		ItemTime17Flag = itemTime17Flag;
		ItemTime18Flag = itemTime18Flag;
		ItemTime19Flag = itemTime19Flag;
		ItemTime20Flag = itemTime20Flag;
		ItemTime21Flag = itemTime21Flag;
		this.carId = carId;
	}


	@Id
	@Column(name = "id", length = 36, nullable = false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "year", length = 12)
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Column(name = "month", length = 12)
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	@Column(name = "weekth", length = 12)
	public String getWeekth() {
		return weekth;
	}

	public void setWeekth(String weekth) {
		this.weekth = weekth;
	}

	@Column(name = "schoolArea", length = 36)
	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	@Column(name = "operator", length = 12)
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "arrangeDate", length = 7, updatable = false)
	public Date getArrangeDate() {
		return arrangeDate;
	}

	public void setArrangeDate(Date arrangeDate) {
		this.arrangeDate = arrangeDate;
	}

	@Column(name = "noArrangeDates", length = 300)
	public String getNoArrangeDates() {
		return noArrangeDates;
	}

	public void setNoArrangeDates(String noArrangeDates) {
		this.noArrangeDates = noArrangeDates;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "firstArrangeDate", length = 7)
	public Date getFirstArrangeDate() {
		return firstArrangeDate;
	}

	public void setFirstArrangeDate(Date firstArrangeDate) {
		this.firstArrangeDate = firstArrangeDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lastArrangeDate", length = 7)
	public Date getLastArrangeDate() {
		return lastArrangeDate;
	}

	public void setLastArrangeDate(Date lastArrangeDate) {
		this.lastArrangeDate = lastArrangeDate;
	}
	
	@Column(name = "ItemTime7", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getItemTime7() {
		return ItemTime7;
	}


	public void setItemTime7(Integer itemTime7) {
		ItemTime7 = itemTime7;
	}


	@Column(name = "ItemTime8", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getItemTime8() {
		return ItemTime8;
	}

	public void setItemTime8(Integer itemTime8) {
		ItemTime8 = itemTime8;
	}

	@Column(name = "ItemTime9", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getItemTime9() {
		return ItemTime9;
	}

	public void setItemTime9(Integer itemTime9) {
		ItemTime9 = itemTime9;
	}

	@Column(name = "ItemTime10", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getItemTime10() {
		return ItemTime10;
	}

	public void setItemTime10(Integer itemTime10) {
		ItemTime10 = itemTime10;
	}

	@Column(name = "ItemTime11", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getItemTime11() {
		return ItemTime11;
	}

	public void setItemTime11(Integer itemTime11) {
		ItemTime11 = itemTime11;
	}

	@Column(name = "ItemTime12", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getItemTime12() {
		return ItemTime12;
	}

	public void setItemTime12(Integer itemTime12) {
		ItemTime12 = itemTime12;
	}

	@Column(name = "ItemTime13", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getItemTime13() {
		return ItemTime13;
	}

	public void setItemTime13(Integer itemTime13) {
		ItemTime13 = itemTime13;
	}

	@Column(name = "ItemTime14", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getItemTime14() {
		return ItemTime14;
	}

	public void setItemTime14(Integer itemTime14) {
		ItemTime14 = itemTime14;
	}

	@Column(name = "ItemTime15", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getItemTime15() {
		return ItemTime15;
	}

	public void setItemTime15(Integer itemTime15) {
		ItemTime15 = itemTime15;
	}

	@Column(name = "ItemTime16", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getItemTime16() {
		return ItemTime16;
	}

	public void setItemTime16(Integer itemTime16) {
		ItemTime16 = itemTime16;
	}

	@Column(name = "ItemTime17", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getItemTime17() {
		return ItemTime17;
	}

	public void setItemTime17(Integer itemTime17) {
		ItemTime17 = itemTime17;
	}

	@Column(name = "ItemTime18", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getItemTime18() {
		return ItemTime18;
	}

	public void setItemTime18(Integer itemTime18) {
		ItemTime18 = itemTime18;
	}

	@Column(name = "ItemTime19", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getItemTime19() {
		return ItemTime19;
	}

	public void setItemTime19(Integer itemTime19) {
		ItemTime19 = itemTime19;
	}

	@Column(name = "ItemTime20", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getItemTime20() {
		return ItemTime20;
	}

	public void setItemTime20(Integer itemTime20) {
		ItemTime20 = itemTime20;
	}

	@Column(name = "ItemTime21", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getItemTime21() {
		return ItemTime21;
	}

	public void setItemTime21(Integer itemTime21) {
		ItemTime21 = itemTime21;
	}

	
	@Column(name = "ItemTime7Flag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getItemTime7Flag() {
		return ItemTime7Flag;
	}


	public void setItemTime7Flag(Integer itemTime7Flag) {
		ItemTime7Flag = itemTime7Flag;
	}


	@Column(name = "ItemTime8Flag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getItemTime8Flag() {
		return ItemTime8Flag;
	}

	public void setItemTime8Flag(Integer itemTime8Flag) {
		ItemTime8Flag = itemTime8Flag;
	}

	@Column(name = "ItemTime9Flag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getItemTime9Flag() {
		return ItemTime9Flag;
	}

	public void setItemTime9Flag(Integer itemTime9Flag) {
		ItemTime9Flag = itemTime9Flag;
	}

	@Column(name = "ItemTime10Flag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getItemTime10Flag() {
		return ItemTime10Flag;
	}

	public void setItemTime10Flag(Integer itemTime10Flag) {
		ItemTime10Flag = itemTime10Flag;
	}

	@Column(name = "ItemTime11Flag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getItemTime11Flag() {
		return ItemTime11Flag;
	}

	public void setItemTime11Flag(Integer itemTime11Flag) {
		ItemTime11Flag = itemTime11Flag;
	}

	@Column(name = "ItemTime12Flag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getItemTime12Flag() {
		return ItemTime12Flag;
	}

	public void setItemTime12Flag(Integer itemTime12Flag) {
		ItemTime12Flag = itemTime12Flag;
	}

	@Column(name = "ItemTime13Flag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getItemTime13Flag() {
		return ItemTime13Flag;
	}

	public void setItemTime13Flag(Integer itemTime13Flag) {
		ItemTime13Flag = itemTime13Flag;
	}

	@Column(name = "ItemTime14Flag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getItemTime14Flag() {
		return ItemTime14Flag;
	}

	public void setItemTime14Flag(Integer itemTime14Flag) {
		ItemTime14Flag = itemTime14Flag;
	}

	@Column(name = "ItemTime15Flag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getItemTime15Flag() {
		return ItemTime15Flag;
	}

	public void setItemTime15Flag(Integer itemTime15Flag) {
		ItemTime15Flag = itemTime15Flag;
	}

	@Column(name = "ItemTime16Flag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getItemTime16Flag() {
		return ItemTime16Flag;
	}

	public void setItemTime16Flag(Integer itemTime16Flag) {
		ItemTime16Flag = itemTime16Flag;
	}

	@Column(name = "ItemTime17Flag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getItemTime17Flag() {
		return ItemTime17Flag;
	}

	public void setItemTime17Flag(Integer itemTime17Flag) {
		ItemTime17Flag = itemTime17Flag;
	}

	@Column(name = "ItemTime18Flag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getItemTime18Flag() {
		return ItemTime18Flag;
	}

	public void setItemTime18Flag(Integer itemTime18Flag) {
		ItemTime18Flag = itemTime18Flag;
	}

	@Column(name = "ItemTime19Flag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getItemTime19Flag() {
		return ItemTime19Flag;
	}

	public void setItemTime19Flag(Integer itemTime19Flag) {
		ItemTime19Flag = itemTime19Flag;
	}

	@Column(name = "ItemTime20Flag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getItemTime20Flag() {
		return ItemTime20Flag;
	}

	public void setItemTime20Flag(Integer itemTime20Flag) {
		ItemTime20Flag = itemTime20Flag;
	}

	@Column(name = "ItemTime21Flag", length = 4, columnDefinition = "INT DEFAULT 0")
	public Integer getItemTime21Flag() {
		return ItemTime21Flag;
	}

	public void setItemTime21Flag(Integer itemTime21Flag) {
		ItemTime21Flag = itemTime21Flag;
	}

	@Column(name = "carId", nullable = false, length = 36)
	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
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
		TautoCarArrange other = (TautoCarArrange) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "TautoCarArrange [id=" + id + ", year=" + year + ", month=" + month + ", weekth=" + weekth
				+ ", schoolArea=" + schoolArea + ", operator=" + operator + ", arrangeDate=" + arrangeDate
				+ ", noArrangeDates=" + noArrangeDates + ", firstArrangeDate=" + firstArrangeDate + ", lastArrangeDate="
				+ lastArrangeDate + ", ItemTime7=" + ItemTime7 + ", ItemTime8=" + ItemTime8 + ", ItemTime9=" + ItemTime9
				+ ", ItemTime10=" + ItemTime10 + ", ItemTime11=" + ItemTime11 + ", ItemTime12=" + ItemTime12
				+ ", ItemTime13=" + ItemTime13 + ", ItemTime14=" + ItemTime14 + ", ItemTime15=" + ItemTime15
				+ ", ItemTime16=" + ItemTime16 + ", ItemTime17=" + ItemTime17 + ", ItemTime18=" + ItemTime18
				+ ", ItemTime19=" + ItemTime19 + ", ItemTime20=" + ItemTime20 + ", ItemTime21=" + ItemTime21
				+ ", ItemTime7Flag=" + ItemTime7Flag + ", ItemTime8Flag=" + ItemTime8Flag + ", ItemTime9Flag="
				+ ItemTime9Flag + ", ItemTime10Flag=" + ItemTime10Flag + ", ItemTime11Flag=" + ItemTime11Flag
				+ ", ItemTime12Flag=" + ItemTime12Flag + ", ItemTime13Flag=" + ItemTime13Flag + ", ItemTime14Flag="
				+ ItemTime14Flag + ", ItemTime15Flag=" + ItemTime15Flag + ", ItemTime16Flag=" + ItemTime16Flag
				+ ", ItemTime17Flag=" + ItemTime17Flag + ", ItemTime18Flag=" + ItemTime18Flag + ", ItemTime19Flag="
				+ ItemTime19Flag + ", ItemTime20Flag=" + ItemTime20Flag + ", ItemTime21Flag=" + ItemTime21Flag
				+ ", carId=" + carId + "]";
	}

	

}

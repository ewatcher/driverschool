package com.tuocheng.model.demo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.alibaba.fastjson.JSON;

/**
 * 教练排班实体类
 * 
 * @author MEI702
 * 
 */
@Entity
@Table(name = "tb_tranierarranges", schema = "")
public class TtrainerArrange implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -4901466774034473477L;
	public static final String FIRST_WEEK = "1";
	public static final String SECOND_WEEK = "2";
	public static final String THREE_WEEK = "3";
	public static final String FOUR_WEEK = "4";
	
	public static final Integer WORKINGTYPE_ELECITRIC=1;
	public static final Integer WORKINGTYPE_SIMULATE=2;
	public static final Integer WORKINGTYPE_FILEITEM=3;
	public static final Integer WORKINGTYPE_ROAD=4;
	public static final Integer WORKINGTYPE_REST=5;

	private String id;// 标识
	private String No;// 编号
	private String year;// 年份
	private String month;// 月份
	private String weekth;// 星期（1：星期一，2：星期二...）
	private String trainTime;// 训练时间段
	private String schoolArea;// 校区标识
	private String operator;// 操作员

	private Date arrangeDate;// 排班第一天
	private String noArrangeDates;// 排班日期
	private Integer teachingType;// 施教类型：1:A1,2:A2,3:A3 ....C5
	private Integer workingType;// 排班类型:1.电车，2.模拟　4.五项　5.路训

	private Date firstArrangeDate;// 该变量记录教练排班计划中的第一天
	private Date lastArrangeDate;// 该变量记录教练排班计划中的最后一天

	// 教练排班时段
	private Integer ItemTime7;// 表示8：00-9：00时段
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

	// 教练排班时段的训练内容（1：电车，2：模拟，3：五项，4：路训）
	private Integer ItemTime7flag;
	private Integer ItemTime8flag;
	private Integer ItemTime9flag;
	private Integer ItemTime10flag;
	private Integer ItemTime11flag;
	private Integer ItemTime12flag;
	private Integer ItemTime13flag;
	private Integer ItemTime14flag;
	private Integer ItemTime15flag;
	private Integer ItemTime16flag;
	private Integer ItemTime17flag;
	private Integer ItemTime18flag;
	private Integer ItemTime19flag;
	private Integer ItemTime20flag;
	private Integer ItemTime21flag;

	private Ttrainer trainer;// 对应教练

	public TtrainerArrange() {
	}

	public TtrainerArrange(String id, String no, String year, String month,
			String weekth, String trainTime, String schoolArea,
			String operator, Date arrangeDate, String noArrangeDates,
			Ttrainer trainer) {
		super();
		this.id = id;
		No = no;
		this.year = year;
		this.month = month;
		this.weekth = weekth;
		this.trainTime = trainTime;
		this.schoolArea = schoolArea;
		this.operator = operator;
		this.arrangeDate = arrangeDate;
		this.noArrangeDates = noArrangeDates;
		this.trainer = trainer;
	}

	@Id
	@Column(name = "id", nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "No", length = 36)
	public String getNo() {
		return No;
	}

	public void setNo(String no) {
		No = no;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "arrangeDate", length = 15)
	public Date getArrangeDate() {
		return arrangeDate;
	}

	public void setArrangeDate(Date arrangeDate) {
		this.arrangeDate = arrangeDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "firstArrangeDate", length = 15)
	public Date getFirstArrangeDate() {
		return firstArrangeDate;
	}

	public void setFirstArrangeDate(Date firstArrangeDate) {
		this.firstArrangeDate = firstArrangeDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lastArrangeDate", length = 15)
	public Date getLastArrangeDate() {
		return lastArrangeDate;
	}

	public void setLastArrangeDate(Date lastArrangeDate) {
		this.lastArrangeDate = lastArrangeDate;
	}

	@Column(name = "weekth", length = 4)
	public String getWeekth() {
		return weekth;
	}

	public void setWeekth(String weekth) {
		this.weekth = weekth;
	}

	@Column(name = "trainTime", length = 300)
	public String getTrainTime() {
		return trainTime;
	}

	public void setTrainTime(String trainTime) {
		this.trainTime = trainTime;
	}

	@Column(name = "schoolArea", length = 36)
	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
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

	@Column(name = "noArrangeDates", length = 300)
	public String getNoArrangeDates() {
		return noArrangeDates;
	}

	public void setNoArrangeDates(String noArrangeDates) {
		this.noArrangeDates = noArrangeDates;
	}

	@Column(name = "operator", length = 36, updatable = false)
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "teachingType", length = 4)
	public Integer getTeachingType() {
		return teachingType;
	}

	public void setTeachingType(Integer teachingType) {
		this.teachingType = teachingType;
	}

	@Column(name = "workingType", length = 4)
	public Integer getWorkingType() {
		return workingType;
	}

	public void setWorkingType(Integer workingType) {
		this.workingType = workingType;
	}
	
	
	@Column(name = "ItemTime7", length = 4)
	public Integer getItemTime7() {
		return ItemTime7;
	}

	public void setItemTime7(Integer itemTime7) {
		ItemTime7 = itemTime7;
	}
	
	

	@Column(name = "ItemTime8", length = 4)
	public Integer getItemTime8() {
		return ItemTime8;
	}

	public void setItemTime8(Integer itemTime8) {
		ItemTime8 = itemTime8;
	}

	@Column(name = "ItemTime9", length = 4)
	public Integer getItemTime9() {
		return ItemTime9;
	}

	public void setItemTime9(Integer itemTime9) {
		ItemTime9 = itemTime9;
	}

	@Column(name = "ItemTime10", length = 4)
	public Integer getItemTime10() {
		return ItemTime10;
	}

	public void setItemTime10(Integer itemTime10) {
		ItemTime10 = itemTime10;
	}

	@Column(name = "ItemTime11", length = 4)
	public Integer getItemTime11() {
		return ItemTime11;
	}

	public void setItemTime11(Integer itemTime11) {
		ItemTime11 = itemTime11;
	}

	@Column(name = "ItemTime12", length = 4)
	public Integer getItemTime12() {
		return ItemTime12;
	}

	public void setItemTime12(Integer itemTime12) {
		ItemTime12 = itemTime12;
	}

	@Column(name = "ItemTime13", length = 4)
	public Integer getItemTime13() {
		return ItemTime13;
	}

	public void setItemTime13(Integer itemTime13) {
		ItemTime13 = itemTime13;
	}

	@Column(name = "ItemTime14", length = 4)
	public Integer getItemTime14() {
		return ItemTime14;
	}

	public void setItemTime14(Integer itemTime14) {
		ItemTime14 = itemTime14;
	}

	@Column(name = "ItemTime15", length = 4)
	public Integer getItemTime15() {
		return ItemTime15;
	}

	public void setItemTime15(Integer itemTime15) {
		ItemTime15 = itemTime15;
	}

	@Column(name = "ItemTime16", length = 4)
	public Integer getItemTime16() {
		return ItemTime16;
	}

	public void setItemTime16(Integer itemTime16) {
		ItemTime16 = itemTime16;
	}

	@Column(name = "ItemTime17", length = 4)
	public Integer getItemTime17() {
		return ItemTime17;
	}

	public void setItemTime17(Integer itemTime17) {
		ItemTime17 = itemTime17;
	}

	@Column(name = "ItemTime18", length = 4)
	public Integer getItemTime18() {
		return ItemTime18;
	}

	public void setItemTime18(Integer itemTime18) {
		ItemTime18 = itemTime18;
	}
	
	@Column(name = "ItemTime7flag", length = 4)
	public Integer getItemTime7flag() {
		return ItemTime7flag;
	}

	public void setItemTime7flag(Integer itemTime7flag) {
		ItemTime7flag = itemTime7flag;
	}

	@Column(name = "ItemTime8flag", length = 4)
	public Integer getItemTime8flag() {
		return ItemTime8flag;
	}

	public void setItemTime8flag(Integer itemTime8flag) {
		ItemTime8flag = itemTime8flag;
	}

	@Column(name = "ItemTime9flag", length = 4)
	public Integer getItemTime9flag() {
		return ItemTime9flag;
	}

	public void setItemTime9flag(Integer itemTime9flag) {
		ItemTime9flag = itemTime9flag;
	}

	@Column(name = "ItemTime10flag", length = 4)
	public Integer getItemTime10flag() {
		return ItemTime10flag;
	}

	public void setItemTime10flag(Integer itemTime10flag) {
		ItemTime10flag = itemTime10flag;
	}

	@Column(name = "ItemTime11flag", length = 4)
	public Integer getItemTime11flag() {
		return ItemTime11flag;
	}

	public void setItemTime11flag(Integer itemTime11flag) {
		ItemTime11flag = itemTime11flag;
	}

	@Column(name = "ItemTime12flag", length = 4)
	public Integer getItemTime12flag() {
		return ItemTime12flag;
	}

	public void setItemTime12flag(Integer itemTime12flag) {
		ItemTime12flag = itemTime12flag;
	}

	@Column(name = "ItemTime13flag", length = 4)
	public Integer getItemTime13flag() {
		return ItemTime13flag;
	}

	public void setItemTime13flag(Integer itemTime13flag) {
		ItemTime13flag = itemTime13flag;
	}

	@Column(name = "ItemTime14flag", length = 4)
	public Integer getItemTime14flag() {
		return ItemTime14flag;
	}

	public void setItemTime14flag(Integer itemTime14flag) {
		ItemTime14flag = itemTime14flag;
	}

	@Column(name = "ItemTime15flag", length = 4)
	public Integer getItemTime15flag() {
		return ItemTime15flag;
	}

	public void setItemTime15flag(Integer itemTime15flag) {
		ItemTime15flag = itemTime15flag;
	}

	@Column(name = "ItemTime16flag", length = 4)
	public Integer getItemTime16flag() {
		return ItemTime16flag;
	}

	public void setItemTime16flag(Integer itemTime16flag) {
		ItemTime16flag = itemTime16flag;
	}

	@Column(name = "ItemTime17flag", length = 4)
	public Integer getItemTime17flag() {
		return ItemTime17flag;
	}

	public void setItemTime17flag(Integer itemTime17flag) {
		ItemTime17flag = itemTime17flag;
	}

	@Column(name = "ItemTime18flag", length = 4)
	public Integer getItemTime18flag() {
		return ItemTime18flag;
	}

	public void setItemTime18flag(Integer itemTime18flag) {
		ItemTime18flag = itemTime18flag;
	}

	@Column(name = "ItemTime19", length = 4)
	public Integer getItemTime19() {
		return ItemTime19;
	}

	public void setItemTime19(Integer itemTime19) {
		ItemTime19 = itemTime19;
	}

	@Column(name = "ItemTime20", length = 4)
	public Integer getItemTime20() {
		return ItemTime20;
	}

	public void setItemTime20(Integer itemTime20) {
		ItemTime20 = itemTime20;
	}

	@Column(name = "ItemTime21", length = 4)
	public Integer getItemTime21() {
		return ItemTime21;
	}

	public void setItemTime21(Integer itemTime21) {
		ItemTime21 = itemTime21;
	}

	@Column(name = "ItemTime19flag", length = 4)
	public Integer getItemTime19flag() {
		return ItemTime19flag;
	}

	public void setItemTime19flag(Integer itemTime19flag) {
		ItemTime19flag = itemTime19flag;
	}

	@Column(name = "ItemTime20flag", length = 4)
	public Integer getItemTime20flag() {
		return ItemTime20flag;
	}

	public void setItemTime20flag(Integer itemTime20flag) {
		ItemTime20flag = itemTime20flag;
	}

	@Column(name = "ItemTime21flag", length = 4)
	public Integer getItemTime21flag() {
		return ItemTime21flag;
	}

	public void setItemTime21flag(Integer itemTime21flag) {
		ItemTime21flag = itemTime21flag;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trainerId")
	public Ttrainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Ttrainer trainer) {
		this.trainer = trainer;
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
		TtrainerArrange other = (TtrainerArrange) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TtrainerArrange [id=" + id + ", No=" + No + ", year=" + year + ", month=" + month + ", weekth=" + weekth
				+ ", trainTime=" + trainTime + ", schoolArea=" + schoolArea + ", operator=" + operator
				+ ", arrangeDate=" + arrangeDate + ", noArrangeDates=" + noArrangeDates + ", teachingType="
				+ teachingType + ", workingType=" + workingType + ", firstArrangeDate=" + firstArrangeDate
				+ ", lastArrangeDate=" + lastArrangeDate + ", ItemTime7=" + ItemTime7 + ", ItemTime8=" + ItemTime8
				+ ", ItemTime9=" + ItemTime9 + ", ItemTime10=" + ItemTime10 + ", ItemTime11=" + ItemTime11
				+ ", ItemTime12=" + ItemTime12 + ", ItemTime13=" + ItemTime13 + ", ItemTime14=" + ItemTime14
				+ ", ItemTime15=" + ItemTime15 + ", ItemTime16=" + ItemTime16 + ", ItemTime17=" + ItemTime17
				+ ", ItemTime18=" + ItemTime18 + ", ItemTime19=" + ItemTime19 + ", ItemTime20=" + ItemTime20
				+ ", ItemTime21=" + ItemTime21 + ", ItemTime7flag=" + ItemTime7flag + ", ItemTime8flag=" + ItemTime8flag
				+ ", ItemTime9flag=" + ItemTime9flag + ", ItemTime10flag=" + ItemTime10flag + ", ItemTime11flag="
				+ ItemTime11flag + ", ItemTime12flag=" + ItemTime12flag + ", ItemTime13flag=" + ItemTime13flag
				+ ", ItemTime14flag=" + ItemTime14flag + ", ItemTime15flag=" + ItemTime15flag + ", ItemTime16flag="
				+ ItemTime16flag + ", ItemTime17flag=" + ItemTime17flag + ", ItemTime18flag=" + ItemTime18flag
				+ ", ItemTime19flag=" + ItemTime19flag + ", ItemTime20flag=" + ItemTime20flag + ", ItemTime21flag="
				+ ItemTime21flag + ", trainer=" + trainer + "]";
	}

	

}

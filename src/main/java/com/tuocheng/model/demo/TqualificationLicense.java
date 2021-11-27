package com.tuocheng.model.demo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.io.filefilter.FalseFileFilter;

/**
 * 资格证实体类（Dao专用）
 * 
 * @author MEI702
 * 
 */
@Entity
@Table(name = "tb_qualificationLicenses", schema = "")
public class TqualificationLicense implements Serializable {

	/**
	 * 序列化
	 */

	private static final long serialVersionUID = 3784014080394445449L;

	private String id;// 标识
	private String numbering;// 编号
	private Date firstGetDate;// 初领日期
	private Date grantDate;// 资格证发放日期
	private Date recieveDate;// 学员领取日期
	private Integer driverType;// 准驾类型(1:A1,2:A2,3:A3,4:B1,5:B2,6:C1,7:C2,8:C3,9:C4,10:D,11:E,12:F,13:M,14:N)
	private Date validBeginDate;// 有效期开始日期
	private Integer validYear;// 有效年限
	private String schoolArea;// 所属于校区
	private Integer grantState;// 资格领取状态(1.未领取，2.已领取)
	private String comment;// 备注
	private String operator;// 操作员

	// ===========connection relation=======
	private Tstudent student;

	public TqualificationLicense() {
	}

	public TqualificationLicense(String id, String numbering,
			Date firstGetDate, Date grantDate, Date recieveDate,
			Integer driverType, Date validBeginDate, Integer validYear,
			String schoolArea, Integer grantState, String comment,
			String operator, Tstudent student) {
		super();
		this.id = id;
		this.numbering = numbering;
		this.firstGetDate = firstGetDate;
		this.grantDate = grantDate;
		this.recieveDate = recieveDate;
		this.driverType = driverType;
		this.validBeginDate = validBeginDate;
		this.validYear = validYear;
		this.schoolArea = schoolArea;
		this.grantState = grantState;
		this.comment = comment;
		this.operator = operator;
		this.student = student;
	}

	@Id
	@Column(name = "id", nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "numbering", nullable = false, length = 36)
	public String getNumbering() {
		return numbering;
	}

	public void setNumbering(String numbering) {
		this.numbering = numbering;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "firstGetDate", length = 7)
	public Date getFirstGetDate() {
		return firstGetDate;
	}

	public void setFirstGetDate(Date firstGetDate) {
		this.firstGetDate = firstGetDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "grantDate", length = 7)
	public Date getGrantDate() {
		return grantDate;
	}

	public void setGrantDate(Date grantDate) {
		this.grantDate = grantDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "recieveDate", length = 7)
	public Date getRecieveDate() {
		return recieveDate;
	}

	public void setRecieveDate(Date recieveDate) {
		this.recieveDate = recieveDate;
	}

	@Column(name = "driverType", length = 4)
	public Integer getDriverType() {
		return driverType;
	}

	public void setDriverType(Integer driverType) {
		this.driverType = driverType;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "validBeginDate", length = 7)
	public Date getValidBeginDate() {
		return validBeginDate;
	}

	public void setValidBeginDate(Date validBeginDate) {
		this.validBeginDate = validBeginDate;
	}

	@Column(name = "validYear", length = 4)
	public Integer getValidYear() {
		return validYear;
	}

	public void setValidYear(Integer validYear) {
		this.validYear = validYear;
	}

	@Column(name = "schoolArea", length = 60)
	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	@Column(name = "grantState", length = 4)
	public Integer getGrantState() {
		return grantState;
	}

	public void setGrantState(Integer grantState) {
		this.grantState = grantState;
	}

	@Column(name = "comment", length = 500)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name = "operator", length = 36, updatable = false)
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "studentId", updatable = false)
	public Tstudent getStudent() {
		return student;
	}

	public void setStudent(Tstudent student) {
		this.student = student;
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
		TqualificationLicense other = (TqualificationLicense) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TdriverLicense [id=" + id + ", numbering=" + numbering
				+ ", firstGetDate=" + firstGetDate + ", grantDate=" + grantDate
				+ ", recieveDate=" + recieveDate + ", driverType=" + driverType
				+ ", validBeginDate=" + validBeginDate + ", validYear="
				+ validYear + ", student=" + student + "]";
	}

}

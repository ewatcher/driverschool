package com.tuocheng.model.demo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NaturalId;

/**
 * 微信用户信息实体模型(DAO专用)
 * 
 * @author 李杰
 * 
 */
@Entity
@Table(name = "tb_weixin_user", schema = "")
public class TweixinUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9090317034590501716L;
	// ===========base properties==============
	private String id;
	private String name; // 用户名
	private String openId; // 微信用户唯一识别码
	private String unionId; // 通一个用户不同平台下统一识别码
	private String studentId; // 学员ID
	private String monitoerId;// 校长ID
	private String trainerId; // 教练员ID
	private String password; // 密码
	private String mobile; // 手机号码
	private String email; // 电子邮箱
	private String icon; // 头像
	private Integer sex; // 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	private String city; // 用户所在城市
	private String country; // 用户所在国家
	private String province; // 用户所在省份
	private String language; // 用户的语言，简体中文为zh_CN
	private Date lastMsgTime; // 最后发送消息时间
	private String lastMsgContent; // 最后发送的消息内容
	private Integer msgSum; // 消息总数
	private Integer msgUnread; // 未读的消息总数
	private Integer visit; // 用户访问次数
	private Date lastVisitTime; // 最后访问时间
	private Date createTime; // 创建时间
	private String ext; // 扩展
	private Integer fromType; // 来自（1：公众号， 2：微信web）
	private Integer status; // 状态
	private String realName;// 微信用记对应本系统中的用户名
	private Integer reservationState;// 学预约状态：0：不可预约，1：可预约，默认为0

	private String organizationId; // 机构ID,分校ID

	// ==============relation properties==============
	public TweixinUser() {
	}

	public TweixinUser(String id, String name, String openId, String unionId, String studentId, String monitoerId,
			String trainerId, String password, String mobile, String email, String icon, Integer sex, String city,
			String country, String province, String language, Date lastMsgTime, String lastMsgContent, Integer msgSum,
			Integer msgUnread, Integer visit, Date lastVisitTime, Date createTime, String ext, Integer fromType,
			Integer status, String realName, Integer reservationState, String organizationId) {
		this.id = id;
		this.name = name;
		this.openId = openId;
		this.unionId = unionId;
		this.studentId = studentId;
		this.monitoerId = monitoerId;
		this.trainerId = trainerId;
		this.password = password;
		this.mobile = mobile;
		this.email = email;
		this.icon = icon;
		this.sex = sex;
		this.city = city;
		this.country = country;
		this.province = province;
		this.language = language;
		this.lastMsgTime = lastMsgTime;
		this.lastMsgContent = lastMsgContent;
		this.msgSum = msgSum;
		this.msgUnread = msgUnread;
		this.visit = visit;
		this.lastVisitTime = lastVisitTime;
		this.createTime = createTime;
		this.ext = ext;
		this.fromType = fromType;
		this.status = status;
		this.realName = realName;
		this.reservationState = reservationState;
		this.organizationId = organizationId;
	}

	@Id
	@Column(name = "id", nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "name", length = 100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "openId", nullable = false, length = 100)
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@Column(name = "unionId", length = 100)
	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	@Column(name = "studentId", length = 36)
	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	@Column(name = "trainerId", length = 36)
	public String getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(String trainerId) {
		this.trainerId = trainerId;
	}

	@Column(name = "monitoerId", length = 36)
	public String getMonitoerId() {
		return monitoerId;
	}

	public void setMonitoerId(String monitoerId) {
		this.monitoerId = monitoerId;
	}

	@Column(name = "password", length = 100)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "mobile", length = 20)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "email", length = 100)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "icon", length = 200)
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Column(name = "sex", length = 4)
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	@Column(name = "city", length = 100)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "country", length = 100)
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "province", length = 100)
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "language", length = 100)
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lastMsgTime", length = 7)
	public Date getLastMsgTime() {
		return lastMsgTime;
	}

	public void setLastMsgTime(Date lastMsgTime) {
		this.lastMsgTime = lastMsgTime;
	}

	@Column(name = "lastMsgContent", length = 300)
	public String getLastMsgContent() {
		return lastMsgContent;
	}

	public void setLastMsgContent(String lastMsgContent) {
		this.lastMsgContent = lastMsgContent;
	}

	@Column(name = "msgSum", length = 10)
	public Integer getMsgSum() {
		return msgSum;
	}

	public void setMsgSum(Integer msgSum) {
		this.msgSum = msgSum;
	}

	@Column(name = "msgUnread", length = 10)
	public Integer getMsgUnread() {
		return msgUnread;
	}

	public void setMsgUnread(Integer msgUnread) {
		this.msgUnread = msgUnread;
	}

	@Column(name = "visit", length = 10)
	public Integer getVisit() {
		return visit;
	}

	public void setVisit(Integer visit) {
		this.visit = visit;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lastVisitTime", length = 7)
	public Date getLastVisitTime() {
		return lastVisitTime;
	}

	public void setLastVisitTime(Date lastVisitTime) {
		this.lastVisitTime = lastVisitTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createTime", length = 7)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "ext", length = 300)
	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	@Column(name = "fromType", length = 10)
	public Integer getFromType() {
		return fromType;
	}

	public void setFromType(Integer fromType) {
		this.fromType = fromType;
	}

	@Column(name = "status", length = 10)
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "organizationId", length = 36)
	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	@Column(name = "realName")
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Column(name="reservationState",columnDefinition="INT DEFAULT 0")
	public Integer getReservationState() {
		return reservationState;
	}

	public void setReservationState(Integer reservationState) {
		this.reservationState = reservationState;
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
		TweixinUser other = (TweixinUser) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TweixinUser [id=" + id + ", name=" + name + ", openId=" + openId + ", unionId=" + unionId
				+ ", studentId=" + studentId + ", monitoerId=" + monitoerId + ", trainerId=" + trainerId + ", password="
				+ password + ", mobile=" + mobile + ", email=" + email + ", icon=" + icon + ", sex=" + sex + ", city="
				+ city + ", country=" + country + ", province=" + province + ", language=" + language + ", lastMsgTime="
				+ lastMsgTime + ", lastMsgContent=" + lastMsgContent + ", msgSum=" + msgSum + ", msgUnread=" + msgUnread
				+ ", visit=" + visit + ", lastVisitTime=" + lastVisitTime + ", createTime=" + createTime + ", ext="
				+ ext + ", fromType=" + fromType + ", status=" + status + ", organizationId=" + organizationId + "]";
	}

}

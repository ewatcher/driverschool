package com.tuocheng.pageModel.demo;

import java.util.Date;

/**
 * 微信用户管理实体类(action专用)
 * 
 * @author 李杰
 * 
 */
public class WeixinUser extends PageModel {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 365741094971384357L;

	private String id;
	private String name; // 用户名
	private String openId; // 微信用户唯一识别码
	private String unionId; // 通一个用户不同平台下统一识别码
	private String studentId; // 学员ID
	private String monitoerId;// 校长IDs
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
	private String organizationId; // 机构ID,分校ID
	private String organizationName; // 机构名称
	private String realName;// 微信用记对应本系统中的用户名
	private Integer reservationState;// 学预约状态：0：不可预约，1：可预约，默认为0

	private String ids;
	private Date createTimeStart;// 注册时间－－－范围查询
	private Date createTimeEnd;// 注册时间－－－范围查询

	public Integer getReservationState() {
		return reservationState;
	}

	public void setReservationState(Integer reservationState) {
		this.reservationState = reservationState;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getMonitoerId() {
		return monitoerId;
	}

	public void setMonitoerId(String monitoerId) {
		this.monitoerId = monitoerId;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Date getLastMsgTime() {
		return lastMsgTime;
	}

	public void setLastMsgTime(Date lastMsgTime) {
		this.lastMsgTime = lastMsgTime;
	}

	public String getLastMsgContent() {
		return lastMsgContent;
	}

	public void setLastMsgContent(String lastMsgContent) {
		this.lastMsgContent = lastMsgContent;
	}

	public Integer getMsgSum() {
		return msgSum;
	}

	public void setMsgSum(Integer msgSum) {
		this.msgSum = msgSum;
	}

	public Integer getMsgUnread() {
		return msgUnread;
	}

	public void setMsgUnread(Integer msgUnread) {
		this.msgUnread = msgUnread;
	}

	public Integer getVisit() {
		return visit;
	}

	public void setVisit(Integer visit) {
		this.visit = visit;
	}

	public Date getLastVisitTime() {
		return lastVisitTime;
	}

	public void setLastVisitTime(Date lastVisitTime) {
		this.lastVisitTime = lastVisitTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public Integer getFromType() {
		return fromType;
	}

	public void setFromType(Integer fromType) {
		this.fromType = fromType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Date getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

}

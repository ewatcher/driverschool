package com.tuocheng.pageModel.demo;

import java.util.Date;


/**
 * 微信报名实体类(action专用)
 * 
 * @author 李杰
 * 
 */
public class SignUp extends PageModel {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 365741094971384357L;
	
	private String id;
	private String openId; // 用户微信ID
	private String upMobile; // 用户提交的联系方式
	private Integer upAge; // 用户提交的年龄
	private String upName;   // 用户提交的名称
	private String upCity; // 用户提交的所在地
	private Date upTime; // 用户提交的时间
	private String comments; // 备注
	private Integer status; // 状态。 1：已联系
	private String organizationId; // 机构ID,分校ID
	
	private String organizationName; // 机构名称
	
	private String ids;
	private Date upTimeStart;// 报名时间－－－范围查询
	private Date upTimeEnd;// 报名时间－－－范围查询

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getUpMobile() {
		return upMobile;
	}

	public void setUpMobile(String upMobile) {
		this.upMobile = upMobile;
	}

	public Integer getUpAge() {
		return upAge;
	}

	public void setUpAge(Integer upAge) {
		this.upAge = upAge;
	}

	public String getUpName() {
		return upName;
	}

	public void setUpName(String upName) {
		this.upName = upName;
	}

	public String getUpCity() {
		return upCity;
	}

	public void setUpCity(String upCity) {
		this.upCity = upCity;
	}

	public Date getUpTime() {
		return upTime;
	}

	public void setUpTime(Date upTime) {
		this.upTime = upTime;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Date getUpTimeStart() {
		return upTimeStart;
	}

	public void setUpTimeStart(Date upTimeStart) {
		this.upTimeStart = upTimeStart;
	}

	public Date getUpTimeEnd() {
		return upTimeEnd;
	}

	public void setUpTimeEnd(Date upTimeEnd) {
		this.upTimeEnd = upTimeEnd;
	}

}

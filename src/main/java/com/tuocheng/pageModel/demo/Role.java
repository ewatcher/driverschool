package com.tuocheng.pageModel.demo;

import java.util.Date;

public class Role implements java.io.Serializable {

	private String ids;
	private String authIds;
	private String authNames;
	private int page;// 当前页
	private int rows;// 每页显示记录数
	private String sort;// 排序字段名
	private String order;// 按什么排序(asc,desc)
	private String schoolArea;// 所属校区
	private String schoolAreaName;
	private Date createDate;// 创建时间
	private String operator;// 创建

	public String getAuthIds() {
		return authIds;
	}

	public void setAuthIds(String authIds) {
		this.authIds = authIds;
	}

	public String getAuthNames() {
		return authNames;
	}

	public void setAuthNames(String authNames) {
		this.authNames = authNames;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSchoolAreaName() {
		return schoolAreaName;
	}

	public void setSchoolAreaName(String schoolAreaName) {
		this.schoolAreaName = schoolAreaName;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	private String cid;
	private String cdesc;
	private String cname;

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCdesc() {
		return cdesc;
	}

	public void setCdesc(String cdesc) {
		this.cdesc = cdesc;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

}

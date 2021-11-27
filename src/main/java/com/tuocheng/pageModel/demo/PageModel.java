package com.tuocheng.pageModel.demo;

import java.io.Serializable;

/**
 * 分页专用实体类
 * 
 * @author MEI702
 *
 */
public class PageModel implements Serializable {
	private int page;// 当前页
	private int rows;// 每页显示记录数
	private String sort;// 排序字段名
	private String order;// 按什么排序(asc,desc)

	private String principalType;
	private String principalSn;
	private int permission;

	public String getPrincipalType() {
		return principalType;
	}

	public void setPrincipalType(String principalType) {
		this.principalType = principalType;
	}

	public String getPrincipalSn() {
		return principalSn;
	}

	public void setPrincipalSn(String principalSn) {
		this.principalSn = principalSn;
	}

	public int getPermission() {
		return permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
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

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}

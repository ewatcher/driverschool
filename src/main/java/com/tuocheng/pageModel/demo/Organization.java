package com.tuocheng.pageModel.demo;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.tuocheng.model.demo.Torganization;

public class Organization {
	private String id;// 机构标识
	private String name;// 机构名称
	private BigDecimal sequence;// 排序
	private String iconcls;// 图标
	private String url;// 连接地址
	private Torganization parent;// 父级机构

	private String myId;
	private String parentId;
	private String parentName;
	private String state = "open";// 是否展开(open,closed)

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

	public BigDecimal getSequence() {
		return sequence;
	}

	public void setSequence(BigDecimal sequence) {
		this.sequence = sequence;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Torganization getParent() {
		return parent;
	}

	public void setParent(Torganization parent) {
		this.parent = parent;
	}

	public String getMyId() {
		return myId;
	}

	public void setMyId(String myId) {
		this.myId = myId;
	}


	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIconcls() {
		return iconcls;
	}

	public void setIconcls(String iconcls) {
		this.iconcls = iconcls;
	}

}

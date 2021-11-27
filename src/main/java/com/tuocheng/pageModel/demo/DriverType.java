package com.tuocheng.pageModel.demo;

import java.io.Serializable;

public class DriverType implements Serializable {
	private int id;
	private String name;
	private int[] ids;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int[] getIds() {
		return ids;
	}

	public void setIds(int[] ids) {
		this.ids = ids;
	}

}

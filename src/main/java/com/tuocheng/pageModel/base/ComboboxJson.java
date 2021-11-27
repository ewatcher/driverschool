package com.tuocheng.pageModel.base;

import java.io.Serializable;

/**
 * 专用于combobox返回Json格式数据的实体通用类
 * 
 * @author TC-EWATCHER
 * 
 */
public class ComboboxJson implements Serializable {
	private static final long serialVersionUID = 6511185860164074712L;
	private String value;
	private String text;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}

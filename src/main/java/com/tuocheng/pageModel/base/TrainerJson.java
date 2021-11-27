package com.tuocheng.pageModel.base;

import java.io.Serializable;
/**
 * 查找教练名称JSON实体
 * @author MEI702
 *
 */
public class TrainerJson implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6364943540652794267L;
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

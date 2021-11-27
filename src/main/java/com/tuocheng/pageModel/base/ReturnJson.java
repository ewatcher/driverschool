package com.tuocheng.pageModel.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * Service层方法返回值实体类
 * 
 * @author MEI702
 * 
 */
public class ReturnJson<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<T> retDataList;// 方法返回值
	private Map<String, String> errorMsg;// 方法返回错误信息
	private boolean success;// 操作是否成功

	/**
	 * 此变量用来传递数据（action专用的model）
	 * 
	 */
	public T model;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ReturnJson() {
		try {
			ParameterizedType type = (ParameterizedType) this.getClass()
					.getGenericSuperclass();
			Class clazz = (Class) type.getActualTypeArguments()[0];
			model = (T) clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<T> getRetDataList() {
		return retDataList;
	}

	public void setRetDataList(List<T> retDataList) {
		this.retDataList = retDataList;
	}

	public Map<String, String> getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(Map<String, String> errorMsg) {
		this.errorMsg = errorMsg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getModel() {
		return model;
	}

	public void setModel(T model) {
		this.model = model;
	}

}

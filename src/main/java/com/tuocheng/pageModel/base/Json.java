package com.tuocheng.pageModel.base;

/**
 * JSON模型
 * 
 * @author 农峰
 * 
 */
public class Json implements java.io.Serializable {

	private boolean success = false;// 是否成功
	private String msg = "";// 提示信息
	/**
	 * 此对象在后台操作数据成功后，将此操作后的数据通过此对象返回给前台
	 */
	private Object obj = null;// 其他信息

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}

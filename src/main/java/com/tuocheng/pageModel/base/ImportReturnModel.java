package com.tuocheng.pageModel.base;

public class ImportReturnModel {
	private boolean success = false;// 是否成功
	private String msg = "";// 提示信息
	private String successDatas; // 成功导入的数据
	private String failDatas;// 无法导入的数据
	private Long successTotal;// 成功导入总数
	private Long failTotal;// 无法导入的总数
	/**
	 * 此对象在后台操作数据成功后，将此操作后的数据通过此对象返回给前台
	 */
	private Object obj = null;// 其他信息
	private Integer size;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

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

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getSuccessDatas() {
		return successDatas;
	}

	public void setSuccessDatas(String successDatas) {
		this.successDatas = successDatas;
	}

	public String getFailDatas() {
		return failDatas;
	}

	public void setFailDatas(String failDatas) {
		this.failDatas = failDatas;
	}

	public Long getSuccessTotal() {
		return successTotal;
	}

	public void setSuccessTotal(Long successTotal) {
		this.successTotal = successTotal;
	}

	public Long getFailTotal() {
		return failTotal;
	}

	public void setFailTotal(Long failTotal) {
		this.failTotal = failTotal;
	}

}

package com.tuocheng.service.demo;

import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.Bug;

public interface BugServiceI {

	/**
	 * 获得数据表格
	 * 
	 * @param bug
	 * @return
	 */
	public DataGrid datagrid(Bug bug)throws Exception;

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids)throws Exception;

	/**
	 * 添加
	 * 
	 * @param bug
	 */
	public void add(Bug bug)throws Exception;

	public String getDescById(String cid)throws Exception;

	public void update(Bug bug)throws Exception;
}

package com.tuocheng.service.demo;

import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.CollectFee;

/**
 * 短信实体类管理接口
 * 
 * @author MEI702
 * 
 */
public interface CollectFeeServiceI {
	/**
	 * 添加短信实体类信息
	 * 
	 * @param collectFee
	 * @return
	 */
	public CollectFee save(CollectFee collectFee)throws Exception;

	/**
	 * 更新短信实体类信息
	 * 
	 * @param collectFee
	 * @return
	 */
	public CollectFee udpate(CollectFee collectFee)throws Exception;

	/**
	 * 根据id标识删除短信实体类信息
	 * 
	 * @param ids
	 */
	public void delete(String ids)throws Exception;

	/**
	 * 从后台中获取所有短信实体类的信息，并以datagrid开发传递到前台
	 * 
	 * @param collectFee
	 * @return
	 */
	public DataGrid dataGrid(CollectFee collectFee)throws Exception;
	
}

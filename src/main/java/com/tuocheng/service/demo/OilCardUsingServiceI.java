package com.tuocheng.service.demo;


import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.OilCardUsing;

/**
 * 油卡管理接口
 * 
 * @author MEI702
 * 
 */
public interface OilCardUsingServiceI {
	/**
	 * 添加油卡信息
	 * 
	 * @param oilCardUsing
	 * @return
	 */
	public OilCardUsing save(OilCardUsing oilCardUsing)throws Exception;

	/**
	 * 更新油卡信息
	 * 
	 * @param oilCardUsing
	 * @return
	 */
	public OilCardUsing udpate(OilCardUsing oilCardUsing)throws Exception;

	/**
	 * 根据id标识删除油卡信息
	 * 
	 * @param ids
	 */
	public void delete(String ids)throws Exception;

	/**
	 * 从后台中获取所有油卡的信息，并以datagrid开发传递到前台
	 * 
	 * @param oilCardUsing
	 * @return
	 */
	public DataGrid dataGrid(OilCardUsing oilCardUsing)throws Exception;

}

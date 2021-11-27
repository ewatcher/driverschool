package com.tuocheng.service.demo;


import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.RechargeOilCard;

/**
 * 油卡管理接口
 * 
 * @author MEI702
 * 
 */
public interface RechargeOilCardServiceI {
	/**
	 * 添加油卡信息
	 * 
	 * @param rechargeOilcard
	 * @return
	 */
	public RechargeOilCard save(RechargeOilCard rechargeOilcard)throws Exception;

	/**
	 * 更新油卡信息
	 * 
	 * @param rechargeOilcard
	 * @return
	 */
	public RechargeOilCard udpate(RechargeOilCard rechargeOilcard)throws Exception;

	/**
	 * 根据id标识删除油卡信息
	 * 
	 * @param ids
	 */
	public void delete(String ids)throws Exception;

	/**
	 * 从后台中获取所有油卡的信息，并以datagrid开发传递到前台
	 * 
	 * @param rechargeOilcard
	 * @return
	 */
	public DataGrid dataGrid(RechargeOilCard rechargeOilcard)throws Exception;

}

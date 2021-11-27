package com.tuocheng.service.demo;

import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.OilCard;

/**
 * 油卡管理接口
 * 
 * @author MEI702
 * 
 */
public interface OilCardServiceI {
	/**
	 * 添加油卡信息
	 * 
	 * @param oilCar
	 * @return
	 */
	public OilCard save(OilCard oilCar) throws Exception;

	/**
	 * 更新油卡信息
	 * 
	 * @param oilCar
	 * @return
	 */
	public OilCard udpate(OilCard oilCar) throws Exception;

	/**
	 * 根据id标识删除油卡信息
	 * 
	 * @param ids
	 */
	public void delete(String ids) throws Exception;

	/**
	 * 从后台中获取所有油卡的信息，并以datagrid开发传递到前台
	 * 
	 * @param oilCar
	 * @return
	 */
	public DataGrid dataGrid(OilCard oilCar) throws Exception;

	/**
	 * 根据卡标识，查找该卡最终余额
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Double getLatelyBalanceByOilcardId(String id) throws Exception;

}

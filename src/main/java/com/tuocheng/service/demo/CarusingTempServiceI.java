package com.tuocheng.service.demo;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.CarusingTemp;

/**
 * 车辆使用管理接口
 * 
 * @author MEI702
 * 
 */
public interface CarusingTempServiceI {
	/**
	 * 添加实体信息
	 * 
	 * @param trainerReservation
	 * @return
	 */
	public CarusingTemp save(CarusingTemp carusingTemp) throws Exception;

	/**
	 * 更新实体信息
	 * 
	 * @param trainerReservation
	 * @return
	 */
	public CarusingTemp udpate(CarusingTemp CarusingTemp) throws Exception;

	/**
	 * 根据id标识删除实体信息
	 * 
	 * @param ids
	 */
	public void delete(String ids) throws Exception;

	/**
	 * 从后台中获取所有实体的信息，并以datagrid开发传递到前台
	 * 
	 * @param trainerReservation
	 * @return
	 */
	public DataGrid dataGrid(CarusingTemp CarusingTemp) throws Exception;

	/**
	 * 从后台中获取所有实体的信息，并以datagrid开发传递到前台
	 * 
	 * @param trainerReservation
	 * @return
	 */
	public DataGrid personalDataGrid(CarusingTemp CarusingTemp)
			throws Exception;
	/**
	 * 根据日期，统计车辆使用情况
	 * @param date
	 * @return（车辆标识　（统计参数，值））
	 */
	public Map<String, Integer> getUsingcarCout(String carId,Date date)throws ParseException;
}

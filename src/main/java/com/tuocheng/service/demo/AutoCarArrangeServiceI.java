package com.tuocheng.service.demo;

import java.util.Date;
import java.util.List;

import com.tuocheng.pageModel.base.ComboboxJson;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.AutoCarArrange;

/**
 * 教练排班管理接口
 * 
 * @author MEI702
 * 
 */
public interface AutoCarArrangeServiceI {
	/**
	 * 添加教练排班信息
	 * 
	 * @param autoCarArrange
	 * @return
	 */
	public boolean save(AutoCarArrange autoCarArrange) throws Exception;

	/**
	 * 更新教练排班信息
	 * 
	 * @param autoCarArrange
	 * @return
	 */
	public AutoCarArrange udpate(AutoCarArrange autoCarArrange)
			throws Exception;

	/**
	 * 根据id标识删除教练排班信息
	 * 
	 * @param ids
	 */
	public void delete(String ids) throws Exception;

	/**
	 * 从后台中获取所有教练排班的信息，并以datagrid开发传递到前台
	 * 
	 * @param autoCarArrange
	 * @return
	 */
	public DataGrid dataGrid(AutoCarArrange autoCarArrange) throws Exception;

	/**
	 * 根据教练标识查找出最近的排班日期
	 * 
	 * @param trainerId
	 * @return
	 */
	public Date getNealyArrageDateByCarId(String carId);


	/**
	 * 根据预约时间查找可预约的自动档车辆信息
	 * @param schoolArea
	 * @param arrangeDate
	 * @param itemTime
	 * @param invokeFlag 1:微信预约 0：后台调用
	 * @return
	 * @throws Exception
	 */
	public List<ComboboxJson> getAutoCarsForReservation(String schoolArea,
			Date arrangeDate, Integer itemTime,Integer invokeFlag) throws Exception;

	/**
	 * 根据预约时间判断是否有C2车辆预约
	 * @param schoolArea
	 * @param arrangeDate
	 * @param itemTime
	 * @return
	 * @throws Exception
	 */
	public boolean checkAutocarFree(String schoolArea, Date arrangeDate,
			Integer itemTime) throws Exception;

}

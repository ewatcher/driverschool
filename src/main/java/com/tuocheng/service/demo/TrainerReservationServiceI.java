package com.tuocheng.service.demo;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.TrainerReservation;

/**
 * 教练预约管理接口
 * 
 * @author MEI702
 * 
 */
public interface TrainerReservationServiceI {
	/**
	 * 添加教练预约信息
	 * 
	 * @param trainerReservation
	 * @return
	 */
	public TrainerReservation save(TrainerReservation trainerReservation)
			throws Exception;

	/**
	 * 更新教练预约信息
	 * 
	 * @param trainerReservation
	 * @return
	 */
	public TrainerReservation udpate(TrainerReservation trainerReservation)
			throws Exception;

	/**
	 * 根据id标识删除教练预约信息
	 * 
	 * @param ids
	 */
	public void delete(String ids) throws Exception;

	/**
	 * 从后台中获取所有教练预约的信息，并以datagrid开发传递到前台
	 * 
	 * @param trainerReservation
	 * @return
	 */
	public DataGrid dataGrid(TrainerReservation trainerReservation)
			throws Exception;

	/**
	 * 从后台中获取所有教练预约的信息，并以datagrid开发传递到前台
	 * 
	 * @param trainerReservation
	 * @return
	 */
	public DataGrid personalDataGrid(TrainerReservation trainerReservation)
			throws Exception;

	/**
	 * 统计教练预约信息
	 * @param trainerId
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public Map<String, Integer> getTrainerReservationCount(String trainerId,
			Date date) throws ParseException;
}

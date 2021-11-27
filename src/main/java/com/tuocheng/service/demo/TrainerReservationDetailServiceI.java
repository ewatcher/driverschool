package com.tuocheng.service.demo;

import java.util.Date;
import java.util.List;

import com.tuocheng.model.demo.TtrainerReservationDetail;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.TrainerReservationDetail;

/**
 * 档案管理实体类
 * 
 * @author 农峰
 * 
 */
public interface TrainerReservationDetailServiceI {
	/**
	 * 添加预约信息
	 * 
	 * @param reservationDetail
	 * @return
	 */
	public TrainerReservationDetail save(
			TrainerReservationDetail reservationDetail) throws Exception;

	/**
	 * 更新预约信息
	 * 
	 * @param reservationDetail
	 * @return
	 */
	public TrainerReservationDetail udpate(
			TrainerReservationDetail reservationDetail) throws Exception;

	/**
	 * 根据id标识删除预约信息
	 * 
	 * @param ids
	 */
	public boolean delete(String ids) throws Exception;

	/**
	 * 从后台中获取所有预约的信息，并以datagrid开发传递到前台
	 * 
	 * @param reservationDetail
	 * @return
	 */
	public DataGrid dataGrid(TrainerReservationDetail reservationDetail)
			throws Exception;

	/**
	 * 从后台中获取所有预约的信息，并以datagrid开发传递到前台
	 * 
	 * @param reservationDetail
	 * @return
	 */
	public DataGrid personalDataGrid(TrainerReservationDetail reservationDetail)
			throws Exception;

	/**
	 * 根据教练标识、排班日期开始，排班日期结束三个条件查询教练预约明细信息
	 * 
	 * @param trainerId
	 * @param reservarionDateBegin
	 * @param reservarionDateEnd
	 * @return
	 * @throws Exception
	 */
	public List<TtrainerReservationDetail> getTrainerReservationDetailByTrainerId(
			String trainerId, Date reservarionDateBegin, Date reservarionDateEnd)
			throws Exception;

	/**
	 * 根据教练标识，校区标识查找出所有该教练的预约明细信息
	 * 
	 * @param trainerId
	 * @param schoolArea
	 * @return
	 */
	public List<TrainerReservationDetail> trainerReservationDetailsForUpdate(
			String trainerId, String schoolArea);

	/**
	 * 
	 * @param reservationDetail
	 * @return
	 * @throws Exception
	 */
	public TrainerReservationDetail arrangeDetailUpdate(
			TrainerReservationDetail reservationDetail) throws Exception;

	/**
	 * 根据教练标识，获取该教练最新的预约日期(取最大值)
	 * 
	 * @param trainerId
	 * @param schoolArea
	 * @return
	 * @throws Exception
	 */
	public Date getLatestDateByTrainerId(String trainerId, String schoolArea)
			throws Exception;

	/**
	 * 根据教练标识，预约日期查找教练预约明细信息
	 * 
	 * @param reservationDetail
	 * @return
	 * @throws Exception
	 */
	public TrainerReservationDetail getDetailByTrainerIdDate(String trainerId,
			Date date) throws Exception;
}

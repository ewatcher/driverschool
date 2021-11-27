package com.tuocheng.service.demo;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tuocheng.model.demo.Treservation;
import com.tuocheng.model.demo.Ttrainer;
import com.tuocheng.pageModel.base.ComboboxJson;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.Reservation;
import com.tuocheng.pageModel.demo.TrainerReservationDetail;

/**
 * 预约管理接口
 * 
 * @author MEI702
 * 
 */
public interface ReservationServiceI {
	/**
	 * 添加预约信息
	 * 
	 * @param reservation
	 * @return(1：预约成绩 -1:表示学时已经用完，-2：表示该教练在该时间段已经存在预约，不可重复，-3：表示预约失败)
	 */
	public Integer save(Reservation reservation) throws Exception;

	/**
	 * 添加预约信息(wexin use only)
	 * 
	 * @param reservation
	 * @return (1：预约成绩 -1:表示学时已经用完，-2：表示该教练在该时间段已经存在预约，不可重复，-3：表示预约失败)
	 * @throws Exception
	 */
	public Integer saveForWexin(Reservation reservation) throws Exception;

	/**
	 * 更新预约信息
	 * 
	 * @param reservation
	 * @return
	 */
	public Reservation udpate(Reservation reservation) throws Exception;

	/**
	 * 根据id标识删除预约信息
	 * 
	 * @param ids
	 */
	public void delete(String ids) throws Exception;

	/**
	 * 从后台中获取所有预约的信息，并以datagrid开发传递到前台
	 * 
	 * @param reservation
	 * @return
	 */
	public DataGrid dataGrid(Reservation reservation) throws Exception;

	/**
	 * 得到某学员的预约信息
	 * 
	 * @param studentId
	 * @return
	 */
	public List<Treservation> listByStudent(String studentId) throws Exception;
	
	/**
	 * 取消预约信息并保留该信息
	 * @param reservationId
	 * @param invokeFlag(0:后台，1微信)
	 * @throws Exception
	 */
	public Integer cancel(String reservationId,Integer invokeFlag)
			throws Exception;

	/**
	 * 根据预约标识，学员确认状态，教练确认状态进行修改该预约信息
	 * 
	 * @param reservation
	 * @return
	 * @throws Exception
	 */
	public void confirmReservation(String reservationId, Integer confirmFlag) throws Exception;

	/**
	 * 判断学员一天内只能预约两个小时
	 * 
	 * @param itemStudentid
	 * @param reservationDate
	 * @return
	 */
	public boolean checkTotalByDate(String studentId, Date reservationDat, Integer reservationType) throws Exception;

	/**
	 * 根据教练预约明细信息，查找出该教练预约明细的预约状态
	 * 
	 * @param detail
	 * @return
	 * @throws Exception
	 */
	public List<Integer> getStateByReservationDetailId(TrainerReservationDetail detail) throws Exception;

	/**
	 * 按日，周，月，年统计预约总数（总体分析统计）
	 * 
	 * @param type
	 * @param sql
	 * @param functionFlag
	 * @param firstDateFlay
	 * @param lastDateFlay
	 * @return
	 * @throws ParseException
	 */
	public List<ComboboxJson> getReservationCoutByDateType(Integer type, String sql, Integer functionFlag,
			Integer firstDateFlay, Integer lastDateFlay) throws ParseException;

	/**
	 * 按周详细统计本周内每一天的学员报名数量
	 * 
	 * @param weekFlag
	 * @return
	 * @throws ParseException
	 */
	public Map<String, Map<String, Integer>> getReservationCountByOrgIds(Integer weekFlag) throws ParseException;

	/**
	 * 根据教练标识，查找当前教练是否存在没有完成的预约
	 * 
	 * @param schoolArea
	 * @param trainerId
	 * @return
	 * @throws ParseException
	 */
	public boolean getUnfinishedReservation(String schoolArea, String trainerId) throws ParseException;

	/**
	 * 判断用户在同一时间段内是否重复预约
	 * 
	 * @param reservation
	 * @return
	 * @throws Exception
	 */
	public boolean reservationExit(String studentId, Date date, Integer fieldOptionFlag, String schoolArea)
			throws Exception;

	/**
	 * 根据预约类型，判断用户在同一时间段内是否重复预约
	 * 
	 * @param studentId
	 * @param date
	 * @param fieldOptionFlag
	 * @param schoolArea
	 * @param reservationType
	 * @return
	 * @throws Exception
	 */
	public boolean checkExit(String studentId, Date date, Integer fieldOptionFlag, String schoolArea,
			Integer reservationType) throws Exception;

	/**
	 * 采用石英调试自动完成当前日期的预约状态修改为完成
	 * 
	 * @param date
	 */
	public void createConfirmReservation();

	/**
	 * 限制新学员预约 （百色校区：新学员允许预约电车，模块，及5个小时的五项，科目成绩合格后才能正常预约）
	 * @param studentId
	 * @param reservationType
	 * @return -1:表示参数无效，-2:达到预约限制5小时，-3：不允许科目一不合格的学员预约路训 ，1：解除限制
	 * @throws Exception
	 */
	public Integer limitNewStudentReservation(String studentId, Integer reservationType) throws Exception;

}

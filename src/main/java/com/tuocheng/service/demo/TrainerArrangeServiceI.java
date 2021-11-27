package com.tuocheng.service.demo;

import java.util.Date;
import java.util.List;

import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.TrainerArrange;

/**
 * 教练排班管理接口
 * 
 * @author MEI702
 * 
 */
public interface TrainerArrangeServiceI {
	/**
	 * 添加教练排班信息
	 * 
	 * @param trainerArrange
	 * @return
	 */
	public TrainerArrange save(TrainerArrange trainerArrange) throws Exception;

	/**
	 * 更新教练排班信息
	 * 
	 * @param trainerArrange
	 * @return
	 */
	public TrainerArrange udpate(TrainerArrange trainerArrange)
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
	 * @param trainerArrange
	 * @return
	 */
	public DataGrid dataGrid(TrainerArrange trainerArrange) throws Exception;

	/**
	 * 根据教练标识查找出最近的排班日期
	 * @param trainerId
	 * @return
	 */
	public Date getNealyArrageDateByTrainerId(String trainerId);

}

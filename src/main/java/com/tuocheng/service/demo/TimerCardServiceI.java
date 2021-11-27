package com.tuocheng.service.demo;

import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.TimerCard;

/**
 * 计时卡实体类Service接口
 * @author 李杰
 *
 */
public interface TimerCardServiceI {
	/**
	 * 添加实体类信息
	 * @param timerCard
	 * @return
	 */
	public TimerCard save(TimerCard timerCard)throws Exception;
	/**
	 * 删除实体类信息
	 * @param ids
	 */
	public void delete(String ids)throws Exception;
	
	/**
	 * 更新实体类信息
	 * @param timerCard
	 * @return
	 */
	public TimerCard update(TimerCard timerCard)throws Exception;
	
	/**
	 * 查找出后台所有该实体类的信息，并以datagrid形式传递给前台
	 * @param timerCard
	 * @return
	 */
	public DataGrid dataGrid(TimerCard timerCard)throws Exception;
	
	
}

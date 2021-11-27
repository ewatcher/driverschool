package com.tuocheng.service.demo;

import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.StudentTimerCard;

/**
 * 学生计时卡实体类Service接口
 * @author 李杰
 *
 */
public interface StudentTimerCardServiceI {
	/**
	 * 添加实体类信息
	 * @param stc
	 * @return
	 */
	public StudentTimerCard save(StudentTimerCard stc)throws Exception;
	
	/**
	 * 更新实体类信息
	 * @param stc
	 * @return
	 */
	public StudentTimerCard update(StudentTimerCard stc)throws Exception;
	
	/**
	 * 查找出后台所有该实体类的信息，并以datagrid形式传递给前台
	 * @param stc
	 * @return
	 */
	public DataGrid dataGrid(StudentTimerCard stc) throws Exception;
	
	/**
	 * 查找出后台所有该实体类的信息，并以datagrid形式传递给前台
	 * @param stc
	 * @return
	 */
	public DataGrid personalDataGrid(StudentTimerCard stc) throws Exception;
	
	
}

package com.tuocheng.service.demo;

import com.tuocheng.model.demo.Tprogress;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.Progress;

/**
 * 学员进度管理接口
 * 
 * @author 农峰
 *
 */
public interface ProgressServiceI {

	/**
	 * 更新学员进度信息
	 * 
	 * @param progress
	 * @return
	 */
	public Progress udpate(Progress progress) throws Exception;

	/**
	 * 根据id标识删除学员进度信息
	 * 
	 * @param ids
	 */
	public void delete(String ids) throws Exception;

	/**
	 * 从后台中获取所有学员进度的信息，并以datagrid开发传递到前台
	 * 
	 * @param progress
	 * @return
	 */
	public DataGrid dataGrid(Progress progress) throws Exception;

	/**
	 * 从后台中获取所有学员进度的信息，并以datagrid开发传递到前台
	 * 
	 * @param progress
	 * @return
	 */
	public DataGrid personalDataGrid(Progress progress) throws Exception;

	/**
	 * 根据学员标识，查找学员进度信息
	 * 
	 * @param studentId
	 * @return
	 * @throws Exception
	 */
	public Tprogress getSingleByStudentId(String schoolArea, String studentId) throws Exception;

	/**
	 * 修改学员状态
	 * @param id
	 * @param stateVal
	 * @return
	 * @throws Exception
	 * 
	 *             2017年8月29日 boolean
	 */
	public boolean updateProsessState(String id, Integer stateVal) throws Exception;
}

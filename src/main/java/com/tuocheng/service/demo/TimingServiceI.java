package com.tuocheng.service.demo;

import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.Timing;

/**
 * 档案管理实体类
 * 
 * @author 农峰
 * 
 */
public interface TimingServiceI {
	/**
	 * 添加预约信息
	 * 
	 * @param timing
	 * @return
	 */
	public Timing save(Timing timing) throws Exception;

	/**
	 * 更新预约信息
	 * 
	 * @param timing
	 * @return
	 */
	public Timing udpate(Timing timing) throws Exception;

	/**
	 * 根据id标识删除预约信息
	 * 
	 * @param ids
	 */
	public void delete(String ids) throws Exception;

	/**
	 * 从后台中获取所有预约的信息，并以datagrid开发传递到前台
	 * 
	 * @param timing
	 * @return
	 */
	public DataGrid dataGrid(Timing timing) throws Exception;

	/**
	 * 从后台中获取所有预约的信息，并以datagrid开发传递到前台
	 * 
	 * @param timing
	 * @return
	 */
	public DataGrid personalDataGrid(Timing timing) throws Exception;
	
	/**
	 * 根据学员标识查找学员学时信息
	 * @param studentId
	 * @return
	 * @throws Exception
	 */
	public Timing getSingleByStudentId(String studentId) throws Exception;
	
}

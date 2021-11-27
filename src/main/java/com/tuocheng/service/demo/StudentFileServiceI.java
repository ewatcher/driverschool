package com.tuocheng.service.demo;

import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.StudentFile;


/**
 * 档案管理实体类
 * @author 农峰
 *
 */
public interface StudentFileServiceI {
	/**
	 * 添加预约信息
	 * 
	 * @param studentFile
	 * @return
	 */
	public StudentFile save(StudentFile studentFile)throws Exception;

	/**
	 * 更新预约信息
	 * 
	 * @param studentFile
	 * @return
	 */
	public StudentFile udpate(StudentFile studentFile)throws Exception;

	/**
	 * 根据id标识删除预约信息
	 * 
	 * @param ids
	 */
	public void delete(String ids)throws Exception;

	/**
	 * 从后台中获取所有预约的信息，并以datagrid开发传递到前台
	 * 
	 * @param studentFile
	 * @return
	 */
	public DataGrid dataGrid(StudentFile studentFile) throws Exception;
}

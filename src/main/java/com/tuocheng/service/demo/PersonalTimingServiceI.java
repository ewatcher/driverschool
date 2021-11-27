package com.tuocheng.service.demo;

import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.PersonalTiming;

/**
 * 学员学时管理接口
 * 
 * @author 农峰
 * 
 */
public interface PersonalTimingServiceI {
	/**
	 * 添加学员个人学时信息
	 * 
	 * @param timing
	 * @return
	 */
	public PersonalTiming save(PersonalTiming timing) throws Exception;

	/**
	 * 更新学员个人学时信息
	 * 
	 * @param timing
	 * @return
	 */
	public PersonalTiming udpate(PersonalTiming timing) throws Exception;

	/**
	 * 根据id标识删除学员个人学时信息
	 * 
	 * @param ids
	 */
	public void delete(String ids) throws Exception;

	/**
	 * 从后台中获取所有学员个人学时的信息，并以datagrid开发传递到前台
	 * 
	 * @param timing
	 * @return
	 */
	public DataGrid dataGrid(PersonalTiming timing) throws Exception;

}

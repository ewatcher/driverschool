package com.tuocheng.service.demo;

import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.QuitSchool;

/**
 * 科目一考试管理接口
 * @author MEI702
 *
 */
public interface QuitschoolServiceI {
	/**
	 * 添加科目一考试信息
	 * @param quitSchool
	 * @return
	 */
	public QuitSchool save(QuitSchool quitSchool)throws Exception;
	/**
	 * 更新科目一考试信息
	 * @param quitSchool
	 * @return
	 */
	public QuitSchool udpate(QuitSchool quitSchool)throws Exception;
	/**
	 * 根据id标识删除科目一考试信息
	 * @param ids
	 */
	public void delete(String ids)throws Exception;
	/**
	 * 从后台中获取所有科目一考试的信息，并以datagrid开发传递到前台
	 * @param subjectTwo
	 * @return
	 */
	public DataGrid dataGrid(QuitSchool quitSchool)throws Exception;
}

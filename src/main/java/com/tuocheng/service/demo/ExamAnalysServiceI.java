package com.tuocheng.service.demo;

import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.ExamAnalys;

/**
 * 考试学员主列表管理接口
 * 
 * @author MEI702
 * 
 */
public interface ExamAnalysServiceI {
	/**
	 * 添加考试学员主列表信息
	 * 
	 * @param examAnalys
	 * @return
	 */
	public ExamAnalys save(ExamAnalys examAnalys) throws Exception;

	/**
	 * 更新考试学员主列表信息
	 * 
	 * @param examAnalys
	 * @return
	 */
	public ExamAnalys udpate(ExamAnalys examAnalys) throws Exception;

	/**
	 * 根据id标识删除考试学员主列表信息
	 * 
	 * @param ids
	 */
	public void delete(String ids) throws Exception;

	/**
	 * 从后台中获取所有考试学员主列表的信息，并以datagrid开发传递到前台
	 * 
	 * @param examAnalys
	 * @return
	 */
	public DataGrid dataGrid(ExamAnalys examAnalys) throws Exception;

	/**
	 * 取最大考试批次
	 * @param schoolArea
	 * @return
	 * @throws Exception
	 */
	public Integer getMaxBatchByschoolArea(String schoolArea)
			throws Exception;

}

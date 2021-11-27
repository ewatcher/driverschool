package com.tuocheng.service.demo;


import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.Log;

public interface LogServiceI {
	/**
	 * 添加日志信息
	 * 
	 * @param trainer
	 * @return
	 */
	public Log save(Log log) throws Exception;

	/**
	 * 更新日志信息
	 * 
	 * @param log
	 * @return
	 */
	public Log update(Log log) throws Exception;

	/**
	 * 根据ID标识删除日志信息
	 * 
	 * @param id
	 */
	public void delete(String id) throws Exception;

	/**
	 * 从数据库存中查找出所有日志员数据信息，并在datagrid方式传递给前台
	 * 
	 * @param log
	 * @return
	 */
	public DataGrid datagrid(Log log) throws Exception;

	/**
	 * 从数据库存中查找出所有日志员数据信息，并在datagrid方式传递给前台
	 * 
	 * @param log
	 * @return
	 */
	public DataGrid personalDatagrid(Log log) throws Exception;

	/**
	 * /** 通过ID得到实体对象
	 * 
	 * @param id
	 * @return
	 */
	public Log get(String id) throws Exception;

	/**
	 * 通过表明创建日志表
	 */
	public void createLogTable(String tableName);

	/**
	 * 动态保存日志
	 * @param log
	 */
	public void saveEntity(Log log);
}

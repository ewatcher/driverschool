package com.tuocheng.service.demo;

import java.util.List;

import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.Salesman;
import com.tuocheng.pageModel.demo.Difficulty;

/**
 * 
 * @author MEI702
 *
 */
public interface SalesmanServiceI {
	/**
	 * 添加实体类信息
	 * @param salesman
	 * @return
	 */
	public Salesman save(Salesman salesman)throws Exception;
	/**
	 * 删除实体类信息
	 * @param ids
	 */
	public void delete(String ids)throws Exception;
	
	/**
	 * 更新实体类信息
	 * @param salesman
	 * @return
	 */
	public Salesman update(Salesman salesman)throws Exception;
	
	/**
	 * 查找出后台所有该实体类的信息，并以datagrid形式传递给前台
	 * @param salesman
	 * @return
	 */
	public DataGrid dataGrid(Salesman salesman)throws Exception;
	
	/**
	 * 
	 * @param salesman
	 * @return
	 */
	public List<Salesman> listData(Salesman salesman)throws Exception;
	
	
}

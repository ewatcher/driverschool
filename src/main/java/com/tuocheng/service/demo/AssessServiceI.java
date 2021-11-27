package com.tuocheng.service.demo;

import java.util.List;

import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.Assess;
import com.tuocheng.pageModel.demo.Difficulty;

/**
 * 微信教练评论实体类Service接口
 * @author 李杰
 *
 */
public interface AssessServiceI {
	/**
	 * 添加实体类信息
	 * @param assess
	 * @return
	 */
	public Assess save(Assess assess)throws Exception;
	/**
	 * 删除实体类信息
	 * @param ids
	 */
	public void delete(String ids)throws Exception;
	
	/**
	 * 更新实体类信息
	 * @param assess
	 * @return
	 */
	public Assess update(Assess assess)throws Exception;
	
	/**
	 * 查找出后台所有该实体类的信息，并以datagrid形式传递给前台
	 * @param assess
	 * @return
	 */
	public DataGrid dataGrid(Assess assess)throws Exception;
	
	/**
	 * 
	 * @param assess
	 * @return
	 */
	public List<Assess> listData(Assess assess)throws Exception;
	
	
}

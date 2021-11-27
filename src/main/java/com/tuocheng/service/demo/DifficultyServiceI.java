package com.tuocheng.service.demo;

import java.util.List;

import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.Difficulty;

/**
 * 微信难点反馈实体类Service接口
 * @author 李杰
 *
 */
public interface DifficultyServiceI {
	/**
	 * 添加实体类信息
	 * @param difficulty
	 * @return
	 */
	public Difficulty save(Difficulty difficulty)throws Exception;
	/**
	 * 删除实体类信息
	 * @param ids
	 */
	public void delete(String ids)throws Exception;
	
	/**
	 * 更新实体类信息
	 * @param difficulty
	 * @return
	 */
	public Difficulty update(Difficulty difficulty)throws Exception;
	
	/**
	 * 查找出后台所有该实体类的信息，并以datagrid形式传递给前台
	 * @param difficulty
	 * @return
	 */
	public DataGrid dataGrid(Difficulty difficulty)throws Exception;
	
	
	/**
	 * 查找出后台所有该实体类的信息，并以datagrid形式传递给前台
	 * @param difficulty
	 * @return
	 */
	public List<Difficulty> listData(Difficulty difficulty)throws Exception;
	
}

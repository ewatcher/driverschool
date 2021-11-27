package com.tuocheng.service.demo;

import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.CommentStudent;

/**
 * 教练点评实体类Service接口
 * @author 李杰
 *
 */
public interface CommentStudentServiceI {
	/**
	 * 添加实体类信息
	 * @param commStudent
	 * @return
	 */
	public CommentStudent save(CommentStudent commStudent);
	/**
	 * 删除实体类信息
	 * @param ids
	 */
	public void delete(String ids);
	
	/**
	 * 更新实体类信息
	 * @param signUp
	 * @return
	 */
	public CommentStudent update(CommentStudent commStudent);
	
	/**
	 * 查找出后台所有该实体类的信息，并以datagrid形式传递给前台
	 * @param signUp
	 * @return
	 */
	public DataGrid dataGrid(CommentStudent commStudent);
	
	
}

package com.tuocheng.service.demo;

import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.SignUp;

/**
 * 微信报名实体类Service接口
 * @author 李杰
 *
 */
public interface SignUpServiceI {
	/**
	 * 添加实体类信息
	 * @param signUp
	 * @return
	 */
	public SignUp save(SignUp signUp);
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
	public SignUp update(SignUp signUp);
	
	/**
	 * 查找出后台所有该实体类的信息，并以datagrid形式传递给前台
	 * @param signUp
	 * @return
	 */
	public DataGrid dataGrid(SignUp signUp);
	
	
}

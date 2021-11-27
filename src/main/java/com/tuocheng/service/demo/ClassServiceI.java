package com.tuocheng.service.demo;

import java.util.List;

import com.tuocheng.pageModel.base.ComboboxJson;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.ClassDesign;

/**
 * 实体类管理接口
 * @author MEI702
 *
 */
public interface ClassServiceI {
	/**
	 * 添加实体类信息
	 * @param clazz
	 * @return
	 */
	public ClassDesign save(ClassDesign clazz)throws Exception;
	/**
	 * 更新实体类信息
	 * @param clazz
	 * @return
	 */
	public ClassDesign udpate(ClassDesign clazz)throws Exception;
	/**
	 * 根据id标识删除实体类信息
	 * @param ids
	 */
	public void delete(String ids)throws Exception;
	/**
	 * 从后台中获取所有实体类的信息，并以datagrid开发传递到前台
	 * @param clazz
	 * @return
	 */
	public DataGrid dataGrid(ClassDesign clazz)throws Exception;
	/**
	 * 查找出所有班级名称，并了Json格式返回
	 * @return
	 */
	public List<ComboboxJson> getAllClass()throws Exception;
}

package com.tuocheng.service.demo;

import java.util.List;

import com.tuocheng.pageModel.base.ComboboxJson;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.Person;

/**
 * 人员管理接口
 * 
 * @author MEI702
 * 
 */
public interface PersonServiceI {
	/**
	 * 添加人员信息
	 * 
	 * @param person
	 * @return
	 */
	public Person save(Person person)throws Exception;

	/**
	 * 更新人员信息
	 * 
	 * @param person
	 * @return
	 */
	public Person udpate(Person person)throws Exception;

	/**
	 * 根据id标识删除人员信息
	 * 
	 * @param ids
	 */
	public void delete(String ids)throws Exception;

	/**
	 * 从后台中获取所有人员的信息，并以datagrid开发传递到前台
	 * 
	 * @param person
	 * @return
	 */
	public DataGrid dataGrid(Person person)throws Exception;

	/**
	 * 取出所有的人员名称和ID
	 * @return
	 */
	public List<ComboboxJson> getAllPersonsForCombobox()throws Exception;
	/**
	 * check the identity repead or not
	 * @param identityId
	 * @return
	 */
	public boolean identityIdExistOrNot(String identityId) ;
}

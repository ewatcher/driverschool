package com.tuocheng.service.demo;

import java.util.List;
import java.util.Map;

import com.tuocheng.pageModel.base.ComboboxJson;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.base.TreeNode;
import com.tuocheng.pageModel.demo.Menu;
import com.tuocheng.pageModel.demo.User;

public interface MenuServiceI {

	/**
	 * 获取菜单树
	 * 
	 * @param auth
	 * @param b
	 *            是否递归子节点
	 * @return
	 */
	public List<TreeNode> tree(Menu menu, Boolean b) throws Exception;

	/**
	 * 获取菜单树
	 * 
	 * @param auth
	 * @param b
	 *            是否递归子节点
	 * @return
	 */
	public List<TreeNode> getAuthTree(Menu menu, Boolean b, User user) throws Exception;

	/**
	 * 获得菜单treegrid
	 * 
	 * @param menu
	 * @return
	 */
	public List<Menu> treegrid(Menu menu) throws Exception;

	/**
	 * 删除菜单
	 * 
	 * @param menu
	 */
	public void delete(Menu menu) throws Exception;

	/**
	 * 添加菜单
	 * 
	 * @param menu
	 */
	public void add(Menu menu) throws Exception;

	public void edit(Menu menu) throws Exception;

	/**
	 * 根据节点标识获取节点信息
	 * 
	 * @param id
	 * @param b:是否递归子节点
	 * @return
	 * @throws Exception
	 */
	public List<TreeNode> getSingleNode(String id, Boolean b) throws Exception;

	/**
	 * 获取一级菜单
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<ComboboxJson> getMainMenus() throws Exception;

	/**
	 * 根据树型结构获取所有菜单信息
	 * 
	 * @return Map(主菜单JSON,子菜单List)
	 * @throws Exception
	 * 
	 *             2017年7月25日 Map<ComboboxJson,List<TreeNode>>
	 */
	public Map<String, List<TreeNode>> getAllMenuAndChild() throws Exception;

	public DataGrid datagrid(Menu menu);

}

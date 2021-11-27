package com.tuocheng.service.demo;

import java.util.List;

import com.tuocheng.model.demo.TweixinMenu;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.WeixinMenu;

/**
 * 微信自定义菜单实体类Service接口
 * @author 李杰
 *
 */
public interface WeixinMenuServiceI {
	/**
	 * 添加实体类信息
	 * @param weixinMenu
	 * @return
	 */
	public WeixinMenu save(WeixinMenu weixinMenu);
	/**
	 * 删除实体类信息
	 * @param ids
	 */
	public void delete(String ids);
	
	/**
	 * 更新实体类信息
	 * @param weixinMenu
	 * @return
	 */
	public WeixinMenu update(WeixinMenu weixinMenu);
	
	/**
	 * 查找出后台所有该实体类的信息，并以datagrid形式传递给前台
	 * @param weixinMenu
	 * @return
	 */
	public DataGrid dataGrid(WeixinMenu weixinMenu);
	
	/**
	 * 获取配置信息
	 * @param weixinMenu
	 * @return
	 */
	public List<TweixinMenu> find(WeixinMenu weixinMenu);
	
	/**
	 * 根据机构ID,key 获取内容
	 * @param orgId
	 * @param keyname
	 * @return
	 */
	public TweixinMenu findByKey(String orgId, String keyname);
	
	
}

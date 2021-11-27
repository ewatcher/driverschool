package com.tuocheng.service.demo;

import com.tuocheng.model.demo.TweixinMessage;
import com.tuocheng.model.demo.TweixinUser;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.WeixinUser;

/**
 * 微信用户信息实体类Service接口
 * @author 李杰
 *
 */
public interface WeixinUserServiceI {
	/**
	 * 添加实体类信息
	 * @param weixinUser
	 * @return
	 */
	public TweixinUser save(TweixinMessage tmsg, String orgId);
	/**
	 * 删除实体类信息
	 * @param ids
	 */
	public void delete(String ids);
	
	/**
	 * 更新实体类信息
	 * @param weixinUser
	 * @return
	 */
	public TweixinUser update(TweixinUser tUser);
	
	/**
	 * 查找出后台所有该实体类的信息，并以datagrid形式传递给前台
	 * @param weixinUser
	 * @return
	 */
	public DataGrid dataGrid(WeixinUser weixinUser);
	
	/**
	 * 根据OpenId查找用户信息
	 * @param openId
	 * @return
	 */
	public TweixinUser getByOpenId(String openId);
	
	/**
	 * 根据StudentId查找微信用户信息
	 * @param studentId
	 * @return
	 */
	public TweixinUser getByStudentId(String studentId);
	
	/**
	 * 根据StudentId查找微信用户信息
	 * @param studentId
	 * @return
	 */
	public TweixinUser getByTrainerId(String trainerId);
	
}

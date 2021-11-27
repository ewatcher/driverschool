package com.tuocheng.service.demo;

import java.util.List;

import com.tuocheng.model.demo.TweixinMessage;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.WeixinMessage;
import com.tuocheng.weixin.message.Message;

/**
 * 微信消息实体类Service接口
 * @author 李杰
 *
 */
public interface WeixinMessageServiceI {
	/**
	 * 添加实体类信息
	 * @param weixinMessage
	 * @return
	 */
	public TweixinMessage save(Message message, String xml);
	
	/**
	 * 保存发送的消息
	 * @param toUserName
	 * @param fromUserName
	 * @param fromNickName
	 * @param content
	 * @return
	 */
	public TweixinMessage saveSendMessage(String toUserName, String fromUserName, String fromNickName, String content);
	/**
	 * 删除实体类信息
	 * @param ids
	 */
	public void delete(String ids);
	
	/**
	 * 更新实体类信息
	 * @param weixinMessage
	 * @return
	 */
	public WeixinMessage update(WeixinMessage weixinMessage);
	
	/**
	 * 查找出后台所有该实体类的信息，并以datagrid形式传递给前台
	 * @param weixinMessage
	 * @return
	 */
	public DataGrid dataGrid(WeixinMessage weixinMessage);
	
	/**
	 * 获取信息
	 * @param weixinMessage
	 * @return
	 */
	public List<TweixinMessage> find(WeixinMessage weixinMessage);
	
	/**
	 * 根据消息ID查找消息
	 * @param msgId
	 * @return
	 */
	public TweixinMessage getByMsgId(long msgId);
	
	/**
	 * 根据openId获取用户的所有消息
	 * @param openId
	 * @return
	 */
	public List<TweixinMessage> listByOpendId(String openId);
	
	
}

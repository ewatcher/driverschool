package com.tuocheng.service.demo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.TweixinMessage;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.WeixinMessage;
import com.tuocheng.service.demo.WeixinMessageServiceI;
import com.tuocheng.util.base.Util;
import com.tuocheng.weixin.message.Message;
import com.tuocheng.weixin.message.TextMessage;

/**
 * 微信消息service实现类
 * 
 * @author 李杰
 * 
 */
@Service("weixinMessageService")
public class WeixinMessageServiceImpl implements WeixinMessageServiceI {

	private BaseDaoI<TweixinMessage> weixinMessageDao;

	// 注入DAO
	@Autowired
	public void setWeixinMessageDao(BaseDaoI<TweixinMessage> weixinMessageDao) {
		this.weixinMessageDao = weixinMessageDao;
	}

	@Override
	public TweixinMessage save(Message msg, String msgXml) {
		// 1.数据模型转换
		if(msg != null){
			TweixinMessage t = new TweixinMessage();
			t.setId(UUID.randomUUID().toString());
			t.setToUserName(msg.getToUserName());
			t.setFromUserName(msg.getFromUserName());
			t.setMsgId(msg.getMsgId());
			t.setCreateTime(Util.timeToDate(msg.getCreateTime() * 1000));
			t.setMsgType(msg.getMsgType());
			t.setMsgExt(msgXml);
			t.setOpenId(msg.getFromUserName());
			t.setReply(0); 
			t.setStatus(1);
			switch(msg.getMsgType()){
			case 1:
				TextMessage tm = (TextMessage)msg;
				t.setMsgContent(tm.getContent());
				break;
			case 2:
				t.setMsgContent("<图片>");
				break;
			case 3:
				t.setMsgContent("<声音>");
				break;
			case 4:
				t.setMsgContent("<视频>");
				break;
			case 5:
				t.setMsgContent("<短视频>");
				break;
			case 6:
				t.setMsgContent("<地址>");
				break;
			case 7:
				t.setMsgContent("<链接>");
			case 11:
				t.setMsgContent("<点击自定义菜单>");
				break;
			}
			
			weixinMessageDao.save(t);
			return t;
		}
		
		return null;
	}
	
	/**
	 * 保存发送的消息
	 * @param toUserName
	 * @param fromUserName
	 * @param fromNickName
	 * @param content
	 * @return
	 */
	@Override
	public TweixinMessage saveSendMessage(String toUserName, String fromUserName, String fromNickName, String content) {
		TweixinMessage t = new TweixinMessage();
		t.setId(UUID.randomUUID().toString());
    	t.setToUserName(toUserName);
    	t.setFromUserName(fromUserName);
    	t.setFromNickName(fromNickName);
    	t.setMsgId(0l);
    	t.setCreateTime(new Date());
    	t.setMsgType(1);
    	t.setMsgExt("");
    	t.setOpenId(toUserName);
    	t.setReply(1);
    	t.setStatus(1);
    	t.setMsgContent(content);
    	weixinMessageDao.save(t);
		return t;
	}

	@Override
	public void delete(String ids) {
		// 1.判断参数是否为空
		if (ids != null) {
			// 2.对参数的字符串进行分割
			for (String id : ids.split(",")) {
				// 排除id为0的情况
				if (!id.trim().equals("0")) {
					// 3.根据ID从数据库中查找数据
					TweixinMessage t = weixinMessageDao.get(TweixinMessage.class, id);
					// 4.调用DAO层删除数据
					weixinMessageDao.delete(t);
				}
			}
		}
	}

	@Override
	public WeixinMessage update(WeixinMessage weixinMessage) {
		// 1.数据模型转换
		TweixinMessage t = new TweixinMessage();
		BeanUtils.copyProperties(weixinMessage, t, new String[] { "id" });
		// 2.设置相关属性
		t.setId(weixinMessage.getId());
		// 3.更新数据
		weixinMessageDao.update(t);
		// 4.返回结果集
		BeanUtils.copyProperties(t, weixinMessage);
		return weixinMessage;
	}

	@Override
	public DataGrid dataGrid(WeixinMessage weixinMessage) {
		DataGrid dataGrid = new DataGrid();
		//1.从数据库中查找出所有实体类信息
		//2.查找出的信息进行数据模型转换
		//3.设置展现数据
		List<TweixinMessage> list = this.find(weixinMessage);
		dataGrid.setRows(this.changeModel(list));
		dataGrid.setTotal(this.total(weixinMessage));
		return dataGrid;
	}
	
	@Override
	public List<TweixinMessage> find(WeixinMessage weixinMessage){
		String hql = " from TweixinMessage t where 1 = 1 ";
		List<Object> params = new ArrayList<Object>();
		hql = addWhere(weixinMessage, hql, params);

		if (weixinMessage.getSort() != null && weixinMessage.getOrder() != null) {
			hql += " order by " + weixinMessage.getSort() + " " + weixinMessage.getOrder();
		}

		return weixinMessageDao.find(hql, params, weixinMessage.getPage(), weixinMessage.getRows());
	}
	
	private List<WeixinMessage> changeModel(List<TweixinMessage> ts){
		List<WeixinMessage> stcs = new ArrayList<WeixinMessage>();
		if (ts != null && ts.size() > 0) {
			for (TweixinMessage t : ts) {
				WeixinMessage weixinMessage = new WeixinMessage();
				BeanUtils.copyProperties(t, weixinMessage);
				stcs.add(weixinMessage);
			}
		}
		return stcs;
	}

	/**
	 * 添加查询约束条件where
	 * 
	 * @param student
	 * @param hql
	 * @param values
	 * @return
	 */
	private String addWhere(WeixinMessage weixinMessage, String hql, List<Object> values) {
		return hql;
	}
	
	/**
	 * 统计符合当前查询条件的总记录数
	 * 
	 * @param timerCard
	 * @return
	 */
	private Long total(WeixinMessage weixinMessage) {
		String hql = "select count(*) from TweixinMessage t where 1 = 1 ";
		List<Object> params = new ArrayList<Object>();
		hql = addWhere(weixinMessage, hql, params);
		return weixinMessageDao.count(hql, params);
	}

	@Override
	public TweixinMessage getByMsgId(long msgId) {
		if(msgId > 0){
			String hql = "from TweixinMessage t where msgId = ? ";
			List<Object> params = new ArrayList<Object>();
			params.add(msgId);
			return weixinMessageDao.get(hql, params);
		}
		return null;
	}
	
	@Override
	public List<TweixinMessage> listByOpendId(String openId){
		String hql = " from TweixinMessage t where msgType < 10 and openId = ? order by createTime desc ";
		List<Object> params = new ArrayList<Object>();
		params.add(openId);

		return weixinMessageDao.find(hql, params);
	}

}

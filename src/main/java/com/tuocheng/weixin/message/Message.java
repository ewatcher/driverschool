package com.tuocheng.weixin.message;

import org.dom4j.Document;
import org.dom4j.Element;

import com.tuocheng.util.base.Util;

public abstract class Message {
	
	private long msgId;
	private int msgType;
	private String msgTypeString;
	private String toUserName;
	private String fromUserName;
	private long createTime;
	private Document document;
	
	public Message(Document document){
		this.document = document;
		readBaseInfo();
		readExtInfo();
	}
	
	/**
	 * 消息ID
	 * @return
	 */
	public long getMsgId(){
		return msgId;
	}
	
	/**
	 * 消息类型
	 * 解析失败：0
	 * 文本消息：1
	 * 图片消息：2
	 * 语音消息：3
	 * 视频消息：4
	 * 小视频消息：5
	 * 地理位置消息：6
	 * 链接消息：7
	 * 自定义菜单事件 ：11
	 * @return
	 */
	public int getMsgType(){
		return msgType;
	}
	
	/**
	 * 开发者微信号
	 * @return
	 */
	public String getToUserName(){
		return toUserName;
	}
	
	/**
	 * 发送方账号（一个ＯpenId）
	 * @return
	 */
	public String getFromUserName(){
		return fromUserName;
	}
	
	/**
	 * 消息创建时间
	 * @return
	 */
	public long getCreateTime(){
		return createTime;
	}
	
	/**
	 * 得到消息
	 * @return
	 */
	public Document getDocument(){
		return document;
	}
	
	/**
	 * 得到消息类型文本
	 * @return
	 */
	public String getMsgTypeString(){
		return msgTypeString;
	}
	
	/**
	 * 读取基本信息，如：消息ID，消息类型等等
	 * @param document
	 */
	public void readBaseInfo(){
		if(document != null){
			try{
				Element root = document.getRootElement();
				Element tun = root.element("ToUserName");
				Element fun = root.element("FromUserName");
				Element ct = root.element("CreateTime");
				Element mt = root.element("MsgType");
				Element mi = root.element("MsgId");
				toUserName = tun.getStringValue();
				fromUserName = fun.getStringValue();
				createTime = Util.objToLong(ct.getStringValue(), 0);
				msgId = mi != null ? Util.objToLong(mi.getStringValue(), 0) : 0;
				msgTypeString = mt.getStringValue();
				if("text".equals(msgTypeString)){
					msgType = 1;
				} else if("image".equals(msgTypeString)){
					msgType = 2;
				} else if("voice".equals(msgTypeString)){
					msgType = 3;
				} else if("video".equals(msgTypeString)){
					msgType = 4;
				} else if("shortvideo".equals(msgTypeString)){
					msgType = 5;
				} else if("location".equals(msgTypeString)){
					msgType = 6;
				} else if("link".equals(msgTypeString)){
					msgType = 7;
				} else if("event".equals(msgTypeString)){
					msgType = 11;
				} 
			} catch(Exception ex){
			}
		}
		
	}
	
	/**
	 * 读取扩展信息，需要各自实现。如文本，图片等等
	 * @param document
	 */
	abstract public void readExtInfo();
	
	

}

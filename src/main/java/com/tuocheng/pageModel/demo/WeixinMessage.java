package com.tuocheng.pageModel.demo;

import java.util.Date;

/**
 * 微信自定义菜单实体类(action专用)
 * 
 * @author 李杰
 * 
 */
public class WeixinMessage extends PageModel {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 365741094971384357L;

	private String id;
	private String toUserName; // 开发者微信号
	private String fromUserName; // 发送方帐号（一个OpenID）
	private String fromNickName; // 发送方昵称
	private String openId; // 客户OpenID，同 fromUserName
	private Date createTime; // 创建时间
	private Integer msgType; // 消息类型
	private Long msgId; // 消息ID
	private String msgContent; // 内容
	private String msgExt; // 消息的原 xml
	private Integer reply; // 是否是回复客户的消息 0:不是 1：是回复用户的消息
	private Integer status; // 状态
	
	private String ids;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getFromNickName() {
		return fromNickName;
	}

	public void setFromNickName(String fromNickName) {
		this.fromNickName = fromNickName;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getMsgType() {
		return msgType;
	}

	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public String getMsgExt() {
		return msgExt;
	}

	public void setMsgExt(String msgExt) {
		this.msgExt = msgExt;
	}

	public Integer getReply() {
		return reply;
	}

	public void setReply(Integer reply) {
		this.reply = reply;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}

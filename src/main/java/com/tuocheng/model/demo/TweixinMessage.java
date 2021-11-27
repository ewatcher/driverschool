package com.tuocheng.model.demo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 微信消息实体模型(DAO专用)
 * 
 * @author 李杰
 * 
 */
@Entity
@Table(name = "tb_weixin_message", schema = "")
public class TweixinMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9090317034590501716L;
	// ===========base properties==============
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

	// ==============relation properties==============
	public TweixinMessage() {
	}

	@Id
	@Column(name = "id", nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "toUserName", length = 100)
	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	@Column(name = "fromUserName", length = 100)
	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	@Column(name = "fromNickName", length = 100)
	public String getFromNickName() {
		return fromNickName;
	}

	public void setFromNickName(String fromNickName) {
		this.fromNickName = fromNickName;
	}

	@Column(name = "openId", length = 100)
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createTime", length = 7)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "msgType", length = 11)
	public Integer getMsgType() {
		return msgType;
	}

	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}

	@Column(name = "msgId", length = 20)
	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	@Column(name = "msgContent", length = 200)
	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	@Column(name = "msgExt", length = 10000)
	public String getMsgExt() {
		return msgExt;
	}

	public void setMsgExt(String msgExt) {
		this.msgExt = msgExt;
	}

	@Column(name = "reply", length = 11)
	public Integer getReply() {
		return reply;
	}

	public void setReply(Integer reply) {
		this.reply = reply;
	}

	@Column(name = "status", length = 11)
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TweixinMessage other = (TweixinMessage) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TweixinMessage [id=" + id + ", toUserName=" + toUserName
				+ ", fromUserName=" + fromUserName + ", fromNickName="
				+ fromNickName + ", openId=" + openId + ", createTime="
				+ createTime + ", msgType=" + msgType + ", msgId=" + msgId
				+ ", msgContent=" + msgContent + ", msgExt=" + msgExt
				+ ", reply=" + reply + ", status=" + status + "]";
	}

}

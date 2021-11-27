package com.tuocheng.model.demo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 微信自定义菜单实体模型(DAO专用)
 * 
 * @author 李杰
 * 
 */
@Entity
@Table(name = "tb_weixin_menu", schema = "")
public class TweixinMenu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9090317034590501716L;
	// ===========base properties==============
	private Integer id;
	private String keyname; // 用户名
	private String nickname; // 名称
	private String type; // 类型（click：点击 view：打开URL menu:有下级菜单）
	private Integer reorder; // 排序
	private String content; // 内容
	private Integer cid; // 上级ID
	private Integer status; // 状态

	private String organizationId; // 机构ID,分校ID

	// ==============relation properties==============
	public TweixinMenu() {
	}

	public TweixinMenu(String nickname, String type, Integer reorder,
			Integer cid, Integer status, String organizationId, String keyname,
			String content) {
		super();
		this.nickname = nickname;
		this.type = type;
		this.reorder = reorder;
		this.cid = cid;
		this.status = status;
		this.organizationId = organizationId;
		this.keyname = keyname;
		this.content = content;
	}

	public TweixinMenu(Integer id, String keyname, String nickname,
			String type, Integer reorder, Integer cid, Integer status,
			String organizationId, String content) {
		super();
		this.id = id;
		this.keyname = keyname;
		this.nickname = nickname;
		this.type = type;
		this.reorder = reorder;
		this.cid = cid;
		this.status = status;
		this.organizationId = organizationId;
		this.content = content;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, length = 11)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "keyname", length = 100)
	public String getKeyname() {
		return keyname;
	}

	public void setKeyname(String keyname) {
		this.keyname = keyname;
	}

	@Column(name = "nickname", length = 100)
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(name = "type", length = 100)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "reorder", length = 11)
	public Integer getReorder() {
		return reorder;
	}

	public void setReorder(Integer reorder) {
		this.reorder = reorder;
	}

	@Column(name = "content", length = 40000)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "cid", length = 11)
	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	@Column(name = "status", length = 11)
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "organizationId", length = 36)
	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
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
		TweixinMenu other = (TweixinMenu) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TweixinMenu [id=" + id + ", keyname=" + keyname + ", nickname="
				+ nickname + ", type=" + type + ", reorder=" + reorder
				+ ", content=" + content + ", cid=" + cid + ", status="
				+ status + ", organizationId=" + organizationId + "]";
	}

}

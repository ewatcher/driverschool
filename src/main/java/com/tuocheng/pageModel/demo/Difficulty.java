package com.tuocheng.pageModel.demo;

import java.util.Date;


/**
 * 微信难点反馈实体类(action专用)
 * 
 * @author 李杰
 * 
 */
public class Difficulty extends PageModel {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 365741094971384357L;
	
	private String id;
	private String openId; // 用户微信ID
	private String nickname; // 微信昵称
	private String difficulty; // 难点
	private Date createTime; // 创建时间时间
	private String comments; // 备注
	private Integer status; // 状态。 1：已联系
	
	private String organizationId; // 机构ID,分校ID
	private String organizationName; // 机构名称
	
	private String ids;
	
	public Difficulty(){
	}
	
	public Difficulty(String id, String openId, String nickname,
			String difficulty, Date createTime, String comments, Integer status) {
		super();
		this.id = id;
		this.openId = openId;
		this.nickname = nickname;
		this.difficulty = difficulty;
		this.createTime = createTime;
		this.comments = comments;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}

package com.tuocheng.pageModel.demo;

import java.util.Date;

public class Assess extends PageModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String openId; // 用户微信ID
	private String nickname; // 微信昵称
	private String trainerId; // 教练ID
	private String trainerName; // 教练名称
	private Integer stars; // 星级（1，很好； 2：一般； 3：差； 4：极差）
	private String evaluate; // 评价
	private Date createTime; // 创建时间时间
	private String comments; // 备注
	private Integer status; // 状态。

	private String organizationId; // 机构ID,分校ID
	private String organizationName; // 机构名称

	private String ids;
	private Date createTimeStart; // 创建时间时间
	private Date createTimeEnd; // 创建时间时间

	private Integer serviceMark;
	private Integer efficientMark;
	private Integer dutyMark;
	private Integer groomingMark;
	private Integer totalMark;

	public Integer getTotalMark() {
		return totalMark;
	}

	public void setTotalMark(Integer totalMark) {
		this.totalMark = totalMark;
	}

	public Integer getServiceMark() {
		return serviceMark;
	}

	public void setServiceMark(Integer serviceMark) {
		this.serviceMark = serviceMark;
	}

	public Integer getEfficientMark() {
		return efficientMark;
	}

	public void setEfficientMark(Integer efficientMark) {
		this.efficientMark = efficientMark;
	}

	public Integer getDutyMark() {
		return dutyMark;
	}

	public void setDutyMark(Integer dutyMark) {
		this.dutyMark = dutyMark;
	}

	public Integer getGroomingMark() {
		return groomingMark;
	}

	public void setGroomingMark(Integer groomingMark) {
		this.groomingMark = groomingMark;
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

	public String getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(String trainerId) {
		this.trainerId = trainerId;
	}

	public String getTrainerName() {
		return trainerName;
	}

	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}

	public Integer getStars() {
		return stars;
	}

	public void setStars(Integer stars) {
		this.stars = stars;
	}

	public String getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
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

	public Date getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

}

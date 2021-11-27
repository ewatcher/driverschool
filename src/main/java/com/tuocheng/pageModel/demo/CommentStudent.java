package com.tuocheng.pageModel.demo;

import java.util.Date;

import com.alibaba.fastjson.JSON;


/**
 * 学员点评实体类(action专用)
 * 
 * @author 李杰
 * 
 */
public class CommentStudent extends PageModel {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 365741094971384357L;
	
	private String id;
	private String studentId; // 学员ID
	private String studentName; // 学员名称
	private String trainId; // 教练ID
	private String trainName; // 教练姓名
	private Date commentTime; // 点评的时间
	private String comments; // 点评
	private Integer isRead; // 已读？ 0,未读， 1：已读
	private Integer status; // 状态。 
	
	private String ids;
	private Date commentTimeStart;// 点评时间－－－范围查询
	private Date commentTimeEnd;// 点评时间－－－范围查询
	private String organizationName; // 学校ID
	private String studentIdentityId; // 身份证ID
	private String studentNo; // 学员编号
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getTrainId() {
		return trainId;
	}
	public void setTrainId(String trainId) {
		this.trainId = trainId;
	}
	public String getTrainName() {
		return trainName;
	}
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}
	public Date getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Integer getIsRead() {
		return isRead;
	}
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
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
	public Date getCommentTimeStart() {
		return commentTimeStart;
	}
	public void setCommentTimeStart(Date commentTimeStart) {
		this.commentTimeStart = commentTimeStart;
	}
	public Date getCommentTimeEnd() {
		return commentTimeEnd;
	}
	public void setCommentTimeEnd(Date commentTimeEnd) {
		this.commentTimeEnd = commentTimeEnd;
	}
	
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getStudentIdentityId() {
		return studentIdentityId;
	}
	public void setStudentIdentityId(String studentIdentityId) {
		this.studentIdentityId = studentIdentityId;
	}
	public String getStudentNo() {
		return studentNo;
	}
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	@Override
	public String toString(){
		return JSON.toJSONString(this);
	}
}

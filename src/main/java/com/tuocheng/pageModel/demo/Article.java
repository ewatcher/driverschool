package com.tuocheng.pageModel.demo;

import java.util.Date;

import com.alibaba.fastjson.JSON;

public class Article extends PageModel {

	/**
	 * 门户网站文章
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String author; // 作者
	private String title; // 标题
	private Integer typeNo; // 类型编号
	private String content; // 内容
	private Integer sortNo; // 排序编号（倒序，数值越大越靠前）
	private Date createTime; // 创建时间时间
	private Date updateTime; // 最后更改时间
	private Integer status; // 状态。

	private String ids;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getTypeNo() {
		return typeNo;
	}

	public void setTypeNo(Integer typeNo) {
		this.typeNo = typeNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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

	@Override
	public String toString() {
		return "Article [id=" + id + ", author=" + author + ", title=" + title
				+ ", typeNo=" + typeNo + ", content=" + content + ", sortNo="
				+ sortNo + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", status=" + status + ", ids=" + ids + "]";
	}

}

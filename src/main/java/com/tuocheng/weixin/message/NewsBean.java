package com.tuocheng.weixin.message;

public class NewsBean {
	private String title;	// 图文消息标题
	private String description;	// 图文消息描述
	private String picurl;	// 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
	private String url;	// 点击图文消息跳转链接
	
	public NewsBean(String title, String description, String picurl, String url){
		this.title = title;
		this.description = description;
		this.picurl = picurl;
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}

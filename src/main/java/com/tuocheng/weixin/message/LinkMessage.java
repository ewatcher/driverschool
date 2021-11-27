package com.tuocheng.weixin.message;

import org.dom4j.Document;
import org.dom4j.Element;

public class LinkMessage extends Message {
	
	private String title; //	消息标题
	private String description; //	消息描述
	private String url; //	消息链接
	
	public LinkMessage(Document document){
		super(document);
	}

	@Override
	public void readExtInfo() {
		Document document = getDocument();
		if(document != null){
			try{
				Element root = document.getRootElement();
				Element t = root.element("Title");
				Element d = root.element("Description");
				Element u = root.element("Url");
				title = t.getStringValue();
				description = d.getStringValue();
				url = u.getStringValue();
			} catch(Exception ex){
			}
		}
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getUrl() {
		return url;
	}

}

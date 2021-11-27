package com.tuocheng.weixin.message;

import org.dom4j.Document;
import org.dom4j.Element;

public class TextMessage extends Message {
	
	private String content; // 文本消息内容
	
	public TextMessage(Document document){
		super(document);
	}

	@Override
	public void readExtInfo() {
		Document document = getDocument();
		if(document != null){
			try{
				Element root = document.getRootElement();
				Element c = root.element("Content");
				content = c.getStringValue();
			} catch(Exception ex){
			}
		}
	}
	
	public String getContent(){
		return content;
	}
	

}

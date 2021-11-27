package com.tuocheng.weixin.message;

import org.dom4j.Document;
import org.dom4j.Element;

public class EventMessage extends Message {
	
	private String event; //	事件类型，CLICK, 
	private String eventKey; //	事件KEY值，与自定义菜单接口中KEY值对应
	
	public EventMessage(Document document){
		super(document);
	}

	@Override
	public void readExtInfo() {
		Document document = getDocument();
		if(document != null){
			try{
				Element root = document.getRootElement();
				Element e = root.element("Event");
				Element ek = root.element("EventKey");
				event = e.getStringValue();
				eventKey = ek.getStringValue();
			} catch(Exception ex){
			}
		}
	}

	public String getEvent() {
		return event;
	}

	public String getEventKey() {
		return eventKey;
	}

}

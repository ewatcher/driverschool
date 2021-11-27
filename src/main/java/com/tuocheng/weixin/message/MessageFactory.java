package com.tuocheng.weixin.message;

import org.dom4j.Document;
import org.dom4j.Element;

public class MessageFactory {
	
	private MessageFactory(){
	}
	
	public static Message getMessage(Document document){
		Message message = null;
		try{
			if(document != null){
				Element root = document.getRootElement();
				Element mt = root.element("MsgType");
				String msgType = mt.getStringValue();
				
				if("text".equals(msgType)){
					message = new TextMessage(document);
				} else if("image".equals(msgType)){
					message = new ImageMessage(document);
				} else if("voice".equals(msgType)){
					message = new VoiceMessage(document);
				} else if("video".equals(msgType)){
					message = new VideoMessage(document);
				} else if("shortvideo".equals(msgType)){
					message = new ShortVideoMessage(document);
				} else if("location".equals(msgType)){
					message = new LocationMessage(document);
				} else if("link".equals(msgType)){
					message = new LinkMessage(document);
				} else if("event".equals(msgType)){
					message = new EventMessage(document);
				}
			}
		} catch(Exception ex){
		}
		
		return message;
	}

}

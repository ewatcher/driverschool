package com.tuocheng.weixin.message;

import org.dom4j.Document;
import org.dom4j.Element;

import com.tuocheng.util.base.Util;

public class ShortVideoMessage extends Message {
	
	private long mediaId; // 媒体id，可以调用多媒体文件下载接口拉取数据。
	private long thumbMediaId; // 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
	
	public ShortVideoMessage(Document document){
		super(document);
	}

	@Override
	public void readExtInfo() {
		Document document = getDocument();
		if(document != null){
			try{
				Element root = document.getRootElement();
				Element m = root.element("MediaId");
				Element t = root.element("ThumbMediaId");
				mediaId = Util.objToLong(m.getStringValue(), 0);
				thumbMediaId = Util.objToLong(t.getStringValue(), 0);
			} catch(Exception ex){
			}
		}
	}
	
	public long getMediaId(){
		return mediaId;
	}
	
	public long getThumbMediaId(){
		return thumbMediaId;
	}

}

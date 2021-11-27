package com.tuocheng.weixin.message;

import org.dom4j.Document;
import org.dom4j.Element;

import com.tuocheng.util.base.Util;

public class ImageMessage extends Message {
	
	private long mediaId; // 媒体id，可以调用多媒体文件下载接口拉取数据。
	private String picUrl; // 图片链接（由系统生成）
	
	
	public ImageMessage(Document document){
		super(document);
	}

	@Override
	public void readExtInfo() {
		Document document = getDocument();
		if(document != null){
			try{
				Element root = document.getRootElement();
				Element p = root.element("PicUrl");
				Element m = root.element("MediaId");
				picUrl = p.getStringValue();
				mediaId = Util.objToLong(m.getStringValue(), 0);
			} catch(Exception ex){
			}
		}
	}
	
	public String getPicUrl(){
		return picUrl;
	}
	
	public long getMediaId(){
		return mediaId;
	}
	

}

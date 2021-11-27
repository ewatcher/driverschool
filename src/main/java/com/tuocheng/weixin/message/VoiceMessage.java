package com.tuocheng.weixin.message;

import org.dom4j.Document;
import org.dom4j.Element;

import com.tuocheng.util.base.Util;

public class VoiceMessage extends Message {
	
	private long mediaId; // 媒体id，可以调用多媒体文件下载接口拉取数据。
	private String format; // 语音格式，如amr，speex等
	private String recognition; // 语音识别结果，UTF8编码
	
	public VoiceMessage(Document document){
		super(document);
	}

	@Override
	public void readExtInfo() {
		Document document = getDocument();
		if(document != null){
			try{
				Element root = document.getRootElement();
				Element f = root.element("Format");
				Element m = root.element("MediaId");
				Element r = root.element("Recognition");
				format = f.getStringValue();
				recognition = r.getStringValue();
				mediaId = Util.objToLong(m.getStringValue(), 0);
			} catch(Exception ex){
			}
		}
	}
	
	public long getMediaId(){
		return mediaId;
	}
	
	public String getFormat(){
		return format;
	}
	
	public String getRecognition(){
		return recognition;
	}

}

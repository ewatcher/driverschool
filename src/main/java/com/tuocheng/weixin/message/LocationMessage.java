package com.tuocheng.weixin.message;

import org.dom4j.Document;
import org.dom4j.Element;

import com.tuocheng.util.base.Util;

public class LocationMessage extends Message {
	
	private float location_X;	// 地理位置维度
	private float location_Y;	// 地理位置经度
	private int scale;	// 地图缩放大小
	private String label;	// 地理位置信息
	
	public LocationMessage(Document document){
		super(document);
	}

	@Override
	public void readExtInfo() {
		Document document = getDocument();
		if(document != null){
			try{
				Element root = document.getRootElement();
				Element lx = root.element("Location_X");
				Element ly = root.element("Location_Y");
				Element s = root.element("Scale");
				Element l = root.element("Label");
				location_X = Util.objToFloat(lx.getStringValue(), 0.0f);
				location_Y = Util.objToFloat(ly.getStringValue(), 0.0f);
				scale = Util.objToInt(s.getStringValue(), 0);
				label = l.getStringValue();
			} catch(Exception ex){
			}
		}
	}

	public float getLocation_X() {
		return location_X;
	}

	public float getLocation_Y() {
		return location_Y;
	}

	public int getScale() {
		return scale;
	}

	public String getLabel() {
		return label;
	}
	

}

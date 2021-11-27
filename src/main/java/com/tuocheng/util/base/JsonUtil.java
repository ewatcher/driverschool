package com.tuocheng.util.base;

import com.alibaba.fastjson.JSONObject;

public class JsonUtil {
	
	public static String getString(JSONObject jsonObject, String key){
		if(jsonObject != null && jsonObject.containsKey(key)){
			try{
				return jsonObject.getString(key);
			} catch(Exception ex){
			}
		}
		return "";
	}
	
	public static int getInt(JSONObject jsonObject, String key){
		if(jsonObject != null && jsonObject.containsKey(key)){
			try{
				return jsonObject.getInteger(key);
			} catch(Exception ex){
			}
		}
		return 0;
	}
	
	public static long getLong(JSONObject jsonObject, String key){
		if(jsonObject != null && jsonObject.containsKey(key)){
			try{
				return jsonObject.getLong(key);
			} catch(Exception ex){
			}
		}
		return 0l;
	}

}

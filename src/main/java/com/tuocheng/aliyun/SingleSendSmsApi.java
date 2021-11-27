package com.tuocheng.aliyun;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;

/**
 * 阿里云短信接口
 * @author huangqinting
 *
 */
public class SingleSendSmsApi {
	
	// 阿里云短信接口URI
	private static final String API_HOST_SMS = "http://sms.market.alicloudapi.com";
	
	// 阿里云短信接口URL路径
	private static final String API_PATH_SMS = "/singleSendSms";
	
	
	
	/**
	 * 发送短信
	 * @param RecNum 目标手机号,多条记录可以英文逗号分隔
	 * @param SignName(可空) 签名
	 * @param TemplateCode 模板ID
	 * @param params 模板对应的参数
	 */
	public String sendSMS(String RecNum, String SignName, String TemplateCode, HashMap<String, String> params){
	    String method = "GET";
	    Map<String, String> headers = new HashMap<String, String>();
	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
	    headers.put("Authorization", "APPCODE " + AliyunParams.AppCode);
	    Map<String, String> querys = new HashMap<String, String>();
	    querys.put("ParamString", getParamsString(params));
	    querys.put("RecNum", RecNum);
	    querys.put("SignName", SignName);
	    querys.put("TemplateCode", TemplateCode);

	    try {
	    	HttpResponse response = HttpUtils.doGet(API_HOST_SMS, API_PATH_SMS, method, headers, querys);
	    	System.out.println(response.toString());
	    	return EntityUtils.toString(response.getEntity());
	    } catch (Exception e) {
	    	HashMap<String, Object> err = new HashMap<String, Object>();
	    	err.put("success", false);
	    	err.put("message", e.toString());
	    	return JSON.toJSONString(err);
	    }
	}
	
	/**
	 * 把参数编码成 %7B%22no%22%3A%22123456%22%7D 样式
	 * @param params
	 * @return
	 */
	private String getParamsString(HashMap<String, String> params){
		if(params != null && params.size() > 0){
			return JSON.toJSONString(params);
		} else {
			return "";
		}
	}

}

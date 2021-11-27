package com.tuocheng.weixin.menu;

import java.nio.charset.Charset;

import javax.servlet.ServletContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.tuocheng.util.base.Util;
import com.tuocheng.weixin.base.Weixin;

public class WeixinMenuService {
	
	private final String CREATE_MENU = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";
	
	private Weixin weixin = null;
	
	public WeixinMenuService(Weixin weixin){
		this.weixin = weixin;
	}
	
	/**
	 * 创建自定义菜单
	 * @param request
	 * @param json
	 * @return
	 */
	public int createMenu(ServletContext application, String json){
		int code= -1;
		try {
			String accessToken = weixin.getAccessToken(application);
			System.out.println("accessToken:" + accessToken);
			if(Util.isNotNull(accessToken)){
				CloseableHttpClient httpclient = HttpClients.createDefault();
				HttpPost httpPost = new HttpPost(String.format(CREATE_MENU, accessToken));
				httpPost.addHeader("Content-type","application/json; charset=utf-8");  
				httpPost.setHeader("Accept", "application/json"); 
				httpPost.setEntity(new StringEntity(json, Charset.forName("UTF-8")));  
				CloseableHttpResponse response = httpclient.execute(httpPost);

			    try {
			        HttpEntity entity = response.getEntity();
			        String body = EntityUtils.toString(entity); 
			        EntityUtils.consume(entity); //  关闭流？
			        if(Util.isNotNull(body)){
			        	JSONObject jsonObject = JSON.parseObject(body);
						if(jsonObject != null && jsonObject.containsKey("errcode")){
							int errcode = jsonObject.getInteger("errcode");
							if(errcode == 0){
								return 1;
							} else {
								return -Math.abs(errcode);
							}
						}
					}
			    } finally {
			    	response.close();
			    }
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return code;
	}

}

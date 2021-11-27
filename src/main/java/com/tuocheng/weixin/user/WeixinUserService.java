package com.tuocheng.weixin.user;

import java.nio.charset.Charset;

import javax.servlet.ServletContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.tuocheng.util.base.JsonUtil;
import com.tuocheng.util.base.Util;
import com.tuocheng.weixin.base.Weixin;

public class WeixinUserService {
	
	private final String GET_USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";
	
	private Weixin weixin = null;
	
	public WeixinUserService(Weixin weixin){
		this.weixin = weixin;
	}
	
	/**
	 * 获取微信用户信息
	 * @param application
	 * @param openId
	 * @return
	 */
	public WeixinUserBean getUserInfo(ServletContext application, String openId){
		String accessToken = weixin.getAccessToken(application);
		if(accessToken != null){
			try{
				CloseableHttpClient httpclient = HttpClients.createDefault();
			    HttpGet httpGet = new HttpGet(String.format(GET_USER_INFO, accessToken, openId));
			    CloseableHttpResponse response = httpclient.execute(httpGet);
			    try {
			        HttpEntity entity = response.getEntity();
			        String body = EntityUtils.toString(entity, Charset.forName("UTF-8")); 
			        EntityUtils.consume(entity); //  关闭流？
			        if(Util.isNotNull(body)){
			        	JSONObject jsonObject = JSON.parseObject(body);
						if(jsonObject != null && jsonObject.containsKey("subscribe")){
							WeixinUserBean wu = new WeixinUserBean();
							wu.setSubscribe(JsonUtil.getInt(jsonObject, "subscribe"));
							wu.setOpenid(JsonUtil.getString(jsonObject, "openid"));
							wu.setNickname(JsonUtil.getString(jsonObject, "nickname"));
							wu.setSex(JsonUtil.getInt(jsonObject, "sex"));
							wu.setLanguage(JsonUtil.getString(jsonObject, "language"));
							wu.setCity(JsonUtil.getString(jsonObject, "city"));
							wu.setProvince(JsonUtil.getString(jsonObject, "province"));
							wu.setCountry(JsonUtil.getString(jsonObject, "country"));
							wu.setHeadimgurl(JsonUtil.getString(jsonObject, "headimgurl"));
							wu.setSubscribeTime(JsonUtil.getLong(jsonObject, "subscribe_time"));
							wu.setUnionid(JsonUtil.getString(jsonObject, "unionid"));
							wu.setRemark(JsonUtil.getString(jsonObject, "remark"));
							wu.setGroupid(JsonUtil.getInt(jsonObject, "groupid"));
							return wu;
						}
					}
			    } catch(Exception ex){ 
			    	ex.printStackTrace();
				}finally {
			        response.close();
			    }
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
		return null;
	}
	
	public static void main(){
		
	}

}

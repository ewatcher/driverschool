package com.tuocheng.weixin.message;

import java.nio.charset.Charset;
import java.util.List;

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
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.tuocheng.util.base.Util;
import com.tuocheng.weixin.base.Weixin;

public class WeixinMessageService {
	
	private String CUSTOM_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=%s";
	
	private Weixin weixin = null;
	
	public WeixinMessageService(Weixin weixin){
		this.weixin = weixin;
	}
	
	/**
	 * 得到回复文本消息的XML	
	 * @param toUserName 接收方帐号（收到的OpenID）
	 * @param fromUserName 开发者微信号
	 * @param content 回复的文本内容
	 * @return
	 */
	public static String getTextMessageXML(Message message, String content){
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("xml");
		addBaseInfo(root, message); // 添加基本信息
		Element m = root.addElement("MsgType");
		m.addCDATA("text");
		Element c = root.addElement("Content");
		c.addCDATA(content);
		return document.asXML();
	}
	
	/**
	 * 得到回复图文消息的XML
	 * @param message
	 * @param list
	 * @return
	 */
	public static String getNewsMessageXML(Message message, List<NewsBean> list){
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("xml");
		addBaseInfo(root, message); // 添加基本信息
		Element m = root.addElement("MsgType");
		m.addCDATA("news");
		
		if(list != null){
			Element a = root.addElement("ArticleCount");
			a.setText(Integer.toString(Math.min(list.size(), 10)));
			Element Articles = root.addElement("Articles");
			for(int i = 0; i < Math.min(list.size(), 10); i++){
				NewsBean newsBean = list.get(i);
				Element item = Articles.addElement("item");
				if(Util.isNotNull(newsBean.getTitle())){
					item.addElement("Title").addCDATA(newsBean.getTitle());
				}
				/**
				if(Util.isNotNull(newsBean.getDescription())){
					item.addElement("Description").addCDATA(newsBean.getDescription());
				}
				**/
				if(Util.isNotNull(newsBean.getPicurl())){
					item.addElement("PicUrl").addCDATA(newsBean.getPicurl());
				}
				if(Util.isNotNull(newsBean.getUrl())){
					item.addElement("Url").addCDATA(newsBean.getUrl());
				}
			}
		}
		
		return document.asXML();
	}
	
	/**
	 * 添加基本信息
	 * @param root xml根节点
	 * @param toUserName 接收方帐号（收到的OpenID）
	 * @param fromUserName 开发者微信号
	 */
	private static void addBaseInfo(Element root, Message message){
		Element t = root.addElement("ToUserName");
		t.addCDATA(message.getFromUserName());
		Element f = root.addElement("FromUserName");
		f.addCDATA(message.getToUserName());
		Element c = root.addElement("CreateTime");
		c.setText(Long.toString(System.currentTimeMillis() / 1000));
	}
	
	/**
	 * 发送客服消息
	 * @param application
	 * @param openId
	 * @param text
	 * @return
	 */
	public int postCustomServiceMessage(ServletContext application, String openId, String text){
		int code= -1;
		try {
			String accessToken = weixin.getAccessToken(application);
			System.out.println("accessToken:" + accessToken);
			if(Util.isNotNull(accessToken)){
				
				JSONObject json = new JSONObject();
				json.put("touser", openId);
				json.put("msgtype", "text");
				JSONObject textJson = new JSONObject();
				textJson.put("content", text);
				json.put("text", textJson);
				
				System.out.println(json.toString());
				
				CloseableHttpClient httpclient = HttpClients.createDefault();
				HttpPost httpPost = new HttpPost(String.format(CUSTOM_SEND_URL, accessToken));
				httpPost.addHeader("Content-type","application/json; charset=utf-8");  
				httpPost.setHeader("Accept", "application/json"); 
				httpPost.setEntity(new StringEntity(json.toString(), Charset.forName("UTF-8")));  
				CloseableHttpResponse response = httpclient.execute(httpPost);

			    try {
			        HttpEntity entity = response.getEntity();
			        String body = EntityUtils.toString(entity); 
			        EntityUtils.consume(entity); //  关闭流？
			        if(Util.isNotNull(body)){
			        	System.out.println(body);
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

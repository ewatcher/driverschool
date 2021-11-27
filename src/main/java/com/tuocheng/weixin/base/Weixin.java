package com.tuocheng.weixin.base;

import java.util.Arrays;

import javax.servlet.ServletContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.*;

import com.tuocheng.util.base.Util;
import com.tuocheng.weixin.message.Message;
import com.tuocheng.weixin.message.MessageFactory;

public class Weixin {

	// 微信获取 access_token 地址
	private final String GET_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
	// 微信获取 jsapi_ticket 地址
	private final String GET_JSAPI_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";
	// 获取微信网页的access_token
	private final String GET_WEB_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

	private String token = "";
	private String appId = "";
	private String secret = "";
	private String encodingAesKey = "";

	public Weixin(String token, String appId, String secret,
			String encodingAesKey) {
		super();
		this.token = token;
		this.appId = appId;
		this.secret = secret;
		this.encodingAesKey = encodingAesKey;
	}

	/**
	 * 验证消息的确来自微信服务器
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public String checkSignature(String timestamp, String nonce) {
		String[] arr = new String[] { token, timestamp, nonce };
		Arrays.sort(arr);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i]);
		}
		return Util.SHA1(sb.toString());
	}

	/**
	 * 获取access_token
	 * 
	 * @param request
	 * @return
	 */
	public String getAccessToken(ServletContext application) {
		String accessToken = application.getAttribute(appId + "access_token") != null ? (String) application
				.getAttribute(appId + "access_token") : null;
		long t = application.getAttribute(appId + "accessTokenTime") != null ? (Long) application
				.getAttribute(appId + "accessTokenTime") : 0;
		long now = System.currentTimeMillis() / 1000;
		if (Util.isNotNull(accessToken) && t > now) {
			return accessToken;
		} else {
			try {
				CloseableHttpClient httpclient = HttpClients.createDefault();
				HttpGet httpGet = new HttpGet(String.format(GET_TOKEN, appId,
						secret));
				CloseableHttpResponse response = httpclient.execute(httpGet);
				try {
					HttpEntity entity = response.getEntity();
					String body = EntityUtils.toString(entity);
					EntityUtils.consume(entity); // 关闭流？
					if (Util.isNotNull(body)) {
						JSONObject jsonObject = JSON.parseObject(body);
						if (jsonObject != null
								&& jsonObject.containsKey("access_token")) {
							accessToken = jsonObject.getString("access_token");
							long expiresIn = jsonObject.getLong("expires_in");
							application.setAttribute(appId + "access_token",
									accessToken);
							application.setAttribute(appId + "accessTokenTime",
									(long) (System.currentTimeMillis() / 1000)
											+ expiresIn);
							return accessToken;
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					response.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}

		return null;
	}

	/**
	 * 获取jsapi_ticket
	 * 
	 * @param request
	 * @return
	 */
	public String getJsapiTicket(ServletContext application) {

		String jsapiTicket = application.getAttribute(appId + "jsapi_ticket") != null ? (String) application
				.getAttribute(appId + "jsapi_ticket") : null;
		long t = application.getAttribute(appId + "jsapiTicketTime") != null ? (Long) application
				.getAttribute(appId + "jsapiTicketTime") : 0;
		long now = System.currentTimeMillis() / 1000;
		if (Util.isNotNull(jsapiTicket) && t > now) {
			return jsapiTicket;
		} else {
			String accessToken = getAccessToken(application); // 获取 accessToken
			try {
				CloseableHttpClient httpclient = HttpClients.createDefault();
				HttpGet httpGet = new HttpGet(String.format(GET_JSAPI_TICKET,
						accessToken));
				CloseableHttpResponse response = httpclient.execute(httpGet);
				try {
					HttpEntity entity = response.getEntity();
					String body = EntityUtils.toString(entity);
					EntityUtils.consume(entity); // 关闭流？
					if (Util.isNotNull(body)) {
						JSONObject jsonObject = JSON.parseObject(body);
						if (jsonObject != null
								&& jsonObject.containsKey("ticket")) {
							jsapiTicket = jsonObject.getString("ticket");
							long expiresIn = jsonObject.getLong("expires_in");
							application.setAttribute(appId + "jsapi_ticket",
									jsapiTicket);
							application.setAttribute(appId + "jsapiTicketTime",
									(long) (System.currentTimeMillis() / 1000)
											+ expiresIn);
							return jsapiTicket;
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					response.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}

		return null;
	}

	/**
	 * 根据微信web页面授权的code获取配置信息（access_token）
	 * 
	 * @param code
	 * @return
	 */
	public String getConfigByWebCode(String code) {
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(String.format(GET_WEB_TOKEN, appId,
					secret, code));
			CloseableHttpResponse response = httpclient.execute(httpGet);
			try {
				HttpEntity entity = response.getEntity();
				String body = EntityUtils.toString(entity);
				EntityUtils.consume(entity); // 关闭流？
				if (Util.isNotNull(body)) {
					System.out.println(body);
					return body;
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				response.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public Message getMessageByXML(String xml) {
		try {
			Document document = DocumentHelper.parseText(xml);
			return MessageFactory.getMessage(document);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getEncodingAesKey() {
		return encodingAesKey;
	}

	public void setEncodingAesKey(String encodingAesKey) {
		this.encodingAesKey = encodingAesKey;
	}

}

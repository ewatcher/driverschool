package com.tuocheng.weixin.web;

public class WeixinJsBean {

	/**
	 * appId: 'wxf8b4f85f3a794e77', timestamp: 1466733007, nonceStr:
	 * 'Kakidg1r3QMl7tVy', signature:
	 * 'ee709f49ac866a14f0868f2de8bc2583e94a8ac0',
	 **/

	private String appId; // 应用ID
	private long timestamp; // 时间戳，单位秒
	private String nonceStr; // 随机字符串
	private String signature; // 签名
	private String url; // 完整URL地址，不包括 # 号及后面的字符
	private String jsapiTicket; // 微信JS接口的临时票据

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getJsapiTicket() {
		return jsapiTicket;
	}

	public void setJsapiTicket(String jsapiTicket) {
		this.jsapiTicket = jsapiTicket;
	}

	public String toCheckString() {
		StringBuilder sb = new StringBuilder();
		sb.append("jsapi_ticket=").append(jsapiTicket).append("&");
		sb.append("noncestr=").append(nonceStr).append("&");
		sb.append("timestamp=").append(timestamp).append("&");
		sb.append("url=").append(url);
		return sb.toString();
	}

}

package com.tuocheng.weixin.web;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.tuocheng.util.base.Util;
import com.tuocheng.weixin.base.Weixin;

/**
 * 微信JS服务类
 * @author laobu
 *
 */
public class WeixinJsService {
	
	public Weixin weixin = null;
	
	public WeixinJsService(Weixin weixin){
		this.weixin = weixin;
	}
	
	/**
	 * 得到JS页面的配置参数
	 * @param context
	 * @return
	 */
	public WeixinJsBean getJsParams(ServletContext application, HttpServletRequest request){
		String url = request.getRequestURL().toString();
		String q = request.getQueryString();
		if(Util.isNotNull(q)){
			url = url + "?" + q;
		}	
		
		WeixinJsBean wJsBean = new WeixinJsBean();
		wJsBean.setTimestamp(System.currentTimeMillis() / 1000);
		wJsBean.setNonceStr(Util.getRandomString());
		wJsBean.setAppId(weixin.getAppId());
		wJsBean.setJsapiTicket(weixin.getJsapiTicket(application));
		wJsBean.setUrl(url);
		
		String checkStr = wJsBean.toCheckString(); // 得到参与验证的字符串
		System.out.println(checkStr);
		
		wJsBean.setSignature(Util.SHA1(checkStr));
		return wJsBean;
	}
	

}

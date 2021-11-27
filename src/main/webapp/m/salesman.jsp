<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.tuocheng.util.base.Util" %>
<%@ page import="com.tuocheng.util.base.WeixinUtil" %>
<%@ page import="com.tuocheng.util.base.WeixinUtil.WeixinParams" %>
<%
	String orgId = Util.objToString(session.getAttribute("orgId"), "");

  WeixinUtil appUtil = new WeixinUtil();
  WeixinParams wxParams = appUtil.getWeixinParamsByOrganizationId(orgId);
  if(wxParams != null){
	  String redirect_uri = wxParams.getDomainName() + request.getContextPath() + "/m/salesman1.jsp";
	  String openId = session.getAttribute("weixinOpenId") != null ? (String)session.getAttribute("weixinOpenId") : "";
	  
	  /**
	  * 如果在 session 里找到 openId ，则跳过微信授权
	  **/
	  if(Util.isNotNull(openId)){
		  response.sendRedirect(redirect_uri);
	  } else {
		  String authorizedUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect";
		  String appId = wxParams.getAppId();
		  String scope = "snsapi_base"; // snsapi_base和snsapi_userinfo； snsapi_base 仅获取openId，snsapi_userinfo获取用户信息
		  String state = orgId;
		  String toUrl = String.format(authorizedUrl, appId, redirect_uri, scope, state);
		  System.out.println(toUrl);
		  response.sendRedirect(toUrl);
	  }
  } else {
     out.println("获取机构ID失败!"); 
  }
%>

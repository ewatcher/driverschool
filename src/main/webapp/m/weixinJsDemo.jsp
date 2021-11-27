<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.tuocheng.util.base.WeixinUtil"%>
<%@ page import="com.tuocheng.weixin.base.Weixin"%>
<%@ page import="com.tuocheng.weixin.web.WeixinJsBean"%>
<%@ page import="com.tuocheng.weixin.web.WeixinJsService"%>
<%
	WeixinJsService wJsService = new WeixinJsService(new WeixinUtil().getWeixinByOrganizationId(""));
	WeixinJsBean wJsBean = wJsService.getJsParams(application, request);
%>
<!doctype html>
<html class="no-js fixed-layout">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>驰程交通驾校</title>
<meta name="description" content="驰程交通驾校">
<meta name="keywords" content="index">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="icon" type="image/png" href="../amaze/assets/i/favicon.png">
<link rel="apple-touch-icon-precomposed" href="../amaze/assets/i/app-icon72x72@2x.png">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />
<link rel="stylesheet" href="../amaze/assets/css/amazeui.min.css" />
</head>

<body>

  <form class="am-form">
  <fieldset>
    <legend style="padding-top:20px;"><h1>在线报名</h1></legend>

    <div class="am-form-group">
      <label for="doc-ipt-email-1">联系方式：</label>
      <input type="text" class="" id="doc-ipt-email-1" placeholder="输入您的手机号码">
    </div>

    <div class="am-form-group">
      <label for="doc-ipt-email-1">称呼：</label>
      <input type="text" class="" id="doc-ipt-email-1" placeholder="输入您的称呼">
    </div>

    <div class="am-form-group">
      <label for="doc-select-1">所在地：</label>
      <select id="doc-select-1">
        <option value="option1">右江区</option>
		<option value="option1">田阳县</option>
		<option value="option1">田东县</option>
		<option value="option1">平果县</option>
		<option value="option1">德保县</option>
		<option value="option1">靖西县</option>
		<option value="option1">那坡县</option>
		<option value="option1">凌云县</option>
		<option value="option1">乐业县</option>
		<option value="option1">西林县</option>
		<option value="option1">田林县</option>
      </select>
      <span class="am-form-caret"></span>
    </div>

    <p><button type="button" id="submit_signup_btn" class="am-btn am-btn-primary am-btn-block">提交报名</button></p>
  </fieldset>
</form>


  <!--[if lt IE 9]>
    <script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
    <script src="assets/js/amazeui.ie8polyfill.min.js"></script>
  <![endif]-->

  <!--[if (gte IE 9)|!(IE)]><!-->
  <script src="../amaze/assets/js/jquery.min.js"></script>
  <!--<![endif]-->
  <script src="../amaze/assets/js/amazeui.min.js"></script>
  <script src="../amaze/assets/js/app.js"></script>
  <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
  
  <script type="text/javascript">
  	wx.config({
		debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	    appId: '<%=wJsBean.getAppId()%>', // 必填，公众号的唯一标识
	    timestamp: <%=wJsBean.getTimestamp()%>, // 必填，生成签名的时间戳
	    nonceStr: '<%=wJsBean.getNonceStr()%>', // 必填，生成签名的随机串
	    signature: '<%=wJsBean.getSignature()%>', // 必填，签名，见附录1
	    jsApiList: [
	    	'checkJsApi',
	    	'onMenuShareTimeline'
	        ]   // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	});
  	
  	wx.ready(function(){
  	    // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
  	    
  	    // 
  	    $('#submit_signup_btn').on('click', function () {
  	    	wx.onMenuShareTimeline({
  	    	    title: '这个是标题', // 分享标题
  	    	    link: '这个是分享连接', // 分享链接
  	    	    imgUrl: 'https://gss1.bdstatic.com/5eN1dDebRNRTm2_p8IuM_a/res/r/image/2016-06-24/63790a5529704f78e678d9906379832c.jpg', // 分享图标
  	    	    success: function () { 
  	    	        // 用户确认分享后执行的回调函数
  	    	    },
  	    	    cancel: function () { 
  	    	        // 用户取消分享后执行的回调函数
  	    	    }
  	    	});
  	  	});

  	});
  </script>

</body>
</html>
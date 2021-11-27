<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.tuocheng.util.base.Util"%>
<%@ page import="com.tuocheng.util.base.WeixinUtil"%>
<%@ page import="com.tuocheng.util.base.WeixinUtil.WeixinParams" %>
<%@ page import="com.tuocheng.weixin.base.Weixin"%>
<%@ page import="com.tuocheng.weixin.web.WeixinJsBean"%>
<%@ page import="com.tuocheng.weixin.web.WeixinJsService"%>
<%
	long key = Util.getRandomLong();
  session.setAttribute("signupKey", key);
  
  String orgId = Util.objToString(request.getParameter("orgId"), "");
  String schoolAreaName=Util.getSchoolAreaNameById(orgId);
  WeixinUtil appUtil = new WeixinUtil();
  WeixinJsBean wJsBean = null;
  
  Weixin weixin = appUtil.getWeixinByOrganizationId(orgId);
  if(weixin != null){
    WeixinJsService wJsService = new WeixinJsService(weixin);
  	wJsBean = wJsService.getJsParams(application, request);
  } else {
	wJsBean = new WeixinJsBean();
  }
%>
<!doctype html>
<html class="no-js fixed-layout">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title><%=schoolAreaName %></title>
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

  <fieldset style="text-align: center;">
  
    <legend style="padding-top:20px;"><h1>微信公众号二维码</h1></legend>
    
    
    <p  style="width:100%;text-align: center;">
    <img src="images/<%=orgId%>.jpg" alt="" width="256" height="256" />
    </p>
    
    <p style="padding:40px;">
      扫码关注<%=schoolAreaName %>微信公众号，微信练车预约更简单！
    </p>
    
    <p></p>
  </fieldset>
  <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
  <script type="text/javascript">
    
    var title = <%=schoolAreaName %>; // 分享标题
    var desc = "扫码关注"+<%=schoolAreaName %>+"微信公众号，微信练车预约更简单！"; // 分享描述
    var link = "http://ccjx.tc1680.com/driverschool/m/share.jsp"; // 分享链接
    var imgUrl = "http://ccjx.tc1680.com/driverschool/m/images/qrcode_for_gh_4c8042560ab5_258.jpg"; // 分享图标
    
    wx.config({
   	  debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
      appId: '<%=wJsBean.getAppId()%>', // 必填，公众号的唯一标识
      timestamp: <%=wJsBean.getTimestamp()%>, // 必填，生成签名的时间戳
      nonceStr: '<%=wJsBean.getNonceStr()%>', // 必填，生成签名的随机串
      signature: '<%=wJsBean.getSignature()%>', // 必填，签名，见附录1
      jsApiList: [
        'checkJsApi',
        'onMenuShareTimeline',
        'onMenuShareAppMessage',
        'onMenuShareQQ',
        'onMenuShareWeibo',
        'onMenuShareQZone'
          ]   // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
  });
    
    wx.ready(function(){
      // 分享到朋友圈
      wx.onMenuShareTimeline({
            title: title, // 分享标题
            link: link, // 分享链接
            imgUrl: imgUrl, // 分享图标
            success: function () { 
                // 用户确认分享后执行的回调函数
            },
            cancel: function () { 
                // 用户取消分享后执行的回调函数
            }
        });
      // 分享给朋友
      wx.onMenuShareAppMessage({
          title: title, // 分享标题
          desc: desc, // 分享描述
          link: link, // 分享链接
          imgUrl: imgUrl, // 分享图标
          type: '', // 分享类型,music、video或link，不填默认为link
          dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
          success: function () { 
              // 用户确认分享后执行的回调函数
              //alert('感谢您的分享！');
          },
          cancel: function () { 
              // 用户取消分享后执行的回调函数
          }
      });
      // 分享到QQ
      wx.onMenuShareQQ({
          title: title, // 分享标题
          desc: desc, // 分享描述
          link: link, // 分享链接
          imgUrl: imgUrl, // 分享图标
          success: function () { 
             // 用户确认分享后执行的回调函数
            //alert('感谢您的分享！');
          },
          cancel: function () { 
             // 用户取消分享后执行的回调函数
          }
      });
      // 分享到腾讯微博
      wx.onMenuShareWeibo({
          title: title, // 分享标题
          desc: desc, // 分享描述
          link: link, // 分享链接
          imgUrl: imgUrl, // 分享图标
          success: function () { 
             // 用户确认分享后执行的回调函数
          },
          cancel: function () { 
              // 用户取消分享后执行的回调函数
          }
      });
      //分享到QQ空间
      wx.onMenuShareQZone({
          title: title, // 分享标题
          desc: desc, // 分享描述
          link: link, // 分享链接
          imgUrl: imgUrl, // 分享图标
          success: function () { 
             // 用户确认分享后执行的回调函数
          },
          cancel: function () { 
              // 用户取消分享后执行的回调函数
          }
      });
    });
    
    window.onload = function() {
      audioAutoPlay('media');
    };
    function audioAutoPlay(id){
        var audio = document.getElementById(id),
        play = function(){
            audio.play();
            document.removeEventListener("touchstart", play, false);
        };
        audio.play();
        document.addEventListener("WeixinJSBridgeReady", function () {
            play();
        }, false);
        document.addEventListener("touchstart", play, false);
    }
  </script>
</body>
</html>
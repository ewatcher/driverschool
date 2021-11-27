﻿<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.tuocheng.util.base.Util"%>
<%@ page import="com.tuocheng.util.base.WeixinUtil"%>
<%@ page import="com.tuocheng.weixin.base.Weixin"%>
<%@ page import="com.tuocheng.weixin.web.WeixinJsBean"%>
<%@ page import="com.tuocheng.weixin.web.WeixinJsService"%>
<%
  long key = Util.getRandomLong();
  session.setAttribute("signupKey", key);
  
  String orgId = Util.objToString(request.getParameter("orgId"), "");
  if(!Util.isNotNull(orgId)) orgId = "b78ffef2-7c54-40fe-be4b-1910a87c8bbs";
  
  WeixinJsService wJsService = new WeixinJsService(new WeixinUtil().getWeixinByOrganizationId(orgId));
  WeixinJsBean wJsBean = wJsService.getJsParams(application, request);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<meta content="email=no" name="format-detection">
<title>广西驰程交通驾校</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="fonts/huawen.ttf">
<link rel="stylesheet" href="../css/swiper.min.css">
<link rel="stylesheet" href="../css/animate.min.css">
<link rel="stylesheet" href="../../amaze/assets/css/amazeui.min.css">
<link rel="stylesheet" href="../css/app.css">
<script src="../../amaze/assets/js/jquery.min.js"></script>
<script src="../js/swiper.min.js"></script>
<script src="../js/swiper.animate.min.js"></script>
</head>

<body>


      
      
     <div class="banner1"><img src="images/001.jpg"></div>
     <div class="w8"><img src="images/002.jpg"></div>
     	<div class="banner1"><img src="images/003.jpg"></div>
    	 
        <div class="banner1"><img src="images/banner1.jpg"></div>
 	 <div class="dot"></div>
 	 	<div class="w9"><img src="images/pai.jpg"></div>
         <div class="pq"><img src="images/5.jpg"></div>
         <div class="w7"><img src="images/7.jpg"></div>
         <div class="w8"><img src="images/8.jpg"></div>
         <div class="w9"><img src="images/9.jpg"></div>
         <div class="w6"><img src="images/6.jpg"></div>
        <div class="thanks"><img src="images/thanks.png"></div>
  <div class="dot"></div>
  
	<div class="bootm"><img src="images/13.jpg">
	        <p>
	           <span class="qq">联系我们</span><br>
	           交通驾校<br>
	              报名热线：0776-2881356<br>
	              报名地址：百色市城北一路29号<br>
	              报名地址：原百色汽车总站大院内<br>
	              <span class="qq">我们的分校</span><br>
	              田林永生驾校<br>
	              报名热线：0776-7207718<br>
	              德保常顺驾校<br>
	              报名热线：0776-3806088<br>
	              田阳威龙驾校<br>
	              报名热线：0776-3453699<br>
	        </p>       
	  </div>
  
        <div class="end">
        <img src="images/foot.png">
        </div>
        <div class="foot">
        <img src="images/public.png">
        </div>
        
   <section class="swiper-slide swiper-slide9">
        <div class="ani resize round alpha" style="width:280px;height:100px;left:20px;top:30px;z-index:3;background-color:#333;" swiper-animate-effect="bounceInUp"
          swiper-animate-duration="0.5s" swiper-animate-delay="1.0s">
        </div>
        <div class="ani resize" style="width:280px;height:350px;left:20px;top:80px;z-index:3;background-color:#FFF;border-bottom:2px solid #888" swiper-animate-effect="bounceInLeft"
          swiper-animate-duration="1.4s" swiper-animate-delay="0s">
        </div>
        <span class="ani resize" style="width:320px;left:0px;top:32px;z-index:4;font-weight:bold;letter-spacing:10px;font-size:30px;text-align:center;color:#FFF;" swiper-animate-effect="zoomIn"
          swiper-animate-duration="1.0s" swiper-animate-delay="1.2s">报名填表</span>
        <form class="am-form" action="${pageContext.request.contextPath}/guest/signUpAction!add.action" method="post">
          <fieldset>
            <input type="hidden" name="key" value="<%=key%>" />
            <input type="hidden" name="organizationId" value="<%=orgId%>" />
            <input type="text" class="ani resize" style="width:240px;height:30px;left:40px;top:120px;z-index:6;font-size:16px;background-color:#f6f6f6;" swiper-animate-effect="zoomIn"
              swiper-animate-duration="1.0s" swiper-animate-delay="1.2s" name="upName" id="upName" placeholder="姓名" />
            <input type="text" class="ani resize" style="width:240px;height:30px;left:40px;top:180px;z-index:6;font-size:16px;background-color:#f6f6f6;" swiper-animate-effect="zoomIn"
              swiper-animate-duration="1.0s" swiper-animate-delay="1.3s" name="upAge" id="upAge" placeholder="年龄" />
            <input type="text" class="ani resize" style="width:240px;height:30px;left:40px;top:240px;z-index:6;font-size:16px;background-color:#f6f6f6;" swiper-animate-effect="zoomIn"
              swiper-animate-duration="1.0s" swiper-animate-delay="1.4s" name="upMobile" id="upMobile" placeholder="联系电话" />
            <select type="text" class="ani resize" style="width:240px;height:30px;left:40px;top:300px;z-index:6;background-color:#f6f6f6;" swiper-animate-effect="zoomIn"
              swiper-animate-duration="1.0s" swiper-animate-delay="1.5s"  name="upCity" id="upCity">
              <option value="右江区">右江区</option>
              <option value="田阳县">田阳县</option>
              <option value="田东县">田东县</option>
              <option value="平果县">平果县</option>
              <option value="德保县">德保县</option>
              <option value="靖西县">靖西县</option>
              <option value="那坡县">那坡县</option>
              <option value="凌云县">凌云县</option>
              <option value="乐业县">乐业县</option>
              <option value="西林县">西林县</option>
              <option value="田林县">田林县</option>
              <option value="隆林县">隆林县</option>
            </select>
            <button type="submit" class="ani resize am-btn am-btn-success am-btn-block" style="width:240px;height:30px;left:40px;top:365px;z-index:6;" swiper-animate-effect="zoomIn"
              swiper-animate-duration="1.0s" swiper-animate-delay="1.6s" onclick="return check()" >提交</button>
          </fieldset>
        </form>
      </section>     
        
        
  <script src="../js/app.js"></script>
  <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
  <script type="text/javascript">
  
    function check(){
      var upMobile = $('#upMobile').val();
      var upName = $('#upName').val();
      var upAge = $('#upAge').val();
      if(upAge == undefined || !isNaN(upAge)){
        if(upMobile == undefined || upName == undefined || upMobile == '' || upName == ''){
            alert("姓名及联系电话不能为空！");
            return false;
          } else {
            return true;
          }
      } else {
        alert("年龄格式不正确！");
        return false;
      }
      return false;
    }
    
    var title = "广西驰程交通驾校　大放价，没有最低只有更低。暑期价低至3800元……。"; // 分享标题
    var desc = "大放价，没有最低只有更低。暑期价低至3800元……。"; // 分享描述
    var link = "http://ccjx.tc1680.com/driverschool/m/ad2/index.jsp"; // 分享链接
    var imgUrl = "http://ccjx.tc1680.com/driverschool/m/ad2/images/banner1.jpg"; // 分享图标
    
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
    
  </script>




</body>
</html>
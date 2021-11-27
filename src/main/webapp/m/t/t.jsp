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
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>驰程驾校 - 招生</title>
<meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0' />
<link rel="stylesheet" href="../css/swiper.min.css">
<link rel="stylesheet" href="../css/animate.min.css">
<link rel="stylesheet" href="../../amaze/assets/css/amazeui.min.css">
<link rel="stylesheet" href="../css/app.css">
<script src="../../amaze/assets/js/jquery.min.js"></script>
<script src="../js/swiper.min.js"></script>
<script src="../js/swiper.animate.min.js"></script>
</head>

<body>
  <div class="resize swiper-container" style="background:url(t/o_1altuq86q16p41nim1lse1r4jcnv.png) no-repeat left top;background-size:100% 100%;">
    <div style="display: block;">
      <audio src="t/yq0KZVXS7tyAOx88AAXcP3xDL7M955.mp3" id="media" autoplay preload loop="loop" ></audio>
    </div>
    <div class="swiper-wrapper">
      <section class="swiper-slide swiper-slide1">
        <span class="ani resize" style="width:320px;left:0px;top:5px;z-index:4;font-weight:bold;text-align:center">驰程驾校 - 暑假</span>
        <img src="t/o_1altuq86r1kehf63ali1q71pe010.png" class="ani resize" style="width:220px;height:240px;left:50px;top:110px;z-index:3;" swiper-animate-effect="pulse"
          swiper-animate-duration="0.5s" swiper-animate-delay="0s"> 
        <span class="ani resize" style="width:320px;left:0px;top:390px;z-index:4;font-weight:bold;letter-spacing:4px;text-align:center" swiper-animate-effect="bounceInLeft"
          swiper-animate-duration="0.4s" swiper-animate-delay="1s">招生时间 : 2016-07</span>
        <img src="t/o_1altulr7t1gkb9s9ve1mvdn7jk.png" class="ani resize" style="width:140px;height:4px;left:85px;top:410px;z-index:3;" swiper-animate-effect="bounceInLeft"
          swiper-animate-duration="0.5s" swiper-animate-delay="1s"> 
        <span class="ani resize" style="width:320px;left:0px;top:130px;z-index:4;font-weight:bold;font-size:20px;letter-spacing:2px;text-align:center" swiper-animate-effect="pulse"
          swiper-animate-duration="0.1s" swiper-animate-delay="0s">Logo</span>
        <img src="t/g.gif" class="ani resize" style="width:90px;height:40px;left:110px;top:110px;z-index:3;" swiper-animate-effect="zoomIn"
          swiper-animate-duration="1.5s" swiper-animate-delay="0s">
        <span class="ani resize" style="width:320px;left:0px;top:160px;z-index:4;font-weight:bold;font-size:60px;letter-spacing:2px;text-align:center" swiper-animate-effect="bounceInLeft"
          swiper-animate-duration="0.5s" swiper-animate-delay="0.5s">暑期班</span>
        <span class="ani resize" style="width:320px;left:0px;top:220px;z-index:4;font-weight:bold;font-size:60px;letter-spacing:2px;text-align:center" swiper-animate-effect="bounceInRight"
          swiper-animate-duration="0.5s" swiper-animate-delay="0.5s">招生了</span>
        <img src="t/yq0KXVbE8U2AH4voAADbYplhGGw260.png" class="ani resize" style="width:50px;height:40px;left:50px;top:290px;z-index:3;" swiper-animate-effect="zoomIn"
          swiper-animate-duration="1.5s" swiper-animate-delay="0s">
        <span class="ani resize" style="width:220px;left:100px;top:300px;z-index:4;font-weight:bold;font-size:16px;letter-spacing:6px;" swiper-animate-effect="flash"
          swiper-animate-duration="0.4s" swiper-animate-delay="1s">驰程交通驾校</span>
        <img src="t/o_1altulr7t6c8g931432102e168in.png" class="ani resize" style="width:20px;height:20px;left:60px;top:170px;z-index:3;" swiper-animate-effect="zoomIn"
          swiper-animate-duration="1.5s" swiper-animate-delay="0s">
        <img src="t/o_1altulr7t6c8g931432102e168im.png" class="ani resize" style="width:20px;height:20px;left:230px;top:250px;z-index:6;" swiper-animate-effect="zoomIn"
          swiper-animate-duration="1.5s" swiper-animate-delay="0s">
        <img src="t/o_1altuq86r15f26gbj5s1nu71ktv14.png" class="ani resize" style="width:85px;height:30px;left:200px;top:210px;z-index:6;" swiper-animate-effect="bounceInRight"
          swiper-animate-duration="1.0s" swiper-animate-delay="0.5s">
        <img src="t/o_1altuq86qpn916ot13ia9lf1515p.png" class="ani resize" style="width:90px;height:140px;left:0px;top:0px;z-index:5;" swiper-animate-effect="bounceInDown"
          swiper-animate-duration="0.5s" swiper-animate-delay="0s">
        <img src="t/o_1altuq86q1lj8cdm1ah2khh1dhur.png" class="ani resize" style="width:90px;height:140px;left:230px;top:0px;z-index:5;" swiper-animate-effect="bounceInDown"
          swiper-animate-duration="0.5s" swiper-animate-delay="0s">
        <img src="t/o_1altuq86q1lj8cdm1ah2khh1dhur.png" class="ani resize" style="width:90px;height:160px;left:0px;top:300px;z-index:1;" swiper-animate-effect="bounceInUp"
          swiper-animate-duration="0.5s" swiper-animate-delay="0s">
        <img src="t/o_1altuq86qpn916ot13ia9lf1515p.png" class="ani resize" style="width:90px;height:160px;left:230px;top:300px;z-index:1;" swiper-animate-effect="bounceInUp"
          swiper-animate-duration="0.5s" swiper-animate-delay="0s">
        <img src="t/o_1altuuofr162gcccvfb1e9s1oq34i.png" class="ani resize" style="width:320px;height:480px;left:0px;top:-130px;z-index:1;" swiper-animate-effect="flash"
          swiper-animate-duration="0.5s" swiper-animate-delay="0s">
      </section>
      <section class="swiper-slide swiper-slide2">
        <img src="t/o_1altuq86r1kehf63ali1q71pe010.png" class="ani resize" style="width:260px;height:70px;left:30px;top:50px;z-index:4;" swiper-animate-effect="bounceInDown"
          swiper-animate-duration="1.5s" swiper-animate-delay="0.2s">
        <img src="t/o_1altuq86r1kehf63ali1q71pe010.png" class="ani resize" style="width:260px;height:300px;left:30px;top:100px;z-index:3;" swiper-animate-effect="zoomIn"
          swiper-animate-duration="0.3s" swiper-animate-delay="0s"> 
        <span class="ani resize" style="width:320px;left:0px;top:74px;z-index:4;font-weight:bold;letter-spacing:4px;font-size:24px;text-align:center" swiper-animate-effect="bounceInDown"
          swiper-animate-duration="1.5s" swiper-animate-delay="0.2s">驰程交通驾校简介</span>
        <span class="ani resize" style="width:190px;left:70px;top:150px;z-index:4;font-weight:bold;letter-spacing:4px;font-size:14px;text-indent:36px;" swiper-animate-effect="zoomIn"
          swiper-animate-duration="1.8s" swiper-animate-delay="1.0s">
          驰程驾校拥有各型性能良好的车辆提供教学，教练员是通过严格的考核，并持证上岗，属经验丰富、作风正派、有职业道德、责任心强的高级驾驶员执教。
        </span>
        <img src="t/o_1alu0b0hq1tun182i1abt8pq1hl68n.png" class="ani resize" style="width:320px;height:100px;left:0px;top:440px;z-index:3;" swiper-animate-effect="zoomIn"
          swiper-animate-duration="0.3s" swiper-animate-delay="0s">
        <img src="t/jiaojing.png" class="ani resize" style="width:80px;height:180px;left:240px;top:280px;z-index:3;" swiper-animate-effect="bounceInRight"
          swiper-animate-duration="1.5s" swiper-animate-delay="0.5s">
      </section>
      <section class="swiper-slide swiper-slide3">
        <img src="t/o_1altuuofq1edh1p6kc5v153o16eo4g.png" class="ani resize" style="width:260px;height:80px;left:40px;top:40px;z-index:4;" swiper-animate-effect="bounceInDown"
          swiper-animate-duration="1.5s" swiper-animate-delay="0s">
        <img src="t/o_1altuq86rseu1k0t1he21u2312uu11.png" class="ani resize" style="width:260px;height:90px;left:30px;top:110px;z-index:4;" swiper-animate-effect="bounceInDown"
          swiper-animate-duration="1.5s" swiper-animate-delay="0.1s">
        <img src="t/o_1altuq86rseu1k0t1he21u2312uu11.png" class="ani resize" style="width:260px;height:90px;left:30px;top:210px;z-index:4;" swiper-animate-effect="bounceInDown"
          swiper-animate-duration="1.5s" swiper-animate-delay="0.2s">
        <img src="t/o_1altuq86rseu1k0t1he21u2312uu11.png" class="ani resize" style="width:260px;height:90px;left:30px;top:310px;z-index:4;" swiper-animate-effect="bounceInDown"
          swiper-animate-duration="1.5s" swiper-animate-delay="0.3s">  
        <img src="t/o_1alu0b0hq1tun182i1abt8pq1hl68n.png" class="ani resize" style="width:320px;height:100px;left:0px;top:440px;z-index:3;" swiper-animate-effect="bounceInDown"
          swiper-animate-duration="1.3s" swiper-animate-delay="0.4s">
        <span class="ani resize" style="width:320px;left:0px;top:50px;z-index:4;font-weight:bold;letter-spacing:10px;font-size:24px;text-align:center" swiper-animate-effect="bounceInLeft"
          swiper-animate-duration="1.4s" swiper-animate-delay="1s">暑期特惠</span>
        <div class="ani resize" style="width:320px;left:0px;top:130px;z-index:4;text-align:center" swiper-animate-effect="bounceIn"
          swiper-animate-duration="1.4s" swiper-animate-delay="0.8s">
          <span style="font-weight:bold;font-size:24px;">优惠 A</span><br/>
          <span style="font-weight:bold;font-size:16px;letter-spacing:5px;">持学生证可优惠500元</span>
        </div>
        <div class="ani resize" style="width:320px;left:0px;top:230px;z-index:4;text-align:center" swiper-animate-effect="bounceIn"
          swiper-animate-duration="1.4s" swiper-animate-delay="1.0s">
          <span style="font-weight:bold;font-size:24px;">优惠 B</span><br/>
          <span style="font-weight:bold;font-size:16px;letter-spacing:5px;">持学生证可优惠500元</span>
        </div>
        <div class="ani resize" style="width:320px;left:0px;top:330px;z-index:4;text-align:center" swiper-animate-effect="bounceIn"
          swiper-animate-duration="1.4s" swiper-animate-delay="1.2s">
          <span style="font-weight:bold;font-size:24px;">优惠 C</span><br/>
          <span style="font-weight:bold;font-size:16px;letter-spacing:5px;">持学生证可优惠500元</span>
        </div>
        <img src="t/o_1altuq86r15f26gbj5s1nu71ktv14.png" class="ani resize" style="width:85px;height:30px;left:220px;top:130px;z-index:6;" swiper-animate-effect="bounceInRight"
          swiper-animate-duration="1.5s" swiper-animate-delay="1s">
        <img src="t/o_1altuq86r15f26gbj5s1nu71ktv14.png" class="ani resize" style="width:85px;height:30px;left:220px;top:230px;z-index:6;" swiper-animate-effect="bounceInRight"
          swiper-animate-duration="1.5s" swiper-animate-delay="1.2s">
        <img src="t/o_1altuq86r15f26gbj5s1nu71ktv14.png" class="ani resize" style="width:85px;height:30px;left:220px;top:330px;z-index:6;" swiper-animate-effect="bounceInRight"
          swiper-animate-duration="1.5s" swiper-animate-delay="1.4s">
        <img src="t/kefu.png" class="ani resize" style="width:100px;height:180px;left:0px;top:280px;z-index:8;" swiper-animate-effect="bounceInUp"
          swiper-animate-duration="1.5s" swiper-animate-delay="0s">
      </section>
      <section class="swiper-slide swiper-slide4">
        <img src="t/o_1altuq86rseu1k0t1he21u2312uu12.png" class="ani resize" style="width:80px;height:400px;left:20px;top:40px;z-index:2;" swiper-animate-effect="bounceInUp"
          swiper-animate-duration="1.0s" swiper-animate-delay="0s">
        <img src="t/o_1alu19ivr14mkeb9u1016rt17brap.png" class="ani resize" style="width:180px;height:60px;left:20px;top:40px;z-index:4;" swiper-animate-effect="bounceInLeft"
          swiper-animate-duration="1.0s" swiper-animate-delay="0.2s">
        <img src="t/o_1altuq86rseu1k0t1he21u2312uu11.png" class="ani resize" style="width:260px;height:80px;left:20px;top:120px;z-index:4;" swiper-animate-effect="bounceInLeft"
          swiper-animate-duration="1.0s" swiper-animate-delay="0.4s">
        <img src="t/o_1altuq86rseu1k0t1he21u2312uu11.png" class="ani resize" style="width:260px;height:80px;left:20px;top:220px;z-index:4;" swiper-animate-effect="bounceInLeft"
          swiper-animate-duration="1.0s" swiper-animate-delay="0.6s">
        <img src="t/o_1altuq86rseu1k0t1he21u2312uu11.png" class="ani resize" style="width:260px;height:80px;left:20px;top:320px;z-index:4;" swiper-animate-effect="bounceInLeft"
          swiper-animate-duration="1.0s" swiper-animate-delay="0.8s">
        <span class="ani resize" style="width:140px;left:58px;top:60px;z-index:4;font-weight:bold;letter-spacing:4px;font-size:24px;" swiper-animate-effect="bounceInLeft"
          swiper-animate-duration="0.4s" swiper-animate-delay="1s">特点介绍</span>
        <img src="t/o_1alu0agq41v841oroo5f1gr818h38e.png" class="ani resize" style="width:120px;height:30px;left:40px;top:130px;z-index:4;" swiper-animate-effect="bounceInRight"
          swiper-animate-duration="1.5s" swiper-animate-delay="0.8s">
        <img src="t/o_1alu0agq41v841oroo5f1gr818h38e.png" class="ani resize" style="width:210px;height:40px;left:50px;top:145px;z-index:4;" swiper-animate-effect="bounceInRight"
          swiper-animate-duration="1.5s" swiper-animate-delay="0.8s">
        <img src="t/o_1alu0agq414e9tq21b7915lv1nbb8f.png" class="ani resize" style="width:120px;height:30px;left:40px;top:230px;z-index:4;" swiper-animate-effect="bounceInRight"
          swiper-animate-duration="1.5s" swiper-animate-delay="0.8s">
        <img src="t/o_1alu0agq414e9tq21b7915lv1nbb8f.png" class="ani resize" style="width:210px;height:40px;left:50px;top:245px;z-index:4;" swiper-animate-effect="bounceInRight"
          swiper-animate-duration="1.5s" swiper-animate-delay="0.8s">
        <img src="t/o_1alu0oo7f1a4a1jbclsp1lp4hmi9v.png" class="ani resize" style="width:120px;height:30px;left:40px;top:330px;z-index:4;" swiper-animate-effect="bounceInRight"
          swiper-animate-duration="1.5s" swiper-animate-delay="0.8s">
        <img src="t/o_1alu0oo7f1a4a1jbclsp1lp4hmi9v.png" class="ani resize" style="width:210px;height:40px;left:50px;top:345px;z-index:4;" swiper-animate-effect="bounceInRight"
          swiper-animate-duration="1.5s" swiper-animate-delay="0.8s">
        <span class="ani resize" style="width:120px;left:58px;top:136px;z-index:4;font-weight:bold;letter-spacing:4px;font-size:18px;" swiper-animate-effect="bounceInLeft"
          swiper-animate-duration="1.4s" swiper-animate-delay="1s">高通过率</span>
        <span class="ani resize" style="width:220px;left:66px;top:156px;z-index:4;font-weight:bold;letter-spacing:4px;font-size:14px;color:#FFF;" swiper-animate-effect="bounceInLeft"
          swiper-animate-duration="1.4s" swiper-animate-delay="1s">考试通过率高达99%</span>
        <span class="ani resize" style="width:120px;left:58px;top:236px;z-index:4;font-weight:bold;letter-spacing:4px;font-size:18px;" swiper-animate-effect="bounceInLeft"
          swiper-animate-duration="1.4s" swiper-animate-delay="1s">高仿真考场</span>
        <span class="ani resize" style="width:220px;left:66px;top:256px;z-index:4;font-weight:bold;letter-spacing:4px;font-size:14px;color:#FFF;" swiper-animate-effect="bounceInLeft"
          swiper-animate-duration="1.4s" swiper-animate-delay="1s">考场高仿车管所，更快通过</span>
        <span class="ani resize" style="width:120px;left:58px;top:336px;z-index:4;font-weight:bold;letter-spacing:4px;font-size:18px;" swiper-animate-effect="bounceInLeft"
          swiper-animate-duration="1.4s" swiper-animate-delay="1s">师资雄厚</span>
        <span class="ani resize" style="width:220px;left:66px;top:356px;z-index:4;font-weight:bold;letter-spacing:4px;font-size:14px;color:#FFF;" swiper-animate-effect="bounceInLeft"
          swiper-animate-duration="1.4s" swiper-animate-delay="1s">拥有多名国家1级教练</span>
        <img src="t/o_1alu1pvvf1fio1nf01cp51hvc1q5ib6.png" class="ani resize" style="width:41px;height:35px;left:260px;top:136px;z-index:4;" swiper-animate-effect="zoomIn"
          swiper-animate-duration="0.3s" swiper-animate-delay="1.6s">
        <img src="t/o_1alu1pvvfns71dj9akc1l0611c1b7.png" class="ani resize" style="width:41px;height:35px;left:260px;top:236px;z-index:4;" swiper-animate-effect="zoomIn"
          swiper-animate-duration="0.3s" swiper-animate-delay="1.7s">
        <img src="t/o_1alu1ro7b1m0lko91i22c2819o2bt.png" class="ani resize" style="width:41px;height:35px;left:260px;top:336px;z-index:4;" swiper-animate-effect="zoomIn"
          swiper-animate-duration="0.3s" swiper-animate-delay="1.8s">
        <img src="t/o_1alu0b0hq1tun182i1abt8pq1hl68n.png" class="ani resize" style="width:320px;height:100px;left:0px;top:440px;z-index:3;" swiper-animate-effect="bounceInUp"
          swiper-animate-duration="0.8s" swiper-animate-delay="0s">
        <img src="t/car.png" class="ani resize" style="width:80px;height:70px;left:230px;top:10px;z-index:3;" swiper-animate-effect="zoomIn"
          swiper-animate-duration="0.4s" swiper-animate-delay="0s">
      </section>
      <section class="swiper-slide swiper-slide5">
        <img src="t/o_1al9lep921ein1cj615invausa20.png" class="ani resize" style="width:300px;height:360px;left:10px;top:40px;z-index:3;background-color:#fdeac9;" swiper-animate-effect="zoomIn"
          swiper-animate-duration="1.5s" swiper-animate-delay="0s">
        <span class="ani resize" style="width:320px;left:0px;top:70px;z-index:4;font-weight:bold;letter-spacing:8px;font-size:24px;text-align:center" swiper-animate-effect="bounceInUp"
          swiper-animate-duration="0.4s" swiper-animate-delay="1s">招生简章</span>
        <span class="ani resize" style="width:190px;left:65px;top:100px;z-index:4;font-size:12px;" swiper-animate-effect="bounceInUp"
          swiper-animate-duration="1.4s" swiper-animate-delay="1s">
          <b>1、年龄条件</b><br/>
          小型车：18-70周岁。<br/>
          大型车：21-50周岁。<br/>
          <b>2、报名所需资料</b><br/>
          身份证复印件（正反面）4张<br/>
          体检表1张<br/>
          驾驶员照片13张（1寸彩色白底免冠照片）<br/>
          外地学员需持居住所在地派出所办理的暂停证<br/>
          <b>3、报名地址</b><br/>
          百色右江区朝阳路203号
        </span>
        <img src="t/o_1alu0b0hq1tun182i1abt8pq1hl68n.png" class="ani resize" style="width:320px;height:100px;left:0px;top:440px;z-index:3;" swiper-animate-effect="zoomIn"
          swiper-animate-duration="0.3s" swiper-animate-delay="0s">
      </section>
      <section class="swiper-slide swiper-slide6">
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
        <img src="t/o_1alu0b0hq1tun182i1abt8pq1hl68n.png" class="ani resize" style="width:320px;height:100px;left:0px;top:460px;z-index:3;" swiper-animate-effect="zoomIn"
          swiper-animate-duration="0.3s" swiper-animate-delay="0s">
      </section>
    </div>
    <img src="../images/arrow.png" style="width:20px;height:15px; top:460px; left:150px;" id="array" class="resize">
    <div class="swiper-pagination"></div>
  </div>
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
    
    var title = "驰程驾校暑期班开班了"; // 分享标题
    var desc = "驰程驾校暑期班开班了，多种优惠项目。凭学生证立减500，微信分享送学时..."; // 分享描述
    var link = "http://ccjx.tc1680.com/driverschool/m/t/t.jsp"; // 分享链接
    var imgUrl = "http://ccjx.tc1680.com/driverschool/m/t/t/icon-sqzs.png"; // 分享图标
    
    wx.config({
    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
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

<%@ page language="java" pageEncoding="UTF-8"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="apple-mobile-web-app-title" content="广西驰程交通驾校开学学车大优惠">
<meta name="description" content="广西驰程交通驾校开学学车大优惠，最低至:3900元">
<meta itemprop="name" content="广西驰程交通驾校开学学车大优惠">
<meta itemprop="description" content="广西驰程交通驾校开学学车大优惠，最低至:3900元">
<meta itemprop="image" content="http://ccjx.tc1680.com/driverschool/m/ad1/file/logo.jpg">
<title>广西驰程交通驾校开学学车大优惠</title>
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
  <div style="position:absolute;right:1.5rem;top:1.5rem;z-index:100;" id="musicBtn" onclick="playChange()">
    <div style="display: block;">
      <audio src="file/background.mp3" id="media" autoplay preload loop="loop" ></audio>
    </div>
    <img alt="..." src="../images/music_stop.png" style="z-index:105;width:3rem;height:3rem;" class="myrotate" id="playImage">
  </div>
  <div class="resize swiper-container" style="background:url(file/background.jpg) no-repeat left top;background-size:100% 100%;">
    <div class="swiper-wrapper">
      <section class="swiper-slide swiper-slide1">
        <img src="file/b1.jpg" class="ani resize" style="width:320px;height:480px;z-index:3;" swiper-animate-effect="pulse"
          swiper-animate-duration="0.5s" swiper-animate-delay="0s">
        <span class="ani resize" style="width:320px;left:0px;top:280px;z-index:4;font-weight:bold;text-align:center;font-size:26px;color:#FF8C00;" swiper-animate-effect="fadeInUp"
          swiper-animate-duration="0.5s" swiper-animate-delay="0.5s">驰程交通驾校</span> 
      </section>
      <section class="swiper-slide swiper-slide2">
        <span class="ani resize" style="width:320px;left:0px;top:60px;z-index:4;font-weight:bold;letter-spacing:4px;font-size:22px;text-align:center" swiper-animate-effect="bounceInDown"
          swiper-animate-duration="1.5s" swiper-animate-delay="0.2s">驰程交通驾校开学大优惠</span>
        <span class="ani resize" style="width:260px;left:30px;top:120px;z-index:4;font-weight:bold;letter-spacing:4px;font-size:20px;" swiper-animate-effect="zoomIn"
          swiper-animate-duration="1.0s" swiper-animate-delay="1.0s">　　今年是驰程集团建司60周年，为回馈社会广大人士长期对我校的关爱，我校决定开展开学学车大优惠活动。<br/>
          　　即日起凡到校报名学车的学员均可享受优惠价：<span style="color:#FF8C00;">4000元/人</span><br/>
          <span class="ani resize" swiper-animate-effect="flash"
            swiper-animate-duration="1.0s" swiper-animate-delay="2.0s">
            　　学生及老师再优惠<span style="color:#FF8C00;">100</span>元，只要：<span style="color:#FF8C00;">3900元/人</span><br/>
        </span>
        
        </span>
      </section>
      <section class="swiper-slide swiper-slide3">
        <span class="ani resize" style="width:320px;left:0px;top:40px;z-index:4;font-weight:bold;letter-spacing:4px;font-size:24px;text-align:center" swiper-animate-effect="bounceInDown"
          swiper-animate-duration="1.5s" swiper-animate-delay="0.2s">驰程交通驾校简介</span>
        <span class="ani resize" style="width:260px;left:30px;top:90px;z-index:4;font-weight:bold;letter-spacing:4px;font-size:16px;" swiper-animate-effect="bounceInUp"
          swiper-animate-duration="1.0s" swiper-animate-delay="1.0s">　　驰程交通驾校是一家专业的机动车驾驶员培训机构。学校位于旧汽车客运总站内,交通便利,场地设备新颖，环境优美,风景宜人。学校凭借“以人为本，服务第一”的教学宗旨“让学员完全满意”的经营理念,“管理最规范，交通最便捷，施教最廉洁，体制最正规”的高质量教学技术和良好的配套服务，深受学员的广大好评，在社会上树立良好的形象。学校师资力量雄厚，教学设施齐全，信誉好,口碑好,为社会各界人士提供专业、系统的机动车驾驶技能培训。施教,廉洁的培训,让您学有所成,称心满意。<br/>
        </span>
      </section>
      <section class="swiper-slide swiper-slide4">
        <img src="file/jpjx.jpg" class="ani resize" style="width:300px;height:180px;left:10px;top:30px;z-index:2;" swiper-animate-effect="flipInX"
          swiper-animate-duration="1.5s" swiper-animate-delay="0s">
        <img src="file/ryzs.jpg" class="ani resize" style="width:300px;height:190px;left:10px;top:250px;z-index:4;" swiper-animate-effect="flipInX"
          swiper-animate-duration="1.5s" swiper-animate-delay="0.5s">
        <span class="ani resize" style="width:320px;left:20px;top:40px;z-index:6;font-weight:bold;letter-spacing:4px;font-size:28px;text-align:left;color:#FF8C00;" swiper-animate-effect="fadeInDown"
          swiper-animate-duration="1.5s" swiper-animate-delay="0.8s">我们的荣誉</span>
        <span class="ani resize" style="width:320px;left:10px;top:215px;z-index:4;font-weight:bold;letter-spacing:4px;font-size:24px;text-align:left;color:#FF3030;" swiper-animate-effect="fadeInRight"
          swiper-animate-duration="1.5s" swiper-animate-delay="1.0s">是良好教学的保证</span>
        <span class="ani resize" style="width:320px;left:60px;top:225px;z-index:4;font-weight:bold;letter-spacing:4px;font-size:24px;text-align:left;color:#FF8C00;" swiper-animate-effect="fadeInLeft"
          swiper-animate-duration="1.5s" swiper-animate-delay="1.0s">------------------</span>
      </section>
      <section class="swiper-slide swiper-slide5">
        <img src="file/DSC05949.jpg" class="ani resize" style="width:300px;height:180px;left:10px;top:75px;z-index:2;" swiper-animate-effect="fadeInDown"
          swiper-animate-duration="1.5s" swiper-animate-delay="0s">
        <img src="file/che.jpg" class="ani resize" style="width:300px;height:140px;left:10px;top:295px;z-index:4;" swiper-animate-effect="fadeInDown"
          swiper-animate-duration="1.5s" swiper-animate-delay="0.5s">
        <span class="ani resize" style="width:320px;left:20px;top:40px;z-index:6;font-weight:bold;letter-spacing:4px;font-size:28px;text-align:left;color:#FF8C00;" swiper-animate-effect="fadeInDown"
          swiper-animate-duration="1.5s" swiper-animate-delay="0.8s">宽广的教学场地</span>
        <span class="ani resize" style="width:320px;left:10px;top:260px;z-index:4;font-weight:bold;letter-spacing:4px;font-size:24px;text-align:left;color:#FF3030;" swiper-animate-effect="fadeInRight"
          swiper-animate-duration="1.5s" swiper-animate-delay="1.0s">一人一车一教练专属教学</span>
        <span class="ani resize" style="width:320px;left:60px;top:275px;z-index:4;font-weight:bold;letter-spacing:4px;font-size:24px;text-align:left;color:#FF8C00;" swiper-animate-effect="fadeInLeft"
          swiper-animate-duration="1.5s" swiper-animate-delay="1.0s">----------------------</span>
      </section>
      <section class="swiper-slide swiper-slide6">
        <span class="ani resize" style="width:320px;left:0px;top:40px;z-index:9;font-weight:bold;letter-spacing:4px;font-size:24px;text-align:right;color:#FF8C00;" swiper-animate-effect="fadeInLeft"
          swiper-animate-duration="1.5s" swiper-animate-delay="1.0s">电视台采访</span>
        <img src="file/xc1.png" class="ani resize" style="width:300px;height:160px;left:10px;top:275px;z-index:9;" swiper-animate-effect="fadeInUp"
          swiper-animate-duration="1.5s" swiper-animate-delay="1.0s">
        <img src="file/xc2.png" class="ani resize" style="width:260px;height:120px;left:30px;top:65px;z-index:4;" swiper-animate-effect="fadeInDown"
          swiper-animate-duration="1.5s" swiper-animate-delay="0s">
        <img src="file/xc3.png" class="ani resize" style="width:260px;height:180px;left:-50px;top:160px;z-index:7;" swiper-animate-effect="rollIn"
          swiper-animate-duration="1.5s" swiper-animate-delay="0.5s">
        <img src="file/xc4.png" class="ani resize" style="width:260px;height:180px;left:140px;top:140px;z-index:6;" swiper-animate-effect="rotateInDownRight"
          swiper-animate-duration="1.5s" swiper-animate-delay="0.5s">
      </section>
      <section class="swiper-slide swiper-slide6">
        <img src="file/fj1.JPG" class="ani resize" style="width:240px;height:160px;left:0px;top:50px;z-index:5;" swiper-animate-effect="fadeInUp"
          swiper-animate-duration="1.5s" swiper-animate-delay="1.0s">
        <img src="file/fj2.JPG" class="ani resize" style="width:240px;height:160px;left:80px;top:160px;z-index:6;" swiper-animate-effect="fadeInDown"
          swiper-animate-duration="1.5s" swiper-animate-delay="0s">
        <img src="file/fj3.JPG" class="ani resize" style="width:240px;height:160px;left:0px;top:280px;z-index:7;" swiper-animate-effect="fadeInRight"
          swiper-animate-duration="1.5s" swiper-animate-delay="0.5s">
        <span class="ani resize" style="width:320px;left:0px;top:40px;z-index:9;font-weight:bold;letter-spacing:4px;font-size:24px;text-align:right;color:#FF8C00;" swiper-animate-effect="fadeInRight"
          swiper-animate-duration="1.5s" swiper-animate-delay="1.0s">教学环境</span>
      </section>
      <section class="swiper-slide swiper-slide8">
        <span class="ani resize" style="width:320px;left:0px;top:50px;z-index:4;font-weight:bold;letter-spacing:8px;font-size:26px;text-align:center" swiper-animate-effect="bounceInUp"
          swiper-animate-duration="0.4s" swiper-animate-delay="0s">招生简章</span>
        <span class="ani resize" style="width:280px;left:20px;top:100px;z-index:4;font-size:16px;" swiper-animate-effect="bounceInUp"
          swiper-animate-duration="1.4s" swiper-animate-delay="0.5s">
          <b>1、年龄条件</b><br/>
          18-70周岁。<br/>
          <b>2、报名所需资料</b><br/>
          身份证复印件（正反面）4张<br/>
          体检表1张<br/>
          驾驶员照片13张（1寸彩色白底免冠照片）<br/>
          外地学员需持居住所在地派出所办理的暂住证<br/>
          <b>3、报名地址</b><br/>
          百色市右江区城北一路２９号（原汽车总站大院）<br/>
          <b>4、报名电话</b><br/>
          (0776)2881356<br/>
        </span>
      </section>
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
    
    var title = "广西驰程交通驾校开学学车大优惠"; // 分享标题
    var desc = "驰程交通驾校开学学车大优惠。凭学生证立减100，微信分享送学时..."; // 分享描述
    var link = "http://ccjx.tc1680.com/driverschool/m/ad1/ad1.jsp"; // 分享链接
    var imgUrl = "http://ccjx.tc1680.com/driverschool/m/ad1/file/logo.jpg"; // 分享图标
    
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


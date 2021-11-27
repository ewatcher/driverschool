<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page import="com.tuocheng.util.base.Util" %>
<%
	String schoolAreanName=Util.objToString(session.getAttribute("schoolAreaName"), "");
	String orgId = Util.objToString(session.getAttribute("orgId"), "");
%>
<!doctype html>
<html class="no-js">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<!--自动适应移动屏幕-->
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
<!--优先使用webkit内核渲染-->
<meta name="renderer" content="webkit" />
<!--不要被百度转码-->
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--以下才是引入amazeui资源-->
<link rel="stylesheet" href="../amaze/assets/css/amazeui.min.css" />
<link rel="stylesheet" href="../amaze/assets/css/app.css" />
<!--引入js的时候要注意，必须先引入jQuery，再引入amazeui，因为这个框架是基于jQuery开发的-->
<script src="../amaze/assets/js/jquery.min.js"></script>
<script src="../amaze/assets/js/amazeui.min.js"></script>
<title>学员服务 - <%=schoolAreanName %></title>
<style type="text/css">
.am-viewport {
	height: 100px;
}

.am-viewport img {
	height: 100px;
}
.am-icon-btn:active {
    background-color:#c4e1ff;
}
.am-icon-btn:hover {
    background-color:#c4e1ff;
}
</style>
</head>
<body style="font-size: 1.2rem;">
  <div data-am-widget="slider" class="am-slider am-slider-b2" data-am-slider='{"controlNav":false}'>
    <ul class="am-slides" style="height:100px;">
      <li><img src="jx/ad3.jpg" height="180"></li>
      <li><img src="jx/ad4.jpg" height="180"></li>
      <li><img src="jx/ad1.jpg" height="180"></li>
      <li><img src="jx/ad2.jpg" height="180"></li>
    </ul>
  </div>

  <div style="margin:10px 10px 10px 10px;">
    <ul class="am-avg-sm-4 am-thumbnails" style="text-align: center;">
    <c:if test="${orgId!='c0510169-8edc-470e-b038-c299c7735bty' }">
      <li><a href="${pageContext.request.contextPath}/m/reservation.jsp" class="am-icon-btn am-secondary am-icon-automobile"></a><br /> 学车预约</li>
    </c:if> 
      <li><a href="${pageContext.request.contextPath}/m/ureservation.jsp" class="am-icon-btn am-success am-icon-recycle"></a><br /> 我的预约</li>
      <li><a href="${pageContext.request.contextPath}/m/assess.jsp" class="am-icon-btn am-warning am-icon-star-half-empty"></a><br /> 评价教练</li>
       <li><a href="${pageContext.request.contextPath}/m/salesman.jsp" class="am-icon-btn am-warning am-icon-star-half-empty"></a><br /> 评价业务员</li>
    </ul>

    <ul class="am-avg-sm-4 am-thumbnails" style="text-align: center;">
      <li><a href="${pageContext.request.contextPath}/m/myevaluate.jsp" class="am-icon-btn am-danger am-icon-commenting-o"></a><br /> 我的点评</li>
      <li><a href="${pageContext.request.contextPath}/m/difficulty.jsp?}" class="am-icon-btn am-danger am-icon-frown-o"></a><br /> 难点反馈</li>
      <li><a href="${pageContext.request.contextPath}/m/articleAction!secretslist.action" class="am-icon-btn am-success am-icon-book"></a><br /> 驾考宝典</li>
      <li><a href="${pageContext.request.contextPath}/m/articleAction!student.action" class="am-icon-btn am-secondary am-icon-drupal"></a><br /> 学员中心</li>
    </ul>
  </div>
  
  <div class="am-panel-secondary">
    <div class="am-panel-hd">
      <span class="am-icon-image" style="margin-right:10px;"></span>驾校风采
    </div>
    <ul data-am-widget="gallery" class="am-gallery am-avg-sm-3
  am-avg-md-3 am-avg-lg-4 am-gallery-imgbordered" style="padding:0px;" data-am-gallery="{  }">
      <li>
        <div class="am-gallery-item">
          <a href="${pageContext.request.contextPath}/m/articleAction!images.action" class=""> <img src="jx/a1.jpg" alt="..." />
          </a>
        </div>
      </li>
      <li>
        <div class="am-gallery-item">
          <a href="${pageContext.request.contextPath}/m/articleAction!images.action" class=""> <img src="jx/a4.jpg" alt="..." />
          </a>
        </div>
      </li>
      <li>
        <div class="am-gallery-item">
          <a href="${pageContext.request.contextPath}/m/articleAction!images.action" class=""> <img src="jx/a8.jpg" alt="..." />
          </a>
        </div>
      </li>
    </ul>
  </div>

  <div class="am-panel am-panel-secondary" style="margin-bottom:0px;">
    <div class="am-panel-hd">
      <span class="am-icon-connectdevelop" style="margin-right:10px;"></span>驾校时讯
    </div>
    <ul class="am-list am-list-border" style="font-size:1.4rem;">
      <c:forEach var="row" items="${newsData.rows}" varStatus="status">
      <li class="am-g am-list-item-dated"><a href="${pageContext.request.contextPath}/m/articleAction!info.action?id=${row.id}" class="am-list-item-hd">${row.title}</a></li>
      </c:forEach>
    </ul>
  </div>
  
  <div>
    <img alt="..." src="http://www.gxccjt.com/Datafiles/Ads/7_Info/20150122111557_291.gif" height="60" width="100%" />
  </div>

  <div class="am-panel am-panel-secondary">
    <div class="am-panel-hd">
      <span class="am-icon-automobile" style="margin-right:10px;"></span>驾考宝典
    </div>
      <ul class="am-list am-list-border" style="font-size:1.4rem;">
        <c:forEach var="row" items="${secretsData.rows}" varStatus="status">
        <li class="am-g am-list-item-dated"><a href="${pageContext.request.contextPath}/m/articleAction!info.action?id=${row.id}" class="am-list-item-hd">${row.title}</a></li>
        </c:forEach>
      </ul>
  </div>

  <div style="height:50px;"></div>

  <div class="am-navbar am-navbar-default">
    <ul class="am-navbar-nav">
      <li class="am-btn am-btn-primary am-btn-xs" style="padding:0px;"><a href="${pageContext.request.contextPath}/m/articleAction!index.action?" style="color:#c4e1ff;"> <span class="am-icon-home"></span> <span class="am-navbar-label">首页</span>
      </a></li>
      <li class="am-btn am-btn-primary am-btn-xs" style="padding:0px;"><a href="${pageContext.request.contextPath}/m/articleAction!notice.action?"> <span class="am-icon-star"></span> <span class="am-navbar-label">报名须知</span>
      </a></li>
      <li class="am-btn am-btn-primary am-btn-xs" style="padding:0px;"><a href="${pageContext.request.contextPath}/m/articleAction!student.action?"> <span class="am-icon-drupal"></span> <span class="am-navbar-label">学员</span>
      </a></li>
    </ul>
  </div>
  <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
  <script type="text/javascript">
    
    var title = <%=schoolAreanName%>; // 分享标题
    var desc = "关注"+<%=schoolAreanName%>+"公众号，直接通过微信练车预约，分享点赞送学时..."; // 分享描述
    var link = "http://ccjx.tc1680.cn/driverschool/m/articleAction!index.action"; // 分享链接
    var imgUrl = "http://ccjx.tc1680.cn/driverschool/m/ad1/file/logo.jpg"; // 分享图标
    
    wx.config({
    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
      appId: '${appId}', // 必填，公众号的唯一标识
      timestamp: ${timestamp}, // 必填，生成签名的时间戳
      nonceStr: '${nonceStr}', // 必填，生成签名的随机串
      signature: '${signature}', // 必填，签名，见附录1
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

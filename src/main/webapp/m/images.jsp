<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!doctype html>
<html class="no-js">
<html>
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
<link rel="stylesheet" href="../ueditor/themes/iframe.css" />
<script src="../ueditor/ueditor.parse.js"></script>
<title>驾校风采 - 广西驰程交通驾校</title>
<style type="text/css">
.am-viewport {
	height: 100px;
}

.am-viewport img {
	height: 100px;
}

.am-icon-btn:active {
	background-color: #c4e1ff;
}

.am-icon-btn:hover {
	background-color: #c4e1ff;
}
</style>
</head>
<body style="font-size: 1.2rem;">

  <div data-am-widget="slider" class="am-slider am-slider-b2" data-am-slider='{"controlNav":false}'>
    <ul class="am-slides" style="height:100px;">
      <li><img src="jx/ad3.jpg"></li>
      <li><img src="jx/ad4.jpg"></li>
      <li><img src="jx/ad1.jpg"></li>
      <li><img src="jx/ad2.jpg"></li>
    </ul>
  </div>

  <div>
    <ul data-am-widget="gallery" class="am-gallery am-avg-sm-2
  am-avg-md-3 am-avg-lg-4 am-gallery-imgbordered" data-am-gallery="{pureview:{weChatImagePreview: false}}" >
      <li>
        <div class="am-gallery-item">
          <img src="jx/a1.jpg" alt="驾校环境" />
          <h3 class="am-gallery-title">驾校环境</h3>
        </div>
      </li>
      <li>
        <div class="am-gallery-item">
          <img src="jx/a2.jpg" alt="驾校车辆" />
          <h3 class="am-gallery-title">驾校车辆</h3>
        </div>
      </li>
      <li>
        <div class="am-gallery-item">
          <img src="jx/a3.jpg" alt="获得的荣誉" />
          <h3 class="am-gallery-title">获得的荣誉</h3>
        </div>
      </li>
      <li>
        <div class="am-gallery-item">
          <img src="jx/a4.jpg" alt="汽车驾驶残疾人第一期毕业照" />
          <h3 class="am-gallery-title">汽车驾驶残疾人第一期毕业照</h3>
        </div>
      </li>
      <li>
        <div class="am-gallery-item">
          <img src="jx/a5.jpg" alt="模拟考试" />
          <h3 class="am-gallery-title">模拟考试</h3>
        </div>
      </li>
      <li>
        <div class="am-gallery-item">
          <img src="jx/a6.jpg" alt="模拟考试车辆" />
          <h3 class="am-gallery-title">模拟考试车辆</h3>
        </div>
      </li>
      <li>
        <div class="am-gallery-item">
          <img src="jx/a7.jpg" alt="模拟公交站台" />
          <h3 class="am-gallery-title">模拟公交站台</h3>
        </div>
      </li>
      <li>
        <div class="am-gallery-item">
          <img src="jx/a8.jpg" alt="获取的奖牌" />
          <h3 class="am-gallery-title">获取的奖牌</h3>
        </div>
      </li>
      <li>
        <div class="am-gallery-item">
          <img src="jx/a9.jpg" alt="电视台采访驾校教练" />
          <h3 class="am-gallery-title">电视台采访驾校教练</h3>
        </div>
      </li>
      <li>
        <div class="am-gallery-item">
          <img src="jx/a10.jpg" alt="驾校车辆" />
          <h3 class="am-gallery-title">驾校车辆</h3>
        </div>
      </li>
      <li>
        <div class="am-gallery-item">
          <img src="jx/a11.jpg" alt="半坡起步" />
          <h3 class="am-gallery-title">半坡起步</h3>
        </div>
      </li>
      <li>
        <div class="am-gallery-item">
          <img src="jx/a12.jpg" alt="宽广的学习场地" />
          <h3 class="am-gallery-title">宽广的学习场地</h3>
        </div>
      </li>
      <li>
        <div class="am-gallery-item">
          <img src="jx/a13.jpg" alt="模拟十字路口" />
          <h3 class="am-gallery-title">模拟十字路口</h3>
        </div>
      </li>
    </ul>
  </div>



  <div style="height:50px;"></div>

  <div class="am-navbar am-navbar-default">
    <ul class="am-navbar-nav">
      <li class="am-btn am-btn-primary am-btn-xs" style="padding:0px;"><a href="${pageContext.request.contextPath}/m/articleAction!index.action" style="color:#c4e1ff;"> <span class="am-icon-home"></span> <span class="am-navbar-label">首页</span>
      </a></li>
      <li class="am-btn am-btn-primary am-btn-xs" style="padding:0px;"><a href="${pageContext.request.contextPath}/m/articleAction!tariff.action"> <span class="am-icon-calculator"></span> <span class="am-navbar-label">资费套餐</span>
      </a></li>
      <li class="am-btn am-btn-primary am-btn-xs" style="padding:0px;"><a href="${pageContext.request.contextPath}/m/articleAction!notice.action"> <span class="am-icon-star"></span> <span class="am-navbar-label">报名须知</span>
      </a></li>
      <li class="am-btn am-btn-primary am-btn-xs" style="padding:0px;"><a href="${pageContext.request.contextPath}/m/articleAction!student.action"> <span class="am-icon-drupal"></span> <span class="am-navbar-label">学员</span>
      </a></li>
    </ul>
  </div>
</body>
</html>

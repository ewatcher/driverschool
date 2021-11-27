<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page import="com.tuocheng.util.base.Util"%>
<%
	String orgId=Util.objToString(session.getAttribute("orgId"), "");
  	String schoolAreaName=Util.objToString(session.getAttribute("schoolAreaName"), "");
%>
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
<title>资费套餐 -<%=schoolAreaName %></title>
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

  <div class="am-panel am-panel-secondary" style="margin-bottom:0px;">
    <div class="am-panel-hd">
      <span class="am-icon-calculator" style="margin-right:10px;"></span>资费套餐
    </div>
    <!-- 百色 -->
    <c:if test="${orgId=='b78ffef2-7c54-40fe-be4b-1910a87c8bbs' }">
    	<div>
      <table class="am-table am-table-bordered am-table-compact">
        <thead>
          <tr>
            <th width="24%">培训车型</th>
            <th width="38%">Ｃ１手动挡小车</th>
            <th width="38%">Ｃ２自动挡小车</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>套餐一</td>
            <td>
              4000元<br/>
              学员学车课时不限，学会为止！（学员在完成学习7个模拟驾驶课时和3个电车驾驶课时之后，即可进入实车操作训练）
            </td>
            <td>
              4200元<br/>
              学员学车课时不限，学会为止！（学员在完成学习7个模拟驾驶课时和3个电车驾驶课时之后，即可进入实车操作训练）
            </td>
          </tr>
          <tr>
            <td>操作培训课时<br/>(国际规定课时)</td>
            <td>
              48课时（驾驶操作）
            </td>
            <td>
              48课时（驾驶操作）
            </td>
          </tr>
        </tbody>
      </table>
      <span style="color:red;"> * 暑期大优惠：学生及老师凭学生证、教师资格证优惠100元</span>
    </div>
    </c:if>
    <!-- 田阳 -->
    <c:if test="${orgId=='c0510169-8edc-470e-b038-c299c7735bty' }">
    	<div>
      <table class="am-table am-table-bordered am-table-compact">
        <thead>
          <tr>
            <th width="24%">培训车型</th>
            <th width="38%">Ｃ１手动挡小车</th>
            <th width="38%">Ｃ２自动挡小车</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>套餐一</td>
            <td>
              3700元<br/>
              学员学车课时不限，学会为止！（学员在完成学习7个模拟驾驶课时和3个电车驾驶课时之后，即可进入实车操作训练）
            </td>
            <td>
              4000元<br/>
              学员学车课时不限，学会为止！（学员在完成学习7个模拟驾驶课时和3个电车驾驶课时之后，即可进入实车操作训练）
            </td>
          </tr>
          <tr>
            <td>操作培训课时<br/>(国际规定课时)</td>
            <td>
              48课时（驾驶操作）
            </td>
            <td>
              48课时（驾驶操作）
            </td>
          </tr>
        </tbody>
      </table>
      <span style="color:red;"> * 庆祝学校开业，实行最优惠方案供学员报名*</span>
    </div>
    </c:if>
    <!-- 田东 -->
    <c:if test="${orgId=='7d15f11b-2928-41e8-8406-8b49cf3939td' }"></c:if>
    <!-- 平果 -->
    <c:if test="${orgId=='606437a6-574c-4b0b-b79f-1a98bd4a45pg' }"></c:if>
    <!-- 德保 -->
    <c:if test="${orgId=='357c1d29-c991-4eed-a331-949f3eb4e9db' }"></c:if>
    <!--　靖西 -->
    <c:if test="${orgId=='6b698583-3aad-4702-8aa5-6e55b27df8jx' }"></c:if>
    <!-- 那坡 -->
    <c:if test="${orgId=='357c1d29-c991-4eed-a331-949f3eb4e9np' }"></c:if>
    <!-- 田林 -->
    <c:if test="${orgId=='357c1d29-c991-4eed-a331-949f3eb4e9tl' }"></c:if>
    <!-- 西林 -->
    <c:if test="${orgId=='357c1d29-c991-4eed-a331-949f3eb4e9xl' }"></c:if>
    <!-- 隆林 -->
    <c:if test="${orgId=='357c1d29-c991-4eed-a331-949f3eb4e9ll' }"></c:if>
    <!-- 凌云 -->
    <c:if test="${orgId=='357c1d29-c991-4eed-a881-949f3eb008ly' }"></c:if>
    <!-- 乐业 -->
    <c:if test="${orgId=='357c1d29-c991-4eed-a331-949f3eb888ly' }"></c:if>
    
    
  </div>

  <div style="height:50px;"></div>

  <div class="am-navbar am-navbar-default">
    <ul class="am-navbar-nav">
      <li class="am-btn am-btn-primary am-btn-xs" style="padding:0px;"><a href="${pageContext.request.contextPath}/m/articleAction!index.action?"> <span class="am-icon-home"></span> <span class="am-navbar-label">首页</span>
      </a></li>
      <li class="am-btn am-btn-primary am-btn-xs" style="padding:0px;"><a href="${pageContext.request.contextPath}/m/articleAction!notice.action?"> <span class="am-icon-star"></span> <span class="am-navbar-label">报名须知</span>
      </a></li>
      <li class="am-btn am-btn-primary am-btn-xs" style="padding:0px;"><a href="${pageContext.request.contextPath}/m/articleAction!student.action?"> <span class="am-icon-drupal"></span> <span class="am-navbar-label">学员</span>
      </a></li>
    </ul>
  </div>
</body>
</html>

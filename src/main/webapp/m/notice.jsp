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
<title>报名须知 - <%=schoolAreaName %></title>
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
h1 {
  font-size:2rem;
  margin:20px 0 0 0;
  padding:0px;
}

.info {
  font-size:1.5rem;
  letter-spacing:4px;
  padding-left:3rem;
}
</style>
</head>
<body>

  <div data-am-widget="slider" class="am-slider am-slider-b2" data-am-slider='{"controlNav":false}'>
    <ul class="am-slides" style="height:100px;">
      <li><img src="jx/ad3.jpg"></li>
      <li><img src="jx/ad4.jpg"></li>
      <li><img src="jx/ad1.jpg"></li>
      <li><img src="jx/ad2.jpg"></li>
    </ul>
  </div>

  <div class="am-panel am-panel-secondary">
    <div class="am-panel-hd">
      <span class="am-icon-star" style="margin-right:10px;"></span>报名须知
    </div>
    <div style="padding:10px;">
     
     <!-- 百色校区　 -->
     <c:if test="${orgId=='b78ffef2-7c54-40fe-be4b-1910a87c8bbs' }">
      <h1>一、培训方式：</h1>
      <span class="info">一人一车、预约计时</span>
      <h1>二、培训时间：</h1>
      <span class="info">每天早上8:00至晚上19:00(节假日培训另行通知)</span>
      <h1>三、培训流程：</h1>
      <span class="info">体检 ➩ 报名 ➩ 科目一考试(文科交规) ➩ 练车(五项) ➩ 科目二考试 ➩ 练车(道路实训) ➩ 科目三考试(路考) ➩ 安全文明考试(宣誓) ➩ 领证</span>
      <h1>四、其他代收费用：</h1>
      <span class="info">1、照相费20元 　2、保险费40元　3、路考场地费500元(交警场地收取）</span>
      <h1>温馨提示：</h1>
      <span class="info">1、体检机构：附属医院、右江区医院、百色市第二人民医院。</span><br/>
      <span class="info">2、驾驶技能准考证明有效期为三年，申请人未在有效期内完成考试的须重新报名考试。</span><br/>
      <span class="info">3、外地学员需持居住所在地派出所办理的暂住证。</span><br/>
      <h1>报名地址：</h1>
      <span class="info">百色市右江区城北一路29号(原汽车总站大院)</span>
         <h1>报名电话：</h1>
      <span class="info">(0776)2881356</span>
     </c:if>
     <!-- 田阳威龙　 -->
     <c:if test="${orgId=='c0510169-8edc-470e-b038-c299c7735bty' }">
      <h1>一、培训方式：</h1>
      <span class="info">一人一车、预约计时</span>
      <h1>二、培训时间：</h1>
      <span class="info">每天早上8:00至晚上22:00(节假日培训另行通知)</span>
      <h1>三、培训流程：</h1>
      <span class="info">体检 ➩ 报名 ➩ 科目一考试(文科交规) ➩ 练车(五项) ➩ 科目二考试 ➩ 练车(道路实训) ➩ 科目三考试(路考) ➩ 安全文明考试(宣誓) ➩ 领证</span>
      <h1>四、其他代收费用：</h1>
      <span class="info">1、照相费20元 2、计时卡150元　3、保险费40元　4、路考场地费500元(交警场地收取）5、远程网络练习费150元</span>
      <h1>温馨提示：</h1>
      <span class="info">1、在校大中专学生异地申请学习汽车驾驶须提供居住证、学生证。</span><br/>
      <span class="info">2、体检机构：田阳县人民医院。</span><br/>
      <span class="info">3、驾驶技能准考证明有效期为三年，申请人未在有效期内完成考试的须重新报名考试。</span><br/>
      <span class="info">4、外地学员需持居住所在地派出所办理的暂住证。</span><br/>
      <h1>报名地址：</h1>
      <span class="info">敢壮大道新汽车客运总站旁（新交警对面）</span>
         <h1>报名电话：</h1>
      <span class="info">(0776)3453699</span>
     </c:if>
   <!-- 田东 -->
    <c:if test="${orgId=='7d15f11b-2928-41e8-8406-8b49cf3939td' }">
    
    </c:if>
    <!-- 平果 -->
    <c:if test="${orgId=='606437a6-574c-4b0b-b79f-1a98bd4a45pg' }">
    
    </c:if>
    <!-- 德保 -->
    <c:if test="${orgId=='357c1d29-c991-4eed-a331-949f3eb4e9db' }">
    
    </c:if>
    <!--　靖西 -->
    <c:if test="${orgId=='6b698583-3aad-4702-8aa5-6e55b27df8jx' }">
    
    </c:if>
    <!-- 那坡 -->
    <c:if test="${orgId=='357c1d29-c991-4eed-a331-949f3eb4e9np' }">
    
    </c:if>
    <!-- 田林 -->
    <c:if test="${orgId=='357c1d29-c991-4eed-a331-949f3eb4e9tl' }">
    
    </c:if>
    <!-- 西林 -->
    <c:if test="${orgId=='357c1d29-c991-4eed-a331-949f3eb4e9xl' }">
    
    </c:if>
    <!-- 隆林 -->
    <c:if test="${orgId=='357c1d29-c991-4eed-a331-949f3eb4e9ll' }">
    
    </c:if>
    <!-- 凌云 -->
    <c:if test="${orgId=='357c1d29-c991-4eed-a881-949f3eb008ly' }">
    
    </c:if>
    <!-- 乐业 -->
    <c:if test="${orgId=='357c1d29-c991-4eed-a331-949f3eb888ly' }">
    
    </c:if>
    </div>
  </div>

  <div style="height:50px;"></div>

  <div class="am-navbar am-navbar-default">
    <ul class="am-navbar-nav">
      <li class="am-btn am-btn-primary am-btn-xs" style="padding:0px;"><a href="${pageContext.request.contextPath}/m/articleAction!index.action?"> <span class="am-icon-home"></span> <span class="am-navbar-label">首页</span>
      </a></li>
      <li class="am-btn am-btn-primary am-btn-xs" style="padding:0px;"><a href="${pageContext.request.contextPath}/m/articleAction!notice.action?" style="color:#c4e1ff;"> <span class="am-icon-star"></span> <span class="am-navbar-label">报名须知</span>
      </a></li>
      <li class="am-btn am-btn-primary am-btn-xs" style="padding:0px;"><a href="${pageContext.request.contextPath}/m/articleAction!student.action?"> <span class="am-icon-drupal"></span> <span class="am-navbar-label">学员</span>
      </a></li>
    </ul>
  </div>
</body>
</html>

<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="com.tuocheng.util.base.Util"%>
<%
  String openId = session.getAttribute("weixinOpenId") != null ? (String)session.getAttribute("weixinOpenId") : "";
  String code = Util.objToString(request.getParameter("code"), "");
  String state = Util.objToString(request.getParameter("state"), "");
  String schoolAreaName=Util.objToString(session.getAttribute("schoolAreaName"), "");
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
<title>学员服务 - <%=schoolAreaName %></title>
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

  <div class="am-g am-list-item-desced am-list-item-thumbed am-list-item-thumb-left" style="background-color:#c4e1ff;">
    <div class="am-u-sm-3 am-list-thumb" style="text-align: right;">
      <img class="am-circle" src="http://s.amazeui.org/media/i/demos/bw-2014-06-19.jpg?imageView/1/w/1000/h/1000/q/80" width="64" height="64"/>
    </div>
    <div class=" am-u-sm-9 am-list-main">
        <div class="am-list-item-hd" style="font-size: 2.0rem;margin-top:10px;">　${weixinUser}</div>
        <div class="am-list-item-text">学员信息：${studentName}<span style="float:right;color:#000;">活跃度：${visit}</span></div>
    </div>
  </div>

  <div style="margin:10px 10px 0px 10px;">
    <ul class="am-avg-sm-5 am-thumbnails" style="text-align: center;">
    <c:if test="${orgId!='c0510169-8edc-470e-b038-c299c7735bty' }">
      <li><a href="${pageContext.request.contextPath}/m/reservation.jsp" class="am-icon-btn am-secondary am-icon-automobile"></a><br /> 学车预约</li>
     </c:if> 
      <li><a href="${pageContext.request.contextPath}/m/ureservation.jsp" class="am-icon-btn am-success am-icon-recycle"></a><br /> 我的预约</li>
      <li><a href="${pageContext.request.contextPath}/m/assess.jsp" class="am-icon-btn am-warning am-icon-star-half-empty"></a><br /> 评价教练</li>
      <li><a href="${pageContext.request.contextPath}/m/difficulty.jsp" class="am-icon-btn am-danger am-icon-frown-o"></a><br /> 难点反馈</li>
      <li><a href="${pageContext.request.contextPath}/m/myevaluate.jsp" class="am-icon-btn am-danger am-icon-commenting-o"></a><br /> 我的点评</li>
    </ul>
  </div>
  
  <div>
    <img alt="..." src="http://www.gxccjt.com/Datafiles/Ads/7_Info/20150122111557_291.gif" height="60" width="100%" />
  </div>
  
  <div class="am-panel am-panel-secondary" style="margin-bottom:0px;">
    <div class="am-panel-hd">
      <span class="am-icon-hourglass-half" style="margin-right:10px;"></span>学习进度
    </div>
    <div class="am-form">
      <fieldset>
        <div class="am-form-group">
          <div class="am-g">
            
            <table class="am-table am-table-bordered am-table-striped am-table-hover am-table-centered">
              <thead>
              <tr>
                <th colspan="2" width="50%">类型</th>
                <th >学时</th>
              </tr>
              </thead>
              <tbody>
              <tr>
                <td colspan="2" >总学时</td>
                <td class="am-primary">${timerCard.totalHours}</td>
              </tr>
              <tr>
                <td colspan="2" >科目一</td>
                <td class="am-primary">${timerCard.oneHours}</td>
              </tr>
              <tr>
                <td rowspan="3" class="am-text-middle" width="25%">科目二</td>
                <td >理论</td>
                <td class="am-primary">${timerCard.twosTheoryHours}</td>
              </tr>
              <tr>
                <td >实操</td>
                <td class="am-primary">${timerCard.twosOperateHours}</td>
              </tr>
              <tr>
                <td >模拟</td>
                <td class="am-primary">${timerCard.twosSimulatorHours}</td>
              </tr>
              <tr>
                <td rowspan="3" class="am-text-middle">科目三</td>
                <td >理论</td>
                <td class="am-primary">${timerCard.threesTheoryHours}</td>
              </tr>
              <tr>
                <td >实操</td>
                <td class="am-primary">${timerCard.threesOperateHours}</td>
              </tr>
              <tr>
                <td >模拟</td>
                <td class="am-primary">${timerCard.threesSimulatorHours}</td>
              </tr>
              </tbody>
            </table>
          </div>
          <span class="am-form-caret"></span>
        </div>
    
      </fieldset>
    </div>
  </div>

  <div style="height:50px;"></div>

  <div class="am-navbar am-navbar-default">
    <ul class="am-navbar-nav">
      <li class="am-btn am-btn-primary am-btn-xs" style="padding:0px;"><a href="${pageContext.request.contextPath}/m/articleAction!index.action"> <span class="am-icon-home"></span> <span class="am-navbar-label">首页</span>
      </a></li>
      <li class="am-btn am-btn-primary am-btn-xs" style="padding:0px;"><a href="${pageContext.request.contextPath}/m/articleAction!notice.action"> <span class="am-icon-star"></span> <span class="am-navbar-label">报名须知</span>
      </a></li>
      <li class="am-btn am-btn-primary am-btn-xs" style="padding:0px;"><a href="${pageContext.request.contextPath}/m/articleAction!student.action" style="color:#c4e1ff;"> <span class="am-icon-drupal"></span> <span class="am-navbar-label">学员</span>
      </a></li>
    </ul>
  </div> 
</body>
</html>

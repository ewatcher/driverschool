<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page import="com.tuocheng.util.base.Util" %>
<%
  String openId = session.getAttribute("weixinOpenId") != null ? (String)session.getAttribute("weixinOpenId") : "";
  String code = Util.objToString(request.getParameter("code"), "");
  String state = Util.objToString(request.getParameter("state"), "");
  String orgId = Util.objToString(session.getAttribute("orgId"), "");
  long key = Util.getRandomLong();
  session.setAttribute("difficultyKey", key);
%>
<!doctype html>
<html class="no-js fixed-layout">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title><%=Util.objToString(session.getAttribute("schoolAreaName"), "") %></title>
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

<body style="font-size: 1.2rem;">

  <div style="margin:10px 10px 10px 10px;">
    <ul class="am-avg-sm-4 am-thumbnails" style="text-align: center;">
     <c:if test="${orgId!='c0510169-8edc-470e-b038-c299c7735bty' }">
      <li><a href="${pageContext.request.contextPath}/m/reservation.jsp" class="am-icon-btn am-secondary am-icon-automobile"></a><br /> 学车预约</li>
     </c:if>
      <li><a href="${pageContext.request.contextPath}/m/ureservation.jsp" class="am-icon-btn am-success am-icon-recycle"></a><br /> 我的预约</li>
      <li><a href="${pageContext.request.contextPath}/m/assess.jsp" class="am-icon-btn am-warning am-icon-star-half-empty"></a><br /> 评价教练</li>
      <li><a href="${pageContext.request.contextPath}/m/myevaluate.jsp" class="am-icon-btn am-danger am-icon-commenting-o"></a><br /> 我的点评</li>
    </ul>
  </div>
  
  <div class="am-panel am-panel-secondary" style="margin-bottom:0px;">
    <div class="am-panel-hd">
      <span class="am-icon-frown-o" style="margin-right:10px;"></span>难点反馈
    </div>

    <form class="am-form" action="${pageContext.request.contextPath}/guest/difficultyAction!add.action" method="post">
      <fieldset>
        <input type="hidden" name="key" value="<%=key%>" />
        <input type="hidden" id="openId" name="openId" value="<%=key%>" />
    
        <div class="am-form-group">
          <label for="doc-ta-1">难点：</label>
          <textarea class="" name="difficulty" id="difficulty" rows="8" id="doc-ta-1"></textarea>
        </div>
        
        <p>
          您在学习过程中碰到的难点，问题都可以向我们反馈。我们会努力改进期望做得更好！
        </p>
    
        <p><button type="submit" id="submit_difficulty_btn" class="am-btn am-btn-success am-btn-block am-btn-xl" onclick="return check()">提交</button></p>
      </fieldset>
    </form>
  
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
  
  <script type="text/javascript">
  	// 使用异步方式读取openId，读取成功后会存入session。
  	<%if(!Util.isNotNull(openId)){%>
  	var submitBtn = $('#submit_difficulty_btn');
    $(function (){
    	submitBtn.attr('disabled', true);
    	$.ajax({
			url : '${pageContext.request.contextPath}/api/weixinApiAction!getConfigByWebCode.action?code=<%=code%>',
			dataType : 'json',
			async: true,
			success : function(data) {
				//alert(JSON.stringify(data));
				submitBtn.removeAttr("disabled");
				//$('#openId').val(data.openid);
			},
			error : function(err){
				submitBtn.removeAttr("disabled");
				//alert(JSON.stringify(err));
			}
		});
    });
    <%}%>
    
    function check(){
  		var difficulty = $('#difficulty').val();
  		if(difficulty == undefined || difficulty == ''){
  			alert("您还没输入难点呢！");
  			return false;
  		} else {
  			return true;
  		}
  	}
  </script>

</body>
</html>
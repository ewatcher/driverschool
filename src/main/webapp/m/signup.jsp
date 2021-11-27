<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.tuocheng.util.base.Util"%>
<%
  long key = Util.getRandomLong();
  session.setAttribute("signupKey", key);
  String orgId = Util.objToString(session.getAttribute("orgId"), ""); 
  String schoolAreaName=Util.getSchoolAreaNameById(orgId);
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

  <form class="am-form" action="${pageContext.request.contextPath}/guest/signUpAction!add.action" method="post">
  <fieldset>
    <legend style="padding-top:20px;"><h1>在线报名</h1></legend>
    
    <input type="hidden" name="key" value="<%=key%>" />

    <div class="am-form-group">
      <label for="doc-ipt-email-1">联系方式：</label>
      <input type="text" name="upMobile" class="" id="upMobile" placeholder="输入您的手机号码">
    </div>

    <div class="am-form-group">
      <label for="doc-ipt-email-1">称呼：</label>
      <input type="text" name="upName" class="" id="upName" placeholder="输入您的称呼">
    </div>

    <div class="am-form-group">
      <label for="doc-select-1">所在地：</label>
      <select id="doc-select-1" name="upCity" id="upCity">
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
      <span class="am-form-caret"></span>
    </div>

    <p><button type="submit" id="submit_signup_btn" class="am-btn am-btn-success am-btn-block am-btn-xl" onclick="return check()">提交报名</button></p>
  </fieldset>
  </form>

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
  	function check(){
  		var upMobile = $('#upMobile').val();
  		var upName = $('#upName').val();
  		console.log(upMobile);
  		console.log(upName);
  		if(upMobile == undefined || upName == undefined || upMobile == '' || upName == ''){
  			alert("联系方式及称呼不能为空！");
  			return false;
  		} else {
  			return true;
  		}
  	}
  </script>

</body>
</html>
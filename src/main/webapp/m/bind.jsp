<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.tuocheng.util.base.Util"%>
<%
  long key = Util.getRandomLong();
  session.setAttribute("bindKey", key);
%>
<!doctype html>
<html class="no-js fixed-layout">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>驰程交通驾校</title>
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

  <form class="am-form" action="${pageContext.request.contextPath}/guest/studentAction!bindStudentToOpenId.action" method="post">
  <fieldset>
    <legend style="padding-top:20px;"><h1>绑定学员信息</h1></legend>
    
    <input type="hidden" name="key" value="<%=key%>" />
    
    <div class="am-form-group">
      <label for="doc-ipt-email-1">真实姓名：</label>
      <input type="text" name="name" class="" id="name" placeholder="输入您的真实姓名">
    </div>

    <div class="am-form-group">
      <label for="doc-ipt-email-1">学员编号：</label>
      <input type="text" name="studentNo" class="" id="studentNo" placeholder="输入您的身份证号">
    </div>

    <h4 style="color:#ccc;">绑定成功后将不会在弹出该页面！</h4>

    <p><button type="submit" id="submit_signup_btn" class="am-btn am-btn-success am-btn-block am-btn-xl" onclick="return check()">帮定</button></p>
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
  		var studentNo = $('#studentNo').val();
  		var name = $('#name').val();
  		if(studentNo == undefined || name == undefined || studentNo == '' || name == ''){
  			alert("姓名及学员编号不能为空！");
  			return false;
  		} else {
  			return true;
  		}
  	}
  </script>

</body>
</html>
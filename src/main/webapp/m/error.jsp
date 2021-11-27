<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.tuocheng.util.base.Util"%>
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

  <fieldset>
    <div style="text-align:center;margin-top:20px;" >
    	<h1 style="margin-bottom: 60px; color:red">错误</h1>
      <a href="##" class="am-icon-lg am-icon-btn am-danger am-icon-close " style="height:128px; width:128px; line-height:128px;"></a>
      <h2 style="margin-top: 50px;">操作失败</h2>
      <h3 style="margin-top: 10px; color:#ccc;"><%=request.getAttribute("message")%></h3>
      <button type="button" class="am-btn am-btn-success am-btn-block am-btn-xl" style="margin-top:20px;" onclick="window.location.href='${pageContext.request.contextPath}<%=request.getAttribute("redirect")%>'">返回重试</button>
    </div>
   </fieldset>

</body>
</html>
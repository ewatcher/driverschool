<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="driver,tc,driverschool">
<meta http-equiv="description" content="tc.driverschool">
<!-- jquery库 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-1.8.2.min.js" charset="utf-8"></script>
<!-- jquery Cookie插件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery.cookie.js" charset="utf-8"></script>
<!-- jquery print插件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery.jqprint-0.3.js" charset="utf-8"></script>

<!-- easyui相关库 -->
<link id="easyuiTheme" rel="stylesheet" href="${pageContext.request.contextPath}/jslib/jquery-easyui-1.5/themes/<c:out value="${cookie.easyuiThemeName.value}" default="default"/>/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/jslib/jquery-easyui-1.5/themes/icon.css" type="text/css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-easyui-1.5/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
<!-- easyui插件库 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/jslib/jquery-easyui-portal/portal.css" type="text/css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-easyui-portal/jquery.portal.js" charset="utf-8"></script>
<!-- ichart插件库 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/ichart.1.2.min.js" charset="utf-8"></script>
<!-- 自定义库 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/syCss.css" type="text/css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/nongfengUtil.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/util.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/validateUtil.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/JSutils.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/formatterUtil.js" charset="utf-8"></script>
<!-- xhEditor插件库 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/xheditor-1.1.14/xheditor-1.1.14-zh-cn.min.js" charset="utf-8"></script>


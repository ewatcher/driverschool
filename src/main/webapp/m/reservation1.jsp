<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page import="com.tuocheng.util.base.Util"%>
<%
  String openId = session.getAttribute("weixinOpenId") != null ? (String)session.getAttribute("weixinOpenId") : "";
  String code = Util.objToString(request.getParameter("code"), "");
  String state = Util.objToString(request.getParameter("state"), "");
  String schoolAreaName=Util.objToString(session.getAttribute("schoolAreaName"), "");
%>
<!doctype html>
<html class="no-js fixed-layout">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>
	<%=schoolAreaName %>
</title>
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
      <li><a href="${pageContext.request.contextPath}/m/ureservation.jsp?" class="am-icon-btn am-success am-icon-recycle"></a><br /> 我的预约</li>
      <li><a href="${pageContext.request.contextPath}/m/assess.jsp" class="am-icon-btn am-warning am-icon-star-half-empty"></a><br /> 评价教练</li>
      <li><a href="${pageContext.request.contextPath}/m/difficulty.jsp" class="am-icon-btn am-danger am-icon-frown-o"></a><br /> 难点反馈</li>
      <li><a href="${pageContext.request.contextPath}/m/myevaluate.jsp" class="am-icon-btn am-danger am-icon-commenting-o"></a><br /> 我的点评</li>
    </ul>
  </div>

<div class="am-panel am-panel-secondary" style="margin-bottom:0px;">
    <div class="am-panel-hd">
      <span class="am-icon-automobile" style="margin-right:10px;"></span>请选择预约选项
    </div>
    
    <div id="buttonList" style="text-align:center;">
      <button class="am-btn am-btn-success" style="width:90%;height:70px;margin-top:20px;font-size:3rem;" disabled onClick="window.location.href='reservation2.jsp?reservationType=3&code=<%=code%>&state=<%=state%>';">
        <i class="am-icon-lastfm" style="margin-right:10px;"></i>
      		  预约五项
      </button>
      
      <button class="am-btn am-btn-primary" style="width:90%;height:70px;margin-top:20px;font-size:3rem;" disabled onClick="window.location.href='reservation2.jsp?reservationType=4&code=<%=code%>&state=<%=state%>';">
        <i class="am-icon-automobile" style="margin-right:10px;"></i>
      		  预约路训
      </button>
      <c:if test="${orgId=='b78ffef2-7c54-40fe-be4b-1910a87c8bbs' }">
	      <button class="am-btn am-btn-warning" style="width:90%;height:70px;margin-top:20px;font-size:3rem;" disabled onClick="window.location.href='reservation2.jsp?reservationType=1&code=<%=code%>&state=<%=state%>';">
	        <i class="am-icon-ambulance" style="margin-right:10px;"></i>
	  			     预约电车
	      </button>
	      <button class="am-btn am-btn-secondary" style="width:90%;height:70px;margin-top:20px;font-size:3rem;" disabled onClick="window.location.href='reservation2.jsp?reservationType=2&code=<%=code%>&state=<%=state%>';">
	        <i class="am-icon-btn am-icon-taxi" style="margin-right:10px;"></i>
	    		    预约模拟
	      </button>
      </c:if>
    </div>
  </div>
  
  <div style="height:50px;"></div>

	<div class="am-modal am-modal-confirm" tabindex="-1" id="banding-confirm">
	  <div class="am-modal-dialog">
	    <div class="am-modal-hd">提示信息</div>
	    <div class="am-modal-bd">
	      	您确定要取消绑定吗？
	    </div>
	    <div class="am-modal-footer">
	      <span class="am-modal-btn" data-am-modal-cancel>取消</span>
	      <span class="am-modal-btn" data-am-modal-confirm>确定</span>
	    </div>
	  </div>
	</div>

  <div class="am-navbar am-navbar-default">
    <ul class="am-navbar-nav" id="low_navi">
      <li class="am-btn am-btn-primary am-btn-xs" style="padding:0px;"><a href="${pageContext.request.contextPath}/m/articleAction!index.action"> <span class="am-icon-home"></span> <span class="am-navbar-label">首页</span>
      </a></li>
      <li class="am-btn am-btn-primary am-btn-xs" style="padding:0px;"><a href="${pageContext.request.contextPath}/m/articleAction!notice.action"> <span class="am-icon-star"></span> <span class="am-navbar-label">报名须知</span>
      </a></li>
      <li class="am-btn am-btn-primary am-btn-xs" style="padding:0px;"><a href="${pageContext.request.contextPath}/m/articleAction!student.action" style="color:#c4e1ff;"> <span class="am-icon-drupal"></span> <span class="am-navbar-label">学员</span>
      </a></li>
       <li class="am-btn am-btn-primary am-btn-xs" style="padding:0px;" ><a href="#"><span class="am-icon-wechat"></span> <span class="am-navbar-label">取消绑定</span>
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
  
	//使用异步方式读取openId，读取成功后会存入session。
	<%if(!Util.isNotNull(openId)){%>
    $(function (){
    	$("#buttonList").find("button").attr('disabled', true);
    	$.ajax({
  			url : '${pageContext.request.contextPath}/api/weixinApiAction!getConfigByWebCode.action?code=<%=code%>',
  			dataType : 'json',
  			async: true,
  			success : function(data) {
  				//alert(JSON.stringify(data));
  				checkWeixin(); // 是否微信登录
  			},
  			error : function(err){
  				//alert(JSON.stringify(err));
  			}
  		});
    });
    <%} else {%>
    	checkWeixin(); // 是否微信登录
    <%}%>
    
    function checkWeixin(){
    	$("#buttonList").find("button").removeAttr("disabled");
    	cancelBind();
    }
    
    function cancelBind(){
    	$('#low_navi').find('.am-icon-wechat').add('#doc-confirm-toggle').
  	    one('click', function() {
  	      $('#banding-confirm').modal({
  	        relatedTarget: this,
  	        onConfirm: function(options) {
  	        	$.ajax({
  	    			url : '${pageContext.request.contextPath}/guest/studentAction!cancelUserBind.action',
  	    			type: 'POST',
  	    			dataType : 'json',
  	    			async: true,
  	    			success : function(data) {
  	    				if(data.code > 0){
  	    					alert('解除微信绑定！');
  	    				} 
  	    			},error : function(err){
  	    				alert('系统错误！请稍后再试！');
  	    			}
  	    		});
  	        },
  	        onCancel: function() {
  	        }
  	      });
  	    });
    }
  </script>

</body>
</html>
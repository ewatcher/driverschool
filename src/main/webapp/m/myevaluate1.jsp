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
      <li><a href="${pageContext.request.contextPath}/m/difficulty.jsp" class="am-icon-btn am-danger am-icon-frown-o"></a><br /> 难点反馈</li>
    </ul>
  </div>
  
  <div class="am-panel am-panel-secondary" style="margin-bottom:0px;">
    <div class="am-panel-hd">
      <span class="am-icon-commenting-o" style="margin-right:10px;"></span>教练对您的点评
    </div>

    <fieldset>
    
      <ul class="am-comments-list am-comments-list-flip" id="comments-list">
  	  
      </ul>
      
      <div class="am-alert am-alert-warning" id="comments-null" data-am-alert style="margin-top:30px;display:none;">
        暂无教练对您的评价！
      </div>
  
      <span id="loading" style="padding-left:20px;text-align: center"><i class="am-icon-spinner am-icon-spin" ></i> 正在读取教练对您的评价...</span>
      
    </fieldset>
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
  
  	$('#loading').hide();
	
	//使用异步方式读取openId，读取成功后会存入session。
	<%if(!Util.isNotNull(openId)){%>
    $(function (){
    	$.ajax({
  			url : '${pageContext.request.contextPath}/api/weixinApiAction!getConfigByWebCode.action?code=<%=code%>',
  			dataType : 'json',
  			async: true,
  			success : function(data) {
  				//alert(JSON.stringify(data));
  				checkUserBind(); // 验证学员是否已经绑定微信
  			},
  			error : function(err){
  				//alert(JSON.stringify(err));
  			}
  		});
    });
    <%} else {%>
    	checkUserBind(); // 验证学员是否已经绑定微信
    <%}%>
    
    function checkUserBind(){
    	$.ajax({
  			url : '${pageContext.request.contextPath}/guest/studentAction!checkUserBind.action',
  			dataType : 'json',
  			async: true,
  			success : function(data) {
  				//alert(JSON.stringify(data));
  				if(data.code > 0){
  					// 该用户已经绑定
  					bindOkEvent(); // 
  				} else if(data.code == -2){
  					// 该用户未绑定
  					alert('您未绑定学员信息，无法查询到您的评价！');
  				} else if(data.code == -1){
  					alert('未通过微信授权，无法进行评价操作！');
  				}
  			},
  			error : function(err){
  				alert('微信授权验证失败，无法进行评价操作！');
  			}
  		});
    }
    
 	// 如果验证，绑定都成功的话执行
    function bindOkEvent(){
    	getCommentList();
    }
 	
 	function getCommentList(){
 		$('#loading').show();
    	$.ajax({
  			url : '${pageContext.request.contextPath}/guest/commentStudentAction!getCommentByStudentList.action',
  			dataType : 'json',
  			async: true,
  			success : function(data) {
  				//alert(JSON.stringify(data));
  				$('#loading').hide();
  				if(data.code > 0){
  					$('#comments-list').children('li').remove();
  					if(data.rows == undefined || data.rows.length == 0){
  						$('#comments-null').show();
  					} else {
  						for(var i = 0; i < data.rows.length; i++){
  	    					var row = data.rows[i];
  	    					var nodeLi = getMessageLi(row);
  	    					$('#comments-list').prepend(nodeLi); // 在前面添加
  	    				}
  					}
  				} else if(data.code == -2){
  					alert('因未绑定学员信息，无法检索预约记录！');
  				} else if(data.code == -1){
  					alert('未通过微信授权，无法进行预约操作！');
  				}
  			},
  			error : function(err){
  				$('#loading').hide();
  				alert('微信授权验证失败，无法进行预约操作！');
  			}
  		});
 	}
 	
 	
 	function getMessageLi(row){
    	var nodeLi = $('<li class="am-comment am-comment-highlight"></li>'); 
		var nodeImg = $('<img src="../images/icon.jpg" class="am-comment-avatar" width="48" height="48" >');
		var nodeDiv1 = $('<div class="am-comment-main">');
		var nodeHeader = $('<header class="am-comment-hd">');
		var nodeDiv2 = $('<div class="am-comment-meta">');
		var nodeSpan = $('<span class="am-comment-author">');
		var nodeTime = $('<time datetime="2013-07-27T04:54:29-07:00" >');
		var nodeDiv3 = $('<div class="am-comment-bd">');
		var nodeP = $('<p>');
		
		nodeP.text(row.comments);
		nodeTime.text(" 点评于：" + row.commentTime);
		nodeSpan.text("教练:" + row.trainName);
		
		nodeDiv3.append(nodeP);
		nodeDiv2.append(nodeSpan);
		nodeDiv2.append(nodeTime);
		nodeHeader.append(nodeDiv2);
		nodeDiv1.append(nodeHeader);
		nodeDiv1.append(nodeDiv3);
		nodeLi.append(nodeImg);
		nodeLi.append(nodeDiv1);
		
		return nodeLi;
	}
    
  </script>

</body>
</html>
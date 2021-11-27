<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="com.tuocheng.util.base.Util"%>
<%
  String openId = session.getAttribute("weixinOpenId") != null ? (String)session.getAttribute("weixinOpenId") : "";
  String code = Util.objToString(request.getParameter("code"), "");
  String state = Util.objToString(request.getParameter("state"), "");
  String orgId = Util.objToString(session.getAttribute("orgId"), "");
  long key = Util.getRandomLong();
  session.setAttribute("assessKey", key);
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

  <div class="am-modal am-modal-prompt" tabindex="-1" id="login-prompt">
    <div class="am-modal-dialog">
      <div class="am-modal-hd" style="margin-bottom:20px;">用户绑定</div>
      <div class="am-modal-bd">
        首次使用请绑定学员信息
        <input type="text" name="studentName" id="studentName" class="am-modal-prompt-input"  style="margin-top:10px;" placeholder="输入您的真实姓名" />
        <input type="text" name="identityId" id="identityId" class="am-modal-prompt-input" style="margin-bottom:10px;" placeholder="输入您的身份证号" />
      </div>
      <div class="am-modal-footer">
        <span class="am-modal-btn" data-am-modal-cancel>取消</span>
        <span class="am-modal-btn" onclick="bindStudent();">绑定</span>
      </div>
    </div>
  </div>
  
  <div style="margin:10px 10px 10px 10px;">
    <ul class="am-avg-sm-4 am-thumbnails" style="text-align: center;">
    <c:if test="${orgId!='c0510169-8edc-470e-b038-c299c7735bty' }">
      <li><a href="${pageContext.request.contextPath}/m/reservation.jsp" class="am-icon-btn am-secondary am-icon-automobile"></a><br /> 学车预约</li>
     </c:if>
      <li><a href="${pageContext.request.contextPath}/m/ureservation.jsp" class="am-icon-btn am-success am-icon-recycle"></a><br /> 我的预约</li>
      <li><a href="${pageContext.request.contextPath}/m/difficulty.jsp" class="am-icon-btn am-danger am-icon-frown-o"></a><br /> 难点反馈</li>
      <li><a href="${pageContext.request.contextPath}/m/myevaluate.jsp" class="am-icon-btn am-danger am-icon-commenting-o"></a><br /> 我的点评</li>
    </ul>
  </div>
  
  <div class="am-panel am-panel-secondary" style="margin-bottom:0px;">
    <div class="am-panel-hd">
      <span class="am-icon-star-half-empty" style="margin-right:10px;"></span>评价业务员
    </div>
    
    <form class="am-form" action="${pageContext.request.contextPath}/guest/salesmanAction!add.action" method="post">
      	<input type="hidden" name="personName" id="salesman_personName"></input>
      <fieldset>
        <input type="hidden" name="key" value="<%=key%>" />
        
        <div class="am-form-group">
          <label for="doc-select-1">业务员：</label>
          <select name="personId" id="person">
            <option value="0"></option>
          </select>
          <span class="am-form-caret"></span>
        </div>
         <span class="am-icon-star-half-empty" style="margin-right:10px;font-size:20px"></span>业务员评分
        <div class="am-form-group">
        <label class="am-radio-inline"  >
            <input type="radio"  id="stars1"  value="1" name="starsTemp"> 优秀
          </label>
          <label class="am-radio-inline"  >
            <input type="radio"  id="stars2" value="2" name="starsTemp"> 良好
          </label>
          <label class="am-radio-inline">
            <input type="radio" id="stars3" value="3" name="starsTemp"> 一般
          </label>
          <label class="am-radio-inline">
            <input type="radio" id="stars4" value="4" name="starsTemp"> 差
          </label>
          <label class="am-radio-inline">
            <input type="radio" id="stars5" value="5" name="starsTemp"> 极差
          </label>
        </div>
        <select id="starsValue" name="stars"  data-am-select>
		</select>
        
        <div class="am-form-group">
          <label for="doc-ta-1">评价内容：</label>
          <textarea class="" name="evaluate" id="evaluate" rows="6" id="doc-ta-1"></textarea>
        </div>
        
        <p>
          客观、坦率的意见是我们前进的最大动力，对您的配合和支持表示衷心感谢！
        </p>
    
        <p><button type="submit" id="submit_assess_btn" class="am-btn am-btn-success am-btn-block am-btn-xl" onclick="return check()">提交评价</button></p>
      </fieldset>
    </form>
    
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
  
    var submitBtn = $('#submit_assess_btn');
    
	//使用异步方式读取openId，读取成功后会存入session。
	<%if(!Util.isNotNull(openId)){%>
	window.setTimeout(function() {
    	$('#analysDate').attr('readonly',true);
    	submitBtn.attr('disabled', true);
    	$.ajax({
  			url : '${pageContext.request.contextPath}/api/weixinApiAction!getConfigByWebCode.action?code=<%=code%>',
  			dataType : 'json',
  			async: true,
  			success : function(data) {
  				checkUserBind(); // 验证学员是否已经绑定微信
  			},
  			error : function(err){
  				//alert(JSON.stringify(err));
  			}
  		});
	}, 0);
   
    <%} else {%>
    checkUserBind(); // 验证学员是否已经绑定微信
    <%}%>
    
    
    function checkUserBind(){
    	submitBtn.attr('disabled', true);
    	$.ajax({
  			url : '${pageContext.request.contextPath}/guest/studentAction!checkUserBind.action',
  			dataType : 'json',
  			async: true,
  			success : function(data) {
  				//alert(JSON.stringify(data));
  				if(data.code > 0){
  					// 该用户已经绑定
  					bindOkEvent(); // 获取业务员信息
  				} else if(data.code == 3){
  					// 该用户未绑定
  					$('#login-prompt').modal({
  						width: document.body.clientWidth * 0.8
  				    });
  				}else if(data.code == -2){
  					// 该用户未绑定
  					$('#login-prompt').modal({
  						width: document.body.clientWidth * 0.8
  				    });
  				} else if(data.code == -1){
  					alert('未通过微信授权，无法进行评价操作！');
  				}
  			},
  			error : function(err){
  				submitBtn.attr('disabled', true);
  				alert('微信授权验证失败，无法进行评价操作！');
  			}
  		});
    }
    
    // 提交绑定信息，绑定
    function bindStudent(){
    	var user = {};
      	user.name = $('#studentName').val();
      	user.identityId = $('#identityId').val();
      	
      	//alert(JSON.stringify(user));
      	$.ajax({
  			url : '${pageContext.request.contextPath}/guest/studentAction!bindStudentToOpenId2.action',
  			data: user,
  			type: 'POST',
  			dataType : 'json',
  			async: true,
  			success : function(data) {
  				if(data.code > 0){
  					alert('绑定成功！');
  					
  					bindOkEvent(); // 获取业务员信息
  				} else {
  					alert(data.msg);
  					$('#login-prompt').modal({
  						width: document.body.clientWidth * 0.8
  				    });
  				}
  			},error : function(err){
  				alert('你已经解除微信绑定，请重新绑定！！');
  				$('#login-prompt').modal({
					width: document.body.clientWidth * 0.8
			    });
  			}
  		});
    }
    
    // 如果验证，绑定都成功的话执行
    function bindOkEvent(){
    	submitBtn.removeAttr("disabled");
    	getTrainerList();
    	cancelBind();
    }
    
    // 获取业务员信息
    function getTrainerList(){
    	var $selected = $('#person');
    	$.ajax({
  			url : '${pageContext.request.contextPath}/guest/studentAction!getPersonByStudentId.action',
  			dataType : 'json',
  			async: true,
  			success : function(data) {
  				//alert(JSON.stringify(data));
  				for(var i = 0; i < data.length; i++){
  					//$selected.empty(); 
  					$selected.append('<option value="' + data[i].value +'">' + data[i].text + '</option>');
  				}
  			},
  			error : function(err){
  				console.log(err);
  			}
  		});
    }
  
  	function check(){
  		var trainer = $('#person  option:selected').val();
  		var stars = $("#starsValue").val();
  		var evaluate = $('#evaluate').val();
  		var trainerName=$('#salesman_personName');
      	trainerName.val($('#person  option:selected').text());
  		if(trainer == undefined || trainer == ''){
  			alert("请选择评价的业务员！");
  			return false;
  		} else if(stars == undefined || stars == ''){
  			alert("请选择您对业务员的简评！");
  			return false;
  		} else if(evaluate == undefined || evaluate == ''){
  			alert("请输入您对业务员的评价！");
  			return false;
  		} else {
  			return true;
  		}
  	}
  	$(document).ready(function(){
 		$("#stars1").change(function(){
 			$("#starsValue").empty(); 
 			for(var i = 100; i >90; i--){
  					$("#starsValue").append('<option value="' + i +'">' + i+ '</option>');
  				}
 		});
 		$("#stars2").change(function(){
 			$("#starsValue").empty(); 
 			for(var i = 90; i >80; i--){
  					$("#starsValue").append('<option value="' + i +'">' + i+ '</option>');
  				}
 		});
 		$("#stars3").change(function(){
 			$("#starsValue").empty(); 
 			for(var i = 80; i >70; i--){
  					$("#starsValue").append('<option value="' + i +'">' + i+ '</option>');
  				}
 		});
 		$("#stars4").change(function(){
 			$("#starsValue").empty(); 
 			for(var i = 70; i >60; i--){
  					$("#starsValue").append('<option value="' + i +'">' + i+ '</option>');
  				}
 		});
 		$("#stars5").change(function(){
 			$("#starsValue").empty(); 
 			for(var i = 60; i >50; i--){
  					$("#starsValue").append('<option value="' + i +'">' + i+ '</option>');
  				}
 		});
	})
	
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
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="com.tuocheng.util.base.Util"%>
<%
	String openId = session.getAttribute("weixinOpenId") != null ? (String)session.getAttribute("weixinOpenId") : "";
	String code = Util.objToString(request.getParameter("code"), "");
	String state = Util.objToString(request.getParameter("state"), "");
	String orgId = Util.objToString(session.getAttribute("orgId"), "");
	String schoolAreaName=Util.objToString(session.getAttribute("schoolAreaName"), "");
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
    <ul class="am-avg-sm-4 am-thumbnails" style="text-align: center;" >
    <c:if test="${orgId!='c0510169-8edc-470e-b038-c299c7735bty' }">
      <li><a href="${pageContext.request.contextPath}/m/reservation.jsp" class="am-icon-btn am-secondary am-icon-automobile"></a><br /> 学车预约</li>
    </c:if>
      <li><a href="${pageContext.request.contextPath}/m/assess.jsp" class="am-icon-btn am-warning am-icon-star-half-empty"></a><br /> 评价教练</li>
      <li><a href="${pageContext.request.contextPath}/m/difficulty.jsp" class="am-icon-btn am-danger am-icon-frown-o"></a><br /> 难点反馈</li>
      <li><a href="${pageContext.request.contextPath}/m/myevaluate.jsp" class="am-icon-btn am-danger am-icon-commenting-o"></a><br /> 我的点评</li>
    </ul>
  </div>
  
  <div class="am-panel am-panel-secondary" style="margin-bottom:0px;">
    <div class="am-panel-hd">
      <span class="am-icon-recycle" style="margin-right:10px;"></span>取消预约
    </div>

    <fieldset>
      <table class="am-table am-table-striped" id="ureservationTable">
        <thead>
          <tr>
            <th>时间</th>
            <th>时段</th>
            <th>状态</th>
            <th>功能</th>
          </tr>
        </thead>
      
        <tbody id="ureservationTbody">
          <tr>
            <td>--</td>
            <td>--</td>
            <td>--</td>
            <td>--</td>
          </tr>
        </tbody>
      </table>
  
      <span id="loading" style="padding-left:20px;text-align: center"><i class="am-icon-spinner am-icon-spin" ></i> 读取预约信息中...</span>
      
      <p style="padding-top:30px;">
        以上仅列出可取消的预约信息！如不能按时到场学习，请尽快取消预约。
      </p>
      
    </fieldset>
    
  </div>
  
  <div class="am-modal am-modal-confirm" tabindex="-1" id="my-confirm">
    <div class="am-modal-dialog">
      <div class="am-modal-hd">提示</div>
      <div class="am-modal-bd">
        您确定要取消该预约吗？
      </div>
      <div class="am-modal-footer">
        <span class="am-modal-btn" data-am-modal-cancel>取消</span>
        <span class="am-modal-btn" data-am-modal-confirm>确定</span>
      </div>
    </div>
  </div>
	
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
  
  <div style="height:50px;"></div>

  <div class="am-navbar am-navbar-default">
    <ul class="am-navbar-nav" id="low_navi">
      <li class="am-btn am-btn-primary am-btn-xs" style="padding:0px;"><a href="${pageContext.request.contextPath}/m/articleAction!index.action"> <span class="am-icon-home"></span> <span class="am-navbar-label">首页</span>
      </a></li>
      <li class="am-btn am-btn-primary am-btn-xs" style="padding:0px;"><a href="${pageContext.request.contextPath}/m/articleAction!notice.action"> <span class="am-icon-star"></span> <span class="am-navbar-label">报名须知</span>
      </a></li>
      <li class="am-btn am-btn-primary am-btn-xs" style="padding:0px;"><a href="${pageContext.request.contextPath}/m/articleAction!student.action" style="color:#c4e1ff;"> <span class="am-icon-drupal"></span> <span class="am-navbar-label">学员</span>
      </a></li>
      <li class="am-btn am-btn-primary am-btn-xs" style="padding:0px;" ><a href="#"><span class="am-icon-wechat"></span> <span  class="am-navbar-label">取消绑定</span>
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
	window.setTimeout(function() {
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
	}, 0);
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
  				if(data.code == 1){
  					// 该用户已经绑定
  					bindOkEvent(); // 验证学员是否已经绑定微信
  				} else if(data.code ==2){
  					alert('驾校没有选配教练给您，请与驾校联系！');
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
  					
  					bindOkEvent(); // 获取教练信息
  				} else {
  					alert(data.msg);
  					$('#login-prompt').modal({
  						width: document.body.clientWidth * 0.8
  				    });
  				}
  			},error : function(err){
  				alert('系统错误！请稍后再试！');
  				$('#login-prompt').modal({
					width: document.body.clientWidth * 0.8
			    });
  			}
  		});
    }
    
    // 如果验证，绑定都成功的话执行
    function bindOkEvent(){
    	getReservationData();
    	cancelBind();
    }
    
    function getReservationData(){
    	$('#loading').show();
    	$.ajax({
  			url : '${pageContext.request.contextPath}/guest/reservationAction!getReservationList.action',
  			dataType : 'json',
  			async: true,
  			success : function(data) {
  				//alert(JSON.stringify(data));
  				$('#loading').hide();
  				if(data.code > 0){
  					// 该用户已经绑定过微信号
  					$('#ureservationTbody tr').empty();
  					var rows = data.rows;
  					if(rows.length > 0){
  						for(var i = 0; i < rows.length; i++){
  							var row = rows[i];
  							
  							var state = "";
  							if(row.state == 1){
  								state = '预约中...';
  							}else if(row.state == 2){
  								state = '正常';
  							}else if(row.state == 3){
  								state = '完成';
  							}else if(row.state == 4){
  								state = '取消';
  							}else if(row.state == 5){
  								state = '挂起';
  							}
  							
  	  						var tr = '<tr><td>' + row.date + '</td><td>'+ row.timeStart + ' - ' + row.timeEnd +'</td><td>'+ state +'</td><td><span class="am-icon-close am-icon-sm" style="color:red" data-id="' + row.id + '"></span></td></tr>';
  	  						$('#ureservationTbody tr:last').after(tr);
  						}
  						
  						addClickEvn();
  					} else {
  						alert('您暂无可取消的预约信息！');
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
    
    function addClickEvn() {
  	  $('#ureservationTable').find('.am-icon-close').add('#doc-confirm-toggle').
  	    one('click', function() {
  	      $('#my-confirm').modal({
  	        relatedTarget: this,
  	        onConfirm: function(options) {
  	          var deleteId = $(this.relatedTarget).data('id');
    	        $.ajax({
    	  			url : '${pageContext.request.contextPath}/guest/reservationAction!delete.action?id=' + deleteId,
    	  			dataType : 'json',
    	  			async: true,
    	  			success : function(data) {
    	  				if(data.code > 0){
    	  					getReservationData();
    	  					alert(data.msg);
    	  				} else {
    	  					alert(data.msg);
    	  				}
    	  			},
    	  			error : function(err){
    	  				alert('系统错误，取消失败！');
    	  			}
    	  		});
  	        },
  	        onCancel: function() {
  	        }
  	      });
  	    });
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
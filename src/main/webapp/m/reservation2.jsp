<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="com.tuocheng.util.base.Util"%>
<%

  // 测试用,正式需要删除 
  //session.setAttribute("weixinOpenId", "ovaZhwam1u0wcHIBLXeLD6gNIdog");

  String openId = session.getAttribute("weixinOpenId") != null ? (String)session.getAttribute("weixinOpenId") : "";
  String code = Util.objToString(request.getParameter("code"), "");
  String state = Util.objToString(request.getParameter("state"), "");
  int reservationType = Util.objToInt(request.getParameter("reservationType"), 3);
  String schoolAreaName=Util.objToString(session.getAttribute("schoolAreaName"), "");
  String orgId = Util.objToString(session.getAttribute("orgId"), "");
  long key = Util.getRandomLong();
  session.setAttribute("reservationKey", key);
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
        <input type="text" name="verificationCode" id="verificationCode" class="am-modal-prompt-input" style="margin-bottom:10px;" placeholder="输入您的预约验证码" />
      </div>
      <div class="am-modal-footer">
        <span class="am-modal-btn" data-am-modal-cancel>取消</span>
        <span class="am-modal-btn" onclick="bindStudent();">绑定</span>
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
  
  <div style="margin:10px 10px 10px 10px;">
    <ul class="am-avg-sm-4 am-thumbnails" style="text-align: center;">
      <li><a href="${pageContext.request.contextPath}/m/ureservation.jsp?code=<%=code%>&state=<%=state%>'" class="am-icon-btn am-success am-icon-recycle"></a><br /> 我的预约</li>
      <li><a href="${pageContext.request.contextPath}/m/assess.jsp" class="am-icon-btn am-warning am-icon-star-half-empty"></a><br /> 评价教练</li>
      <li><a href="${pageContext.request.contextPath}/m/difficulty.jsp" class="am-icon-btn am-danger am-icon-frown-o"></a><br /> 难点反馈</li>
      <li><a href="${pageContext.request.contextPath}/m/myevaluate.jsp" class="am-icon-btn am-danger am-icon-commenting-o"></a><br /> 我的点评</li>
    </ul>
  </div>

  <div class="am-panel am-panel-secondary" style="margin-bottom:0px;">
    <div class="am-panel-hd">
      <span class="am-icon-automobile" style="margin-right:10px;">学习预约</span>
      <span class="am-icon-automobile" style="margin-left:50px;"><b id="select_reservationType"></b></span>
    </div>
    <div class="am-form">
      <fieldset>
        <input type="hidden" name="key" value="<%=key%>" />
        
        <div class="am-form-group">
          <label for="doc-select-1">预约教练：</label>
          <select name="trainerId" id="trainerId">
          </select>
          <span class="am-form-caret"></span>
        </div>
        
        <div class="am-form-group">
          <label for="doc-select-1">预约时间：</label>
          <select name="resvdate" id="resvdate" onchange="changeSelectDate();">
          </select>
          <span class="am-form-caret"></span>
        </div>
        
        <div class="am-form-group">
          <label for="doc-select-1">教练员：</label><b id="trainerName"></b> <label for="doc-select-1" style="margin-left:30px;">预约日期：</label><b id="reservationDate"></b>
          <div class="am-g">
            
            <table class="am-table am-table-bordered am-table-striped am-table-hover">
              <thead>
              <tr>
                <th width="40%">时间段</th>
                <th width="60%">状态</th>
              </tr>
              </thead>
              <tbody>
               <tr>
                <td>07:00 - 08:00</td>
                <td id="item7Studentid" class="am-danger"></td>
              </tr>
              <tr>
                <td>08:00 - 09:00</td>
                <td id="item8Studentid" class="am-danger"></td>
              </tr>
              <tr>
                <td>09:00 - 10:00</td>
                <td id="item9Studentid" class="am-danger"></td>
              </tr>
              <tr>
                <td>10:00 - 11:00</td>
                <td id="item10Studentid" class="am-danger"></td>
              </tr>
              <tr>
                <td>11:00 - 12:00</td>
                <td id="item11Studentid" class="am-danger"></td>
              </tr>
              <tr>
                <td>12:00 - 13:00</td>
                <td id="item12Studentid" class="am-danger"></td>
              </tr>
              <tr>
                <td>13:00 - 14:00</td>
                <td id="item13Studentid" class="am-danger"></td>
              </tr>
              <tr>
                <td>14:00 - 15:00</td>
                <td id="item14Studentid" class="am-danger"></td>
              </tr>
              <tr>
                <td>15:00 - 16:00</td>
                <td id="item15Studentid" class="am-danger"></td>
              </tr>
              <tr>
                <td>16:00 - 17:00</td>
                <td id="item16Studentid" class="am-danger"></td>
              </tr>
              <tr>
                <td>17:00 - 18:00</td>
                <td id="item17Studentid" class="am-danger"></td>
              </tr>
              <tr>
                <td>18:00 - 19:00</td>
                <td id="item18Studentid" class="am-danger"></td>
              </tr>
              <c:if test="${orgId=='c0510169-8edc-470e-b038-c299c7735bty' }">
              <tr>
                <td>19:00 - 20:00</td>
                <td id="item19Studentid" class="am-danger"></td>
              </tr>
              <tr>
                <td>20:00 - 21:00</td>
                <td id="item20Studentid" class="am-danger"></td>
              </tr>
              <tr>
                <td>21:00 - 22:00</td>
                <td id="item21Studentid" class="am-danger"></td>
              </tr>
              </c:if>
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
	
    var reservationType = <%=reservationType%>;
	var submitBtn = $('#submit_reservation_btn');
	var resData = {};
	
	//使用异步方式读取openId，读取成功后会存入session。
	<%if(!Util.isNotNull(openId)){%>
	window.setTimeout(function() {
		submitBtn.attr('disabled', true);
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
    	submitBtn.attr('disabled', true);
    	$("#select_reservationType").empty();
    	$("#select_reservationType").text("您当前预约类型:"+getWorkingType(reservationType));
    	$.ajax({
  			url : '${pageContext.request.contextPath}/guest/studentAction!checkUserBind.action',
  			dataType : 'json',
  			async: true,
  			success : function(data) {
  				//alert(JSON.stringify(data));
  				if(data.code == 1){
  					// 该用户已经绑定
  					bindOkEvent(data.studentId,data.trainerId,data.trainerName); // 获取教练信息
  				} else if(data.code ==2){
  					alert('驾校没有选配教练给您，请与驾校联系！');
  				}else if(data.code == 3){
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
      	user.verificationCode = $('#verificationCode').val();
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
  				alert('你已经解除微信绑定，请重新绑定！');
  				$('#login-prompt').modal({
					width: document.body.clientWidth * 0.8
			    });
  			}
  		});
    }
    
    // 如果验证，绑定都成功的话执行
    function bindOkEvent(studentId,trainerId,trainerName){
    	submitBtn.removeAttr("disabled");
    	$("#trainerId").attr("disabled","disabled");
    	$("#resvdate").attr("disabled","disabled");
    	
    	loadTrainerSelect(trainerId,trainerName); // 初始化教练下拉列表
    	getReservationList(trainerId, trainerName);
    	
    }
    
 	// 获取教练排班信息
    function getReservationList(trainerId, trainerName){
    	$("#resvdate").attr("disabled","disabled");
    	$.ajax({
    		url : '${pageContext.request.contextPath}/guest/reservationAction!getDetailReservationByTrainerId.action?trainerId=' + trainerId,
  			data:{reservationType:reservationType},
    		dataType : 'json',
  			async: true,
  			success : function(data) {
  				cancelBind();
  				if(data.code == 1){
  					resData = data;
  					if(reservationType==1||reservationType==2){
  						resData.trainerName = data.trainerName;
  					   //初始化显示教练名称
  						loadTrainerSelect(data.trainerId,data.trainerName);
  					}else{
  						resData.trainerName = trainerName;
  					}
  					
  					loadDateSelect();
  					refreshDate(resData.dateSelect[0].date,data.trainerId,data.trainerName);
  				} else {
  					alert(data.msg);
  				}
  			},
  			error : function(err){
  				console.log(err);
  			}
  		});
    }
    
    function loadTrainerSelect(trainerId,trainerName){
    	$('#trainerId').empty();
    	$('#trainerId').append("<option value='"+ trainerId + "'>" + trainerName + "</option>");
    	$("#trainerId").removeAttr("disabled");
    }
    
    function loadDateSelect(){
    	$('#resvdate').empty();
    	for(var i = 0; i < resData.dateSelect.length; i++){
    		var item = resData.dateSelect[i];
    		$('#resvdate').append("<option value='"+ item.date + "'>" + item.key + "</option>");
    	}
    	$("#resvdate").removeAttr("disabled");
    }
    
    // 日期选择发生变化
    function changeSelectDate(){
    	var date = $('#resvdate').children('option:selected').val(); //这就是selected的值
    	refreshDate(date,trainerId,trainerName);
    }
    
    // 加载预约数据
    function refreshDate(date,trainerId,trainerName){
    	//console.log("选择的日期:" + date);
    	$('#trainerName').text(resData.trainerName);
    	$('#reservationDate').text(date.substring(0, 10));
    	
    	// 先把数据清零
    	setRefDate(date, 'NO', "#item7Studentid");
    	setRefDate(date, 'NO', "#item8Studentid");
		setRefDate(date, 'NO', "#item9Studentid");
		setRefDate(date, 'NO', "#item10Studentid");
		setRefDate(date, 'NO', "#item11Studentid");
		setRefDate(date, 'NO', "#item12Studentid");
		setRefDate(date, 'NO', "#item13Studentid");
		setRefDate(date, 'NO', "#item14Studentid");
		setRefDate(date, 'NO', "#item15Studentid");
		setRefDate(date, 'NO', "#item16Studentid");
		setRefDate(date, 'NO', "#item17Studentid");
		setRefDate(date, 'NO', "#item18Studentid");
		setRefDate(date, 'NO', "#item19Studentid");
		setRefDate(date, 'NO', "#item20Studentid");
		setRefDate(date, 'NO', "#item21Studentid");
    	
    	for(var i = 0; i < resData.retData.length; i++){
    		var item = resData.retData[i];
    		if(item.reservationDate == date){
    			setRefDate(date, item.item7Studentid, "#item7Studentid", item, item.item7TrainContent,trainerId,trainerName);
    			setRefDate(date, item.item8Studentid, "#item8Studentid", item, item.item8TrainContent,trainerId,trainerName);
    			setRefDate(date, item.item9Studentid, "#item9Studentid", item, item.item9TrainContent,trainerId,trainerName);
    			setRefDate(date, item.item10Studentid, "#item10Studentid", item, item.item10TrainContent,trainerId,trainerName);
    			setRefDate(date, item.item11Studentid, "#item11Studentid", item, item.item11TrainContent,trainerId,trainerName);
    			setRefDate(date, item.item12Studentid, "#item12Studentid", item, item.item12TrainContent,trainerId,trainerName);
    			setRefDate(date, item.item13Studentid, "#item13Studentid", item, item.item13TrainContent,trainerId,trainerName);
    			setRefDate(date, item.item14Studentid, "#item14Studentid", item, item.item14TrainContent,trainerId,trainerName);
    			setRefDate(date, item.item15Studentid, "#item15Studentid", item, item.item15TrainContent,trainerId,trainerName);
    			setRefDate(date, item.item16Studentid, "#item16Studentid", item, item.item16TrainContent,trainerId,trainerName);
    			setRefDate(date, item.item17Studentid, "#item17Studentid", item, item.item17TrainContent,trainerId,trainerName);
    			setRefDate(date, item.item18Studentid, "#item18Studentid", item, item.item18TrainContent,trainerId,trainerName);
    			setRefDate(date, item.item19Studentid, "#item19Studentid", item, item.item19TrainContent,trainerId,trainerName);
    			setRefDate(date, item.item20Studentid, "#item20Studentid", item, item.item20TrainContent,trainerId,trainerName);
    			setRefDate(date, item.item21Studentid, "#item21Studentid", item, item.item21TrainContent,trainerId,trainerName);
    			break;
    		}
    	}
    }
    
    function setRefDate(date, value, td, itemDate, rtype,trainerId,trainerName){
    	$(td).off('click');
    	if(value == undefined || value == ''){
    		if(rtype == <%=reservationType%>){
    			$(td).attr("class", "am-primary");
        		$(td).text('点击预约');
        		$(td).on('click', function(){
        			check(date, td, itemDate,trainerId,trainerName);
        		});
    		} else {
    			$(td).attr("class", "am-warning");
        		$(td).text('排班类型：' + getWorkingType(rtype));
    		}
    	} else if(value == 'NO'){
    		$(td).attr("class", "am-danger");
    		$(td).text('不排班');
    	} else if(value == resData.studentNo){
    		$(td).attr("class", "am-success");
    		$(td).text('您已预约');
    	} else if(value.length > 0){
    		$(td).attr("class", "am-warning");
    		$(td).text('已预约');
    	}
    }
  
  	function check(date, td, itemDate,trainerId,trainerName){
  		var start = "0:00";
  		var end = "0:00";
  		if(td == "#item7Studentid"){start = "7:00"; end = "8:00";}
  		if(td == "#item8Studentid"){start = "8:00"; end = "9:00";}
  		if(td == "#item9Studentid"){start = "9:00"; end = "10:00";}
  		if(td == "#item10Studentid"){start = "10:00"; end = "11:00";}
  		if(td == "#item11Studentid"){start = "11:00"; end = "12:00";}
  		if(td == "#item12Studentid"){start = "12:00"; end = "13:00";}
  		if(td == "#item13Studentid"){start = "13:00"; end = "14:00";}
  		if(td == "#item14Studentid"){start = "14:00"; end = "15:00";}
  		if(td == "#item15Studentid"){start = "15:00"; end = "16:00";}
  		if(td == "#item16Studentid"){start = "16:00"; end = "17:00";}
  		if(td == "#item17Studentid"){start = "17:00"; end = "18:00";}
  		if(td == "#item18Studentid"){start = "18:00"; end = "19:00";}
  		if(td == "#item19Studentid"){start = "19:00"; end = "20:00";}
  		if(td == "#item20Studentid"){start = "20:00"; end = "21:00";}
  		if(td == "#item21Studentid"){start = "21:00"; end = "22:00";}
  		var typeStr = "五项";
  		if(reservationType == 4){ 
  			typeStr = "路考";
  		}else if(reservationType ==1 ){
  			typeStr = "电车";
  		}else if(reservationType == 2){
  			typeStr = "模拟";
  		}
  		
  		var flag = window.confirm("您确定需要预约: " + date.substring(0, 10) + " " + start + "-" + end + " [" + typeStr + "]吗?"); 
  		if(flag){
  			
  			itemDate.date = date;
  			itemDate.timeStart = start;
  			itemDate.timeEnd = end;
  			itemDate.reservationType = reservationType;
  			itemDate.studentNo = resData.studentNo;
  			
  			console.log(itemDate);
  			
  			//var params = "resvdate=" + date + "&timeStart=" + start + "&timeEnd=" + end + "&reservationType=" + reservationType + "&trainerId=" + resData.trainerId + "&key=<%=key%>";
  			$.ajax({
  	  			url : '${pageContext.request.contextPath}/guest/reservationAction!add.action?key=<%=key%>',
  	  			data: itemDate,
  				type: 'POST',
  	  			dataType : 'json',
  	  			async: true,
  	  			success : function(data) {
  	  				console.log(data);
  	  				if(data.code > 0){
  	  					alert(data.msg);
  	  					window.location.href="reservation2.jsp"; 
  	  				//getReservationList(trainerId, trainerName);
  	  				} else {
  	  					alert(data.msg);
  	  				}
  	  			},
  	  			error : function(err){
  	  				console.log(err);
  	  			}
  	  		});
  		}
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
  	function getWorkingType(rtype){
  		if(rtype == 1) return "电车";
  		if(rtype == 2) return "模拟";
  		if(rtype == 3) return "五项";
  		if(rtype == 4) return "路训";
  	}
  </script>

</body>
</html>
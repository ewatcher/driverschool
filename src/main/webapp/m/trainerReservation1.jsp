<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.tuocheng.util.base.Util"%>
<%

  // 测试用,正式需要删除 
  //session.setAttribute("weixinOpenId", "ovaZhwam1u0wcHIBLXeLD6gNIdog");
  //session.setAttribute("orgId", "357c1d29-c991-4eed-a331-949f3eb4e9ly");

  String openId = session.getAttribute("weixinOpenId") != null ? (String)session.getAttribute("weixinOpenId") : "";
  String code = Util.objToString(request.getParameter("code"), "");
  String state = Util.objToString(request.getParameter("state"), "");
  String schoolAreaName=Util.objToString(session.getAttribute("schoolAreaName"), "");
  int reservationType = Util.objToInt(request.getParameter("reservationType"), 1);
  
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
      <div class="am-modal-hd" style="margin-bottom:20px;">教练绑定</div>
      <div class="am-modal-bd">
        首次使用请绑定教练员信息
        <input type="text" name="trainerName" id="trainerName" class="am-modal-prompt-input"  style="margin-top:10px;" placeholder="输入您的真实姓名" />
        <input type="text" name="identity" id="identity" class="am-modal-prompt-input" style="margin-bottom:10px;" placeholder="输入您的身份证号" />
      </div>
      <div class="am-modal-footer">
        <span class="am-modal-btn" data-am-modal-cancel>取消</span>
        <span class="am-modal-btn" onclick="bindStudent();">绑定</span>
      </div>
    </div>
  </div>
  
  <div class="am-panel am-panel-secondary" style="margin-bottom:0px;">
    	<div class="am-panel-hd">
     <span class="am-icon-automobile" style="margin-right:10px;"></span>欢迎使用《驾校一体化》教练端
    </div>
	
	<div style="margin:10px 10px 10px 10px;">
	    <ul class="am-avg-sm-4 am-thumbnails" style="text-align: center;">
	      <li><a href="javascript:void(0);"  class="am-icon-btn am-success am-icon-recycle" onclick="reservationList();"></a><br /> 预约信息</li>
	      <li><a href="javascript:void(0);" class="am-icon-btn am-warning am-icon-star-half-empty" onclick="studentList();"></a><br /> 学员信息</li>
	      <li><a href="javascript:void(0);" class="am-icon-btn am-danger am-icon-frown-o"  onclick="arrangeList();"></a><br /> 排班信息</li>
	      <li><a href="javascript:void(0);" class="am-icon-btn am-danger am-icon-commenting-o"  onclick="examList();"></a><br /> 考试信息</li>
	    </ul>
 	 </div>
 	 
    <div class="am-panel-hd">
      <span id="trainer_title" class="am-icon-automobile" style="margin-right:10px;">教练预约统计</span>
    </div>
   <!-- 教练我的学员列表 -->
   <div id="trainer_studentList" style="display:none"  class="am-form">
      <fieldset>
        <div class="am-form-group">
          <label for="doc-select-1">教练员：</label><b id="studentTrainerName"></b> <label for="doc-select-1" style="margin-left:30px;">学员总数：</label><b id="studentTotal"></b>
          <div class="am-g">
            <table id="show_table" class="am-table am-table-bordered am-table-striped am-table-hover">
              <thead>
              <tr>
                <th style="width:20%" >姓名</th>
                <th style="width:15%">编号</th>
                <th style="width:35%">手机</th>
                <th style="width:30%">报名日期</th>
              </tr>
              </thead>
              <tbody id="studentData">
              </tbody>
            </table>
          </div>
          <span class="am-form-caret"></span>
        </div>
    
      </fieldset>
    </div>
    <!-- 教练预约统计 -->
    <div id="trainer_week" class="am-form">
      <fieldset>
        <input type="hidden" name="key" value="<%=key%>" />
         <div class="am-form-group">
	    	 <button class="am-btn am-round am-btn-secondary" id="reservation_last_week" onclick="lastWeekChange();">上周 </button>
	    	 <button class="am-btn am-round am-btn-secondary" id="reservation_this_week" onclick="thisWeekChange();">本周</button>
	    	 <button class="am-btn am-round am-btn-secondary" id="reservation_next_week" onclick="nextWeekChange();">下周</button>
   		 </div>
        <div class="am-form-group">
          <label for="doc-select-1">预约时间：</label>
          <select name="resvdate" id="resvdate" onchange="changeSelect();">
          </select>
          <span class="am-form-caret"></span>
        </div>
        
        <div class="am-form-group">
          <label for="doc-select-1">教练员：</label><b id="myName"></b> <label for="doc-select-1" style="margin-left:30px;">预约日期：</label><b id="reservationDate"></b>
          <div class="am-g">
            
            <table id="show_table" class="am-table am-table-bordered am-table-striped am-table-hover">
              <thead>
              <tr>
                <th style="width:35%">预约时间</th>
                <th style="width:35%">预约学员</th>
                <th style="width:15%">状态</th>
                <th style="width:15%">训练</th>
              </tr>
              </thead>
              <tbody>
               <tr>
                <td>07:00 - 08:00</td>
                <td id="item7Studentid" class="am-danger"></td>
                <td id="item7State" class="am-danger"></td>
                <td id="item7TrainContent" class="am-danger"></td>
              </tr>
              <tr>
                <td>08:00 - 09:00</td>
                <td id="item8Studentid" class="am-danger"></td>
                <td id="item8State" class="am-danger"></td>
                <td id="item8TrainContent" class="am-danger"></td>
              </tr>
              <tr>
                <td>09:00 - 10:00</td>
                <td id="item9Studentid" class="am-danger"></td>
                <td id="item9State" class="am-danger"></td>
                <td id="item9TrainContent" class="am-danger"></td>
              </tr>
              <tr>
                <td>10:00 - 11:00</td>
                <td id="item10Studentid" class="am-danger"></td>
                <td id="item10State" class="am-danger"></td>
                <td id="item10TrainContent" class="am-danger"></td>
              </tr>
              <tr>
                <td>11:00 - 12:00</td>
                <td id="item11Studentid" class="am-danger"></td>
                <td id="item11State" class="am-danger"></td>
                <td id="item11TrainContent" class="am-danger"></td>
              </tr>
              <tr>
                <td>12:00 - 13:00</td>
                <td id="item12Studentid" class="am-danger"></td>
                <td id="item12State" class="am-danger"></td>
                <td id="item12TrainContent" class="am-danger"></td>
              </tr>
              <tr>
                <td>13:00 - 14:00</td>
                <td id="item13Studentid" class="am-danger"></td>
                <td id="item13State" class="am-danger"></td>
                <td id="item13TrainContent" class="am-danger"></td>
              </tr>
              <tr>
                <td>14:00 - 15:00</td>
                <td id="item14Studentid" class="am-danger"></td>
                <td id="item14State" class="am-danger"></td>
                <td id="item14TrainContent" class="am-danger"></td>
              </tr>
              <tr>
                <td>15:00 - 16:00</td>
                <td id="item15Studentid" class="am-danger"></td>
                <td id="item15State" class="am-danger"></td>
                <td id="item15TrainContent" class="am-danger"></td>
              </tr>
              <tr>
                <td>16:00 - 17:00</td>
                <td id="item16Studentid" class="am-danger"></td>
                <td id="item16State" class="am-danger"></td>
                <td id="item16TrainContent" class="am-danger"></td>
              </tr>
              <tr>
                <td>17:00 - 18:00</td>
                <td id="item17Studentid" class="am-danger"></td>
                <td id="item17State" class="am-danger"></td>
                <td id="item17TrainContent" class="am-danger"></td>
              </tr>
              <tr>
                <td>18:00 - 19:00</td>
                <td id="item18Studentid" class="am-danger"></td>
                <td id="item18State" class="am-danger"></td>
                <td id="item18TrainContent" class="am-danger"></td>
              </tr>
               <tr>
                <td>19:00 - 20:00</td>
                <td id="item19Studentid" class="am-danger"></td>
                <td id="item19State" class="am-danger"></td>
                <td id="item19TrainContent" class="am-danger"></td>
              </tr>
               <tr>
                <td>20:00 - 21:00</td>
                <td id="item20Studentid" class="am-danger"></td>
                <td id="item20State" class="am-danger"></td>
                <td id="item20TrainContent" class="am-danger"></td>
              </tr>
               <tr>
                <td>21:00 - 22:00</td>
                <td id="item21Studentid" class="am-danger"></td>
                <td id="item21State" class="am-danger"></td>
                <td id="item21TrainContent" class="am-danger"></td>
              </tr>
              </tbody>
            </table>
          </div>
          <span class="am-form-caret"></span>
        </div>
    
      </fieldset>
    </div>
  </div>
  
  <div style="height:50px;"></div>

  <div class="am-navbar am-navbar-default" style="display: none;">
    <ul class="am-navbar-nav">
      <li class="am-btn am-btn-primary am-btn-xs" style="padding:0px;"><a href="${pageContext.request.contextPath}/m/articleAction!tariff.action"> <span class="am-icon-calculator"></span> <span class="am-navbar-label">我的预约</span>
      </a></li>
      <li class="am-btn am-btn-primary am-btn-xs" style="padding:0px;"><a href="${pageContext.request.contextPath}/m/articleAction!notice.action"> <span class="am-icon-star"></span> <span class="am-navbar-label">我的学员</span>
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
	var lastWeek=[];
	var thisWeek=[];
	var nextWeek=[];
	var reservationDatas={};
	var clickFlag=1;
	
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
  				checkTrainerBind(); // 验证学员是否已经绑定微信
  			},
  			error : function(err){
  				//alert(JSON.stringify(err));
  			}
  		});
	}, 0);
    <%} else {%>
    checkTrainerBind(); // 验证学员是否已经绑定微信
    <%}%>
    
    function checkTrainerBind(){
    	submitBtn.attr('disabled', true);
    	$.ajax({
  			url : '${pageContext.request.contextPath}/guest/trainerAction!checkTrainerBind.action',
  			dataType : 'json',
  			async: true,
  			success : function(data) {
  				//alert(JSON.stringify(data));
  				if(data.code > 0){
  					// 该用户已经绑定
  					bindOkEvent(); // 获取教练信息
  				} else if(data.code == -2){
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
      	user.name = $('#trainerName').val();
      	user.identity = $('#identity').val();
      	//alert(JSON.stringify(user));
      	$.ajax({
  			url : '${pageContext.request.contextPath}/guest/trainerAction!bindStudentToOpenId2.action',
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
  //用户单击教练预约事件
    function reservationList(){
    	clickFlag=1;
    	$("#trainer_title").text("学员预约列表");
    	$("#trainer_week").show();
    	$("#trainer_studentList").hide();
    }
    
    function studentList(){
    	clickFlag=2;
    	$("#trainer_studentList").show();
    	checkTrainerBind();
    }
    
    function arrangeList(){
    	clickFlag=3;
    	checkTrainerBind();
    	$("#trainer_week").hide();
    	alert("开发中...");
    }
    
    function examList(){
    	clickFlag=4;
    	checkTrainerBind();
    	$("#trainer_week").hide();
    	alert("开发中...");
    }
    
    // 如果验证，绑定都成功的话执行
    function bindOkEvent(){
    	if(clickFlag==1){
    		getReservationDatas();
    	}else if(clickFlag==2){
        	$("#trainer_week").hide();
    		getStudentsByTrainer();
    	}else if(clickFlag==3){
    		
    	}else if(clickFlag==4){
    		
    	}
    }
    
    // 获取教练信息
    function getReservationDatas(){
    	$.ajax({
  			url : '${pageContext.request.contextPath}/guest/reservationAction!getDetailReservationToWeixin.action',
  			dataType : 'json',
  			async: true,
  			success : function(data) {
  				console.log(data);
  				if(data.code == 1){
  					//从后台获取展现的数据
  					resData = data;
  					thisWeekChange();
  				} else {
  					alert(data.msg);
  				}
  			},
  			error : function(err){
  				alert(err);
  				console.log(err);
  			}
  		});
    	
    }
    
    function lastWeekChange(){
    	$('#resvdate').empty();
    		$('#resvdate').append("<option value='"+ null + "'>请选择</option>");
	    	for(var i = 0; i < resData.lastWeekDatestring.length; i++){
	    		var item=resData.lastWeekDatestring[i];
	    		var dates=resData.datesOfLastWeek[i];
	    		$('#resvdate').append("<option value='"+ dates + "'>" + item + "</option>");
	    	}
	    	$('#reservationDate').text(lastWeek[0]);
	    	$("#reservation_last_week").attr("class","am-btn am-round am-btn-warning");
	    	$("#reservation_this_week").attr("class","am-btn am-round am-btn-secondary");
	    	$("#reservation_next_week").attr("class","am-btn am-round am-btn-secondary");
    }
    function thisWeekChange(){
    	$('#resvdate').empty();
    	$('#resvdate').append("<option value='"+ null + "'>请选择</option>");
    	for(var i = 0; i < resData.thisWeekDatestring.length; i++){
    		var item=resData.thisWeekDatestring[i];
    		var dates=resData.datesOfThisWeek[i];
    		$('#resvdate').append("<option value='"+ dates + "'>" + item + "</option>");
    	}
    	$("#reservation_last_week").attr("class","am-btn am-round am-btn-secondary");
    	$("#reservation_this_week").attr("class","am-btn am-round am-btn-warning");
    	$("#reservation_next_week").attr("class","am-btn am-round am-btn-secondary");
    	
    }
    function nextWeekChange(){
    	$('#resvdate').empty();
    	$('#resvdate').append("<option value='"+ null + "'>请选择</option>");
    	for(var i = 0; i < resData.nextWeekDatestring.length; i++){
    		var item=resData.nextWeekDatestring[i];
    		var dates=resData.datesOfNextWeek[i];
    		$('#resvdate').append("<option value='"+ dates + "'>" + item + "</option>");
    	}
    	$("#reservation_last_week").attr("class","am-btn am-round am-btn-secondary");
    	$("#reservation_this_week").attr("class","am-btn am-round am-btn-secondary");
    	$("#reservation_next_week").attr("class","am-btn am-round am-btn-warning");
    	
    }
    
    //根据标识标识从后台获取该 教练的学员信息
    function getStudentsByTrainer(){
    	$.ajax({
  			url : '${pageContext.request.contextPath}/guest/trainerAction!getStudentsByTrainer.action',
  			dataType : 'json',
  			async: true,
  			success : function(retMap) {
  				if(retMap.code == 1){
  					//从后台获取展现的数据
  					$('#myName').text(retMap.trainerName);
  			    		var item = retMap.students;
  			    		var trainerName=retMap.trainerName;
  			    		if(item==null||item==undefined||item==''){
  			    			alert("无学员记录信息！");
  			    		}else{
  			    			$("#studentData").empty();
  			    			$("#studentTrainerName").text(trainerName);
  			    			$("#studentTrainerName").css("color","red");
  			    			$("#studentTotal").text(item.length+"人");
  			    			$("#studentTotal").css("color","red");
  			    			$("#trainer_title").text("【"+trainerName+"】教练学员列表");
  			    			$("#studentTotal").attr("class", "am-danger");
  			    			for(var i=0;i<item.length;i++){
  			    				var student=item[i];
  			    				var studentNoTemp=student.studentNo;
  			    				$("#studentData").append("<tr><td>"+student.name+"</td><td>"+getSubstring(studentNoTemp,2,studentNoTemp.length)+"</td><td>"
  			    						+student.phone+"</td><td>"+getSubstring(student.createTime,0,10)+"</td></tr>");
  			    			}
  			    			
  			    		}
  			    		
  				} else {
  					alert(retMap.msg);
  				}
  			},
  			error : function(err){
  				alert(err);
  				console.log(err);
  			}
  		});
    }
    
    // 日期选择发生变化
    function changeSelect(){
    	var date = $('#resvdate').children('option:selected').val(); //这就是selected的值
    	refreshDate(date);
    	
    }
    
    
    // 加载预约数据
    function refreshDate(date){
    	$.ajax({
  			url : '${pageContext.request.contextPath}/guest/reservationAction!getTrainerReservationDetailForWechat.action',
  			data:{date:date},
  			dataType : 'json',
  			async: true,
  			success : function(retMap) {
  				console.log(retMap);
  				if(retMap.code == 1){
  					//从后台获取展现的数据
  					$('#myName').text(retMap.trainerName);
  			    	$('#reservationDate').text(date.substring(0, 10));
  			    		var item = retMap.trainerReservationDetail;
  			    		var states=retMap.reservationStates;
  			    		setRefDate(date, item.item7Studentid, "#item7Studentid",item.item7TrainContent,"#item7TrainContent",states[0],"#item7State");
  			    		setRefDate(date, item.item8Studentid, "#item8Studentid",item.item8TrainContent,"#item8TrainContent",states[0],"#item8State");
			    		setRefDate(date, item.item9Studentid, "#item9Studentid", item.item9TrainContent,"#item9TrainContent",states[1],"#item9State");
			    		setRefDate(date, item.item10Studentid, "#item10Studentid", item.item10TrainContent,"#item10TrainContent",states[2],"#item10State");
			    		setRefDate(date, item.item11Studentid, "#item11Studentid",item.item11TrainContent,"#item11TrainContent" ,states[3],"#item11State");
			    		setRefDate(date, item.item12Studentid, "#item12Studentid", item.item12TrainContent,"#item12TrainContent",states[4],"#item12State");
			    		setRefDate(date, item.item13Studentid, "#item13Studentid",item.item13TrainContent,"#item13TrainContent" ,states[5],"#item13State");
			    		setRefDate(date, item.item14Studentid, "#item14Studentid", item.item14TrainContent,"#item14TrainContent",states[6],"#item14State");
			    		setRefDate(date, item.item15Studentid, "#item15Studentid", item.item15TrainContent,"#item15TrainContent",states[7],"#item15State");
			    		setRefDate(date, item.item16Studentid, "#item16Studentid", item.item16TrainContent,"#item16TrainContent",states[8],"#item16State");
			    		setRefDate(date, item.item17Studentid, "#item17Studentid", item.item17TrainContent,"#item17TrainContent",states[9],"#item17State");
			    		setRefDate(date, item.item18Studentid, "#item18Studentid", item.item18TrainContent,"#item18TrainContent",states[10],"#item18State");
			    		setRefDate(date, item.item19Studentid, "#item19Studentid", item.item19TrainContent,"#item19TrainContent",states[11],"#item19State");
			    		setRefDate(date, item.item20Studentid, "#item20Studentid", item.item20TrainContent,"#item20TrainContent",states[12],"#item20State");
			    		setRefDate(date, item.item21Studentid, "#item21Studentid", item.item21TrainContent,"#item21TrainContent",states[13],"#item21State");
  				} else {
  					alert(retMap.msg);
  				}
  			},
  			error : function(err){
  				alert(err);
  				console.log(err);
  			}
  		});
    	
    }
    
    function setRefDate(date, value, td,trainerContentVal,contentTd,stateValue,stateTd){
    	if(value == undefined || value == ''){
    		$(td).attr("class", "am-primary");
    		$(td).text('无人预约');
    	} else if(value == 'NO'){
    		$(td).attr("class", "am-danger");
    		$(td).text('不排班');
    	} else if(value.length > 0){
    		$(td).attr("class", "am-success");
    		$(td).text(value);
    	}
    	if(trainerContentVal > 0){
    		$(contentTd).attr("class", "am-primary");
    		if(trainerContentVal==3){
    			$(contentTd).text("五项");
    		}else{
    			$(contentTd).text("路训");
    		}
    	}
    	if(stateValue ==0){
    		$(stateTd).attr("class", "am-primary");
    		$(stateTd).text('');
    	} else if(stateValue == 1){
    		$(stateTd).attr("class", "am-primary");
    		$(stateTd).text('预约中');
    	} else if(stateValue==3){
    		$(stateTd).attr("class", "am-danger");
    		$(stateTd).text('完成');
    	}else if(stateValue==4){
    		$(stateTd).attr("class", "am-primary");
    		$(stateTd).text('取消');
    	}else if(stateValue==6){
    		$(stateTd).attr("class", "am-danger");
    		$(stateTd).text('确认中');
    	}
    }
    
    function getSubstring(str,startIndex,endIndex){
    	return str.substring(startIndex,endIndex);
    }
  	
  </script>

</body>
</html>
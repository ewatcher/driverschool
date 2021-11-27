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
        首次使用请绑定用户信息
        <input type="text" name="cname" id="cname" class="am-modal-prompt-input"  style="margin-top:10px;" placeholder="输入您的用户名" />
        <input type="text" name="cpwd" id="cpwd" class="am-modal-prompt-input" style="margin-bottom:10px;" placeholder="输入您的密码" />
      </div>
      <div class="am-modal-footer">
        <span class="am-modal-btn" data-am-modal-cancel>取消</span>
        <span class="am-modal-btn" onclick="bindMonitoer();">绑定</span>
      </div>
    </div>
  </div>
  
  <div class="am-panel am-panel-secondary" style="margin-bottom:0px;">
    <div class="am-panel-hd">
      <span class="am-icon-automobile" style="margin-left:10px;"></span>驾校数据统计分析
    </div>
  </div>
   <div style="margin:10px 10px 10px 10px;">
    <ul class="am-avg-sm-4 am-thumbnails" style="text-align: center;">
      <li><a href="javascript:void(0);"  class="am-icon-btn am-success am-icon-recycle" onclick="sigupAnalys();"></a><br /> 报名统计</li>
      <li><a href="javascript:void(0);" class="am-icon-btn am-warning am-icon-star-half-empty" onclick="moneyAnalys();"></a><br /> 财务统计</li>
      <li><a href="javascript:void(0);" class="am-icon-btn am-danger am-icon-frown-o"  onclick="reservationAnalys();"></a><br /> 预约统计</li>
      <li><a href="javascript:void(0);" class="am-icon-btn am-danger am-icon-commenting-o"  onclick="trainerAnalys();"></a><br /> 教练统计</li>
       <li><a href="javascript:void(0);" class="am-icon-btn am-danger am-icon-commenting-o"  onclick="carAnalys();"></a><br />车辆统计</li>
    </ul>
  </div>
   <div class="am-panel am-panel-secondary" style="margin-bottom:0px;">
	    <div class="am-panel-hd">
	      <span class="am-icon-automobile" id="analys_title" style="margin-left:10px;">统计分析列表</span>
	    </div>
  	</div>
  	 <div id="week_select" class="am-form-group" style="margin-left:20px;">
  	 		 <button class="am-btn am-round am-btn-secondary" id="monitor_base_week" onclick="baseChange();">总体</button>
	    	 <button class="am-btn am-round am-btn-secondary" id="monitor_last_week" onclick="lastWeekChange();">上周 </button>
	    	 <button class="am-btn am-round am-btn-secondary" id="monitor_this_week" onclick="thisWeekChange();">本周</button>
	    	 <button class="am-btn am-round am-btn-secondary" id="monitor_next_week" onclick="nextWeekChange();">下周</button>
   	</div>
    <div class="am-form-group">
          <div class="am-g">
            
            <table class="am-table am-table-bordered am-table-striped am-table-hover" id="anlys_table">
              <thead>
              <tr id="datas_title">
                <th width="25%">学校名称</th>
                <th width="18%" id="title_date"></th>
                <th width="18%" id="title_week"></th>
                <th width="18%" id="title_month"></th>
                <th width="18%" id="title_year"></th>
              </tr>
              </thead>
              <tbody id="loop_output_data">
              
              </tbody>
            </table>
          </div>
          <span class="am-form-caret"></span>
     </div>
  <script src="../amaze/assets/js/jquery.min.js"></script>
  <script src="../amaze/assets/js/amazeui.min.js"></script>
  <script src="../amaze/assets/js/app.js"></script>
  
  <script type="text/javascript">
	
    var reservationType = <%=reservationType%>;
	var submitBtn = $('#submit_reservation_btn');
	var resData = {};
	var analyWeekFlag=undefined;//1.列示基本统计，2.表示本周，3.表示上周，4表示一下周
	var lastWeekDates={};
	var thisWeekDates={};
	var nextWeekDates={};
	var sigupTotalMap={};
	var moneyTotalMap={};
	var reservationTotalMap={};
	var trainerTotalMap={};
	var carTotalMap={};
	
	var thisWeekSigupMap={};
	var thisWeekMoneyMap={};
	var thisWeekReservationMap={};
	var thisWeekTrainerMap={};
	var thisWeekCarMap={};
	
	var analy_sigup=1;
	var analy_money=2;
	var analy_reservation=3;
	var analy_trainer=4;
	var analy_car=5;
	var weekFlag_next=1;
	var weekFlag_this=2;
	var weekFlag_last=3;
	
	var weekTotals=[];
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
    	$("#datas_title").hide();
    	$.ajax({
  			url : '${pageContext.request.contextPath}/guest/userAction!checkUserBind.action',
  			dataType : 'json',
  			async: true,
  			success : function(data) {
  				//alert(JSON.stringify(data));
  				if(data.code > 0){
  					// 该用户已经绑定
  					bindOkEvent(); // 获取教练信息
  				} else if(data.code == -2){
  					// 该用户未绑定
  					alert(data.msg);
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
    function bindMonitoer(){
    	var user = {};
      	user.cname = $('#cname').val();
      	user.cpwd = $('#cpwd').val();
      	//alert(JSON.stringify(user));
      	$.ajax({
  			url : '${pageContext.request.contextPath}/guest/userAction!bindStudentToOpenId2.action',
  			data: user,
  			type: 'POST',
  			dataType : 'json',
  			async: true,
  			success : function(data) {
  				if(data.code > 0){
  					alert('绑定成功！');
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
    	$("#week_select").hide();
    	submitBtn.removeAttr("disabled");
    	studentCount();//获取校区标识
    }
    
    var analyType=undefined;
	var schoolAreas=[];
	var weekDates=[];
	var weekMap={};
	
	function sigupAnalys(){//用户单击报名统计事件
		analyType=1;
		//按校区分别统计日，月，年的数据
		$("#loop_output_data").empty();
		$("#week_select").show();
		$("#monitor_next_week").hide();
		$("#monitor_last_week").show();
		$("#monitor_this_week").show();
		$("#datas_title").hide();
		$("#analys_title").text("《学员报名》 统计分析列表");
		$("#monitor_base_week").attr("class","am-btn am-round am-btn-secondary");
		$("#monitor_last_week").attr("class","am-btn am-round am-btn-secondary");
    	$("#monitor_this_week").attr("class","am-btn am-round am-btn-secondary");
    	$("#monitor_next_week").attr("class","am-btn am-round am-btn-secondary");
	}
	function moneyAnalys(){//用户单击财务统计事件
		analyType=2;
		$("#week_select").show();
		$("#monitor_next_week").hide();
		$("#monitor_last_week").show();
		$("#monitor_this_week").show();
		$("#loop_output_data").empty();
		$("#datas_title").hide();
		$("#analys_title").text("《财务》 统计分析");
		$("#monitor_base_week").attr("class","am-btn am-round am-btn-secondary");
		$("#monitor_last_week").attr("class","am-btn am-round am-btn-secondary");
    	$("#monitor_this_week").attr("class","am-btn am-round am-btn-secondary");
    	$("#monitor_next_week").attr("class","am-btn am-round am-btn-secondary");
		
	}
	function reservationAnalys(){//用户预约报名统计事件
		analyType=3;
		$("#week_select").show();
		$("#monitor_next_week").show();
		$("#monitor_last_week").show();
		$("#monitor_this_week").show();
		$("#loop_output_data").empty();
		$("#datas_title").hide();
		$("#analys_title").text("《预约》 统计分析");
		$("#monitor_base_week").attr("class","am-btn am-round am-btn-secondary");
		$("#monitor_last_week").attr("class","am-btn am-round am-btn-secondary");
    	$("#monitor_this_week").attr("class","am-btn am-round am-btn-secondary");
    	$("#monitor_next_week").attr("class","am-btn am-round am-btn-secondary");
	}
	function trainerAnalys(){//户单击教练统计事件
		analyType=4;
		$("#week_select").show();
		$("#monitor_next_week").show();
		$("#loop_output_data").empty();
		$("#monitor_last_week").hide();
		$("#monitor_this_week").hide();
		$("#monitor_next_week").hide();
		$("#datas_title").hide();
		$("#analys_title").text("《教练》 统计分析");
		$("#monitor_base_week").attr("class","am-btn am-round am-btn-secondary");
		$("#monitor_last_week").attr("class","am-btn am-round am-btn-secondary");
    	$("#monitor_this_week").attr("class","am-btn am-round am-btn-secondary");
    	$("#monitor_next_week").attr("class","am-btn am-round am-btn-secondary");
	}
	function carAnalys(){//用户单击车辆统计事件
		analyType=5;
		$("#week_select").show();
		$("#monitor_next_week").show();
		$("#monitor_last_week").show();
		$("#monitor_this_week").show();
		$("#loop_output_data").empty();
		$("#datas_title").hide();
		$("#analys_title").text("《车辆》 统计分析");
		$("#monitor_base_week").attr("class","am-btn am-round am-btn-secondary");
		$("#monitor_last_week").attr("class","am-btn am-round am-btn-secondary");
    	$("#monitor_this_week").attr("class","am-btn am-round am-btn-secondary");
    	$("#monitor_next_week").attr("class","am-btn am-round am-btn-secondary");
	}
	
	//基本统计(从后台获取日周月年各数据总数)
	function baseChange(){
		$("#monitor_base_week").attr("class","am-btn am-round am-btn-warning");
		$("#monitor_last_week").attr("class","am-btn am-round am-btn-secondary");
    	$("#monitor_this_week").attr("class","am-btn am-round am-btn-secondary");
    	$("#monitor_next_week").attr("class","am-btn am-round am-btn-secondary");
		if(analyType==analy_sigup){//报名统计
			//根据校区获取各校区报名总人数
			$.ajax({
				url : '${pageContext.request.contextPath}/guest/userAction!getAllCoutByOrgs.action',
				dataType : 'json',
				success : function(map) {
					baseAnalys(analy_sigup,map);
				},
			});
			
		}else if(analyType==analy_money){//财务统计
			//根据校区获取各校区报名总人数
			$.ajax({
				url : '${pageContext.request.contextPath}/guest/userAction!getBaseMoneyCount.action',
				dataType : 'json',
				success : function(map) {
					baseAnalys(analy_money,map);
				},
			});
			
		}else if(analyType==analy_reservation){//学员预约统计
			$.ajax({
				url : '${pageContext.request.contextPath}/guest/userAction!getBaseReservationCount.action',
				dataType : 'json',
				success : function(map) {
					baseAnalys(analy_reservation,map);
				},
			});
			
		}else if(analyType==analy_trainer){//教练预约统计
			//根据校区获取各校区报名总人数
			$.ajax({
				url : '${pageContext.request.contextPath}/guest/userAction!getTrainerTotalByOrg.action',
				dataType : 'json',
				success : function(map) {
					trainerShow(map);
				},
			});
			
		}else if(analyType==analy_car){//车辆使用统计
			//根据校区获取各校区车辆使用总学时
			$.ajax({
				url : '${pageContext.request.contextPath}/guest/userAction!getBaseCarCount.action',
				dataType : 'json',
				success : function(map) {
					baseAnalys(analy_car,map);
				},
			});
			
			
		}
	}
	//本周统计
	function thisWeekChange(){
		$("#monitor_base_week").attr("class","am-btn am-round am-btn-secondary");
		$("#monitor_last_week").attr("class","am-btn am-round am-btn-secondary");
    	$("#monitor_this_week").attr("class","am-btn am-round am-btn-warning");
    	$("#monitor_next_week").attr("class","am-btn am-round am-btn-secondary");
    	//获取本周的日期
		$.ajax({
			url : '${pageContext.request.contextPath}/guest/userAction!getWeekDays.action',
			data:{countType:2},
			dataType : 'json',
			success : function(d) {
				//1.获取该周统计结果集
				thisWeekDates=d;
			},
		});
		if(analyType==1){//报名统计
			$.ajax({
				url : '${pageContext.request.contextPath}/guest/userAction!getWeekCountTotal.action',
				data:{countType:weekFlag_this},
				dataType : 'json',
				success : function(total) {
					weekTotals=total;
				},
				error:function(err){
					alert("获取该周统计总数失败");
				}
			});
			//获取本周内每一天的学员报名详细数据
			$.ajax({
				url : '${pageContext.request.contextPath}/guest/userAction!getWeekCountByOrgs.action',
				data:{countType:weekFlag_this},
				dataType : 'json',
				success : function(weekMap) {
					weekAnalysDetails(thisWeekDates,weekMap);
				},
				error:function(err){
					alert("获取该周明细统计总数失败");
				}
			});
			
			//周统计数据
    		
		}else if(analyType==analy_money){//财务统计
			$.ajax({
				url : '${pageContext.request.contextPath}/guest/userAction!getMoneyWeekCountTotal.action',
				data:{countType:weekFlag_this},
				dataType : 'json',
				success : function(total) {
					weekTotals=total;
				},
				error:function(err){
					alert("获取该周统计总数失败");
				}
			});
			//获取本周内每一天的学员报名详细数据
			$.ajax({
				url : '${pageContext.request.contextPath}/guest/userAction!getMoneyWeekCountByOrgs.action',
				data:{countType:weekFlag_this},
				dataType : 'json',
				success : function(weekMap) {
					weekAnalysDetails(thisWeekDates,weekMap);
				},
				error:function(err){
					alert("获取该周明细统计总数失败");
				}
			});
		}else if(analyType==3){//学员预约统计
			$.ajax({
				url : '${pageContext.request.contextPath}/guest/userAction!getReservationWeekCountTotal.action',
				data:{countType:weekFlag_this},
				dataType : 'json',
				success : function(total) {
					weekTotals=total;
				},
				error:function(err){
					alert("获取该周统计总数失败");
				}
			});
			//获取本周内每一天的学员报名详细数据
			$.ajax({
				url : '${pageContext.request.contextPath}/guest/userAction!getReservationWeekCountByOrgs.action',
				data:{countType:weekFlag_this},
				dataType : 'json',
				success : function(weekMap) {
					weekAnalysDetails(thisWeekDates,weekMap);
				},
				error:function(err){
					alert("获取该周明细统计总数失败");
				}
			});
		}else if(analyType==4){//教练预约统计
			
		}else if(analyType==5){//车辆使用统计
			$.ajax({
				url : '${pageContext.request.contextPath}/guest/userAction!getCarWeekCountTotal.action',
				data:{countType:weekFlag_this},
				dataType : 'json',
				success : function(total) {
					weekTotals=total;
				},
				error:function(err){
					alert("获取该周统计总数失败");
				}
			});
			//获取本周内每一天的学员报名详细数据
			$.ajax({
				url : '${pageContext.request.contextPath}/guest/userAction!getCarWeekCountByOrgs.action',
				data:{countType:weekFlag_this},
				dataType : 'json',
				success : function(weekMap) {
					weekAnalysDetails(thisWeekDates,weekMap);
				},
				error:function(err){
					alert("获取该周明细统计总数失败");
				}
			});
		}
	}
	
	//上周统计
	function lastWeekChange(){
		$("#monitor_base_week").attr("class","am-btn am-round am-btn-secondary");
		$("#monitor_last_week").attr("class","am-btn am-round am-btn-warning");
    	$("#monitor_this_week").attr("class","am-btn am-round am-btn-secondary");
    	$("#monitor_next_week").attr("class","am-btn am-round am-btn-secondary");
    	//获取上周的日期
		$.ajax({
			url : '${pageContext.request.contextPath}/guest/userAction!getWeekDays.action',
			data:{countType:3},
			dataType : 'json',
			success : function(d) {
				//1.获取该周统计结果集
				lastWeekDates=d;
			},
		});
		if(analyType==1){//报名统计
			//周统计数据
			$.ajax({
				url : '${pageContext.request.contextPath}/guest/userAction!getWeekCountTotal.action',
				data:{countType:weekFlag_last},
				dataType : 'json',
				success : function(total) {
					weekTotals=total;
				},
				error:function(err){
					alert("获取该周统计总数失败");
				}
			});
			//获取本周内每一天的学员报名详细数据
			$.ajax({
				url : '${pageContext.request.contextPath}/guest/userAction!getWeekCountByOrgs.action',
				data:{countType:weekFlag_last},
				dataType : 'json',
				success : function(weekMap) {
					weekAnalysDetails(lastWeekDates,weekMap);
				},
				error:function(err){
					alert("获取该周明细统计总数失败");
				}
			});
		}else if(analyType==2){//财务统计
			$.ajax({
				url : '${pageContext.request.contextPath}/guest/userAction!getMoneyWeekCountTotal.action',
				data:{countType:weekFlag_last},
				dataType : 'json',
				success : function(total) {
					weekTotals=total;
				},
				error:function(err){
					alert("获取该周统计总数失败");
				}
			});
			//获取本周内每一天的学员报名详细数据
			$.ajax({
				url : '${pageContext.request.contextPath}/guest/userAction!getMoneyWeekCountByOrgs.action',
				data:{countType:weekFlag_last},
				dataType : 'json',
				success : function(weekMap) {
					weekAnalysDetails(lastWeekDates,weekMap);
				},
				error:function(err){
					alert("获取该周明细统计总数失败");
				}
			});
		}else if(analyType==3){//学员预约统计
			$.ajax({
				url : '${pageContext.request.contextPath}/guest/userAction!getReservationWeekCountTotal.action',
				data:{countType:weekFlag_last},
				dataType : 'json',
				success : function(total) {
					weekTotals=total;
				},
				error:function(err){
					alert("获取该周统计总数失败");
				}
			});
			//获取本周内每一天的学员报名详细数据
			$.ajax({
				url : '${pageContext.request.contextPath}/guest/userAction!getReservationWeekCountByOrgs.action',
				data:{countType:weekFlag_last},
				dataType : 'json',
				success : function(weekMap) {
					weekAnalysDetails(lastWeekDates,weekMap);
				},
				error:function(err){
					alert("获取该周明细统计总数失败");
				}
			});
		}else if(analyType==4){//教练预约统计
			
		}else if(analyType==5){//车辆使用统计
			$.ajax({
				url : '${pageContext.request.contextPath}/guest/userAction!getCarWeekCountTotal.action',
				data:{countType:weekFlag_last},
				dataType : 'json',
				success : function(total) {
					weekTotals=total;
				},
				error:function(err){
					alert("获取该周统计总数失败");
				}
			});
			//获取本周内每一天的学员报名详细数据
			$.ajax({
				url : '${pageContext.request.contextPath}/guest/userAction!getCarWeekCountByOrgs.action',
				data:{countType:weekFlag_last},
				dataType : 'json',
				success : function(weekMap) {
					weekAnalysDetails(lastWeekDates,weekMap);
				},
				error:function(err){
					alert("获取该周明细统计总数失败");
				}
			});
		}
	}
	
	//下周统计
	function nextWeekChange(){
		$("#monitor_base_week").attr("class","am-btn am-round am-btn-secondary");
		$("#monitor_last_week").attr("class","am-btn am-round am-btn-secondary");
    	$("#monitor_this_week").attr("class","am-btn am-round am-btn-secondary");
    	$("#monitor_next_week").attr("class","am-btn am-round am-btn-warning");
		//获取该下周的日期
		$.ajax({
			url : '${pageContext.request.contextPath}/guest/userAction!getWeekDays.action',
			data:{countType:1},
			dataType : 'json',
			success : function(d) {
				//1.获取该周统计结果集
				nextWeekDates=d;
			},
		});
		if(analyType==1){//报名统计
			//周统计数据
    		$.ajax({
    			url : '${pageContext.request.contextPath}/guest/userAction!getWeekCountTotal.action',
    			data:{countType:1},
    			dataType : 'json',
    			success : function(total) {
    				weekTotals=total;
    			},
    			error:function(err){
    				alert("获取该周统计总数失败");
    			}
    		});
    		weekAnalysDetails(1);
		}else if(analyType==2){//财务统计
			
		}else if(analyType==3){//学员预约统计
			$.ajax({
				url : '${pageContext.request.contextPath}/guest/userAction!getReservationWeekCountTotal.action',
				data:{countType:weekFlag_next},
				dataType : 'json',
				success : function(total) {
					weekTotals=total;
				},
				error:function(err){
					alert("获取该周统计总数失败");
				}
			});
			//获取本周内每一天的学员报名详细数据
			$.ajax({
				url : '${pageContext.request.contextPath}/guest/userAction!getReservationWeekCountByOrgs.action',
				data:{countType:weekFlag_next},
				dataType : 'json',
				success : function(weekMap) {
					weekAnalysDetails(nextWeekDates,weekMap);
				},
				error:function(err){
					alert("获取该周明细统计总数失败");
				}
			});
		}else if(analyType==4){//教练预约统计
			
		}else if(analyType==5){//车辆使用统计
			$.ajax({
				url : '${pageContext.request.contextPath}/guest/userAction!getCarWeekCountTotal.action',
				data:{countType:weekFlag_next},
				dataType : 'json',
				success : function(total) {
					weekTotals=total;
				},
				error:function(err){
					alert("获取该周统计总数失败");
				}
			});
			//获取本周内每一天的学员报名详细数据
			$.ajax({
				url : '${pageContext.request.contextPath}/guest/userAction!getCarWeekCountByOrgs.action',
				data:{countType:weekFlag_next},
				dataType : 'json',
				success : function(weekMap) {
					weekAnalysDetails(nextWeekDates,weekMap);
				},
				error:function(err){
					alert("获取该周明细统计总数失败");
				}
			});
		}
	}
	
  	//从后台中获取校区的id和名称,周日期（上周，本周，下周）
	function studentCount(){
  		
		//从后台中获取校区的id和名称
		$.ajax({
			url : '${pageContext.request.contextPath}/guest/userAction!getAllOrganization.action',
			dataType : 'json',
			success : function(data) {
				schoolAreas=data;
			},
			error:function(err){
				alert("获取校区数据失败");
			}
		});
	};
	//
    
    function weekAnalysDetails(weekDates,weekMap){
    	//按该周日期统计第一天学员报名，返回Map<校区标识，Map<周日期，统计数>>
		$("#loop_output_data").empty();
		for(var i=0;i<schoolAreas.length;i++){
			var key=schoolAreas[i].value;
			$("#loop_output_data").append("<tr id=schoolArea"+key+"><th>"+schoolAreas[i].text+"</th><th calss='am-danger'>"+weekTotals[i].value+"</th></tr>")
			$("#schoolArea"+key).after("<tr><th>"+weekDates[6]+"</th><th calss='am-danger'>"+weekMap[key]["Sunday"]+"</th></tr>");
			$("<tr><th>"+weekDates[5]+"</th><th calss='am-danger'>"+weekMap[key]["Saturday"]+"</th></tr>").appendTo($("#loop_output_data"));
			$("<tr><th>"+weekDates[4]+"</th><th calss='am-danger'>"+weekMap[key]["Friday"]+"</th></tr>").appendTo($("#loop_output_data"));
			$("<tr><th>"+weekDates[3]+"</th><th calss='am-danger'>"+weekMap[key]["Thursday"]+"</th></tr>").appendTo($("#loop_output_data"));
			$("<tr><th>"+weekDates[2]+"</th><th calss='am-danger'>"+weekMap[key]["Wednesday"]+"</th></tr>").appendTo($("#loop_output_data"));
			$("<tr><th>"+weekDates[1]+"</th><th calss='am-danger'>"+weekMap[key]["Tuesday"]+"</th></tr>").appendTo($("#loop_output_data"));
			$("<tr><th>"+weekDates[0]+"</th><th calss='am-danger'>"+weekMap[key]["Monday"]+"</th></tr>").appendTo($("#loop_output_data"));
			$("#schoolArea"+key).attr("class", "am-primary");
		}
		$("#title_date").text("总数");
		$("#title_year").hide();
		$("#title_month").hide();
		$("#title_week").hide();
    }
    
	//基本数据分析
    function baseAnalys(analyFlag,dataMap){
    	$("#loop_output_data").empty();
    	$("#datas_title").show();
    	$("#title_year").show();
		$("#title_month").show();
		$("#title_week").show();
		
    	if(analyFlag==analy_sigup){//报名统计
    		$("#title_date").text("日(人)");
        	$("#title_week").text("周(人)");
        	$("#title_month").text("月(人)");
        	$("#title_year").text("年(人)");
    	}else if(analyFlag==analy_money){//财务
    		$("#title_date").text("日(元)");
        	$("#title_week").text("周(元)");
        	$("#title_month").text("月(元)");
        	$("#title_year").text("年(元)");
    	}else if(analyFlag==analy_reservation){//预约
    		$("#title_date").text("日(学时)");
        	$("#title_week").text("周(学时)");
        	$("#title_month").text("月(学时)");
        	$("#title_year").text("年(学时)");
    	}else if(analyFlag==analy_trainer){//教练
    		$("#title_date").text("日(人)");
        	$("#title_week").text("周(人)");
        	$("#title_month").text("月(人)");
        	$("#title_year").text("年(人)");
    	}else if(analyFlag==analy_car){//车辆
    		$("#title_date").text("日(学时)");
        	$("#title_week").text("周(学时)");
        	$("#title_month").text("月(学时)");
        	$("#title_year").text("年(学时)");
    	}
    	for(var i=0;i<schoolAreas.length;i++){
			$("#loop_output_data").append("<tr><th>"+schoolAreas[i].text+"</th>"
					+"<th calss=dateCount_total>"+dataMap["dateCount"][i].value
					+"<th calss=weekCount_total>"+dataMap["weekCount"][i].value
					+"<th calss=monthCount_total>"+dataMap["monthCount"][i].value
					+"<th calss=yearCount_total>"+dataMap["yearCount"][i].value
					+"</th></tr>");
		}
    }
	
	//教练统计展现table
	function trainerShow(map){
		var trainerCount=map["orgs"];
		var insideTotal=map["insideTotal"];
		var outTotal=map["outTotal"];
		var names={};
		var codeNo={};
		var studentTotal={};
		var reservationTotal={};
		var trainerType={};
		//从后台中获取校区的id和名称
		$.ajax({
			url : '${pageContext.request.contextPath}/guest/userAction!getTtrainerDatasByOrg.action',
			dataType : 'json',
			success : function(trainerMap) {
				names=trainerMap["trainerName"];
				codeNo=trainerMap["codeNo"];
				studentTotal=trainerMap["studentTotal"];
				reservationTotal=trainerMap["reservationTotal"];
				trainerType=trainerMap["trainerType"];
				
				$("#loop_output_data").append("<tr><th width=25%>学校名称</th>"+
				"<th width=18%>教练编号</th><th width=18%>总学员(人)</th><th width=18%>预约总数</th width=18%><th width=18%>教练性质</th></tr>");
				for(var i=0;i<schoolAreas.length;i++){
					$("#loop_output_data").append("<tr  id=trainer"+schoolAreas[i].value+"><th calss='am-danger'>"+schoolAreas[i].text+"</th>"
							+"<th colspan=4 calss=dateCount_total>共:"+trainerCount[i].value+"人(本部："+insideTotal[i].value+"人 ，外包："+outTotal[i].value+"人)"+"</th></tr>");
					for(var j=0;j<names.length;j++){
						if(names[j].value==schoolAreas[i].value){
							$("#loop_output_data").append("<tr>"+
									"<th>"+names[j].text+"</th>"+
									"<th>"+codeNo[j].text+"</th>"+
									"<th>"+studentTotal[j].value+"</th>"+
									"<th>"+reservationTotal[j].text+"</th>"+
									"<th>"+trainerType[j].value+"</th></tr>");
						}
					}
					$("#trainer"+schoolAreas[i].value).attr("class", "am-primary");
				}
			},
			error:function(err){
				alert("获取校区数据失败");
			}
		});
		
	}
    
  </script>

</body>
</html>
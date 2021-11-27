<%@ page language="java" pageEncoding="UTF-8"%>
<ul id="signup_analys">
	<li id="signupId">系统当前学员总人数为</li>
	
</ul>

<script type="text/javascript">
	var signuptotals= [];
	var signupSchoolIds=[];
	var studentDatas=[];
	var tempStudentDatas=[];
	$(function(){
		//从后台中获取校区的id和名称
		$.ajax({
			url : '${pageContext.request.contextPath}/demo/organizationAction!doNotNeedSession_org.action',
			dataType : 'json',
			success : function(d) {
				for(var i=0;i<d.length;i++){
					$("#signup_analys").append("<li id=signup"+d[i].value+" value="+d[i].value+">"+d[i].text+": "+"</li>");
					signuptotals.push(d[i].value);
				}
				$("#signup_analys li").css({float:'top'});
				//从后台中统计各分校总人数
				sentSignupTotals(signuptotals);
				//从后台中获取学员总人数
				getSignupStudentTotals();
			},
			errer:function(err){
				console.info(err);
			}
			
		});
	});
	
	//从后台中统计各分校总人数
	function sentSignupTotals(signuptotals){
		$.ajax({
			url : '${pageContext.request.contextPath}/demo/studentAnalysisAction!getStudentBySchoolArea.action',
			data:{organizationIds : signuptotals.join(',')},
			dataType : 'json',
			success : function(d) {
				for(var i=0;i<d.obj.length;i++){
					$("#signup"+d.obj[i].organizationId).append("[<font color=red>"+d.obj[i].totals+"</font>] 人")
					.append("<ul id=driver"+d.obj[i].organizationId+" class=trainerui >"+
							"<li id=todaySignup"+d.obj[i].organizationId+" class=signupid value=A1"+d.obj[i].organizationId+">今日报名人数: </li>"+
							"<li id=weekSignup"+d.obj[i].organizationId+" class=signupid value=A2"+d.obj[i].organizationId+">本周报名人数: </li>"+
							"<li id=monthSignup"+d.obj[i].organizationId+" class=signupid value=A3"+d.obj[i].organizationId+">本月报名人数: </li>"+
							"<li id=yearSignup"+d.obj[i].organizationId+" class=signupid value=B1"+d.obj[i].organizationId+">本年度报名人数: </li>"+
							"</ul><br>");
					
					signupSchoolIds.push(d.obj[i].organizationId);
				}
				$(".signupid").css({float:'top',width:'120px'});
				
				var tempArray=[];
				$.ajax({
					url : '${pageContext.request.contextPath}/demo/studentAnalysisAction!getSignupStudent.action',
					data:{organizationIds : signuptotals.join(',')},
					type: 'POST',//提交方式 可以选择post/get 推荐post   
					async: false,//同步异步
					dataType : 'json',
					success : function(d) {
						for(var i=0;i<d.obj.length;i++){
							studentDatas.push(d.obj[i].totals);
						}
						
						for (var x = 0; x < Math.ceil(d.obj.length / 4); x++) {
					          var start = x * 4;
					          var end = start + 4;
					          tempStudentDatas.push(studentDatas.slice(start, end));
					    }
						
						for(var i=0;i<signuptotals.length;i++){
							tempArray=tempStudentDatas[i].toString().trim().split(",");
							/* tempArray=drivatTypeTotals[i].toString().trim().split(","); */
							$("#todaySignup"+signupSchoolIds[i]).append("<font color=#0099FF>"+tempArray[0]+"</font>人");
							$("#weekSignup"+signupSchoolIds[i]).append("<font color=#0099FF>"+tempArray[1]+"</font>人");
							$("#monthSignup"+signupSchoolIds[i]).append("<font color=#0099FF>"+tempArray[2]+"</font>人");
							$("#yearSignup"+signupSchoolIds[i]).append("<font color=#0099FF>"+tempArray[3]+"</font>人");
						}
					}
				});
			}
		});
	}
	
	//从后台中获取学员总人数
	function getSignupStudentTotals(){
		$.ajax({
			url : '${pageContext.request.contextPath}/demo/studentAnalysisAction!getAllStudentCount.action',
			data:{organizationIds : signuptotals.join(',')},
			dataType : 'json',
			success : function(d) {
				$("#signupId").append(": [<font color=#0099FF>"+d.obj+"</font>] 人").css('color','red').css('font-size','10pt');
			}
		});
	}
	
</script>



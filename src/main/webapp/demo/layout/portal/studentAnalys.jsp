<%@ page language="java" pageEncoding="UTF-8"%>
<ul id="student_analys">
	<li id="studentId">系统当前学员总人数为</li>
	
</ul>

<script type="text/javascript">
	var studentDtotals= [];
	
	var studentDriverTypeIds=[];
	var studentDrivatTypeTotals=[];
	var studentDriveTypeSplit=[];
	$(function(){
		//从后台中获取校区的id和名称
		$.ajax({
			url : '${pageContext.request.contextPath}/demo/organizationAction!doNotNeedSession_org.action',
			dataType : 'json',
			success : function(d) {
				for(var i=0;i<d.length;i++){
					$("#student_analys").append("<li id=student"+d[i].value+" value="+d[i].value+">"+d[i].text+": "+"</li>");
					studentDtotals.push(d[i].value);
				}
				$("#student_analys li").css({float:'left'});
				//从后台中统计各分校总人数
				sentTotals(studentDtotals);
				//从后台中获取学员总人数
				getStudentTotals();
			},
			errer:function(err){
				console.info(err);
			}
		});
	});
	
	//从后台中统计各分校总人数
	function sentTotals(studentDtotals){
		$.ajax({
			url : '${pageContext.request.contextPath}/demo/studentAnalysisAction!getStudentBySchoolArea.action',
			data:{organizationIds : studentDtotals.join(',')},
			dataType : 'json',
			success : function(d) {
				for(var i=0;i<d.obj.length;i++){
					$("#student"+d.obj[i].organizationId).append("[<font color=red>"+d.obj[i].totals+"</font>] 人")
					.append("<ul id=studentdriver"+d.obj[i].organizationId+" class=studentui >"+
							"<li id=studentA1"+d.obj[i].organizationId+" class=studentli value=studentA1"+d.obj[i].organizationId+">A1: </li>"+
							"<li id=studentA2"+d.obj[i].organizationId+" class=studentli value=studentA2"+d.obj[i].organizationId+">A2: </li>"+
							"<li id=studentA3"+d.obj[i].organizationId+" class=studentli value=studentA3"+d.obj[i].organizationId+">A3: </li>"+
							"<li id=studentB1"+d.obj[i].organizationId+" class=studentli value=studentB1"+d.obj[i].organizationId+">B1: </li>"+
							"<li id=studentB2"+d.obj[i].organizationId+" class=studentli value=studentB2"+d.obj[i].organizationId+">B2: </li>"+
							"<li id=studentC1"+d.obj[i].organizationId+" class=studentli value=studentC1"+d.obj[i].organizationId+">C1: </li>"+
							"<li id=studentC2"+d.obj[i].organizationId+" class=studentli value=studentC2"+d.obj[i].organizationId+">C2: </li>"+
							"<li id=studentC3"+d.obj[i].organizationId+" class=studentli value=studentC3"+d.obj[i].organizationId+">C3: </li>"+
							"<li id=studentC4"+d.obj[i].organizationId+" class=studentli value=studentC4"+d.obj[i].organizationId+">C4: </li>"+
							"</ul><br>");
					studentDriverTypeIds.push(d.obj[i].organizationId);
				}
				$(".studentli").css({float:'left',width:'80px','list-style-type':'none'});
				var tempArray=[];
				
				$.ajax({
					url : '${pageContext.request.contextPath}/demo/studentAnalysisAction!getStudentCountByDriverType.action',
					data:{organizationIds : studentDtotals.join(',')},
					dataType : 'json',
					success : function(d) {
						for(var i=0;i<d.obj.length;i++){
							studentDrivatTypeTotals.push(d.obj[i].totals);
						}
						
						for (var x = 0; x < Math.ceil(d.obj.length / 9); x++) {
					          var start = x * 9;
					          var end = start + 9;
					          studentDriveTypeSplit.push(studentDrivatTypeTotals.slice(start, end));
					    }
						
						for(var i=0;i<studentDriverTypeIds.length;i++){
							tempArray=studentDriveTypeSplit[i].toString().trim().split(",");
							/* tempArray=drivatTypeTotals[i].toString().trim().split(","); */
							
							$("#studentA1"+driverTypeIds[i]).append("<font color=#0099FF>"+tempArray[0]+"</font>人");
							$("#studentA2"+driverTypeIds[i]).append("<font color=#0099FF>"+tempArray[1]+"</font>人");
							$("#studentA3"+driverTypeIds[i]).append("<font color=#0099FF>"+tempArray[2]+"</font>人");
							$("#studentB1"+driverTypeIds[i]).append("<font color=#0099FF>"+tempArray[3]+"</font>人");
							$("#studentB2"+driverTypeIds[i]).append("<font color=#0099FF>"+tempArray[4]+"</font>人");
							$("#studentC1"+driverTypeIds[i]).append("<font color=#0099FF>"+tempArray[5]+"</font>人");
							$("#studentC2"+driverTypeIds[i]).append("<font color=#0099FF>"+tempArray[6]+"</font>人");
							$("#studentC3"+driverTypeIds[i]).append("<font color=#0099FF>"+tempArray[7]+"</font>人");
							$("#studentC4"+driverTypeIds[i]).append("<font color=#0099FF>"+tempArray[8]+"</font>人");
							
						}
					}
				});
			}
		});
	}
	
	//从后台中获取学员总人数
	function getStudentTotals(){
		$.ajax({
			url : '${pageContext.request.contextPath}/demo/studentAnalysisAction!getAllStudentCount.action',
			data:{organizationIds : studentDtotals.join(',')},
			dataType : 'json',
			success : function(d) {
				$("#studentId").append(": [<font color=#0099FF>"+d.obj+"</font>] 人").css('color','red').css('font-size','10pt');
			}
		});
	}
	
</script>


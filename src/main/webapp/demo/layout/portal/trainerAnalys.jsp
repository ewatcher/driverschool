<%@ page language="java" pageEncoding="UTF-8"%>
<ul id="trainer_analys">
	<li id="trainerId">系统当前教练总人数为</li>
</ul>
<script type="text/javascript">
	var trainertotals= [];
	var driverTypeIds=[];
	var drivatTypeTotals=[];
	var driveTypeSplit=[];
	$(function(){
		//从后台中获取校区的id和名称
		$.ajax({
			url : '${pageContext.request.contextPath}/demo/organizationAction!doNotNeedSession_org.action',
			dataType : 'json',
			success : function(d) {
				for(var i=0;i<d.length;i++){
					$("#trainer_analys").append("<li id=trainer"+d[i].value+" class=schoolAreali value="+d[i].value+">"+d[i].text+": "+"</li>");
					
					trainertotals.push(d[i].value);
				}
				$("#trainer_analys li").css({float:'left'});
				//从后台中统计各分校总人数
				sentTrainerTotals(trainertotals);
				//从后台中获取教练总人数
				getTrainerTotals();
			}
		});
	});
	
	//从后台中统计各分校总人数
	function sentTrainerTotals(trainertotals){
		
		$.ajax({
			url : '${pageContext.request.contextPath}/demo/studentAnalysisAction!getTrainerBySchoolArea.action',
			data:{organizationIds : trainertotals.join(',')},
			dataType : 'json',
			success : function(d) {
				for(var i=0;i<d.obj.length;i++){
					$("#trainer"+d.obj[i].organizationId).append("[<font color=red>"+d.obj[i].totals+"</font>] 人")
					.append("<ul id=driver"+d.obj[i].organizationId+" class=trainerui >"+
							"<li id=A1"+d.obj[i].organizationId+" class=trainerli value=A1"+d.obj[i].organizationId+">A1: </li>"+
							"<li id=A2"+d.obj[i].organizationId+" class=trainerli value=A2"+d.obj[i].organizationId+">A2: </li>"+
							"<li id=A3"+d.obj[i].organizationId+" class=trainerli value=A3"+d.obj[i].organizationId+">A3: </li>"+
							"<li id=B1"+d.obj[i].organizationId+" class=trainerli value=B1"+d.obj[i].organizationId+">B1: </li>"+
							"<li id=B2"+d.obj[i].organizationId+" class=trainerli value=B2"+d.obj[i].organizationId+">B2: </li>"+
							"<li id=C1"+d.obj[i].organizationId+" class=trainerli value=C1"+d.obj[i].organizationId+">C1: </li>"+
							"<li id=C2"+d.obj[i].organizationId+" class=trainerli value=C2"+d.obj[i].organizationId+">C2: </li>"+
							"<li id=C3"+d.obj[i].organizationId+" class=trainerli value=C3"+d.obj[i].organizationId+">C3: </li>"+
							"<li id=C4"+d.obj[i].organizationId+" class=trainerli value=C4"+d.obj[i].organizationId+">C4: </li>"+
							"</ul><br>");
					//保存li的id标识，便于在后面添加人数
					driverTypeIds.push(d.obj[i].organizationId);
				}
				$(".trainerli").css({float:'left',width:'80px','list-style-type':'none'});
				
				/* getTrainerTotalsByDriverType(); */
				var tempArray=[];
				
				
				$.ajax({
					url : '${pageContext.request.contextPath}/demo/studentAnalysisAction!getTrainerCountByDriverType.action',
					data:{organizationIds : trainertotals.join(',')},
					dataType : 'json',
					success : function(d) {
						for(var i=0;i<d.obj.length;i++){
							drivatTypeTotals.push(d.obj[i].totals);
						}
						
						for (var x = 0; x < Math.ceil(d.obj.length / 9); x++) {
					          var start = x * 9;
					          var end = start + 9;
					          driveTypeSplit.push(drivatTypeTotals.slice(start, end));
					    }
						
						for(var i=0;i<driverTypeIds.length;i++){
							tempArray=driveTypeSplit[i].toString().trim().split(",");
							/* tempArray=drivatTypeTotals[i].toString().trim().split(","); */
							
							$("#A1"+driverTypeIds[i]).append("<font color=#0099FF>"+tempArray[0]+"</font>人");
							$("#A2"+driverTypeIds[i]).append("<font color=#0099FF>"+tempArray[1]+"</font>人");
							$("#A3"+driverTypeIds[i]).append("<font color=#0099FF>"+tempArray[2]+"</font>人");
							$("#B1"+driverTypeIds[i]).append("<font color=#0099FF>"+tempArray[3]+"</font>人");
							$("#B2"+driverTypeIds[i]).append("<font color=#0099FF>"+tempArray[4]+"</font>人");
							$("#C1"+driverTypeIds[i]).append("<font color=#0099FF>"+tempArray[5]+"</font>人");
							$("#C2"+driverTypeIds[i]).append("<font color=#0099FF>"+tempArray[6]+"</font>人");
							$("#C3"+driverTypeIds[i]).append("<font color=#0099FF>"+tempArray[7]+"</font>人");
							$("#C4"+driverTypeIds[i]).append("<font color=#0099FF>"+tempArray[8]+"</font>人");
							
						}
					}
				});
				
			}
		});
	}
	
	//从后台中获取教练总人数
	function getTrainerTotals(){
		$.ajax({
			url : '${pageContext.request.contextPath}/demo/studentAnalysisAction!getAllTrainerCount.action',
			data:{organizationIds : trainertotals.join(',')},
			dataType : 'json',
			success : function(d) {
				$("#trainerId").append(": [<font color=#0099FF>"+d.obj+"</font>] 人").css('color','red').css('font-size','10pt');;
			}
		});
	}
	
	
	
		
</script>

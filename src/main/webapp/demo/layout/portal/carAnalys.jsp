<%@ page language="java" pageEncoding="UTF-8"%>
<ul id="car_analys">
	<li id="carId" >系统当前车辆总数为</li>
	
</ul>

<script type="text/javascript">
	var cartotals= [];
	
	
	$(function(){
		//从后台中获取校区的id和名称
		$.ajax({
			url : '${pageContext.request.contextPath}/demo/organizationAction!doNotNeedSession_org.action',
			dataType : 'json',
			success : function(d) {
				for(var i=0;i<d.length;i++){
					$("#car_analys").append("<li id=car"+d[i].value+" value="+d[i].value+">"+d[i].text+": "+"</li>");
					cartotals.push(d[i].value);
				}
				//从后台中统计各分校总人数
				sentCarTotals(cartotals);
				//从后台中获取教练总人数
				getCarTotals();
			}
		});
	});
	
	//从后台中统计各分校车辆总数　
	function sentCarTotals(cartotals){
		$.ajax({
			url : '${pageContext.request.contextPath}/demo/studentAnalysisAction!getCarBySchoolArea.action',
			data:{organizationIds : cartotals.join(',')},
			dataType : 'json',
			success : function(d) {
				for(var i=0;i<d.obj.length;i++){
					$("#car"+d.obj[i].organizationId).append("[<font color=#0099FF>"+d.obj[i].totals+"</font>] 辆");
				}
			}
		});
	}
	
	//从后台中获取学员总人数
	function getCarTotals(){
		$.ajax({
			url : '${pageContext.request.contextPath}/demo/studentAnalysisAction!getAllCarCount.action',
			data:{organizationIds : cartotals.join(',')},
			dataType : 'json',
			success : function(d) {
				$("#carId").append(": "+d.obj+" 辆").css('color','red').css('font-size','10pt');
			}
		});
	}
	
</script>

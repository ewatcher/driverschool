<%@ page language="java" pageEncoding="UTF-8"%>
<ul id="children_analys">
	<li id="childrenId">系统当前学员总人数为</li>
	
</ul>

<script type="text/javascript">
	var childrentotals= [];
	
	
	$(function(){
		//从后台中获取校区的id和名称
		$.ajax({
			url : '${pageContext.request.contextPath}/demo/organizationAction!doNotNeedSession_org.action',
			dataType : 'json',
			success : function(d) {
				for(var i=0;i<d.length;i++){
					$("#children_analys").append("<li id=children"+d[i].value+" value="+d[i].value+">"+d[i].text+": "+"</li>");
					childrentotals.push(d[i].value);
				}
				//从后台中统计各分校总人数
				sentChildrenTotals(childrentotals);
				//从后台中获取学员总人数
				getChildrenStudentTotals();
			},
			errer:function(err){
				console.info(err);
			}
		});
	});
	
	//从后台中统计各分校总人数
	function sentChildrenTotals(childrentotals){
		$.ajax({
			url : '${pageContext.request.contextPath}/demo/studentAnalysisAction!getStudentBySchoolArea.action',
			data:{organizationIds : childrentotals.join(',')},
			dataType : 'json',
			success : function(d) {
				for(var i=0;i<d.obj.length;i++){
					$("#children"+d.obj[i].organizationId).append("[<font color=#0099FF>"+d.obj[i].totals+"</font>] 人");
				}
			}
		});
	}
	
	//从后台中获取学员总人数
	function getChildrenStudentTotals(){
		$.ajax({
			url : '${pageContext.request.contextPath}/demo/studentAnalysisAction!getAllStudentCount.action',
			data:{organizationIds : childrentotals.join(',')},
			dataType : 'json',
			success : function(d) {
				$("#childrenId").append(": "+d.obj+" 人").css('color','red').css('font-size','10pt');
			}
		});
	}
	
</script>


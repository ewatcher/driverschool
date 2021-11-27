<%@ page language="java" pageEncoding="UTF-8"%>
<ul id="exam_analys">
	<li id="examId">系统当前学员总人数为</li>
	
</ul>

<script type="text/javascript">
	var examtotals= [];
	var thoeryTotals=[];
	var passExamTotals=[];
	var lastThoeryTotals=[];
	$(function(){
		//从后台中获取校区的id和名称
		$.ajax({
			url : '${pageContext.request.contextPath}/demo/organizationAction!doNotNeedSession_org.action',
			dataType : 'json',
			success : function(d) {
				for(var i=0;i<d.length;i++){
					$("#exam_analys").append("<li id=exam"+d[i].value+" value="+d[i].value+">"+d[i].text+": "+"</li>");
					examtotals.push(d[i].value);
				}
				$("#exam_analys li").css('float','top');
				//从后台中统计各分校总人数
				sentExamTotals(examtotals);
				//从后台中获取学员总人数
				getExamStudentTotals();
			},
			errer:function(err){
				console.info(err);
			}
		});
	});
	
	//从后台中统计各分校总人数
	function sentExamTotals(examtotals){
		$.ajax({
			url : '${pageContext.request.contextPath}/demo/studentAnalysisAction!getStudentBySchoolArea.action',
			data:{organizationIds : examtotals.join(',')},
			dataType : 'json',
			success : function(d) {
				//1.遍历输出每个校区第个科目数据
				for(var i=0;i<d.obj.length;i++){
					$("#exam"+d.obj[i].organizationId).append("[<font color=red>"+d.obj[i].totals+"</font>] 人")
					.append("<ul id=subjectType"+d.obj[i].organizationId+" class=examui >"+
							"<li id=subject1title"+examtotals[i]+" class=driverTypeExamTitleli value=A1"+examtotals[i]+">"+
							"科目   　 　　   　总人数   　 　合格人数　        　       合格率　     　    未合格人数 </li>"+
							"<li id=subject1"+d.obj[i].organizationId+" class=examli value=subject1"+d.obj[i].organizationId+">科一(理论): </li>"+
							"<li id=subject2"+d.obj[i].organizationId+" class=examli value=subject2"+d.obj[i].organizationId+">科二(五项): </li>"+
							"<li id=subject3"+d.obj[i].organizationId+" class=examli value=subject3"+d.obj[i].organizationId+">科三(路考): </li>"+
							"<li id=subject4"+d.obj[i].organizationId+" class=examli value=subject4"+d.obj[i].organizationId+">科四(文明): </li>"+
							"</ul><br>");
				}
				$(".examui").css('float','top');
			}
		});
		
		
	}
	
	//从后台中获取学员总人数
	function getExamStudentTotals(){
		$.ajax({
			url : '${pageContext.request.contextPath}/demo/studentAnalysisAction!getAllStudentCount.action',
			data:{organizationIds : examtotals.join(',')},
			dataType : 'json',
			success : function(d) {
				$("#examId").append(": "+d.obj+" 人").css('color','red').css('font-size','10pt');
			}
		});
		
	}
	
	//Map格式数据结构
	function getMap() {//初始化map_,给map_对象增加方法，使map_像Map    
        var map_ = new Object();    
        map_.put = function(key, value) {    
            map_[key+'_'] = value;    
        };    
        map_.get = function(key) {    
            return map_[key+'_'];    
        };    
        map_.remove = function(key) {    
            delete map_[key+'_'];    
        };    
        map_.keyset = function() {    
            var ret = "";    
            for(var p in map_) {    
                if(typeof p == 'string' && p.substring(p.length-1) == "_") {    
                    ret += ",";    
                    ret += p.substring(0,p.length-1);    
                }    
            }    
            if(ret == "") {    
                return ret.split(",");    
            } else {    
                return ret.substring(1).split(",");    
            }    
        };    
        return map_;    
	}
	
	//计算合格率
	function GetPercent(num, total) { 
		num = parseFloat(num); 
		total = parseFloat(total); 
		if (isNaN(num) || isNaN(total)) { 
			return "-"; 
		} 
		return total <= 0 ? "0%" : (Math.round(num / total * 10000) / 100.00 + "%"); 
	} 
	
</script>


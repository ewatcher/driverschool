<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<link rel="stylesheet" href="../amaze/assets/css/amazeui.min.css" />
</head>
<body class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north',border:false,title:'查询功能'" style="height: 100px;overflow: hidden;" align="left">
      <!-- 人员信息查询表单 -->
      <form id="studentList_searchForm">
        <table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
          <tr>
          <th style="width: 60px;">所属校区:</th>
          <td><input id="mySchoolArea" name="organizationName" class="easyui-combotree" style="width:150px;"/></td>
          <th style="width: 60px;">学员编号:</th>
          <td><input id="demo_theory_driverType" name="studentNo" style="width: 155px;"/></td>
          <th style="width: 55px;">姓名:</th>
          <td><input id="demo_theory_name" name="studentName"  style="width: 155px;"/></td>
          <th style="width: 55px;">身份证号:</th>
          <td><input name="studentIdentityId"  style="width: 155px;"/></td>
        </tr>
          <tr>
            <th style="width: 55px;"></th>
            <td></td>
            <td colspan="2">
              <a href="javascript:void(0);" id="studentFile_a_searche" class="easyui-linkbutton" iconCls="icon-search" onclick="submitSearch();">查询</a>
              <a href="javascript:void(0);" id="studentFile_a_canel" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cleanSearch();">取消</a>
            </td>
            <th></th>
            <td></td>
          </tr>
        </table>
      </form>
    </div>
    <div data-options="region:'center',border:false" style="overflow: hidden;">
	<table id="signUpDataGrid" class="easyui-datagrid" title="学员点评" 
			data-options="rownumbers:true,fit:true,pagination:true,singleSelect:true,url:'${pageContext.request.contextPath}/demo/commentStudentAction!getStudentDatagrid.action',method:'get'">
		<thead>
			<tr>
                <th data-options="field:'organizationName',align:'center'" width="10%">所属校区</th>
				<th data-options="field:'name',align:'center'" width="15%">姓名</th>
				<th data-options="field:'studentNo',align:'center'" width="15%">学员编号</th>
                <th data-options="field:'sex',align:'center'" width="15%">性别</th>
                <th data-options="field:'trainerName',align:'center'" width="15%">教练</th>
				<th data-options="field:'address',align:'center'" width="15%">所在地</th>
                <th data-options="field:'id',align:'center',formatter:function(val,row,index){
                  return '<a href=javascript:void(0) class=easyui-linkbutton onclick=dbClick(' + index + ')>点评学员</a>';
                }" width="13%">功能</th>
			</tr>
		</thead>
	</table>
  </div>
    <div id="dlg" class="easyui-dialog" title="学员点评" style="width:500px;height:500px;padding:10px;" data-options="closed:true,iconCls:'icon-save',buttons:'#dlg-buttons'">
      <div class="doc-example" id="comments-div">
        <ul class="am-list" id="comments-list">
        <li class="am-g am-list-item-desced" >
          <span class="am-list-item-hd" style="color:blue;font-size:14px;">教练员：xxx 评论于：2016.04.05 21:00</span>
          <div class="am-list-item-text">这个是评论！</div>
        </li>
        </ul>
      </div>
    </div>
    <div id="dlg-buttons" style="text-align:center">
      <input class="easyui-textbox" id="sendText" data-options="multiline:true" value="" style="width:74%;height:60px;">
      <a href="javascript:void(0)" id="sendBtn" class="easyui-linkbutton" style="width:24%;height:60px">发表新的点评</a>
    </div>
	<script type="text/javascript">
		var mydatagrid = $('#signUpDataGrid');
		
		function dbClick(index){
			var row = mydatagrid.datagrid('getRows')[index];
			console.log(row);
			$('#dlg').dialog('open');
			$.ajax({
				url : '${pageContext.request.contextPath}/demo/commentStudentAction!datagrid.action?studentId=' + row.id,
				dataType : 'json',
				async: false,
				success : function(data) {
					console.log(data);
					$('#comments-list').children('li').remove();
    				for(var i = 0; i < data.rows.length; i++){
    					var item = data.rows[i];
    					var nodeLi = getMessageLi(row, item);
    					$('#comments-list').prepend(nodeLi); // 在前面添加
    				}
				},
				error : function(err){
					console.log(err);
					$.messager.show({
							title:'错误',
							msg:err.statusText,
							showType:'show'
						});
				}
			});
			
			$('#sendBtn').off('click');
			$('#sendBtn').on('click', function(){
				//console.log("发表点评:" + row.openId);
				$('#sendBtn').linkbutton('disable');
				var comments = $('#sendText').val();
				var postData = {};
				postData['studentId'] = row.id;
				postData['trainId'] = row.trainerId;
				postData['comments'] = comments;
				console.log(postData);
				$.ajax({
					url : '${pageContext.request.contextPath}/demo/commentStudentAction!addComment.action',  
					data : postData,
					type : 'post',
					cache : false,
					dataType : 'json',
					success : function(data) {
						console.log(data);
						$('#sendBtn').linkbutton('enable');
						if(data.success){
							var nodeLi = getMessageLi(row, data.obj);
							$('#comments-list').append(nodeLi); // 在末尾添加
						} else {
							$.messager.show({
								title:'错误',
								msg:data.msg,
								showType:'show'
							});
						}
					},
					error : function(err) {
						console.log(err);
						$.messager.show({
								title:'错误',
								msg:err.statusText,
								showType:'show'
							});
					}
				})
			});
		}
		
		//查询按钮功能函数
		function submitSearch() {
			var o = sy.serializeObject($('#studentList_searchForm'));
			console.log(o);
			mydatagrid.datagrid('load', o);
		}
		
		//取消查询功能按钮
		function cleanSearch() {
			mydatagrid.datagrid('load', {});
			//清空与查询有关的输入框
			$('#studentList_searchForm input').val('');
		}
		
		function getMessageLi(row, item){
			var trainerName = undefined;
			if(item.trainName != undefined && item.trainName != '' && item.trainName.length > 0){
				trainerName = item.trainName;
			} else {
				trainerName = row.trainerName;
			}
			var isRead = item.isRead == 1 ? "<span style='color:green;'>已读</span>" : "<span style='color:Crimson;'>未读</span>";
			var nodeLi = $('<li class="am-g am-list-item-desced"></li>'); 
			var nodeSpan = $('<span class="am-list-item-hd" style="color:#999;font-size:16px;"></span>');
			var nodeDiv1 = $('<div class="am-list-item-text" style="color:#000;padding:10px;font-size:14px;">');
			nodeSpan.html("教练:" + trainerName + "　　　　发表于:" + item.commentTime + "　　　　" + isRead);
			nodeDiv1.text(item.comments);
			nodeLi.append(nodeSpan);
			nodeLi.append(nodeDiv1);
			return nodeLi;
		}
		
		//农峰添加，校区数据过虑功能
		$.ajax({
			url : '${pageContext.request.contextPath}/demo/studentAction!getSchoolArea.action',
			dataType : 'json',
			success : function(d) {
				userSchoolArea=d;
				initSchoolArea($("#mySchoolArea"),d);
			}
		});
		
	</script>
</body>
</html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.tuocheng.util.base.Util"%>
<%
  String resourceSn = Util.objToString(request.getParameter("resourceSn"), "");
%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<link rel="stylesheet" href="../amaze/assets/css/amazeui.min.css" />
</head>
<body class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north',border:false,title:'查询功能'" style="height: 60px;overflow: hidden;" align="left">
      <!-- 菜单查询表单 -->
      <form id="weixin_searchForm">
        <table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
          <tr>
            <th >所属校区:</th>
            <td><input id="mySchoolArea" name="organizationId" class="easyui-combotree" style="width:150px;"/></td>
            <th >姓名:</th>
            <td><input id="realName" name="realName" style="width:150px;"/></td>
            <td>
              <a href="javascript:void(0);" id="student_a_searche" class="easyui-linkbutton" iconCls="icon-search" onclick="mySearch();">查询</a>
              <a href="javascript:void(0);" id="student_a_cancel" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancelSearch();">取消</a>
            </td>
            <td width="50%">&nbsp;</td>
          </tr>
        </table>
      </form>
    </div>
    <div data-options="region:'center',border:false" style="overflow: hidden;">
	   <table id="weixinUserDataGrid" class="easyui-datagrid" title="微信用户管理" 
			data-options=
			"rownumbers:true,
			fit:true,
			pagination:true,
			singleSelect:true,
			url:'${pageContext.request.contextPath}/demo/weixinUserAction!datagrid.action',
			method:'get',
			checkOnSelect : true,
			selectOnCheck : true,
			idField : 'id',
			sortName : 'createTime', 
			sortOrder : 'desc', 
			toolbar:toolbar,
			onDblClickRow:dbClick,
			onBeforeLoad:onMyBeforeLoad">
		  <thead>
			<tr>
                <th data-options="field:'organizationName',align:'center'" width="12%">所属机构</th>
				<th data-options="field:'icon',align:'center',formatter:formatIcon" width="7%">头像</th>
				<th data-options="field:'name',align:'center'" width="7%">昵称</th>
				<th data-options="field:'realName',align:'center'" width="7%">姓名</th>
				<th data-options="field:'sex',align:'center',formatter:function(val,row,index){
					if(val == 1) {
						return '男';
					} else if(val == 2) {
						return '女';
					} else {
						return '未知';
					}
				}" width="5%">性别</th>
				<th data-options="field:'city',align:'center'" width="7%">所在城市</th>
				<th data-options="field:'province',align:'center'" width="7%">所在省份</th>
				<th data-options="field:'visit',align:'center'" width="5%">访问次数</th>
				<th data-options="field:'msgSum',align:'center'" width="5%">消息总数</th>
				<th data-options="field:'createTime',align:'center'" width="10%">创建时间</th>
				<th data-options="field:'lastMsgContent',align:'left',formatter:function(val,row,index){
					var msg = '';
					if(val && val.length > 0) msg = val;
					return '<p style=width:100%;height:auto;text-align:left;white-space:normal;margin-right:25px; >' + msg + '</p>';
				}" width="35%">最新消息</th>
                <th data-options="field:'id',align:'center',formatter:function(val,row,index){
					return '<a href=javascript:void(0) class=easyui-linkbutton onclick=dbClick(' + index + ')>回复消息</a>';
				}" width="5%">功能</th>
			</tr>
		  </thead>
	   </table>
    </div>
    <div id="toolbar" class="datagrid-toolbar" style="height:auto">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="remove()">删除</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="cancel()">取消选择</a>
    </div>
    
	<div id="dlg" class="easyui-dialog" title="聊天记录" style="width:600px;height:500px;padding:10px;" data-options="closed:true,iconCls:'icon-save',buttons:'#dlg-buttons'">
    	<div class="doc-example" id="comments-div">
    		<ul class="am-comments-list am-comments-list-flip" id="comments-list">
    		</ul>
    	</div>
    </div>
    <div id="dlg-buttons" style="text-align:center">
    	<input class="easyui-textbox" id="sendText" data-options="multiline:true" value="" style="width:84%;height:60px;">
    	<a href="javascript:void(0)" id="sendBtn" class="easyui-linkbutton" style="width:14%;height:60px">回复</a>
    </div>
	<script type="text/javascript">
		var mydatagrid = $('#weixinUserDataGrid');
		var userACLs=[];
		var toolbar = [{
			id:'remove-btn-toolbar',
			text:'删除',
			iconCls:'icon-remove',
			handler:remove
		},'-',{
			text:'取消选择',
			iconCls:'icon-edit',
			handler:cancel
		},'-',{
			text:'刷新',
			iconCls:'icon-reload',
			handler:reload
		},'-'];
		
		function getUserACL(){
			//初始化主菜单
			$.ajax({
				url : '${pageContext.request.contextPath}/demo/aclAction!getUserPermissions.action',
				data:{resourceSn:'<%=resourceSn%>'},
				type : 'POST', //提交方式 可以选择post/get 推荐post   
				async : false, //同步异步
				dataType : 'json',
				success : function(data) {
					for (var i = 0; i < data.length; i++) {
						userACLs.push(data[i]);
					}
				}
			});
		}
		
		function onMyBeforeLoad(){
			getUserACL();
			if(!userACLs[2]){
				$("#edit-btn-toolbar").linkbutton('disable');
			}
			if(!userACLs[3]){
				$("#remove-btn-toolbar").linkbutton('disable');
			}
		}
		
		function remove(){
			var rows = mydatagrid.datagrid('getChecked');
			var ids = [];
			if (rows.length > 0) {
				parent.sy.messagerConfirm(
					'请确认',
					'您要删除当前所选择项信息？',
					function(r) {
						if (r) {
							for (var i = 0; i < rows.length; i++) {
								ids.push(rows[i].id);
							}
							$.ajax({
								url : '${pageContext.request.contextPath}/demo/weixinUserAction!delete.action',
								data : {ids : ids.join(',')},
								dataType : 'json',
								success : function(d) {
									mydatagrid.datagrid('load');
									mydatagrid.datagrid('unselectAll');
									parent.sy.messagerShow({
										title : '提示信息',
										msg : d.msg
									});
								}
							});
						}
					});
			} else {
				parent.sy.messagerAlert('提示信息', '请勾选要删除的记录！', 'error');
			}
		}
	
		function cancel(){
			//取消所有的选中行
			mydatagrid.datagrid('clearSelections');
			//取消当前页面的选中行
			mydatagrid.datagrid('unselectAll');
		}
		function reload(){
			mydatagrid.datagrid('reload');
		}
	
		function formatIcon(val, rows, index){
			return "<img src='" + formatWeixinIcon(val, 64) + "' alt='...' class='am-comment-avatar' width='64' height='64' style='margin-left:20px;' />";
		}
		function dbClick(index){
			var row = $('#weixinUserDataGrid').datagrid('getRows')[index];
			$('#dlg').dialog('open');
			$.ajax({
				url : '${pageContext.request.contextPath}/demo/weixinUserAction!getMessageListByOpenId.action?openId=' + row.openId,
				dataType : 'json',
				async: false,
				success : function(data) {
					console.log(data);
					$('#comments-list').children('li').remove();
    				for(var i = 0; i < data.length; i++){
    					var item = data[i];
    					var nodeLi = getMessageLi(row, item);
    					$('#comments-list').prepend(nodeLi); // 在前面添加
    				}
    				setTimeout(function () { 
    					console.log('xxxxxxx');
    					$('#comments-list').animate({scrollTop:800}, 0);
    					console.log('xxsssss');
    			    }, 1000);
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
				console.log("发送消息:" + row.openId);
				$('#sendBtn').linkbutton('disable');
				var content = $('#sendText').val();
				console.log(content);
				var postData = {};
				postData['openId'] = row.openId;
				postData['content'] = content;
				//console.log(JSON.stringify(postData));
				$.ajax({
					url : '${pageContext.request.contextPath}/api/weixinApiAction!sendTextMessage.action',  
					data : postData,
					type : 'post',
					cache : false,
					dataType : 'json',
					success : function(data) {
						console.log(data);
						$('#sendBtn').linkbutton('enable');
						if(data.success){
							var nodeLi = getMessageLi(data, data);
							$('#comments-list').append(nodeLi); // 在末尾添加
							$('#dlg-buttons input').attr("value", '');
							$('#dlg-buttons textarea').attr("value", '');
							$('#comments-div').scrollTop = 300;
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
		
		function getMessageLi(row, item){
			var nodeLi = $('<li class="am-comment am-comment-highlight"></li>'); 
			var nodeImg = $('<img class="am-comment-avatar" width="48" height="48" >');
			var nodeDiv1 = $('<div class="am-comment-main">');
			var nodeHeader = $('<header class="am-comment-hd">');
			var nodeDiv2 = $('<div class="am-comment-meta">');
			var nodeSpan = $('<span class="am-comment-author">');
			var nodeTime = $('<time datetime="2013-07-27T04:54:29-07:00" >');
			var nodeDiv3 = $('<div class="am-comment-bd">');
			var nodeP = $('<p>');
			nodeP.text(item.msgContent);
			nodeDiv3.append(nodeP);
			nodeTime.text(" 发表于：" + item.createTime);
			nodeDiv2.append(nodeSpan);
			nodeDiv2.append(nodeTime);
			nodeHeader.append(nodeDiv2);
			nodeDiv1.append(nodeHeader);
			nodeDiv1.append(nodeDiv3);
			nodeLi.append(nodeImg);
			nodeLi.append(nodeDiv1);
			if(item.reply == 1){
				nodeSpan.text(item.fromNickName);
				nodeImg.attr('src', '${pageContext.request.contextPath}/images/icon.jpg');
				nodeLi.addClass('am-comment-flip');
			} else {
				nodeSpan.text(row.name);
				nodeImg.attr('src', formatWeixinIcon(row.icon, 46));
			}
			return nodeLi;
		}
		
		//查询按钮功能函数
		function submitSearch() {
			var o = sy.serializeObject($('#menu_searchForm'));
			console.log(o);
			$('#weixinUserDataGrid').datagrid('reload', o);
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
		//初始化校区
		function initSchoolArea(object,userSchoolArea){
			object.combotree({
				url:'${pageContext.request.contextPath}/demo/organizationAction!doNotNeedSession_tree.action',
				lines:true,
				required:true,
			});
			//非系统管理员，禁止查询其他校区
			if(userSchoolArea!==null){
				object.combotree('setValue',userSchoolArea);
				object.combotree('readonly',true);
			}else{
				object.combotree('readonly',false);
			}
		} 
		
		//查询按钮功能函数
		function mySearch() {
			$("#weixinUserDataGrid").datagrid('load', sy.serializeObject($('#weixin_searchForm')));
		}

		//取消查询功能按钮
		function cancelSearch() {
			$("#weixinUserDataGrid").datagrid('load', {});
			//清空与查询有关的输入框
			$('#realName').val('');
		}
	</script>
</body>
</html>
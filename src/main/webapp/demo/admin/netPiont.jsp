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
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询功能'"
		style="height: 100%;overflow: hidden;" align="left">
		<!-- 人员信息查询表单 -->
		<form id="demo_netpiont_searchForm">
			<input type="hidden" id="propertyTypeVal" name="propertyType"
				value="0">
			<table class="tableForm datagrid-toolbar"
				style="width: 100%;height: 100%;">
				<tr>
					<th style="width: 65px;">所属部门:</th>
					<td><input id="demo_NetPiont_schoolArea" name="schoolArea"
						style="width:150px;" /></td>
					<th style="width: 65px;">姓名:</th>
					<td><input name="name" class="input_clean"
						style="width:120px;" /></td>
					<th style="width: 65px;">手机号：</th>
					<td><input name="phone" class="input_clean"
						style="width:120px;" /></td>
					<th style="width: 65px;">发送人：</th>
					<td><input name="operator" class="input_clean"
						style="width:120px;" /></td>
				</tr>
				<tr>
					<th style="width: 65px;">发送日期:</th>
					<td><input id="input_clean_start" name="dateStart"
						class="easyui-datebox" data-options="editable:false"
						style="width: 95px;" />至:<input id="input_clean_end"
						name="dateEnd" class="easyui-datebox"
						data-options="editable:false" style="width: 95px;" /></td>
					<td colspan="2"><a href="javascript:void(0);"
						id="sms_a_searche" class="easyui-linkbutton" iconCls="icon-search"
						onclick="mySearch();">查询</a> <a href="javascript:void(0);"
						id="sms_a_canel" class="easyui-linkbutton" iconCls="icon-cancel"
						onclick="cleanSearch();">取消</a></td>
				</tr>
			</table>
		</form>
		<!-- 系统配置tab -->
		<div id="feeTabs" class="easyui-tabs" style="width:100%;height:90%;">
			<!-- 网点参数配置 tab -->
			<div title="网点参数配置" style="padding:2px;">
				<table id="demo_NetPiont_config" class="easyui-datagrid"
					title="报名网点详细列表"
					data-options="
				url : '${pageContext.request.contextPath}/demo/netPiontAction!datagrid.action',
				title : '报名网点详细列表!', 
				pagination : true,
				pagePosition : 'bottom', 
				pageSize : 20, 
				pageList : [ 10, 20, 30, 40,50 ,100,200 ], 
				fit : true,
				fitColumns : false, 
				nowrap : false, 
				border : false,
				rownumbers: true,
				idField : 'id', 
				sortName : 'orderNo', 
				sortOrder : 'desc', 
				checkOnSelect : true,
				selectOnCheck : true,
				striped : true,
				nowrap:true,
				toolbar:toolbar,
				onClickRow:onClickRow,
				onBeforeLoad:onMyBeforeLoad,
				onLoadSuccess:onloadNetPiont,
				onRowContextMenu:netPiontMenu">

					<!-- 行编辑时冻结列 -->
					<thead data-options="frozen:true">
						<tr>
							<th
								data-options="field:'id',width:100,align:'left',checkbox:true,">编号</th>
							<th data-options="field:'schoolAreaName',width:100,align:'left',">所属校区</th>
							<th
								data-options="field:'ownId',width:80,align:'center',editor:'text',">标识</th>
							<th
								data-options="field:'name',width:150,align:'center',editor:'text',">名称</th>
							<th
								data-options="field:'address',width:250,align:'left',editor:'text',">地址</th>
							<th
								data-options="field:'phone',width:100,align:'center',editor:'text',">报名电话</th>
							<th
								data-options="field:'enableFlag',width:80,align:'center',editor:{type:'checkbox',options:{on:1,off:0}},formatter:formatBinaryFlag">启用状态</th>
						</tr>
					</thead>
					<thead>
						<tr>
							<th
								data-options="field:'orderNo',width:70,align:'center',editor:'numberbox',">序号</th>
							<th
								data-options="field:'createTime',width:100,align:'center',editor:'datebox',formatter:formatTimeYMD">创建日期</th>
							<th
								data-options="field:'operator',width:70,align:'center',">创建人</th>
							<th
								data-options="field:'comment',width:200,align:'center',editor:'text',formatter:showAllMsg">备注</th>
							<th
								data-options="field:'schoolArea',width:100,align:'center',hidden:true">校区标识</th>
						</tr>
					</thead>
				</table>
			</div>
			<div title="预约参数配置" closable="false" style="padding:2px;">
				<table id="demo_NetPiont_person" class="tableForm"></table>
			</div>
		</div>
	</div>

	<!-- 操作按钮事件 -->

	<div id="demo_NetPiont_menu" class="easyui-menu"
		style="width:120px;display: none;">
		<div id="add-menu" onclick="append();" data-options="iconCls:'icon-add'">增加</div>
		<div id="del-menu"  onclick="remove();" data-options="iconCls:'icon-remove'">删除</div>
		<div id="edit-menu" onclick="edit();" data-options="iconCls:'icon-edit'">更新</div>
	</div>
	<script type="text/javascript">
	
		var userSchoolArea=getUserSchoolArea();
		var userACLs=[];
		$(function() {
			userSchoolArea=getUserSchoolArea();
		});	
		var mydatagrid = $('#demo_NetPiont_config');
		var toolbar = [{
			id:'edit-btn-toolbar',
			text:'添加',
			iconCls:'icon-add',
			handler:append
		},'-',{
			id:'remove-btn-toolbar',
			text:'删除',
			iconCls:'icon-remove',
			handler:remove
		},'-',{
			id:'edit-btn-toolbar',
			text:'修改',
			iconCls:'icon-edit',
			handler:edit
		},'-',{
			text:'保存',
			iconCls:'icon-save',
			handler:save
		},'-',{
			text:'取消',
			iconCls:'icon-undo',
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
			if(!userACLs[0]){ //Create
	            $("#add-btn-toolbar").linkbutton('disable'); 
	            $("#add-menu").hide(); 
	        }
			if(!userACLs[2]){//Update
				$("#edit-btn-toolbar").linkbutton('disable');
				$("#role-btn-toolbar").linkbutton('disable'); 
				 $("#edit-menu").hide();
				 $("#role-menu").hide();
			}
			if(!userACLs[3]){//delete
				$("#remove-btn-toolbar").linkbutton('disable');  
				 $("#del-menu").hide();
			}
		}
		
		var editIndex = undefined;  // 编辑状态的行标
		var clickIndex = undefined; // 选中状态的行标
		function onClickRow(index,row){
			clickIndex = index;
		}
		function endEditing(){
			if (editIndex == undefined){return true;}
			if (mydatagrid.datagrid('validateRow', editIndex)){ // 验证行行（editIndex）是否有效
				mydatagrid.datagrid('endEdit', editIndex); // 结束编辑
				return true;
			} else {
				return false;
			}
		}
		
		function append(){
			var temp=$("#demo_NetPiont_schoolArea").combotree('getValue');
			if(null==temp||undefined==temp||temp==''){
				$.messager.alert('Warning', '所属部门不能为空！');
				return;
			}
			if (endEditing()){
				disableButton(); // 禁用功能按钮
				//mydatagrid.datagrid('appendRow', {'driverTypeId':1}); // 追加一个新行。新的行将被添加在最后的位置,后面参数为字段默认值
				mydatagrid.datagrid('appendRow', {'id':null,'schoolArea':temp}); // 追加一个新行。新的行将被添加在最后的位置,后面参数为字段默认值
				editIndex = mydatagrid.datagrid('getRows').length - 1; // 得到行下标
				mydatagrid.datagrid('selectRow', editIndex).datagrid('beginEdit', editIndex); // 开启编辑模式
			}
		}
		
		function edit(){
			console.log(clickIndex);
			if (endEditing()){
				if(clickIndex != undefined && mydatagrid.datagrid('validateRow', clickIndex)){
					disableButton(); // 禁用功能按钮
					editIndex = clickIndex;
					mydatagrid.datagrid('selectRow', editIndex).datagrid('beginEdit', editIndex); // 开启编辑模式
				} else {
					$.messager.alert('Warning', '请选择需要修改的行！','question');
				}
			}
		}
		
		function remove(){
			var rows = mydatagrid.datagrid('getChecked');
			var ids = [];
			if (rows.length > 0) {
				parent.sy
						.messagerConfirm(
								'请确认',
								'您要删除当前所选择项信息？是，将被删除！！！',
								function(r) {
									if (r) {
										for (var i = 0; i < rows.length; i++) {
											ids.push(rows[i].id);
										}
										$.ajax({
											url : '${pageContext.request.contextPath}/demo/netPiontAction!delete.action',
													data : {
														ids : ids.join(',')
													},
													dataType : 'json',
													success : function(d) {
														mydatagrid.datagrid('load');
														mydatagrid.datagrid('unselectAll');
														mydatagrid.datagrid('uncheckAll');
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
		
		function save(){
			if (endEditing()){
				var insertRows = mydatagrid.datagrid('getChanges','inserted'); // 得到新增的数据
   				var updateRows = mydatagrid.datagrid('getChanges','updated'); // 得到更新的数据
   				
   				if(insertRows.length > 0){
   					saveSubmit('${pageContext.request.contextPath}/demo/netPiontAction!add.action', insertRows[0]);
   				} else if(updateRows.length > 0) {
   					saveSubmit('${pageContext.request.contextPath}/demo/netPiontAction!edit.action', updateRows[0]);
   				} 
   			}
			enableButton(); // 开启功能按钮
		}
		//取消
		function cancel(){
			mydatagrid.datagrid('rejectChanges'); // 回滚，撤销上次保存至今的所有编辑操作
			mydatagrid.datagrid('unselectAll');
			mydatagrid.datagrid('uncheckAll');
			editIndex = undefined;
			clickIndex = undefined;
			enableButton(); // 开启功能按钮
		}
		//刷新
		function reload(){
			mydatagrid.datagrid('reload',{});
			mydatagrid.datagrid('unselectAll');
			mydatagrid.datagrid('uncheckAll');
			editIndex = undefined;
			clickIndex = undefined;
			enableButton(); // 开启功能按钮
		}
		function disableButton(){
			$('#add-btn-toolbar').linkbutton('disable');
			$('#edit-btn-toolbar').linkbutton('disable');
			$('#remove-btn-toolbar').linkbutton('disable');
		}
		function enableButton(){
			if(!userACLs[0]){ //Create
	            $("#add-btn-toolbar").linkbutton('disable'); 
	            $("#add-menu").hide(); 
	        }else{
	        	$("#add-btn-toolbar").linkbutton('enable'); 
	            $("#add-menu").show(); 
	        }
			if(!userACLs[2]){//Update
				$("#edit-btn-toolbar").linkbutton('disable');
				$("#role-btn-toolbar").linkbutton('disable'); 
				 $("#edit-menu").hide();
				 $("#role-menu").hide();
			}else{
				$("#edit-btn-toolbar").linkbutton('enable');
				$("#role-btn-toolbar").linkbutton('enable'); 
				 $("#edit-menu").show();
				 $("#role-menu").show();
			}
			if(!userACLs[3]){//delete
				$("#remove-btn-toolbar").linkbutton('disable');  
				 $("#del-menu").hide();
			}else{
				$("#remove-btn-toolbar").linkbutton('enable');  
				 $("#del-menu").show();
			}
		}
		function saveSubmit(url, data){
			//提交修改
			$.ajax({
				url : url,
				data : data,
				dataType : 'json',
				async:false,
				success : function(data) {
					console.log(data);
					$.messager.show({
							title:'提示',
							msg:data.msg,
							showType:'show'
						});
					mydatagrid.datagrid('reload');
				},
				error : function(err){
					console.log(err);
					$.messager.show({
							title:'错误',
							msg:err.statusText,
							showType:'show'
						});
					mydatagrid.datagrid('reload');
				}
			});
		}
		//查询按钮功能函数
		function mySearch() {
			var o = sy.serializeObject($('#demo_netpiont_searchForm'));
			mydatagrid.datagrid('load', o);
		} 
		//取消查询功能按钮
		function cleanSearch() {
			mydatagrid.datagrid('load', {});
			//清空与查询有关的输入框
			$('#demo_netpiont_searchForm input').val('');
		}
		
		function onloadNetPiont(data){
			//修改序号bug
			mydatagrid.datagrid("fixRownumber");
			//初始化校区选项
			initSchoolArea($("#demo_NetPiont_schoolArea"),userSchoolArea);
			//添加回车事件
			bindingKeypressEvent($(".keypress_event"),mydatagrid,$("#demo_netpiont_searchForm"));
			//datagrid没有数据时显示没有记录信息
			if(data.total==0){
				mydatagrid.datagrid('appendRow', {
					schoolAreaName:  '<div style="text-align:center;color:red">没有记录信息！</div>' })
				.datagrid('mergeCells', { index: 1, field: 'schoolAreaName', colspan:8 });
			}
		}
		
		//右健菜单
		function netPiontMenu(e, rowIndex, rowData){
			e.preventDefault();
			//取消当前页被选中的所有行
			$(this).datagrid('unselectAll');
			$(this).datagrid('selectRow', rowIndex);
			$('#demo_NetPiont_menu').menu('show', {
				left : e.pageX,
				top : e.pageY
			});
		}
	</script>
</body>
</html>
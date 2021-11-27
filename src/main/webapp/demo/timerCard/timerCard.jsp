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
	<table id="timerCardDataGrid" class="easyui-datagrid" title="科目学时安排表" 
			data-options="rownumbers:true,pageSize : 20,singleSelect:true,url:'${pageContext.request.contextPath}/demo/timerCardAction!datagrid.action',method:'get',toolbar:toolbar,onClickRow: onClickRow">
		<thead>
			<tr>
				<th data-options="field:'driverTypeId',width:150,align:'center',
						formatter:function(value, rows){
								return rows.driverTypeName;
							},
						editor:{
							type:'combobox',
							options:{
								valueField:'value',
								textField:'text',
								method:'get',
								url:'${pageContext.request.contextPath}/demo/json/driverType_combobox.json',
								required:true
							}
						}" rowspan="2">车型</th>
				<th data-options="field:'totalHours',width:120,align:'center',editor:'numberbox'" rowspan="2">总学时</th>
				<th data-options="field:'oneHours',width:100,align:'center',editor:'numberbox'" rowspan="2">科目一</th>
				<th colspan="3">科目二</th>
				<th colspan="3">科目三</th>
			</tr>
			<tr>
				<th data-options="field:'twosTheoryHours',width:100,align:'center',editor:'numberbox'">理论</th>
				<th data-options="field:'twosOperateHours',width:100,align:'center',editor:'numberbox'">实操</th>
				<th data-options="field:'twosSimulatorHours',width:100,align:'center',editor:'numberbox'">模拟</th>
				<th data-options="field:'threesTheoryHours',width:100,align:'center',editor:'numberbox'">理论</th>
				<th data-options="field:'threesOperateHours',width:100,align:'center',editor:'numberbox'">实操</th>
				<th data-options="field:'threesSimulatorHours',width:100,align:'center',editor:'numberbox'">模拟</th>
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
		var mydatagrid = $('#timerCardDataGrid');
		var toolbar = [{
			id:'add-btn-toolbar',
			text:'添加',
			iconCls:'icon-add',
			handler:append
		},'-',{
			id:'edit-btn-toolbar',
			text:'修改',
			iconCls:'icon-edit',
			handler:edit
		},'-',{
			id:'del-btn-toolbar',
			text:'删除',
			iconCls:'icon-remove',
			handler:remove
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
		
		var editIndex = undefined;  // 编辑状态的行标
		var clickIndex = undefined; // 选中状态的行标
		function onClickRow(index){
			clickIndex = index;
		}
		function endEditing(){
			if (editIndex == undefined){return true;}
			if (mydatagrid.datagrid('validateRow', editIndex)){ // 验证行行（editIndex）是否有效
				var ed = mydatagrid.datagrid('getEditor', {index:editIndex, field:'driverTypeId'}); // 获取指定的编辑器
				if(ed){ // 如果处于编辑状态
					var name = $(ed.target).combobox('getText'); // 得到combobox的文本
					mydatagrid.datagrid('getRows')[editIndex]['driverTypeName'] = name; // 把编辑器里的值赋与json数据里的对应字段
					mydatagrid.datagrid('endEdit', editIndex); // 结束编辑
					editIndex = undefined; // 重置编辑行标记
				}
				return true;
			} else {
				return false;
			}
		}
		function append(){
			if (endEditing()){
				disableButton(); // 禁用功能按钮
				mydatagrid.datagrid('appendRow', {'driverTypeId':1}); // 追加一个新行。新的行将被添加在最后的位置,后面参数为字段默认值
				editIndex = mydatagrid.datagrid('getRows').length - 1; // 得到行下标
				mydatagrid.datagrid('selectRow', editIndex).datagrid('beginEdit', editIndex); // 开启编辑模式
			}
		}
		function edit(){
			if (endEditing()){
				if(clickIndex != undefined && mydatagrid.datagrid('validateRow', clickIndex)){
					disableButton(); // 禁用功能按钮
					editIndex = clickIndex;
					mydatagrid.datagrid('selectRow', editIndex).datagrid('beginEdit', editIndex); // 开启编辑模式
				} else {
					$.messager.alert('Warning', '请选择需要修改的行！');
				}
			}
		}
		function remove(){
			if (endEditing()){
				if (clickIndex != undefined && mydatagrid.datagrid('validateRow', clickIndex)){
					editIndex = clickIndex;
					var row = mydatagrid.datagrid('getRows')[editIndex];
					$.messager.confirm('Confirm','您确定需要删除[ ' + row.driverTypeName + ' ]的数据吗?',function(r){
					    if (r){
					    	saveSubmit('${pageContext.request.contextPath}/demo/timerCardAction!delete.action', {'ids':row.id}); // 删除数据
					    }
					});
				} else {
					$.messager.alert('Warning', '请选择需要删除的行！');
				}
			}
		}
		function save(){
			if (endEditing()){
				var insertRows = mydatagrid.datagrid('getChanges','inserted'); // 得到新增的数据
   				var updateRows = mydatagrid.datagrid('getChanges','updated'); // 得到更新的数据
   				//var deleteRows = mydatagrid.datagrid('getChanges','deleted'); // 得到删除的数据
   				
   				if(insertRows.length > 0){
   					saveSubmit('${pageContext.request.contextPath}/demo/timerCardAction!add.action', insertRows[0]);
   				} else if(updateRows.length > 0) {
   					saveSubmit('${pageContext.request.contextPath}/demo/timerCardAction!edit.action', updateRows[0]);
   				} 
   			}
			enableButton(); // 开启功能按钮
		}
		function cancel(){
			mydatagrid.datagrid('rejectChanges'); // 回滚，撤销上次保存至今的所有编辑操作
			editIndex = undefined;
			clickIndex = undefined;
			enableButton(); // 开启功能按钮
		}
		function reload(){
			mydatagrid.datagrid('reload');
			enableButton(); // 开启功能按钮
		}
		function disableButton(){
			$('#add-btn-toolbar').linkbutton('disable');
			$('#edit-btn-toolbar').linkbutton('disable');
			$('#remove-btn-toolbar').linkbutton('disable');
		}
		function enableButton(){
			$('#add-btn-toolbar').linkbutton('enable');
			$('#edit-btn-toolbar').linkbutton('enable');
			$('#remove-btn-toolbar').linkbutton('enable');
		}
		function saveSubmit(url, data){
			//提交修改
			console.log(url);
			console.log(data);
			
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
	</script>
</body>
</html>
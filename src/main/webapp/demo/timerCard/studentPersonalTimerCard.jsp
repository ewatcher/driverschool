<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<table id="studentTimerCardDataGrid" class="easyui-datagrid" title="学员计时表" 
				data-options="rownumbers:true,fit:true,pagination:true,singleSelect:true,url:'${pageContext.request.contextPath}/demo/studentTimerCardAction!personalDatagrid.action',method:'get',toolbar:toolbar,onClickRow: onClickRow">
			<thead>
				<tr>
					<th data-options="field:'schoolArea',width:100,align:'center'" rowspan="2">所属校区</th>
					<th data-options="field:'studentName',width:100,align:'center'" rowspan="2">学生名称</th>
					<th data-options="field:'studentNo',width:120,align:'center'" rowspan="2">学员编号</th>
					<th data-options="field:'driverTypeId',width:90,align:'center',formatter:formatterDriverType" rowspan="2">车型</th>
					<th data-options="field:'totalHours',width:100,align:'center',editor:'numberbox'" rowspan="2">总学时</th>
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
	</div>
	<script type="text/javascript">
		var mydatagrid = $('#studentTimerCardDataGrid');
		var toolbar = [{
			text:'刷新',
			iconCls:'icon-reload',
			handler:reload
		},'-'];
		
		var editIndex = undefined;  // 编辑状态的行标
		var clickIndex = undefined; // 选中状态的行标
		function onClickRow(index){
			clickIndex = index;
			console.log("点击行:" + clickIndex);
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
		
		function edit(){
			console.log(clickIndex);
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
		
		function save(){
			if (endEditing()){
   				var updateRows = mydatagrid.datagrid('getChanges','updated'); // 得到更新的数据
   				
   				console.log(updateRows);
   				
   				if(updateRows.length > 0) {
   					saveSubmit('${pageContext.request.contextPath}/demo/studentTimerCardAction!edit.action', updateRows[0]);
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
			$('#edit-btn-toolbar').linkbutton('disable');
		}
		function enableButton(){
			$('#edit-btn-toolbar').linkbutton('enable');
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
		//查询按钮功能函数
		function submitSearch() {
			var o = sy.serializeObject($('#studentTimerCard_searchForm'));
			console.log(o);
			mydatagrid.datagrid('load', o);
		} 
		//取消查询功能按钮
		function cleanSearch() {
			mydatagrid.datagrid('load', {});
			//清空与查询有关的输入框
			$('#studentTimerCard_searchForm input').val('');
		}
		function formatterDriverType(value, row, index){
			if(value==1){
				return 'A1';
			}else if(value==2){
				return 'A2';
			}else if(value==3){
				return 'A2';
			}else if(value==4){
				return 'B1';
			}else if(value==5){
				return 'B2';
			}else if(value==6){
				return 'C1';
			}else if(value==7){
				return 'C2';
			}else if(value==8){
				return 'C3';
			}else if(value==9){
				return 'C4';
			}else if(value==10){
				return 'D';
			}else if(value==11){
				return 'E';
			}else if(value==12){
				return 'F';
			}else if(value==13){
				return 'M';
			}else if(value==14){
				return 'N';
			}else{
				return '';
			}
		}
	</script>
</body>
</html>
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
	<div data-options="region:'north',border:false,title:'查询功能'" style="height: 110px;overflow: hidden;" align="left">
		<!-- 学员信息查询表单 -->
		<form id="studentTimerCard_searchForm">
			<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
				<tr>
					<th >姓名:</th>
					<td><input name="name" style="width:150px;" /></td>
					<th>身份证号：</th>
					<td><input name="identityId" style="width:150px;" /></td>
					<th >所属校区:</th>
					<td><input id="mySchoolArea" name="organizationName" class="easyui-combotree" style="width:150px;"/></td>
				</tr>
				<tr>
					<th>报名日期:</th>
					<td><input name="createTimeStart"
						class="easyui-datetimebox" data-options="editable:false"
						style="width: 155px;" /></td>
					<th>至</th>
					<td><input name="createTimeEnd"
						class="easyui-datetimebox" data-options="editable:false"
						style="width: 155px;" />
					</td>
					<th>编号：</th>
					<td><input name="id" style="width:150px;" /></td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
                    <td>
                      &nbsp;
                    </td>
					<td colspan="2">
						<a href="javascript:void(0);" id="student_a_searche" class="easyui-linkbutton" iconCls="icon-search" onclick="submitSearch();">查询</a>
						<a href="javascript:void(0);" id="student_a_canel" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cleanSearch();">取消</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<table id="studentTimerCardDataGrid" class="easyui-datagrid" title="学员计时表" 
				data-options="rownumbers:true,
				pageSize : 20,
				fit:true,
				pagination:true,
				singleSelect:true,
				url:'${pageContext.request.contextPath}/demo/studentTimerCardAction!datagrid.action',
				method:'get',
				toolbar:toolbar,
				onClickRow: onClickRow,
				onBeforeLoad:initUserACL">
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
		var userACLs=[];
		$(function() {
			getUserACL();
		});
	
		var mydatagrid = $('#studentTimerCardDataGrid');
		var toolbar = [{
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
   					saveSubmit('${pageContext.request.contextPath}/demo/studentFileAction!edit.action', updateRows[0]);
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
			if(userACLs[2]){//Update
				$('#edit-btn-toolbar').linkbutton('enable');
			}
			
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
		
		function initUserACL(){
			if(!userACLs[0]){ //Create
                $("#add-btn-toolbar").linkbutton('disable'); 
                $("#add-menu").hide(); 
            }
			if(!userACLs[2]){//Update
				$("#edit-btn-toolbar").linkbutton('disable');
				$("#import-btn-toolbar").linkbutton('disable'); 
				$("#export-btn-toolbar").linkbutton('disable'); 
				 $("#edit-menu").hide();
			}
			if(!userACLs[3]){//delete
				$("#del-btn-toolbar").linkbutton('disable');  
				 $("#del-menu").hide();
			}
		}
	</script>
</body>
</html>
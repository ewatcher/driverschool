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
	<div data-options="region:'north',border:false,title:'查询功能'" style="height: 90px;overflow: hidden;" align="left">
		<!-- 学员信息查询表单 -->
		<form id="studentFile_searchForm">
			<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
				<tr>
					<th style="width: 65px;">所属校区:</th>
					<td><input id="demo_studentFile_schoolArea"  name="schoolArea" style="width: 155px;"/></td>
					<th style="width: 65px;">学员编号:</th>
					<td><input id="search_studentNo" class="keypress_event" name="studentNo" style="width: 155px;"/></td>
					<th style="width: 65px;">姓名:</th>
					<td><input id="demo_theory_name" class="keypress_event" name="studentName"  style="width: 155px;"/></td>
					<th style="width: 65px;">身份证号:</th>
					<td><input name="studentIdentityId" class="keypress_event"  style="width: 155px;"/></td>
				</tr>
				<tr>
					<th style="width: 65px;">交费情况:</th>
					<td><input id="demo_studentFile_update_feeState" name="feeState"  style="width: 155px;"/></td>
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
		<table id="studentTimerCardDataGrid" class="easyui-datagrid" title="学员计时表" 
				data-options="
				url : '${pageContext.request.contextPath}/demo/studentFileAction!datagrid.action',
				title : '学员档案信息管理列表!', 
				iconCls : 'icon-save',
				pagination : true, 
				pagePosition : 'bottom', 
				pageSize : 20, 
				pageList : [ 10, 20, 30, 40,50 ,100,200 ], 
				fit : true,
				fitColumns : false, 
				nowrap : false, 
				border : false,
				singleSelect:true,
				rownumbers: true,
				idField : 'id', 
				sortName : 'id', 
				sortOrder : 'desc', 
				checkOnSelect : true,
				selectOnCheck : true,
				striped : true,
				nowrap:true,
				method:'get',
				toolbar:toolbar,
				onSelect:onSelectStudentFile,
				onBeforeLoad:onMyBeforeLoad,
				onLoadSuccess:onloadStudentFile,
				onRowContextMenu:studentFileMenu">
				<!-- 行编辑时冻结列 -->
			<thead data-options="frozen:true">
				<tr>
					<th data-options="field:'id',width:100,align:'left',checkbox:true,">编号</th>
					<th data-options="field:'schoolAreaName',width:100,align:'left',">所属校区</th>
					<th data-options="field:'studentNo',width:80,align:'center',formatter:formatStudentNo," >学员编号</th>
					<th data-options="field:'studentName',width:70,align:'center'," >学生名称</th>
					<th data-options="field:'studentDriverType',width:60,align:'center',formatter:formatterDriverType">驾照类型</th>
					<th data-options="field:'studentIdentityId',width:140,align:'center'" >身份证</th>
					<th data-options="field:'studentPhone',width:100,align:'center'" >手机</th>
					<th data-options="field:'studentCreateTime',width:80,align:'center',formatter:formatTimeYMD">报名日期</th>
 				</tr>
 			</thead>
 			<thead>	
 				<tr>	
 					<th data-options="field:'contributionTime',width:100,align:'center',editor:'datebox',formatter:formatTimeYMD">交费日期</th>
					<th data-options="field:'feeState',width:70,align:'center',
						editor:{
							type:'combobox',
                            options:{
                                url:'${pageContext.request.contextPath}/demo/json/moneyState_combobox.json',
                                valueField:'value',
                                textField:'text',
                                method:'get',
                            }
						},
						formatter:formatFeed">资费情况</th>
					<th data-options="field:'learnTime',width:70,align:'center',editor:'numberbox'">所交学时</th>
					<th data-options="field:'theoryDate',width:100,align:'center',editor:'datebox',formatter:formatTimeYMD">考交规日期</th>
					<th data-options="field:'subjectTwoDate',width:100,align:'center',editor:'datebox',formatter:formatTimeYMD">考五项日期</th>
					<th data-options="field:'subjectThreeDate',width:100,align:'center',editor:'datebox',formatter:formatTimeYMD">路考日期</th>
					<th data-options="field:'subjectFourDate',width:100,align:'center',editor:'datebox',formatter:formatTimeYMD">安全交规</th>
					<th data-options="field:'qualificationDate',width:100,align:'center',editor:'datebox',formatter:formatTimeYMD">资格考试</th>
					<th data-options="field:'comment',width:200,align:'center',editor:'text',">备注</th>
					<th data-options="field:'schoolArea',width:100,align:'center',hidden:true">校区标识</th>
					<th data-options="field:'studentId',width:100,align:'center',hidden:true">学员标识</th>
				</tr>
			</thead>
		</table>
	</div>
	<div id="studentFile_menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="edit();" data-options="iconCls:'icon-edit'">更新</div>
		<div onclick="reload();" data-options="iconCls:'icon-reload'">刷新</div>
	</div>
	<script type="text/javascript">
		var userACLs=[];
		$(function() {
			getUserACL();
		});
		
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
		
		function onMyBeforeLoad(){
			getUserACL();
			if(!userACLs[2]){
				$("#edit-btn-toolbar").linkbutton('disable');
			}
		}
		
		var editIndex = undefined;  // 编辑状态的行标
		var clickIndex = undefined; // 选中状态的行标
		function onSelectStudentFile(index,row){
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
			$('#edit-btn-toolbar').linkbutton('disable');
		}
		function enableButton(){
			if(userACLs[2]){
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
			var o = sy.serializeObject($('#studentFile_searchForm'));
			console.log(o);
			mydatagrid.datagrid('load', o);
		} 
		//取消查询功能按钮
		function cleanSearch() {
			mydatagrid.datagrid('load', {});
			//清空与查询有关的输入框
			$('#studentFile_searchForm input').val('');
		}
		
		function onloadStudentFile(data){
			//修改序号bug
			mydatagrid.datagrid("fixRownumber");
			//初始化校区选项
			initStudentFileSchoolArea($("#demo_studentFile_schoolArea"));
			//初始化交费情况
			initMoneyState($("#demo_studentFile_update_feeState"));
			//添加回车事件
			bindingKeypressEvent($(".keypress_event"),mydatagrid,$("#studentFile_searchForm"));
			//datagrid没有数据时显示没有记录信息
			if(data.total==0){
				mydatagrid.datagrid('appendRow', {
					schoolAreaName:  '<div style="text-align:center;color:red">没有记录信息！</div>' })
				.datagrid('mergeCells', { index: 1, field: 'schoolAreaName', colspan:8 });
			}
		}
		
		//右健菜单
		function studentFileMenu(e, rowIndex, rowData){
			e.preventDefault();
			//取消当前页被选中的所有行
			$(this).datagrid('unselectAll');
			$(this).datagrid('selectRow', rowIndex);
			$('#studentFile_menu').menu('show', {
				left : e.pageX,
				top : e.pageY
			});
		}
		
		
	</script>
</body>
</html>
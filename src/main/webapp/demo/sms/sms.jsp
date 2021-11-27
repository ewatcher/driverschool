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
<script type="text/javascript" charset="utf-8">
	var mydatagrid;
	var initPersonDepartment;
	
	var userSchoolArea=undefined;
	$(function() {
		userSchoolArea=getUserSchoolArea();
		
		mydatagrid = $('#demo_sms_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/demo/smsAction!datagrid.action',/* 从后台读取所有user数据 */
			queryParams:{propertyType:0},
			title : '短信记录详细列表!', /* 表头标题 */
			iconCls : 'icon-save', /* 标题前边的图标 */
			pagination : true, /* 是否显示页码 */
			pagePosition : 'bottom', /* 页码显示的位置 */
			pageSize : 20, /* 每面显示的记录数 */
			pageList : [ 10, 20, 30, 40,50 ,100,200 ], /* 选择每面显示的记录数 */
			fit : true,
			fitColumns : false, //列自动调整功能
			nowrap : false, //以行的形式进行显示
			border : false,
			rownumbers: true,
			idField : 'id', //此属性一定要配置
			sortName : 'id', //列排序
			sortOrder : 'asc', //列排序
			checkOnSelect : false,
			selectOnCheck : true,
			striped : true,//表示条纹，隔行显示不现背景色
			nowrap:true,//单元显示
			//冻结列
			frozenColumns : [ [ {
				title : '编号',
				field : 'id',
				width : 20,
				sortable : true,
				checkbox : true
			}, {
				title : '<font color="#0099FF">所属部门</font>',
				field : 'schoolAreaName',
				width : 120,
				align : 'left'
			},{
				title : '<font color="#0099FF">姓名</font>',
				field : 'name',
				width : 80,
				sortable : true,
				align : 'center'
			},{
				title : '<font color="#0099FF">手机号</font>',
				field : 'phone',
				sortable : true,
				align : 'center',
				width : 150
			} ] ],
			//普通列
			columns : [ [{
				title : '<font color="#0099FF">发送日期</font>',
				field : 'date',
				sortable : true,
				width : 90,
				align : 'center',
				formatter:formatTimeYMD,
			}, {
				title : '<font color="#0099FF">发送人</font>',
				field : 'operator',
				align : 'center',
				width : 80
			}, {
				title : '<font color="#0099FF">短信内容</font>',
				field : 'content',
				width : 450,
				align : 'center',
				formatter:showAllMsg,
			},{
				title : '<font color="#0099FF">校区标识</font>',
				field : 'schoolArea',
				fixed:false,
				width : 150
			}

			] ],
			//菜单功能设置
			toolbar : [ {
				text : '刷新',
				iconCls : 'icon-reload',
				handler : function() {
					mydatagrid.datagrid('clearSelections');
					//取消当前页面的选中行
					mydatagrid.datagrid('unselectAll');
					mydatagrid.datagrid('uncheckAll');
					mydatagrid.datagrid('reload');
				}
			}, '-', {
				text : '取消选中',
				iconCls : 'icon-undo',
				handler : function() {
					//取消所有的选中行
					mydatagrid.datagrid('clearSelections');
					//取消当前页面的选中行
					mydatagrid.datagrid('unselectAll');
				}
			}, '-' ],
			onLoadSuccess : function (data) {
				//修改序号bug
				$(this).datagrid("fixRownumber");
				$(this).datagrid('hideColumn','schoolArea');
				initSchoolArea($("#demo_personDepartment"),userSchoolArea);
				//datagrid没有数据时显示没有记录信息
				if(data.total==0){
					$(this).datagrid('appendRow', {
						schoolAreaName: '<div style="text-align:center;color:red">没有记录信息！</div>' })
					.datagrid('mergeCells', { index: 0, field: 'schoolAreaName', colspan:6 });
				}
			},
			/* //右键菜单所触发的事件
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				//取消当前页被选中的所有行
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow', rowIndex);
				$('#demo_sms_menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			} */
		});

	});

	
	//单击编辑按钮，实现对用户的编辑功能
	function edit() {
		var rows = mydatagrid.datagrid('getSelections');
		//编辑时只能单选一行进行编辑
		if (rows.length == 1) {
			var p = parent.sy.dialog({
						title : '编辑人员信息',
						href : '${pageContext.request.contextPath}/demo/personAction!toPersonEditPage.action',
						width : 1024,
						height : 460,
						buttons : [
								{
									text : '更新',
									iconCls : 'icon-tip',
									handler : function() {
										var f = p.find('form');
										//提交表单
										f.form('submit',{
											url : '${pageContext.request.contextPath}/demo/personAction!edit.action',
											success : function(d) {
													var json = $.parseJSON(d);
													if (json.success) {
														/* mydatagrid.datagrid('reload'); */
															mydatagrid.datagrid('updateRow',{
																							//获得行的索引
																	index : mydatagrid.datagrid('getRowIndex',rows[0]),
																	row : json.obj
															}); 
														p.dialog('close');
													}
													parent.sy.messagerShow({
														msg : json.msg,
														title : '提示信息'
													});
											}
										});
									}
								}, {
									text : '取消',
									iconCls : 'icon-cancel',
									handler : function() {
										p.dialog('close');
									}
								} ],
						//数据回显显示
						onLoad : function() {
							var f = p.find('form');
							f.form('load', {
								id : rows[0].id,
								name : rows[0].name,
								sex : rows[0].sex,
								birthday : rows[0].birthday,
								identityId : rows[0].identityId,
								address : rows[0].address,
								nation : rows[0].nation,
								phone : rows[0].phone,
								email : rows[0].email,
								birthPlace : rows[0].birthPlace,
								political : rows[0].political,
								profession : rows[0].profession,
								educationLevel : rows[0].educationLevel,
								accountNature : rows[0].accountNature,
								graduateSchool : rows[0].graduateSchool,
								graduateDay : rows[0].graduateDay,
								departmentId : rows[0].schoolAreaId,
								duty : rows[0].duty,
								driverLevel : rows[0].driverLevel,
								employDay : rows[0].employDay,
								marriageState : rows[0].marriageState,
								languageLevel : rows[0].languageLevel,
								computerLevel : rows[0].computerLevel,
								startedJob:rows[0].startedJob,
								comment : rows[0].comment,
								
							});
							//初始化部门选择菜单
							 var myDepartment=f.find('input[name=departmentId]');
							initSchoolArea(myDepartment,userSchoolArea);
						}
					});
		} else if (rows.length > 1) {
			parent.sy.messagerAlert('提示信息', '同一时间只能更新一条记录！', 'error');
		} else {
			parent.sy.messagerAlert('提示信息', '请选择要更新的记录！', 'error');
		}
	}

	//添加用户
	function append() {
		var addDialog = parent.sy.dialog({
					title : '人员信息录入',
					href : '${pageContext.request.contextPath}/demo/personAction!toPersonAddPage.action',
					width : 1024,
					height : 460,
					buttons : [
							{
								text : '添加',
								iconCls : 'icon-add',
								handler : function() {
									var f = addDialog.find('form');
									//提交表单
									f.form('submit',{
										//提交添加用户表单时，调用UserAction的add方法
										url : '${pageContext.request.contextPath}/demo/personAction!add.action',
										success : function(d) {
											var json = $.parseJSON(d);
											if (json.success) {
												/* mydatagrid.datagrid('reload'); *///这种方式性能差，消耗系统资源
												mydatagrid.datagrid('insertRow',{
													index : 0, // index start with 0
													row : json.obj
												});
												addDialog.dialog('close');
											}
											parent.sy.messagerShow({
												msg : json.msg,
												title : '提示信息'
											});
										}
										
									});
								}
							}, {
								text : '取消',
								iconCls : 'icon-cancel',
								handler : function() {
									addDialog.dialog('close');
								}
							} ],
							//通过onLoad函数实现数据回显
							onLoad : function() {
								var myForm=addDialog.find('form');
								var myDepartment=myForm.find('input[name=departmentId]');
								initSchoolArea(myDepartment,userSchoolArea);
								//check indentity repead or not
								var identityTemp=myForm.find('input[name=identityId]');
								identityTemp.change(function(){
									$.ajax({
										url : '${pageContext.request.contextPath}/demo/personAction!identityIdExistOrNot.action',
										data:{identityId:identityTemp.val()},
										dataType : 'json',
										success : function(d) {
											if(d){
												parent.sy.messagerAlert('提示信息', '您输入的身份证号系统中已经存在，请核对！', 'question');
												identityTemp.val('');
											}
										}
									});
								});
							},
				});
	}

	//删除用户
	function remove() {
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
										url : '${pageContext.request.contextPath}/demo/personAction!delete.action',
										data : {ids : ids.join(',')},
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

	//查询按钮功能函数
	function mySearch() {
		mydatagrid.datagrid('load', sy.serializeObject($('#demo_sms_searchForm')));
	}

	//取消查询功能按钮
	function cleanSearch() {
		mydatagrid.datagrid('load', {propertyType:0});
		//清空与查询有关的输入框
		$('.input_clean').val('');
		$('#demo_sms_searchForm input').val('');
		console.info('Test now...');
	}

</script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询功能'" style="height: 92px;overflow: hidden;" align="left">
		<!-- 人员信息查询表单 -->
		<form id="demo_sms_searchForm">
			<input type="hidden" id="propertyTypeVal" name="propertyType" value="0">
			<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
				<tr>
					<th style="width: 65px;">所属部门:</th>
					<td><input id="demo_personDepartment" name="schoolArea" style="width:150px;" />
	 					</td>
					<th style="width: 65px;">姓名:</th>
					<td><input name="name" class="input_clean" style="width:120px;" /></td>
					<th style="width: 65px;">手机号：</th>
					<td><input name="phone" class="input_clean"  style="width:120px;" /></td>
					<th style="width: 65px;">发送人：</th>
					<td><input name="operator" class="input_clean"  style="width:120px;" /></td>
				</tr>
				<tr>
					<th style="width: 65px;">发送日期:</th>
					<td><input id="input_clean_start" name="dateStart"
						class="easyui-datebox" data-options="editable:false"
						style="width: 95px;" />至:<input id="input_clean_end" name="dateEnd"
						class="easyui-datebox" data-options="editable:false"
						style="width: 95px;" /></td>
					<td colspan="2">
						<a href="javascript:void(0);" id="sms_a_searche" class="easyui-linkbutton" iconCls="icon-search" onclick="mySearch();">查询</a>
						<a href="javascript:void(0);" id="sms_a_canel" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cleanSearch();">取消</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<table id="demo_sms_datagrid"></table>
	</div>
	<!-- 操作按钮事件 -->
	<div id="demo_sms_menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="append();" data-options="iconCls:'icon-add'">增加</div>
		<div onclick="remove();" data-options="iconCls:'icon-remove'">删除</div>
		<div onclick="edit();" data-options="iconCls:'icon-edit'">更新</div>
	</div>
</body>
</html>
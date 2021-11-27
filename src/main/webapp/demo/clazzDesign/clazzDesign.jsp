<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">
	var mydatagrid;
	var initPersonDepartment;
	var tempType;
	var userSchoolArea=undefined;
	$(function() {
		userSchoolArea=getUserSchoolArea();
		mydatagrid = $('#demo_classDesign_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/demo/classDesignAction!datagrid.action',/* 从后台读取所有user数据 */
			title : '班级开设信息管理列表!', /* 表头标题 */
			iconCls : 'icon-save', /* 标题前边的图标 */
			pagination : true, /* 是否显示页码 */
			pagePosition : 'bottom', /* 页码显示的位置 */
			pageSize : 10, /* 每面显示的记录数 */
			pageList : [ 10, 20, 30, 40 ], /* 选择每面显示的记录数 */
			fit : true,
			fitColumns : false, //列自动调整功能
			nowrap : false, //以行的形式进行显示
			border : false,
			rownumbers: true,
			idField : 'id', //此属性一定要配置
			sortName : 'name', //列排序
			sortOrder : 'desc', //列排序
			checkOnSelect : false,
			selectOnCheck : true,
			striped : true,//表示条纹，隔行显示不现背景色
			nowrap:true,//单元显示
			//冻结列
			//普通列
			columns : [ [{
				title : '编号',
				field : 'id',
				width : 20,
				sortable : true,
				checkbox : true
			}, {
				title : '<font color="#0099FF">序号</font>',
				field : 'orderNo',
				width : 120,
				sortable : true,
				align : 'center'
			},{
				title : '<font color="#0099FF">所属校区</font>',
				field : 'schoolAreaName',
				width : 150
			},{
				title : '<font color="#0099FF">名称</font>',
				field : 'name',
				sortable : true,
				width : 150
			},{
				title : '<font color="#0099FF">费用</font>',
				field : 'fee',
				sortable : true,
				width : 150,
				align : 'left'
			},{
				title : '<font color="#0099FF">课时(天)</font>',
				field : 'costTime',
				sortable : true,
				width : 150,
				align : 'center'
			},{
				title : '<font color="#0099FF">业务类型</font>',
				field : 'type',
				sortable : true,
				width : 70,
				align : 'center',
				formatter:function(value,row,index){
					return formatterDriverType(value, row, index);
				}
			},{
				title : '<font color="#0099FF">计时类型</font>',
				field :	'classType',
				sortable : true,
				width : 120,
				align : 'center',
				formatter: function(value,row,index){
					if(value==1){
						return '<span style="color:red;">'+'计时'+'</span>';
					}else{
						return '不计时';
					}
				}
			},  {
				title : '<font color="#0099FF">操作员</font>',
				field : 'operator',
				width : 80,
			},{
				title : '<font color="#0099FF">描述</font>',
				field : 'comment',
				width : 200,
				formatter:showAllMsg,
			},{
				title : '<font color="#0099FF">学校标识</font>',
				field : 'schoolArea',
				sortable : true,
				width : 150,
			}
			] ],
			//菜单功能设置
			toolbar : [ {
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					append();
				}
			}, '-', { //'-':减号，将用“｜”隔开按钮
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					remove();
				}
			}, '-', {
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					edit();
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
				$(this).datagrid("fixRownumber");
				$(this).datagrid('hideColumn','schoolArea');
				//初始化校区选择
				$("#demo_clazz_type").combobox({
					url : '${pageContext.request.contextPath}/demo/json/driverType_combobox.json',
					valueField : 'value',
					textField : 'text',
					method:'get',
					editable:false,
					onChange:function(){
						mydatagrid.datagrid('load', sy.serializeObject($('#demo_clazz_searchForm')));
					}
				});
				
				//初始化开班业务类型选择
				initSchoolArea($("#demo_clazz_schoolArea"),userSchoolArea);
				
				//datagrid没有数据时显示没有记录信息
				if(data.total==0){
					mydatagrid.datagrid('appendRow', {
						orderNo: '<div style="text-align:center;color:red">系统当前没有班级记录信息！</div>' })
					.datagrid('mergeCells', { index: 0, field: 'orderNo', colspan:10 });
				}
			},
			
			//右键菜单所触发的事件
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				//取消当前页被选中的所有行
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow', rowIndex);
				$('#demo_classDesign_menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			}
		});

	});

	//初始化校区
	function initSchoolArea(object){
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
	//单击编辑按钮，实现对用户的编辑功能
	function edit() {
		var rows = mydatagrid.datagrid('getSelections');
		//编辑时只能单选一行进行编辑
		if (rows.length == 1) {
			var p = parent.sy.dialog({
						title : '编辑班级开设信息',
						href : '${pageContext.request.contextPath}/demo/classDesignAction!toClassDesignEditPage.action',
						width : 1024,
						height : 340,
						buttons : [
								{
									text : '更新',
									iconCls : 'icon-tip',
									handler : function() {
										var f = p.find('form');
										//提交表单
										f.form('submit',{
											url : '${pageContext.request.contextPath}/demo/classDesignAction!edit.action',
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
							var temp;
							if(rows[0].classType==1){
								temp='计时';
							}else if(rows[0].classType==0){
								temp='不计时';
							}else{
								temp='';
							}
							var f = p.find('form');
							f.form('load', {
								id : rows[0].id,
								name : rows[0].name,
								fee : rows[0].fee,
								costTime : rows[0].costTime,
								type:rows[0].type,
								classType : rows[0].classType,
								orderNo : rows[0].orderNo,
								comment : rows[0].comment,
								schoolArea:rows[0].schoolArea,
							});
							//初始化开班业务类型选择
							var f = p.find('form');
							var bsnType=f.find('input[name=type]');
							bsnType.combobox({
								url : '${pageContext.request.contextPath}/demo/json/driverType_combobox.json',
								valueField : 'value',
								textField : 'text',
								method:'get',
								required:true,
								editable:false,
							});
							
							//初始化校区选择
							var dep=f.find('input[name=schoolArea]');
							initSchoolArea(dep);
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
					title : '班级开设信息录入',
					href : '${pageContext.request.contextPath}/demo/classDesignAction!toClassDesignAddPage.action',
					width : 1024,
					height : 340,
					buttons : [
							{
								text : '添加',
								iconCls : 'icon-add',
								handler : function() {
									var f = addDialog.find('form');
									//提交表单
									f.form('submit',{
										//提交添加用户表单时，调用UserAction的add方法
										url : '${pageContext.request.contextPath}/demo/classDesignAction!add.action',
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
				//数据回显显示
				onLoad : function() {
					//初始化开班业务类型选择
					var f = addDialog.find('form');
					var bsnType=f.find('input[name=type]');
					bsnType.combobox({
						url : '${pageContext.request.contextPath}/demo/json/driverType_combobox.json',
						valueField : 'value',
						textField : 'text',
						method:'get',
						required:true,
						editable:false,
					});
					//初始化校区选择
					var dep=f.find('input[name=schoolArea]');
					initSchoolArea(dep);
					
				}//onLoad end=====
					
		});//dialog end
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
										url : '${pageContext.request.contextPath}/demo/classDesignAction!delete.action',
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
		mydatagrid.datagrid('load', sy.serializeObject($('#demo_clazz_searchForm')));
	}

	//取消查询功能按钮
	function cleanSearch() {
		mydatagrid.datagrid('load', {});
		//清空与查询有关的输入框
		$('#demo_clazz_searchForm input').val('');
	}
	


</script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询功能'" style="height: 85px;overflow: hidden;" align="left">
		<!--  班级信息查询表单 -->
		<form id="demo_clazz_searchForm">
			<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
			<tr>
				<th style="width: 80px;">所属校区:</th>
				<td><input id="demo_clazz_schoolArea" name="schoolArea" style="width: 155px;"/></td>
				<th style="width:80px;">业务类型:</th>
				<td><input id="demo_clazz_type" name="type" style="width: 155px;"/></td>
			</tr>
			<tr>
				<th></th>
				<td></td>
				<td colspan="2">
					<a href="javascript:void(0);" id="reservation_a_searche" class="easyui-linkbutton" iconCls="icon-search" onclick="mySearch();">查询</a>
					<a href="javascript:void(0);" id="reservation_a_canel" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cleanSearch();">取消</a>
				</td>
			</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<table id="demo_classDesign_datagrid"></table>
	</div>
	<!-- 操作按钮事件 -->
	<div id="demo_classDesign_menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="append();" data-options="iconCls:'icon-add'">增加</div>
		<div onclick="remove();" data-options="iconCls:'icon-remove'">删除</div>
		<div onclick="edit();" data-options="iconCls:'icon-edit'">更新</div>
	</div>
</body>
</html>
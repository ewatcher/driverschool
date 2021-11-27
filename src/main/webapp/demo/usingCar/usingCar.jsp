<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">
	var mydatagrid;
	var userSchoolArea=undefined;
	var detailDatagrid=undefined;
	$(function() {
		userSchoolArea=getUserSchoolArea();
		mydatagrid = $('#demo_usingcar_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/demo/usingCarAction!datagrid.action',
			title : '车辆使用信息管理列表!', /* 表头标题 */
			iconCls : 'icon-save', /* 标题前边的图标 */
			pagination : true, /* 是否显示页码 */
			pagePosition : 'bottom', /* 页码显示的位置 */
			pageSize : 20, /* 每面显示的记录数 */
			pageList : [ 10, 20, 30, 40 ], /* 选择每面显示的记录数 */
			fit : true,
			fitColumns : false, //列自动调整功能
			nowrap : false, //以行的形式进行显示
			border : false,
			rownumbers: true,
			idField : 'id', //此属性一定要配置
			sortName : 'carId', //列排序
			sortOrder : 'desc', //列排序
			checkOnSelect : true,
			selectOnCheck : true,
			striped : true,//表示条纹，隔行显示不现背景色
			nowrap:true,//单元显示
			singleSelect:true,
			//冻结列
			frozenColumns : [ [ {
				title : '编号',
				field : 'id',
				width : 20,
				sortable : true,
				checkbox : true
			}, {
				title : '<font color="#0099FF">所属校区</font>',
				field : 'schoolAreaName',
				width : 100,
				sortable : true,
				align : 'center'
			},{
				title : '<font color="#0099FF">车牌号</font>',
				field : 'carNo',
				width : 100,
				align : 'center'
			},{
				title : '<font color="#0099FF">行驶证号</font>',
				field : 'licenseNo',
				sortable : true,
				width : 150
			},{
				title : '<font color="#0099FF">车辆用途</font>',
				field : 'carType',
				sortable : true,
				width : 70,
				align : 'center',
				formatter:function(value,row,index){
					return initCarUsingWay(value,row,index); 
				}
			},
			{
				title : '<font color="#0099FF">使用状态</font>',
				field : 'usingState',
				sortable : true,
				width : 70,
				align : 'center',
				formatter:function(value,row,index){
					return initCarUsingState(value,row,index);
				}
			},
			 ] ],
			//普通列
			columns : [ [{
				title : '<font color="#0099FF">今日用时</font>',
				field : 'dateCount',
				sortable : true,
				width : 70,
				align : 'center',
				formatter:function(value,row,index){
					return initColumColorRed(value, row, index);
				}
			},{
				title : '<font color="#0099FF">今日预约用时</font>',
				field : 'dateReservateCount',
				sortable : true,
				width : 80,
				align : 'center',
				formatter:function(value,row,index){
					return initColumColorBlud(value, row, index);
				}
			},{
				title : '<font color="#0099FF">本周用时</font>',
				field : 'weekCount',
				sortable : true,
				width : 70,
				align : 'center',
				formatter:function(value,row,index){
					return initColumColorRed(value, row, index);
				}
			},{
				title : '<font color="#0099FF">本周预约用时</font>',
				field : 'weekReservateCount',
				sortable : true,
				width : 80,
				align : 'center',
				formatter:function(value,row,index){
					return initColumColorBlud(value, row, index);
				}
			},{
				title : '<font color="#0099FF">本月用时</font>',
				field : 'manthCount',
				sortable : true,
				width : 70,
				align : 'center',
				formatter:function(value,row,index){
					return initColumColorRed(value, row, index);
				}
			},{
				title : '<font color="#0099FF">本月预约用时</font>',
				field : 'monthReservateCount',
				sortable : true,
				width : 80,
				align : 'center',
				formatter:function(value,row,index){
					return initColumColorBlud(value, row, index);
				}
			},{
				title : '<font color="#0099FF">本年用时</font>',
				field : 'yearCount',
				sortable : true,
				width : 70,
				align : 'center',
				formatter:function(value,row,index){
					return initColumColorRed(value, row, index);
				}
			},{
				title : '<font color="#0099FF">本年预约用时</font>',
				field : 'yearReservateCount',
				sortable : true,
				width : 80,
				align : 'center',
				formatter:function(value,row,index){
					return initColumColorBlud(value, row, index);
				}
			},{
				title : '<font color="#0099FF">捆绑教练</font>',
				field : 'trainerName',
				width : 80,
				align : 'center'
			},{
				title : '<font color="#0099FF">校区标识</font>',
				field : 'schoolArea',
				width : 180,
				align : 'center'
			}, {
				title : '<font color="#0099FF">车辆标识</font>',
				field : 'carId',
				width : 100,
				align : 'center'
			},{
				title : '<font color="#0099FF">教练标识</font>',
				field : 'trainerId',
				width : 100,
				align : 'center'
			}
			] ],
			//菜单功能设置
			toolbar : [ {
				text : '车辆使用明细',
				iconCls : 'icon-add',
				handler : function() {
					detail();
				}
			}, '-',  {
				text : '取消选中',
				iconCls : 'icon-undo',
				handler : function() {
					//取消所有的选中行
					mydatagrid.datagrid('clearSelections');
					//取消当前页面的选中行
					mydatagrid.datagrid('unselectAll');
				}
			}, ],
			onLoadSuccess : function (data) {
				$(this).datagrid("fixRownumber");
				$(this).datagrid('hideColumn','schoolArea');
				$(this).datagrid('hideColumn','carId');
				$(this).datagrid('hideColumn','trainerId');
				initSchoolArea($("#demo_usingcar_organization"),userSchoolArea);
				//datagrid没有数据时显示没有记录信息
				if(data.total==0){
					$(this).datagrid('appendRow', {
						schoolAreaName: '<div style="text-align:center;color:red">没有记录信息！</div>' })
					.datagrid('mergeCells', { index: 0, field: 'schoolAreaName', colspan:8 });
				}
			},
			//右键菜单所触发的事件
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				//取消当前页被选中的所有行
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow', rowIndex);
				$('#demo_carusing_menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			}
			
		});

	});
	
	//查询按钮功能函数
	function mySearch() {
		mydatagrid.datagrid('load', sy.serializeObject($('#demo_usingcar_searchForm')));
	}

	//车辆使用明细页面
	function detail(){
		var p=undefined;
		var rows = mydatagrid.datagrid('getSelections');
		//编辑时只能单选一行进行编辑
		if (rows.length == 1) {
			p = parent.sy.dialog({
						title : '车辆使用明细信息列表',
						href : '${pageContext.request.contextPath}/demo/usingCarAction!toUsingCarDetailPage.action',
						width : 1280,
						height : 700,
						buttons : [
								{
									text : '关闭',
									iconCls : 'icon-cancel',
									handler : function() {
										p.dialog('close');
									}
								} ],
						//数据回显显示
						onLoad : function() {
							initDetailDatagrid(p,rows[0].schoolArea,rows[0].carId,rows[0].carNo,rows[0].trainerName,rows[0].usingState);
						}
					});
		} else if (rows.length > 1) {
			parent.sy.messagerAlert('提示信息', '同一时间只能更新一条记录！', 'error');
		} else {
			parent.sy.messagerAlert('提示信息', '请选择要更新的记录！', 'error');
		}
	}
	
	function initDetailDatagrid(p,schoolArea,carId,carNo,trainerName,usingState){
		var datagridId=p.find('table[id=detail_usingcar_datagrid]');
		detailDatagrid=datagridId.datagrid({
			url : '${pageContext.request.contextPath}/demo/usingCarAction!getCarusingDetailByCarId.action',
			queryParams:{schoolArea:schoolArea,carId:carId,carNo:carNo,trainerName:trainerName,usingState:usingState},
			iconCls : 'icon-save', /* 标题前边的图标 */
			pagination : true, /* 是否显示页码 */
			pagePosition : 'bottom', /* 页码显示的位置 */
			pageSize : 20, /* 每面显示的记录数 */
			pageList : [ 10, 20, 30, 40 ], /* 选择每面显示的记录数 */
			fit : true,
			fitColumns : false, //列自动调整功能
			nowrap : false, //以行的形式进行显示
			border : false,
			rownumbers: true,
			idField : 'id', //此属性一定要配置
			sortName : 'id', //列排序
			sortOrder : 'desc', //列排序
			checkOnSelect : true,
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
				title : '<font color="#0099FF">所属校区</font>',
				field : 'schoolAreaName',
				width : 100,
				sortable : true,
				align : 'center'
			},{
				title : '<font color="#0099FF">车牌号</font>',
				field : 'carNo',
				width : 100,
				align : 'center'
			},{
				title : '<font color="#0099FF">编号</font>',
				field : 'usingCarNumbering',
				width : 120,
				sortable : true,
				align : 'center'
			},{
				title : '<font color="#0099FF">使用日期</font>',
				field : 'usingCarUsingDate',
				sortable : true,
				width : 90,
				align : 'center',
				formatter:formatTimeYMD,
			} ] ],
			//普通列
			columns : [ [{
				title : '<font color="#0099FF">开始时间</font>',
				field : 'usingCarStartTime',
				sortable : true,
				width : 100,
				align : 'center'
			},{
				title : '<font color="#0099FF">结束时间</font>',
				field : 'usingCarEndTime',
				sortable : true,
				width : 100,
				align : 'center'
			},{
				title : '<font color="#0099FF">车辆使用状态</font>',
				field : 'usingCarUsingState',
				width : 90,
				align : 'center',
				formatter:function(value,row,index){
					return formatterReservationState(value, row, index);
				}
			},{
				title : '<font color="#0099FF">使用人</font>',
				field : 'trainerName',
				width : 90,
				align : 'center'
			},{
				title : '<font color="#0099FF">使用说明</font>',
				field : 'usingCarReason',
				width : 180,
				align : 'center'
			}, {
				title : '<font color="#0099FF">使用场地</font>',
				field : 'usingCarPlace',
				width : 100,
				align : 'center'
			}, {
				title : '<font color="#0099FF">开始公理数</font>',
				field : 'usingCarStartMile',
				width : 90
			}, {
				title : '<font color="#0099FF">结束公理数</font>',
				field : 'usingCarEndMile',
				width : 90
			},{
				title : '<font color="#0099FF">总里程</font>',
				field : 'usingCarUsingMile',
				width : 90
			},{
				title : '<font color="#0099FF">操作员</font>',
				field : 'operator',
				width : 80,
			},{
				title : '<font color="#0099FF">usingCarId</font>',
				field : 'usingCarId',
				width : 80,
			}
			] ],
			//菜单功能设置
			toolbar : [ {
				text : '取消选中',
				iconCls : 'icon-undo',
				handler : function() {
					//取消所有的选中行
					detailDatagrid.datagrid('clearSelections');
					//取消当前页面的选中行
					detailDatagrid.datagrid('unselectAll');
				}
			}, {
				text : '删除',
				iconCls : 'icon-undo',
				handler : function() {
					deleteUsingCar();
				}
			},],
			onLoadSuccess : function (data) {
				detailDatagrid.datagrid("fixRownumber");
				//datagrid没有数据时显示没有记录信息
				if(data.total==0){
					detailDatagrid.datagrid('appendRow', {
						schoolAreaName: '<div style="text-align:center;color:red">没有记录信息！</div>' })
					.datagrid('mergeCells', { index: 0, field: 'schoolAreaName', colspan:8 });
				}
				detailDatagrid.datagrid('hideColumn','usingCarId');
			},
			//右键菜单所触发的事件
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				//取消当前页被选中的所有行
				detailDatagrid.datagrid('unselectAll');
				detailDatagrid.datagrid('selectRow', rowIndex);
				$('#demo_car_menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			}
			
		});
	}
	
	//删除用户
	function deleteUsingCar() {
		var rows = detailDatagrid.datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			parent.sy.messagerConfirm('请确认','您要删除当前所选择项信息？',function(r) {
				if (r) {
					for (var i = 0; i < rows.length; i++) {
						ids.push(rows[i].usingCarId);
					}
					$.ajax({
						url : '${pageContext.request.contextPath}/demo/usingCarAction!delete.action',
						data : {ids : ids.join(',')},
						dataType : 'json',
						success : function(d) {
							detailDatagrid.datagrid('reload');
							detailDatagrid.datagrid('unselectAll');
							detailDatagrid.datagrid('uncheckAll');
								parent.sy.messagerShow({
										title : '提示信息',
										msg : d.msg
								});
						}
					});
				}
			});
		} else {
			parent.sy.messagerAlert('提示信息', '请勾选要删除的记录！', 'question');
		}
	}
	
	//取消查询功能按钮
	function cleanSearch() {
		mydatagrid.datagrid('load', {});
		//清空与查询有关的输入框
		$('#demo_usingcar_searchForm input').val('');
	}
	
</script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询功能'" style="height:58px;overflow: hidden;" align="left">
		<!-- 车辆使用信息查询表单 -->
		<form id="demo_usingcar_searchForm">
			<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
				<tr>
					<th style="width: 65px;">所属校区：</th>
					<td><input id="demo_usingcar_organization" name="schoolArea" style="width:150px;" /></td>
					<th  style="width: 65px;">车牌号:</th>
					<td><input name="carNo" style="width:150px;" /></td>
					<th style="width: 65px;">行驶证号：</th>
					<td><input name="LicenseNo" style="width:150px;" /></td>
					<td colspan="2">
						<a href="javascript:void(0);" id="car_a_searche" class="easyui-linkbutton" iconCls="icon-search" onclick="mySearch();">查询</a>
						<a href="javascript:void(0);" id="car_a_canel" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cleanSearch();">取消</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<table id="demo_usingcar_datagrid"></table>
	</div>
	<!-- 操作按钮事件 -->
	<div id="demo_car_menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="append();" data-options="iconCls:'icon-add'">增加</div>
		<div onclick="remove();" data-options="iconCls:'icon-remove'">删除</div>
		<div onclick="edit();" data-options="iconCls:'icon-edit'">更新</div>
	</div>
	<div id="demo_carusing_menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="detail();" data-options="iconCls:'icon-add'">使用明细</div>
	</div>
</body>
</html>
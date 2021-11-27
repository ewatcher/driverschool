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
	var userSchoolArea=undefined;
	var detailDatagrid=undefined;
	var addDialog=undefined;
	var userACLs=[];
	$(function() {
		userSchoolArea=getUserSchoolArea();
		getUserACL();
		
		mydatagrid = $('#demo_usingcar_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/demo/usingCarAction!getAutoCarDatagrid.action',
			title : '自动档车辆排班列表!', /* 表头标题 */
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
			},{
				title : '<font color="#0099FF">使用状态</font>',
				field : 'usingState',
				sortable : true,
				width : 70,
				align : 'center',
				formatter:initCarUsingState,
			},{
				title : '<font color="red">微信预约</font>',
				field : 'wechatFalg',
				sortable : true,
				width : 70,
				align : 'center',
				formatter:initCarWechatState,
			},{
				title : '<font color="#0099FF">最近排班日期</font>',
				field : 'lastArrangeDate',
				sortable : true,
				width : 90,
				align : 'center',
				formatter:formatTimeYMD,
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
				text : '排班明细',
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
				$('#demo_autocarArrange_menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			}
			
		});

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
	
	//初始化校区
	function initMySchoolArea(object){
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
		mydatagrid.datagrid('load', sy.serializeObject($('#demo_usingcar_searchForm')));
	}

	//车辆使用明细页面
	function detail(){
		var rows = mydatagrid.datagrid('getSelections');
		//编辑时只能单选一行进行编辑
		if (rows.length == 1) {
			var p = parent.sy.dialog({
						title : '自动档车辆排班明细信息列表',
						href : '${pageContext.request.contextPath}/demo/autocarArrangeAction!toAutocarArrangePage.action',
						width : 1080,
						height : 790,
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
							//初始化车辆基本信息
							var f=p.find('form');
							f.form('load',{
								id:rows[0].id,
								schoolAreaName:rows[0].schoolAreaName,
								carNo:rows[0].carNo,
							});
							//2.禁止用户操作教练的基本信息
							var disableItem=f.find('input[class=demo_detail_update]');
							disableItem.attr('disabled',true);
							//初始化查询模块
							//初始化月份查询选项
							var startDate=f.find('input[name=arrangeDateBegin]');
							startDate.datebox({
							});
							var endDate=f.find('input[name=arrangeDateEnd]');
							endDate.datebox({
							});
							//查找按钮事件
							var searchEvent=f.find('a[id=autocarArrange_a_searche]');
							searchEvent.linkbutton({
							    iconCls: 'icon-search',
							    onClick:function(){
							    	detailDatagrid.datagrid('load', sy.serializeObject(f));
							    },
							});	
							//重围按钮
							var resetEvent=f.find('a[id=autocarArrange_a_cancel]');
							resetEvent.linkbutton({
							    iconCls: 'icon-cancel',
							    onClick:function(){
							    	startDate.datebox('setValue','');
							    	endDate.datebox('setValue','');
							    	detailDatagrid.datagrid('load', {});
							    },
							});	
							
							//初始化自动档排班明细列表
							initDetailDatagrid(p,rows[0].schoolArea,rows[0].carId,rows[0].carNo,rows[0].usingState);
						}
					});
		} else if (rows.length > 1) {
			parent.sy.messagerAlert('提示信息', '同一时间只能操作一条记录！', 'question');
		} else {
			parent.sy.messagerAlert('提示信息', '请选择要排班的车辆信息！', 'question');
		}
	}
	
	function initDetailDatagrid(p,schoolArea,carId,carNo,usingState){
		var datagridId=p.find('table[id=detail_autocar_datagrid]');
		detailDatagrid=datagridId.datagrid({
			url : '${pageContext.request.contextPath}/demo/autocarArrangeAction!getAutocarArrangeByCarId.action',
			queryParams:{schoolArea:schoolArea,carId:carId,carNo:carNo},
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
			sortName : 'arrangeDate', //列排序
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
				title : '<font color="#0099FF">排班日期</font>',
				field : 'arrangeDate',
				sortable : true,
				width : 90,
				align : 'center',
				formatter:formatTimeYMD,
			} ] ],
			//普通列
			columns : [ [{
				title : '<font color="#0099FF">8-9</font>',
				field : 'itemTime8',
				sortable : true,
				width : 60,
				align : 'center',
				formatter:function(value, row, index){
					return initAutocarItemTime(value, row, index);
				}
			},{
				title : '<font color="#0099FF">9-10</font>',
				field : 'itemTime9',
				sortable : true,
				width : 60,
				align : 'center',
				formatter:function(value, row, index){
					return initAutocarItemTime(value, row, index);
				}
			},{
				title : '<font color="#0099FF">10-11</font>',
				field : 'itemTime10',
				sortable : true,
				width : 60,
				align : 'center',
				formatter:function(value, row, index){
					return initAutocarItemTime(value, row, index);
				}
			},{
				title : '<font color="#0099FF">11-12</font>',
				field : 'itemTime11',
				sortable : true,
				width : 60,
				align : 'center',
				formatter:function(value, row, index){
					return initAutocarItemTime(value, row, index);
				}
			},{
				title : '<font color="#0099FF">12-13</font>',
				field : 'itemTime12',
				sortable : true,
				width : 60,
				align : 'center',
				formatter:function(value, row, index){
					return initAutocarItemTime(value, row, index);
				}
			},{
				title : '<font color="#0099FF">13-14</font>',
				field : 'itemTime13',
				sortable : true,
				width : 60,
				align : 'center',
				formatter:function(value, row, index){
					return initAutocarItemTime(value, row, index);
				}
			},{
				title : '<font color="#0099FF">14-15</font>',
				field : 'itemTime14',
				sortable : true,
				width : 60,
				align : 'center',
				formatter:function(value, row, index){
					return initAutocarItemTime(value, row, index);
				}
			},{
				title : '<font color="#0099FF">15-16</font>',
				field : 'itemTime15',
				sortable : true,
				width : 60,
				align : 'center',
				formatter:function(value, row, index){
					return initAutocarItemTime(value, row, index);
				}
			},{
				title : '<font color="#0099FF">16-17</font>',
				field : 'itemTime16',
				sortable : true,
				width : 60,
				align : 'center',
				formatter:function(value, row, index){
					return initAutocarItemTime(value, row, index);
				}
			},{
				title : '<font color="#0099FF">17-18</font>',
				field : 'itemTime17',
				sortable : true,
				width : 60,
				align : 'center',
				formatter:function(value, row, index){
					return initAutocarItemTime(value, row, index);
				}
			},{
				title : '<font color="#0099FF">18-19</font>',
				field : 'itemTime18',
				sortable : true,
				width : 60,
				align : 'center',
				formatter:function(value, row, index){
					return initAutocarItemTime(value, row, index);
				}
			},{
				title : '<font color="#0099FF">19-20</font>',
				field : 'itemTime19',
				sortable : true,
				width : 60,
				align : 'center',
				formatter:function(value, row, index){
					return initAutocarItemTime(value, row, index);
				}
			},{
				title : '<font color="#0099FF">20-21</font>',
				field : 'itemTime20',
				sortable : true,
				width : 60,
				align : 'center',
				formatter:function(value, row, index){
					return initAutocarItemTime(value, row, index);
				}
			},{
				title : '<font color="#0099FF">21-22</font>',
				field : 'itemTime21',
				sortable : true,
				width : 60,
				align : 'center',
				formatter:function(value, row, index){
					return initAutocarItemTime(value, row, index);
				}
			},{
				title : '<font color="#0099FF">carId</font>',
				field : 'carId',
				sortable : true,
				width : 60,
				align : 'center',
			},{
				title : '<font color="#0099FF">schoolArea</font>',
				field : 'schoolArea',
				sortable : true,
				width : 100,
				align : 'center',
			},{
				title : '<font color="#0099FF">itemTime8Flag</font>',
				field : 'itemTime8Flag',
				sortable : true,
				width : 60,
				align : 'center',
			},{
				title : '<font color="#0099FF">itemTime9Flag</font>',
				field : 'itemTime9Flag',
				sortable : true,
				width : 60,
				align : 'center',
			},{
				title : '<font color="#0099FF">itemTime10Flag</font>',
				field : 'itemTime10Flag',
				sortable : true,
				width : 60,
				align : 'center',
			},{
				title : '<font color="#0099FF">itemTime11Flag</font>',
				field : 'itemTime11Flag',
				sortable : true,
				width : 60,
				align : 'center',
			},{
				title : '<font color="#0099FF">itemTime12Flag</font>',
				field : 'itemTime12Flag',
				sortable : true,
				width : 60,
				align : 'center',
			},{
				title : '<font color="#0099FF">itemTime13Flag</font>',
				field : 'itemTime13Flag',
				sortable : true,
				width : 60,
				align : 'center',
			},{
				title : '<font color="#0099FF">itemTime14Flag</font>',
				field : 'itemTime14Flag',
				sortable : true,
				width : 60,
				align : 'center',
			},{
				title : '<font color="#0099FF">itemTime15Flag</font>',
				field : 'itemTime15Flag',
				sortable : true,
				width : 60,
				align : 'center',
			},{
				title : '<font color="#0099FF">itemTime16Flag</font>',
				field : 'itemTime16Flag',
				sortable : true,
				width : 60,
				align : 'center',
			},{
				title : '<font color="#0099FF">itemTime17Flag</font>',
				field : 'itemTime17Flag',
				sortable : true,
				width : 60,
				align : 'center',
			},{
				title : '<font color="#0099FF">itemTime18Flag</font>',
				field : 'itemTime18Flag',
				sortable : true,
				width : 60,
				align : 'center',
			},{
				title : '<font color="#0099FF">itemTime19Flag</font>',
				field : 'itemTime19Flag',
				sortable : true,
				width : 60,
				align : 'center',
			},{
				title : '<font color="#0099FF">itemTime20Flag</font>',
				field : 'itemTime20Flag',
				sortable : true,
				width : 60,
				align : 'center',
			},{
				title : '<font color="#0099FF">itemTime21Flag</font>',
				field : 'itemTime21Flag',
				sortable : true,
				width : 60,
				align : 'center',
			},
			] ],
			//菜单功能设置
			toolbar : [  {
				id:'add-btn-toolbar',
				text : '添加',
				iconCls : 'icon-add',
				handler : function() {
					appendAutocar(p,schoolArea,carId,carNo,usingState);
				}
			}, {
				id:'edit-btn-toolbar',
				text : '更新',
				iconCls : 'icon-edit',
				handler : function() {
					editAutocar();
				}
			},{
				id:'del-btn-toolbar',
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					removeAutocar();
				}
			},{
				text : '取消选中',
				iconCls : 'icon-undo',
				handler : function() {
					//取消所有的选中行
					detailDatagrid.datagrid('clearSelections');
					//取消当前页面的选中行
					detailDatagrid.datagrid('unselectAll');
				}
			}, {
				text : '刷新',
				iconCls : 'icon-reload',
				handler : function() {
					detailDatagrid.datagrid('reload');
				}
			}, ],
			onBeforeLoad:function(param){
				var btnAdd=p.find('a[id=add-btn-toolbar]');
				var btnUpdate=p.find('a[id=edit-btn-toolbar]');
				var btnDel=p.find('a[id=del-btn-toolbar]');
				if(!userACLs[0]){ //Create
					btnAdd.linkbutton('disable');
                }
				if(!userACLs[2]){//Update
					btnUpdate.linkbutton('disable');
				}
				if(!userACLs[3]){//delete
					btnDel.linkbutton('disable');
				}
			},
			onLoadSuccess : function (data) {
				detailDatagrid.datagrid("fixRownumber");
				//datagrid没有数据时显示没有记录信息
				if(data.total==0){
					detailDatagrid.datagrid('appendRow', {
						itemTime8: '<div style="text-align:center;color:red">没有记录信息！</div>' })
					.datagrid('mergeCells', { index: 0, field: 'itemTime8', colspan:14 });
				}
				detailDatagrid.datagrid('hideColumn','carId');
				detailDatagrid.datagrid('hideColumn','schoolArea');
				detailDatagrid.datagrid('hideColumn','itemTime8Flag');
				detailDatagrid.datagrid('hideColumn','itemTime9Flag');
				detailDatagrid.datagrid('hideColumn','itemTime10Flag');
				detailDatagrid.datagrid('hideColumn','itemTime11Flag');
				detailDatagrid.datagrid('hideColumn','itemTime12Flag');
				detailDatagrid.datagrid('hideColumn','itemTime13Flag');
				detailDatagrid.datagrid('hideColumn','itemTime14Flag');
				detailDatagrid.datagrid('hideColumn','itemTime15Flag');
				detailDatagrid.datagrid('hideColumn','itemTime16Flag');
				detailDatagrid.datagrid('hideColumn','itemTime17Flag');
				detailDatagrid.datagrid('hideColumn','itemTime18Flag');
				detailDatagrid.datagrid('hideColumn','itemTime19Flag');
				detailDatagrid.datagrid('hideColumn','itemTime20Flag');
				detailDatagrid.datagrid('hideColumn','itemTime21Flag');
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
	function removeAutocar() {
		var rows = detailDatagrid.datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			parent.sy.messagerConfirm(
				'请确认',
				'您要删除当前所选择项信息？',
				function(flag) {
					if (flag) {
						for (var i = 0; i < rows.length; i++) {
							ids.push(rows[i].id);
						}
						$.ajax({
							url : '${pageContext.request.contextPath}/demo/autocarArrangeAction!delete.action',
							data : {ids : ids.join(',')},
							dataType : 'json',
							success : function(d) {
								detailDatagrid.datagrid('load');
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
			parent.sy.messagerAlert('提示信息', '请勾选要删除的记录！', 'error');
		}
	}
	
	//更新排班
	function editAutocar(){
		var itemTime8Temp=undefined;
		var itemTime9Temp=undefined;
		var itemTime10Temp=undefined;
		var itemTime11Temp=undefined;
		var itemTime12Temp=undefined;
		var itemTime13Temp=undefined;
		var itemTime14Temp=undefined;
		var itemTime15Temp=undefined;
		var itemTime16Temp=undefined;
		var itemTime17Temp=undefined;
		var itemTime18Temp=undefined; 
		var itemTime19Temp=undefined;
		var itemTime20Temp=undefined;
		var itemTime21Temp=undefined; 
		var rows = detailDatagrid.datagrid('getSelections');
		//编辑时只能单选一行进行编辑
		if (rows.length == 1) {
		 addDialog = parent.sy.dialog({
				title : '<div style=color:#0099FF align=center>更新车辆排班信息</div>',
				href : '${pageContext.request.contextPath}/demo/autocarArrangeAction!toAutocarArrangeEditPage.action?orgId='+rows[0].schoolArea,
				width : 660,
				height : 560,
				buttons : [
						{
							text : '更新',
							iconCls : 'icon-edit',
							handler : function() {
								//异步更新教练排班明细信息
								$.ajax({
									url : '${pageContext.request.contextPath}/demo/autocarArrangeAction!edit.action',
									data : {id:rows[0].id,
											carId:rows[0].carId,
											itemTime8:itemTime8Temp.combobox('getValue'),
											itemTime9:itemTime9Temp.combobox('getValue'),
											itemTime10:itemTime10Temp.combobox('getValue'),
											itemTime11:itemTime11Temp.combobox('getValue'),
											itemTime12:itemTime12Temp.combobox('getValue'),
											itemTime13:itemTime13Temp.combobox('getValue'),
											itemTime14:itemTime14Temp.combobox('getValue'),
											itemTime15:itemTime15Temp.combobox('getValue'),
											itemTime16:itemTime16Temp.combobox('getValue'),
											itemTime17:itemTime17Temp.combobox('getValue'),
											itemTime18:itemTime18Temp.combobox('getValue'),
											itemTime19:itemTime19Temp.combobox('getValue'),
											itemTime20:itemTime20Temp.combobox('getValue'),
											itemTime21:itemTime21Temp.combobox('getValue'),
											itemTime8Flag:rows[0].itemTime8Flag,
											itemTime9Flag:rows[0].itemTime9Flag,
											itemTime10Flag:rows[0].itemTime10Flag,
											itemTime11Flag:rows[0].itemTime11Flag,
											itemTime12Flag:rows[0].itemTime12Flag,
											itemTime13Flag:rows[0].itemTime13Flag,
											itemTime14Flag:rows[0].itemTime14Flag,
											itemTime15Flag:rows[0].itemTime15Flag,
											itemTime16Flag:rows[0].itemTime16Flag,
											itemTime17Flag:rows[0].itemTime17Flag,
											itemTime18Flag:rows[0].itemTime18Flag,
											itemTime19Flag:rows[0].itemTime19Flag,
											itemTime20Flag:rows[0].itemTime20Flag,
											itemTime21Flag:rows[0].itemTime21Flag,
										},
									dataType : 'json',
									success : function(d) {
										var json=$.parseJSON(d);
										addDialog.dialog('close');
										detailDatagrid.datagrid('reload');
										detailDatagrid.datagrid('unselectAll');
										parent.sy.messagerShow({
											title : '提示信息',
											msg : d.msg
										});
									},
								
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
							var f=addDialog.find('form');
								//初始化预约日期
								var arrangeDateTemp=f.find('input[name=arrangeDate]');
								var str=rows[0].arrangeDate;
								arrangeDateTemp.val(str.substring(0,10));
								arrangeDateTemp.attr('readonly',true);
								//初始化用户选择预约时间段
								var itemTime8flagTemp=f.find('input[name=ItemTime8Flag]');
								var itemTime9flagTemp=f.find('input[name=ItemTime9Flag]');
								var itemTime10flagTemp=f.find('input[name=ItemTime10Flag]');
								var itemTime11flagTemp=f.find('input[name=ItemTime11Flag]');
								var itemTime12flagTemp=f.find('input[name=ItemTime12Flag]');
								var itemTime13flagTemp=f.find('input[name=ItemTime13Flag]');
								var itemTime14flagTemp=f.find('input[name=ItemTime14Flag]');
								var itemTime15flagTemp=f.find('input[name=ItemTime15Flag]');
								var itemTime16flagTemp=f.find('input[name=ItemTime16Flag]');
								var itemTime17flagTemp=f.find('input[name=ItemTime17Flag]');
								var itemTime18flagTemp=f.find('input[name=ItemTime18Flag]'); 
								var itemTime19flagTemp=f.find('input[name=ItemTime19Flag]'); 
								var itemTime20flagTemp=f.find('input[name=ItemTime20Flag]'); 
								var itemTime21flagTemp=f.find('input[name=ItemTime21Flag]');
								itemTime8Temp=f.find('input[name=ItemTime8]');
								itemTime9Temp=f.find('input[name=ItemTime9]');
								itemTime10Temp=f.find('input[name=ItemTime10]');
								itemTime11Temp=f.find('input[name=ItemTime11]');
								itemTime12Temp=f.find('input[name=ItemTime12]');
								itemTime13Temp=f.find('input[name=ItemTime13]');
								itemTime14Temp=f.find('input[name=ItemTime14]');
								itemTime15Temp=f.find('input[name=ItemTime15]');
								itemTime16Temp=f.find('input[name=ItemTime16]');
								itemTime17Temp=f.find('input[name=ItemTime17]');
								itemTime18Temp=f.find('input[name=ItemTime18]'); 
								itemTime19Temp=f.find('input[name=ItemTime19]'); 
								itemTime20Temp=f.find('input[name=ItemTime20]'); 
								itemTime21Temp=f.find('input[name=ItemTime21]'); 
								
								//初始化时段选择器
								var url='${pageContext.request.contextPath}/demo/json/autocarArrangeType_combobox.json';
								initComboboxData(itemTime8Temp, rows[0].itemTime8, url, true,true);
								initComboboxData(itemTime9Temp, rows[0].itemTime9, url, true,true);
								initComboboxData(itemTime10Temp, rows[0].itemTime10, url, true,true);
								initComboboxData(itemTime11Temp, rows[0].itemTime11, url, true,true);
								initComboboxData(itemTime12Temp, rows[0].itemTime12, url, true,true);
								initComboboxData(itemTime13Temp, rows[0].itemTime13, url, true,true);
								initComboboxData(itemTime14Temp, rows[0].itemTime14, url, true,true);
								initComboboxData(itemTime15Temp, rows[0].itemTime15, url, true,true);
								initComboboxData(itemTime16Temp, rows[0].itemTime16, url, true,true);
								initComboboxData(itemTime17Temp, rows[0].itemTime17, url, true,true);
								initComboboxData(itemTime18Temp, rows[0].itemTime18, url, true,true);
								initComboboxData(itemTime19Temp, rows[0].itemTime19, url, true,true);
								initComboboxData(itemTime20Temp, rows[0].itemTime20, url, true,true);
								initComboboxData(itemTime21Temp, rows[0].itemTime21, url, true,true);
								//禁止用户选择
								var autocarItemTime=f.find('input[class=autocar_itemTime]');
								autocarItemTime.combobox({
									readonly:true,
								});
								//初始化排班时段使用标记
								initUpdateChecked(itemTime8flagTemp,itemTime8Temp,rows[0].itemTime8);
								initUpdateChecked(itemTime9flagTemp,itemTime9Temp,rows[0].itemTime9);
								initUpdateChecked(itemTime10flagTemp,itemTime10Temp,rows[0].itemTime10);
								initUpdateChecked(itemTime11flagTemp,itemTime11Temp,rows[0].itemTime11);
								initUpdateChecked(itemTime12flagTemp,itemTime12Temp,rows[0].itemTime12);
								initUpdateChecked(itemTime13flagTemp,itemTime13Temp,rows[0].itemTime13);
								initUpdateChecked(itemTime14flagTemp,itemTime14Temp,rows[0].itemTime14);
								initUpdateChecked(itemTime15flagTemp,itemTime15Temp,rows[0].itemTime15);
								initUpdateChecked(itemTime16flagTemp,itemTime16Temp,rows[0].itemTime16);
								initUpdateChecked(itemTime17flagTemp,itemTime17Temp,rows[0].itemTime17);
								initUpdateChecked(itemTime18flagTemp,itemTime18Temp,rows[0].itemTime18);
								initUpdateChecked(itemTime19flagTemp,itemTime19Temp,rows[0].itemTime19);
								initUpdateChecked(itemTime20flagTemp,itemTime20Temp,rows[0].itemTime20);
								initUpdateChecked(itemTime21flagTemp,itemTime21Temp,rows[0].itemTime21);
						},
			});
		} else if (rows.length > 1) {
			parent.sy.messagerAlert('提示信息', '同一时间只能更新一条记录！', 'error');
		} else {
			parent.sy.messagerAlert('提示信息', '请选择要更新的记录！', 'error');
		}
	}
	
	//初始化排班时段使用标记
	function initUpdateChecked(obj,targer,initVal){
		if(1==initVal){
			obj.attr('checked',true);
		}else if(0==initVal){
			obj.val(0);
			obj.attr('checked',false);
		}else if(3==initVal){
			obj.attr('disabled',true);
			obj.attr('checked',true);
		}
		obj.change(function(){
			if(obj.is(':checked')){
				targer.combobox('setValue',1);
			}else{
				targer.combobox('setValue',0);
			}
		});
	}
	
	
	//添加排班
	function appendAutocar(p,schoolArea,carId,carNo,usingState){
		if(2!=usingState){
			parent.sy.messagerAlert('提示信息', '当前车辆状态没有处于启用状态，无法添加排班！', 'question');
			return ;
		}
		 addDialog = parent.sy.dialog({
				title : '排班信息录入',
				href : '${pageContext.request.contextPath}/demo/autocarArrangeAction!toAutocarArrangeAddPage.action',
				width : 660,
				height : 600,
				buttons : [
						{
							text : '添加',
							iconCls : 'icon-add',
							handler : function() {
									var btnAdd = this;
									$(btnAdd).hide();
									var addForm = addDialog.find('form[id=demo_autocarArrange_addForm]');
									//提交表单
									addForm.form('submit',{
										//提交添加用户表单时，调用UserAction的add方法
										url : '${pageContext.request.contextPath}/demo/autocarArrangeAction!add.action',
										success : function(d) {
											$(btnAdd).show();
											var json = $.parseJSON(d);
											if (json.success) {
												detailDatagrid.datagrid('reload'); 
												mydatagrid.datagrid('reload'); 
												addDialog.dialog('close'); 
												parent.sy.messagerShow({
													msg : json.msg,
													title : '提示信息'
												});
											}
											parent.sy.messagerShow({
												msg : json.msg,
												title : '提示信息'
											});
										},
										error:function(err){
											$(btnAdd).show();
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
							var f=addDialog.find('form');
								var carIdTemp=f.find('input[name=carId]');
								carIdTemp.val(carId);
								var schoolAreaTemp=f.find('input[name=schoolArea]');
								schoolAreaTemp.val(schoolArea);
								var myYear=f.find('input[name=year]');
								var myMonth=f.find('input[name=month]');
								//初始化所属校区选项
								var schoolDepartment=f.find('input[name=schoolArea]');
								initSchoolArea(schoolDepartment,userSchoolArea);
								//初始化用户选择预约时间段
								initItemtime(f);
								var autocarItemTime=f.find('input[class=autocar_itemTime]');
								
								//全选按钮事件
								var itemTimeChecked=f.find('input[class=timeItem_checked]');
								var selectAllTemp=f.find('input[name=selectAll]');
								var cancelAllTemp=f.find('input[name=cancelAll]');
								//默认全选
								selectAllTemp.attr('checked',true);
								itemTimeChecked.attr('checked',true);
								selectAllTemp.change(function(){
									if(selectAllTemp.is(':checked')){
										itemTimeChecked.attr('checked',true);
										cancelAllTemp.attr('checked',false);
										autocarItemTime.combobox('setValue',1);
									}
								});
								//取消按钮事件
								cancelAllTemp.change(function(){
									if(cancelAllTemp.is(':checked')){
										itemTimeChecked.attr('checked',false);
										cancelAllTemp.attr('checked',true);
										selectAllTemp.attr('checked',false);
										autocarItemTime.combobox('setValue',0);
									}
									
								});
								//初始化时段选择器
								var itemTimeTemp=f.find('input[class=autocar_itemTime]');
								var url='${pageContext.request.contextPath}/demo/json/autocarArrangeType_combobox.json';
								initComboboxData(autocarItemTime, 1, url, true,true);
								//初始化年份选项
								var initYear=myYear.combobox({
									url:'${pageContext.request.contextPath}/demo/json/year_combobox.json',
								    valueField:'value',
								    textField:'text',
								    method:'post',
								    hasDownArrow : true, 
								    required:true,
								    editable:true,
							    //初始默认值被选中
								   
								});
								
								//初始月份选择
								var noarragedates=f.find('input[name=noArrangeDates]');
								var initMonth=myMonth.combobox({
									url:'${pageContext.request.contextPath}/demo/json/monthselect_combobox.json',
								    valueField:'value',
								    textField:'text',
								    method:'get',
								    required:true,
								    editable:true,
								    onClickIcon:function(){
								    	if(myYear.combobox('getValue')==""){
								    		parent.sy.messagerAlert('提示信息', '年份不能为空，请选择年份！', 'question');
								    	}
								    },
								    onChange:function(){
								    	//初始化排班日期
								    	if(myMonth.combobox('getValue')=="1"||myMonth.combobox('getValue')=="3"||
								    			myMonth.combobox('getValue')=="5"||myMonth.combobox('getValue')=="7"
								    				||myMonth.combobox('getValue')=="8"||myMonth.combobox('getValue')=="10"
								    					||myMonth.combobox('getValue')=="12"){
								    		arrangeDatesUrl='${pageContext.request.contextPath}/demo/json/noArrangeDate31_combobox.json';
										}else if(myMonth.combobox('getValue')=="4"||myMonth.combobox('getValue')=="6"||myMonth.combobox('getValue')=="9"||
												myMonth.combobox('getValue')=="11"){
											arrangeDatesUrl='${pageContext.request.contextPath}/demo/json/noArrangeDate30_combobox.json';
										}else if(myMonth.combobox('getValue')=="2"){
											var year=parseInt(myYear.combobox('getValue'));
											if((year % 4 == 0 && year % 100 != 0) || year % 400 == 0){
												arrangeDatesUrl='${pageContext.request.contextPath}/demo/json/noArrangeDate29_combobox.json';
											}else{
												arrangeDatesUrl='${pageContext.request.contextPath}/demo/json/noArrangeDate28_combobox.json';
											}
										}
								    	//排班日combobox
								    	noarragedates.combobox({
											url:arrangeDatesUrl,
										    valueField:'value',
										    textField:'text',
										    multiple:true,
										    multiline:true,
										    method: 'get',
										    required:true,
										});
								    }
								});
						},
						onLoadSuccess:function(){
							
						},
			});
	}
	
	//初始化教练排班时间段
	function initItemtime(f){
		var itemTime8flagTemp=f.find('input[name=ItemTime8Flag]');
		var itemTime9flagTemp=f.find('input[name=ItemTime9Flag]');
		var itemTime10flagTemp=f.find('input[name=ItemTime10Flag]');
		var itemTime11flagTemp=f.find('input[name=ItemTime11Flag]');
		var itemTime12flagTemp=f.find('input[name=ItemTime12Flag]');
		var itemTime13flagTemp=f.find('input[name=ItemTime13Flag]');
		var itemTime14flagTemp=f.find('input[name=ItemTime14Flag]');
		var itemTime15flagTemp=f.find('input[name=ItemTime15Flag]');
		var itemTime16flagTemp=f.find('input[name=ItemTime16Flag]');
		var itemTime17flagTemp=f.find('input[name=ItemTime17Flag]');
		var itemTime18flagTemp=f.find('input[name=ItemTime18Flag]'); 
		var itemTime19flagTemp=f.find('input[name=ItemTime19Flag]'); 
		var itemTime20flagTemp=f.find('input[name=ItemTime20Flag]'); 
		var itemTime21flagTemp=f.find('input[name=ItemTime21Flag]');
		var itemTime8Temp=f.find('input[name=ItemTime8]');
		var itemTime9Temp=f.find('input[name=ItemTime9]');
		var itemTime10Temp=f.find('input[name=ItemTime10]');
		var itemTime11Temp=f.find('input[name=ItemTime11]');
		var itemTime12Temp=f.find('input[name=ItemTime12]');
		var itemTime13Temp=f.find('input[name=ItemTime13]');
		var itemTime14Temp=f.find('input[name=ItemTime14]');
		var itemTime15Temp=f.find('input[name=ItemTime15]');
		var itemTime16Temp=f.find('input[name=ItemTime16]');
		var itemTime17Temp=f.find('input[name=ItemTime17]');
		var itemTime18Temp=f.find('input[name=ItemTime18]'); 
		var itemTime19Temp=f.find('input[name=ItemTime19]'); 
		var itemTime20Temp=f.find('input[name=ItemTime20]'); 
		var itemTime21Temp=f.find('input[name=ItemTime21]'); 
		initChecked(itemTime8flagTemp,itemTime8Temp,f);
		initChecked(itemTime9flagTemp,itemTime9Temp,f);
		initChecked(itemTime10flagTemp,itemTime10Temp,f);
		initChecked(itemTime11flagTemp,itemTime11Temp,f);
		initChecked(itemTime12flagTemp,itemTime12Temp,f);
		initChecked(itemTime13flagTemp,itemTime13Temp,f);
		initChecked(itemTime14flagTemp,itemTime14Temp,f);
		initChecked(itemTime15flagTemp,itemTime15Temp,f);
		initChecked(itemTime16flagTemp,itemTime16Temp,f);
		initChecked(itemTime17flagTemp,itemTime17Temp,f);
		initChecked(itemTime18flagTemp,itemTime18Temp,f);
		initChecked(itemTime19flagTemp,itemTime19Temp,f);
		initChecked(itemTime20flagTemp,itemTime20Temp,f);
		initChecked(itemTime21flagTemp,itemTime21Temp,f);
	}
	
	function initChecked(obj,targer,f){
		obj.change(function(){
			if(obj.is(':checked')){
				targer.combobox('setValue',1);
				var cancelAllTemp=f.find('input[name=cancelAll]');
				cancelAllTemp.attr('checked',false);
			}else{
				targer.combobox('setValue',0);
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
	<div data-options="region:'north',border:false,title:'查询功能'" style="height:59px;overflow: hidden;" align="left">
		<!-- 车辆使用信息查询表单 -->
		<form id="demo_usingcar_searchForm">
			<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
				<tr>
					<th style="width: 70px;">所属校区：</th>
					<td><input id="demo_usingcar_organization" name="schoolArea" style="width:150px;" /></td>
					<th  style="width: 70px;">车牌号:</th>
					<td><input name="carNo" style="width:150px;" /></td>
					<th style="width: 70px;">行驶证号：</th>
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
	<div id="demo_autocarArrange_menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="detail();" data-options="iconCls:'icon-add'">排班名细</div>
	</div>
</body>
</html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">
	var mydatagrid=undefined;
	var rechargeDatagrid=undefined;
	var oilcardUsingDtagrid=undefined;
	
	var oilcardAddDialog=undefined;
	var oilcardEditDialog=undefined;
	var rechargeMainDialog=undefined;
	var rechargeAddDialog=undefined;
	var rechargeEditDialog=undefined;
	var oilcardUsingMainDialog=undefined;
	var oilcardUsingAddDialog=undefined;
	var oilcardUsingEditDialog=undefined;
	
	var userSchoolArea=undefined;
	$(function() {
		$.ajax({
			url : '${pageContext.request.contextPath}/demo/studentAction!getSchoolArea.action',
			dataType : 'json',
			success : function(d) {
				userSchoolArea=d;
			}
		});
		
		rechargeDatagrid=$('#demo_rechargeList_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/demo/rechargeOilcardAction!datagrid.action',
			title:'油卡充值明细信息列表',
			iconCls : 'icon-save', /* 标题前边的图标 */
			pagination : true, /* 是否显示页码 */
			pagePosition : 'bottom', /* 页码显示的位置 */
			pageSize : 20, /* 每面显示的记录数 */
			pageList : [ 10, 20, 30, 40,50,100 ], /* 选择每面显示的记录数 */
			fit : true,
			fitColumns : false, //列自动调整功能
			nowrap : false, //以行的形式进行显示
			border : false,
			rownumbers: true,
			idField : 'oilCardId', //此属性一定要配置
			sortName : 'rechargeDate', //列排序
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
			},{
				title : '<font color="#0099FF">所属校区</font>',
				field : 'schoolAreaName',
				sortable : true,
				width : 100,
				align : 'center',
			},{
				title : '<font color="#0099FF">卡号</font>',
				field : 'oilCardNo',
				sortable : true,
				width : 100,
				align : 'center',
			},{
				title : '<font color="#0099FF">充值前余额</font>',
				field : 'beforeBalance',
				sortable : true,
				width : 100,
				align : 'center',
			},{
				title : '<font color="#0099FF">充值金额</font>',
				field : 'money',
				sortable : true,
				width : 100,
				align : 'center',
			},{
				title : '<font color="#0099FF">充值后余额</font>',
				field : 'afterBalance',
				sortable : true,
				width : 100,
				align : 'center',
			}, {
				title : '<font color="#0099FF">充值日期</font>',
				field : 'rechargeDate',
				sortable : true,
				width : 100,
				align : 'center',
				formatter:function(value,row,index){
					return formatTimeYMD(value,row,index);
				},
			},] ],
			//普通列
			columns : [ [{
				title : '<font color="#0099FF">创建人</font>',
				field : 'rechargePerson',
				sortable : true,
				width : 100,
				align : 'center',
			}, {
				title : '<font color="#0099FF">备注</font>',
				field : 'remarks',
				sortable : true,
				width : 200,
				align : 'center',
			},{
				title : '<font color="#0099FF">schoolArea</font>',
				field : 'schoolArea',
				sortable : true,
				width : 60,
				align : 'center',
			},{
				title : '<font color="#0099FF">oilCardId</font>',
				field : 'oilCardId',
				sortable : true,
				width : 60,
				align : 'center',
			},
			] ],
			//菜单功能设置
			toolbar : [  {
				text : '取消选中',
				iconCls : 'icon-undo',
				handler : function() {
					//取消所有的选中行
					rechargeDatagrid.datagrid('clearSelections');
					//取消当前页面的选中行
					rechargeDatagrid.datagrid('unselectAll');
					rechargeDatagrid.datagrid('uncheckAll');
				}
			}, {
				text : '刷新',
				iconCls : 'icon-reload',
				handler : function() {
					rechargeDatagrid.datagrid('reload');
					//取消当前页面的选中行
					rechargeDatagrid.datagrid('unselectAll');
					//取消当前页面的选中行
					rechargeDatagrid.datagrid('uncheckAll');
				}
			}, ],
			onLoadSuccess : function (data) {
				initSchoolArea($('#demo_schoolArea_search'));
				
				rechargeDatagrid.datagrid("fixRownumber");
				//datagrid没有数据时显示没有记录信息
				if(data.total==0){
					rechargeDatagrid.datagrid('appendRow', {
						oilCardNo: '<div style="text-align:center;color:red">没有记录信息！</div>' })
					.datagrid('mergeCells', { index: 0, field: 'oilCardNo', colspan:5 });
				}
				rechargeDatagrid.datagrid('hideColumn','oilCardId');
				rechargeDatagrid.datagrid('hideColumn','schoolArea');
			},
			//右键菜单所触发的事件
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				//取消当前页被选中的所有行
				rechargeDatagrid.datagrid('unselectAll');
				rechargeDatagrid.datagrid('selectRow', rowIndex);
				$('#demo_rechargeOilcard_menu').menu('show', {
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
	
	
	//查询按钮功能函数
	function oilcardSearch() {
		rechargeDatagrid.datagrid('load', sy.serializeObject($('#demo_rechargeoilcard_searchForm')));
	}

	//取消查询功能按钮
	function cleanSearch() {
		rechargeDatagrid.datagrid('load', {});
		rechargeDatagrid.datagrid('unselectAll');
		//取消当前页面的选中行
		rechargeDatagrid.datagrid('uncheckAll');
		//清空与查询有关的输入框
		$('#demo_rechargeoilcard_searchForm input').val('');
	}
	
	
</script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询功能'" style="height: 90px;overflow: hidden;" align="left">
		<!-- 教练排班信息查询表单 -->
		<form id="demo_rechargeoilcard_searchForm">
			<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
				<tr>
					<th style="width:65px">所属校区:</th>
					<td><input id="demo_schoolArea_search" name="schoolArea"  style="width:150px;" /></td>
					<th style="width:65px">卡号:</th>
					<td><input  name="oilCardNo"  style="width:150px;" /></td>
					<th style="width:65px">日期开始:</th>
					<td><input  name="rechargeDateStart" class="easyui-datebox" data-options="editable:false"  style="width:150px;" /></td>
					<th style="width:65px">日期结束</th>
					<td><input name="rechargeDateEnd" class="easyui-datebox" data-options="editable:false"  style="width:150px;" /></td>
				</tr>
				<tr>
					<th ></th>
					<th ></th>
					<td colspan="4">
						<a href="javascript:void(0);" id="oilcard_a_searche" class="easyui-linkbutton" iconCls="icon-search" onclick="oilcardSearch();">查询</a>
						<a href="javascript:void(0);" id="oilcard_a_canel" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cleanSearch();">取消</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<table id="demo_rechargeList_datagrid"></table>
	</div>
	<!-- 操作按钮事件 -->
	<div id="demo_rechargeOilcard_menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="cleanSearch();" data-options="iconCls:'icon-add'">刷新</div>
		<div onclick="cleanSearch();" data-options="iconCls:'icon-remove'">取消选中</div>
	</div>
	
</body>
</html>
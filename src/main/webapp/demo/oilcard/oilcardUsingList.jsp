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
		userSchoolArea=getUserSchoolArea();
		
		oilcardUsingDatagrid=$('#demo_oilcardUsingList_datagrid').datagrid({
					url : '${pageContext.request.contextPath}/demo/oilcardUsingAction!datagrid.action',
					title:'油卡使用明细信息列表',
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
					sortName : 'usingDate', //列排序
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
						title : '<font color="#0099FF">卡号</font>',
						field : 'oilCardNo',
						sortable : true,
						width : 100,
						align : 'center',
					},{
						title : '<font color="#0099FF">车牌号</font>',
						field : 'carNo',
						sortable : true,
						width : 100,
						align : 'center',
					},{
						title : '<font color="#0099FF">使用日期</font>',
						field : 'usingDate',
						sortable : true,
						width : 100,
						align : 'center',
						formatter:formatTimeYMD,
					},{
						title : '<font color="#0099FF">油料名称</font>',
						field : 'oilName',
						sortable : true,
						width : 100,
						align : 'center',
						formatter:function(value,row,index){
							return formatOilName(value,row,index);
						},
					}, {
						title : '<font color="#0099FF">油料牌号</font>',
						field : 'oilType',
						sortable : true,
						width : 100,
						align : 'center',
						formatter:function(value,row,index){
							return formatOilType(value,row,index);
						},
					},] ],
					//普通列
					columns : [ [{
						title : '<font color="#0099FF">数量(升)</font>',
						field : 'oilTotal',
						sortable : true,
						width : 100,
						align : 'center',
					},{
						title : '<font color="#0099FF">单价(元)</font>',
						field : 'price',
						sortable : true,
						width : 100,
						align : 'center',
					},{
						title : '<font color="#0099FF">合计(元)</font>',
						field : 'moneyTotal',
						sortable : true,
						width : 100,
						align : 'center',
					},{
						title : '<font color="#0099FF">领料员</font>',
						field : 'usingPerson',
						sortable : true,
						width : 100,
						align : 'center',
					},{
						title : '<font color="#0099FF">借卡时间</font>',
						field : 'startTime',
						sortable : true,
						width : 100,
						align : 'center',
					},{
						title : '<font color="#0099FF">还卡时间</font>',
						field : 'endTime',
						sortable : true,
						width : 100,
						align : 'center',
					},{
						title : '<font color="#0099FF">组别</font>',
						field : 'groupType',
						sortable : true,
						width : 100,
						align : 'center',
					},{
						title : '<font color="#0099FF">创建人</font>',
						field : 'operator',
						sortable : true,
						width : 100,
						align : 'center',
					},  {
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
							oilcardUsingDatagrid.datagrid('clearSelections');
							//取消当前页面的选中行
							oilcardUsingDatagrid.datagrid('unselectAll');
							oilcardUsingDatagrid.datagrid('uncheckAll');
						}
					}, {
						text : '刷新',
						iconCls : 'icon-reload',
						handler : function() {
							oilcardUsingDatagrid.datagrid('reload');
							//取消当前页面的选中行
							oilcardUsingDatagrid.datagrid('unselectAll');
							//取消当前页面的选中行
							oilcardUsingDatagrid.datagrid('uncheckAll');
						}
					}, ],
					onLoadSuccess : function (data) {
						oilcardUsingDatagrid.datagrid("fixRownumber");
						oilcardUsingDatagrid.datagrid('hideColumn','oilCardId');
						oilcardUsingDatagrid.datagrid('hideColumn','schoolArea');
						//初始化校区选项
						initSchoolArea($("#demo_schoolArea_search"),userSchoolArea);
						//选择车辆
						$("#carNo").combobox({
							url : '${pageContext.request.contextPath}/demo/carAction!getAllCarsForCombobox.action',
							queryParams:{schoolArea:userSchoolArea},
							valueField : 'value',
							textField : 'text',
							method:'get',
							required:false,
							editable:false,
						});
						//datagrid没有数据时显示没有记录信息
						if(data.total==0){
							oilcardUsingDatagrid.datagrid('appendRow', {
								oilCardNo: '<div style="text-align:center;color:red">没有记录信息！</div>' })
							.datagrid('mergeCells', { index: 0, field: 'oilCardNo', colspan:10 });
						}
						
					},
					//右键菜单所触发的事件
					onRowContextMenu : function(e, rowIndex, rowData) {
						e.preventDefault();
						//取消当前页被选中的所有行
						oilcardUsingDatagrid.datagrid('unselectAll');
						oilcardUsingDatagrid.datagrid('selectRow', rowIndex);
						$('#demo_oilcardUsing_menu').menu('show', {
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
	function oilcardUsingSearch() {
		oilcardUsingDatagrid.datagrid('load', sy.serializeObject($('#demo_oilcardUsing_searchForm')));
	}

	//取消查询功能按钮
	function cleanSearch() {
		oilcardUsingDatagrid.datagrid('load', {});
		oilcardUsingDatagrid.datagrid('unselectAll');
		//取消当前页面的选中行
		oilcardUsingDatagrid.datagrid('uncheckAll');
		//清空与查询有关的输入框
		$('#demo_oilcardUsing_searchForm input').val('');
	}
	
	
</script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询功能'" style="height: 90px;overflow: hidden;" align="left">
		<!-- 教练排班信息查询表单 -->
		<form id="demo_oilcardUsing_searchForm">
			<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
				<tr>
					<th style="width:65px">所属校区:</th>
					<td><input id="demo_schoolArea_search" name="schoolArea"  style="width:150px;" /></td>
					<th style="width:65px">教练:</th>
					<td><input id="usingPerson" name="usingPerson"  style="width:150px;" /></td>
					<th style="width:65px">车牌号:</th>
					<td><input id="carNo" name="carNo"  style="width:150px;" /></td>
					<th style="width:65px">卡号:</th>
					<td><input  name="oilCardNo"  style="width:150px;" /></td>
				</tr>
				<tr>
					<th style="width:65px">日期开始:</th>
					<td><input  name="usingDateStart" class="easyui-datebox" data-options="editable:false"  style="width:150px;" /></td>
					<th style="width:65px">日期结束</th>
					<td><input name="usingDateEnd" class="easyui-datebox" data-options="editable:false"  style="width:150px;" /></td>
					<td colspan="4">
						<a href="javascript:void(0);" id="oilcard_a_searche" class="easyui-linkbutton" iconCls="icon-search" onclick="oilcardUsingSearch();">查询</a>
						<a href="javascript:void(0);" id="oilcard_a_canel" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cleanSearch();">取消</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<table id="demo_oilcardUsingList_datagrid"></table>
	</div>
	<!-- 操作按钮事件 -->
	<div id="demo_rechargeOilcard_menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="cleanSearch();" data-options="iconCls:'icon-add'">刷新</div>
		<div onclick="cleanSearch();" data-options="iconCls:'icon-remove'">取消选中</div>
	</div>
	
</body>
</html>
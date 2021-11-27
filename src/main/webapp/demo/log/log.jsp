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
		mydatagrid = $('#demo_person_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/demo/logAction!datagrid.action',/* 从后台读取所有user数据 */
			title : '日记信息管理列表!', /* 表头标题 */
			iconCls : 'icon-save', /* 标题前边的图标 */
			pagination : false, /* 是否显示页码 */
			pagePosition : 'bottom', /* 页码显示的位置 */
			pageSize : 10, /* 每面显示的记录数 */
			pageList : [ 10, 20, 30, 40,50 ,100,200 ], /* 选择每面显示的记录数 */
			fit : true,
			fitColumns : false, //列自动调整功能
			nowrap : false, //以行的形式进行显示
			border : false,
			rownumbers: true,
			idField : 'id', //此属性一定要配置
			sortName : 'operTime', //列排序
			sortOrder : 'desc', //列排序
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
				title : '<font color="#0099FF">所属校区</font>',
				field : 'schoolAreaName',
				sortable : true,
				width : 100 ,
				formatter:showAllMsg,
			},{
				title : '<font color="#0099FF">操作人</font>',
				field : 'operator',
				sortable : true,
				width : 170,
				align : 'left',
				formatter:showAllMsg,
			},{
				title : '<font color="#0099FF">操作方法</font>',
				field : 'operName',
				width : 150,
				sortable : true,
				align : 'center',
				formatter:showAllMsg,
			},{
				title : '<font color="#0099FF">操作参数</font>',
				field : 'operParams',
				sortable : true,
				width : 150,
				formatter:showAllMsg,
			} ] ],
			//普通列
			columns : [ [{
				title : '<font color="#0099FF">操作学结果</font>',
				field : 'operResult',
				sortable : true,
				width : 80,
				formatter:showAllMsg,
			} ,{
				title : '<font color="#0099FF">操作时间</font>',
				field : 'operTime',
				sortable : true,
				width : 150,
				align : 'center',
				formatter:showAllMsg,
			},{
				title : '<font color="#0099FF">结果消息</font>',
				field : 'resultMsg',
				sortable : true,
				width : 150,
				align : 'center',
				formatter:showAllMsg,
			}, {
				title : '<font color="#0099FF">结果消息</font>',
				field : 'schoolArea',
				sortable : true,
				width : 150,
				align : 'center',
				formatter:showAllMsg,
			}, 
			] ],
			//菜单功能设置
			toolbar : [ {
				text : '取消选中',
				iconCls : 'icon-undo',
				handler : function() {
					//取消所有的选中行
					mydatagrid.datagrid('clearSelections');
					//取消当前页面的选中行
					mydatagrid.datagrid('unselectAll');
				}
			},],
			onLoadSuccess : function (data) {
				//修改序号bug
				$(this).datagrid("fixRownumber");
				$(this).datagrid('hideColumn','schoolArea');
				initSchoolArea($("#demo_personDepartment"),userSchoolArea);
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
				$('#demo_person_menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			}
		});

	});
	//完整显示提示信息
	function showAllMsg(value, rowData, rowIndex) {
		return sy.showtip('<span title="{0}">{1}</span>', value, value);
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
		mydatagrid.datagrid('load', sy.serializeObject($('#demo_person_searchForm')));
	}

	//取消查询功能按钮
	function cleanSearch() {
		mydatagrid.datagrid('load', {});
		//清空与查询有关的输入框
		$('#demo_person_searchForm input').val('');
	}

</script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询功能'" style="height: 85px;overflow: hidden;" align="left">
		<!-- 日记信息查询表单 -->
		<form id="demo_person_searchForm">
			<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
				<tr>
					<th>所属部门：</th>
					<td><input id="demo_personDepartment" name="schoolArea" style="width:150px;" />
	 					</td>
					<th>日期开始:</th>
				<td>
					<input name="startTime" class="easyui-datebox mydateFormat" data-options="showSeconds:false,editable:false" 
					 style="width: 155px;" />
				</td>
					<th>日期结束:</th>
				<td>
					<input name="endTime" class="easyui-datebox mydateFormat" data-options="showSeconds:false,editable:false"
					 style="width: 155px;" />
				</td>
				</tr>
				<tr>
					<th>操作員:</th>
				<td><input name="operator" style="width: 155px;" /></td>
					<td colspan="2">
						<a href="javascript:void(0);" id="student_a_searche" class="easyui-linkbutton" iconCls="icon-search" onclick="mySearch();">查询</a>
						<a href="javascript:void(0);" id="student_a_canel" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cleanSearch();">取消</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<table id="demo_person_datagrid"></table>
	</div>
	<!-- 操作按钮事件 -->
	<div id="demo_person_menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="remove();" data-options="iconCls:'icon-remove'">删除</div>
	</div>
</body>
</html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">
	var mydatagrid=undefined;
	var initPersonDepartment=undefined;
	var trainerID=undefined;
	var detailDialog=undefined;
	var detailDatagrid=undefined;
	
	var userSchoolArea=undefined;
	$(function() {
		$.ajax({
			url : '${pageContext.request.contextPath}/demo/studentAction!getSchoolArea.action',
			dataType : 'json',
			success : function(d) {
				userSchoolArea=d;
			}
		});
		
		mydatagrid = $('#demo_trainerArrange_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/demo/trainerArrangeAction!personalDatagrid.action',/* 从后台读取所有user数据 */
			title : '教练排班信息管理列表!', /* 表头标题 */
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
			checkOnSelect : false,
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
			},{
				title : '<font color="#0099FF">所属校区</font>',
				field : 'trainerSchoolAreaName',
				width : 120,
				align : 'center'
			},{
				title : '<font color="#0099FF">教练姓名</font>',
				field : 'trainerName',
				width : 80,
				align : 'center'
			},{
				title : '<font color="#0099FF">身份证</font>',
				field : 'trainerIdentity',
				width : 150,
				sortable:true,
				align : 'center'
			},{
				title : '<font color="#0099FF">联系电话</font>',
				field : 'trainerPhone',
				width : 120,
				sortable:true,
				align : 'center'
			}] ],
			//普通列
			columns : [ [{
				title : '<font color="#0099FF">驾照类型</font>',
				field : 'trainerDriverType',
				width : 75,
				align : 'center',
				formatter:function(value ,row ,index){
					return formatterDriverType(value,row,index);
				}
			},{
				title : '<font color="#0099FF">教练ID</font>',
				field : 'trainerId',
				width : 120,
				align : 'left'
			},{
				title : '<font color="#0099FF">校区标识</font>',
				field : 'schoolArea',
				width : 120,
				align : 'left'
			}
			] ],
			//菜单功能设置
			toolbar : [ {
				text : '排班明细',
				iconCls : 'icon-add',
				handler : function() {
					arrangeDetail();
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
				$(this).datagrid('hideColumn','trainerId');
				$(this).datagrid('hideColumn','schoolArea');
				//datagrid没有数据时显示没有记录信息
				if(data.total==0){
					$(this).datagrid('appendRow', {
						trainerSchoolAreaName: '<div style="text-align:center;color:red">没有记录信息！</div>' })
					.datagrid('mergeCells', { index: 0, field: 'trainerSchoolAreaName', colspan:14 });
				}
			},
			//右键菜单所触发的事件
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				//取消当前页被选中的所有行
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow', rowIndex);
				$('#demo_trainerArrange_menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			}
		});
		//初始化训练时间段选择选项
		$('#demo_tainerArrange_search').combobox({
		    url:'${pageContext.request.contextPath}/demo/json/traintime_combobox.json',
		    valueField:'value',
		    textField:'text',
		    multiple:true,
		    multiline:true,
		});	
		//初始化教练选择选项
		$('#demo_trainer_search').combobox({
			url:'${pageContext.request.contextPath}/demo/trainerArrangeAction!getTrainterNames.action',
		    valueField:'value',
		    textField:'text',
		    method: 'post',
		});
		//初始化年份选项
		$('#demo_year_search').combobox({
			url:'${pageContext.request.contextPath}/demo/json/year_combobox.json',
		    valueField:'value',
		    textField:'text',
		    multiple:true,
		    multiline:true,
		    method:'get',
		    hasDownArrow : true, 
		});
		
		//初始月份选择
		$('#demo_month_search').combobox({
			url:'${pageContext.request.contextPath}/demo/json/monthselect_combobox.json',
		    valueField:'value',
		    textField:'text',
		    multiple:true,
		    multiline:true,
		    method:'get',
		    hasDownArrow : true, 
		});
		//初始星期选择
		$('#demo_week_search').combobox({
			url:'${pageContext.request.contextPath}/demo/json/weekth_combobox.json',
		    valueField:'value',
		    textField:'text',
		    multiple:true,
		    multiline:true,
		    method:'get',
		    hasDownArrow : true, 
		});
	});
	
	//完整显示提示信息
	function showAllMsg(value, rowData, rowIndex) {
		return sy.showtip('<span title="{0}">{1}</span>', value, value);
	}
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
	
	//初始化显示驾照类型
	function formatterDriverType(value,row,index){
		driverTypeValue=value;
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
	
	//排班明细对话框
	function arrangeDetail() {
		var rows = mydatagrid.datagrid('getSelections');
		if (rows.length > 0) {
			detailDialog=parent.sy.dialog({
				title : '教练排班明细信息',
				href : '${pageContext.request.contextPath}/demo/trainerArrangeAction!toMyDetailPage.action',
				width : 960,
				height : 615,
				buttons : [
						{
							text : '关闭',
							iconCls : 'icon-cancel',
							handler : function() {
								detailDialog.dialog('close');
							}
						} ],
				//数据回显显示
				onLoad : function() {
					var f = detailDialog.find('form');
					//1.显示教练基本信息
					f.form('load', {
						id:rows[0].id,
						trainerId:rows[0].trainerId,
						trainerName:rows[0].trainerName,
						trainerPhone:rows[0].trainerPhone,
						trainerIdentity:rows[0].trainerIdentity,
						schoolArea:rows[0].trainerSchoolAreaName						
					});
					//2.禁止用户操作教练的基本信息
					var disableItem=f.find('input[class=demo_detail_update]');
					disableItem.attr('disabled',true);
					
					//初始化月份查询选项
					var startDate=f.find('input[name=startArrangeDate]');
					startDate.datebox({
					});
					var endDate=f.find('input[name=endArrangeDate]');
					endDate.datebox({
					});
					
					//保存当前教练标识
					trainerId=rows[0].trainerId;
					
					initArrangeDetailDatagrid(trainerId,rows[0].trainerName,rows[0].schoolArea);
					
					//查找按钮事件
					var searchEvent=f.find('a[id=reservationDetail_a_searche]');
					searchEvent.linkbutton({
					    iconCls: 'icon-search',
					    onClick:function(){
					    	detailDatagrid.datagrid('load', sy.serializeObject(f));
					    },
					});	
					//重围按钮
					var resetEvent=f.find('a[id=reservationDetail_a_cancel]');
					resetEvent.linkbutton({
					    iconCls: 'icon-cancel',
					    onClick:function(){
					    	startDate.datebox('setValue','');
					    	endDate.datebox('setValue','');
					    	detailDatagrid.datagrid('load', sy.serializeObject(f));
					    },
					});	
					
				}//end onload
			});
		} else {
			parent.sy.messagerAlert('提示信息', '请选择教练员！', 'question');
		}
		
	}
	
	//教练排班明细对话框中添加教练排班明细
	function initArrangeDetailDatagrid(trainerId,trainerName,schoolArea){
		var datagridId=detailDialog.find('table[id=demo_trainerArrange_datagrid]');
		detailDatagrid=datagridId.datagrid({
			url:'${pageContext.request.contextPath}/demo/trainerArrangeAction!detailDatagrid.action',
			queryParams:{trainerId:trainerId},
			title : '当前教练排班明细信息管理列表!', /* 表头标题 */
			iconCls : 'icon-save', /* 标题前边的图标 */
			pagination : true, /* 是否显示页码 */
			pagePosition : 'bottom', /* 页码显示的位置 */
			pageSize : 10, /* 每面显示的记录数 */
			pageList : [ 10, 20, 30, 40 ], /* 选择每面显示的记录数 */
			fit : true,
			fitColumns : false, //列自动调整功能
			nowrap : true, //以行的形式进行显示
			border : false,
			rownumbers: true,
			idField : 'id', //此属性一定要配置
			sortName : 'arrangeDate', //列排序
			sortOrder : 'desc', //列排序
			/* checkOnSelect : false,
			selectOnCheck : true, */
			striped : true,//表示条纹，隔行显示不现背景色
			frozenColumns : [ [{
				title : '编号',
				field : 'id',
				width : 260,
				sortable : true,
				checkbox : true,
			},{
				title : '<font color="#0099FF">创建日期</font>',
				field : 'arrangeDate',
				width : 110,
				sortable:true,
				align : 'center',
				formatter:showAllMsg,
			},
			 ] ],
			columns:[[{
				title : '<font color="#0099FF">年份</font>',
				field : 'year',
				width : 90,
				align : 'center',
				formatter:showAllMsg,
			},{
				title : '<font color="#0099FF">月分</font>',
				field : 'month',
				width : 90,
				align : 'center',
				formatter:showAllMsg,
			},{
				title : '<font color="#0099FF">日</font>',
				field : 'noArrangeDates',
				width : 250,
				align : 'center',
				formatter:showAllMsg,
			},{
				title : '<font color="#0099FF">训练时段</font>',
				field : 'trainTime',
				width : 320,
				align : 'center',
				formatter:showAllMsg,
			},{
				title : '<font color="#0099FF">教练标识</font>',
				field : 'trainerId',
				width : 120,
				align : 'center'
			},
			]],
			//菜单功能设置
			toolbar : [{
				text : '取消选中',
				iconCls : 'icon-undo',
				handler : function() {
					//取消所有的选中行
					detailDatagrid.datagrid('clearSelections');
					//取消当前页面的选中行
					detailDatagrid.datagrid('unselectAll');
				}
			}, '-',{
				text : '刷新',
				iconCls : 'icon-reload',
				handler : function() {
					detailDatagrid.datagrid('load');
				}
			}],
			onLoadSuccess : onLoadSuccessArrangeDetial,
			//右键菜单所触发的事件
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				//取消当前页被选中的所有行
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow', rowIndex);
				$('#demo_reservation_menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			},
		 	onHeaderContextMenu: function(e, field){
	             e.preventDefault();
	             if (!cmenu){
	                 createColumnMenu();
	             }
	             cmenu.menu('show', {
	                 left:e.pageX,
	                 top:e.pageY
	             });
         	}
		});
	}
	
	function onLoadSuccessArrangeDetial(data){
		detailDatagrid.datagrid("fixRownumber");
		/* detailDatagrid.datagrid('hideColumn','trainerId'); */
		//datagrid没有数据时显示没有记录信息
		if(data.total==0){
			detailDatagrid.datagrid('appendRow', {
				arrangeDate: '<div style="text-align:center;color:red">没有教练排班明细记录信息！</div>' })
			.datagrid('mergeCells', { index: 0, field: 'arrangeDate', colspan:14 });
		}
	}
	
	function getMonthWeek(weekStart) {
		 weekStart = (weekStart || 0) - 0;  
		    if(isNaN(weekStart) || weekStart > 6)  
		        weekStart = 0;  
		  
		    var dayOfWeek = this.getDay();  
		    var day = this.getDate();  
		    return Math.ceil((day - dayOfWeek - 1) / 7) + ((dayOfWeek >= weekStart) ? 1 : 0);
	};
		
	
</script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<table id="demo_trainerArrange_datagrid"></table>
	</div>
	<!-- 操作按钮事件 -->
	<div id="demo_trainerArrange_menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="arrangeDetail();" data-options="iconCls:'icon-filter'">排班明细</div>
	</div>
</body>
</html>
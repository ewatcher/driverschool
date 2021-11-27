<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">
	var mydatagrid;
	var reservationDetailDiaglog=undefined;
	var reservationDetailDatagrid=undefined;
	var trainingItemJson=[{"value":1,"text":"五项"},{"value":2,"text":"路训"},{"value":null,"text":"取消"}];
	var trainerId=undefined;
	var studentDatas=undefined;
	var studentNoEditer=undefined;
	//训练内容编辑器初始化
	var myEditor={
			type:'combobox',
			options:{
				data:trainingItemJson,
				valueField:'value',
				textField:'text'
			}
		};
	
	$(function() {
		
		mydatagrid = $('#demo_trainerreservation_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/demo/trainerReservationAction!personalDatagrid.action',
			title : '教练预约信息管理列表!', /* 表头标题 */
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
			sortName : 'id', //列排序
			sortOrder : 'desc', //列排序
			checkOnSelect : false,
			selectOnCheck : true,
			striped : true,//表示条纹，隔行显示不现背景色
			nowrap:true,//单元显示
			singleSelect:true,
			//冻结列
			frozenColumns : [ [{
				title : '编号',
				field : 'id',
				width : 20,
				sortable : true,
				checkbox : true
			}, {
				title : '<font color="#0099FF">所属校区</font>',
				field : 'schoolAreaName',
				width : 120,
				align : 'left'
			},{
				title : '<font color="#0099FF">教练姓名</font>',
				field : 'trainerName',
				width : 120,
				align : 'left'
			},{
				title : '<font color="#0099FF">身份证</font>',
				field : 'trainerIdentityId',
				width : 150,
				align : 'left'
			},{
				title : '<font color="#0099FF">联系电话</font>',
				field : 'trainerPhone',
				width : 150,
				align : 'left'
			},{
				title : '<font color="#0099FF">五项课时</font>',
				field : 'fiveItem',
				sortable : true,
				width : 90,
				align : 'left',
			},
			 ] ],
			
			 columns:[[{
					title : '<font color="#0099FF">路考课时</font>',
					field : 'roadExam',
					sortable : true,
					width : 90,
					align : 'left'
				},{
					title : '<font color="#0099FF">带学员总数</font>',
					field : 'studentTotal',
					sortable : true,
					width : 120,
					align : 'left'
				}, {
					title : '<font color="#0099FF">备注</font>',
					field : 'comment',
					sortable : true,
					width : 150,
					align : 'left'
				}, {
					title : '<font color="#0099FF">学校标识</font>',
					field : 'schoolArea',
					sortable : true,
					width : 150,
					align : 'left'
				},{
					title : '<font color="#0099FF">教练标识</font>',
					field : 'trainerId',
					sortable : true,
					width : 150,
					align : 'left'
				}]],
			//菜单功能设置
			toolbar : [ { //'-':减号，将用“｜”隔开按钮
				text : '预约明细信息',
				iconCls : 'icon-remove',
				handler : function() {
					reservationDetail();
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
			onLoadSuccess : onLoadSuccess,
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

	});
	
	var cmenu;
    function createColumnMenu(){
        cmenu = $('<div/>').appendTo('body');
        cmenu.menu({
            onClick: function(item){
                if (item.iconCls == 'icon-ok'){
                    mydatagrid.datagrid('hideColumn', item.name);
                    cmenu.menu('setIcon', {
                        target: item.target,
                        iconCls: 'icon-empty'
                    });
                } else {
                	mydatagrid.datagrid('showColumn', item.name);
                    cmenu.menu('setIcon', {
                        target: item.target,
                        iconCls: 'icon-ok'
                    });
                }
            }
        });
        var fields = mydatagrid.datagrid('getColumnFields');
        for(var i=0; i<fields.length; i++){
            var field = fields[i];
            var col = mydatagrid.datagrid('getColumnOption', field);
            cmenu.menu('appendItem', {
                text: col.title,
                name: field,
                iconCls: 'icon-ok'
            });
        }
    }
	
	function onLoadSuccess(data) {
		//修改序号bug
		$(this).datagrid("fixRownumber");
		$(this).datagrid('hideColumn','schoolArea');
		$(this).datagrid('hideColumn','trainerId');
		//初始化查询相关属性选项值
		//初始化校区选项
		$("#demo_trainerreservation_organization").combotree({
			url:'${pageContext.request.contextPath}/demo/organizationAction!doNotNeedSession_tree.action',
			lines:true,
			width:155,
			onChange:function(){
				//根据combobox选择名称检索预约学员信息
				mydatagrid.datagrid('load', sy.serializeObject($("#demo_trainerreservation_searchForm")));
			}
		});
		
		
		//datagrid没有数据时显示没有记录信息
		if(data.total==0){
			mydatagrid.datagrid('appendRow', {
				schoolAreaName: '<div style="text-align:center;color:red">没有预约记录信息！</div>' })
			.datagrid('mergeCells', { index: 0, field: 'schoolAreaName', colspan:10 });
		}
	}
	
	
	//教练预约明细对话框
	function reservationDetail() {
		var rows =  mydatagrid.datagrid('getSelections');
		
		if (rows.length > 0) {
			reservationDetailDialog=parent.sy.dialog({
				title : '教练预约明细信息',
				href : '${pageContext.request.contextPath}/demo/reservationDetailAction!toMyDetailPage.action',
				width : 960,
				height : 615,
				buttons : [
						{
							text : '关闭',
							iconCls : 'icon-cancel',
							handler : function() {
								reservationDetailDialog.dialog('close');
							}
						} ],
				//数据回显显示
				onLoad : function() {
					var f = reservationDetailDialog.find('form');
					//1.显示教练基本信息
					f.form('load', {
						id:rows[0].id,
						trainerId:rows[0].trainerId,
						trainerName:rows[0].trainerName,
						trainerPhone:rows[0].trainerPhone,
						trainerIdentityId:rows[0].trainerIdentityId,
						trainerSchoolArea:rows[0].schoolAreaName						
					});
					//2.禁止用户操作教练的基本信息
					var disableItem=f.find('input[class=demo_detail_update]');
					disableItem.attr('disabled',true);
					
					//初始化月份查询选项
					var startDate=f.find('input[name=beginMonth]');
					startDate.datebox({
					});
					var endDate=f.find('input[name=endMonth]');
					endDate.datebox({
					});
					
					//保存当前教练标识
					trainerId=rows[0].trainerId;
					
					//异步从后台获取当前教练所带的学员信息列表
					
					$.ajax({
						url : '${pageContext.request.contextPath}/demo/reservationDetailAction!getStudentByTrainerId.action',
						data :{trainerId:trainerId},
						dataType : 'json',
						success : function(d) {
							studentDatas=d;
							studentNoEditer={
									type:'combobox',
									options:{
										data:d,
										valueField:'value',
										textField:'text'
									}
							};
							//3.展现教练预约明细信息
							initReservationDetailDatagrid(trainerId,studentNoEditer);
						}
					 });
					
					//查找按钮事件
					var searchEvent=f.find('a[id=reservationDetail_a_searche]');
					searchEvent.linkbutton({
					    iconCls: 'icon-search',
					    onClick:function(){
					    	reservationDetailDatagrid.datagrid('load', sy.serializeObject(f));
					    },
					});	
					//重围按钮
					var resetEvent=f.find('a[id=reservationDetail_a_cancel]');
					resetEvent.linkbutton({
					    iconCls: 'icon-cancel',
					    onClick:function(){
					    	startDate.datebox('setValue','');
					    	endDate.datebox('setValue','');
					    	reservationDetailDatagrid.datagrid('load', {});
					    },
					});	
					
				}//end onload
			});
		} else {
			parent.sy.messagerAlert('提示信息', '请选择教练员！', 'question');
		}
	}
	

	function initReservationDetailDatagrid(trainerId,studentNoEditer){
		var datagridId=reservationDetailDialog.find('table[id=demo_detailReservation_datagrid]');
		reservationDetailDatagrid=datagridId.datagrid({
			url:'${pageContext.request.contextPath}/demo/reservationDetailAction!personalDatagrid.action',
			queryParams:{trainerId:trainerId},
			title : '当前教练预约明细信息管理列表!', /* 表头标题 */
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
			sortName : 'reservationDate', //列排序
			sortOrder : 'desc', //列排序
			/* checkOnSelect : false,
			selectOnCheck : true, */
			striped : true,//表示条纹，隔行显示不现背景色
			singleSelect:true,
			frozenColumns : [ [{
				title : '编号',
				field : 'id',
				width : 260,
				sortable : true,
				checkbox : false,
			},{
				title:'预约日期',
				field:'reservationDate',
				sortable : true,
				width:130,
				formatter:function(value,row,index){
					return formatTimeYMDW(value,row,index);
				},
			},
			 ] ],
			columns:[[{
						title:'7:00-8:00',
						width:140,
						align:'center',
						colspan:2
					},{
						title:'8:00-9:00',
						width:140,
						align:'center',
						colspan:2
					},{
						title:'9:00-10:00',
						align:'center',
						colspan:2
					},{
						title:'10:00-11:00',
						align:'center',
						colspan:2
					},{
						title:'11:00-12:00',
						align:'center',
						colspan:2
					},{
						title:'12:00-13:00',
						align:'center',
						colspan:2
					},{
						title:'13:00-14:00',
						align:'center',
						colspan:2
					},{
						title:'14:00-15:00',
						align:'center',
						colspan:2
					},{
						title:'15:00-16:00',
						align:'center',
						colspan:2
					},{
						title:'16:00-17:00',
						align:'center',
						colspan:2
					},{
						title:'17:00-18:00',
						align:'center',
						colspan:2
					},{
						title:'18:00-19:00',
						align:'center',
						colspan:2
					},{
						title:'五项总数',
						field:'fiveItemTotal',
						width:70,
						align:'center',
						rowspan:2
					},{
						title:'路考总数',
						field:'raodExamTotal',
						width:70,
						align:'center',
						rowspan:2
					},{
						title:'教练预约编号',
						field:'trainerReservationId',
						width:260,
						rowspan:2
					}
					],[{
						title:'学员编号',
						field:'item7Studentid',
						width:100,
						align:'center',
						editor:studentNoEditer,
						formatter:function(value,row,index){
							return formatNoArrange(value,row,index);
						}
						
					},{
						title:'预约类型',
						field:'item7TrainContent',
						width:60,
						align:'center',
						formatter:function(value,row,index){
							return formatterTimingUsingType(value,row,index);
						}
						
					},{
						title:'学员编号',
						field:'item8Studentid',
						width:100,
						align:'center',
						editor:studentNoEditer,
						formatter:function(value,row,index){
							return formatNoArrange(value,row,index);
						}
						
					},{
						title:'预约类型',
						field:'item8TrainContent',
						width:60,
						align:'center',
						formatter:function(value,row,index){
							return formatterTimingUsingType(value,row,index);
						}
						
					},{
						title:'学员编号',
						field:'item9Studentid',
						width:100,
						align:'center',
						editor:studentNoEditer,
						formatter:function(value,row,index){
							return formatNoArrange(value,row,index);
						}
					},{
						title:'预约类型',
						field:'item9TrainContent',
						width:60,
						align:'center',
						formatter:function(value,row,index){
							return formatterTimingUsingType(value,row,index);
						}
					},{
						title:'学员编号',
						field:'item10Studentid',
						width:100,
						align:'center',
						editor:studentNoEditer,
						formatter:function(value,row,index){
							return formatNoArrange(value,row,index);
						}
					},{
						title:'预约类型',
						field:'item10TrainContent',
						width:60,
						align:'center',
						formatter:function(value,row,index){
							return formatterTimingUsingType(value,row,index);
						}
					},{
						title:'学员编号',
						field:'item11Studentid',
						width:100,
						align:'center',
						editor:studentNoEditer,
						formatter:function(value,row,index){
							return formatNoArrange(value,row,index);
						}
					},{
						title:'预约类型',
						field:'item11TrainContent',
						width:60,
						align:'center',
						formatter:function(value,row,index){
							return formatterTimingUsingType(value,row,index);
						}
					},{
						title:'学员编号',
						field:'item12Studentid',
						width:100,
						align:'center',
						editor:studentNoEditer,
						formatter:function(value,row,index){
							return formatNoArrange(value,row,index);
						}
					},{
						title:'预约类型',
						field:'item12TrainContent',
						width:60,
						align:'center',
						formatter:function(value,row,index){
							return formatterTimingUsingType(value,row,index);
						}
					},{
						title:'学员编号',
						field:'item13Studentid',
						width:100,
						align:'center',
						editor:studentNoEditer,
						formatter:function(value,row,index){
							return formatNoArrange(value,row,index);
						}
					},{
						title:'预约类型',
						field:'item13TrainContent',
						width:60,
						align:'center',
						formatter:function(value,row,index){
							return formatterTimingUsingType(value,row,index);
						}
					},{
						title:'学员编号',
						field:'item14Studentid',
						width:100,
						align:'center',
						editor:studentNoEditer,
						formatter:function(value,row,index){
							return formatNoArrange(value,row,index);
						}
					},{
						title:'预约类型',
						field:'item14TrainContent',
						width:60,
						align:'center',
						formatter:function(value,row,index){
							return formatterTimingUsingType(value,row,index);
						}
					},{
						title:'学员编号',
						field:'item15Studentid',
						width:100,
						align:'center',
						editor:studentNoEditer,
						formatter:function(value,row,index){
							return formatNoArrange(value,row,index);
						}
					},{
						title:'预约类型',
						field:'item15TrainContent',
						width:60,
						align:'center',
						formatter:function(value,row,index){
							return formatterTimingUsingType(value,row,index);
						}
					},{
						title:'学员编号',
						field:'item16Studentid',
						width:100,
						align:'center',
						editor:studentNoEditer,
						formatter:function(value,row,index){
							return formatNoArrange(value,row,index);
						}
					},{
						title:'预约类型',
						field:'item16TrainContent',
						width:60,
						align:'center',
						formatter:function(value,row,index){
							return formatterTimingUsingType(value,row,index);
						}
					},{
						title:'学员编号',
						field:'item17Studentid',
						width:100,
						align:'center',
						editor:studentNoEditer,
						formatter:function(value,row,index){
							return formatNoArrange(value,row,index);
						}
					},{
						title:'预约类型',
						field:'item17TrainContent',
						width:60,
						align:'center',
						formatter:function(value,row,index){
							return formatterTimingUsingType(value,row,index);
						}
					},{
						title:'学员编号',
						field:'item18Studentid',
						width:100,
						align:'center',
						editor:studentNoEditer,
						formatter:function(value,row,index){
							return formatNoArrange(value,row,index);
						}
					},{
						title:'预约类型',
						field:'item18TrainContent',
						width:60,
						align:'center',
						formatter:function(value,row,index){
							return formatterTimingUsingType(value,row,index);
						}
					},
					]],
			//菜单功能设置
			toolbar : [ {
				text : '取消选中',
				iconCls : 'icon-undo',
				handler : function() {
					//取消所有的选中行
					reservationDetailDatagrid.datagrid('clearSelections');
					//取消当前页面的选中行
					reservationDetailDatagrid.datagrid('unselectAll');
				}
			}, '-',{
				text : '刷新',
				iconCls : 'icon-reload',
				handler : function() {
					reservationDetailDatagrid.datagrid('load');
				}
			}],
			onLoadSuccess : onLoadSuccessReservationDetial,
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
	//datagrid加载成功后的事件
	function onLoadSuccessReservationDetial(data){
		reservationDetailDatagrid.datagrid("fixRownumber");
		/* reservationDetailDatagrid.datagrid('hideColumn','trainerId'); */
		reservationDetailDatagrid.datagrid('hideColumn','trainerReservationId');
		reservationDetailDatagrid.datagrid('hideColumn','id');
		//datagrid没有数据时显示没有记录信息
		if(data.total==0){
			reservationDetailDatagrid.datagrid('appendRow', {
				reservationDate: '<div style="text-align:center;color:red">没有教练预约明细记录信息！</div>' })
			.datagrid('mergeCells', { index: 0, field: 'reservationDate', colspan:10 });
		}
		
	}
	
	
	//查询按钮功能函数
	function mySearch() {
		mydatagrid.datagrid('load', sy.serializeObject($('#demo_trainerreservation_searchForm')));
	}

	//取消查询功能按钮
	function cleanSearch() {
		mydatagrid.datagrid('load', {});
		//清空与查询有关的输入框
		$('#demo_trainerreservation_searchForm input').val('');
	}
	
	//格式化显示不排班时间段落
	function formatNoArrange(value,row,index){
		if(value=="NO"){
			return '<span style="color:red;">'+'休假'+'</span>';
		}else{
			return value;
		}
	}
	
	
	var editIndex = undefined;//当前编辑行的行标
	function endEditing(){
		if (editIndex == undefined) {
				return true
			}
			if (reservationDetailDatagrid.datagrid('validateRow', editIndex)) {
				reservationDetailDatagrid.datagrid('endEdit', editIndex);
				editIndex = undefined;
				return true;
			} else {
				return false;
			}
	}

	var rowsIndex = undefined;//选择单元格的行索引
	function onClickCell(index, field,value) {
		rowsIndex = index;
		if (endEditing()) {
			reservationDetailDatagrid.datagrid('selectRow', index).datagrid(
					'editCell', {
						index : index,
						field : field,
						value:value,
					});
			editIndex = index;
		}
	}
	
	//修改教练预约明细信息
	function saveOrupdateDetail() {
		if (endEditing()) {
			var rows = reservationDetailDatagrid.datagrid('getSelections');
			//编辑时只能单选一行进行编辑
			if (rows.length == 1) {
				//异步更新后台数据
				$.ajax({
					url : '${pageContext.request.contextPath}/demo/reservationDetailAction!edit.action',
					data :rows[0],
					dataType : 'json',
					success : function(d) {
						if (d.success) {
							/* mydatagrid.datagrid('reload'); */
							reservationDetailDatagrid.datagrid('updateRow',{
								//获得行的索引
								index : reservationDetailDatagrid.datagrid('getRowIndex',rows[0]),
								row : d.obj
							});
						}
						parent.sy.messagerShow({
							msg : d.msg,
							title : '提示信息'
						});
					}
				});
			} else if (rows.length > 1) {
				parent.sy.messagerAlert('提示信息', '同一时间只能修改一条记录！', 'question');
			} else {
				parent.sy.messagerAlert('提示信息', '请选择要更新的记录！', 'question');
			}
		} else {
			parent.sy.messagerAlert('提示信息', '没有完成编辑不能提交！', 'question');
		}

	}
</script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询功能'" style="height: 85px;overflow: hidden;" align="left">
		<!-- 教练预约信息查询表单 -->
		<form id="demo_trainerreservation_searchForm">
			<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
			<tr>
				<th style="width: 80px;">所属校区:</th>
				<td><input id="demo_trainerreservation_organization" name="schoolArea" style="width: 155px;"/></td>
				<th style="width:80px;">教练姓名:</th>
				<td><input  name="trainerName" style="width: 155px;"/></td>
				<th style="width: 75px;">身份证:</th>
				<td><input name="trainerIdentityId" style="width: 155px;" /></td>
			</tr>
			<tr>
				<th></th>
				<td></td>
				<td colspan="2">
					<a href="javascript:void(0);" id="student_a_searche" class="easyui-linkbutton" iconCls="icon-search" onclick="mySearch();">查询</a>
					<a href="javascript:void(0);" id="student_a_canel" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cleanSearch();">取消</a>
				</td>
			</tr>
			</table>
		</form>
	</div>
	<!-- datagrid数据在center中展现 -->
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<table id="demo_trainerreservation_datagrid"></table>
	</div>
	<!-- 操作按钮事件 -->
	<div id="demo_reservation_menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="reservationDetail();" data-options="iconCls:'icon-search'">预约明细信息</div>
	</div>
</body>
</html>
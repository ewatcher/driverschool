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
	var arrangeDetailDatagrid=undefined;
	var addDialog=undefined;
	
	var userSchoolArea=undefined;
	$(function() {
		userSchoolArea=getUserSchoolArea();
		mydatagrid = $('#demo_trainerArrange_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/demo/trainerArrangeAction!datagrid.action',/* 从后台读取所有user数据 */
			title : '教练排班信息管理列表!', /* 表头标题 */
			iconCls : 'icon-save', /* 标题前边的图标 */
			pagination : true, /* 是否显示页码 */
			pagePosition : 'bottom', /* 页码显示的位置 */
			pageSize : 20, /* 每面显示的记录数 */
			pageList : [ 10, 20, 30, 40,50 ,100,200 ],/* 选择每面显示的记录数 */
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
			sortName : 'codeNo', //列排序
			sortOrder : 'desc', //列排序
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
				width : 100,
				align : 'center'
			},{
				title : '<font color="#0099FF">教练编号</font>',
				field : 'trainerCodeNo',
				width : 70,
				sortable:true,
				align : 'center',
				formatter:formatTrainerNo,
			},{
				title : '<font color="#0099FF">教练姓名</font>',
				field : 'trainerName',
				width : 80,
				align : 'center'
			},{
				title : '<font color="#0099FF">教练属性</font>',
				field : 'trainerType',
				sortable : true,
				width : 80,
				formatter:initTrainerType,
			},{
				title : '<font color="#0099FF">联系电话</font>',
				field : 'trainerPhone',
				width : 100,
				sortable:true,
				align : 'center'
			}] ],
			//普通列
			columns : [ [{
				title : '<font color="#0099FF">施教类型</font>',
				field : 'trainerDriverType',
				width : 75,
				align : 'center',
				formatter:formatterDriverType,
			},{
				title : '<font color="#0099FF">教练ID</font>',
				field : 'trainerId',
				width : 120,
				align : 'left'
			},{
				title : '<font color="#0099FF">能否排班</font>',
				field : 'trainerArrangeFlag',
				width : 80,
				align : 'center',
				formatter:initTrainerArrangeFlag,
			},{
				title : '<font color="#0099FF">排班类型</font>',
				field : 'arrangeType',
				width : 80,
				align : 'center',
				formatter:formatterDriverType,
			},{
				title : '<font color="#0099FF">最新排班日期</font>',
				field : 'arrangeDate',
				width : 100,
				align : 'center',
				formatter:formatTimeYMD,
			},{
				title : '<font color="#0099FF">身份证</font>',
				field : 'trainerIdentity',
				width : 150,
				sortable:true,
				align : 'center'
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
						schoolAreaName: '<div style="text-align:center;color:red">没有记录信息！</div>' })
					.datagrid('mergeCells', { index: 0, field: 'month', colspan:14 });
				}
				//初始化校区选择
				initSchoolArea($("#demo_schoolArea_search"),userSchoolArea);
				//初始化驾照类型选项
				initDriverType($('#demo_driverType_search'));
				//添加回车事件
				bindingKeypressEvent($(".keypress_event"),mydatagrid,$("#demo_trainerArrange_searchForm"));
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
		
	});
	
	//排班明细对话框
	function arrangeDetail() {
		var rows = mydatagrid.datagrid('getSelections');
		//编辑时只能单选一行进行编辑
		if (rows.length == 1) {
			editList(rows[0].trainerId,rows[0].trainerName,rows[0].schoolArea,rows[0].trainerIdentity,rows[0].trainerPhone,rows[0].arrangeType);
		} else if (rows.length > 1) {
			parent.sy.messagerAlert('提示信息', '同一时间只能更新一条记录！', 'error');
		} else {
			parent.sy.messagerAlert('提示信息', '请选择要更新的记录！', 'error');
		}
	}	
	
	
	function editList(trainerId,trainerName,schoolArea,trainerIdentityId,trainerPhone,arrangeType) {
			var p = parent.sy.dialog({
						title : '编辑教练排班明细信息',
						href : '${pageContext.request.contextPath}/demo/trainerArrangeAction!toTrainerArrangeDetailListPage.action',
						width : 1024,
						height : 785,
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
							var f = p.find('form');
							//教练基本信息参数初始化
							var schoolDepartment=f.find('input[name=schoolArea]');
							initSchoolArea(schoolDepartment,userSchoolArea);
							var trainerIdTemp=f.find('input[name=trainerId]');
							var trainerNameTemp=f.find('input[name=trainerName]');
							var trainerIdentityTemp=f.find('input[name=trainerIdentity]');
							var trainerPhoneTemp=f.find('input[name=trainerPhone]');
							trainerIdTemp.val(trainerId);
							trainerNameTemp.val(trainerName);
							trainerIdentityTemp.val(trainerIdentityId);
							trainerPhoneTemp.val(trainerPhone);
							
							initUpdateArrangeDetailDatagrid(p,trainerId,trainerName,schoolArea,arrangeType);
							
						},//end onload	
					});//end dialog
		
	}
	
	//更新明细信息datagrid
	function initUpdateArrangeDetailDatagrid(detailDialog,trainerId,trainerName,schoolArea,arrangeType){
		var updateDatagridId=detailDialog.find('table[id=demo_trainerArrangedetail_datagrid]');
			 arrangeDetailDatagrid=updateDatagridId.datagrid({
			url:'${pageContext.request.contextPath}/demo/trainerArrangeAction!getDetailArrangeByTrainerId.action',
			queryParams:{trainerId:trainerId,schoolArea:schoolArea},
			title : '当前教练排班明细信息管理列表!', /* 表头标题 */
			iconCls : 'icon-save', /* 标题前边的图标 */
			pagination : true, /* 是否显示页码 */
			pagePosition : 'bottom', /* 页码显示的位置 */
			pageSize : 20, /* 每面显示的记录数 */
			pageList : [ 10, 20, 30, 40,50 ,100,200 ], /* 选择每面显示的记录数 */
			fit : true,
			fitColumns : false, //列自动调整功能
			nowrap : true, //以行的形式进行显示
			border : false,
			rownumbers: true,
			idField : 'trainerReservationId', //此属性一定要配置
			sortName : 'reservationDate', //列排序
			sortOrder : 'desc', //列排序
			/* checkOnSelect : false,
			selectOnCheck : true, */
			striped : true,//表示条纹，隔行显示不现背景色
			singleSelect:false,
			frozenColumns : [ [{
				title : '编号',
				field : 'id',
				width : 260,
				sortable : true,
				checkbox : true,
			},{
				title : '<font color="#0099FF">排班日期</font>',
				field : 'reservationDate',
				width : 100,
				sortable:true,
				align : 'center',
				formatter:formatTimeYMD,
			},
			 ] ],
			columns:[[{
				title : '<font color="#0099FF">8:00-9:00</font>',
				field : 'item8TrainContent',
				width : 80,
				sortable:true,
				align : 'center',
				formatter:formatterTimingUsingType,
			},{
				title : '<font color="#0099FF">9:00-10:00</font>',
				field : 'item9TrainContent',
				width : 80,
				sortable:true,
				align : 'center',
				formatter:formatterTimingUsingType,
			},{
				title : '<font color="#0099FF">10:00-11:00</font>',
				field : 'item10TrainContent',
				width : 80,
				align : 'center',
				formatter:formatterTimingUsingType,
			},{
				title : '<font color="#0099FF">11:00-12:00</font>',
				field : 'item11TrainContent',
				width : 80,
				align : 'center',
				formatter:formatterTimingUsingType,
			},{
				title : '<font color="#0099FF">12:00-13:00</font>',
				field : 'item12TrainContent',
				width : 80,
				align : 'center',
				formatter:formatterTimingUsingType,
			},{
				title : '<font color="#0099FF">13:00-14:00</font>',
				field : 'item13TrainContent',
				width : 80,
				align : 'center',
				formatter:formatterTimingUsingType,
			},{
				title : '<font color="#0099FF">14:00-15:00</font>',
				field : 'item14TrainContent',
				width : 80,
				align : 'center',
				formatter:formatterTimingUsingType,
			},{
				title : '<font color="#0099FF">15:00-16:00</font>',
				field : 'item15TrainContent',
				width : 80,
				align : 'center',
				formatter:formatterTimingUsingType,
			},{
				title : '<font color="#0099FF">16:00-17:00</font>',
				field : 'item16TrainContent',
				width : 80,
				align : 'center',
				formatter:formatterTimingUsingType,
			},{
				title : '<font color="#0099FF">17:00-18:00</font>',
				field : 'item17TrainContent',
				width : 80,
				align : 'center',
				formatter:formatterTimingUsingType,
			},{
				title : '<font color="#0099FF">18:00-19:00</font>',
				field : 'item18TrainContent',
				width : 80,
				align : 'center',
				formatter:formatterTimingUsingType,
			},{
				title : '<font color="#0099FF">19:00-20:00</font>',
				field : 'item19TrainContent',
				width : 80,
				align : 'center',
				formatter:formatterTimingUsingType,
			},{
				title : '<font color="#0099FF">20:00-21:00</font>',
				field : 'item20TrainContent',
				width : 80,
				align : 'center',
				formatter:formatterTimingUsingType,
			},{
				title : '<font color="#0099FF">21:00-22:00</font>',
				field : 'item21TrainContent',
				width : 80,
				align : 'center',
				formatter:formatterTimingUsingType,
			},{
				title : '<font color="#0099FF">field8</font>',
				field : 'reservationFieldId8',
				width : 120,
				align : 'center',
			},{
				title : '<font color="#0099FF">field9</font>',
				field : 'reservationFieldId9',
				width : 120,
				align : 'center',
			},{
				title : '<font color="#0099FF">field10</font>',
				field : 'reservationFieldId10',
				width : 120,
				align : 'center',
			},{
				title : '<font color="#0099FF">field11</font>',
				field : 'reservationFieldId11',
				width : 120,
				align : 'center',
			},{
				title : '<font color="#0099FF">field12</font>',
				field : 'reservationFieldId12',
				width : 120,
				align : 'center',
			},{
				title : '<font color="#0099FF">field13</font>',
				field : 'reservationFieldId13',
				width : 120,
				align : 'center',
			},{
				title : '<font color="#0099FF">field14</font>',
				field : 'reservationFieldId14',
				width : 120,
				align : 'center',
			},{
				title : '<font color="#0099FF">field15</font>',
				field : 'reservationFieldId15',
				width : 120,
				align : 'center',
			},{
				title : '<font color="#0099FF">field16</font>',
				field : 'reservationFieldId16',
				width : 120,
				align : 'center',
			},{
				title : '<font color="#0099FF">field17</font>',
				field : 'reservationFieldId17',
				width : 60,
				align : 'center',
			},{
				title : '<font color="#0099FF">field18</font>',
				field : 'reservationFieldId18',
				width : 120,
				align : 'center',
			},{
				title : '<font color="#0099FF">field19</font>',
				field : 'reservationFieldId19',
				width : 120,
				align : 'center',
			},{
				title : '<font color="#0099FF">field20</font>',
				field : 'reservationFieldId20',
				width : 120,
				align : 'center',
			},{
				title : '<font color="#0099FF">field21</font>',
				field : 'reservationFieldId21',
				width : 120,
				align : 'center',
			},{
				title : '<font color="#0099FF">trainerId</font>',
				field : 'trainerId',
				width : 120,
				align : 'center',
			},{
				title : '<font color="#0099FF">trainerReservationDetailId</font>',
				field : 'trainerReservationDetailId',
				width : 120,
				align : 'center',
			},{
				title : '<font color="#0099FF">arrangeId</font>',
				field : 'arrangeId',
				width : 120,
				align : 'center',
			}
			]],
			//菜单功能设置
			toolbar : [{
				text : '添加',
				iconCls : 'icon-add',
				handler :function (){
					append(arrangeDetailDatagrid,trainerId,trainerName,schoolArea,arrangeType);
				},
			}, '-', {
				text : '更新',
				iconCls : 'icon-edit',
				handler :function (){
					edit(arrangeDetailDatagrid,trainerId,trainerName,schoolArea);
				},
			}, '-', {
				text : '删除',
				iconCls : 'icon-remove',
				handler :function (){
					remove(arrangeDetailDatagrid);
				}
			}, '-',{
				text : '取消选中',
				iconCls : 'icon-undo',
				handler : function() {
					//取消所有的选中行
					arrangeDetailDatagrid.datagrid('clearSelections');
					//取消当前页面的选中行
					arrangeDetailDatagrid.datagrid('unselectAll');
				}
			}, '-',{
				text : '刷新',
				iconCls : 'icon-reload',
				handler : function() {
					arrangeDetailDatagrid.datagrid('load');
					arrangeDetailDatagrid.datagrid('unselectAll');
				}
			}],
			onLoadSuccess : arrangeDetailDatagridLoadSuccess,
			//右键菜单所触发的事件//TODO
		});
	}
	
	function arrangeDetailDatagridLoadSuccess(data){
		arrangeDetailDatagrid.datagrid("fixRownumber");
		arrangeDetailDatagrid.datagrid('hideColumn','trainerId');
		arrangeDetailDatagrid.datagrid('hideColumn','reservationFieldId8');
		arrangeDetailDatagrid.datagrid('hideColumn','reservationFieldId9');
		arrangeDetailDatagrid.datagrid('hideColumn','reservationFieldId10');
		arrangeDetailDatagrid.datagrid('hideColumn','reservationFieldId11');
		arrangeDetailDatagrid.datagrid('hideColumn','reservationFieldId12');
		arrangeDetailDatagrid.datagrid('hideColumn','reservationFieldId13');
		arrangeDetailDatagrid.datagrid('hideColumn','reservationFieldId14');
		arrangeDetailDatagrid.datagrid('hideColumn','reservationFieldId15');
		arrangeDetailDatagrid.datagrid('hideColumn','reservationFieldId16');
		arrangeDetailDatagrid.datagrid('hideColumn','reservationFieldId17');
		arrangeDetailDatagrid.datagrid('hideColumn','reservationFieldId18');
		arrangeDetailDatagrid.datagrid('hideColumn','reservationFieldId19');
		arrangeDetailDatagrid.datagrid('hideColumn','reservationFieldId20');
		arrangeDetailDatagrid.datagrid('hideColumn','reservationFieldId21');
		arrangeDetailDatagrid.datagrid('hideColumn','trainerReservationDetailId');
		arrangeDetailDatagrid.datagrid('hideColumn','arrangeId');
		//datagrid没有数据时显示没有记录信息
		if(data.total==0){
			arrangeDetailDatagrid.datagrid('appendRow', {
				item8TrainContent: '<div style="text-align:center;color:red">没有记录信息 </div>' })
				.datagrid('mergeCells', { index: 0, field: 'item8TrainContent', colspan:8 });
		}
	}
	
	//单击编辑按钮，实现对教练排班的编辑功能
	function edit(arrangeDetailDatagrid,trainerId,trainerName,schoolArea) {
		var itemTime8flagTemp=undefined;
		var itemTime9flagTemp=undefined;
		var itemTime10flagTemp=undefined;
		var itemTime11flagTemp=undefined;
		var itemTime12flagTemp=undefined;
		var itemTime13flagTemp=undefined;
		var itemTime14flagTemp=undefined;
		var itemTime15flagTemp=undefined;
		var itemTime16flagTemp=undefined;
		var itemTime17flagTemp=undefined;
		var itemTime18flagTemp=undefined;
		var itemTime19flagTemp=undefined;
		var itemTime20flagTemp=undefined;
		var itemTime21flagTemp=undefined;
		var rows = arrangeDetailDatagrid.datagrid('getSelections');
		//编辑时只能单选一行进行编辑
		if (rows.length == 1) {
			
			var p = parent.sy.dialog({
						title : '编辑教练排班信息',
						href : '${pageContext.request.contextPath}/demo/trainerArrangeAction!toTrainerArrangeEditPage.action',
						width : 600,
						height : 580,
						buttons : [
								{
									text : '更新',
									iconCls : 'icon-tip',
									handler : function() {
										var f = p.find('form');
										//异步更新教练排班明细信息
										$.ajax({
											url : '${pageContext.request.contextPath}/demo/trainerArrangeAction!arrangeDetailChange.action',
											data : {trainerReservationDetailId:rows[0].trainerReservationDetailId,
													reservationDate:rows[0].reservationDate,
													item8TrainContent:itemTime8flagTemp.combobox('getValue'),
													item9TrainContent:itemTime9flagTemp.combobox('getValue'),
													item10TrainContent:itemTime10flagTemp.combobox('getValue'),
													item11TrainContent:itemTime11flagTemp.combobox('getValue'),
													item12TrainContent:itemTime12flagTemp.combobox('getValue'),
													item13TrainContent:itemTime13flagTemp.combobox('getValue'),
													item14TrainContent:itemTime14flagTemp.combobox('getValue'),
													item15TrainContent:itemTime15flagTemp.combobox('getValue'),
													item16TrainContent:itemTime16flagTemp.combobox('getValue'),
													item17TrainContent:itemTime17flagTemp.combobox('getValue'),
													item18TrainContent:itemTime18flagTemp.combobox('getValue'),
													item19TrainContent:itemTime19flagTemp.combobox('getValue'),
													item20TrainContent:itemTime20flagTemp.combobox('getValue'),
													item21TrainContent:itemTime21flagTemp.combobox('getValue'),},
											dataType : 'json',
											success : function(d) {
												var json=$.parseJSON(d);
													p.dialog('close');
													arrangeDetailDatagrid.datagrid('reload');
													arrangeDetailDatagrid.datagrid('unselectAll');
													parent.sy.messagerShow({
														title : '提示信息',
														msg : d.msg
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
							
							itemTime8flagTemp=f.find('input[name=item8TrainContent]');
							itemTime9flagTemp=f.find('input[name=item9TrainContent]');
							itemTime10flagTemp=f.find('input[name=item10TrainContent]');
							itemTime11flagTemp=f.find('input[name=item11TrainContent]');
							itemTime12flagTemp=f.find('input[name=item12TrainContent]');
							itemTime13flagTemp=f.find('input[name=item13TrainContent]');
							itemTime14flagTemp=f.find('input[name=item14TrainContent]');
							itemTime15flagTemp=f.find('input[name=item15TrainContent]');
							itemTime16flagTemp=f.find('input[name=item16TrainContent]');
							itemTime17flagTemp=f.find('input[name=item17TrainContent]');
							itemTime18flagTemp=f.find('input[name=item18TrainContent]');
							itemTime19flagTemp=f.find('input[name=item19TrainContent]'); 
							itemTime20flagTemp=f.find('input[name=item20TrainContent]'); 
							itemTime21flagTemp=f.find('input[name=item21TrainContent]'); 
							
							//综合参数初始化
							var trainerNameTemp=f.find('input[name=trainerName]');
							trainerNameTemp.val(trainerName);
							var trainerIdTemp=f.find('input[name=trainerId]');	
							trainerIdTemp.val(trainerId);
						//初始化所属校区选项
						var schoolDepartment=f.find('input[name=schoolArea]');
						initSchoolArea(schoolDepartment,userSchoolArea);
						var reservationDateTemp1=f.find('input[name=reservationDateTemp]');
						reservationDateTemp1.val(rows[0].reservationDate);
						var reservationDate=f.find('input[name=reservationDate]');
						reservationDate.val(rows[0].reservationDate);
						//禁用非修改属性
						var disabledTemp=f.find('input[class=demo_trainerArrangeAdd_disabled]');
						disabledTemp.attr('readonly',true);
						
						//初始化施教类型，
						var teachingTypeTemp=f.find('input[name=teachingType]');
						initDriverType(teachingTypeTemp,6);
						//排班类型初始化
						
						//初始化教练排班标记（实现教练排班按时段变更功能）
						var workingTypeUrl='${pageContext.request.contextPath}/demo/json/arrangeTypeRest_combobox.json';
						initComboboxData(itemTime8flagTemp,rows[0].item8TrainContent,workingTypeUrl,true);
						initComboboxData(itemTime9flagTemp,rows[0].item9TrainContent,workingTypeUrl,true);
						initComboboxData(itemTime10flagTemp,rows[0].item10TrainContent,workingTypeUrl,true);
						initComboboxData(itemTime11flagTemp,rows[0].item11TrainContent,workingTypeUrl,true);
						initComboboxData(itemTime12flagTemp,rows[0].item12TrainContent,workingTypeUrl,true);
						initComboboxData(itemTime13flagTemp,rows[0].item13TrainContent,workingTypeUrl,true);
						initComboboxData(itemTime14flagTemp,rows[0].item14TrainContent,workingTypeUrl,true);
						initComboboxData(itemTime15flagTemp,rows[0].item15TrainContent,workingTypeUrl,true);
						initComboboxData(itemTime16flagTemp,rows[0].item16TrainContent,workingTypeUrl,true);
						initComboboxData(itemTime17flagTemp,rows[0].item17TrainContent,workingTypeUrl,true);
						initComboboxData(itemTime18flagTemp,rows[0].item18TrainContent,workingTypeUrl,true);
						initComboboxData(itemTime19flagTemp,rows[0].item19TrainContent,workingTypeUrl,true);
						initComboboxData(itemTime20flagTemp,rows[0].item20TrainContent,workingTypeUrl,true);
						initComboboxData(itemTime21flagTemp,rows[0].item21TrainContent,workingTypeUrl,true);
						
						//如果该时段存在预约不允许修改排班
						disabledUpdateItemtime(itemTime8flagTemp,itemTime8Temp,rows[0].reservationFieldId8);
						disabledUpdateItemtime(itemTime9flagTemp,itemTime9Temp,rows[0].reservationFieldId9);
						disabledUpdateItemtime(itemTime10flagTemp,itemTime10Temp,rows[0].reservationFieldId10);
						disabledUpdateItemtime(itemTime11flagTemp,itemTime11Temp,rows[0].reservationFieldId11);
						disabledUpdateItemtime(itemTime12flagTemp,itemTime12Temp,rows[0].reservationFieldId12);
						disabledUpdateItemtime(itemTime13flagTemp,itemTime13Temp,rows[0].reservationFieldId13);
						disabledUpdateItemtime(itemTime14flagTemp,itemTime14Temp,rows[0].reservationFieldId14);
						disabledUpdateItemtime(itemTime15flagTemp,itemTime15Temp,rows[0].reservationFieldId15);
						disabledUpdateItemtime(itemTime16flagTemp,itemTime16Temp,rows[0].reservationFieldId16);
						disabledUpdateItemtime(itemTime17flagTemp,itemTime17Temp,rows[0].reservationFieldId17);
						disabledUpdateItemtime(itemTime18flagTemp,itemTime18Temp,rows[0].reservationFieldId18);
						disabledUpdateItemtime(itemTime19flagTemp,itemTime19Temp,rows[0].reservationFieldId19);
						disabledUpdateItemtime(itemTime20flagTemp,itemTime20Temp,rows[0].reservationFieldId20);
						disabledUpdateItemtime(itemTime21flagTemp,itemTime21Temp,rows[0].reservationFieldId21);
						
						var timeItemCheckTemp=f.find('input[class=timeItem_checked]');
						var selectAllTemp=f.find('input[name=selectAll]');
						var cancelAllTemp=f.find('input[name=cancelAll]');
						//全选按钮事件
						selectAllTemp.change(function(){
							timeItemCheckTemp.attr('checked',true);
							cancelAllTemp.attr('checked',false);
						});
						//取消按钮事件
						cancelAllTemp.change(function(){
							timeItemCheckTemp.attr('checked',false);
							selectAllTemp.attr('checked',false);
						});
						
						
						//初始化时间段选择
						if(rows[0].item8TrainContent!=5){
							itemTime8Temp.attr('checked',true);
						}
						if(rows[0].item9TrainContent!=5){
							itemTime9Temp.attr('checked',true);
						}
						if(rows[0].item10TrainContent!=5){
							itemTime10Temp.attr('checked',true);
						}
						if(rows[0].item11TrainContent!=5){
							itemTime11Temp.attr('checked',true);
						}
						if(rows[0].item12TrainContent!=5){
							itemTime12Temp.attr('checked',true);
						}
						if(rows[0].item13TrainContent!=5){
							itemTime13Temp.attr('checked',true);
						}
						if(rows[0].item14TrainContent!=5){
							itemTime14Temp.attr('checked',true);
						}
						if(rows[0].item15TrainContent!=5){
							itemTime15Temp.attr('checked',true);
						}
						if(rows[0].item16TrainContent!=5){
							itemTime16Temp.attr('checked',true);
						}
						if(rows[0].item17TrainContent!=5){
							itemTime17Temp.attr('checked',true);
						}
						if(rows[0].item18TrainContent!=5){
							itemTime18Temp.attr('checked',true);
						}
						if(rows[0].item19TrainContent!=5){
							itemTime19Temp.attr('checked',true);
						}
						if(rows[0].item20TrainContent!=5){
							itemTime20Temp.attr('checked',true);
						}
						if(rows[0].item21TrainContent!=5){
							itemTime21Temp.attr('checked',true);
						}
						//如果不选中该时间，则排班标志显示为休息
						initItemtimeFlag(itemTime8Temp,itemTime8flagTemp,rows[0].itemTime8flag);
						initItemtimeFlag(itemTime9Temp,itemTime9flagTemp,rows[0].itemTime9flag);
						initItemtimeFlag(itemTime10Temp,itemTime10flagTemp,rows[0].itemTime10flag);
						initItemtimeFlag(itemTime11Temp,itemTime11flagTemp,rows[0].itemTime11flag);
						initItemtimeFlag(itemTime12Temp,itemTime12flagTemp,rows[0].itemTime12flag);
						initItemtimeFlag(itemTime13Temp,itemTime13flagTemp,rows[0].itemTime13flag);
						initItemtimeFlag(itemTime14Temp,itemTime14flagTemp,rows[0].itemTime14flag);
						initItemtimeFlag(itemTime15Temp,itemTime15flagTemp,rows[0].itemTime15flag);
						initItemtimeFlag(itemTime16Temp,itemTime16flagTemp,rows[0].itemTime16flag);
						initItemtimeFlag(itemTime17Temp,itemTime17flagTemp,rows[0].itemTime17flag);
						initItemtimeFlag(itemTime18Temp,itemTime18flagTemp,rows[0].itemTime18flag);
						initItemtimeFlag(itemTime19Temp,itemTime19flagTemp,rows[0].itemTime19flag);
						initItemtimeFlag(itemTime20Temp,itemTime20flagTemp,rows[0].itemTime20flag);
						initItemtimeFlag(itemTime21Temp,itemTime21flagTemp,rows[0].itemTime21flag);
						}//end onload
					});//end dialog
		} else if (rows.length > 1) {
			parent.sy.messagerAlert('提示信息', '同一时间只能更新一条记录！', 'error');
		} else {
			parent.sy.messagerAlert('提示信息', '请选择要更新的记录！', 'error');
		}
		
	}
	
	//襟止修改已经存在预约的时段内容
	function disabledUpdateItemtime(objCombobox,objCheck,filedId){
		if(filedId!=undefined){
			objCombobox.combobox({
				readonly:true,
			});
			objCheck.attr('disabled',true);
		}
	}
	
	function initItemtimeFlag(itemTimeObject,itemTimeFlag,primaryVal){
		itemTimeObject.click(function(){
			if(!itemTimeObject.is(':checked')){
				itemTimeFlag.combobox('setValue',5);
			}else{
				itemTimeFlag.combobox('setValue',primaryVal);
			}
		});
	}

	//添加教练排班信息
	function append(arrangeDetailDatagrid,trainerId,trainerName,schoolArea,arrangeType) {
		//1.校验教练车辆是否为启用，	
		$.ajax({
				url : '${pageContext.request.contextPath}/demo/trainerArrangeAction!trainerArrangeOrNot.action',
				data : {trainerId:trainerId},
				dataType : 'json',
				success : function(d) {
					if(!d){
						parent.sy.messagerShow({
							msg : '该教练车处于维修中……或没有设置状态为‘启用’',
							title : '提示信息'
						});
						return;
					}else{
						var arrangeDatesUrl=undefined;
						 addDialog = parent.sy.dialog({
								title : '教练排班信息录入',
								href : '${pageContext.request.contextPath}/demo/trainerArrangeAction!toTrainerArrangeAddPage.action',
								width : 660,
								height : 690,
								
								buttons : [
										{
											text : '添加',
											iconCls : 'icon-add',
											handler : function() {
													var btnAdd = this;
													$(btnAdd).hide();
													var addForm = addDialog.find('form[id=demo_trainerArrange_addForm]');
													//提交表单
													addForm.form('submit',{
														//提交添加教练排班表单时，调用UserAction的add方法
														url : '${pageContext.request.contextPath}/demo/trainerArrangeAction!add.action',
														onSubmit : function() {
															var isValid = addForm.form('validate');
															if (!isValid) {
																$(btnAdd).show();
															}
														},
														success : function(d) {
															var json = $.parseJSON(d);
															if (json.success) {
																arrangeDetailDatagrid.datagrid('reload'); //这种方式性能差，消耗系统资源
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
												trainerID=f.find('input[name=trainerName]');
												var trainerIdTemp=f.find('input[name=trainerId]');	
													trainerIdTemp.val(trainerId);
												var myYear=f.find('input[name=year]');
												var myMonth=f.find('input[name=month]');
												//初始化所属校区选项
												var schoolDepartment=f.find('input[name=schoolArea]');
												initSchoolArea(schoolDepartment,userSchoolArea);
												
												//初始化教练排班选择预约时间段
												initItemtime(f);
												var itemTimeflagTemp=f.find('input[class=arrange_add_timeflag]');
												var chekedAll=f.find('input[class=timeItem_checked]');
												
												//初始化施教类型，
												var teachingTypeTemp=f.find('input[name=teachingType]');
												var teachingTypeUrl='${pageContext.request.contextPath}/demo/json/driverType_combobox.json';
												initComboboxData(teachingTypeTemp,6,teachingTypeUrl,true);
												
												//排班类型初始化
												var workingTypeTemp=f.find('input[name=workingType]');
												var arrangeTypeUrl=undefined;
												var initData=undefined;
												if(1==arrangeType){
													initData=1;
													arrangeTypeUrl='${pageContext.request.contextPath}/demo/json/reservationtype_combobox.json';
												}else if(2==arrangeType){
													initData=2;
													arrangeTypeUrl='${pageContext.request.contextPath}/demo/json/reservationtype_combobox.json';
												}else{
													initData=null;
													arrangeTypeUrl='${pageContext.request.contextPath}/demo/json/reservationTypeNomal_combobox.json';
												}
												initComboboxData(workingTypeTemp,initData,arrangeTypeUrl,true);
												if(initData==1||initData==2){//预约为电车，模拟则襟用预约内容选项
													workingTypeTemp.combobox({readonly:true});
												}
												
												//初始化教练排班标记（实现教练排班按时段变更功能）
												var workingTypeUrl='${pageContext.request.contextPath}/demo/json/arrangeTypeRest_combobox.json';
												initComboboxData(itemTimeflagTemp,initData,workingTypeUrl,true);
												
												workingTypeTemp.combobox({
													onChange:function(){
														chekedAll.attr('checked',true);
														itemTimeflagTemp.combobox('setValue',workingTypeTemp.combobox('getValue'));
													}
												}); 
												
												
												//全选按钮事件
												var timeItemCheckTemp=f.find('input[class=timeItem_checked]');
												var selectAllTemp=f.find('input[name=selectAll]');
												var cancelAllTemp=f.find('input[name=cancelAll]');
												//默认全选
												timeItemCheckTemp.attr('checked',true);
												selectAllTemp.change(function(){
													timeItemCheckTemp.attr('checked',true);
													cancelAllTemp.attr('checked',false);
												});
												//取消按钮事件
												cancelAllTemp.change(function(){
													timeItemCheckTemp.attr('checked',false);
													selectAllTemp.attr('checked',false);
												});
												
												
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
											
											//初始化教练员选择
											var myTrainer=trainerID.val(trainerName);
											trainerID.attr('readonly',true);
											//选择教练事件
											var trainerEvent=f.find('a[id=trainerArrange_a_selectTrainer]');
											trainerEvent.linkbutton({
						    				    iconCls: 'icon-search',
						    				    onClick:function(){
						    				    	schoolAreaId=userSchoolArea;
						    				    	if(schoolAreaId!=""){
						    				    		initTrainerSelect(schoolAreaId);
						    				    	}else{
						    				    		parent.sy.messagerAlert('提示信息', '请选择教练所属校区！', 'question');
						    				    	}
						    				    },
						    				});
											
											
										},
										onLoadSuccess:function(){
											
										},
							});
					}
				}
			});
		//2.
			
	}
	//初始化教练排班时间段
	function initItemtime(f){
		var itemTime8flagTemp=f.find('input[name=ItemTime8flag]');
		var itemTime9flagTemp=f.find('input[name=ItemTime9flag]');
		var itemTime10flagTemp=f.find('input[name=ItemTime10flag]');
		var itemTime11flagTemp=f.find('input[name=ItemTime11flag]');
		var itemTime12flagTemp=f.find('input[name=ItemTime12flag]');
		var itemTime13flagTemp=f.find('input[name=ItemTime13flag]');
		var itemTime14flagTemp=f.find('input[name=ItemTime14flag]');
		var itemTime15flagTemp=f.find('input[name=ItemTime15flag]');
		var itemTime16flagTemp=f.find('input[name=ItemTime16flag]');
		var itemTime17flagTemp=f.find('input[name=ItemTime17flag]');
		var itemTime18flagTemp=f.find('input[name=ItemTime18flag]'); 
		var itemTime19flagTemp=f.find('input[name=ItemTime19flag]'); 
		var itemTime20flagTemp=f.find('input[name=ItemTime20flag]'); 
		var itemTime21flagTemp=f.find('input[name=ItemTime21flag]');
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
		initChecked(itemTime8Temp,itemTime8flagTemp);
		initChecked(itemTime9Temp,itemTime9flagTemp);
		initChecked(itemTime10Temp,itemTime10flagTemp);
		initChecked(itemTime11Temp,itemTime11flagTemp);
		initChecked(itemTime12Temp,itemTime12flagTemp);
		initChecked(itemTime13Temp,itemTime13flagTemp);
		initChecked(itemTime14Temp,itemTime14flagTemp);
		initChecked(itemTime15Temp,itemTime15flagTemp);
		initChecked(itemTime16Temp,itemTime16flagTemp);
		initChecked(itemTime17Temp,itemTime17flagTemp);
		initChecked(itemTime18Temp,itemTime18flagTemp);
		initChecked(itemTime19Temp,itemTime19flagTemp);
		initChecked(itemTime20Temp,itemTime20flagTemp);
		initChecked(itemTime21Temp,itemTime21flagTemp);
	}
	
	function initChecked(obj,targer){
		obj.change(function(){
			if(obj.is(':checked')){
				targer.combobox('setValue',3);
			}else{
				targer.combobox('setValue',5);
			}
		});
	}
	
	////初始化施教类型参数
	function initWorkAndTeachingType(teachingTypeTemp){
		
		teachingTypeTemp.combobox({
			url : '${pageContext.request.contextPath}/demo/json/driverType_combobox.json',
			valueField : 'value',
			textField : 'text',
			method:'get',
			editable:false,
			required:true,
		}); 
		//设置默认选中项
		teachingTypeTemp.combobox('setValue','6');
		
		
	}
	
	//初始化教练排班类型
	function initWorkingType(workingTypeTemp){
		workingTypeTemp.combobox({
			url : '${pageContext.request.contextPath}/demo/json/timingUsingType_combobox.json',
			valueField : 'value',
			textField : 'text',
			method:'get',
			editable:false,
			required:true,
		}); 
	}
	
	function getMonthWeek(weekStart) {
		 weekStart = (weekStart || 0) - 0;  
		    if(isNaN(weekStart) || weekStart > 6)  
		        weekStart = 0;  
		  
		    var dayOfWeek = this.getDay();  
		    var day = this.getDate();  
		    return Math.ceil((day - dayOfWeek - 1) / 7) + ((dayOfWeek >= weekStart) ? 1 : 0);
	};
		
	
	
	//删除教练排班
	function remove(arrangeDetailDatagrid) {
		var rows = arrangeDetailDatagrid.datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			parent.sy.messagerConfirm(
							'请确认',
							'您要删除当前所选择项信息？选择确定，如该排班信息已经存在预约将会被删除！',
							function(r) {
								if (r) {
									for (var i = 0; i < rows.length; i++) {
										ids.push(rows[i].trainerReservationDetailId);
									}
									$.ajax({
										url : '${pageContext.request.contextPath}/demo/trainerArrangeAction!delete.action',
										data : {ids : ids.join(',')},
										dataType : 'json',
										success : function(d) {
										
												arrangeDetailDatagrid.datagrid('reload');
												arrangeDetailDatagrid.datagrid('unselectAll');
												arrangeDetailDatagrid.datagrid('uncheckAll');
											
											parent.sy.messagerShow({
												msg : '删除信息成功!',
												title : '提示信息'
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
		mydatagrid.datagrid('load', sy.serializeObject($('#demo_trainerArrange_searchForm')));
	}

	//取消查询功能按钮
	function cleanSearch() {
		mydatagrid.datagrid('load', {});
		//清空与查询有关的输入框
		$('#demo_trainerArrange_searchForm input').val('');
		$('#demo_tainerArrange_search').combobox('clear');
		$('#demo_year_search').combobox('clear');
		$('#demo_month_search').combobox('clear');
		$('#demo_week_search').combobox('clear');
	}
	
	
</script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询功能'" style="height: 85px;overflow: hidden;" align="left">
		<!-- 教练排班信息查询表单 -->
		<form id="demo_trainerArrange_searchForm">
			<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
				<tr>
					<th >所属校区:</th>
					<td><input id="demo_schoolArea_search" class="keypress_event" name="schoolArea"  style="width:150px;" /></td>
					<th >教练姓名:</th>
					<td><input  name="trainerName"  class="keypress_event" style="width:150px;" /></td>
					<th >身份证:</th>
					<td><input  name="trainerIdentity" class="keypress_event"  style="width:150px;" /></td>
					<th >驾照类型:</th>
					<td><input id="demo_driverType_search" class="keypress_event" name="trainerDriverType"  style="width:150px;" /></td>
				</tr>
				<tr>
					<th ></th>
					<th ></th>
					<td colspan="4">
						<a href="javascript:void(0);" id="trainerArrange_a_searche" class="easyui-linkbutton" iconCls="icon-search" onclick="mySearch();">查询</a>
						<a href="javascript:void(0);" id="trainerArrange_a_canel" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cleanSearch();">取消</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
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
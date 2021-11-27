
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
	var rowData=undefined;
	var personalTimingDatagrid=undefined;
	var personalDialog=undefined;
	var timingDate=undefined;
	var timimgType=undefined;
	var myData=undefined;
	var userACLs=[];
	
	var userSchoolArea=undefined;
	$(function() {
		userSchoolArea=getUserSchoolArea();
		getUserACL();
		mydatagrid = $('#demo_timing_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/demo/timingAction!datagrid.action',/* 从后台读取所有user数据 */
			title : '学员学时信息管理列表!', /* 表头标题 */
			pagination : true, /* 是否显示页码 */
			pagePosition : 'bottom', /* 页码显示的位置 */
			pageSize : 20, /* 每面显示的记录数 */
			pageList : [ 10, 20, 30, 40,50 ,100,200 ], /* 选择每面显示的记录数 */
			fit : true,
			fitColumns : true, //列自动调整功能
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
			//冻结列
			frozenColumns : [ [{
				title : '编号',
				field : 'studentId',
				width : 20,
				sortable : true,
				checkbox : true
			},{
				title : '<font color="#0099FF">所属校区</font>',
				field : 'schoolAreaName',
				sortable : true,
				width : 80,
				align : 'left'
			},{
				title : '<font color="#0099FF">学员编号</font>',
				field : 'studentNo',
				width : 80,
				align : 'left',
				formatter:formatStudentNo,
			},{
				title : '<font color="#0099FF">姓名</font>',
				field : 'studentName',
				width : 80,
				align : 'left'
			},{
				title : '<font color="#0099FF">性别</font>',
				field : 'studentSex',
				width : 60,
				align : 'center',
			}, {
				title : '<font color="#0099FF">身份证号</font>',
				field : 'studentIdentityId',
				width : 150,
				align : 'left'
			},{
				title : '<font color="#0099FF">计时类型</font>',
				field : 'timingFlag',
				sortable : true,
				width : 70, 
				align : 'center',
				formatter:initTimingCountType,
			},] ],
			 columns:[ [{
					title : '<font color="#0099FF">电车总学时</font>',
					field : 'tramTiming',
					sortable : true,
					width : 70, 
					align : 'center',
					
				},{
					title : '<font color="#0099FF">模拟总学时</font>',
					field : 'simulationTiming',
					sortable : true,
					width : 70, 
					align : 'center',
					
				},{
					title : '<font color="#0099FF">实车总学时</font>',
					field : 'carTiming',
					sortable : true,
					width : 70, 
					align : 'center',
					
				},{
					title : '<font color="#0099FF">已用学时</font>',
					field : 'usingTiming',
					sortable : true,
					width : 70, 
					align : 'center',
					
				},{
					title : '<font color="#0099FF">报名含学时</font>',
					field : 'handselTiming',
					sortable : true,
					width : 90, 
					align : 'center',
					
				},{
					title : '<font color="#0099FF">购买学时</font>',
					field : 'buyTiming',
					sortable : true,
					width : 70, 
					align : 'center',
					
				},{
					title : '<font color="#0099FF">可预约学时</font>',
					field : 'reservationTiming',
					width : 80,
					align : 'center',
				} ,{
					title : '<font color="#0099FF">剩余学时</font>',
					field : 'restTiming',
					sortable : true,
					width : 70, 
					align : 'center',
					
				},{
					title : '<font color="#0099FF">教练名称</font>',
					field : 'trainerName',
					width : 80,
				},{
					title : '<font color="#0099FF">操作员</font>',
					field : 'operator',
					width : 80,
				},{
					title : '<font color="#0099FF">备注</font>',
					field : 'comment',
					width : 200,
				},{
					title : '<font color="#0099FF">教练标识</font>',
					field : 'trainerId',
					width : 150,
				} ,{
					title : '<font color="#0099FF">校区标识</font>',
					field : 'schoolArea',
					width : 150,
				}, {
					title : '<font color="#0099FF">学员标识</font>',
					field : 'studentId',
					width : 150,
				}   
			  ]],
			//菜单功能设置
			toolbar : [{
				text : '学员学时明细',
				iconCls : 'icon-filter',
				handler : function() {
					addPersonalTiming();
				}
			}, '-',{
				id:'add-btn-toolbar',
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					edit();
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
			}, '-',{
				text : '刷新',
				iconCls : 'icon-reload',
				handler : function() {
					mydatagrid.datagrid('load',{});
				}
			}, '-'],
			onLoadSuccess : onLoadSuccess,
			//右键菜单所触发的事件
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				//取消当前页被选中的所有行
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow', rowIndex);
				$('#demo_timing_menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			},
		});

	});
	
	function onLoadSuccess(data) {
		//修改序号bug
		$(this).datagrid("fixRownumber");
		//隐藏列
		$(this).datagrid('hideColumn','trainerId');
		$(this).datagrid('hideColumn','schoolArea');
		$(this).datagrid('hideColumn','studentId');
		//初始化校区选项
		initSchoolArea($("#demo_timing_organization"),userSchoolArea);
		//添加回车事件
		bindingKeypressEvent($(".keypress_event"),mydatagrid,$("#demo_timing_searchForm"));
		//datagrid没有数据时显示没有记录信息
		if(data.total==0){
			mydatagrid.datagrid('appendRow', {
				schoolArea: '<div style="text-align:center;color:red">没有学时登记记录信息！</div>' })
			.datagrid('mergeCells', { index: 0, field: 'numbering', colspan:14 });
		}
		
		if(!userACLs[2]){
			$("#add-btn-toolbar").linkbutton('disable');
		}
	}
	
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
	
	//添加学员个人学时对话框
	function addPersonalTiming(){
		var rows = mydatagrid.datagrid('getSelections');
		
		//编辑时只能单选一行进行编辑
		if (rows.length == 1) {
			personalDialog = parent.sy.dialog({
						title : '编辑学时登记信息',
						href : '${pageContext.request.contextPath}/demo/personalTimingAction!toPersonalTimingAddPage.action',
						width : 1024,
						height : 800,
						buttons : [
								{
									text : '关闭',
									iconCls : 'icon-cancel',
									handler : function() {
										personalDialog.dialog('close');
										mydatagrid.datagrid('reload');
									}
								} ],
						//数据回显显示
						onLoad : function() {
							var f = personalDialog.find('form');
							f.form('load', {
								id:rows[0].id,
								studentId:rows[0].studentId,
								studentOrganization:rows[0].studentOrganization,
								studentName:rows[0].studentName,
								studentSex:rows[0].studentSex,
								studentCreateTime:rows[0].studentCreateTime,
								studentIdentityId:rows[0].studentIdentityId,
								studentAddress:rows[0].studentAddress,
								studentBirthdate:rows[0].studentBirthdate,
								studentPhone:rows[0].studentPhone,
								studentEmail:rows[0].studentEmail,
								studentTelephone:rows[0].studentTelephone,
								studentMailCode:rows[0].studentMailCode,
								studentContry:rows[0].studentContry,
								studentNativeNation:rows[0].studentNativeNation,
								studentResidenceId:rows[0].studentResidenceId,
								studentResidenceAddr:rows[0].studentResidenceAddr,
								studentImageId:rows[0].studentImageId,
								studentNowstate:rows[0].studentNowstate,
								studentClazz:rows[0].studentClazz,
								studentApplyType:rows[0].studentApplyType,
								studentDriverType:rows[0].studentDriverType,
								studentNo:rows[0].studentNo,
								
								trainerId:rows[0].trainerId,
								trainerName:rows[0].trainerName,
								
								numbering:rows[0].numbering,
								totalTiming:rows[0].totalTiming,
								buyTiming:rows[0].buyTiming,
								tramTiming:rows[0].tramTiming,
								simulationTiming:rows[0].simulationTiming,
								carTiming:rows[0].carTiming,
								handselTiming:rows[0].handselTiming,
								usingTiming:rows[0].usingTiming,
								restTiming:rows[0].restTiming,
								reservationTiming:rows[0].reservationTiming,
								comment:rows[0].comment,
							});
							
							//将学员基本信息属性襟止修改。将学员学时基本统计信息属性襟止修改
							var inputDisable=f.find('input[class=demo_timing_update]');
							inputDisable.attr('disabled',true);
					
							//初始化更新页面中的驾照类型
							var stuDriverType=f.find('input[name=studentDriverType]');
							stuDriverType.combobox({
								url : '${pageContext.request.contextPath}/demo/json/driverType_combobox.json',
								valueField : 'value',
								textField : 'text',
								method:'get',
							});
							//初始化更新页面中的审领取类型
							var stuAppyType=f.find('input[name=studentApplyType]');
							stuAppyType.combobox({
								url : '${pageContext.request.contextPath}/demo/json/applyType_combobox.json',
								valueField : 'value',
								textField : 'text',
								method:'get',
							});
							
							//初始化学员培训状态
							var stuState=f.find('input[name=studentNowstate]');
							stuState.combobox({
								url : '${pageContext.request.contextPath}/demo/json/studentstate_combobox.json',
								valueField : 'value',
								textField : 'text',
								method:'get',
							});
							
							//初始化所属校区参数
							var mySchoolArea=f.find('input[name=studentOrganization]');
							initSchoolArea(mySchoolArea,userSchoolArea);
							
							//加载学员个人学时datagrid
							addPersonalTimgDatagrid(rows[0].id,rows[0].studentId,rows[0].trainerId,rows[0].trainerName);
							
						}
					});
		} else if (rows.length > 1) {
			parent.sy.messagerAlert('提示信息', '同一时间只能对一个学员进行操作！', 'error');
		} else {
			parent.sy.messagerAlert('提示信息', '当前没有选中任何学员信息！', 'error');
		}
	};
	
	
	//加载学员个人学时明细对话框
	function addPersonalTimgDatagrid(timingId,studentId,trainerId,trainerName){
		//1.从datagrid中获取需要修改的信息
		var rows = mydatagrid.datagrid('getSelections');
		
		var datagridId=personalDialog.find('table[id=demo_personalTiming_datagrid]');
		personalTimingDatagrid=datagridId.datagrid({
			url:'${pageContext.request.contextPath}/demo/personalTimingAction!datagrid.action',
			queryParams:{studentId:studentId},
			title : '学员个人学时明细信息管理列表!', /* 表头标题 */
			iconCls : 'icon-save', /* 标题前边的图标 */
			pagination : true, /* 是否显示页码 */
			pagePosition : 'bottom', /* 页码显示的位置 */
			pageSize : 10, /* 每面显示的记录数 */
			pageList : [ 10, 20, 30, 40,50 ,100,200 ], /* 选择每面显示的记录数 */
			fit : true,
			fitColumns : false, //列自动调整功能
			nowrap : false, //以行的形式进行显示
			border : false,
			rownumbers: true,
			idField : 'id', //此属性一定要配置
			sortName : 'timingId', //列排序
			sortOrder : 'desc', //列排序
			checkOnSelect : false,
			selectOnCheck : true,
			striped : true,//表示条纹，隔行显示不现背景色
			nowrap:true,//单元显示
			//冻结列
			frozenColumns : [ [{
				title : '编号',
				field : 'timingId',
				width : 20,
				sortable : true,
				checkbox : true
			},{
				title : '<font color="#0099FF">编号</font>',
				field : 'numbering',
				sortable : true,
				width : 120,
				align : 'left'
			}
			 ] ],
			
			 columns:[[
			    {
					title : '<font color="#0099FF">日期</font>',
					field : 'date',
					sortable : true,
					width : 90, 
					align : 'center',
					formatter:formatTimeYMD,
				},{
					title : '<font color="#0099FF">学时类型</font>',
					field : 'timingType',
					sortable : true,
					width : 90, 
					align : 'center',
					formatter:function(value,row,index){
						if(value==0){
							return '购买';
						}else if(value==1){
							return '消费';
						}else{
							return '';
						}
					}
				}, {
					title : '<font color="#0099FF">学时用途</font>',
					field : 'timingUsingType',
					sortable : true,
					width : 100, 
					align : 'center',
					formatter:function(value,row,index){
						return formatterTimingUsingType(value,row,index);
					}
				},{
					title : '<font color="#0099FF">学时数</font>',
					field : 'useTiming',
					sortable : true,
					width : 80, 
					align : 'center',
				},{
					title : '<font color="#0099FF">教练</font>',
					field : 'trainerName',
					width : 90,
				},{
					title : '<font color="#0099FF">操作员</font>',
					field : 'operator',
					sortable : true,
					width : 70, 
					align : 'center',
					
				},{
					title : '<font color="#0099FF">创建方式</font>',
					field : 'createWay',
					sortable : true,
					width : 70, 
					align : 'center',
					formatter:function(value,row,index){
						if(value==1){
							return '<div style="text-align:center;color:red">预约</div>';
						}else{
							return '';
						}
					}
				},]],
			//菜单功能设置
			toolbar : [{
				id:"person-timing-add",
				text : '添加记录',
				iconCls : 'icon-edit',
				handler : function() {
					addMyPersonalTiming(timingId,studentId,trainerId,trainerName);
				}
			}, '-',{
				id:"person-timing-edit",
				text : '购买学时',
				iconCls : 'icon-edit',
				handler : function() {
					buyPersonalTiming(timingId,studentId,trainerId,trainerName);
				}
			}, '-', { //'-':减号，将用“｜”隔开按钮
				id:"person-timing-del",
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					removePersonalTiming(timingId,studentId);
				}
			}, '-',  {
				text : '取消选中',
				iconCls : 'icon-undo',
				handler : function() {
					//取消所有的选中行
					personalTimingDatagrid.datagrid('clearSelections');
					//取消当前页面的选中行
					personalTimingDatagrid.datagrid('unselectAll');
				}
			}, '-',{
				text : '刷新',
				iconCls : 'icon-reload',
				handler : function() {
					personalTimingDatagrid.datagrid('load',{studentId:studentId});
				}
			}, '-'],
			onBeforeLoad:onMyBeforeLoad,
			onLoadSuccess : onLoadSuccessPersonalTiming,
			//右键菜单所触发的事件
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				//取消当前页被选中的所有行
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow', rowIndex);
				$('#demo_timing_menu').menu('show', {
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
	
	function onMyBeforeLoad(){
		getUserACL();
		var btnAdd=personalDialog.find('a[id=person-timing-add]');
		var btnUpdate=personalDialog.find('a[id=person-timing-edit]');
		var btnDel=personalDialog.find('a[id=person-timing-del]');
		if(!userACLs[0]){
			btnAdd.linkbutton('disable');
		}
		if(!userACLs[2]){
			btnUpdate.linkbutton('disable');
		}
		if(!userACLs[3]){
			btnDel.linkbutton('disable');
		}
	}
	
	//添加学员个人学时页面加载成功后触发的事件
	function onLoadSuccessPersonalTiming(data) {
		
		//修改序号bug
		personalTimingDatagrid.datagrid("fixRownumber");
		//datagrid没有数据时显示没有记录信息
		if(data.total==0){
			personalTimingDatagrid.datagrid('appendRow', {
				numbering: '<div style="text-align:center;color:red">当前学员没有学时记录信息！</div>' })
			.datagrid('mergeCells', { index: 0, field: 'numbering', colspan:5 });
		}
		//personalTimingDatagrid.datagrid('hideColumn','createWay');
		
	}
	
	//添加学员个人学时信息－－添加页面
	function addMyPersonalTiming(timingId,studentId,trainerId,trainerName){
		var myForm=personalDialog.find('form');
		var addTimingDialog=parent.sy.dialog({
			title : '添加学时登记信息录入',
			href : '${pageContext.request.contextPath}/demo/personalTimingAction!toPersonalTimingAddInputPage.action',
			width : 1024,
			height : 250,
			buttons : [
					{
						text : '保存',
						iconCls : 'icon-tip',
						handler : function() {
							var btnAdd = this;
							$(btnAdd).hide();
							formSubmit=addTimingDialog.find('form');
							formSubmit.form('submit',{
									//提交添加用户表单时，调用UserAction的add方法
								url : '${pageContext.request.contextPath}/demo/personalTimingAction!add.action',
								success : function(d) {
									$(btnAdd).show();
									//对后台传递过来的JSON格式数据进行解析
									var json = $.parseJSON(d);
									if (json.success) {
										/* mydatagrid.datagrid('reload'); *///这种方式性能差，消耗系统资源
										personalTimingDatagrid.datagrid('insertRow',{
											index : 0, // index start with 0
											row : json.obj
										});
										
										addTimingDialog.dialog('close');
										personalTimingDatagrid.datagrid('selectRow',0);
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
							addTimingDialog.dialog('close');
						}
					} ],
			//数据回显显示
			onLoad : function() {
				
				var f = addTimingDialog.find('form');
				
				f.form('load', {
					timingId:timingId,
					studentId:studentId,
					trainerId:trainerId,
					trainerName:trainerName
				});
				//所消费学时用途
				var selectUsingType=f.find('input[name=timingUsingType]');
				selectUsingType.combobox({
					url : '${pageContext.request.contextPath}/demo/json/timingUsingType_combobox.json',
					valueField : 'value',
					textField : 'text',
					method:'get',
					required:true,
				});
				//教练选项初始化事件
				var trainerNameTemp=f.find('input[name=trainerName]');
				trainerNameTemp.val(trainerName);
				trainerNameTemp.attr('readonly',true);
			}//==diaolog onload()=====end
		});//end ===dialog
	}
	

	//购买学员个人学时添信息－－添加页面
	function buyPersonalTiming(timingId,studentId,trainerId,trainerName){
		var myForm=personalDialog.find('form');
		var addTimingDialog=parent.sy.dialog({
			title : '购买学时登记信息录入',
			href : '${pageContext.request.contextPath}/demo/personalTimingAction!toPersonalBuyTimingPage.action',
			width : 1024,
			height : 250,
			buttons : [
					{
						text : '保存',
						iconCls : 'icon-tip',
						handler : function() {
							formSubmit=addTimingDialog.find('form');
							formSubmit.form('submit',{
									//提交添加用户表单时，调用UserAction的add方法
								url : '${pageContext.request.contextPath}/demo/personalTimingAction!add.action',
								success : function(d) {
									//对后台传递过来的JSON格式数据进行解析
									var json = $.parseJSON(d);
									if (json.success) {
										/* mydatagrid.datagrid('reload'); *///这种方式性能差，消耗系统资源
										personalTimingDatagrid.datagrid('insertRow',{
											index : 0, // index start with 0
											row : json.obj
										});
										addTimingDialog.dialog('close');
										personalTimingDatagrid.datagrid('selectRow',0);
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
							addTimingDialog.dialog('close');
						}
					} ],
			//数据回显显示
			onLoad : function() {
				
				var f = addTimingDialog.find('form');
				
				f.form('load', {
					timingId:timingId,
					studentId:studentId,
					trainerId:trainerId,
					trainerName:trainerName
				});
				
				//教练选项初始化事件
				var trainerNameTemp=f.find('input[name=trainerName]');
				trainerNameTemp.val(trainerName);
				trainerNameTemp.attr('readonly',true);
			}//==diaolog onload()=====end
		});//end ===dialog
	}
	
	
	//更新学员个人学时信息－－更新页面
	function updateMyPersonalTiming(timingId,studentId,trainerId){
		//1.从datagrid中获取需要修改的信息
		var rows=personalTimingDatagrid.datagrid('getSelections');
		//编辑时只能单选一行进行编辑
		if (rows.length == 1) {
			var p = parent.sy.dialog({
						title : '更新学员学时信息',
						//通过href方式打开添加页面
						href : '${pageContext.request.contextPath}/demo/personalTimingAction!toPersonalTimingUpdateInputPage.action',
						width : 1024,
						height : 380,
						buttons : [{
									text : '更新',
									iconCls : 'icon-tip',
									handler : function() {
										var f = p.find('form');
										f.form('submit',{
											url : '${pageContext.request.contextPath}/demo/personalTimingAction!edit.action',
											success : function(d) {
												//接受一个JSON字符串，返回解析后的对象。
												var json = $.parseJSON(d);
												if (json.success) {
													personalTimingDatagrid.datagrid('updateRow',{
																						//获得行的索引
																index : personalTimingDatagrid.datagrid('getRowIndex',rows[0]),
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
								date:rows[0].date,
								numbering:rows[0].numbering,
								teachingContent:rows[0].teachingContent,
								timeItem:rows[0].timeItem,
								timingType:rows[0].timingType,
								useTiming:rows[0].useTiming,
								timingId:rows[0].timingId,
								studentId:rows[0].studentId,
								trainerName:rows[0].trainerName,
								trainerId:trainerId,
								timingId:timingId,
							});
							
							//初始化信息＝＝＝＝＝begin====
											//初始化时间段
							var selectTime=f.find('input[name=timeItem]');
							selectTime.combobox({
								url : '${pageContext.request.contextPath}/demo/json/traintime_combobox.json',
								valueField : 'value',
								textField : 'text',
								method:'get',
							});
							//初始化添加明细页面中的训练类型选项
							var trainType=f.find('input[name=timingType]');
							trainType.combobox({
								url : '${pageContext.request.contextPath}/demo/json/timingType_combobox.json',
								valueField : 'value',
								textField : 'text',
								method:'get',
							});
							
							//选择教练事件
							var trainerNameTemp=f.find('input[name=trainerName]');
							//TODO 
						}//end onload
					});
		} else if (rows.length > 1) {
			parent.sy.messagerAlert('提示信息', '同一时间只能更新一条记录！', 'error');
		} else {
			parent.sy.messagerAlert('提示信息', '请选择要更新的记录！', 'error');
		}
	};
	//添加学员个人学时信息－－删除
	function removePersonalTiming(){
		var rows = personalTimingDatagrid.datagrid('getChecked');
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
										url : '${pageContext.request.contextPath}/demo/personalTimingAction!delete.action',
										data : {ids : ids.join(',')},
										dataType : 'json',
										success : function(d) {
											personalTimingDatagrid.datagrid('load');
											personalTimingDatagrid.datagrid('unselectAll');
											personalTimingDatagrid.datagrid('uncheckAll');
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
	
	
	
	//
	function edit() {
		var rows = mydatagrid.datagrid('getSelections');
		//编辑时只能单选一行进行编辑
		if (rows.length == 1) {
			var p = parent.sy.dialog({
						title : '编辑学时登记信息',
						href : '${pageContext.request.contextPath}/demo/timingAction!toTimingEditPage.action',
						width : 1024,
						height : 640,
						buttons : [
								{
									text : '更新',
									iconCls : 'icon-tip',
									handler : function() {
										var f = p.find('form');
										//提交表单
										f.form('submit',{
											url : '${pageContext.request.contextPath}/demo/timingAction!edit.action',
											success : function(d) {
													var json = $.parseJSON(d);
													if (json.success) {
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
								id:rows[0].id,
								studentId:rows[0].studentId,
								studentOrganization:rows[0].studentOrganization,
								studentName:rows[0].studentName,
								studentSex:rows[0].studentSex,
								studentCreateTime:rows[0].studentCreateTime,
								studentIdentityId:rows[0].studentIdentityId,
								studentAddress:rows[0].studentAddress,
								studentBirthdate:rows[0].studentBirthdate,
								studentPhone:rows[0].studentPhone,
								studentEmail:rows[0].studentEmail,
								studentTelephone:rows[0].studentTelephone,
								studentMailCode:rows[0].studentMailCode,
								studentContry:rows[0].studentContry,
								studentNativeNation:rows[0].studentNativeNation,
								studentResidenceId:rows[0].studentResidenceId,
								studentResidenceAddr:rows[0].studentResidenceAddr,
								studentImageId:rows[0].studentImageId,
								studentNowstate:rows[0].studentNowstate,
								studentClazz:rows[0].studentClazz,
								studentApplyType:rows[0].studentApplyType,
								studentDriverType:rows[0].studentDriverType,
								studentNo:rows[0].studentNo,
								
								numbering:rows[0].numbering,
								totalTiming:rows[0].totalTiming,
								buyTiming:rows[0].buyTiming,
								tramTiming:rows[0].tramTiming,
								simulationTiming:rows[0].simulationTiming,
								carTiming:rows[0].carTiming,
								handselTiming:rows[0].handselTiming,
								usingTiming:rows[0].usingTiming,
								restTiming:rows[0].restTiming,
								reservationTiming:rows[0].reservationTiming,
								comment:rows[0].comment,
							});
							
							//将学员基本信息属性襟止修改
							var inputDisable=f.find('input[class=demo_timing_update]');
							inputDisable.attr('disabled',true);
					
							//初始化更新页面中的驾照类型
							var stuDriverType=f.find('input[name=studentDriverType]');
							stuDriverType.combobox({
								url : '${pageContext.request.contextPath}/demo/json/driverType_combobox.json',
								valueField : 'value',
								textField : 'text',
								method:'get',
							});
							//初始化更新页面中的审领取类型
							var stuAppyType=f.find('input[name=studentApplyType]');
							stuAppyType.combobox({
								url : '${pageContext.request.contextPath}/demo/json/applyType_combobox.json',
								valueField : 'value',
								textField : 'text',
								method:'get',
							});
							
							//初始化学员培训状态
							var stuState=f.find('input[name=studentNowstate]');
							stuState.combobox({
								url : '${pageContext.request.contextPath}/demo/json/studentstate_combobox.json',
								valueField : 'value',
								textField : 'text',
								method:'get',
							});
							
							//初始化所属校区参数
							var mySchoolArea=f.find('input[name=studentOrganization]');
							mySchoolArea.combotree({
								url:'${pageContext.request.contextPath}/demo/organizationAction!doNotNeedSession_tree.action',
								lines:true,
							});
							
							//更新参数事件
							var totalTimingTemp=f.find('input[name=totalTiming]');
							var buyTimingTemp=f.find('input[name=buyTiming]');
							var tramTimingTemp=f.find('input[name=tramTiming]');
							var simulationTimingTemp=f.find('input[name=simulationTiming]');
							var carTimingTemp=f.find('input[name=carTiming]');
							var handselTimingTemp=f.find('input[name=handselTiming]');
							var usingTimingTemp=f.find('input[name=usingTiming]');
							var restTimingTemp=f.find('input[name=restTiming]');
							
							//购买学时事件
							buyTimingTemp.change(function(){
								//1)修改总学时
								var temp=parseInt(totalTimingTemp.val())+parseInt(buyTimingTemp.val());
								totalTimingTemp.val(temp);
								//2)修改剩余学时
								var restTempval=parseInt(restTimingTemp.val())+parseInt(buyTimingTemp.val());
								restTimingTemp.val(restTempval);
							});
							
							//电车学时事件
							tramTimingTemp.change(function(){
								//1)剩余学时
								var temp=parseInt(21)-parseInt(tramTimingTemp.val());
								if(temp<0){
									parent.sy.messagerAlert('提示信息', '您当前输入值大于剩余学时！', 'error');
									tramTimingTemp.val('0');
								}
							});
							
							//模拟学时事件
							simulationTimingTemp.change(function(){
								//1)剩余学时
								var temp=parseInt(4)-parseInt(simulationTimingTemp.val());
								if(temp<0){
									parent.sy.messagerAlert('提示信息', '您当前输入值大于剩余学时！', 'error');
									simulationTimingTemp.val('0');
								}
								
							});
							
							//实车学时事件
							carTimingTemp.change(function(){
								//1)剩余学时
								var temp=parseInt(23)-parseInt(carTimingTemp.val());
								if(temp<0){
									parent.sy.messagerAlert('提示信息', '您当前输入值大于剩余学时！', 'error');
									carTimingTemp.val('0');
								}
								
							});
							
							//报名所含学时
							handselTimingTemp.change(function(){
								if(totalTimingTemp.val()==0){
									totalTimingTemp.val(parseInt(handselTimingTemp.val()));
									var temp=parseInt(restTimingTemp.val())+parseInt(totalTimingTemp.val());
									restTimingTemp.val(temp);
								}
							});
							
						}
					});
		} else if (rows.length > 1) {
			parent.sy.messagerAlert('提示信息', '同一时间只能更新一条记录！', 'error');
		} else {
			parent.sy.messagerAlert('提示信息', '请选择要更新的记录！', 'error');
		}
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
										url : '${pageContext.request.contextPath}/demo/timingAction!delete.action',
										data : {ids : ids.join(',')},
										dataType : 'json',
										success : function(d) {
											mydatagrid.datagrid('load');
											mydatagrid.datagrid('unselectAll');
											mydatagrid.datagrid('onUncheckAll');
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
		searchStudentNo($("#search_studentNo"), userSchoolArea);
		mydatagrid.datagrid('load', sy.serializeObject($('#demo_timing_searchForm')));
		$("#search_studentNo").val('');
	}

	//取消查询功能按钮
	function cleanSearch() {
		mydatagrid.datagrid('load', {});
		//清空与查询有关的输入框
		$('#demo_timing_searchForm input').val('');
	}

</script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询功能'" style="height: 90px;overflow: hidden;" >
		<!-- 人员信息查询表单 -->
		<form id="demo_timing_searchForm">
			<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
				<tr>
				<th style="width: 65px;">所属校区:</th>
				<td><input id="demo_timing_organization" name="schoolArea" style="width: 155px;"/></td>
				<th style="width: 65px;">编号:</th>
				<td><input id="search_studentNo" class="keypress_event" name="studentNo" style="width: 155px;"/></td>
				<th style="width: 65px;">姓名:</th>
				<td><input id="demo_theory_name" class="keypress_event" name="studentName"  style="width: 155px;"/></td>
				<th style="width: 65px;">身份证号:</th>
				<td><input name="studentIdentityId" class="keypress_event"  style="width: 155px;"/></td>
			</tr>
				<tr>
					<th style="width: 55px;"></th>
					<td></td>
					<td colspan="2">
						<a href="javascript:void(0);" id="timing_a_searche" class="easyui-linkbutton" iconCls="icon-search" onclick="mySearch();">查询</a>
						<a href="javascript:void(0);" id="timing_a_canel" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cleanSearch();">取消</a>
					</td>
					<th></th>
					<td></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- datagrid数据在center中展现 -->
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<table id="demo_timing_datagrid"></table>
	</div>
	<!-- 操作按钮事件 -->
	<div id="demo_timing_menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="addPersonalTiming();" data-options="iconCls:'icon-add'">学员学时明细</div>
		<div onclick="edit();" data-options="iconCls:'icon-edit'">更新</div>
		<div onclick="remove();" data-options="iconCls:'icon-cancel'">删除</div>
	</div>
</body>
</html>
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
	var userACLs=[];
	$(function() {
		userSchoolArea=getUserSchoolArea();
		getUserACL();
		mydatagrid = $('#demo_car_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/demo/carAction!datagrid.action',/* 从后台读取所有user数据 */
			title : '车辆信息管理列表!', /* 表头标题 */
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
			sortName : 'carNo', //列排序
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
				width : 100,
				align : 'center'
			},{
				title : '<font color="#0099FF">车牌号</font>',
				field : 'carNo',
				width : 100,
				sortable : true,
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
				formatter:initCarUsingWay,
			},
			{
				title : '<font color="green">使用状态</font>',
				field : 'usingState',
				sortable : true,
				width : 70,
				align : 'center',
				formatter:initCarUsingState,
			},{
				title : '<font color="red">微信预约</font>',
				field : 'receiveWechatFlag',
				sortable : true,
				width : 70,
				align : 'center',
				formatter:initCarWechatState,
			}, ] ],
			//普通列
			columns : [ [
			{
				title : '<font color="#0099FF">使用类型</font>',
				field : 'useType',
				sortable : true,
				width : 70,
				align : 'center',
				formatter:initCarUsingWay,
			},{
				title : '<font color="#0099FF">所属教练</font>',
				field : 'trainerName',
				width : 80,
			},{
				title : '<font color="#0099FF">车辆品牌</font>',
				field : 'vehicleBrands',
				sortable : true,
				width : 150,
				align : 'left'
			},{
				title : '<font color="#0099FF">车辆型号</font>',
				field : 'vehicleType',
				sortable : true,
				width : 150,
				align : 'center'
			},{
				title : '<font color="#0099FF">运输证号</font>',
				field : 'transportNo',
				sortable : true,
				width : 120,
				align : 'center'
			}, {
				title : '<font color="#0099FF">购买日期</font>',
				field : 'buyDate',
				sortable : true,
				width : 90,
				align : 'center',
				formatter:formatTimeYMD,
			},{
				title : '<font color="#0099FF">商业保险日期</font>',
				field : 'businessExpireDay',
				sortable : true,
				width : 100,
				align : 'center',
				formatter:formatTimeYMD,
			},{
				title : '<font color="#0099FF">交强险日期</font>',
				field : 'insuranceExpireDay',
				sortable : true,
				width : 90,
				align : 'center',
				formatter:formatTimeYMD,
			}, {
				title : '<font color="#0099FF">维护日期</font>',
				field : 'maintainDay',
				sortable : true,
				width : 90,
				align:'center',
				formatter:formatTimeYMD,
			}, {
				title : '<font color="#0099FF">年审日期</font>',
				field : 'examinedDay',
				sortable : true,
				width : 80,
				align:'center',
				formatter:function(value,row,index){
					return formatTimeYMD(value, row, index);
				}
			},{
				title : '<font color="#0099FF">教练标识</font>',
				field : 'trainerId',
				width : 80,
			},{
				title : '<font color="#0099FF">操作员</font>',
				field : 'operator',
				width : 80,
			},{
				title : '<font color="#0099FF">备注</font>',
				field : 'comment',
				width : 200
			},{
				title : '<font color="#0099FF">学校标识</font>',
				field : 'schoolArea',
				width : 200
			},{
				title : '<font color="#0099FF">车辆使用标识</font>',
				field : 'carusingTempId',
				width : 200
			}
			] ],
			//菜单功能设置
			toolbar : [ {
				id:'add-btn-toolbar',
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					append();
				}
			}, '-', { //'-':减号，将用“｜”隔开按钮
				id:'del-btn-toolbar',
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					remove();
				}
			}, '-', {
				id:'edit-btn-toolbar',
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
			}, '-' , {
				id:'import-btn-toolbar',
				text : '导入',
				iconCls : 'icon-ok',
				handler : function() {
					importDatas();
				}
			}, '-', {
				id:'export-btn-toolbar',
				text : '导出',
				iconCls : 'icon-ok',
				handler : function() {
					exportDatas();
				}
			}, '-'],
			onBeforeLoad:function(param){
				if(!userACLs[0]){ //Create
                    $("#add-btn-toolbar").linkbutton('disable'); 
					$("#add-menu").hide();
                }
				if(!userACLs[2]){//Update
					$("#edit-btn-toolbar").linkbutton('disable');
					$("#import-btn-toolbar").linkbutton('disable'); 
					$("#export-btn-toolbar").linkbutton('disable');
					$("#edit-menu").hide();
				}
				if(!userACLs[3]){//delete
					$("#del-btn-toolbar").linkbutton('disable');
					$("#del-menu").hide();
				}
			},
			onLoadSuccess : function (data) {
				$(this).datagrid("fixRownumber");
				$(this).datagrid('hideColumn','schoolArea');
				$(this).datagrid('hideColumn','transportNo');
				$(this).datagrid('hideColumn','trainerId');
				$(this).datagrid('hideColumn','carusingTempId');
				
				//初始化校区选项
				$("#demo_car_schoolArea").combotree({
					url:'${pageContext.request.contextPath}/demo/organizationAction!doNotNeedSession_tree.action',
					lines:true,
				});
				//非系统管理员，禁止查询其他校区
				if(userSchoolArea!==null){
					$("#demo_car_schoolArea").combotree('setValue',userSchoolArea);
					$("#demo_car_schoolArea").combotree('readonly',true);
				}else{
					$("#demo_car_schoolArea").combotree('readonly',false);
				}
				
				$("#demo_car_carType").combobox({
					url : '${pageContext.request.contextPath}/demo/json/carUsingType_combobox.json',
					valueField : 'value',
					textField : 'text',
					method:'get',
				});
				$("#demo_car_usingState").combobox({
					url : '${pageContext.request.contextPath}/demo/json/carState_combobox.json',
					valueField : 'value',
					textField : 'text',
					method:'get',
				});
				$("#demo_car_useType").combobox({
					url : '${pageContext.request.contextPath}/demo/json/carUsingType_combobox.json',
					valueField : 'value',
					textField : 'text',
					method:'get',
				});
				
				//datagrid没有数据时显示没有记录信息
				if(data.total==0){
					$(this).datagrid('appendRow', {
						carNo: '<div style="text-align:center;color:red">没有车辆记录信息！</div>' })
					.datagrid('mergeCells', { index: 0, field: 'transportNo', colspan:14 });
				}
			},
			//右键菜单所触发的事件
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				//取消当前页被选中的所有行
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow', rowIndex);
				$('#demo_car_menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			}
			
		});

	});
	
	//单击编辑按钮，实现对车辆的编辑功能
	function edit() {
		var rows = mydatagrid.datagrid('getSelections');
		//编辑时只能单选一行进行编辑
		if (rows.length == 1) {
			var p = parent.sy.dialog({
						title : '编辑车辆信息',
						href : '${pageContext.request.contextPath}/demo/carAction!toEditPage.action',
						width : 1080,
						height : 460,
						buttons : [
								{
									text : '更新',
									iconCls : 'icon-tip',
									handler : function() {
										var f = p.find('form');
										//提交表单
										f.form('submit',{
											url : '${pageContext.request.contextPath}/demo/carAction!edit.action',
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
							var f = p.find('form');
							f.form('load', {
								id : rows[0].id,
								vehicleBrands : rows[0].vehicleBrands,
								carNo : rows[0].carNo,
								vehicleType : rows[0].vehicleType,
								licenseNo : rows[0].licenseNo,
								transportNo : rows[0].transportNo,
								buyDate : rows[0].buyDate,
								businessExpireDay : rows[0].businessExpireDay,
								insuranceExpireDay : rows[0].insuranceExpireDay,
								maintainDay : rows[0].maintainDay,
								examinedDay : rows[0].examinedDay,
								comment : rows[0].comment,
								schoolArea:rows[0].schoolArea,
								carType:rows[0].carType,
								usingState:rows[0].usingState,
								useType:rows[0].useType,
								trainerId:rows[0].trainerId,
								trainerName:rows[0].trainerName,
								carusingTempId:rows[0].carusingTempId,
							});
							var schoolId=f.find('input[name=schoolArea]');
							initSchoolArea(schoolId,userSchoolArea);//初始校区选项
							onloadCarParams(p,rows[0].carType,rows[0].usingState,rows[0].useType,1,rows[0].trainerId);//初始化车辆cobobox
							var receiveWecharFlag=f.find('input[name=receiveWechatFlag]');
							initCarWechatFlag(receiveWecharFlag,rows[0].receiveWechatFlag, true, false);
						}
					});
		} else if (rows.length > 1) {
			parent.sy.messagerAlert('提示信息', '同一时间只能更新一条记录！', 'error');
		} else {
			parent.sy.messagerAlert('提示信息', '请选择要更新的记录！', 'error');
		}
	}
	
	//导入数据按钮，实现对车辆基本信息的导入
	function importDatas(){
		var importDialog = parent.sy.dialog({
			title : '车辆信息导入',
			href : '${pageContext.request.contextPath}/demo/carAction!toUploadPage.action',
			width : 560,
			height : 160,
			
			buttons : [
					{
						text : '导入',
						iconCls : 'icon-add',
						handler : function() {
							var f = importDialog.find('form');
							//提交表单
							f.form('submit',{
								//提交添加用户表单时，调用UserAction的add方法
								url : '${pageContext.request.contextPath}/demo/carAction!importCarDatas.action',
								success : function(d) {
									var json = $.parseJSON(d);
									importDialog.dialog('close');
									if(json.success){
										$.messager.progress({
											text : '正在导入中...，请稍后',
											interval : 100
										});
										window.setTimeout(function() {
											try {
												$.messager.progress('close');
												parent.sy.messagerShow({
													msg : json.msg,
													title : '提示信息'
												});
												mydatagrid.datagrid('reload'); ///这种方式性能差，消耗系统资源
											} catch (e) {
											}
										}, 5000);
									}else{
										parent.sy.messagerShow({
											msg : json.msg,
											title : '提示信息'
										});
									}	 
									
								}
								
							});
						}
					}, {
						text : '取消',
						iconCls : 'icon-cancel',
						handler : function() {
							importDialog.dialog('close');
						}
				} ],
				//通过onLoad函数实现数据回显
				onLoad : function() {
					var f = importDialog.find('form');
					var myfile=f.find('input[name=importFile]');
					myfile.filebox({
						 buttonText:'打开',
						    iconCls:'icon-man',
						    iconAlign:'left',
						    multiple:false,
						    prompt:'请选择要导入的Excel文件',
						    onChange:function(){
						    	//todo
						    }
						    
					});
				},
		});
		
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

	//添加用户
	function append() {
		var addDialog=undefined;
		addDialog= parent.sy.dialog({
					title : '车辆信息录入',
					href : '${pageContext.request.contextPath}/demo/carAction!toAddPage.action',
					width : 1080,
					height : 460,
					//通过onLoad函数实现数据回显
					onLoad : function() {
						//todo
					},
					buttons : [
							{
								text : '添加',
								iconCls : 'icon-add',
								handler : function() {
									var f = addDialog.find('form');
									var btnAdd = this;
									$(btnAdd).hide();
									//提交表单
									f.form('submit',{
										//提交添加用户表单时，调用UserAction的add方法
										url : '${pageContext.request.contextPath}/demo/carAction!add.action',
										success : function(d) {
											$(btnAdd).show();
											var json = $.parseJSON(d);
											if (json.success) {
												/* mydatagrid.datagrid('reload'); *///这种方式性能差，消耗系统资源
												mydatagrid.datagrid('insertRow',{
													index : 0, // index start with 0
													row : json.obj
												});
												addDialog.dialog('close');
												mydatagrid.datagrid('selectRow',0);
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
						onLoad:function(){
							var f = addDialog.find('form');
							var schoolId=f.find('input[name=schoolArea]');
							initSchoolArea(schoolId,userSchoolArea);
							onloadCarParams(addDialog);
							var receiveWecharFlag=f.find('input[name=receiveWechatFlag]');
							initCarWechatFlag(receiveWecharFlag, 1, true, false);
						},
				});
	}
	
	//初始化车辆信息combobox选项 updateState=1,表示更新方法调用，否则表示添加方法调用
	function onloadCarParams(addDialog,carType,usingState,useType,updateState,trainerId){
		var f = addDialog.find('form');
		//初始化车辆用途
		var usingFor=f.find('input[name=carType]');
		usingFor.combobox({
			url : '${pageContext.request.contextPath}/demo/json/carUsingType_combobox.json',
			valueField : 'value',
			textField : 'text',
			method:'get',
			onLoadSuccess:function(){
				if(updateState!=1){
					usingFor.combobox('setValue',6);//默认选择C1
				}else{
					usingFor.combobox('setValue',carType);
				}
				
			},
			onChange:function(){
				if(usingFor.combobox('getValue')==11){
					usingType.combobox('setValue','');
					usingType.combobox('reload','${pageContext.request.contextPath}/demo/json/C2C5State_combobox.json');
				}else{
					//禁用车辆使用类型选择
					usingType.combobox('setValue',usingFor.combobox('getValue'));
				}
			}	
		}); 
		//初始化车辆使用状态
		var carStateTemp=f.find('input[name=usingState]');
		carStateTemp.combobox({
			url : '${pageContext.request.contextPath}/demo/json/carState_combobox.json',
			valueField : 'value',
			textField : 'text',
			method:'get',
			onLoadSuccess:function(){
				if(updateState!=1){
					carStateTemp.combobox('setValue',2);//默认选择使用
				}else{
					carStateTemp.combobox('setValue',usingState);
				}
			},
			onChange:function(){
				
			}
		}); 
		//初始化车辆使用类型
		var usingType=f.find('input[name=useType]');
		usingType.combobox({
			url : '${pageContext.request.contextPath}/demo/json/carUsingType_combobox.json',
			valueField : 'value',
			textField : 'text',
			method:'get',
			onLoadSuccess:function(){
				if(usingFor.combobox('getValue')!=11){
					if(updateState!=1){
						usingType.combobox('setValue',6);//默认选择C1
					}else{
						usingType.combobox('setValue',useType);
					}
				}else{
					if(updateState==1){
						usingType.combobox('setValue',useType);
					}
				}
				
			}
		}); 
		
		
		//初始化所属教练combobox
		var trainerIdTemp=f.find('input[name=trainerId]');
		trainerIdTemp.combobox({
			url : '${pageContext.request.contextPath}/demo/studentAction!getAllTrainersForCombobox.action',
			queryParams:{organizationId:userSchoolArea},
			valueField : 'value',
			textField : 'text',
			method:'get',
			editable:false,
			required:false,
		});
		trainerIdTemp.combobox('readonly',true);
		trainerIdTemp.combobox('setValue',trainerId);
		//取消教练捆绑
		var btnCancelTrainer=f.find('a[id=demo_car_a_canceltrainer]');
			btnCancelTrainer.linkbutton({
		    	iconCls: 'icon-cancel',
		    	onClick:function(){
		    		cancelTarinerBinding(trainerIdTemp);
		    	},
		  });
		
		//选择教练按钮事件
		var btnTrainer=f.find('a[id=demo_car_a_selecttrainer]');
		btnTrainer.linkbutton({
	    		iconCls: 'icon-search',
	    		onClick:function(){
	    				if(userSchoolArea==""){
	    				    		parent.sy.messagerAlert('提示信息', '系统当前没有选择学员所属校区！！', 'question');
	    				}else{
	    				    		initTrainerSelect(usingFor.combobox('getValue'),trainerIdTemp,userSchoolArea);
	    				 }
	    				    	
	    		 },
	    });
		
	}
	//用户取消教练，更换教练时学员编号发生改变
	function cancelTarinerBinding(trainerId){
		trainerId.combobox('setValue',null);
    	trainerId.combobox('setText',null);
	}
	
	//选择教练对话框
	function initTrainerSelect(stuDriverType,trainerId,organizationId){
		var selectTrainerDialog=undefined;
		 selectTrainerDialog = parent.sy.dialog({
			title : '选择教练信息列表',
			href : '${pageContext.request.contextPath}/demo/studentAction!toSelectTrainerPage.action',
			width : 1024,
			height :590,
			buttons : [
					{
						text : '关闭',
						iconCls : 'icon-cancel',
						handler : function() {
							selectTrainerDialog.dialog('close');
						}
					} ],
			//数据回显显示
			onLoad : function() {
				var f=selectTrainerDialog.find('form');
				var organizationIdVal=f.find('input[name=organizationId]')
				organizationIdVal.val(organizationId);
				
				var trainerDatagrid=f.find('table[id=demo_student_selectTrainer]');
				var carBandingFlag=2;//取非1值即可
				trainerDatagrid.datagrid({
					url : '${pageContext.request.contextPath}/demo/studentAction!getTrainerDatabyType.action',/* 从后台读取所有user数据 */
					queryParams:{trainerTechingType:stuDriverType,organizationId:organizationId,carBanding:carBandingFlag},
					title : '教练信息管理列表!', /* 表头标题 */
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
					sortName : 'codeNo', //列排序
					sortOrder : 'asc', //列排序
					checkOnSelect : true,
					selectOnCheck : true,
					striped : true,//表示条纹，隔行显示不现背景色
					nowrap:true,//单元显示
					singleSelect:true,//单选
					//冻结列
					//普通列
					frozenColumns : [ [ {
						title : '编号',
						field : 'trainerId',
						width : 20,
						checkbox : true
					}, {
						title : '<font color="#0099FF">所属校区</font>',
						field : 'trainerSchoolAreaName',
						width : 80,
						align : 'center'
					} ,{
						title : '<font color="#0099FF">教练编号</font>',
						field : 'trainerCodeNo',
						width : 80,
						align : 'center',
						formatter:formatTrainerNo,
					} ,{
						title : '<font color="#0099FF">姓名</font>',
						field : 'trainerName',
						width : 80,
						align : 'center'
					} ,{
						title : '<font color="#0099FF">身份证</font>',
						field : 'trainerIdentity',
						width : 140, //在datagrid中一定要添加列宽度，否则会加长datagrid渲染的时间，消耗系统资源
					}
					] ],
					//普通列
					columns : [ [ {
						title : '<font color="#0099FF">联系电话</font>',
						field : 'trainerPhone',
						sortable : true,
						width : 140
					},{
						title : '<font color="#0099FF">驾照类型</font>',
						field : 'trainerDriverType',
						width : 70,
						align:'center',
						formatter:formatterDriverType,
						
					},
					] ],
					//菜单功能设置
					toolbar : [ {
						text : '确定',
						iconCls : 'icon-add',
						handler : function() {
							rows=trainerDatagrid.datagrid('getSelections');
							if(rows.length==0){
								parent.sy.messagerAlert('提示信息', '当前没有给学员选配教练^v^！', 'error');
							}
							trainerId.combobox('setValue',rows[0].trainerId);
							selectTrainerDialog.dialog('close');
						}
					},  '-', { //'-':减号，将用“｜”隔开按钮
						text : '返回上一步',
						iconCls : 'icon-remove',
						handler : function() {
							selectTrainerDialog.dialog('close');
						}
					},  '-',{
						text : '取消选中',
						iconCls : 'icon-undo',
						handler : function() {
							//取消所有的选中行
							trainerDatagrid.datagrid('clearSelections');
							//取消当前页面的选中行
							trainerDatagrid.datagrid('unselectAll');
						}
					}, '-' ],
					onLoadSuccess : function (data) {
						//修改序号bug
						trainerDatagrid.datagrid("fixRownumber");
						//datagrid没有数据时显示没有记录信息
						if(data.total==0){
							trainerDatagrid.datagrid('appendRow', {
								trainerSchoolAreaName: '<div style="text-align:center;color:red">没有记录信息！</div>' })
							.datagrid('mergeCells', { index: 0, field: 'trainerSchoolAreaName', colspan:14 });
						}
						
					},
				});
				//查询按钮功能事件
				var btnSearch=f.find('a[id=demo_student_searchTrainer]');
				btnSearch.linkbutton({
				    iconCls: 'icon-search',
				    onClick:function(){
				    	trainerDatagrid.datagrid('load', sy.serializeObject(f)); 	
				    }
				});
				//重置按钮功能事件
				var btnReset=f.find('a[id=demo_student_resetTrainer]');
				btnReset.linkbutton({
				    iconCls: 'icon-redo',
				    onClick:function(){
				    	//将数据还原显示到点击查询按钮前的数据
				    	trainerDatagrid.datagrid('load', {organizationId:organizationId});
				    	f.find('input').val('');
				    }
				});
			}//end onLoad
		});//end diaolog
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
										url : '${pageContext.request.contextPath}/demo/carAction!delete.action',
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
		mydatagrid.datagrid('load', sy.serializeObject($('#demo_car_searchForm')));
	}

	//取消查询功能按钮
	function cleanSearch() {
		mydatagrid.datagrid('load', {});
		//清空与查询有关的输入框
		$('#demo_car_searchForm input').val('');
	}
	
	function exportDatas() {
		var rows = mydatagrid.datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			parent.sy.messagerConfirm(
							'请确认',
							'您要导出当前所选择项信息？',
							function(r) {
								if (r) {
									for (var i = 0; i < rows.length; i++) {
										ids.push(rows[i].id);
									}
									window.location.href="${pageContext.request.contextPath}/demo/carAction!toExportPage.action?ids="+ids;
								}
							});
		} else {
			parent.sy.messagerAlert('提示信息', '请勾选要导出的记录！', 'error');
		}
	}
	
</script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询功能'" style="height:100px;overflow: hidden;" align="left">
		<!-- 车辆信息查询表单 -->
		<form id="demo_car_searchForm">
			<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
				<tr>
					<th style="width:65px;">所属校区:</th>
					<td><input id="demo_car_schoolArea" name="schoolArea" style="width:150px;" /></td>
					<th style="width:65px;">车牌号:</th>
					<td><input name="carNo" style="width:150px;" /></td>
					<th style="width:65px;">行驶证号：</th>
					<td><input name="licenseNo" style="width:150px;" /></td>
					<th style="width: 55px;">车辆用途:</th>
					<td><input id="demo_car_carType" name="carType" style="width: 155px;"  /></td>
				</tr>
				<tr>
					<th style="width: 55px;">使用状态:</th>
					<td><input id="demo_car_usingState"  name=usingState  style="width: 155px;" />
					</td>
					<th style="width: 55px;">使用类型:</th>
					<td><input id="demo_car_useType"  name="useType" style="width: 155px;" />
					</td>
					<!-- <th style="width: 55px;">所属教练:</th>
					<td><input   name="trainerName" style="width: 155px;" />
					</td> -->
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
		<table id="demo_car_datagrid"></table>
	</div>
	<!-- 操作按钮事件 -->
	<div id="demo_car_menu" class="easyui-menu" style="width:120px;display: none;">
		<div id="add-menu" onclick="append();" data-options="iconCls:'icon-add'">增加</div>
		<div id="del-menu" onclick="remove();" data-options="iconCls:'icon-remove'">删除</div>
		<div id="edit-menu" onclick="edit();" data-options="iconCls:'icon-edit'">更新</div>
	</div>
</body>
</html>
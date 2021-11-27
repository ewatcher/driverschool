<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.tuocheng.util.base.Util"%>
<%
  String resourceSn = Util.objToString(request.getParameter("resourceSn"), "");
	out.print(resourceSn);
%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">
	var mydatagrid=undefined;;
	var selectDatagrid=undefined;
	var initPersonDepartment;
	var rowData=undefined;
	var userSchoolArea=undefined;
	var userACLs=[];
	$(function() {
		userSchoolArea=getUserSchoolArea();
		getUserACL();
		
		mydatagrid = $('#demo_quitschool_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/demo/quitschoolAction!datagrid.action',/* 从后台读取所有user数据 */
			title : '学员退学信息管理列表!', /* 表头标题 */
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
			sortName : 'id', //列排序
			sortOrder : 'desc', //列排序
			checkOnSelect : false,
			selectOnCheck : true,
			striped : true,//表示条纹，隔行显示不现背景色
			nowrap:true,//单元显示
			//冻结列
			frozenColumns : [ [{
				title : '编号',
				field : 'id',
				width : 20,
				sortable : true,
				checkbox : true
			}, {
				title : '<font color="#0099FF">所属校区</font>',
				field : 'studentOrganization',
				width : 150,
				align : 'left'
			},{
				title : '<font color="#0099FF">姓名</font>',
				field : 'studentName',
				width : 100,
				align : 'left'
			},{
				title : '<font color="#0099FF">学员编号</font>',
				field : 'studentNo',
				sortable : true,
				width : 150,
				align : 'left',
				formatter:formatStudentNo,
			},{
				title : '<font color="#0099FF">身份证号</font>',
				field : 'studentIdentityId',
				width : 150,
				align : 'left'
			},{
				title : '<font color="#0099FF">退学日期</font>',
				field : 'quitDate',
				sortable : true,
				width : 90,
				align : 'left',
				formatter:formatTimeYMD,
			},  
			 ] ],
			
			 columns:[[
			    {
					title : '<font color="#0099FF">扣款金额</font>',
					field : 'debitFee',
					sortable : true,
					width : 100, 
					align : 'center',
					
				}, {
					title : '<font color="#0099FF">退款金额</font>',
					field : 'quitFee',
					width : 100 
				},
					{
						title : '<font color="#0099FF">退学原因</font>',
						field : 'quitReason',
						width : 200,
						formatter:showAllMsg,
					},{
						title : '<font color="#0099FF">操作员</font>',
						field : 'operator',
						width : 80,
					},{
						title : '<font color="#0099FF">备注</font>',
						field : 'comment',
						width : 200,
						formatter:showAllMsg,
					}
			          
			       ]],
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
				id:'detail-btn-toolbar',
				text : '详细信息',
				iconCls : 'icon-edit',
				handler : function() {
					read();
				}
			}, '-',{
				text : '取消选中',
				iconCls : 'icon-undo',
				handler : function() {
					//取消所有的选中行
					mydatagrid.datagrid('clearSelections');
					//取消当前页面的选中行
					mydatagrid.datagrid('unselectAll');
				}
			}, '-' ],
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
			onLoadSuccess : onLoadSuccess,
			//右键菜单所触发的事件
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				//取消当前页被选中的所有行
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow', rowIndex);
				$('#demo_quitschool_menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			},
		});

	});
	
	function onLoadSuccess(data) {
		//修改序号bug
		$(this).datagrid("fixRownumber");
		//初始化查询相关属性选项值
		//初始化校区选项
		initSchoolArea($("#demo_quitschool_organization"),userSchoolArea);
		//添加回车事件
		bindingKeypressEvent($(".keypress_event"),mydatagrid,$("#demo_quitschool_searchForm"));
		//datagrid没有数据时显示没有记录信息
		if(data.total==0){
			mydatagrid.datagrid('appendRow', {
				studentOrganization: '<div style="text-align:center;color:red">没有退学登记记录信息！</div>' })
			.datagrid('mergeCells', { index: 0, field: 'studentOrganization', colspan:14 });
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
	
	//单击编辑按钮，实现对用户的编辑功能
	function edit() {
		var rows = mydatagrid.datagrid('getSelections');
		//编辑时只能单选一行进行编辑
		if (rows.length == 1) {
			var p =undefined; 
				p=parent.sy.dialog({
						title : '编辑退学登记信息',
						href : '${pageContext.request.contextPath}/demo/quitschoolAction!toQuitschoolEditPage.action',
						width : 1024,
						height : 580,
						buttons : [
								{
									text : '更新',
									iconCls : 'icon-tip',
									handler : function() {
										var f = p.find('form');
										//提交表单
										f.form('submit',{
											url : '${pageContext.request.contextPath}/demo/quitschoolAction!edit.action',
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
								
								quitDate:rows[0].quitDate,
								debitFee:rows[0].debitFee,
								quitFee:rows[0].quitFee,
								quitReason:rows[0].quitReason,
								comment:rows[0].comment,
							});
							
							//将学员基本信息属性襟止修改
							var inputDisable=f.find('input[class=demo_quitschool_update]');
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
							
						}
					});
		} else if (rows.length > 1) {
			parent.sy.messagerAlert('提示信息', '同一时间只能更新一条记录！', 'error');
		} else {
			parent.sy.messagerAlert('提示信息', '请选择要更新的记录！', 'error');
		}
	}

	//詳細信息
	function read() {
		var rows = mydatagrid.datagrid('getSelections');
		//编辑时只能单选一行进行查看明细信息
		if (rows.length == 1) {
			var p = parent.sy.dialog({
						title : '编辑退学登记信息',
						href : '${pageContext.request.contextPath}/demo/quitschoolAction!toQuitschoolDetailPage.action',
						width : 1024,
						height : 580,
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
							f.form('load', {
								id:rows[0].id,
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
								
								quitDate:rows[0].quitDate,
								debitFee:rows[0].debitFee,
								quitFee:rows[0].quitFee,
								quitReason:rows[0].quitReason,
								comment:rows[0].comment,
							});
							
							//将学员基本信息属性襟止修改
							var inputDisable=f.find('input[class=demo_quitschool_detail]');
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
							
						}
					});
		} else if (rows.length > 1) {
			parent.sy.messagerAlert('提示信息', '同一时间只能查看一条记录！', 'question');
		} else {
			parent.sy.messagerAlert('提示信息', '请选择要查看的记录！', 'question');
		}
	}

	//添加用户
	function append() {
		var addDialog=undefined;
		addDialog= parent.sy.dialog({
					title : '退学登记信息录入',
					href : '${pageContext.request.contextPath}/demo/quitschoolAction!toQuitschoolAddPage.action',
					width : 1280,
					height : 600,
					buttons : [
							 {
								text : '取消',
								iconCls : 'icon-cancel',
								handler : function() {
									addDialog.dialog('close');
								}
							} ],
							//通过onLoad函数实现数据回显
							onLoad : function() {
								var myForm=addDialog.find('form');
								var myDepartment=myForm.find('input[name=studentOrganization]');
								var myDriverType=myForm.find('input[name=driverType]');
								var myLinkButton_search=myForm.find('a[id=quitschool_a_searche]');
								var myLinkButton_cancel=myForm.find('a[id=quitschool_a_cancel]');
								selectDatagird=myForm.find('table[id=demo_quitschool_selectStudent]');
								var myExamDate=myForm.find('input[name=examDate]');
								var myArrangedNo=myForm.find('input[name=arrangedNo]');
								var disableItem=myForm.find('input[class=demo_add_disable]');
								myExamDate.datetimebox({
								    showSeconds: false
								});
								//襟用相应的input选项
								disableItem.attr('disabled',false);//TODO bug
								
								var studentNoTemp=myForm.find('input[name=studentNo]');
								searchStudentNo(studentNoTemp, userSchoolArea);
								//初始化校区选项
								initSchoolArea(myDepartment,userSchoolArea);
								//初始化考试类型选项
								initDriverType(myDriverType);
								myDriverType.combobox({
									onChange:function(){
										//根据combobox选择名称检索科目二考试学员信息
										selectDatagird.datagrid('load', sy.serializeObject(myForm));
									}
								});
								
								
								//初始考试计划选项
								var addArrangedNo=myArrangedNo.combobox({
									url : '${pageContext.request.contextPath}/demo/quitschoolAction!getAllArrangedNo.action',
									valueField : 'value',
									textField : 'text',
									required:true,
									method:'get',
									onChange:function(){
										//通过异步获取初始化所属校区的值 ---当用选择考试计划编号时初始化所属校区
										$.ajax({
											url : '${pageContext.request.contextPath}/demo/arrangedExaminationAction!getOrganizationByArrangedNo.action',
											data : {schoolArea : addArrangedNo.combobox('getValue')},
											dataType : 'json',
											async:false,
											success : function(json) {
												depCombobox.combotree('setValue',json.obj);
											}
										});
										//当用选择考试计划编号时初始化---考试业务类型
										$.ajax({
											url : '${pageContext.request.contextPath}/demo/arrangedExaminationAction!getExaminationSubjectByArrangedNo.action',
											data : {schoolArea : addArrangedNo.combobox('getValue')},
											dataType : 'json',
											async:false,
											success : function(json) {
												driverTypeCombobox.combobox('setValue',json.obj);
											}
										});
										selectDatagird.datagrid('load', sy.serializeObject(myForm)); 	
									}
								});
								
								// 展现符合科目二考试的学员信息
								selectDatagird.datagrid({
						    		url : '${pageContext.request.contextPath}/demo/quitschoolAction!getAllStudentByState.action',
						    		title : '每一 次只能选择一个学员进行操作', /* 表头标题 */
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
									idField : 'studentId', //此属性一定要配置
									sortName : 'name', //列排序 //TODO
									sortOrder : 'desc', //列排序
									checkOnSelect : true,
									selectOnCheck : true,
									striped : true,//表示条纹，隔行显示不现背景色
									nowrap:true,//单元显示
									singleSelect:true,//只能选择单行
									//冻结列
									frozenColumns : [ [ {
											title : '编号',
											field : 'studentId',
											width : 20,
											sortable : true,
											checkbox : true
										}, {
											title : '<font color="#0099FF">所属校区</font>',
											field : 'schoolAreaName',
											width : 100,
											align : 'left'
										},{
											title : '<font color="#0099FF">姓名</font>',
											field : 'studentName',
											width : 80,
											align : 'left'
										},{
											title : '<font color="#0099FF">学员编号</font>',
											field : 'studentNo',
											width : 90,
											align : 'center',
											formatter:function(value,row,index){
												return formatStudentNo(value, row, index);
											},
										},{
											title : '<font color="#0099FF">业务类型</font>',
											field : 'studentDriverType',
											width : 70,
											align : 'center',
											formatter:function(value,row,index){
												return formatterDriverType(value, row, index);
											}
										}] ],
										//普通列
										columns : [ [ {
											title : '<font color="#0099FF">性别</font>',
											field : 'studentSex',
											width : 60, //在datagrid中一定要添加列宽度，否则会加长datagrid渲染的时间，消耗系统资源
											align : 'center',
										}, {
											title : '<font color="#0099FF">身份证号</font>',
											field : 'studentIdentityId',
											width : 150
										},{
											title : '<font color="#0099FF">联系电话</font>',
											field : 'studentPhone',
											width : 150
										},{
											title : '<font color="#0099FF">报名日期</font>',
											field : 'studentCreateTime',
											width : 90,
											align : 'center',
											formatter:formatTimeYMD,
										}
						    		]],
						    		toolbar : [ 
						    		    {
						    				text : '下一步',
											iconCls : 'icon-add',
											handler : function() {
												var rows=selectDatagird.datagrid('getChecked');
												 if(rows.length>0){
													 addQuitRecord(rows);
													 addDialog.dialog('close');
												}else{
													parent.sy.messagerAlert('提示信息', '您当前没有选择学员信息！', 'error');
												}
											}
										}, {
						    				text : '取消选中',
											iconCls : 'icon-undo',
											handler : function() {
												var rows=selectDatagird.datagrid('getChecked');
												if(rows.length<=0){
													parent.sy.messagerAlert('提示信息', '您当前没有选中行信息！', 'error');
												}
												//取消所有的选中行
												selectDatagird.datagrid('clearSelections');
												//取消当前页面的选中行
												selectDatagird.datagrid('unselectAll');
											}
										},
										
										'-'],
						    		onLoadSuccess : function () {
										//修改序号bug
										selectDatagird.datagrid("fixRownumber");
									},
									
						    	});
								//查询按钮事件
								var linkButton=myLinkButton_search.linkbutton({
								    iconCls: 'icon-search',
								    onClick:function(){
								    	selectDatagird.datagrid('load', sy.serializeObject(myForm)); 	
								    	
								    }
								});
								//取消按钮事件
								var linkButton=myLinkButton_cancel.linkbutton({
								    iconCls: 'icon-cancel',
								    onClick:function(){
								    	//将数据还原显示到点击查询按钮前的数据
								    	selectDatagird.datagrid('load', {});
								    	myForm.find('input').val('');
								    }
								});
							},
				});
		
	}
	
	//到达添加退学记录dialog
	function addQuitRecord(rows){
		var addInputDialog=undefined;
		addInputDialog=parent.sy.dialog({
			
			title : '退学登记信息录入',
			href : '${pageContext.request.contextPath}/demo/quitschoolAction!toQuitschoolAddInputPage.action',
			width : 1024,
			height : 600,
			buttons : [
				{
					text : '保存',
					iconCls : 'icon-edit',
					handler : function() {
						var btnAdd = this;
						$(btnAdd).hide();
						f=addInputDialog.find('form');
						f.form('submit',{
							url : '${pageContext.request.contextPath}/demo/quitschoolAction!add.action',
							success : function(d) {
								$(btnAdd).show();
								var json = $.parseJSON(d);
								if (json.success) {
									mydatagrid.datagrid('insertRow',
													{
														index : 0, // index start with 0
														row : json.obj
													});
									addInputDialog.dialog('close');
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
				}, 
				{
					text : '上一步',
					iconCls : 'icon-redo',
					handler : function() {
						addInputDialog.dialog('close');
						append();
					}
				},
			  {
				text : '关闭',
				iconCls : 'icon-cancel',
				handler : function() {
					addInputDialog.dialog('close');
				}
			}
			  ],
			//通过onLoad函数实现数据回显
			onLoad : function() {
				//初始化添加页面中的选择参数信息（申请类型，驾照类型）
				var f = addInputDialog.find('form');
				
				var disableItems=f.find('input[class=demo_quitschool_update]');
				disableItems.attr('disabled',true);
				//初始化申请类型选项
				var applyType=f.find('input[name=studentApplyType]');
				applyType.combobox({
					url : '${pageContext.request.contextPath}/demo/json/applyType_combobox.json',
					valueField : 'value',
					textField : 'text',
					required:true,
					method:'get',
				});
				//初始化驾照类型选项
				var driverType=f.find('input[name=studentDriverType]');
				driverType.combobox({
					url : '${pageContext.request.contextPath}/demo/json/driverType_combobox.json',
					valueField : 'value',
					textField : 'text',
					required:true,
					method:'get',
				});
				
				//学员状态初始化
				var stuStateInit=f.find('input[name=studentNowstate]');
				stuStateInit.combobox({
					url : '${pageContext.request.contextPath}/demo/json/studentstate_combobox.json',
					valueField : 'value',
					textField : 'text',
					required:true,
					width:155,
					method:'get',
				});
				
				//初始化学员信息
				f.form('load', {
					studentId:rows[0].studentId,
					schoolAreaName : rows[0].schoolAreaName,
					studentDriverType:rows[0].studentDriverType,
					studentNo :rows[0].studentNo,
					studentCreateTime : rows[0].studentCreateTime,
					studentName : rows[0].studentName,
					studentIdentityId :rows[0].studentIdentityId,
					studentSex:rows[0].studentSex,
					studentAddress :rows[0].studentAddress,
					studentPhone:rows[0].studentPhone,
					studentFeed :rows[0].studentFeed,
					studentRealFeed :rows[0].studentRealFeed,
					studentOwnFeed :rows[0].studentOwnFeed,
					
					studentTimingFlag :rows[0].studentTimingFlag,
					studentLearningTime :rows[0].studentLearningTime,
					studentEmail :rows[0].studentEmail,
					studentType :rows[0].studentType,
					studentResidenceId :rows[0].studentResidenceId,
					studentResidenceAddr :rows[0].studentResidenceAddr,
					studentApplyType :rows[0].studentApplyType,
					studentNowState :rows[0].studentNowState,
					studentImageId :rows[0].studentImageId,
					studentBirthdate :rows[0].studentBirthdate,
					studentPassword :rows[0].studentPassword,
					trainerName :rows[0].trainerName,
					
					
					
				});
			},
		});
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
										url : '${pageContext.request.contextPath}/demo/quitschoolAction!delete.action',
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
		searchStudentNo($("#search_studentNo"), userSchoolArea);
		mydatagrid.datagrid('load', sy.serializeObject($('#demo_quitschool_searchForm')));
		$("#search_studentNo").val('');
	}

	//取消查询功能按钮
	function cleanSearch() {
		mydatagrid.datagrid('load', {});
		//清空与查询有关的输入框
		$('#demo_quitschool_searchForm input').val('');
	}

</script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询功能'" style="height: 110px;overflow: hidden;" align="left">
		<!-- 人员信息查询表单 -->
		<form id="demo_quitschool_searchForm">
			<table class="tableForm " style="width: 100%;height: 100%;">
				<tr>
				<th style="width: 70px;">所属校区:</th>
				<td><input id="demo_quitschool_organization" class="keypress_event" name="schoolArea" style="width: 155px;"/></td>
				<th style="width: 70px;">编号:</th>
				<td><input id="search_studentNo" class="keypress_event" name="studentNo" style="width: 155px;"/></td>
				<th style="width: 65px;">姓名:</th>
				<td><input id="demo_theory_name" class="keypress_event" name="studentName"  style="width: 155px;" /></td>
				<th style="width: 70px;">身份证号:</th>
				<td><input class="keypress_event" name="studentIdentityId"  style="width: 155px;" /></td>
			</tr>
			<tr>
				<th style="width: 70px;">退学日期</th>
				<td>
					<input name="quitDateStart" class="easyui-datetimebox" data-options="showSeconds:false,editable:false"
					 style="width: 155px;" />
				</td>
				<th>至</th>
				<td>
					<input name="quitDateEnd" class="easyui-datetimebox" data-options="showSeconds:false,editable:false"
					 style="width: 155px;" />
				</td>
				<td colspan="2">
						<a href="javascript:void(0);" id="quitschool_a_searche" class="easyui-linkbutton" iconCls="icon-search" onclick="mySearch();">查询</a>
						<a href="javascript:void(0);" id="quitschool_a_canel" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cleanSearch();">取消</a>
				</td>
			</tr>
			</table>
		</form>
	</div>
	<!-- datagrid数据在center中展现 -->
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<table id="demo_quitschool_datagrid"></table>
	</div>
	<!-- 操作按钮事件 -->
	<div id="demo_quitschool_menu" class="easyui-menu" style="width:120px;display: none;">
		<div id="add-menu" onclick="append();" data-options="iconCls:'icon-add'">增加</div>
		<div id="del-menu" onclick="remove();" data-options="iconCls:'icon-no'">删除</div>
		<div id="edit-menu" onclick="edit();" data-options="iconCls:'icon-edit'">更新</div>
		<div id="detail-menu" onclick="read();" data-options="iconCls:'icon-filter'">详细信息</div>
	</div>
</body>
</html>
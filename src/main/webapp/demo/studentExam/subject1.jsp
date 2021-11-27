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
	var studentExamDiaglog=undefined;
	var studentExamDatagrid=undefined;
	var addStudentExamDialog=undefined;
	var addStudentDialog=undefined;
	var studentSelectDatagrid=undefined;
	var moveStudentDatagrid=undefined;
	var moveStudentDialog=undefined;
	var batchFlag=undefined;//	标记用户操作批量修改成绩，批量报名科目考试
	var selectSubjectDialog=undefined;
	var cancelHandupDialog=undefined;
	var userSchoolArea=undefined;
	var userACLs=[];
	$(function() {
		userSchoolArea=getUserSchoolArea();
		mydatagrid = $('#demo_examAnalys_datagrid');
		getUserACL();

	});
	
	//按批次添加考试学员信息(明细信息)
	function appendExamStudent(){
		var rows = mydatagrid.datagrid('getSelections');
		//编辑时只能单选一行进行编辑
		if (rows.length == 1) {
			addStudentExamDialog = parent.sy.dialog({
						title : '科目一学员考试信息',
						href : '${pageContext.request.contextPath}/demo/examAnalysAction!toAddStudentExamPage.action',
						width : 1280,
						height : 730,
						zIndex:-1,
						buttons : [
								{
									text : '关闭',
									iconCls : 'icon-cancel',
									handler : function() {
										mydatagrid.datagrid('reload');
										mydatagrid.datagrid('unselectAll');
										mydatagrid.datagrid('uncheckAll');
										addStudentExamDialog.dialog('close');
									}
								} ],
						//数据回显显示
						onLoad : function() {
							var f = addStudentExamDialog.find('form');
							//初始化校区
							var schoolAreaTemp=f.find('input[name=schoolArea]');
							initSchoolArea(schoolAreaTemp,rows[0].schoolArea);
							//初始批次
							var batchTemp=f.find('input[name=batch]');
							var retVal="第【"+rows[0].batch+"】期";
							batchTemp.val(retVal);
							batchTemp.css("color","red");
							//初始化创建时间
							var createDateTemp=f.find('input[name=createDate]');
							createDateTemp.datebox({
								showSeconds:false,
								realonly:true,
							});
							createDateTemp.datebox('setValue',rows[0].createDate);
							//初始化总数
							var totalTemp=f.find('input[name=total]');
							totalTemp.val(rows[0].total);
							//可不修改选项
							var disabledItems=f.find('input[class=examAnalys_add_readonly]');
							disabledItems.attr('readonly',true);
							
							//查询按钮事件
							var studentNameTemp=f.find('input[name=studentName]');
							var studentNoTemp=f.find('input[name=studentNo]');
							var studentIdentityIdTemp=f.find('input[name=studentIdentityId]');
							var btnSearch=f.find('a[id=studentExam_a_searche]');
							btnSearch.linkbutton({
							    iconCls: 'icon-search',
							    onClick:function(){
							    	studentExamDatagrid.datagrid('load', {
							    		schoolArea:schoolAreaTemp.combobox('getValue'),
							    		studentName:studentNameTemp.val(),
							    		studentNo:studentNoTemp.val(),
							    		studentIdentityId:studentIdentityIdTemp.val(),
							    	}); 	
							    }
							});
							//回车查询功能
							var btnEnter=f.find('input[class=keypress_event]');
							btnEnter.unbind('keypress');
							btnEnter.bind('keypress',function(event){
					            if(event.keyCode == "13")    
					            {
					            	studentExamDatagrid.datagrid('load', {
							    		schoolArea:schoolAreaTemp.combobox('getValue'),
							    		studentName:studentNameTemp.val(),
							    		studentNo:studentNoTemp.val(),
							    		studentIdentityId:studentIdentityIdTemp.val(),	
							    	}); 
					            	studentNoTemp.val('');
					            	studentNameTemp.val('');
					            	studentIdentityIdTemp.val('');
					            }
					        });
							
							
							//重置按钮功能事件
							var btnReset=f.find('a[id=studentExam_a_canel]');
							btnReset.linkbutton({
							    iconCls: 'icon-redo',
							    onClick:function(){
							    	//将数据还原显示到点击查询按钮前的数据
							    	studentExamDatagrid.datagrid('load', {batch:rows[0].batch});
							    	studentNoTemp.val('');
							    	studentNameTemp.val('');
							    	studentIdentityIdTemp.val('');
							    }
							});
							
							//初始该批次考试学员信息
							initStudentExamDatagrid(rows[0].id,rows[0].batch,addStudentExamDialog,rows[0].schoolArea);
						}
					});
		} else if (rows.length > 1) {
			parent.sy.messagerAlert('提示信息', '同一时间只能操作一条记录！', 'question');
		} else {
			parent.sy.messagerAlert('提示信息', '请选择批次！', 'question');
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
	
	//初始化考试学员信息（明细信息datagrid）
	function initStudentExamDatagrid(examAnalysId,batch,addStudentExamDialog,schoolArea){
		var f=addStudentExamDialog.find('form');
		studentExamDatagrid=f.find('table[id=demo_studentExam_datagrid]');
		studentExamDatagrid.datagrid({
			url : '${pageContext.request.contextPath}/demo/studentExamAction!datagrid.action',
			queryParams:{batch:batch,subjectType:1},
			iconCls : 'icon-save',
			pagination : true, 
			pagePosition : 'bottom', 
			pageSize : 20, 
			pageList : [ 10, 20, 30, 40 ,50,100,200], 
			fit : true,
			fitColumns : false, 
			nowrap : false, 
			border : false,
			singleSelect:false,
			rownumbers: true,
			idField : 'id', 
			sortName : 'importOrder', 
			sortOrder : 'asc', 
			checkOnSelect : true,
			selectOnCheck : true,
			striped : true,
			nowrap:true,
			method:'get',
			frozenColumns : [ [ {
				title : '编号',
				field : 'id',
				width : 20,
				checkbox : true
			},{
				title : '<font color="#0099FF">学员编号</font>',
				field : 'studentNo',
				width : 90,
				align : 'center',
				formatter:formatStudentNo,
			},{
				title : '<font color="#0099FF">姓名</font>',
				field : 'studentName',
				width : 80,
				align : 'center',
				formatter:showAllMsg,
			},] ],
			//普通列
			columns : [ [{
				title : '<font color="#0099FF">性别</font>',
				field : 'studentSex',
				width : 50,
				align : 'center',
				formatter:showAllMsg,
			},{
				title : '<font color="#0099FF">身份证号</font>',
				field : 'studentIdentityId',
				width : 155,
				align : 'center',
				formatter:showAllMsg,
			}, {
				title : '<font color="#0099FF">报考车型</font>',
				field : 'studentDriverType',
				width : 60,
				align : 'center',
				formatter:formatterDriverType,
			},{
				title : '<font color="#0099FF">原有车型</font>',
				field : 'primaryDriver',
				width : 60,
				align : 'center',
				formatter:showAllMsg,
			},{
				title : '<font color="#0099FF">地址</font>',
				field : 'studentAddress',
				width : 260,
				formatter:showAllMsg,
			},{
				title : '<font color="#0099FF">编号</font>',
				field : 'otherNo',
				width : 80,
				align : 'center',
				formatter:showAllMsg,
			},{
				title : '<font color="#0099FF">手机</font>',
				field : 'examPhone',
				width : 100 ,
				align:'center',
			},{
				title : '<font color="#0099FF">报名日期</font>',
				field : 'studentCreateTime',
				width : 100 ,
				align:'center',
				formatter:formatTimeYMD,
			}, {
				title : '<font color="#FF7F00">报考科一</font>',
				field : 'sub1ExamFlag',
				width : 70 ,
				align:'center',
				formatter:formatSubjectExamFalg,
			},{
				title : '<font color="#FF7F00">交警审批</font>',
				field : 'sub1ApplyFlag',
				width : 70 ,
				align:'center',
				formatter:formatApplyFlag,
			},{
				title : '<font color="#FF7F00">科一日期</font>',
				field : 'sub1ExamDate',
				width : 120,
				align:'center',
				formatter:formatTimeYMDHM,
			}, {
				title : '<font color="#FF7F00">科一成绩</font>',
				field : 'sub1Score',
				width : 70 ,
				align:'center',
				formatter:formatStudentExamScore,
			},{
				title : '<font color="#FF7F00">补考日期</font>',
				field : 'sub1MakeupDate',
				width : 120,
				align:'center',
				formatter:formatTimeYMDHM,
			}, {
				title : '<font color="#FF7F00">补考成绩</font>',
				field : 'sub1MakeupScore',
				width : 70 ,
				align:'center',
				formatter:formatStudentExamScore,
			},{
				title : '<font color="#FF7F00">补考次数</font>',
				field : 'sub1MakeupTimes',
				width : 70 ,
				align:'center',
			},{
				title : '<font color="#FF7F00">缺考</font>',
				field : 'sub1MissExamFlag',
				width : 70 ,
				align:'center',
				formatter:formatMissExam,
			},{
				title : '<font color="#0099FF">schoolArea</font>',
				field : 'schoolArea',
				width : 60 ,
				align:'center',
			}, {
				title : '<font color="#0099FF">studentId</font>',
				field : 'studentId',
				width : 60 ,
				align:'center',
			},]],
			toolbar:studentExamToolbar,
			onBeforeLoad:onMyBeforeLoad,
			onLoadSuccess:studentExamLoadSuccess,
			
		});
	}
	//学员考试明细datagrid加载成功后调用
	function studentExamLoadSuccess(data){
		studentExamDatagrid.datagrid("fixRownumber");
		studentExamDatagrid.datagrid('hideColumn','schoolArea');
		studentExamDatagrid.datagrid('hideColumn','studentId');
		//datagrid没有数据时显示没有记录信息
		if(data.total==0){
			studentExamDatagrid.datagrid('appendRow', {
				schoolAreaName:  '<div style=color:red>没有记录信息！</div>' })
			.datagrid('mergeCells', { index: 0, field: 'schoolAreaName', colspan:4 });
		}
		var f=addStudentExamDialog.find('form');
		//动态显示当前批次总人数
		var totalTemp=f.find('input[name=total]');
		totalTemp.val(data.total);
		totalTemp.css('color','red');
	}
	
	
	//刷新考试学员明细datagrid
	function reloadStudentExam(){
	 	var rows=mydatagrid.datagrid('getChecked');
		studentExamDatagrid.datagrid('load',{batch:rows[0].batch});
		//取消所有的选中行
		studentExamDatagrid.datagrid('clearSelections');
		//取消当前页面的选中行
		studentExamDatagrid.datagrid('unselectAll');
		studentExamDatagrid.datagrid('uncheckAll');
	}
	
	//取消选中考试学员明细datagrid
	function cancelSelect(){
		//取消所有的选中行
		studentExamDatagrid.datagrid('clearSelections');
		//取消当前页面的选中行
		studentExamDatagrid.datagrid('unselectAll');
		studentExamDatagrid.datagrid('uncheckAll');
	}
	
	//导入考试学员明细信息
	function importStudentExam(){
		var rows=mydatagrid.datagrid('getChecked');
		var importDialog=undefined;
		 importDialog = parent.sy.dialog({
			title : '导入考试学员明细信息',
			href : '${pageContext.request.contextPath}/demo/studentExamAction!toUploadPage.action',
			width : 560,
			height : 160,
			closable: true, 
			buttons : [
					{
						text : '导入',
						iconCls : 'icon-add',
						handler : function() {
							$.messager.progress({
								text : '正在导入数据，请稍后....',
								interval : 100
							});
							importDialog.hide();
							
							var f = importDialog.find('form');
							//提交表单
							f.form('submit',{
								//提交添加用户表单时，调用UserAction的add方法
								url : '${pageContext.request.contextPath}/demo/studentExamAction!importExamStudentDatas.action?',
								onSubmit: function(param){    
							        param.studentBatch = rows.batch;    
							    },    		
								success : function(d) {
									var json = $.parseJSON(d);
									importDialog.dialog('close');
									if(d){
										$.messager.progress({
											text : '正在导入中...，请稍后',
											interval : 50
										});
										window.setTimeout(function() {
											try {
												$.messager.progress('close');
												parent.sy.messagerShow({
													msg : json.msg,
													title : '提示信息'
												});
												studentExamDatagrid.datagrid('reload');
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
				
		});
	}
	
	function disableStudentExmaButton(){
		
		$("#studentExam-btn-add").linkbutton('disable');
		$("#studentExam-btn-edit").linkbutton('disable');
		$("#studentExam-btn-remove").linkbutton('disable');
		$("#studentExam-btn-move").linkbutton('disable');
		$("#studentExam-btn-import").linkbutton('disable');
	}
	
	function enableStudentExmaButton(){
		$("#studentExam-btn-add").linkbutton('enable');
		$("#studentExam-btn-edit").linkbutton('enable');
		$("#studentExam-btn-remove").linkbutton('enable');
		$("#studentExam-btn-move").linkbutton('enable');
		$("#studentExam-btn-import").linkbutton('enable');
	}
	
	
	
	//添加考试批准基本信息
	function append() {
		var addDialog=undefined;
		addDialog = parent.sy.dialog({
					title : '按批次添加学员考试信息',
					href : '${pageContext.request.contextPath}/demo/examAnalysAction!toAddExamAnalysPage.action',
					width : 1024,
					height : 520,
					buttons : [{
								text : '添加',
								iconCls : 'icon-add',
								handler : function() {
									var f = addDialog.find('form');
											f.form('submit',{
												//提交添加用户表单时，调用UserAction的add方法
											url : '${pageContext.request.contextPath}/demo/examAnalysAction!add.action',
											success : function(d) {
													//对后台传递过来的JSON格式数据进行解析
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
						var f = addDialog.find('form');
						//初始化校区
						var org=f.find('input[name=schoolArea]');
						initSchoolArea(org, userSchoolArea);
						//创建日期初始化
						var createDateTemp=f.find('input[name=createDate]');
						createDateTemp.datebox({
							showSeconds:false,
						});
						createDateTemp.datebox('setValue',new Date().toLocaleDateString());
						//从后台获取最大学员编号
						var batchTemp=f.find('input[name=batch]');
						$.ajax({
							url : '${pageContext.request.contextPath}/demo/examAnalysAction!doNotNeedSession_getMaxBatchByschoolArea.action',
							data:{selectSchoolArea:org.combobox('getValue')},
							dataType : 'json',
							success : function(d) {
								if(d){
									batchTemp.val(d);
								}
							}
						});
						batchTemp.attr('readonly',true);
					}//end onLoad
				});//end dialog
	}
	
	function reload(){
		mydatagrid.datagrid('reload');
		//取消所有的选中行
		mydatagrid.datagrid('clearSelections');
		//取消当前页面的选中行
		mydatagrid.datagrid('unselectAll');
		mydatagrid.datagrid('uncheckAll');
		$("#examAnalys-btn-add").linkbutton('enable');
		$("#examAnalys-btn-edit").linkbutton('enable');
		$("#examAnalys-btn-remove").linkbutton('enable');
		$("#examAnalys-btn-detail").linkbutton('enable');
	}
	//学员考试列表
	function onLoadSuccess(data) {
		//修改序号bug
		$(this).datagrid("fixRownumber");
		$(this).datagrid('hideColumn','schoolArea');
		//初始化查询相关属性选项值
		//初始化校区选项
		initSchoolArea($("#demo_trainerreservation_organization"),userSchoolArea);
		//添加回车事件
		bindingKeypressEvent($(".keypress_event"),mydatagrid,$("#demo_trainerreservation_searchForm"));
		//datagrid没有数据时显示没有记录信息
		if(data.total==0){
			mydatagrid.datagrid('appendRow', {
				schoolAreaName: '<div style="text-align:center;color:red">没有记录信息！</div>' })
			.datagrid('mergeCells', { index: 0, field: 'schoolAreaName', colspan:4});
		}
		$("#sub1font0").css('color','#FF7F00');
		$("#sub1font1").css('color','#FF7F00');
		$("#sub1font2").css('color','#FF7F00');
		$("#sub1font3").css('color','#FF7F00');
		$("#sub1font4").css('color','#FF7F00');
		$("#sub1font5").css('color','#FF7F00');
		$("#sub1font6").css('color','#FF7F00');
		$("#sub2font0").css('color','#0000CD');
		$("#sub2font1").css('color','#0000CD');
		$("#sub2font2").css('color','#0000CD');
		$("#sub2font3").css('color','#0000CD');
		$("#sub2font4").css('color','#0000CD');
		$("#sub2font5").css('color','#0000CD');
		$("#sub2font6").css('color','#0000CD');
		$("#sub3font0").css('color','#A020F0');
		$("#sub3font1").css('color','#A020F0');
		$("#sub3font2").css('color','#A020F0');
		$("#sub3font3").css('color','#A020F0');
		$("#sub3font4").css('color','#A020F0');
		$("#sub3font5").css('color','#A020F0');
		$("#sub3font6").css('color','#A020F0');
		$("#sub4font0").css('color','#006400');
		$("#sub4font1").css('color','#006400');
		$("#sub4font2").css('color','#006400');
		$("#sub4font3").css('color','#006400');
		$("#sub4font4").css('color','#006400');
		$("#sub4font5").css('color','#006400');
		$("#sub4font6").css('color','#006400');
		$("#sub5font0").css('color','#FF7F00');
		$("#sub5font1").css('color','#FF7F00');
		$("#sub5font2").css('color','#FF7F00');
		$("#sub5font3").css('color','#FF7F00');
		$("#sub5font4").css('color','#FF7F00');
		$("#sub5font5").css('color','#FF7F00');
		$("#sub5font6").css('color','#FF7F00');
		
	}
	
	//查询按钮功能函数
	function mySearch() {
		mydatagrid.datagrid('load', sy.serializeObject($('#demo_examAnalys_searchform')));
	}

	//取消查询功能按钮
	function cleanSearch() {
		mydatagrid.datagrid('load', {});
		//清空与查询有关的输入框
		$('#demo_examAnalys_searchform input').val('');
	}
	
	//格式化显示不排班时间段落
	function formatNoArrange(value,row,index){
		if(value=="NO"){
			return '<span style="color:red;">'+'休息 '+'</span>';
		}else{
			return value;
		}
	}

	var studentExamToolbar=[{
				id:'studentExam-btn-export',
				text:'导出',
				iconCls:'icon-ok',
				handler:function(){
					exportDatas();
				} 
			},'-',{
				id:'studentExam-btn-import',
				text:'成绩导入',
				iconCls:'icon-redo',
				handler:function(){
					importScores();
				} 
			},'-',{
				id:'studentExam-btn-sms',
				text:'短信通知',
				iconCls:'icon-ok',
				handler:function(){
					smsNotice();
				} 
			},'-',{
				id:'studentExam-btn-wechat',
				text:'微信通知',
				iconCls:'icon-more',
				handler:function(){
					weChatNotice();
				} 
			},'-',{
				id:'studentExam-btn-cancel',
				text:'取消选中',
				iconCls:'icon-undo',
				handler:function(){
					cancelSelect();
				} 
			},'-',{
				id:'studentExam-btn-reload',
				text:'刷新',
				iconCls:'icon-reload',
				handler:function(){
					reloadStudentExam();
				} 
			},'-'];
	
	function onMyBeforeLoad(){
		var btnImport=addStudentExamDialog.find('a[id=studentExam-btn-import]');
		var btnExport=addStudentExamDialog.find('a[id=studentExam-btn-export]');
		var btnSms=addStudentExamDialog.find('a[id=studentExam-btn-sms]');
		var btnWechat=addStudentExamDialog.find('a[id=studentExam-btn-wechat]');
		if(!userACLs[2]){//Update
			btnImport.linkbutton('disable');
			btnExport.linkbutton('disable');
			btnSms.linkbutton('disable');
			btnWechat.linkbutton('disable');
		}
	}
	//微信通知学员考试
	function weChatNotice(){
		var rows = studentExamDatagrid.datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			parent.sy.messagerConfirm(
							'请确认',
							'您确定通过微信的方式通知学员吗，如果是请选择《是》',
							function(r) {
								if (r) {
									for (var i = 0; i < rows.length; i++) {
										ids.push(rows[i].studentId);
									}
									$.ajax({
										url : '${pageContext.request.contextPath}/demo/smsAction!sendMsgByWechat.action',
												data : {
													ids : ids.join(','),
													subjectType:1,
													date:rows[0].sub1ExamDate,
												},
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
			parent.sy.messagerAlert('提示信息', '请勾选要发送信息！', 'question');
		}
	}
	//短信通知学员考试		
	function smsNotice(){
		var rows = studentExamDatagrid.datagrid('getChecked');
			var ids = [];
			if (rows.length > 0) {
				parent.sy.messagerConfirm(
								'请确认',
								'发短信通知学员将会产生费用，您确定发送信息吗，如果是请选择《是》',
								function(r) {
									if (r) {
										for (var i = 0; i < rows.length; i++) {
											ids.push(rows[i].studentId);
										}
										$.ajax({
											url : '${pageContext.request.contextPath}/demo/smsAction!add.action',
													data : {
														ids : ids.join(','),
														subjectType:1,
														date:rows[0].sub1ExamDate,
													},
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
				parent.sy.messagerAlert('提示信息', '请勾选要发送信息！', 'question');
			}
	}	
			
	
	var toolbarSubject=[ { 
		id:'examAnalys-btn-detail',
		text : '明细信息',
		iconCls : 'icon-add',
		handler : function() {
			appendExamStudent();
		}
	}, '-',{
		id:'examAnalys-btn-reload',
		text : '刷新',
		iconCls : 'icon-reload',
		handler : function() {
			reload();
		}
	}, '-' ];
	
	//导入成绩
	function importScores(){
		var rows=mydatagrid.datagrid('getChecked');
		var importDialog=undefined;
		 importDialog = parent.sy.dialog({
			title : '导入考试学员成绩',
			href : '${pageContext.request.contextPath}/demo/studentExamAction!toUploadPage.action',
			width : 560,
			height : 160,
			closable: true, 
			buttons : [{
				id:'btn_import_ok',
				text : '导入',
				iconCls : 'icon-add',
				handler : function() {
					var btnAdd = this;
					var f = importDialog.find('form');
					var uploadfile=f.find('input[name=uploadfile]');
					if(uploadfile.val()!=null&&uploadfile.val()!=""&&uploadfile.val()!=undefined){
						console.info(uploadfile.val());
						$(btnAdd).hide();
					}else{
						parent.sy.messagerAlert('提示信息', '请选择要导入的文件', 'question');
						return;
					}
					//提交表单
					f.form('submit',{
						//提交添加用户表单时，调用UserAction的add方法
						url : '${pageContext.request.contextPath}/demo/studentExamAction!importExamScores.action?',
						onSubmit: function(param){    
					        param.examAnalysId = rows[0].id;
					        param.subjectType=1;
					        $.messager.progress({
								text : '正在导入数据，请稍后....',
								interval : 100
							});
					    },    		
						success : function(d) {
							var json = $.parseJSON(d);
							
							if(d){
									window.setTimeout(function() {
									try {
										$.messager.progress('close');
										parent.sy.messagerAlert('提示信息', json.msg, 'question');
										studentExamDatagrid.datagrid('reload');
										importDialog.dialog('close');
									} catch (e) {
									}
								}, 5000);
							} 
							
						}
						
					});
				}
			}, {
				id:'btn_import_cancel',
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					importDialog.dialog('close');
				}
		}],
				
		});
	}
	
	//导出函数
	function exportDatas() {
		var rows = studentExamDatagrid.datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			parent.sy.messagerConfirm('请确认','您要导出当前所选择项信息？',
					function(r) {
								if (r) {
									for (var i = 0; i < rows.length; i++) {
										ids.push(rows[i].studentId);
									}
									$("#idsValues").val(ids);
									
									$('#demo_exprot_form').form({    
									    url:'${pageContext.request.contextPath}/demo/studentExamAction!toExportPage.action',    
									    onSubmit: function(param){    
									    	param.datagridSubjectType=1;
									    	param.schoolArea=rows[0].schoolArea;
	 									    },    
									    success:function(data){    
									        alert(data);    
									    }    
									});    
									$('#demo_exprot_form').submit(); 
								}
			});
		} else {
			parent.sy.messagerAlert('提示信息', '请勾选要导出的记录！', 'error');
		}
	}
	
</script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询功能'" style="height: 90px;overflow: hidden;" align="left">
		<!-- 学员考试 -->
		<form id="demo_examAnalys_searchform">
			<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
			<tr>
				<th style="width: 80px;">所属校区:</th>
				<td><input id="demo_trainerreservation_organization" name="schoolArea" style="width: 155px;"/></td>
				<th style="width:80px;">批次:</th>
				<td><input  name="batch" class="keypress_event" style="width: 155px;"/></td>
			</tr>
				<tr>
					<td colspan="2"></td>
					<td colspan="2">
						<a href="javascript:void(0);" id="examAnalys_a_searche" class="easyui-linkbutton" iconCls="icon-search" onclick="mySearch();">查询</a>
						<a href="javascript:void(0);" id="examAnalys_a_canel" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cleanSearch();">取消</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<!-- datagrid数据在center中展现 -->
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<!-- <table id="demo_examAnalys_datagrid"></table> -->
		<table id="demo_examAnalys_datagrid" class="easyui-datagrid" title="学员考试信息列表" 
				data-options="
				url : '${pageContext.request.contextPath}/demo/examAnalysAction!datagrid.action',
				title : '《科目一》学员考试信息管理列表!',
				iconCls : 'icon-save', 
				pagination : true, 
				pagePosition : 'bottom', 
				pageSize : 20, 
				pageList : [ 10, 20, 30, 40 ],
				fit : true,
				fitColumns : false, 
				nowrap : false, 
				border : false,
				rownumbers: true,
				idField : 'id', 
				sortName : 'batch',
				sortOrder : 'desc', 
				checkOnSelect : true,
				selectOnCheck : true,
				striped : true,
				nowrap:true,
				singleSelect:true,
				showFooter:true,
				onLoadSuccess : onLoadSuccess,
				toolbar : toolbarSubject,
				onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow', rowIndex);
				$('#demo_examAnalys_subject_menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
				},
				">
				<!-- 行编辑时冻结列 -->
			<thead >
				<tr >
					<th data-options="field:'id',width:100,align:'left',checkbox:true," rowspan="2">编号</th>
					<th data-options="field:'schoolAreaName',width:100,align:'left'," rowspan="2">所属校区</th>
					<th data-options="field:'batch',width:80,align:'center',formatter:formatBatch,sortable:'true'," rowspan="2">批次</th>
					<th data-options="field:'createDate',width:100,align:'center',sortable:'true',formatter:formatTimeYMD" rowspan="2">创建日期</th>
					<th data-options="field:'total',width:70,align:'center'," rowspan="2">总人数</th>
					<th colspan="6" id="sub1font0">科目一</th>
 				</tr>
 				<tr>	
 					<th data-options="field:'subject1ExamDate',width:100,align:'center',editor:'datebox',formatter:formatTimeYMD" id="sub1font1">考试日期</th>
					<th data-options="field:'subject1HandupTotal',width:80,align:'center'," id="sub1font2">报名人数</th>
					<th data-options="field:'subject1ApproveTotal',width:80,align:'center'," id="sub1font3">审批人数</th>
					<th data-options="field:'subject1RealExamTotal',width:80,align:'center'," id="sub1font4">实考人数</th>
					<th data-options="field:'subject1MissExamTotal',width:80,align:'center'," id="sub1font5">缺考人数</th>
					<th data-options="field:'subject1PassExamTotal',width:80,align:'center'," id="sub1font6">合格人数</th>
					<th data-options="field:'schoolArea',width:100,align:'center',hidden:true">校区标识</th>
					<th data-options="field:'sub1EditAbleFlag',width:100,align:'center',hidden:true">sub1EditAbleFlag</th>
					<th data-options="field:'sub2EditAbleFlag',width:100,align:'center',hidden:true">sub2EditAbleFlag</th>
					<th data-options="field:'sub3EditAbleFlag',width:100,align:'center',hidden:true">sub3EditAbleFlag</th>
					<th data-options="field:'sub4EditAbleFlag',width:100,align:'center',hidden:true">sub4EditAbleFlag</th>
					<th data-options="field:'sub5EditAbleFlag',width:100,align:'center',hidden:true">sub5EditAbleFlag</th>
				</tr>
					
			</thead>
		</table>
	</div>
	<!-- 操作按钮事件 -->
	<div id="demo_examAnalys_subject_menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="appendExamStudent();" data-options="iconCls:'icon-filter'">明细信息</div>
		<div onclick="reload();" data-options="iconCls:'icon-reload'">刷新</div>
	</div>
	<div  style="display: none;">
		<form id="demo_exprot_form" method="post">
			<input id="idsValues"  name="ids" style="width: 155px;"/>
		</form>
	</div>
</body>
</html>
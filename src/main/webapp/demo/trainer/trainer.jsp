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
	var driverTypeValue=undefined;
	var userSchoolArea=undefined;
	var userACLs=[];
	$(function() {
		userSchoolArea=getUserSchoolArea();
		getUserACL();
		mydatagrid = $('#demo_trainer_datagrid').datagrid({
			url : 'trainerAction!datagrid.action',/* 从后台读取该实体类所有数据 */
			title : '教练信息管理列表!', /* 表头标题 */
			iconCls : 'icon-save', /* 标题前边的图标 */
			pagination : true, /* 是否显示页码 */
			pagePosition : 'bottom', /* 页码显示的位置 */
			pageSize : 20, /* 每面显示的记录数 */
			pageList : [ 10, 20, 30, 40,50 ,100,200 ], /* 选择每面显示的记录数 */
			fit : true,
			fitColumns : false, //列自动调整功能
			border : false,
			rownumbers: true,
			idField : 'id', //此属性一定要配置
			sortName : 'codeNo', //列排序
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
				title : '<font color="#0099FF">教练编号</font>',
				field : 'codeNo',
				width : 80,
				sortable : true,
				align : 'center',
				formatter:formatTrainerNo,
			} ,{
				title : '<font color="#0099FF">姓名</font>',
				field : 'name',
				width : 80,
				sortable : true,
				align : 'center'
			} ,{
				title : '<font color="#0099FF">身份证</font>',
				field : 'identity',
				width : 150, 
			}
			] ],
			//普通列
			columns : [ [ {
				title : '<font color="#0099FF">手机</font>',
				field : 'phone',
				width : 100,
				align : 'center'
			}, {
				title : '<font color="#0099FF">性别</font>',
				field : 'sex',
				sortable : true,
				width : 60
			}, {
				title : '<font color="#0099FF">教练属性</font>',
				field : 'trainerType',
				sortable : true,
				width : 80,
				formatter:initTrainerType,
			},{
				title : '<font color="#0099FF">排班类型</font>',
				field : 'arrangeType',
				width : 80,
				align : 'center',
				formatter:formatArrangType,
			}, {
				title : '<font color="#0099FF">出生日期</font>',
				field : 'birthday',
				sortable : true,
				width : 90,
				align : 'center',
				formatter:formatTimeYMD,
			}, {
				title : '<font color="#0099FF">入职日期</font>',
				field : 'jionDay',
				sortable : true,
				width : 90,
				align : 'center',
				formatter:formatTimeYMD,
			},{
				title : '<font color="#0099FF">民族</font>',
				field : 'nation',
				sortable : true,
				width : 80
			}, {
				title : '<font color="#0099FF">籍贯</font>',
				field : 'contry',
				width : 100
			}, {
				title : '<font color="#0099FF">地址</font>',
				field : 'addres',
				width : 200
			}, {
				title : '<font color="#0099FF">固定电话</font>',
				field : 'telephone',
				width : 100
			}, {
				title : '<font color="#0099FF">邮箱</font>',
				field : 'email',
				width : 150
			}, {
				title : '<font color="#0099FF">驾驶证ID</font>',
				field : 'driverLicenseId',
				sortable : true,
				width : 150
			},{
				title : '<font color="#0099FF">准驾类型</font>',
				field : 'driverType',
				sortable : true,
				width : 70,
				formatter:function(value,row,index){
					driverTypeValue=value;
					return formatterDriverType(value, row, index);
				}
				
			},{
				title : '<font color="#0099FF">准教车型</font>',
				field : 'driverCarType',
				sortable : true,
				width : 180,
				formatter:function(value,row,index){
					if(driverTypeValue==1){
						return 'A1,A3,B1,B2,C1,C2,C3,C4,M';
					}else if(driverTypeValue==2){
						return 'B1,B2,C1,C2,C3,C4,M';
					}else if(driverTypeValue==3){
						return 'C1,C2,C3,C4';
					}else if(driverTypeValue==4||driverTypeValue==5){
						return 'C1,C2,C3,C4,M';
					}else if(driverTypeValue==6){
						return 'C2,C3,C4';
					}else if(driverTypeValue==8){
						return 'C4';
					}else if(driverTypeValue==10){
						return 'E,F';
					}else if(driverTypeValue==11){
						return 'F';
					}else{
						return '';
					}
				}
				
			}, {
				title : '<font color="#0099FF">教员有效期(开始)</font>',
				field : 'teachValidatyStart',
				sortable : true,
				width : 100,
				align : 'center',
				formatter:formatTimeYMD,
			}, {
				title : '<font color="#0099FF">教员有效期(结束)</font>',
				field : 'teachBalidatyEnd',
				sortable : true,
				width : 100,
				align : 'center',
				formatter:formatTimeYMD,
			},{
				title : '<font color="#0099FF">操作员</font>',
				field : 'operator',
				width : 80,
			},{
				title : '<font color="#0099FF">备注</font>',
				field : 'comment',
				width : 250
			},{
				title : '<font color="#0099FF">所属校区</font>',
				field : 'schoolArea',
				width : 100,
			},{
				title : '<font color="#0099FF">是否与车辆捆绑</font>',
				field : 'carBanding',
				sortable : true,
				width : 40,
				align : 'center'
			},{
				title : '<font color="#0099FF">是否排班</font>',
				field : 'arrangeFlag',
				sortable : true,
				width : 40,
				align : 'center'
			},
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
				id:'detail-btn-toolbar',
				text : '详细信息',
				iconCls : 'icon-filter',
				handler : function() {
					read();
				}
			}, '-',{
				id:'import-btn-toolbar',
				text : '导入',
				iconCls : 'icon-filter',
				handler : function() {
					importDatas();
				}
			}, '-',{
				id:'export-btn-toolbar',
				text : '导出',
				iconCls : 'icon-filter',
				handler : function() {
					
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
			onLoadSuccess : function (data) {
				//修改序号bug
				$(this).datagrid("fixRownumber");
				$(this).datagrid('hideColumn','schoolArea');
				$(this).datagrid('hideColumn','carBanding');
				$(this).datagrid('hideColumn','arrangeFlag');
				//datagrid没有数据时显示没有记录信息
				if(data.total==0){
					$(this).datagrid('appendRow', {
						schoolAreaName: '<div style="text-align:center;color:red">没有教练记录信息！</div>' })
					.datagrid('mergeCells', { index: 0, field: 'sex', colspan:14 });
				}
				//2.初始化教练驾照类型选择
				initDriverType($("#demo_driverType"));
				//初始化校区
				initSchoolArea($("#demo_trainer_schoolArea"),userSchoolArea);
				//添加回车事件
				bindingKeypressEvent($(".keypress_event"),mydatagrid,$("#demo_trainer_searchForm"));
			},
			//右键菜单所触发的事件
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				//取消当前页被选中的所有行
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow', rowIndex);
				$('#demo_trainer_menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			}
		});

	});

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
		var p=undefined;
		var rows = mydatagrid.datagrid('getSelections');
		//编辑时只能单选一行进行编辑
		if (rows.length == 1) {
			 p = parent.sy.dialog({
						title : '更新教练信息',
						//通过href方式打开添加页面
						href : '${pageContext.request.contextPath}/demo/trainerAction!toUpdatePage.action',
						width :1024,
						height : 545,
						buttons : [{
									text : '更新',
									iconCls : 'icon-tip',
									handler : function() {
										var f = p.find('form');
										f.form('submit',{
											url : '${pageContext.request.contextPath}/demo/trainerAction!edit.action',
											success : function(d) {
												//接受一个JSON字符串，返回解析后的对象。
												var json = $.parseJSON(d);
												if (json.success) {
														mydatagrid.datagrid('updateRow',{
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
								name : rows[0].name,
								sex : rows[0].sex,
								codeNo:rows[0].codeNo,
								birthday : rows[0].birthday,
								identity : rows[0].identity,
								addres : rows[0].addres,
								contry : rows[0].contry,
								telephone : rows[0].telephone,
								phone : rows[0].phone,
								email : rows[0].email,
								getCodeNo:rows[0].codeNo,
								nation:rows[0].nation,
								comment:rows[0].comment,
								driverLicenseId:rows[0].driverLicenseId,
								firstGetDate:rows[0].firstGetDate,
								driverType:rows[0].driverType,
								driverCarType:rows[0].driverCarType,
								driverValidatyStart:rows[0].driverValidatyStart,
								driverValidatyEnd:rows[0].driverValidatyEnd,
								teachId:rows[0].teachId,
								teachValidatyStart:rows[0].teachValidatyStart,
								teachBalidatyEnd:rows[0].teachBalidatyEnd,
								firstTeachingDate:rows[0].firstTeachingDate,
								jionDay:rows[0].jionDay,
								schoolArea:rows[0].schoolArea,
								carBanding:rows[0].carBanding,
								arrangeFlag:rows[0].arrangeFlag,
								arrangeType:rows[0].arrangeType,
							});
							
							var myDriverCarType=undefined;
							//1.初始化教练准教类型选择
							 myDriverCarType=f.find('input[name=driverCarType]');
							var driverCombobox=myDriverCarType.combobox({
								url:'${pageContext.request.contextPath}/demo/json/driverType_combobox.json',
							    valueField:'value',
							    textField:'text',
							    multiple:true,
							    multiline:true,
							    method:'post',
							    readonly:true,
							    editable:false,
							    separator:',',
							    hasDownArrow : true, 
							    //初始化训练时段默认值被选中
							    onLoadSuccess:function(){
									var temp=rows[0].trainTime;
									var as=new Array();
									as=temp.toString().split(",");
									for(var i=0;i<as.length;i++){
										myDriverCarType.combobox('select',as[i]);
									}
								}
							});
							
							//2.初始化教练驾照类型选择
							var myDriverType=f.find('input[name=driverType]');
							myDriverType.combobox({
								url : '${pageContext.request.contextPath}/demo/json/driverType_combobox.json',
								valueField : 'value',
								textField : 'text',
								method:'get',
								editable:false,
								onChange:function(){
									//1.首先清空教练准教类型combobox
									myDriverCarType.combobox('clear');
									var comboValue=myDriverType.combobox('getValue');
									if(comboValue==1){
										var temp=[1,3,4,5,6,7,8,9,13];
										myDriverCarType.combobox('clear');
										for(var i=0;i<temp.length;i++){
											myDriverCarType.combobox('select',temp[i]);
										}
									}else if(comboValue==2){
										var temp=[2,4,5,6,7,8,9,13];
										myDriverCarType.combobox('clear');
										for(var i=0;i<temp.length;i++){
											myDriverCarType.combobox('select',temp[i]);
										}
									}else if(comboValue==3){
										var temp=[3,6,7,8,9];
										myDriverCarType.combobox('clear');
										for(var i=0;i<temp.length;i++){
											myDriverCarType.combobox('select',temp[i]);
										}
									}else if(comboValue==4){
										var temp=[4,6,7,8,9];
										myDriverCarType.combobox('clear');
										for(var i=0;i<temp.length;i++){
											myDriverCarType.combobox('select',temp[i]);
										}
									}else if(comboValue==5){
										var temp=[5,6,7,8,9];
										myDriverCarType.combobox('clear');
										for(var i=0;i<temp.length;i++){
											myDriverCarType.combobox('select',temp[i]);
										}
									}else if(comboValue==6){
										var temp=[6,7,8,9];
										myDriverCarType.combobox('clear');
										for(var i=0;i<temp.length;i++){
											myDriverCarType.combobox('select',temp[i]);
										}
									}else if(comboValue==7){
										var temp=[7,8,9];
										myDriverCarType.combobox('clear');
										for(var i=0;i<temp.length;i++){
											myDriverCarType.combobox('select',temp[i]);
										}
									}else if(comboValue==8){
										var temp=[8,9];
										myDriverCarType.combobox('clear');
										for(var i=0;i<temp.length;i++){
											myDriverCarType.combobox('select',temp[i]);
										}
										
									}else if(comboValue==9){
										var temp=[9];
										myDriverCarType.combobox('clear');
										for(var i=0;i<temp.length;i++){
											myDriverCarType.combobox('select',temp[i]);
										}
										
									}else if(comboValue==10||comboValue==11||
											comboValue==12){
										var temp=[10,11,12];
										myDriverCarType.combobox('clear');
										for(var i=0;i<temp.length;i++){
											myDriverCarType.combobox('select',temp[i]);
										}
									}else{
										myDriverCarType.combobox('select',"");
									}
									
								}
							});
							//初始化校区
							var org=f.find('input[name=schoolArea]');
							initSchoolArea(org,userSchoolArea);
							//初始化用身份证做教练驾驶证ID
							var licenseId=f.find('input[name=driverLicenseId]');
							licenseId.attr('readonly',true);
							var iden=f.find('input[name=identity]');
							iden.change(function(){
								licenseId.val(iden.val());
							});
							iden.attr('readonly',false);
							
							var trainerNoTemp=f.find('input[name=codeNo]');
							trainerNoTemp.attr('readonly',true);
							//初始化教练类别
							var trainerTypeTemp=f.find('input[name=trainerType]');
							initStudentTypeVal(trainerTypeTemp,rows[0].trainerType);
							//初始教练排班类型
							var arrangeTypeTemp=f.find('input[name=arrangeType]');
							initArrangeType(arrangeTypeTemp,rows[0].arrangeType);
							
						}//end onload
					});
		} else if (rows.length > 1) {
			parent.sy.messagerAlert('提示信息', '同一时间只能更新一条记录！', 'error');
		} else {
			parent.sy.messagerAlert('提示信息', '请选择要更新的记录！', 'error');
		}
	}
	
	//导入数据按钮，实现对教练基本信息的导入
	function importDatas(){
		var importDialog=undefined;
		 importDialog = parent.sy.dialog({
			title : '导入教练信息',
			href : '${pageContext.request.contextPath}/demo/trainerAction!toUploadPage.action',
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
								url : '${pageContext.request.contextPath}/demo/trainerAction!importTrainerDatas.action',
								success : function(d) {
									var json = $.parseJSON(d);
									importDialog.dialog('close');
									if(json.success){
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
		});
		
	}
	
	//查看详细信息
	//单击编辑按钮，实现对用户的编辑功能
	function read() {
		var p=undefined;
		var rows = mydatagrid.datagrid('getSelections');
		//编辑时只能单选一行进行查看
		if (rows.length == 1) {
			p = parent.sy.dialog({
						title : '----> 教练详细信息  <----',
						//通过href方式打开添加页面
						href : '${pageContext.request.contextPath}/demo/trainerAction!toUpdatePage.action',
						width : 1024,
						height : 545,
						buttons : [{
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
								id : rows[0].id,
								name : rows[0].name,
								sex : rows[0].sex,
								codeNo:rows[0].codeNo,
								birthday : rows[0].birthday,
								identity : rows[0].identity,
								addres : rows[0].addres,
								contry : rows[0].contry,
								telephone : rows[0].telephone,
								phone : rows[0].phone,
								email : rows[0].email,
								getCodeNo:rows[0].codeNo,
								nation:rows[0].nation,
								comment:rows[0].comment,
								driverLicenseId:rows[0].driverLicenseId,
								firstGetDate:rows[0].firstGetDate,
								driverType:rows[0].driverType,
								driverCarType:rows[0].driverCarType,
								driverValidatyStart:rows[0].driverValidatyStart,
								driverValidatyEnd:rows[0].driverValidatyEnd,
								teachId:rows[0].teachId,
								teachValidatyStart:rows[0].teachValidatyStart,
								teachBalidatyEnd:rows[0].teachBalidatyEnd,
								firstTeachingDate:rows[0].firstTeachingDate,
								jionDay:rows[0].jionDay,
								schoolArea:rows[0].schoolArea,
								arrangeType:rows[0].arrangeType,
							});
							
							var myDriverCarType=undefined;
							//1.初始化教练准教类型选择
							 myDriverCarType=f.find('input[name=driverCarType]');
							var driverCombobox=myDriverCarType.combobox({
								url:'${pageContext.request.contextPath}/demo/json/driverType_combobox.json',
							    valueField:'value',
							    textField:'text',
							    multiple:true,
							    multiline:true,
							    method:'post',
							    readonly:true,
							    separator:',',
							    hasDownArrow : true, 
							    //初始化训练时段默认值被选中
							    onLoadSuccess:function(){
									var temp=rows[0].trainTime;
									var as=new Array();
									as=temp.toString().split(",");
									for(var i=0;i<as.length;i++){
										myDriverCarType.combobox('select',as[i]);
									}
								}
							});
							
							//2.初始化教练驾照类型选择
							var myDriverType=f.find('input[name=driverType]');
							myDriverType.combobox({
								url : '${pageContext.request.contextPath}/demo/json/driverType_combobox.json',
								valueField : 'value',
								textField : 'text',
								method:'get',
								editable:true,
								onChange:function(){
									//1.首先清空教练准教类型combobox
									myDriverCarType.combobox('clear');
									var comboValue=myDriverType.combobox('getValue');
									if(comboValue==1){
										var temp=[1,3,4,5,6,7,8,9,13];
										myDriverCarType.combobox('clear');
										for(var i=0;i<temp.length;i++){
											myDriverCarType.combobox('select',temp[i]);
										}
									}else if(comboValue==2){
										var temp=[2,4,5,6,7,8,9,13];
										myDriverCarType.combobox('clear');
										for(var i=0;i<temp.length;i++){
											myDriverCarType.combobox('select',temp[i]);
										}
									}else if(comboValue==3){
										var temp=[3,6,7,8,9];
										myDriverCarType.combobox('clear');
										for(var i=0;i<temp.length;i++){
											myDriverCarType.combobox('select',temp[i]);
										}
									}else if(comboValue==4){
										var temp=[4,6,7,8,9];
										myDriverCarType.combobox('clear');
										for(var i=0;i<temp.length;i++){
											myDriverCarType.combobox('select',temp[i]);
										}
									}else if(comboValue==5){
										var temp=[5,6,7,8,9];
										myDriverCarType.combobox('clear');
										for(var i=0;i<temp.length;i++){
											myDriverCarType.combobox('select',temp[i]);
										}
									}else if(comboValue==6){
										var temp=[6,7,8,9];
										myDriverCarType.combobox('clear');
										for(var i=0;i<temp.length;i++){
											myDriverCarType.combobox('select',temp[i]);
										}
									}else if(comboValue==7){
										var temp=[7,8,9];
										myDriverCarType.combobox('clear');
										for(var i=0;i<temp.length;i++){
											myDriverCarType.combobox('select',temp[i]);
										}
									}else if(comboValue==8){
										var temp=[8,9];
										myDriverCarType.combobox('clear');
										for(var i=0;i<temp.length;i++){
											myDriverCarType.combobox('select',temp[i]);
										}
										
									}else if(comboValue==9){
										var temp=[9];
										myDriverCarType.combobox('clear');
										for(var i=0;i<temp.length;i++){
											myDriverCarType.combobox('select',temp[i]);
										}
										
									}else if(comboValue==10||comboValue==11||
											comboValue==12){
										var temp=[10,11,12];
										myDriverCarType.combobox('clear');
										for(var i=0;i<temp.length;i++){
											myDriverCarType.combobox('select',temp[i]);
										}
									}else{
										myDriverCarType.combobox('select',"");
									}
									
								}
							});
							//初始化校区
							var org=f.find('input[name=schoolArea]');
							initSchoolArea(org,userSchoolArea);
							//初始化用身份证做教练驾驶证ID
							var licenseId=f.find('input[name=driverLicenseId]');
							licenseId.attr('readonly',true);
							var iden=f.find('input[name=identity]');
							iden.change(function(){
								licenseId.val(iden.val());
							});
							
							var disableInput=f.find('input');
							disableInput.attr('disabled',true);
							//初始化教练类别
							var trainerTypeTemp=f.find('input[name=trainerType]');
							initStudentTypeVal(trainerTypeTemp,rows[0].trainerType);
							//初始教练排班类型
							var arrangeTypeTemp=f.find('input[name=arrangeType]');
							initArrangeType(arrangeTypeTemp,rows[0].arrangeType);
						}//end onload
					});
		} else if (rows.length > 1) {
			parent.sy.messagerAlert('提示信息', '同一时间只能查看一条记录！', 'error');
		} else {
			parent.sy.messagerAlert('提示信息', '请选择要查看的记录！', 'error');
		}
	}

	//添加用户
	function append() {
		var addDialog=undefined;
		var myDriverType=undefined;
		addDialog = parent.sy.dialog({
					title : '教练信息录入',
					href : '${pageContext.request.contextPath}/demo/trainerAction!toAddPage.action',
					width : 1024,
					height : 520,
					buttons : [{
								text : '添加',
								iconCls : 'icon-add',
								handler : function() {
									var btnAdd = this;
									$(btnAdd).hide();
									var f = addDialog.find('form');
									if(myDriverType.combobox('getValue')!=""){
											f.form('submit',{
												//提交添加用户表单时，调用UserAction的add方法
											url : '${pageContext.request.contextPath}/demo/trainerAction!add.action',
											success : function(d) {
												$(btnAdd).show();
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
											},
											error:function(err){
												$(btnAdd).show();
											}
										});
									}else{
										parent.sy.messagerAlert('提示信息！','请选择教练驾照类型！！','question');
								  	}
									
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
						var myDriverCarType=undefined;
						//1.初始化教练准教类型选择
						 myDriverCarType=f.find('input[name=driverCarType]');
						var driverCombobox=myDriverCarType.combobox({
							url:'${pageContext.request.contextPath}/demo/json/driverType_combobox.json',
						    valueField:'value',
						    textField:'text',
						    multiple:true,
						    multiline:true,
						    method:'post',
						    hasDownArrow : true, 
						    readonly:false,
						    editable:false,
						    //初始化训练时段默认值被选中
						    onLoadSuccess:function(){
								/* var temp=rows[0].trainTime;
								var as=new Array();
								as=temp.toString().split(",");
								for(var i=0;i<as.length;i++){
									myDriverCarType.combobox('select',as[i]);
								} */
							}
						});
						
						//2.初始化教练驾照类型选择
						 myDriverType=f.find('input[name=driverType]');
						myDriverType.combobox({
							url : '${pageContext.request.contextPath}/demo/json/driverType_combobox.json',
							valueField : 'value',
							textField : 'text',
							method:'get',
							required:true,
							editable:false,
							onChange:function(){
								var comboValue=myDriverType.combobox('getValue');
								if(comboValue==1){
									var temp=[1,3,4,5,6,7,8,9,13];
									myDriverCarType.combobox('clear');
									for(var i=0;i<temp.length;i++){
										myDriverCarType.combobox('select',temp[i]);
									}
								}else if(comboValue==2){
									var temp=[2,4,5,6,7,8,9,13];
									myDriverCarType.combobox('clear');
									for(var i=0;i<temp.length;i++){
										myDriverCarType.combobox('select',temp[i]);
									}
								}else if(comboValue==3){
									var temp=[3,6,7,8,9];
									myDriverCarType.combobox('clear');
									for(var i=0;i<temp.length;i++){
										myDriverCarType.combobox('select',temp[i]);
									}
								}else if(comboValue==4){
									var temp=[4,6,7,8,9];
									myDriverCarType.combobox('clear');
									for(var i=0;i<temp.length;i++){
										myDriverCarType.combobox('select',temp[i]);
									}
								}else if(comboValue==5){
									var temp=[5,6,7,8,9];
									myDriverCarType.combobox('clear');
									for(var i=0;i<temp.length;i++){
										myDriverCarType.combobox('select',temp[i]);
									}
								}else if(comboValue==6){
									var temp=[6,7,8,9];
									myDriverCarType.combobox('clear');
									for(var i=0;i<temp.length;i++){
										myDriverCarType.combobox('select',temp[i]);
									}
								}else if(comboValue==7){
									var temp=[7,8,9];
									myDriverCarType.combobox('clear');
									for(var i=0;i<temp.length;i++){
										myDriverCarType.combobox('select',temp[i]);
									}
								}else if(comboValue==8){
									var temp=[8,9];
									myDriverCarType.combobox('clear');
									for(var i=0;i<temp.length;i++){
										myDriverCarType.combobox('select',temp[i]);
									}
									
								}else if(comboValue==9){
									var temp=[9];
									myDriverCarType.combobox('clear');
									for(var i=0;i<temp.length;i++){
										myDriverCarType.combobox('select',temp[i]);
									}
									
								}else if(comboValue==10||comboValue==11||
										comboValue==12){
									var temp=[10,11,12];
									myDriverCarType.combobox('clear');
									for(var i=0;i<temp.length;i++){
										myDriverCarType.combobox('select',temp[i]);
									}
								}else{
									myDriverCarType.combobox('select',"");
								}
								
							}
						});
						
						//初始化校区
						var org=f.find('input[name=schoolArea]');
						initSchoolArea(org,userSchoolArea);
						//初始化用身份证做教练驾驶证ID
						var licenseId=f.find('input[name=driverLicenseId]');
						licenseId.attr('readonly',true);
						var iden=f.find('input[name=identity]');
						iden.change(function(){
							licenseId.val(iden.val());
						});
						//
						var identitytemp=f.find('input[name=identity]');
						identitytemp.change(function(){
							$.ajax({
								url : '${pageContext.request.contextPath}/demo/trainerAction!identityIdExistOrNot.action',
								data:{identity:identitytemp.val()},
								dataType : 'json',
								success : function(d) {
									if(d){
										parent.sy.messagerAlert('提示信息', '您输入的身份证号系统中已经存在，请核对！', 'question');
										identitytemp.val('');
									}
								}
							});
						});
						
						//初始化教练编号
						var trainerNo=f.find('input[name=codeNo]');
						trainerNo.attr('readonly',true);
						$.ajax({
							url : '${pageContext.request.contextPath}/demo/trainerAction!getMaxTrainerNo.action',
							dataType : 'json',
							success : function(d) {
								trainerNo.val(d);
							}
						});
					}//end onLoad
				});//end dialog
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
							url : '${pageContext.request.contextPath}/demo/trainerAction!delete.action',
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
		mydatagrid.datagrid('load', sy.serializeObject($('#demo_trainer_searchForm')));
	}

	//取消查询功能按钮
	function cleanSearch() {
		mydatagrid.datagrid('load', {});
		//清空与查询有关的输入框
		$('#demo_trainer_searchForm input').val('');
	}
</script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询功能'" style="height: 115px;overflow: hidden;" align="left">
		<!-- 教练信息查询表单 -->
		<form id="demo_trainer_searchForm">
			<table class="tableForm datagrid-toolbar"
				style="width: 100%;height: 100%;">
				<tr>
					<th>所属于校区:</th>
					<td><input id="demo_trainer_schoolArea" name="schoolArea" style="width:150px;" /></td>
					<th>姓名:</th>
					<td><input name="name" class="keypress_event" style="width:150px;" /></td>
					<th>身份证号：</th>
					<td><input name="identity" class="keypress_event" style="width:150px;" /></td>
					<th>驾驶证号：</th>
					<td><input name="driverLicenseId" class="keypress_event" style="width:150px;" /></td>
					
				</tr>
				<tr >
					<th>教练编号：</th>
					<td><input name="codeNo" class="keypress_event" style="width:150px;" /></td>
					<th>驾驶证有效期:</th>
					<td><input name="driverValidatyEnd" class="easyui-datebox" data-options="editable:false" style="width: 155px;" /></td>
					<th>至</th>
					<td><input name="birthday" class="easyui-datebox" data-options="editable:false" style="width: 155px;" /></td>
					<th>准驾类型:</th>
					<td><input id="demo_driverType" name="driverType" style="width:155px;" /></td>
				</tr>
				<tr>
					<th>教练类别</th>
					<td>
						<select class="easyui-combobox" name="trainerType" style="width:100px;">
	       				 	<option value="">请选择</option>
	       				 	<option value="0" >本部教练</option>
	       				 	<option value="1">外聘教练</option>
	    				</select>
					</td>
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
		<table id="demo_trainer_datagrid"></table>
	</div>
	<!-- 操作按钮事件 -->
	<div id="demo_trainer_menu" class="easyui-menu" style="width:120px;display: none;">
		<div id="add-menu" onclick="append();" data-options="iconCls:'icon-add'">添加</div>
		<div id="del-menu" onclick="remove();" data-options="iconCls:'icon-remove'">删除</div>
		<div id="edit-menu" onclick="edit();" data-options="iconCls:'icon-edit'">更新</div>
		<div id="detail-menu" onclick="read();" data-options="iconCls:'icon-filter'">详细信息</div>
	</div>
</body>
</html>
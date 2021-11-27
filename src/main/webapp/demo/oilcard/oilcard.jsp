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
	var userACLs=[];
	$(function() {
		userSchoolArea=getUserSchoolArea();
		getUserACL();
		
		mydatagrid = $('#demo_oilcard_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/demo/oilcardAction!datagrid.action',/* 从后台读取所有user数据 */
			title : '<div style=color:red align:center>油卡信息管理列表!</div>', /* 表头标题 */
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
			checkOnSelect : true,
			selectOnCheck : true,
			striped : true,//表示条纹，隔行显示不现背景色
			nowrap:true,//单元显示
			singleSelect:false,
			sortName : 'cardNo', //列排序
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
				field : 'schoolAreaName',
				width : 100,
				align : 'center'
			},{
				title : '<font color="#0099FF">卡号</font>',
				field : 'cardNo',
				width : 70,
				sortable:true,
				align : 'center',
			},{
				title : '<font color="#0099FF">余额</font>',
				field : 'balance',
				width : 80,
				align : 'center',
			},{
				title : '<font color="#0099FF">上次充值</font>',
				field : 'prevateDate',
				sortable : true,
				width : 80,
				align : 'center',
				formatter:formatTimeYMD,
			},{
				title : '<font color="#0099FF">状态</font>',
				field : 'state',
				width : 100,
				sortable:true,
				align : 'center',
				formatter:function(value,row,index){
					return initOilcardState(value,row,index);
				}
			},{
				title : '<font color="#0099FF">创建者</font>',
				field : 'operator',
				width : 100,
				sortable:true,
				align : 'center',
			},{
				title : '<font color="#0099FF">创建日期</font>',
				field : 'createTime',
				width : 100,
				sortable:true,
				align : 'center',
				formatter:formatTimeYMD,
			},{
				title : '<font color="#0099FF">备注</font>',
				field : 'remarks',
				width : 300,
				sortable:true,
				align : 'center',
				formatter:showAllMsg,
			},{
				title : '<font color="#0099FF">schoolArea</font>',
				field : 'schoolArea',
				width : 100,
				sortable:true,
				align : 'center',
			}] ],
			
			//菜单功能设置
			toolbar : [ {
				id:'add-btn-toolbar',
				text : '添加',
				iconCls : 'icon-add',
				handler : function() {
					appendOilcard();
				}
			},'-', {
				id:'edit-btn-toolbar',
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					editOilcard();
				}
			}, '-',{
				id:'del-btn-toolbar',
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					removeOilcard();
				}
			}, '-',{
				id:'rechange-btn-toolbar',
				text : '卡充值',
				iconCls : 'icon-more',
				handler : function() {
					recharge();
				}
			}, '-',{
				id:'detail-btn-toolbar',
				text : '加油登记明细',
				iconCls : 'icon-more',
				handler : function() {
					usingDetail();
				}
			}, '-',{
				text : '取消选中',
				iconCls : 'icon-undo',
				handler : function() {
					//取消所有的选中行
					mydatagrid.datagrid('clearSelections');
					mydatagrid.datagrid('uncheckAll');
					mydatagrid.datagrid('unselectAll');
					
				}
			}, '-',{
				text : '刷新',
				iconCls : 'icon-reload',
				handler : function() {
					//取消所有的选中行
					mydatagrid.datagrid('reload');
					//取消当前页面的选中行
					mydatagrid.datagrid('unselectAll');
					mydatagrid.datagrid('uncheckAll');
				}
			}, '-' ],
			onBeforeLoad:function(param){
				if(!userACLs[0]){ //Create
                    $("#add-btn-toolbar").linkbutton('disable'); 
                    $("#add-menu").hide(); 
                }
				if(!userACLs[2]){//Update
					$("#edit-btn-toolbar").linkbutton('disable');
					$("#rechange-btn-toolbar").linkbutton('disable'); 
					$("#detail-btn-toolbar").linkbutton('disable'); 
					 $("#edit-menu").hide();
					 $("#rechange-menu").hide();
					 $("#detail-menu").hide();
				}
				if(!userACLs[3]){//delete
					$("#del-btn-toolbar").linkbutton('disable');  
					 $("#del-menu").hide();
				}
			},
			onLoadSuccess : function (data) {
				//修改序号bug
				mydatagrid.datagrid("fixRownumber");
				mydatagrid.datagrid('hideColumn','schoolArea');
				//datagrid没有数据时显示没有记录信息
				if(data.total==0){
					mydatagrid.datagrid('appendRow', {
						schoolAreaName: '<div style="text-align:center;color:red">没有记录信息！</div>' })
					.datagrid('mergeCells', { index: 0, field: 'schoolAreaName', colspan:8 });
				}
				//初始化校区选择
				initSchoolArea($("#demo_schoolArea_search"),userSchoolArea);
			},
			//右键菜单所触发的事件
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				//取消当前页被选中的所有行
				mydatagrid.datagrid('unselectAll');
				mydatagrid.datagrid('selectRow', rowIndex);
				$('#demo_oilcard_menu').menu('show', {
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
	
	//添加卡信息
	function appendOilcard(){
		oilcardAddDialog = parent.sy.dialog({
			title : '<div align=center>添加油卡信息</div>',
			href : '${pageContext.request.contextPath}/demo/oilcardAction!toOilcardAddPage.action',
			width : 1024,
			height : 460,
			buttons : [
					{
						text : '添加',
						iconCls : 'icon-add',
						handler : function() {
							var btnAdd = this;
							$(btnAdd).hide();
							var f = oilcardAddDialog.find('form');
							//提交表单
							f.form('submit',{
								//提交添加用户表单时，调用UserAction的add方法
								url : '${pageContext.request.contextPath}/demo/oilcardAction!add.action',
								success : function(d) {
									$(btnAdd).show();
									var json = $.parseJSON(d);
									if (json.success) {
										/* mydatagrid.datagrid('reload'); *///这种方式性能差，消耗系统资源
										mydatagrid.datagrid('insertRow',{
											index : 0, // index start with 0
											row : json.obj
										});
										oilcardAddDialog.dialog('close');
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
						text : '关闭',
						iconCls : 'icon-cancel',
						handler : function() {
							oilcardAddDialog.dialog('close');
						}
					} ],
					//通过onLoad函数实现数据回显
					onLoad : function() {
						var myForm=oilcardAddDialog.find('form');
						var schoolAreaTemp=myForm.find('input[name=schoolArea]');
						initSchoolArea(schoolAreaTemp,userSchoolArea);
					},
		});
	}
	
	//修改卡信息
	function editOilcard(){
		var rows = mydatagrid.datagrid('getSelections');
		//编辑时只能单选一行进行编辑
		if (rows.length == 1) {
			oilcardEditDialog = parent.sy.dialog({
						title : '<div align=center >更新加油卡基本信息</div>',
						href : '${pageContext.request.contextPath}/demo/oilcardAction!toOilcardEditPage.action',
						width : 1024,
						height : 540,
						buttons : [
								{
									text : '<div style=color:red align:center>更新</div>',
									iconCls : 'icon-tip',
									handler : function() {
										var f = oilcardEditDialog.find('form');
										f.form('submit',{
											url : '${pageContext.request.contextPath}/demo/oilcardAction!edit.action',
											success : function(d) {
													var json = $.parseJSON(d);
													if (json.success) {
														mydatagrid.datagrid('reload');
														mydatagrid.datagrid('uncheckAll');
														mydatagrid.datagrid('unselectAll');
														oilcardEditDialog.dialog('close');
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
										oilcardEditDialog.dialog('close');
									}
								} ],
						//数据回显显示
						onLoad : function() {
							var f = oilcardEditDialog.find('form');
							f.form('load', {
								id : rows[0].id,
								cardNo:rows[0].carNo,
								balance:rows[0].balance,
								operator:rows[0].operator,
								schoolArea:rows[0].schoolArea,
								remarks:rows[0].remarks,
								prevateDate:rows[0].prevateDate,
							});
							//初始化校区
							var schoolAreaTemp=f.find('input[name=schoolArea]');
							initSchoolArea(schoolAreaTemp,userSchoolArea);
							//初始化卡状态
							var stateTemp=f.find('input[name=state]');
							var stateUrl='${pageContext.request.contextPath}/demo/json/oilcardState_combobox.json';
							initComboboxData(stateTemp,rows[0].state,stateUrl,true);
							
						}
					});
		} else if (rows.length > 1) {
			parent.sy.messagerAlert('提示信息', '同一时间只能更新一条记录！', 'question');
		} else {
			parent.sy.messagerAlert('提示信息', '请选择要更新的记录！', 'question');
		}
	}
	
	//删除卡信息
	function removeOilcard(){
		var rows = mydatagrid.datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			parent.sy
					.messagerConfirm(
							'请确认',
							'您要删除当前所选择项信息？系统中与该加油卡的所有信息将会被删除！！！',
							function(r) {
								if (r) {
									for (var i = 0; i < rows.length; i++) {
										ids.push(rows[i].id);
									}
									$.ajax({
										url : '${pageContext.request.contextPath}/demo/oilcardAction!delete.action',
												data : {
													ids : ids.join(',')
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
			parent.sy.messagerAlert('提示信息', '请勾选要删除的记录！', 'question');
		}
	}
	
	//充值卡信息
	function recharge(){
		var rows = mydatagrid.datagrid('getSelections');
		//编辑时只能单选一行进行编辑
		if (rows.length == 1) {
			rechargeMainDialog = parent.sy.dialog({
						title : '<div align=center >油卡充值明细信息</div>',
						href : '${pageContext.request.contextPath}/demo/rechargeOilcardAction!toRechargeOilcardPage.action',
						width : 1280,
						height : 768,
						buttons : [{
							text : '关闭',
							iconCls : 'icon-cancel',
							handler : function() {
								rechargeMainDialog.dialog('close');
							}
						} ],
						//数据回显显示
						onLoad : function() {
							var f = rechargeMainDialog.find('form');
							f.form('load',{
								id:rows[0].id,
								schoolAreaName:rows[0].schoolAreaName,
								cardNo:rows[0].cardNo,
								beforeBalance:rows[0].balance,
							});
							//初始化卡状态
							var stateTemp=f.find('input[name=oilcardState]');
							var stateUrl='${pageContext.request.contextPath}/demo/json/oilcardState_combobox.json';
							initComboboxData(stateTemp,rows[0].state,stateUrl,true);
							
							//查找按钮事件
							var searchEvent=f.find('a[id=rechargeOilcard_a_searche]');
							searchEvent.linkbutton({
							    iconCls: 'icon-search',
							    onClick:function(){
							    	rechargeDatagrid.datagrid('load', sy.serializeObject(f));
							    },
							});	
							//重围按钮
							var startTime=f.find('input[name=rechargeDateStart]');
							startTime.datebox({
							});
							var endTime=f.find('input[name=rechargeDateEnd]');
							endTime.datebox({
							});
							var resetEvent=f.find('a[id=rechargeOilcard_a_cancel]');
							resetEvent.linkbutton({
							    iconCls: 'icon-cancel',
							    onClick:function(){
							    	startTime.datebox('setValue','');
							    	endTime.datebox('setValue','');
							    	rechargeDatagrid.datagrid('load', {});
							    },
							});	
							
							//初始化充值明细列表
							initRechargeDategrid(rows[0].id,rows[0].schoolArea,rows[0].cardNo,rows[0].schoolAreaName,rows[0].balance,rows[0].state);
						}
					});
		} else if (rows.length > 1) {
			parent.sy.messagerAlert('提示信息', '同一时间只能操作一张卡！', 'question');
		} else {
			parent.sy.messagerAlert('提示信息', '请选择要充值的卡！', 'question');
		}
	}
	
	//初始化充值明细列表
	function initRechargeDategrid(cardId,schoolArea,cardNo,schoolAreaName,balance,state){
		var rechargeDategridId=rechargeMainDialog.find('table[id=detail_rechargeOilcard_datagrid]');
		rechargeDatagrid=rechargeDategridId.datagrid({
			url : '${pageContext.request.contextPath}/demo/rechargeOilcardAction!getRechargeDetailByCardId.action',
			queryParams:{schoolArea:schoolArea,oilCardId:cardId,cardNo:cardNo},
			title:'<div align=center >油卡充值明细信息列表</div>',
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
			idField : 'oilCardId', //此属性一定要配置
			sortName : 'rechargeDate', //列排序
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
				title : '<font color="#0099FF">充值前余额</font>',
				field : 'beforeBalance',
				sortable : true,
				width : 100,
				align : 'center',
			},{
				title : '<font color="#0099FF">充值金额</font>',
				field : 'money',
				sortable : true,
				width : 100,
				align : 'center',
			},{
				title : '<font color="#0099FF">充值后余额</font>',
				field : 'afterBalance',
				sortable : true,
				width : 100,
				align : 'center',
			}, {
				title : '<font color="#0099FF">充值日期</font>',
				field : 'rechargeDate',
				sortable : true,
				width : 90,
				align : 'center',
				formatter:formatTimeYMD,
			},] ],
			//普通列
			columns : [ [{
				title : '<font color="#0099FF">创建人</font>',
				field : 'rechargePerson',
				sortable : true,
				width : 100,
				align : 'center',
			}, {
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
				text : '充值',
				iconCls : 'icon-add',
				handler : function() {
					appendRecharge(cardId,schoolArea,cardNo,schoolAreaName,balance,state);
				}
			},{
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					removeRecharge();
				}
			},{
				text : '取消选中',
				iconCls : 'icon-undo',
				handler : function() {
					//取消所有的选中行
					rechargeDatagrid.datagrid('clearSelections');
					//取消当前页面的选中行
					rechargeDatagrid.datagrid('unselectAll');
					rechargeDatagrid.datagrid('uncheckAll');
				}
			}, {
				text : '刷新',
				iconCls : 'icon-reload',
				handler : function() {
					rechargeDatagrid.datagrid('reload');
					//取消当前页面的选中行
					rechargeDatagrid.datagrid('unselectAll');
					//取消当前页面的选中行
					rechargeDatagrid.datagrid('uncheckAll');
				}
			}, ],
			onLoadSuccess : function (data) {
				rechargeDatagrid.datagrid("fixRownumber");
				//datagrid没有数据时显示没有记录信息
				if(data.total==0){
					rechargeDatagrid.datagrid('appendRow', {
						oilCardNo: '<div style="text-align:center;color:red">没有记录信息！</div>' })
					.datagrid('mergeCells', { index: 0, field: 'oilCardNo', colspan:5 });
				}
				rechargeDatagrid.datagrid('hideColumn','oilCardId');
				rechargeDatagrid.datagrid('hideColumn','schoolArea');
			},
			//右键菜单所触发的事件
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				//取消当前页被选中的所有行
				rechargeDatagrid.datagrid('unselectAll');
				rechargeDatagrid.datagrid('selectRow', rowIndex);
				$('#demo_recharge_menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			}
			
		});
	}
	
	//添加油卡充值
	var latelyBalance=undefined;
	var afterBalance=undefined;
	function appendRecharge(cardId,schoolArea,cardNo,schoolAreaName,balance,state){
		//1.获取油卡当前余额
		$.ajax({
			url : '${pageContext.request.contextPath}/demo/rechargeOilcardAction!getLatelyBalanceByOilcardId.action',
			data:{oilCardId:cardId},
			dataType : 'json',
			success : function(d) {
				latelyBalance=d;
			},
			error:function(err){
				alert("获取余额信息失败");
			}
		});
		
		rechargeAddDialog = parent.sy.dialog({
			title : '<div align=center>油卡充值信息录入</div>',
			href : '${pageContext.request.contextPath}/demo/rechargeOilcardAction!toRechargeOilcardAddPage.action',
			width : 1024,
			height : 368,
			buttons : [
					{
						text : '添加',
						iconCls : 'icon-add',
						handler : function() {
							var btnAdd = this;
							$(btnAdd).hide();
								var addForm = rechargeAddDialog.find('form[id=demo_rechargeOilcard_addForm]');
								//提交表单
								addForm.form('submit',{
									//提交添加用户表单时，调用UserAction的add方法
									url : '${pageContext.request.contextPath}/demo/rechargeOilcardAction!add.action',
									success : function(d) {
										$(btnAdd).show();
										var json = $.parseJSON(d);
										if (json.success) {
											var f=rechargeMainDialog.find('form');
											var mainBalance=f.find('input[name=beforeBalance]');
											$.ajax({
												url : '${pageContext.request.contextPath}/demo/rechargeOilcardAction!getLatelyBalanceByOilcardId.action',
												data:{oilCardId:cardId},
												dataType : 'json',
												success : function(d) {
													mainBalance.val(d);
												},
												error:function(err){
													alert("获取余额信息失败");
												}
											});
											rechargeDatagrid.datagrid('reload'); //这种方式性能差，消耗系统资源
											mydatagrid.datagrid('reload'); //这种方式性能差，消耗系统资源
											rechargeAddDialog.dialog('close'); 
											parent.sy.messagerShow({
												msg : json.msg,
												title : '提示信息'
											});
										}
										parent.sy.messagerShow({
											msg : json.msg,
											title : '提示信息'
										});
									},
									error:function(er){
										$(btnAdd).show();
									}
									
								});
						}
					}, {
						text : '关闭',
						iconCls : 'icon-cancel',
						handler : function() {
							rechargeAddDialog.dialog('close');
						}
					} ],
					//通过onLoad函数实现数据回显
					onLoad : function() {
						var f=rechargeAddDialog.find('form');
						f.form('load',{
							oilCardId:cardId,
							oilCardNo:cardNo,
							schoolArea:schoolArea,
							schoolAreaName:schoolAreaName,
							beforeBalance:latelyBalance,
						});
						//初始化卡状态
						var stateTemp=f.find('input[name=oilcardState]');
						var stateUrl='${pageContext.request.contextPath}/demo/json/oilcardState_combobox.json';
						initComboboxData(stateTemp,state,stateUrl,true,true);
						//充值后余额(充值前余额+本次充值金额)
						var moneyTemp=f.find('input[name=money]');
						var afterBalanceTemp=f.find('input[name=afterBalance]');
						moneyTemp.change(function(){
							if(moneyTemp.val()!=null&&moneyTemp.val()!=undefined&&moneyTemp.val()!=''){
								afterBalanceTemp.val((parseFloat(latelyBalance)+parseFloat(moneyTemp.val())).toFixed(2));
								afterBalance=parseFloat(latelyBalance)+parseFloat(moneyTemp.val()).toFixed(2);//动态显示当前余额
							}
						});
						
						//充值日期
						var rechargeDateTemp=f.find('input[name=rechargeDate]');
						rechargeDateTemp.datebox({
							required:true,
						});
						
						//禁止修改项
						var disableItem=f.find('input[class=recharge_add_readonly]');
						disableItem.attr('readonly',true);
					},
		});
	}
	
	//删除油卡充值
	function removeRecharge(){
		var rows = rechargeDatagrid.datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			parent.sy
					.messagerConfirm(
							'请确认',
							'您要删除当前所选择项信息？充值记录会被删除，但油卡余额不发生改变！！！',
							function(r) {
								if (r) {
									for (var i = 0; i < rows.length; i++) {
										ids.push(rows[i].id);
									}
									$.ajax({
										url : '${pageContext.request.contextPath}/demo/rechargeOilcardAction!delete.action',
												data : {
													ids : ids.join(',')
												},
												dataType : 'json',
												success : function(d) {
													rechargeDatagrid.datagrid('load');
													rechargeDatagrid.datagrid('unselectAll');
													rechargeDatagrid.datagrid('uncheckAll');
													parent.sy.messagerShow({
														title : '提示信息',
														msg : d.msg
													});
												}
											});
								}
							});
		} else {
			parent.sy.messagerAlert('提示信息', '请勾选要删除的记录！', 'question');
		}
	}
	
	//登记卡使用明细信息
	function usingDetail(){
		var rows = mydatagrid.datagrid('getSelections');
		//编辑时只能单选一行进行编辑
		if (rows.length == 1) {
			oilcardUsingMainDialog = parent.sy.dialog({
						title : '<div align=center >油卡使用明细信息</div>',
						href : '${pageContext.request.contextPath}/demo/oilcardUsingAction!toOilcardUsingPage.action',
						width : 1280,
						height : 768,
						buttons : [{
							text : '关闭',
							iconCls : 'icon-cancel',
							handler : function() {
								oilcardUsingMainDialog.dialog('close');
							}
						} ],
						//数据回显显示
						onLoad : function() {
							var f = oilcardUsingMainDialog.find('form');
							f.form('load',{
								id:rows[0].id,
								schoolAreaName:rows[0].schoolAreaName,
								oilCardNo:rows[0].cardNo,
								oilcardBalance:rows[0].balance,
							});
							//初始化卡状态
							var stateTemp=f.find('input[name=oilcardState]');
							var stateUrl='${pageContext.request.contextPath}/demo/json/oilcardState_combobox.json';
							initComboboxData(stateTemp,rows[0].state,stateUrl,true);
							
							//车辆初始化
							var carNoTemp=f.find('input[name=carNo]');
							carNoTemp.combobox({
								url : '${pageContext.request.contextPath}/demo/carAction!getAllCarsForCombobox.action',
								queryParams:{schoolArea:rows[0].schoolArea},
								valueField : 'value',
								textField : 'text',
								method:'get',
								required:true,
								editable:false,
							});
							
							//教练初始化
							//initTrainerSelect(rows[0].schoolArea);
							//查找按钮事件
							var searchEvent=f.find('a[id=oilcardUsing_a_searche]');
							searchEvent.linkbutton({
							    iconCls: 'icon-search',
							    onClick:function(){
							    	oilcardUsingDatagrid.datagrid('load', sy.serializeObject(f));
							    },
							});	
							//重围按钮
							var startTime=f.find('input[name=usingDateStart]');
							startTime.datebox({
							});
							var endTime=f.find('input[name=usingDateEnd]');
							endTime.datebox({
							});
							var resetEvent=f.find('a[id=oilcardUsing_a_cancel]');
							resetEvent.linkbutton({
							    iconCls: 'icon-cancel',
							    onClick:function(){
							    	startTime.datebox('setValue','');
							    	endTime.datebox('setValue','');
							    	oilcardUsingDatagrid.datagrid('load', {});
							    },
							});	
							
							//初始化充值明细列表
							initOilcardUsingDategrid(rows[0].id,rows[0].schoolArea,rows[0].cardNo,rows[0].schoolAreaName,rows[0].balance,rows[0].state);
						}
					});
		} else if (rows.length > 1) {
			parent.sy.messagerAlert('提示信息', '同一时间只能操作一张卡！', 'question');
		} else {
			parent.sy.messagerAlert('提示信息', '请选择要充值的卡！', 'question');
		}
	}
	
	//初始化充值明细列表
	 function initOilcardUsingDategrid(oilcardId,schoolArea,oilcardNo,schoolAreaName,balance,state){
		 var oilcardUsingDategridId=oilcardUsingMainDialog.find('table[id=detail_oilcardUsing_datagrid]');
		 oilcardUsingDatagrid=oilcardUsingDategridId.datagrid({
				url : '${pageContext.request.contextPath}/demo/oilcardUsingAction!getOilcardUsingDetailByCardId.action',
				queryParams:{schoolArea:schoolArea,oilCardId:oilcardId,cardNo:oilcardNo},
				title:'油卡使用明细信息列表',
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
					width : 90,
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
					text : '添加',
					iconCls : 'icon-add',
					handler : function() {
						appendOilcardUsing(oilcardId,schoolArea,oilcardNo,schoolAreaName,balance,state);
					}
				},{
					text : '删除',
					iconCls : 'icon-remove',
					handler : function() {
						removeOilcardUsing();
					}
				},{
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
	}
	
	//添加油卡使用明细
	var latelyUsingBalance=undefined;
	var afterUsingBalance=undefined;
	function appendOilcardUsing(oilCardId,schoolArea,oilcardNo,schoolAreaName,balance,state){
		//1.获取油卡当前余额
		$.ajax({
			url : '${pageContext.request.contextPath}/demo/rechargeOilcardAction!getLatelyBalanceByOilcardId.action',
			data:{oilCardId:oilCardId},
			dataType : 'json',
			success : function(d) {
				latelyUsingBalance=d;
			},
			error:function(err){
				alert("获取余额信息失败");
			}
		});
		var f=oilcardUsingMainDialog.find('form');
		//刷新当前余额
		var mainBalance=f.find('input[name=oilcardBalance]');
		
		oilcardUsingAddDialog = parent.sy.dialog({
			title : '油卡使用登记明细信息录入',
			href : '${pageContext.request.contextPath}/demo/oilcardUsingAction!toOilcardUsingAddPage.action',
			width : 1024,
			height : 400,
			buttons : [
					{
						text : '添加',
						iconCls : 'icon-add',
						handler : function() {
							var btnAdd = this;
							$(btnAdd).hide();
							var addForm=oilcardUsingAddDialog.find('form[id=demo_oilcardUsing_addForm]');
							var moneyTotalCount=addForm.find('input[name=moneyTotal]');
							var	temp=parseInt(latelyUsingBalance)-parseInt(moneyTotalCount.val());
							if(temp>0){
									var addForm = oilcardUsingAddDialog.find('form[id=demo_oilcardUsing_addForm]');
									//提交表单
									addForm.form('submit',{
										//提交添加用户表单时，调用UserAction的add方法
										url : '${pageContext.request.contextPath}/demo/oilcardUsingAction!add.action',
										success : function(d) {
											$(btnAdd).show();
											var json = $.parseJSON(d);
											if (json.success) {
												$.ajax({
													url : '${pageContext.request.contextPath}/demo/rechargeOilcardAction!getLatelyBalanceByOilcardId.action',
													data:{oilCardId:oilCardId},
													dataType : 'json',
													success : function(d) {
														mainBalance.val(d);
													},
													error:function(err){
														alert("获取余额信息失败")
													}
												});
												oilcardUsingDatagrid.datagrid('reload'); //这种方式性能差，消耗系统资源
												mydatagrid.datagrid('reload'); //这种方式性能差，消耗系统资源
												oilcardUsingAddDialog.dialog('close'); 
												parent.sy.messagerShow({
													msg : json.msg,
													title : '提示信息'
												});
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
									parent.sy.messagerAlert('提示信息', '当前油卡余额不足！', 'question');
								}
						}
					}, {
						text : '关闭',
						iconCls : 'icon-cancel',
						handler : function() {
							oilcardUsingAddDialog.dialog('close');
						}
					} ],
					//通过onLoad函数实现数据回显
					onLoad : function() {
						var f=oilcardUsingAddDialog.find('form[id=demo_oilcardUsing_addForm]');
						f.form('load',{
							oilCardId:oilCardId,
							oilCardNo:oilcardNo,
							schoolArea:schoolArea,
							schoolAreaName:schoolAreaName,
							beforeBalance:latelyUsingBalance,
						});
						//初始化卡状态
						var stateTemp=f.find('input[name=oilcardState]');
						var stateUrl='${pageContext.request.contextPath}/demo/json/oilcardState_combobox.json';
						initComboboxData(stateTemp,state,stateUrl,true,true);
						
						//禁止修改项
						var disableItem=f.find('input[class=recharge_add_readonly]');
						disableItem.attr('readonly',true);
						
						//初始化油料名称
						//初始化油号
						//选择教练事件
						//选择车辆事件
						//总价计算
						//初始化借卡时间
						initBaseParams(f,schoolArea);
					},
		});
	}
	
	//初始化油料名称
	//初始化油号
	//选择教练事件
	//选择车辆事件
	//总价计算
	//初始化借卡时间
	function initBaseParams(f,schoolArea){
		var oilNameTemp=f.find('input[name=oilName]');//初始化油料名称
		initComboboxData(oilNameTemp,null,'${pageContext.request.contextPath}/demo/json/oilName_combobox.json',true);
		var oilTypeTemp=f.find('input[name=oilType]');//初始化油号
		initComboboxData(oilTypeTemp,null,'${pageContext.request.contextPath}/demo/json/oilType_combobox.json',true);
		var trainerTemp=f.find('input[name=usingPerson]');
		trainerTemp.attr('readonly',true);
		var btnTrainer=f.find('a[id=demo_oilcardUsing_a_selecttrainer]');
		btnTrainer.linkbutton({
		    iconCls: 'icon-search',
		    onClick:function(){
		    	initTrainerSelect(schoolArea);
		    },
		});
		//取消捆绑
		var btnCancelTrainer=f.find('a[id=demo_oilcardUsing_a_canceltrainer]');
		btnCancelTrainer.linkbutton({
		    iconCls: 'icon-cancel',
		    onClick:function(){
		    	trainerTemp.val('');
		    },
		});
		//选择车辆
		var carNoTemp=f.find('input[name=carNo]');
		carNoTemp.combobox({
			url : '${pageContext.request.contextPath}/demo/carAction!getAllCarsForCombobox.action',
			queryParams:{schoolArea:schoolArea},
			valueField : 'value',
			textField : 'text',
			method:'get',
			required:true,
			editable:false,
		});
		//计算总价
		var oilTotalTemp=f.find('input[name=oilTotal]');
		var priceTemp=f.find('input[name=price]');
		var moneyTotalTemp=f.find('input[name=moneyTotal]');
		moneyTotalTemp.attr('readonly',true);
		
		oilTotalTemp.change(function(){
			if(priceTemp.val()!=null||priceTemp.val()!=undefined||priceTemp.val()!=''){
				moneyTotalTemp.val((parseFloat(oilTotalTemp.val())*parseFloat(priceTemp.val())).toFixed(2));
			}
		});
		priceTemp.change(function(){
			if(oilTotalTemp.val()!=null||oilTotalTemp.val()!=undefined||oilTotalTemp.val()!=''){
				moneyTotalTemp.val((parseFloat(oilTotalTemp.val())*parseFloat(priceTemp.val())).toFixed(2));
			}
		});
		
		var startTimeTemp=f.find('input[name=startTime]');
		startTimeTemp.timespinner({    
		    min: '08:30',  
		    max:'22:00',
		    required: false,    
		    showSeconds: false,   
		});  

		var endTimeTemp=f.find('input[name=endTime]');
		endTimeTemp.timespinner({    
		    min: '08:30',  
		    max:'22:00',
		    required: false,    
		    showSeconds: false,   
		});  
	}
	//选择教练对话框
	function initTrainerSelect(userSchoolArea){
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
				var trainerDatagrid=f.find('table[id=demo_student_selectTrainer]');
				var carflag=1;
				trainerDatagrid.datagrid({
					url : '${pageContext.request.contextPath}/demo/studentAction!getTrainerDatabyType.action',/* 从后台读取所有user数据 */
					queryParams:{organizationId:userSchoolArea},//carBanding 1;表示查找没有与车辆捆绑的教练
					title : '教练信息管理列表!', /* 表头标题 *
					iconCls : 'icon-save', /* 标题前边的图标 */
					pagination : true, /* 是否显示页码 */
					pagePosition : 'bottom', /* 页码显示的位置 */
					pageSize : 10, /* 每面显示的记录数 */
					pageList : [ 10, 20, 30, 40,50 ,100,200 ], /* 选择每面显示的记录数 */
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
						title : '<font color="#0099FF">姓名</font>',
						field : 'trainerName',
						width : 80,
						align : 'center'
					} ,
					{
						title : '<font color="#0099FF">编号</font>',
						field : 'trainerCodeNo',
						width : 80,
						align : 'center',
						formatter:function(value,row,index){
							return formatTrainerNo(value, row, index);
						}
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
						formatter:function(value,row,index){
							return formatterDriverType(value, row, index);
						}
						
					},{
						title : '<font color="#0099FF">编号后3位</font>',
						field : 'trainerSubCodeNo',
						width : 80,
						align : 'center'
					} ,{
						title : '<font color="#0099FF">教练属性</font>',
						field : 'trainerType',
						sortable : true,
						width : 90,
						formatter:function(value,row,index){
							return initTrainerType(value, row, index);
						}
					},
					] ],
					//菜单功能设置
					toolbar : [ {
						text : '确定',
						iconCls : 'icon-add',
						handler : function() {
							rows=trainerDatagrid.datagrid('getSelections');
							if(rows.length==0){
								parent.sy.messagerAlert('提示信息', '当前没有选配教练^v^！', 'error');
							}
							//初始化使用人
							var formTemp= oilcardUsingAddDialog.find('form[id=demo_oilcardUsing_addForm]');
							var usingPersonTemp=formTemp.find('input[name=usingPerson]');
							usingPersonTemp.val(rows[0].trainerName);
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
						trainerDatagrid.datagrid('hideColumn','trainerSubCodeNo');
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
	
	//删除油卡使用明细
	function removeOilcardUsing(){
		var rows = oilcardUsingDatagrid.datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			parent.sy
					.messagerConfirm(
							'请确认',
							'您要删除当前所选择项信息？充值记录会被删除，但油卡余额不发生改变！！！',
							function(r) {
								if (r) {
									for (var i = 0; i < rows.length; i++) {
										ids.push(rows[i].id);
									}
									$.ajax({
										url : '${pageContext.request.contextPath}/demo/oilcardUsingAction!delete.action',
												data : {
													ids : ids.join(',')
												},
												dataType : 'json',
												success : function(d) {
													oilcardUsingDatagrid.datagrid('load');
													oilcardUsingDatagrid.datagrid('unselectAll');
													oilcardUsingDatagrid.datagrid('uncheckAll');
													parent.sy.messagerShow({
														title : '提示信息',
														msg : d.msg
													});
												}
											});
								}
							});
		} else {
			parent.sy.messagerAlert('提示信息', '请勾选要删除的记录！', 'question');
		}
	}
	
	//查询按钮功能函数
	function oilcardSearch() {
		mydatagrid.datagrid('load', sy.serializeObject($('#demo_oilcard_searchForm')));
	}

	//取消查询功能按钮
	function cleanSearch() {
		mydatagrid.datagrid('load', {});
		//清空与查询有关的输入框
		$('#demo_oilcard_searchForm input').val('');
	}
	
	
</script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询功能'" style="height: 90px;overflow: hidden;" align="left">
		<!-- 教练排班信息查询表单 -->
		<form id="demo_oilcard_searchForm">
			<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
				<tr>
					<th style="width:65px">所属校区:</th>
					<td><input id="demo_schoolArea_search" name="schoolArea"  style="width:150px;" /></td>
					<th style="width:65px">卡号:</th>
					<td><input  name="cardNo"  style="width:150px;" /></td>
					<th style="width:65px">日期开始:</th>
					<td><input  name="createTimeStart" class="easyui-datebox" data-options="editable:false"  style="width:150px;" /></td>
					<th style="width:65px">日期结束</th>
					<td><input name="createTimeEnd" class="easyui-datebox" data-options="editable:false"  style="width:150px;" /></td>
				</tr>
				<tr>
					<th ></th>
					<th ></th>
					<td colspan="4">
						<a href="javascript:void(0);" id="oilcard_a_searche" class="easyui-linkbutton" iconCls="icon-search" onclick="oilcardSearch();">查询</a>
						<a href="javascript:void(0);" id="oilcard_a_canel" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cleanSearch();">取消</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<table id="demo_oilcard_datagrid"></table>
	</div>
	<!-- 操作按钮事件 -->
	<div id="demo_oilcard_menu" class="easyui-menu" style="width:120px;display: none;">
		<div id="add-menu" onclick="appendOilcard();" data-options="iconCls:'icon-add'">添加</div>
		<div id="edit-menu" onclick="editOilcard();" data-options="iconCls:'icon-edit'">修改</div>
		<div id="del-menu" onclick="removeOilcard();" data-options="iconCls:'icon-remove'">删除</div>
		<div id="rechange-menu" onclick="recharge();" data-options="iconCls:'icon-filter'">卡充值</div>
		<div id="detail-menu" onclick="usingDetail();" data-options="iconCls:'icon-filter'">使用明细</div>
	</div>
	
	<div id="demo_recharge_menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="appendRecharge();" data-options="iconCls:'icon-add'">添加</div>
		<div onclick="editRecharge();" data-options="iconCls:'icon-edit'">修改</div>
		<div onclick="removeRecharge();" data-options="iconCls:'icon-remove'">删除</div>
	</div>
	
	<div id="demo_oilcardUsing_menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="appendOilcardUsing();" data-options="iconCls:'icon-add'">添加</div>
		<div onclick="editOilcardUsing();" data-options="iconCls:'icon-edit'">修改</div>
		<div onclick="removeOilcardUsing();" data-options="iconCls:'icon-remove'">删除</div>
	</div>
</body>
</html>
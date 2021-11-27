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
	var userACLs=[];
	$(function() {
		userSchoolArea=getUserSchoolArea();
		getUserACL();
		
		mydatagrid = $('#demo_feeDetailType_config').datagrid({
			url : '${pageContext.request.contextPath}/demo/feeDetailTypeAction!datagrid.action',/* 从后台读取所有user数据 */
			queryParams:{propertyType:0},
			title : '收费类型详细列表!', /* 表头标题 */
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
			sortName : 'orderNo', //列排序
			sortOrder : 'asc', //列排序
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
			}, {
				title : '<font color="#0099FF">所属部门</font>',
				field : 'schoolAreaName',
				width : 120,
				align : 'left'
			},{
				title : '<font color="#0099FF">收费名称</font>',
				field : 'itemName',
				width : 100,
				sortable : true,
				align : 'center'
			},{
				title : '<font color="#0099FF">收费金额</font>',
				field : 'money',
				sortable : true,
				align : 'center',
				width : 100,
			},{
				title : '<font color="#0099FF">排序号</font>',
				field : 'orderNo',
				width : 80,
				sortable : true,
				align : 'center'
			}, ] ],
			//普通列
			columns : [ [{
				title : '<font color="#0099FF">创建日期</font>',
				field : 'createTime',
				sortable : true,
				width : 100,
				align : 'center',
				formatter:formatTimeYMD,
			}, {
				title : '<font color="#0099FF">创建人</font>',
				field : 'operator',
				align : 'center',
				width : 100
			},{
				title : '<font color="#0099FF">备注说明</font>',
				field : 'comment',
				align : 'center',
				width : 400,
				formatter:showAllMsg,
			},{
				title : '<font color="#0099FF">校区标识</font>',
				field : 'schoolArea',
				fixed:false,
				width : 150
			}

			] ],
			//菜单功能设置
			toolbar : [ {
				id:'add-btn-toolbar',
				text : '添加',
				iconCls : 'icon-add',
				handler : function() {
					appendFeeDetailType();
				}
			},'-', {
				id:'edit-btn-toolbar',
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					editFeeDetailType();
				}
			}, '-',{
				id:'del-btn-toolbar',
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					removeFeeDetailType();
				}
			}, '-',{
				text : '刷新',
				iconCls : 'icon-reload',
				handler : function() {
					mydatagrid.datagrid('clearSelections');
					//取消当前页面的选中行
					mydatagrid.datagrid('unselectAll');
					mydatagrid.datagrid('uncheckAll');
					mydatagrid.datagrid('reload');
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
				initSchoolArea($("#demo_feeDetailType_schoolArea"),userSchoolArea);
				//datagrid没有数据时显示没有记录信息
				if(data.total==0){
					$(this).datagrid('appendRow', {
						schoolAreaName: '<div style="text-align:center;color:red">没有记录信息！</div>' })
					.datagrid('mergeCells', { index: 0, field: 'schoolAreaName', colspan:6 });
				}
			},
			//右键菜单所触发的事件
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				//取消当前页被选中的所有行
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow', rowIndex);
				$('#demo_feeDetailType_menu').menu('show', {
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
	function editFeeDetailType() {
		var rows = mydatagrid.datagrid('getSelections');
		//编辑时只能单选一行进行编辑
		if (rows.length == 1) {
			var p = parent.sy.dialog({
						title : '编辑收费类别',
						href : '${pageContext.request.contextPath}/demo/feeDetailTypeAction!toFeeDetailTypeEditPage.action',
						width : 1024,
						height : 460,
						buttons : [
								{
									text : '更新',
									iconCls : 'icon-tip',
									handler : function() {
										var f = p.find('form');
										//提交表单
										f.form('submit',{
											url : '${pageContext.request.contextPath}/demo/feeDetailTypeAction!edit.action',
											success : function(d) {
													var json = $.parseJSON(d);
													if (json.success) {
														mydatagrid.datagrid('reload');
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
								itemName : rows[0].itemName,
								money : rows[0].money,
								orderNo : rows[0].orderNo,
								createTime : rows[0].createTime,
								operator : rows[0].operator,
								comment : rows[0].comment,
								schoolArea : rows[0].schoolArea,
								
							});
							//初始化部门选择菜单
							 var myDepartment=f.find('input[name=schoolArea]');
							initSchoolArea(myDepartment,userSchoolArea);
							var orderNotTemp=f.find('input[name=orderNo]');
							orderNotTemp.numberspinner({    
							    min: 1,    
							    max: 200,    
							    editable:true,  
							});  
							orderNotTemp.val(rows[0].orderNo);
							var createTimeTemp=f.find('input[name=createTime]');
							createTimeTemp.datebox({    
							    required:true   
							});   
							createTimeTemp.val( rows[0].createTime);
						}
					});
		} else if (rows.length > 1) {
			parent.sy.messagerAlert('提示信息', '同一时间只能更新一条记录！', 'error');
		} else {
			parent.sy.messagerAlert('提示信息', '请选择要更新的记录！', 'error');
		}
	}

	//添加用户
	function appendFeeDetailType() {
		var addDialog = parent.sy.dialog({
					title : '添加收费类别',
					href : '${pageContext.request.contextPath}/demo/feeDetailTypeAction!toFeeDetailTypeAddPage.action',
					width : 1024,
					height : 360,
					buttons : [
							{
								text : '添加',
								iconCls : 'icon-add',
								handler : function() {
									var btnAdd = this;
									$(btnAdd).hide();
									var f = addDialog.find('form');
									//提交表单
									f.form('submit',{
										//提交添加用户表单时，调用UserAction的add方法
										url : '${pageContext.request.contextPath}/demo/feeDetailTypeAction!add.action',
										success : function(d) {
											$(btnAdd).show();
											var json = $.parseJSON(d);
											if (json.success) {
												mydatagrid.datagrid('insertRow',{
													index : 0, // index start with 0
													row : json.obj
												});
												addDialog.dialog('close');
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
							//通过onLoad函数实现数据回显
							onLoad : function() {
								var myForm=addDialog.find('form');
								var myDepartment=myForm.find('input[name=schoolArea]');
								initSchoolArea(myDepartment,userSchoolArea);
								
								//初始化创建时间
								var createTimeTemp=myForm.find('input[name=createTime]');
								createTimeTemp.datebox({
									showSeconds:false,
								});
								createTimeTemp.datebox('setValue',new Date().toLocaleDateString());
								//初始化排序号
								var orderNotTemp=myForm.find('input[name=orderNo]');
								$.ajax({
									url : '${pageContext.request.contextPath}/demo/feeDetailTypeAction!toFomatterOrderNo.action',
									data : {schoolArea : myDepartment.combobox('getValue')},
									dataType : 'json',
									success : function(d) {
										orderNotTemp.numberspinner({    
										    min: 1,    
										    max: 200,    
										    editable:false,  
										    value:d,
										}); 
									}
								});
							},
				});
	}

	//删除用户
	function removeFeeDetailType() {
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
										url : '${pageContext.request.contextPath}/demo/feeDetailTypeAction!delete.action',
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
		mydatagrid.datagrid('load', sy.serializeObject($('#demo_sms_searchForm')));
	}

	//取消查询功能按钮
	function cleanSearch() {
		mydatagrid.datagrid('load', {propertyType:0});
		//清空与查询有关的输入框
		$('.input_clean').val('');
		$('#demo_sms_searchForm input').val('');
		console.info('Test now...');
	}

</script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询功能'" style="height: 100%;overflow: hidden;" align="left">
		<!-- 人员信息查询表单 -->
		<form id="demo_sms_searchForm" >
			<input type="hidden" id="propertyTypeVal" name="propertyType" value="0">
			<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
				<tr>
					<th style="width: 65px;">所属部门:</th>
					<td><input id="demo_feeDetailType_schoolArea" name="schoolArea" style="width:150px;" />
	 					</td>
					<th style="width: 65px;">姓名:</th>
					<td><input name="name" class="input_clean" style="width:120px;" /></td>
					<th style="width: 65px;">手机号：</th>
					<td><input name="phone" class="input_clean"  style="width:120px;" /></td>
					<th style="width: 65px;">发送人：</th>
					<td><input name="operator" class="input_clean"  style="width:120px;" /></td>
				</tr>
				<tr>
					<th style="width: 65px;">发送日期:</th>
					<td><input id="input_clean_start" name="dateStart"
						class="easyui-datebox" data-options="editable:false"
						style="width: 95px;" />至:<input id="input_clean_end" name="dateEnd"
						class="easyui-datebox" data-options="editable:false"
						style="width: 95px;" /></td>
					<td colspan="2">
						<a href="javascript:void(0);" id="sms_a_searche" class="easyui-linkbutton" iconCls="icon-search" onclick="mySearch();">查询</a>
						<a href="javascript:void(0);" id="sms_a_canel" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cleanSearch();">取消</a>
					</td>
				</tr>
			</table>
		</form>
		<div id="feeTabs" class="easyui-tabs" style="width:100%;height:90%;">
			<div title="收费配置" style="padding:2px;">
				<table id="demo_feeDetailType_config" class="tableForm" ></table>
			</div>
			<div title="收费人员" closable="false" style="padding:2px;">
				<table id="demo_feeDetailType_person" class="tableForm" ></table>
			</div>
		</div>
	</div>
	
	<!-- 操作按钮事件 -->
	
	<div id="demo_feeDetailType_menu" class="easyui-menu" style="width:120px;display: none;">
		<div id="add-menu" onclick="appendFeeDetailType();" data-options="iconCls:'icon-add'">增加</div>
		<div id="del-menu" onclick="removeFeeDetailType();" data-options="iconCls:'icon-remove'">删除</div>
		<div id="edit-menu" onclick="editFeeDetailType();" data-options="iconCls:'icon-edit'">更新</div>
	</div>
</body>
</html>
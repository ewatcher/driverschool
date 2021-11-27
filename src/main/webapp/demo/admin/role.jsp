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
	var aclDatagrid = undefined;
	var mainMenuIds = [];
	var mainMenuNames = [];
	var ChildrenMenus = [];
	var allChildrenMenus = [];
	var childrenMenuMaps = {};
	var authorizationDatagrid = undefined;
	var userACLs=[];
	var aclEditor = {
			type : 'checkbox',
			options : {
				on:true,
				off:false,
			},
		};
	
	$(function() {
		userSchoolArea=getUserSchoolArea();
		getMainMenus();
		getChildrenMenus();
		getAllChildMenus();
		getUserACL();
		
		mydatagrid = $('#datagrid').datagrid({
			url : '${pageContext.request.contextPath}/demo/roleAction!datagrid.action',
			title : '',
			iconCls : 'icon-save',
			pagination : true,
			pageSize : 20,
			pageList : [ 10, 20, 30, 40,50 ,100,200 ],
			fit : true,
			fitColumns : true,
			nowrap : false,
			border : false,
			idField : 'cid',
			sortName : 'cname',
			sortOrder : 'desc',
			checkOnSelect : false,
			selectOnCheck : true,
			rownumbers: true,
			striped:true,
			frozenColumns : [ [ {
				title : '编号',
				field : 'cid',
				width : 150,
				sortable : true,
				checkbox : true
			}, {
				title : '所属校区',
				field : 'schoolAreaName',
				width : 150,
				sortable : true
			} ,{
				title : '角色名称',
				field : 'cname',
				width : 150,
				sortable : true
			},{
				title : '创建日期',
				field : 'createDate',
				width : 120,
				sortable : true,
				formatter:formatTimeYMD
			},{
				title : '创建者',
				field : 'operator',
				width : 120,
				sortable : true
			}] ],
			columns : [ [ {
				title : '备注',
				field : 'cdesc',
				width : 120
			}, {
				title : '权限ID',
				field : 'authIds',
				width : 100
			} ,{
				title : '拥有权限',
				field : 'authNames',
				width : 100
			} , {
				title : '校区标识',
				field : 'schoolArea',
				width : 100
			}] ],
			toolbar : [ {
				id:'add-btn-toolbar',
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					append();
				}
			}, '-', {
				id:'del-btn-toolbar',
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					remove();
				}
			}, '-',  {
				id:'role-btn-toolbar',
				text : '角色授权',
				iconCls : 'icon-filter',
				handler : function() {
					roleAuthorization();
				}
			}, '-', {
				text : '取消选中',
				iconCls : 'icon-undo',
				handler : function() {
					mydatagrid.datagrid('unselectAll');
					mydatagrid.datagrid('uncheckAll');
				}
			}, '-' ],
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow', rowIndex);
				$('#menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			},
			onBeforeLoad:onMyBeforeLoad,
			onLoadSuccess : function (data) {
				//修改序号bug
				$(this).datagrid("fixRownumber");
				$(this).datagrid('hideColumn','schoolArea');
				$(this).datagrid('hideColumn','authNames');
				$(this).datagrid('hideColumn','authIds');
				//datagrid没有数据时显示没有记录信息
				if(data.total==0){
					mydatagrid.datagrid('appendRow', {
						schoolAreaName: '<div style="text-align:center;color:red">没有记录信息！</div>' })
					.datagrid('mergeCells', { index: 0, field: 'schoolAreaName', colspan:8 });
				}
			},
			
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
	
	function onMyBeforeLoad(){
		if(!userACLs[0]){ //Create
            $("#add-btn-toolbar").linkbutton('disable'); 
            $("#add-menu").hide(); 
        }
		if(!userACLs[2]){//Update
			$("#edit-btn-toolbar").linkbutton('disable');
			$("#role-btn-toolbar").linkbutton('disable'); 
			 $("#edit-menu").hide();
			 $("#role-menu").hide();
		}
		if(!userACLs[3]){//delete
			$("#del-btn-toolbar").linkbutton('disable');  
			 $("#del-menu").hide();
		}
	}

	function getMainMenus() {
		//初始化主菜单
		$.ajax({
			url : '${pageContext.request.contextPath}/demo/menuAction!toGainMainMenus.action',
			type : 'POST', //提交方式 可以选择post/get 推荐post   
			async : false, //同步异步
			dataType : 'json',
			success : function(data) {
				for (var i = 0; i < data.length; i++) {
					mainMenuIds.push(data[i].value);
					mainMenuNames.push(data[i].text);
				}
			}
		});
	}

	function getChildrenMenus() {
		//获取子菜单信息
		$.ajax({
			url : '${pageContext.request.contextPath}/demo/menuAction!toGainAuthorizationMenus.action',
			type : 'POST', //提交方式 可以选择post/get 推荐post   
			async : false, //同步异步
			dataType : 'json',
			success : function(retMenuMaps) {
				childrenMenuMaps = retMenuMaps;
			}
		});
	}

	function getAllChildMenus() {
		for (var i = 0; i < mainMenuIds.length; i++) {
			var key = mainMenuIds[i];
			var childrenMenus = childrenMenuMaps[key];
			for (var j = 0; j < childrenMenus.length; j++) {
				var temp = childrenMenus[j].id;
				allChildrenMenus.push(temp);
			}
		}
	}


	//用户授权
	function roleAuthorization() {
		var rows = mydatagrid.datagrid('getSelections');
		//编辑时只能单选一行进行编辑
		if (rows.length == 1) {
			var authorizationDialog = parent.sy.dialog({
				title : '角色【' + rows[0].cname + '】权限列表',
				href : '${pageContext.request.contextPath}/demo/aclAction!toUserAuthorizationPage.action',
				width : 800,
				height : 700,
				buttons : [ {
					text : '刷新',
					iconCls : 'icon-reload',
					handler : function() {
						aclDatagrid.datagrid('reload');
					}
				}, {
					text : '关闭',
					iconCls : 'icon-cancel',
					handler : function() {
						authorizationDialog.dialog('close');
					}
				} ],

				onLoad : function() {
					var f = authorizationDialog.find('form');
					var tableTemp = f.find('table[id=authorizationTable]');
					aclDatagrid = tableTemp.datagrid({
						url : '${pageContext.request.contextPath}/demo/aclAction!toInitAclMenu.action', /* 从后台读取所有user数据 */
						queryParams : {
							principalSn : rows[0].cid,
							principalType:'ROLE',
						},
						fit : true,
						fitColumns : true, //列自动调整功能
						nowrap : false, //以行的形式进行显示
						border : false,
						idField : 'id', //此属性一定要配置
						sortName : 'id', //列排序
						sortOrder : 'desc', //列排序
						checkOnSelect : true,
						selectOnCheck : true,
						rownumbers : true,
						singleSelect : true,
						striped : true, //表示条纹，隔行显示不现背景色
						//冻结列
						frozenColumns : [ [ {
							title : '编号',
							field : 'id',
							width : 150,
							sortable : true,
						}, {
							title : '<font color="#0099FF">一级菜单</font>',
							field : 'mainMenuName',
							width : 100,
							sortable : true
						}, {
							title : '<font color="#0099FF">二级菜单</font>',
							field : 'secondMenuName',
							width : 100,
							sortable : true
						} ] ],
						//普通列
						columns : [ [ {
							title : '<font color="#0099FF">添加操作</font>',
							field : 'aclStateC',
							width : 50,
							align : 'center',
							editor : aclEditor,
							formatter : formatACLState,
						}, {
							title : '<font color="#0099FF">删除操作</font>',
							field : 'aclStateD',
							width : 50,
							align : 'center',
							editor : aclEditor,
							formatter : formatACLState,
						}, {
							title : '<font color="#0099FF">修改操作</font>',
							field : 'aclStateU',
							width : 50,
							align : 'center',
							editor : aclEditor,
							formatter : formatACLState,
						}, {
							title : '<font color="#0099FF">查询操作</font>',
							field : 'aclStateR',
							width : 50,
							align : 'center',
							editor : aclEditor,
							formatter : formatACLState,
						}, {
							title : '<font color="#0099FF">全部授权</font>',
							field : 'enableFlag',
							width : 50,
							align : 'center',
							editor : aclEditor,
							formatter : formatACLState,
						},{
							title : '<font color="#0099FF">一级标识</font>',
							field : 'mainMenuId',
							width : 50,
							align : 'center',
						}, {
							title : '<font color="#0099FF">二级标识</font>',
							field : 'secondMenuId',
							width : 50,
							align : 'center',
						}, ] ],
						onClickCell : onClickACL,
						onLoadSuccess : function(data) {
							//修改序号bug
							aclDatagrid.datagrid("fixRownumber");
							aclDatagrid.datagrid('hideColumn', 'mainMenuId');
							aclDatagrid.datagrid('hideColumn', 'secondMenuId');
							aclDatagrid.datagrid('hideColumn', 'id');
							//datagrid没有数据时显示没有记录信息
							if (data.total == 0) {
								aclDatagrid.datagrid('appendRow', {
									mainMenuName : '<div style="text-align:center;color:red">没有记录信息！</div>'
								})
									.datagrid('mergeCells', {
										index : 0,
										field : 'mainMenuName',
										colspan : 4
									});
							}
						},
					});


				} //on load
			});
		} else if (rows.length > 1) {
			parent.sy.messagerAlert('提示信息', '同一时间只能编辑一条记录！', 'question');
		} else {
			parent.sy.messagerAlert('提示信息', '请选择要授权的用户！', 'question');
		}
	}

	var editIndex = undefined; // 编辑状态的行标
	var clickIndex = undefined; // 选中状态的行标
	function onClickACL(index, field, value) {
		var roleId = mydatagrid.datagrid('getSelections')[0].cid;
		var rows = aclDatagrid.datagrid('getData').rows[index]
		if (endEditing() && (rows.mainMenuId != null || rows.mainMenuId != undefined || rows.mainMenuId != '')) {
			aclDatagrid.datagrid('selectRow', index).datagrid(
				'editCell', {
					index : index,
					field : field,
					value : value,
				});
			editIndex = index;
			//获取当前用户操作的editor
			var myEditor = aclDatagrid.datagrid('getEditor', {
				index : index,
				field : field
			});
			//checkbox change事件（用户授权操作事件）
			
			var myEd = myEditor.target;
			var aclStateFlag=false;
			var permissonFlag=undefined;
			//CRUD授权
			if(field == 'enableFlag'){
				$(myEd).attr('checked',rows.enableFlag);//初始化checkbox选中状态
				$(myEd).change(function(){
					if($(myEd).is(":checked")){
						addOrupdateUserACL(roleId, rows.id, 0, true, index, field, value);
						addOrupdateUserACL(roleId, rows.id, 1, true, index, field, value);
						addOrupdateUserACL(roleId, rows.id, 2, true, index, field, value);
						addOrupdateUserACL(roleId, rows.id, 3, true, index, field, value);
					}else{
						addOrupdateUserACL(roleId, rows.id, 0, false, index, field, value);
						addOrupdateUserACL(roleId, rows.id, 1, false, index, field, value);
						addOrupdateUserACL(roleId, rows.id, 2, false, index, field, value);
						addOrupdateUserACL(roleId, rows.id, 3, false, index, field, value);
					}
				});
			}else{
				if (field == 'aclStateC') {
					$(myEd).attr('checked',rows.aclStateC);//初始化checkbox选中状态
					permissonFlag=0;//权限CRUD标记
				} else if (field == 'aclStateR') {
					$(myEd).attr('checked',rows.aclStateR);//初始化checkbox选中状态
					permissonFlag=1;//权限CRUD标记
				} else if (field == 'aclStateU') {
					$(myEd).attr('checked',rows.aclStateU);//初始化checkbox选中状态
					permissonFlag=2;//权限CRUD标记
				} else if (field == 'aclStateD') {
					$(myEd).attr('checked',rows.aclStateD);//初始化checkbox选中状态
					permissonFlag=3;//权限CRUD标记
				} 
				$(myEd).change(function(){
					if($(myEd).is(":checked")){
						aclStateFlag=true;
						addOrupdateUserACL(roleId, rows.id, permissonFlag, aclStateFlag, index, field, value);
					}else{
						aclStateFlag=false;
						addOrupdateUserACL(roleId, rows.id, permissonFlag, aclStateFlag, index, field, value);
					}
				});
			}
		}
	}

	function endEditing() {
		if (editIndex == undefined) {
			return true;
		}
		if (aclDatagrid.datagrid('validateRow', editIndex)) { // 验证行行（editIndex）是否有效
			aclDatagrid.datagrid('endEdit', editIndex); // 结束编辑
			return true;
		} else {
			return false;
		}
	}


	function addOrupdateUserACL(roleId, resourceId, permission, aclTriStateFlag, index, field, value) {
		console.info("userId:" + roleId + ",resourceId:" + resourceId + ",permission:" + permission + ",aclTriStateFlag:" + aclTriStateFlag);
		//初始化主菜单
		$.ajax({
			url : '${pageContext.request.contextPath}/demo/aclAction!addOrEdit.action',
			data : {
				principalType : 'ROLE',
				principalSn : roleId,
				resourceSn : resourceId,
				permission : permission,
				aclTriStateFlag : aclTriStateFlag
			},
			type : 'POST', //提交方式 可以选择post/get 推荐post   
			async : false, //同步异步
			dataType : 'json',
			success : function(d) {
				aclDatagrid.datagrid('reload');
			}
		});
	}

	
	function append() {
		var p = parent.sy.dialog({
			title : '添加角色',
			href : '${pageContext.request.contextPath}/demo/roleAction!roleAdd.action',
			width : 520,
			height : 320,
			buttons : [ {
				text : '添加',
				handler : function() {
					var btnAdd = this;
					$(btnAdd).hide();
					var f = p.find('form');
					f.form('submit', {
						url : '${pageContext.request.contextPath}/demo/roleAction!add.action',
						success : function(d) {
							$(btnAdd).show();
							var json = $.parseJSON(d);
							if (json.success) {
								/* datagrid.datagrid('reload'); */
								mydatagrid.datagrid('insertRow',{
									index:0,
									row:json.obj
								});
								p.dialog('close');
							}
							parent.sy.messagerShow({
								msg : json.msg,
								title : '提示'
							});
						},
						error:function(err){
							$(btnAdd).show();
						}
					});
				}
			} ],
			onLoad : function() {
				var f = p.find('form');
				var org=f.find('input[name=schoolArea]');
				initSchoolArea(org,userSchoolArea);
			}
		});
	}
	function remove() {
		var rows = mydatagrid.datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			parent.sy.messagerConfirm('请确认', '您要删除当前所选项目？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].cid);
					}
					$.ajax({
						url : '${pageContext.request.contextPath}/demo/roleAction!delete.action',
						data : {
							ids : ids.join(',')
						},
						dataType : 'json',
						success : function(d) {
							mydatagrid.datagrid('load');
							mydatagrid.datagrid('unselectAll');
							parent.sy.messagerShow({
								title : '提示',
								msg : d.msg
							});
						}
					});
				}
			});
		} else {
			parent.sy.messagerAlert('提示', '请勾选要删除的记录！', 'error');
		}
	}
</script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<table id="datagrid"></table>
	</div>

	<div id="menu" class="easyui-menu" style="width:120px;display: none;">
		<div id="add-menu" onclick="append();" data-options="iconCls:'icon-add'">增加</div>
		<div id="del-menu" onclick="remove();" data-options="iconCls:'icon-remove'">删除</div>
		<div id="edit-menu" onclick="edit();" data-options="iconCls:'icon-edit'">编辑</div>
		<div id="detail-menu" onclick="detail();" data-options="iconCls:'icon-filter'">详细信息</div>
	</div>
</body>
</html>
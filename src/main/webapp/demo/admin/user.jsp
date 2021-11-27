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
	var aclDatagrid = undefined;
	var mainMenuIds = [];
	var mainMenuNames = [];
	var ChildrenMenus = [];
	var allChildrenMenus = [];
	var childrenMenuMaps = {};
	var authorizationDatagrid = undefined;

	var comboData = [ {
		"value" : true,
		"text" : "启用"
	}, {
		"value" : false,
		"text" : "关闭"
	} ];
	var aclEditor = {
		type : 'checkbox',
		options : {
			on:true,
			off:false,
		},
	};

	var userSchoolArea = undefined;
	var userACLs=[];
	$(function() {
		userSchoolArea = getUserSchoolArea();
		getMainMenus();
		getChildrenMenus();
		getAllChildMenus();
		getUserACL();

		mydatagrid = $('#datagrid').datagrid({
			url : 'userAction!datagrid.action', /* 从后台读取所有user数据 */
			title : '用户管理列表!', /* 表头标题 */
			iconCls : 'icon-save', /* 标题前边的图标 */
			pagination : true, /* 是否显示页码 */
			pagePosition : 'bottom', /* 页码显示的位置 */
			pageSize : 20, /* 每面显示的记录数 */
			pageList : [ 10, 20, 30, 40, 50, 100, 200 ], /* 选择每面显示的记录数 */
			fit : true,
			fitColumns : true, //列自动调整功能
			nowrap : false, //以行的形式进行显示
			border : false,
			idField : 'cid', //此属性一定要配置
			sortName : 'ccreatedatetime', //列排序
			sortOrder : 'desc', //列排序
			checkOnSelect : false,
			selectOnCheck : true,
			rownumbers : true,
			striped : true, //表示条纹，隔行显示不现背景色
			//冻结列
			frozenColumns : [ [ {
				title : '编号',
				field : 'cid',
				width : 150,
				sortable : true,
				checkbox : true
			}, {
				title : '<font color="#0099FF">所属于校区</font>',
				field : 'schoolAreaName',
				width : 150,
			}, {
				title : '<font color="#0099FF">登录名</font>',
				field : 'cname',
				width : 150,
				sortable : true
			} ] ],
			//普通列
			columns : [ [ {
				title : '<font color="#0099FF">密码</font>',
				field : 'cpwd',
				width : 50,
				formatter : function(value, rowData, rowIndex) {
					return '******';
				}
			}, {
				title : '<font color="#0099FF">用户类型</font>',
				field : 'userType',
				width : 60,
				align : 'center',
				formatter : function(value, rowData, rowIndex) {
					if (value == 1) {
						return '学员';
					} else if (value == 2) {
						return '教练';
					} else if (value == 3) {
						return '职员';
					} else {
						return '';
					}
				}
			}, {
				title : '<font color="#0099FF">创建时间</font>',
				field : 'ccreatedatetime',
				sortable : true,
				width : 90,
				align : 'center',
				formatter : formatTimeYMD,
			}, {
				title : '<font color="#0099FF">最后修改时间</font>',
				field : 'cmodifydatetime',
				sortable : true,
				width : 90,
				align : 'center',
				formatter : formatTimeYMD,
			}, {
				title : '<font color="#0099FF">所属角色ID</font>',
				field : 'roleIds',
				width : 150,
				hidden : true
			}, {
				title : '<font color="#0099FF">所属角色</font>',
				field : 'roleNames',
				width : 150
			}, {
				title : '<font color="#0099FF">校区标识</font>',
				field : 'schoolArea',
				width : 150
			} ] ],
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
					mydatagrid.datagrid('uncheckAll');
				}
			}, '-', {
				id:'authorization-btn-toolbar',
				text : '用户授权',
				iconCls : 'icon-man',
				handler : function() {
					userAuthorization();
				}
			}, '-', {
				id:'role-btn-toolbar',
				text : '分配角色',
				iconCls : 'icon-man',
				handler : function() {
					roleAuthorization();
				}
			}, '-', {
				id:'batch-btn-toolbar',
				text : '批量分配角色',
				iconCls : 'icon-more',
				handler : function() {
					editRole();
				}
			}, '-' ],
			onBeforeLoad:onMyBeforeLoad,
			onLoadSuccess : function(data) {
				//修改序号bug
				$(this).datagrid("fixRownumber");
				$(this).datagrid('hideColumn', 'schoolArea');
				//初始化校区选项
				initSchoolArea($("#demo_user_schoolArea"), userSchoolArea);
				//datagrid没有数据时显示没有记录信息
				if (data.total == 0) {
					mydatagrid.datagrid('appendRow', {
						schoolAreaName : '<div style="text-align:center;color:red">没有用户记录信息！</div>'
					})
						.datagrid('mergeCells', {
							index : 0,
							field : 'schoolAreaName',
							colspan : 8
						});
				}
			},
			

			//右键菜单所触发的事件
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				//取消当前页被选中的所有行
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow', rowIndex);
				$('#menu').menu('show', {
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
	
	function onMyBeforeLoad(){
		if(!userACLs[0]){ //Create
            $("#add-btn-toolbar").linkbutton('disable'); 
            $("#add-menu").hide(); 
        }
		if(!userACLs[2]){//Update
			$("#edit-btn-toolbar").linkbutton('disable');
			$("#authorization-btn-toolbar").linkbutton('disable'); 
			$("#role-btn-toolbar").linkbutton('disable'); 
			$("#batch-btn-toolbar").linkbutton('disable'); 
			 $("#edit-menu").hide();
			 $("#authorization-menu").hide();
			 $("#role-menu").hide();
			 $("#batch-menu").hide();
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
	function userAuthorization() {
		var rows = mydatagrid.datagrid('getSelections');
		//编辑时只能单选一行进行编辑
		if (rows.length == 1) {
			var authorizationDialog = parent.sy.dialog({
				title : '用户【' + rows[0].cname + '】权限列表',
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
							principalType:'USER',
							principalSn : rows[0].cid,
						},
						fit : true,
						fitColumns : true, //列自动调整功能
						nowrap : false, //以行的形式进行显示
						border : true,
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
							title : '<font color="#0099FF">启用角色</font>',
							field : 'aclStateT',
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
	function onClickACL(index, field, value, userId) {
		var userId = mydatagrid.datagrid('getSelections')[0].cid;
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
			if(field == 'aclStateT'){
				//继承
				console.info("rows.aclStateT:"+rows.aclStateT);
				$(myEd).attr('checked',rows.aclStateT);//初始化checkbox选中状态
				$(myEd).change(function(){
					if($(myEd).is(":checked")){
						aclStateFlag=true;
						addOrupdateUserExtends(userId, rows.id, aclStateFlag,index, field, value);
					}else{
						aclStateFlag=false;
						addOrupdateUserExtends(userId, rows.id, aclStateFlag,index, field, value);
					}
				});
				
			}else if(field == 'enableFlag'){
				$(myEd).attr('checked',rows.enableFlag);//初始化checkbox选中状态
				$(myEd).change(function(){
					if($(myEd).is(":checked")){
						addOrupdateUserACL(userId, rows.id, 0, true, index, field, value);
						addOrupdateUserACL(userId, rows.id, 1, true, index, field, value);
						addOrupdateUserACL(userId, rows.id, 2, true, index, field, value);
						addOrupdateUserACL(userId, rows.id, 3, true, index, field, value);
					}else{
						addOrupdateUserACL(userId, rows.id, 0, false, index, field, value);
						addOrupdateUserACL(userId, rows.id, 1, false, index, field, value);
						addOrupdateUserACL(userId, rows.id, 2, false, index, field, value);
						addOrupdateUserACL(userId, rows.id, 3, false, index, field, value);
					}
				});
			}else{
				//CRUD授权
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
						addOrupdateUserACL(userId, rows.id, permissonFlag, aclStateFlag, index, field, value);
					}else{
						aclStateFlag=false;
						addOrupdateUserACL(userId, rows.id, permissonFlag, aclStateFlag, index, field, value);
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


	function addOrupdateUserACL(userId, resourceId, permission, aclTriStateFlag,index, field, value) {
		console.info("userId:" + userId + ",resourceId:" + resourceId + ",permission:" + permission + ",aclTriStateFlag:" + aclTriStateFlag);
		//初始化主菜单
		$.ajax({
			url : '${pageContext.request.contextPath}/demo/aclAction!addOrEdit.action',
			data : {
				principalType : 'USER',
				principalSn : userId,
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

	function addOrupdateUserExtends(userId, resourceId, aclTriStateFlag,index, field, value) {
		//初始化主菜单
		$.ajax({
			url : '${pageContext.request.contextPath}/demo/aclAction!addOrUpdateUserExtends.action',
			data : {
				principalSn : userId,
				resourceSn : resourceId,
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

	//给用户分配角色
	function roleAuthorization() {
		var rows = mydatagrid.datagrid('getSelections');
		//编辑时只能单选一行进行编辑
		if (rows.length == 1) {
			var p = parent.sy.dialog({
				title : '编辑用户',
				href : '${pageContext.request.contextPath}/demo/userAction!userEdit.action',
				width : 450,
				height : 250,
				buttons : [ {
					text : '编辑',
					iconCls : 'icon-edit',
					handler : function() {
						var f = p.find('form');
						f.form('submit', {
							url : '${pageContext.request.contextPath}/demo/userAction!edit.action',
							success : function(d) {
								var json = $.parseJSON(d);
								if (json.success) {
									/* mydatagrid.datagrid('reload'); */
									mydatagrid.datagrid('updateRow', {
										//获得行的索引
										index : mydatagrid.datagrid('getRowIndex', rows[0]),
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
				} ,{
					text : '关闭',
					iconCls : 'icon-no',
					handler : function() {
						p.dialog('close');
					}
				}],
				//数据回显显示
				onLoad : function() {
					var f = p.find('form');
					var roleIds = f.find('input[name=roleIds]');
					//初始化角色选项
					var roleIdsCombobox = roleIds.combobox({
						url : '${pageContext.request.contextPath}/demo/userAction!doNotNeedSession_rolecombobox.action',
						valueField : 'cid',
						textField : 'cname',
						queryParams : {
							schoolArea : rows[0].schoolArea,
						},
						multiple : true,
						editable : false,
						panelHeight : 'auto',
						onLoadSuccess : function() {
							parent.$.messager.progress('close');
						}
					});
					//初始化校区选择
					var schoolAreaTemp = f.find('input[name=schoolArea]');
					initSchoolArea(schoolAreaTemp, userSchoolArea);
					//初始化修改时间
					var currentTime=new Date().toLocaleDateString();
					var cmodifydatetime=f.find('input[name=cmodifydatetime]');
					cmodifydatetime.datetimebox({    
					    value: currentTime,    
					    required: true,    
					    showSeconds: true,   
					});  
					//初始化更新页面参数
					f.form('load', {
						cid : rows[0].cid,
						cname : rows[0].cname,
						roleIds : sy.getList(rows[0].roleIds),
						ccreatedatetime : rows[0].ccreatedatetime,
						schoolArea : rows[0].schoolArea,
						userType : rows[0].userType,
					});
				}
			});
		} else if (rows.length > 1) {
			parent.sy.messagerAlert('提示信息', '同一时间只能编辑一条记录！', 'error');
		} else {
			parent.sy.messagerAlert('提示信息', '请选择要编辑的记录！', 'error');
		}
	}

	//编辑角色功能
	function editRole() {
		var rows = mydatagrid.datagrid('getSelections');
		var ids = [];
		if (rows.length > 0) {
			for (var i = 0; i < rows.length; i++) {
				ids.push(rows[i].cid);
			}
			var p = parent.sy.dialog({
				title : '批量编辑用户角色',
				href : '${pageContext.request.contextPath}/demo/userAction!userRoleEdit.action',
				width : 250,
				height : 130,
				buttons : [ {
					text : '编辑',
					handler : function() {
						var f = p.find('form');
						f.form('submit', {
							url : '${pageContext.request.contextPath}/demo/userAction!roleEdit.action',
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
				} ],
				onLoad : function() {
					var f = p.find('form');
					var idsInput = f.find('input[name=ids]');
					var roleIds = f.find('input[name=roleIds]');
					var roleIdsCombobox = roleIds.combobox({
						url : '${pageContext.request.contextPath}/demo/roleAction!doNotNeedSession_combobox.action',
						valueField : 'cid',
						textField : 'cname',
						queryParams : {
							schoolArea : userSchoolArea
						},
						multiple : true,
						editable : false,
						panelHeight : 'auto',
						onLoadSuccess : function() {
							f.form('load', {
								ids : ids.join(',')
							});
						}
					});
				}
			});
		} else {
			//当用户再没有选择行单击编辑按钮时，给出的提示信息
			parent.sy.messagerAlert('提示信息', '请选择要编辑的记录！', 'error');
		}
	}

	//单击编辑按钮，实现对用户的编辑功能
	function edit() {
		var rows = mydatagrid.datagrid('getSelections');
		//编辑时只能单选一行进行编辑
		if (rows.length == 1) {
			var p = parent.sy.dialog({
				title : '编辑用户',
				href : '${pageContext.request.contextPath}/demo/userAction!userEdit.action',
				width : 450,
				height : 250,
				buttons : [ {
					text : '编辑',
					iconCls : 'icon-edit',
					handler : function() {
						var f = p.find('form');
						f.form('submit', {
							url : '${pageContext.request.contextPath}/demo/userAction!edit.action',
							success : function(d) {
								var json = $.parseJSON(d);
								if (json.success) {
									/* mydatagrid.datagrid('reload'); */
									mydatagrid.datagrid('updateRow', {
										//获得行的索引
										index : mydatagrid.datagrid('getRowIndex', rows[0]),
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
				} ,{
					text : '关闭',
					iconCls : 'icon-no',
					handler : function() {
						p.dialog('close');
					}
				}],
				//数据回显显示
				onLoad : function() {
					var f = p.find('form');
					var roleIds = f.find('input[name=roleIds]');
					//初始化角色选项
					var roleIdsCombobox = roleIds.combobox({
						url : '${pageContext.request.contextPath}/demo/userAction!doNotNeedSession_rolecombobox.action',
						valueField : 'cid',
						textField : 'cname',
						queryParams : {
							schoolArea : rows[0].schoolArea,
						},
						multiple : true,
						editable : false,
						panelHeight : 'auto',
						onLoadSuccess : function() {
							parent.$.messager.progress('close');
						}
					});
					//初始化校区选择
					var schoolAreaTemp = f.find('input[name=schoolArea]');
					initSchoolArea(schoolAreaTemp, userSchoolArea);
					//初始化修改时间
					var currentTime=new Date().toLocaleDateString();
					var cmodifydatetime=f.find('input[name=cmodifydatetime]');
					cmodifydatetime.datetimebox({    
					    value: currentTime,    
					    required: true,    
					    showSeconds: true,   
					});  
					//初始化更新页面参数
					f.form('load', {
						cid : rows[0].cid,
						cname : rows[0].cname,
						roleIds : sy.getList(rows[0].roleIds),
						ccreatedatetime : rows[0].ccreatedatetime,
						schoolArea : rows[0].schoolArea,
						userType : rows[0].userType,
					});
				}
			});
		} else if (rows.length > 1) {
			parent.sy.messagerAlert('提示信息', '同一时间只能编辑一条记录！', 'error');
		} else {
			parent.sy.messagerAlert('提示信息', '请选择要编辑的记录！', 'error');
		}
	}

	//添加用户
	function append() {
		var addDialog = parent.sy.dialog({
			title : '添加用户',
			href : '${pageContext.request.contextPath}/demo/userAction!userAdd.action',
			width : 540,
			height : 200,
			buttons : [ {
				text : '添加',
				iconCls : 'icon-add',
				handler : function() {
					var btnAdd = this;
					$(btnAdd).hide();
					var f = addDialog.find('form');
					f.form('submit', {
						//提交添加用户表单时，调用UserAction的add方法
						url : '${pageContext.request.contextPath}/demo/userAction!add.action',
						success : function(d) {
							$(btnAdd).show();
							var json = $.parseJSON(d);
							if (json.success) {
								/* mydatagrid.datagrid('reload'); */ //这种方式性能差，消耗系统资源
								mydatagrid.datagrid('insertRow', {
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
			} ,{
				text : '关闭',
				iconCls : 'icon-no',
				handler : function() {
					addDialog.dialog('close');
				}
			}],
			//通过onLoad函数实现数据回显
			onLoad : function() {
				var f = addDialog.find('form');
				var roleIds = f.find('input[name=roleIds]');
				var roleIdsCombobox = roleIds.combobox({
					url : '${pageContext.request.contextPath}/demo/userAction!doNotNeedSession_rolecombobox.action',
					valueField : 'cid',
					textField : 'cname',
					multiple : true,
					editable : false,
				});
				var org = f.find('input[name=schoolArea]');
				initSchoolArea(org, userSchoolArea);
				//初始化修改时间
				var currentTime=new Date().toLocaleDateString();
				var crateDate=f.find('input[name=createDate]');
				crateDate.datetimebox({    
				    value: currentTime,    
				    required: true,    
				    showSeconds: true,   
				});  

			}
		});
	}

	//删除用户
	function remove() {
		var rows = mydatagrid.datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			parent.sy.messagerConfirm('请确认', '您要删除当前所选项信息？', function(r) {
				if (r) {
					for (var i = 0; i < rows.length; i++) {
						ids.push(rows[i].cid);
					}
					$.ajax({
						url : '${pageContext.request.contextPath}/demo/userAction!delete.action',
						data : {
							ids : ids.join(',')
						},
						dataType : 'json',
						success : function(d) {
							mydatagrid.datagrid('load');
							mydatagrid.datagrid('unselectAll');
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
	function _search() {
		mydatagrid.datagrid('load', sy.serializeObject($('#user_searchForm')));
	}

	//取消查询功能按钮
	function cleanSearch() {
		mydatagrid.datagrid('load', {});
		//清空与查询有关的输入框
		$('#user_searchForm input').val('');
	}
</script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询功能'"
		style="height: 115px;overflow: hidden;" align="left">
		<!-- 添加用户界面表单 -->
		<form id="user_searchForm">
			<table class="tableForm datagrid-toolbar"
				style="width: 100%;height: 100%;">
				<tr>
					<th>所属于校区</th>
					<td><input id="demo_user_schoolArea" name="schoolArea"
						style="width:150px;" /></td>
					<th>用户名</th>
					<td><input name="cname" style="width:150px;" /></td>
					<th>创建时间</th>
					<td><input name="ccreatedatetimeStart" class="easyui-datebox"
						data-options="editable:false" style="width: 155px;" /></td>
					<th>至</th>
					<td><input name="ccreatedatetimeEnd" class="easyui-datetbox"
						data-options="editable:false" style="width: 155px;" /></td>
				</tr>
				<tr>
					<th>最后修改时间</th>
					<td><input name="cmodifydatetimeStart" class="easyui-datebox"
						data-options="editable:false" style="width: 155px;" /></td>
					<th>至</th>
					<td><input name="cmodifydatetimeEnd" class="easyui-datebox"
						data-options="editable:false" style="width: 155px;" /></td>
				</tr>
				<tr>
					<th></th>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						iconCls="icon-search" onclick="_search();">查询</a><a
						href="javascript:void(0);" class="easyui-linkbutton"
						iconCls="icon-cancel" onclick="cleanSearch();">取消</a></td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<table id="datagrid"></table>
	</div>
	<!-- 操作按钮事件 -->
	<div id="menu" class="easyui-menu" style="width:120px;display: none;">
		<div id="add-menu" onclick="append();" data-options="iconCls:'icon-add'">增加</div>
		<div id="del-menu" onclick="remove();" data-options="iconCls:'icon-remove'">删除</div>
		<div id="edit-menu" onclick="edit();" data-options="iconCls:'icon-edit'">修改</div>
		<div id="authorization-menu" onclick="userAuthorization();" data-options="iconCls:'icon-edit'">用户授权</div>
		<div id="role-menu" onclick="roleAuthorization();" data-options="iconCls:'icon-edit'">分配角色</div>
		<div id="batch-menu" onclick="editRole();" data-options="iconCls:'icon-edit'">批量分配角色</div>
	</div>
</body>
</html>
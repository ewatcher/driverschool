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
	var treegrid;
	var iconData;
	
	var userSchoolArea=undefined;
	var userACLs=[];
	$(function() {
		getUserACL();
	
		$.ajax({
			url : '${pageContext.request.contextPath}/demo/studentAction!getSchoolArea.action',
			dataType : 'json',
			success : function(d) {
				userSchoolArea=d;
			}
		});

		iconData = [ {
			value : '',
			text : '默认'
		}, {
			value : 'icon-add',
			text : 'icon-add'
		}, {
			value : 'icon-edit',
			text : 'icon-edit'
		}, {
			value : 'icon-remove',
			text : 'icon-remove'
		}, {
			value : 'icon-save',
			text : 'icon-save'
		}, {
			value : 'icon-cut',
			text : 'icon-cut'
		}, {
			value : 'icon-ok',
			text : 'icon-ok'
		}, {
			value : 'icon-no',
			text : 'icon-no'
		}, {
			value : 'icon-cancel',
			text : 'icon-cancel'
		}, {
			value : 'icon-reload',
			text : 'icon-reload'
		}, {
			value : 'icon-search',
			text : 'icon-search'
		}, {
			value : 'icon-print',
			text : 'icon-print'
		}, {
			value : 'icon-help',
			text : 'icon-help'
		}, {
			value : 'icon-undo',
			text : 'icon-undo'
		}, {
			value : 'icon-redo',
			text : 'icon-redo'
		}, {
			value : 'icon-back',
			text : 'icon-back'
		}, {
			value : 'icon-sum',
			text : 'icon-sum'
		}, {
			value : 'icon-tip',
			text : 'icon-tip'
		} ];

		treegrid = $('#demo_organization_treegrid').treegrid({
			url : '${pageContext.request.contextPath}/demo/organizationAction!treegrid.action',
			toolbar : [ {
				text : '展开',
				iconCls : 'icon-redo',
				handler : function() {
					var node = treegrid.treegrid('getSelected');
					if (node) {
						treegrid.treegrid('expandAll', node.id);
					} else {
						treegrid.treegrid('expandAll');
					}
				}
			}, '-', {
				text : '折叠',
				iconCls : 'icon-undo',
				handler : function() {
					var node = treegrid.treegrid('getSelected');
					if (node) {
						treegrid.treegrid('collapseAll', node.id);
					} else {
						treegrid.treegrid('collapseAll');
					}
				}
			}, '-', {
				text : '刷新',
				iconCls : 'icon-reload',
				handler : function() {
					treegrid.treegrid('reload');
				}
			}, '-', {
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
			}, '-', {
				id:'edit-btn-toolbar',
				text : '编辑',
				iconCls : 'icon-edit',
				handler : function() {
					edit();
				}
			}, '-', {
				text : '取消选中',
				iconCls : 'icon-undo',
				handler : function() {
					treegrid.treegrid('unselectAll');
				}
			}, '-' ],
			title : '',
			iconCls : 'icon-save',
			fit : true,
			fitColumns : true,
			nowrap : false,
			animate : false,
			border : false,
			rownumbers: true,
			idField : 'id',
			treeField : 'name',
			frozenColumns : [ [ {
				title : 'id',
				field : 'id',
				width : 150,
				hidden : true
			}, {
				field : 'name',
				title : '机构名称',
				width : 230,
				formatter : function(value) {
					if (value) {
						return sy.showtip('<span title="{0}">{1}</span>', value, value);
					}
				}
			} ] ],
			columns : [ [ {
				field : 'iconcls',
				title : '机构图标',
				width : 70,
				formatter : function(value) {
					if (!value) {
						return '';
					} else {
						return sy.showtip('<span class="{0}" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span>', value);
					}
				}
			}, {
				field : 'url',
				title : '机构地址',
				width : 250,
				formatter : function(value) {
					if (value) {
						return sy.showtip('<span title="{0}">{1}</span>', value, value);
					}
				}
			}, {
				field : 'sequence',
				title : '排序',
				width : 40
			}, {
				field : 'parentId',
				title : '上级机构ID',
				width : 150,
				hidden : true
			}, {
				field : 'parentName',
				title : '上级菜单',
				width : 150
			} ] ],
			onContextMenu : function(e, row) {
				e.preventDefault();
				$(this).treegrid('unselectAll');
				$(this).treegrid('select', row.id);
				$('#demo_organization_menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			},
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
			onLoadSuccess : function(row, data) {
				/*var t = $(this);
				if (data) {
					$(data).each(function(index, d) {
						if (this.state == 'closed') {
							t.treegrid('expandAll');
						}
					});
				}*/
			},
			onExpand : function(row) {
				treegrid.treegrid('unselectAll');
			},
			onCollapse : function(row) {
				treegrid.treegrid('unselectAll');
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
	
	//初始化校区
	function initSchoolArea(object){
		object.combotree({
			url:'${pageContext.request.contextPath}/demo/organizationAction!doNotNeedSession_tree.action',
			lines:true,
			required:true,
		});
		//非系统管理员，禁止查询其他校区
		if(userSchoolArea!==null){
			object.combotree('setValue',userSchoolArea);
			object.combotree('readonly',true);
		}else{
			object.combotree('readonly',false);
		}
		
	}

	//更新機構信息
	function edit() {
		var node = treegrid.treegrid('getSelected');
		if (node) {
			var p = parent.sy.dialog({
				title : '编辑机构',
				href : '${pageContext.request.contextPath}/demo/organizationAction!organizationEdit.action',
				width : 460,
				height : 220,
				buttons : [ {
					text : '编辑',
					handler : function() {
						var f = p.find('form');
						f.form('submit', {
							url : '${pageContext.request.contextPath}/demo/organizationAction!edit.action',
							success : function(d) {
								var json = $.parseJSON(d);
								if (json.success) {
									treegrid.treegrid('reload');
									p.dialog('close');
									parent.ctrlTree.tree('reload');/*刷新左侧菜单树*/
								}
								parent.sy.messagerShow({
									msg : json.msg,
									title : '提示'
								});
							}
						});
					}
				} ],
				onLoad : function() {
					var f = p.find('form');
					var pid = f.find('input[name=parentId]');
					initSchoolArea(pid);
					var iconCls = f.find('input[name=iconcls]');
					var iconCombo = iconCls.combobox({
						data : iconData,
						formatter : function(v) {
							return sy.showtip('<span class="{0}" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span>{1}', v.value, v.value);
						}
					});
					f.form('load', node);
				}
			});
		} else {
			parent.sy.messagerAlert('提示', '请选中要编辑的记录！', 'error');
		}
	}
	//添加机构
	function append() {
		var p = parent.sy.dialog({
			title : '添加机构',
			href : '${pageContext.request.contextPath}/demo/organizationAction!organizationAdd.action',
			width : 460,
			height : 220,
			buttons : [ {
				text : '添加',
				handler : function() {
					var f = p.find('form');
					f.form('submit', {
						url : '${pageContext.request.contextPath}/demo/organizationAction!add.action',
						success : function(d) {
							var json = $.parseJSON(d);
							if (json.success) {
								treegrid.treegrid('reload');
								p.dialog('close');
								parent.ctrlTree.tree('reload');/*刷新左侧菜单树*/
							}
							parent.sy.messagerShow({
								msg : json.msg,
								title : '提示'
							});
						}
					});
				}
			} ],
			//数据回显
			onLoad : function() {
				var f = p.find('form');
				var pid = f.find('input[name=parentId]');
				var iconCls = f.find('input[name=iconcls]');
				var iconCombo = iconCls.combobox({
					data : iconData,
					formatter : function(v) {
						return sy.showtip('<span class="{0}" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span>{1}', v.value, v.value);
					}
				});
				initSchoolArea(pid);
			}
		});
	}
	
	function remove() {
		var node = treegrid.treegrid('getSelected');
		if (node) {
			parent.sy.messagerConfirm('询问', '您确定要删除【' + node.name + '】？', function(b) {
				if (b) {
					$.ajax({
						url : '${pageContext.request.contextPath}/demo/organizationAction!delete.action',
						data : {
							id : node.id
						},
						cache : false,
						dataType : 'JSON',
						success : function(r) {
							if (r.success) {
								treegrid.treegrid('remove', r.obj);
								parent.ctrlTree.tree('reload');/*刷新左侧菜单树*/
							}
							parent.sy.messagerShow({
								msg : r.msg,
								title : '提示'
							});
						}
					});
				}
			});
		}
	}
</script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<table id="demo_organization_treegrid"></table>
	</div>

	<div id="demo_organization_menu" class="easyui-menu" style="width:120px;display: none;">
		<div id="add-menu" onclick="append();" data-options="iconCls:'icon-add'">增加</div>
		<div id="del-menu"  onclick="remove();" data-options="iconCls:'icon-remove'">删除</div>
		<div id="edit-menu"  onclick="edit();" data-options="iconCls:'icon-edit'">编辑</div>
	</div>
</body>
</html>
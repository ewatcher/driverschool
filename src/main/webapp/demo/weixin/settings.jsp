<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
</head>
<body class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north',border:false,title:'查询功能'" style="height: 60px;overflow: hidden;" align="left">
      <!-- 菜单查询表单 -->
      <form id="settings_searchForm">
        <table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
          <tr>
            <th >所属校区:</th>
            <td><input id="mySchoolArea" name="organizationId" class="easyui-combotree" style="width:150px;"/></td>
            <td>
              <a href="javascript:void(0);" id="student_a_searche" class="easyui-linkbutton" iconCls="icon-search" onclick="submitSearch();">查询</a>
            </td>
            <td width="50%">&nbsp;</td>
          </tr>
        </table>
      </form>
    </div>
    <div data-options="region:'center',border:false" style="overflow: hidden;">
	   <table id="weixinSetDataGrid" class="easyui-datagrid" title="微信系统配置" 
			data-options="rownumbers:true,fit:true,pagination:true,singleSelect:true,url:'${pageContext.request.contextPath}/demo/weixinSettingsAction!datagrid.action',method:'get',toolbar:toolbar,onClickRow: onClickRow">
		  <thead>
			<tr>
                <th data-options="field:'organizationName',width:100,align:'center'" width="13%">所属机构</th>
				<th data-options="field:'nickname',width:100,align:'center'" width="15%">别名</th>
				<th data-options="field:'keyname',width:100,align:'center'" width="10%">标识</th>
				<th data-options="field:'type',width:100,align:'center',formatter:function(val, rows, index){
								if(val == 1) return '文本';
								else if(val == 2) return 'URL地址';
								else return '其它';
							}" width="10%">类型</th>
				<th data-options="field:'config',width:100,align:'center',formatter:function(val, rows, index){
								val = val.replace(/\n/g, '<br />');
								var str = '<p style=width:100%;height:auto;text-align:left;white-space:normal;margin-right:25px; >' + val + '</p>';
								return str;
							}" width="40%">内容</th>
				<th data-options="field:'status',width:100,align:'center',formatter:function(val, rows, index){
								if(val == 10) {
									return '<span style=color:red>禁止编辑</span>';
								} else if(val == 1) {
									return '<span style=color:green>可编辑</span>';
								} else {
                                    return '<span style=color:darkgray>无效</span>';
                                }
							}" width="10%">状态</th>
			</tr>
		  </thead>
	   </table>
    </div>
	<script type="text/javascript">
	
		var userSchoolArea = undefined;
	
		var toolbar = [{
			id:'add-btn-toolbar',
			text:'添加',
			iconCls:'icon-add',
			handler:append
		},'-',{
			id:'edit-btn-toolbar',
			text:'修改',
			iconCls:'icon-edit',
			handler:edit
		},'-',{
			id:'remove-btn-toolbar',
			text:'删除',
			iconCls:'icon-remove',
			handler:remove
		},'-',{
			text:'刷新',
			iconCls:'icon-reload',
			handler:reload
		},'-'];
		
		var editIndex = undefined;  // 编辑状态的行标
		var clickIndex = undefined; // 选中状态的行标
		function onClickRow(index){
			clickIndex = index;
		}
		
		function append(){
			var addDialog = parent.sy.dialog({
				title : '新增微信配置信息',
				href : '${pageContext.request.contextPath}/demo/weixinSettingsAction!settingsAdd.action',
				width : 960,
				height : 340,
				buttons : [{
						text : '添加',
						iconCls : 'icon-add',
						handler : function() {
							var f = addDialog.find('form');
							//提交表单
							f.form('submit',{
								//提交添加用户表单时，调用UserAction的add方法
								url : '${pageContext.request.contextPath}/demo/weixinSettingsAction!add.action',
								success : function(d) {
									var json = $.parseJSON(d);
									if (json.success) {
										/* mydatagrid.datagrid('reload'); *///这种方式性能差，消耗系统资源
										$('#weixinSetDataGrid').datagrid('insertRow',{
											index : 0, // index start with 0
											row : json.obj
										});
										addDialog.dialog('close');
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
				onLoad:function(){
					//初始化校区选择
					var f = addDialog.find('form');
					var dep=f.find('input[name=schoolArea]');
					initSchoolArea(dep);
				}
				
			});
		}
		function edit(){
			if (clickIndex != undefined && $('#weixinSetDataGrid').datagrid('validateRow', clickIndex)){
				editIndex = clickIndex;
				var row = $('#weixinSetDataGrid').datagrid('getRows')[editIndex];
				//编辑时只能单选一行进行编辑
				if (row) {
					if(row.status == 10){
						$.messager.alert('Warning', '该行禁止编辑！');
					} else {
						var p = parent.sy.dialog({
							title : '编辑微信配置信息',
							href : '${pageContext.request.contextPath}/demo/weixinSettingsAction!settingsEdit.action',
							width : 960,
							height : 340,
							buttons : [
									{
										text : '更新',
										iconCls : 'icon-tip',
										handler : function() {
											var f = p.find('form');
											//提交表单
											f.form('submit',{
												url : '${pageContext.request.contextPath}/demo/weixinSettingsAction!edit.action',
												success : function(d) {
														var json = $.parseJSON(d);
														if (json.success) {
															/* mydatagrid.datagrid('reload'); */
																$('#weixinSetDataGrid').datagrid('updateRow',{
																		index : editIndex,
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
									id : row.id,
									keyname : row.keyname,
									nickname : row.nickname,
									type : row.type,
									config: row.config,
									status : row.status
								});
							}
						});
					}
				} else {
					parent.sy.messagerAlert('提示信息', '请选择要更新的记录！', 'error');
				}
			} else {
				$.messager.alert('Warning', '请选择需要删除的行！');
			}
		}
		function remove(){
			if (clickIndex != undefined && $('#weixinSetDataGrid').datagrid('validateRow', clickIndex)){
				editIndex = clickIndex;
				var row = $('#weixinSetDataGrid').datagrid('getRows')[editIndex];
				if(row.status == 10){
					$.messager.alert('Warning', '该行禁止删除！');
				} else {
					$.messager.confirm('Confirm','您确定需要删除[ ' + row.driverTypeName + ' ]的数据吗?',function(r){
					    if (r){
					    	saveSubmit('${pageContext.request.contextPath}/demo/weixinSettingsAction!delete.action', {'ids':row.id}); // 删除数据
					    }
					});
				}
			} else {
				$.messager.alert('Warning', '请选择需要删除的行！');
			}
		}
		
		function reload(){
			$('#weixinSetDataGrid').datagrid('reload');
		}
		
		function saveSubmit(url, data){
			//提交修改
			console.log(url);
			console.log(data);
			
			$.ajax({
				url : url,
				data : data,
				dataType : 'json',
				async:false,
				success : function(data) {
					console.log(data);
					$.messager.show({
							title:'提示',
							msg:data.msg,
							showType:'show'
						});
					$('#weixinSetDataGrid').datagrid('reload');
				},
				error : function(err){
					console.log(err);
					$.messager.show({
							title:'错误',
							msg:err.statusText,
							showType:'show'
						});
					$('#weixinSetDataGrid').datagrid('reload');
				}
			});
		}
		
		//查询按钮功能函数
		function submitSearch() {
			var o = sy.serializeObject($('#settings_searchForm'));
			console.log(o);
			$('#weixinSetDataGrid').datagrid('load', o);
		} 
		
		//农峰添加，校区数据过虑功能
		$.ajax({
			url : '${pageContext.request.contextPath}/demo/studentAction!getSchoolArea.action',
			dataType : 'json',
			success : function(d) {
				userSchoolArea=d;
				initSchoolArea($("#mySchoolArea"),d);
			}
		});
		//初始化校区
		function initSchoolArea(object,userSchoolArea){
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
	</script>
</body>
</html>
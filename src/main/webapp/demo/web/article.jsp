<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
</head>
<body class="easyui-layout" data-options="fit:true">
	<table id="articleDataGrid" class="easyui-datagrid" title="网站文章管理" 
			data-options="rownumbers:true,fit:true,pagination:true,singleSelect:true,url:'${pageContext.request.contextPath}/demo/articleAction!datagrid.action',method:'get',toolbar:toolbar,onClickRow: onClickRow">
		<thead>
			<tr>
				<th data-options="field:'author',width:100,align:'center'" width="10%">作者</th>
				<th data-options="field:'typeNo',width:100,align:'center',formatter:function(val, rows, index){
								if(val == 1) return '新闻中心';
								else if(val == 2) return '驰程分校';
                                else if(val == 3) return '集团介绍';
                                else if(val == 4) return '为您服务';
                                else if(val == 5) return '党团建设';
                                else if(val == 6) return '联系我们';
                                else if(val == 7) return '驾考宝典';
                                else if(val == 8) return '通知';
								else return '其它';
							}" width="20%">类型</th>
				<th data-options="field:'title',formatter:function(val, rows, index){
								return '<a href=${pageContext.request.contextPath}/web/articleAction!info.action?id=' + rows.id + ' target=_blank >' + val + '</a>';
							}" width="54%">标题</th>
                <th data-options="field:'updateTime',width:100,align:'center'" width="15%">更新时间</th>
			</tr>
		</thead>
	</table>
  
    <div id="dlg" class="easyui-dialog" title="内容编辑" style="width:800px;height:500px;padding:2px;" data-options="closed:true,iconCls:'icon-save',buttons: [{
					text:'取消',
					handler:function(){
                        $('#dlg').dialog('close');
					}
				},{
					text:'保存修改',
                    iconCls:'icon-ok',
					handler:saveArticle
				}]">
        <form id="demo_weixinsettings_addForm" method="post">
			<table class="tableForm">
                <input type="hidden" name="id" id="articleId" value="" />
                <input type="hidden" name="status" id="status" value="1" />
				<tr style="padding:15px;">
					<th style="width: 100px;">标题:</th>
					<td><input name="title"  id="articleTitle" class="easyui-validatebox" style="width:100%;" data-options="required:'true',missingMessage:'请填写文章标题'" /></td>
                    <th style="width: 100px;">&nbsp;</th>
                </tr>
                <tr style="padding:15px;">
					<th style="width: 55px;">类型:</th>
					<td>
						<input class="easyui-combobox" id="typeNo" name="typeNo" style="width:100%"
                			data-options="
                				url:'${pageContext.request.contextPath}/demo/json/article_type_combobox.json',
                				method:'get',
                				valueField:'value',
                				textField:'text',
                				panelHeight:'auto'
                			">
					</td>
                    <th style="width: 100px;">&nbsp;</th>
                </tr>
				<tr>
					<td colspan="8">
                        <script id="editor" type="text/plain" name="content" style="width:100%;height:238px;"></script>
                    </td>
				</tr>
			</table>
       </form>
    </div>
  
    <script type="text/javascript" charset="utf-8" src="../ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="../ueditor/ueditor.all.js"></script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="../ueditor/lang/zh-cn/zh-cn.js"></script>
  
	<script type="text/javascript">
	
		var ue = UE.getEditor('editor');
	
		var mydatagrid = $('#articleDataGrid');
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
			$('#articleId').val('');
			$('#articleTitle').val("");
			$('#typeNo').combobox('setValue', 1);
			ue.setContent('');
			$('#dlg').dialog('open');
		}  
		
		function edit(){
			if (clickIndex != undefined && mydatagrid.datagrid('validateRow', clickIndex)){
				editIndex = clickIndex;
				var row = mydatagrid.datagrid('getRows')[editIndex];
				//编辑时只能单选一行进行编辑
				if (row) {
					$('#articleId').val(row.id);
					$('#articleTitle').val(row.title);
					$('#typeNo').combobox('setValue', row.typeNo);
					ue.setContent(row.content);
					$('#dlg').dialog('open');
				} else {
					parent.sy.messagerAlert('提示信息', '请选择要更新的记录！', 'error');
				}
			} else {
				$.messager.alert('Warning', '请选择需要删除的行！');
			}
		}
		function remove(){
			if (clickIndex != undefined && mydatagrid.datagrid('validateRow', clickIndex)){
				editIndex = clickIndex;
				var row = mydatagrid.datagrid('getRows')[editIndex];
				$.messager.confirm('Confirm','您确定需要删除[ ' + row.title + ' ]的数据吗?',function(r){
				    if (r){
				    	saveSubmit('${pageContext.request.contextPath}/demo/articleAction!delete.action', {'ids':row.id}); // 删除数据
				    }
				});
			} else {
				$.messager.alert('Warning', '请选择需要删除的行！');
			}
		}
		
		function reload(){
			mydatagrid.datagrid('reload');
		}
		
		function saveArticle(){
			var f = $('#dlg').find('form');
			f.form('submit',{
				url : '${pageContext.request.contextPath}/demo/articleAction!save.action',
				type : 'json',
				success : function(data) {
						var json = $.parseJSON(data);
						if (json.success) {
							 mydatagrid.datagrid('reload');
							 $('#dlg').dialog('close');
						}
						parent.sy.messagerShow({
							msg : json.msg,
							title : '提示信息'
						});
				}
			});
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
					mydatagrid.datagrid('reload');
				},
				error : function(err){
					console.log(err);
					$.messager.show({
							title:'错误',
							msg:err.statusText,
							showType:'show'
						});
					mydatagrid.datagrid('reload');
				}
			});
		}
	</script>
</body>
</html>
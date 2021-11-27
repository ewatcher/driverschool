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
</head>
<body class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north',border:false,title:'查询功能'" style="height: 60px;overflow: hidden;" align="left">
      <!-- 菜单查询表单 -->
      <form id="menu_searchForm">
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
	  <table id="signUpDataGrid" class="easyui-datagrid" title="微信用户管理" 
			data-options="rownumbers:true,
			fit:true,
			pagination:true,
			singleSelect:true,
			url:'${pageContext.request.contextPath}/demo/signUpAction!datagrid.action',
			method:'get',
			toolbar:toolbar,
			onClickRow: onClickRow,
			onBeforeLoad:onMyBeforeLoad">
		 <thead>
			<tr>
                <th data-options="field:'organizationName',align:'center'" width="8%">所属机构</th>
				<th data-options="field:'upMobile',align:'center'" width="8%">联系方式</th>
				<th data-options="field:'upName',align:'center'" width="8%">称呼</th>
                <th data-options="field:'upAge',align:'center'" width="8%">年龄</th>
				<th data-options="field:'upCity',align:'center'" width="8%">所在地</th>
				<th data-options="field:'upTime',align:'center'" width="10%">报名时间</th>
                <th data-options="field:'status',align:'center',formatter:function(val, row, index){
                  if(val == 1){
                    return '<span style=color:green>已联系</span>';
                  } else {
                    return '<span style=color:red>未联系</span>';
                  }
                },editor:{type:'checkbox',options:{on:1,off:0}}" width="8%">联系</th>
                <th data-options="field:'comments',align:'center',editor:'textbox'" width="40%">备注</th>
			</tr>
		  </thead>
	   </table>
    </div>
	<script type="text/javascript">
		var userACLs=[]; 
		var mydatagrid = $('#signUpDataGrid');
		var toolbar = [{
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
			text:'保存',
			iconCls:'icon-save',
			handler:save
		},'-',{
			text:'取消',
			iconCls:'icon-undo',
			handler:cancel
		},'-',{
			text:'刷新',
			iconCls:'icon-reload',
			handler:reload
		},'-'];
		
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
			getUserACL();
			if(!userACLs[2]){
				$("#edit-btn-toolbar").linkbutton('disable');
			}
			if(!userACLs[3]){
				$("#remove-btn-toolbar").linkbutton('disable');
			}
		}
		
		var editIndex = undefined;  // 编辑状态的行标
		var clickIndex = undefined; // 选中状态的行标
		function onClickRow(index){
			clickIndex = index;
		}
		function endEditing(){
			if (editIndex == undefined){return true;}
			if (mydatagrid.datagrid('validateRow', editIndex)){ // 验证行行（editIndex）是否有效
				mydatagrid.datagrid('endEdit', editIndex); // 结束编辑
				editIndex = undefined; // 重置编辑行标记
				return true;
			} else {
				return false;
			}
		}
		
		function edit(){
			if (endEditing()){
				if(clickIndex != undefined && mydatagrid.datagrid('validateRow', clickIndex)){
					disableButton(); // 禁用功能按钮
					editIndex = clickIndex;
					mydatagrid.datagrid('selectRow', editIndex).datagrid('beginEdit', editIndex); // 开启编辑模式
				} else {
					$.messager.alert('Warning', '请选择需要修改的行！');
				}
			}
		}
		function remove(){
			if (endEditing()){
				if (clickIndex != undefined && mydatagrid.datagrid('validateRow', clickIndex)){
					editIndex = clickIndex;
					var row = mydatagrid.datagrid('getRows')[editIndex];
					$.messager.confirm('Confirm','您确定需要删除[ ' + row.upMobile + ' ]的数据吗?',function(r){
					    if (r){
					    	saveSubmit('${pageContext.request.contextPath}/demo/signUpAction!delete.action', {'ids':row.id}); // 删除数据
					    }
					});
				} else {
					$.messager.alert('Warning', '请选择需要删除的行！');
				}
			}
		}
		function save(){
			if (endEditing()){
				//var insertRows = mydatagrid.datagrid('getChanges','inserted'); // 得到新增的数据
   				var updateRows = mydatagrid.datagrid('getChanges','updated'); // 得到更新的数据
   				//var deleteRows = mydatagrid.datagrid('getChanges','deleted'); // 得到删除的数据
   				
   				if(updateRows.length > 0) {
   					saveSubmit('${pageContext.request.contextPath}/demo/signUpAction!edit.action', updateRows[0]);
   				} 
   			}
			enableButton(); // 开启功能按钮
		}
		function cancel(){
			mydatagrid.datagrid('rejectChanges'); // 回滚，撤销上次保存至今的所有编辑操作
			editIndex = undefined;
			clickIndex = undefined;
			enableButton(); // 开启功能按钮
		}
		function reload(){
			mydatagrid.datagrid('reload');
			enableButton(); // 开启功能按钮
		}
		function disableButton(){
			$('#edit-btn-toolbar').linkbutton('disable');
			$('#remove-btn-toolbar').linkbutton('disable');
		}
		function enableButton(){
			getUserACL();
			if(!userACLs[2]){
				$("#edit-btn-toolbar").linkbutton('disable');
			}
			if(!userACLs[3]){
				$("#remove-btn-toolbar").linkbutton('disable');
			}
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
		
		//查询按钮功能函数
		function submitSearch() {
			var o = sy.serializeObject($('#menu_searchForm'));
			console.log(o);
			mydatagrid.datagrid('reload', o);
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
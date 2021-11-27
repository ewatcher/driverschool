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
	   <table id="weixinMenuTreegrid" class="easyui-treegrid" title="微信自定义菜单" style="width:100%;height:auto"
			data-options="
				animate: true,
				rownumbers: true,
				fitColumns: true,
				url: '${pageContext.request.contextPath}/demo/weixinMenuAction!datagrid.action',
				method: 'get',
				idField: 'id',
				treeField: 'nickname',
				showFooter: true,
				toolbar:toolbar,
				onBeforeLoad:onMyBeforeLoad,
			">
		  <thead>
			<tr>
                <th data-options="field:'organizationName'" width="8%">所属机构</th>
				<th data-options="field:'nickname',editor:'textbox'" width="15%">名称(1-5个汉字)</th>
				<th data-options="field:'type',formatter:function(val, rows, index){
								if(val == 'click')  return '自动回复';
								if(val == 'view')  return '打开网页';
								if(val == 'menu')  return '展开下级菜单';
							},editor:{
								type:'combobox',
								options:{
									valueField:'value',
									textField:'text',
									method:'get',
									url:'${pageContext.request.contextPath}/demo/json/weixin_menu_combobox.json',
									required:true
								}
							}" width="10%">事件</th>
				<th data-options="field:'status',formatter:function(val, rows, index){
								if(val > 0) {
									return '开启';
								} else {
									return '<span style=color:red>关闭</span>';
								}
							},editor:{type:'checkbox',options:{on:1,off:0}}" width="5%">状态</th>
				<th data-options="field:'keyname',editor:'textbox'" width="5%">标识</th>
				<th data-options="field:'content',editor:'textbox',formatter:showAllMsg" width="57%">内容</th>
			</tr>
		  </thead>
	   </table>
    </div>
	<script type="text/javascript">
		var userACLs=[];
		var toolbar = [{
			id:'edit-btn-toolbar',
			text:'修改',
			iconCls:'icon-edit',
			handler:edit
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
		},'-',{
			id:'reset-btn-toolbar',
			text:'重置微信公众号菜单（谨慎使用）',
			iconCls:'icon-no',
			handler:submitMenuToWeixin
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
				$("#reset-btn-toolbar").linkbutton('disable');
			}
			if(!userACLs[3]){
				$("#remove-btn-toolbar").linkbutton('disable');
			}
		}
		
		var mydatagrid = $('#weixinMenuTreegrid');
		var editId = undefined;  // 编辑状态的行标
		
		function edit(){
			var row = mydatagrid.treegrid('getSelected');
			if (row){
				editId = row.id;
				mydatagrid.treegrid('beginEdit', editId);
				disableButton();
			} else {
				$.messager.alert('Warning', '请选择需要编辑的行！');
			}
		}
		
		function save(){
			
			if(editId != undefined){
				mydatagrid.treegrid('endEdit', editId);
				var rows = mydatagrid.treegrid('getChildren');
				for(var i = 0; i < rows.length; i++){
					if(rows[i].id == editId) {
						saveSubmit('${pageContext.request.contextPath}/demo/weixinMenuAction!edit.action', rows[i]);
						break;
					}
				}
			}
   				
			editId = undefined;
			enableButton(); // 开启功能按钮
		}
		function cancel(){
			if (editId != undefined){
				mydatagrid.treegrid('cancelEdit', editId);
				editId = undefined;
			}
			enableButton(); // 开启功能按钮
		}
		function reload(){
			mydatagrid.treegrid('reload');
			editId = undefined;
			enableButton(); // 开启功能按钮
		}
		function disableButton(){
			$('#edit-btn-toolbar').linkbutton('disable');
		}
		function enableButton(){
			if(!userACLs[2]){
				$("#edit-btn-toolbar").linkbutton('disable');
				$("#reset-btn-toolbar").linkbutton('disable');
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
					mydatagrid.treegrid('reload');
				},
				error : function(err){
					console.log(err);
					$.messager.show({
							title:'错误',
							msg:err.statusText,
							showType:'show'
						});
					mydatagrid.treegrid('reload');
				}
			});
		}
		function submitMenuToWeixin(){
			var o = sy.serializeObject($('#menu_searchForm'));
			var params = {};
			params.organizationId = o.organizationId;
			$.messager.confirm('提示', '您确定需要重置微信公众号菜单吗?', function(r){
				if (r){
					$.ajax({
						url : '${pageContext.request.contextPath}/demo/weixinMenuAction!push.action',
						data : params,
						dataType : 'json',
						async:false,
						success : function(data) {
							$.messager.show({
									title:'提示',
									msg:data.msg,
									showType:'show'
								});
						},
						error : function(err){
							$.messager.show({
									title:'错误',
									msg:err.statusText,
									showType:'show'
								});
						}
					});
				}
			});
		}
		
		//查询按钮功能函数
		function submitSearch() {
			var o = sy.serializeObject($('#menu_searchForm'));
			console.log(o);
			mydatagrid.treegrid('reload', o);
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
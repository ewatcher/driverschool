<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" charset="utf-8">
	$.messager.progress({
		text : '数据加载中....',
		interval : 100
	});
</script>
<div style="padding: 5px;">
	<form id="userForm" method="post">
		<input name="cid" type="hidden" />
		<input name="userType" type="hidden" />
		<table class="tableForm">
			<tr>
				<th style="width: 65px;">所属校区</th>
				<td colspan="3"><input name="schoolArea" style="width: 150px;" /></td>
			</tr>
			<tr>
				<th style="width: 55px;">登录名</th>
				<td><input name="cname" class="easyui-validatebox" data-options="required:'true',missingMessage:'请填写登录名称'" style="width: 150px;" />
				</td>
			</tr>
			<tr>
				<th style="width: 65px;">所属角色</th>
				<td colspan="2"><input name="roleIds" style="width: 150px;" /></td>
			</tr>
			<tr>
				<th>密码</th>
				<td><input name="cpwd" style="width:150px" type="password" />如果不修改请留空
				</td>
			</tr>
			<tr>
				<th style="width: 65px;">创建时间</th>
				<td><input name="ccreatedatetime" class="easyui-datetimebox" data-options="required:'true',editable:false" style="width: 155px;" />
				</td>
			</tr>
			<tr>
				<th style="width: 65px;">修改时间</th>
				<td><input name="cmodifydatetime"  style="width: 150px;" />
				</td>
			</tr>
		</table>
	</form>
</div>
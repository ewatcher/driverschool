<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" charset="utf-8">
	$.messager.progress({
		text : '数据加载中....',
		interval : 100
	});
</script>
<div align="center" style="padding: 5px;">
	<form method="post">
		<input type="hidden" name="id" />
		<table class="tableForm">
			<tr>
				<th>机构名称</th>
				<td><input name="name" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写机构名称'" /></td>
				<th>排序</th>
				<td><input name="sequence" class="easyui-numberspinner" data-options="min:0,max:999,editable:false,required:true,missingMessage:'请选择机构排序'" value="10" style="width:150px;" /></td>
			</tr>
			<tr>
				<th>机构地址</th>
				<td colspan="3"><textarea name="url" style="height: 50px;width: 97%;"></textarea>
				</td>
			</tr>
			<tr>
				<th>上级机构</th>
				<td><input name="parentId" style="width:155px;" />
				</td>
				<th>机构图标</th>
				<td><input name="iconcls" style="width:150px;" />
				</td>
			</tr>
		</table>
	</form>
</div>
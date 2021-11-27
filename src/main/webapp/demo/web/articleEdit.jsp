<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="padding: 5px;overflow: hidden;">
	<form id="demo_weixinsettings_editForm" method="post">
		<fieldset >
			<legend>微信设置信息</legend>
			<table class="tableForm">
				<tr>
					<th style="width: 55px;">别名:</th>
					<td><input name="nickname"  class="easyui-validatebox" data-options="required:'true',missingMessage:'请填写配置别名'" /></td>
					<th style="width: 55px;">标识:</th>
					<td><input name="keyname" class="easyui-validatebox" data-options="required:true,missingMessage:'请输入配置标识'" value="10" style="width:150px;" />
					<th style="width: 55px;">类型:</th>
					<td>
						<select class="easyui-combobox" name="type" style="width:100%;height:auto;">
							<option value="1">文本</option>
							<option value="2">URL地址</option>
	                		<option value="0">其它</option>
						</select>
					</td>
				</tr>
				<tr><th >描述:</th></tr>
				<tr>
					<td colspan="8"><textarea name="config" style="height: 150px;width: 98%;"></textarea></td>
				</tr>
			</table>
		</fieldset>
		<input type="hidden" name="id" />
        <input type="hidden" name="status" />
	</form>
</div>
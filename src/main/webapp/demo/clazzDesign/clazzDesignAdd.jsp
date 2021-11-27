<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="padding: 5px;overflow: hidden;">
	<form id="demo_person_addForm" method="post">
	<fieldset >
		<legend>开设班级基本信息</legend>
		<table class="tableForm">
			<tr><th style="width: 55px;">所属校区:</th>
				<td><input name="schoolArea" style="width:150px;" />
				<th style="width: 55px;">名称:</th>
				<td><input name="name"  class="easyui-validatebox" data-options="required:'true',missingMessage:'请填写开设班级名'" style="width:150px;"/></td>
				<th style="width: 55px;">费用:</th>
				<td><input name="fee"  style="width:150px;"/></td>
			<tr>
				<th style="width: 55px;">开班时长:</th>
				<td><input name="costTime"  style="width:150px;"/></td>
				<th style="width: 55px;">计时类型:</th>
				<td>
					<select class="easyui-combobox" name="classType" data-options="editable:false" style="width:150px;height:auto;">
						<option value="">请选择</option>
						<option value="1">计时</option>
                		<option value="0">不计时</option>
					</select>
				</td>
				<th style="width: 60px;">业务类型:</th>
				<td><input name="type"  style="width:150px;" style="width:150px" /></td>
				<tr><th >描述:</th></tr>
			<tr>
				<td colspan="8"><textarea name="comment" style="height: 100px;width: 98%;"></textarea></td>
			</tr>
		</table>
	</fieldset>
	</form>
</div>
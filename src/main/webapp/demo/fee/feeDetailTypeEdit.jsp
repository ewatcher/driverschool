<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="padding: 5px;overflow: hidden;">
	<form id="demo_feeDetailType_addForm" method="post">
	<input name="id" type="hidden">
	<input name="operator" type="hidden">
	<fieldset >
		<legend>收费类别信息导入</legend>
		<table class="tableForm">
			<tr>
				<th style="width: 65px;">所属部门</th>
				<td><input id="demo_feeDetailType_department" name="schoolArea"  style="width:160px;" /></td>
				<th style="width: 65px;">收费名称:</th>
				<td><input name="itemName" class="easyui-validatebox" data-options="required:'true',validType:'validChinese',missingMessage:'请填写收费名称'" style="width: 130px;"/></td>
				<th style="width: 65px;">收费金额:</th>
				<td><input name="money"
				class="easyui-validatebox" data-options="required:false,validType:'doublePrice',missingMessage:'请填写收费金额'"
				onblur="var reg = /^(\d+)$/; if(reg.test(this.value)) this.value=this.value.replace(reg,'$1.00');"
				onfocus="var reg =/^(\d+)\.00$/;if(reg.test(this.value)) this.value=this.value.replace(reg,'$1');"
				/>
				</td>
				<th style="width: 65px;">创建日期:</th>
				<td><input name="createTime"
					 style="width: 120px;" />
				</td>
			<tr>
			<tr>
				<th style="width: 65px;">排序:</th>
				<td><input name="orderNo"  style="width: 125px;"/></td>
			<tr>
			<tr><th >备注:</th></tr>
			<tr><td colspan="8"><textarea name="comment" style="height: 100px;width: 98%;"></textarea></td></tr>
				
		</table>
	</fieldset>
	</form>
</div>
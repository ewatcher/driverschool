<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding: 5px;overflow: hidden;">
	<form id="oilcardForm" method="post">
	<fieldset >
		<legend>油卡基本信息</legend>
		<table class="tableForm">
			<tr>
				<th style="width: 65px;">所属校区:</th>
				<td ><input name="schoolArea" style="width:150px;"/></td>
				<th style="width: 65px;">卡号:</th>
				<td ><input name="cardNo" style="width: 150px;" /></td>
				<th style="width: 65px;">日期:</th>
				<td><input name="createTime" class="easyui-datebox" data-options="showSeconds:false,editable:false" style="width: 155px;" /></td>
			</tr>
			<tr><th >备注:</th><td>(最多输入150个字)</td></tr>
			<tr><td colspan="6"><textarea name="remarks" style="height: 100px;width: 98%;"></textarea></td></tr>
		</table>
	</fieldset>
	
	</form>
</div>
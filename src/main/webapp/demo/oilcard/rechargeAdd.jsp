<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="padding: 5px;overflow: hidden;">
	<form id="demo_rechargeOilcard_addForm" method="post">
	<input type="hidden" name="oilCardId"/>
	<input type="hidden" name="schoolArea"/>
	<fieldset >
		<legend>油卡基本信息</legend>
		<table class="tableForm">
			<tr>
				<th style="width:65px;">所属校区：</th>
				<td><input class="recharge_add_readonly" name="schoolAreaName" style="width:155px;"/></td>
				<th style="width:65px;">卡号：</th>
				<td><input class="recharge_add_readonly" name="oilCardNo" style="width:155px;"/></td>
				<th style="width:65px;">当前余额：</th>
				<td><input class="recharge_add_readonly" name="beforeBalance" style="width:155px;"/></td>
				<th style="width:65px;">状态：</th>
				<td><input class="recharge_add_readonly" name="oilcardState" style="width:155px;"/></td>
			</tr>
		</table>
	</fieldset>
	<fieldset >
		<legend>充值信息</legend>
		<table class="tableForm">
			<tr>
				<th style="width:65px;">充值金额：</th>
				<td><input name="money" style="width:155px;"/></td>
				<th style="width:75px;">充值后金额：</th>
				<td><input class="recharge_add_readonly" name="afterBalance" style="width:155px;"/></td>
				<th style="width:65px;">日期：</th>
				<td><input name="rechargeDate" style="width:155px;"/></td>
			</tr>	
			<tr><th >备注:</th><td>(最多输入150个字)</td></tr>
			<tr>
				<td colspan="8"><textarea name="remarks" style="height: 100px;width: 98%;"></textarea></td>
			</tr>
			
		</table>
	</fieldset>
	</form>
</div>
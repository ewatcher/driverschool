<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.tuocheng.util.base.Util"%>
<div style="padding: 5px;height:125px;overflow: hidden;">
	<form id="demo_autocarArrange_updateForm" method="post">
	<input name="id" type="hidden"/>
	<input name="cardId" type="hidden" />
	<input name="schoolArea" type="hidden" />
	<fieldset >
		<legend style="color:red">1.油卡基本信息</legend>
		<table class="tableForm">
			<tr>
				<th style="width: 65px;">所属校区:</th>
				<td ><input class="demo_rechargeOilcard_update" name="schoolAreaName" style="width: 155px;" /></td>
				<th style="width: 65px;">卡号:</th>
				<td><input class="demo_rechargeOilcard_update" name="oilCardNo" style="width: 155px;" /></td>
				<th style="width: 65px;">卡余额:</th>
				<td><input class="demo_rechargeOilcard_update" name="oilcardBalance" style="width: 155px;" /></td>
				<th style="width: 65px;">状态:</th>
				<td><input class="demo_rechargeOilcard_update" name="oilcardState" style="width: 155px;" /></td>
			<tr>
		</table>
	</fieldset>
	<fieldset >
		<legend style="color:red">2.油卡充值查询</legend>
		<table class="tableForm">
			<tr>
				<th style="width:65px">教练:</th>
				<td><input  name="usingPerson"  style="width:150px;" /></td>
				<th style="width:65px">车牌号:</th>
				<td><input  name="carNo"  style="width:150px;" /></td>
				<th style="width: 85px;">日期开始:</th>
				<td ><input name="usingDateStart" style="width: 155px;" /></td>
				<th style="width: 85px;">日期结束:</th>
				<td ><input name="usingDateEnd" style="width: 155px;" /></td>
				<td colspan="2">
					<a href="javascript:void(0);" id="oilcardUsing_a_searche">查询</a>
					<a href="javascript:void(0);" id="oilcardUsing_a_cancel">取消</a>
				</td>
				
			<tr>
		</table>
	</fieldset>
	</form>
</div>
<!-- datagrid数据在center中展现 -->
	<div data-options="region:'center',border:false"
		style="height:560px;overflow: hidden;">
		<table id="detail_oilcardUsing_datagrid"></table>
	</div>

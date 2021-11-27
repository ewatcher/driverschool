<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding: 5px;height:120px;overflow: hidden;">
	<form id="demo_trainerReservationDetail_updateForm" method="post">
	<input name="id" type="hidden"/>
	<input name="trainerId" type="hidden" />
	<fieldset >
		<legend>教练基本信息</legend>
		<table class="tableForm">
			<tr>
				<th style="width: 55px;">所属校区:</th>
				<td ><input class="demo_detail_update" name="trainerSchoolArea" style="width: 155px;" /></td>
				<th style="width: 55px;">姓名:</th>
				<td><input class="demo_detail_update" name="trainerName" style="width: 155px;" /></td>
					<th style="width: 55px;">身份证:</th>
				<td><input class="demo_detail_update" name="trainerIdentityId" style="width: 155px;" /></td>
				<th style="width: 55px;">联系电话:</th>
				<td><input class="demo_detail_update" name="trainerPhone" style="width: 155px;" /></td>
			<tr>
		</table>
	</fieldset>
	<fieldset >
		<legend>预约明细查询</legend>
		<table class="tableForm">
			<tr>
				<th style="width: 55px;">月份开始:</th>
				<td ><input name="beginMonth" style="width: 155px;" /></td>
				<th style="width: 55px;">月份结束:</th>
				<td ><input name="endMonth" style="width: 155px;" /></td>
				<td colspan="2">
					<a href="javascript:void(0);" id="reservationDetail_a_searche">查询</a>
					<a href="javascript:void(0);" id="reservationDetail_a_cancel">取消</a>
				</td>
				
			<tr>
		</table>
	</fieldset>
	</form>
</div>
<!-- datagrid数据在center中展现 -->
	<div data-options="region:'center',border:false"
		style="height:410px;overflow: hidden;">
		<table id="demo_detailReservation_datagrid"></table>
	</div>
	<!-- 操作按钮事件 -->
	<div id="demo_timing_menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="edit();" data-options="iconCls:'icon-edit'">更新</div>
	</div>

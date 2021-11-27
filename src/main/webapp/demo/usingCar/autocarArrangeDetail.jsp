<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding: 5px;height:120px;overflow: hidden;">
	<form id="demo_autocarArrange_updateForm" method="post">
	<input name="id" type="hidden"/>
	<input name="carId" type="hidden" />
	<input name="schoolArea" type="hidden" />
	<fieldset >
		<legend style="color:red">1.车辆信息</legend>
		<table class="tableForm">
			<tr>
				<th style="width: 65px;">所属校区:</th>
				<td ><input class="demo_detail_update" name="schoolAreaName" style="width: 155px;" /></td>
				<th style="width: 65px;">车牌号:</th>
				<td><input class="demo_detail_update" name="carNo" style="width: 155px;" /></td>
			<tr>
		</table>
	</fieldset>
	<fieldset >
		<legend style="color:red">2.排班明细查询</legend>
		<table class="tableForm">
			<tr>
				<th style="width: 85px;">排班日期开始:</th>
				<td ><input name="arrangeDateBegin" style="width: 155px;" /></td>
				<th style="width: 85px;">排班日期结束:</th>
				<td ><input name="arrangeDateEnd" style="width: 155px;" /></td>
				<td colspan="2">
					<a href="javascript:void(0);" id="autocarArrange_a_searche">查询</a>
					<a href="javascript:void(0);" id="autocarArrange_a_cancel">取消</a>
				</td>
				
			<tr>
		</table>
	</fieldset>
	</form>
</div>
<!-- datagrid数据在center中展现 -->
	<div data-options="region:'center',border:false"
		style="height:585px;overflow: hidden;">
		<table id="detail_autocar_datagrid"></table>
	</div>

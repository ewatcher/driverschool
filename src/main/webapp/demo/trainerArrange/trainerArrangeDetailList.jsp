<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="padding: 5px;overflow: hidden;">
	<form id="demo_trainerArrange_addForm" method="post">
	<input type="hidden" name="id"/>
	<input type="hidden" name="no"/>
	<input type="hidden" name="trainerId"/>
	<input type="hidden" name="arrangeDate"/>
	<input type="hidden" name="arrageFlag"/>
	<fieldset >
		<legend>教练排班基本信息</legend>
		<table class="tableForm">
			<tr>
				<th style="width: 55px;">所属校区:</th>
				<td ><input class="demo_detail_update" name="schoolArea" style="width: 155px;" /></td>
				<th style="width: 55px;">姓名:</th>
				<td><input class="demo_detail_update" name="trainerName" style="width: 155px;" /></td>
					<th style="width: 55px;">身份证:</th>
				<td><input class="demo_detail_update" name="trainerIdentity" style="width: 155px;" /></td>
				<th style="width: 55px;">联系电话:</th>
				<td><input class="demo_detail_update" name="trainerPhone" style="width: 155px;" /></td>
			<tr>
		</table>
	</fieldset>
	</form>
</div>
<!-- datagrid数据在center中展现 -->
	<div data-options="region:'center',border:false"
		style="height:635px;overflow: hidden;">
		<table id="demo_trainerArrangedetail_datagrid"></table>
	</div>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="padding: 5px;overflow: hidden;">
	<form id="demo_car_addForm" method="post">
	<input name="id" type="hidden" />
	<input name="carId" type="hidden" />
	<input name="trainerId" type="hidden" />
	<fieldset >
		<legend>选择车辆、教练</legend>
		<table class="tableForm">
			<tr>
				<th style="width: 50px;">所属校区:</th>
				<td><input name="schoolArea"  style="width: 155px;"/></td>
				<th style="width: 55px;">车辆选择:</th>
				<th style="width: 100px;"  ><a href="javascript:void(0);" id="usingcar_a_selectcar"  >选择</a></th>
				<th style="width: 55px;">车牌号:</th>
				<td><input name="carNo" class="demo_disabled_usingcar" style="width: 155px;" /></td>
				<th style="width: 65px;">行驶证号:</th>
				<td><input name="carLicenseNo" class="demo_disabled_usingcar" style="width: 155px;" /></td>
			</tr>
			<tr>
				<th style="width: 55px;">教练选择:</th>
				<th style="width: 100px;"  ><a href="javascript:void(0);" id="usingcar_a_selecttrainer"  >选择</a></th>
				<th style="width: 55px;">姓名:</th>
				<td><input name="trainerName" class="demo_disabled_usingcar" style="width: 155px;" /></td>
				<th style="width: 65px;">身份证:</th>
				<td><input name="trainerIdentity" class="demo_disabled_usingcar" style="width: 155px;" /></td>
				<th style="width: 65px;">联系电话:</th>
				<td><input name="trainerPhone" class="demo_disabled_usingcar" style="width: 155px;" /></td>
			</tr>
		</table>
	</fieldset>
	<fieldset >
		<legend>车辆使用基本信息录入</legend>
		<table class="tableForm">
			<tr>
				<th style="width: 65px;">使用日期:</th>
				<td><input name="usingDate" class="easyui-datebox" data-options="editable:false,required:true" style="width: 155px;"/></td>
				<th style="width: 65px;">使用说明:</th>
				<td><input name="reason"  style="width: 155px;" /></td>
				<th style="width: 65px;">使用场地:</th>
				<td><input name="place"  style="width: 155px;" /></td>
			</tr>
			<tr>
				<th style="width: 65px;">开始时间:</th>
				<td><input name="startTime" style="width: 155px;"/></td>
				<th style="width: 65px;">结束时间:</th>
				<td><input name="endTime" style="width: 155px;"/></td>
				<th style="width: 65px;">开始里程:</th>
				<td><input name="startMile" class="easyui-numberbox" data-options="min:0, max:2147483648" style="width: 155px;"/></td>
				<th style="width: 65px;">结束里程:</th>
				<td><input name="endMile" class="easyui-numberbox" data-options="min:0, max:2147483648" style="width: 155px;"/></td>
			</tr>
			<tr>
				<th style="width: 55px;">备注:</th>
				<td colspan="7"><textarea name="comment" style="height: 100px;width: 98%;"></textarea></td>
			</tr>
			
		</table>
	</fieldset>
	</form>
</div>
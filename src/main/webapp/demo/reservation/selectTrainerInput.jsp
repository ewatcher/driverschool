<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="padding: 5px;overflow: hidden ;height:655px">
	<form id="demo_reservation_selectTrainer" method="post">
	<input name="teachingType" type="hidden"/>
	<input name="workingType" type="hidden"/>
	<fieldset >
		<legend>预约教练筛选</legend>
		<table class="tableForm">
			<tr>
				<th style="width: 65px;">所属校区:</th>
				<td><input  name="schoolArea" style="width: 155px;"/></td>
				<th style="width: 55px;">姓名:</th>
				<td><input class="demo_reservation_clear" name="trainerName"  style="width: 150px;"/></td>
				<th style="width: 65px;">身份证号:</th>
				<td><input class="demo_reservation_clear" name="trainerIdentityId" style="width: 150px;" /></td>
				<td>
					<a href="javascript:void(0);" id="reservation_trainer_a_search" >查询</a>
					<a href="javascript:void(0);" id="reservation_trainer_a_cancel" >重置</a>
				</td>
			</tr>
		</table>
	</fieldset>
	
	<fieldset style="height:565px;">
		<legend >当前选择预约教练信息</legend>
		<table id="demo_reservation_selectTrainer" class="tableForm"></table>
	</fieldset>
	</form>
</div>
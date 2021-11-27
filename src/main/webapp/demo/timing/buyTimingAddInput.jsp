<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding: 5px;height:270px;overflow: hidden;">
	<form id="demo_timing_updateForm" method="post">
	<input name="id" type="hidden"/>
	<input name="studentId" type="hidden"/>
	<input name="trainerId" type="hidden"/>
	<input name="timingId" type="hidden" />
	<input name="timingType" type="hidden" value="0"/>
	<fieldset >
		<legend>学员购买学时信息录入</legend>
		<table class="tableForm">
			<tr>
				<th style="width: 55px;">日期:</th>
				<td>
					<input name="date" class="easyui-datetimebox" data-options="showSeconds: false,editable:false"
					 style="width: 155px;" />
				</td>
				<th style="width: 55px;">课时:</th>
				<td><input  name="useTiming" style="width: 155px;" /></td>
			</tr>
		</table>
	</fieldset>
	</form>
</div>


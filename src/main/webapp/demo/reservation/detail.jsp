<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="padding: 5px;overflow: hidden ;height:730px">
	<form id="demo_reservation_addForm" method="post">
	<input name="studentId" type="hidden"/>
	<input name="trainerId" type="hidden"/>
	<input name="trainerReservationDetailId" type="hidden"/>
	<input name="date" type="hidden"/>
	<input name="reservationType" type="hidden"/>
	<input name="timeStart" type="hidden"/>
	<input name="timeEnd" type="hidden"/>
	<input name="trainerReservationId" type="hidden" />
	<input name="reservationId" type="hidden" />
	<input name="fieldOptionFlag" type="hidden" />
	<input name="item7Studentid" type="hidden" />
	<input name="item7TrainContent" type="hidden" />
	<input name="item8Studentid" type="hidden" />
	<input name="item8TrainContent" type="hidden" />
	<input name="item9Studentid" type="hidden" />
	<input name="item9TrainContent" type="hidden" />
	<input name="item10Studentid" type="hidden" />
	<input name="item10TrainContent" type="hidden" />
	<input name="item11Studentid" type="hidden" />
	<input name="item11TrainContent" type="hidden" />
	<input name="item12Studentid" type="hidden" />
	<input name="item12TrainContent" type="hidden" />
	<input name="item13Studentid" type="hidden" />
	<input name="item13TrainContent" type="hidden" />
	<input name="item14Studentid" type="hidden" />
	<input name="item14TrainContent" type="hidden" />
	<input name="item15Studentid" type="hidden" />
	<input name="item15TrainContent" type="hidden" />
	<input name="item16Studentid" type="hidden" />
	<input name="item16TrainContent" type="hidden" />
	<input name="item17Studentid" type="hidden" />
	<input name="item17TrainContent" type="hidden" />
	<input name="item18Studentid" type="hidden" />
	<input name="item18TrainContent" type="hidden" />
	<input name="reservationFieldId7" type="hidden" />
	<input name="reservationFieldId8" type="hidden" />
	<input name="reservationFieldId9" type="hidden" />
	<input name="reservationFieldId10" type="hidden" />
	<input name="reservationFieldId11" type="hidden" />
	<input name="reservationFieldId12" type="hidden" />
	<input name="reservationFieldId13" type="hidden" />
	<input name="reservationFieldId14" type="hidden" />
	<input name="reservationFieldId15" type="hidden" />
	<input name="reservationFieldId16" type="hidden" />
	<input name="reservationFieldId17" type="hidden" />
	<input name="reservationFieldId18" type="hidden" />
	<fieldset >
		<legend>1.选择学员信息</legend>
		<table class="tableForm">
			<tr>
				<th style="width: 80px;">所属校区:</th>
				<td><input class="reservation_add_readonly" name="schoolArea" style="width: 150px;"/></td>
				<th style="width: 80px;">学员姓名:</th>
				<td><input class="reservation_add_readonly" name="studentName" style="width: 150px;"/></td>
				<th style="width: 80px;">学员编号:</th>
				<td><input class="reservation_add_readonly" name="studentNo" style="width: 150px;"/></td>
				<th style="width: 90px;">身份证:</th>
				<td><input class="reservation_add_readonly" name="studentIdentityId"  style="width: 155px;"/></td>
			</tr>
		</table>
	</fieldset>
	<fieldset >
		<legend>2.当前学员教练信息</legend>
		<table class="tableForm">
			<tr>
				<th style="width: 60px;">教练姓名:</th>
				<td><input class="reservation_add_readonly" name="trainerName" style="width: 155px;"/></td>
				<th style="width: 80px;">教练身份证:</th>
				<td><input class="reservation_add_readonly" name="trainerIdentityId"  style="width: 155px;"/></td>
				<th style="width: 60px;">联系电话:</th>
				<td><input class="reservation_add_readonly" name="trainerPhone" style="width: 155px;"/></td>
			</tr>
		</table>
	</fieldset>
	<fieldset >
		<legend>3.选择预约内容</legend>
		<div data-options="region:'center',border:false"
		style="height:410px;overflow: hidden;">
		<table id="demo_detailReservation_datagrid"></table>
	</div>
	</fieldset>
	
	<fieldset >
		<legend>备注信息</legend>
		<textarea name="comment" class="easyui-validatebox" data-options="validType:'contentSize[0,150]'" style="height: 100px;width: 98%;"></textarea>
		</fieldset>
	</form>
</div>
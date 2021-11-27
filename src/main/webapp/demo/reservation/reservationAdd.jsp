<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="padding: 5px;overflow: hidden ;height:720px">
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
	<input name="item19Studentid" type="hidden" />
	<input name="item19TrainContent" type="hidden" />
	<input name="item20Studentid" type="hidden" />
	<input name="item20TrainContent" type="hidden" />
	<input name="item21Studentid" type="hidden" />
	<input name="item21TrainContent" type="hidden" />
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
	<input name="reservationFieldId19" type="hidden" />
	<input name="reservationFieldId20" type="hidden" />
	<input name="reservationFieldId21" type="hidden" />
	<input name="reservation7MaxTotal" type="hidden" />
	<input name="reservation8MaxTotal" type="hidden" />
	<input name="reservation9MaxTotal" type="hidden" />
	<input name="reservation10MaxTotal" type="hidden" />
	<input name="reservation11MaxTotal" type="hidden" />
	<input name="reservation12MaxTotal" type="hidden" />
	<input name="reservation13MaxTotal" type="hidden" />
	<input name="reservation14MaxTotal" type="hidden" />
	<input name="reservation15MaxTotal" type="hidden" />
	<input name="reservation16MaxTotal" type="hidden" />
	<input name="reservation17MaxTotal" type="hidden" />
	<input name="reservation18MaxTotal" type="hidden" />
	<input name="reservation19MaxTotal" type="hidden" />
	<input name="reservation20MaxTotal" type="hidden" />
	<input name="reservation21MaxTotal" type="hidden" />
	<input name="cancelFlag" type="hidden" />
	<fieldset >
		<legend><div style="text-align:center;color:red">第一步：选择学员信息:</div></legend>
		<table class="tableForm">
			<tr>
				<th style="width: 80px;">所属校区:</th>
				<td><input  name="studentOrganization" style="width: 150px;"/></td>
				<th style="width: 80px;">学员姓名:</th>
				<td><input class="reservation_add_readonly" name="studentName" style="width: 150px;"/></td>
				<th style="width: 80px;">学员编号:</th>
				<td><input class="reservation_add_readonly" name="studentNo" style="width: 150px;"/></td>
				<th style="width: 90px;">身份证:</th>
				<td><input class="reservation_add_readonly" name="studentIdentityId"  style="width: 155px;"/></td>
			</tr>
			<tr>
				<th style="width: 80px;">驾照类型:</th>
				<td><input class="reservation_add_readonly"  name="selectDriverType" style="width: 150px;"/></td>
				<th style="width: 80px;">学员类型:</th>
				<td><input class="reservation_add_readonly" name="studentType"  style="width: 155px;"/></td>
				<td colspan="3">
						<a href="javascript:void(0);" id="reservation_a_student" class="easyui-linkbutton" >选择学员</a>
				</td>
			</tr>
		</table>
	</fieldset>
	<fieldset >
		<legend><div style="text-align:center;color:red">第二步：.预约类型</div></legend>
		<table class="tableForm">
			<tr>
				<th style="width: 80px;"><div style="text-align:center;color:red">预约类型:</div></th>
				<td><input   name="selectReservationType" style="width: 150px;"/></td>
			</tr>
		</table>
	</fieldset>
	<fieldset >
		<legend><div style="text-align:center;color:red">第三步：.当前预约教练信息</div></legend>
		<table class="tableForm">
			<tr>
				<th style="width: 60px;">教练姓名:</th>
				<td><input class="reservation_add_readonly" name="trainerName" style="width: 155px;"/></td>
				<th style="width: 80px;">教练身份证:</th>
				<td><input class="reservation_add_readonly" name="trainerIdentityId"  style="width: 155px;"/></td>
				<th style="width: 60px;">联系电话:</th>
				<td><input class="reservation_add_readonly" name="trainerPhone" style="width: 155px;"/></td>
				<th style="width: 80px;">排班类型:</th>
				<td><input class="reservation_add_readonly"  name="trainerWorkingType" style="width: 150px;"/></td>
			</tr>
			<tr>
				<th style="width: 80px;">教练类型:</th>
				<td><input class="reservation_add_readonly" name="trainerType"  style="width: 155px;"/></td>
				<td colspan="2">
						<a href="javascript:void(0);" id="reservation_a_selectTrainer" class="easyui-linkbutton" >选择教练</a>
				</td>
			</tr>
		</table>
	</fieldset>
	<fieldset >
		<legend><div style="text-align:center;color:red">第四步：.选择预约时间段</div></legend>
		<div data-options="region:'center',border:false" style="height:450px;overflow: hidden;">
			<table id="demo_detailReservation_datagrid"></table>
		</div>
	</fieldset>
	</form>
</div>
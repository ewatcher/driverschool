<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="padding: 5px;overflow: hidden;">
	<form id="demo_trainerArrange_addForm" method="post">
	<input type="hidden" name="id"/>
	<input type="hidden" name="no"/>
	<input type="hidden" name="trainerId"/>
	<input type="hidden" name="trainerReservationDetailId"/>
	<input type="hidden" name="arrageFlag"/>
	<input type="hidden" name="reservationDate"/>
	<fieldset >
		<legend>教练排班基本信息</legend>
		<table class="tableForm">
			<tr>
				<th >所属校区:</th>
				<td><input name="schoolArea" class="demo_trainerArrangeAdd_disabled" style="width:150px;" /></td>
				<th >教练:</th>
				<td><input name="trainerName" class="demo_trainerArrangeAdd_disabled" style="width:80px;" /></td>
			</tr>
			<tr>
				<th >施教类型:</th>
				<td><input name="teachingType"  class="demo_trainerArrangeAdd_disabled" style="width:150px;" /></td>
				<th >排班日期:</th>
				<td><input name="reservationDateTemp" class="demo_trainerArrangeAdd_disabled" style="width:150px;color: red;" />
				</td>
			</tr>
			<tr>
				<th>训练时段：</th>
				<td colspan="3">
					7:00- -8:00	<input type="checkbox" class="timeItem_checked" name="ItemTime7"  value =1 style="width:20px;"/> <input name="item7TrainContent" class="arrange_add_timeflag" style="width:70px;"/> <br>
					8:00- -9:00	<input type="checkbox" class="timeItem_checked" name="ItemTime8"  value =1 style="width:20px;"/> <input name="item8TrainContent" class="arrange_add_timeflag" style="width:70px;"/> <br>
					9:00- 10:00	<input type="checkbox" class="timeItem_checked" name="ItemTime9" value =1 style="width:20px;"/> <input name="item9TrainContent" class="arrange_add_timeflag" style="width:70px;"/> <br>
					10:00-11:00<input type="checkbox" class="timeItem_checked" name="ItemTime10" value =1 style="width:20px;"/> <input name="item10TrainContent" class="arrange_add_timeflag" style="width:70px;"/> <br>
					11:00-12:00<input type="checkbox" class="timeItem_checked" name="ItemTime11" value =1 style="width:20px;"/> <input name="item11TrainContent" class="arrange_add_timeflag" style="width:70px;"/> <br>
					12:00-13:00<input type="checkbox" class="timeItem_checked" name="ItemTime12" value =1 style="width:20px;"/> <input name="item12TrainContent" class="arrange_add_timeflag" style="width:70px;"/> <br>
					13:00-14:00<input type="checkbox" class="timeItem_checked" name="ItemTime13" value =1 style="width:20px;"/> <input name="item13TrainContent" class="arrange_add_timeflag" style="width:70px;"/> <br>
					14:00-15:00<input type="checkbox" class="timeItem_checked" name="ItemTime14" value =1 style="width:20px;"/> <input name="item14TrainContent" class="arrange_add_timeflag" style="width:70px;"/> <br>
					15:00-16:00<input type="checkbox" class="timeItem_checked" name="ItemTime15" value =1 style="width:20px;"/> <input name="item15TrainContent" class="arrange_add_timeflag" style="width:70px;"/> <br>
					16:00-17:00<input type="checkbox" class="timeItem_checked" name="ItemTime16" value =1 style="width:20px;"/> <input name="item16TrainContent" class="arrange_add_timeflag" style="width:70px;"/> <br>
					17:00-18:00<input type="checkbox" class="timeItem_checked" name="ItemTime17" value =1 style="width:20px;"/> <input name="item17TrainContent" class="arrange_add_timeflag" style="width:70px;"/> <br>
					18:00-19:00<input type="checkbox" class="timeItem_checked" name="ItemTime18" value =1 style="width:20px;"/> <input name="item18TrainContent" class="arrange_add_timeflag" style="width:70px;"/> <br>
					19:00-20:00<input type="checkbox" class="timeItem_checked" name="ItemTime19" value =1 style="width:20px;"/> <input name="item19TrainContent" class="arrange_add_timeflag" style="width:70px;"/> <br>
					20:00-21:00<input type="checkbox" class="timeItem_checked" name="ItemTime20" value =1 style="width:20px;"/> <input name="item20TrainContent" class="arrange_add_timeflag" style="width:70px;"/> <br>
					21:00-22:00<input type="checkbox" class="timeItem_checked" name="ItemTime21" value =1 style="width:20px;"/> <input name="item21TrainContent" class="arrange_add_timeflag" style="width:70px;"/> <br>
				</td>
			</tr>
		</table>
	</fieldset>
	</form>
</div>
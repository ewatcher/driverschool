<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="padding: 5px;overflow: hidden;">
	<form id="demo_trainerArrange_addForm" method="post">
	<input type="hidden" name="trainerId"/>
	<input type="hidden" name="arrangeFlag"/>
	<fieldset >
		<legend>教练排班基本信息</legend>
		<table class="tableForm">
			<tr>
				<th >所属校区:</th>
				<td><input name="schoolArea" style="width:150px;" /></td>
				<th >教练:</th>
				<td><input name="trainerName" style="width:80px;" />
				</td>
				
			</tr>
			<tr>
				<th >施教类型:</th>
				<td><input name="teachingType" style="width:150px;" /></td>
				<th >排班类型:</th>
				<td><input name="workingType" style="width:150px;" />
				</td>
				
			</tr>
			<tr>
				<th>年份：</th>
				<td><input name="year" style="width:155px;"/>
				</td>
				<th>月份：</th>
				<td>
					<input name="month" style="width:155px;"/>
				</td>
			</tr>
			<tr>
				<th >日(多选):</th>
				<td  colspan="3"><input name="noArrangeDates" style="width:255px;height:55px" /></td>
			</tr>
			<tr>
				<th>训练时段：</th>
				<td colspan="3">
					教练排班时段选择：全选	<input type="checkbox" name="selectAll"  value =1 style="width:20px;"/>取消全选: <input type="checkbox" name="cancelAll"  value =1 style="width:20px;"/> <br>
					7:00- -8:00	<input type="checkbox" class="timeItem_checked" name="ItemTime7"  value =1 style="width:20px;"/> <input name="ItemTime7flag" class="arrange_add_timeflag" style="width:70px;"/> <br>
					8:00- -9:00	<input type="checkbox" class="timeItem_checked" name="ItemTime8"  value =1 style="width:20px;"/> <input name="ItemTime8flag" class="arrange_add_timeflag" style="width:70px;"/> <br>
					9:00- 10:00	<input type="checkbox" class="timeItem_checked" name="ItemTime9" value =1 style="width:20px;"/> <input name="ItemTime9flag" class="arrange_add_timeflag" style="width:70px;"/> <br>
					10:00-11:00<input type="checkbox" class="timeItem_checked" name="ItemTime10" value =1 style="width:20px;"/> <input name="ItemTime10flag" class="arrange_add_timeflag" style="width:70px;"/> <br>
					11:00-12:00<input type="checkbox" class="timeItem_checked" name="ItemTime11" value =1 style="width:20px;"/> <input name="ItemTime11flag" class="arrange_add_timeflag" style="width:70px;"/> <br>
					12:00-13:00<input type="checkbox" class="timeItem_checked" name="ItemTime12" value =1 style="width:20px;"/> <input name="ItemTime12flag" class="arrange_add_timeflag" style="width:70px;"/> <br>
					13:00-14:00<input type="checkbox" class="timeItem_checked" name="ItemTime13" value =1 style="width:20px;"/> <input name="ItemTime13flag" class="arrange_add_timeflag" style="width:70px;"/> <br>
					14:00-15:00<input type="checkbox" class="timeItem_checked" name="ItemTime14" value =1 style="width:20px;"/> <input name="ItemTime14flag" class="arrange_add_timeflag" style="width:70px;"/> <br>
					15:00-16:00<input type="checkbox" class="timeItem_checked" name="ItemTime15" value =1 style="width:20px;"/> <input name="ItemTime15flag" class="arrange_add_timeflag" style="width:70px;"/> <br>
					16:00-17:00<input type="checkbox" class="timeItem_checked" name="ItemTime16" value =1 style="width:20px;"/> <input name="ItemTime16flag" class="arrange_add_timeflag" style="width:70px;"/> <br>
					17:00-18:00<input type="checkbox" class="timeItem_checked" name="ItemTime17" value =1 style="width:20px;"/> <input name="ItemTime17flag" class="arrange_add_timeflag" style="width:70px;"/> <br>
					18:00-19:00<input type="checkbox" class="timeItem_checked" name="ItemTime18" value =1 style="width:20px;"/> <input name="ItemTime18flag" class="arrange_add_timeflag" style="width:70px;"/> <br>
					19:00-20:00<input type="checkbox" class="timeItem_checked" name="ItemTime19" value =1 style="width:20px;"/> <input name="ItemTime19flag" class="arrange_add_timeflag" style="width:70px;"/> <br>
					20:00-21:00<input type="checkbox" class="timeItem_checked" name="ItemTime20" value =1 style="width:20px;"/> <input name="ItemTime20flag" class="arrange_add_timeflag" style="width:70px;"/> <br>
					21:00-22:00<input type="checkbox" class="timeItem_checked" name="ItemTime21" value =1 style="width:20px;"/> <input name="ItemTime21flag" class="arrange_add_timeflag" style="width:70px;"/> <br>
				</td>
			</tr>
		</table>
	</fieldset>
	</form>
</div>
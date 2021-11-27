<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.tuocheng.util.base.Util"%>
<%
  String orgId = Util.objToString(session.getAttribute("orgId"), "");
%>

<div style="padding: 5px;overflow: hidden;">
	<form id="demo_autocarArrange_updateForm" method="post">
	<input type="hidden" name="carId"/>
	<input type="hidden" name="schoolArea"/>
	<fieldset >
		<legend>车辆排班基本信息</legend>
		<table class="tableForm">
			<tr>
				<th>排班日期</th>
				<td><input name="arrangeDate" style="width:155px;"/>
				</td>
			</tr>
			<tr>
				<th>排班时段：</th>
				<td colspan="3">
					8:00- -9:00	<input type="checkbox" class="timeItem_checked" name="ItemTime8Flag"  value =0 style="width:20px;"/> <input name="ItemTime8" class="autocar_itemTime" style="width:70px;"/> <br>
					9:00- 10:00	<input type="checkbox" class="timeItem_checked" name="ItemTime9Flag" value =0 style="width:20px;"/> <input name="ItemTime9" class="autocar_itemTime" style="width:70px;"/> <br>
					10:00-11:00<input type="checkbox" class="timeItem_checked" name="ItemTime10Flag" value =0 style="width:20px;"/> <input name="ItemTime10" class="autocar_itemTime" style="width:70px;"/> <br>
					11:00-12:00<input type="checkbox" class="timeItem_checked" name="ItemTime11Flag" value =0 style="width:20px;"/> <input name="ItemTime11" class="autocar_itemTime" style="width:70px;"/> <br>
					12:00-13:00<input type="checkbox" class="timeItem_checked" name="ItemTime12Flag" value =0 style="width:20px;"/> <input name="ItemTime12" class="autocar_itemTime" style="width:70px;"/> <br>
					13:00-14:00<input type="checkbox" class="timeItem_checked" name="ItemTime13Flag" value =0 style="width:20px;"/> <input name="ItemTime13" class="autocar_itemTime" style="width:70px;"/> <br>
					14:00-15:00<input type="checkbox" class="timeItem_checked" name="ItemTime14Flag" value =0 style="width:20px;"/> <input name="ItemTime14" class="autocar_itemTime" style="width:70px;"/> <br>
					15:00-16:00<input type="checkbox" class="timeItem_checked" name="ItemTime15Flag" value =0 style="width:20px;"/> <input name="ItemTime15" class="autocar_itemTime" style="width:70px;"/> <br>
					16:00-17:00<input type="checkbox" class="timeItem_checked" name="ItemTime16Flag" value =0 style="width:20px;"/> <input name="ItemTime16" class="autocar_itemTime" style="width:70px;"/> <br>
					17:00-18:00<input type="checkbox" class="timeItem_checked" name="ItemTime17Flag" value =0 style="width:20px;"/> <input name="ItemTime17" class="autocar_itemTime" style="width:70px;"/> <br>
					18:00-19:00<input type="checkbox" class="timeItem_checked" name="ItemTime18Flag" value =0 style="width:20px;"/> <input name="ItemTime18" class="autocar_itemTime" style="width:70px;"/> <br>
					19:00-20:00<input type="checkbox" class="timeItem_checked" name="ItemTime19Flag" value =0 style="width:20px;"/> <input name="ItemTime19" class="autocar_itemTime" style="width:70px;"/> <br>
					20:00-21:00<input type="checkbox" class="timeItem_checked" name="ItemTime20Flag" value =0 style="width:20px;"/> <input name="ItemTime20" class="autocar_itemTime" style="width:70px;"/> <br>
					21:00-22:00<input type="checkbox" class="timeItem_checked" name="ItemTime21Flag" value =0 style="width:20px;"/> <input name="ItemTime21" class="autocar_itemTime" style="width:70px;"/> <br>
				</td>
			</tr>
		</table>
	</fieldset>
	</form>
</div>
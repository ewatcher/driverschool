<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding: 5px;overflow: hidden;">
	<form id="demo_timing_updateForm" method="post">
	<input name="id" type="hidden"/>
	<input name="studentId" type="hidden" />
	<input name="studentOrganization" type="hidden" />
	<input name="numbering" type="hidden" />
		<input name="reservationTiming" type="hidden" />
		<fieldset >
			<legend>1:学员基本信息</legend>
			<table class="tableForm">
				<tr>
					<th style="width: 55px;">所属校区:</th>
					<td ><input class="demo_timing_update" name="studentOrganization" style="width: 155px;" /></td>
					<th style="width: 55px;">姓名:</th>
					<td><input class="demo_timing_update" name="studentName" style="width: 155px;" /></td>
					<th style="width: 55px;">性别:</th>
					<td><input class="demo_timing_update" name="studentSex" style="width: 155px;" /></td>
					<th style="width: 55px;">学员编号:</th>
					<td><input name="studentNo"  class="demo_timing_update"/></td>
				</tr>
				<tr>
					<th style="width: 55px;">报名日期:</th>
					<td><input class="demo_timing_update" name="studentCreateTime" style="width: 155px;" /></td>
					<th style="width: 55px;">身份证号:</th>
					<td><input class="demo_timing_update" name="studentIdentityId"  style="width: 155px;" /></td>
					<th>地址:</th>
					<td><input class="demo_timing_update" name="studentAddress"  style="width: 155px;" /></td>
					<th style="width: 55px;">出生日期:</th>
					<td><input class="demo_timing_update" name="studentBirthdate"  style="width: 155px;" /></td>
				</tr>
				
				<tr>
					<th style="width: 55px;">手机:</th>
					<td><input class="demo_timing_update" name="studentPhone"   style="width: 155px;" /></td>
					<th style="width: 55px;">邮箱:</th>
					<td><input class="demo_timing_update" name="studentEmail"  style="width: 155px;" /></td>
					<th style="width: 55px;">电话:</th>
					<td><input class="demo_timing_update" name="studentTelephone"  style="width: 155px;" /></td>
					<th style="width: 55px;">邮编:</th>
					<td><input class="demo_timing_update" name="studentMailCode"   style="width: 155px;" /></td>
				</tr>
				
				<tr>
					<th style="width: 55px;">国籍:</th>
					<td><input class="demo_timing_update" name="studentContry"  style="width: 155px;" /></td>
					<th style="width: 55px;">地域:</th>
					<td><input class="demo_timing_update" name="studentNativeNation" style="width: 155px;" /></td>
					<th style="width: 55px;">居住证:</th>
					<td><input class="demo_timing_update" name="studentResidenceId"  style="width: 155px;" /></td>
					<th style="width: 65px;">居住证地址:</th>
					<td><input class="demo_timing_update" name="studentResidenceAddr"  style="width: 155px;" /></td>
				</tr>
				
				<tr>
					<th style="width: 55px;">照片回执:</th>
					<td><input class="demo_timing_update" name="studentImageId"  style="width: 155px;" /></td>
					<th style="width: 55px;">状态:</th>
					<td><input class="demo_timing_update" name="studentNowstate"  style="width: 155px;" /></td>
					<th style="width: 55px;">班级:</th>
					<td ><input class="demo_timing_update" name="studentClazz"  style="width: 155px;" /></td>
						<th style="width: 55px;">申领类型:</th>
					<td ><input class="demo_timing_update" name="studentApplyType" style="width: 155px;" /></td>
				</tr>
				<tr>
					<th style="width: 55px;">驾照类型:</th>
					<td ><input class="demo_timing_update" name="studentDriverType"  style="width: 155px;"/></td>
				</tr>
			</table>
		</fieldset>
		<fieldset >
			<legend>2:当前使用情况</legend>
			<table class="tableForm">
				<tr>
					<th style="width: 80px;">报名含学时:</th>
					<td><input name="handselTiming" style="width: 80px;"/></td>
					<th style="width: 80px;">电车总学时:</th>
					<td><input name="tramTiming" style="width: 80px;"/></td>
					<th style="width: 80px;">模拟总学时:</th>
					<td><input name="simulationTiming" style="width: 80px;"/></td>
					<th style="width: 80px;">实车总学时:</th>
					<td><input name="carTiming" style="width: 80px;"/></td>
					
				</tr>
				<tr>
					<th style="width: 80px;">总学时:</th>
					<td><input name="totalTiming" style="width: 80px;"/></td>
					<th style="width: 80px;">购买学时:</th>
					<td><input name="buyTiming" style="width: 80px;"/></td>
					<th style="width: 80px;">已用学时:</th>
					<td><input name="usingTiming" style="width: 80px;"/></td>
					<th style="width: 80px;color: red">剩余学时:</th>
					<td><input name="restTiming" style="width: 80px;"/></td>
				</tr>
			</table>
		</fieldset>		
		<fieldset >
			<legend>3:备注信息</legend>
			<textarea name="comment" style="height: 100px;width: 98%;"></textarea>
		</fieldset>
	</form>
</div>
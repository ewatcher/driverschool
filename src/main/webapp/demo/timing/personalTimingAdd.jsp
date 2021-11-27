<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding: 5px;height:320px;overflow: hidden;">
	<form id="demo_timing_updateForm" method="post">
	<input name="id" type="hidden"/>
	<input name="studentId" type="hidden" />
	<input name="studentOrganization" type="hidden" />
	<input name="numbering" type="hidden" />
	<input name="trainerId" type="hidden" />
	<input name="trainerName" type="hidden" />
	<fieldset >
		<legend>学员基本信息</legend>
		<table class="tableForm">
			<tr>
				<th style="width: 65px;">所属校区:</th>
				<td ><input class="demo_timing_update" name="studentOrganization" style="width: 155px;" /></td>
				<th style="width: 65px;">姓名:</th>
				<td><input class="demo_timing_update" name="studentName" style="width: 155px;" /></td>
				<th style="width: 65px;">性别:</th>
				<td><input class="demo_timing_update" name="studentSex" style="width: 155px;" /></td>
				<th style="width: 65px;">学员编号:</th>
					<td><input name="studentNo"  class="demo_timing_update"/></td>
				</tr>
			<tr>
				<th style="width: 65px;">报名日期:</th>
				<td><input class="demo_timing_update" name="studentCreateTime" style="width: 155px;" /></td>
				<th style="width: 65px;">身份证号:</th>
				<td><input class="demo_timing_update" name="studentIdentityId"  style="width: 155px;" /></td>
				<th style="width: 65px;">地址:</th>
				<td><input class="demo_timing_update" name="studentAddress"  style="width: 155px;" /></td>
				<th style="width: 65px;">出生日期:</th>
				<td><input class="demo_timing_update" name="studentBirthdate"  style="width: 155px;" /></td>
			</tr>
			
			<tr>
				<th style="width: 65px;">手机:</th>
				<td><input class="demo_timing_update" name="studentPhone"   style="width: 155px;" /></td>
				<th style="width: 65px;">邮箱:</th>
				<td><input class="demo_timing_update" name="studentEmail"  style="width: 155px;" /></td>
				<th style="width: 65px;">电话:</th>
				<td><input class="demo_timing_update" name="studentTelephone"  style="width: 155px;" /></td>
				<th style="width: 65px;">邮编:</th>
				<td><input class="demo_timing_update" name="studentMailCode"   style="width: 155px;" /></td>
			</tr>
			
			<tr>
				<th style="width: 65px;">国籍:</th>
				<td><input class="demo_timing_update" name="studentContry"  style="width: 155px;" /></td>
				<th style="width: 65px;">地域:</th>
				<td><input class="demo_timing_update" name="studentNativeNation" style="width: 155px;" /></td>
				<th style="width: 65px;">居住证:</th>
				<td><input class="demo_timing_update" name="studentResidenceId"  style="width: 155px;" /></td>
				<th style="width: 65px;">居住证地址:</th>
				<td><input class="demo_timing_update" name="studentResidenceAddr"  style="width: 155px;" /></td>
			</tr>
			
			<tr>
				<th style="width: 65px;">照片回执:</th>
				<td><input class="demo_timing_update" name="studentImageId"  style="width: 155px;" /></td>
				<th style="width: 65px;">状态:</th>
				<td><input class="demo_timing_update" name="studentNowstate"  style="width: 155px;" /></td>
				<th style="width: 65px;">班级:</th>
				<td ><input class="demo_timing_update" name="studentClazz"  style="width: 155px;" /></td>
					<th style="width: 65px;">申领类型:</th>
				<td ><input class="demo_timing_update" name="studentApplyType" style="width: 155px;" /></td>
			</tr>
			<tr>
				<th style="width: 65px;">驾照类型:</th>
				<td ><input class="demo_timing_update" name="studentDriverType"  style="width: 155px;"/></td>
			</tr>
		</table>
	</fieldset>
	<fieldset >
		<legend>当前学员学时统计信息</legend>
		<table class="tableForm">
			<tr>
				<th style="width: 70px;">总学时:</th>
				<td><input name="totalTiming" class="demo_timing_update" style="width: 80px;"/></td>
				<th style="width: 70px;">电车总学时:</th>
				<td><input name="tramTiming" class="demo_timing_update" style="width: 80px;"/></td>
				<th style="width: 70px;">模拟总学时:</th>
				<td><input name="simulationTiming" class="demo_timing_update" style="width: 80px;"/></td>
				<th style="width: 80px;">实车总学时:</th>
				<td><input name="carTiming" class="demo_timing_update" style="width: 80px;"/></td>
			</tr>
			<tr>
				<th style="width: 70px;">购买学时:</th>
				<td><input name="buyTiming" class="demo_timing_update" style="width: 80px;"/></td>
				<th style="width: 70px;">报名含学时:</th>
				<td><input name="handselTiming" class="demo_timing_update" style="width: 80px;"/></td>
				<th style="width: 80px;">已用学时:</th>
				<td><input name="usingTiming" class="demo_timing_update" style="width: 80px;"/></td>
				<th style="width: 80px;color:red;">剩余学时:</th>
				<td><input name="restTiming" class="demo_timing_update" style="width: 80px;"/></td>
			</tr>
		</table>
	</fieldset>		
		
	</form>
</div>
<!-- datagrid数据在center中展现 -->
	<div data-options="region:'center',border:false"
		style="height:370px;overflow: hidden;">
		<table id="demo_personalTiming_datagrid"></table>
	</div>
	<!-- 操作按钮事件 -->
	<div id="demo_timing_menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="edit();" data-options="iconCls:'icon-edit'">更新</div>
	</div>

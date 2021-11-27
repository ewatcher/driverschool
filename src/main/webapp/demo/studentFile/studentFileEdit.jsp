<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding: 5px;overflow: hidden;">
	<form id="demo_studentFile_updateForm" method="post">
	<input name="id" type="hidden"/>
	<input name="studentId" type="hidden" />
	<input name="studentOrganization" type="hidden" />
	<fieldset >
		<legend>学员基本信息</legend>
		<table class="tableForm">
			<tr>
				<th style="width: 55px;">所属校区:</th>
				<td ><input class="demo_studentFile_update" name="studentOrganization" style="width: 155px;" /></td>
				<th style="width: 55px;">姓名:</th>
				<td><input class="demo_studentFile_update" name="studentName" style="width: 155px;" /></td>
				<th style="width: 55px;">性别:</th>
				<td><input class="demo_studentFile_update" name="studentSex" style="width: 155px;" /></td>
				<th style="width: 55px;">学员编号:</th>
				<td><input name="studentNo"  class="demo_studentFile_update"/></td>
			</tr>
			<tr>
				<th style="width: 55px;">报名日期:</th>
				<td><input class="demo_studentFile_update" name="studentCreateTime" style="width: 155px;" /></td>
				<th style="width: 55px;">身份证号:</th>
				<td><input class="demo_studentFile_update" name="studentIdentityId"  style="width: 155px;" /></td>
				<th>地址:</th>
				<td><input class="demo_studentFile_update" name="studentAddress"  style="width: 155px;" /></td>
				<th style="width: 55px;">出生日期:</th>
				<td><input class="demo_studentFile_update" name="studentBirthdate"  style="width: 155px;" /></td>
			</tr>
			
			<tr>
				<th style="width: 55px;">手机:</th>
				<td><input class="demo_studentFile_update" name="studentPhone"   style="width: 155px;" /></td>
				<th style="width: 55px;">邮箱:</th>
				<td><input class="demo_studentFile_update" name="studentEmail"  style="width: 155px;" /></td>
				<th style="width: 55px;">电话:</th>
				<td><input class="demo_studentFile_update" name="studentTelephone"  style="width: 155px;" /></td>
				<th style="width: 55px;">邮编:</th>
				<td><input class="demo_studentFile_update" name="studentMailCode"   style="width: 155px;" /></td>
			</tr>
			
			<tr>
				<th style="width: 55px;">国籍:</th>
				<td><input class="demo_studentFile_update" name="studentContry"  style="width: 155px;" /></td>
				<th style="width: 55px;">地域:</th>
				<td><input class="demo_studentFile_update" name="studentNativeNation" style="width: 155px;" /></td>
				<th style="width: 55px;">居住证:</th>
				<td><input class="demo_studentFile_update" name="studentResidenceId"  style="width: 155px;" /></td>
				<th style="width: 65px;">居住证地址:</th>
				<td><input class="demo_studentFile_update" name="studentResidenceAddr"  style="width: 155px;" /></td>
			</tr>
			
			<tr>
				<th style="width: 55px;">照片回执:</th>
				<td><input class="demo_studentFile_update" name="studentImageId"  style="width: 155px;" /></td>
				<th style="width: 55px;">状态:</th>
				<td><input class="demo_studentFile_update" name="studentNowstate"  style="width: 155px;" /></td>
				<th style="width: 55px;">班级:</th>
				<td ><input class="demo_studentFile_update" name="studentClazz"  style="width: 155px;" /></td>
					<th style="width: 55px;">申领类型:</th>
				<td ><input class="demo_studentFile_update" name="studentApplyType" style="width: 155px;" /></td>
			</tr>
			<tr>
				<th style="width: 55px;">驾照类型:</th>
				<td ><input class="demo_studentFile_update" name="studentDriverType"  style="width: 155px;"/></td>
				<th style="width: 65px;">应交学费:</th>
				<td ><input class="demo_studentFile_update" name="studentFeed"  style="width: 155px;" /></td>
				<th style="width: 65px;">实收学费:</th>
				<td ><input class="demo_studentFile_update" name="studentRealFeed"  style="width: 155px;" /></td>
				<th style="width: 65px;">欠费:</th>
				<td ><input class="demo_studentFile_update" name="studentOwnFeed"  style="width: 155px;" /></td>
		</table>
	</fieldset>
	<fieldset >
		<legend>档案信息输入</legend>
		<table class="tableForm">
			<tr>
				<th style="width: 65px;">交费日期:</th>
				<td>
					<input name="contributionTime" class="easyui-datetimebox" data-options="showSeconds: false,editable:false"
					 style="width: 155px;" />
				</td>
				<th style="width: 70px;">交费情况:</th>
				<td><input name="feeState" style="width: 155px;"/></td>
				<th style="width: 70px;">所交学时:</th>
				<td><input name="learnTime" style="width: 155px;"/></td>
			</tr>
			<tr>
				<th style="width: 65px;">交规日期:</th>
				<td>
					<input name="theoryDate" class="easyui-datetimebox" data-options="showSeconds: false,editable:false"
					 style="width: 155px;" />
				</td>
				<th style="width: 65px;">五项日期:</th>
				<td>
					<input name="subjectTwoDate" class="easyui-datetimebox" data-options="showSeconds: false,editable:false"
					 style="width: 155px;" />
				</td>
				<th style="width: 65px;">路考日期:</th>
				<td>
					<input name="subjectThreeDate" class="easyui-datetimebox" data-options="showSeconds: false,editable:false"
					 style="width: 155px;" />
				</td>
				<th style="width: 65px;">文明日期:</th>
				<td>
					<input name="subjectFourDate" class="easyui-datetimebox" data-options="showSeconds: false,editable:false"
					 style="width: 155px;" />
				</td>
			</tr>
			<tr>
				<th style="width: 65px;">资格日期:</th>
				<td>
					<input name="qualificationDate" class="easyui-datetimebox" data-options="showSeconds: false,editable:false"
					 style="width: 155px;" />
				</td>
			</tr>
			
		</table>
	</fieldset>		
		<fieldset >
		<legend>备注信息</legend>
		<textarea name="comment" style="height: 100px;width: 98%;"></textarea>
		</fieldset>
	</form>
</div>
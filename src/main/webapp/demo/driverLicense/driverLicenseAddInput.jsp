<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding: 5px;overflow: hidden;">
	<form id="demo_driverLicense_updateForm" method="post">
	<input name="id" type="hidden"/>
	<input name="studentId" type="hidden" />
	<input name="studentOrganization" type="hidden" />
	<fieldset >
		<legend>学员基本信息</legend>
		<table class="tableForm">
			<tr>
				<th style="width: 65px;">所属校区:</th>
				<td ><input class="demo_driverLicense_update" name="schoolAreaName" /></td>
				<th style="width: 65px;">姓名:</th>
				<td><input class="demo_driverLicense_update" name="studentName"/></td>
				<th style="width: 65px;">性别:</th>
				<td><input class="demo_driverLicense_update" name="studentSex"/></td>
				<th style="width: 65px;">学员编号:</th>
				<td><input class="demo_driverLicense_update" name="studentNo" style="width: 155px;"/></td>
			</tr>
			<tr>
				<th style="width: 55px;">报名日期:</th>
				<td><input class="demo_driverLicense_update" name="studentCreateTime" /></td>
				<th style="width: 55px;">身份证号:</th>
				<td><input class="demo_driverLicense_update" name="studentIdentityId"  /></td>
				<th>地址:</th>
				<td><input class="demo_driverLicense_update" name="studentAddress"  /></td>
				<th style="width: 55px;">出生日期:</th>
				<td><input class="demo_driverLicense_update" name="studentBirthdate"  /></td>
			</tr>
			
			<tr>
				<th style="width: 55px;">手机:</th>
				<td><input class="demo_driverLicense_update" name="studentPhone"   /></td>
				<th style="width: 55px;">邮箱:</th>
				<td><input class="demo_driverLicense_update" name="studentEmail"  /></td>
				<th style="width: 55px;">电话:</th>
				<td><input class="demo_driverLicense_update" name="studentTelephone"  /></td>
				<th style="width: 55px;">邮编:</th>
				<td><input class="demo_driverLicense_update" name="studentMailCode"   /></td>
			</tr>
			
			<tr>
				<th style="width: 55px;">国籍:</th>
				<td><input class="demo_driverLicense_update" name="studentContry"  /></td>
				<th style="width: 55px;">地域:</th>
				<td><input class="demo_driverLicense_update" name="studentNativeNation" /></td>
				<th style="width: 55px;">居住证:</th>
				<td><input class="demo_driverLicense_update" name="studentResidenceId"  /></td>
				<th style="width: 65px;">居住证地址:</th>
				<td><input class="demo_driverLicense_update" name="studentResidenceAddr"  /></td>
			</tr>
			
			<tr>
				<th style="width: 55px;">照片回执:</th>
				<td><input class="demo_driverLicense_update" name="studentImageId"  /></td>
				<th style="width: 55px;">状态:</th>
				<td><input class="demo_driverLicense_update" name="studentNowstate"  /></td>
				<th style="width: 55px;">班级:</th>
				<td ><input class="demo_driverLicense_update" name="studentClazz"  /></td>
					<th style="width: 55px;">申领类型:</th>
				<td ><input class="demo_driverLicense_update" name="studentApplyType" style="width: 155px;" /></td>
			</tr>
			<tr>
				<th style="width: 55px;">驾照类型:</th>
				<td ><input id="demo_driverlicenseaddInput" class="demo_driverLicense_update" name="studentDriverType"  style="width: 155px;"/></td>
			</tr>
		</table>
	</fieldset>
	<fieldset >
		<legend>资格证信息输入</legend>
		<table class="tableForm">
			<tr>
				<th>发放日期:</th>
				<td>
					<input name="grantDate" class="easyui-datebox" data-options="showSeconds: false,editable:false"
					 style="width: 155px;" />
				</td>
				<th>初领日期:</th>
				<td>
					<input name="firstGetDate" class="easyui-datebox" data-options="showSeconds: false,editable:false"
					 style="width: 155px;" />
				</td>
				<th style="width: 70px;">准驾车型:</th>
				<td><input name="driverType"  style="width: 155px;"/></td>
				<th>有效开始日期:</th>
				<td>
					<input name="validBeginDate" class="easyui-datebox" data-options="showSeconds: false,editable:false"
					 style="width: 155px;" />
				</td>
			</tr>
			<tr>
				<th style="width: 70px;">有效年限:</th>
				<td><input name="validYear" style="width: 155px;"/></td>
				<th style="width: 70px;">领取状态:</th>
				<td>
					<select id="demo_grantState_combobox" name="grantState" style="width:155px;">
							<option value="">请选择</option>
							<option value=1 selected=true>未领取</option>
							<option value=2>已领取</option>
					</select>
				</td>
				<th>领取日期:</th>
				<td>
					<input name="recieveDate" class="easyui-datebox" data-options="showSeconds: false,editable:false"
					 style="width: 155px;" />
				</td>
			</tr>
		</table>
	</fieldset>		
		<fieldset >
		<legend>备注信息(150个字)</legend>
		<textarea name="comment" style="height: 100px;width: 98%;"></textarea>
		</fieldset>
	</form>
</div>
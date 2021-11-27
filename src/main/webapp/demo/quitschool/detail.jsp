<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding: 5px;overflow: hidden;">
	<form id="demo_quitschool_detailForm" method="post">
	<input name="id" type="hidden"/>
	<input name="studentId" type="hidden" />
	<fieldset >
		<legend>学员基本信息</legend>
		<table class="tableForm">
			<tr>
				<th style="width: 55px;">所属校区:</th>
				<td ><input class="demo_quitschool_detail" name="studentOrganization" /></td>
				<th style="width: 55px;">姓名:</th>
				<td><input class="demo_quitschool_detail" name="studentName"/></td>
				<th style="width: 55px;">性别:</th>
				<td><input class="demo_quitschool_detail" name="studentSex"/></td>
			</tr>
			<tr>
				<th style="width: 55px;">报名日期:</th>
				<td><input class="demo_quitschool_detail" name="studentCreateTime" /></td>
				<th style="width: 55px;">身份证号:</th>
				<td><input class="demo_quitschool_detail" name="studentIdentityId"  /></td>
				<th>地址:</th>
				<td><input class="demo_quitschool_detail" name="studentAddress"  /></td>
				<th style="width: 55px;">出生日期:</th>
				<td><input class="demo_quitschool_detail" name="studentBirthdate"  /></td>
			</tr>
			
			<tr>
				<th style="width: 55px;">手机:</th>
				<td><input class="demo_quitschool_detail" name="studentPhone"   /></td>
				<th style="width: 55px;">邮箱:</th>
				<td><input class="demo_quitschool_detail" name="studentEmail"  /></td>
				<th style="width: 55px;">电话:</th>
				<td><input class="demo_quitschool_detail" name="studentTelephone"  /></td>
				<th style="width: 55px;">邮编:</th>
				<td><input class="demo_quitschool_detail" name="studentMailCode"   /></td>
			</tr>
			
			<tr>
				<th style="width: 55px;">国籍:</th>
				<td><input class="demo_quitschool_detail" name="studentContry"  /></td>
				<th style="width: 55px;">地域:</th>
				<td><input class="demo_quitschool_detail" name="studentNativeNation" /></td>
				<th style="width: 55px;">居住证:</th>
				<td><input class="demo_quitschool_detail" name="studentResidenceId"  /></td>
				<th style="width: 65px;">居住证地址:</th>
				<td><input class="demo_quitschool_detail" name="studentResidenceAddr"  /></td>
			</tr>
			
			<tr>
				<th style="width: 55px;">照片回执:</th>
				<td><input class="demo_quitschool_detail" name="studentImageId"  /></td>
				<th style="width: 55px;">状态:</th>
				<td><input class="demo_quitschool_detail" name="studentNowstate"  /></td>
				<th style="width: 55px;">班级:</th>
				<td ><input class="demo_quitschool_detail" name="studentClazz"  /></td>
					<th style="width: 55px;">申领类型:</th>
				<td ><input class="demo_quitschool_detail" name="studentApplyType" style="width: 155px;" /></td>
			</tr>
			<tr>
				<th style="width: 55px;">驾照类型:</th>
				<td ><input class="demo_quitschool_detail" name="studentDriverType"  style="width: 155px;"/></td>
			</tr>
		</table>
	</fieldset>
	<fieldset >
		<legend>退学登记信息输入</legend>
		<table class="tableForm">
			<tr>
				<th style="width: 70px;">扣款金额:</th>
				<td><input name="debitFee" style="width: 155px;"/></td>
				<th style="width: 70px;">退款金额:</th>
				<td><input name="quitFee" style="width: 155px;"/></td>
				<th>退学日期:</th>
				<td>
					<input name="quitDate" class="easyui-datetimebox" data-options="showSeconds: false,editable:false"
					 style="width: 155px;" />
				</td>
			</tr>
			<tr>
				<th style="width: 70px;">退学原因:</th>
				<td colspan="5"><textarea name="quitReason" style="height: 50px;width: 98%;"></textarea></td>
			</tr>
		</table>
	</fieldset>		
		<fieldset >
		<legend>备注信息</legend>
		<textarea name="comment" style="height: 100px;width: 98%;"></textarea>
		</fieldset>
	</form>
</div>
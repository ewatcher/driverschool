<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding: 5px;overflow: hidden;">
	<form id="demo_quitschool_updateForm" method="post">
	<input name="id" type="hidden"/>
	<input name="studentId" type="hidden" />
	<fieldset >
		<legend>学员基本信息</legend>
		<table class="tableForm">
			<tr>
				<th style="width: 65px;">所属校区:</th>
				<td ><input name="schoolAreaName" style="width:150px;"/></td>
				<th style="width: 65px;">驾照类型:</th>
				<td ><input name="studentDriverType" style="width: 150px;" /></td>
				<th style="width: 65px;">编号:</th>
				<td>
					<input name="studentNo" class="easyui-validatebox" data-options="required:'false',missingMessage:'请填写学员编号'" style="width: 150px;"/>
				</td>
				<th style="width: 65px;">报名日期:</th>
				<td><input name="studentCreateTime" class="easyui-datebox" data-options="showSeconds:false,editable:false" style="width: 155px;" /></td>
				
			<tr>
				<th style="width: 65px;">姓名:</th>
				<td><input name="studentName" class="easyui-validatebox" data-options="required:'true',validType:'validChinese',missingMessage:'请填写学员姓名'" style="width: 130px;"/></td>
				<th style="width: 65px;">身份证号:</th>
				<td><input name="studentIdentityId"  class="easyui-validatebox" data-options="required:'true',validType:'validateIdentity[15,18]',missingMessage:'请填写学员身份证号码'" /></td>
				<th style="width: 65px;">地址:</th>
				<td colspan="3"><input name="studentAddress"  class="easyui-validatebox" data-options="required:'true',missingMessage:'请填写学员地址'" /></td>
				
			</tr>
			
			<tr>
				<th style="width: 65px;">性别:</th>
				<td>
					<select class="easyui-combobox" name="studentSex" style="width:100px;">
       				 	<option value="">请选择</option>
       				 	<option value="男">男</option>
       				 	<option value="女">女</option>
    				</select>
    			</td>
				<th style="width: 55px;">手机:</th>
				<td><input name="studentPhone"  class="easyui-validatebox" data-options="required:'true',validType:'validMobile',missingMessage:'请填写学员身手机号'" /></td>
				<th style="width: 65px;">应交学费:</th>
				<td colspan="3">
					<input name="studentFeed"style="width: 80px;" /> 
					实收学费:<input name="studentRealFeed" style="width: 80px;" />
				 	 欠费:<input name="studentOwnFeed" style="width: 80px;" class="demo_student_disable"
				 	  value="0.00"/>
				 </td>
			</tr>
			
			<tr>
				<th style="width: 55px;">学时类型:</th>
				<td><input name="studentTimingFlag"  style="width: 100px;" /></td>
				<th style="width: 55px;">包含学时:</th>
				<td><input name="studentLearningTime"  value="0" class="easyui-validatebox" data-options="required:'true',missingMessage:'请填写学员注册所含学时'" style="width: 100px;"/></td>
				<th style="width: 55px;">邮箱:</th>
				<td><input name="studentEmail"  class="easyui-validatebox" data-options="validType:'email',missingMessage:'请填写学邮箱'" type="email" /></td>
				<th style="width: 65px;">学员类型:</th>
				<td><input name="studentType"  style="width: 100px;" /></td>
			</tr>
			<tr>
				<th style="width: 55px;">居住证:</th>
				<td><input name="studentResidenceId"  /></td>
				<th style="width: 65px;">居住证地址:</th>
				<td><input name="studentResidenceAddr"  /></td>
				<th style="width: 65px;">申领类型:</th>
				<td ><input name="studentApplyType" style="width: 150px;" /></td>
				<th style="width: 55px;">状态:</th>
				<td><select id="student_search_combobox" class="easyui-combobox" data-options="editable:false,enabled:false" name="studentNowState" style="width: 150px;">
						   <option value="">请选择</option>
						    <option Selected="selected" value=1>在培</option>
						    <option value=2>毕业</option>
						    <option value=3>退学</option>
						    <option value=4>到期</option>
						    <option value=5>不计时</option>
					</select>
				</td>
			</tr>
			<tr>
				<th style="width: 65px;">照片回执:</th>
				<td><input name="studentImageId"  /></td>
				<th style="width: 65px;">出生日期:</th>
				<td><input name="studentBirthdate" class="easyui-datebox" data-options="showSeconds:false,editable:false" style="width: 155px;" /></td>
				<th style="width: 65px;">学员密码:</th>
				<td><input name="studentPassword"  /></td>
			</tr>
			<tr>
				<th style="width: 55px;">教练:</th>
				<td colspan="3"><input name="trainerName" style="width: 120px;" />
				</td>
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
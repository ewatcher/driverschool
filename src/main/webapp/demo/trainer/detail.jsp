<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding: 5px;overflow: hidden;">
	<form id="demo_trainer_updateForm" method="post">
	<input name="id" type="hidden"/>
	<fieldset >
		<legend>教练基本信息</legend>
		<table class="tableForm">
			<tr>
				<th style="width: 65px;">所属校区:</th>
				<td><input name="schoolArea"  ></td>
				<th style="width: 65px;">编号:</th>
				<td ><input name="codeNo" class="demo_trainer_detail" style="width: 130px;" /></td>
				<th style="width: 65px;">姓名:</th>
				<td><input name="name" class="demo_trainer_detail" style="width: 130px;"/></td>
				<th style="width: 65px;">身份证:</th>
				<td><input name="identity" class="demo_trainer_detail" style="width: 130px;"/></td>
				
			</tr>
			<tr>
				<th style="width: 40px;">性别:</th>
				<td><select id="trainer_add_combobox" disabled="true" name="sex">
						    <option value="">请选择</option>
						    <option value="男">男</option>
						    <option value="女">女</option>
					</select>
				</td>
				<th style="width: 55px;">手机:</th>
				<td><input name="phone"  class="demo_trainer_detail" /></td>
				<th style="width: 55px;">邮箱:</th>
				<td><input name="email" class="demo_trainer_detail" type="email" /></td>
				<th style="width: 55px;">地址:</th>
				<td><input name="addres" class="demo_trainer_detail" /></td>
			</tr>
			<tr>
				<th style="width: 65px;">出生日期:</th>
				<td><input name="birthday" class="easyui-datebox" data-options="disabled:true" style="width: 155px;" /></td>
				<th style="width: 55px;">民族:</th>
				<td><input name="nation"class="demo_trainer_detail" /></td>
				<th style="width: 55px;">籍贯:</th>
				<td><input name="contry" class="demo_trainer_detail"/></td>
				<th style="width: 65px;">入职日期:</th>
				<td><input name="jionDay" class="easyui-datebox" data-options="disabled:true" style="width: 155px;" /></td>
			</tr>
			<tr>
				<th style="width: 55px;">电话:</th>
				<td><input name="telephone" class="demo_trainer_detail" ></td>
				<th style="width: 55px;">教练类别:</th>
				<td><input name="trainerType" style="width:155px;"/></td>
			</tr>
		</table>
	</fieldset>
	<fieldset >
		<legend>教练驾驶资料</legend>
			<table class="tableForm">
				<tr>
					<th style="width: 50px;">驾驶证ID:</th>
					<td><input name="driverLicenseId" class="demo_trainer_detail" style="width: 130px;"/></td>
					<th>准驾类型:</th>
					<td><input name="driverType" style="width:155px;"/></td>
					<th>准驾教车型:</th>
					<td><input name="driverCarType" style="width:155px;"/></td>
					<th style="width: 65px;">初领驾驶证日期:</th>
					<td><input name="firstGetDate" class=easyui-datebox data-options="disabled:true" style="width: 155px;" /></td>
				</tr>
				<tr>
					<th style="width: 65px;">驾驶证有效期:</th>
					<td><input name="driverValidatyStart" class="easyui-datebox" data-options="disabled:true" style="width: 155px;" /></td>
					<th style="width: 55px;">到</th>
					<td><input name="driverValidatyEnd" class="easyui-datebox" data-options="disabled:true" style="width: 155px;" /></td>
					<th style="width: 65px;">教员编号:</th>
					<td><input name="teachId"/></td>
				</tr>
				<tr>
					<th style="width: 65px;">教员证初领:</th>
					<td><input name="firstTeachingDate" class="easyui-datebox" data-options="disabled:true" style="width: 155px;" /></td>
					<th style="width: 65px;">教员证有效期:</th>
					<td><input name="teachValidatyStart" class="easyui-datebox" data-options="disabled:true" style="width: 155px;" /></td>
					<th style="width: 55px;">到</th>
					<td><input name="teachBalidatyEnd" class="easyui-datebox" data-options="disabled:true" style="width: 155px;" /></td>
				</tr>
		</table>
	</fieldset>
	<fieldset >
		<legend>排班类型</legend>
		<table class="tableForm">
			<tr>
				<td><input name="arrangeType" style="width:155px;"/>*该属性用来安排教练电车或者模拟器排班做标记</td>
			</tr>
		</table>	
	</fieldset>
	<fieldset >
		<legend>备注信息</legend>
		<textarea name="comment" style="height: 100px;width: 98%;"></textarea>
	</fieldset>
	</form>
</div>
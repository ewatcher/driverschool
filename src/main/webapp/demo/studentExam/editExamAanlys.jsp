<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.tuocheng.util.base.Util"%>
<%
  String resourceSn = Util.objToString(request.getParameter("resourceSn"), "");
	out.print(resourceSn);
%>
<div style="padding: 5px;overflow: hidden;">
	<form id="demo_trainer_updateForm" method="post">
	<input name="id" type="hidden"/>
	<input name="carBanding" type="hidden"/>
	<input name="arrangeFlag" type="hidden"/>
	<fieldset >
		<legend>教练基本信息</legend>
		<table class="tableForm">
			<tr>
				<th style="width: 65px;">所属校区:</th>
				<td><input name="schoolArea"  style="width: 130px;"></td>
				<th style="width: 40px;">编号:</th>
				<td ><input name="codeNo" class="easyui-validatebox" data-options="required:'false',missingMessage:'请填写教练编号'" style="width: 130px;" /></td>
				<th style="width: 50px;">姓名:</th>
				<td><input name="name" class="easyui-validatebox" data-options="required:'false',validType:'validChinese',missingMessage:'请填写教练员姓名'" style="width: 130px;"/></td>
				<th style="width: 50px;">身份证:</th>
				<td><input name="identity" class="easyui-validatebox" data-options="required:'true',validType:'validateIdentity[15,18]',missingMessage:'请填写身份证号'" style="width: 130px;"/></td>
				
			</tr>
			<tr>
				<th style="width: 40px;">性别:</th>
				<td><select id="trainer_add_combobox" class="easyui-combobox" data-options="required:true,editable:false" name="sex">
						    <option value="">请选择</option>
						    <option value="男">男</option>
						    <option value="女">女</option>
					</select>
				</td>
				<th style="width: 55px;">手机:</th>
				<td><input name="phone"  class="easyui-validatebox" data-options="required:'true',missingMessage:'请填写学员身手机号'" /></td>
				<th style="width: 55px;">邮箱:</th>
				<td><input name="email" class="easyui-validatebox" data-options="validType:'email',missingMessage:''" type="email" /></td>
				<th style="width: 55px;">地址:</th>
				<td><input name="addres" style="width: 130px;" /></td>
			</tr>
			<tr>
				<th style="width: 65px;">出生日期:</th>
				<td><input name="birthday" class="easyui-datebox" data-options="editable:false" style="width: 155px;" /></td>
				<th style="width: 55px;">民族:</th>
				<td><input name="nation" class="easyui-validatebox" data-options="validType:'validChinese'" style="width: 130px;"/></td>
				<th style="width: 55px;">籍贯:</th>
				<td><input name="contry" class="easyui-validatebox" data-options="validType:'validChinese'"style="width: 130px;"/></td>
				<th style="width: 65px;">入职日期:</th>
				<td><input name="jionDay" class="easyui-datebox" data-options="editable:false" style="width: 155px;" /></td>
			</tr>
			<tr>
				<th style="width: 55px;">电话:</th>
				<td><input name="telephone" class="easyui-validatebox" data-options="validType:'validTelephone',missingMessage:'请填写学员身手机号'" ></td>
				<th style="width: 65px;">教练类别:</th>
				<td><input name="trainerType" style="width:155px;"/></td>
			</tr>
		</table>
	</fieldset>
	<fieldset >
		<legend>教练驾驶资料</legend>
			<table class="tableForm">
				<tr>
					<th style="width: 60px;">驾驶证ID:</th>
					<td><input name="driverLicenseId" class="easyui-validatebox" data-options="required:'true',missingMessage:'请填写驾驶证号'" style="width: 130px;"/></td>
					<th>准驾类型:</th>
					<td><input name="driverType" style="width:155px;"/></td>
					<th>准驾教车型:</th>
					<td><input name="driverCarType" style="width:155px;"/></td>
					<th style="width: 65px;">初领驾驶证日期:</th>
					<td><input name="firstGetDate" class=easyui-datebox data-options="editable:false" style="width: 155px;" /></td>
				</tr>
				<tr>
					<th style="width: 65px;">驾驶证有效期:</th>
					<td><input name="driverValidatyStart" class="easyui-datebox" data-options="editable:false" style="width: 155px;" /></td>
					<th style="width: 55px;">到</th>
					<td><input name="driverValidatyEnd" class="easyui-datebox" data-options="editable:false" style="width: 155px;" /></td>
					<th style="width: 60px;">教员编号:</th>
					<td><input name="teachId"/></td>
				</tr>
				<tr>
					<th style="width: 60px;">教员证初领:</th>
					<td><input name="firstTeachingDate" class="easyui-datebox" data-options="editable:false" style="width: 155px;" /></td>
					<th style="width: 65px;">教员证有效期:</th>
					<td><input name="teachValidatyStart" class="easyui-datebox" data-options="editable:false" style="width: 155px;" /></td>
					<th style="width: 55px;">到</th>
					<td><input name="teachBalidatyEnd" class="easyui-datebox" data-options="editable:false" style="width: 155px;" /></td>
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
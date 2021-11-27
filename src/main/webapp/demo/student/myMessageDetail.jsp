<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding: 5px;overflow: hidden;">
	<form id="studentForm" method="post">
	<input name="id" type="hidden"/>
	<input name="clazzId" type="hidden"/>
	<input name="trainerId" type="hidden"/>
	<input name="personId" type="hidden"/>
	<input name="examSelected" type="hidden"/>
	
<fieldset >
		<legend>学员基本信息</legend>
		<table class="tableForm">
			<tr>
				<th style="width: 65px;">所属校区:</th>
				<td ><input name="organizationName" style="width:150px;"/></td>
				<th style="width: 65px;">驾照类型:</th>
				<td ><input name="driverType" style="width: 150px;" /></td>
				<th style="width: 65px;">编号:</th>
				<td><input name="studentNo" class="easyui-validatebox" data-options="required:'false',missingMessage:'请填写学员编号'" style="width: 130px;"/></td>
				<th style="width: 65px;">报名日期:</th>
				<td><input name="createTime" class="easyui-datebox" data-options="showSeconds:false,editable:false" style="width: 155px;" /></td>
				
			<tr>
				<th style="width: 65px;">姓名:</th>
				<td><input name="name" class="easyui-validatebox" data-options="required:'true',validType:'validChinese',missingMessage:'请填写学员姓名'" style="width: 130px;"/></td>
				<th style="width: 65px;">身份证号:</th>
				<td><input name="identityId"  class="easyui-validatebox" data-options="required:'true',validType:'validateIdentity[15,18]',missingMessage:'请填写学员身份证号码'" /></td>
				<th style="width: 65px;">地址:</th>
				<td colspan="3"><input name="address"  class="easyui-validatebox" data-options="required:'true',missingMessage:'请填写学员地址'" /></td>
				
			</tr>
			
			<tr>
				<th style="width: 65px;">性别:</th>
				<td>
					<select class="easyui-combobox" name="sex" style="width:100px;">
       				 	<option value="">请选择</option>
       				 	<option value="男">男</option>
       				 	<option value="女">女</option>
    				</select>
    			</td>
				<th style="width: 55px;">手机:</th>
				<td><input name="phone"  class="easyui-validatebox" data-options="required:'true',validType:'validMobile',missingMessage:'请填写学员身手机号'" /></td>
				<th style="width: 65px;">应交学费:</th>
				<td colspan="3">
					<input name="feed"
					 	onblur="var reg = /^(\d+)$/; if(reg.test(this.value)) this.value=this.value.replace(reg,'$1.00');"
    					onfocus="var reg =/^(\d+)\.00$/;if(reg.test(this.value)) this.value=this.value.replace(reg,'$1');" 
    					class="easyui-validatebox" data-options="required:false,validType:'doublePrice',missingMessage:'请填写学员所交的学费'"
    					style="width: 80px;" /> 
					实收学费:<input name="realFeed" 
						onblur="var reg = /^(\d+)$/; if(reg.test(this.value)) this.value=this.value.replace(reg,'$1.00');"
    					onfocus="var reg =/^(\d+)\.00$/;if(reg.test(this.value)) this.value=this.value.replace(reg,'$1');"
    					class="easyui-validatebox" data-options="required:false,validType:'doublePrice',missingMessage:'请填写学员所交的学费'"
    					style="width: 80px;" />
				 	 欠费:<input name="ownFeed" 
				 	 onblur="var reg = /^(\d+)$/; if(reg.test(this.value)) this.value=this.value.replace(reg,'$1.00');"
    					onfocus="var reg =/^(\d+)\.00$/;if(reg.test(this.value)) this.value=this.value.replace(reg,'$1');"
				 	  style="width: 80px;" 
				 	  class="demo_student_disable"
				 	  value="0.00"/>
				 </td>
			</tr>
			
			<tr>
				<th style="width: 55px;">包含学时:</th>
				<td><input name="learningTime"  value="20" class="easyui-validatebox" data-options="required:'true',missingMessage:'请填写学员注册所含学时'" /></td>
				<th style="width: 55px;">邮箱:</th>
				<td><input name="email"  class="easyui-validatebox" data-options="validType:'email',missingMessage:'请填写学邮箱'" type="email" /></td>
				<th style="width: 65px;">出生日期:</th>
				<td><input name="birthdate" class="easyui-datebox" data-options="showSeconds:false,editable:false" style="width: 155px;" /></td>
				<th style="width: 65px;">照片回执:</th>
				<td><input name="imageId"  /></td>
			</tr>
			<tr>
				<th style="width: 55px;">居住证:</th>
				<td><input name="residenceId"  /></td>
				<th style="width: 65px;">居住证地址:</th>
				<td><input name="residenceAddr"  /></td>
				<th style="width: 65px;">申领类型:</th>
				<td ><input name="applyType" style="width: 150px;" /></td>
				<th style="width: 55px;">状态:</th>
				<td><select id="student_search_combobox" class="easyui-combobox" data-options="editable:false,enabled:false" name="nowState" style="width: 150px;">
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
				
				<th style="width: 55px;">班级:</th>
				<td ><input name="clazzName" style="width: 90px;" />
				 <a href="javascript:void(0);" id="demo_student_a_selectclazz"  >选择</a>
				</td>
				<th style="width: 55px;">教练:</th>
				<td ><input name="trainerName" style="width: 90px;" />
				 <a href="javascript:void(0);" id="demo_student_a_selecttrainer"  >选择</a>
				</td>
				<th style="width: 55px;">业务员:</th>
				<td ><input name="personName" style="width: 90px;" />
				 <a href="javascript:void(0);" id="demo_student_a_selectperson"  >选择</a>
				</td>
			</tr>
			<tr><th >备注:</th><td>(最多输入150个字)</td></tr>
			<tr><td colspan="8"><textarea name="comment" style="height: 100px;width: 98%;"></textarea></td></tr>
		</table>
	</fieldset>
	<fieldset >
		<legend>材料基本信息</legend>
		<table class="tableForm">
			<tr>
				<th style="width: 55px;">面签:</th>
				<td >
					<select class="easyui-combobox" name="faceSign" style="width:100px;">
						<option value="">请选择</option>
       				 	<option value="1"  selected="selected" >已签</option>
       				 	<option value="0">未签</option>
    				</select>
				</td>
				<th style="width: 55px;">提档:</th>
				<td >
					<select class="easyui-combobox" name="submitFile" style="width:100px;">
						<option value="">请选择</option>
       				 	<option value="1"  selected="selected" >已交</option>
       				 	<option value="0">未交</option>
    				</select>
				
				<th style="width: 55px;">照片:</th>
				<td >
					<select class="easyui-combobox" name="images" style="width:100px;">
						<option value="">请选择</option>
       				 	<option value="1"  selected="selected" >已交</option>
       				 	<option value="0">未交</option>
    				</select>
				</td>
				<th style="width: 55px;">体检表:</th>
				<td >
					<select class="easyui-combobox" name="examinationTable" style="width:100px;">
						<option value="">请选择</option>
       				 	<option value="1"  selected="selected" >已交</option>
       				 	<option value="0">未交</option>
    				</select>
				</td>
				<th style="width: 55px;">户籍:</th>
				<td >
					<select class="easyui-combobox" name="foreignStudent" style="width:100px;">
       				 	<option value="">请选择</option>
       				 	<option value="1" selected="selected">本地</option>
       				 	<option value="0">外籍</option>
    				</select>
				</td>
			</tr>
			<tr>	
				<th style="width: 55px;">计时卡:</th>
				<td >
					<select class="easyui-combobox" name="timingCar" style="width:100px;">
       				 	<option value="">请选择</option>
       				 	<option value="1" selected="selected">已发</option>
       				 	<option value="0">未发</option>
    				</select>
				</td>
				<th style="width: 55px;">身份证资料:</th>
				<td >
					<select class="easyui-combobox" name="indentityFlag" style="width:100px;">
       				 	<option value="">请选择</option>
       				 	<option value="1"  selected="selected" >已交</option>
       				 	<option value="0">未交</option>
    				</select>
				</td>
			</tr>
		</table>
	</fieldset>
	</form>
</div>
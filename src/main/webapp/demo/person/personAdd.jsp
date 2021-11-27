<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="padding: 5px;overflow: hidden;">
	<form id="demo_person_addForm" method="post">
	<fieldset >
		<legend>人员基本信息</legend>
		<table class="tableForm">
			<tr>
				<th style="width: 65px;">姓名:</th>
				<td><input name="name" class="easyui-validatebox" data-options="required:'true',missingMessage:'请填写学员姓名'" style="width: 150px;"/></td>
				<th style="width: 65px;">手机:</th>
				<td><input name="phone"  /></td>
				<th style="width: 65px;">性别:</th>
				<td><select class="easyui-combobox" name="sex" style="width:100%;height:auto;">
						<option value="">请选择</option>
						<option value="男">男</option>
                		<option value="女">女</option>
					</select>
				</td>
				<th style="width: 65px;">所属部门</th>
				<td><input id="demo_person_department" name="departmentId"  style="width:160px;" />
				   </td>
			<tr>
				<th style="width: 65px;">身份证号:</th>
				<td><input name="identityId"  class="easyui-validatebox" data-options="required:false,missingMessage:'请填写学员身份证号码'" /></td>
				<th style="width: 65px;">职务:</th>
				<td><input name="duty"  /></td>
				<th style="width: 65px;">政治面貌:</th>
				<td><input name="political"  /></td>
				<th style="width: 65px;">专业:</th>
				<td><input name="profession"  /></td>
			</tr>
			<tr>
				<th style="width: 65px;">毕业学校:</th>
				<td><input name="graduateSchool" /></td>
				<th style="width: 65px;">外语等级:</th>
				<td><input name="languageLevel" /></td>
				<th style="width: 65px;">计算机等级:</th>
				<td><input name="computerLevel"  /></td>
				<th style="width: 65px;">毕业日期:</th>
				<td><input name="graduateDay" class="easyui-datetimebox" data-options="showSeconds:false,editable:false"
					 style="width: 155px;" />
				</td>
			</tr>
			<tr>
				<th style="width: 65px;">民族:</th>
				<td><input name="nation"  /></td>
				<th style="width: 65px;">出生地:</th>
				<td><input name="birthPlace" /></td>
				<th style="width: 65px;">地址:</th>
				<td colspan="3"><input name="address"  /></td>
			</tr>
			<tr>
				<th style="width: 65px;">文化程度:</th>
				<td><input name="educationLevel"  /></td>
				<th style="width: 65px;">邮箱:</th>
				<td><input name="email" type="email" /></td>
				<th style="width: 65px;">驾照级别:</th>
				<td><input name="driverLevel"  /></td>
				<th style="width: 65px;">婚姻状况:</th>
				<td><select class="easyui-combobox" name="marriageState" style="width:100%;height:auto;">
						<option value="">请选择</option>
						<option value="未婚">未婚</option>
                		<option value="已婚">已婚</option>
					</select>
				</td>
			</tr>
			<tr>
				<th style="width: 65px;">出生日期:</th>
				<td><input name="birthday" class="easyui-datetimebox" data-options="showSeconds:false,editable:false"
					 style="width: 155px;" />
				</td>
				<th style="width: 65px;">参加工作日期:</th>
				<td><input name="startedJob" class="easyui-datetimebox" data-options="showSeconds:false,editable:false"
					 style="width: 155px;" />
				</td>
				<th style="width: 65px;">录用日期:</th>
				<td><input name="employDay" class="easyui-datetimebox" data-options="showSeconds:false,editable:false"
					 style="width: 155px;" />
				</td>
				<th style="width: 65px;">户口性质:</th>
				<td><input name="accountNature"  /></td>
			</tr>
			<tr><th >备注:</th></tr>
			<tr><td colspan="8"><textarea name="comment" style="height: 100px;width: 98%;"></textarea></td></tr>
			
		</table>
	</fieldset>
	</form>
</div>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="padding: 5px;overflow: hidden ;height:600px">
	<form id="demo_subjectFour_addForm" method="post">
	<input name="setet" type="hidden"/>
	<fieldset >
		<legend>资格考试学员筛选</legend>
		<table class="tableForm">
			<tr>
				<th style="width: 50px;">所属校区:</th>
				<td><input name="studentOrganization" style="width: 155px;"/></td>
				<th style="width: 50px;">业务类型:</th>
				<td><input name="driverType" style="width: 130px;"/></td>
				<th>注册日期</th>
				<td>
					<input name="registerTimeStart" class="easyui-datetimebox" data-options="showSeconds: false,editable:false"
					 style="width: 155px;" />
				</td>
				<th>至</th>
				<td>
					<input name="registerTimeEnd" class="easyui-datetimebox" data-options="showSeconds: false,editable:false"
					 style="width: 155px;" />
				</td>
			</tr>
			<tr>
				<th style="width: 55px;">姓名:</th>
				<td><input name="studentName"  /></td>
				<th style="width: 55px;">身份证号:</th>
				<td><input name="studentIdentityId"  /></td>
				<td colspan="2">
					<a href="javascript:void(0);" id="qualification_a_searche" >查询</a>
					<a href="javascript:void(0);" id="qualification_a_cancel" >重置</a>
				</td>
				<th style="width: 55px;"></th>
				<td>
				</td>
			</tr>
		</table>
	</fieldset>
	<fieldset >
		<legend>考试参数设置</legend>
			<table class="tableForm">
			<tr>
				<th>考试类型:</th>
				<td>
					<input name="examinationSubject" style="width: 155px;" />
				</td>
				<th style="width: 55px;">申报日期:</th>
				<td>
					<input name="applyDate"  style="width: 155px;" />
				</td>
				<th></th>
				<td></td>
			</tr>
		</table>			
	</fieldset>	
	<fieldset style="height:440px;">
		<legend >当前选择资格考试学员信息</legend>
		<table id="demo_qualification_selectStudent" class="tableForm"></table>
	</fieldset>
	</form>
</div>
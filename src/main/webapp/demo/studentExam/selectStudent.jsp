<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="padding: 5px;overflow: hidden ;height:615px">
	<form id="demo_examStudent_select_addForm" method="post">
	<input name="setet" type="hidden"/>
	<fieldset >
		<legend>查询学员信息</legend>
		<table class="tableForm">
			<tr>
				<th style="width: 65px;">所属校区:</th>
				<td><input  name="schoolArea" style="width: 155px;"/></td>
				<th style="width: 65px;">业务类型:</th>
				<td><input class="demo_selectStudent_clear" name="studentDriverType" style="width: 150px;"/></td>
				<th style="width: 55px;">姓名:</th>
				<td><input class="demo_selectStudent_clear" name="studentName"  style="width: 150px;"/></td>
				<th style="width: 65px;">学员编号:</th>
				<td><input class="demo_selectStudent_clear" name="studentNo"  style="width: 150px;"/></td>
			</tr>
			<tr>
				<th style="width: 65px;">身份证号:</th>
					<td><input class="demo_selectStudent_clear" name="studentIdentityId" style="width: 150px;" /></td>
				<th style="width: 65px;">注册日期</th>
				<td>
					<input name="registerTimeStart" class="easyui-datebox" data-options="showSeconds: false,editable:false"
					 style="width: 155px;" />
				</td>
				<th>至</th>
				<td>
					<input name="registerTimeEnd" class="easyui-datebox" data-options="showSeconds: false,editable:false"
					 style="width: 155px;" />
				</td>
				
			</tr>
			<tr>
				<th></th>
				<td></td>
				<td colspan="8" >
					<a href="javascript:void(0);" id="examAnalys_selectStudent_a_search">查询</a>
					<a href="javascript:void(0);" id="reservation_selectStudent_a_cancel" >重置</a>
				</td>
			</tr>
		</table>
	</fieldset>
	
	<fieldset style="height:485px;">
		<legend >学员信息列表</legend>
		<table id="demo_examAnalys_selectStudent" class="tableForm"></table>
	</fieldset>
	</form>
</div>
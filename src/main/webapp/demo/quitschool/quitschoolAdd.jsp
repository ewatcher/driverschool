<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="padding: 5px;overflow: hidden ;height:505px">
	<form id="demo_quitschool_addForm" method="post">
	<fieldset >
		<legend>学员信息查询</legend>
		<table class="tableForm">
			<tr>
				<th style="width: 60px;">所属校区:</th>
				<td><input class="demo_add_disable" name="studentOrganization" style="width: 155px;"/></td>
				<th style="width: 60px;">学员编号:</th>
				<td><input name="studentNo" style="width: 155px;"/></td>
				<th style="width: 60px;">姓名:</th>
				<td><input name="studentName"  /></td>
				<th style="width: 60px;">身份证号:</th>
				<td><input name="studentIdentityId"  /></td>
			</tr>
			<tr>
				<td colspan="8">
					<a href="javascript:void(0);" id="quitschool_a_searche"  >查询</a>
					<a href="javascript:void(0);" id="quitschool_a_cancel" >重置</a>
				</td>
			</tr>
		</table>
	</fieldset>
	<fieldset style="height:385px;">
		<legend >符合退学条件学员信息列表</legend>
		<table id="demo_quitschool_selectStudent" class="tableForm"></table>
	</fieldset>
	</form>
</div>
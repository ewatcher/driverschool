<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="padding: 5px;overflow: hidden ;height:500px">
	<form id="demo_usingcar_selectTrainerForm" method="post">
	<input name="setet" type="hidden"/>
	<input name="trainerId" type="hidden"/>
	<fieldset >
		<legend>教练信息筛选</legend>
		<table class="tableForm">
			<tr>
				<th style="width: 50px;">所属校区:</th>
				<td><input  name="schoolArea" style="width: 155px;"/></td>
				<th style="width: 55px;">姓名:</th>
				<td><input name="trainerName"  style="width: 155px;" /></td>
				<th style="width: 65px;">身份证:</th>
				<td><input name="trainerIdentityId" style="width: 155px;" /></td>
			</tr>
			<tr>
			    <th></th>
			    <th></th>
				<td colspan="2">
					<a href="javascript:void(0);" id="usingcar_a_search" >查询</a>
					<a href="javascript:void(0);" id="usingcar_a_cancel" >重置</a>
				</td>
			</tr>
		</table>
	</fieldset>
	
	<fieldset style="height:380px;">
		<legend >选择教练信息列表</legend>
		<table id="demo_usingcar_selecttrainer" class="tableForm"></table>
	</fieldset>
	</form>
</div>
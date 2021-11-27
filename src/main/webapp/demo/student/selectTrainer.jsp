<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="padding: 5px;overflow: hidden ;height:500px">
	<form id="demo_student_selectform" method="post" style="height:490px">
		<input type=hidden name=organizationId></input>
		<fieldset >
			<legend>查找教练信息输入</legend>
		<table class="tableForm">
			<tr>
				<th style="width: 80px;">姓名:</th>
				<td><input name="trainerName"  class="input_clean" style="width: 155px;"/></td>
				<th style="width: 80px;">身份证号:</th>
				<td><input name="trainerIdentity"  class="input_clean"  style="width: 155px;"/></td>
				<td colspan="2">
					<a href="javascript:void(0);" id="demo_student_searchTrainer" >查询</a>
					<a href="javascript:void(0);" id="demo_student_resetTrainer" >重置</a>
				</td>
			</tr>
		</table>
	</fieldset>
	<fieldset style="height:410px;">
		<legend >符合当前学员教练信息列表</legend>
		<table id="demo_student_selectTrainer" class="tableForm" ></table>
	</fieldset>	
	</form>	
</div>
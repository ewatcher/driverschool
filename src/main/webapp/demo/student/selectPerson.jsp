<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="padding: 5px;overflow: hidden ;height:590px">
	<form id="demo_student_selectform" method="post" style="height:490px">
		<fieldset >
			<legend>查找教练信息输入</legend>
		<table class="tableForm">
			<tr>
				<th style="width: 80px;">姓名:</th>
				<td><input name="personName" class="input_clean" style="width: 155px;"/></td>
				<th style="width: 80px;">身份证号:</th>
				<td><input name="personIdentityId" class="input_clean"  style="width: 155px;"/></td>
				<td colspan="2">
					<a href="javascript:void(0);" id="demo_student_searchPerson" >查询</a>
					<a href="javascript:void(0);" id="demo_student_resetPerson" >重置</a>
				</td>
			</tr>
		</table>
	</fieldset>
		<div id="personTabs" class="easyui-tabs" style="width:990px;height:540px;">
			<div title="业务员" style="padding:5px;">
				<table id="demo_student_selectPerson" class="tableForm" ></table>
			</div>
			<div title="教练员(业务员)" closable="false" style="padding:5px;">
				<table id="demo_student_selectTrainer" class="tableForm" ></table>
			</div>
		</div>
		
	</form>	
</div>
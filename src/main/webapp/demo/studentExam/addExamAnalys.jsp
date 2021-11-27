<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding: 5px;overflow: hidden;">
	<form id="demo_trainer_addForm" method="post">
	<fieldset >
		<legend>按批次添加考试学员信息</legend>
		<table class="tableForm">
			<tr>
				<th style="width: 65px;">所属校区:</th>
				<td><input name="schoolArea"  style="width: 155px;" ></td>
				<th style="width: 65px;">考试批次:</th>
				<td ><input name="batch" style="width: 150px;" ></td>
				<th style="width: 65px;">创建时间:</th>
				<td><input name="createDate"  style="width: 150px;" ></td>
			</tr>
		</table>
	</fieldset>
	</form>
</div>
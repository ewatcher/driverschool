<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="padding: 5px;overflow: hidden ;height:90px">
	<form id="demo_exmaScore_Form" method="post">
		<table class="tableForm">
				<tr>
					<th style="width:65px;">科目选择:</th>
					<td><input name="subjectType" style="width:155px;"/></td>
				</tr>
				<tr>
					<th style="width:65px;">成绩:</th>
					<td><input  name="examScore" style="width:155px;"/></td>
				</tr>
				<tr>
					<th style="width:65px;">成绩类型:</th>
					<td><input  name="examScoreFlag" style="width:155px;"/></td>
				</tr>
			</table>
	</form>
</div>
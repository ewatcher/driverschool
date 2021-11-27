<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="padding: 5px;overflow: hidden;">
	<form id="demo_studentExam_addForm" method="post">
	<input type="hidden" name="oilCardId"/>
	<input type="hidden" name="schoolArea"/>
	<fieldset >
		<legend style="color:#0000ff">学员批次基本信息</legend>
		<table class="tableForm">
			<tr>
				<th style="width:65px;">所属校区：</th>
				<td><input class="examAnalys_add_readonly" name="schoolArea" style="width:155px;"/></td>
				<th style="width:65px;">批次：</th>
				<td><input class="examAnalys_add_readonly" name="batch" style="width:155px;"/></td>
				<th style="width:65px;">创建日间：</th>
				<td><input class="examAnalys_add_readonly" name="createDate" style="width:155px;"/></td>
				<th style="width:65px;">当前总数：</th>
				<td><input class="examAnalys_add_readonly" name="total" style="width:155px;"/></td>
			</tr>
		</table>
	</fieldset>
	<fieldset>
		<legend style="color:#0000ff">学员信息查询</legend>
		<table class="tableForm " style="width: 100%;height: 100%;">
				<tr>
					<th style="width: 65px;">所属校区:</th>
					<td><input id="demo_studentExam_schoolArea"  name="schoolArea" style="width: 155px;"/></td>
					<th style="width: 65px;">学员编号:</th>
					<td><input id="search_studentNo" class="keypress_event" name="studentNo" style="width: 155px;"/></td>
					<th style="width: 65px;">姓名:</th>
					<td><input id="demo_theory_name" class="keypress_event" name="studentName"  style="width: 155px;"/></td>
					<th style="width: 65px;">身份证号:</th>
					<td><input name="studentIdentityId" class="keypress_event"  style="width: 155px;"/></td>
				</tr>
				<tr>
					<th colspan="2"></th>
					<td colspan="5">
						<a href="javascript:void(0);" id="studentExam_a_searche" >查询</a>
						<a href="javascript:void(0);" id="studentExam_a_repeat" >累加查询</a>
						<a href="javascript:void(0);" id="studentExam_a_canel" >取消</a>
					</td>
					<td></td>
				</tr>
			</table>
	</fieldset>
	<fieldset style="height:450px;">
		<legend style="color:#0000ff">当前批次考试学员明细</legend>
		<table id="demo_studentExam_datagrid"></table>
	</fieldset>
	</form>
</div>
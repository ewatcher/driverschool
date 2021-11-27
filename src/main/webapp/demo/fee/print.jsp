<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="myMain" style="padding: 5px;overflow: hidden;">
	<div id="mainRight">
		<form id="demo_feeDetailType_addForm" method="post">
			<div>
				<table class="titleForm">
					<tr>
						<th id="schoolAreaId" style="width:600px;text-align: center;font-size:20pt;"></th>
					</tr>
				</table>
			</div>
			<div>
				<table class="titleForm">
					<tr>
						<th style="width: 110px;">收费日期:</th>
						<td id="myCreateDate" style="width:180px;"></td>
					</tr>
				</table>
			</div>
			<fieldset style="width:600px">
				<table class="tickitForm">
					<tr>
						<th style="width: 60px;">今收到</th>
						<td id="handupMan" style="width:350px;"></td>
					</tr>
					<tr>
						<th style="width: 20px;">交来:</th>
						<td id="feeDetailTypeName" style="width:350px;"></td>
					</tr>
					<tr>
						<th style="width: 50px;">人民币:</th>
						<td id="realTotal" style="width:180px;"></td>
					</tr>
					<tr>
						<th style="width: 90px;">收款单位公章</th>
						<td id="ticketMaker"  style="width:240px;" ></td>
					</tr>
					<tr>
						<th style="width: 60px;">备注说明:</th>
						<td id="comment" style="width:350px;"></td>
					</tr>
				</table>
			</fieldset>
			<div>
				<table class="titleForm">
					<tr>
						<td id="schoolAreaName" style="width:250px;"></td>
						<th>报名热线:07762881311</th>
						<td><input name="" style="width:120px;" /></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</div>
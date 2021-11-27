<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="padding: 5px;overflow: hidden ;height:500px">
	<form id="demo_usingcar_addForm" method="post">
	<input name="setet" type="hidden"/>
	<fieldset >
		<legend>车辆信息筛选</legend>
		<table class="tableForm">
			<tr>
				<th style="width:65px;">所属校区:</th>
				<td><input  name="schoolArea" style="width: 155px;"/></td>
				<th style="width: 55px;">车牌号:</th>
				<td><input name="carNo"  style="width: 155px;" /></td>
				<th style="width: 65px;">行驶证号:</th>
				<td><input name="carLicenseNo" style="width: 155px;" /></td>
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
		<legend >符合条件车辆信息</legend>
		<table id="demo_usingcar_selectcar" class="tableForm"></table>
	</fieldset>
	</form>
</div>
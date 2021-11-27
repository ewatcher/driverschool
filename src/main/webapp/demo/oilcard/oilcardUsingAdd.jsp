<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="padding: 5px;overflow: hidden;">
	<form id="demo_oilcardUsing_addForm" method="post">
	<input type="hidden" name="oilCardId"/>
	<input type="hidden" name="schoolArea"/>
	<fieldset >
		<legend>油卡基本信息</legend>
		<table class="tableForm">
			<tr>
				<th style="width:65px;">所属校区：</th>
				<td><input class="recharge_add_readonly" name="schoolAreaName" style="width:155px;"/></td>
				<th style="width:65px;">卡号：</th>
				<td><input class="recharge_add_readonly" name="oilCardNo" style="width:155px;"/></td>
				<th style="width:65px;">当前余额：</th>
				<td><input class="recharge_add_readonly" name="beforeBalance" style="width:155px;"/></td>
				<th style="width:65px;">状态：</th>
				<td><input class="recharge_add_readonly" name="oilcardState" style="width:155px;"/></td>
			</tr>
		</table>
	</fieldset>
	<fieldset >
		<legend>使用登记明细信息</legend>
		<table class="tableForm">
			<tr>
				<th style="width:65px;">油料名称:</th>
				<td><input  name="oilName" style="width:155px;"/></td>
				<th style="width:65px;">油号:</th>
				<td><input  name="oilType" style="width:155px;"/></td>
				<th style="width: 65px;">教练:</th>
				<td >
					<input name="usingPerson" class="easyui-validatebox" data-options="required:'true',validType:'validChinese',missingMessage:'请选择教练'" style="width: 90px;" />
				 	<a href="javascript:void(0);" id="demo_oilcardUsing_a_selecttrainer"  >选择</a>
				 	<a href="javascript:void(0);" id="demo_oilcardUsing_a_canceltrainer"  >取消</a>
				</td>
				<th style="width: 65px;">车辆:</th>
				<td ><input name="carNo" style="width:150px;" /></td>
				
			</tr>
			<tr>
				<th style="width: 55px;">数量(升):</th>
				<td><input name="oilTotal" class="easyui-validatebox" data-options="required:'true',validType:'doublePrice',missingMessage:'油量不能为空'"  style="width: 155px;" /></td>
				<th style="width: 55px;">单价(元):</th>
				<td><input name="price" class="easyui-validatebox" data-options="required:'true',validType:'doublePrice',missingMessage:'价格不能为空'"  value="0.00" style="width: 155px;"/></td>
				<th style="width: 55px;">总价(元):</th>
				<td><input name="moneyTotal" class="easyui-validatebox" data-options="required:'true',validType:'doublePrice',missingMessage:'合计为能为空'" style="width: 155px;"/></td>
				<th style="width: 65px;">使用日期:</th>
				<td><input name=usingDate class="easyui-datebox" data-options="editable:false,required:true"   style="width: 100px;" /></td>
			</tr>
			<tr>
				<th style="width: 55px;">借卡时间:</th>
				<td ><input name="startTime" style="width: 155px;" /></td>
				<th style="width: 55px;">还卡时间:</th>
				<td ><input name="endTime" style="width: 155px;" /></td>
				<th style="width: 55px;">组别:</th>
				<td ><input name="groupType" style="width: 155px;" /></td>
			</tr>
			<tr><th >备注:</th><td>(最多输入150个字)</td></tr>
			<tr>
				<td colspan="8"><textarea name="remarks" style="height: 100px;width: 98%;"></textarea></td>
			</tr>
			
		</table>
	</fieldset>
	</form>
</div>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding: 5px;overflow: hidden;">
	<form id="demo_car_updateForm" method="post">
	<input name="id" type="hidden"/>
	<input name="carusingTempId" type="hidden"/>
	<fieldset >
		<legend>更新车辆基本信息</legend>
			<table class="tableForm">
			<tr>
				<th style="width: 70px;">所属校区:</th>
				<td><input name="schoolArea" style="width: 130px;" /></td>
				<th style="width: 70px;">车牌号:</th>
				<td><input name="carNo" class="easyui-validatebox" data-options="required:'true',missingMessage:'请填写车牌号'" style="width: 130px;"/></td>
				<th style="width: 70px;">行驶证号:</th>
				<td><input name="licenseNo"  class="easyui-validatebox" data-options="required:'true',missingMessage:'请填写行驶证号'"  style="width: 150px;"/></td>
				<th style="width: 70px;">车辆型号:</th>
				<td><input name="vehicleType"  style="width: 150px;"/></td>
			</tr>
			<tr>
				<th style="width: 70px;">车辆用途:</th>
				<td><input name="carType" style="width: 130px;"  /></td>
				<th style="width: 70px;">使用状态:</th>
				<td><input name=usingState  style="width: 130px;" />
				</td>
				<th style="width: 70px;">使用类型:</th>
				<td><input name="useType" style="width: 130px;" />
				</td>
				<th style="width: 70px;">车辆品牌:</th>
				<td><input name="vehicleBrands"  style="width: 130px;"/></td>
			</tr>	
			<tr>
				<th style="width: 70px;">运输证号:</th>
				<td><input name="transportNo" style="width: 130px;" /></td>
				<th style="width: 70px;">购买日期:</th>
				<td><input name="buyDate" class="easyui-datebox" data-options="editable:false"
					 style="width: 130px;" />
				</td>
				<th style="width: 70px;">商业保险到期日期:</th>
				<td><input name="businessExpireDay" class="easyui-datebox" data-options="editable:false"
					 style="width: 130px;" />
				</td>
				<th style="width: 70px;">交强险到期日期:</th>
				<td><input name="insuranceExpireDay" class="easyui-datebox" data-options="editable:false"
					 style="width: 130px;" />
				</td>
			</tr>
			<tr>
				<th style="width: 70px;">二次维护日期:</th>
				<td><input name="maintainDay" class="easyui-datebox" data-options="editable:false"
					 style="width: 130px;" />
				</td>
				<th style="width: 70px;">下次年审日期:</th>
				<td><input name="examinedDay" class="easyui-datebox" data-options="editable:false"
					 style="width: 130px;" />
				</td>
				<th style="width: 70px;">所属教练:</th>
				<td colspan="3"><input name="trainerId" style="width: 90px;" />
				 <a href="javascript:void(0);" id="demo_car_a_selecttrainer"  >选择</a>
				 <a href="javascript:void(0);" id="demo_car_a_canceltrainer"  >取消</a>
				</td>
			</tr>
			<tr>
				<th style="width: 70px;"><div style="color:red"></div>微信预约:</th>
				<td><input name="receiveWechatFlag" style="width: 130px;" /></td>
			</tr>
			<tr>
				<th style="width: 70px;">备注:</th>
				<td colspan="7"><textarea name="comment" style="height: 100px;width: 98%;"></textarea></td>
			</tr>
			
		</table>
	</fieldset>
	</form>
</div>
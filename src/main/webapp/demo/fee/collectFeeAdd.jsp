<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="padding: 5px;overflow: hidden;">
	<form id="demo_collectFee_addForm" method="post">
	<input name="handupMan" type="hidden">
	<input name="handupManType" type="hidden">
	<fieldset >
		<legend><div style="color:red">1:交费人基本信息</div></legend>
		<table class="tableForm">
			<tr>
				<th style="width: 65px;">所属部门</th>
				<td><input id="demo_collectFee_department" name="schoolArea"  style="width:150px;" /></td>
				<th style="width: 65px;">交费人:</th>
				<td><input name="handupManName" class="easyui-validatebox" data-options="required:'true',validType:'validChinese',missingMessage:'交费人不能为空'" style="width: 80px;"/> 
					<a href="javascript:void(0);" id="demo_collectFee_a_select"  ></a>
				  	<a href="javascript:void(0);" id="demo_collectFee_a_cancel"  ></a>
				</td>
				<th style="width: 65px;">身份证:</th>
				<td><input name="handupManIdentity" class="demo_collectFee_disable" style="width:150px"/>
				</td>
				<th style="width: 65px;">手机:</th>
				<td><input name="handupManPhone" class="demo_collectFee_disable" style="width: 125px;" />
			</tr>
		</table>
	</fieldset>
	<fieldset >
		<legend><div style="color:red">2:收费内容</div></legend>
		<table class="tableForm">
			<tr>
				<th style="width: 65px;">收费项目:</th>
				<td><input name="feeDetailType" style="width: 130px;"/></td>
				<th style="width: 65px;">收费金额:</th>
				<td><input name="collectTotal"
				class="easyui-validatebox" data-options="required:true,validType:'doublePrice',missingMessage:'请填写收费金额'"
				onblur="var reg=/^(\d+)$/;if(reg.test(this.value)) this.value=this.value.replace(reg,'$1.00');"
				onfocus="var reg =/^(\d+)\.00$/;if(reg.test(this.value)) this.value=this.value.replace(reg,'$1');"
				style="width: 150px;"/>
				</td>
				<th style="width: 65px;">优惠金额:</th>
				<td><input name="favouravleFee" id="demo_disable_ownTotal"
					class="easyui-validatebox" data-options="required:false,validType:'doublePrice',missingMessage:'请填写收费金额'"
					onblur="var reg = /^(\d+)$/; if(reg.test(this.value)) this.value=this.value.replace(reg,'$1.00');"
					onfocus="var reg =/^(\d+)\.00$/;if(reg.test(this.value)) this.value=this.value.replace(reg,'$1');"
					style="width: 150px;"/>
				</td>
				<th style="width: 65px;">实收金额:</th>
				<td><input name="realTotal"
				class="easyui-validatebox" data-options="required:true,validType:'doublePrice',missingMessage:'请填写收费金额'"
				onblur="var reg = /^(\d+)$/; if(reg.test(this.value)) this.value=this.value.replace(reg,'$1.00');"
				onfocus="var reg =/^(\d+)\.00$/;if(reg.test(this.value)) this.value=this.value.replace(reg,'$1');"
				style="width: 150px;"/>
				</td>
				
			<tr>
			<tr>
				<th style="width: 65px;">应收金额:</th>
				<td><input name="mustTotal" id="demo_disable_ownTotal"
					class="easyui-validatebox" data-options="required:false,validType:'doublePrice',missingMessage:'请填写收费金额'"
					onblur="var reg = /^(\d+)$/; if(reg.test(this.value)) this.value=this.value.replace(reg,'$1.00');"
					onfocus="var reg =/^(\d+)\.00$/;if(reg.test(this.value)) this.value=this.value.replace(reg,'$1');"
					style="width: 150px;"/>
				</td>
				<th style="width: 65px;">欠费金额:</th>
					<td><input name="ownTotal" id="demo_disable_ownTotal" style="width: 150px;"/>
				</td>
				<th style="width: 65px;">收费日期:</th>
				<td><input name="createTime" style="width: 155px;" />
				</td>
				<th style="width: 65px;">序号:</th>
				<td><input name="collectNo" class="demo_collectFee_disable"  style="width:150px;"/></td>
			</tr>
			<tr><th >备注:</th></tr>
			<tr><td colspan="8"><textarea name="comment" style="height: 100px;width: 98%;"></textarea></td></tr>
				
		</table>
	</fieldset>
	</form>
</div>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding: 5px;overflow: hidden;">
	<form id="demo_progress_detailForm" method="post">
	<fieldset >
		<legend>学员基本信息</legend>
		<table class="tableForm">
			<tr>
				<th style="width: 65px;">所属校区:</th>
				<td ><input class="demo_progress_detail" name="studentOrganization" style="width: 155px;" /></td>
				<th style="width: 65px;">姓名:</th>
				<td><input class="demo_progress_detail" name="studentName" style="width: 155px;" /></td>
				<th style="width: 65px;">性别:</th>
				<td><input class="demo_progress_detail" name="studentSex" style="width: 155px;" /></td>
				<th style="width: 65px;">学员编号:</th>
				<td><input name="studentNo"  class="demo_progress_detail"/></td>
			</tr>
			<tr>
				<th style="width: 65px;">报名日期:</th>
				<td><input class="demo_progress_detail" name="studentCreateTime" style="width: 155px;" /></td>
				<th style="width: 65px;">身份证号:</th>
				<td><input class="demo_progress_detail" name="studentIdentityId"  style="width: 155px;" /></td>
				<th>地址:</th>
				<td><input class="demo_progress_detail" name="studentAddress"  style="width: 155px;" /></td>
				<th style="width: 65px;">出生日期:</th>
				<td><input class="demo_progress_detail" name="studentBirthdate"  style="width: 155px;" /></td>
			</tr>
			
			<tr>
				<th style="width: 55px;">手机:</th>
				<td><input class="demo_progress_detail" name="studentPhone"   style="width: 155px;" /></td>
				<th style="width: 55px;">邮箱:</th>
				<td><input class="demo_progress_detail" name="studentEmail"  style="width: 155px;" /></td>
				<th style="width: 55px;">电话:</th>
				<td><input class="demo_progress_detail" name="studentTelephone"  style="width: 155px;" /></td>
				<th style="width: 55px;">邮编:</th>
				<td><input class="demo_progress_detail" name="studentMailCode"   style="width: 155px;" /></td>
			</tr>
			
			<tr>
				<th style="width: 55px;">国籍:</th>
				<td><input class="demo_progress_detail" name="studentContry"  style="width: 155px;" /></td>
				<th style="width: 55px;">地域:</th>
				<td><input class="demo_progress_detail" name="studentNativeNation" style="width: 155px;" /></td>
				<th style="width: 55px;">居住证:</th>
				<td><input class="demo_progress_detail" name="studentResidenceId"  style="width: 155px;" /></td>
				<th style="width: 65px;">居住证地址:</th>
				<td><input class="demo_progress_detail" name="studentResidenceAddr"  style="width: 155px;" /></td>
			</tr>
			
			<tr>
				<th style="width: 55px;">照片回执:</th>
				<td><input class="demo_progress_detail" name="studentImageId"  style="width: 155px;" /></td>
				<th style="width: 55px;">状态:</th>
				<td><input class="demo_progress_detail" name="studentNowstate"  style="width: 155px;" /></td>
				<th style="width: 55px;">班级:</th>
				<td ><input class="demo_progress_detail" name="studentClazz"  style="width: 155px;" /></td>
					<th style="width: 55px;">申领类型:</th>
				<td ><input class="demo_progress_detail" name="studentApplyType" style="width: 155px;" /></td>
			</tr>
			<tr>
				<th style="width: 55px;">驾照类型:</th>
				<td ><input class="demo_progress_detail" name="studentDriverType"  style="width: 155px;"/></td>
			</tr>
		</table>
	</fieldset>
	<fieldset >
		<legend>学员进度基本信息</legend>
		<div id="demo_quitschool_text"></div>
		<div id="demo_progress_title">
			<ul id="progressTitle">
				<li id="titleStart" class="progressLi">开始</li>
				<li id="titleSinup" class="progressLi">注册</li>
				<li id="titleSbuject1" class="progressLi">理论</li>
				<li id="titleSbuject2" class="progressLi">五项</li>
				<li id="titleSbuject3" class="progressLi">路考</li>
				<li id="titleSbuject4" class="progressLi">文明</li>
				<li id="titleGraduate" class="progressLi">毕业</li>
			</ul>
		</div>
		<div id="demo_progress_detail"></div> 
		<div id="demo_progress_date">
			<ul id="progressDate">
				<li id="dateStart" class="progressDateLi">开始</li>
				<li id="dateSinup" class="progressDateLi"></li>
				<li id="dateSbuject1" class="progressDateLi"></li>
				<li id="dateSbuject2" class="progressDateLi"></li>
				<li id="dateSbuject3" class="progressDateLi"></li>
				<li id="dateSbuject4" class="progressDateLi"></li>
				<li id="dateGraduate" class="progressDateLi"></li>
			</ul>
		</div>
		<div id="demo_quitschool_date"></div>
	</fieldset>		
	</form>
</div>
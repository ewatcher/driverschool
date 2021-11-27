<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">
	var mydatagrid;
	var userSchoolArea=undefined;
	$(function() {
		userSchoolArea=getUserSchoolArea();
		mydatagrid = $('#demo_studentExam_datagrid');
		mydatagrid.datagrid({
			url : '${pageContext.request.contextPath}/demo/studentExamAction!datagrid.action',
			iconCls : 'icon-save',
			pagination : true, 
			pagePosition : 'bottom', 
			pageSize : 20, 
			pageList : [ 10, 20, 30, 40 ], 
			fit : true,
			fitColumns : false, 
			nowrap : false, 
			border : false,
			singleSelect:false,
			rownumbers: true,
			idField : 'id', 
			sortName : 'updateTime', 
			sortOrder : 'desc', 
			checkOnSelect : true,
			selectOnCheck : true,
			striped : true,
			nowrap:true,
			method:'get',
			frozenColumns : [ [ {
				title : '编号',
				field : 'id',
				width : 20,
				checkbox : true
			},{
				title : '<font color="#0099FF">所属校区</font>',
				field : 'schoolAreaName',
				width : 120,
				align : 'center',
			},{
				title : '<font color="#0099FF">批次</font>',
				field : 'batch',
				width : 90,
				align : 'center',
				formatter:formatBatch,
			},{
				title : '<font color="#0099FF">学员编号</font>',
				field : 'studentNo',
				width : 90,
				align : 'center',
				formatter:formatStudentNo,
			},{
				title : '<font color="#0099FF">姓名</font>',
				field : 'studentName',
				width : 80,
				align : 'center',
				formatter:showAllMsg,
			},] ],
			//普通列
			columns : [ [{
				title : '<font color="#0099FF">性别</font>',
				field : 'studentSex',
				width : 50,
				align : 'center',
				formatter:showAllMsg,
				rowspan:2
			},{
				title : '<font color="#0099FF">身份证号</font>',
				field : 'studentIdentityId',
				width : 155,
				align : 'center',
				formatter:showAllMsg,
				rowspan:2
			}, {
				title : '<font color="#0099FF">报考车型</font>',
				field : 'studentDriverType',
				width : 60,
				align : 'center',
				formatter:formatterDriverType,
				rowspan:2
			},{
				title : '<font color="#0099FF">原有车型</font>',
				field : 'primaryDriver',
				width : 60,
				align : 'center',
				formatter:showAllMsg,
				rowspan:2
			},{
				title : '<font color="#0099FF">地址</font>',
				field : 'studentAddress',
				width : 260,
				formatter:showAllMsg,
				rowspan:2,
			},{
				title : '<font color="#0099FF">手机</font>',
				field : 'examPhone',
				width : 100 ,
				align:'center',
				rowspan:2
			},{
				title : '<font color="#0099FF">报名日期</font>',
				field : 'studentCreateTime',
				width : 100 ,
				align:'center',
				formatter:formatTimeYMD,
				rowspan:2
			},{
				title : '<font color="#FF7F00">科目一</font>',
				width : 100 ,
				align:'center',
				colspan:8,
			},{
				title : '<font color="#0000CD">科目二</font>',
				width : 100 ,
				align:'center',
				colspan:8,
			},{
				title : '<font color="#A020F0">科目三</font>',
				width : 100 ,
				align:'center',
				colspan:8,
			},{
				title : '<font color="#006400">科目四</font>',
				width : 100 ,
				align:'center',
				colspan:8,
			},{
				title : '<font color="#FF7F00">科目五</font>',
				width : 100 ,
				align:'center',
				colspan:8,
			}],[ {
				title : '<font color="#FF7F00">报考科一</font>',
				field : 'sub1ExamFlag',
				width : 70 ,
				align:'center',
				formatter:formatSubjectExamFalg,
			},{
				title : '<font color="#FF7F00">交警审批</font>',
				field : 'sub1ApplyFlag',
				width : 70 ,
				align:'center',
				formatter:formatApplyFlag,
			},{
				title : '<font color="#FF7F00">科一日期</font>',
				field : 'sub1ExamDate',
				width : 100,
				align:'center',
				formatter:formatTimeYMD,
			}, {
				title : '<font color="#FF7F00">科一成绩</font>',
				field : 'sub1Score',
				width : 70 ,
				align:'center',
				formatter:formatStudentExamScore,
			},{
				title : '<font color="#FF7F00">补考日期</font>',
				field : 'sub1MakeupDate',
				width : 100,
				align:'center',
				formatter:formatTimeYMD,
			}, {
				title : '<font color="#FF7F00">补考成绩</font>',
				field : 'sub1MakeupScore',
				width : 70 ,
				align:'center',
				formatter:formatStudentExamScore,
			},{
				title : '<font color="#FF7F00">补考次数</font>',
				field : 'sub1MakeupTimes',
				width : 70 ,
				align:'center',
			},{
				title : '<font color="#FF7F00">缺考</font>',
				field : 'sub1MissExamFlag',
				width : 70 ,
				align:'center',
				formatter:formatMissExam,
			},{
				title : '<font color="#0000CD">报考科二</font>',
				field : 'sub2ExamFlag',
				width : 70 ,
				align:'center',
				formatter:formatSubjectExamFalg,
			},{
				title : '<font color="#0000CD">交警审批</font>',
				field : 'sub2ApplyFlag',
				width : 70 ,
				align:'center',
				formatter:formatApplyFlag,
			},{
				title : '<font color="#0000CD">科二日期</font>',
				field : 'sub2ExamDate',
				width : 100,
				align:'center',
				formatter:formatTimeYMD,
			}, {
				title : '<font color="#0000CD">科二成绩</font>',
				field : 'sub2Score',
				width : 70 ,
				align:'center',
				formatter:formatStudentExamScore,
			},{
				title : '<font color="#0000CD">补考日期</font>',
				field : 'sub2MakeupDate',
				width : 100,
				align:'center',
				formatter:formatTimeYMD,
			}, {
				title : '<font color="#0000CD">补考成绩</font>',
				field : 'sub2MakeupScore',
				width : 70 ,
				align:'center',
				formatter:formatStudentExamScore,
			},{
				title : '<font color="#0000CD">补考次数</font>',
				field : 'sub2MakeupTimes',
				width : 70 ,
				align:'center',
			},{
				title : '<font color="#0000CD">缺考</font>',
				field : 'sub2MissExamFlag',
				width : 70 ,
				align:'center',
				formatter:formatMissExam,
			},{
				title : '<font color="#A020F0">报考科三</font>',
				field : 'sub3ExamFlag',
				width : 70 ,
				align:'center',
				formatter:formatSubjectExamFalg,
			},{
				title : '<font color="#A020F0">交警审批</font>',
				field : 'sub3ApplyFlag',
				width : 70 ,
				align:'center',
				formatter:formatApplyFlag,
			},{
				title : '<font color="#A020F0">科三日期</font>',
				field : 'sub3ExamDate',
				width : 100 ,
				align:'center',
				formatter:formatTimeYMD,
			}, {
				title : '<font color="#A020F0">科三成绩</font>',
				field : 'sub3Score',
				width : 70 ,
				align:'center',
				formatter:formatStudentExamScore,
			},{
				title : '<font color="#A020F0">补考日期</font>',
				field : 'sub3MakeupDate',
				width : 100,
				align:'center',
				formatter:formatTimeYMD,
			}, {
				title : '<font color="#A020F0">补考成绩</font>',
				field : 'sub3MakeupScore',
				width : 70 ,
				align:'center',
				formatter:formatStudentExamScore,
			},{
				title : '<font color="#A020F0">补考次数</font>',
				field : 'sub3MakeupTimes',
				width : 70 ,
				align:'center',
			},{
				title : '<font color="#A020F0">缺考</font>',
				field : 'sub3MissExamFlag',
				width : 70 ,
				align:'center',
				formatter:formatMissExam,
			},{
				title : '<font color="#006400">报考科四</font>',
				field : 'sub4ExamFlag',
				width : 70 ,
				align:'center',
				formatter:formatSubjectExamFalg,
			},{
				title : '<font color="#006400">交警审批</font>',
				field : 'sub4ApplyFlag',
				width : 70 ,
				align:'center',
				formatter:formatApplyFlag,
			},{
				title : '<font color="#006400">科四日期</font>',
				field : 'sub4ExamDate',
				width : 100 ,
				align:'center',
				formatter:formatTimeYMD,
			}, {
				title : '<font color="#006400">科四成绩</font>',
				field : 'sub4Score',
				width : 70 ,
				align:'center',
				formatter:formatStudentExamScore,
			},{
				title : '<font color="#006400">补考日期</font>',
				field : 'sub4MakeupDate',
				width : 100,
				align:'center',
				formatter:formatTimeYMD,
			}, {
				title : '<font color="#006400">补考成绩</font>',
				field : 'sub4MakeupScore',
				width : 70 ,
				align:'center',
				formatter:formatStudentExamScore,
			},{
				title : '<font color="#006400">补考次数</font>',
				field : 'sub4MakeupTimes',
				width : 70 ,
				align:'center',
			},{
				title : '<font color="#006400">缺考</font>',
				field : 'sub4MissExamFlag',
				width : 70 ,
				align:'center',
				formatter:formatMissExam,
			},{
				title : '<font color="#FF7F00">报考科五</font>',
				field : 'sub5ExamFlag',
				width : 70 ,
				align:'center',
				formatter:formatSubjectExamFalg,
			},{
				title : '<font color="#FF7F00">交警审批</font>',
				field : 'sub5ApplyFlag',
				width : 70 ,
				align:'center',
				formatter:formatApplyFlag,
			},{
				title : '<font color="#FF7F00">资格日期</font>',
				field : 'sub5ExamDate',
				width : 100 ,
				align:'center',
				formatter:formatTimeYMD,
			}, {
				title : '<font color="#FF7F00">资格成绩</font>',
				field : 'sub5Score',
				width : 70 ,
				align:'center',
				formatter:formatStudentExamScore,
			},{
				title : '<font color="#FF7F00">补考日期</font>',
				field : 'sub5MakeupDate',
				width : 100,
				align:'center',
				formatter:formatTimeYMD,
			}, {
				title : '<font color="#FF7F00">补考成绩</font>',
				field : 'sub5MakeupScore',
				width : 70 ,
				align:'center',
				formatter:formatStudentExamScore,
			},{
				title : '<font color="#FF7F00">补考次数</font>',
				field : 'sub5MakeupTimes',
				width : 70 ,
				align:'center',
			},{
				title : '<font color="#FF7F00">缺考</font>',
				field : 'sub5MissExamFlag',
				width : 70 ,
				align:'center',
				formatter:formatMissExam,
			},{
				title : '<font color="#0099FF">schoolArea</font>',
				field : 'schoolArea',
				width : 60 ,
				align:'center',
			}, {
				title : '<font color="#0099FF">studentId</font>',
				field : 'studentId',
				width : 60 ,
				align:'center',
			},]],
			toolbar:studentExamToolbar,
			onLoadSuccess:onLoadSuccess,
			
		});
	});
	
	//教学学员明细列表toolbar
	var studentExamToolbar=[  { 
		id:'examAnalys-btn-detail',
		text : '取消选中',
		iconCls : 'icon-add',
		handler : function() {
			cancelSelect();
		}
	}, '-',{
		id:'examAnalys-btn-reload',
		text : '刷新',
		iconCls : 'icon-reload',
		handler : function() {
			reload();
		}
	}, '-' ];
	
	//刷新考试学员明细datagrid
	function reloadStudentExam(){
	 	var rows=mydatagrid.datagrid('getChecked');
	 	mydatagrid.datagrid('load',{batch:rows[0].batch});
		//取消所有的选中行
		mydatagrid.datagrid('clearSelections');
		//取消当前页面的选中行
		mydatagrid.datagrid('unselectAll');
		mydatagrid.datagrid('uncheckAll');
	}
	
	//取消选中考试学员明细datagrid
	function cancelSelect(){
		//取消所有的选中行
		mydatagrid.datagrid('clearSelections');
		//取消当前页面的选中行
		mydatagrid.datagrid('unselectAll');
		mydatagrid.datagrid('uncheckAll');
	}
	
	
	function reload(){
		mydatagrid.datagrid('reload');
		//取消所有的选中行
		mydatagrid.datagrid('clearSelections');
		//取消当前页面的选中行
		mydatagrid.datagrid('unselectAll');
		mydatagrid.datagrid('uncheckAll');
		$("#examAnalys-btn-add").linkbutton('enable');
		$("#examAnalys-btn-edit").linkbutton('enable');
		$("#examAnalys-btn-remove").linkbutton('enable');
		$("#examAnalys-btn-detail").linkbutton('enable');
	}
	//学员考试列表
	function onLoadSuccess(data) {
		mydatagrid.datagrid("fixRownumber");
		mydatagrid.datagrid('hideColumn','schoolArea');
		mydatagrid.datagrid('hideColumn','studentId');
		
		//初始化校区选项
		initSchoolArea($("#demo_studentExam_schoolArea"),userSchoolArea);
		//添加回车事件
		bindingKeypressEvent($(".keypress_event"),mydatagrid,$("#demo_allStudentExam_addForm"));
		//初始化考试科目
		initSubjectType($("#studentExam_subjectType"), null, false);
		//初始化考试成绩
		initExamScore($("#studentExam_examScore"), null, false);
		//datagrid没有数据时显示没有记录信息
		if(data.total==0){
			mydatagrid.datagrid('appendRow', {
				schoolAreaName:  '<div style=color:red>没有记录信息！</div>' })
			.datagrid('mergeCells', { index: 0, field: 'schoolAreaName', colspan:4 });
		}
	}
	
	//查询按钮功能函数
	function mySearch() {
		mydatagrid.datagrid('load', sy.serializeObject($('#demo_allStudentExam_searchForm')));
	}

	//取消查询功能按钮
	function cleanSearch() {
		mydatagrid.datagrid('load', {});
		//清空与查询有关的输入框
		$('#demo_allStudentExam_searchForm input').val('');
	}
	
</script>
</head>
<body class="easyui-layout" data-options="fit:true">

	<div data-options="region:'north',border:false,title:'查询功能'" style="height: 85px;overflow: hidden;" align="left">
		<form id="demo_allStudentExam_searchForm" method="post">
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
						<th style="width: 65px;">考试批次:</th>
						<td><input name="batch" class="keypress_event"  style="width: 155px;"/></td>
						<th style="width: 65px;">考试科目:</th>
						<td><input id="studentExam_subjectType" name="subjectType"  style="width: 155px;"/></td>
						<th style="width: 65px;">考试成绩:</th>
						<td><input id="studentExam_examScore" name="examScore"   style="width: 155px;"/></td>
						<td colspan="3">
							<a href="javascript:void(0);" id="studentExam_a_searche" class="easyui-linkbutton" iconCls="icon-search" onclick="mySearch();">查询</a>
							<a href="javascript:void(0);" id="studentExam_a_canel" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cleanSearch();">取消</a>
						</td>
						<td></td>
					</tr>
				</table>
		</form>
	</div>
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<table id="demo_studentExam_datagrid"></table>
	</div>
	<!-- 操作按钮事件 -->
	<div id="demo_examAnalys_menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="reload();" data-options="iconCls:'icon-filter'">取消选中</div>
		<div onclick="reload();" data-options="iconCls:'icon-reload'">刷新</div>
	</div>
	
</body>
</html>	
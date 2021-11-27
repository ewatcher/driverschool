<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">
	var mydatagrid;
	$(function() {
		mydatagrid = $('#student_datagrid').datagrid({
			url : 'studentAction!studentMsgDatagrid.action',/* 从后台读取所有user数据 */
			title : '学生信息管理列表!', /* 表头标题 */
			iconCls : 'icon-save', /* 标题前边的图标 */
			pagination : true, /* 是否显示页码 */
			pagePosition : 'bottom', /* 页码显示的位置 */
			pageSize : 10, /* 每面显示的记录数 */
			pageList : [ 10, 20, 30, 40 ], /* 选择每面显示的记录数 */
			fit : true,
			fitColumns : false, //列自动调整功能
			nowrap : false, //以行的形式进行显示
			border : false,
			rownumbers: true,
			idField : 'id', //此属性一定要配置
			sortName : 'createTime', //列排序
			sortOrder : 'desc', //列排序
			checkOnSelect : false,
			selectOnCheck : true,
			striped : true,//表示条纹，隔行显示不现背景色
			nowrap:true,//单元显示
			//冻结列
			frozenColumns : [ [ {
				title : '编号',
				field : 'id',
				width : 20,
				sortable : true,
				checkbox : true
			},{
				title : '<font color="#0099FF">所属校区</font>',
				field : 'organizationName',
				width : 150,
				align : 'center'
			}, {
				title : '<font color="#0099FF">姓名</font>',
				field : 'name',
				width : 80,
				sortable : true,
				align : 'center'
			}, {
				title : '<font color="#0099FF">学员编号</font>',
				field : 'studentNo',
				width : 120,
				sortable : true,
				align : 'center'
			}] ],
			//普通列
			columns : [ [ {
				title : '<font color="#0099FF">性别</font>',
				field : 'sex',
				width : 60 //在datagrid中一定要添加列宽度，否则会加长datagrid渲染的时间，消耗系统资源
			}, {
				title : '<font color="#0099FF">出生日期</font>',
				field : 'birthdate',
				sortable : true,
				width : 150,
				align : 'center',
				
			}, {
				title : '<font color="#0099FF">身份证号</font>',
				field : 'identityId',
				sortable : true,
				width : 150
			}, {
				title : '<font color="#0099FF">地址</font>',
				field : 'address',
				sortable : true,
				width : 150
			}, {
				title : '<font color="#0099FF">报名日期</font>',
				field : 'createTime',
				sortable : true,
				width : 150,
				
			},{
				title : '<font color="#0099FF">应交学费</font>',
				field : 'feed',
				sortable : true,
				width : 70,
				align : 'center',
			},{
				title : '<font color="#0099FF">实收学费</font>',
				field : 'realFeed',
				sortable : true,
				width : 70,
				align : 'center',
			},{
				title : '<font color="#0099FF">欠学费</font>',
				field : 'ownFeed',
				sortable : true,
				width : 70,
				align : 'center',
			},{
				title : '<font color="#0099FF">学时</font>',
				field : 'learningTime',
				sortable : true,
				width : 70,
				align : 'center',
			},{
				title : '<font color="#0099FF">毕业日期</font>',
				field : 'graduateDate',
				sortable : true,
				width : 150,
				
			},{
				title : '<font color="#0099FF">状态</font>',
				field : 'nowState',
				width : 100,
				formatter:function(value,row,index){
					if(value==1){
						return '在培';
					}else if(value==2){
						return '毕业';
					}else if(value==3){
						return '退学';
					}else if(value==4){
						return '到期';
					}else if(value==5){
						return '不计时';
					}else{
						return '';
					}
				}
			}, {
				title : '<font color="#0099FF">手机</font>',
				field : 'phone',
				width : 100
			}, {
				title : '<font color="#0099FF">固定电话</font>',
				field : 'telephone',
				width : 100
			}, {
				title : '<font color="#0099FF">邮箱</font>',
				field : 'email',
				sortable : true,
				width : 150
			}, {
				title : '<font color="#0099FF">照片回执编号</font>',
				field : 'imageId',
				sortable : true,
				width : 150
			}, {
				title : '<font color="#0099FF">邮编</font>',
				field : 'mailCode',
				sortable : true,
				width : 100
			}, {
				title : '<font color="#0099FF">居住证</font>',
				field : 'residenceId',
				sortable : true,
				width : 150
			}, {
				title : '<font color="#0099FF">居住证地址</font>',
				field : 'residenceAddr',
				sortable : true,
				width : 150
			}, {
				title : '<font color="#0099FF">国籍</font>',
				field : 'contry',
				sortable : true,
				width : 70
			}, {
				title : '<font color="#0099FF">地域</font>',
				field : 'nativeNation',
				sortable : true,
				width : 70
			}, {
				title : '<font color="#0099FF">申领类型</font>',
				field : 'applyType',
				sortable : true,
				width : 150,
				formatter:function(value,row,index){
					if(value==1){
						return '初次申领';
					}else if(value==2){
						return '增驾准车型';
					}else if(value==3){
						return '持军官驾驶证';
					}else if(value==4){
						return '持境外驾驶证';
					}else{
						return '';
					}
				}
			},{
				title : '<font color="#0099FF">驾照类型</font>',
				field : 'driverType',
				sortable : true,
				width : 70,
				formatter:function(value,row,index){
					return formatterDriverType(value, row, index);
				}
			},{
				title : '<font color="#0099FF">班级</font>',
				field : 'clazzName',
				sortable : true,
				width : 100
			},{
				title : '<font color="#0099FF">班级标识</font>',
				field : 'clazzId',
				sortable : true,
				width : 100
			},{
				title : '<font color="#0099FF">教练标识</font>',
				field : 'trainerId',
				sortable : true,
				width : 100
			},{
				title : '<font color="#0099FF">教练名称</font>',
				field : 'trainerName',
				sortable : true,
				width : 100
			},{
				title : '<font color="#0099FF">教练电话</font>',
				field : 'trainerPhone',
				sortable : true,
				width : 150
			},{
				title : '<font color="#0099FF">备注</font>',
				field : 'comment',
				sortable : true,
				width : 200
			},
			] ],
			//菜单功能设置
			toolbar : [ {
				text : '详细信息',
				iconCls : 'icon-filter',
				handler : function() {
					read();
				}
			}, '-', {
				text : '取消选中',
				iconCls : 'icon-undo',
				handler : function() {
					//取消所有的选中行
					mydatagrid.datagrid('clearSelections');
					//取消当前页面的选中行
					mydatagrid.datagrid('unselectAll');
				}
			}, ],
			onLoadSuccess : function (data) {
				
				//修改序号bug
				$(this).datagrid("fixRownumber");
				$(this).datagrid('hideColumn','clazzId');
				$(this).datagrid('hideColumn','trainerId');
				$(this).datagrid('hideColumn','personId');
				$(this).datagrid('hideColumn','organizationId');
				$(this).datagrid('hideColumn','examSelected');
				//初始化校区选项
				
				//datagrid没有数据时显示没有记录信息
				if(data.total==0){
					mydatagrid.datagrid('appendRow', {
						studentNo: '<div style="text-align:center;color:red">没有学员记录信息！</div>' })
					.datagrid('mergeCells', { index: 0, field: 'studentNo', colspan:8 });
				}
			},
			//右键菜单所触发的事件
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				//取消当前页被选中的所有行
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow', rowIndex);
				$('#menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			}
		});

	});
	

	//单击编辑按钮，实现对用户的编辑功能
	function read() {
		var stuDriverType=undefined;
		var clazzId=undefined;
		var trainerId=undefined;
		var personId=undefined;
		var rows = mydatagrid.datagrid('getSelections');
		//编辑时只能单选一行进行编辑
		if (rows.length == 1) {
			var p = parent.sy.dialog({
						title : '学员个人详细信息',
						href : '${pageContext.request.contextPath}/demo/studentAction!toMyMessageDetailPage.action',
						width : 970,
						height : 540,
						buttons : [
								 {
									text : '关闭',
									iconCls : 'icon-cancel',
									handler : function() {
										p.dialog('close');
									}
								} ],
						//数据回显显示
						onLoad : function() {
							var f = p.find('form');
							f.form('load', {
								id : rows[0].id,
								name : rows[0].name,
								sex : rows[0].sex,
								birthdate : rows[0].birthdate,
								identityId : rows[0].identityId,
								address : rows[0].address,
								contry : rows[0].contry,
								nativeNation : rows[0].nativeNation,
								telephone : rows[0].telephone,
								phone : rows[0].phone,
								email : rows[0].email,
								mailCode : rows[0].mailCode,
								residenceId : rows[0].residenceId,
								residenceAddr : rows[0].residenceAddr,
								imageId : rows[0].imageId,
								createTime : rows[0].createTime,
								graduateDate:rows[0].graduateDate,
								nowState : rows[0].nowState,
								studentNo:rows[0].studentNo,
								comment:rows[0].comment,
								
								natively : rows[0].natively,
								hight : rows[0].hight,
								rightEye : rows[0].rightEye,
								leftEye : rows[0].leftEye,
								analyColor : rows[0].analyColor,
								hearing : rows[0].hearing,
								rightUpperLimb : rows[0].rightUpperLimb,
								leftUpperLimb : rows[0].leftUpperLimb,
								rightDownLimb : rows[0].rightDownLimb,
								leftDownLimb : rows[0].leftDownLimb,
								body : rows[0].body,
								eyeRedress : rows[0].eyeRedress,
								examDay : rows[0].examDay,
								hospital : rows[0].hospital,
								driverType : rows[0].driverType,
								applyType : rows[0].applyType,
								organizationName:rows[0].organizationName,
								examSelected:rows[0].examSelected,
								
								clazzId : rows[0].clazzId,
								clazzName : rows[0].clazzName,
								trainerId : rows[0].trainerId,
								trainerName : rows[0].trainerName,
								personId : rows[0].personId,
								personName : rows[0].personName,
							});
							
							var f = p.find('form');
							var myDriverType=f.find('input[name=driverType]');
							var myApplyType=f.find('input[name=applyType]');
							var myOrganization=f.find('input[name=organizationName]')
							myDriverType.combobox({
								url : '${pageContext.request.contextPath}/demo/json/driverType_combobox.json',
								valueField : 'value',
								textField : 'text',
								method:'get',
							}); 
							myApplyType.combobox({
								url : '${pageContext.request.contextPath}/demo/json/applyType_combobox.json',
								valueField : 'value',
								textField : 'text',
								method:'get',
							});
							myApplyType.validatebox({
								required:true,
							});
							
						}
					});
		} else if (rows.length > 1) {
			parent.sy.messagerAlert('提示信息', '同一时间只能更新一条记录！', 'error');
		} else {
			parent.sy.messagerAlert('提示信息', '请选择要更新的记录！', 'error');
		}
	}

	
</script>
</head>
<body class="easyui-layout" data-options="fit:true">
	
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<table id="student_datagrid"></table>
	</div>
	<!-- 操作按钮事件 -->
	<div id="menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="read();" data-options="iconCls:'icon-filter'">详细信息</div>
	</div>
</body>
</html>
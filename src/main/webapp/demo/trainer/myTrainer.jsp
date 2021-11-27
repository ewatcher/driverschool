<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">
	var mydatagrid;
	var driverTypeValue=undefined;
	$(function() {
		mydatagrid = $('#demo_trainer_datagrid').datagrid({
			url : 'trainerAction!personalDatagrid.action',/* 从后台读取该实体类所有数据 */
			title : '教练信息管理列表!', /* 表头标题 */
			iconCls : 'icon-save', /* 标题前边的图标 */
			pagination : true, /* 是否显示页码 */
			pagePosition : 'bottom', /* 页码显示的位置 */
			pageSize : 10, /* 每面显示的记录数 */
			pageList : [ 10, 20, 30, 40 ], /* 选择每面显示的记录数 */
			fit : true,
			fitColumns : false, //列自动调整功能
			border : false,
			rownumbers: true,
			idField : 'id', //此属性一定要配置
			sortName : 'name', //列排序
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
			}, {
				title : '<font color="#0099FF">所属校区</font>',
				field : 'schoolArea',
				width : 100,
				align : 'center'
			},{
				title : '<font color="#0099FF">姓名</font>',
				field : 'name',
				width : 80,
				sortable : true,
				align : 'center'
			} ,{
				title : '<font color="#0099FF">身份证</font>',
				field : 'identity',
				sortable : true,
				width : 150, //在datagrid中一定要添加列宽度，否则会加长datagrid渲染的时间，消耗系统资源
			}
			] ],
			//普通列
			columns : [ [ {
				title : '<font color="#0099FF">手机</font>',
				field : 'phone',
				sortable : true,
				width : 150,
				align : 'center'
			}, {
				title : '<font color="#0099FF">性别</font>',
				field : 'sex',
				sortable : true,
				width : 60
			}, {
				title : '<font color="#0099FF">出生日期</font>',
				field : 'birthday',
				sortable : true,
				width : 150
			}, {
				title : '<font color="#0099FF">入职日期</font>',
				field : 'jionDay',
				sortable : true,
				width : 150
			},{
				title : '<font color="#0099FF">民族</font>',
				field : 'nation',
				sortable : true,
				width : 80
			}, {
				title : '<font color="#0099FF">籍贯</font>',
				field : 'contry',
				width : 100
			}, {
				title : '<font color="#0099FF">地址</font>',
				field : 'addres',
				width : 200
			}, {
				title : '<font color="#0099FF">固定电话</font>',
				field : 'telephone',
				width : 100
			}, {
				title : '<font color="#0099FF">邮箱</font>',
				field : 'email',
				width : 150
			}, {
				title : '<font color="#0099FF">驾驶证ID</font>',
				field : 'driverLicenseId',
				sortable : true,
				width : 150
			},{
				title : '<font color="#0099FF">准驾类型</font>',
				field : 'driverType',
				sortable : true,
				width : 70,
				formatter:function(value,row,index){
					driverTypeValue=value;
					if(value==1){
						return 'A1';
					}else if(value==2){
						return 'A2';
					}else if(value==3){
						return 'A2';
					}else if(value==4){
						return 'B1';
					}else if(value==5){
						return 'B2';
					}else if(value==6){
						return 'C1';
					}else if(value==7){
						return 'C2';
					}else if(value==8){
						return 'C3';
					}else if(value==9){
						return 'C4';
					}else if(value==10){
						return 'D';
					}else if(value==11){
						return 'E';
					}else if(value==12){
						return 'F';
					}else if(value==13){
						return 'M';
					}else if(value==14){
						return 'N';
					}else{
						return '';
					}
				}
				
			},{
				title : '<font color="#0099FF">准教车型</font>',
				field : 'driverCarType',
				sortable : true,
				width : 180,
				formatter:function(value,row,index){
					if(driverTypeValue==1){
						return 'A1,A3,B1,B2,C1,C2,C3,C4,M';
					}else if(driverTypeValue==2){
						return 'B1,B2,C1,C2,C3,C4,M';
					}else if(driverTypeValue==3){
						return 'C1,C2,C3,C4';
					}else if(driverTypeValue==4||driverTypeValue==5){
						return 'C1,C2,C3,C4,M';
					}else if(driverTypeValue==6){
						return 'C2,C3,C4';
					}else if(driverTypeValue==8){
						return 'C4';
					}else if(driverTypeValue==10){
						return 'E,F';
					}else if(driverTypeValue==11){
						return 'F';
					}else{
						return '';
					}
				}
				
			}, {
				title : '<font color="#0099FF">初次领证日期</font>',
				field : 'firstGetDate',
				sortable : true,
				width : 150
			},  {
				title : '<font color="#0099FF">驾驶证有效日期</font>',
				field : 'driverValidatyStart',
				sortable : true,
				width : 150
			}, {
				title : '<font color="#0099FF">驾驶证有效日期</font>',
				field : 'driverValidatyEnd',
				sortable : true,
				width : 150
			}, {
				title : '<font color="#0099FF">教员编号</font>',
				field : 'teachId',
				sortable : true,
				width : 100
			}, {
				title : '<font color="#0099FF">教员有效期</font>',
				field : 'teachValidatyStart',
				sortable : true,
				width : 150
			}, {
				title : '<font color="#0099FF">教员有效期</font>',
				field : 'teachBalidatyEnd',
				sortable : true,
				width : 150
			}, {
				title : '<font color="#0099FF">教员初次领证</font>',
				field : 'firstTeachingDate',
				sortable : true,
				width : 150
			}, {
				title : '<font color="#0099FF">备注</font>',
				field : 'comment',
				sortable : true,
				width : 250
			},
			] ],
			//菜单功能设置
			toolbar : [{
				text : '详细信息',
				iconCls : 'icon-filter',
				handler : function() {
					read();
				}
			}, '-',{
				text : '取消选中',
				iconCls : 'icon-undo',
				handler : function() {
					//取消所有的选中行
					mydatagrid.datagrid('clearSelections');
					//取消当前页面的选中行
					mydatagrid.datagrid('unselectAll');
				}
			}, '-' ],
			onLoadSuccess : function (data) {
				//修改序号bug
				$(this).datagrid("fixRownumber");
				/* $(this).datagrid("autoMergeCells",['phone','sex']); */
				
				//datagrid没有数据时显示没有记录信息
				if(data.total==0){
					$(this).datagrid('appendRow', {
						schoolAreaName: '<div style="text-align:center;color:red">没有教练记录信息！</div>' })
					.datagrid('mergeCells', { index: 0, field: 'sex', colspan:14 });
				}
			},
			//右键菜单所触发的事件
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				//取消当前页被选中的所有行
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow', rowIndex);
				$('#demo_trainer_menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			}
		});

	});

	//查看详细信息
	//单击编辑按钮，实现对用户的编辑功能
	function read() {
		var rows = mydatagrid.datagrid('getSelections');
		//编辑时只能单选一行进行查看
		if (rows.length == 1) {
			var p = parent.sy.dialog({
						title : '----> 教练详细信息  <----',
						//通过href方式打开添加页面
						href : '${pageContext.request.contextPath}/demo/trainerAction!toMyDetailPage.action',
						width : 960,
						height : 510,
						buttons : [{
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
								codeNo:rows[0].codeNo,
								birthday : rows[0].birthday,
								identity : rows[0].identity,
								addres : rows[0].addres,
								contry : rows[0].contry,
								telephone : rows[0].telephone,
								phone : rows[0].phone,
								email : rows[0].email,
								getCodeNo:rows[0].codeNo,
								nation:rows[0].nation,
								comment:rows[0].comment,
								driverLicenseId:rows[0].driverLicenseId,
								firstGetDate:rows[0].firstGetDate,
								driverType:rows[0].driverType,
								driverCarType:rows[0].driverCarType,
								driverValidatyStart:rows[0].driverValidatyStart,
								driverValidatyEnd:rows[0].driverValidatyEnd,
								teachId:rows[0].teachId,
								teachValidatyStart:rows[0].teachValidatyStart,
								teachBalidatyEnd:rows[0].teachBalidatyEnd,
								firstTeachingDate:rows[0].firstTeachingDate,
								jionDay:rows[0].jionDay,
								schoolArea:rows[0].schoolArea,
							});
							
							var myDriverCarType=undefined;
							//1.初始化教练准教类型选择
							 myDriverCarType=f.find('input[name=driverCarType]');
							var driverCombobox=myDriverCarType.combobox({
								url:'${pageContext.request.contextPath}/demo/json/driverType_combobox.json',
							    valueField:'value',
							    textField:'text',
							    multiple:true,
							    multiline:true,
							    method:'post',
							    readonly:true,
							    separator:',',
							    hasDownArrow : true, 
							    //初始化训练时段默认值被选中
							    onLoadSuccess:function(){
									var temp=rows[0].trainTime;
									var as=new Array();
									as=temp.toString().split(",");
									for(var i=0;i<as.length;i++){
										myDriverCarType.combobox('select',as[i]);
									}
								}
							});
							
							//2.初始化教练驾照类型选择
							var myDriverType=f.find('input[name=driverType]');
							myDriverType.combobox({
								url : '${pageContext.request.contextPath}/demo/json/driverType_combobox.json',
								valueField : 'value',
								textField : 'text',
								method:'get',
								editable:true,
								onChange:function(){
									//1.首先清空教练准教类型combobox
									myDriverCarType.combobox('clear');
									var comboValue=myDriverType.combobox('getValue');
									if(comboValue==1){
										var temp=[1,3,4,5,6,7,8,9,13];
										myDriverCarType.combobox('clear');
										for(var i=0;i<temp.length;i++){
											myDriverCarType.combobox('select',temp[i]);
										}
									}else if(comboValue==2){
										var temp=[2,4,5,6,7,8,9,13];
										myDriverCarType.combobox('clear');
										for(var i=0;i<temp.length;i++){
											myDriverCarType.combobox('select',temp[i]);
										}
									}else if(comboValue==3){
										var temp=[3,6,7,8,9];
										myDriverCarType.combobox('clear');
										for(var i=0;i<temp.length;i++){
											myDriverCarType.combobox('select',temp[i]);
										}
									}else if(comboValue==4){
										var temp=[4,6,7,8,9];
										myDriverCarType.combobox('clear');
										for(var i=0;i<temp.length;i++){
											myDriverCarType.combobox('select',temp[i]);
										}
									}else if(comboValue==5){
										var temp=[5,6,7,8,9];
										myDriverCarType.combobox('clear');
										for(var i=0;i<temp.length;i++){
											myDriverCarType.combobox('select',temp[i]);
										}
									}else if(comboValue==6){
										var temp=[6,7,8,9];
										myDriverCarType.combobox('clear');
										for(var i=0;i<temp.length;i++){
											myDriverCarType.combobox('select',temp[i]);
										}
									}else if(comboValue==7){
										var temp=[7,8,9];
										myDriverCarType.combobox('clear');
										for(var i=0;i<temp.length;i++){
											myDriverCarType.combobox('select',temp[i]);
										}
									}else if(comboValue==8){
										var temp=[8,9];
										myDriverCarType.combobox('clear');
										for(var i=0;i<temp.length;i++){
											myDriverCarType.combobox('select',temp[i]);
										}
										
									}else if(comboValue==9){
										var temp=[9];
										myDriverCarType.combobox('clear');
										for(var i=0;i<temp.length;i++){
											myDriverCarType.combobox('select',temp[i]);
										}
										
									}else if(comboValue==10||comboValue==11||
											comboValue==12){
										var temp=[10,11,12];
										myDriverCarType.combobox('clear');
										for(var i=0;i<temp.length;i++){
											myDriverCarType.combobox('select',temp[i]);
										}
									}else{
										myDriverCarType.combobox('select',"");
									}
									
								}
							});
							//初始化校区
							//初始化用身份证做教练驾驶证ID
							var licenseId=f.find('input[name=driverLicenseId]');
							licenseId.attr('readonly',true);
							var iden=f.find('input[name=identity]');
							iden.change(function(){
								licenseId.val(iden.val());
							});
							
							var disableInput=f.find('input[class=demo_trainer_detail]');
							disableInput.attr('readonly',true);
							
						}//end onload
					});
		} else if (rows.length > 1) {
			parent.sy.messagerAlert('提示信息', '同一时间只能查看一条记录！', 'error');
		} else {
			parent.sy.messagerAlert('提示信息', '请选择要查看的记录！', 'error');
		}
	}

</script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<table id="demo_trainer_datagrid"></table>
	</div>
	<!-- 操作按钮事件 -->
	<div id="demo_trainer_menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="read();" data-options="iconCls:'icon-filter'">详细信息</div>
	</div>
</body>
</html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">
	var mydatagrid;
	var initPersonDepartment;
	var rowData=undefined;
	var personalTimingDatagrid=undefined;
	var personalDialog=undefined;
	var timingDate=undefined;
	var timimgType=undefined;
	var myData=undefined;
	
	var userSchoolArea=undefined;
	$(function() {
		$.ajax({
			url : '${pageContext.request.contextPath}/demo/studentAction!getSchoolArea.action',
			dataType : 'json',
			success : function(d) {
				userSchoolArea=d;
			}
		});
		mydatagrid = $('#demo_timing_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/demo/timingAction!personalDatagrid.action',/* 从后台读取所有user数据 */
			title : '学员学时信息管理列表!', /* 表头标题 */
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
			sortName : 'id', //列排序
			sortOrder : 'desc', //列排序
			checkOnSelect : false,
			selectOnCheck : true,
			striped : true,//表示条纹，隔行显示不现背景色
			nowrap:true,//单元显示
			//冻结列
			frozenColumns : [ [{
				title : '编号',
				field : 'studentId',
				width : 20,
				sortable : true,
				checkbox : true
			}, {
				title : '<font color="#0099FF">所属校区</font>',
				field : 'schoolArea',
				sortable : true,
				width : 150,
				align : 'left'
			},{
				title : '<font color="#0099FF">学员编号</font>',
				field : 'studentNo',
				width : 120,
				align : 'left',
				formatter:function(value,row,index){
					return formatStudentNo(value, row, index);
				}
			},{
				title : '<font color="#0099FF">姓名</font>',
				field : 'studentName',
				width : 80,
				align : 'left'
			},{
				title : '<font color="#0099FF">性别</font>',
				field : 'studentSex',
				width : 60,
				align : 'left'
			}, {
				title : '<font color="#0099FF">身份证号</font>',
				field : 'studentIdentityId',
				width : 150,
				align : 'left'
			}, 
			 ] ],
			
			 columns:[[
			    {
					title : '<font color="#0099FF">总学时</font>',
					field : 'totalTiming',
					sortable : true,
					width : 70, 
					align : 'center',
					
				},{
					title : '<font color="#0099FF">已用学时</font>',
					field : 'usingTiming',
					sortable : true,
					width : 70, 
					align : 'center',
					
				},{
					title : '<font color="#0099FF">报名含学时</font>',
					field : 'handselTiming',
					sortable : true,
					width : 90, 
					align : 'center',
					
				},{
					title : '<font color="#0099FF">购买学时</font>',
					field : 'buyTiming',
					sortable : true,
					width : 70, 
					align : 'center',
					
				},{
					title : '<font color="#0099FF">剩余学时</font>',
					field : 'restTiming',
					sortable : true,
					width : 70, 
					align : 'center',
					
				},{
					title : '<font color="#0099FF">教练名称</font>',
					field : 'trainerName',
					width : 150,
				},{
					title : '<font color="#0099FF">操作员</font>',
					field : 'operator',
					width : 80,
				},{
					title : '<font color="#0099FF">备注</font>',
					field : 'comment',
					width : 200,
				},{
					title : '<font color="#0099FF">教练标识</font>',
					field : 'trainerId',
					width : 150,
				} ,{
					title : '<font color="#0099FF">校区标识</font>',
					field : 'schoolArea',
					width : 150,
				},{
					title : '<font color="#0099FF">预约时时</font>',
					field : 'reservationTiming',
					width : 60,
				}     
			       ]],
			//菜单功能设置
			toolbar : [{
				text : '学员学时明细',
				iconCls : 'icon-edit',
				handler : function() {
					addPersonalTiming();
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
			}, '-',{
				text : '刷新',
				iconCls : 'icon-reload',
				handler : function() {
					mydatagrid.datagrid('load',{});
				}
			}, '-'],
			onLoadSuccess : onLoadSuccess,
			//右键菜单所触发的事件
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				//取消当前页被选中的所有行
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow', rowIndex);
				$('#demo_timing_menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			},
		 	onHeaderContextMenu: function(e, field){
	             e.preventDefault();
	             if (!cmenu){
	                 createColumnMenu();
	             }
	             cmenu.menu('show', {
	                 left:e.pageX,
	                 top:e.pageY
	             });
         	}
		});

	});
	
	var cmenu;
    function createColumnMenu(){
        cmenu = $('<div/>').appendTo('body');
        cmenu.menu({
            onClick: function(item){
                if (item.iconCls == 'icon-ok'){
                    mydatagrid.datagrid('hideColumn', item.name);
                    cmenu.menu('setIcon', {
                        target: item.target,
                        iconCls: 'icon-empty'
                    });
                } else {
                	mydatagrid.datagrid('showColumn', item.name);
                    cmenu.menu('setIcon', {
                        target: item.target,
                        iconCls: 'icon-ok'
                    });
                }
            }
        });
        var fields = mydatagrid.datagrid('getColumnFields');
        for(var i=0; i<fields.length; i++){
            var field = fields[i];
            var col = mydatagrid.datagrid('getColumnOption', field);
            cmenu.menu('appendItem', {
                text: col.title,
                name: field,
                iconCls: 'icon-ok'
            });
        }
    }
  //初始化校区
	function initSchoolArea(object){
		object.combotree({
			url:'${pageContext.request.contextPath}/demo/organizationAction!doNotNeedSession_tree.action',
			lines:true,
			required:true,
		});
		//非系统管理员，禁止查询其他校区
		if(userSchoolArea!==null){
			object.combotree('setValue',userSchoolArea);
			object.combotree('readonly',true);
		}else{
			object.combotree('readonly',false);
		}
		
	}
	function onLoadSuccess(data) {
		//修改序号bug
		$(this).datagrid("fixRownumber");
		$(this).datagrid('hideColumn','trainerId');
		/* $(this).datagrid('hideColumn','trainerName'); */
		//初始化查询相关属性选项值
		//datagrid没有数据时显示没有记录信息
		if(data.total==0){
			mydatagrid.datagrid('appendRow', {
				schoolArea: '<div style="text-align:center;color:red">没有学时登记记录信息！</div>' })
			.datagrid('mergeCells', { index: 0, field: 'numbering', colspan:14 });
		}
		
		
		
	}
	
	//添加学员个人学时对话框
	function addPersonalTiming(){
		var rows = mydatagrid.datagrid('getSelections');
		
		//编辑时只能单选一行进行编辑
		if (rows.length == 1) {
			personalDialog = parent.sy.dialog({
						title : '编辑学时登记信息',
						href : '${pageContext.request.contextPath}/demo/personalTimingAction!toMyDetailTimingPage.action',
						width : 960,
						height : 760,
						buttons : [
								{
									text : '关闭',
									iconCls : 'icon-cancel',
									handler : function() {
										personalDialog.dialog('close');
									}
								} ],
						//数据回显显示
						onLoad : function() {
							var f = personalDialog.find('form');
							f.form('load', {
								id:rows[0].id,
								studentId:rows[0].studentId,
								studentOrganization:rows[0].studentOrganization,
								studentName:rows[0].studentName,
								studentSex:rows[0].studentSex,
								studentCreateTime:rows[0].studentCreateTime,
								studentIdentityId:rows[0].studentIdentityId,
								studentAddress:rows[0].studentAddress,
								studentBirthdate:rows[0].studentBirthdate,
								studentPhone:rows[0].studentPhone,
								studentEmail:rows[0].studentEmail,
								studentTelephone:rows[0].studentTelephone,
								studentMailCode:rows[0].studentMailCode,
								studentContry:rows[0].studentContry,
								studentNativeNation:rows[0].studentNativeNation,
								studentResidenceId:rows[0].studentResidenceId,
								studentResidenceAddr:rows[0].studentResidenceAddr,
								studentImageId:rows[0].studentImageId,
								studentNowstate:rows[0].studentNowstate,
								studentClazz:rows[0].studentClazz,
								studentApplyType:rows[0].studentApplyType,
								studentDriverType:rows[0].studentDriverType,
								studentNo:rows[0].studentNo,
								
								trainerId:rows[0].trainerId,
								trainerName:rows[0].trainerName,
								
								numbering:rows[0].numbering,
								totalTiming:rows[0].totalTiming,
								buyTiming:rows[0].buyTiming,
								handselTiming:rows[0].handselTiming,
								usingTiming:rows[0].usingTiming,
								restTiming:rows[0].restTiming,
								reservationTiming:rows[0].reservationTiming,
								comment:rows[0].comment,
							});
							
							//将学员基本信息属性襟止修改
							var inputDisable=f.find('input[class=demo_timing_update]');
							inputDisable.attr('disabled',true);
					
							//初始化更新页面中的驾照类型
							var stuDriverType=f.find('input[name=studentDriverType]');
							stuDriverType.combobox({
								url : '${pageContext.request.contextPath}/demo/json/driverType_combobox.json',
								valueField : 'value',
								textField : 'text',
								method:'get',
							});
							//初始化更新页面中的审领取类型
							var stuAppyType=f.find('input[name=studentApplyType]');
							stuAppyType.combobox({
								url : '${pageContext.request.contextPath}/demo/json/applyType_combobox.json',
								valueField : 'value',
								textField : 'text',
								method:'get',
							});
							
							//初始化学员培训状态
							var stuState=f.find('input[name=studentNowstate]');
							stuState.combobox({
								url : '${pageContext.request.contextPath}/demo/json/studentstate_combobox.json',
								valueField : 'value',
								textField : 'text',
								method:'get',
							});
							
							//初始化所属校区参数
							var mySchoolArea=f.find('input[name=studentOrganization]');
							initSchoolArea(mySchoolArea);
							
							//加载学员个人学时datagrid
							addPersonalTimgDatagrid(rows[0].id,rows[0].studentId,rows[0].trainerId,rows[0].trainerName);
							
						}
					});
		} else if (rows.length > 1) {
			parent.sy.messagerAlert('提示信息', '同一时间只能对一个学员进行操作！', 'error');
		} else {
			parent.sy.messagerAlert('提示信息', '当前没有选中任何学员信息！', 'error');
		}
	};
	
	
	//加载学员个人学时明细对话框
	function addPersonalTimgDatagrid(timingId,studentId,trainerId,trainerName){
		//1.从datagrid中获取需要修改的信息
		var rows = mydatagrid.datagrid('getSelections');
		
		var datagridId=personalDialog.find('table[id=demo_personalTiming_datagrid]');
		personalTimingDatagrid=datagridId.datagrid({
			url:'${pageContext.request.contextPath}/demo/personalTimingAction!datagrid.action',
			queryParams:{studentId:studentId},
			title : '学员个人学时明细信息管理列表!', /* 表头标题 */
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
			sortName : 'timingId', //列排序
			sortOrder : 'desc', //列排序
			checkOnSelect : false,
			selectOnCheck : true,
			striped : true,//表示条纹，隔行显示不现背景色
			nowrap:true,//单元显示
			//冻结列
			frozenColumns : [ [{
				title : '编号',
				field : 'timingId',
				width : 20,
				sortable : true,
				checkbox : true
			},{
				title : '<font color="#0099FF">编号</font>',
				field : 'numbering',
				sortable : true,
				width : 120,
				align : 'left'
			}
			 ] ],
			
			 columns:[[
			    {
					title : '<font color="#0099FF">日期</font>',
					field : 'date',
					sortable : true,
					width : 130, 
					align : 'center',
					
				},{
					title : '<font color="#0099FF">时段</font>',
					field : 'timeItem',
					sortable : true,
					width : 90, 
					align : 'center',
					
				}, {
					title : '<font color="#0099FF">训练类型</font>',
					field : 'timingType',
					sortable : true,
					width : 100, 
					align : 'center',
					formatter:function(value,row,index){
						if(value==1){
							return '模拟训练';
						}else if(value==2){
							return '电车训练';
						}else if(value==3){
							return '实车训练';	
						}else{
							return '';
						}
					}
				},{
					title : '<font color="#0099FF">教学内容</font>',
					field : 'teachingContent',
					sortable : true,
					width : 150, 
					align : 'center',
				},{
					title : '<font color="#0099FF">课时</font>',
					field : 'useTiming',
					sortable : true,
					width : 70, 
					align : 'center',
					
				},{
					title : '<font color="#0099FF">教练</font>',
					field : 'trainerName',
					width : 90,
				}
			          
			       ]],
			//菜单功能设置
			toolbar : [{
				text : '取消选中',
				iconCls : 'icon-undo',
				handler : function() {
					//取消所有的选中行
					personalTimingDatagrid.datagrid('clearSelections');
					//取消当前页面的选中行
					personalTimingDatagrid.datagrid('unselectAll');
				}
			}, '-',{
				text : '刷新',
				iconCls : 'icon-reload',
				handler : function() {
					personalTimingDatagrid.datagrid('load',{studentId:studentId});
				}
			}, '-'],
			
			onLoadSuccess : onLoadSuccessPersonalTiming,
			//右键菜单所触发的事件
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				//取消当前页被选中的所有行
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow', rowIndex);
				$('#demo_timing_menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			},
		 	onHeaderContextMenu: function(e, field){
	             e.preventDefault();
	             if (!cmenu){
	                 createColumnMenu();
	             }
	             cmenu.menu('show', {
	                 left:e.pageX,
	                 top:e.pageY
	             });
         	}
		});
	}
	
	//添加学员个人学时页面加载成功后触发的事件
	function onLoadSuccessPersonalTiming(data) {
		
		//修改序号bug
		personalTimingDatagrid.datagrid("fixRownumber");
		//datagrid没有数据时显示没有记录信息
		if(data.total==0){
			personalTimingDatagrid.datagrid('appendRow', {
				numbering: '<div style="text-align:center;color:red">当前学员没有学时记录信息！</div>' })
			.datagrid('mergeCells', { index: 0, field: 'numbering', colspan:5 });
		}
		
		
	}
	
	

</script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<!-- datagrid数据在center中展现 -->
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<table id="demo_timing_datagrid"></table>
	</div>
	<!-- 操作按钮事件 -->
	<div id="demo_timing_menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="addPersonalTiming();" data-options="iconCls:'icon-add'">学员学时明细</div>
	</div>
</body>
</html>
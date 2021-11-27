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
	$(function() {
		mydatagrid = $('#demo_driveLicense_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/demo/driverLicenseAction!personalDatagrid.action',/* 从后台读取所有user数据 */
			title : '驾驶证信息管理列表!', /* 表头标题 */
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
				field : 'id',
				width : 20,
				sortable : true,
				checkbox : true
			}, {
				title : '<font color="#0099FF">所属校区</font>',
				field : 'schoolArea',
				width : 120,
				align : 'left'
			},{
				title : '<font color="#0099FF">姓名</font>',
				field : 'studentName',
				width : 100,
				align : 'left'
			},{
				title : '<font color="#0099FF">学员编号</font>',
				field : 'studentNo',
				width : 100,
				align : 'left'
				
			}  
			 ] ],
			
			 columns:[[{
					title : '<font color="#0099FF">驾驶证证号</font>',
					field : 'studentIdentityId',
					width : 150,
					align : 'left'
				},{
					title : '<font color="#0099FF">联系电话</font>',
					field : 'studentPhone',
					sortable : true,
					width : 150,
					align : 'left',
					formatter:function(value,row,index){
						return formatStudentNo(value, row, index);
					}
				},
			    {
					title : '<font color="#0099FF">交管发证日期</font>',
					field : 'grantDate',
					sortable : true,
					width : 100, 
					align : 'center',
					
				}, {
					title : '<font color="#0099FF">初领日期</font>',
					field : 'firstGetDate',
					sortable : true,
					width : 100, 
					align : 'center',
					
				}, {
					title : '<font color="#0099FF">准驾车型</font>',
					field : 'studentDriverType',
					sortable : true,
					width : 100, 
					align : 'center',
					formatter:function(value,row,index){
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
					
				}, {
					title : '<font color="#0099FF">有效期开始日期</font>',
					field : 'validBeginDate',
					sortable : true,
					width : 100, 
					align : 'center',
					
				}, {
					title : '<font color="#0099FF">有效年限</font>',
					field : 'validYear',
					sortable : true,
					width : 100, 
					align : 'center',
					
				}, {
					title : '<font color="#0099FF">领取状态</font>',
					field : 'grantState',
					sortable : true,
					width : 100,
					formatter:function(value,row,index){
						if(value==1){
							return '未领取';
						}else if(value==2){
							return '已领取';
						}else{
							return '';
						}
					}
				},{
					title : '<font color="#0099FF">领取日期</font>',
					field : 'recieveDate',
					sortable : true,
					width : 100 
				},{
						title : '<font color="#0099FF">备注</font>',
						field : 'comment',
						width : 200 
					}
			       ]],
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
			}, '-' ],
			onLoadSuccess : onLoadSuccess,
			//右键菜单所触发的事件
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				//取消当前页被选中的所有行
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow', rowIndex);
				$('#demo_driverLicense_menu').menu('show', {
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
	
	function onLoadSuccess(data) {
		//修改序号bug
		$(this).datagrid("fixRownumber");
		//初始化查询相关属性选项值
		//datagrid没有数据时显示没有记录信息
		if(data.total==0){
			mydatagrid.datagrid('appendRow', {
				studentOrganization: '<div style="text-align:center;color:red">没有驾驶证记录信息！</div>' })
			.datagrid('mergeCells', { index: 0, field: 'numbering', colspan:10 });
		}
	}
	
	//查看记录详细信息
	function read() {
		var rows = mydatagrid.datagrid('getSelections');
		//编辑时只能单选一行进行查看
		if (rows.length == 1) {
			var p = parent.sy.dialog({
						title : '----> 学员驾驶证详细信息 <----',
						href : '${pageContext.request.contextPath}/demo/driverLicenseAction!toMyDetailPage.action',
						width : 960,
						height : 520,
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
								id:rows[0].id,
								studentOrganization:rows[0].schoolArea,
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
								
								numbering:rows[0].numbering,
								firstGetDate:rows[0].firstGetDate,
								driverType:rows[0].studentDriverType,
								grantDate:rows[0].grantDate,
								recieveDate:rows[0].recieveDate,
								validBeginDate:rows[0].validBeginDate,
								validYear:rows[0].validYear,
								schoolArea:rows[0].schoolArea,
								grantState:rows[0].grantState,
								comment:rows[0].comment,
							});
							
							//将学员基本信息属性襟止修改
							var inputDisable=f.find('input[class=demo_driverLicense_update]');
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
							
							//学员状态初始化
							var stuStateInit=f.find('input[name=studentNowstate]');
							stuStateInit.combobox({
								url : '${pageContext.request.contextPath}/demo/json/studentstate_combobox.json',
								valueField : 'value',
								textField : 'text',
								required:true,
								width:155,
								method:'get',
							});
							
							//初始化准驾车型选项
							var mydriverType=f.find('input[name=driverType]');
							mydriverType.combobox({
								url : '${pageContext.request.contextPath}/demo/json/driverType_combobox.json',
								valueField : 'value',
								textField : 'text',
								required:true,
								disabled:true,
								method:'get',
							});
							
						}
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
	<!-- datagrid数据在center中展现 -->
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<table id="demo_driveLicense_datagrid"></table>
	</div>
	<!-- 操作按钮事件 -->
	<div id="demo_driverLicense_menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="read();" data-options="iconCls:'icon-filter'">详细信息</div>
	</div>
</body>
</html>
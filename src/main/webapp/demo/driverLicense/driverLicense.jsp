<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.tuocheng.util.base.Util"%>
<%
  String resourceSn = Util.objToString(request.getParameter("resourceSn"), "");
%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">
	var mydatagrid;
	var initPersonDepartment;
	var rowData=undefined;
	var userSchoolArea=undefined;
	var student=undefined;
	var userACLs=[];
	$(function() {
		userSchoolArea=getUserSchoolArea();
		getUserACL();
		
		mydatagrid = $('#demo_driveLicense_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/demo/driverLicenseAction!datagrid.action',/* 从后台读取所有user数据 */
			title : '驾驶证信息管理列表!', /* 表头标题 */
			iconCls : 'icon-save', /* 标题前边的图标 */
			pagination : true, /* 是否显示页码 */
			pagePosition : 'bottom', /* 页码显示的位置 */
			pageSize : 20, /* 每面显示的记录数 */
			pageList : [ 10, 20, 30, 40,50 ,100,200 ], /* 选择每面显示的记录数 */
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
				field : 'studentOrganization',
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
				align : 'left',
				formatter:function(value,row,index){
					return formatStudentNo(value, row, index);
				}
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
					align : 'left'
				},
			    {
					title : '<font color="#0099FF">交管发证日期</font>',
					field : 'grantDate',
					sortable : true,
					width : 100, 
					align : 'center',
					formatter:formatTimeYMD,
				}, {
					title : '<font color="#0099FF">初领日期</font>',
					field : 'firstGetDate',
					sortable : true,
					width : 100, 
					align : 'center',
					formatter:formatTimeYMD,
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
					formatter:formatTimeYMD,
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
				}, {
					title : '<font color="#0099FF">操作员</font>',
					field : 'operator',
					width : 80,
				},{
						title : '<font color="#0099FF">备注</font>',
						field : 'comment',
						width : 200 
				},{
						title : '<font color="#0099FF">studentId</font>',
						field : 'studentId',
						sortable : true,
						width : 100 ,
						hidden:true,
				},{
					title : '<font color="#0099FF">schoolArea</font>',
					field : 'schoolArea',
					sortable : true,
					width : 100 ,
					hidden:true,
			},
				]],
			//菜单功能设置
			toolbar : [ {
				id:'add-btn-toolbar',
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					append();
				}
			}, '-', { //'-':减号，将用“｜”隔开按钮
				id:'del-btn-toolbar',
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					remove();
				}
			}, '-', {
				id:'edit-btn-toolbar',
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					edit();
				}
			}, '-',{
				id:'detail-btn-toolbar',
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
			onBeforeLoad:function(param){
				if(!userACLs[0]){ //Create
                    $("#add-btn-toolbar").linkbutton('disable'); 
					$("#add-menu").hide();
                }
				if(!userACLs[2]){//Update
					$("#edit-btn-toolbar").linkbutton('disable');
					$("#import-btn-toolbar").linkbutton('disable'); 
					$("#export-btn-toolbar").linkbutton('disable');
					$("#edit-menu").hide();
				}
				if(!userACLs[3]){//delete
					$("#del-btn-toolbar").linkbutton('disable');
					$("#del-menu").hide();
				}
			},
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
	
	function getUserACL(){
		//初始化主菜单
		$.ajax({
			url : '${pageContext.request.contextPath}/demo/aclAction!getUserPermissions.action',
			data:{resourceSn:'<%=resourceSn%>'},
			type : 'POST', //提交方式 可以选择post/get 推荐post   
			async : false, //同步异步
			dataType : 'json',
			success : function(data) {
				for (var i = 0; i < data.length; i++) {
					userACLs.push(data[i]);
				}
			}
		});
	}
	
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
		//初始化校区选项
		initSchoolArea($("#demo_driverLicense_organization"),userSchoolArea);
		//初始化驾照类型选项
		$("#demo_driverLicense_driverType").combobox({
			url : '${pageContext.request.contextPath}/demo/json/driverType_combobox.json',
			valueField : 'value',
			textField : 'text',
			required:true,
			method:'get',
			onChange:function(){
				//根据combobox选择名称检索驾驶证学员信息
				mydatagrid.datagrid('load', sy.serializeObject($("#demo_driverLicense_searchForm")));
			}
		});
		
		$("#demo_grantState_combobox").combobox({
			onChange:function(){
				//根据combobox选择名称检索驾驶证学员信息
				mydatagrid.datagrid('load', sy.serializeObject($("#demo_driverLicense_searchForm")));
			}
		});
		
		//datagrid没有数据时显示没有记录信息
		if(data.total==0){
			mydatagrid.datagrid('appendRow', {
				studentOrganization: '<div style="text-align:center;color:red">没有驾驶证记录信息！</div>' })
			.datagrid('mergeCells', { index: 0, field: 'numbering', colspan:10 });
		}
	}
	
	//单击编辑按钮，实现对驾驶证的编辑功能
	function edit() {
		var rows = mydatagrid.datagrid('getSelections');
		//编辑时只能单选一行进行编辑
		if (rows.length == 1) {
			var p = parent.sy.dialog({
						title : '编辑驾驶证信息',
						href : '${pageContext.request.contextPath}/demo/driverLicenseAction!toDriverLicenseEditPage.action',
						width : 1024,
						height : 520,
						buttons : [
								{
									text : '更新',
									iconCls : 'icon-tip',
									handler : function() {
										var f = p.find('form');
										//提交表单
										f.form('submit',{
											url : '${pageContext.request.contextPath}/demo/driverLicenseAction!edit.action',
											success : function(d) {
													var json = $.parseJSON(d);
													if (json.success) {
															mydatagrid.datagrid('updateRow',{
																	//获得行的索引
																	index : mydatagrid.datagrid('getRowIndex',rows[0]),
																	row : json.obj
															}); 
														p.dialog('close');
													}
													parent.sy.messagerShow({
														msg : json.msg,
														title : '提示信息'
													});
											}
										});
									}
								}, {
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
								studentId:rows[0].studentId,
								
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
								disabled:true,
							});
							//初始化更新页面中的审领取类型
							var stuAppyType=f.find('input[name=studentApplyType]');
							stuAppyType.combobox({
								url : '${pageContext.request.contextPath}/demo/json/applyType_combobox.json',
								valueField : 'value',
								textField : 'text',
								method:'get',
								disabled:true,
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
								disabled:true,
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
								disabled:true,
							});
							
						}
					});
		} else if (rows.length > 1) {
			parent.sy.messagerAlert('提示信息', '同一时间只能更新一条记录！', 'error');
		} else {
			parent.sy.messagerAlert('提示信息', '请选择要更新的记录！', 'error');
		}
	}
	
	//查看记录详细信息
	function read() {
		var rows = mydatagrid.datagrid('getSelections');
		//编辑时只能单选一行进行查看
		if (rows.length == 1) {
			var p = parent.sy.dialog({
						title : '----> 学员驾驶证详细信息 <----',
						href : '${pageContext.request.contextPath}/demo/driverLicenseAction!toDriverLicenseDetailPage.action',
						width : 1024,
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

	//添加驾驶证
	function append() {
		var addDialog = parent.sy.dialog({
					title : '驾驶证信息录入',
					href : '${pageContext.request.contextPath}/demo/driverLicenseAction!toDriverLicenseAddPage.action',
					width : 1024,
					height : 585,
					buttons : [
							 {
								text : '取消',
								iconCls : 'icon-cancel',
								handler : function() {
									addDialog.dialog('close');
								}
							} ],
							//通过onLoad函数实现数据回显
							onLoad : function() {
								var myForm=addDialog.find('form');
								var myDepartment=myForm.find('input[name=schoolArea]');
								var myDriverType=myForm.find('input[name=driverType]');
								var myLinkButton_search=myForm.find('a[id=driverLicense_a_searche]');
								var myLinkButton_cancel=myForm.find('a[id=driverLicense_a_cancel]');
								var myDatagird=myForm.find('table[id=demo_driverLicense_selectStudent]');
								var myExamDate=myForm.find('input[name=examDate]');
								var disableItem=myForm.find('input[class=demo_add_disable]');
								var cleanObject=myForm.find('input[class=demo_add_cancle]');
								myExamDate.datetimebox({
								    showSeconds: false
								});
								//襟用相应的input选项
								disableItem.attr('disabled',false);//TODO bug
								
								//初始化校区选项
								initSchoolArea(myDepartment,userSchoolArea);
								//初始化添加页面数据
								if(userSchoolArea!==''){
									addInputDatagrid(addDialog,myDatagird,userSchoolArea);
								}else{
									addInputDatagrid(addDialog,myDatagird,null);
								}
								//初始化考试类型选项
								var driverTypeCombobox=myDriverType.combobox({
									url : '${pageContext.request.contextPath}/demo/json/driverType_combobox.json',
									valueField : 'value',
									textField : 'text',
									required:true,
									method:'get',
								});
								//查询按钮事件
								var linkButton=myLinkButton_search.linkbutton({
								    iconCls: 'icon-search',
								    onClick:function(){
								    	myDatagird.datagrid('load', sy.serializeObject(myForm)); 	
								    	
								    }
								});
								//取消按钮事件
								var cancelButton=myLinkButton_cancel.linkbutton({
								    iconCls: 'icon-cancel',
								    onClick:function(){
								    	//将数据还原显示到点击查询按钮前的数据
								    	myDatagird.datagrid('load', sy.serializeObject(myForm));
								    	cleanObject.val('');
								    }
								});
							},
				});
		
	}
	//添加输入页面列表
	function addInputDatagrid(addDialog,myDatagird,schoolArea){
		// 展现符合驾驶证的学员信息
		myDatagird.datagrid({
			url : '${pageContext.request.contextPath}/demo/studentExamAction!datagrid.action',
			queryParams:{driverLicenseFlag:1,driverLicenseFinishFlag:0,schoolArea:schoolArea},
			iconCls : 'icon-save',
			pagination : true, 
			pagePosition : 'bottom', 
			pageSize : 20, 
			pageList : [ 10, 20, 30, 40 ,50,100,200], 
			fit : true,
			fitColumns : false, 
			nowrap : false, 
			border : false,
			singleSelect:true,
			rownumbers: true,
			idField : 'id', 
			sortName : 'importOrder', 
			sortOrder : 'asc', 
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
			},{
				title : '<font color="#0099FF">身份证号</font>',
				field : 'studentIdentityId',
				width : 155,
				align : 'center',
				formatter:showAllMsg,
			}, {
				title : '<font color="#0099FF">报考车型</font>',
				field : 'studentDriverType',
				width : 60,
				align : 'center',
				formatter:formatterDriverType,
			},{
				title : '<font color="#0099FF">原有车型</font>',
				field : 'primaryDriver',
				width : 60,
				align : 'center',
				formatter:showAllMsg,
			},{
				title : '<font color="#0099FF">地址</font>',
				field : 'studentAddress',
				width : 260,
				formatter:showAllMsg,
			},{
				title : '<font color="#0099FF">手机</font>',
				field : 'examPhone',
				width : 100 ,
				align:'center',
			},{
				title : '<font color="#0099FF">报名日期</font>',
				field : 'studentCreateTime',
				width : 100 ,
				align:'center',
				formatter:formatTimeYMD,
			},{
				title : '<font color="#0099FF">schoolArea</font>',
				field : 'schoolArea',
				width : 60 ,
				align:'center',
				hidden:true,
			},{
				title : '<font color="#0099FF">studentId</font>',
				field : 'studentId',
				width : 60 ,
				align:'center',
				hidden:true,
			},{
				title : '<font color="#0099FF">校区名称</font>',
				field : 'schoolAreaName',
				width : 60 ,
				align:'center',
				hidden:true,
			}]],
    		toolbar : [ 
    		    {
    				text : '下一步',
					iconCls : 'icon-add',
					handler : function() {
						var rows=myDatagird.datagrid('getChecked');
						 if(rows.length>0){
							 addDriverLicenseRecord(rows,rows[0].studentDriverType);
							 addDialog.dialog('close');
						}else{
							parent.sy.messagerAlert('提示信息', '您当前没有选择学员信息！', 'error');
						}
					}
				}, {
    				text : '取消选中',
					iconCls : 'icon-undo',
					handler : function() {
						var rows=myDatagird.datagrid('getChecked');
						if(rows.length<=0){
							parent.sy.messagerAlert('提示信息', '您当前没有选中行信息！', 'error');
						}
						//取消所有的选中行
						myDatagird.datagrid('clearSelections');
						//取消当前页面的选中行
						myDatagird.datagrid('unselectAll');
					}
				},
				
				'-'],
    		onLoadSuccess : function (data) {
				//修改序号bug
				myDatagird.datagrid("fixRownumber");
				//datagrid没有数据时显示没有记录信息
				if(data.total==0){
					myDatagird.datagrid('appendRow', {
						studentOrganization: '<div style="text-align:center;color:red">没有记录信息！</div>' })
					.datagrid('mergeCells', { index: 0, field: 'studentOrganization', colspan:5 });
				}
			},
			
    	});
	}
	
	//到达添加退学记录dialog
	function addDriverLicenseRecord(rows,driverType){
		var student=undefined;
		var addInputDialog=parent.sy.dialog({
			
			title : '驾驶证信息录入',
			href : '${pageContext.request.contextPath}/demo/driverLicenseAction!toDriverLicenseAddInputPage.action',
			width : 1024,
			height : 600,
			buttons : [
				{
					text : '保存',
					iconCls : 'icon-edit',
					handler : function() {
						f=addInputDialog.find('form');
						f.form('submit',{
							url : '${pageContext.request.contextPath}/demo/driverLicenseAction!add.action',
							success : function(d) {
								var json = $.parseJSON(d);
								if (json.success) {
									mydatagrid.datagrid('insertRow',
													{
														index : 0, // index start with 0
														row : json.obj
													});
									addInputDialog.dialog('close');
								}
								parent.sy.messagerShow({
											msg : json.msg,
											title : '提示信息'
								});
							}
						});
					}
				}, 
				{
					text : '上一步',
					iconCls : 'icon-redo',
					handler : function() {
						addInputDialog.dialog('close');
						append();
					}
				},
			  {
				text : '关闭',
				iconCls : 'icon-cancel',
				handler : function() {
					addInputDialog.dialog('close');
				}
			}
			  ],
			//通过onLoad函数实现数据回显
			onLoad : function() {
				//初始化添加页面中的选择参数信息（申请类型，驾照类型）
				var f = addInputDialog.find('form');
				var disableItems=f.find('input[class=demo_driverLicense_update]');
				disableItems.attr('disabled',true);
				//初始化申请类型选项
				var applyType=f.find('input[name=studentApplyType]');
				applyType.combobox({
					url : '${pageContext.request.contextPath}/demo/json/applyType_combobox.json',
					valueField : 'value',
					textField : 'text',
					required:true,
					method:'get',
				});
				//初始化驾照类型选项
				var driverType=f.find('input[name=studentDriverType]');
				driverType.combobox({
					url : '${pageContext.request.contextPath}/demo/json/driverType_combobox.json',
					valueField : 'value',
					textField : 'text',
					required:true,
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
					method:'get',
				});
				
				//初始化领取状态
				var grantStateInit=f.find('input[name=grantState]');
					grantStateInit.combobox({
					url : '${pageContext.request.contextPath}/demo/json/driverType_combobox.json',
					valueField : 'value',
					textField : 'text',
					required:true,
					method:'get',
				});
				
					
				//从后台异步，根据学员标识获取学员信息
				$.ajax({
					url : '${pageContext.request.contextPath}/demo/driverLicenseAction!getSingStudentInformation.action',
					data : {studentId : rows[0].studentId},
					dataType : 'json',
					type:'POST',
					async:false,
					success : function(json) {
						student=json;
						f.form('load', {
							studentId:student.id,
							studentName:student.name,
							studentOrganization :rows[0].studentOrganization,//TODO
							schoolAreaName:rows[0].schoolAreaName,
							studentSex : student.sex,
							studentCreateTime : student.createTime,
							studentIdentityId : student.identityId,
							studentPhone : student.phone,
							studentClazz : rows[0].studentClazz,//TODO
							studentDriverType : student.driverType,
							studentBirthdate: student.birthdate,
							studentContry:student.contry,
							studentAddress: student.address,
							studentNativeNation:student.nativeNation,
							studentTelephone: student.telephone,
							studentEmail: student.email,
							studentMailCode: student.mailCode,
							studentResidenceId:student.residenceId,
							studentResidenceAddr: student.residenceAddr,
							studentImageId: student.imageId,
							studentNowstate: student.nowState,
							studentApplyType: student.applyType,
							driverType:student.driverType,
						});
					}
				});
				//初始化学员信息
			
				
			},
		});
	}

	//删除驾驶证
	function remove() {
		var rows = mydatagrid.datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			parent.sy.messagerConfirm(
							'请确认',
							'您要删除当前所选择项信息？',
							function(r) {
								if (r) {
									for (var i = 0; i < rows.length; i++) {
										ids.push(rows[i].id);
									}
									$.ajax({
										url : '${pageContext.request.contextPath}/demo/driverLicenseAction!delete.action',
										data : {ids : ids.join(',')},
										dataType : 'json',
										success : function(d) {
											mydatagrid.datagrid('load');
											mydatagrid.datagrid('unselectAll');
											mydatagrid.datagrid('uncheckAll');
											parent.sy.messagerShow({
												title : '提示信息',
												msg : d.msg
											});
										}
									});
								}
							});
		} else {
			parent.sy.messagerAlert('提示信息', '请勾选要删除的记录！', 'error');
		}
	}

	//查询按钮功能函数
	function mySearch() {
		searchStudentNo($("#search_studentNo"), userSchoolArea);
		mydatagrid.datagrid('load', sy.serializeObject($('#demo_driverLicense_searchForm')));
		$("#search_studentNo").val('');
	}

	//取消查询功能按钮
	function cleanSearch() {
		mydatagrid.datagrid('load', {});
		//清空与查询有关的输入框
		$('#demo_driverLicense_searchForm input').val('');
	}

</script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询功能'" style="height: 125px;overflow: hidden;" align="left">
		<!-- 人员信息查询表单 -->
		<form id="demo_driverLicense_searchForm">
			<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
				<tr>
				<th style="width: 65px;">所属校区:</th>
				<td><input id="demo_driverLicense_organization" name="schoolArea" style="width: 155px;"/></td>
				<th style="width: 65px;">编号:</th>
				<td><input id="search_studentNo" name="numbering" style="width: 155px;"/></td>
				<th>驾驶证发放日期</th>
				<td>
					<input name="grantDateStart" class="easyui-datetimebox" data-options="showSeconds:false,editable:false"
					 style="width: 155px;" />
				</td>
				<th>至</th>
				<td>
					<input name="grantDateEnd" class="easyui-datetimebox" data-options="showSeconds:false,editable:false"
					 style="width: 155px;" />
				</td>
			</tr>
			<tr>
				<th style="width: 65px;">姓名:</th>
				<td><input id="demo_theory_name" name="studentName" style="width: 155px;" /></td>
				<th style="width: 65px;">驾驶证号:</th>
				<td><input name="studentIdentityId"  style="width: 155px;" /></td>
				<th style="width: 65px;">驾照类型:</th>
				<td><input id="demo_driverLicense_driverType" name="driverType" style="width: 155px;" /></td>
				<th style="width: 65px;">领取状态:</th>
				<td>
					<select id="demo_grantState_combobox" name="grantState" style="width:155px;">
							<option value="">请选择</option>
							<option value=1>未领取</option>
							<option value=2>已领取</option>
					</select>
				</td>
			</tr>
				<tr>
					<th></th>
					<td></td>
					<td colspan="2">
						<a href="javascript:void(0);" id="driverLicense_a_searche" class="easyui-linkbutton" iconCls="icon-search" onclick="mySearch();">查询</a>
						<a href="javascript:void(0);" id="driverLicense_a_canel" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cleanSearch();">取消</a>
					</td>
					<th></th>
					<td></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- datagrid数据在center中展现 -->
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<table id="demo_driveLicense_datagrid"></table>
	</div>
	<!-- 操作按钮事件 -->
	<div id="demo_driverLicense_menu" class="easyui-menu" style="width:120px;display: none;">
		<div id="add-menu" onclick="append();" data-options="iconCls:'icon-add'">增加</div>
		<div id="del-menu" onclick="remove();" data-options="iconCls:'icon-no'">删除</div>
		<div id="edit-menu" onclick="edit();" data-options="iconCls:'icon-edit'">更新</div>
		<div id="detail-menu" onclick="read();" data-options="iconCls:'icon-filter'">详细信息</div>
	</div>
</body>
</html>
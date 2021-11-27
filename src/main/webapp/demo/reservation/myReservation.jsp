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
	var studentNameTemp=undefined;
	var studentIdentityIdTemp=undefined;
	var studentOrganizationTemp=undefined;
	var detailDialog=undefined;
	var studentNoEditer=undefined;
	var reservationDetailDatagrid=undefined;
	var trainingItemJson=[{"value":1,"text":"五项"},{"value":2,"text":"路训"},{"value":null,"text":"取消"}];
	var fieldOptionFlagTemp=undefined;
	
	var timeItemClikcFlag=undefined;//记录当前用户操作的是哪个时间列
	var defaultSelectRow=undefined;
	var nextStepFlag=undefined;
	var student=undefined;
	//训练内容编辑器初始化
	var myEditor={
			type:'combobox',
			options:{
				data:trainingItemJson,
				valueField:'value',
				textField:'text'
			}
		};
	
	var userSchoolArea=undefined;
	$(function() {
		$.ajax({
			url : '${pageContext.request.contextPath}/demo/studentAction!getSchoolArea.action',
			dataType : 'json',
			success : function(d) {
				userSchoolArea=d;
			}
		});
		
		mydatagrid = $('#demo_reservation_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/demo/reservationAction!personalDatagrid.action',/* 从后台读取所有user数据 */
			title : '预约信息管理列表!', /* 表头标题 */
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
			sortName : 'numbering', //列排序
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
				title : '<font color="#0099FF">学员编号</font>',
				field : 'studentNo',
				sortable : true,
				width : 120,
				align : 'left',
				formatter:function(value,row,index){
					return formatStudentNo(value, row, index);
				}
			},{
				title : '<font color="#0099FF">学员姓名</font>',
				field : 'studentName',
				width : 70,
				align : 'left'
			},{
				title : '<font color="#0099FF">教练姓名</font>',
				field : 'trainerName',
				width : 70,
				align : 'left'
			},{
				title : '<font color="#0099FF">预约类型</font>',
				field : 'reservationType',
				sortable : true,
				width : 80,
				align : 'left',
				formatter:function(value,row,index){
					if(value==1){
						return '五项';
					}else if(value==2){
						return '路训';
					}else{
						return '';
					}
				}
			},
			 ] ],
			
			 columns:[[{
					title : '<font color="#0099FF">预约日期</font>',
					field : 'date',
					sortable : true,
					width : 130,
					align : 'left'
				},{
					title : '<font color="#0099FF">开始时段</font>',
					field : 'timeStart',
					sortable : true,
					width : 130,
					align : 'left'
				}, {
					title : '<font color="#0099FF">结束时段</font>',
					field : 'timeEnd',
					sortable : true,
					width : 130,
					align : 'left'
				},{
					title : '<font color="#0099FF">预约状态</font>',
					field : 'reservationState',
					sortable : true,
					width : 80,
					align : 'left',
					formatter:function(value,row,index){
						if(value==1){
							return '未确认';
						}else if(value==2){
							return '正常';
						}else if(value==3){
							return '完成';
						}else if(value==4){
							return '取消';
						}else if(value==5){
							return '挂起';
						}else{
							return '';
						}
					}
				}, {
						title : '<font color="#0099FF">预约方式</font>',
						field : 'reservationWay',
						sortable : true,
						width : 80,
						align : 'left',
						formatter:function(value,row,index){
							if(value==1){
								return '电话预约';
							}else if(value==2){
								return '网络预约';
							}else if(value==3){
								return '微信预约';
							}else if(value==4){
								return '其他';
							}else{
								return '';
							}
						}
				},
			    {
					title : '<font color="#0099FF">学员电话</font>',
					field : 'studentPhone',
					width : 100, 
					align : 'center',
					
				}, {
					title : '<font color="#0099FF">教练电话</font>',
					field : 'trainerPhone',
					width : 100, 
					align : 'center',
					
				}, {
					title : '<font color="#0099FF">学员身份证号</font>',
					field : 'studentIdentityId',
					width : 150, 
					align : 'center',
					
				}, {
					title : '<font color="#0099FF">教练身份证号</font>',
					field : 'trainerIdentityId',
					width : 150, 
					align : 'center',
					
				},{
					title : '<font color="#0099FF">教练预约明细标识</font>',
					field : 'trainerReservationDetailId',
					width : 150, 
					align : 'center',
					
				}
			       ]],
			//菜单功能设置
			toolbar : [ {
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					append();
				}
			}, {
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
				$('#demo_reservation_menu').menu('show', {
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
		$(this).datagrid('hideColumn','trainerReservationDetailId');
		//初始化查询相关属性选项值
		//初始化校区选项
		initSchoolArea($("#demo_reservation_organization"));
		//初始化预约状态选项
		$("#demo_reservation_state").combobox({
			url : '${pageContext.request.contextPath}/demo/json/reservationstate_combobox.json',
			valueField : 'value',
			textField : 'text',
			required:true,
			method:'get',
			onChange:function(){
				//根据combobox选择名称检索预约学员信息
				mydatagrid.datagrid('load', sy.serializeObject($("#demo_reservation_searchForm")));
			}
		});
		
		//datagrid没有数据时显示没有记录信息
		if(data.total==0){
			mydatagrid.datagrid('appendRow', {
				schoolArea: '<div style="text-align:center;color:red">没有预约记录信息！</div>' })
			.datagrid('mergeCells', { index: 0, field: 'numbering', colspan:10 });
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
	
	//添加用户
	function append() {
		var studentId=undefined;
			
		
		var addDialog=undefined;
		 addDialog = parent.sy.dialog({
					title : '预约信息录入',
					href : '${pageContext.request.contextPath}/demo/reservationAction!toReservationAddPage.action',
					width : 960,
					height : 835,
					buttons : [
					 		{
								text : '保存',
								iconCls : 'icon-save',
								handler : function() {
									if(nextStepFlag){
										nextStepFlag=false;
										f=addDialog.find('form');
										var studentId=f.find('input[name=studentId]');
										if(studentId.val()!=""){
											f.form('submit',{
												url : '${pageContext.request.contextPath}/demo/reservationAction!add.action',
												success : function(d) {
													var json = $.parseJSON(d);
													if (json.success) {
														mydatagrid.datagrid('load',{});
														addDialog.dialog('close');
													}
													parent.sy.messagerShow({
																msg : json.msg,
																title : '提示信息'
													});
												}
											});
										}else{
											parent.sy.messagerAlert('提示信息', '选择学员信息中没有选择学员，请重新选择！', 'question');
										}
									}else{
										parent.sy.messagerAlert('提示信息', '请先点击一下步，再点保存！', 'question');
									}
									
								}
							},	
							{
								text : '关闭',
								iconCls : 'icon-cancel',
								handler : function() {
									addDialog.dialog('close');
								}
							}
							],
							//通过onLoad函数实现数据回显
					onLoad : function() {
						$.ajax({
							url : '${pageContext.request.contextPath}/demo/studentAction!getSingleStudent.action',
							dataType : 'json',
							success : function(d) {
								//初始化添加页面选项＝＝＝＝＝begin====
								var myForm=addDialog.find('form');
								//初始化校区选项
								var myDepartment=myForm.find('input[name=studentOrganization]');
								//初始化校区选项
								initSchoolArea(myDepartment);	
								//初始化学员，教练信息
								stuName=myForm.find('input[name=studentName]');
								stuId=myForm.find('input[name=studentId]');
								stuNo=myForm.find('input[name=studentNo]');
								stuIdentityId=myForm.find('input[name=studentIdentityId]');
								stuName.val(d.name);
								stuNo.val(d.studentNo);
								stuId.val(d.id);
								stuIdentityId.val(d.identityId);
								
								traId=myForm.find('input[name=trainerId]');
								traName=myForm.find('input[name=trainerName]');
								traIdentity=myForm.find('input[name=trainerIdentityId]');
								traPhone=myForm.find('input[name=trainerPhone]');
								traId.val(d.trainerId);
								traName.val(d.trainerName);
								traIdentity.val(d.trainerIdentity);
								traPhone.val(d.trainerPhone);
								
								//设置添加页面中部分选项的可读属性
								var readonlyItems=myForm.find('input[class=reservation_add_readonly]');
									readonlyItems.attr('readonly',true);
								//添加页面中选择学员按钮事件		
								var selectStu_btn=myForm.find('a[id=reservation_a_selectstudent]');
								selectStu_btn.linkbutton({
									iconCls: 'icon-search',
									onClick:function(){
										//1.单击选择学员按钮，查找出学员信息
										findStudentInfo(addDialog,myDepartment.combotree('getValue'));
									}
								});
								
								//3.展现教练预约明细信息
								 initReservationDetailDatagrid(addDialog,d.trainerId,studentNoEditer,d.studentNo,d.name);
							
							}
					});
						
					},
				});
		
	}
	//1.单击选择学员按钮，查找出学员信息
	function findStudentInfo(addDialog,myDepartment){
		//1.1 确证用户是否已经选择所属校区
    	if(myDepartment==""){
    		parent.sy.messagerAlert('提示信息', '当前没有选择所属校区，请选择！', 'question');
    	}else{
    		//==============select student diaolog====
	    	//选择学员对话框
	    	var selectDialogAdd=undefined;
	    		selectDialogAdd=parent.sy.dialog({
	    			
	    			title : '预约信息录入',
	    			href : '${pageContext.request.contextPath}/demo/reservationAction!toReservationSelectStudentInputPage.action',
	    			width : 960,
	    			height : 600,
	    			buttons : [
	    				{
	    					text : '关闭',
	    					iconCls : 'icon-edit',
	    					handler : function(){
	    						selectDialogAdd.dialog('close');
	    					}
	    				},
	    			  ],
	    			//通过onLoad函数实现数据回显
	    			onLoad : function() {
	    				studentSelectDialogOnload(addDialog,selectDialogAdd,myDepartment);
	    			}
	    		});
    	}
		
	}
	//选择学员对话框中onload事件
	function studentSelectDialogOnload(addDialog,selectDialogAdd,myDepartment){
		//符合预约学员信息列表   ===================begin===============
		var f=selectDialogAdd.find('form');
		var myForm=addDialog.find('form');
		var studentIdTemp=myForm.find('input[name=studentId]');
		 studentNameTemp=myForm.find('input[name=studentName]');
		 studentIdentityIdTemp=myForm.find('input[name=studentIdentityId]');
		 studentNoTemp=myForm.find('input[name=studentNo]');
		
		var trainerIdTemp=myForm.find('input[name=trainerId]');
		var trainerNameTemp=myForm.find('input[name=trainerName]');
		var trainerIdentityIdTemp=myForm.find('input[name=trainerIdentityId]');
		var trainerPhoneTemp=myForm.find('input[name=trainerPhone]');
		
		//选择学员页面datagrid初始化
		var studentSelectDatagrid=f.find('table[id=demo_reservation_selectStudent]');
		studentSelectDatagrid.datagrid({
    		url : '${pageContext.request.contextPath}/demo/reservationAction!getAllReservationStudents.action',
			queryParams:{schoolArea:myDepartment},
    		title : '符合预约条件学员信息', /* 表头标题 */
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
			idField : 'studentId', //此属性一定要配置
			sortName : 'name', //列排序 //TODO
			sortOrder : 'desc', //列排序
			checkOnSelect : true,
			selectOnCheck : true,
			striped : true,//表示条纹，隔行显示不现背景色
			nowrap:true,//单元显示
			singleSelect:true,
			//冻结列
			frozenColumns : [ [ {
					title : '编号',
					field : 'studentId',
					width : 20,
					sortable : true,
					checkbox : true
				}, {
					title : '<font color="#0099FF">所属校区</font>',
					field : 'studentOrganization',
					width : 150,
					align : 'center'
				}, {
					title : '<font color="#0099FF">业务类型</font>',
					field : 'studentDriverType',
					width : 75,
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
				},{
					title : '<font color="#0099FF">姓名</font>',
					field : 'studentName',
					width : 80,
					sortable : true,
					align : 'center'
				} ,{
					title : '<font color="#0099FF">学员编号</font>',
					field : 'studentNo',
					width : 120,
					sortable : true,
					align : 'center'
				} ] ],
				//普通列
				columns : [ [ {
					title : '<font color="#0099FF">性别</font>',
					field : 'studentSex',
					width : 60 //在datagrid中一定要添加列宽度，否则会加长datagrid渲染的时间，消耗系统资源
				},  {
					title : '<font color="#0099FF">身份证号</font>',
					field : 'studentIdentityId',
					sortable : true,
					width : 150
				},{
					title : '<font color="#0099FF">联系电话</font>',
					field : 'studentPhone',
					width : 150
				},{
					title : '<font color="#0099FF">报名日期</font>',
					field : 'studentCreateTime',
					sortable : true,
					width : 150
				},{
					title : '<font color="#0099FF">状态</font>',
					field : 'studentNowstate',
					sortable : true,
					width : 80,
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
				},{
					title : '<font color="#0099FF">教练</font>',
					field : 'trainerName',
					width : 100,
					align : 'center'
				},{
					title : '<font color="#0099FF">班级</font>',
					field : 'studentClazz',
					width : 150,
					align : 'center'
				},
    		]],
    		toolbar : [ 
    		    {
    				text : '确认',
					iconCls : 'icon-add',
					handler : function() {
						var rows=studentSelectDatagrid.datagrid('getChecked');
						//TODO 校验如果校区选择为空应该给出提示信息
						 if(rows.length>0){
							 //从表中获取学员的姓名和身份证号
							studentIdTemp.val(rows[0].studentId);
							studentNameTemp.val(rows[0].studentName);
							studentIdentityIdTemp.val(rows[0].studentIdentityId);
							studentNoTemp.val(rows[0].studentNo);
							
							//异步从后台获取该学员的教练信息
							$.ajax({
								url : '${pageContext.request.contextPath}/demo/reservationAction!getTrainerByStudentId.action',
								data : {studentId : rows[0].studentId},
								dataType : 'json',
								success : function(trainer) {
									trainerIdTemp.val(trainer.id);
									trainerNameTemp.val(trainer.name);
									trainerIdentityIdTemp.val(trainer.identity);
									trainerPhoneTemp.val(trainer.phone);
									
									//3.加载教练预约明细信息
									//基本参数
									 var myText=rows[0].studentName+"-"+rows[0].studentNo;
									 var studentDatas=[{"value":rows[0].studentNo,"text":myText},{"value":"","text":"取消"}];
									 studentNoEditer={
												type:'combobox',
												options:{
													data:studentDatas,
													valueField:'value',
													textField:'text'
												}
									 };
									//3.展现教练预约明细信息
									 initReservationDetailDatagrid(addDialog,trainer.id,studentNoEditer,rows[0].studentNo,rows[0].studentName,defaultSelectRow);
								}
							});
							
							selectDialogAdd.dialog('close');					
						 }else{
							parent.sy.messagerAlert('提示信息', '您当前没有选择学员信息！', 'error');
						}
					}
				}, {
    				text : '取消选中',
					iconCls : 'icon-undo',
					handler : function() {
						var rows=studentSelectDatagrid.datagrid('getChecked');
						if(rows.length<=0){
							parent.sy.messagerAlert('提示信息', '您当前没有选中行信息！', 'error');
						}
						//取消所有的选中行
						studentSelectDatagrid.datagrid('clearSelections');
						//取消当前页面的选中行
						studentSelectDatagrid.datagrid('unselectAll');
					}
				},
				
				'-'],
    		onLoadSuccess : function () {
				//修改序号bug
				studentSelectDatagrid.datagrid("fixRownumber");
			},
			
    	});/////==============end datagrid=============
		    				
		//初始化所属校区选项
		var organization=f.find('input[name=studentOrganization]');
		organization.combotree({
			url:'${pageContext.request.contextPath}/demo/organizationAction!doNotNeedSession_tree.action',
			valueField : 'value',
			textField : 'text',
			lines:true,
			width:155,
			readonly:true,
			onChange:function(){
				//根据combobox选择名称检索预约学员信息
				studentSelectDatagrid.datagrid('load', sy.serializeObject(f));
			}
		});
		
		//初始化驾照类型
		var driverType=f.find('input[name=studentDriverType]');
		var driverTypeCombobox=driverType.combobox({
			url : '${pageContext.request.contextPath}/demo/json/driverType_combobox.json',
			valueField : 'value',
			textField : 'text',
			required:true,
			method:'get',
			onChange:function(){
				//根据combobox选择名称检索考试计划学员信息
				studentSelectDatagrid.datagrid('load', sy.serializeObject(f));
			}
		});
		
		//查询按钮，重置按钮功能
		var searchBtn=f.find('a[id=reservation_a_search]');
		var search=searchBtn.linkbutton({
		    iconCls: 'icon-search',
		    onClick:function(){
		    	studentSelectDatagrid.datagrid('load', sy.serializeObject(f));
		    }
		});
		//取消按钮事件
		var resetBtn=f.find('a[id=reservation_a_cancel]');
		var reset=resetBtn.linkbutton({
		    iconCls: 'icon-cancel',
		    onClick:function(){
		    	//将数据还原显示到点击查询按钮前的数据
		    	studentSelectDatagrid.datagrid('load', {schoolArea:myDepartment.combotree('getValue')});//TODO
		    	f.find('input[class=demo_reservation_clear]').val('');
		    	f.find('input[class=easyui-datetimebox demo_reservation_clear]').val('');
		    }
		});
		//初始化学员选择页面中的所属校区
		var schoolInit=f.find('input[name=studentOrganization]');
		schoolInit.val(myDepartment);
	}
	
	//3.展现教练预约明细信息
	function initReservationDetailDatagrid(addDialog,trainerId,studentNoEditer,studentNo,studentName,defaultSelectRow){
		var datagridId=addDialog.find('table[id=demo_detailReservation_datagrid]');
		
		reservationDetailDatagrid=datagridId.datagrid({
			url:'${pageContext.request.contextPath}/demo/reservationAction!getDetailReservationByTrainerId.action',
			queryParams:{detailTrainerId:trainerId},
			title : '当前教练预约明细信息管理列表!', /* 表头标题 */
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
			idField : 'reservationDetailId', //此属性一定要配置
			sortName : 'reservationDate', //列排序
			sortOrder : 'asc', //列排序
			/* checkOnSelect : false,
			selectOnCheck : true, */
			striped : true,//表示条纹，隔行显示不现背景色
			singleSelect:true,
			onDblClickCell: function(index, field,value){
				onClickCell(index, field,value,addDialog);
			},
			
			frozenColumns : [ [{
				title : '编号',
				field : 'reservationDetailId',
				width : 260,
				sortable : true,
				checkbox : true,
			}, {
				title:'预约日期',
				field:'reservationDate',
				sortable : true,
				width:130,
				formatter:function(value,row,index){
					return formatTimeYMDW(value,row,index);
				},
			},
			 ] ],
			columns:[[{
						title:'7:00-8:00',
						width:140,
						align:'center',
						colspan:2
					},{
						title:'8:00-9:00',
						width:140,
						align:'center',
						colspan:2
					},{
						title:'9:00-10:00',
						align:'center',
						colspan:2
					},{
						title:'10:00-11:00',
						align:'center',
						colspan:2
					},{
						title:'11:00-12:00',
						align:'center',
						colspan:2
					},{
						title:'12:00-13:00',
						align:'center',
						colspan:2
					},{
						title:'13:00-14:00',
						align:'center',
						colspan:2
					},{
						title:'14:00-15:00',
						align:'center',
						colspan:2
					},{
						title:'15:00-16:00',
						align:'center',
						colspan:2
					},{
						title:'16:00-17:00',
						align:'center',
						colspan:2
					},{
						title:'17:00-18:00',
						align:'center',
						colspan:2
					},{
						title:'18:00-19:00',
						align:'center',
						colspan:2
					},{
						title:'五项总数',
						field:'fiveItemTotal',
						width:70,
						align:'center',
						rowspan:2
					},{
						title:'路考总数',
						field:'raodExamTotal',
						width:70,
						align:'center',
						rowspan:2
					},{
						title:'教练编号',
						field:'trainerId',
						width:260,
						rowspan:2
					},{
						title:'教练预约编号',
						field:'trainerReservationId',
						width:260,
						rowspan:2
					},{
						title:'学员预约标识',
						field:'reservationFieldId8',
						width:260,
						rowspan:2
					},{
						title:'学员预约标识',
						field:'reservationFieldId9',
						width:260,
						rowspan:2
					},{
						title:'学员预约标识',
						field:'reservationFieldId10',
						width:260,
						rowspan:2
					},{
						title:'学员预约标识',
						field:'reservationFieldId11',
						width:260,
						rowspan:2
					},{
						title:'学员预约标识',
						field:'reservationFieldId12',
						width:260,
						rowspan:2
					},{
						title:'学员预约标识',
						field:'reservationFieldId13',
						width:260,
						rowspan:2
					},{
						title:'学员预约标识',
						field:'reservationFieldId14',
						width:260,
						rowspan:2
					},{
						title:'学员预约标识',
						field:'reservationFieldId15',
						width:260,
						rowspan:2
					},{
						title:'学员预约标识',
						field:'reservationFieldId16',
						width:260,
						rowspan:2
					},{
						title:'学员预约标识',
						field:'reservationFieldId17',
						width:260,
						rowspan:2
					},{
						title:'学员预约标识',
						field:'reservationFieldId18',
						width:260,
						rowspan:2
					}
					],[{
						title:'学员编号',
						field:'item8Studentid',
						width:100,
						align:'center',
						editor:studentNoEditer,
						formatter:function(value,row,index){
							return formatNoArrange(value,row,index);
						}
						
					},{
						title:'预约类型',
						field:'item8TrainContent',
						width:60,
						align:'center',
						editor:myEditor,
						formatter:function(value,row,index){
							return formatTrainingItem(value,row,index);
						}
						
					},{
						title:'学员编号',
						field:'item9Studentid',
						width:100,
						align:'center',
						editor:studentNoEditer,
						formatter:function(value,row,index){
							return formatNoArrange(value,row,index);
						}
					},{
						title:'预约类型',
						field:'item9TrainContent',
						width:60,
						align:'center',
						editor:myEditor,
						formatter:function(value,row,index){
							return formatTrainingItem(value,row,index);
						}
					},{
						title:'学员编号',
						field:'item10Studentid',
						width:100,
						align:'center',
						editor:studentNoEditer,
						formatter:function(value,row,index){
							return formatNoArrange(value,row,index);
						}
					},{
						title:'预约类型',
						field:'item10TrainContent',
						width:60,
						align:'center',
						editor:myEditor,
						formatter:function(value,row,index){
							return formatTrainingItem(value,row,index);
						}
					},{
						title:'学员编号',
						field:'item11Studentid',
						width:100,
						align:'center',
						editor:studentNoEditer,
						formatter:function(value,row,index){
							return formatNoArrange(value,row,index);
						}
					},{
						title:'预约类型',
						field:'item11TrainContent',
						width:60,
						align:'center',
						editor:myEditor,
						formatter:function(value,row,index){
							return formatTrainingItem(value,row,index);
						}
					},{
						title:'学员编号',
						field:'item12Studentid',
						width:100,
						align:'center',
						editor:studentNoEditer,
						formatter:function(value,row,index){
							return formatNoArrange(value,row,index);
						}
					},{
						title:'预约类型',
						field:'item12TrainContent',
						width:60,
						align:'center',
						editor:myEditor,
						formatter:function(value,row,index){
							return formatTrainingItem(value,row,index);
						}
					},{
						title:'学员编号',
						field:'item13Studentid',
						width:100,
						align:'center',
						editor:studentNoEditer,
						formatter:function(value,row,index){
							return formatNoArrange(value,row,index);
						}
					},{
						title:'预约类型',
						field:'item13TrainContent',
						width:60,
						align:'center',
						editor:myEditor,
						formatter:function(value,row,index){
							return formatTrainingItem(value,row,index);
						}
					},{
						title:'学员编号',
						field:'item14Studentid',
						width:100,
						align:'center',
						editor:studentNoEditer,
						formatter:function(value,row,index){
							return formatNoArrange(value,row,index);
						}
					},{
						title:'预约类型',
						field:'item14TrainContent',
						width:60,
						align:'center',
						editor:myEditor,
						formatter:function(value,row,index){
							return formatTrainingItem(value,row,index);
						}
					},{
						title:'学员编号',
						field:'item15Studentid',
						width:100,
						align:'center',
						editor:studentNoEditer,
						formatter:function(value,row,index){
							return formatNoArrange(value,row,index);
						}
					},{
						title:'预约类型',
						field:'item15TrainContent',
						width:60,
						align:'center',
						editor:myEditor,
						formatter:function(value,row,index){
							return formatTrainingItem(value,row,index);
						}
					},{
						title:'学员编号',
						field:'item16Studentid',
						width:100,
						align:'center',
						editor:studentNoEditer,
						formatter:function(value,row,index){
							return formatNoArrange(value,row,index);
						}
					},{
						title:'预约类型',
						field:'item16TrainContent',
						width:60,
						align:'center',
						editor:myEditor,
						formatter:function(value,row,index){
							return formatTrainingItem(value,row,index);
						}
					},{
						title:'学员编号',
						field:'item17Studentid',
						width:100,
						align:'center',
						editor:studentNoEditer,
						formatter:function(value,row,index){
							return formatNoArrange(value,row,index);
						}
					},{
						title:'预约类型',
						field:'item17TrainContent',
						width:60,
						align:'center',
						editor:myEditor,
						formatter:function(value,row,index){
							return formatTrainingItem(value,row,index);
						}
					},{
						title:'学员编号',
						field:'item18Studentid',
						width:100,
						align:'center',
						editor:studentNoEditer,
						formatter:function(value,row,index){
							return formatNoArrange(value,row,index);
						}
					},{
						title:'预约类型',
						field:'item18TrainContent',
						width:60,
						align:'center',
						editor:myEditor,
						formatter:function(value,row,index){
							return formatTrainingItem(value,row,index);
						}
					},
					]],
			//菜单功能设置
			toolbar : [{
				text : '下一步',
				iconCls : 'icon-save',
				handler :function(){
					saveOrupdateReservationDetail(addDialog); 
				}
			}, '-', {
				id:'detail-edit-reservaton',
				text : '编辑',
				iconCls : 'icon-undo',
				handler : function() {
					
				alert("here"+tol);
				}
			}, '-',{
				text : '取消选中',
				iconCls : 'icon-undo',
				handler : function() {
					//取消所有的选中行
					reservationDetailDatagrid.datagrid('clearSelections');
					//取消当前页面的选中行
					reservationDetailDatagrid.datagrid('unselectAll');
				}
			}, '-',{
				text : '刷新',
				iconCls : 'icon-reload',
				handler : function() {
					reservationDetailDatagrid.datagrid('load');
				}
			}, '-'],
			onLoadSuccess : onLoadSuccessReservationDetial,
			//右键菜单所触发的事件
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				//取消当前页被选中的所有行
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow', rowIndex);
				$('#demo_reservation_menu').menu('show', {
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
	
	function saveOrupdateReservationDetail(addDialog){
		nextStepFlag=true;
		var f=addDialog.find('form');
		//预约时间
		var reserDateTemp=f.find('input[name=date]');
		//初始化预约类型选项
		var reserTypeTemp=f.find('input[name=reservationType]');
		var timeStartTemp=f.find('input[name=timeStart]');
		var timeEndTemp=f.find('input[name=timeEnd]');
		var reservationDetailIdTemp=f.find('input[name=trainerReservationDetailId]');
		fieldOptionFlagTemp=f.find('input[name=fieldOptionFlag]');	
		var trainerReservationIdTemp=f.find('input[name=trainerReservationId]');
		var reservationIdTemp=f.find('input[name=reservationId]');
		
		var item8StudentIdTemp=f.find('input[name=item8Studentid]');
		var item8TrainContentTemp=f.find('input[name=item8TrainContent]');
		var item9StudentIdTemp=f.find('input[name=item9Studentid]');
		var item9TrainContentTemp=f.find('input[name=item9TrainContent]');
		var item10StudentIdTemp=f.find('input[name=item10Studentid]');
		var item10TrainContentTemp=f.find('input[name=item10TrainContent]');
		var item11StudentIdTemp=f.find('input[name=item11Studentid]');
		var item11TrainContentTemp=f.find('input[name=item11TrainContent]');
		var item12StudentIdTemp=f.find('input[name=item12Studentid]');
		var item12TrainContentTemp=f.find('input[name=item12TrainContent]');
		var item13StudentIdTemp=f.find('input[name=item13Studentid]');
		var item13TrainContentTemp=f.find('input[name=item13TrainContent]');
		var item14StudentIdTemp=f.find('input[name=item14Studentid]');
		var item14TrainContentTemp=f.find('input[name=item14TrainContent]');
		var item15StudentIdTemp=f.find('input[name=item15Studentid]');
		var item15TrainContentTemp=f.find('input[name=item15TrainContent]');
		var item16StudentIdTemp=f.find('input[name=item16Studentid]');
		var item16TrainContentTemp=f.find('input[name=item16TrainContent]');
		var item17StudentIdTemp=f.find('input[name=item17Studentid]');
		var item17TrainContentTemp=f.find('input[name=item17TrainContent]');
		var item18StudentIdTemp=f.find('input[name=item18Studentid]');
		var item18TrainContentTemp=f.find('input[name=item18TrainContent]');
		
		var reservationFieldId8Temp=f.find('input[name=reservationFieldId8]');
		var reservationFieldId9Temp=f.find('input[name=reservationFieldId9]');
		var reservationFieldId10Temp=f.find('input[name=reservationFieldId10]');
		var reservationFieldId11Temp=f.find('input[name=reservationFieldId11]');
		var reservationFieldId12Temp=f.find('input[name=reservationFieldId12]');
		var reservationFieldId13Temp=f.find('input[name=reservationFieldId13]');
		var reservationFieldId14Temp=f.find('input[name=reservationFieldId14]');
		var reservationFieldId15Temp=f.find('input[name=reservationFieldId15]');
		var reservationFieldId16Temp=f.find('input[name=reservationFieldId16]');
		var reservationFieldId17Temp=f.find('input[name=reservationFieldId17]');
		var reservationFieldId18Temp=f.find('input[name=reservationFieldId18]');
		
		if (endEditing()) {
			var rows = reservationDetailDatagrid.datagrid('getSelections');
			//编辑时只能单选一行进行编辑
			if (rows.length == 1) {
				//1.初始化学员预约选项内容
				//1.1初始化预约时间
				reserDateTemp.val(rows[0].reservationDate);
				reservationDetailIdTemp.val(rows[0].reservationDetailId);
				trainerReservationIdTemp.val(rows[0].trainerReservationId);
				reservationIdTemp.val(rows[0].reservationId);
				
				reservationFieldId8Temp.val(rows[0].reservationFieldId8);
				reservationFieldId9Temp.val(rows[0].reservationFieldId9);
				reservationFieldId10Temp.val(rows[0].reservationFieldId10);
				reservationFieldId11Temp.val(rows[0].reservationFieldId11);
				reservationFieldId12Temp.val(rows[0].reservationFieldId12);
				reservationFieldId13Temp.val(rows[0].reservationFieldId13);
				reservationFieldId14Temp.val(rows[0].reservationFieldId14);
				reservationFieldId15Temp.val(rows[0].reservationFieldId15);
				reservationFieldId16Temp.val(rows[0].reservationFieldId16);
				reservationFieldId17Temp.val(rows[0].reservationFieldId17);
				reservationFieldId18Temp.val(rows[0].reservationFieldId18);
				
				item8StudentIdTemp.val(rows[0].item8Studentid);
				item8TrainContentTemp.val(rows[0].item8TrainContent);
				item9StudentIdTemp.val(rows[0].item9Studentid);
				item9TrainContentTemp.val(rows[0].item9TrainContent);
				item10StudentIdTemp.val(rows[0].item10Studentid);
				item10TrainContentTemp.val(rows[0].item10TrainContent);
				item11StudentIdTemp.val(rows[0].item11Studentid);
				item11TrainContentTemp.val(rows[0].item11TrainContent);
				item12StudentIdTemp.val(rows[0].item12Studentid);
				item12TrainContentTemp.val(rows[0].item12TrainContent);
				item13StudentIdTemp.val(rows[0].item13Studentid);
				item13TrainContentTemp.val(rows[0].item13TrainContent);
				item14StudentIdTemp.val(rows[0].item14Studentid);
				item14TrainContentTemp.val(rows[0].item14TrainContent);
				item15StudentIdTemp.val(rows[0].item15Studentid);
				item15TrainContentTemp.val(rows[0].item15TrainContent);
				item16StudentIdTemp.val(rows[0].item16Studentid);
				item16TrainContentTemp.val(rows[0].item16TrainContent);
				item17StudentIdTemp.val(rows[0].item17Studentid);
				item17TrainContentTemp.val(rows[0].item17TrainContent);
				item18StudentIdTemp.val(rows[0].item18Studentid);
				item18TrainContentTemp.val(rows[0].item18TrainContent);
				//2.2根据用户在教练预约明细中操作的记录进行相关项初始化
				var temp8=rows[0].item8Studentid;
				//教练明细表中可排班单元格操作值传递
				if(!(temp8=="NO")&&timeItemClikcFlag==8||!(temp8=="NO")&&timeItemClikcFlag==80){
					fieldOptionFlagTemp.val('8');
					reserTypeTemp.val(rows[0].item8TrainContent);
					timeStartTemp.val("8:00");
					timeEndTemp.val("9:00");
					
				}else{
					item8StudentIdTemp.val(rows[0].item8Studentid);
				}
				var temp9=rows[0].item9Studentid;
				if(!(temp9=="NO")&&timeItemClikcFlag==9||!(temp9=="NO")&&timeItemClikcFlag==90){
					fieldOptionFlagTemp.val('9');
					reserTypeTemp.val(rows[0].item9TrainContent);
					
					timeStartTemp.val("9:00");
					timeEndTemp.val("10:00");
				}else{
					item9StudentIdTemp.val(rows[0].item9Studentid);
				}
				var temp10=rows[0].item10Studentid;
				if(!(temp10=="NO")&&timeItemClikcFlag==10||!(temp10=="NO")&&timeItemClikcFlag==100){
					fieldOptionFlagTemp.val('10');
					reserTypeTemp.val(rows[0].item10TrainContent);
					timeStartTemp.val("10:00");
					timeEndTemp.val("11:00");
				}else{
					item10StudentIdTemp.val(rows[0].item10Studentid);
				}
				var temp11=rows[0].item11Studentid;
				if(!(temp11=="NO")&&timeItemClikcFlag==11||!(temp11=="NO")&&timeItemClikcFlag==110){
					fieldOptionFlagTemp.val('11');
					
					reserTypeTemp.val(rows[0].item11TrainContent);
					timeStartTemp.val("11:00");
					timeEndTemp.val("12:00");
				}else{
					item11StudentIdTemp.val(rows[0].item11Studentid);
				}
				var temp12=rows[0].item12Studentid;
				if(!(temp12=="NO")&&timeItemClikcFlag==12||!(temp12=="NO")&&timeItemClikcFlag==120){
					fieldOptionFlagTemp.val('12');
					
					reserTypeTemp.val(rows[0].item10TrainContent);
					timeStartTemp.val("12:00");
					timeEndTemp.val("13:00");
				}else{
					item12StudentIdTemp.val(rows[0].item12Studentid);
				}
				var temp13=rows[0].item13Studentid;
				if(!(temp13=="NO")&&timeItemClikcFlag==13||!(temp13=="NO")&&timeItemClikcFlag==130){
					fieldOptionFlagTemp.val('13');
					
					reserTypeTemp.val(rows[0].item13TrainContent);
					timeStartTemp.val("13:00");
					timeEndTemp.val("14:00");
				}else{
					item13StudentIdTemp.val(rows[0].item13Studentid);
				}
				var temp14=rows[0].item14Studentid;
				if(!(temp14=="NO")&&timeItemClikcFlag==14||!(temp14=="NO")&&timeItemClikcFlag==140){
					fieldOptionFlagTemp.val('14');
					
					reserTypeTemp.val(rows[0].item14TrainContent);
					timeStartTemp.val("14:00");
					timeEndTemp.val("15:00");
				}else{
					item14StudentIdTemp.val(rows[0].item14Studentid);
				}
				var temp15=rows[0].item15Studentid;
				if(!(temp15=="NO")&&timeItemClikcFlag==15||!(temp15=="NO")&&timeItemClikcFlag==150){
					fieldOptionFlagTemp.val('150');
				
					reserTypeTemp.val(rows[0].item15TrainContent);
					timeStartTemp.val("15:00");
					timeEndTemp.val("16:00");
				}else{
					item15StudentIdTemp.val(rows[0].item15Studentid);
				}
				var temp16=rows[0].item16Studentid;
				if(!(temp16=="NO")&&timeItemClikcFlag==16||!(temp16=="NO")&&timeItemClikcFlag==160){
					fieldOptionFlagTemp.val('160');
				
					reserTypeTemp.val(rows[0].item16TrainContent);
					timeStartTemp.val("16:00");
					timeEndTemp.val("17:00");
				}else{
					item16StudentIdTemp.val(rows[0].item16Studentid);
				}
				var temp17=rows[0].item17Studentid;
				if(!(temp17=="NO")&&timeItemClikcFlag==17||!(temp17=="NO")&&timeItemClikcFlag==170){
					fieldOptionFlagTemp.val('17');
					
					reserTypeTemp.val(rows[0].item17TrainContent);
					timeStartTemp.val("17:00");
					timeEndTemp.val("18:00");
				}else{
					item17StudentIdTemp.val(rows[0].item17Studentid);
				}
				var temp18=rows[0].item18Studentid;
				if(!(temp18=="NO")&&timeItemClikcFlag==18||!(temp18=="NO")&&timeItemClikcFlag==180){
					fieldOptionFlagTemp.val('18');
					
					reserTypeTemp.val(rows[0].item18TrainContent);
					timeStartTemp.val("18:00");
					timeEndTemp.val("19:00");
				}else{
					item18StudentIdTemp.val(rows[0].item18Studentid);
				}
				
	
			} else if (rows.length > 1) {
				parent.sy.messagerAlert('提示信息', '同一时间只能修改一条记录！', 'question');
			} else {
				parent.sy.messagerAlert('提示信息', '请选择要更新的记录！', 'question');
			}
		} else {
			parent.sy.messagerAlert('提示信息', '没有完成编辑不能提交！', 'question');
		}
	}
	
	
	//datagrid加载成功后的事件
	function onLoadSuccessReservationDetial(data){
		reservationDetailDatagrid.datagrid("fixRownumber");
		reservationDetailDatagrid.datagrid('hideColumn','trainerId');
		reservationDetailDatagrid.datagrid('hideColumn','reservationDetailId');
		reservationDetailDatagrid.datagrid('hideColumn','trainerReservationId');
		reservationDetailDatagrid.datagrid('hideColumn','reservationFieldId8');
		reservationDetailDatagrid.datagrid('hideColumn','reservationFieldId9');
		reservationDetailDatagrid.datagrid('hideColumn','reservationFieldId10');
		reservationDetailDatagrid.datagrid('hideColumn','reservationFieldId11');
		reservationDetailDatagrid.datagrid('hideColumn','reservationFieldId12');
		reservationDetailDatagrid.datagrid('hideColumn','reservationFieldId13');
		reservationDetailDatagrid.datagrid('hideColumn','reservationFieldId14');
		reservationDetailDatagrid.datagrid('hideColumn','reservationFieldId15');
		reservationDetailDatagrid.datagrid('hideColumn','reservationFieldId16');
		reservationDetailDatagrid.datagrid('hideColumn','reservationFieldId17');
		reservationDetailDatagrid.datagrid('hideColumn','reservationFieldId18'); 
		reservationDetailDatagrid.datagrid('selectRecord',defaultSelectRow);
		//datagrid没有数据时显示没有记录信息
		if(data.total==0){
			reservationDetailDatagrid.datagrid('appendRow', {
				reservationDate: '<div style="text-align:center;color:red">没有教练预约明细记录信息！</div>' })
			.datagrid('mergeCells', { index: 0, field: 'item10Studentid', colspan:10 });
		}
	}
	//格式化显示不排班时间段落
	function formatNoArrange(value,row,index){
		if(value=="NO"){
			return '<span style="color:red;">'+'休假'+'</span>';
		}else{
			return value;
		}
	}
	
	//格式化显示训练内容选项
	function formatTrainingItem(value,row,index){
		if(value==1){
			return '五项';
		}else if(value==2){
			return '路训';
		}else{
			return '';
		}
	}
	
	
	var editIndex = undefined;//当前编辑行的行标
	function endEditing(){
		if (editIndex == undefined) {
				return true
			}
			if (reservationDetailDatagrid.datagrid('validateRow', editIndex)) {
				reservationDetailDatagrid.datagrid('endEdit', editIndex);
				editIndex = undefined;
				return true;
			} else {
				return false;
			}
	}
	

	//单击单元格事件函数
	var rowsIndex = undefined;//选择单元格的行索引
	function onClickCell(index, field,value,addDialog) {
		rowsIndex = index;
		if (endEditing()) {
			reservationDetailDatagrid.datagrid('selectRow', index).datagrid(
					'editCell', {
						index : index,
						field : field,
						value:value,
					});
			editIndex = index;
			//标记用户操作预约时段
			switch(field){
				case 'item8Studentid':timeItemClikcFlag=8;
					break;
				case 'item9Studentid':timeItemClikcFlag=9;
					break;
				case 'item10Studentid':timeItemClikcFlag=10;
					break;
				case 'item11Studentid':timeItemClikcFlag=11;
					break;
				case 'item12Studentid':timeItemClikcFlag=12;
					break;
				case 'item13Studentid':timeItemClikcFlag=13;
					break;
				case 'item14Studentid':timeItemClikcFlag=14;
					break;
				case 'item15Studentid':timeItemClikcFlag=15;
					break;
				case 'item16Studentid':timeItemClikcFlag=16;
					break;
				case 'item17Studentid':timeItemClikcFlag=17;
					break;
				case 'item18Studentid':timeItemClikcFlag=18;
					break;
				case 'item8TrainContent':timeItemClikcFlag=80;
				break;
				case 'item9TrainContent':timeItemClikcFlag=90;
					break;
				case 'item10TrainContent':timeItemClikcFlag=100;
					break;
				case 'item11TrainContent':timeItemClikcFlag=110;
					break;
				case 'item12TrainContent':timeItemClikcFlag=120;
					break;
				case 'item13TrainContent':timeItemClikcFlag=130;
					break;
				case 'item14TrainContent':timeItemClikcFlag=140;
					break;
				case 'item15TrainContent':timeItemClikcFlag=150;
					break;
				case 'item16TrainContent':timeItemClikcFlag=160;
					break;
				case 'item17TrainContent':timeItemClikcFlag=170;
					break;
				case 'item18TrainContent':timeItemClikcFlag=180;
					break;
				}
		}
		
	}
	
	
	
	//删除用户
	function remove() {
		var rows = mydatagrid.datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			parent.sy.messagerConfirm('请确认','您要删除当前所选择项信息？',function(r) {
				if (r) {
					for (var i = 0; i < rows.length; i++) {
						ids.push(rows[i].id);
					}
					$.ajax({
						url : '${pageContext.request.contextPath}/demo/reservationAction!delete.action',
						data : {ids : ids.join(',')},
						dataType : 'json',
						success : function(d) {
								mydatagrid.datagrid('load');
								mydatagrid.datagrid('unselectAll');
								mydatagrid.datagrid('onUncheckAll');
								parent.sy.messagerShow({
										title : '提示信息',
										msg : d.msg
								});
						}
					});
				}
			});
		} else {
			parent.sy.messagerAlert('提示信息', '请勾选要删除的记录！', 'question');
		}
	}

	//查询按钮功能函数
	function mySearch() {
		mydatagrid.datagrid('load', sy.serializeObject($('#demo_reservation_searchForm')));
	}

	//取消查询功能按钮
	function cleanSearch() {
		mydatagrid.datagrid('load', {});
		//清空与查询有关的输入框
		$('#demo_reservation_searchForm input').val('');
	}
	

</script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询功能'" style="height: 145px;overflow: hidden;" align="left">
		<!-- 人员信息查询表单 -->
		<form id="demo_reservation_searchForm">
			<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
			<tr>
				<th style="width: 65px;">所属校区:</th>
				<td><input id="demo_reservation_organization" name="schoolArea" style="width: 155px;"/></td>
				<th style="width: 65px;">学员编号:</th>
				<td><input id="demo_reservation_driverType" name="studentNo" style="width: 155px;"/></td>
				<th style="width: 65px;">学员姓名:</th>
				<td><input id="demo_reservation_name" name="studentName" style="width: 155px;" /></td>
				<th style="width: 65px;">教练姓名:</th>
				<td><input id="demo_reservation_name" name="trainerName" style="width: 155px;" /></td>
			</tr>
			<tr>
				<th style="width: 65px;">学员身份证:</th>
				<td><input id="demo_reservation_organization" name="studentIdentityId" style="width: 155px;"/></td>
				<th style="width: 65px;">教练身份证:</th>
				<td><input id="demo_reservation_organization" name="trainerIdentityId" style="width: 155px;"/></td>
				<th>预约日期开始:</th>
				<td>
					<input name="dateStart" class="easyui-datebox mydateFormat" data-options="showSeconds:false,editable:false" 
					 style="width: 155px;" />
				</td>
				<th>预约日期结束:</th>
				<td>
					<input name="dateEnd" class="easyui-datebox mydateFormat" data-options="showSeconds:false,editable:false"
					 style="width: 155px;" />
				</td>
			</tr>
			<tr>
				<th style="width: 55px;">预约状态:</th>
				<td><input id="demo_reservation_state" name="reservationState" style="width: 155px;" /></td>
			</tr>
				<tr>
					<th></th>
					<td></td>
					<td colspan="2">
						<a href="javascript:void(0);" id="reservation_a_searche" class="easyui-linkbutton" iconCls="icon-search" onclick="mySearch();">查询</a>
						<a href="javascript:void(0);" id="reservation_a_canel" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cleanSearch();">取消</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<!-- datagrid数据在center中展现 -->
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<table id="demo_reservation_datagrid"></table>
	</div>
	<!-- 操作按钮事件 -->
	<div id="demo_reservation_menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="append();" data-options="iconCls:'icon-add'">增加</div>
		<div onclick="edit();" data-options="iconCls:'icon-edit'">更新</div>
	</div>
</body>
</html>
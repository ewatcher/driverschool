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
	var studentNameTemp=undefined;
	var studentIdentityIdTemp=undefined;
	var studentOrganizationTemp=undefined;
	var detailDialog=undefined;
	var studentNoEditer=undefined;
	var reservationDetailDatagrid=undefined;
	var trainingItemJson=[{"value":1,"text":"电车"},{"value":2,"text":"模拟"},{"value":3,"text":"五项"},{"value":4,"text":"路训"}];
	var timeItemClikcFlag=undefined;//记录当前预约操作的是哪个时间列
	var defaultSelectRow=undefined;
	var nextStepFlag=undefined;
	var studentSelectDatagrid=undefined;
	var reservationTypeNotMatch=false;//学员预约类型与教练预约明细中预约类型匹配
	var driverTypeCombobox=undefined;
	var reservationTypeCombobox=undefined;
	var selectReservationTypeTemp=undefined;
	var selectDriverTypeTemp=undefined;
	
	var studentTypeTemp=undefined;
	
	//添加页面中教练属性全局参数定义
	var trainerTeachingTypeTemp=undefined;
	var trainerWorkongTypeTemp=undefined;
	var trainerIdTemp=undefined;
	var	trainerName=undefined;
	var trainerIdentityIdTemp=undefined;
	var trainerPhoneTemp=undefined;
	var trainerTypeTemp=undefined;
	var studentTypeTemp=undefined;
	
	//添加预约标记
	var addReservationFlag=false;
	var workingTypeVal=undefined;
	var cancelFlag=false;//用记在教练预约明细表中选择“取消”标记变量
	var exitFlag=undefined;
	
	var trainerTypeFlag=undefined;//用来记录预约选择教练时，记录教练类型
	var selectAutocarDialog=undefined;
	
	var addReservationFlag=true;
	
	//训练内容编辑器初始化
	var trainingTypeEditor={
			type:'combobox',
			options:{
				data:trainingItemJson,
				valueField:'value',
				textField:'text'
			},
			onChange:function(){
				addReservationFlag=true;
			}
		};
	
	var userSchoolArea=undefined;
	var userACLs=[];
	$(function() {
		userSchoolArea=getUserSchoolArea();
		getUserACL();
		
		var reservationDateTemp=undefined;
		mydatagrid = $('#demo_reservation_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/demo/reservationAction!datagrid.action',/* 从后台读取所有user数据 */
			title : '预约信息管理列表!', /* 表头标题 */
			iconCls : 'icon-save', /* 标题前边的图标 */
			pagination : true, /* 是否显示页码 */
			pagePosition : 'bottom', /* 页码显示的位置 */
			pageSize : 20, /* 每面显示的记录数 */
			pageList : [ 10, 20, 30, 40 ,50,100,500], /* 选择每面显示的记录数 */
			striped:true,
			fit : true,
			fitColumns : false, //列自动调整功能
			nowrap : false, //以行的形式进行显示
			border : false,
			rownumbers: true,
			idField : 'id', //此属性一定要配置
			sortName : 'numbering', //列排序
			sortOrder : 'desc', //列排序
			checkOnSelect : true,
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
				width : 100,
				align : 'left'
			},{
				title : '<font color="#0099FF">学员编号</font>',
				field : 'studentNo',
				width : 80,
				align : 'center',
				formatter:formatStudentNo,
			},{
				title : '<font color="#0099FF">学员姓名</font>',
				field : 'studentName',
				width : 70,
				align : 'left'
			},{
				title : '<font color="#0099FF">预约教练</font>',
				field : 'trainerName',
				width : 70,
				align : 'left'
			},{
				title : '<font color="#0099FF">预约内容</font>',
				field : 'reservationType',
				sortable : true,
				width : 80,
				align : 'left',
				formatter:formatterTimingUsingType,
			},{
				title : '<font color="#0099FF">预约类型</font>',
				field : 'studentDriverType',
				width : 70,
				align : 'center',
				formatter:formatterDriverType,
			},{
				title : '<font color="#0099FF">预约日期</font>',
				field : 'date',
				sortable : true,
				width : 90,
				align : 'center',
				formatter:formatTimeYMD,
			},
			 ] ],
			
			 columns:[[{
					title : '<font color="#0099FF">开始时段</font>',
					field : 'timeStart',
					sortable : true,
					width : 80,
					align : 'center'
				}, {
					title : '<font color="#0099FF">结束时段</font>',
					field : 'timeEnd',
					sortable : true,
					width :80,
					align : 'center'
				},{
					title : '<font color="#0099FF">预约状态</font>',
					field : 'reservationState',
					sortable : true,
					width : 80,
					align : 'center',
					formatter:formatterReservationState,
				}, {
					title : '<font color="#0099FF">预约车辆</font>',
					field : 'carNo',
					width : 80,
					align : 'center',
				},{
					title : '<font color="#0099FF">车辆状态</font>',
					field : 'carStateFlag',
					sortable : true,
					width : 80,
					align : 'left',
					formatter:formatCarState,
					hidden:true,
				},{
					title : '<font color="#0099FF">创建时间</font>',
					field : 'createTime',
					sortable : true,
					width : 140,
					align : 'center',
					formatter:formatTimeYMDHMS,
				},{
					title : '<font color="#0099FF">创建方式</font>',
					field : 'reservationWay',
					sortable : true,
					width : 80,
					align : 'center',
					formatter:formatReservationWay,
				},{
					title : '<font color="#0099FF">学员属性</font>',
					field : 'studentType',
					width : 80,
					align : 'center',
					formatter:function(value,row,index){
						return initTrainerType(value, row, index);
					}
				},{
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
					title : '<font color="#0099FF">操作员</font>',
					field : 'operator',
					width : 80,
				},{
					title : '<font color="#0099FF">学员确认</font>',
					field : 'studentConfirm',
					width : 80,
					align : 'center',
					formatter:formatterReservationConfirm,
				},{
					title : '<font color="#0099FF">教练确认</font>',
					field : 'trainerConfirm',
					width : 80,
					align : 'center',
					formatter:formatterReservationConfirm,
				},{
					title : '<font color="#0099FF">教练预约明细标识</font>',
					field : 'trainerReservationDetailId',
					width : 150, 
					align : 'center',
					
				},{
					title : '<font color="#0099FF">学员标识</font>',
					field : 'studentId',
					width : 150, 
					align : 'center',
					
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
				id:'cancel-btn-toolbar',
				text : '取消预约',
				iconCls : 'icon-edit',
				handler : function() {
					cancelReservation();
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
			onBeforeLoad:function(param){
				if(!userACLs[0]){ //Create
                    $("#add-btn-toolbar").linkbutton('disable'); 
                    $("#add-menu").hide(); 
                }
				if(!userACLs[2]){//Update
					$("#edit-btn-toolbar").linkbutton('disable');
					$("#cancel-btn-toolbar").linkbutton('disable');
					$("#import-btn-toolbar").linkbutton('disable'); 
					$("#export-btn-toolbar").linkbutton('disable'); 
					 $("#edit-menu").hide();
					 $("#cancel-menu").hide();
				}
				if(!userACLs[3]){//delete
					$("#del-btn-toolbar").linkbutton('disable');  
					 $("#del-menu").hide();
				}
			},
			onLoadSuccess : onLoadSuccess,
			onDblClickCell:function(rowIndex, field, value){
				var rows=mydatagrid.datagrid('getSelections');
				var reservationDate=new Date(rows[0].date);
				var now=new Date();
				if(rows.length==1){
					//预约单击学员确认事件
					if(field=='studentConfirm'&& reservationDate<=now
							&& rows[0].studentConfirm!=1){
						if(rows[0].reservationState==1||rows[0].reservationState==6){
							confirmReservation(rows[0].id,1);
						}
					}else if(field=='trainerConfirm'
							&& reservationDate<=now
							&& rows[0].trainerConfirm!=1){
						if(rows[0].reservationState==1||rows[0].reservationState==6){
							confirmReservation(rows[0].id,2);
						}
					}
				}else if (rows.length > 1) {
					parent.sy.messagerAlert('提示信息', '同一时间只能确认一条记录！', 'error');
				} else {
					parent.sy.messagerAlert('提示信息', '请选择要更新的记录！', 'error');
				}
			},
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
	//确认预约
	function confirmReservation(reservationId,confirmFlag){
		$.ajax({
			url : '${pageContext.request.contextPath}/demo/reservationAction!confirmReservation.action',
			data : {id :reservationId,confirmFlag:confirmFlag},
			dataType : 'json',
			success : function(d) {
				mydatagrid.datagrid('reload');
				mydatagrid.datagrid('unselectAll');
				parent.sy.messagerShow({
					title : '提示信息',
					msg : d.msg
				});
			}
		});
	}
	
	
	function onLoadSuccess(data) {
		//修改序号bug
		mydatagrid.datagrid("fixRownumber");
		mydatagrid.datagrid('hideColumn','trainerReservationDetailId');
		mydatagrid.datagrid('hideColumn','studentId');
		//初始化校区选项
		initSchoolArea($("#demo_reservation_organization"),userSchoolArea);
		//初始化预约状态选项
		initReservationState($("#demo_reservation_state"));
		//初始化预约类型
		initReservationType($("#demo_reservation_type"));
		//添加回车事件
		bindingKeypressEvent($(".reservation_keyEvent"),mydatagrid,$("#demo_reservation_searchForm"));
		
		//datagrid没有数据时显示没有记录信息
		if(data.total==0){
			mydatagrid.datagrid('appendRow', {
				schoolArea: '<div style="text-align:center;color:red">没有预约记录信息！</div>' })
			.datagrid('mergeCells', { index: 0, field: 'numbering', colspan:10 });
		}
		$(".easyui-linkbutton").linkbutton({});

	}
	
	
	//取消预约
	function cancelReservation(){
		var rows = mydatagrid.datagrid('getSelections');
		if (rows.length>0 && rows.length==1) {
			/* //1.可取消预约记录的条件，（预约记录状态不等于：完成和确认中……　）
			if(rows[0].reservationState==3||rows[0].reservationState==6){
				parent.sy.messagerAlert('提示信息', '在当前预约状态下不允许删除该记录！', 'question');
				return;
			} */
			parent.sy.messagerConfirm('请确认',
					'您要取消当前所选择预约信息吗？单击〈确定〉确认取消',
					function(r) {
							$.ajax({
								url : '${pageContext.request.contextPath}/demo/reservationAction!cancelReservation.action',
								data : {id : rows[0].id,studentId:rows[0].studentId,reservationDetailId:rows[0].trainerReservationDetailId},
								dataType : 'json',
								success : function(d) {
									mydatagrid.datagrid('load');
									mydatagrid.datagrid('unselectAll');
									parent.sy.messagerShow({
										title : '提示信息',
										msg : d.msg
									});
								}
							});
			});
		}else if(rows.length<=0){
			parent.sy.messagerAlert('提示信息', '请选择需要取消记录！', 'question');
		}else{
			parent.sy.messagerAlert('提示信息', '同一时间只能取消一条记录！', 'question');
		}
	}
	
	//添加预约
	function append() {
		var addDialog=undefined;
		 addDialog = parent.sy.dialog({
					title : '预约信息录入---主页',
					href : '${pageContext.request.contextPath}/demo/reservationAction!toReservationAddPage.action',
					width : 1200,
					height :800,
					resizable:true,
					maximizable:true,
					buttons : [
					           {
								text : '关闭',
								iconCls : 'icon-cancel',
								handler : function() {
									addDialog.dialog('close');
								},
							},
						],
							//通过onLoad函数实现数据回显
					onLoad : function() {
						//初始化添加页面选项＝＝＝＝＝begin====
						var myForm=addDialog.find('form');
						//初始化校区选项
						var myDepartment=myForm.find('input[name=studentOrganization]');
						//初始化校区选项
						initSchoolArea(myDepartment,userSchoolArea);
						//设置添加页面中部分选项的可读属性
						var readonlyItems=myForm.find('input[class=reservation_add_readonly]');
						readonlyItems.attr('readonly',true);
						
						//初始化预约类型（选择电车，随机加载一个电车教练，选择模拟，随机匹配一个模拟教练）
						selectReservationTypeTemp=myForm.find('input[name=selectReservationType]');
						initReservationType(selectReservationTypeTemp,3);
						selectReservationTypeTemp.combobox({
							onChange:function(){
								var studentIdTemp=myForm.find('input[name=trainerId]');
								var trainerIdTemp=myForm.find('input[name=trainerId]');
								var trainerNameTemp=myForm.find('input[name=trainerName]');
								var trainerIdentityIdTemp=myForm.find('input[name=trainerIdentityId]');
								var trainerPhoneTemp=myForm.find('input[name=trainerPhone]');
									trainerTeachingTypeTemp=myForm.find('input[name=trainerTeachingType]');
									trainerWorkingTypeTemp=myForm.find('input[name=trainerWorkingType]');
									trainerTypeTemp=myForm.find('input[name=trainerType]');
								//当预约选择预约类型为电车，模拟时，自动初始化教练信息
								$.ajax({
									url : '${pageContext.request.contextPath}/demo/reservationAction!getElectricAndSimulations.action',
									dataType : 'json',
									success : function(d) {
										var electricTrainer=d.obj["electricTrainer"];
										var simulationTrainer=d.obj["simulationTrainer"];
										var comboboxVal=selectReservationTypeTemp.combobox('getValue');
										if(comboboxVal==1){//电车预约
											if(electricTrainer!=null&&electricTrainer!=undefined&&electricTrainer!=''){
												//初始化教练信息显示
												trainerIdTemp.val(electricTrainer.id);
												trainerNameTemp.val(electricTrainer.name);
												trainerIdentityIdTemp.val(electricTrainer.identity);
												trainerPhoneTemp.val(electricTrainer.phone);
												trainerTypeTemp.val(initPropertyType(electricTrainer.trainerType));
												trainerWorkingTypeTemp.val(initArrangeType(electricTrainer.arrangeType));
												//初始化教练预约明细列表
												initReservationDetailDatagrid(addDialog,electricTrainer.id,studentNoEditer,
		    											studentNoTemp.val(),studentNameTemp.val(),defaultSelectRow,comboboxVal,studentIdTemp.val());
											}else{
												emptyPropertyAndDatagrid(trainerIdTemp,trainerNameTemp,trainerIdentityIdTemp,trainerPhoneTemp,trainerTypeTemp,trainerWorkingTypeTemp);
											}
										}else if(comboboxVal==2){//模拟预约
											if(simulationTrainer!=null&&electricTrainer!=undefined&&simulationTrainer!=''){
												//初始化教练信息显示
												trainerIdTemp.val(simulationTrainer.id);
												trainerNameTemp.val(simulationTrainer.name);
												trainerIdentityIdTemp.val(simulationTrainer.identity);
												trainerPhoneTemp.val(simulationTrainer.phone);
												trainerTypeTemp.val(initPropertyType(simulationTrainer.trainerType));
												trainerWorkingTypeTemp.val(initArrangeType(simulationTrainer.arrangeType));
												//初始化教练预约明细列表
												initReservationDetailDatagrid(addDialog,simulationTrainer.id,studentNoEditer,
		    											studentNoTemp.val(),studentNameTemp.val(),defaultSelectRow,comboboxVal,studentIdTemp.val());
											}else{
												emptyPropertyAndDatagrid(trainerIdTemp,trainerNameTemp,trainerIdentityIdTemp,trainerPhoneTemp,trainerTypeTemp,trainerWorkingTypeTemp);
											}
										}else if(comboboxVal==3){//五项
											//清空教练预约明细列表
											emptyPropertyAndDatagrid(trainerIdTemp,trainerNameTemp,trainerIdentityIdTemp,trainerPhoneTemp,trainerTypeTemp,trainerWorkingTypeTemp);
										}else if(comboboxVal==4){//路训
											//清空教练预约明细列表
											emptyPropertyAndDatagrid(trainerIdTemp,trainerNameTemp,trainerIdentityIdTemp,trainerPhoneTemp,trainerTypeTemp,trainerWorkingTypeTemp);
										}
									},
									errer:function(err){
										reservationDetailDatagrid.datagrid('loadData', { total: 0, rows: [] });
										trainerIdTemp.val('');
										trainerNameTemp.val('');
										trainerIdentityIdTemp.val('');
										trainerPhoneTemp.val('');
										trainerTypeTemp.val('');
										trainerWorkingTypeTemp.val('');
									}
								});//end ajax
								
							}
						});
						
						//选择学员按钮事件
						var btn_student=myForm.find('a[id=reservation_a_student]');
						btn_student.linkbutton({
							    iconCls: 'icon-search',
							    onClick:function(){
							    	findStudentInfo(addDialog,userSchoolArea,selectReservationTypeTemp.combobox('getValue'));
							    }
							});
						
						//加载符合预约条件的信息	
						findStudentInfo(addDialog,userSchoolArea,selectReservationTypeTemp.combobox('getValue'));	
					},
					
				});
		
	}
	
	function emptyPropertyAndDatagrid(trainerIdTemp,trainerNameTemp,trainerIdentityIdTemp,trainerPhoneTemp,trainerTypeTemp,trainerWorkingTypeTemp){
		if(reservationDetailDatagrid!=null&&reservationDetailDatagrid!=undefined&&reservationDetailDatagrid!=''){
			reservationDetailDatagrid.datagrid('loadData', { total: 0, rows: [] });
		}
		trainerIdTemp.val('');
		trainerNameTemp.val('');
		trainerIdentityIdTemp.val('');
		trainerPhoneTemp.val('');
		trainerTypeTemp.val('');
		trainerWorkingTypeTemp.val('');
	}
	
	function initArrangeType(value){
		if(value==1){
			return '电车';
		}else if(value==2){
			return '模拟';
		}
	}
	function initPropertyType(value){
		if(value==0){
			return '本校';
		}else if(value==1){
			return '承包';
		}
	}
	//1.单击选择学员按钮，查找出学员信息
	function findStudentInfo(addDialog,myDepartment,reservationTpyeVal){
	
		//1.1 确证预约是否已经选择所属校区
    	if(myDepartment==""){
    		parent.sy.messagerAlert('提示信息', '当前没有选择所属校区，请选择！', 'question');
    	}else{
    		//==============select student diaolog====
	    	//选择学员对话框
	    	var selectDialogAdd=undefined;
	    		selectDialogAdd=parent.sy.dialog({
	    			
	    			title : '1.预约信息录入(选择学员)',
	    			href : '${pageContext.request.contextPath}/demo/reservationAction!toReservationSelectStudentInputPage.action',
	    			width : 1024,
	    			height : 750,
	    			buttons : [
	    				{
	    					text : '关闭',
	    					iconCls : 'icon-edit',
	    					handler : function(){
	    						selectDialogAdd.dialog('close');
	    						addDialog.dialog('close');
	    					}
	    				},
	    			  ],
	    			//通过onLoad函数实现数据回显
	    			onLoad : function() {
	    				studentSelectDialogOnload(addDialog,selectDialogAdd,myDepartment,reservationTpyeVal);
	    			}
	    		});
    	}
		
	}
	//选择学员对话框中onload事件
	function studentSelectDialogOnload(addDialog,selectDialogAdd,myDepartment,reservationTpyeVal){
		//符合预约学员信息列表   ===================begin===============
		var f=selectDialogAdd.find('form');
		var myForm=addDialog.find('form');
		var studentIdTemp=myForm.find('input[name=studentId]');
		 studentNameTemp=myForm.find('input[name=studentName]');
		 studentIdentityIdTemp=myForm.find('input[name=studentIdentityId]');
		 studentNoTemp=myForm.find('input[name=studentNo]');
		 selectDriverTypeTemp=myForm.find('input[name=selectDriverType]');
		 studentTypeTemp=myForm.find('input[name=studentType]');
		 
		 var bandingTrainerId=myForm.find('input[name=trainerId]');
		 var bandingTrainerName=myForm.find('input[name=trainerName]');
		 var bandingTrainerIdentity=myForm.find('input[name=trainerIdentityId]');
		 var bandingTrainerPhone=myForm.find('input[name=trainerPhone]');
		 var bandingTrainerTeachingType=myForm.find('input[name=trainerTeachingType]');
		 var bandingTrainerWorkingType=myForm.find('input[name=trainerWorkingType]');
		
		 btn_selectTrainer=myForm.find('a[id=reservation_a_selectTrainer]');
		 var searchStudentNoTemp=f.find('input[name=studentNo]');
		 var trainerTypeVal=undefined;
		//初始化所属校区选项
		var organization=f.find('input[name=schoolArea]');
		initSchoolArea(organization,myDepartment);
		//(选择学员页面)驾照类型初始化
		driverTypeCombobox=f.find('input[name=studentDriverType]');
		initDriverType(driverTypeCombobox,6);
		
		//查询按钮，重置按钮功能
		var searchBtn=f.find('a[id=reservation_a_search]');
		searchBtn.linkbutton({
		    iconCls: 'icon-search',
		    onClick:function(){
		    	organization.val(myDepartment);
		    	searchStudentNo(searchStudentNoTemp, userSchoolArea);
		    	studentSelectDatagrid.datagrid('load', sy.serializeObject(f));
		    	searchStudentNoTemp.val('');
		    }
		});
		//学员编号添加回车事件
		searchStudentNoTemp.unbind('keypress');
		searchStudentNoTemp.bind('keypress',function(event){
            if(event.keyCode == "13")    
            {
            	organization.val(myDepartment);
		    	searchStudentNo(searchStudentNoTemp, userSchoolArea);
		    	studentSelectDatagrid.datagrid('load', sy.serializeObject(f));
		    	searchStudentNoTemp.val('');
            }
        });
		
		//取消按钮事件
		var resetBtn=f.find('a[id=reservation_a_cancel]');
		resetBtn.linkbutton({
		    iconCls: 'icon-cancel',
		    onClick:function(){
		    	//将数据还原显示到点击查询按钮前的数据
		    	studentSelectDatagrid.datagrid('load', {schoolArea:myDepartment,
		    		studentDriverType:6});
		    	driverTypeCombobox.combobox('setValue',6);//业务类型默认为Ｃ1
		    	f.find('input[class=demo_reservation_clear]').val('');
		    	f.find('input[name=registerTimeStart]').val('');
		    	f.find('input[name=registerTimeEnd]').val('');
		    }
		});
		
		//选择学员页面datagrid初始化
		 studentSelectDatagrid=f.find('table[id=demo_reservation_selectStudent]');
		studentSelectDatagrid.datagrid({
    		url : '${pageContext.request.contextPath}/demo/reservationAction!getAllReservationStudents.action',
			queryParams:{schoolArea:myDepartment},
    		title : '符合预约条件学员信息', /* 表头标题 */
			iconCls : 'icon-save', /* 标题前边的图标 */
			pagination : true, /* 是否显示页码 */
			pagePosition : 'bottom', /* 页码显示的位置 */
			pageSize : 20, /* 每面显示的记录数 */
			pageList : [ 10, 20, 30, 40,,50,100,500 ], /* 选择每面显示的记录数 */
			fit : true,
			fitColumns : false, //列自动调整功能
			nowrap : false, //以行的形式进行显示
			border : false,
			rownumbers: true,
			idField : 'studentId', //此属性一定要配置
			sortName : 'studentNo', //列排序 //TODO
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
					width : 100,
					align : 'center'
				}, {
					title : '<font color="#0099FF">业务类型</font>',
					field : 'studentDriverType',
					width : 65,
					align : 'center',
					formatter:formatterDriverType,
				},{
					title : '<font color="#0099FF">姓名</font>',
					field : 'studentName',
					width : 80,
					align : 'center'
				} ,{
					title : '<font color="#0099FF">学员编号</font>',
					field : 'studentNo',
					width : 100,
					sortable : true,
					align : 'center',
					formatter:formatStudentNo,
				} ] ],
				//普通列
				columns : [ [ {
					title : '<font color="#0099FF">性别</font>',
					field : 'studentSex',
					width : 60,
					align:'center',
				}, {
					title : '<font color="#0099FF">学员类别</font>',
					field : 'studentType',
					width : 70,
					sortable : true,
					align : 'center',
					formatter:initStudentType,
				},{
					title : '<font color="#0099FF">报名日期</font>',
					field : 'studentCreateTime',
					width : 80,
					align : 'center',
					formatter:formatTimeYMD,
				},{
					title : '<font color="#0099FF">计时类型</font>',
					field : 'timingFlag',
					width : 70,
					align : 'center',
					formatter:initTimingCountType,
				},{
					title : '<font color="#0099FF">状态</font>',
					field : 'studentNowstate',
					width : 80,
					formatter:formatStudentState,
				},{
					title : '<font color="#0099FF">身份证号</font>',
					field : 'studentIdentityId',
					width : 150
				},{
					title : '<font color="#0099FF">联系电话</font>',
					field : 'studentPhone',
					width : 150
				},{
					title : '<font color="#0099FF">教练</font>',
					field : 'trainerName',
					width : 100,
					align : 'center'
				},{
					title : '<font color="#0099FF">教练标识</font>',
					field : 'trainerId',
					width : 150,
					align : 'center'
				},{
					title : '<font color="#0099FF">教练身份证</font>',
					field : 'trainerIdentityId',
					width : 150,
					align : 'center'
				},{
					title : '<font color="#0099FF">教练标识</font>',
					field : 'trainerId',
					width : 150,
					align : 'center'
				},{
					title : '<font color="#0099FF">教练电话</font>',
					field : 'trainerPhone',
					width : 150,
					align : 'center'
				},{
					title : '<font color="#0099FF">施教类型</font>',
					field : 'trainerTeachingType',
					width : 150,
					align : 'center'
				},{
					title : '<font color="#0099FF">排班类型</font>',
					field : 'arrangeType',
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
						 if(rows.length>0 ){
							 //从表中获取学员的姓名和身份证号
							studentIdTemp.val(rows[0].studentId);
							studentNameTemp.val(rows[0].studentName);
							studentIdentityIdTemp.val(rows[0].studentIdentityId);
							studentNoTemp.val(rows[0].studentNo);
							//初始化添加主页面中的驾照类型
							initDriverType(selectDriverTypeTemp,rows[0].studentDriverType,true);
							studentTypeTemp.val(initPropertyType(rows[0].studentType));
							//选择学员标识editor
							 var myText=rows[0].studentNo+":"+rows[0].studentName;
							 var studentDatas=[{"value":myText,"text":myText},{"value":"1","text":"取消"}];
							 studentNoEditer={
									type:'combobox',
									options:{
										data:studentDatas,
										valueField:'value',
										textField:'text'
									}
							 };
							
							workingTypeVal=selectReservationTypeTemp.combobox('getValue');//预约类型
							var myTrainerIdTemp=rows[0].trainerId;
							//记录学员类别变量
							studentTypeTemp=rows[0].studentType;
							trainerTypeVal=rows[0].studentType;
							//学员没有与教练捆绑预约处理
							if(validateIsNull(myTrainerIdTemp)){
								 if(bandingTrainerId.val()!=null&&bandingTrainerId.val()!=undefined&&bandingTrainerId.val()!=""){
									 initReservationDetailDatagrid(addDialog,bandingTrainerId.val(),studentNoEditer,
											 studentNoTemp.val(),studentNameTemp.val(),defaultSelectRow,workingTypeVal,studentIdTemp.val());
								 }
								 selectDialogAdd.dialog('close');
							}else{//学员与教练捆绑则自动选择该学员教练的预约明细信息
								 bandingTrainerId.val(rows[0].trainerId);
								 bandingTrainerName.val(rows[0].trainerName);
								 bandingTrainerIdentity.val(rows[0].trainerIdentityId);
								 bandingTrainerPhone.val(rows[0].trainerPhone);
								  //添加主页面中的教练属性的排班类型
								 initReservationType(bandingTrainerWorkingType,workingTypeVal);
								 bandingTrainerWorkingType.val(workingTypeVal);
								//TODO 初始化教练施教类型参数及排班类型参数
								initReservationDetailDatagrid(addDialog,bandingTrainerId.val(),studentNoEditer,
											 studentNoTemp.val(),studentNameTemp.val(),defaultSelectRow,workingTypeVal,studentIdTemp.val());
								selectDialogAdd.dialog('close');
							}
							
						 }else if(reservationTypeCombobox.combobox('getValue')==""){
							parent.sy.messagerAlert('提示信息', '请选择预约类型！！', 'question');
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
				studentSelectDatagrid.datagrid('hideColumn','trainerId');
				studentSelectDatagrid.datagrid('hideColumn','trainerIdentityId');
				studentSelectDatagrid.datagrid('hideColumn','trainerPhone');
				studentSelectDatagrid.datagrid('hideColumn','trainerTeachingType');
				studentSelectDatagrid.datagrid('hideColumn','arrangeType');
			},
			
    	});/////==============end datagrid=============
		
    	//单击选择添加页面中的=>选择教练<=事件
		btn_selectTrainer.linkbutton({
		    iconCls: 'icon-search',
		    onClick:function(){
		    	if(validateIsNull(studentIdTemp.val())){
		    		parent.sy.messagerAlert('提示信息', '请选择学员，学员信息栏不能为空！！！！', 'question');
		    	}else{
		    		selectTrainerEvent(addDialog,myDepartment,selectDriverTypeTemp.combobox('getValue'),
							selectReservationTypeTemp.combobox('getValue'),studentNoEditer,null,trainerTypeVal);
		    	}
		    }
		});
    	
    }
	
	//初始化选择教练事件　
	function selectTrainerEvent(addDialog,myDepartment,teachingTypeVal,workingTypeVal,studentNoEditer,studentIdVal,trainerTypeVal){
		//符合预约学员信息列表   ===================begin===============
		var selectTrainerDialog=undefined;
		var myForm=addDialog.find('form');
		var trainerIdTemp=myForm.find('input[name=trainerId]');
		var trainerNameTemp=myForm.find('input[name=trainerName]');
		var trainerIdentityIdTemp=myForm.find('input[name=trainerIdentityId]');
		var trainerPhoneTemp=myForm.find('input[name=trainerPhone]');
			trainerWorkingTypeTemp=myForm.find('input[name=trainerWorkingType]');
			trainerTypeTemp=myForm.find('input[name=trainerWorkingType]');
		var trainerTypeTemp=myForm.find('input[name=trainerType]'); 
		var myReservationType=myForm.find('input[name=selectReservationType]');
		    //选择学员对话框
		    selectTrainerDialog=parent.sy.dialog({
		    		title : '<div style=color:red align=center>2.选择学员预约教练</div>',
		    		href : '${pageContext.request.contextPath}/demo/reservationAction!toSelectTrainerInputPage.action',
		    		width : 960,
		    		height : 750,
		    		buttons : [
		    				{
		    					text : '关闭',
		    					iconCls : 'icon-cancel',
		    					handler : function(){
		    						selectTrainerDialog.dialog('close');
		    						addDialog.dialog('close');
		    					}
		    				},
		    			  ],
		    			  
		    			//通过onLoad函数实现数据回显
		    		onLoad : function() {
		    				//符合预约教练信息列表   ===================begin===============
		    				var trainerForm=selectTrainerDialog.find('form');	
		    				trainerSelectDatagrid=trainerForm.find('table[id=demo_reservation_selectTrainer]');
		    				trainerSelectDatagrid.datagrid({
		    		    		url : '${pageContext.request.contextPath}/demo/reservationAction!getTrainerArrangeForReservation.action',
		    		    		queryParams:{selectReservationType:myReservationType.val(),teachingType:teachingTypeVal,
		    		    			workingType:workingTypeVal,schoolArea:myDepartment,trainerType:trainerTypeVal},
		    		    		title : '符合预约条件教练信息', /* 表头标题 */
		    					iconCls : 'icon-save', /* 标题前边的图标 */
		    					pagination : true, /* 是否显示页码 */
		    					pagePosition : 'bottom', /* 页码显示的位置 */
		    					pageSize : 30, /* 每面显示的记录数 */
		    					pageList : [ 10, 20, 30, 40,50 ,100,200 ], /* 选择每面显示的记录数 */
		    					fit : true,
		    					fitColumns : false, //列自动调整功能
		    					nowrap : false, //以行的形式进行显示
		    					border : false,
		    					rownumbers: true,
		    					idField : 'trainerId', //此属性一定要配置
		    					sortName : 'schoolArea', //列排序 //TODO
		    					sortOrder : 'desc', //列排序
		    					checkOnSelect : true,
		    					selectOnCheck : true,
		    					striped : true,//表示条纹，隔行显示不现背景色
		    					nowrap:true,//单元显示
		    					singleSelect:true,
		    					//冻结列
		    					frozenColumns : [ [ {
		    						title : '编号',
		    						field : 'trainerId',
		    						width : 20,
		    						sortable : true,
		    						checkbox : true
		    					}, {
		    						title : '<font color="#0099FF">所属校区</font>',
		    						field : 'schoolArea',
		    						sortable : true,
		    						width : 100,
		    						align : 'center'
		    					}, {
		    						title : '<font color="#0099FF">教练编号</font>',
		    						field : 'trainerCodeNo',
		    						sortable : true,
		    						width : 80,
		    						align : 'center',
		    						formatter:formatTrainerNo,
		    					},{
		    						title : '<font color="#0099FF">姓名</font>',
		    						field : 'trainerName',
		    						width : 80,
		    						sortable : true,
		    						align : 'center'
		    					} ,{
		    						title : '<font color="#0099FF">是否排班</font>',
		    						field : 'trainerArrangeFlag',
		    						sortable : true,
		    						width : 70, 
		    						formatter:initTrainerArrangeFlag,
		    					},{
		    						title : '<font color="#0099FF">排班类型</font>',
		    						field : 'arrangeType',
		    						sortable : true,
		    						width : 70, 
		    						formatter:formatArrangType,
		    					},{
		    						title : '<font color="#0099FF">最近排班日期</font>',
		    						field : 'trainerNealyArrageDate',
		    						sortable : true,
		    						width : 120, 
		    						align : 'center',
		    						formatter:formatTimeYMD,
		    					},{
		    						title : '<font color="#0099FF">教练类别</font>',
		    						field : 'trainerType',
		    						sortable : true,
		    						width : 80,
		    						align : 'center',
		    						formatter:initTrainerType,
		    					},{
			    						title : '<font color="#0099FF">身份证</font>',
			    						field : 'trainerIdentityId',
			    						sortable : true,
			    						width : 150, 
			    					},{
			    						title : '<font color="#0099FF">电话</font>',
			    						field : 'trainerPhone',
			    						sortable : true,
			    						width : 150, 
			    					},
		    		    		]],
		    		    		
		    		    		toolbar : [ 
		    		    		    {
		    		    				text : '确认',
		    							iconCls : 'icon-add',
		    							handler : function() {
		    								//初始化添加页面中的教练属性信息
		    								var rows=trainerSelectDatagrid.datagrid('getChecked');
		    								//TODO 校验如果校区选择为空应该给出提示信息
		    								 if(rows.length>0){
		    									 //初始化显示教练基本信息
		    									trainerIdTemp.val(rows[0].trainerId);
		    									trainerNameTemp.val(rows[0].trainerName);
		    									trainerIdentityIdTemp.val(rows[0].trainerIdentityId);
		    									trainerPhoneTemp.val(rows[0].trainerPhone);
		    									trainerTypeTemp.val(initPropertyType(rows[0].trainerType));
		    									trainerTypeFlag=rows[0].trainerType;
		    									//TODO 初始化教练施教类型参数及排班类型参数
		    									initReservationDetailDatagrid(addDialog,rows[0].trainerId,studentNoEditer,
		    											studentNoTemp.val(),studentNameTemp.val(),defaultSelectRow,workingTypeVal,studentIdVal);
		    									selectTrainerDialog.dialog('close');					
		    								 }else{
		    									parent.sy.messagerAlert('提示信息', '您当前没有选择教练信息！', 'question');
		    								}
		    							}
		    						}, {
		    		    				text : '取消选中',
		    							iconCls : 'icon-undo',
		    							handler : function() {
		    								var rows=trainerSelectDatagrid.datagrid('getChecked');
		    								if(rows.length<=0){
		    									parent.sy.messagerAlert('提示信息', '您当前没有选中行信息！', 'question');
		    								}
		    								//取消所有的选中行
		    								trainerSelectDatagrid.datagrid('clearSelections');
		    								//取消当前页面的选中行
		    								trainerSelectDatagrid.datagrid('unselectAll');
		    							}
		    						},
		    						
		    						'-'],
		    						onLoad:function(){
		    						},
		    					
		    		    	});/////==============end datagrid=============
		    		    	var f=selectTrainerDialog.find('form');
		    		    		//初始化所属校区选项
		    				var trainerSchoolArea=trainerForm.find('input[name=schoolArea]');
		    				initSchoolArea(trainerSchoolArea,userSchoolArea);
		    				//查询按钮，重置按钮功能
		    				var searchBtn=trainerForm.find('a[id=reservation_trainer_a_search]');
		    				searchBtn.linkbutton({
		    				    iconCls: 'icon-search',
		    				    onClick:function(){
		    				    	trainerSelectDatagrid.datagrid('load', sy.serializeObject(f));
		    				    }
		    				});
		    				//取消按钮事件
		    				var resetBtn=trainerForm.find('a[id=reservation_trainer_a_cancel]');
		    				resetBtn.linkbutton({
		    				    iconCls: 'icon-cancel',
		    				    onClick:function(){
		    				    	//将数据还原显示到点击查询按钮前的数据
		    				    	trainerSelectDatagrid.datagrid('load',sy.serializeObject(f));//TODO
		    				    	trainerForm.find('input[class=demo_reservation_clear]').val('');
		    				    }
		    				});
		    				//添加主页面中的教练属性的排班类型
		    				initReservationTypeParams(trainerWorkingTypeTemp,workingTypeVal);
		    			}
		    		});//===============end trainer dialog===	
		    
	}
	
	//初始化添加页面中的驾照类型
	function initDriverType(object,initData,required){
		var url= '${pageContext.request.contextPath}/demo/json/driverType_combobox.json';
		initComboboxData(object,initData,url,required);
	}
	
	//初始化添加页面中的驾照类型，预约类型参数
	function initReservationTypeParams(comboObject,initData){
		var url='${pageContext.request.contextPath}/demo/json/timingUsingType_combobox.json';
		initComboboxData(comboObject,initData,url,true);
	}
	
	//3.展现教练预约明细信息
	function initReservationDetailDatagrid(addDialog,trainerId,studentNoEditer,studentNo,studentName,defaultSelectRow,workingTypeVal,studentIdVal){
		var datagridId=addDialog.find('table[id=demo_detailReservation_datagrid]');
		var myForm=addDialog.find('form');
		var studentIdValTemp=myForm.find('input[name=studentId]');
		var trainerIdValTemp=myForm.find('input[name=trainerId]');
		var myReservationType=myForm.find('input[name=selectReservationType]');
		reservationDetailDatagrid=datagridId.datagrid({
			url:'${pageContext.request.contextPath}/demo/reservationAction!getDetailReservationByTrainerId.action',
			queryParams:{detailTrainerId:trainerId,studentId:studentIdValTemp.val(),reservationType:myReservationType.val()},
			title : '当前教练预约明细信息管理列表!', /* 表头标题 */
			iconCls : 'icon-save', /* 标题前边的图标 */
			pagination : true, /* 是否显示页码 */
			pagePosition : 'bottom', /* 页码显示的位置 */
			pageSize : 30, /* 每面显示的记录数 */
			pageList : [ 10, 20, 30, 40,50 ,100,200 ], /* 选择每面显示的记录数 */
			fit : true,
			fitColumns : false, //列自动调整功能
			nowrap : false, //以行的形式进行显示
			border : false,
			rownumbers: true,
			idField : 'reservationDetailId', //此属性一定要配置
			sortName : 'reservationDate', //列排序
			sortOrder : 'desc', //列排序
			/* checkOnSelect : false,
			selectOnCheck : true, */
			striped : true,//表示条纹，隔行显示不现背景色
			singleSelect:true,
			
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
				width:90,
				align : 'center',
				formatter:formatTimeYMD,
			},
			 ] ],
			columns:[[{
						title:'7:00-8:00',
						width:140,
						align:'center',
						colspan:2,
					},{
						title:'8:00-9:00',
						width:140,
						align:'center',
						colspan:2,
					},{
						title:'9:00-10:00',
						align:'center',
						colspan:2,
					},{
						title:'10:00-11:00',
						align:'center',
						colspan:2,
					},{
						title:'11:00-12:00',
						align:'center',
						colspan:2,
					},{
						title:'12:00-13:00',
						align:'center',
						colspan:2,
					},{
						title:'13:00-14:00',
						align:'center',
						colspan:2,
					},{
						title:'14:00-15:00',
						align:'center',
						colspan:2,
					},{
						title:'15:00-16:00',
						align:'center',
						colspan:2,
					},{
						title:'16:00-17:00',
						align:'center',
						colspan:2,
					},{
						title:'17:00-18:00',
						align:'center',
						colspan:2,
					},{
						title:'18:00-19:00',
						align:'center',
						colspan:2,
					},{
						title:'19:00-20:00',
						align:'center',
						colspan:2,
					},{
						title:'20:00-21:00',
						align:'center',
						colspan:2,
					},{
						title:'21:00-22:00',
						align:'center',
						colspan:2,
					},{
						title:'五项总数',
						field:'fiveItemTotal',
						width:70,
						align:'center',
						rowspan:2,
					},{
						title:'路考总数',
						field:'raodExamTotal',
						width:70,
						align:'center',
						rowspan:2,
					},{
						title:'教练编号',
						field:'trainerId',
						width:260,
						rowspan:2,
					},{
						title:'教练预约编号',
						field:'trainerReservationId',
						width:260,
						rowspan:2,
					},{
						title:'学员预约标识',
						field:'reservationFieldId7',
						width:260,
						rowspan:2,
					},{
						title:'学员预约标识',
						field:'reservationFieldId8',
						width:260,
						rowspan:2,
					},{
						title:'学员预约标识',
						field:'reservationFieldId9',
						width:260,
						rowspan:2,
					},{
						title:'学员预约标识',
						field:'reservationFieldId10',
						width:260,
						rowspan:2,
					},{
						title:'学员预约标识',
						field:'reservationFieldId11',
						width:260,
						rowspan:2,
					},{
						title:'学员预约标识',
						field:'reservationFieldId12',
						width:260,
						rowspan:2,
					},{
						title:'学员预约标识',
						field:'reservationFieldId13',
						width:260,
						rowspan:2,
					},{
						title:'学员预约标识',
						field:'reservationFieldId14',
						width:260,
						rowspan:2,
					},{
						title:'学员预约标识',
						field:'reservationFieldId15',
						width:260,
						rowspan:2,
					},{
						title:'学员预约标识',
						field:'reservationFieldId16',
						width:260,
						rowspan:2,
					},{
						title:'学员预约标识',
						field:'reservationFieldId17',
						width:260,
						rowspan:2,
					},{
						title:'学员预约标识',
						field:'reservationFieldId18',
						width:260,
						rowspan:2,
					},{
						title:'学员预约标识',
						field:'reservationFieldId19',
						width:260,
						rowspan:2,
					},{
						title:'学员预约标识',
						field:'reservationFieldId20',
						width:260,
						rowspan:2,
					},{
						title:'学员预约标识',
						field:'reservationFieldId21',
						width:260,
						rowspan:2,
					},{
						title:'学员预约标识',
						field:'reservation7MaxTotal',
						width:10,
						rowspan:2,
					},{
						title:'学员预约标识',
						field:'reservation8MaxTotal',
						width:10,
						rowspan:2,
					},{
						title:'学员预约标识',
						field:'reservation9MaxTotal',
						width:10,
						rowspan:2,
					},{
						title:'学员预约标识',
						field:'reservation10MaxTotal',
						width:10,
						rowspan:2,
					},{
						title:'学员预约标识',
						field:'reservation11MaxTotal',
						width:10,
						rowspan:2,
					},{
						title:'学员预约标识',
						field:'reservation12MaxTotal',
						width:10,
						rowspan:2,
					},{
						title:'学员预约标识',
						field:'reservation13MaxTotal',
						width:10,
						rowspan:2,
					},{
						title:'学员预约标识',
						field:'reservation14MaxTotal',
						width:10,
						rowspan:2,
					},{
						title:'学员预约标识',
						field:'reservation15MaxTotal',
						width:10,
						rowspan:2,
					},{
						title:'学员预约标识',
						field:'reservation16MaxTotal',
						width:10,
						rowspan:2
					},{
						title:'学员预约标识',
						field:'reservation17MaxTotal',
						width:10,
						rowspan:2,
					},{
						title:'学员预约标识',
						field:'reservation18MaxTotal',
						width:10,
						rowspan:2,
					},{
						title:'学员预约标识',
						field:'reservation19MaxTotal',
						width:10,
						rowspan:2,
					},{
						title:'学员预约标识',
						field:'reservation20MaxTotal',
						width:10,
						rowspan:2,
					},{
						title:'学员预约标识',
						field:'reservation21MaxTotal',
						width:10,
						rowspan:2,
					}
					],[{
						title:'学员编号',
						field:'item7Studentid',
						width:120,
						align:'center',
						editor:studentNoEditer,
						formatter:formatNoArrange,
					},{
						title:'预约类型',
						field:'item7TrainContent',
						width:60,
						align:'center',
						editor:trainingTypeEditor,
						formatter:formatterTimingUsingType,
					},{
						title:'学员编号',
						field:'item8Studentid',
						width:120,
						align:'center',
						editor:studentNoEditer,
						formatter:formatNoArrange,
					},{
						title:'预约类型',
						field:'item8TrainContent',
						width:60,
						align:'center',
						editor:trainingTypeEditor,
						formatter:formatterTimingUsingType,
					},{
						title:'学员编号',
						field:'item9Studentid',
						width:120,
						align:'center',
						editor:studentNoEditer,
						formatter:formatNoArrange,
					},{
						title:'预约类型',
						field:'item9TrainContent',
						width:60,
						align:'center',
						editor:trainingTypeEditor,
						formatter:formatterTimingUsingType,
					},{
						title:'学员编号',
						field:'item10Studentid',
						width:120,
						align:'center',
						editor:studentNoEditer,
						formatter:formatNoArrange
					},{
						title:'预约类型',
						field:'item10TrainContent',
						width:60,
						align:'center',
						editor:trainingTypeEditor,
						formatter:formatterTimingUsingType,
					},{
						title:'学员编号',
						field:'item11Studentid',
						width:120,
						align:'center',
						editor:studentNoEditer,
						formatter:formatNoArrange,
					},{
						title:'预约类型',
						field:'item11TrainContent',
						width:60,
						align:'center',
						editor:trainingTypeEditor,
						formatter:formatterTimingUsingType,
					},{
						title:'学员编号',
						field:'item12Studentid',
						width:120,
						align:'center',
						editor:studentNoEditer,
						formatter:formatNoArrange,
					},{
						title:'预约类型',
						field:'item12TrainContent',
						width:60,
						align:'center',
						editor:trainingTypeEditor,
						formatter:formatterTimingUsingType,
					},{
						title:'学员编号',
						field:'item13Studentid',
						width:120,
						align:'center',
						editor:studentNoEditer,
						formatter:formatNoArrange,
					},{
						title:'预约类型',
						field:'item13TrainContent',
						width:60,
						align:'center',
						editor:trainingTypeEditor,
						formatter:formatterTimingUsingType,
					},{
						title:'学员编号',
						field:'item14Studentid',
						width:120,
						align:'center',
						editor:studentNoEditer,
						formatter:formatNoArrange,
					},{
						title:'预约类型',
						field:'item14TrainContent',
						width:60,
						align:'center',
						editor:trainingTypeEditor,
						formatter:formatterTimingUsingType,
					},{
						title:'学员编号',
						field:'item15Studentid',
						width:120,
						align:'center',
						editor:studentNoEditer,
						formatter:formatNoArrange,
					},{
						title:'预约类型',
						field:'item15TrainContent',
						width:60,
						align:'center',
						editor:trainingTypeEditor,
						formatter:formatterTimingUsingType,
					},{
						title:'学员编号',
						field:'item16Studentid',
						width:120,
						align:'center',
						editor:studentNoEditer,
						formatter:formatNoArrange,
					},{
						title:'预约类型',
						field:'item16TrainContent',
						width:60,
						align:'center',
						editor:trainingTypeEditor,
						formatter:formatterTimingUsingType,
					},{
						title:'学员编号',
						field:'item17Studentid',
						width:120,
						align:'center',
						editor:studentNoEditer,
						formatter:formatNoArrange,
					},{
						title:'预约类型',
						field:'item17TrainContent',
						width:60,
						align:'center',
						editor:trainingTypeEditor,
						formatter:formatterTimingUsingType,
					},{
						title:'学员编号',
						field:'item18Studentid',
						width:120,
						align:'center',
						editor:studentNoEditer,
						formatter:formatNoArrange,
					},{
						title:'预约类型',
						field:'item18TrainContent',
						width:60,
						align:'center',
						editor:trainingTypeEditor,
						formatter:formatterTimingUsingType,
					},{
						title:'学员编号',
						field:'item19Studentid',
						width:120,
						align:'center',
						editor:studentNoEditer,
						formatter:formatNoArrange,
					},{
						title:'预约类型',
						field:'item19TrainContent',
						width:60,
						align:'center',
						editor:trainingTypeEditor,
						formatter:formatterTimingUsingType,
					},{
						title:'学员编号',
						field:'item20Studentid',
						width:120,
						align:'center',
						editor:studentNoEditer,
						formatter:formatNoArrange,
					},{
						title:'预约类型',
						field:'item20TrainContent',
						width:60,
						align:'center',
						editor:trainingTypeEditor,
						formatter:formatterTimingUsingType,
					},{
						title:'学员编号',
						field:'item21Studentid',
						width:120,
						align:'center',
						editor:studentNoEditer,
						formatter:formatNoArrange,
					},{
						title:'预约类型',
						field:'item21TrainContent',
						width:60,
						align:'center',
						editor:trainingTypeEditor,
						formatter:formatterTimingUsingType,
					}
					]],
			//菜单功能设置
			toolbar : [{
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
					addReservationFlag=true;
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
		 	
         	onDblClickCell: function(index, field,value){
         		if(addReservationFlag){
         			var rows=reservationDetailDatagrid.datagrid('getSelections');
             		if(rows.length==1){
             			
             			//2.限制学员在一天内只能预约两个小时
                		$.ajax({
                			url : '${pageContext.request.contextPath}/demo/reservationAction!checkTotalByDate.action',
                			data:{reservationStudentId:studentIdValTemp.val(),date:rows[0].reservationDate,reservationType:myReservationType.val()},
                			dataType : 'json',
                			success : function(d) {
                				if(d.success){
                					parent.sy.messagerAlert('提示信息', '当前学员在当前选择日期的预约数量已经达到上限!', 'question');
                					mydatagrid.datagrid('reload');
                					reservationDetailDatagrid.datagrid('reload');
                					reservationDetailDatagrid.datagrid('unselectAll');
                					return;
                				}else{
                					onMyClickCell(index, field,value,addDialog,studentNo,studentName,studentIdValTemp.val(),trainerIdValTemp.val(),myReservationType.val());
                				}
                			}
                		});
             		}
         		}
			},
		});//end datagrid
		
	}//end initReservationDetailDatagrid
	
    
    var editIndex = undefined;//当前编辑行的行标
	//单击单元格事件函数(用来记录预约单击那个单元格进行操作)
	var rowsIndex = undefined;//选择单元格的行索引
	function onMyClickCell(index, field,value,addDialog,studentNo,studentName,studentIdVal,trainerId) {
		addReservationFlag=false;
		var rows = reservationDetailDatagrid.datagrid('getSelections');
		var rowTemp7=rows[0].reservationFieldId7;
		var rowTemp8=rows[0].reservationFieldId8;
		var rowTemp9=rows[0].reservationFieldId9;
		var rowTemp10=rows[0].reservationFieldId10;
		var rowTemp11=rows[0].reservationFieldId11;
		var rowTemp12=rows[0].reservationFieldId12;
		var rowTemp13=rows[0].reservationFieldId13;
		var rowTemp14=rows[0].reservationFieldId14;
		var rowTemp15=rows[0].reservationFieldId15;
		var rowTemp16=rows[0].reservationFieldId16;
		var rowTemp17=rows[0].reservationFieldId17;
		var rowTemp18=rows[0].reservationFieldId18;
		var rowTemp19=rows[0].reservationFieldId19;
		var rowTemp20=rows[0].reservationFieldId20;
		var rowTemp21=rows[0].reservationFieldId21;
		
		//用来判断当前field是否已经存在预约,ID值为空表示没有预约,如果不为空则不允许预约操作
		var studentId7Temp=rows[0].item7Studentid;
		var studentId8Temp=rows[0].item8Studentid;
		var studentId9Temp=rows[0].item9Studentid;
		var studentId10Temp=rows[0].item10Studentid;
		var studentId11Temp=rows[0].item11Studentid;
		var studentId12Temp=rows[0].item12Studentid;
		var studentId13Temp=rows[0].item13Studentid;
		var studentId14Temp=rows[0].item14Studentid;
		var studentId15Temp=rows[0].item15Studentid;
		var studentId16Temp=rows[0].item16Studentid;
		var studentId17Temp=rows[0].item17Studentid;
		var studentId18Temp=rows[0].item18Studentid;
		var studentId19Temp=rows[0].item19Studentid;
		var studentId20Temp=rows[0].item20Studentid;
		var studentId21Temp=rows[0].item21Studentid;
		
		var reservationDateTemp=rows[0].reservationDate;
		
		rowsIndex = index;
		if (endEditing()) {
			reservationDetailDatagrid.datagrid('selectRow', index).datagrid(
					'editCell', {
						index : index,
						field : field,
						value:value,
					});
			editIndex = index;
			//标记预约操作预约时段
			if(field=='item7Studentid'){
				timeItemClikcFlag=7;//标记预约当前选择8：00－9：00时段
				//预约选择学员编号，取消事件　
				onDbclickCellStudentId(addDialog,index,'item7Studentid',rowTemp7,workingTypeVal,studentId7Temp,reservationDateTemp,studentIdVal,trainerId);
			}else if(field=='item8Studentid'){
				timeItemClikcFlag=8;
				onDbclickCellStudentId(addDialog,index,'item8Studentid',rowTemp8,workingTypeVal,studentId8Temp,reservationDateTemp,studentIdVal,trainerId);
			}else if(field=='item9Studentid'){
				timeItemClikcFlag=9;
				onDbclickCellStudentId(addDialog,index,'item9Studentid',rowTemp9,workingTypeVal,studentId9Temp,reservationDateTemp,studentIdVal,trainerId);
			}else if(field=='item10Studentid'){
				timeItemClikcFlag=10;
				onDbclickCellStudentId(addDialog,index,'item10Studentid',rowTemp10,workingTypeVal,studentId10Temp,reservationDateTemp,studentIdVal,trainerId);
			}else if(field=='item11Studentid'){
				timeItemClikcFlag=11;
				onDbclickCellStudentId(addDialog,index,'item11Studentid',rowTemp11,workingTypeVal,studentId11Temp,reservationDateTemp,studentIdVal,trainerId);
			}else if(field=='item12Studentid'){
				timeItemClikcFlag=12;
				onDbclickCellStudentId(addDialog,index,'item12Studentid',rowTemp12,workingTypeVal,studentId12Temp,reservationDateTemp,studentIdVal,trainerId);
			}else if(field=='item13Studentid'){
				timeItemClikcFlag=13;
				onDbclickCellStudentId(addDialog,index,'item13Studentid',rowTemp13,workingTypeVal,studentId13Temp,reservationDateTemp,studentIdVal,trainerId);
			}else if(field=='item14Studentid'){
				timeItemClikcFlag=14;
				onDbclickCellStudentId(addDialog,index,'item14Studentid',rowTemp14,workingTypeVal,studentId14Temp,reservationDateTemp,studentIdVal,trainerId);
			}else if(field=='item15Studentid'){
				timeItemClikcFlag=15;
				onDbclickCellStudentId(addDialog,index,'item15Studentid',rowTemp15,workingTypeVal,studentId15Temp,reservationDateTemp,studentIdVal,trainerId);
			}else if(field=='item16Studentid'){
				timeItemClikcFlag=16;
				onDbclickCellStudentId(addDialog,index,'item16Studentid',rowTemp16,workingTypeVal,studentId16Temp,reservationDateTemp,studentIdVal,trainerId);
			}else if(field=='item17Studentid'){
				timeItemClikcFlag=17;
				onDbclickCellStudentId(addDialog,index,'item17Studentid',rowTemp17,workingTypeVal,studentId17Temp,reservationDateTemp,studentIdVal,trainerId);
			}else if(field=='item18Studentid'){
				timeItemClikcFlag=18;
				onDbclickCellStudentId(addDialog,index,'item18Studentid',rowTemp18,workingTypeVal,studentId18Temp,reservationDateTemp,studentIdVal,trainerId);
			}else if(field=='item19Studentid'){
				timeItemClikcFlag=19;
				onDbclickCellStudentId(addDialog,index,'item19Studentid',rowTemp19,workingTypeVal,studentId19Temp,reservationDateTemp,studentIdVal,trainerId);
			}else if(field=='item20Studentid'){
				timeItemClikcFlag=20;
				onDbclickCellStudentId(addDialog,index,'item20Studentid',rowTemp20,workingTypeVal,studentId20Temp,reservationDateTemp,studentIdVal,trainerId);
			}else if(field=='item21Studentid'){
				timeItemClikcFlag=21;
				onDbclickCellStudentId(addDialog,index,'item21Studentid',rowTemp21,workingTypeVal,studentId21Temp,reservationDateTemp,studentIdVal,trainerId);
			}
			if(field=='item7TrainContent'||field=='item8TrainContent'||field=='item9TrainContent'||field=='item10TrainContent'||field=='item11TrainContent'||field=='item12TrainContent'
				||field=='item12TrainContent'||field=='item13TrainContent'||field=='item14TrainContent'||field=='item15TrainContent'||field=='item16TrainContent'
				||field=='item17TrainContent'||field=='item18TrainContent'||field=='item19TrainContent'||field=='item20TrainContent'||field=='item21TrainContent'){
				var editorTemp=reservationDetailDatagrid.datagrid('getEditor',{index:index,field:field});
				var myEditor=editorTemp.target;
				myEditor.combobox({
					onChange:function(){
						reservationDetailDatagrid.datagrid('endEdit',index);
						addReservationFlag=true;
					}
				});
			}
		}
		
	}
	
	//预约双击“学员标识”单元格后函数
	//studentIdTemp由用预约前所选择学员的ID
	function onDbclickCellStudentId(addDialog,index,field,reservationId,workingTypeVal,studentIdFieldVal,reservationDateTemp,studentIdVal,trainerId){
		var f=addDialog.find('form');
		var reservationTypeVal=f.find('input[name=selectReservationType]');
		//1.禁止预约双击不排班字段
		if(!validateIsNull(studentIdFieldVal)){
			if(studentIdFieldVal=="NO"){
				parent.sy.messagerAlert('提示信息', '该时段不允许预约！', 'error');
				reservationDetailDatagrid.datagrid('reload');
				return;
			}else{
				parent.sy.messagerAlert('提示信息', '该时段不允许预约！', 'error');
				reservationDetailDatagrid.datagrid('reload');
			}
		}
		
		var ed=reservationDetailDatagrid.datagrid('getEditor',{index:index,field:field});
		var myEd=ed.target;
		myEd.combobox({
			onChange:function(){
				//预约选择取消操作时响应事件
				if(myEd.combobox('getValue')==1){
					//预约选择取消时，异步删除预约信息
					$.ajax({
						url : '${pageContext.request.contextPath}/demo/reservationAction!delete.action',
						data:{ids:reservationId},
						dataType : 'json',
						success : function(d) {
							mydatagrid.datagrid('reload');
							reservationDetailDatagrid.datagrid('reload');
						}
					});
				}else{
					//预约选择学员编号后添加预约事件
					saveOrupdateReservationDetail(addDialog,reservationTypeVal.val(),reservationDateTemp,studentIdVal,trainerId);
				}
				
			}
		});
	}
	
	function saveOrupdateReservationDetail(addDialog,workingTypeVal,reservationDateTemp,studentIdVal,trainerId){
        		var f=addDialog.find('form');
        		//初始化预约类型选项
        		var reserTypeTemp=undefined;
        		var timeStartTemp=undefined;
        		var timeEndTemp=undefined;
        		var fieldOptionFlagTemp=undefined;	//记录预约操作哪个时间段
        		
        		var item7StudentIdTemp=undefined;
        		var item7TrainContentTemp=undefined;
        		var item8StudentIdTemp=undefined;
        		var item8TrainContentTemp=undefined;
        		var item9StudentIdTemp=undefined;
        		var item9TrainContentTemp=undefined;
        		var item10StudentIdTemp=undefined;
        		var item10TrainContentTemp=undefined;
        		var item11StudentIdTemp=undefined;
        		var item11TrainContentTemp=undefined;
        		var item12StudentIdTemp=undefined;
        		var item12TrainContentTemp=undefined;
        		var item13StudentIdTemp=undefined;
        		var item13TrainContentTemp=undefined;
        		var item14StudentIdTemp=undefined;
        		var item14TrainContentTemp=undefined;
        		var item15StudentIdTemp=undefined;
        		var item15TrainContentTemp=undefined;
        		var item16StudentIdTemp=undefined;
        		var item16TrainContentTemp=undefined;
        		var item17StudentIdTemp=undefined;
        		var item17TrainContentTemp=undefined;
        		var item18StudentIdTemp=undefined;
        		var item18TrainContentTemp=undefined;
        		var item19StudentIdTemp=undefined;
        		var item19TrainContentTemp=undefined;
        		var item20StudentIdTemp=undefined;
        		var item20TrainContentTemp=undefined;
        		var item21StudentIdTemp=undefined;
        		var item21TrainContentTemp=undefined;
        		
        		if (endEditing()) {
        			var rows = reservationDetailDatagrid.datagrid('getSelections');
        			//编辑时只能单选一行进行编辑
        			if (rows.length == 1) {
        				//1.初始化学员预约选项内容
        				//1.1初始化预约时间
        		
        				//2.2根据预约在教练预约明细中操作的记录进行相关项初始化(目标：为reservation对象组织参数)
        				if(timeItemClikcFlag==7){
        					item7StudentIdTemp=rows[0].item7Studentid;
            				item7TrainContentTemp=rows[0].item7TrainContent;
        					//判断预约选择的时间段相应的预约类型是否与学员预约的类型相符合
        					if(workingTypeVal!=rows[0].item7TrainContent){
        						reservationTypeNotMatch=true;
        						parent.sy.messagerAlert('提示信息', '当前选择时间段预约类型与学员预约类型不相符！', 'question');
        						reservationDetailDatagrid.datagrid('reload');
        						return;
        					}
        					var temp7=getFirstPartString(rows[0].item7Studentid);
        					//教练明细表中可排班单元格操作值传递(该单元格表示教练排班)
        					if(!(temp7=="NO")&&timeItemClikcFlag==7){
        						fieldOptionFlagTemp='7';
        						reserTypeTemp=rows[0].item7TrainContent;
        						timeStartTemp="7:00";
        						timeEndTemp="8:00";
        						
        					}else{//（表示8：00-9：00时间段，存在约预，即該時段已经有学员学号信息)
        						item7StudentIdTemp=getFirstPartString(rows[0].item7Studentid);
        					}
        				}else if(timeItemClikcFlag==8){
        					item8StudentIdTemp=rows[0].item8Studentid;
            				item8TrainContentTemp=rows[0].item8TrainContent;
        					//判断预约选择的时间段相应的预约类型是否与学员预约的类型相符合
        					if(workingTypeVal!=rows[0].item8TrainContent){
        						reservationTypeNotMatch=true;
        						parent.sy.messagerAlert('提示信息', '当前选择时间段预约类型与学员预约类型不相符！', 'question');
        						reservationDetailDatagrid.datagrid('reload');
        						return;
        					}
        					var temp8=getFirstPartString(rows[0].item8Studentid);
        					//教练明细表中可排班单元格操作值传递(该单元格表示教练排班)
        					if(!(temp8=="NO")&&timeItemClikcFlag==8){
        						fieldOptionFlagTemp='8';
        						reserTypeTemp=rows[0].item8TrainContent;
        						timeStartTemp="8:00";
        						timeEndTemp="9:00";
        						
        					}else{//（表示8：00-9：00时间段，存在约预，即該時段已经有学员学号信息)
        						item8StudentIdTemp=getFirstPartString(rows[0].item8Studentid);
        					}
        				}else if(timeItemClikcFlag==9){
        					item9StudentIdTemp=rows[0].item9Studentid;
            				item9TrainContentTemp=rows[0].item9TrainContent;
        					//判断预约选择的时间段相应的预约类型是否与学员预约的类型相符合
        					if(workingTypeVal!=rows[0].item9TrainContent){
        						reservationTypeNotMatch=true;
        						parent.sy.messagerAlert('提示信息', '当前选择时间段预约类型与学员预约类型不相符！', 'question');
        						reservationDetailDatagrid.datagrid('reload');
        						return;
        					}
        					var temp9=getFirstPartString(rows[0].item9Studentid);
        					if(!(temp9=="NO")&&timeItemClikcFlag==9){
        						fieldOptionFlagTemp='9';
        						reserTypeTemp=rows[0].item9TrainContent;
        						
        						timeStartTemp="9:00";
        						timeEndTemp="10:00";
        					}else{
        						item9StudentIdTemp=getFirstPartString(rows[0].item9Studentid);
        					}
        				}else if(timeItemClikcFlag==10){
        					item10StudentIdTemp=rows[0].item10Studentid;
            				item10TrainContentTemp=rows[0].item10TrainContent;
            				
        					if(workingTypeVal!=rows[0].item10TrainContent){
        						reservationTypeNotMatch=true;
        						parent.sy.messagerAlert('提示信息', '当前选择时间段预约类型与学员预约类型不相符！', 'question');
        						reservationDetailDatagrid.datagrid('reload');
        						return;
        					}
        					var temp10=getFirstPartString(rows[0].item10Studentid);
        					if(!(temp10=="NO")&&timeItemClikcFlag==10){
        						fieldOptionFlagTemp='10';
        						reserTypeTemp=rows[0].item10TrainContent;
        						timeStartTemp="10:00";
        						timeEndTemp="11:00";
        					}else{
        						item10StudentIdTemp=getFirstPartString(rows[0].item10Studentid);
        					}
        				}else if(timeItemClikcFlag==11){
        					item11StudentIdTemp=rows[0].item11Studentid;
            				item11TrainContentTemp=rows[0].item11TrainContent;
        					
        					if(workingTypeVal!=rows[0].item11TrainContent){
        						reservationTypeNotMatch=true;
        						parent.sy.messagerAlert('提示信息', '当前选择时间段预约类型与学员预约类型不相符！', 'question');
        						reservationDetailDatagrid.datagrid('reload');
        						return;
        					}
        					var temp11=getFirstPartString(rows[0].item11Studentid);
        					//教练明细表中可排班单元格操作值传递(该单元格表示教练排班)
        					if(!(temp11=="NO")&&timeItemClikcFlag==11){
        						fieldOptionFlagTemp='11';
        						reserTypeTemp=rows[0].item11TrainContent;
        						timeStartTemp="11:00";
        						timeEndTemp="12:00";
        						
        					}else{//(该单元格表示教练不排班)
        						item11StudentIdTemp=getFirstPartString(rows[0].item11Studentid);
        					}
        				}else if(timeItemClikcFlag==12){
        					item12StudentIdTemp=rows[0].item12Studentid;
            				item12TrainContentTemp=rows[0].item12TrainContent;
        					
        					if(workingTypeVal!=rows[0].item12TrainContent){
        						reservationTypeNotMatch=true;
        						parent.sy.messagerAlert('提示信息', '当前选择时间段预约类型与学员预约类型不相符！', 'question');
        						reservationDetailDatagrid.datagrid('reload');
        						return;
        					}
        					var temp12=getFirstPartString(rows[0].item12Studentid);
        					if(!(temp12=="NO")&&timeItemClikcFlag==12){
        						fieldOptionFlagTemp='12';
        						
        						reserTypeTemp=rows[0].item12TrainContent;
        						timeStartTemp="12:00";
        						timeEndTemp="13:00";
        					}else{
        						item12StudentIdTemp=getFirstPartString(rows[0].item12Studentid);
        					}
        				}else if(timeItemClikcFlag==13){
        					item13StudentIdTemp=rows[0].item13Studentid;
            				item13TrainContentTemp=rows[0].item13TrainContent;
        					
        					if(workingTypeVal!=rows[0].item13TrainContent){
        						reservationTypeNotMatch=true;
        						parent.sy.messagerAlert('提示信息', '当前选择时间段预约类型与学员预约类型不相符！', 'question');
        						reservationDetailDatagrid.datagrid('reload');
        						return;
        					}
        					var temp13=getFirstPartString(rows[0].item13Studentid);
        					if(!(temp13=="NO")&&timeItemClikcFlag==13){
        						fieldOptionFlagTemp='13';
        						
        						reserTypeTemp=rows[0].item13TrainContent;
        						timeStartTemp="13:00";
        						timeEndTemp="14:00";
        					}else{
        						item13StudentIdTemp=getFirstPartString(rows[0].item13Studentid);
        					}
        				}else if(timeItemClikcFlag==14){
            				item14StudentIdTemp=rows[0].item14Studentid;
            				item14TrainContentTemp=rows[0].item14TrainContent;
        					
        					if(workingTypeVal!=rows[0].item14TrainContent){
        						reservationTypeNotMatch=true;
        						parent.sy.messagerAlert('提示信息', '当前选择时间段预约类型与学员预约类型不相符！', 'question');
        						reservationDetailDatagrid.datagrid('reload');
        						return;
        					}
        					var temp14=getFirstPartString(rows[0].item14Studentid);
        					if(!(temp14=="NO")&&timeItemClikcFlag==14){
        						fieldOptionFlagTemp='14';
        						
        						reserTypeTemp=rows[0].item14TrainContent;
        						timeStartTemp="14:00";
        						timeEndTemp="15:00";
        					}else{
        						item14StudentIdTemp=getFirstPartString(rows[0].item14Studentid);
        					}
        				}else if(timeItemClikcFlag==15){
        					item15StudentIdTemp=rows[0].item15Studentid;
            				item15TrainContentTemp=rows[0].item15TrainContent;
            				
        					if(workingTypeVal!=rows[0].item15TrainContent){
        						reservationTypeNotMatch=true;
        						parent.sy.messagerAlert('提示信息', '当前选择时间段预约类型与学员预约类型不相符！', 'question');
        						reservationDetailDatagrid.datagrid('reload');
        						return;
        					}
        					var temp15=getFirstPartString(rows[0].item15Studentid);
        					if(!(temp15=="NO")&&timeItemClikcFlag==15){
        						fieldOptionFlagTemp='15';
        					
        						reserTypeTemp=rows[0].item15TrainContent;
        						timeStartTemp="15:00";
        						timeEndTemp="16:00";
        					}else{
        						item15StudentIdTemp=getFirstPartString(rows[0].item15Studentid);
        					}
        				}else if(timeItemClikcFlag==16){
        					item16StudentIdTemp=rows[0].item16Studentid;
            				item16TrainContentTemp=rows[0].item16TrainContent;
            				
        					if(workingTypeVal!=rows[0].item16TrainContent){
        						reservationTypeNotMatch=true;
        						parent.sy.messagerAlert('提示信息', '当前选择时间段预约类型与学员预约类型不相符！', 'question');
        						reservationDetailDatagrid.datagrid('reload');
        						return;
        					}
        					var temp16=getFirstPartString(rows[0].item16Studentid);
        					if(!(temp16=="NO")&&timeItemClikcFlag==16){
        						fieldOptionFlagTemp='16';
        					
        						reserTypeTemp=rows[0].item16TrainContent;
        						timeStartTemp="16:00";
        						timeEndTemp="17:00";
        					}else{
        						item16StudentIdTemp=getFirstPartString(rows[0].item16Studentid);
        					}
        				}else if(timeItemClikcFlag==17){
        					item17StudentIdTemp=rows[0].item17Studentid;
            				item17TrainContentTemp=rows[0].item17TrainContent;
            				
        					if(workingTypeVal!=rows[0].item17TrainContent){
        						reservationTypeNotMatch=true;
        						parent.sy.messagerAlert('提示信息', '当前选择时间段预约类型与学员预约类型不相符！', 'question');
        						reservationDetailDatagrid.datagrid('reload');
        						return;
        					}
        					var temp17=getFirstPartString(rows[0].item17Studentid);
        					if(!(temp17=="NO")&&timeItemClikcFlag==17){
        						fieldOptionFlagTemp='17';
        						
        						reserTypeTemp=rows[0].item17TrainContent;
        						timeStartTemp="17:00";
        						timeEndTemp="18:00";
        					}else{
        						item17StudentIdTemp=getFirstPartString(rows[0].item17Studentid);
        					}
        				}else if(timeItemClikcFlag==18){
        					item18StudentIdTemp=rows[0].item18Studentid;
            				item18TrainContentTemp=rows[0].item18TrainContent;
        					if(workingTypeVal!=rows[0].item18TrainContent){
        						reservationTypeNotMatch=true;
        						parent.sy.messagerAlert('提示信息', '当前选择时间段预约类型与学员预约类型不相符！', 'question');
        						reservationDetailDatagrid.datagrid('reload');
        						return;
        					}
        					var temp18=getFirstPartString(rows[0].item18Studentid);
        					if(!(temp18=="NO")&&timeItemClikcFlag==18){
        						fieldOptionFlagTemp='18';
        						
        						reserTypeTemp=rows[0].item18TrainContent;
        						timeStartTemp="18:00";
        						timeEndTemp="19:00";
        					}else{
        						item18StudentIdTemp=getFirstPartString(rows[0].item18Studentid);
        					}
        				}else if(timeItemClikcFlag==19){
        					item19StudentIdTemp=rows[0].item19Studentid;
            				item19TrainContentTemp=rows[0].item19TrainContent;
        					if(workingTypeVal!=rows[0].item19TrainContent){
        						reservationTypeNotMatch=true;
        						parent.sy.messagerAlert('提示信息', '当前选择时间段预约类型与学员预约类型不相符！', 'question');
        						reservationDetailDatagrid.datagrid('reload');
        						return;
        					}
        					var temp19=getFirstPartString(rows[0].item19Studentid);
        					if(!(temp19=="NO")&&timeItemClikcFlag==19){
        						fieldOptionFlagTemp='19';
        						
        						reserTypeTemp=rows[0].item19TrainContent;
        						timeStartTemp="19:00";
        						timeEndTemp="20:00";
        					}else{
        						item19StudentIdTemp=getFirstPartString(rows[0].item19Studentid);
        					}
        				}else if(timeItemClikcFlag==20){
        					item20StudentIdTemp=rows[0].item20Studentid;
            				item20TrainContentTemp=rows[0].item20TrainContent;
        					if(workingTypeVal!=rows[0].item20TrainContent){
        						reservationTypeNotMatch=true;
        						parent.sy.messagerAlert('提示信息', '当前选择时间段预约类型与学员预约类型不相符！', 'question');
        						reservationDetailDatagrid.datagrid('reload');
        						return;
        					}
        					var temp20=getFirstPartString(rows[0].item20Studentid);
        					if(!(temp20=="NO")&&timeItemClikcFlag==20){
        						fieldOptionFlagTemp='20';
        						
        						reserTypeTemp=rows[0].item20TrainContent;
        						timeStartTemp="20:00";
        						timeEndTemp="21:00";
        					}else{
        						item20StudentIdTemp=getFirstPartString(rows[0].item20Studentid);
        					}
        				}else if(timeItemClikcFlag==21){
        					item21StudentIdTemp=rows[0].item21Studentid;
            				item21TrainContentTemp=rows[0].item21TrainContent;
        					if(workingTypeVal!=rows[0].item21TrainContent){
        						reservationTypeNotMatch=true;
        						parent.sy.messagerAlert('提示信息', '当前选择时间段预约类型与学员预约类型不相符！', 'question');
        						reservationDetailDatagrid.datagrid('reload');
        						return;
        					}
        					var temp21=getFirstPartString(rows[0].item21Studentid);
        					if(!(temp21=="NO")&&timeItemClikcFlag==21){
        						fieldOptionFlagTemp='21';
        						
        						reserTypeTemp=rows[0].item21TrainContent;
        						timeStartTemp="21:00";
        						timeEndTemp="22:00";
        					}else{
        						item21StudentIdTemp=getFirstPartString(rows[0].item21Studentid);
        					}
        				}
        				
        				 var studentId=f.find('input[name=studentId]');
        				 var studentDriverType=f.find('input[name=selectDriverType]');
        				 var carIdVal=undefined;
        				 if(validateIsNotNull(studentId.val())){
        					//预约为C2则获取车辆标识
							 if(7==studentDriverType.val()){
								 if(1==reserTypeTemp||2==reserTypeTemp){
									 addReservation(studentId.val(),trainerId,reservationDateTemp,rows[0].reservationDetailId,rows[0].trainerReservationId,
			        							carIdVal,fieldOptionFlagTemp,reserTypeTemp,studentDriverType.val(),timeStartTemp,timeEndTemp,
			        							item7StudentIdTemp,item8StudentIdTemp,item9StudentIdTemp,item10StudentIdTemp,item11StudentIdTemp,item12StudentIdTemp,
			        							item13StudentIdTemp,item14StudentIdTemp,item15StudentIdTemp,item16StudentIdTemp,item17StudentIdTemp,
			        							item18StudentIdTemp,item19StudentIdTemp,item20StudentIdTemp,item21StudentIdTemp,item7TrainContentTemp,item8TrainContentTemp,
			        							item9TrainContentTemp,item10TrainContentTemp,item11TrainContentTemp,item12TrainContentTemp,item13TrainContentTemp,
			        							item14TrainContentTemp,item15TrainContentTemp,item16TrainContentTemp,item17TrainContentTemp,item18TrainContentTemp,
			        							item19TrainContentTemp,item20TrainContentTemp,item21TrainContentTemp,
			        							rows[0].reservation7MaxTotal,rows[0].reservation8MaxTotal,rows[0].reservation9MaxTotal,rows[0].reservation10MaxTotal,rows[0].reservation11MaxTotal,rows[0].reservation12MaxTotal,
			        							rows[0].reservation13MaxTotal,rows[0].reservation14MaxTotal,rows[0].reservation15MaxTotal,rows[0].reservation16MaxTotal,
			        							rows[0].reservation17MaxTotal,rows[0].reservation18MaxTotal,rows[0].reservation19MaxTotal,rows[0].reservation20MaxTotal,
			        							rows[0].reservation21MaxTotal);
								 }else{
									//判断是否有车辆可用
									 $.ajax({
				    				    	url : '${pageContext.request.contextPath}/demo/reservationAction!checkAutocarFree.action',
				    				    	data:{date:reservationDateTemp,fieldOptionFlag:fieldOptionFlagTemp,},
				    						dataType : 'json',
				    						success : function(d) {
				    							if(d){
				    								selectAutocarDialog=parent.sy.dialog({
														title : '选择可预约车辆',
														href : '${pageContext.request.contextPath}/demo/reservationAction!toSelectAutocarPage.action',
														width : 400,
														height : 300,
														buttons : [
																{
																	text : '关闭',
																	iconCls : 'icon-cancel',
																	handler : function() {
																		reservationDetailDatagrid.datagrid('reload');
																		selectAutocarDialog.dialog('close');
																	}
																} ],
																//通过onLoad函数实现数据回显
																onLoad : function() {
																	var myForm=selectAutocarDialog.find('form');
																	var carIdTemp=myForm.find('input[name=carId]');
																	var myurl='${pageContext.request.contextPath}/demo/reservationAction!getAutocarForReservation.action';
																	carIdTemp.combobox({
																		url:myurl,
																		queryParams:{date:reservationDateTemp,fieldOptionFlag:fieldOptionFlagTemp,},
																		valueField : 'value',
																		textField : 'text',
																		method : 'get',
																		editable : false,
																		required:true,
																		onChange:function(){
																			carIdVal=carIdTemp.combobox('getValue');
																			if(validateIsNotNull(carIdVal)){
																				addReservation(studentId.val(),trainerId,reservationDateTemp,rows[0].reservationDetailId,rows[0].trainerReservationId,
													        							carIdVal,fieldOptionFlagTemp,reserTypeTemp,studentDriverType.val(),timeStartTemp,timeEndTemp,
													        							item7StudentIdTemp,item8StudentIdTemp,item9StudentIdTemp,item10StudentIdTemp,item11StudentIdTemp,item12StudentIdTemp,
													        							item13StudentIdTemp,item14StudentIdTemp,item15StudentIdTemp,item16StudentIdTemp,item17StudentIdTemp,
													        							item18StudentIdTemp,item19StudentIdTemp,item20StudentIdTemp,item21StudentIdTemp,item7TrainContentTemp,item8TrainContentTemp,
													        							item9TrainContentTemp,item10TrainContentTemp,item11TrainContentTemp,item12TrainContentTemp,item13TrainContentTemp,
													        							item14TrainContentTemp,item15TrainContentTemp,item16TrainContentTemp,item17TrainContentTemp,item18TrainContentTemp,
													        							item19TrainContentTemp,item20TrainContentTemp,item21TrainContentTemp,
													        							rows[0].reservation7MaxTotal,rows[0].reservation8MaxTotal,rows[0].reservation9MaxTotal,rows[0].reservation10MaxTotal,rows[0].reservation11MaxTotal,rows[0].reservation12MaxTotal,
													        							rows[0].reservation13MaxTotal,rows[0].reservation14MaxTotal,rows[0].reservation15MaxTotal,rows[0].reservation16MaxTotal,
													        							rows[0].reservation17MaxTotal,rows[0].reservation18MaxTotal,rows[0].reservation19MaxTotal,rows[0].reservation20MaxTotal,
													        							rows[0].reservation21MaxTotal);
																				
																				selectAutocarDialog.dialog('close');
																			}else{
																				parent.sy.messagerAlert('提示信息', '请选择预约车辆！', 'question');
																			}
																			
																		} 
																	});
																},
													});
				    							}else{
				    								parent.sy.messagerAlert('提示信息', '自动档车辆在该时段已约满！', 'question');
				    								autocarFlag=false;
		   											reservationDetailDatagrid.datagrid('reload');
				    							}
				    						},
				    						errer:function(){
				    							parent.sy.messagerAlert('提示信息', '获取自动档车辆信息失败！', 'question');
				    						}
			    				    	});
								 }
							 }else{
								 //非C2业务直接添加预约(不需要选择车辆)
		        					addReservation(studentId.val(),trainerId,reservationDateTemp,rows[0].reservationDetailId,rows[0].trainerReservationId,
		        							carIdVal,fieldOptionFlagTemp,reserTypeTemp,studentDriverType.val(),timeStartTemp,timeEndTemp,
		        							item7StudentIdTemp,item8StudentIdTemp,item9StudentIdTemp,item10StudentIdTemp,item11StudentIdTemp,item12StudentIdTemp,
		        							item13StudentIdTemp,item14StudentIdTemp,item15StudentIdTemp,item16StudentIdTemp,item17StudentIdTemp,
		        							item18StudentIdTemp,item19StudentIdTemp,item20StudentIdTemp,item21StudentIdTemp,item7TrainContentTemp,item8TrainContentTemp,
		        							item9TrainContentTemp,item10TrainContentTemp,item11TrainContentTemp,item12TrainContentTemp,item13TrainContentTemp,
		        							item14TrainContentTemp,item15TrainContentTemp,item16TrainContentTemp,item17TrainContentTemp,item18TrainContentTemp,
		        							item19TrainContentTemp,item20TrainContentTemp,item21TrainContentTemp,
		        							rows[0].reservation7MaxTotal,rows[0].reservation8MaxTotal,rows[0].reservation9MaxTotal,rows[0].reservation10MaxTotal,rows[0].reservation11MaxTotal,rows[0].reservation12MaxTotal,
		        							rows[0].reservation13MaxTotal,rows[0].reservation14MaxTotal,rows[0].reservation15MaxTotal,rows[0].reservation16MaxTotal,
		        							rows[0].reservation17MaxTotal,rows[0].reservation18MaxTotal,rows[0].reservation19MaxTotal,rows[0].reservation20MaxTotal,
		        							rows[0].reservation21MaxTotal);
							 }
							 
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
	
	//添加预约信息
	function addReservation(studentIdRe,trainerId,reservationDateTemp,reservationDetailId,trainerReservationId,
			carIdVal,fieldOptionFlagTemp,reserTypeTemp,studentDriverTypeRe,timeStartTemp,timeEndTemp,
			item7StudentIdTemp,item8StudentIdTemp,item9StudentIdTemp,item10StudentIdTemp,item11StudentIdTemp,item12StudentIdTemp,
			item13StudentIdTemp,item14StudentIdTemp,item15StudentIdTemp,item16StudentIdTemp,item17StudentIdTemp,
			item18StudentIdTemp,item19StudentIdTemp,item20StudentIdTemp,item21StudentIdTemp,item7TrainContentTemp,item8TrainContentTemp,
			item9TrainContentTemp,item10TrainContentTemp,item11TrainContentTemp,item12TrainContentTemp,item13TrainContentTemp,
			item14TrainContentTemp,item15TrainContentTemp,item16TrainContentTemp,item17TrainContentTemp,item18TrainContentTemp,
			item19TrainContentTemp,item20TrainContentTemp,item21TrainContentTemp,reservation7MaxTotal,reservation8MaxTotal,
			reservation9MaxTotal,reservation10MaxTotal,reservation11MaxTotal,reservation12MaxTotal,
			reservation13MaxTotal,reservation14MaxTotal,reservation15MaxTotal,reservation16MaxTotal,
			reservation17MaxTotal,reservation18MaxTotal,reservation19MaxTotal,reservation20MaxTotal,
			reservation21MaxTotal){
			 $.ajax({
					url : '${pageContext.request.contextPath}/demo/reservationAction!add.action',
					data:{	studentId:studentIdRe,
							trainerId:trainerId,
							date:reservationDateTemp,
							reservationDetailId:reservationDetailId,
  						trainerReservationId:trainerReservationId,
  						carId:carIdVal,
	        				
	        				fieldOptionFlag:fieldOptionFlagTemp,
	        				reservationType:reserTypeTemp,
	        				selectDriverType:studentDriverTypeRe,
	        				timeStart:timeStartTemp,
	        				timeEnd:timeEndTemp,
	        				item7Studentid:item7StudentIdTemp,
	        				item8Studentid:item8StudentIdTemp,
	        				item9Studentid:item9StudentIdTemp,
	        				item10Studentid:item10StudentIdTemp,
	        				item11Studentid:item11StudentIdTemp,
	        				item12Studentid:item12StudentIdTemp,
	        				item13Studentid:item13StudentIdTemp,
	        				item14Studentid:item14StudentIdTemp,
	        				item15Studentid:item15StudentIdTemp,
	        				item16Studentid:item16StudentIdTemp,
	        				item17Studentid:item17StudentIdTemp,
	        				item18Studentid:item18StudentIdTemp,
	        				item19Studentid:item19StudentIdTemp,
	        				item20Studentid:item20StudentIdTemp,
	        				item21Studentid:item21StudentIdTemp,
	        				item7TrainContent:item7TrainContentTemp,
	        				item8TrainContent:item8TrainContentTemp,
	        				item9TrainContent:item9TrainContentTemp,
	        				item10TrainContent:item10TrainContentTemp,
	        				item11TrainContent:item11TrainContentTemp,
	        				item12TrainContent:item12TrainContentTemp,
	        				item13TrainContent:item13TrainContentTemp,
	        				item14TrainContent:item14TrainContentTemp,
	        				item15TrainContent:item15TrainContentTemp,
	        				item16TrainContent:item16TrainContentTemp,
	        				item17TrainContent:item17TrainContentTemp,
	        				item18TrainContent:item18TrainContentTemp,
	        				item19TrainContent:item19TrainContentTemp,
	        				item20TrainContent:item20TrainContentTemp,
	        				item21TrainContent:item21TrainContentTemp,
	        				
	        				reservation7MaxTotal:reservation7MaxTotal,
	        				reservation8MaxTotal:reservation8MaxTotal,
	        				reservation9MaxTotal:reservation9MaxTotal,
	        				reservation10MaxTotal:reservation10MaxTotal,
	        				reservation11MaxTotal:reservation11MaxTotal,
	        				reservation12MaxTotal:reservation12MaxTotal,
	        				reservation13MaxTotal:reservation13MaxTotal,
	        				reservation14MaxTotal:reservation14MaxTotal,
	        				reservation15MaxTotal:reservation15MaxTotal,
	        				reservation16MaxTotal:reservation16MaxTotal,
	        				reservation17MaxTotal:reservation17MaxTotal,
	        				reservation18MaxTotal:reservation18MaxTotal,
	        				reservation19MaxTotal:reservation19MaxTotal,
	        				reservation20MaxTotal:reservation20MaxTotal,
	        				reservation21MaxTotal:reservation21MaxTotal,
	        				
						},
					dataType : 'json',
					success : function(d) {
						if (d) {
							addReservationFlag=true;
							mydatagrid.datagrid('reload');
							reservationDetailDatagrid.datagrid('reload');
						}
						parent.sy.messagerShow({
								msg : d.msg,
								title : '提示信息'
						});
					}
				});
	}
	
	//datagrid加载成功后的事件
	function onLoadSuccessReservationDetial(data){
		reservationDetailDatagrid.datagrid("fixRownumber");
		reservationDetailDatagrid.datagrid('hideColumn','trainerId');
		reservationDetailDatagrid.datagrid('hideColumn','reservationDetailId');
		reservationDetailDatagrid.datagrid('hideColumn','trainerReservationId');
		reservationDetailDatagrid.datagrid('hideColumn','reservationFieldId7');
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
		reservationDetailDatagrid.datagrid('hideColumn','reservationFieldId19');
		reservationDetailDatagrid.datagrid('hideColumn','reservationFieldId20');
		reservationDetailDatagrid.datagrid('hideColumn','reservationFieldId21');
		reservationDetailDatagrid.datagrid('hideColumn','reservation7MaxTotal');
		reservationDetailDatagrid.datagrid('hideColumn','reservation8MaxTotal');
		reservationDetailDatagrid.datagrid('hideColumn','reservation9MaxTotal');
		reservationDetailDatagrid.datagrid('hideColumn','reservation10MaxTotal');
		reservationDetailDatagrid.datagrid('hideColumn','reservation11MaxTotal');
		reservationDetailDatagrid.datagrid('hideColumn','reservation12MaxTotal');
		reservationDetailDatagrid.datagrid('hideColumn','reservation13MaxTotal');
		reservationDetailDatagrid.datagrid('hideColumn','reservation14MaxTotal');
		reservationDetailDatagrid.datagrid('hideColumn','reservation15MaxTotal');
		reservationDetailDatagrid.datagrid('hideColumn','reservation16MaxTotal');
		reservationDetailDatagrid.datagrid('hideColumn','reservation17MaxTotal');
		reservationDetailDatagrid.datagrid('hideColumn','reservation18MaxTotal');
		reservationDetailDatagrid.datagrid('hideColumn','reservation19MaxTotal');
		reservationDetailDatagrid.datagrid('hideColumn','reservation20MaxTotal');
		reservationDetailDatagrid.datagrid('hideColumn','reservation21MaxTotal');
		reservationDetailDatagrid.datagrid('selectRecord',defaultSelectRow);
		//datagrid没有数据时显示没有记录信息
		if(data.total==0){
			reservationDetailDatagrid.datagrid('appendRow', {
				item8Studentid: '<div style="text-align:center;color:red">当前教练没有排班，请选择其它教练！</div>' })
			.datagrid('mergeCells', { index: 0, field: 'item7Studentid', colspan:8 });
		}
	}
	
	function endEditing(){
		if (editIndex == undefined) {
				return true;
			}
			if (reservationDetailDatagrid.datagrid('validateRow', editIndex)) {
				reservationDetailDatagrid.datagrid('endEdit', editIndex);
				editIndex = undefined;
				return true;
			} else {
				return false;
			}
	}
	
	//获取学员编号前缀(如：F1001:农峰，目标只取F1001)
	function getFirstPartString(s){
		
		var arr=null;
		if(s.indexOf(":")>0){
			arr=s.split(":");
			return arr[0];
		}
		return s;
	}
	
	//删除预约
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
								mydatagrid.datagrid('reload');
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
			parent.sy.messagerAlert('提示信息', '请勾选要删除的记录！', 'question');
		}
	}

	//查询按钮功能函数
	function mySearch() {
		searchStudentNo($("#demo_reservation_studentNo"), userSchoolArea);
		mydatagrid.datagrid('load', sy.serializeObject($('#demo_reservation_searchForm')));
		$("#demo_reservation_studentNo").val('');
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
	<div data-options="region:'north',border:false,title:'查询功能'" style="height: 125px;overflow: hidden;" align="left">
		<!-- 人员信息查询表单 -->
		<form id="demo_reservation_searchForm">
			<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
			<tr>
				<th style="width: 65px;">所属校区:</th>
				<td><input id="demo_reservation_organization" class="reservation_keyEvent" name="schoolArea" style="width: 155px;"/></td>
				<th style="width: 65px;">学员编号:</th>
				<td><input id="demo_reservation_studentNo" class="reservation_keyEvent" name="studentNo" style="width: 150px;"/></td>
				<th style="width: 65px;">学员姓名:</th>
				<td><input id="demo_reservation_studentName" class="reservation_keyEvent" name="studentName" style="width: 150px;" /></td>
				<th style="width: 65px;">教练姓名:</th>
				<td><input id="demo_reservation_trainerName" class="reservation_keyEvent"  name="trainerName" style="width: 150px;" /></td>
			</tr>
			<tr>
				<th style="width: 80px;">学员身份证:</th>
				<td><input   name="studentIdentityId" class="reservation_keyEvent" style="width: 150px;"/></td>
				<th style="width: 80px;">教练身份证:</th>
				<td><input  name="trainerIdentityId" class="reservation_keyEvent"  style="width: 150px;"/></td>
				<th style="width: 80px;">预约日期开始:</th>
				<td>
					<input name="dateStart" class="easyui-datebox mydateFormat" data-options="showSeconds:false,editable:false" 
					 style="width: 155px;" />
				</td>
				<th style="width: 80px;">预约日期结束:</th>
				<td>
					<input name="dateEnd"  class="easyui-datebox mydateFormat" data-options="showSeconds:false,editable:false"
					 style="width: 155px;" />
				</td>
			</tr>
			<tr>
				<th style="width: 65px;">预约状态:</th>
				<td><input id="demo_reservation_state" name="reservationState" style="width: 155px;" /></td>
				<th style="width: 65px;">预约类型:</th>
				<td><input id="demo_reservation_type" name="reservationType" style="width: 155px;" /></td>
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
		<div id="add-menu" onclick="append();" data-options="iconCls:'icon-add'">增加</div>
		<div id="cancel-menu" onclick="cancelReservation();" data-options="iconCls:'icon-filter'">取消预约</div>
		<div id="del-menu" onclick="remove();" data-options="iconCls:'icon-filter'">删除</div>
	</div>
</body>
</html>
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
		$.ajax({
			url : '${pageContext.request.contextPath}/demo/studentAction!getSchoolArea.action',
			dataType : 'json',
			success : function(d) {
				userSchoolArea=d;
			}
		});
		mydatagrid = $('#student_datagrid').datagrid({
			url : 'studentAction!trainerStudentsDatagrid.action',/* 从后台读取所有user数据 */
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
			}, {
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
				title : '<font color="#0099FF">业务员标识</font>',
				field : 'personId',
				sortable : true,
				width : 100
			},{
				title : '<font color="#0099FF">业务员</font>',
				field : 'personName',
				sortable : true,
				width : 100
			},{
				title : '<font color="#0099FF">备注</font>',
				field : 'comment',
				sortable : true,
				width : 200
			},{
				title : '<font color="#0099FF">学校标识</font>',
				field : 'organizationId',
				sortable : true,
				width : 100
			},{
				title : '<font color="#0099FF">考试标识</font>',
				field : 'examSelected',
				width : 100
			},
			] ],
			//菜单功能设置
			toolbar : [ {
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
				initSchoolArea($("#demo_student_schoolArea"));
				
				$("#demo_student_indexDriverType").combobox({
					url : '${pageContext.request.contextPath}/demo/json/driverType_combobox.json',
					valueField : 'value',
					textField : 'text',
					method:'get',
				});
				//datagrid没有数据时显示没有记录信息
				if(data.total==0){
					mydatagrid.datagrid('appendRow', {
						organizationName: '<div style="text-align:center;color:red">没有学员记录信息！</div>' })
					.datagrid('mergeCells', { index: 0, field: 'organizationName', colspan:8 });
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

	//导入数据按钮，实现对车辆基本信息的导入
	function importDatas(){
		var importDialog = parent.sy.dialog({
			title : '车辆信息导入',
			href : '${pageContext.request.contextPath}/demo/studentAction!importStudentDatas.action',
			width : 560,
			height : 160,
			
			buttons : [
					{
						text : '导入',
						iconCls : 'icon-add',
						handler : function() {
							/* var f = importDialog.find('form');
							//提交表单
							f.form('submit',{
								//提交添加用户表单时，调用UserAction的add方法
								url : '${pageContext.request.contextPath}/demo/carAction!importCarDatas.action',
								success : function(d) {
										 mydatagrid.datagrid('reload'); ///这种方式性能差，消耗系统资源
										 importDialog.dialog('close');
									parent.sy.messagerShow({
										msg : json.msg,
										title : '提示信息'
									});
								}
								
							}); */
						}
					}, {
						text : '取消',
						iconCls : 'icon-cancel',
						handler : function() {
							importDialog.dialog('close');
						}
				} ],
				//通过onLoad函数实现数据回显
				onLoad : function() {
					var f = importDialog.find('form');
					var myfile=f.find('input[name=importFile]');
					myfile.filebox({
						 buttonText:'打开',
						    iconCls:'icon-man',
						    iconAlign:'left',
						    multiple:false,
						    prompt:'请选择要导入的Excel文件',
						    onChange:function(){
						    	//todo
						    }
						    
					});
				},
		});
		
	}

	//单击编辑按钮，实现对用户的编辑功能
	function edit() {
		var stuDriverType=undefined;
		var clazzId=undefined;
		var trainerId=undefined;
		var personId=undefined;
		var rows = mydatagrid.datagrid('getSelections');
		//编辑时只能单选一行进行编辑
		if (rows.length == 1) {
			var p = parent.sy.dialog({
						title : '更新学员信息',
						href : '${pageContext.request.contextPath}/demo/studentAction!toStudentUpdatePage.action',
						width : 970,
						height : 540,
						buttons : [
								{
									text : '更新',
									iconCls : 'icon-tip',
									handler : function() {
										var f = p.find('form');
										f.form('submit',{
											url : '${pageContext.request.contextPath}/demo/studentAction!edit.action',
											success : function(d) {
													var json = $.parseJSON(d);
													if (json.success) {
														mydatagrid.datagrid('reload');
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
									text : '取消',
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
							initSchoolArea(myOrganization);
							
							//班级信息初始化并以combobox形式展现
							clazzId=f.find('input[name=clazzName]');
							clazzId.combobox({
								url : '${pageContext.request.contextPath}/demo/studentAction!getAllClass.action',
								valueField : 'value',
								textField : 'text',
								method:'get',
								editable:false,
								disable:true,
							});
							clazzId.combobox('setValue',rows[0].clazzId);
							clazzId.combobox('readonly',true);
							
							//教练信息初始化并以combobox形式展现
							trainerId=f.find('input[name=trainerName]');
							trainerId.combobox({
								url : '${pageContext.request.contextPath}/demo/studentAction!getAllTrainersForCombobox.action',
								valueField : 'value',
								textField : 'text',
								method:'get',
								editable:false,
							});
							trainerId.combobox('setValue',rows[0].trainerId);
							trainerId.combobox('readonly',true);
							
							//人员信息初始化并以combobox形式展现
							personId=f.find('input[name=personName]');
							personId.combobox({
								url : '${pageContext.request.contextPath}/demo/studentAction!getAllPersonsForCombobox.action',
								valueField : 'value',
								textField : 'text',
								method:'get',
								editable:false,
							});
							personId.combobox('setValue',rows[0].personId);
							personId.combobox('readonly',true);
							
							//选择班级事件
							var btnClazz=f.find('a[id=demo_student_a_selectclazz]');
							btnClazz.linkbutton({
		    				    iconCls: 'icon-search',
		    				    onClick:function(){
		    				    	stuDriverType=myDriverType.combobox('getValue');
		    				    	initClazzSelect(stuDriverType,clazzId,rows[0].organizationId);
		    				    },
		    				});
							//选择教练事件
							btnTrainer=f.find('a[id=demo_student_a_selecttrainer]');
							btnTrainer.linkbutton({
		    				    iconCls: 'icon-search',
		    				    onClick:function(){
		    				    	stuDriverType=myDriverType.combobox('getValue');
		    				    	initTrainerSelect(stuDriverType,trainerId,rows[0].organizationId);
		    				    },
		    				});
							//选择业务员事件
							btnPerson=f.find('a[id=demo_student_a_selectperson]');
							btnPerson.linkbutton({
		    				    iconCls: 'icon-search',
		    				    onClick:function(){
		    				    	initPersonSelect(personId,rows[0].organizationId);
		    				    },
		    				});
							
						}
					});
		} else if (rows.length > 1) {
			parent.sy.messagerAlert('提示信息', '同一时间只能更新一条记录！', 'error');
		} else {
			parent.sy.messagerAlert('提示信息', '请选择要更新的记录！', 'error');
		}
	}

	//添加
	function append() {
		var stuDriverType=undefined;
		var clazzId=undefined;
		var trainerId=undefined;
		var personId=undefined;
		var organizationId=undefined;
		var addDialog = parent.sy.dialog({
					title : '学员信息录入',
					href : '${pageContext.request.contextPath}/demo/studentAction!toStudentAddPage.action',
					width : 960,
					height : 540,
					buttons : [
							{
								text : '添加',
								iconCls : 'icon-add',
								handler : function() {
									var f = addDialog.find('form');
									f.form('submit',{
										//提交添加用户表单时，调用UserAction的add方法
										url : '${pageContext.request.contextPath}/demo/studentAction!add.action',
										success : function(d) {
											var json = $.parseJSON(d);
											if (json.success) {
													mydatagrid.datagrid('reload');//这种方式性能差，消耗系统资源
													addDialog.dialog('close');
											}
											parent.sy.messagerShow({
													msg : json.msg,
													title : '提示信息'
											});
										}
									});
								}
							},{
								text : '取消',
								iconCls : 'icon-cancel',
								handler : function() {
									addDialog.dialog('close');
								}
							} ],
					//通过onLoad函数实现数据回显
					onLoad : function() {
						//初始化班级选项
						var f = addDialog.find('form');
						clazzId=f.find('input[name=clazzName]');
						var myDriverType=f.find('input[name=driverType]');
						var myApplyType=f.find('input[name=applyType]');
						var myOrganization=f.find('input[name=organizationName]');
						//班级信息初始化并以combobox形式展现
						clazzId.combobox({
							url : '${pageContext.request.contextPath}/demo/studentAction!getAllClass.action',
							valueField : 'value',
							textField : 'text',
							method:'get',
							required:true,
							editable:false,
							disable:true,
						});
						clazzId.combobox('readonly',true);
						//教练信息初始化并以combobox形式展现
						trainerId=f.find('input[name=trainerName]');
						trainerId.combobox({
							url : '${pageContext.request.contextPath}/demo/studentAction!getAllTrainersForCombobox.action',
							valueField : 'value',
							textField : 'text',
							method:'get',
							required:true,
							editable:false,
						});
						trainerId.combobox('readonly',true);
						
						//人员信息初始化并以combobox形式展现
						personId=f.find('input[name=personName]');
						personId.combobox({
							url : '${pageContext.request.contextPath}/demo/studentAction!getAllPersonsForCombobox.action',
							valueField : 'value',
							textField : 'text',
							method:'get',
							editable:false,
						});
						personId.combobox('readonly',true);
						
						myDriverType.combobox({
							url : '${pageContext.request.contextPath}/demo/json/driverType_combobox.json',
							valueField : 'value',
							textField : 'text',
							method:'get',
							editable:false,
						}); 
						//设置默认选中项
						myDriverType.combobox('setValue','6');
						
						myApplyType.combobox({
							url : '${pageContext.request.contextPath}/demo/json/applyType_combobox.json',
							valueField : 'value',
							textField : 'text',
							editable:false,
							method:'get',
						});
						//设置默认选中项
						myApplyType.combobox('setValue','1');
						initSchoolArea(myOrganization);
						
						//选择班级事件
						var btnClazz=f.find('a[id=demo_student_a_selectclazz]');
						btnClazz.linkbutton({
	    				    iconCls: 'icon-search',
	    				    onClick:function(){
	    				    	stuDriverType=myDriverType.combobox('getValue');
	    				    	organizationId=myOrganization.combotree('getValue');
	    				    	if(organizationId==""){
	    				    		parent.sy.messagerAlert('提示信息', '系统当前没有选择学员所属校区！！', 'question');
	    				    	}else{
	    				    		initClazzSelect(stuDriverType,clazzId,organizationId);
	    				    	}
	    				    	
	    				    },
	    				});
						//选择教练事件
						btnTrainer=f.find('a[id=demo_student_a_selecttrainer]');
						btnTrainer.linkbutton({
	    				    iconCls: 'icon-search',
	    				    onClick:function(){
	    				    	stuDriverType=myDriverType.combobox('getValue');
	    				    	organizationId=myOrganization.combotree('getValue');
	    				    	if(organizationId==""){
	    				    		parent.sy.messagerAlert('提示信息', '系统当前没有选择学员所属校区！！', 'question');
	    				    	}else{
	    				    		initTrainerSelect(stuDriverType,trainerId,organizationId);
	    				    	}
	    				    	
	    				    },
	    				});
						//选择业务员事件
						btnPerson=f.find('a[id=demo_student_a_selectperson]');
						btnPerson.linkbutton({
	    				    iconCls: 'icon-search',
	    				    onClick:function(){
	    				    	organizationId=myOrganization.combotree('getValue');
	    				    	if(organizationId==""){
	    				    		parent.sy.messagerAlert('提示信息', '系统当前没有选择学员所属校区！！', 'question');
	    				    	}else{
	    				    		initPersonSelect(personId,organizationId);
	    				    	}
	    				    	
	    				    },
	    				});
						
						//初始化学员编号选项
						var studentNum=f.find('input[name=studentNo]');
						$.ajax({
							url : '${pageContext.request.contextPath}/demo/studentAction!getMaxStudentNo.action',
							dataType : 'json',
							success : function(d) {
								studentNum.val(d);
							}
						});
						//较验学员编号是否可用
						studentNum.change(function(){
							$.ajax({
								url : '${pageContext.request.contextPath}/demo/studentAction!studentNoExistOrNot.action',
								data:{studentNo:studentNum.val()},
								dataType : 'json',
								success : function(d) {
									if(d){
										parent.sy.messagerAlert('提示信息', '学员编号重复请重新输入学员编号！', 'question');
									}
								}
							});
						});
						
					},
				});
	}
	//选择班级对话框
	function initClazzSelect(stuDriverType,clazzId,organizationId){
		var selectClazzDialog=undefined;
		 selectClazzDialog = parent.sy.dialog({
			title : '选择班级信息列表',
			href : '${pageContext.request.contextPath}/demo/studentAction!toSelectClazzPage.action',
			width : 960,
			height :585,
			buttons : [
					{
						text : '关闭',
						iconCls : 'icon-cancel',
						handler : function() {
							selectClazzDialog.dialog('close');
						}
					} ],
			//数据回显显示
			onLoad : function() {
				var f=selectClazzDialog.find('form');
				var clazzDatagrid=f.find('table[id=demo_student_selectClazz]');
				clazzDatagrid.datagrid({
					url : '${pageContext.request.contextPath}/demo/studentAction!getClazzDatabyType.action',/* 从后台读取所有user数据 */
					queryParams:{clazzType:stuDriverType,organizationId:organizationId},
					title : '班级开设信息管理列表!', /* 表头标题 */
					iconCls : 'icon-save', /* 标题前边的图标 */
					pagination : true, /* 是否显示页码 */
					pagePosition : 'bottom', /* 页码显示的位置 */
					pageSize : 10, /* 每面显示的记录数 */
					pageList : [ 10, 20, 30, 40 ], /* 选择每面显示的记录数 */
					fit : true,
					fitColumns : false, //列自动调整功能
					nowrap : true, //以行的形式进行显示
					border : false,
					rownumbers: true,
					idField : 'id', //此属性一定要配置
					sortName : 'id', //列排序
					sortOrder : 'desc', //列排序
					checkOnSelect : true,
					selectOnCheck : true,
					striped : true,//表示条纹，隔行显示不现背景色
					nowrap:true,//单元显示
					singleSelect:true,//单选
					//冻结列
					//普通列
					columns : [ [{
						title : '编号',
						field : 'clazzId',
						width : 20,
						checkbox : true
					},{
						title : '<font color="#0099FF">所属校区</font>',
						field : 'clazzSchoolAreaName',
						width : 150,
					},{
						title : '<font color="#0099FF">序号</font>',
						field : 'clazzOrderNo',
						width : 120,
						align : 'center'
					},{
						title : '<font color="#0099FF">名称</font>',
						field : 'clazzName',
						width : 120
					},{
						title : '<font color="#0099FF">费用</font>',
						field : 'clazzFee',
						width : 120,
						align : 'left'
					},{
						title : '<font color="#0099FF">课时(天)</font>',
						field : 'clazzCostTime',
						width : 100,
						align : 'center'
					},{
						title : '<font color="#0099FF">业务类型</font>',
						field : 'clazzType',
						width : 70,
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
						title : '<font color="#0099FF">计时类型</font>',
						field :	'clazzClassType',
						width : 100,
						align : 'center',
						formatter: function(value,row,index){
							if(value==1){
								return '<span style="color:red;">'+'计时'+'</span>';
							}else{
								return '不计时';
							}
						}
					}, {
						title : '<font color="#0099FF">描述</font>',
						field : 'clazzComment',
						width : 200//在datagrid中一定要添加列宽度，否则会加长datagrid渲染的时间，消耗系统资源
					}
					] ],
					//菜单功能设置
					toolbar : [ {
						text : '确认',
						iconCls : 'icon-add',
						handler : function() {
							rows=clazzDatagrid.datagrid('getSelections');
							if(rows.length==0){
								parent.sy.messagerAlert('提示信息', '当前没有给学员选择班级！', 'error');
							}
							clazzId.combobox('setValue',rows[0].clazzId);
							selectClazzDialog.dialog('close');
						}
					}, '-', { //'-':减号，将用“｜”隔开按钮
						text : '返回上一步',
						iconCls : 'icon-remove',
						handler : function() {
							selectClazzDialog.dialog('close');
						}
					},  '-', {
						text : '取消选中',
						iconCls : 'icon-undo',
						handler : function() {
							//取消所有的选中行
							clazzDatagrid.datagrid('clearSelections');
							//取消当前页面的选中行
							clazzDatagrid.datagrid('unselectAll');
						}
					}, '-' ],
					
					onLoadSuccess : function (data) {
						clazzDatagrid.datagrid("fixRownumber");
						//datagrid没有数据时显示没有记录信息
						if(data.total==0){
							clazzDatagrid.datagrid('appendRow', {
								clazzSchoolAreaName: '<div style="text-align:center;color:red">没有记录信息！</div>' })
							.datagrid('mergeCells', { index: 0, field: 'clazzSchoolAreaName', colspan:14 });
						}
					},
					
				});
			}//end onLoad
		});//end diaolog
	}
	
	//选择教练对话框
	function initTrainerSelect(stuDriverType,trainerId,organizationId){
		var selectTrainerDialog=undefined;
		 selectTrainerDialog = parent.sy.dialog({
			title : '选择教练信息列表',
			href : '${pageContext.request.contextPath}/demo/studentAction!toSelectTrainerPage.action',
			width : 960,
			height :590,
			buttons : [
					{
						text : '关闭',
						iconCls : 'icon-cancel',
						handler : function() {
							selectTrainerDialog.dialog('close');
						}
					} ],
			//数据回显显示
			onLoad : function() {
				var f=selectTrainerDialog.find('form');
				var trainerDatagrid=f.find('table[id=demo_student_selectTrainer]');
				trainerDatagrid.datagrid({
					url : '${pageContext.request.contextPath}/demo/studentAction!getTrainerDatabyType.action',/* 从后台读取所有user数据 */
					queryParams:{trainerTechingType:stuDriverType,
						organizationId:organizationId},
					title : '教练信息管理列表!', /* 表头标题 */
					iconCls : 'icon-save', /* 标题前边的图标 */
					pagination : true, /* 是否显示页码 */
					pagePosition : 'bottom', /* 页码显示的位置 */
					pageSize : 10, /* 每面显示的记录数 */
					pageList : [ 10, 20, 30, 40 ], /* 选择每面显示的记录数 */
					fit : true,
					fitColumns : false, //列自动调整功能
					nowrap : true, //以行的形式进行显示
					border : false,
					rownumbers: true,
					idField : 'id', //此属性一定要配置
					sortName : 'id', //列排序
					sortOrder : 'desc', //列排序
					checkOnSelect : true,
					selectOnCheck : true,
					striped : true,//表示条纹，隔行显示不现背景色
					nowrap:true,//单元显示
					singleSelect:true,//单选
					//冻结列
					//普通列
					frozenColumns : [ [ {
						title : '编号',
						field : 'trainerId',
						width : 20,
						checkbox : true
					}, {
						title : '<font color="#0099FF">所属校区</font>',
						field : 'trainerSchoolAreaName',
						width : 80,
						align : 'center'
					} ,{
						title : '<font color="#0099FF">姓名</font>',
						field : 'trainerName',
						width : 80,
						align : 'center'
					} ,{
						title : '<font color="#0099FF">身份证</font>',
						field : 'trainerIdentity',
						width : 140, //在datagrid中一定要添加列宽度，否则会加长datagrid渲染的时间，消耗系统资源
					}
					] ],
					//普通列
					columns : [ [ {
						title : '<font color="#0099FF">联系电话</font>',
						field : 'trainerPhone',
						sortable : true,
						width : 140
					},{
						title : '<font color="#0099FF">驾照类型</font>',
						field : 'trainerDriverType',
						width : 70,
						align:'center',
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
						title : '<font color="#0099FF">准教车型</font>',
						field : 'trainerDriverCarType',
						width : 180,
						formatter:function(value,row,index){
							var temp=[];
							var retValue="";
							temp=value;
							for(var i=0;i<temp.length;i++){
								if(temp[i]!=","){
									retValue+=temp[i];
								}
							}
							if(retValue=="1 3 4 5 6 7 8 9 13"){
								return 'A1,A3,B1,B2,C1,C2,C3,C4,M';
							}else if(retValue=="2 4 5 6 7 8 9 13" ){
								return 'A2,B1,B2,C1,C2,C3,C4,M';
							}else if(retValue=="3 6 7 8 9" ){
								return 'A3,C1,C2,C3,C4';
							}else if(retValue=="4 6 7 8 9" ){
								return 'B1,C1,C2,C3,C4';
							}else if(retValue=="5 6 7 8 9" ){
								return 'B2,C1,C2,C3,C4';
							}else if(retValue=="6 7 8 9" ){
								return 'C1,C2,C3,C4';
							}else if(retValue=="7 8 9" ){
								return 'C2,C3,C4';
							}else if(retValue=="8 9" ){
								return 'C3,C4';
							}else if(retValue=="9" ){
								return 'C4';
							}else{
								return '';
							}
							
						}
						
					}, 
					] ],
					//菜单功能设置
					toolbar : [ {
						text : '确定',
						iconCls : 'icon-add',
						handler : function() {
							rows=trainerDatagrid.datagrid('getSelections');
							if(rows.length==0){
								parent.sy.messagerAlert('提示信息', '当前没有给学员选配教练^v^！', 'error');
							}
							trainerId.combobox('setValue',rows[0].trainerId);
							selectTrainerDialog.dialog('close');
						}
					},  '-', { //'-':减号，将用“｜”隔开按钮
						text : '返回上一步',
						iconCls : 'icon-remove',
						handler : function() {
							selectTrainerDialog.dialog('close');
						}
					},  '-',{
						text : '取消选中',
						iconCls : 'icon-undo',
						handler : function() {
							//取消所有的选中行
							trainerDatagrid.datagrid('clearSelections');
							//取消当前页面的选中行
							trainerDatagrid.datagrid('unselectAll');
						}
					}, '-' ],
					onLoadSuccess : function (data) {
						//修改序号bug
						trainerDatagrid.datagrid("fixRownumber");
						//datagrid没有数据时显示没有记录信息
						if(data.total==0){
							trainerDatagrid.datagrid('appendRow', {
								trainerSchoolAreaName: '<div style="text-align:center;color:red">没有记录信息！</div>' })
							.datagrid('mergeCells', { index: 0, field: 'trainerSchoolAreaName', colspan:14 });
						}
						
					},
				});
				//查询按钮功能事件
				var btnSearch=f.find('a[id=demo_student_searchTrainer]');
				btnSearch.linkbutton({
				    iconCls: 'icon-search',
				    onClick:function(){
				    	trainerDatagrid.datagrid('load', sy.serializeObject(f)); 	
				    }
				});
				//重置按钮功能事件
				var btnReset=f.find('a[id=demo_student_resetTrainer]');
				btnReset.linkbutton({
				    iconCls: 'icon-redo',
				    onClick:function(){
				    	//将数据还原显示到点击查询按钮前的数据
				    	trainerDatagrid.datagrid('load', {organizationId:organizationId});
				    	f.find('input').val('');
				    }
				});
			}//end onLoad
		});//end diaolog
	}

	//选择业务员对话框
	function initPersonSelect(personId,organizationId){
		var selectPersonDialog=undefined;
		selectPersonDialog = parent.sy.dialog({
			title : '选择教练信息列表',
			href : '${pageContext.request.contextPath}/demo/studentAction!toSelectPersonPage.action',
			width : 960,
			height :590,
			buttons : [
					{
						text : '关闭',
						iconCls : 'icon-cancel',
						handler : function() {
							selectPersonDialog.dialog('close');
						}
					} ],
			//数据回显显示
			onLoad : function() {
				var f=selectPersonDialog.find('form');
				var personDatagrid=f.find('table[id=demo_student_selectPerson]');
				personDatagrid.datagrid({
					url : '${pageContext.request.contextPath}/demo/studentAction!getAllPersons.action',/* 从后台读取所有user数据 */
					queryParams:{organizationId:organizationId},
					title : '业务员信息管理列表!', /* 表头标题 */
					iconCls : 'icon-save', /* 标题前边的图标 */
					pagination : true, /* 是否显示页码 */
					pagePosition : 'bottom', /* 页码显示的位置 */
					pageSize : 10, /* 每面显示的记录数 */
					pageList : [ 10, 20, 30, 40 ], /* 选择每面显示的记录数 */
					fit : true,
					fitColumns : false, //列自动调整功能
					nowrap : true, //以行的形式进行显示
					border : false,
					rownumbers: true,
					idField : 'id', //此属性一定要配置
					sortName : 'id', //列排序
					sortOrder : 'desc', //列排序
					checkOnSelect : true,
					selectOnCheck : true,
					striped : true,//表示条纹，隔行显示不现背景色
					nowrap:true,//单元显示
					singleSelect:true,//单选
					//冻结列
					//普通列
					frozenColumns : [ [ {
						title : '编号',
						field : 'personId',
						width : 20,
						checkbox : true
					},{
						title : '<font color="#0099FF">所属校区</font>',
						field : 'personalSchoolAreaName',
						width : 120,
						align : 'center'
					} , {
						title : '<font color="#0099FF">姓名</font>',
						field : 'personName',
						width : 80,
						align : 'center'
					} ,{
						title : '<font color="#0099FF">身份证</font>',
						field : 'personIdentityId',
						width : 140, //在datagrid中一定要添加列宽度，否则会加长datagrid渲染的时间，消耗系统资源
					}
					] ],
					//普通列
					columns : [ [ {
						title : '<font color="#0099FF">职务</font>',
						field : 'personDuty',
						width : 120
					},{
						title : '<font color="#0099FF">联系电话</font>',
						field : 'personPhone',
						width : 120,
						align:'center',
					}, 
					] ],
					//菜单功能设置
					toolbar : [ {
						text : '确定',
						iconCls : 'icon-add',
						handler : function() {
							rows=personDatagrid.datagrid('getSelections');
							if(rows.length==0){
								parent.sy.messagerAlert('提示信息', '当前没有给学员选配业务员^v^！', 'error');
							}
							personId.combobox('setValue',rows[0].personId);
							selectPersonDialog.dialog('close');
						}
					},  '-',  { //'-':减号，将用“｜”隔开按钮
						text : '返回上一步',
						iconCls : 'icon-remove',
						handler : function() {
							selectPersonDialog.dialog('close');
						}
					},  '-',{
						text : '取消选中',
						iconCls : 'icon-undo',
						handler : function() {
							//取消所有的选中行
							personDatagrid.datagrid('clearSelections');
							//取消当前页面的选中行
							personDatagrid.datagrid('unselectAll');
						}
					}, '-' ],
					onLoadSuccess : function (data) {
						//修改序号bug
						personDatagrid.datagrid("fixRownumber");
						if(data.total==0){
							personDatagrid.datagrid('appendRow', {
								personalSchoolAreaName: '<div style="text-align:center;color:red">没有记录信息！</div>' })
							.datagrid('mergeCells', { index: 0, field: 'personalSchoolAreaName', colspan:14 });
						}
					},
				});
				//查询按钮功能事件
				var btnSearch=f.find('a[id=demo_student_searchPerson]');
				btnSearch.linkbutton({
				    iconCls: 'icon-search',
				    onClick:function(){
				    	personDatagrid.datagrid('load', sy.serializeObject(f)); 	
				    }
				});
				//重置按钮功能事件
				var btnReset=f.find('a[id=demo_student_resetPerson]');
				btnReset.linkbutton({
				    iconCls: 'icon-redo',
				    onClick:function(){
				    	//将数据还原显示到点击查询按钮前的数据
				    	personDatagrid.datagrid('load', {});
				    	f.find('input').val('');
				    }
				});
			}//end onLoad
		});//end diaolog
	}


	//删除用户
	function remove() {
		var rows = mydatagrid.datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			parent.sy
					.messagerConfirm(
							'请确认',
							'您要删除当前所选择项信息？',
							function(r) {
								if (r) {
									for (var i = 0; i < rows.length; i++) {
										ids.push(rows[i].id);
									}
									$.ajax({
												url : '${pageContext.request.contextPath}/demo/studentAction!delete.action',
												data : {
													ids : ids.join(',')
												},
												dataType : 'json',
												success : function(d) {
													mydatagrid.datagrid('load');
													mydatagrid
															.datagrid('unselectAll');
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
		mydatagrid.datagrid('load', sy.serializeObject($('#student_searchForm')));
	}

	//取消查询功能按钮
	function cleanSearch() {
		mydatagrid.datagrid('load', {});
		//清空与查询有关的输入框
		$('#student_searchForm input').val('');
	}
</script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询功能'" style="height: 110px;overflow: hidden;" align="left">
		<!-- 学员信息查询表单 -->
		<form id="student_searchForm">
			<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
				<tr>
					<th >所属校区:</th>
					<td><input id="demo_student_schoolArea" name="organizationName"style="width:150px;" /></td>
					<th >姓名:</th>
					<td><input name="name" class="easyui-validatebox" data-options="validType:'validChinese'" style="width:150px;" /></td>
					<th>身份证号：</th>
					<td><input name="identityId" class="easyui-validatebox" data-options="validType:'validateIdentity[15,18]'"  style="width:150px;" /></td>
					
				</tr>
				<tr>
					<th>学员编号：</th>
						<td><input name="studentNo" style="width:150px;" /></td>
					<th>报名日期:</th>
					<td><input name="createTimeStart"
						class="easyui-datebox" data-options="editable:false"
						style="width: 155px;" /></td>
					<th>至</th>
					<td><input name="createTimeEnd"
						class="easyui-datebox" data-options="editable:false"
						style="width: 155px;" />
					</td>
					
				</tr>
				<tr>
					<th>查询类型:</th>
					<td>
						<select id="student_search_combobox"
						class="easyui-combobox"  name="nowState" data-options="editable:false" style="width:155px;">
							<option value="">请选择</option>
							<option value=1>在培学员查询</option>
							<option value=2>毕业学员查询</option>
							<option value=3>退学学员查询</option>
							<option value=4>到期学员查询</option>
						</select>
					</td>
					<th>驾照类型：</th>
					<td><input id="demo_student_indexDriverType" name="driverType" style="width:150px;" /></td>
					<td colspan="2">
						<a href="javascript:void(0);" id="student_a_searche" class="easyui-linkbutton" iconCls="icon-search" onclick="mySearch();">查询</a>
						<a href="javascript:void(0);" id="student_a_canel" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cleanSearch();">取消</a>
					</td>
					
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<table id="student_datagrid"></table>
	</div>
	<!-- 操作按钮事件 -->
	<div id="menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="append();" data-options="iconCls:'icon-add'">增加</div>
		<div onclick="remove();" data-options="iconCls:'icon-remove'">删除</div>
		<div onclick="edit();" data-options="iconCls:'icon-edit'">更新</div>
	</div>
</body>
</html>
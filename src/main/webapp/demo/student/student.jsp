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
	var userSchoolArea=undefined;
	var userACLs=[];
	$(function() {
		userSchoolArea=getUserSchoolArea();
		getUserACL();
		
		mydatagrid = $('#student_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/demo/studentAction!datagrid.action',/* 从后台读取所有user数据 */
			title : '学生信息管理列表!', /* 表头标题 */
			iconCls : 'icon-save', /* 标题前边的图标 */
			pagination : true, /* 是否显示页码 */
			pagePosition : 'bottom', /* 页码显示的位置 */
			pageSize : 20, /* 每面显示的记录数 */
			pageList : [ 10, 20, 30, 40,50,100,200,1000], /* 选择每面显示的记录数 */
			fit : true,
			fitColumns : false, //列自动调整功能
			nowrap : false, //以行的形式进行显示
			border : false,
			rownumbers: true,
			idField : 'id', //此属性一定要配置
			sortName : 'sorting', //列排序
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
				width : 120,
				align : 'center',
				formatter:showAllMsg,
			},  {
				title : '<font color="#0099FF">学员编号</font>',
				field : 'studentNo',
				width : 90,
				sortable : true,
				align : 'center',
				formatter:formatStudentNo,
			},{
				title : '<font color="#0099FF">姓名</font>',
				field : 'name',
				width : 80,
				sortable : true,
				align : 'center',
				formatter:showAllMsg,
			},] ],
			//普通列
			columns : [ [  {
				title : '<font color="#0099FF">身份证号</font>',
				field : 'identityId',
				sortable : true,
				width : 150,
				formatter:showAllMsg,
			},{
				title : '<font color="#0099FF">性别</font>',
				field : 'sex',
				width : 60 ,
				align:'center',
				
			}, {
				title : '<font color="#0099FF">地址</font>',
				field : 'address',
				sortable : true,
				width : 290,
				formatter:showAllMsg,
			}, {
				title : '<font color="#0099FF">手机</font>',
				field : 'phone',
				width : 120,
				formatter:showAllMsg,
			},{
				title : '<font color="#0099FF">报名日期</font>',
				field : 'createTime',
				sortable : true,
				width : 90,
				align : 'center',
				formatter:formatTimeYMD,
			},{
				title : '<font color="#0099FF">驾照类型</font>',
				field : 'driverType',
				sortable : true,
				width : 70,
				align:'center',
				formatter:function(value,row,index){
					return formatterDriverType(value, row, index);
				}
			},{
				title : '<font color="#0099FF">申领类型</font>',
				field : 'applyType',
				sortable : true,
				width : 90,
				formatter:formatApplyType,
			},{
				title : '<font color="#0099FF">教练名称</font>',
				field : 'trainerName',
				width :80
			},{
				title : '<font color="#0099FF">学员类别</font>',
				field : 'studentType',
				sortable : true,
				width : 70,
				align : 'center',
				formatter:initStudentType,
			},{
				title : '<font color="#0099FF">计时类型</font>',
				field : 'timingFlag',
				sortable : true,
				width : 70,
				align : 'center',
				formatter:initTimingCountType,
			},{
				title : '<font color="#0099FF">预约验证码</font>',
				field : 'verificationCode',
				align : 'center',
				width :80,
			},{
				title : '<font color="#0099FF">报名网点</font>',
				field : 'netPiontName',
				align : 'center',
				width : 150,
			},{
				title : '<font color="#0099FF">交管约考</font>',
				field : 'password',
				width :100,
			},{
				title : '<font color="#0099FF">运营资格</font>',
				field : 'qualificationFlag',
				width :80,
				align : 'center',
				formatter:formatQualification,
			},{
				title : '<font color="#0099FF">面签</font>',
				field : 'faceSign',
				sortable : true,
				width : 70,
				align : 'center',
				formatter:formatCommitValue,
			},{
				title : '<font color="#0099FF">提交档案</font>',
				field : 'submitFile',
				sortable : true,
				width : 70,
				align : 'center',
				formatter:formatCommitValue,
			},{
				title : '<font color="#0099FF">相片</font>',
				field : 'images',
				sortable : true,
				width : 70,
				align : 'center',
				formatter:formatCommitValue,
			},{
				title : '<font color="#0099FF">体检表</font>',
				field : 'examinationTable',
				sortable : true,
				width : 70,
				align : 'center',
				formatter:formatCommitValue,
			},{
				title : '<font color="#0099FF">户籍</font>',
				field : 'foreignStudent',
				sortable : true,
				width : 70,
				align : 'center',
				formatter:formatForeign,
			},{
				title : '<font color="#0099FF">计时卡</font>',
				field : 'timingCar',
				sortable : true,
				width : 70,
				align : 'center',
				formatter:formatTimingCar,
			},{
				title : '<font color="#0099FF">身份证资料</font>',
				field : 'indentityFlag',
				sortable : true,
				width : 70,
				align : 'center',
				formatter:formatCommitValue,
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
				width : 90,
				align:'center',
				formatter:formatTimeYMD,
			},{
				title : '<font color="#0099FF">状态</font>',
				field : 'nowState',
				width : 100,
				formatter:formatStudentState,
			}, {
				title : '<font color="#0099FF">固定电话</font>',
				field : 'telephone',
				width : 100,
				formatter:showAllMsg,
			}, {
				title : '<font color="#0099FF">邮箱</font>',
				field : 'email',
				sortable : true,
				width : 150,
				formatter:showAllMsg,
			}, {
				title : '<font color="#0099FF">照片回执编号</font>',
				field : 'imageId',
				sortable : true,
				width : 150,
				formatter:showAllMsg,
			}, {
				title : '<font color="#0099FF">邮编</font>',
				field : 'mailCode',
				sortable : true,
				width : 100,
				formatter:showAllMsg,
			}, {
				title : '<font color="#0099FF">居住证</font>',
				field : 'residenceId',
				sortable : true,
				width : 150,
				formatter:showAllMsg,
			}, {
				title : '<font color="#0099FF">居住证地址</font>',
				field : 'residenceAddr',
				sortable : true,
				width : 150,
				formatter:showAllMsg,
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
			},{
				title : '<font color="#0099FF">出生日期</font>',
				field : 'birthdate',
				sortable : true,
				width : 90,
				align : 'center',
				formatter:formatTimeYMD,
			},{
				title : '<font color="#0099FF">班级</font>',
				field : 'clazzName',
				width : 100,
				formatter:showAllMsg,
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
				title : '<font color="#0099FF">操作员</font>',
				field : 'operator',
				sortable : true,
				width : 80
			},{
				title : '<font color="#0099FF">备注</font>',
				field : 'comment',
				sortable : true,
				width : 200,
				formatter:showAllMsg,
			},{
				title : '<font color="#0099FF">学校标识</font>',
				field : 'organizationId',
				sortable : true,
				width : 100
			},{
				title : '<font color="#0099FF">考试标识</font>',
				field : 'examSelected',
				width : 100
			},{
				title : '<font color="#0099FF">剩余学时</font>',
				field : 'restTiming',
				width : 70
			},{
				title : '<font color="#0099FF">报名网点</font>',
				field : 'netPiont',
				width : 70
			},
			] ],
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
				id:'import-btn-toolbar',
				text : '导入',
				iconCls : 'icon-ok',
				handler : function() {
					importDatas();
				}
			}, '-', {
				id:'export-btn-toolbar',
				text : '导出',
				iconCls : 'icon-ok',
				handler : function() {
					exportDatas();
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
			}, '-', {
				id:'exp-btn-toolbar',
				text : '刷新',
				iconCls : 'icon-reload',
				handler : function() {
					mydatagrid.datagrid('clearSelections');
					mydatagrid.datagrid('unselectAll');
					mydatagrid.datagrid('uncheckAll');
				}
			}, ],
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
			onLoadSuccess : function (data) {
				//修改序号bug
				$(this).datagrid("fixRownumber");
				$(this).datagrid('hideColumn','clazzId');
				$(this).datagrid('hideColumn','trainerId');
				$(this).datagrid('hideColumn','personId');
				$(this).datagrid('hideColumn','organizationId');
				$(this).datagrid('hideColumn','examSelected');
				$(this).datagrid('hideColumn','restTiming');
				$(this).datagrid('hideColumn','netPiont');
				//初始化校区选项
				initSchoolArea($("#demo_student_schoolArea"),userSchoolArea);
				initDriverType($("#demo_student_indexDriverType"));
				//添加回车事件
				bindingKeypressEvent($(".keypress_event"),mydatagrid,$("#student_searchForm"));
				//datagrid没有数据时显示没有记录信息
				if(data.total==0){
					mydatagrid.datagrid('appendRow', {
						organizationName:  '<div style="text-align:center;color:red">没有学员记录信息！</div>' })
					.datagrid('mergeCells', { index: 1, field: 'organizationName', colspan:10 });
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
	
	//导入数据按钮，实现对学员基本信息的导入
	function importDatas(){
		var importDialog =undefined;
			importDialog= parent.sy.dialog({
			title : '导入学员信息',
			href : '${pageContext.request.contextPath}/demo/studentAction!toUploadPage.action',
			width : 560,
			height : 160,
			
			buttons : [
					{
						id:'studentImport',
						text : '导入',
						iconCls : 'icon-add',
						handler : function() {
							var f = importDialog.find('form');
							//提交表单
							f.form('submit',{
								//提交添加用户表单时，调用UserAction的add方法
								url : '${pageContext.request.contextPath}/demo/studentAction!importStudentDatas.action',
								success : function(d) {
									var json = $.parseJSON(d);
									importDialog.dialog('close');
									
									if(json.success){
										$.messager.progress({
											text : '正在导入中...，请稍后',
											interval : 200
										});
										window.setTimeout(function() {
											try {
												$.messager.progress('close');
												parent.sy.messagerShow({
													msg : json.msg,
													title : '提示信息'
												});
												mydatagrid.datagrid('reload'); ///这种方式性能差，消耗系统资源
											} catch (e) {
											}
										}, 5000);
									}else{
										parent.sy.messagerShow({
											msg : json.msg,
											title : '提示信息'
										});
									}	 
									
								}
								
							});
						}
					}, {
						text : '取消',
						iconCls : 'icon-cancel',
						handler : function() {
							importDialog.dialog('close');
						}
				} ],
		});
		
	}
	//单击编辑按钮，实现对用户的编辑功能
	function edit() {
		var stuDriverType=undefined;
		var clazzId=undefined;
		var trainerId=undefined;
		var personId=undefined;
		var studentTypeTemp=undefined;
		var rows = mydatagrid.datagrid('getSelections');
		//编辑时只能单选一行进行编辑
		if (rows.length == 1) {
			var p=undefined; 
			p= parent.sy.dialog({
						title : '更新学员信息',
						href : '${pageContext.request.contextPath}/demo/studentAction!toStudentUpdatePage.action',
						width : 1024,
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
														mydatagrid.datagrid('uncheckAll');
														mydatagrid.datagrid('unselectAll');
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
								feed:rows[0].feed,
								realFeed:rows[0].realFeed,
								ownFeed:rows[0].ownFeed,
								learningTime:rows[0].learningTime,
								timingFlag:rows[0].timingFlag,
								studentType:rows[0].studentType,
								
								driverType : rows[0].driverType,
								applyType : rows[0].applyType,
								organizationName:rows[0].organizationName,
								examSelected:rows[0].examSelected,
								restTiming:rows[0].restTiming,
								
								faceSign:rows[0].faceSign,
								submitFile:rows[0].submitFile,
								images:rows[0].images,
								examinationTable:rows[0].examinationTable,
								foreignStudent:rows[0].foreignStudent,
								timingCar:rows[0].timingCar,
								indentityFlag:rows[0].indentityFlag,
								
								clazzId : rows[0].clazzId,
								clazzName : rows[0].clazzName,
								trainerId : rows[0].trainerId,
								trainerName : rows[0].trainerName,
								personId : rows[0].personId,
								personName : rows[0].personName,
								password:rows[0].password,
								verificationCode:rows[0].verificationCode,
								qualificationFlag:rows[0].qualificationFlag,
								netPiont:rows[0].netPiont,
								
							});
							initPriceParams(p);//初始化学员学费
							var f = p.find('form');
							//初始化申领类型
							var myApplyType=f.find('input[name=applyType]');
							initApplyType(myApplyType,rows[0].applyType);
							//初始化驾照类型（并处理，C1到C2，C2到C1学员编号更换功能）
							var studentNoTemp=f.find('input[name=studentNo]');
							var myDriverType=f.find('input[name=driverType]');
							initDriverType(myDriverType,rows[0].driverType);
							myDriverType.combobox({
								onChange:function(){
									var temp=rows[0].studentNo;
									if(6==myDriverType.combobox('getValue')){
										studentNoTemp.val(changeStudentNo(temp,6));
									}else if(7==myDriverType.combobox('getValue')){
										studentNoTemp.val(changeStudentNo(temp,7));
									}else if(8==myDriverType.combobox('getValue')){
										studentNoTemp.val(changeStudentNo(temp,8));
									}else if(9==myDriverType.combobox('getValue')){
										studentNoTemp.val(changeStudentNo(temp,9));
									}else if(10==myDriverType.combobox('getValue')){
										studentNoTemp.val(changeStudentNo(temp,10));
									}else if(1==myDriverType.combobox('getValue')){
										studentNoTemp.val(changeStudentNo(temp,1));
									}else if(2==myDriverType.combobox('getValue')){
										studentNoTemp.val(changeStudentNo(temp,2));
									}else if(3==myDriverType.combobox('getValue')){
										studentNoTemp.val(changeStudentNo(temp,3));
									}else if(4==myDriverType.combobox('getValue')){
										studentNoTemp.val(changeStudentNo(temp,4));
									}else if(5==myDriverType.combobox('getValue')){
										studentNoTemp.val(changeStudentNo(temp,5));
									}
								}
							});
							//初始化校区
							var myOrganization=f.find('input[name=organizationName]');
							initSchoolArea(myOrganization,userSchoolArea);
							//初始化学员计时类型选项(默认选择非计时)
							var timingFlagTemp=f.find('input[name=timingFlag]');
							var learningTimeTemp=f.find('input[name=learningTime]');
							var timingFlagURL= '${pageContext.request.contextPath}/demo/json/timingCountType_combobox.json';
							initComboboxData(timingFlagTemp, rows[0].timingFlag, timingFlagURL, true);
							learningTimeTemp.attr('disabled',true);
							timingFlagTemp.combobox({
								onChange:function(){
									if(timingFlagTemp.combobox('getValue')==1){
										learningTimeTemp.attr('disabled',false);
									}else{
										learningTimeTemp.attr('disabled',true);
									}
								}
							});
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
								queryParams:{organizationId:userSchoolArea},
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
							//运营资格初始化
							var qualificationFlag=f.find('input[name=qualificationFlag]');
							initQualificationFlag(qualificationFlag, rows[0].qualificationFlag, true, false);
							//选择班级事件
							var btnClazz=f.find('a[id=demo_student_a_selectclazz]');
							btnClazz.linkbutton({
		    				    iconCls: 'icon-search',
		    				    onClick:function(){
		    				    	stuDriverType=myDriverType.combobox('getValue');
		    				    	initClazzSelect(stuDriverType,clazzId,rows[0].organizationId);
		    				    },
		    				});
							var studnetNoTemp=f.find('input[name=studentNo]');
							studnetNoTemp.attr('readonly',true);//TODO
							
							//选择教练事件
							btnTrainer=f.find('a[id=demo_student_a_selecttrainer]');
							btnTrainer.linkbutton({
		    				    iconCls: 'icon-more',
		    				    onClick:function(){
		    				    	stuDriverType=myDriverType.combobox('getValue');
		    				    	initTrainerSelect(stuDriverType,trainerId,rows[0].organizationId,studnetNoTemp,studentTypeTemp);
		    				    },
		    				});
							btnTrainer.tooltip({
								position : 'top',
								content : '单击选择教练',
								onShow : function() {
									btnTrainer.tooltip('tip').css({
										backgroundColor : '#00ffcc',
										borderColor : '#666'
									});
								}
							});
							
							//初始化学员类型
							studentTypeTemp=f.find('input[name=studentType]');
							initStudentTypeVal(studentTypeTemp, rows[0].studentType);
							//取消捆绑
							var btnCancelTrainer=f.find('a[id=demo_student_a_canceltrainer]');
							btnCancelTrainer.linkbutton({
		    				    iconCls: 'icon-no',
		    				    onClick:function(){
		    				    	cancelTarinerBinding(trainerId,studnetNoTemp,studentTypeTemp);
		    				    },
		    				});
							btnCancelTrainer.tooltip({
								position : 'top',
								content : '单击取消教练选择',
								onShow : function() {
									btnCancelTrainer.tooltip('tip').css({
										backgroundColor : '#00ffcc',
										borderColor : '#666'
									});
								}
							});
							//取消业务员
							var btnCancelPerson=f.find('a[id=demo_student_a_cancelperson]');
							btnCancelPerson.linkbutton({
		    				    iconCls: 'icon-no',
		    				    onClick:function(){
		    				    	cancelPersonBinding(personId);
		    				    },
		    				});
							btnCancelPerson.tooltip({
								position : 'top',
								content : '取消业务员',
								onShow : function() {
									btnCancelPerson.tooltip('tip').css({
										backgroundColor : '#00ffcc',
										borderColor : '#666'
									});
								}
							});
							//选择业务员事件
							btnPerson=f.find('a[id=demo_student_a_selectperson]');
							btnPerson.linkbutton({
		    				    iconCls: 'icon-more',
		    				    onClick:function(){
		    				    	initPersonSelect(personId,rows[0].organizationId,stuDriverType,trainerId,rows[0].organizationId,studnetNoTemp,studentTypeTemp);
		    				    },
		    				});
							btnPerson.tooltip({
								position : 'top',
								content : '选择业务员',
								onShow : function() {
									btnPerson.tooltip('tip').css({
										backgroundColor : '#00ffcc',
										borderColor : '#666'
									});
								}
							});
							//预约验证码
							var verificationCodeTemp=f.find('input[name=verificationCode]');
							verificationCodeTemp.val(rows[0].verificationCode);
							//刷新验证码按钮
							var btnReload=f.find('a[id=demo_student_a_verificationCode]');
							btnReload.linkbutton({
		    				    iconCls: 'icon-reload',
		    				    onClick:function(){
		    				    	initVerificationCode(verificationCodeTemp);
		    				    },
		    				});
							var  netPiont=f.find('input[name=netPiont]');
							initMyNetPiont(netPiont,rows[0].netPiont);
						}
					});
		} else if (rows.length > 1) {
			parent.sy.messagerAlert('提示信息', '同一时间只能更新一条记录！', 'error');
		} else {
			parent.sy.messagerAlert('提示信息', '请选择要更新的记录！', 'error');
		}
	}
	
	//学员C1与C2转换
	function changeStudentNo(studentNo,flag){
		var temp=undefined;
		if(flag==6){
			temp='F';
		}else if(flag==7){
			temp='B';
		}else if(flag==8){
			temp='R';
		}else if(flag==9){
			temp='S';
		}else if(flag==10){
			temp='T';
		}else if(flag==5){
			temp='V';
		}else if(flag==4){
			temp='U';
		}else if(flag==3){
			temp='Y';
		}else if(flag==2){
			temp='X';
		}else if(flag==1){
			temp='W';
		}
		var str=studentNo;
		var startStr=str.substring(0,2);
		var endStr=str.substring(3,str.length);
		return startStr+temp+endStr;
	}
	
	//查看学员明细信息
	function read() {
		var stuDriverType=undefined;
		var clazzId=undefined;
		var trainerId=undefined;
		var personId=undefined;
		var rows = mydatagrid.datagrid('getSelections');
		//编辑时只能单选一行进行编辑
		if (rows.length == 1) {
			var p = parent.sy.dialog({
						title : '学员详细信息',
						href : '${pageContext.request.contextPath}/demo/studentAction!toStudentDetailPage.action',
						width : 1024,
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
										feed:rows[0].feed,
										realFeed:rows[0].realFeed,
										ownFeed:rows[0].ownFeed,
										learningTime:rows[0].learningTime,
										timingFlag:rows[0].timingFlag,
										studentType:rows[0].studentType,
										
										driverType : rows[0].driverType,
										applyType : rows[0].applyType,
										organizationName:rows[0].organizationName,
										examSelected:rows[0].examSelected,
										restTiming:rows[0].restTiming,
										
										faceSign:rows[0].faceSign,
										submitFile:rows[0].submitFile,
										images:rows[0].images,
										examinationTable:rows[0].examinationTable,
										foreignStudent:rows[0].foreignStudent,
										timingCar:rows[0].timingCar,
										indentityFlag:rows[0].indentityFlag,
										
										clazzId : rows[0].clazzId,
										clazzName : rows[0].clazzName,
										trainerId : rows[0].trainerId,
										trainerName : rows[0].trainerName,
										personId : rows[0].personId,
										personName : rows[0].personName,
										password:rows[0].password,
										
									});
									initPriceParams(p);//初始化学员学费
									var f = p.find('form');
									//初始化申领类型
									var myApplyType=f.find('input[name=applyType]');
									initApplyType(myApplyType,rows[0].applyType);
									//初始化驾照类型（并处理，C1到C2，C2到C1学员编号更换功能）
									var studentNoTemp=f.find('input[name=studentNo]');
									var myDriverType=f.find('input[name=driverType]');
									initDriverType(myDriverType,rows[0].driverType);
									myDriverType.combobox({
										onChange:function(){
											var temp=rows[0].studentNo;
											if(6==myDriverType.combobox('getValue')){
												studentNoTemp.val(changeStudentNo(temp,6));
											}else if(7==myDriverType.combobox('getValue')){
												studentNoTemp.val(changeStudentNo(temp,7));
											}else if(8==myDriverType.combobox('getValue')){
												studentNoTemp.val(changeStudentNo(temp,8));
											}else if(9==myDriverType.combobox('getValue')){
												studentNoTemp.val(changeStudentNo(temp,9));
											}else if(10==myDriverType.combobox('getValue')){
												studentNoTemp.val(changeStudentNo(temp,10));
											}else if(1==myDriverType.combobox('getValue')){
												studentNoTemp.val(changeStudentNo(temp,1));
											}else if(2==myDriverType.combobox('getValue')){
												studentNoTemp.val(changeStudentNo(temp,2));
											}else if(3==myDriverType.combobox('getValue')){
												studentNoTemp.val(changeStudentNo(temp,3));
											}else if(4==myDriverType.combobox('getValue')){
												studentNoTemp.val(changeStudentNo(temp,4));
											}else if(5==myDriverType.combobox('getValue')){
												studentNoTemp.val(changeStudentNo(temp,5));
											}
										}
									});
									//初始化校区
									var myOrganization=f.find('input[name=organizationName]');
									initSchoolArea(myOrganization,userSchoolArea);
									//初始化学员计时类型选项(默认选择非计时)
									var timingFlagTemp=f.find('input[name=timingFlag]');
									var learningTimeTemp=f.find('input[name=learningTime]');
									var timingFlagURL= '${pageContext.request.contextPath}/demo/json/timingCountType_combobox.json';
									initComboboxData(timingFlagTemp, rows[0].timingFlag, timingFlagURL, true);
									learningTimeTemp.attr('disabled',true);
									timingFlagTemp.combobox({
										onChange:function(){
											if(timingFlagTemp.combobox('getValue')==1){
												learningTimeTemp.attr('disabled',false);
											}else{
												learningTimeTemp.attr('disabled',true);
											}
										}
									});
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
										queryParams:{organizationId:userSchoolArea},
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
									var studnetNoTemp=f.find('input[name=studentNo]');
									studnetNoTemp.attr('readonly',true);//TODO
									
									//选择教练事件
									btnTrainer=f.find('a[id=demo_student_a_selecttrainer]');
									btnTrainer.linkbutton({
				    				    iconCls: 'icon-search',
				    				    onClick:function(){
				    				    	stuDriverType=myDriverType.combobox('getValue');
				    				    	initTrainerSelect(stuDriverType,trainerId,rows[0].organizationId,studnetNoTemp,studentTypeTemp);
				    				    },
				    				});
									
									//初始化学员类型
									studentTypeTemp=f.find('input[name=studentType]');
									initStudentType(studentTypeTemp, rows[0].studentType);
									//取消捆绑
									var btnCancelTrainer=f.find('a[id=demo_student_a_canceltrainer]');
									btnCancelTrainer.linkbutton({
				    				    iconCls: 'icon-cancel',
				    				    onClick:function(){
				    				    	cancelTarinerBinding(trainerId,studnetNoTemp,studentTypeTemp);
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
			parent.sy.messagerAlert('提示信息', '同一时间只能查看一条记录！', 'question');
		} else {
			parent.sy.messagerAlert('提示信息', '请选择要查看的记录！', 'question');
		}
	}
	
	//添加
	function append() {
		var stuDriverType=undefined;
		var clazzId=undefined;
		var trainerId=undefined;
		var personId=undefined;
		var organizationId=undefined;
		var studentNoConfirm=undefined;
		var studentTypeTemp=undefined;
		var addDialog =undefined;
		addDialog=parent.sy.dialog({
					title : '学员信息录入',
					href : '${pageContext.request.contextPath}/demo/studentAction!toStudentAddPage.action',
					width : 1024,
					height : 580,
					buttons : [
							{
								text : '添加',
								iconCls : 'icon-add',
								handler : function() {
									var btnAdd = this;
									$(btnAdd).hide();
									var f = addDialog.find('form');
									f.form('submit',{
										//提交添加用户表单时，调用UserAction的add方法
										url : '${pageContext.request.contextPath}/demo/studentAction!add.action',
										success : function(d) {
											$(btnAdd).show();
											var json = $.parseJSON(d);
											if (json.success) {
													mydatagrid.datagrid('insertRow',{
														index : 0, // index start with 0
														row : json.obj
													});
												addDialog.dialog('close');
												mydatagrid.datagrid('selectRow',0);
											}else{
												studentNoConfirm.linkbutton({disabled:false});
												studentNoConfirm.linkbutton('enable');
											}
											parent.sy.messagerShow({
													msg : json.msg,
													title : '提示信息'
											});
										},
										error:function(err){
											$(btnAdd).show();
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
						
						initPriceParams(addDialog);//初始化学员费用选项
						var f = addDialog.find('form');
						clazzId=f.find('input[name=clazzName]');
						
						var myApplyType=f.find('input[name=applyType]');
						var myOrganization=f.find('input[name=organizationName]');
						//默认注册时间为当前日期
						var createTimeTemp=f.find('input[name=createTime]');
						createTimeTemp.datebox({
							showSeconds:false,
						});
						createTimeTemp.datebox('setValue',new Date().toLocaleDateString());
						//班级信息初始化并以combobox形式展现
						clazzId.combobox({
							url : '${pageContext.request.contextPath}/demo/studentAction!getAllClass.action',
							valueField : 'value',
							textField : 'text',
							method:'get',
							editable:false,
							disable:true,
						});
						clazzId.combobox('readonly',true);
						//教练信息初始化并以combobox形式展现
						trainerId=f.find('input[name=trainerName]');
						trainerId.combobox({
							url : '${pageContext.request.contextPath}/demo/studentAction!getAllTrainersForCombobox.action',
							queryParams:{organizationId:userSchoolArea},
							valueField : 'value',
							textField : 'text',
							method:'get',
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
						//初始化学员类型
						studentTypeTemp=f.find('input[name=studentType]');
						initStudentTypeVal(studentTypeTemp,0);
						
						//初始化学员计时类型选项(默认选择非计时)
						var timingFlagTemp=f.find('input[name=timingFlag]');
						initTimingType(timingFlagTemp, 0);
						
						var learningTimeTemp=f.find('input[name=learningTime]');
						learningTimeTemp.attr('disabled',true);
						timingFlagTemp.combobox({
							onChange:function(){
								if(timingFlagTemp.combobox('getValue')==1){
									learningTimeTemp.attr('disabled',false);
								}else{
									learningTimeTemp.attr('disabled',true);
								}
							}
						});
						
						//学员编号
						var studentNum=f.find('input[name=studentNo]');
						studentNum.attr('readonly',false);//TODO
						//初始化驾照类型
						var myDriverType=f.find('input[name=driverType]');
						initDriverType(myDriverType,6);
						myDriverType.combobox({
							onChange:function(){
								initStudentNo(studentNum,myDriverType.combobox('getValue'),trainerId,studentTypeTemp);
							}
						}); 
						
						//初始化学员编号选项(C1-F-6,C2-B-7)
						initStudentNo(studentNum,myDriverType.combobox('getValue'),trainerId,studentTypeTemp);
						//编号认证
						studentNoConfirm=f.find('a[id=demo_student_a_studentNo]');
						studentNoConfirm.linkbutton({
							disabled:true,
	    				    onClick:function(){
	    				    	$.ajax({
		    				    	url : '${pageContext.request.contextPath}/demo/studentAction!getMaxStudentNo.action',
		    						data:{studentNoTypeVal:myDriverType.combobox('getValue')},
		    						dataType : 'json',
		    						success : function(d) {
		    							studentNum.val(d.studentNo);
		    							trainerId.combobox('setValue',d.trainerId);
		    							studentTypeTemp.combobox('setValue',d.trainerType);
		    							studentNoConfirm.linkbutton('disable');
		    						},
	    				    	});
	    				    },
	    				});
						
						initApplyType(myApplyType,1);
						initSchoolArea(myOrganization,userSchoolArea);
						//运营资格初始化
						var qualificationFlag=f.find('input[name=qualificationFlag]');
						initQualificationFlag(qualificationFlag, 0, true, false);
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
	    				    iconCls: 'icon-more',
	    				    onClick:function(){
	    				    	stuDriverType=myDriverType.combobox('getValue');
	    				    	organizationId=myOrganization.combotree('getValue');
	    				    	if(organizationId==""){
	    				    		parent.sy.messagerAlert('提示信息', '系统当前没有选择学员所属校区！！', 'question');
	    				    	}else{
	    				    		initTrainerSelect(stuDriverType,trainerId,organizationId,studentNum,studentTypeTemp);
	    				    	}
	    				    	
	    				    },
	    				});
						btnTrainer.tooltip({
							position : 'top',
							content : '单击选择教练',
							onShow : function() {
								btnTrainer.tooltip('tip').css({
									backgroundColor : '#00ffcc',
									borderColor : '#666'
								});
							}
						});
						
						//取消教练捆绑
						var btnCancelTrainer=f.find('a[id=demo_student_a_canceltrainer]');
						btnCancelTrainer.linkbutton({
	    				    iconCls: 'icon-no',
	    				    onClick:function(){
	    				    	cancelTarinerBinding(trainerId,studentNum,studentTypeTemp);
	    				    	
	    				    },
	    				});
						btnCancelTrainer.tooltip({
							position : 'top',
							content : '单击取消教练选择',
							onShow : function() {
								btnCancelTrainer.tooltip('tip').css({
									backgroundColor : '#00ffcc',
									borderColor : '#666'
								});
							}
						});
						//取消业务员
						var btnCancelPerson=f.find('a[id=demo_student_a_cancelperson]');
						btnCancelPerson.linkbutton({
	    				    iconCls: 'icon-no',
	    				    onClick:function(){
	    				    	cancelPersonBinding(personId);
	    				    },
	    				});
						btnCancelPerson.tooltip({
							position : 'top',
							content : '单击取消业务员',
							onShow : function() {
								btnCancelPerson.tooltip('tip').css({
									backgroundColor : '#00ffcc',
									borderColor : '#666'
								});
							}
						});
						
						//选择业务员事件
						btnPerson=f.find('a[id=demo_student_a_selectperson]');
						btnPerson.linkbutton({
	    				    iconCls: 'icon-more',
	    				    onClick:function(){
	    				    	organizationId=myOrganization.combotree('getValue');
	    				    	if(organizationId==""){
	    				    		parent.sy.messagerAlert('提示信息', '系统当前没有选择学员所属校区！！', 'question');
	    				    	}else{
	    				    		initPersonSelect(personId,organizationId,stuDriverType,trainerId,organizationId,studentNum,studentTypeTemp);
	    				    	}
	    				    	
	    				    },
	    				});
						btnPerson.tooltip({
							position : 'top',
							content : '单击选择业务员',
							onShow : function() {
								btnPerson.tooltip('tip').css({
									backgroundColor : '#00ffcc',
									borderColor : '#666'
								});
							}
						});
						
						//校验身份证不能重复
						var stuIdentity=f.find('input[name=identityId]');
						//较验学员编号是否可用
						stuIdentity.change(function(){
							$.ajax({
								url : '${pageContext.request.contextPath}/demo/studentAction!identityIdExistOrNot.action',
								data:{identityId:stuIdentity.val()},
								dataType : 'json',
								success : function(d) {
									if(d){
										parent.sy.messagerAlert('提示信息', '您输入的身份证号系统中已经存在，请核对！', 'question');
										stuIdentity.val('');
									}
								}
							});
						});
						//预约验证码
						var verificationCodeTemp=f.find('input[name=verificationCode]');
						initVerificationCode(verificationCodeTemp);
						//刷新验证码按钮
						var btnReload=f.find('a[id=demo_student_a_verificationCode]');
						btnReload.linkbutton({
	    				    iconCls: 'icon-reload',
	    				    onClick:function(){
	    				    	initVerificationCode(verificationCodeTemp);
	    				    },
	    				});
						//初始化报名网点
						var netPiont=f.find('input[name=netPiont]');
						initMyNetPiont(netPiont,null);
					},
				});
	}
	
	//初始化预约验证码
	function initVerificationCode(obj){
		$.ajax({
			url : '${pageContext.request.contextPath}/demo/studentAction!toVerificationCode.action',
			dataType : 'json',
			success : function(d) {
				obj.val(d);
			},
			error:function(err){
				console.log("获取预约验证友失败");
			}
});
	}
	
	//用户取消教练，更换教练时学员编号发生改变
	function cancelTarinerBinding(trainerId,studentNum,studentTypeTemp){
		trainerId.combobox('setValue',null);
    	trainerId.combobox('setText',null);
    	studentTypeTemp.combobox('setValue',0);
    	//在学员编号中添加教练编号
		var studentNoTemp=studentNum.val();
		var endStr=studentNoTemp.toString().substring(studentNoTemp.length-4,studentNoTemp.length);
		var startStr=null;
		if(studentNoTemp.toString().length>7){
			startStr=studentNoTemp.toString().substring(0,studentNoTemp.length-7);
		}else{
			startStr=studentNoTemp.toString().substring(0,studentNoTemp.length-4);
		}
		if(studentNoTemp.toString().length>4){
			studentNum.val(startStr+"000"+endStr);
		}else if(studentNoTemp.toString().length>7){
			studentNum.val(startStr+"000"+endStr);
		}else{
			studentNum=studentNum.val();
		}
		
	}
	
	//用户取消教练，更换教练时学员编号发生改变
		function cancelPersonBinding(personId){
			personId.combobox('setValue',null);
			personId.combobox('setText',null);
		}
	
	//初始化学员编号
	function initStudentNo(studentNum,driverTypeVal,trainerId,studentTypeTemp){
		$.ajax({
					url : '${pageContext.request.contextPath}/demo/studentAction!getMaxStudentNo.action',
					data:{studentNoTypeVal:driverTypeVal},
					dataType : 'json',
					success : function(d) {
						if(validateIsNull(d)){
							studentNum.attr('readonly',false);
						}
						studentNum.val(d.studentNo);
						trainerId.combobox('setValue',d.trainerId);
						studentTypeTemp.combobox('setValue',d.trainerType);//学员类别属性
					},
					error:function(err){
					}
		});
	}
	
	//学员学学费参数处理（Double数据类型）
	function initPriceParams(dialog){
		var f = dialog.find('form');
		feedTemp=f.find('input[name=feed]');
		realFeedTemp=f.find('input[name=realFeed]');
		ownFeedTemp=f.find('input[name=ownFeed]');
		ownFeedEdit=f.find('input[class=demo_student_disable]');
		
		feedTemp.change(function(){
			if(realFeedTemp.val()>feedTemp.val()){
				alert("输入应交费用的金额有误");
			}
			if(realFeedTemp.val()>0&&(realFeedTemp.val()>feedTemp.val())){
				ownFeedTemp.val(feedTemp.val()-realFeedTemp.val());
			}
		});
		realFeedTemp.change(function(){
			//计算欠费值 
			ownFeedTemp.val(feedTemp.val()-realFeedTemp.val());
			if(realFeedTemp.val()>feedTemp.val()){
				realFeedTemp.val('');
				ownFeedTemp.val('');
				alert("输入实收费用的金额有误");
			}
		});
		ownFeedEdit.attr('editable',false);
	}
	
	//选择班级对话框
	function initClazzSelect(stuDriverType,clazzId,organizationId){
		var selectClazzDialog=undefined;
		 selectClazzDialog = parent.sy.dialog({
			title : '选择班级信息列表',
			href : '${pageContext.request.contextPath}/demo/studentAction!toSelectClazzPage.action',
			width : 1024,
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
					pageList : [ 10, 20, 30, 40,50 ,100,200 ], /* 选择每面显示的记录数 */
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
							return formatterDriverType(value, row, index);
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
	
	//选择教练对话框(调用参数，1：选择教练（为业务员），2：反之)
	function initTrainerSelect(stuDriverType,trainerId,organizationId,studentNum,studentTypeTemp,initObjectType){
		var selectTrainerDialog=undefined;
		 selectTrainerDialog = parent.sy.dialog({
			title : '选择教练信息列表',
			href : '${pageContext.request.contextPath}/demo/studentAction!toSelectTrainerPage.action',
			width : 1024,
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
				var carflag=1;
				trainerDatagrid.datagrid({
					url : '${pageContext.request.contextPath}/demo/studentAction!getTrainerDatabyType.action',/* 从后台读取所有user数据 */
					queryParams:{trainerTechingType:stuDriverType,organizationId:organizationId,carBanding:carflag},//carBanding 1;表示查找没有与车辆捆绑的教练
					title : '教练信息管理列表!', /* 表头标题 *
					iconCls : 'icon-save', /* 标题前边的图标 */
					pagination : true, /* 是否显示页码 */
					pagePosition : 'bottom', /* 页码显示的位置 */
					pageSize : 20, /* 每面显示的记录数 */
					pageList : [ 10, 20, 30, 40,50 ,100,200 ], /* 选择每面显示的记录数 */
					fit : true,
					fitColumns : false, //列自动调整功能
					nowrap : true, //以行的形式进行显示
					border : false,
					rownumbers: true,
					idField : 'id', //此属性一定要配置
					sortName : 'codeNo', //列排序
					sortOrder : 'asc', //列排序
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
						width : 100,
						align : 'center'
					} ,{
						title : '<font color="#0099FF">姓名</font>',
						field : 'trainerName',
						width : 80,
						align : 'center'
					} ,
					{
						title : '<font color="#0099FF">编号</font>',
						field : 'trainerCodeNo',
						width : 80,
						align : 'center',
						formatter:function(value,row,index){
							return formatTrainerNo(value, row, index);
						}
					} ,{
						title : '<font color="#0099FF">身份证</font>',
						field : 'trainerIdentity',
						width : 150, //在datagrid中一定要添加列宽度，否则会加长datagrid渲染的时间，消耗系统资源
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
							return formatterDriverType(value, row, index);
						}
						
					},{
						title : '<font color="#0099FF">编号后3位</font>',
						field : 'trainerSubCodeNo',
						width : 80,
						align : 'center'
					} ,{
						title : '<font color="#0099FF">教练属性</font>',
						field : 'trainerType',
						sortable : true,
						width : 90,
						formatter:function(value,row,index){
							return initTrainerType(value, row, index);
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
							//在学员编号中添加教练编号
							var studentNoTemp=studentNum.val();
							var endStr=studentNoTemp.toString().substring(studentNoTemp.length-4,studentNoTemp.length);
							var startStr=null;
							if(studentNoTemp.toString().length>7){
								startStr=studentNoTemp.toString().substring(0,studentNoTemp.length-7);
							}else{
								startStr=studentNoTemp.toString().substring(0,studentNoTemp.length-4);
							}
							studentNum.val(startStr+rows[0].trainerSubCodeNo+endStr);
							
							trainerId.combobox('setValue',rows[0].trainerId);
							studentTypeTemp.combobox('setValue',rows[0].trainerType);
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
						trainerDatagrid.datagrid('hideColumn','trainerSubCodeNo');
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
				    	var nameVal=f.find('input[name=trainerName]');
				    	var identityVal=f.find('input[name=trainerIdentity]');
				    	trainerDatagrid.datagrid('load', {organizationId:organizationId,trainerName:nameVal.val(),trainerIdentityId:identityVal.val()}); 	
				    }
				});
				//重置按钮功能事件
				var btnReset=f.find('a[id=demo_student_resetTrainer]');
				btnReset.linkbutton({
				    iconCls: 'icon-redo',
				    onClick:function(){
				    	//将数据还原显示到点击查询按钮前的数据
				    	trainerDatagrid.datagrid('load', {organizationId:organizationId});
				    	f.find('input[class=input_clean]').val('');
				    }
				});
			}//end onLoad
		});//end diaolog
	}

	//选择业务员对话框
	function initPersonSelect(personId,organizationId,stuDriverType,trainerId,organizationId,studentNum,studentTypeTemp){
		var selectPersonDialog=undefined;
		selectPersonDialog = parent.sy.dialog({
			title : '选择教练信息列表',
			href : '${pageContext.request.contextPath}/demo/studentAction!toSelectPersonPage.action',
			width : 1024,
			height :690,
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
				var trainerDatagridPerson=f.find('table[id=demo_student_selectTrainer]');
				personDatagrid.datagrid({
					url : '${pageContext.request.contextPath}/demo/studentAction!getAllPersons.action',/* 从后台读取所有user数据 */
					queryParams:{organizationId:organizationId,propertyType:0},
					title : '业务员信息管理列表!', /* 表头标题 */
					pagination : true, /* 是否显示页码 */
					pagePosition : 'bottom', /* 页码显示的位置 */
					pageSize : 20, /* 每面显示的记录数 */
					pageList : [ 10, 20, 30, 40,50 ,100,200 ], /* 选择每面显示的记录数 */
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
						width : 150, //在datagrid中一定要添加列宽度，否则会加长datagrid渲染的时间，消耗系统资源
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
				//TAB 教练作为业务员
				trainerDatagridPerson.datagrid({
					url : '${pageContext.request.contextPath}/demo/studentAction!getTrainerDatabyType.action',/* 从后台读取所有user数据 */
					queryParams:{trainerTechingType:stuDriverType,organizationId:organizationId,carBanding:1},//carBanding 1;表示查找没有与车辆捆绑的教练
					title : '教练信息管理列表!', /* 表头标题 *
					iconCls : 'icon-save', /* 标题前边的图标 */
					pagination : true, /* 是否显示页码 */
					pagePosition : 'bottom', /* 页码显示的位置 */
					pageSize : 20, /* 每面显示的记录数 */
					pageList : [ 10, 20, 30, 40,50 ,100,200 ], /* 选择每面显示的记录数 */
					fit : true,
					fitColumns : false, //列自动调整功能
					nowrap : true, //以行的形式进行显示
					border : false,
					rownumbers: true,
					idField : 'id', //此属性一定要配置
					sortName : 'codeNo', //列排序
					sortOrder : 'asc', //列排序
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
						width : 100,
						align : 'center'
					} ,{
						title : '<font color="#0099FF">姓名</font>',
						field : 'trainerName',
						width : 80,
						align : 'center'
					} ,
					{
						title : '<font color="#0099FF">编号</font>',
						field : 'trainerCodeNo',
						width : 80,
						align : 'center',
						formatter:function(value,row,index){
							return formatTrainerNo(value, row, index);
						}
					} ,{
						title : '<font color="#0099FF">身份证</font>',
						field : 'trainerIdentity',
						width : 150, //在datagrid中一定要添加列宽度，否则会加长datagrid渲染的时间，消耗系统资源
					}
					] ],
					//普通列
					columns : [ [ {
						title : '<font color="#0099FF">联系电话</font>',
						field : 'trainerPhone',
						align:'center',
						width : 140
					},{
						title : '<font color="#0099FF">驾照类型</font>',
						field : 'trainerDriverType',
						width : 70,
						align:'center',
						formatter:function(value,row,index){
							return formatterDriverType(value, row, index);
						}
						
					},{
						title : '<font color="#0099FF">教练属性</font>',
						field : 'trainerType',
						width : 90,
						formatter:function(value,row,index){
							return initTrainerType(value, row, index);
						}
					},
					] ],
					//菜单功能设置
					toolbar : [ {
						text : '确定',
						iconCls : 'icon-add',
						handler : function() {
							rows=trainerDatagridPerson.datagrid('getSelections');
							if(rows.length==0){
								parent.sy.messagerAlert('提示信息', '当前没有给学员选配教练^v^！', 'error');
							}
							
							personId.combobox('setValue',rows[0].trainerId);
							selectPersonDialog.dialog('close');
						}
					},  '-', { //'-':减号，将用“｜”隔开按钮
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
							selectPersonDialog.datagrid('clearSelections');
							//取消当前页面的选中行
							selectPersonDialog.datagrid('unselectAll');
						}
					}, '-' ],
					onLoadSuccess : function (data) {
						//修改序号bug
						trainerDatagridPerson.datagrid("fixRownumber");
						trainerDatagridPerson.datagrid('hideColumn','trainerSubCodeNo');
						//datagrid没有数据时显示没有记录信息
						if(data.total==0){
							trainerDatagridPerson.datagrid('appendRow', {
								trainerSchoolAreaName: '<div style="text-align:center;color:red">没有记录信息！</div>' })
							.datagrid('mergeCells', { index: 0, field: 'trainerSchoolAreaName', colspan:4 });
						}
						
					},
				});
				
				
				var tabTemp=f.find('div[id=personTabs]');
				
				//查询按钮功能事件
				var btnSearch=f.find('a[id=demo_student_searchPerson]');
				btnSearch.linkbutton({
				    iconCls: 'icon-search',
				    onClick:function(){
				    	var nameVal=f.find('input[name=personName]');
				    	var identityIdVal=f.find('input[name="personIdentityId"]');
				    	//获取当前选择Tab
				    	var tab = tabTemp.tabs('getSelected');
						var tabIndex = tabTemp.tabs('getTabIndex',tab);
						if(0==tabIndex){
							personDatagrid.datagrid('load', {organizationId:organizationId,propertyType:0,personName:nameVal.val(),personIdentityId:identityIdVal.val()});
						}else if(1==tabIndex){
							trainerDatagridPerson.datagrid('load', {organizationId:organizationId,trainerName:nameVal.val(),trainerIdentity:identityIdVal.val()}); 	
						}
				    }
				});
				//重置按钮功能事件
				var btnReset=f.find('a[id=demo_student_resetPerson]');
				btnReset.linkbutton({
				    iconCls: 'icon-redo',
				    onClick:function(){
				    	//将数据还原显示到点击查询按钮前的数据
				    	var nameVal=f.find('input[name=personName]');
				    	var identityIdVal=f.find('input[name="personIdentityId"]');
				    	//获取当前选择Tab
				    	var tab = tabTemp.tabs('getSelected');
						var tabIndex = tabTemp.tabs('getTabIndex',tab);
						if(0==tabIndex){
							personDatagrid.datagrid('load', {organizationId:organizationId,propertyType:0});
						}else if(1==tabIndex){
							trainerDatagridPerson.datagrid('load', {organizationId:organizationId});
						}
				    	f.find('input[class=input_clean]').val('');
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
							'您要删除当前所选择项信息？系统中与该学员的所有信息将会被删除！！！',
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
	
	function exportDatas() {
		var rows = mydatagrid.datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			parent.sy.messagerConfirm(
							'请确认',
							'您要导出当前所选择项信息？',
							function(r) {
								if (r) {
									for (var i = 0; i < rows.length; i++) {
										ids.push(rows[i].id);
									}
									$("#demo_exportIds").val(ids);
									$('#demo_exprot_form').form({    
									    url:'${pageContext.request.contextPath}/demo/studentAction!toExportPage.action',    
									    success:function(data){    
									        alert("导出成功："+data+",ids:"+$("#demo_exportIds").val());    
									    }    
									});    
									$('#demo_exprot_form').submit(); 
								}
							});
		} else {
			parent.sy.messagerAlert('提示信息', '请勾选要导出的记录！', 'error');
		}
	}

	//查询按钮功能函数
	function mySearch() {
		searchStudentNo($("#search_studentNo"), userSchoolArea);
		mydatagrid.datagrid('load', sy.serializeObject($('#student_searchForm')));
		$("#search_studentNo").val('');
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
	<div data-options="region:'north',border:false,title:'查询功能'" style="height: 115px;overflow: hidden;" align="left">
		<!-- 学员信息查询表单 -->
		<form id="student_searchForm">
			<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
				<tr>
					<th >所属校区:</th>
					<td><input id="demo_student_schoolArea" name="organizationName"style="width:150px;" /></td>
					<th>学员编号：</th>
					<td><input id="search_studentNo" class="keypress_event" name="studentNo" style="width:150px;" /></td>
					<th >姓名:</th>
					<td><input name="name" class="easyui-validatebox keypress_event" data-options="validType:'validChinese'" style="width:150px;" /></td>
					<th>身份证号：</th>
					<td><input name="identityId" class="easyui-validatebox keypress_event" data-options="validType:'validateIdentity[15,18]'"  style="width:150px;" /></td>
					
				</tr>
				<tr>
					<th>报名日期:</th>
					<td><input name="createTimeStart"
						class="easyui-datebox" data-options="editable:false"
						style="width: 95px;" />至:<input name="createTimeEnd"
						class="easyui-datebox" data-options="editable:false"
						style="width: 95px;" /></td>
					<th>手机:</th>
					<td><input name="phone" style="width: 150px;" class="easyui-validatebox keypress_event" data-options="required:false,validType:'validMobile',missingMessage:'请填写学员身手机号'" /></td>
					<th>驾照类型：</th>
					<td><input id="demo_student_indexDriverType" name="driverType" style="width:150px;" /></td>
					<th>学员状态:</th>
					<td>
						<select id="student_search_combobox"
						class="easyui-combobox"  name="nowState" data-options="editable:false" style="width:155px;">
							<option value="">请选择</option>
							<option value=1>在培</option>
							<option value=2>毕业</option>
							<option value=3>退学</option>
							<option value=4>到期</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>教练员：</th>
					<td><input name="trainerName" style="width:150px;" /></td>
					<th>业务员：</th>
					<td><input name="personName" style="width:150px;" /></td>
					<th>学员类型：</th>
					<td>
						<select class="easyui-combobox" name="studentType" style="width:100px;">
	       				 	<option value="">请选择</option>
	       				 	<option value="0" >本校</option>
	       				 	<option value="1">承包</option>
	    				</select>
					</td>
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
		<div id="add-menu" onclick="append();" data-options="iconCls:'icon-add'">增加</div>
		<div id="del-menu" onclick="remove();" data-options="iconCls:'icon-remove'">删除</div>
		<div id="edit-menu" onclick="edit();" data-options="iconCls:'icon-edit'">更新</div>
		<div id="detail-menu" onclick="read();" data-options="iconCls:'icon-filter'">详细信息</div>
	</div>
	<div  style="display: none;">
		<form id="demo_exprot_form" method="post">
			<input id="demo_exportIds"  name="ids" style="width: 155px;"/>
		</form>
	</div>
</body>
</html>

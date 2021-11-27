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
	var tempDate=undefined;
	var userSchoolArea=undefined;
	$(function() {
		$.ajax({
			url : '${pageContext.request.contextPath}/demo/studentAction!getSchoolArea.action',
			dataType : 'json',
			success : function(d) {
				userSchoolArea=d;
			}
		});
		mydatagrid = $('#demo_progress_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/demo/progressAction!personalDatagrid.action',/* 从后台读取所有user数据 */
			title : '学员进度信息管理列表!', /* 表头标题 */
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
			singleSelect:true,
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
				sortable : true,
				width : 150,
				align : 'left',
				formatter:function(value,row,index){
					return formatStudentNo(value, row, index);
				}
			},{
				title : '<font color="#0099FF">学员编号</font>',
				field : 'studentNo',
				width : 100,
				align : 'left'
			},{
				title : '<font color="#0099FF">姓名</font>',
				field : 'studentName',
				width : 100,
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
					title : '<font color="#0099FF">当前进度</font>',
					field : 'state',
					sortable : true,
					width : 100, 
					align : 'center',
					formatter:function(value,row,index){
						if(value==1){
							tempDate='sinupDate';
							return '<font color="#0099FF">报名完成</font>';
						}else if(value==2){
							return '<font color="#0099FF">科一理论</font>';
						}else if(value==3){
							return '<font color="#0099FF">科二五项</font>';
						}else if(value==4){
							return '<font color="#0099FF">科三路考</font>';
						}else if(value==5){
							return '<font color="#0099FF">科四文明</font>';
						}else if(value==6){
							return '<font color="#0099FF">毕业</font>';
						}else if(value==7){
							tempDate='quitSchoolDate';
							return '<font color="red">退学</font>';
						}else{
							return '';
						}
					}
				},{
					title : '<font color="#0099FF">注册日期</font>',
					field : 'sinupdateDate',
					width : 150,
					align : 'left'
				}, {
					title : '<font color="#0099FF">科一理论</font>',
					field : 'subject1Date',
					width : 150,
					align : 'left'
				}, {
					title : '<font color="#0099FF">科二五项</font>',
					field : 'subject2Date',
					width : 150,
					align : 'left'
				}, {
					title : '<font color="#0099FF">科三路考</font>',
					field : 'subject3Date',
					width : 150,
					align : 'left'
				}, {
					title : '<font color="#0099FF">科四文明</font>',
					field : 'subject4Date',
					width : 150,
					align : 'left'
				}, {
					title : '<font color="#0099FF">毕业</font>',
					field : 'graduateDate',
					width : 150,
					align : 'left'
				}, {
					title : '<font color="#0099FF">退学</font>',
					field : 'quitSchoolDate',
					width : 150,
					align : 'left'
				}
			       ]],
			//菜单功能设置
			toolbar : [{
				text : '详细信息',
				iconCls : 'icon-filter',
				handler : function() {
					read();
				}
			}, '-',  {
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
				$('#demo_studentFile_menu').menu('show', {
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
		$(this).datagrid('hideColumn','sinupdateDate');
		$(this).datagrid('hideColumn','subject1Date');
		$(this).datagrid('hideColumn','subject2Date');
		$(this).datagrid('hideColumn','subject3Date');
		$(this).datagrid('hideColumn','subject4Date');
		$(this).datagrid('hideColumn','graduateDate');
		$(this).datagrid('hideColumn','quitSchoolDate');
		//初始化查询相关属性选项值
		//初始化校区选项
		initSchoolArea($("#demo_progress_organization"));
		
		$("#demo_progress_state").combobox({
			url : '${pageContext.request.contextPath}/demo/json/progressState_combobox.json',
			valueField : 'value',
			textField : 'text',
			method:'get',
		});
		//datagrid没有数据时显示没有记录信息
		if(data.total==0){
			mydatagrid.datagrid('appendRow', {
				schoolArea: '<div style="text-align:center;color:red">没有进度登记记录信息！</div>' })
			.datagrid('mergeCells', { index: 0, field: 'comment', colspan:14 });
		}
		
	}
	
	//查看详细信息
	function read() {
		var progressValue=undefined;
		var rows = mydatagrid.datagrid('getSelections');
		//编辑时只能单选一行进行编辑
		if (rows.length == 1) {
			var p = parent.sy.dialog({
						title : '----> 学员进度详细信息  <----',
						href : '${pageContext.request.contextPath}/demo/progressAction!toPersonalProgressDetailPage.action',
						width : 960,
						height : 450,
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
								
								state:rows[0].state,
								sinupdateDate:rows[0].sinupdateDate,
								subject1Date:rows[0].subject1Date,
								subject2Date:rows[0].subject2Date,
								subject3Date:rows[0].subject3Date,
								subject4Date:rows[0].subject4Date,
								graduateDate:rows[0].graduateDate,
								quitSchoolDate:rows[0].quitSchoolDate,
							});
							
							//将学员基本信息属性襟止修改
							var inputDisable=f.find('input[class=demo_progress_detail]');
							inputDisable.attr('disabled',true);
							
							var schoolAreaTemp=f.find('input[name=studentOrganization]');
							initSchoolArea(schoolAreaTemp);
							
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
							
							if(rows[0].state==1){
								progressValue=18;
							}else if(rows[0].state==2){
								progressValue=36;
							}else if(rows[0].state==3){
								progressValue=50;
							}else if(rows[0].state==4){
								progressValue=68;
							}else if(rows[0].state==5){
								progressValue=85;
							}else if(rows[0].state==6){
								progressValue=100;
							}else{
								progressValue=0;
							}
							
							//进度条显示信息
							var progressTemp=f.find('div[id=demo_progress_detail]');
								progressTemp.progressbar({
									width:760,
									value: progressValue, 
							}); 
							//修改文字显示样式及方式
							var titleUi=f.find('ul[id=progressTitle]');
							$(titleUi).css({float:'left','margin-left':'10px',padding:'0px'});
							var titleLi=f.find('ul li[class=progressLi]');
							$(titleLi).css({float:'left',width:'125px','list-style-type':'disc','margin':'0px',   
						    padding:'0px'});
							//显示日期
							var dateUl=f.find('ul[id=progressDate]');
							$(dateUl).css({float:'left','margin-left':'10px',padding:'0px'});
							var dateLi=f.find('ul li[class=progressDateLi]');
							$(dateLi).css({float:'left',width:'125px','list-style-type':'disc','margin':'0px',   
						    padding:'0px'});
							//给时间项初始化
							var singupdate=f.find('ul li[id=dateSinup]');
							var dateSbuject1=f.find('ul li[id=dateSbuject1]');
							var dateSbuject2=f.find('ul li[id=dateSbuject2]');
							var dateSbuject3=f.find('ul li[id=dateSbuject3]');
							var dateSbuject4=f.find('ul li[id=dateSbuject4]');
							var dateGraduate=f.find('ul li[id=dateGraduate]');
							if (rows[0].state>0){
								$(singupdate).append(formatTimeYMD(rows[0].sinupdateDate));
							} 
							if (rows[0].state>1){
								$(dateSbuject1).append(formatTimeYMD(rows[0].subject1Date));
							}
							if (rows[0].state>2){
								$(dateSbuject2).append(formatTimeYMD(rows[0].subject2Date));
							}
							if (rows[0].state>3){
								$(dateSbuject3).append(formatTimeYMD(rows[0].subject3Date));
							}
							if (rows[0].state>4){
								$(dateSbuject4).append(formatTimeYMD(rows[0].subject4Date));	
							}
							if (rows[0].state>5){
								$(dateGraduate).append(formatTimeYMD(rows[0].graduateDate));
							}
							
							//处理学员退学时进度处理（学员在报名，科一，科二，科三，科四，都有可能退学）
							if(rows[0].state==7){
								//1.将非退学显示的文字，日期隐藏
								$(titleUi).hide();
								$(dateUl).hide();
								//2.查找出当前学员退学前的进度情况
								var quitText=f.find('div[id=demo_quitschool_text]');
								var quitDate=f.find('div[id=demo_quitschool_date]');
								if(rows[0].subject4Date!=null){//当前学员进度为科四
									//显示进度状态
									$(quitText).append("<ul id=quitText>"+
											"<li class=quitschoolText style='float: left; width: 110px; list-style-type: disc; margin: 0px; padding: 0px;'>开始</li>"+
											"<li class=quitschoolText style='float: left; width: 110px; list-style-type: disc; margin: 0px; padding: 0px;'>注册</li>"+
											"<li class=quitschoolText style='float: left; width: 110px; list-style-type: disc; margin: 0px; padding: 0px;'>理论</li>"+
											"<li class=quitschoolText style='float: left; width: 110px; list-style-type: disc; margin: 0px; padding: 0px;'>五项</li>"+
											"<li class=quitschoolText style='float: left; width: 110px; list-style-type: disc; margin: 0px; padding: 0px;'>路考</li>"+
											"<li class=quitschoolText style='float: left; width: 110px; list-style-type: disc; margin: 0px; padding: 0px;'>文明</li>"+
											"<li class=quitschoolText style='float: left; width: 110px; list-style-type: disc; margin: 0px; padding: 0px;'>退学</li>"+
											"</ul>");
									//修改进度条
									progressTemp.progressbar({
										width:690,
										value: 100, 
									}).css('margin-left','20px');
									//显示日期
									$(quitDate).append("<ul id=quitDate>"+
											"<li class=quitschoolDate style='float: left; width: 110px; list-style-type: disc; margin: 0px; padding: 0px;'>开始</li>"+
											"<li class=quitschoolDate style='float: left; width: 110px; list-style-type: disc; margin: 0px; padding: 0px;'>"+formatTimeYMD(rows[0].sinupdateDate)+"</li>"+
											"<li class=quitschoolDate style='float: left; width: 110px; list-style-type: disc; margin: 0px; padding: 0px;'>"+formatTimeYMD(rows[0].subject1Date)+"</li>"+
											"<li class=quitschoolDate style='float: left; width: 110px; list-style-type: disc; margin: 0px; padding: 0px;'>"+formatTimeYMD(rows[0].subject2Date)+"</li>"+
											"<li class=quitschoolDate style='float: left; width: 110px; list-style-type: disc; margin: 0px; padding: 0px;'>"+formatTimeYMD(rows[0].subject3Date)+"</li>"+
											"<li class=quitschoolDate style='float: left; width: 110px; list-style-type: disc; margin: 0px; padding: 0px;'>"+formatTimeYMD(rows[0].subject4Date)+"</li>"+
											"<li class=quitschoolDate style='float: left; width: 110px; list-style-type: disc; margin: 0px; padding: 0px;'>"+formatTimeYMD(rows[0].quitSchoolDate)+"</li>"+
											"</ul>");
								}else if(rows[0].subject3Date!=null){
									//显示进度状态
									$(quitText).append("<ul id=quitText>"+
											"<li class=quitschoolText style='float: left; width: 125px; list-style-type: disc; margin: 0px; padding: 0px;'>开始</li>"+
											"<li class=quitschoolText style='float: left; width: 125px; list-style-type: disc; margin: 0px; padding: 0px;'>注册</li>"+
											"<li class=quitschoolText style='float: left; width: 125px; list-style-type: disc; margin: 0px; padding: 0px;'>理论</li>"+
											"<li class=quitschoolText style='float: left; width: 125px; list-style-type: disc; margin: 0px; padding: 0px;'>五项</li>"+
											"<li class=quitschoolText style='float: left; width: 125px; list-style-type: disc; margin: 0px; padding: 0px;'>路考</li>"+
											"<li class=quitschoolText style='float: left; width: 125px; list-style-type: disc; margin: 0px; padding: 0px;'>退学</li>"+
											"</ul>");
									//修改进度条
									progressTemp.progressbar({
										width:650,
										value: 100, 
									}).css('margin-left','20px');
									//显示日期
									$(quitDate).append("<ul id=quitDate>"+
											"<li class=quitschoolDate style='float: left; width: 125px; list-style-type: disc; margin: 0px; padding: 0px;'>开始</li>"+
											"<li class=quitschoolDate style='float: left; width: 125px; list-style-type: disc; margin: 0px; padding: 0px;'>"+formatTimeYMD(rows[0].sinupdateDate)+"</li>"+
											"<li class=quitschoolDate style='float: left; width: 125px; list-style-type: disc; margin: 0px; padding: 0px;'>"+formatTimeYMD(rows[0].subject1Date)+"</li>"+
											"<li class=quitschoolDate style='float: left; width: 125px; list-style-type: disc; margin: 0px; padding: 0px;'>"+formatTimeYMD(rows[0].subject2Date)+"</li>"+
											"<li class=quitschoolDate style='float: left; width: 125px; list-style-type: disc; margin: 0px; padding: 0px;'>"+formatTimeYMD(rows[0].subject3Date)+"</li>"+
											"<li class=quitschoolDate style='float: left; width: 125px; list-style-type: disc; margin: 0px; padding: 0px;'>"+formatTimeYMD(rows[0].quitSchoolDate)+"</li>"+
											"</ul>");
								}else if(rows[0].subject2Date!=null){//科目二后退学
									//显示进度状态
									$(quitText).append("<ul id=quitText>"+
											"<li class=quitschoolText style='float: left; width: 125px; list-style-type: disc; margin: 0px; padding: 0px;'>开始</li>"+
											"<li class=quitschoolText style='float: left; width: 125px; list-style-type: disc; margin: 0px; padding: 0px;'>注册</li>"+
											"<li class=quitschoolText style='float: left; width: 125px; list-style-type: disc; margin: 0px; padding: 0px;'>理论</li>"+
											"<li class=quitschoolText style='float: left; width: 125px; list-style-type: disc; margin: 0px; padding: 0px;'>五项</li>"+
											"<li class=quitschoolText style='float: left; width: 125px; list-style-type: disc; margin: 0px; padding: 0px;'>退学</li>"+
											"</ul>");
									//修改进度条
									progressTemp.progressbar({
										width:530,
										value: 100, 
									}).css('margin-left','20px');;
									//显示日期
									$(quitDate).append("<ul id=quitDate>"+
											"<li class=quitschoolDate style='float: left; width: 125px; list-style-type: disc; margin: 0px; padding: 0px;'>开始</li>"+
											"<li class=quitschoolDate style='float: left; width: 125px; list-style-type: disc; margin: 0px; padding: 0px;'>"+formatTimeYMD(rows[0].sinupdateDate)+"</li>"+
											"<li class=quitschoolDate style='float: left; width: 125px; list-style-type: disc; margin: 0px; padding: 0px;'>"+formatTimeYMD(rows[0].subject1Date)+"</li>"+
											"<li class=quitschoolDate style='float: left; width: 125px; list-style-type: disc; margin: 0px; padding: 0px;'>"+formatTimeYMD(rows[0].subject2Date)+"</li>"+
											"<li class=quitschoolDate style='float: left; width: 125px; list-style-type: disc; margin: 0px; padding: 0px;'>"+formatTimeYMD(rows[0].quitSchoolDate)+"</li>"+
											"</ul>");
								}else if(rows[0].subject1Date!=null){//考试科目一后退学　
									//显示进度状态
									$(quitText).append("<ul id=quitText>"+
											"<li class=quitschoolText style='float: left; width: 200px; list-style-type: disc; margin: 0px; padding: 0px;'>开始</li>"+
											"<li class=quitschoolText style='float: left; width: 200px; list-style-type: disc; margin: 0px; padding: 0px;'>注册</li>"+
											"<li class=quitschoolText style='float: left; width: 200px; list-style-type: disc; margin: 0px; padding: 0px;'>理论</li>"+
											"<li class=quitschoolText style='float: left; width: 200px; list-style-type: disc; margin: 0px; padding: 0px;'>退学</li>"+
											"</ul>");
									//修改进度条
									progressTemp.progressbar({
										width:630,
										value: 100, 
									}).css('margin-left','20px');;
									//显示日期
									$(quitDate).append("<ul id=quitDate >"+
											"<li class=quitschoolDate style='float: left; width: 200px; list-style-type: disc; margin: 0px; padding: 0px;'>开始</li>"+
											"<li class=quitschoolDate style='float: left; width: 200px; list-style-type: disc; margin: 0px; padding: 0px;'>"+formatTimeYMD(rows[0].sinupdateDate)+"</li>"+
											"<li class=quitschoolDate style='float: left; width: 200px; list-style-type: disc; margin: 0px; padding: 0px;'>"+formatTimeYMD(rows[0].subject1Date)+"</li>"+
											"<li class=quitschoolDate style='float: left; width: 200px; list-style-type: disc; margin: 0px; padding: 0px;'>"+formatTimeYMD(rows[0].quitSchoolDate)+"</li>"+
											"</ul>");
								}else if(rows[0].sinupdateDate!=null){//注册完成后退学　
									//显示进度状态
									$(quitText).append("<ul id=quitText>"+
											"<li class=quitschoolText style='float: left; width: 200px; list-style-type: disc; margin: 0px; padding: 0px;'>开始</li>"+
											"<li class=quitschoolText style='float: left; width: 200px; list-style-type: disc; margin: 0px; padding: 0px;'>注册</li>"+
											"<li class=quitschoolText style='float: left; width: 200px; list-style-type: disc; margin: 0px; padding: 0px;'>退学</li>"+
											"</ul>");
									//修改进度条
									progressTemp.progressbar({
										width:430,
										value: 100, 
									}).css('margin-left','20px');
									//显示日期
									$(quitDate).append("<ul id=quitDate >"+
											"<li class=quitschoolDate style='float: left; width: 200px; list-style-type: disc; margin: 0px; padding: 0px;'>开始</li>"+
											"<li class=quitschoolDate style='float: left; width: 200px; list-style-type: disc; margin: 0px; padding: 0px;'>"+formatTimeYMD(rows[0].sinupdateDate)+"</li>"+
											"<li class=quitschoolDate style='float: left; width: 200px; list-style-type: disc; margin: 0px; padding: 0px;'>"+formatTimeYMD(rows[0].quitSchoolDate)+"</li>"+
											"</ul>");
								}
								
								
							}

						}
					});
		} else if (rows.length > 1) {
			parent.sy.messagerAlert('提示信息', '同一时间只能查年一条记录！', 'error');
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
		<table id="demo_progress_datagrid"></table>
	</div>
	<!-- 操作按钮事件 -->
	<div id="demo_studentFile_menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="read();" data-options="iconCls:'icon-filter'">详细信息</div>
	</div>
</body>
</html>
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
	var handupManTemp = undefined;
	var handupManNameTemp = undefined;
	var handupManIdentityTemp = undefined;
	var handupManPhoneTemp = undefined;
	var handupManTypeTemp = undefined;
	var feeDetailTypeTemp = undefined;

	var studentSelectDatagrid = undefined;

	var userSchoolArea = undefined;
	var userACLs=[];
	$(function() {
		userSchoolArea = getUserSchoolArea();
		getUserACL();

		mydatagrid = $('#demo_collectFee_config').datagrid({
			url : '${pageContext.request.contextPath}/demo/collectFeeAction!datagrid.action', /* 从后台读取所有user数据 */
			title : '收费明细列表!', /* 表头标题 */
			pagination : true, /* 是否显示页码 */
			pagePosition : 'bottom', /* 页码显示的位置 */
			pageSize : 20, /* 每面显示的记录数 */
			pageList : [ 10, 20, 30, 40, 50, 100, 200 ], /* 选择每面显示的记录数 */
			fit : true,
			fitColumns : false, //列自动调整功能
			nowrap : false, //以行的形式进行显示
			rownumbers : true,
			idField : 'id', //此属性一定要配置
			sortName : 'createDate', //列排序
			sortOrder : 'desc', //列排序
			checkOnSelect : true,
			selectOnCheck : true,
			striped : true, //表示条纹，隔行显示不现背景色
			nowrap : true, //单元显示
			//冻结列
			frozenColumns : [ [ {
				title : '编号',
				field : 'id',
				width : 20,
				sortable : true,
				checkbox : true
			}, {
				title : '<font color="#0099FF">所属部门</font>',
				field : 'schoolAreaName',
				width : 120,
				align : 'left'
			}, {
				title : '<font color="#0099FF">姓名</font>',
				field : 'handupManName',
				width : 80,
				align : 'center',
			}, {
				title : '<font color="#0099FF">学员编号</font>',
				field : 'handupManNo',
				width : 100,
				align : 'center',
				formatter : formatStudentNo,
			}, {
				title : '<font color="#0099FF">驾照类型</font>',
				field : 'studentDriverType',
				width : 80,
				align : 'center',
				formatter : formatterDriverType,
			}, {
				title : '<font color="#0099FF">收费项目</font>',
				field : 'feeDetailTypeName',
				width : 100,
				align : 'center'
			}, {
				title : '<font color="#0099FF">收费金额</font>',
				field : 'collectTotal',
				sortable : true,
				align : 'center',
				width : 80,
			}, {
				title : '<font color="#0099FF">优惠金额</font>',
				field : 'favouravleFee',
				sortable : true,
				align : 'center',
				width : 80,
			}, {
				title : '<font color="#0099FF">应收金额</font>',
				field : 'mustTotal',
				sortable : true,
				align : 'center',
				width : 80,
			}, {
				title : '<font color="#0099FF">实收金额</font>',
				field : 'realTotal',
				sortable : true,
				align : 'center',
				width : 80,
			}, {
				title : '<font color="#0099FF">欠费金额</font>',
				field : 'ownTotal',
				sortable : true,
				align : 'center',
				width : 80,
			}, ] ],
			//普通列
			columns : [ [ {
				title : '<font color="#0099FF">流水号</font>',
				field : 'collectNo',
				sortable : true,
				width : 160,
				align : 'center',
			}, {
				title : '<font color="#0099FF">交费日期</font>',
				field : 'createTime',
				width : 80,
				sortable : true,
				align : 'center',
				formatter : formatTimeYMD,
			}, {
				title : '<font color="#0099FF">交费人身份证</font>',
				field : 'handupManIdentity',
				width : 150,
				align : 'center',
			}, {
				title : '<font color="#0099FF">交费人手机</font>',
				field : 'handupManPhone',
				width : 150,
				align : 'center',
			}, {
				title : '<font color="#0099FF">资费人类别</font>',
				field : 'handupManType',
				align : 'center',
				sortable : true,
				width : 100,
				formatter:formatHandupManType,
			}, {
				title : '<font color="#0099FF">创建人</font>',
				field : 'operator',
				align : 'center',
				sortable : true,
				width : 80,
				formatter : showAllMsg,
			}, {
				title : '<font color="#0099FF">备注说明</font>',
				field : 'comment',
				align : 'center',
				width : 400,
				formatter : showAllMsg,
			}, {
				title : '<font color="#0099FF">校区标识</font>',
				field : 'schoolArea',
				width : 150
			}, {
				title : '<font color="#0099FF">交费人标识</font>',
				field : 'handupMan',
				width : 150
			},{
				title : '<font color="#0099FF">交费人标识</font>',
				field : 'createDate',
				width : 150
			},{
				title : '<font color="#0099FF">学员性别</font>',
				field : 'studentSex',
				width : 150
			},{
				title : '<font color="#0099FF">申领类型</font>',
				field : 'applyType',
				width : 150
			},{
				title : '<font color="#0099FF">申领类型</font>',
				field : 'qualificationFlag',
				width : 150
			},

			] ],
			//菜单功能设置
			toolbar : [ {
				id:'add-btn-toolbar',
				text : '添加',
				iconCls : 'icon-add',
				handler : function() {
					appendCollectFee();
				}
			}, '-', {
				id:'edit-btn-toolbar',
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					editCollectFee();
				}
			}, '-', {
				id:'del-btn-toolbar',
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					removeCollectFee();
				}
			}, '-', {
				id:'print-btn-toolbar',
				text : '打印',
				iconCls : 'icon-print',
				handler : function() {
					collectPrint();
				}
			}, '-', {
				text : '刷新',
				iconCls : 'icon-reload',
				handler : function() {
					mydatagrid.datagrid('clearSelections');
					//取消当前页面的选中行
					mydatagrid.datagrid('unselectAll');
					mydatagrid.datagrid('uncheckAll');
					mydatagrid.datagrid('reload');
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
			onLoadSuccess : function(data) {
				//修改序号bug
				$(this).datagrid("fixRownumber");
				$(this).datagrid('hideColumn', 'schoolArea');
				$(this).datagrid('hideColumn', 'handupMan');
				$(this).datagrid('hideColumn', 'createDate');
				$(this).datagrid('hideColumn', 'studentSex');
				$(this).datagrid('hideColumn', 'applyType');
				$(this).datagrid('hideColumn', 'qualificationFlag');
				initSchoolArea($("#demo_CollectFee_schoolArea"), userSchoolArea);
				//datagrid没有数据时显示没有记录信息
				if (data.total == 0) {
					$(this).datagrid('appendRow', {
						schoolAreaName : '<div style="text-align:center;color:red">没有记录信息！</div>'
					})
						.datagrid('mergeCells', {
							index : 0,
							field : 'schoolAreaName',
							colspan : 6
						});
				}
			},
			//右键菜单所触发的事件
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				//取消当前页被选中的所有行
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow', rowIndex);
				$('#demo_CollectFee_menu').menu('show', {
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

	function collectPrint() {
		var rows = mydatagrid.datagrid('getSelections');
		//编辑时只能单选一行进行编辑
		if (rows.length == 1) {
			var p = parent.sy.dialog({
				title : '打印',
				href : '${pageContext.request.contextPath}/demo/collectFeeAction!toPrintPage.action',
				width : 700,
				height : 400,
				buttons : [
					{
						text : '打印预览',
						iconCls : 'icon-tip',
						handler : function() {
							var f = p.find('form');
							var btnPrint = p.find('div[id=myMain]')
							btnPrint.jqprint({
							});
						}
					}, {
						text : '打印',
						iconCls : 'icon-tip',
						handler : function() {
							var btnPrint = p.find('div[id=ddd]')
							btnPrint.jqprint();
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
					//收据抬头
					var schoolId=f.find('th[id=schoolAreaId]')
					schoolId.append(rows[0].schoolAreaName);
					//初始化日期
					var date=f.find('td[id=myCreateDate]');
					date.append("<input class='myInputLine' value="+rows[0].createTime+">");
					//初始化序号
					var collectNo = f.find('td[id=collectNo]');
					collectNo.append("<input class='myInputLine' value="+rows[0].collectNo+">");
					//初始化交费人基本信息
					var handupMan = f.find('td[id=handupMan]');
					handupMan.append("<input class='myInputLine' value="+rows[0].handupManName + "(" + rows[0].handupManIdentity +
							")("+rows[0].studentSex+")>");
					//费用项目初始化
					var feeDetailTypeName=f.find('td[id=feeDetailTypeName]');
					feeDetailTypeName.append("<input class='myInputLine' value="+rows[0].feeDetailTypeName+"("+formatApplyType(rows[0].applyType)+"/"+
							formatterDriverType(rows[0].studentDriverType)+"/"+formatQualification(rows[0].qualificationFlag)+")>");
					//人民币初始化
					var realTotal = f.find('td[id=realTotal]');
					realTotal.append("<input style=width:180px value="+toUpperMoney(rows[0].realTotal)+
							">￥(RMB):<input style=width:120px value="+rows[0].realTotal+">元");
					//人员 信息初始化
					var ticketMaker = f.find('td[id=ticketMaker]');
					ticketMaker.append("制单人:<input style=width:80px value=业务组> 收款人:<input style=width:80px value="
							+rows[0].operator+"> 交款人：<input style=width:80px value="+rows[0].handupManName+">");

					//备注初始化
					var centment = f.find('td[id=comment]');
					centment.append("<input style=width:400px value="+rows[0].comment+">");
					//公司名称
					var schoolAreaName = f.find('td[id=schoolAreaName]');
					schoolAreaName.append("<input style=width:250px value="+rows[0].schoolAreaName+">");
					//报名热线

				}
			});
		} else if (rows.length > 1) {
			parent.sy.messagerAlert('提示信息', '同一时间只能打印一条记录！', 'question');
		} else {
			parent.sy.messagerAlert('提示信息', '请选择要打印的记录！', 'question');
		}
	}


	//单击编辑按钮，实现对的编辑功能
	function editCollectFee() {
		var rows = mydatagrid.datagrid('getSelections');
		//编辑时只能单选一行进行编辑
		if (rows.length == 1) {
			var p = parent.sy.dialog({
				title : '编辑收费明细',
				href : '${pageContext.request.contextPath}/demo/collectFeeAction!toCollectFeeEditPage.action',
				width : 1024,
				height : 460,
				buttons : [
					{
						text : '更新',
						iconCls : 'icon-tip',
						handler : function() {
							var f = p.find('form');
							//提交表单
							f.form('submit', {
								url : '${pageContext.request.contextPath}/demo/collectFeeAction!edit.action',
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
					var myForm = p.find('form');
					myForm.form('load', {
						id : rows[0].id,
						handupMan : rows[0].handupMan,
						handupManName : rows[0].handupManName,
						handupManType : rows[0].handupManType,
						handupManIdentity : rows[0].handupManIdentity,
						handupManPhone : rows[0].handupManPhone,
						feeDetailType : rows[0].feeDetailType,
						collectTotal : parseFloat(rows[0].collectTotal).toFixed(2),

						createTime : rows[0].createTime,
						collectNo : rows[0].collectNo,
						comment : rows[0].comment,
						operator : rows[0].operator,
						schoolArea : rows[0].schoolArea,
					});

					//初始化部门选择菜单
					var myDepartment = myForm.find('input[name=schoolArea]');
					initSchoolArea(myDepartment, userSchoolArea);
					//不可编辑属性
					var disableTemp = myForm.find('input[class=demo_collectFee_disable]');
					disableTemp.attr('readonly', true);
					handupManTemp = myForm.find('input[name=handupMan]');
					handupManNameTemp = myForm.find('input[name=handupManName]');
					handupManTypeTemp = myForm.find('input[name=handupManType]');
					handupManIdentityTemp = myForm.find('input[name=handupManIdentity]');
					handupManPhoneTemp = myForm.find('input[name=handupManPhone]');
					feeDetailTypeTemp = myForm.find('input[name=feeDetailType]');
					var searchStudentNoTemp = myForm.find('input[name=feeDetailType]');
					//选择收费人事件
					var handupBtn = myForm.find('a[id=demo_collectFee_a_select]');
					handupBtn.linkbutton({
						iconCls : 'icon-more',
						onClick : function() {
							findStudentInfo(p, userSchoolArea);
						}
					});
					handupBtn.tooltip({
						position : 'top',
						content : '单击选择交费人员',
						onShow : function() {
							handupBtn.tooltip('tip').css({
								backgroundColor : '#00ffcc',
								borderColor : '#666'
							});
						}
					});

					//取消收费人事件
					var cancelBtn = myForm.find('a[id=demo_collectFee_a_cancel]');
					cancelBtn.linkbutton({
						iconCls : 'icon-no',
						onClick : function() {
							handupManTemp.val('');
							handupManNameTemp.val('');
							handupManTypeTemp.val('');
							handupManIdentityTemp.val('');
							handupManPhoneTemp.val('');
			 			}
					});
					cancelBtn.tooltip({
						position : 'top',
						content : '单击取消交费人员',
						onShow : function() {
							cancelBtn.tooltip('tip').css({
								backgroundColor : '#00ffcc',
								borderColor : '#666'
							});
						}
					});

					//初始化收费时间
					var createTimeTemp = myForm.find('input[name=createTime]');
					createTimeTemp.datebox({
						editable : false,
					});
					createTimeTemp.datebox('setValue', new Date().toLocaleDateString());
					//初始化序号
					var orderNotTemp = myForm.find('input[name=collectNo]');
					orderNotTemp.val(dateToDataNo(new Date, 12));
					//初始化收费项目
					var itemNameTemp = myForm.find('input[name=feeDetailType]');
					initFeeItemNameAll(itemNameTemp, rows[0].feeDetailType, myDepartment.combobox('getValue'));
					//初始化收费金额
					var collectTotal = myForm.find('input[name=collectTotal]'); //收费金额

					//初始化收费金额
					initPriceParams(p, itemNameTemp, myDepartment.combobox('getValue'),
						parseFloat(rows[0].favouravleFee).toFixed(2),
						parseFloat(rows[0].realTotal).toFixed(2),
						parseFloat(rows[0].ownTotal).toFixed(2),
						parseFloat(rows[0].mustTotal).toFixed(2));
					handupManTypeTemp.val('1');
				}
			});
		} else if (rows.length > 1) {
			parent.sy.messagerAlert('提示信息', '同一时间只能更新一条记录！', 'error');
		} else {
			parent.sy.messagerAlert('提示信息', '请选择要更新的记录！', 'error');
		}
	}

	//添加收费明细
	function appendCollectFee() {
		var addDialog = parent.sy.dialog({
			title : '添加收费明细',
			href : '${pageContext.request.contextPath}/demo/collectFeeAction!toCollectFeeAddPage.action',
			width : 1024,
			height : 360,
			buttons : [
				{
					text : '添加',
					id : 'demo_collectFee_addBtn',
					iconCls : 'icon-add',
					handler : function() {
						var btnAdd = this;
						$(btnAdd).hide();
						var f = addDialog.find('form');
						var handupMan = f.find('input[name=handupManName]');
						if (NaN == handupMan.val() || null == handupMan.val() || undefined == handupMan.val()) {
							parent.sy.messagerAlert('提示信息', '交费人不能为空！', 'question');
							return;
						}

						f.form('submit', {
							//提交添加表单时，调用UserAction的add方法
							url : '${pageContext.request.contextPath}/demo/collectFeeAction!add.action',
							onSubmit : function() {
								var isValid = f.form('validate');
								if (!isValid) {
									$(btnAdd).show();
								}
							},
							success : function(d) {
								var json = $.parseJSON(d);
								if (json.success) {
									mydatagrid.datagrid('insertRow', {
										index : 0, // index start with 0
										row : json.obj
									});
									addDialog.dialog('close');
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
						addDialog.dialog('close');
					}
				} ],
			//通过onLoad函数实现数据回显
			onLoad : function() {
				var myForm = addDialog.find('form');
				var myDepartment = myForm.find('input[name=schoolArea]');
				initSchoolArea(myDepartment, userSchoolArea);
				//不可编辑属性
				var disableTemp = myForm.find('input[class=demo_collectFee_disable]');
				disableTemp.attr('readonly', true);
				handupManTemp = myForm.find('input[name=handupMan]');
				handupManNameTemp = myForm.find('input[name=handupManName]');
				handupManTypeTemp = myForm.find('input[name=handupManType]');
				handupManIdentityTemp = myForm.find('input[name=handupManIdentity]');
				handupManPhoneTemp = myForm.find('input[name=handupManPhone]');
				feeDetailTypeTemp = myForm.find('input[name=feeDetailType]');
				var searchStudentNoTemp = myForm.find('input[name=feeDetailType]');
				//选择收费人事件
				var handupBtn = myForm.find('a[id=demo_collectFee_a_select]');
				handupBtn.linkbutton({
					iconCls : 'icon-more',
					onClick : function() {
						findStudentInfo(addDialog, userSchoolArea);
					}
				});
				handupBtn.tooltip({
					position : 'top',
					content : '单击选择交费人员',
					onShow : function() {
						handupBtn.tooltip('tip').css({
							backgroundColor : '#00ffcc',
							borderColor : '#666'
						});
					}
				});

				//取消收费人事件
				var cancelBtn = myForm.find('a[id=demo_collectFee_a_cancel]');
				cancelBtn.linkbutton({
					iconCls : 'icon-no',
					onClick : function() {
						handupManTemp.val('');
						handupManNameTemp.val('');
						handupManTypeTemp.val('');
						handupManIdentityTemp.val('');
						handupManPhoneTemp.val('');
					}
				});
				cancelBtn.tooltip({
					position : 'top',
					content : '单击取消交费人员',
					onShow : function() {
						cancelBtn.tooltip('tip').css({
							backgroundColor : '#00ffcc',
							borderColor : '#666'
						});
					}
				});

				//初始化收费时间
				var createTimeTemp = myForm.find('input[name=createTime]');
				createTimeTemp.datebox({
					editable : false,
				});
				createTimeTemp.datebox('setValue', new Date().toLocaleDateString());
				//初始化序号
				var orderNotTemp = myForm.find('input[name=collectNo]');
				orderNotTemp.val(dateToDataNo(new Date, 12));
				//初始化收费项目
				var itemNameTemp = myForm.find('input[name=feeDetailType]');
				initFeeItemNameAll(itemNameTemp, null, myDepartment.combobox('getValue'));
				//初始化收费金额
				var collectTotal = myForm.find('input[name=collectTotal]'); //收费金额

				//初始化收费金额
				initPriceParams(addDialog, itemNameTemp, myDepartment.combobox('getValue'), '0.00', '0.00', '0.00', '0.00');
				handupManTypeTemp.val('1');
			},
		});
	}
	//学员学学费参数处理（Double数据类型）
	function initPriceParams(dialog, itemNameTemp, schoolArea,
		initFavouravleFee, initRealFeed, initOwnFee, initMustTotal) {
		var f = dialog.find('form');
		var collectTotalTemp = f.find('input[name=collectTotal]'); //收费金额
		collectTotalTemp.attr('readonly', true);
		var favouravleFeeTemp = f.find('input[name=favouravleFee]'); //优惠金额
		favouravleFeeTemp.val(initFavouravleFee);
		var realFeedTemp = f.find('input[name=realTotal]'); //实收金额
		realFeedTemp.val(initRealFeed);
		var ownFeedTemp = f.find('input[name=ownTotal]'); //欠费金额
		ownFeedTemp.val(initOwnFee);
		var mustTotalTemp = f.find('input[name=mustTotal]'); //应收金额
		mustTotalTemp.val(initMustTotal);
		mustTotalTemp.attr('readonly', true);

		//初始化收费金额
		itemNameTemp.combobox({
			onChange : function() {
				$.ajax({
					url : '${pageContext.request.contextPath}/demo/collectFeeAction!toInitMoney.action',
					data : {
						schoolArea : schoolArea,
						feeDetailType : itemNameTemp.combobox('getValue')
					},
					dataType : 'json',
					success : function(d) {
						collectTotalTemp.val(parseFloat(d).toFixed(2));
						realFeedTemp.val(parseFloat(d).toFixed(2));
						ownFeedTemp.val('0.00');
						mustTotalTemp.val(parseFloat(d).toFixed(2));
					},
				});
			}
		});
		//优惠金额
		favouravleFeeTemp.change(function() {
			if (favouravleFeeTemp.val() > collectTotalTemp.val()) {
				favouravleFeeTemp.val('')
				alert("输入优惠金额有误");
			}
			if (favouravleFeeTemp.val() > 0 && (collectTotalTemp.val() > favouravleFeeTemp.val())) {
				var temp1 = parseFloat(collectTotalTemp.val()) - parseFloat(favouravleFeeTemp.val());
				realFeedTemp.val(temp1.toFixed(2));
				mustTotalTemp.val(parseFloat(temp1).toFixed(2));
				//欠费＝总－优惠－实收
				if (realFeedTemp.val() > 0 && temp1) {
					var temp2 = parseFloat(temp1) - parseFloat(realFeedTemp.val());
					ownFeedTemp.val(parseFloat(temp2).toFixed(2));
				}
			}
		});
		//实收金额
		realFeedTemp.change(function() {
			//实收＝收费总－优惠
			var temp = parseFloat(collectTotalTemp.val()) - parseFloat(favouravleFeeTemp.val()).toFixed(2);

			if (realFeedTemp.val() > temp) {
				realFeedTemp.val('');
				alert("输入实收金额有误");
			}
			if (realFeedTemp.val() > 0 && temp > 0) {
				var temp2 = temp - parseFloat(realFeedTemp.val());
				ownFeedTemp.val(parseFloat(temp2).toFixed(2));
			}

		});

		ownFeedTemp.attr('readonly', true);
	}

	//初始化收费项目
	function initFeeItemNameAll(obj, initData, schoolArea) {
		obj.combobox({
			url : '${pageContext.request.contextPath}/demo/collectFeeAction!toInitFeeItemName.action',
			queryParams : {
				schoolArea : schoolArea
			},
			valueField : 'value',
			textField : 'text',
			method : 'get',
			editable : false,
			required : true,
			readonly : false,
			onLoadSuccess : function() {
				obj.combobox('setValue', initData);
			},
		});
	}

	function findStudentInfo(addDialog, myDepartment) {

		//1.1 确证是否已经选择所属校区
		if (myDepartment == "") {
			parent.sy.messagerAlert('提示信息', '当前没有选择所属校区，请选择！', 'question');
		} else {
			//==============select student diaolog====
			//选择学员对话框
			var selectDialogAdd = undefined;
			selectDialogAdd = parent.sy.dialog({
				title : '选择交费人',
				href : '${pageContext.request.contextPath}/demo/collectFeeAction!toSelectHandupManPage.action',
				width : 1280,
				height : 750,
				buttons : [
					{
						text : '关闭',
						iconCls : 'icon-edit',
						handler : function() {
							selectDialogAdd.dialog('close');
							addDialog.dialog('close');
						}
					},
				],
				//通过onLoad函数实现数据回显
				onLoad : function() {
					studentSelectDialogOnload(addDialog, selectDialogAdd, myDepartment);
				}
			});
		}

	}
	//选择学员对话框中onload事件
	function studentSelectDialogOnload(addDialog, selectDialogAdd, myDepartment) {
		//符合预约学员信息列表   ===================begin===============
		var f = selectDialogAdd.find('form');
		var myForm = addDialog.find('form');
		handupManTemp = myForm.find('input[name=handupMan]');
		handupManNameTemp = myForm.find('input[name=handupManName]');
		handupManTypeTemp = myForm.find('input[name=handupManType]');
		handupManIdentityTemp = myForm.find('input[name=handupManIdentity]');
		handupManPhoneTemp = myForm.find('input[name=handupManPhone]');
		var searchStudentNoTemp = f.find('input[name=studentNo]');
		//初始化所属校区选项
		var organization = f.find('input[name=schoolArea]');
		initSchoolArea(organization, myDepartment);
		//(选择学员页面)驾照类型初始化
		driverTypeCombobox = f.find('input[name=studentDriverType]');
		initDriverType(driverTypeCombobox, 6);

		//查询按钮，重置按钮功能
		var searchBtn = f.find('a[id=reservation_a_search]');
		searchBtn.linkbutton({
			iconCls : 'icon-search',
			onClick : function() {
				organization.val(myDepartment);
				searchStudentNo(searchStudentNoTemp, userSchoolArea);
				studentSelectDatagrid.datagrid('load', sy.serializeObject(f));
				searchStudentNoTemp.val('');
			}
		});
		//学员编号添加回车事件
		searchStudentNoTemp.unbind('keypress');
		searchStudentNoTemp.bind('keypress', function(event) {
			if (event.keyCode == "13") {
				organization.val(myDepartment);
				searchStudentNo(searchStudentNoTemp, userSchoolArea);
				studentSelectDatagrid.datagrid('load', sy.serializeObject(f));
				searchStudentNoTemp.val('');
			}
		});

		//取消按钮事件
		var resetBtn = f.find('a[id=reservation_a_cancel]');
		resetBtn.linkbutton({
			iconCls : 'icon-cancel',
			onClick : function() {
				//将数据还原显示到点击查询按钮前的数据
				studentSelectDatagrid.datagrid('load', {
					schoolArea : myDepartment,
					studentDriverType : 6
				});
				driverTypeCombobox.combobox('setValue', 6); //业务类型默认为Ｃ1
				f.find('input[class=demo_reservation_clear]').val('');
				f.find('input[name=registerTimeStart]').val('');
				f.find('input[name=registerTimeEnd]').val('');
			}
		});

		//选择学员页面datagrid初始化
		initStudentDatagrid(addDialog, selectDialogAdd, myDepartment);
	}

	//初始化学员datagrid
	function initStudentDatagrid(addDialog, selectDialogAdd, myDepartment) {
		var f = selectDialogAdd.find('form');
		studentSelectDatagrid = f.find('table[id=demo_reservation_selectStudent]');
		studentSelectDatagrid.datagrid({
			url : '${pageContext.request.contextPath}/demo/reservationAction!getAllReservationStudents.action',
			queryParams : {
				schoolArea : myDepartment
			},
			pagination : true, /* 是否显示页码 */
			pagePosition : 'bottom', /* 页码显示的位置 */
			pageSize : 20, /* 每面显示的记录数 */
			pageList : [ 10, 20, 30, 40,, 50, 100, 500 ], /* 选择每面显示的记录数 */
			fit : true,
			fitColumns : false, //列自动调整功能
			nowrap : false, //以行的形式进行显示
			border : false,
			rownumbers : true,
			idField : 'studentId', //此属性一定要配置
			sortName : 'studentNo', //列排序 //TODO
			sortOrder : 'desc', //列排序
			checkOnSelect : true,
			selectOnCheck : true,
			striped : true, //表示条纹，隔行显示不现背景色
			nowrap : true, //单元显示
			singleSelect : true,
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
				formatter : formatterDriverType,
			}, {
				title : '<font color="#0099FF">姓名</font>',
				field : 'studentName',
				width : 80,
				align : 'center'
			}, {
				title : '<font color="#0099FF">学员编号</font>',
				field : 'studentNo',
				width : 100,
				sortable : true,
				align : 'center',
				formatter : formatStudentNo,
			} ] ],
			//普通列
			columns : [ [ {
				title : '<font color="#0099FF">性别</font>',
				field : 'studentSex',
				width : 60,
				align : 'center',
			}, {
				title : '<font color="#0099FF">学员类别</font>',
				field : 'studentType',
				width : 70,
				sortable : true,
				align : 'center',
				formatter : initStudentType,
			}, {
				title : '<font color="#0099FF">报名日期</font>',
				field : 'studentCreateTime',
				width : 80,
				align : 'center',
				formatter : formatTimeYMD,
			}, {
				title : '<font color="#0099FF">计时类型</font>',
				field : 'timingFlag',
				width : 70,
				align : 'center',
				formatter : initTimingCountType,
			}, {
				title : '<font color="#0099FF">状态</font>',
				field : 'studentNowstate',
				width : 80,
				formatter : formatStudentState,
			}, {
				title : '<font color="#0099FF">身份证号</font>',
				field : 'studentIdentityId',
				width : 150
			}, {
				title : '<font color="#0099FF">联系电话</font>',
				field : 'studentPhone',
				width : 150
			}, {
				title : '<font color="#0099FF">教练</font>',
				field : 'trainerName',
				width : 100,
				align : 'center'
			}, {
				title : '<font color="#0099FF">教练标识</font>',
				field : 'trainerId',
				width : 150,
				align : 'center'
			}, {
				title : '<font color="#0099FF">教练身份证</font>',
				field : 'trainerIdentityId',
				width : 150,
				align : 'center'
			}, {
				title : '<font color="#0099FF">教练标识</font>',
				field : 'trainerId',
				width : 150,
				align : 'center'
			}, {
				title : '<font color="#0099FF">教练电话</font>',
				field : 'trainerPhone',
				width : 150,
				align : 'center'
			}, {
				title : '<font color="#0099FF">施教类型</font>',
				field : 'trainerTeachingType',
				width : 150,
				align : 'center'
			}, {
				title : '<font color="#0099FF">排班类型</font>',
				field : 'arrangeType',
				width : 150,
				align : 'center'
			},

			] ],
			toolbar : [
				{
					text : '确认',
					iconCls : 'icon-add',
					handler : function() {

						var rows = studentSelectDatagrid.datagrid('getChecked');
						//TODO 校验如果校区选择为空应该给出提示信息
						if (rows.length > 0) {
							//从表中获取学员的姓名和身份证号
							handupManTemp.val(rows[0].studentId);
							handupManNameTemp.val(rows[0].studentName);
							handupManIdentityTemp.val(rows[0].studentIdentityId);
							handupManPhoneTemp.val(rows[0].studentPhone);
							selectDialogAdd.dialog('close');

						} else if (reservationTypeCombobox.combobox('getValue') == "") {
							parent.sy.messagerAlert('提示信息', '请选择预约类型！！', 'question');
						} else {
							parent.sy.messagerAlert('提示信息', '您当前没有选择学员信息！', 'error');
						}
					}
				}, {
					text : '取消选中',
					iconCls : 'icon-undo',
					handler : function() {
						var rows = studentSelectDatagrid.datagrid('getChecked');
						if (rows.length <= 0) {
							parent.sy.messagerAlert('提示信息', '您当前没有选中行信息！', 'error');
						}
						//取消所有的选中行
						studentSelectDatagrid.datagrid('clearSelections');
						//取消当前页面的选中行
						studentSelectDatagrid.datagrid('unselectAll');
					}
				},

				'-' ],
			onLoadSuccess : function() {
				//修改序号bug
				studentSelectDatagrid.datagrid("fixRownumber");
				studentSelectDatagrid.datagrid('hideColumn', 'trainerId');
				studentSelectDatagrid.datagrid('hideColumn', 'trainerIdentityId');
				studentSelectDatagrid.datagrid('hideColumn', 'trainerPhone');
				studentSelectDatagrid.datagrid('hideColumn', 'trainerTeachingType');
				studentSelectDatagrid.datagrid('hideColumn', 'arrangeType');
			},
		}); /////==============end datagrid=============
	}

	//删除
	function removeCollectFee() {
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
							url : '${pageContext.request.contextPath}/demo/collectFeeAction!delete.action',
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

	//查询按钮功能函数
	function mySearch() {
		mydatagrid.datagrid('load', sy.serializeObject($('#demo_sms_searchForm')));
	}

	//取消查询功能按钮
	function cleanSearch() {
		mydatagrid.datagrid('load', {
			propertyType : 0
		});
		//清空与查询有关的输入框
		$('.input_clean').val('');
		$('#demo_sms_searchForm input').val('');
	}
</script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询功能'"
		style="height: 100%;overflow: hidden;" align="left">
		<!-- 人员信息查询表单 -->
		<form id="demo_sms_searchForm">
			<input type="hidden" id="propertyTypeVal" name="propertyType"
				value="0">
			<table class="tableForm datagrid-toolbar"
				style="width: 100%;height: 100%;">
				<tr>
					<th style="width: 65px;">所属部门:</th>
					<td><input id="demo_CollectFee_schoolArea" name="schoolArea"
						style="width:150px;" /></td>
					<th style="width: 65px;">姓名:</th>
					<td><input name="name" class="input_clean"
						style="width:120px;" /></td>
					<th style="width: 65px;">手机号：</th>
					<td><input name="phone" class="input_clean"
						style="width:120px;" /></td>
					<th style="width: 65px;">发送人：</th>
					<td><input name="operator" class="input_clean"
						style="width:120px;" /></td>
				</tr>
				<tr>
					<th style="width: 65px;">发送日期:</th>
					<td><input id="input_clean_start" name="dateStart"
						class="easyui-datebox" data-options="editable:false"
						style="width: 95px;" />至:<input id="input_clean_end"
						name="dateEnd" class="easyui-datebox"
						data-options="editable:false" style="width: 95px;" /></td>
					<td colspan="2"><a href="javascript:void(0);"
						id="sms_a_searche" class="easyui-linkbutton" iconCls="icon-search"
						onclick="mySearch();">查询</a> <a href="javascript:void(0);"
						id="sms_a_canel" class="easyui-linkbutton" iconCls="icon-cancel"
						onclick="cleanSearch();">取消</a></td>
				</tr>
			</table>
		</form>
		<div id="feeTabs" class="easyui-tabs" style="width:100%;height:90%;">
			<div title="收费配置" style="padding:2px;">
				<table id="demo_collectFee_config" class="tableForm"></table>
			</div>
			<div title="收费人员" closable="false" style="padding:2px;">
				<table id="demo_CollectFee_person" class="tableForm"></table>
			</div>
		</div>
	</div>

	<!-- 操作按钮事件 -->

	<div id="demo_CollectFee_menu" class="easyui-menu"
		style="width:120px;display: none;">
		<div id="add-menu" onclick="appendCollectFee();" data-options="iconCls:'icon-add'">增加</div>
		<div id="del-menu" onclick="removeCollectFee();" data-options="iconCls:'icon-remove'">删除</div>
		<div id="edit-menu" onclick="editCollectFee();" data-options="iconCls:'icon-edit'">更新</div>
		<div id="detail-menu" onclick="collectPrint();" data-options="iconCls:'icon-print'">打印</div>
	</div>
</body>
</html>
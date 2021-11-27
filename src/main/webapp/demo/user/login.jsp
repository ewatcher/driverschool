<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" charset="utf-8">
	var loginDialog;
	var loginTabs;
	var loginInputForm;
	var loginDatagridForm;
	var loginComboboxForm;
	var loginDatagridName;
	var loginComboboxName;
	var sessionInfo;
	$(function() {

		var formParam = {
			url : '${pageContext.request.contextPath}/demo/userAction!doNotNeedSession_login.action',
			success : function(data) {
				var d = $.parseJSON(data);
				if (d.success) {
					location.reload(true);
					loginDialog.dialog('close');

					$('#sessionInfoDiv').html(sy.showtip('[<strong>{0}</strong>]，欢迎你！您使用[<strong>{1}</strong>]IP登录！', d.obj.loginName, d.obj.ip));

					flashOnlineDatagrid();
				}
				$.messager.show({
					msg : d.msg,
					title : '提示'
				});
			}
		};

		loginInputForm = $('#loginInputForm').form(formParam);

		loginDialog = $('#loginDialog').show().dialog({
			closable : false,
			title : '登录',
			modal : true,
			buttons : [  {
				text : '登录',
				handler : function() {
					if ($('#onlineDatagrid').length > 0) {
						var f = loginTabs.tabs('getSelected').find('form');
						f.submit();
					}
				}
			},],
			onOpen : function() {
				var t = $(this);
				window.setTimeout(function() {
					t.find('input[name=cname]').focus();
				}, 0);
			}
		});

		loginTabs = $('#loginTabs').tabs({
			onSelect : function(title) {
				if ('输入方式' == title) {
					window.setTimeout(function() {
						$('div.validatebox-tip').remove();
						loginInputForm.find('input[name=cname]').focus();
					}, 0);
				} 
			}
		});

		loginDatagridName = $('#loginDatagridName').combogrid({
			required : true,
			loadMsg : '数据加载中....',
			panelWidth : 440,
			panelHeight : 180,
			required : true,
			fitColumns : true,
			value : '',
			idField : 'cname',
			textField : 'cname',
			mode : 'remote',
			url : '',
			pagination : true,
			pageSize : 5,
			pageList : [ 5, 10 ],
			columns : [ [ {
				field : 'cid',
				title : '编号',
				width : 60,
				hidden : true
			}, {
				field : 'cname',
				title : '登录名',
				width : 100,
				sortable : true
			}, {
				field : 'ccreatedatetime',
				title : '创建时间',
				width : 150,
				sortable : true
			}, {
				field : 'cmodifydatetime',
				title : '最后修改时间',
				width : 150,
				sortable : true
			} ] ],
			delay : 500
		});

		loginComboboxName = $('#loginComboboxName').combobox({
			required : true,
			url : '',
			textField : 'cname',
			valueField : 'cname',
			mode : 'remote',
			panelHeight : 'auto',
			delay : 500,
			value : ''
		});

		$('form input').bind('keyup', function(event) {/* 增加回车提交功能 */
			if (event.keyCode == '13') {
				$(this).submit();
			}
		});

		sessionInfo = {
			userId : '${sessionInfo.userId}',
			loginName : '${sessionInfo.loginName}',
			menuIds:'${sessionInfo.menuIds}',
			schoolArea:'${sessionInfo.schoolArea}',
			schoolAreaName:'${sessionInfo.schoolAreaName}',
			ip : '${sessionInfo.ip}'
		};
		if (sessionInfo.userId) {/*如果有userId说明已经登陆过了*/
			window.setTimeout(function() {
				$('div.validatebox-tip').remove();
			}, 50000);
			loginDialog.dialog('close');
			flashOnlineDatagrid();
		}

	});

	function flashOnlineDatagrid() {/*刷新在线列表*/
		window.setTimeout(function() {
			if ($('#onlineDatagrid').length > 0) {
				onlineDatagrid.datagrid({
					pagination : true,
					url : '${pageContext.request.contextPath}/demo/onlineAction!doNotNeedSession_onlineDatagrid.action'
				});
				var p = onlineDatagrid.datagrid('getPager');
				p.pagination({
					showPageList : false,
					showRefresh : false,
					beforePageText : '',
					afterPageText : '/{pages}',
					displayMsg : ''
				});
			}
		}, 300);
	}
</script>
<div id="loginDialog" style="width:250px;height:210px;display: none;overflow: hidden;">
	<div id="loginTabs" data-options="fit:true,border:false">
		<div title="输入方式" style="overflow: hidden;">
			<div class="info">
			</div>
			<div align="center">
				<form id="loginInputForm" method="post">
					<table>
						<tr>
							<th style="width:75px">登录名</th>
							<td><input name="cname" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写登录名称'"  style="width: 150px;"/></td>
						</tr>
						<tr>
							<th style="width:75px">密码</th>
							<td><input name="cpwd" type="password" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写登录密码'"  style="width: 150px;" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		
	</div>
</div>
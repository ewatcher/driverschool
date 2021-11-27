<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="utf-8">
	var portal;
	var panels;
	$(function() {
		
		panels = [ {
			id : 'p1',
			title : '----> 学员数据分析  <----',
			height : 232,
			collapsible : true,
			href:'${pageContext.request.contextPath}/demo/layout/portal/studentAnalys.jsp'
		}, {
			id : 'p2',
			title : '----> 分校学员统计  <----',
			height : 232,
			collapsible : true,
			href:'${pageContext.request.contextPath}/demo/layout/portal/childrenAnalys.jsp'
		}, {
			id : 'p3',
			title : '----> 教练数据分析  <----',
			height : 232,
			collapsible : true,
			href:'${pageContext.request.contextPath}/demo/layout/portal/trainerAnalys.jsp'
		}, {
			id : 'p4',
			title : '----> 报名统计与分析  <----',
			height : 232,
			collapsible : true,
			href:'${pageContext.request.contextPath}/demo/layout/portal/signupAnalys.jsp'
		} ,{
			id : 'p5',
			title : '----> 考试数据分析  <----',
			height : 232,
			collapsible : true,
			href:'${pageContext.request.contextPath}/demo/layout/portal/examAnalys.jsp'
		},{
			id : 'p6',
			title : '----> 车辆数据统计分析  <----',
			height : 232,
			collapsible : true,
			href:'${pageContext.request.contextPath}/demo/layout/portal/carAnalys.jsp'
		} ];

		portal = $('#portal').portal({
			border : false,
			fit : true,
			onStateChange : function() {
				$.cookie('portal-state', getPortalState(), {
					expires : 7
				});
			}
		});
		var state = $.cookie('portal-state');
		if (!state) {
			state = 'p1,p2,p3:p4,p5,p6';/*冒号代表列，逗号代表行*/
		}
		addPanels(state);
		portal.portal('resize');

	});

	function getPanelOptions(id) {
		for ( var i = 0; i < panels.length; i++) {
			if (panels[i].id == id) {
				return panels[i];
			}
		}
		return undefined;
	}
	function getPortalState() {
		var aa=[];
		for(var columnIndex=0;columnIndex<2;columnIndex++) {
			var cc=[];
			var panels=portal.portal('getPanels',columnIndex);
			for(var i=0;i<panels.length;i++) {
				cc.push(panels[i].attr('id'));
			}
			aa.push(cc.join(','));
		}
		return aa.join(':');
	}
	function addPanels(portalState) {
		var columns = portalState.split(':');
		for (var columnIndex = 0; columnIndex < columns.length; columnIndex++) {
			var cc = columns[columnIndex].split(',');
			for (var j = 0; j < cc.length; j++) {
				var options = getPanelOptions(cc[j]);
				if (options) {
					var p = $('<div/>').attr('id', options.id).appendTo('body');
					p.panel(options);
					portal.portal('add', {
						panel : p,
						columnIndex : columnIndex
					});
				}
			}
		}
	}
</script>
<div id="portal" style="position:relative">
	<div></div>
	<div></div>
</div>
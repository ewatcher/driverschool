<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" charset="utf-8">
	
	$(function() {
		initMenu();
	});
	
	function initMenu(){
		var treeNode=undefined;
		$.ajax({
			url : '${pageContext.request.contextPath}/demo/aclAction!toGainMainMenus.action',
			type: 'POST',//提交方式 可以选择post/get 推荐post   
			async: false,//同步异步
			dataType : 'json',
			success : function(data) {
				//1.获取主菜单数据
				for(var i=0;i<data.length;i++){
					$('#sysMenu').append("<div id="+data[i].value+" title="+data[i].text+"><ul id="+data[i].value+"></ul></div>");
					//2.初始化树子节点
					var idTemp=data[i].value;
					$('#'+idTemp).tree({
						url : '${pageContext.request.contextPath}/demo/aclAction!toGainSingleNode.action',
						queryParams:{mainMenuId:idTemp},
						lines : true,
						onClick : function(node) {
								addTab(node);
							},
						onDblClick : function(node) {
							if (node.state == 'closed') {
									$(this).tree('expand', node.target);
								} else {
									$(this).tree('collapse', node.target);
								}
						}
				});
				}
			}
		});
	}
</script>
<div id="sysMenu" class="easyui-accordion" data-options="fit:true,border:false,selected:-1"></div>


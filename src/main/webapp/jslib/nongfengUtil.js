/**
 * 包含easyui的扩展和常用的方法
 * 
 * @author 农峰
 * 
 * @version 20160401
 */

var sy = $.extend({}, sy);/* 定义全局对象，类似于命名空间或包的作用 */

/**
 * 
 * 取消easyui默认开启的parser
 * 
 * 在页面加载之前，先开启一个进度条
 * 
 * 然后在页面所有easyui组件渲染完毕后，关闭进度条
 * 
 * @author 农峰
 * 
 * @requires jQuery,EasyUI
 * 
 */
$.parser.auto = false;
$(function() {
	$.messager.progress({
		text : '页面加载中....',
		interval : 100
	});
	$.parser.parse(window.document);
	window.setTimeout(function() {
		$.messager.progress('close');
		if (self != parent) {
			window.setTimeout(function() {
				try {
					parent.$.messager.progress('close');
				} catch (e) {
				}
			}, 500);
		}
	}, 1);
	$.parser.auto = true;
});

/**
 * 使panel和datagrid在加载时提示
 * 
 * @author 农峰
 * 
 * @requires jQuery,EasyUI
 * 
 */
$.fn.panel.defaults.loadingMessage = '加载中....';
$.fn.datagrid.defaults.loadMsg = '加载中....';

/**
 * @author 农峰
 * 
 * @requires jQuery,EasyUI
 * 
 * 避免验证tip屏幕跑偏
 */
var removeEasyuiTipFunction = function() {
	window.setTimeout(function() {
		$('div.validatebox-tip').remove();
	}, 0);
};
$.fn.panel.defaults.onClose = removeEasyuiTipFunction;
$.fn.window.defaults.onClose = removeEasyuiTipFunction;
$.fn.dialog.defaults.onClose = removeEasyuiTipFunction;

/**
 * @author 农峰
 * 
 * @requires jQuery,EasyUI
 * 
 * 通用错误提示
 * 
 * 用于datagrid/treegrid/tree/combogrid/combobox/form加载数据出错时的操作
 */
var easyuiErrorFunction = function(XMLHttpRequest) {
	$.messager.progress('close');
	$.messager.alert('错误', XMLHttpRequest.responseText);
};
$.fn.datagrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.treegrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.tree.defaults.onLoadError = easyuiErrorFunction;
$.fn.combogrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.combobox.defaults.onLoadError = easyuiErrorFunction;
$.fn.form.defaults.onLoadError = easyuiErrorFunction;

/**
 * @author 农峰
 * 
 * @requires jQuery,EasyUI
 * 
 * 为datagrid、treegrid增加表头菜单，用于显示或隐藏列，注意：冻结列不在此菜单中
 */
var createGridHeaderContextMenu = function(e, field) {
	e.preventDefault();
	var grid = $(this);/* grid本身 */
	var headerContextMenu = this.headerContextMenu;/* grid上的列头菜单对象 */
	if (!headerContextMenu) {
		var tmenu = $('<div style="width:100px;"></div>').appendTo('body');
		var fields = grid.datagrid('getColumnFields');
		for (var i = 0; i < fields.length; i++) {
			var fildOption = grid.datagrid('getColumnOption', fields[i]);
			if (!fildOption.hidden) {
				$('<div iconCls="icon-ok" field="' + fields[i] + '"/>').html(
						fildOption.title).appendTo(tmenu);
			} else {
				$('<div iconCls="icon-empty" field="' + fields[i] + '"/>')
						.html(fildOption.title).appendTo(tmenu);
			}
		}
		headerContextMenu = this.headerContextMenu = tmenu.menu({
			onClick : function(item) {
				var field = $(item.target).attr('field');
				if (item.iconCls == 'icon-ok') {
					grid.datagrid('hideColumn', field);
					$(this).menu('setIcon', {
						target : item.target,
						iconCls : 'icon-empty'
					});
				} else {
					grid.datagrid('showColumn', field);
					$(this).menu('setIcon', {
						target : item.target,
						iconCls : 'icon-ok'
					});
				}
			}
		});
	}
	headerContextMenu.menu('show', {
		left : e.pageX,
		top : e.pageY
	});
};
$.fn.datagrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;
$.fn.treegrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;

/**
 * @author 农峰
 * 
 * @requires jQuery,EasyUI
 * 
 * 防止panel/window/dialog组件超出浏览器边界
 * @param left
 * @param top
 */
var easyuiPanelOnMove = function(left, top) {
	var l = left;
	var t = top;
	if (l < 1) {
		l = 1;
	}
	if (t < 1) {
		t = 1;
	}
	var width = parseInt($(this).parent().css('width')) + 14;
	var height = parseInt($(this).parent().css('height')) + 14;
	var right = l + width;
	var buttom = t + height;
	var browserWidth = $(window).width();
	var browserHeight = $(window).height();
	if (right > browserWidth) {
		l = browserWidth - width;
	}
	if (buttom > browserHeight) {
		t = browserHeight - height;
	}
	$(this).parent().css({/* 修正面板位置 */
		left : l,
		top : t
	});
};
$.fn.dialog.defaults.onMove = easyuiPanelOnMove;
$.fn.window.defaults.onMove = easyuiPanelOnMove;
$.fn.panel.defaults.onMove = easyuiPanelOnMove;

/**
 * @author 农峰
 * 
 * @requires jQuery,EasyUI
 * 
 * panel关闭时回收内存
 */
$.fn.panel.defaults.onBeforeDestroy = function() {
	var frame = $('iframe', this);
	try {
		if (frame.length > 0) {
			for (var i = 0; i < frame.length; i++) {
				frame[i].contentWindow.document.write('');
				frame[i].contentWindow.close();
			}
			frame.remove();
			if ($.browser.msie) {
				CollectGarbage();
			}
		}
	} catch (e) {
	}
};

/**
 * @author 农峰
 * 
 * @requires jQuery,EasyUI
 * 
 * 扩展validatebox，添加验证两次密码功能
 */
$.extend($.fn.validatebox.defaults.rules, {
	eqPassword : {
		validator : function(value, param) {
			return value == $(param[0]).val();
		},
		message : '密码不一致！'
	}
});

/**
 * @author 农峰
 * 
 * @requires jQuery,EasyUI
 * 
 * 扩展datagrid，添加动态增加或删除Editor的方法
 * 
 * 例子如下，第二个参数可以是数组
 * 
 * datagrid.datagrid('removeEditor', 'cpwd');
 * 
 * datagrid.datagrid('addEditor', [ { field : 'ccreatedatetime', editor : { type :
 * 'datetimebox', options : { editable : false } } }, { field :
 * 'cmodifydatetime', editor : { type : 'datetimebox', options : { editable :
 * false } } } ]);
 * 
 */
$.extend($.fn.datagrid.methods, {
	addEditor : function(jq, param) {
		if (param instanceof Array) {
			$.each(param, function(index, item) {
				var e = $(jq).datagrid('getColumnOption', item.field);
				e.editor = item.editor;
			});
		} else {
			var e = $(jq).datagrid('getColumnOption', param.field);
			e.editor = param.editor;
		}
	},
	removeEditor : function(jq, param) {
		if (param instanceof Array) {
			$.each(param, function(index, item) {
				var e = $(jq).datagrid('getColumnOption', item);
				e.editor = {};
			});
		} else {
			var e = $(jq).datagrid('getColumnOption', param);
			e.editor = {};
		}
	}
});

/**
 * @author 农峰
 * 
 * @requires jQuery,EasyUI
 * 
 * 扩展datagrid的editor
 * 
 * 增加带复选框的下拉树
 * 
 * 增加日期时间组件editor
 * 
 * 增加多选combobox组件
 */
$.extend($.fn.datagrid.defaults.editors, {
	combocheckboxtree : {
		init : function(container, options) {
			var editor = $('<input />').appendTo(container);
			options.multiple = true;
			editor.combotree(options);
			return editor;
		},
		destroy : function(target) {
			$(target).combotree('destroy');
		},
		getValue : function(target) {
			return $(target).combotree('getValues').join(',');
		},
		setValue : function(target, value) {
			$(target).combotree('setValues', sy.getList(value));
		},
		resize : function(target, width) {
			$(target).combotree('resize', width);
		}
	},
	datetimebox : {
		init : function(container, options) {
			var editor = $('<input />').appendTo(container);
			editor.datetimebox(options);
			return editor;
		},
		destroy : function(target) {
			$(target).datetimebox('destroy');
		},
		getValue : function(target) {
			return $(target).datetimebox('getValue');
		},
		setValue : function(target, value) {
			$(target).datetimebox('setValue', value);
		},
		resize : function(target, width) {
			$(target).datetimebox('resize', width);
		}
	},
	multiplecombobox : {
		init : function(container, options) {
			var editor = $('<input />').appendTo(container);
			options.multiple = true;
			editor.combobox(options);
			return editor;
		},
		destroy : function(target) {
			$(target).combobox('destroy');
		},
		getValue : function(target) {
			return $(target).combobox('getValues').join(',');
		},
		setValue : function(target, value) {
			$(target).combobox('setValues', sy.getList(value));
		},
		resize : function(target, width) {
			$(target).combobox('resize', width);
		}
	}
});

/**
 * @author 农峰 创建动态的对话框
 * @requires jQuery,EasyUI
 * 
 * @param options
 */
sy.dialog = function(options) {
	var opts = $.extend({
		modal : true,// 模态对话框
		// 动态创建的对话框需要编写关闭，避免内存泄露
		onClose : function() {
			$(this).dialog('destroy');
		}
	}, options);
	return $('<div/>').dialog(opts);
};

/**
 * @author 农峰
 * 
 * @requires jQuery,EasyUI
 * 
 * @param title
 *            标题
 * 
 * @param msg
 *            提示信息
 * 
 * @param fun
 *            回调方法
 */
sy.messagerConfirm = function(title, msg, fn) {
	return $.messager.confirm(title, msg, fn);
};

/**
 * @author 农峰
 * 
 * @requires jQuery,EasyUI
 */
sy.messagerShow = function(options) {
	return $.messager.show(options);
};

/**
 * @author 农峰
 * 
 * @requires jQuery,EasyUI
 */
sy.messagerAlert = function(title, msg, icon, fn) {
	return $.messager.alert(title, msg, icon, fn);
};

/**
 * @author 农峰
 * 
 * @requires jQuery,EasyUI,jQuery cookie plugin
 * 
 * 更换EasyUI主题的方法
 * 
 * @param themeName
 *            主题名称
 */
sy.changeTheme = function(themeName) {
	var $easyuiTheme = $('#easyuiTheme');
	var url = $easyuiTheme.attr('href');
	var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName
			+ '/easyui.css';
	$easyuiTheme.attr('href', href);

	var $iframe = $('iframe');
	if ($iframe.length > 0) {
		for (var i = 0; i < $iframe.length; i++) {
			var ifr = $iframe[i];
			$(ifr).contents().find('#easyuiTheme').attr('href', href);
		}
	}

	$.cookie('easyuiThemeName', themeName, {
		expires : 7
	});
};

/**
 * @author 农峰
 * 
 * 获得项目根路径
 * 
 * 使用方法：sy.bp();
 * 
 * @returns 项目根路径
 */
sy.bp = function() {
	var curWwwPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	var localhostPaht = curWwwPath.substring(0, pos);
	var projectName = pathName
			.substring(0, pathName.substr(1).indexOf('/') + 1);
	return (localhostPaht + projectName);
};

/**
 * @author 农峰
 * 
 * 使用方法:sy.pn();
 * 
 * @returns 项目名称(/driverschool)
 */
sy.pn = function() {
	return window.document.location.pathname.substring(0,
			window.document.location.pathname.indexOf('\/', 1));
};

/**
 * @author 农峰
 * 
 * 增加formatString功能
 * 
 * 使用方法：sy.fs('字符串{0}字符串{1}字符串','第一个变量','第二个变量');
 * 
 * @returns 格式化后的字符串
 */
sy.showtip = function(str) {
	for (var i = 0; i < arguments.length - 1; i++) {
		str = str.replace("{" + i + "}", arguments[i + 1]);
	}
	return str;
};

/**
 * @author 农峰
 * 
 * 增加命名空间功能
 * 
 * 使用方法：sy.ns('jQuery.bbb.ccc','jQuery.eee.fff');
 */
sy.ns = function() {
	var o = {}, d;
	for (var i = 0; i < arguments.length; i++) {
		d = arguments[i].split(".");
		o = window[d[0]] = window[d[0]] || {};
		for (var k = 0; k < d.slice(1).length; k++) {
			o = o[d[k + 1]] = o[d[k + 1]] || {};
		}
	}
	return o;
};

/**
 * @author 农峰
 * 
 * 生成UUID
 * 
 * @returns UUID字符串
 */
sy.random4 = function() {
	return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
};
sy.UUID = function() {
	return (sy.random4() + sy.random4() + "-" + sy.random4() + "-"
			+ sy.random4() + "-" + sy.random4() + "-" + sy.random4()
			+ sy.random4() + sy.random4());
};

/**
 * @author 农峰
 * 
 * 获得URL参数
 * 
 * @returns 对应名称的值
 */
sy.getUrlParam = function(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
};

/**
 * @author 农峰
 * 
 * 接收一个以逗号分割的字符串，返回List，list里每一项都是一个字符串
 * 
 * @returns list
 */
sy.getList = function(value) {
	if (value != undefined && value != '') {
		var values = [];
		var t = value.split(',');
		for (var i = 0; i < t.length; i++) {
			values.push('' + t[i]);/* 避免他将ID当成数字 */
		}
		return values;
	} else {
		return [];
	}
};

/**
 * @author 农峰
 * 
 * @requires jQuery
 * 
 * 判断浏览器是否是IE并且版本小于8
 * 
 * @returns true/false
 */
sy.isLessThanIe8 = function() {
	return ($.browser.msie && $.browser.version < 8);
};

/**
 * @author 农峰
 * 
 * @requires jQuery
 * 
 * 将form表单元素的值序列化成对象
 * 
 * @returns object
 */
sy.serializeObject = function(form) {
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (o[this['name']]) {
			o[this['name']] = o[this['name']] + "," + this['value'];
		} else {
			o[this['name']] = this['value'];
		}
	});
	return o;
};

/**
 * 
 * 将JSON对象转换成字符串
 * 
 * @param o
 * @returns string
 */
sy.jsonToString = function(o) {
	var r = [];
	if (typeof o == "string")
		return "\""
				+ o.replace(/([\'\"\\])/g, "\\$1").replace(/(\n)/g, "\\n")
						.replace(/(\r)/g, "\\r").replace(/(\t)/g, "\\t") + "\"";
	if (typeof o == "object") {
		if (!o.sort) {
			for ( var i in o)
				r.push(i + ":" + obj2str(o[i]));
			if (!!document.all
					&& !/^\n?function\s*toString\(\)\s*\{\n?\s*\[native code\]\n?\s*\}\n?\s*$/
							.test(o.toString)) {
				r.push("toString:" + o.toString.toString());
			}
			r = "{" + r.join() + "}";
		} else {
			for (var i = 0; i < o.length; i++)
				r.push(obj2str(o[i]));
			r = "[" + r.join() + "]";
		}
		return r;
	}
	return o.toString();
};

/**
 * @author 郭华(夏悸)
 * 
 * 格式化日期时间
 * 
 * @param format
 * @returns
 */
Date.prototype.format = function(format) {
	if (isNaN(this.getMonth())) {
		return '';
	}
	if (!format) {
		format = "yyyy-MM-dd hh:mm:ss";
	}
	var o = {
		/* month */
		"M+" : this.getMonth() + 1,
		/* day */
		"d+" : this.getDate(),
		/* hour */
		"h+" : this.getHours(),
		/* minute */
		"m+" : this.getMinutes(),
		/* second */
		"s+" : this.getSeconds(),
		/* quarter */
		"q+" : Math.floor((this.getMonth() + 3) / 3),
		/* millisecond */
		"S" : this.getMilliseconds()
	};
	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
};

/**
 * @author 农峰
 * 
 * @requires jQuery
 * 
 * 改变jQuery的AJAX默认属性和方法
 */
$.ajaxSetup({
	type : 'POST',
	error : function(XMLHttpRequest, textStatus, errorThrown) {
		$.messager.progress('close');
		$.messager.alert('错误', XMLHttpRequest.responseText);
	}
});

/**
 * 扩展序号显示bug（easyui bug）
 */
$.extend($.fn.datagrid.methods, {
	fixRownumber : function(nonfeng) {
		return nonfeng.each(function() {
			var panel = $(this).datagrid("getPanel");
			// 获取最后一行的number容器,并拷贝一份
			var clone = $(".datagrid-cell-rownumber", panel).last().clone();
			// 由于在某些浏览器里面,是不支持获取隐藏元素的宽度,所以取巧一下
			clone.css({
				"position" : "absolute",
				left : -1000
			}).appendTo("body");
			var width = clone.width("auto").width();
			// 默认宽度是25,所以只有大于25的时候才进行fix
			if (width > 25) {
				// 多加5个像素,保持一点边距
				$(".datagrid-header-rownumber,.datagrid-cell-rownumber", panel)
						.width(width + 5);
				// 修改了宽度之后,需要对容器进行重新计算,所以调用resize
				$(this).datagrid("resize");
				// 一些清理工作
				clone.remove();
				clone = null;
			} else {
				// 还原成默认状态
				$(".datagrid-header-rownumber,.datagrid-cell-rownumber", panel)
						.removeAttr("style");
			}
		});
	}
});
/**
 * 获取datagrid行号
 */
$.extend($.fn.datagrid.methods, {
	getRowNum : function(jq) {
		var opts = $.data(jq[0], "datagrid").options;
		var array = new Array();
		opts.finder.getTr(jq[0], "", "selected", 1).each(function() {
			array.push($(this).find("td.datagrid-td-rownumber").text());
		});
		return array.join(",");
	}
});
/**
 * 限制备注输入项中文字限制在0-150个字之间
 */
$.extend($.fn.validatebox.defaults.rules, {
	contentSize : {
		validator : function(value, param) {
			return value.length >= param[0] && value.length <= param[1];
		},
		message : '最大输入 {1} 个汉字，当前已经超出允许最大输入范围！'
	}
});

/**
 * 农峰 相同连续列合并扩展
 * 
 * 使用方法：在onLoadSuccess中调用 例如：onLoadSuccess:function(){ //所有列进行合并操作
 * //$(this).datagrid("autoMergeCells"); //指定列进行合并操作
 * $(this).datagrid("autoMergeCells",['itemid','productid']); }
 */
$.extend($.fn.datagrid.methods, {
	autoMergeCells : function(jq, fields) {
		return jq.each(function() {
			var target = $(this);
			if (!fields) {
				fields = target.datagrid("getColumnFields");
			}
			var rows = target.datagrid("getRows");
			var i = 0, j = 0, temp = {};
			for (i; i < rows.length; i++) {
				var row = rows[i];
				j = 0;
				for (j; j < fields.length; j++) {
					var field = fields[j];
					var tf = temp[field];
					if (!tf) {
						tf = temp[field] = {};
						tf[row[field]] = [ i ];
					} else {
						var tfv = tf[row[field]];
						if (tfv) {
							tfv.push(i);
						} else {
							tfv = tf[row[field]] = [ i ];
						}
					}
				}
			}
			$.each(temp, function(field, colunm) {
				$.each(colunm, function() {
					var group = this;

					if (group.length > 1) {
						var before, after, megerIndex = group[0];
						for (var i = 0; i < group.length; i++) {
							before = group[i];
							after = group[i + 1];
							if (after && (after - before) == 1) {
								continue;
							}
							var rowspan = before - megerIndex + 1;
							if (rowspan > 1) {
								target.datagrid('mergeCells', {
									index : megerIndex,
									field : field,
									rowspan : rowspan
								});
							}
							if (after && (after - before) != 1) {
								megerIndex = after;
							}
						}
					}
				});
			});
		});
	}
});



$.extend($.fn.datagrid.methods, {
	editCell : function(jq, param) {
		return jq.each(function() {
			var opts = $(this).datagrid('options');
			var fields = $(this).datagrid('getColumnFields', true).concat(
					$(this).datagrid('getColumnFields'));
			for (var i = 0; i < fields.length; i++) {
				var col = $(this).datagrid('getColumnOption', fields[i]);
				col.editor1 = col.editor;
				if (fields[i] != param.field) {
					col.editor = null;
				}
			}
			$(this).datagrid('beginEdit', param.index);
			for (var i = 0; i < fields.length; i++) {
				var col = $(this).datagrid('getColumnOption', fields[i]);
				col.editor = col.editor1;
			}
		});

	}
});

/**
 * 给时间框控件扩展一个清除的按钮
 */
$.fn.datebox.defaults.cleanText = '清空';

(function($) {
	var buttons = $.extend([], $.fn.datebox.defaults.buttons);
	buttons.splice(1, 0, {
		text : function(target) {
			return $(target).datebox("options").cleanText
		},
		handler : function(target) {
			$(target).datebox("setValue", "");
			$(target).datebox("hidePanel");
		}
	});
	$.extend($.fn.datebox.defaults, {
		buttons : buttons
	});

})(jQuery);



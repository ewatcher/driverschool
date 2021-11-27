
//格式化学员进度状态
function formatProgressState(value, row, index) {
	if (value == 1) {
		tempDate = 'sinupDate';
		return '<font color="#0099FF">报名完成</font>';
	} else if (value == 2) {
			return '<font color="#CC00FF">科一理论</font>';
	} else if (value == 3) {
		return '<font color="#FF0066">科二五项</font>';
	} else if (value == 4) {
		return '<font color="#006600">科三路考</font>';
	} else if (value == 5) {
		return '<font color="#33CC33">科四文明</font>';
	} else if (value == 6) {
		return '<font color="#000066">毕业</font>';
	} else if (value == 7) {
		tempDate = 'quitSchoolDate';
		return '<font color="red">退学</font>';
	} else {
		return '';
	}
}

function formatACLState(value, row, index) {
	if (value) {
		return '<font color=#0099FF">启用</font>';
	} else {
		return '';
	}

}

//格式申领类型
function formatQualification(value, row, index) {
	if (value == 0) {
		return '否';
	} else if (value == 1) {
		return '是';
	} else {
		return '';
	}
}

//格式申领类型
function formatApplyType(value, row, index) {
	if (value == 0 || value == null || value == undefined || value == ""
		|| value == "NaN-NaN-NaN") {
		return '';
	} else {
		if (value == 1) {
			return '初次申领';
		} else if (value == 2) {
			return '增驾准车型';
		} else if (value == 3) {
			return '持军官驾驶证';
		} else if (value == 4) {
			return '持境外驾驶证';
		} else {
			return '';
		}
	}
}

//格式收费人类别
function formatHandupManType(value, row, index) {
	if (value == 0 || value == null || value == undefined || value == ""
		|| value == "NaN-NaN-NaN") {
		return '';
	} else {
		if (1 == value) {
			return '<font color=#0099FF">学员</font>';
		} else if (2 == value) {
			return '<font color=#0099FF">教练</font>';
		} else if (3 == value) {
			return '<font color=#0099FF">职员</font>';
		} else if (4 == value) {
			return '<font color=#0099FF">其它</font>';
		}
	}
}
//格式化审批选项
function formatApplyFlag(value, row, index) {
	if (value == 0 || value == null || value == undefined || value == ""
		|| value == "NaN-NaN-NaN") {
		return '';
	} else {
		if (1 == value) {
			return '<font color=#0099FF">通过</font>';
		} else if (2 == value) {
			return '<font color=#0099FF">未通过</font>';
		}
	}
}

//格式化缺考
function formatMissExam(value, row, index) {
	if (value == 0 || value == null || value == undefined || value == ""
		|| value == "NaN-NaN-NaN") {
		return '';
	} else {
		if (1 == value) {
			return '<font color=red>缺考</font>';
		}
	}
}

/**
 * 格式化考试标记
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function formatSubjectExamFalg(value, row, index) {
	if (value == 0 || value == null || value == undefined || value == ""
		|| value == "NaN-NaN-NaN") {
		return '';
	} else {
		if (1 == value) {
			return '<font color=#5b0f00>已报考</font>';
		}
		return '<font color=red>未报考</font>';
		;
	}
}

/**
 * 格式化显示批次
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function formatBatch(value, row, index) {
	if (value == null || value == undefined || value == ""
		|| value == "NaN-NaN-NaN") {
		return '';
	} else {
		//return '<div style="text-align:center;color:red;font-size:13px">第'+value+'期</div>';
		return '第【<font color=red>' + value + '</font>】期';
	}
}
/**
 * 考试学员列表格式化学员成绩
 * 
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function formatStudentExamScore(value, row, index) {
	if (value == null || value == undefined || value == ""
		|| value == "NaN-NaN-NaN") {
		return '';
	}
	if (value == 1) {
		return '<div style="text-align:center;color:#0099FF">合格</div>';
	} else if (value == 2) {
		return '<div style="text-align:center;color:red">不合格</div>';
	}
}
/**
 * 初始化交费
 * 
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function formatFeed(value, row, index) {
	if (value != null && value != undefined && value != '') {
		if (value == 1) {
			return '<div style="text-align:center;color:#0099FF">已交完</div>';
		} else if (value == 2) {
			return '<div style="text-align:center;color:red">欠费</div>';
		} else if (value == 3) {
			return '<div style="text-align:center;color:red">其它</div>';
		}
	} else {
		return '';
	}
}
// 格式化显示不排班时间段落
function formatNoArrange(value, row, index) {
	if (value == "NO") {
		return '<span style="color:red;">' + '休息' + '</span>';
	} else {
		return value;
	}
}

/**
 * 格式化微信创建方式
 * 
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function formatReservationWay(value, row, index) {
	if (value != null && value != undefined && value != '') {
		if (value == 1) {
			return '<div style="text-align:center;color:#0099FF">后台</div>';
		} else if (value == 2) {
			return '<div style="text-align:center;color:red">微信端</div>';
		}
	} else {
		return '';
	}
}
/**
 * 格式化排班类型
 * 
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function formatArrangType(value, row, index) {
	if (value != null && value != undefined && value != '') {
		if (value == 1) {
			return '<div style="text-align:center;color:#0099FF">电车</div>';
		} else if (value == 2) {
			return '<div style="text-align:center;color:red">模拟</div>';
		} else if (value == 3) {
			return '';
		}
	} else {
		return '';
	}
}

/**
 * 时间格式化显示
 * 
 * 使用方法： return formatTime(value, row, index); 返回时间格式：2016-07-01 星期五
 * 
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function formatTimeYMDW(value, row, index) {
	var date = new Date(value);
	var year = date.getFullYear().toString();
	var month = (date.getMonth() + 1);
	var day = date.getDate().toString();
	var hour = date.getHours().toString();
	var minutes = date.getMinutes().toString();
	var seconds = date.getSeconds().toString();
	var week = date.getDay();
	var retWeek = undefined;
	if (month < 10) {
		month = "0" + month;
	}
	if (day < 10) {
		day = "0" + day;
	}
	if (hour < 10) {
		hour = "0" + hour;
	}
	if (minutes < 10) {
		minutes = "0" + minutes;
	}
	if (seconds < 10) {
		seconds = "0" + seconds;
	}
	if (week == 0) {
		retWeek = "星期日";
		return year + "-" + month + "-" + day + "  " + retWeek;
	} else if (week == 1) {
		retWeek = "星期一";
		return year + "-" + month + "-" + day + "  " + retWeek;
	} else if (week == 2) {
		retWeek = "星期二";
		return year + "-" + month + "-" + day + "  " + retWeek;
	} else if (week == 3) {
		retWeek = "星期三";
		return year + "-" + month + "-" + day + "  " + retWeek;
	} else if (week == 4) {
		retWeek = "星期四";
		return year + "-" + month + "-" + day + "  " + retWeek;
	} else if (week == 5) {
		retWeek = "星期五";
		return year + "-" + month + "-" + day + "  " + retWeek;
	} else if (week == 6) {
		retWeek = "星期六";
		return year + "-" + month + "-" + day + "  " + retWeek;
	}

}

function initStudentType(value, row, index) {
	if (value == 0) {
		return '<div style="text-align:center;color:#0099FF">本校</div>';
	} else if (value == 1) {
		return '<div style="text-align:center;color:#4B0082">承包</div>';
	} else {
		return '';
	}
}

// 格式化显示预约中的车辆状态
function formatCarState(value, row, index) {
	if (value == 0) {
		return '<div style="text-align:center;color:red">无车辆</div>';
	} else if (value == 1) {
		return '<div style="text-align:center;color:#4B0082">正常</div>';
	} else {
		return '';
	}
}

function initTrainerType(value, row, index) {
	if (value == 0) {
		return '<div style="text-align:center;color:#0099FF">本校</div>';
	} else if (value == 1) {
		return '<div style="text-align:center;color:#4B0082">承包</div>';
	} else {
		return '';
	}
}

/**
 * 格式化显示资料上交情况
 * 
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function formatCommitValue(value, row, index) {
	if (value == 0) {
		return '已交';
	} else if (value == 1) {
		return '未交';
	} else {
		return '';
	}
}

function formatFaceSign(value, row, index) {
	if (value == 0) {
		return '已签';
	} else if (value == 1) {
		return '未签';
	} else {
		return '';
	}
}

function formatTimingCar(value, row, index) {
	if (value == 0) {
		return '已发';
	} else if (value == 1) {
		return '未发';
	} else {
		return '';
	}
}

function formatForeign(value, row, index) {
	if (value == 0) {
		return '本地';
	} else if (value == 1) {
		return '外籍';
	} else {
		return '';
	}
}

/**
 * 样式化显示学员编号 (如：BSF0001001格式化后F0001001）
 * 
 * @param value
 * @param row
 * @param index
 * @returns
 */
function formatStudentNo(value, row, index) {
	if (value != null && value != undefined && value != '') {
		return value.substring(value.length - 8, value.length);
	}
	return '';
}

/**
 * 样式化显示学员编号 (如：BSJX001格式化后001）
 * 
 * @param value
 * @param row
 * @param index
 * @returns
 */
function formatTrainerNo(value, row, index) {
	if (value != null && value != undefined && value != '') {
		var temp = value.toString();
		return temp.substring(temp.length - 3, temp.length);
	}
	return '';
}

/**
 * 时间格式化显示
 * 
 * 使用方法： return formatTimeYMD(value, row, index); 返回时间格式：2016-07-01
 * 
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function formatTimeYMD(value, row, index) {
	if (value == null || value == undefined || value == ""
		|| value == "NaN-NaN-NaN") {
		return '';
	}
	var date = new Date(value);
	var year = date.getFullYear().toString();
	var month = (date.getMonth() + 1);
	var day = date.getDate().toString();
	var hour = date.getHours().toString();
	var minutes = date.getMinutes().toString();
	var seconds = date.getSeconds().toString();
	var week = date.getDay();
	var retWeek = undefined;
	if (month < 10) {
		month = "0" + month;
	}
	if (day < 10) {
		day = "0" + day;
	}
	if (hour < 10) {
		hour = "0" + hour;
	}
	if (minutes < 10) {
		minutes = "0" + minutes;
	}
	if (seconds < 10) {
		seconds = "0" + seconds;
	}
	return year + "-" + month + "-" + day;
}

/**
 * 时间格式化显示
 * 
 * 使用方法： return formatTimeYMDHMS(value, row, index); 返回时间格式：2016-07-01 10:12:22
 * 
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function formatTimeYMDHMS(value, row, index) {
	if (value == undefined || value == null || value == '' || value == 'NaN-NaN-NaN NaN:NaN:NaN') {
		return '';
	}
	var date = new Date(value);
	var year = date.getFullYear().toString();
	var month = (date.getMonth() + 1);
	var day = date.getDate().toString();
	var hour = date.getHours().toString();
	var minutes = date.getMinutes().toString();
	var seconds = date.getSeconds().toString();
	var week = date.getDay();
	var retWeek = undefined;
	if (month < 10) {
		month = "0" + month;
	}
	if (day < 10) {
		day = "0" + day;
	}
	if (hour < 10) {
		hour = "0" + hour;
	}
	if (minutes < 10) {
		minutes = "0" + minutes;
	}
	if (seconds < 10) {
		seconds = "0" + seconds;
	}

	return year + "-" + month + "-" + day + " " + hour + ":" + minutes + ":"
		+ seconds;

}

/**
 * 时间格式化显示
 * 
 * 使用方法： return formatTimeYMDHMS(value, row, index); 返回时间格式：2016-07-01 10:12:22
 * 
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function formatTimeYMDHM(value, row, index) {
	if (value == undefined || value == null || value == '' || value == 'NaN-NaN-NaN NaN:NaN') {
		return '';
	}
	var date = new Date(value);
	var year = date.getFullYear().toString();
	var month = (date.getMonth() + 1);
	var day = date.getDate().toString();
	var hour = date.getHours().toString();
	var minutes = date.getMinutes().toString();
	var seconds = date.getSeconds().toString();
	var week = date.getDay();
	var retWeek = undefined;
	if (month < 10) {
		month = "0" + month;
	}
	if (day < 10) {
		day = "0" + day;
	}
	if (hour < 10) {
		hour = "0" + hour;
	}
	if (minutes < 10) {
		minutes = "0" + minutes;
	}
	if (seconds < 10) {
		seconds = "0" + seconds;
	}

	return year + "-" + month + "-" + day + " " + hour + ":" + minutes;

}

// 完整显示提示信息
function showAllMsg(value, rowData, rowIndex) {
	if (value == "" || value == null || value == undefined) {
		return '';
	}
	return sy.showtip('<span title="{0}">{1}</span>', value, value);
}

// 初始化学时用途参数:电车，模拟，五项，路训
function formatterTimingUsingType(value, row, index) {
	if (value == 1) {
		return '<div style="text-align:center;color:#0099FF">电车</div>';
	} else if (value == 2) {
		return '<div style="text-align:center;color:#4F4F4F">模拟</div>';
	} else if (value == 3) {
		return '<div style="text-align:center;color:#0000EE">五项</div>';
	} else if (value == 4) {
		return '<div style="text-align:center;color:#8E388E">路训</div>';
	} else if (value == 5) {
		return '<div style="text-align:center;color:red">休息</div>';
	} else {
		return '';
	}
}

// 初始化预约状态参数
function formatterReservationState(value, row, index) {
	if (value == 1) {
		return '<div style="text-align:left;color:#00868B">预约中...</div>';
	} else if (value == 2) {
		return '<div style="text-align:left;color:#0099FF">正常</div>';
	} else if (value == 3) {
		return '<div style="text-align:left;color:#9A32CD">完成</div>';
	} else if (value == 4) {
		return '<div style="text-align:left;color:red">已取消</div>';
	} else if (value == 5) {
		return '<div style="text-align:left;color:#0099FF">挂起</div>';
	} else if (value == 6) {
		return '<div style="text-align:left;color:#0099FF">确认中...</div>';
	} else {
		return '';
	}
}

// 初始化考试成绩
function formatExamScore(value, row, index) {
	if (value == 1) {
		return '缺考';
	} else if (value == 2) {
		return '合格';
	} else if (value == 3) {
		return '不合格';
	} else {
		return '';
	}
}

// 驾照类型参数初始化
function formatterDriverType(value, row, index) {
	if (value == 1) {
		return 'A1';
	} else if (value == 2) {
		return 'A2';
	} else if (value == 3) {
		return 'A2';
	} else if (value == 4) {
		return 'B1';
	} else if (value == 5) {
		return 'B2';
	} else if (value == 6) {
		return 'C1';
	} else if (value == 7) {
		return 'C2';
	} else if (value == 8) {
		return 'C3';
	} else if (value == 9) {
		return 'C4';
	} else if (value == 10) {
		return 'C5';
	} else if (value == 11) {
		return 'D';
	} else if (value == 12) {
		return 'E';
	} else if (value == 13) {
		return 'F';
	} else if (value == 14) {
		return 'M';
	} else if (value == 15) {
		return 'N';
	} else if (value == 16) {
		return 'P';
	} else {
		return '';
	}
}

// 车辆用途
function initCarUsingWay(value, row, index) {
	if (value == 1) {
		return 'A1';
	} else if (value == 2) {
		return 'A2';
	} else if (value == 3) {
		return 'A2';
	} else if (value == 4) {
		return 'B1';
	} else if (value == 5) {
		return 'B2';
	} else if (value == 6) {
		return 'C1';
	} else if (value == 7) {
		return 'C2';
	} else if (value == 8) {
		return 'C3';
	} else if (value == 9) {
		return 'C4';
	} else if (value == 10) {
		return 'C5';
	} else if (value == 11) {
		return 'C2/C5';
	} else if (value == 12) {
		return '<div style="text-align:center;color:red">电车</div>';
	} else if (value == 13) {
		return '<div style="text-align:center;color:red">模拟器</div>';
	} else {
		return '';
	}
}

/**
 * 初始化预约中的学员确认状态 confirmState:1表示学员确认，2表示教练确认
 */
function formatterReservationConfirm(value, row, index, reservationState,
	reservationDate, confirmState) {
	var date = new Date(reservationDate);
	var nowDate = new Date();
	if (value == 1) {
		return '<div style="text-align:center;color:#0000FF">已确认</div>';
	} else {
		if (reservationState == 1 || reservationState == 6) {
			if (nowDate >= date && confirmState == 1) {
				return '<a href="#" id="reservation_studentConfirm'
					+ index
					+ '" class="easyui-linkbutton" style="height:15px">确认</a>';
			} else if (nowDate >= date && confirmState == 2) {
				return '<a href="#" id="reservation_trainerConfirm'
					+ index
					+ '" class="easyui-linkbutton" style="height:15px">确认</a>';
			}
		}
	}

}

/**
 * 格式化显示教练排班状态
 * 
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function initTrainerArrangeFlag(value, row, index) {
	if (value == 1) {
		return '<div style="text-align:center;color:red">是</div>';
	} else if (value == 0) {
		return '<div style="text-align:center;color:#1a0000">否</div>';
	} else {
		return '';
	}
}

/**
 * 初始化计时类型参数
 * 
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function initTimingCountType(value, row, index) {
	if (value == 0) {
		return '<div style="text-align:center;color:red">非计时</div>';
	} else if (value == 1) {
		return '<div style="text-align:center;color:#1a0000">计时</div>';
	} else {
		return '';
	}
}

function initColumColorRed(value, row, index) {
	if (value != null) {
		return '<div style="text-align:center;color:red">' + value + '</div>';
	} else {
		return '';
	}
}

function initColumColorBlud(value, row, index) {
	if (value != null) {
		return '<div style="text-align:center;color:#8470FF">' + value
			+ '</div>';
	} else {
		return '';
	}
}
/**
 * 学员编号查询（功能，在输入编号前添加各分校的前缀）
 * 
 * @param object
 * @param userSchoolArea
 */
function searchStudentNo(object, userSchoolArea) {
	var temp = object.val();
	if (temp != undefined && temp != null && temp != "") {
		var str = removeLeftTrim(temp);
		var retVal = str.substring(str.length - 4, str.length);
		object.val(retVal);
	}
}
/**
 * 队去字符串左边空格
 * 
 * @param str
 */
function removeLeftTrim(str) {
	if (str.charAt(0) == " ") {
		// 如果字串左边第一个字符为空 格
		str = str.slice(1); // 将空格从字串中去掉
		str = removeLeftTrim(str); // 递归调用
	}
	return str;
}
/**
 * 格式化自动档排班时间段
 */
function initAutocarItemTime(value, row, index) {
	if (value == 0) {
		return '<div style="text-align:center;color:red">休息</div>';
	} else if (value == 1) {
		return '<div style="text-align:center;color:#1a0000">启用</div>';
	} else if (value == 3) {
		return '<div style="text-align:center;color:#FF34B3">已约</div>';
	} else {
		return '';
	}
}

/**
 * 初始化车辆使用状态
 * 
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function initCarUsingState(value, row, index) {
	if (value == 1) {
		return '<div style="text-align:center;color:red">空闲</div>';
	} else if (value == 2) {
		return '<div style="text-align:center;color:green">启用</div>';
	} else if (value == 3) {
		return '<div style="text-align:center;color:#3300cc">维修中</div>';
	} else if (value == 4) {
		return '其它';
	} else {
		return '';
	}
}

/**
 * 初始化车辆微信状态
 */
function initCarWechatState(value, row, index) {
	if (value == 0) {
		return '<div style="text-align:center;color:red">未启用</div>';
	} else if (value == 1) {
		return '<div style="text-align:center;color:green">启用</div>';
	} else {
		return '';
	}
}

/**
 * 初始化参数为0与1的参数
 */
function formatBinaryFlag(value, row, index) {
	if (value == 0) {
		return '<div style="text-align:center;color:red">未启用</div>';
	} else if (value == 1) {
		return '<div style="text-align:center;color:green">启用</div>';
	} else {
		return '';
	}
}

/**
 * 初始化油卡状态
 * 
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function initOilcardState(value, row, index) {
	if (value == 1) {
		return '<div style="text-align:center;color:red">正常</div>';
	} else if (value == 2) {
		return '<div style="text-align:center;color:#3300cc">挂失</div>';
	} else {
		return '';
	}
}
/**
 * 格式化油名称
 * 
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function formatOilName(value, row, index) {
	if (value == 1) {
		return '<div style="text-align:center;color:red">汽油</div>';
	} else if (value == 2) {
		return '<div style="text-align:center;color:#3300cc">柴油</div>';
	} else if (value == 3) {
		return '<div style="text-align:center;color:#3399FF">其它</div>';
	} else {
		return '';
	}
}

/**
 * 格式化油型号
 * 
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function formatOilType(value, row, index) {
	if (value == 1) {
		return '<div style="text-align:center;color:red">92#</div>';
	} else if (value == 2) {
		return '<div style="text-align:center;color:#3300cc">95#</div>';
	} else if (value == 3) {
		return '<div style="text-align:center;color:#3399FF">其它</div>';
	} else {
		return '';
	}
}

function formatStudentState(value, row, index) {
	if (value == 1) {
		return '在培';
	} else if (value == 2) {
		return '毕业';
	} else if (value == 3) {
		return '退学';
	} else if (value == 4) {
		return '到期';
	} else if (value == 5) {
		return '不计时';
	} else {
		return '';
	}
}

// ==========================初始参数＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
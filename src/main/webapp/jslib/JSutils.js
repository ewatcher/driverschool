//JS 辅助工具类


//初始化车辆微信预约状态
function initCarWechatFlag(obj, value, required, readonly) {
	var url = '../demo/json/binary_combobox.json';
	initComboboxData(obj, value, url, required, readonly);
}

//初始化运营资格（0：否，1：是）
function initQualificationFlag(obj, value, required, readonly) {
	var url = '../demo/json/binaryFlag_combobox.json';
	initComboboxData(obj, value, url, required, readonly);
}

//初始化修改成绩类型（1：修改初考成绩，2：修改补考成绩）
function initScoreUpdateFlag(obj, value, required, readonly) {
	var url = '../demo/json/examScoreUpdateFlag_combobox.json';
	initComboboxData(obj, value, url, required, readonly);
}

//初始化考试成绩
function initExamScore(obj, value, required, readonly) {
	var url = '../demo/json/examScore_combobox.json';
	initComboboxData(obj, value, url, required, readonly);
}

/**'
 * 初始化科目类型
 * @param obj
 * @param value
 * @param required
 * @param readonly
 */
function initSubjectType(obj, value, required, readonly) {
	var url = '../demo/json/subject_combobox.json';
	initComboboxData(obj, value, url, required, readonly);
}
/**
 * 初始化教练排班类型
 * @param obj
 * @param value
 * @param required
 * @param readonly
 */
function initArrangeType(obj, value, required, readonly) {
	var url = '../demo/json/arrangeType_combobox.json';
	initComboboxData(obj, value, url, required, readonly);
}

/**
 * 交费情况初始化
 * @param obj
 * @param value
 * @param required
 * @param readonly
 */
function initMoneyState(obj, value, required, readonly) {
	var url = '../demo/json/moneyState_combobox.json';
	initComboboxData(obj, value, url, required, readonly);
}

/**费用状态初始化
 * 
 * @param obj
 * @param value
 * @param required
 * @param readonly
 */
function initFeedState(obj, value, required, readonly) {
	var url = '../demo/json/feeState_combobox.json';
	initComboboxData(obj, value, url, required, readonly);
}

/**
 * 添加键盘按钮事件
 * @param obj
 * @param datagrid
 * @param commitForm
 */
function bindingKeypressEvent(obj, datagrid, commitForm) {
	obj.unbind('keypress');
	obj.bind('keypress', function(event) {
		if (event.keyCode == "13") {
			datagrid.datagrid('load', sy.serializeObject(commitForm));
			obj.val('');
		}
	});

}

/**
 * 初始化预约类型
 * 
 * @param obj
 * @param value
 * @param required
 * @param readonly
 */
function initReservationType(obj, value, required, readonly) {
	var url = '../demo/json/reservationtype_combobox.json';
	initComboboxData(obj, value, url, required, readonly);
}

/**
 * 初始化驾照时令申领类型：1：初次申领...
 * 
 * @param obj
 * @param value
 */
function initApplyType(obj, value, required, readonly) {
	var url = '../demo/json/applyType_combobox.json';
	initComboboxData(obj, value, url, required, readonly);
}

/**
 * 初始化学员类型
 * 
 * @param obj
 * @param value
 * @param required
 * @param readonly
 */
function initStudentTypeVal(obj, value, required, readonly) {
	var url = '../demo/json/studentType_combobox.json';
	initComboboxData(obj, value, url, required, readonly);
}

/**
 * 初始化计时类型 （计时，非计时）
 * 
 * @param obj
 * @param value
 * @param required
 * @param readonly
 */
function initTimingType(obj, value, required, readonly) {
	var url = '../demo/json/timingCountType_combobox.json';
	initComboboxData(obj, value, url, required, readonly);
}

/**
 * 初始学预约状态
 * 
 * @param obj
 * @param value
 * @param required
 * @param readonly
 */
function initReservationState(obj, value, required, readonly) {
	var url = '../demo/json/reservationstate_combobox.json';
	initComboboxData(obj, value, url, required, readonly);
}


/**
 * 判断传入值是否为空,如果为空返回true,否则返回false
 * 
 * @param obj
 * @returns {Boolean}
 */
function validateIsNull(obj) {
	if (obj == '' || obj == null || obj == undefined) {
		return true;
	}
	return false;
}

/**
 * 判断传入值是不为空，不为空则返回true,
 * @param obj
 * @returns {Boolean}
 */
function validateIsNotNull(obj) {
	if (obj != '' && obj != null && obj != undefined) {
		return true;
	}
	return false;
}

// 初始化校区
function initStudentFileSchoolArea(object) {
	object.combotree({
		url : '../demo/organizationAction!doNotNeedSession_tree.action',
		lines : true,
		required : true,
	});
	// 农峰添加，校区数据过虑功能
	$.ajax({
		url : '../demo/studentAction!getSchoolArea.action',
		dataType : 'json',
		success : function(d) {
			// 非系统管理员，禁止查询其他校区
			if (d !== null) {
				object.combotree('setValue', d);
				object.combotree('readonly', true);
			} else {
				object.combotree('readonly', false);
			}
		}
	});

}

/**
 * 获取用户所属学校标识
 */
function getUserSchoolArea() {
	$.ajax({
		url : '../demo/studentAction!getSchoolArea.action',
		dataType : 'json',
		success : function(d) {
			userSchoolArea = d;
		}
	});
}


/**
 * 初始化校区标识，普通用户不能选择校区，超级用记可选择校区
 * 
 * @param object
 */
function initSchoolArea(object, userSchoolArea) {
	object.combotree({
		url : '../demo/organizationAction!doNotNeedSession_tree.action',
		lines : true,
		required : true,
	});
	// 非系统管理员，禁止查询其他校区
	if (userSchoolArea !== null) {
		object.combotree('setValue', userSchoolArea);
		object.combotree('readonly', true);
	} else {
		object.combotree('readonly', false);
	}

}

//初始化校区报名网点combobox
function initMyNetPiont(object, initDate) {
	var url = '../demo/studentAction!toInitMyNetPiont.action';
	initComboboxData(object, initDate, url, false, false);
}

/**
 * //初始化驾照类型combobox （初始化对象，默认值,url,必须/不是必选）
 */
function initComboboxData(comboObject, initData, url, required, readonlyVal) {
	var readonly = false;
	if (!validateIsNull(readonlyVal)) {
		readonly = true;
	}
	var requiredTemp = false;
	if (!validateIsNull(required)) {
		requiredTemp = true;
	}
	comboObject.combobox({
		url : url,
		valueField : 'value',
		textField : 'text',
		method : 'get',
		editable : false,
		required : requiredTemp,
		readonly : readonly,
		onLoadSuccess : function() {
			comboObject.combobox('setValue', initData);
		}
	});
}

/**
 * 初始化驾照类型
 * 
 * @param object
 * @param defaultVal
 * @param required
 * @param readonly
 */
function initDriverType(object, defaultVal, required, readonly) {
	var url = '../demo/json/driverType_combobox.json';
	initComboboxData(object, defaultVal, url, required, readonly);
}
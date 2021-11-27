
/**
 * 输入校验
 */
$.extend($.fn.validatebox.defaults.rules, {
	// 身份证输入项长度限制，不能输入中文，长度0-15
	validateIdentity : {
		validator : function(value, param) {
			return value.length >= param[0] && value.length <= param[1];
		},
		message : '身份证号长度为15-18位！',
	},
	// 验证汉子
    validChinese: {
        validator: function (value) {
            return /^[\u0391-\uFFE5]+$/.test(value);
        },
        message: '只能输入汉字',
    },
    // 移动手机号码验证
    validMobile: {// value值为文本框中的值
        validator: function (value) {
            var reg = /^1[3|4|5|7|8|9]\d{9}$/;
            return reg.test(value);
        },
        message: '输入手机号码格式不准确.',
    },
    // 固定电话码验证
    validTelephone: {// value值为文本框中的值
        validator: function (value) {
            var reg =/^((0?1[34578]\d{9})|((0(10|2[1-3]|[3-9]\d{2}))?[1-9]\d{6,7}))$/;
            return reg.test(value);
        },
        message: '输入电话号码格式不准确.',
    },
    // 国内邮编验证
    vlidZipcode: {
        validator: function (value) {
            var reg = /^[1-9]\d{5}$/;
            return reg.test(value);
        },
        message: '邮编必须是非0开始的6位数字.',
    },
    /*
	 * 用途：检查输入字符串是否只由英文字母和数字和下划线组成 输入： s：字符串 返回： 如果通过验证返回true,否则返回false
	 */
    // 国内邮编验证
    vlidUserName: {
        validator: function (value) {
        	var regu = "^[0-9a-zA-Z\_]+$";
            var re = new RegExp(regu);
            if (re.test(value)) {
                return true;
                }else{
                return false;
                }
        },
        message: '由英文字母和数字和下划线组成.',
    },
  
    // 用户账号验证(只能包括 _ 数字 字母)
    account: {// param的值为[]中值
        validator: function (value, param) {
            if (value.length < param[0] || value.length > param[1]) {
                $.fn.validatebox.defaults.rules.account.message = '用户名长度必须在' + param[0] + '至' + param[1] + '范围';
                return false;
            } else {
                if (!/^[\w]+$/.test(value)) {
                    $.fn.validatebox.defaults.rules.account.message = '用户名只能数字、字母、下划线组成.';
                    return false;
                } else {
                    return true;
                }
            }
        }, message: '',
    },
    //较验保留两位小数点发double类型
    doublePrice: {// param的值为[]中值
        validator: function (val){
            var reg = /^(([1-9]+)|([0-9]+\.[0-9]{1,2}))$/;
            return reg.test(val);
        }, message: '小数点后保留两位数',
    },
    //仅允许输入数字
    nuberInputOnly: {// param的值为[]中值
        validator: function (val){
            var reg = /[^\d]/g;
            return reg.test(val);
        }, message: '只能输入数字',
    },
});
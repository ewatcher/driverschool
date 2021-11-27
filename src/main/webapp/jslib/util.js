/**
 * 根据微信图标原地址，获取不同大小图片
 * @param url 微信图标地址
 * @param size  有0、46、64、96、132数值可选，0代表640*640正方形头像， 46代表 46x46大小图片
 */
function formatWeixinIcon(url, size) {
	var src = url;
	if (url && url.length > 8) {
		var index = url.lastIndexOf('/');
		if (index > 0) {
			src = url.substring(0, index);
			src = src + "/" + size;
		}
	}
	return src;
}

Date.prototype.Format = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, //月份   
		"d+" : this.getDate(), //日   
		"h+" : this.getHours(), //小时   
		"m+" : this.getMinutes(), //分   
		"s+" : this.getSeconds(), //秒   
		"q+" : Math.floor((this.getMonth() + 3) / 3), //季度   
		"S" : this.getMilliseconds() //毫秒   
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}
/**
 * 将日期转换为数字串
 * @param date日期
 * @param param显示日期位数
 */
function dateToDataNo(date, param) {
	var dataString = date.Format("yyyyMMdd-hhmm");
	return dataString.replace(/[^0-9]/mg, '').match(/.{12}/);
}

/**
 * 将人民币数字转换成汉字大写，
 */
function toUpperMoney(inVal){
	if(isNull(inVal)){
		return ;
	}
	for ( var int = 0; int < inVal.length; int++) {
		if(checkNum(inVal)){
			break;
		}else{
			inVal = removeLastChar(inVal);
			int --;
		}
		if(isNull(inVal)) return;
	}
	
	return (ToTrans(inVal));
	
	function isNull(inVal){
		if(inVal == null || inVal == ""){
			return true;
		}
		return false;
	}
}
//校验是否为正浮点数或正整数
function checkNum(str){
  var patrn=/^([+]?)\d*\.?\d+$/;
  return patrn.test(str);
};

//移除最后一个字符
function removeLastChar(str){
	if(str == null || str == ""){
		return str;
	}
	return str.substring(0,str.length-1);
}

function ToTrans(a) {
    var b = 9.999999999999E10,
    f = "\u96f6",
    h = "\u58f9",
    g = "\u8d30",
    e = "\u53c1",
    k = "\u8086",
    p = "\u4f0d",
    q = "\u9646",
    r = "\u67d2",
    s = "\u634c",
    t = "\u7396",
    l = "\u62fe",
    d = "\u4f70",
    i = "\u4edf",
    m = "\u4e07",
    j = "\u4ebf",
    u = "人民币",
    o = "\u5143",
    c = "\u89d2",
    n = "\u5206",
    v = "\u6574";
    a = a.toString();
    if (a == "") {
        alert("转换内容不能为空!");
        return "";
    }
    if (a.match(/[^,.\d]/) != null) {
        alert("输入有误,请输入小数点和纯数字!");
        return "";
    }
    if (a.match(/^((\d{1,3}(,\d{3})*(.((\d{3},)*\d{1,3}))?)|(\d+(.\d+)?))$/) == null) {
        alert("输入有误,请输入小数点和纯数字!");
        return "";
    }
    a = a.replace(/,/g, "");
    a = a.replace(/^0+/, "");
    if (Number(a) > b) {
        alert("\u5bf9\u4e0d\u8d77,\u4f60\u8f93\u5165\u7684\u6570\u5b57\u592a\u5927\u4e86!\u6700\u5927\u6570\u5b57\u4e3a99999999999.99\uff01");
        return "";
    }
    b = a.split(".");
    if (b.length > 1) {
        a = b[0];
        b = b[1];
        b = b.substr(0, 2);
    } else {
        a = b[0];
        b = "";
    }
    h = new Array(f, h, g, e, k, p, q, r, s, t);
    l = new Array("", l, d, i);
    m = new Array("", m, j);
    n = new Array(c, n);
    c = "";
    if (Number(a) > 0) {
        for (d = j = 0; d < a.length; d++) {
            e = a.length - d - 1;
            i = a.substr(d, 1);
            g = e / 4;
            e = e % 4;
            if (i == "0") j++;
            else {
                if (j > 0) c += h[0];
                j = 0;
                c += h[Number(i)] + l[e];
            }
            if (e == 0 && j < 4) c += m[g];
        }
        c += o;
    }
    if (b != "") for (d = 0; d < b.length; d++) {
        i = b.substr(d, 1);
        if (i != "0") c += h[Number(i)] + n[d];
    }
    if (c == "") c = f + o;
    if (b.length < 2) c += v;
    return c;
}

function getCurrentTime(formatStr,fdate){
	  var fTime, fStr = 'ymdhis';
	  var date,hour;
	     if (!formatStr)  
	        formatStr= "y-m-d h:i";  
	     if (fdate)  
	        fTime = new Date(fdate);  
	     else  
	        fTime = new Date(); 
	     if(fTime.getHours()<6||fTime.getHours() >19){
	    	 fTime.setDate(fTime.getDate()+1);
	    	 date=fTime.getDate();
	    	 hour='8';
	     	} else{
	     		date=fTime.getDate();
	     		hour=fTime.getHours()+3;
	     	}
	     var formatArr = [  
	         fTime.getFullYear().toString(),  
	         (fTime.getMonth()+1).toString(),  
	         date.toString(),  
	         hour.toString(),
	         '00',
	     ];  
	     for (var i=0; i<formatArr.length; i++){  
	        formatStr = formatStr.replace(fStr.charAt(i), formatArr[i]);  
	     }  
	     return formatStr;
}

function getCurrentTime(formatStr,fdate){
	  var fTime, fStr = 'ymdhis';
	  var date,hour;
	     if (!formatStr)  
	        formatStr= "y-m-d h:i";  
	     if (fdate)  
	        fTime = new Date(fdate);  
	     else  
	        fTime = new Date(); 
	     if(fTime.getHours()<6||fTime.getHours() >19){
	    	 fTime.setDate(fTime.getDate()+1);
	    	 date=fTime.getDate();
	    	 hour='8';
	     	} else{
	     		date=fTime.getDate();
	     		hour=fTime.getHours()+3;
	     	}
	     var formatArr = [  
	         fTime.getFullYear().toString(),  
	         (fTime.getMonth()+1).toString(),  
	         date.toString(),  
	         hour.toString(),
	         '00',
	     ];  
	     for (var i=0; i<formatArr.length; i++){  
	        formatStr = formatStr.replace(fStr.charAt(i), formatArr[i]);  
	     }  
	     return formatStr;
}

//日期加上天数得到新的日期  
//dateTemp 需要参加计算的日期，days要添加的天数，返回新的日期，日期格式：YYYY-MM-DD  
function getNewDay(date, days) {  
	var d=new Date(date); 
  d.setDate(d.getDate()+days); 
  var m=d.getMonth()+1; 
  return d.getFullYear()+'-'+m+'-'+d.getDate(); 
} 

function getNextTwoDays(){
	var temp=getCurrentTime();
	var date=temp.substring(0, 10);
	var time=temp.substring(11, temp.length);
	var rdate=getNewDay(date,2);
	return rdate+" "+time;
}
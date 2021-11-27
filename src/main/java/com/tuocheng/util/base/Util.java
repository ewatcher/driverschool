package com.tuocheng.util.base;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

	private final static char[] digits = { '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's',
			't', 'u', 'v', 'w', 'x', 'y', 'z', '0', 'o', 'O', '~', '!', '(', ')', '_', '@', '#', '$', '%', '^', '&' };

	
	/**
	 * 把对象转换为整型数,失败返回默认值 def
	 * 
	 * @param obj
	 * @param def
	 * @return
	 */
	public static int objToInt(Object obj, int def) {
		try {
			if (obj != null) {
				return Integer.parseInt(obj.toString());
			} else {
				return def;
			}
		} catch (Exception ex) {
			return def;
		}
	}

	/**
	 * 把对象转换为整型数,失败返回默认值 def
	 * 
	 * @param obj
	 * @param def
	 * @return
	 */
	public static long objToLong(Object obj, long def) {
		try {
			if (obj != null) {
				return Long.parseLong(obj.toString());
			} else {
				return def;
			}
		} catch (Exception ex) {
			return def;
		}
	}

	/**
	 * 把对象转换为字符串,为null返回默认def
	 * 
	 * @param obj
	 * @param def
	 * @return
	 */
	public static String objToString(Object obj, String def) {
		try {
			if (obj != null) {
				return obj.toString().trim();
			} else {
				return def;
			}
		} catch (Exception ex) {
			return def;
		}
	}
	
	/**
	 * 把对象转换为浮点数,错误返回默认def
	 * 
	 * @param obj
	 * @param def
	 * @return
	 */
	public static float objToFloat(Object obj, float def) {
		try {
			if (obj != null) {
				return Float.parseFloat(obj.toString());
			} else {
				return def;
			}
		} catch (Exception ex) {
			return def;
		}
	}

	/**
	 * 得到字符串的MD5
	 * 
	 * @param text
	 * @return
	 */
	public static String md5(String text) {
		if (text == null)
			return null;
		StringBuffer buf = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(text.getBytes());
			byte b[] = md.digest();
			int i;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return buf.toString();
	}
	
	/**
	 * 得到字符串的SHA1
	 * 
	 * @param text
	 * @return
	 */
	public static String SHA1(String text) {
		if (text == null)
			return null;
		StringBuffer buf = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(text.getBytes());
			byte b[] = md.digest();
			int i;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return buf.toString();
	}

	/**
	 * 自定义进制转换,没有 0 和 O
	 * 
	 * @param i
	 *            要转换的长整型数
	 * @param shift
	 *            2:2进制; 3:8进制; 4:16进制 5:32进制 6:64进制
	 * @return
	 */
	private static String toUnsignedString(long i, int shift) {
		char[] buf = new char[64];
		int charPos = 64;
		int radix = 1 << shift;
		long mask = radix - 1;
		do {
			buf[--charPos] = digits[(int) (i & mask)];
			i >>>= shift;
		} while (i != 0);
		return new String(buf, charPos, (64 - charPos));
	}

	/**
	 * 生成一个重复纪律很底的字符串
	 * 
	 * @param type
	 *            类型
	 * @return
	 */
	public static String getRandomString() {
		java.util.Random random = new java.util.Random();
		int n = random.nextInt(100000);
		if (n < 10000)
			n = 99999 - n;
		String temp = toUnsignedString(System.currentTimeMillis() * 100000l + n, 5);
		return temp;
	}

	/**
	 * 生成一个重复纪律很底的字符串
	 * 
	 * @param type
	 *            类型
	 * @return
	 */
	public static long getRandomLong() {
		String s1 = Long.toString(System.currentTimeMillis() / 1000 / 60 >> 3);

		java.util.Random random = new java.util.Random();
		int n = random.nextInt(100000);
		if (n < 10000)
			n = 99999 - n;
		String s2 = Integer.toString(n);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s1.length(); i++) {
			if (i == 0)
				sb.append(s2.charAt(0));
			if (i == 3)
				sb.append(s2.charAt(1));
			if (i == 5)
				sb.append(s2.charAt(2));
			if (i == 9)
				sb.append(s2.charAt(3));
			sb.append(s1.charAt(i));
		}
		return objToLong(sb.toString(), 0);
	}

	/**
	 * 判断字符串是否有值。“”：返回false
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotNull(String str) {
		if (str != null && str.length() > 0)
			return true;
		return false;
	}
	
	/**
	 * 清空字符串两端空格
	 * @param str
	 * @return
	 */
	public static String trim(String str){
		if(str != null && str.length() > 0){
			return str.trim();
		} else {
			return "";
		}
	}

	/**
	 * 得到价格（分）的货币表示
	 * 
	 * @param prices
	 * @return
	 */
	public static String getGoodsPrice(int prices) {
		DecimalFormat format = new DecimalFormat("#0.00");
		double f = prices / 100.00;
		return format.format(f);
	}

	/**
	 * 获取xml安全的字符串
	 * 
	 * @param text
	 * @return
	 */
	public static String getXMLSafeString(String text) {
		if (text == null)
			return "";
		text = text.replaceAll("&amp;", "#x_x#");
		text = text.replaceAll("&", "&amp;");
		text = text.replaceAll("<", "&lt;");
		text = text.replaceAll(">", "&gt;");
		text = text.replaceAll("\"", "&quot;");
		text = text.replaceAll("\'", "&apos;");
		text = text.replaceAll("#x_x#", "&amp;");
		return text.trim();
	}

	/**
	 * 判断字符串是否符合正则表达式
	 * 
	 * @param str
	 * @param regex
	 *            正则表达式.如手机号. "[1](3|5|8)[0-9]{9}"
	 * @return
	 */
	public static boolean isMatcher(String str, String regex) {
		boolean value = false;
		if (str != null) {
			try {
				Pattern pattern = Pattern.compile(regex);
				Matcher isNum = pattern.matcher(str);
				if (isNum.matches())
					value = true;
			} catch (Exception ex) {
			}
		}
		return value;
	}

	/**
	 * 使用正则表达式获取到内容
	 * 
	 * @param text
	 *            文本内容
	 * @param regex
	 *            正则表达式
	 * @param flags
	 *            匹配模式如 Pattern.DOTALL | Pattern.CASE_INSENSITIVE . 34
	 * @param number
	 *            需要取第几个值.下标 1. 取第一个括号则为 1
	 * @return
	 */
	public static String getPatternValue(String text, String regex, int flags, int number) {
		if (text != null && regex != null && text.length() > 0 && regex.length() > 0) {
			try {
				Pattern pattern = Pattern.compile(regex, flags);
				Matcher matcher = pattern.matcher(text);
				if (matcher.find()) {
					if (matcher.groupCount() >= number && matcher.group(number) != null) {
						// MLog.d("查找到正则:" + regex);
						// MLog.d("得到返回内容:" + matcher.group(number));
						return matcher.group(number);
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 得到文件的扩展名(包含 . 如: .jpg)
	 * 
	 * @param file
	 * @return
	 */
	public static String getFileExtName(String name) {
		if (name != null) {
			int index = name.lastIndexOf(".");
			if (index > 0) {
				return name.substring(index, name.length());
			} else {
				return name;
			}
		}
		return null;
	}
	
	/**
	 * 把时间戳(毫秒)转成 20110502 格式.
	 * 
	 * @param time
	 * @param 时间格式yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getDateStr(long time, String dateFormat) {
		return new SimpleDateFormat(dateFormat).format(time);
	}
	
	/**
	 * 把日期字符串（2015-02-04） 转时间戳
	 * @param date
	 * @param "yyyy-MM-dd"
	 * @return
	 */
	public static long strToTime(String dateString, String format){
		try {
			SimpleDateFormat sdf =   new SimpleDateFormat(format);
			Date date = sdf.parse(dateString);
			return date.getTime();
		} catch (ParseException e) {
			//e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * 时间戳（毫秒） 转 date 对象
	 * @param timeMillis 时间戳（毫秒）
	 * @return
	 */
	public static Date timeToDate(long timeMillis){
		return new Date(timeMillis);
	}
	
	/**
	 * 获得明天 0点0分1秒的时间戳
	 * 
	 * @return
	 */
	public static long getTomorrowTime() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, 1);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 0, 0, 1);
		return cal.getTimeInMillis();
	}

	/**
	 * 获得昨天 0点0分1秒的时间戳
	 * 
	 * @return
	 */
	public static long getYesterdayTime() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -1);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 0, 0, 1);
		return cal.getTimeInMillis();
	}
	
	/**
	 * 获得 n天前(n为负整数)或 n天后(n为正整数) 0点0分1秒的时间戳(毫秒)
	 * 
	 * @return
	 */
	public static long getTimeOffsetDay(int n) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, n);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 0, 0, 1);
		return cal.getTimeInMillis();
	}
	
	/**
	 * 得到当前时间的字符串表示方式.
	 * 
	 * @param dateFormat
	 *            时间格式yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getNowDate(String dateFormat) {
		return new SimpleDateFormat(dateFormat).format(new Date());
	}
	
	/**
	 * 数字日期（20150406） 转字符串日期（2015-04-06）
	 * @param date
	 * @return
	 */
	public static String intDateToStrDate(int date){
		String str = Integer.toString(date);
		StringBuilder sb = new StringBuilder();
		sb.append(str.charAt(0));
		sb.append(str.charAt(1));
		sb.append(str.charAt(2));
		sb.append(str.charAt(3));
		sb.append('-');
		sb.append(str.charAt(4));
		sb.append(str.charAt(5));
		sb.append('-');
		sb.append(str.charAt(6));
		sb.append(str.charAt(7));
		return sb.toString();
	}
	
	/**
	 * @param timeInMillis (毫秒)
	 * @return
	 */
	public static String getNowToDateString(long timeInMillis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timeInMillis);
		int d = calendar.get(Calendar.DATE);

		calendar.setTimeInMillis(System.currentTimeMillis());
		int d2 = calendar.get(Calendar.DATE);

		switch (d2 - d) {
		case 0:
			return "今天";
		case 1:
			return "昨天";
		case 2:
			return "前天";
		case 3:
			return "3天前";
		case 4:
			return "4天前";
		case 5:
			return "5天前";
		case 6:
			return "6天前";
		case 7:
			return "7天前";
		}

		return "";
	}

	public static String getSchoolAreaNameById(String orgId){
		if(!ValidateUtil.isValid(orgId)){
			return null;
		}
		if(orgId.equals("")){
			return "";
		}else if(orgId.trim().equals("b78ffef2-7c54-40fe-be4b-1910a87c8bbs")){
			return "广西驰程交通驾驶员培训有限责任公司";
		}else if(orgId.trim().equals("c0510169-8edc-470e-b038-c299c7735bty")){
			return "田阳威龙驾校";
		}else if(orgId.trim().equals("7d15f11b-2928-41e8-8406-8b49cf3939td")){
			return "田东旗胜驾校";
		}else if(orgId.trim().equals("606437a6-574c-4b0b-b79f-1a98bd4a45pg")){
			return "平果校区";
		}else if(orgId.trim().equals("357c1d29-c991-4eed-a331-949f3eb4e9db")){
			return "德保常顺驾校";
		}else if(orgId.trim().equals("6b698583-3aad-4702-8aa5-6e55b27df8jx")){
			return "靖西众安驾校";
		}else if(orgId.trim().equals("357c1d29-c991-4eed-a331-949f3eb4e9np")){
			return "那坡孟安驾校";
		}else if(orgId.trim().equals("357c1d29-c991-4eed-a331-949f3eb4e9tl")){
			return "田林永生驾校";
		}else if(orgId.trim().equals("357c1d29-c991-4eed-a331-949f3eb4e9xl")){
			return "西林旺程驾校";
		}else if(orgId.trim().equals("357c1d29-c991-4eed-a331-949f3eb4e9ll")){
			return "隆林校区";
		}else if(orgId.trim().equals("357c1d29-c991-4eed-a881-949f3eb008ly")){
			return "凌云校区";
		}else if(orgId.trim().equals("357c1d29-c991-4eed-a331-949f3eb888ly")){
			return "乐业校区";
		}
		return null;
	}
	

}

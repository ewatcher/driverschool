package com.tuocheng.util.base;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 使用日期做为编号
 * @author MEI702
 *
 */
public class GeneraterIdByDateUtil {
	/**
	 * 根据当前日期自动生成编号（格式为：yyyyMMdd-HHmmss）
	 * @return
	 */
	public static String getGeneraterIdByDate(){
		Date currentDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
		return sdf.format(currentDate);
	}
	
	/**
	 *  根据当前日期自动生成编号（格式为：yyyy-MM-dd）
	 * @return
	 */
	public static String getGeneraterIdByDateNoSecond(){
		Date currentDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(currentDate);
	}
	
}

package com.tuocheng.util.base;

import java.text.DecimalFormat;
import java.util.Calendar;

public class LogUtil {
	/**
	 * 生成日志表名称:logs_2016_1 offset:偏移量
	 */
	public static String generateLogTableName(int offset) {
		Calendar c = Calendar.getInstance();
		// 2016
		int year = c.get(Calendar.YEAR);
		// 0 -11
		int month = c.get(Calendar.MONTH) + 1 + offset;

		if (month > 12) {
			year++;
			month = month - 12;
		}
		if (month < 1) {
			year--;
			month = month + 12;
		}
		//初始化月份格式为01，02
		DecimalFormat df = new DecimalFormat();
		df.applyPattern("00");
		return "logs_" + year + "_" + df.format(month);
	}
}

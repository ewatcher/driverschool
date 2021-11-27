package com.tuocheng.util.base;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import com.tuocheng.pageModel.demo.Car;

public class DateUtil {
	public static Date d = new Date();
	public static GregorianCalendar gc = new GregorianCalendar();
	public static Calendar calendar = Calendar.getInstance();
	public static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

	public static String getYears() {
		gc.setTime(d);
		gc.add(1, +1);
		gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc.get(Calendar.DATE));
		return sf.format(gc.getTime());
	}

	/**
	 * 返回日期为当前日期加7天
	 * 
	 * @return
	 */
	public static Date getNextWeeks() {
		gc.setTime(d);
		gc.add(5, +7);
		gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc.get(Calendar.DATE));
		return gc.getTime();
	}

	public static Date getTowWeeks() {
		gc.setTime(d);
		gc.add(5, +15);
		gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc.get(Calendar.DATE));
		return gc.getTime();
	}

	public static String getHalfYear() {
		gc.setTime(d);
		gc.add(2, +6);
		gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc.get(Calendar.DATE));
		return sf.format(gc.getTime());
	}

	public static String getQuarters() {
		gc.setTime(d);
		gc.add(2, +3);
		gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc.get(Calendar.DATE));
		return sf.format(gc.getTime());
	}

	public static Date getLocalDate() {
		d.setHours(0);
		d.setMinutes(0);
		d.setSeconds(0);
		return d;
	}

	/**
	 * 获取某年的第一天日期
	 * 
	 * @param year
	 * @return
	 */
	public static Date getYearFirstDate(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date currYearFirst = calendar.getTime();
		return currYearFirst;
	}

	/**
	 * 获取某年最后一天日期
	 * 
	 * @param year
	 *            年份
	 * @return Date
	 */
	public static Date getYearLastDates(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();

		return currYearLast;
	}

	/**
	 * 获取某月的第一天日期
	 * 
	 * @param year
	 * @return
	 */
	public static Date getMonthFirstDate(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		Date currYearFirst = calendar.getTime();
		return currYearFirst;
	}

	/**
	 * 获取某年最后一天日期
	 * 
	 * @param year
	 *            年份
	 * @return Date
	 */
	public static Date getMontLastDate(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.roll(Calendar.DAY_OF_MONTH, -1);
		Date currYearLast = calendar.getTime();

		return currYearLast;
	}

	/**
	 * 得到某年某周的第一天
	 * 
	 * @param year
	 * @param week
	 * @return
	 */
	public static Date getMondayOfThisWeek() {
		Calendar c = Calendar.getInstance();
		int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0)
			dayofweek = 7;
		c.add(Calendar.DATE, -dayofweek + 1);
		Date date = c.getTime();
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		return date;
	}

	/**
	 * 得到本周周日
	 * 
	 * @return date
	 */
	public static Date getSundayOfThisWeek() {
		Calendar c = Calendar.getInstance();
		int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0)
			dayofweek = 7;
		c.add(Calendar.DATE, -dayofweek + 7);
		Date date = c.getTime();
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		return date;
	}

	/**
	 * 將日期轉換成字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String DateToString(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		Integer year = c.get(Calendar.YEAR);
		Integer month = c.get(Calendar.MONTH) + 1;
		Integer day = c.get(Calendar.DATE);
		return year.toString() + "-" + month.toString() + "-" + day.toString();
	}

	/**
	 * 將日期轉換成字符串(返回:年－月－日 时:分)
	 * 
	 * @param date
	 * @return
	 */
	public static String DateToStringYMDHM(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		Integer year = c.get(Calendar.YEAR);
		Integer month = c.get(Calendar.MONTH) + 1;
		Integer day = c.get(Calendar.DATE);
		Integer hour = c.get(Calendar.HOUR);
		Integer minues = c.get(Calendar.MINUTE);
		return year.toString() + "-" + month.toString() + "-" + day.toString() + " " + hour.toString() + ":"
				+ minues.toString();
	}

	/**
	 * 根据字符串（格式为：yyyy-MM-dd）获取日期
	 * 
	 * @param dateString
	 * @return
	 * @throws ParseException
	 */
	public static Date getDateByString(String dateString) throws ParseException {
		if (!ValidateUtil.isValid(dateString)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(dateString);
		return date;
	}

	/**
	 * 将日期转换为字符串 格式为：yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	/**
	 * 根据用户给定日期，返回该日期(星期，月，年)的第一天和最后一天
	 * 
	 * @param date
	 * @return(map key is:
	 *             "firstDateOfWeek,lastDateOfWeek,firstDateOfMonth,lastDateOfMonth"
	 *             )
	 * @throws ParseException
	 */
	public static Map<String, Date> getFisrtLastDateByDate(Date date) throws ParseException {
		Map<String, Date> retMap = new HashMap<String, Date>(0);
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		calendar.setTime(date);
		// 获取星期一，星期日
		int d = 0;
		if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
			d = -6;
		} else {
			d = 2 - calendar.get(Calendar.DAY_OF_WEEK);
		}
		calendar.add(Calendar.DAY_OF_WEEK, d);
		// 所在周开始日期
		retMap.put("firstDateOfWeek", calendar.getTime());
		calendar.add(Calendar.DAY_OF_WEEK, 1);
		retMap.put("secondDateOfWeek", calendar.getTime());
		calendar.add(Calendar.DAY_OF_WEEK, 1);
		retMap.put("thirdDateOfWeek", calendar.getTime());
		calendar.add(Calendar.DAY_OF_WEEK, 1);
		retMap.put("fourthDateOfWeek", calendar.getTime());
		calendar.add(Calendar.DAY_OF_WEEK, 1);
		retMap.put("fifthDateOfWeek", calendar.getTime());
		calendar.add(Calendar.DAY_OF_WEEK, 1);
		retMap.put("sixthDateOfWeek", calendar.getTime());
		calendar.add(Calendar.DAY_OF_WEEK, 1);
		retMap.put("lastDateOfWeek", calendar.getTime());
		// 获取上周日期
		calendar.add(Calendar.DAY_OF_WEEK, -13);
		retMap.put("firstDateOfLastWeek", calendar.getTime());
		calendar.add(Calendar.DAY_OF_WEEK, 1);
		retMap.put("secondDateOfLastWeek", calendar.getTime());
		calendar.add(Calendar.DAY_OF_WEEK, 1);
		retMap.put("thirdDateOfLastWeek", calendar.getTime());
		calendar.add(Calendar.DAY_OF_WEEK, 1);
		retMap.put("fourthDateOfLastWeek", calendar.getTime());
		calendar.add(Calendar.DAY_OF_WEEK, 1);
		retMap.put("fifthDateOfLastWeek", calendar.getTime());
		calendar.add(Calendar.DAY_OF_WEEK, 1);
		retMap.put("sixthDateOfLastWeek", calendar.getTime());
		calendar.add(Calendar.DAY_OF_WEEK, 1);
		retMap.put("lastDateOfLastWeek", calendar.getTime());
		// 获取一下周日期
		calendar.add(Calendar.DAY_OF_WEEK, 8);
		retMap.put("firstDateOfNextWeek", calendar.getTime());
		calendar.add(Calendar.DAY_OF_WEEK, 1);
		retMap.put("secondDateOfNextWeek", calendar.getTime());
		calendar.add(Calendar.DAY_OF_WEEK, 1);
		retMap.put("thirdDateOfNextWeek", calendar.getTime());
		calendar.add(Calendar.DAY_OF_WEEK, 1);
		retMap.put("fourthDateOfNextWeek", calendar.getTime());
		calendar.add(Calendar.DAY_OF_WEEK, 1);
		retMap.put("fifthDateOfNextWeek", calendar.getTime());
		calendar.add(Calendar.DAY_OF_WEEK, 1);
		retMap.put("sixthDateOfNextWeek", calendar.getTime());
		calendar.add(Calendar.DAY_OF_WEEK, 1);
		retMap.put("lastDateOfNextWeek", calendar.getTime());

		// 获取当前月第一天，最后一天
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		retMap.put("firstDateOfMonth", calendar.getTime());
		// 获取前月的最后一天
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		retMap.put("lastDateOfMonth", calendar.getTime());

		// 获取某年第一天，最后一天
		int year = calendar.get(Calendar.YEAR);
		retMap.put("firstDateOfYear", new SimpleDateFormat("yyyy-MM-dd").parse(year + "-01-01"));
		retMap.put("lastDateOfYear", new SimpleDateFormat("yyyy-MM-dd").parse(year + "-12-31"));
		// 获取去年第一天，最后一天
		retMap.put("firstDateOfLastYear", new SimpleDateFormat("yyyy-MM-dd").parse(year - 1 + "-01-01"));
		retMap.put("lastDateOfLastYear", new SimpleDateFormat("yyyy-MM-dd").parse(year - 1 + "-12-31"));

		// r返回
		return retMap;
	}

	/**
	 * 获取当前月的第一天，最后一天
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Map<String, Date> getDatesOfMonth(Date date) throws ParseException {
		Map<String, Date> retMap = new HashMap<String, Date>(0);
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		calendar.setTime(date);

		// 上一个月
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		retMap.put("firstDateOfLastMonth", calendar.getTime());
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		retMap.put("lastDateOfLastMonth", calendar.getTime());

		// 获取当前月第一天，最后一天
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		retMap.put("firstDateOfMonth", calendar.getTime());
		// 获取前月的最后一天
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		retMap.put("lastDateOfMonth", calendar.getTime());

		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		retMap.put("firstDateOfNextMonth", calendar.getTime());
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		retMap.put("lastDateOfNextMonth", calendar.getTime());

		return retMap;
	}

	/**
	 * 获取当年，上一年的第一天与最后一天
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Map<String, Date> getDatesOfYear(Date date) throws ParseException {
		Map<String, Date> retMap = new HashMap<String, Date>(0);
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		calendar.setTime(date);

		// 获取某年第一天，最后一天
		int year = calendar.get(Calendar.YEAR);
		retMap.put("firstDateOfYear", new SimpleDateFormat("yyyy-MM-dd").parse(year + "-01-01"));
		retMap.put("lastDateOfYear", new SimpleDateFormat("yyyy-MM-dd").parse(year + "-12-31"));
		// 获取去年第一天，最后一天
		retMap.put("firstDateOfLastYear", new SimpleDateFormat("yyyy-MM-dd").parse(year - 1 + "-01-01"));
		retMap.put("lastDateOfLastYear", new SimpleDateFormat("yyyy-MM-dd").parse(year - 1 + "-12-31"));

		// 返回
		return retMap;
	}

	public static Map<String, Date> getLastDateOfMonth(Date date) throws ParseException {
		Map<String, Date> retMap = new HashMap<String, Date>(0);
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		calendar.setTime(date);
		// 获取星期一，星期日

		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		retMap.put("firstDateOfMonth", calendar.getTime());
		// 获取前月的最后一天
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		retMap.put("lastDateOfMonth", calendar.getTime());

		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		retMap.put("lastDateOfLastMonth", calendar.getTime());
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		retMap.put("firstDateOfLastMonth", calendar.getTime());

		// 返回
		return retMap;
	}

}

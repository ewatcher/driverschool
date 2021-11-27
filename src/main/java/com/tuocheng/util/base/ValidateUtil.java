package com.tuocheng.util.base;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class ValidateUtil {
	/**
	 * 判断字符串有效性
	 */
	public static boolean isValid(String src) {
		if (src == null || "".equals(src.trim())) {
			return false;
		}
		return true;
	}

	/**
	 * 判断集合的有效性
	 */
	public static boolean isValid(Collection col) {
		if (col == null || col.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * 判断字符串数组的有效性
	 * 
	 * @param arrays
	 * @return
	 */
	public static boolean isValid(Object[] arrays) {
		if (arrays == null || arrays.length == 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * 判断字符串数组的有效性
	 * 
	 * @param arrays
	 * @return
	 */
	public static boolean isValidListObject(List obj) {
		if (obj != null && obj.size() > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断整型对象有效性
	 * 
	 * @param arrays
	 * @return
	 */
	public static boolean isValid(Integer entry) {
		if (entry == null || entry<0) {
			return false;
		}
		return true;
	}
	
	/**
	 * 判断浮点型对象有效性
	 * 
	 * @param arrays
	 * @return
	 */
	public static boolean isValid(Double entry) {
		if (entry == null || entry<=0) {
			return false;
		}
		return true;
	}
	
	/**
	 * 判断浮点型对象有效性
	 * 
	 * @param arrays
	 * @return
	 */
	public static boolean isValid(Date entry) {
		if (entry == null) {
			return false;
		}
		return true;
	}
	
	/**
	 * 判断浮点型对象有效性
	 * 
	 * @param arrays
	 * @return
	 */
	public static boolean isValid(Map entry) {
		if (entry == null||entry.size()<=0) {
			return false;
		}
		return true;
	}

}

package com.tuocheng.util.base;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ListUtil {
	/**
	 * 生成日志表名称:logs_2016_1 offset:偏移量
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List removeRepeat(List list) {
		if (!ValidateUtil.isValidListObject(list)) {
			return null;
		}
		Set<Object> set = new HashSet<Object>();
		List<Object> newList = new ArrayList<Object>();
		for (Iterator<Object> iter = list.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (set.add(element))
				newList.add(element);
		}
		list.clear();
		list.addAll(newList);
		if (ValidateUtil.isValidListObject(list)) {
			return list;
		}
		return null;
	}
}

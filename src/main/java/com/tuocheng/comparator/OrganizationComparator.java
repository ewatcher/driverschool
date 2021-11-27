package com.tuocheng.comparator;

import java.util.Comparator;

import com.tuocheng.model.demo.Torganization;

/**
 * 菜单排序
 * 
 * @author 农峰
 * 
 */
public class OrganizationComparator implements Comparator<Torganization> {

	public int compare(Torganization o1, Torganization o2) {
		int i1 = o1.getSequence()!= null ? o1.getSequence().intValue() : -1;
		int i2 = o2.getSequence() != null ? o2.getSequence().intValue() : -1;
		return i1 - i2;
	}

}

package com.tuocheng.comparator;

import java.util.Comparator;

import com.tuocheng.model.demo.Tauth;

/**
 * Auth排序
 * 
 * @author 农峰
 * 
 */
public class AuthComparator implements Comparator<Tauth> {

	public int compare(Tauth o1, Tauth o2) {
		int i1 = o1.getCseq() != null ? o1.getCseq().intValue() : -1;
		int i2 = o2.getCseq() != null ? o2.getCseq().intValue() : -1;
		return i1 - i2;
	}

}

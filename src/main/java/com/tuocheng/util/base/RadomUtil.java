package com.tuocheng.util.base;

import java.util.Random;

public class RadomUtil {

	/**
	 * 获取指定范围内的随机数据
	 * @param minVal
	 * @param maxVal
	 * @return
	 */
	public static Integer getRandomByRand(Integer minVal, Integer maxVal) {
		Random random = new Random();
		return random.nextInt(maxVal) % (maxVal - minVal + 1) + minVal;
	}

}

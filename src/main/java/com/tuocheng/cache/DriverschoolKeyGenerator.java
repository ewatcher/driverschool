package com.tuocheng.cache;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;

import com.tuocheng.util.base.StringUtil;

public class DriverschoolKeyGenerator implements KeyGenerator {

	@Override
	public Object generate(Object target, Method method, Object... params) {
		String className = target.getClass().getSimpleName();
		String methodName = method.getName();
		String paramsTemp = StringUtil.arrayToString(params);
		String key = className + "@" + target.hashCode() + "." + methodName
				+ "(" + paramsTemp + ")";
		return key;
	}

}

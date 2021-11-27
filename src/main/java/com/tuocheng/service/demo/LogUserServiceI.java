package com.tuocheng.service.demo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public interface LogUserServiceI {

	public void beforeLog(JoinPoint point)throws Exception;

	public void afterLog(JoinPoint point)throws Exception;

	public Object aroundLog(ProceedingJoinPoint point)throws Exception;

}

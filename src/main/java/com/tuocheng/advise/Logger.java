package com.tuocheng.advise;

import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tuocheng.pageModel.base.SessionInfo;
import com.tuocheng.pageModel.demo.Log;
import com.tuocheng.service.demo.LogServiceI;
import com.tuocheng.util.base.StringUtil;
import com.tuocheng.util.base.ValidateUtil;

public class Logger {
	// 1.注入service
	private LogServiceI logService;

	@Autowired
	public void setLogService(LogServiceI logService) {
		this.logService = logService;
	}

	public Object record(ProceedingJoinPoint pjp) throws Exception {
		Log log = new Log();
		try {
			ActionContext ac = ActionContext.getContext();
			// 1.设置操作人
			if (ac != null) {
				Map<String, Object> session = ac.getSession();
				if (session != null) {
					SessionInfo sessionInfo = (SessionInfo) session
							.get("sessionInfo");
					if (sessionInfo != null) {
						log.setOperator(""
								+ sessionInfo.getUser().getCname().trim() + ":"
								+ sessionInfo.getUser().getCid());
						//根据校区标识对日志按校区分类
						if(ValidateUtil.isValid(sessionInfo.getSchoolArea())){
							log.setSchoolArea(sessionInfo.getSchoolArea());
						}else{
							log.setSchoolArea("超级管理员");
						}
						
					}
				}
			}
			// 操作方法
			String operName = pjp.getSignature().getName();
			log.setOperName(operName);
			// 操作參數
			Object[] params = pjp.getArgs();
			log.setOperParams(StringUtil.arrayToString(params));
			Object ret = pjp.proceed();
			//设置操作结果
			log.setOperResult("success");
			//设置结果消息
			if (ret != null) {
				log.setResultMsg(ret.toString());
			}
			return ret;
		} catch (Throwable e) {
			log.setOperResult("failure");
			log.setResultMsg(e.getMessage());
		} finally {
			logService.saveEntity(log);
		}
		return null;
	}

}

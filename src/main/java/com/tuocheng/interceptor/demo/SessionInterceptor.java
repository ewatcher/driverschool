package com.tuocheng.interceptor.demo;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.tucheng.struts2.UserAware;
import com.tuocheng.action.base.BaseAction;
import com.tuocheng.model.demo.Tuser;
import com.tuocheng.pageModel.base.SessionInfo;
import com.tuocheng.util.base.ResourceUtil;

/**
 * session拦截器
 * 
 * @author 农峰
 * 
 */
public class SessionInterceptor extends MethodFilterInterceptor {

	private static final Logger logger = Logger
			.getLogger(SessionInterceptor.class);

	protected String doIntercept(ActionInvocation actionInvocation)
			throws Exception {
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext
				.getRequest().getSession()
				.getAttribute(ResourceUtil.getSessionInfoName());
		if (sessionInfo == null) {
			ServletActionContext.getRequest().setAttribute("msg",
					"您还没有登录或登录已超时，请重新登录，然后再刷新本功能！");
			return "noSession";
		}
		BaseAction action = (BaseAction) actionInvocation.getAction();
		if (action != null && action instanceof UserAware) {
			((UserAware) action).setUser(sessionInfo.getUser());
		}
		return actionInvocation.invoke();
	}

}

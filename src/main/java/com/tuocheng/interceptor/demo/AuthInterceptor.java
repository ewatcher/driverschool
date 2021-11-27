package com.tuocheng.interceptor.demo;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.tucheng.struts2.UserAware;
import com.tuocheng.action.base.BaseAction;
import com.tuocheng.model.demo.Tuser;
import com.tuocheng.pageModel.base.SessionInfo;
import com.tuocheng.pageModel.demo.User;
import com.tuocheng.util.base.RequestUtil;
import com.tuocheng.util.base.ResourceUtil;

/**
 * 权限拦截器
 * 
 * @author 农峰
 * 
 */
public class AuthInterceptor extends MethodFilterInterceptor {

	private static final Logger logger = Logger
			.getLogger(AuthInterceptor.class);

	protected String doIntercept(ActionInvocation actionInvocation)
			throws Exception {
		ActionContext actionContext = actionInvocation.getInvocationContext();
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext
				.getRequest().getSession()
				.getAttribute(ResourceUtil.getSessionInfoName());
		if (sessionInfo.getLoginName().equals("driverschool")) {// admin用户不需要验证权限
			return actionInvocation.invoke();
		}
		
		String requestPath = RequestUtil.getRequestPath(ServletActionContext
				.getRequest());
		String authUrls = sessionInfo.getAuthUrls();
		boolean b = false;
		for (String url : authUrls.split(",")) {
			if (requestPath.equals(url)) {
				b = true;//it own auth 
				break;
			}
		}
		if (b) {
			return actionInvocation.invoke();
		}
		ServletActionContext.getRequest().setAttribute("msg",
				"您没有访问此功能的权限！权限路径为[" + requestPath + "]请联系管理员给你赋予相应权限。");
		return "noAuth";
	}

}

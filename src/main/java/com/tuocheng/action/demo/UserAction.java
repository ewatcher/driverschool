package com.tuocheng.action.demo;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.tuocheng.action.base.BaseAction;
import com.tuocheng.model.demo.Tuser;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.base.SessionInfo;
import com.tuocheng.pageModel.demo.User;
import com.tuocheng.service.demo.RoleServiceI;
import com.tuocheng.service.demo.UserServiceI;
import com.tuocheng.util.base.ExceptionUtil;
import com.tuocheng.util.base.IpUtil;
import com.tuocheng.util.base.ResourceUtil;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 用户ACTION
 * 
 * @author 农峰
 * 
 */
@Namespace("/demo")
@Action(value = "userAction", results = {
		@Result(name = "user", location = "/demo/admin/user.jsp"),
		@Result(name = "userAdd", location = "/demo/admin/userAdd.jsp"),
		@Result(name = "userEdit", location = "/demo/admin/userEdit.jsp"),
		@Result(name = "userRoleEdit", location = "/demo/admin/userRoleEdit.jsp"),
		@Result(name = "doNotNeedAuth_userInfo", location = "/demo/user/userInfo.jsp"),
		@Result(name = "index", location = "/demo/index.jsp") })
public class UserAction extends BaseAction<User> {

	/**
	 * 序列化标识符
	 */
	private static final long serialVersionUID = -1824843271401691003L;

	private static final Logger logger = Logger.getLogger(UserAction.class);

	private UserServiceI demoUserService;
	private RoleServiceI demoRoleService;

	public UserServiceI getDemoUserService() {
		return demoUserService;
	}

	/**
	 * 注入service
	 * 
	 * @param demoUserService
	 */
	@Autowired
	public void setDemoUserService(UserServiceI demoUserService) {
		this.demoUserService = demoUserService;
	}

	@Autowired
	public void setDemoRoleService(RoleServiceI demoRoleService) {
		this.demoRoleService = demoRoleService;
	}

	public String user() {
		return "user";
	}

	// 到达添加用户页面
	public String userAdd() {
		return "userAdd";
	}

	// 到达更新用户页面
	public String userEdit() {
		return "userEdit";
	}

	// 到达用户角色编辑页面
	public String userRoleEdit() {
		return "userRoleEdit";
	}

	// 达到用授权页面
	public String doNotNeedAuth_userInfo() {
		return "doNotNeedAuth_userInfo";
	}

	public void doNotNeedAuth_editUserInfo() {
		Json j = new Json();
		try {
			demoUserService.editUserInfo(model);
			j.setSuccess(true);
			j.setMsg("修改成功！");
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("修改失败！");
		}
		super.writeJson(j);
	}

	public void doNotNeedSession_login() throws Exception {
		Json j = new Json();
		User u = demoUserService.login(model);
		if (u != null) {
			SessionInfo sessionInfo = new SessionInfo();
			sessionInfo.setUserId(u.getCid());
			sessionInfo.setLoginName(model.getCname());
			sessionInfo.setLoginPassword(model.getCpwd());
			sessionInfo.setSchoolArea(u.getSchoolArea());
			sessionInfo.setSchoolAreaName(u.getSchoolAreaName());
			sessionInfo.setIp(IpUtil.getIpAddr(ServletActionContext
					.getRequest()));
			sessionInfo.setRoleIds(u.getRoleIds());
			sessionInfo.setRoleNames(u.getRoleNames());
			sessionInfo.setUser(u);
			ServletActionContext
			.getRequest()
			.getSession()
			.setAttribute(ResourceUtil.getSessionInfoName(),
					sessionInfo);
			j.setObj(sessionInfo);
			j.setMsg("登录成功！");
			j.setSuccess(true);
		} else {
			j.setMsg("登录失败！用户名或密码错误！");
		}
		super.writeJson(j);
	}

	public void doNotNeedSession_logout() {
		ServletActionContext.getRequest().getSession().invalidate();
		Json j = new Json();
		j.setSuccess(true);
		super.writeJson(j);
	}

	public void doNotNeedSession_reg() {
		Json j = new Json();
		try {
			demoUserService.save(model);
			j.setMsg("注册成功！");
			j.setSuccess(true);
		} catch (Exception e) {
			j.setMsg("注册失败，用户名已存在！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(j);
	}

	// 添加用户信息
	public void add() {
		Json j = new Json();
		try {
			User user = demoUserService.save(model);
			j.setMsg("添加成功！");
			j.setSuccess(true);
			// 将修改后的数据通过j.obj返回给前台
			j.setObj(user);
		} catch (Exception e) {
			j.setMsg("添加失败，用户名已存在！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(j);
	}

	// 更新用户信息
	public void edit() {
		Json j = new Json();
		try {
			User user = demoUserService.update(model);
			j.setSuccess(true);
			j.setMsg("编辑成功！");
			// 将修改后的数据通过j.obj返回给前台
			j.setObj(user);
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("编辑失败，用户名已存在！");
		}
		super.writeJson(j);
	}

	public void roleEdit() {
		Json j = new Json();
		try {
			demoUserService.roleEdit(model);
			j.setSuccess(true);
			j.setMsg("编辑成功！");
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("编辑失败！");
		}
		super.writeJson(j);
	}

	public void delete() throws Exception {
		Json j = new Json();
		demoUserService.delete(model.getIds());
		j.setSuccess(true);
		j.setMsg("删除成功!");
		super.writeJson(j);
	}

	public void datagrid() throws Exception {
		// 超级管理员有查找所有校区选项
		if (ValidateUtil.isValid(user.getSchoolArea())) {
			this.model.setSchoolArea(user.getSchoolArea());
		}
		super.writeJson(demoUserService.datagrid(model));
	}

	public void doNotNeedSession_combobox() throws Exception {
		
		super.writeJson(demoUserService.combobox(model));
	}

	public void doNotNeedSession_datagrid() throws Exception {
		if (model.getQ() != null && !model.getQ().trim().equals("")) {
			model.setCname(model.getQ());
		}
		super.writeJson(demoUserService.datagrid(model));
	}

	/**
	 * 根据校区标识获取角色列表
	 * 
	 * @throws Exception
	 */
	public void doNotNeedSession_rolecombobox() throws Exception {
		super.writeJson(demoRoleService.combobox(model.getSchoolArea()));
	}
	
}

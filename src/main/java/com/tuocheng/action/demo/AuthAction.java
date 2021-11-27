package com.tuocheng.action.demo;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.BaseAction;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.demo.Auth;
import com.tuocheng.service.demo.AuthServiceI;
import com.tuocheng.util.base.ExceptionUtil;

/**
 * 权限ACTION
 * 
 * @author 农峰
 * 
 */
@Namespace("/demo")
@Action(value = "authAction", results = {
		@Result(name = "auth", location = "/demo/admin/auth.jsp"),
		@Result(name = "authAdd", location = "/demo/admin/authAdd.jsp"),
		@Result(name = "authEdit", location = "/demo/admin/authEdit.jsp") })
public class AuthAction extends BaseAction<Auth> {

	/**
	 * 序列化标识符
	 */
	private static final long serialVersionUID = 3585018359993711105L;

	private static final Logger logger = Logger.getLogger(AuthAction.class);

	private AuthServiceI demoAuthService;

	public AuthServiceI getDemoAuthService() {
		return demoAuthService;
	}

	@Autowired
	public void setDemoAuthService(AuthServiceI demoAuthService) {
		this.demoAuthService = demoAuthService;
	}

	/**
	 * 达到授权页面
	 * @return
	 */
	public String auth() {
		return "auth";
	}

	/**
	 * 打开添加授权页面
	 * @return
	 */
	public String authAdd() {
		return "authAdd";
	}

	/**
	 * 打开编码授权页面
	 * @return
	 */
	public String authEdit() {
		return "authEdit";
	}

	/**
	 * 获得权限树
	 */
	public void doNotNeedSession_tree() {
		super.writeJson(demoAuthService.tree(model, false));
	}

	//第二个参数为true，加载时显示所有子节点，限递归显示
	public void doNotNeedSession_treeRecursive() {
		super.writeJson(demoAuthService.tree(model, true));
	}

	/**
	 * 获得权限treegrid
	 */
	public void treegrid() {
		super.writeJson(demoAuthService.treegrid(model));
	}

	/**
	 * 删除一个权限
	 */
	public void delete() {
		Json j = new Json();
		try {
			demoAuthService.delete(model);
			j.setSuccess(true);
			j.setMsg("删除成功！");
			j.setObj(model.getCid());
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("删除失败！");
		}
		super.writeJson(j);
	}

	/**
	 * 编辑权限
	 */
	public void edit() {
		Json j = new Json();
		try {
			demoAuthService.edit(model);
			j.setSuccess(true);
			j.setMsg("编辑成功!");
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("编辑失败！");
		}
		super.writeJson(j);
	}

	/**
	 * 添加权限
	 */
	public void add() {
		Json j = new Json();
		try {
			demoAuthService.add(model);
			j.setSuccess(true);
			j.setMsg("添加成功!");
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("添加失败！");
		}
		super.writeJson(j);
	}

}

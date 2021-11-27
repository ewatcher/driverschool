package com.tuocheng.action.demo;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.tuocheng.action.base.BaseAction;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.demo.Auth;
import com.tuocheng.pageModel.demo.Role;
import com.tuocheng.service.demo.AuthServiceI;
import com.tuocheng.service.demo.RoleServiceI;
import com.tuocheng.util.base.ExceptionUtil;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 角色ACTION
 * 
 * @author 农峰
 * 
 */
@Namespace("/demo")
@Action(value = "roleAction", results = {
		@Result(name = "role", location = "/demo/admin/role.jsp"),
		@Result(name = "roleAdd", location = "/demo/admin/roleAdd.jsp"),
		@Result(name = "roleEdit", location = "/demo/admin/roleEdit.jsp"),
		@Result(name = "roleDetail", location = "/demo/admin/roleDetail.jsp") })
public class RoleAction extends BaseAction<Role> {

	/**
	 * 序列化标识符
	 */
	private static final long serialVersionUID = -8398059182773409963L;

	private static final Logger logger = Logger.getLogger(RoleAction.class);

	private RoleServiceI demoRoleService;
	private AuthServiceI demoAuthService;

	@Autowired
	public void setDemoRoleService(RoleServiceI demoRoleService) {
		this.demoRoleService = demoRoleService;
	}

	@Autowired
	public void setDemoAuthService(AuthServiceI demoAuthService) {
		this.demoAuthService = demoAuthService;
	}

	/**
	 * 跳转到角色管理页面
	 * 
	 * @return
	 */
	public String role() {
		return "role";
	}

	public String roleAdd() {
		return "roleAdd";
	}

	public String roleEdit() {
		return "roleEdit";
	}

	public String roleDetailPage() {
		return "roleDetail";
	}

	/**
	 * 获得角色datagrid
	 * 
	 * @throws Exception
	 */
	public void datagrid() throws Exception {
		// 超级管理员有查找所有校区选项
		if (ValidateUtil.isValid(user.getSchoolArea())) {
			this.model.setSchoolArea(user.getSchoolArea());
		}
		super.writeJson(demoRoleService.datagrid(model));
	}

	/**
	 * 添加一个角色
	 */
	public void add() {
		Json j = new Json();
		try {
			model.setOperator(user.getCname());
			demoRoleService.add(model);
			j.setSuccess(true);
			j.setMsg("添加成功！");
			// 将修改后的数据通过j.obj返回给前台
			j.setObj(model);
		} catch (Exception e) {
			j.setMsg("添加失败！");
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
		super.writeJson(j);
	}

	/**
	 * 编辑一个角色
	 */
	public void edit() {
		Json j = new Json();
		try {
			demoRoleService.edit(model);
			j.setSuccess(true);
			j.setMsg("编辑成功！");
			// 将修改后的数据通过j.obj返回给前台
			j.setObj(model);
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("编辑失败！");
		}
		super.writeJson(j);
	}

	/**
	 * 删除一个或多个角色
	 * 
	 * @throws Exception
	 */
	public void delete() throws Exception {
		Json j = new Json();
		demoRoleService.delete(model.getIds());
		j.setSuccess(true);
		j.setMsg("删除成功！");
		super.writeJson(j);
	}

	/**
	 * 获得角色下拉列表
	 * 
	 * @throws Exception
	 */
	public void doNotNeedSession_combobox() throws Exception {
		String schoolAreaId = null;
		if (user!=null) {
			schoolAreaId = user.getSchoolArea();
		}else if(ValidateUtil.isValid(model.getSchoolArea())){
			schoolAreaId=model.getSchoolArea();
		}
		super.writeJson(demoRoleService.combobox(schoolAreaId));
	}

}

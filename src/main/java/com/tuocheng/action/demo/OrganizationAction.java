package com.tuocheng.action.demo;


import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.BaseAction;
import com.tuocheng.model.demo.Tuser;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.demo.Organization;
import com.tuocheng.service.demo.OrganizationServiceI;
import com.tuocheng.util.base.ExceptionUtil;

/**
 * 机构管理action
 * 
 * @author MEI702
 * 
 */
@Namespace("/demo")
@Action(value = "organizationAction", results = {
		@Result(name = "organization", location = "/demo/organization/organization.jsp"),
		@Result(name = "organizationAdd", location = "/demo/organization/organizationAdd.jsp"),
		@Result(name = "organizationEdit", location = "/demo/organization/organizationEdit.jsp") })
public class OrganizationAction extends BaseAction<Organization> {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -5836976027913642129L;
	private static final Logger logger = Logger
			.getLogger(OrganizationAction.class);

	private OrganizationServiceI organizationService;

	public OrganizationServiceI getOrganizationService() {
		return organizationService;
	}
	
	@SuppressWarnings("unused")
	private Tuser user;

	@Autowired
	public void setOrganizationService(OrganizationServiceI organizationService) {
		this.organizationService = organizationService;
	}

	/**
	 * 首页获得菜单树
	 * @throws Exception 
	 */
	public void doNotNeedSession_tree() throws Exception {
		super.writeJson(organizationService.tree(model, true));
	}

	public void doNotNeedSession_treeRecursive() throws Exception {
		super.writeJson(organizationService.tree(model, true));
	}

	public String organization() {
		return "organization";
	}

	public String organizationAdd() {
		return "organizationAdd";
	}

	public String organizationEdit() {
		return "organizationEdit";
	}

	/**
	 * 获得菜单treegrid
	 * @throws Exception 
	 */
	public void treegrid() throws Exception {
		super.writeJson(organizationService.treegrid(model));
	}

	/**
	 * 删除一个菜单
	 */
	public void delete() {
		Json j = new Json();
		try {
			organizationService.delete(model);
			j.setSuccess(true);
			j.setMsg("删除成功！");
			j.setObj(model.getId());
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("删除失败！");
		}
		super.writeJson(j);
	}

	public void add() {
		Json j = new Json();
		try {
			organizationService.add(model);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("添加失败！");
		}
		super.writeJson(j);
	}

	/**
	 * 编辑菜单
	 */
	public void edit() {
		Json j = new Json();
		try {
			organizationService.update(model);
			j.setSuccess(true);
			j.setMsg("编辑成功!");
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("编辑失败！");
		}
		writeJson(j);
	}
	//根据标识，查找机构名称
	public void getDepartmentNameById(String department){
		Json j = new Json();
		try {
			Organization organization=organizationService.getOrgNameById(department);
			j.setSuccess(true);
			j.setMsg("农峰测试");
			j.setObj(organization);
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("查询失败！");
		}
		super.writeJson(j);
	}
	
	//从DAO层获取组织机构的标识和名称
	public void getAllOrganization() throws Exception{
		
		super.writeJson(organizationService.getAllOrganization());
	}
	
	public void doNotNeedSession_org(){
		try {
			super.writeJson(organizationService.getAllOrganization());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}

}

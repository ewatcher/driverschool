package com.tuocheng.action.demo;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.BaseAction;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.demo.Organization;
import com.tuocheng.pageModel.demo.Person;
import com.tuocheng.service.demo.OrganizationServiceI;
import com.tuocheng.service.demo.PersonServiceI;
import com.tuocheng.util.base.ExceptionUtil;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 人员管理action
 * 
 * @author MEI702
 * 
 */
@Namespace("/demo")
@Action(value = "personAction", results = {
		@Result(name = "person", location = "/demo/person/person.jsp"),
		@Result(name = "personAdd", location = "/demo/person/personAdd.jsp"),
		@Result(name = "personEdit", location = "/demo/person/personEdit.jsp"), })
public class PersonAction extends BaseAction<Person> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 794769370809817909L;
	private static final Logger logger = Logger.getLogger(PersonAction.class);

	private PersonServiceI personService;
	private OrganizationServiceI organizationService;

	public PersonServiceI getPersonService() {
		return personService;
	}

	// 注入service
	@Autowired
	public void setPersonService(PersonServiceI personService) {
		this.personService = personService;
	}

	public OrganizationServiceI getOrganizationService() {
		return organizationService;
	}

	// 注入service
	@Autowired
	public void setOrganizationService(OrganizationServiceI organizationService) {
		this.organizationService = organizationService;
	}

	// 到达人员管理主页面
	public String toPersonPage() {
		return "person";
	}

	// 到达添加人员页面
	public String toPersonAddPage() {
		return "personAdd";
	}

	// 到达编辑页面
	public String toPersonEditPage() {
		return "personEdit";
	}

	public void datagrid() throws Exception {
		// 超级管理员有查找所有校区选项
		if (ValidateUtil.isValid(user.getSchoolArea())) {
			this.model.setDepartmentId(user.getSchoolArea());
		}
		super.writeJson(personService.dataGrid(model));
	}

	// 添加信息
	public void add() {
		Json json = new Json();
		try {
			model.setOperator(user.getCname().trim());
			Person person = personService.save(model);
			json.setMsg("添加人员信息成功！");
			json.setSuccess(true);
			json.setObj(person);
		} catch (Exception e) {
			json.setMsg("添加人员信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 编辑信息
	public void edit() {
		Json json = new Json();
		try {
			Person person = personService.udpate(model);
			json.setMsg("编辑人员信息成功！");
			json.setSuccess(true);
			json.setObj(person);
		} catch (Exception e) {
			json.setMsg("编辑人员信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 删除信息
	public void delete() {
		Json json = new Json();
		try {
			personService.delete(model.getIds());
			json.setMsg("删除人员信息成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("删除人员信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);

	}

	public void getDepartmentNameById() {
		Json j = new Json();
		try {
			Organization organization = organizationService
					.getOrgNameById(model.getDepartment());
			j.setSuccess(true);
			j.setMsg("农峰测试");
			j.setObj(organization);
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("查询失败！");
		}
		super.writeJson(j);
	}

	// 身份证校验，不允许重复
	public void identityIdExistOrNot() throws Exception {
		super.writeJson(personService.identityIdExistOrNot(model.getIdentityId()
				.trim()));
	}
}

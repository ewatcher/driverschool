package com.tuocheng.service.demo;

import java.util.List;
import java.util.Map;

import com.tuocheng.model.demo.Torganization;
import com.tuocheng.pageModel.base.ComboboxJson;
import com.tuocheng.pageModel.base.TreeNode;
import com.tuocheng.pageModel.demo.Organization;

public interface OrganizationServiceI {

	/**
	 * 获取机构树
	 * 
	 * @param organization
	 * @param b
	 *            　是否递归子节点
	 * @return
	 */
	public List<TreeNode> tree(Organization organization, boolean b)
			throws Exception;

	/**
	 * 获取菜单treegrid
	 * 
	 * @param organization
	 * @return
	 */
	public List<Organization> treegrid(Organization organization)
			throws Exception;

	/**
	 * 添加机构信息
	 * 
	 * @param organization
	 */
	public void add(Organization organization) throws Exception;

	/**
	 * 更新机构信息
	 * 
	 * @param organization
	 */
	public void update(Organization organization) throws Exception;

	/**
	 * 删除机构信息
	 * 
	 * @param organization
	 */
	public void delete(Organization organization) throws Exception;

	/**
	 * 根据标识，查找机构名称
	 * 
	 * @param id
	 * @return
	 */
	public Organization getOrgNameById(String id) throws Exception;

	/**
	 * 从DAO层获取组织机构的标识和名称
	 * 
	 * @return
	 */
	public List<ComboboxJson> getAllOrganization() throws Exception;

	/**
	 * 根据机构标识查找机构信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Torganization getSingleById(String id) throws Exception;
}

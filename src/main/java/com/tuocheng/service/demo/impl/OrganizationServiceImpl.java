package com.tuocheng.service.demo.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javassist.expr.NewArray;

import org.aspectj.weaver.ast.Or;
import org.hibernate.dialect.Oracle10gDialect;
import org.hibernate.mapping.Array;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuocheng.comparator.MenuComparator;
import com.tuocheng.comparator.OrganizationComparator;
import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.Torganization;
import com.tuocheng.pageModel.base.ComboboxJson;
import com.tuocheng.pageModel.base.TreeNode;
import com.tuocheng.pageModel.demo.Organization;
import com.tuocheng.service.demo.OrganizationServiceI;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 机构管理(service层)
 * 
 * @author MEI702
 * 
 */
@Service("organizationService")
public class OrganizationServiceImpl implements OrganizationServiceI {

	private BaseDaoI<Torganization> organizationDao;

	public BaseDaoI<Torganization> getOrganizationDao() {
		return organizationDao;
	}

	@Autowired
	public void setOrganizationDao(BaseDaoI<Torganization> organizationDao) {
		this.organizationDao = organizationDao;
	}

	@Override
	public List<TreeNode> tree(Organization organization, boolean b) {
		List<Object> param = new ArrayList<Object>();
		String hql = "from Torganization t where t.parent is null order by t.sequence";
		if (organization != null && organization.getId() != null
				&& !organization.getId().trim().equals("")) {
			hql = "from Torganization t where t.parent.id =? order by t.sequence";
			param.add(organization.getId());
		}
		List<Torganization> oList = organizationDao.find(hql, param);
		List<TreeNode> tree = new ArrayList<TreeNode>();
		for (Torganization t : oList) {
			tree.add(this.tree(t, b));
		}
		return tree;
	}

	private TreeNode tree(Torganization t, boolean recursive) {
		TreeNode node = new TreeNode();
		node.setId(t.getId());
		node.setText(t.getName());
		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("url", t.getUrl());
		node.setAttributes(attributes);
		if (t.getIconcls() != null) {
			node.setIconCls(t.getIconcls());
		} else {
			node.setIconCls("");
		}
		// 默认情况下收起树，不展开
		if (t.getChildrens() != null && t.getChildrens().size() > 0) {
			node.setState("closed");
			if (recursive) {// 递归查询子节点
				List<Torganization> l = new ArrayList<Torganization>(
						t.getChildrens());
				Collections.sort(l, new OrganizationComparator());// 排序
				List<TreeNode> children = new ArrayList<TreeNode>();
				for (Torganization r : l) {
					TreeNode tn = tree(r, true);
					children.add(tn);
				}
				node.setChildren(children);
				node.setState("open");
			}
		}
		return node;
	}

	@Override
	public List<Organization> treegrid(Organization organization) {
		List<Torganization> oList = new ArrayList<Torganization>();
		if (organization != null && organization.getId() != null) {
			String hql = "from Torganization t where t.parent.id =? order by t.sequence";
			oList = organizationDao.find(hql,
					new Object[] { organization.getId() });
		} else {
			oList = organizationDao
					.find("from Torganization t where t.parent is null order by t.sequence ");
		}
		return this.changDaoModelToActionModel(oList);
	}

	@Override
	public void add(Organization organization) {
		Torganization t = new Torganization();
		BeanUtils.copyProperties(organization, t);
		t.setId(UUID.randomUUID().toString());
		if (organization.getParentId() != null) {
			t.setParent(organizationDao.get(Torganization.class,
					organization.getParentId()));
		}
		if (organization.getIconcls() != null) {
			t.setIconcls(organization.getIconcls());
		}
		organizationDao.save(t);
	}

	@Override
	public void update(Organization organization) {
		Torganization t = organizationDao.get(Torganization.class, organization.getId());
		BeanUtils.copyProperties(organization, t);
		if (organization.getIconcls() != null) {
			t.setIconcls(organization.getIconcls());
		}
		if (organization.getParentId() != null
				&& !organization.getParentId().equals(organization.getId())) {
			Torganization parentOrg=organizationDao.get(Torganization.class, organization.getParentId());
				if(parentOrg!=null){
					if(this.isDown(t, parentOrg)){
						//将当前的节点修改到当前节点的子节点
						Set<Torganization> orgs=t.getChildrens();
						if(orgs!=null&&orgs.size()>0){
							for(Torganization o:orgs){
								if(o!=null){
									o.setParent(null);
								}
							}
						}
					}
					t.setParent(parentOrg);
				}
			}
		}
	

	@Override
	public void delete(Organization organization) {
		del(organization.getId());
	}

	private void del(String id) {
		// 1.根据ID查找
		Torganization t = organizationDao.get(Torganization.class, id);
		if (t != null) {
			Set<Torganization> organizations = t.getChildrens();
			// 2.如果当前存在子节点则行删除子节点
			if (organizations != null && !organizations.isEmpty()) {
				for (Torganization o : organizations) {
					del(o.getId());
				}
			}
			// 3.删除当前节点
			organizationDao.delete(t);
		}

	}

	/**
	 * 数据模型转换
	 * 
	 * @return
	 */
	private List<Organization> changDaoModelToActionModel(
			List<Torganization> organizations) {
		List<Organization> retOrganizations = new ArrayList<Organization>();
		if (organizations != null && organizations.size() > 0) {
			for (Torganization t : organizations) {
				Organization o = new Organization();
				BeanUtils.copyProperties(t, o);
				// 如果存在父机构，则设置相关属性字段值
				if (t.getParent() != null) {
					o.setParentId(t.getParent().getId());
					o.setParentName(t.getParent().getName());
				}
				if (this.countChildren(t.getId()) > 0) {
					o.setState("closed");
				}
				if (t.getIconcls() == null) {
					o.setIconcls("");
				} else {
					o.setIconcls(t.getIconcls());
				}
				retOrganizations.add(o);
			}

		}
		return retOrganizations;

	}

	/**
	 * 统计有多少子节点
	 */
	private Long countChildren(String pid) {
		return organizationDao.count(
				"select count(*) from Torganization t where t.parent.id = ?",
				new Object[] { pid });
	}

	/**
	 * 判断是否是将当前节点修改到当前节点的子节点
	 * 
	 * @param t
	 * @param pt
	 * @return
	 */
	private boolean isDown(Torganization t, Torganization pt) {
		if (pt.getParent() != null) {
			if (pt.getParent().getId().equals(t.getId())) {
				return true;
			} else {
				return isDown(t, pt.getParent());
			}
		}
		return false;
	}

	@Override
	public Organization getOrgNameById(String id) {
		Torganization organization=new Torganization();
		String hql="from Torganization t where t.id=?";
		List<Object> params=new ArrayList<Object>();
		params.add(id);
		organization=(Torganization) organizationDao.find(hql,params).get(0);
		Organization o=new Organization();
		BeanUtils.copyProperties(organization, o);
		return o;
	}
	
	public List<ComboboxJson> getAllOrganization(){
		List<ComboboxJson> retLists=new ArrayList<ComboboxJson>();
		String sql="select * from tb_organizations  where prentId is not null order by sequence asc";
		List<Torganization> orgs=organizationDao.findAllBySQL(Torganization.class, sql);
		if(orgs!=null&&orgs.size()>0){
			for(Torganization t:orgs){
				ComboboxJson json=new ComboboxJson();
				json.setText(t.getName());
				json.setValue(t.getId());
				retLists.add(json);
			}
		}
		if(ValidateUtil.isValidListObject(retLists)){
			return retLists;
		}
		return null;
	}

	@Override
	public Torganization getSingleById(String id) throws Exception {
		return organizationDao.get(Torganization.class, id);
	}

}

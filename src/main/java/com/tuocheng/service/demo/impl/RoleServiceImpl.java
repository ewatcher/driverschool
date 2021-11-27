package com.tuocheng.service.demo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.Tauth;
import com.tuocheng.model.demo.Torganization;
import com.tuocheng.model.demo.Trole;
import com.tuocheng.model.demo.Troletauth;
import com.tuocheng.model.demo.TuserRole;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.Role;
import com.tuocheng.service.demo.RoleServiceI;
import com.tuocheng.util.base.ValidateUtil;

@Service("demoRoleService")
public class RoleServiceImpl implements RoleServiceI {

	private BaseDaoI<Trole> roleDao;
	private BaseDaoI<Tauth> authDao;
	private BaseDaoI<Troletauth> roleauthDao;
	private BaseDaoI<TuserRole> userroleDao;
	private BaseDaoI<Torganization> organizationDao;

	public BaseDaoI<Tauth> getAuthDao() {
		return authDao;
	}

	@Autowired
	public void setAuthDao(BaseDaoI<Tauth> authDao) {
		this.authDao = authDao;
	}

	public BaseDaoI<Troletauth> getRoleauthDao() {
		return roleauthDao;
	}

	@Autowired
	public void setRoleauthDao(BaseDaoI<Troletauth> roleauthDao) {
		this.roleauthDao = roleauthDao;
	}

	public BaseDaoI<TuserRole> getUserroleDao() {
		return userroleDao;
	}

	@Autowired
	public void setUserroleDao(BaseDaoI<TuserRole> userroleDao) {
		this.userroleDao = userroleDao;
	}

	public BaseDaoI<Trole> getRoleDao() {
		return roleDao;
	}

	@Autowired
	public void setRoleDao(BaseDaoI<Trole> roleDao) {
		this.roleDao = roleDao;
	}

	@Autowired
	public void setOrganizationDao(BaseDaoI<Torganization> organizationDao) {
		this.organizationDao = organizationDao;
	}

	public DataGrid datagrid(Role role) {
		DataGrid j = new DataGrid();
		j.setRows(changeModel(find(role)));
		j.setTotal(total(role));
		return j;
	}

	private List<Role> changeModel(List<Trole> troles) {
		List<Role> roles = new ArrayList<Role>();
		if (troles != null && troles.size() > 0) {
			for (Trole tu : troles) {
				Role entry = new Role();
				BeanUtils.copyProperties(tu, entry);

				Set<Troletauth> troletauths = tu.getTroletauths();
				String authIds = "";
				String authNames = "";
				boolean b = false;
				if (troletauths != null && troletauths.size() > 0) {
					for (Troletauth troletauth : troletauths) {
						if (troletauth.getTauth() != null) {
							if (b) {
								authIds += ",";
								authNames += ",";
							}
							authIds += troletauth.getTauth().getCid();
							authNames += troletauth.getTauth().getCname();
							b = true;
						}
					}
				}
				entry.setAuthIds(authIds);
				entry.setAuthNames(authNames);
				// 根据校区标识显示校区名称
				if (ValidateUtil.isValid(tu.getSchoolArea())) {
					Torganization org = organizationDao.get(
							Torganization.class, tu.getSchoolArea());
					entry.setSchoolArea(org.getId());
					entry.setSchoolAreaName(org.getName());
				}

				roles.add(entry);
			}
		}
		return roles;
	}

	private List<Trole> find(Role role) {
		String hql = "from Trole t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(role, hql, values);

		if (role.getSort() != null && role.getOrder() != null) {
			hql += " order by " + role.getSort() + " " + role.getOrder();
		}
		return roleDao.find(hql, values, role.getPage(), role.getRows());
	}

	private Long total(Role role) {
		String hql = "select count(*) from Trole t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(role, hql, values);
		return roleDao.count(hql, values);
	}

	private String addWhere(Role role, String hql, List<Object> values) {
		if (ValidateUtil.isValid(role.getSchoolArea())) {
			hql += " and t.schoolArea=? ";
			values.add(role.getSchoolArea());
		}
		return hql;
	}

	public Role add(Role role) {
		Trole r = new Trole();
		BeanUtils.copyProperties(role, r);
		r.setCid(UUID.randomUUID().toString());
		r.setCreateDate(new Date());
		roleDao.save(r);

		this.saveRoleAuth(role, r);
		// 3.将修改后的数据传给前台
		BeanUtils.copyProperties(r, role);
		return role;
	}

	/**
	 * 保存Trole和Tauth的关系
	 * 
	 * @param role
	 * @param r
	 */
	private void saveRoleAuth(Role role, Trole r) {
		roleauthDao.executeHql("delete Troletauth t where t.trole = ?",
				new Object[] { r });
		if (role.getAuthIds() != null) {
			for (String id : role.getAuthIds().split(",")) {
				Troletauth troletauth = new Troletauth();
				troletauth.setCid(UUID.randomUUID().toString());
				troletauth.setTauth(authDao.get(Tauth.class, id.trim()));
				troletauth.setTrole(r);
				roleauthDao.save(troletauth);
			}
		}
	}

	public Role edit(Role role) {
		Trole r = roleDao.get(Trole.class, role.getCid());
		BeanUtils.copyProperties(role, r, new String[] { "cid" });

		this.saveRoleAuth(role, r);
		// 3.将修改后的数据传给前台
		BeanUtils.copyProperties(r, role);
		return role;
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				Trole r = roleDao.get(Trole.class, id.trim());
				if (r != null) {
					roleauthDao.executeHql(
							"delete Troletauth t where t.trole = ?",
							new Object[] { r });
					userroleDao.executeHql(
							"delete TuserRole t where t.trole = ?",
							new Object[] { r });
					roleDao.delete(r);
				}
			}
		}
	}

	public List<Role> combobox(String schoolArea) {
		List<Role> rl = new ArrayList<Role>();
		List<Trole> roles=new ArrayList<Trole>();
		if(!ValidateUtil.isValid(schoolArea)){
			String hql="from Trole ";
			roles=roleDao.find(hql);
		}else{
			String hql="from Trole t where t.schoolArea=?";
			List<Object> param=new ArrayList<Object>();
			param.add(schoolArea);
			roles=roleDao.find(hql, param);
		}
		if (roles != null && roles.size() > 0) {
			for (Trole t : roles) {
				Role r = new Role();
				r.setCid(t.getCid());
				r.setCname(t.getCname());
				rl.add(r);
			}
		}
		return rl;
	}

}

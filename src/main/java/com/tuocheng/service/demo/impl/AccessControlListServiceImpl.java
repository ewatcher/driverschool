package com.tuocheng.service.demo.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Service;

import com.tuocheng.comparator.MenuComparator;
import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.TaccessControlList;
import com.tuocheng.model.demo.Tmenu;
import com.tuocheng.model.demo.Torganization;
import com.tuocheng.model.demo.Tstudent;
import com.tuocheng.model.demo.TuserRole;
import com.tuocheng.pageModel.base.ComboboxJson;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.base.TreeNode;
import com.tuocheng.pageModel.demo.AccessControlList;
import com.tuocheng.service.demo.AccessControlListServiceI;
import com.tuocheng.util.base.ListUtil;
import com.tuocheng.util.base.Permission;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 
 * @author MEI702
 *
 */
@Service("aclService")
public class AccessControlListServiceImpl implements AccessControlListServiceI {
	private BaseDaoI<TaccessControlList> aclDao;
	private BaseDaoI<Tmenu> menuDao;
	private BaseDaoI<TuserRole> userRoleDao;
	private BaseDaoI<Torganization> organizationDao;

	@Autowired
	public void setOrganizationDao(BaseDaoI<Torganization> organizationDao) {
		this.organizationDao = organizationDao;
	}

	@Autowired
	public void setUserRoleDao(BaseDaoI<TuserRole> userRoleDao) {
		this.userRoleDao = userRoleDao;
	}

	@Autowired
	public void setMenuDao(BaseDaoI<Tmenu> menuDao) {
		this.menuDao = menuDao;
	}

	@Autowired
	public void setAclDao(BaseDaoI<TaccessControlList> aclDao) {
		this.aclDao = aclDao;
	}

	@Override
	public TaccessControlList getACL(String principalType, String principalSn, String resourceSn) throws Exception {
		String sql = "select * from tb_accessControlLists where principalType=? and principalSn=? and resourceSn=?";
		TaccessControlList acl = aclDao.getSingleBySQL(TaccessControlList.class, sql, principalType, principalSn,
				resourceSn);
		if (null != acl) {
			return acl;
		}
		return null;
	}

	@Override
	public boolean addOrUpdatePermission(String principalType, String principalSn, String resourceSn, int permission,
			boolean yes) throws Exception {
		// 1. 根据主体类型，主体标识，资源标识查找ACL实例
		TaccessControlList acl = this.getACL(principalType, principalSn, resourceSn);
		// 2. 判断是否存在ACL实例
		// 2.1 存在ACL实例，则更新ACL实例的授权
		if (acl != null) {
			acl.setPermission(permission, yes);
			aclDao.update(acl);
			return true;
		}
		// 2.2 如果不存在ACL实例，则报建ACL实例，并设置ACL实例的授权
		acl = new TaccessControlList();
		acl.setId(UUID.randomUUID().toString());
		acl.setPrincipalType(principalType);
		acl.setPrincipalSn(principalSn);
		acl.setResourceSn(resourceSn);
		acl.setPermission(permission, yes);
		aclDao.save(acl);
		// 3.返回结果集
		return true;
	}


	@Override
	public boolean addOrUpdateUserExtends(String userId, String resourceSn, boolean yes) throws Exception {
		if (!ValidateUtil.isValid(userId) || !ValidateUtil.isValid(resourceSn)) {
			return false;
		}
		// 1. 主体标识，资源标识查找ACL实例
		TaccessControlList acl = this.getACL(TaccessControlList.TYPE_USER, userId, resourceSn);

		// 2. 判断是否存在ACL实例
		// 2.1 存在ACL实例，则更新ACL实例的继承性
		if (acl != null) {
			acl.setExtends(yes);
			aclDao.update(acl);
			return true;
		}

		// 2.2 如果不存在ACL实例，则报建ACL实例，并设置ACL实例的授权
		acl = new TaccessControlList();
		acl.setId(UUID.randomUUID().toString());
		acl.setPrincipalType(TaccessControlList.TYPE_USER);
		acl.setPrincipalSn(userId);
		acl.setResourceSn(resourceSn);
		acl.setExtends(yes);
		aclDao.save(acl);

		// 3.返回操作结果
		return true;
	}

	@Override
	public boolean deletePermission(String principalType, String principalSn, String resourceSn) throws Exception {
		// 1.参数验证
		if (!ValidateUtil.isValid(principalType) || !ValidateUtil.isValid(principalSn)
				|| !ValidateUtil.isValid(resourceSn)) {
			return false;
		}
		// 2.根据参数查找实体
		TaccessControlList acl = this.getACL(principalType, principalSn, resourceSn);
		if (null != acl) {
			aclDao.delete(acl);
			return true;
		}
		// 3.返回结果集
		return false;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Tmenu> getAllMenus(String userId) throws Exception {
		// 1.查找用户拥有的角色，并按优先级从低到高排序
		// 根据用户标识，查找出与该用户有关的角色标识
		String mysql = "select * from  tb_user_role ur,tb_role r where ur.cuserId=? and ur.croleId=r.cid order by ur.priority desc";
		List<TuserRole> roles = userRoleDao.findBySQL(TuserRole.class, mysql, userId);
		List<String> roleIds = new ArrayList<String>(0);
		if (ValidateUtil.isValidListObject(roles)) {
			for (TuserRole ur : roles) {
				roleIds.add(ur.getTrole().getCid());
			}
		}
		Map<String, TaccessControlList> aclsMap = new HashedMap();
		// 2.根据角色标识，查找出该角色标识的所有权限，
		List<TaccessControlList> roleAcls = null;
		if (ValidateUtil.isValidListObject(roleIds)) {
			for (String roleId : roleIds) {
				roleAcls = this.getRoleACLs(roleId);
				if (ValidateUtil.isValidListObject(roleAcls)) {
					for (TaccessControlList acl : roleAcls) {
						aclsMap.put(acl.getResourceSn(), acl);
					}
				}
			}
		}
		// 3.根据用户标识，查找有效的用户授权
		List<TaccessControlList> userAcls = this.getUserACLs(userId);
		if (ValidateUtil.isValidListObject(userAcls)) {
			for (TaccessControlList acl : userAcls) {
				aclsMap.put(acl.getResourceSn(), acl);
			}
		}
		// 4.去除掉那些没有读取权限的授权记录
		Set entries = aclsMap.entrySet();
		Map.Entry<String, TaccessControlList> entry = null;
		TaccessControlList aclTemp = null;
		for (Iterator iterator = entries.iterator(); iterator.hasNext();) {
			entry = (Map.Entry<String, TaccessControlList>) iterator.next();
			aclTemp = entry.getValue();
			if (aclTemp.getPermission(Permission.READ) != TaccessControlList.ACL_YES) {
				iterator.remove();
			}
		}
		// 5.返回结果集
		if (aclsMap.isEmpty()) {
			return null;
		}
		// 构造select * from tb_menu where cid in(:ids)
		List<Tmenu> menus = menuDao.getAllByWhereIn(Tmenu.class, "tb_menu", "cid", aclsMap.keySet());
		if (ValidateUtil.isValidListObject(menus)) {
			return menus;
		}
		return null;
	}

	@Override
	public boolean haspermission(String userId, String resourceSn, int permission) throws Exception {
		if (!ValidateUtil.isValid(userId) || !ValidateUtil.isValid(resourceSn) || !ValidateUtil.isValid(permission)) {
			return false;
		}
		// 1.根据用户标识和资源标识获取ACL实例
		TaccessControlList acl = this.getACL(TaccessControlList.TYPE_USER, userId, resourceSn);
		// 2.判断是否存在ACL实例
		// 2.1 存在ACL实例，
		if (acl != null) {
			// 2.1.1则判断授权情况, 有确定授权，返回授权情况
			int yesOrNo = acl.getPermission(permission);
			if (yesOrNo != TaccessControlList.ACL_NEUTRAL) {
				return yesOrNo == TaccessControlList.ACL_YES ? true : false;
			}
		}
		// 2.1.2不确定授权（继承），则查询用户拥有的角色列表
		// 2.2不存在ACL实例，则查找用户拥有的角色列表
		// 3.查找用户拥有的角色列表,按照优先级从高到低排序
		String mysql = "select * from  tb_user_role ur,tb_role r where ur.cuserId=? and ur.croleId=r.cid order by ur.priority desc";
		List<TuserRole> roles = userRoleDao.findBySQL(TuserRole.class, mysql, userId);
		List<String> roleIds = new ArrayList<String>(0);
		if (ValidateUtil.isValidListObject(roles)) {
			for (TuserRole ur : roles) {
				roleIds.add(ur.getTrole().getCid());
			}
		}

		// 4. 根据角色标识和资源标识查找ACL实例
		for (String rid : roleIds) {
			acl = this.getACL(TaccessControlList.TYPE_ROLE, rid, resourceSn);
			// 4.1 存在授权，直接返回授权
			if (acl != null) {
				return acl.getPermission(permission) == TaccessControlList.ACL_YES ? true : false;
			}
		}
		// 4.1不存在ACL实例，没有授权，则继续查找下一个角色的授权
		return false;
	}

	private boolean roleHaspermission(String roleId, String resourceSn, int permission) {
		if (!ValidateUtil.isValid(roleId) || !ValidateUtil.isValid(resourceSn) || !ValidateUtil.isValid(permission)) {
			return false;
		}

		try {
			TaccessControlList acl = this.getACL(TaccessControlList.TYPE_ROLE, roleId, resourceSn);
			// 4.1 存在授权，直接返回授权
			if (acl != null) {
				return acl.getPermission(permission) == TaccessControlList.ACL_YES ? true : false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<TaccessControlList> searchAclRecord(String principalType, String principalSn) throws Exception {
		if (!ValidateUtil.isValid(principalType) || !ValidateUtil.isValid(principalSn)) {
			return null;
		}
		String sql = "select id, resourceSn,aclState&1,aclState&2,aclState&4,aclState&8,aclState,aclTriState ,operator,principalType,principalSn,resourceSn,schoolArea "
				+ " from tb_accessControlLists where principalType =? and principalSn = ?";

		List<TaccessControlList> retVals = aclDao.findAllBySQL(TaccessControlList.class, sql, principalType,
				principalSn);
		if (ValidateUtil.isValidListObject(retVals)) {
			return retVals;
		}
		return null;
	}

	@Override
	public List<TaccessControlList> getRoleACLs(String roleId) throws Exception {
		if (!ValidateUtil.isValid(roleId)) {
			return null;
		}
		String sql = "select * from tb_accessControlLists  where principalType=? and principalSn=? ";
		List<TaccessControlList> rets = aclDao.findBySQL(TaccessControlList.class, sql, TaccessControlList.TYPE_ROLE,
				roleId);
		if (ValidateUtil.isValidListObject(rets)) {
			return rets;
		}
		return null;
	}

	@Override
	public List<TaccessControlList> getUserACLs(String userId) throws Exception {
		if (!ValidateUtil.isValid(userId)) {
			return null;
		}
		String sql = "select * from tb_accessControlLists  where principalType=? "
				+ "and principalSn=? and aclTriState=0";
		List<TaccessControlList> rets = aclDao.findAllBySQL(TaccessControlList.class, sql, TaccessControlList.TYPE_USER,
				userId);
		if (ValidateUtil.isValidListObject(rets)) {
			return rets;
		}
		return null;
	}

	@Override
	public boolean haspermissionByResouceSn(String principalType,String principalSn, String resourceSn, int permission) throws Exception {
		if (!ValidateUtil.isValid(principalSn) || !ValidateUtil.isValid(resourceSn) || !ValidateUtil.isValid(permission)) {
			return false;
		}
		String sql = "select * from tb_menu  where cid=? ";
		Tmenu menu = menuDao.getSingleBySQL(Tmenu.class, sql, resourceSn);
		if (null != menu) {
			if("USER".equals(principalType.trim())){
				return this.haspermission(principalSn, menu.getCid(), permission);
			}else if("ROLE".equals(principalType.trim())){
				return this.roleHaspermission(principalSn, menu.getCid(), permission);
			}
			
		}
		return false;
	}

	public List<ComboboxJson> getMainMenuByUserId(String userId) throws Exception {
		// 1.参数校验
		if (!ValidateUtil.isValid(userId)) {
			return null;
		}
		// 2.获取主数据，数据模型转换
		List<ComboboxJson> retList = new ArrayList<ComboboxJson>();
		String sql = "select * from tb_menu where mainMenuflag=?";
		List<Tmenu> mainMenus = menuDao.findBySQL(Tmenu.class, sql, 1);
		if (ValidateUtil.isValidListObject(mainMenus)) {
			ComboboxJson json = null;
			for (Tmenu entry : mainMenus) {
				if (this.haspermission(userId, entry.getCid(), Permission.READ)) {
					json = new ComboboxJson();
					json.setValue(entry.getCid());
					json.setText(entry.getCname());
					retList.add(json);
				}
			}

		}

		// 3.返回结果集
		if (ValidateUtil.isValidListObject(retList)) {
			return retList;
		}
		return null;
	}

	public List<TreeNode> getTreeNodeByMainMenuId(String mainMenuId, String userId) throws Exception {
		// 1.参数校验
		if (!ValidateUtil.isValid(mainMenuId) || !ValidateUtil.isValid(userId)) {
			return null;
		}
		// 根据用户标识，主菜单标识获取该主菜单的所有子菜单
		List<TreeNode> retTree = new ArrayList<TreeNode>();
		String sql = "select * from tb_menu where cpid=? order by cseq asc";
		List<Tmenu> childen = menuDao.findAllBySQL(Tmenu.class, sql, mainMenuId);
		if (ValidateUtil.isValidListObject(childen)) {
			TreeNode node = null;
			for (Tmenu entry : childen) {
				if (this.haspermission(userId, entry.getCid(), Permission.READ)) {
					node = new TreeNode();
					node.setId(entry.getCid());
					node.setText(entry.getCname());
					Map<String, Object> attributes = new HashMap<String, Object>();
					attributes.put("url", entry.getCurl());
					node.setAttributes(attributes);
					if (entry.getCiconcls() != null) {
						node.setIconCls(entry.getCiconcls());
					} else {
						node.setIconCls("");
					}
					retTree.add(node);
				}
			}
		}
		// 返回结果集
		if (ValidateUtil.isValidListObject(retTree)) {
			return retTree;
		}
		return null;
	}

	@Override
	public DataGrid dataGrid(AccessControlList acl) throws Exception {
		DataGrid dataGrid = new DataGrid();
		// 设置返回前台的数据
		dataGrid.setRows(this.changedModel(this.find(acl)));
		// 设置总记录数
		dataGrid.setTotal(this.total(acl));
		return dataGrid;
	}

	/**
	 * 从数据库存中查找出该实体信息并将此实体类信息转换成action专用的实体类信息，即模型转换
	 * 
	 * @param students
	 * @return
	 */
	private List<AccessControlList> changedModel(List<TaccessControlList> collectFeeList) {
		List<AccessControlList> retVals = new ArrayList<AccessControlList>();
		if (collectFeeList != null && collectFeeList.size() > 0) {
			// TODO(交费人员类型，1：学员，只做学员，教练，职员后期处理)
			Tstudent student = null;
			for (TaccessControlList temp : collectFeeList) {
				AccessControlList entry = new AccessControlList();
				entry.setSchoolAreaName(organizationDao.get(Torganization.class, temp.getSchoolArea()).getName());

				BeanUtils.copyProperties(temp, entry);
				retVals.add(entry);
			}
		}
		return retVals;
	}

	/**
	 * 根据action专用实体类的信息查询所有dao专用的实体类信息
	 * 
	 * @param student
	 * @return
	 */
	private List<TaccessControlList> find(AccessControlList acl) {
		String hql = "from TaccessControlList t where 1=1";
		List<Object> paramList = new ArrayList<Object>();
		hql = this.addWhere(acl, hql, paramList);
		if (acl.getSort() != null && acl.getOrder() != null) {
			hql += " order by " + acl.getSort() + " " + acl.getOrder();
		}
		return aclDao.find(hql, paramList, acl.getPage(), acl.getRows());
	}

	/**
	 * 统计符合当前查询条件的总记录数
	 * 
	 * @param student
	 * @return
	 */
	private Long total(AccessControlList acl) {
		// 拼接查询条件
		String hql = "select count(*) from TaccessControlList t where 1=1 ";
		List<Object> paramsList = new ArrayList<Object>();
		hql = this.addWhere(acl, hql, paramsList);
		return aclDao.count(hql, paramsList);
	}

	/**
	 * 添加查询约束条件
	 * 
	 * @param collectFee
	 * @param hql
	 * @param params
	 * @return
	 */
	private String addWhere(AccessControlList collectFee, String hql, List<Object> params) {

		return hql;
	}
}

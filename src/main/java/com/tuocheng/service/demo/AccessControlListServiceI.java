package com.tuocheng.service.demo;

import java.util.List;

import com.tuocheng.model.demo.TaccessControlList;
import com.tuocheng.model.demo.Tmenu;
import com.tuocheng.pageModel.base.ComboboxJson;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.base.TreeNode;
import com.tuocheng.pageModel.demo.AccessControlList;
import com.tuocheng.pageModel.demo.CollectFee;

/**
 * 访问权限控制列表接口
 * 
 * @author MEI702
 *
 */
public interface AccessControlListServiceI {
	/**
	 * 根据主体类型，主体标识，资源标识查找ACL
	 * 
	 * @param principalType：主体类型
	 * @param principalSn：主体标识
	 * @param resourceSn：资源标识
	 * @return
	 * @throws Exception
	 * 
	 *             2017年7月21日 TaccessControlList
	 */
	public TaccessControlList getACL(String principalType, String principalSn, String resourceSn) throws Exception;

	/**
	 * 
	 * @param principalType主体类型
	 * @param principalSn主体标识
	 * @param resourceSn资源标识
	 * @param permissionCRUD
	 * @param yes是否继承
	 * @return
	 * @throws Exception
	 * 
	 *             2017年7月21日 boolean
	 */
	public boolean addOrUpdatePermission(String principalType, String principalSn, String resourceSn, int permission,
			boolean yes) throws Exception;

	/**
	 * 添加或者更新用户继承的特性
	 * 
	 * @param userId--用户ID
	 * @param resourceSn--资源标识
	 * @param yes
	 *            true表示继承， false表示不继承
	 * @return
	 * @throws Exception
	 * 
	 *             2017年7月21日 boolean
	 */
	public boolean addOrUpdateUserExtends(String userId, String resourceSn, boolean yes) throws Exception;

	/**
	 * 删除授权成功
	 * 
	 * @param principalType
	 * @param principalSn
	 * @param resourceSn
	 * @return
	 * @throws Exception
	 * 
	 *             2017年7月21日 boolean
	 */
	public boolean deletePermission(String principalType, String principalSn, String resourceSn) throws Exception;

	/**
	 * 根据用户ID，查找某个用户拥有读取权限的模块列表(用于登录，形成导航菜单的时候)
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 * 
	 *             2017年7月21日 List
	 */
	public List<Tmenu> getAllMenus(String userId) throws Exception;

	/**
	 * 即时认证：判断用户是对某个模块的某种操作授权（允许/不允许）
	 * 
	 * @param userId(用户标识)
	 * @param resourceSn(资源标识)
	 * @param permission(CRUD)
	 * @return
	 * @throws Exception
	 * 
	 *             2017年7月21日 boolean
	 */
	public boolean haspermission(String userId, String resourceSn, int permission) throws Exception;

	/**
	 * 根据主体类型和主体标识查找ACL记录
	 * 
	 * @param principalType
	 * @param principalSn
	 * @return
	 * @throws Exception
	 * 
	 *             2017年7月21日 List
	 */
	public List<TaccessControlList> searchAclRecord(String principalType, String principalSn) throws Exception;

	/**
	 * 根据角色标识,查找角色的授权列表，返回的列表元素是ACL对象
	 * 
	 * @param roleId
	 * @return
	 * @throws Exception
	 * 
	 *             2017年7月21日 List<TaccessControlList>
	 */
	public List<TaccessControlList> getRoleACLs(String roleId) throws Exception;

	/**
	 * 根据用户标识，查找直接授于用户的权限（注意：如果直接授于用户 的权限是继承，那么不应该包含在此列表中），
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 * 
	 *             2017年7月21日 List<TaccessControlList>
	 */
	public List<TaccessControlList> getUserACLs(String userId) throws Exception;

	/**
	 * 判断用户是对某个模块的某种操作授权（允许/不允许)
	 * 
	 * @param principalType
	 * @param userId
	 * @param resourceSn
	 * @param permission
	 * @return
	 * @throws Exception
	 * 
	 *             2017年8月2日 boolean
	 */
	public boolean haspermissionByResouceSn(String principalType, String userId, String resourceSn, int permission)
			throws Exception;

	/**
	 * 查找所有权限列表
	 * 
	 * @param acl
	 * @return
	 * @throws Exception
	 * 
	 *             2017年7月21日 DataGrid
	 */
	public DataGrid dataGrid(AccessControlList acl) throws Exception;

	/**
	 * 根据用户标识，查找用记主菜单列表
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 * 
	 *             2017年8月1日 List<ComboboxJson>
	 */
	public List<ComboboxJson> getMainMenuByUserId(String userId) throws Exception;

	/**
	 * 根据用户标识，根据该用户拥有主菜单获取子权限树
	 * 
	 * @param mainMenuId
	 * @param userId
	 * @return
	 * @throws Exception
	 * 
	 *             2017年8月1日 List<TreeNode>
	 */
	public List<TreeNode> getTreeNodeByMainMenuId(String mainMenuId, String userId) throws Exception;
}

package com.tuocheng.service.demo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.TaccessControlList;
import com.tuocheng.model.demo.Tauth;
import com.tuocheng.model.demo.Tbug;
import com.tuocheng.model.demo.Tmenu;
import com.tuocheng.model.demo.Tonline;
import com.tuocheng.model.demo.Torganization;
import com.tuocheng.model.demo.Trole;
import com.tuocheng.model.demo.Troletauth;
import com.tuocheng.model.demo.Tuser;
import com.tuocheng.model.demo.TuserRole;
import com.tuocheng.service.demo.RepairServiceI;
import com.tuocheng.util.base.AuthInitUtils;
import com.tuocheng.util.base.Encrypt;
import com.tuocheng.util.base.MenuInitUtils;
import com.tuocheng.util.base.ValidateUtil;

@Service("demoRepairService")
public class RepairServiceImpl implements RepairServiceI {

	private BaseDaoI<Tbug> bugDao;
	private BaseDaoI<Tuser> userDao;
	private BaseDaoI<Tmenu> menuDao;
	private BaseDaoI<Tonline> onlineDao;
	private BaseDaoI<Tauth> authDao;
	private BaseDaoI<Trole> roleDao;
	private BaseDaoI<TuserRole> userroleDao;
	private BaseDaoI<Troletauth> roleauthDao;
	private BaseDaoI<Torganization> organizationDao;
	private BaseDaoI<TaccessControlList> aclDao;

	@Autowired
	public void setAclDao(BaseDaoI<TaccessControlList> aclDao) {
		this.aclDao = aclDao;
	}

	@Autowired
	public void setBugDao(BaseDaoI<Tbug> bugDao) {
		this.bugDao = bugDao;
	}

	@Autowired
	public void setUserDao(BaseDaoI<Tuser> userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setMenuDao(BaseDaoI<Tmenu> menuDao) {
		this.menuDao = menuDao;
	}

	@Autowired
	public void setOnlineDao(BaseDaoI<Tonline> onlineDao) {
		this.onlineDao = onlineDao;
	}

	@Autowired
	public void setAuthDao(BaseDaoI<Tauth> authDao) {
		this.authDao = authDao;
	}

	@Autowired
	public void setRoleDao(BaseDaoI<Trole> roleDao) {
		this.roleDao = roleDao;
	}

	@Autowired
	public void setUserroleDao(BaseDaoI<TuserRole> userroleDao) {
		this.userroleDao = userroleDao;
	}

	@Autowired
	public void setRoleauthDao(BaseDaoI<Troletauth> roleauthDao) {
		this.roleauthDao = roleauthDao;
	}

	@Autowired
	public void setOrganizationDao(BaseDaoI<Torganization> organizationDao) {
		this.organizationDao = organizationDao;
	}

	synchronized public void deleteAndRepair() {
		bugDao.executeHql("delete Tbug");
		onlineDao.executeHql("delete Tonline");
		menuDao.executeHql("update Tmenu t set t.tmenu = null");
		menuDao.executeHql("delete Tmenu");
		roleauthDao.executeHql("delete Troletauth");
		userroleDao.executeHql("delete TuserRoles");
		authDao.executeHql("update Tauth t set t.tauth = null");
		authDao.executeHql("delete Tauth");
		roleDao.executeHql("delete Trole");
		userDao.executeHql("delete Tuser");
		repair();
	}

	synchronized public void repair() {
		repairMenu();// 修复菜单
		repairAuth();// 修复权限
		repairRole();// 修复角色
		repairUser();// 修复用户
		repairRoleAuth();// 修复角色和权限的关系
		repairUserRole();// 修复用户和角色的关系
	}

	private void repairUserRole() {
		userroleDao.executeHql("delete TuserRole t where t.tuser.cid in ( '0','admin001',\'"+ AuthInitUtils.SUPERID_ADMIN +"\')");

		TuserRole userrole = new TuserRole();
		userrole.setCid(UUID.randomUUID().toString());
		userrole.setTrole(roleDao.get(Trole.class, "0"));
		userrole.setTuser(userDao.get(Tuser.class, "0"));
		userroleDao.saveOrUpdate(userrole);

		TuserRole userrole1 = new TuserRole();
		userrole1.setCid(UUID.randomUUID().toString());
		userrole1.setTrole(roleDao.get(Trole.class, "0"));
		userrole1.setTuser(userDao.get(Tuser.class, "admin001"));
		userroleDao.saveOrUpdate(userrole1);
	}

	private void repairRoleAuth() {
		roleauthDao.executeHql("delete Troletauth t where t.trole.cid = '0'");

		Trole role = roleDao.get(Trole.class, "0");

		List<Tauth> auths = authDao.find("from Tauth");
		if (auths != null && auths.size() > 0) {
			for (Tauth auth : auths) {
				Troletauth roleauth = new Troletauth();
				roleauth.setCid(UUID.randomUUID().toString());
				roleauth.setTrole(role);
				roleauth.setTauth(auth);
				roleauthDao.saveOrUpdate(roleauth);
			}
		}
	}

	// 初始化系统角色
	private void repairRole() {
		Trole admin = new Trole();
		admin.setCid("0");
		admin.setCname("超级管理员");
		admin.setCdesc("拥有系统所有权限");
		admin.setCreateDate(new Date());
		admin.setOperator("系统");
		roleDao.saveOrUpdate(admin);

	}

	// 初始化系统授权
	private void repairAuth() {
		authDao.executeHql("update Tauth a set a.tauth = null");

		Tauth sshe = new Tauth();
		sshe.setCid("0");
		sshe.setTauth(null);
		sshe.setCname("驾校一体化管理系统");
		sshe.setCurl("");
		sshe.setCseq(BigDecimal.valueOf(1));
		sshe.setCdesc("拓程科技软件研发部");
		authDao.saveOrUpdate(sshe);
		// 二级权限初始化
		AuthInitUtils.initMainAuth(sshe, authDao);

	}

	/**
	 * 初始化系统菜单
	 */
	private void repairMenu() {
		//menuDao.executeHql("update Tmenu m set m.tmenu = null");
		// =============菜单根节点=== 1级菜单 =============
		Tmenu root = new Tmenu();
		root.setCid("0");
		root.setCname("驾校一体化管理系统");
		root.setCseq(BigDecimal.valueOf(1));
		root.setCurl("");
		root.setTmenu(null);
		root.setCiconcls("icon-man");
		menuDao.saveOrUpdate(root);
		// ======== 2级菜单 =======
		MenuInitUtils.initMainMemu(root, menuDao);

		// 初始化机构管理
		Torganization mainOrganization = new Torganization();
		mainOrganization.setId("4e5e35c0-2f4b-42fe-b04b-df357ff74e7c");
		mainOrganization.setName("互联+驾培系统");
		mainOrganization.setIconcls("icon-filter");
		BigDecimal B1 = new BigDecimal("1");
		mainOrganization.setSequence(B1);
		organizationDao.saveOrUpdate(mainOrganization);

		Torganization childOne = new Torganization();
		childOne.setId("b78ffef2-7c54-40fe-be4b-1910a87c8bbs");
		childOne.setName("百色交通技校");
		childOne.setIconcls("icon-man");
		BigDecimal c1 = new BigDecimal(1);
		childOne.setSequence(c1);
		childOne.setParent(mainOrganization);
		organizationDao.saveOrUpdate(childOne);

		Torganization childTwo = new Torganization();
		childTwo.setId("c0510169-8edc-470e-b038-c299c7735bty");
		childTwo.setName("田阳威龙驾校");
		childTwo.setIconcls("icon-man");
		BigDecimal c2 = new BigDecimal(2);
		childTwo.setSequence(c2);
		childTwo.setParent(mainOrganization);
		organizationDao.saveOrUpdate(childTwo);

		Torganization childThree = new Torganization();
		childThree.setId("7d15f11b-2928-41e8-8406-8b49cf3939td");
		childThree.setName("田东旗胜驾校");
		childThree.setIconcls("icon-man");
		BigDecimal c3 = new BigDecimal(3);
		childThree.setSequence(c3);
		childThree.setParent(mainOrganization);
		organizationDao.saveOrUpdate(childThree);

		Torganization childFour = new Torganization();
		childFour.setId("606437a6-574c-4b0b-b79f-1a98bd4a45pg");
		childFour.setName("平果校区");
		childFour.setIconcls("icon-man");
		BigDecimal c4 = new BigDecimal(4);
		childFour.setSequence(c4);
		childFour.setParent(mainOrganization);
		organizationDao.saveOrUpdate(childFour);

		Torganization childFive = new Torganization();
		childFive.setId("357c1d29-c991-4eed-a331-949f3eb4e9db");
		childFive.setName("德保常顺驾校");
		childFive.setIconcls("icon-man");
		BigDecimal c5 = new BigDecimal(5);
		childFive.setSequence(c5);
		childFive.setParent(mainOrganization);
		organizationDao.saveOrUpdate(childFive);

		Torganization childSix = new Torganization();
		childSix.setId("6b698583-3aad-4702-8aa5-6e55b27df8jx");
		childSix.setName("靖西众安驾校");
		childSix.setIconcls("icon-man");
		BigDecimal c6 = new BigDecimal(6);
		childSix.setSequence(c6);
		childSix.setParent(mainOrganization);
		organizationDao.saveOrUpdate(childSix);

		Torganization childSeven = new Torganization();
		childSeven.setId("357c1d29-c991-4eed-a331-949f3eb4e9np");
		childSeven.setName("那坡孟安驾校");
		childSeven.setIconcls("icon-man");
		BigDecimal c7 = new BigDecimal(7);
		childSeven.setSequence(c7);
		childSeven.setParent(mainOrganization);
		organizationDao.saveOrUpdate(childSeven);

		Torganization child8 = new Torganization();
		child8.setId("357c1d29-c991-4eed-a331-949f3eb4e9tl");
		child8.setName("田林永生驾校");
		child8.setIconcls("icon-man");
		BigDecimal c8 = new BigDecimal(8);
		child8.setSequence(c8);
		child8.setParent(mainOrganization);
		organizationDao.saveOrUpdate(child8);

		Torganization child9 = new Torganization();
		child9.setId("357c1d29-c991-4eed-a331-949f3eb4e9xl");
		child9.setName("西林旺程驾校");
		child9.setIconcls("icon-man");
		BigDecimal c9 = new BigDecimal(9);
		child9.setSequence(c9);
		child9.setParent(mainOrganization);
		organizationDao.saveOrUpdate(child9);

		Torganization child10 = new Torganization();
		child10.setId("357c1d29-c991-4eed-a331-949f3eb4e9ll");
		child10.setName("隆林校区");
		child10.setIconcls("icon-man");
		BigDecimal c10 = new BigDecimal(10);
		child10.setSequence(c10);
		child10.setParent(mainOrganization);
		organizationDao.saveOrUpdate(child10);

		Torganization child11 = new Torganization();
		child11.setId("357c1d29-c991-4eed-a331-949f3eb888ly");
		child11.setName("乐业校区");
		child11.setIconcls("icon-man");
		BigDecimal c11 = new BigDecimal(11);
		child11.setSequence(c11);
		child11.setParent(mainOrganization);
		organizationDao.saveOrUpdate(child11);

		Torganization child12 = new Torganization();
		child12.setId("357c1d29-c991-4eed-a881-949f3eb008ly");
		child12.setName("凌云校区");
		child12.setIconcls("icon-man");
		BigDecimal c12 = new BigDecimal(12);
		child12.setSequence(c12);
		child12.setParent(mainOrganization);
		organizationDao.saveOrUpdate(child12);

	}

	/**
	 * 初始化系统用户
	 */
	private void repairUser() {
		/*List<Tuser> t = userDao.find("from Tuser t where t.cname = ? and t.cid != ?", new Object[] { "driverschool", "\'"+AuthInitUtils.SUPERID_ADMIN+"\'" });// cid!='0'并且cname='admin'这是错误的数据，需要修复
		if (t != null && t.size() > 0) {
			for (Tuser u : t) {
				u.setCname(u.getCname() + UUID.randomUUID().toString());
			}
		}*/
		Tuser user=userDao.get(Tuser.class, AuthInitUtils.SUPERID_ADMIN);
		if(null==user){
			Tuser admin = new Tuser();
			admin.setCid(MenuInitUtils.SUPERID_ADMIN);
			admin.setCname("driverschool");
			admin.setCpwd(Encrypt.e("fengaimei328@"));
			admin.setUserType(Tuser.USERTYPE_MONITOR);
			admin.setCcreatedatetime(new Date());
			userDao.saveOrUpdate(admin);
		}

		// 初始化超级管理员权限
		//1.删除超级用户原有的ACL
		String oldSql="delete from tb_accessControlLists where principalSn=?";
		aclDao.executeSQL(oldSql, AuthInitUtils.SUPERID_ADMIN);
		
		// 1.获取所有菜单
		String sql = "select * from tb_menu";
		List<Tmenu> menus = menuDao.getAllBySQL(Tmenu.class, sql);
		TaccessControlList superACL = null;
		if (ValidateUtil.isValidListObject(menus)) {
			for (Tmenu tmenu : menus) {
				superACL = new TaccessControlList();
				superACL.setId(UUID.randomUUID().toString());
				superACL.setPrincipalType("USER");
				superACL.setPrincipalSn(AuthInitUtils.SUPERID_ADMIN);
				superACL.setAclState(15);
				superACL.setAclTriState(0);
				superACL.setExtends(false);
				superACL.setResourceSn(tmenu.getCid());
				aclDao.saveOrUpdate(superACL);
			}
		}

	}

}

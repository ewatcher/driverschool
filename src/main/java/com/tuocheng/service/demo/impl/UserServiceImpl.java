package com.tuocheng.service.demo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.tuocheng.model.demo.Tstudent;
import com.tuocheng.model.demo.Ttrainer;
import com.tuocheng.model.demo.Tuser;
import com.tuocheng.model.demo.TuserRole;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.User;
import com.tuocheng.service.demo.UserServiceI;
import com.tuocheng.util.base.Encrypt;
import com.tuocheng.util.base.MenuInitUtils;
import com.tuocheng.util.base.ValidateUtil;

@Service("demoUserService")
public class UserServiceImpl implements UserServiceI {

	private BaseDaoI<Tuser> userDao;
	private BaseDaoI<Trole> roleDao;
	private BaseDaoI<TuserRole> userroleDao;
	private BaseDaoI<Torganization> organizationDao;
	private BaseDaoI<Ttrainer> trainerDao;
	private BaseDaoI<Tstudent> studentDao;

	@Autowired
	public void setTrainerDao(BaseDaoI<Ttrainer> trainerDao) {
		this.trainerDao = trainerDao;
	}

	@Autowired
	public void setStudentDao(BaseDaoI<Tstudent> studentDao) {
		this.studentDao = studentDao;
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
	public void setUserDao(BaseDaoI<Tuser> userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setOrganizationDao(BaseDaoI<Torganization> organizationDao) {
		this.organizationDao = organizationDao;
	}

	public User login(User user) {
		Tuser entry = userDao.get("from Tuser t where t.cname = ? and t.cpwd = ?",
				new Object[] { user.getCname().trim(), Encrypt.e(user.getCpwd().trim()) });
		if (entry != null) {
			BeanUtils.copyProperties(entry, user, new String[] { "cpwd" });

			String roleIds = "";
			String roleNames = "";
			boolean b1 = false;
			Set<TuserRole> tusertroles = entry.getTusertroles();
			if (tusertroles != null && tusertroles.size() > 0) {
				for (TuserRole tusertrole : tusertroles) {
					Trole trole = tusertrole.getTrole();
					if (b1) {
						roleIds += ",";
						roleNames += ",";
					}
					roleIds += trole.getCid();
					roleNames += trole.getCname();
					b1 = true;
					
				}
			}
			
			user.setRoleIds(roleIds);
			user.setRoleNames(roleNames);

			// 将校区信息注入到 user对象，为以后存入session做准备
			if (ValidateUtil.isValid(entry.getSchoolArea())) {
				Torganization org = organizationDao.get(Torganization.class, entry.getSchoolArea());
				user.setSchoolArea(entry.getSchoolArea());
				user.setSchoolAreaName(org.getName());
			}

			return user;
		}
		return null;
	}

	public User save(User user) {
		// 1.添加用记信息
		Tuser u = new Tuser();
		// 模型数据转换：将action中的model数据拷贝给dao中的model
		BeanUtils.copyProperties(user, u, new String[] { "cpwd" });
		u.setCid(UUID.randomUUID().toString());
		if (user.getCcreatedatetime() == null) {
			u.setCcreatedatetime(new Date());
		}
		if (user.getCmodifydatetime() == null) {
			u.setCmodifydatetime(new Date());
		}
		u.setCpwd(Encrypt.e(user.getCpwd()));
		u.setUserType(Tuser.USERTYPE_EMPLOYEE);
		userDao.save(u);
		// 2.给新添加的用户进行分配角色
		this.saveUserRole(user, u);

		// 3.将修改后的数据传给前台
		BeanUtils.copyProperties(u, user);
		return user;
	}

	/**
	 * 保存用户和角色的关系
	 * 
	 * @param user
	 * @param u
	 */
	private void saveUserRole(User user, Tuser u) {
		userroleDao.executeHql("delete TuserRole t where t.tuser = ?", new Object[] { u });
		if (user.getRoleIds() != null) {
			for (String id : user.getRoleIds().split(",")) {
				TuserRole tusertrole = new TuserRole();
				tusertrole.setCid(UUID.randomUUID().toString());
				tusertrole.setTrole(roleDao.get(Trole.class, id.trim()));
				tusertrole.setTuser(u);
				userroleDao.save(tusertrole);
			}
		}
	}

	public User update(User user) {
		// 1.从数据库存中取出需要修改的用户信息
		Tuser u = userDao.get(Tuser.class, user.getCid());
		// id,密码不用拷贝
		// 2.将页面传递过来的model转换成dao的model
		BeanUtils.copyProperties(user, u, new String[] { "cid", "cpwd" });
		if (user.getCcreatedatetime() == null) {
			u.setCcreatedatetime(new Date());
		}
		if (user.getCmodifydatetime() == null) {
			u.setCmodifydatetime(new Date());
		}
		if (user.getCpwd() != null && !user.getCpwd().trim().equals("")) {
			u.setCpwd(Encrypt.e(user.getCpwd()));
		}
		this.saveUserRole(user, u);

		// 3.将修改后的数据传给前台
		BeanUtils.copyProperties(u, user);
		return user;
	}

	public DataGrid datagrid(User user) {
		DataGrid j = new DataGrid();
		j.setRows(this.changeModel(this.find(user)));
		j.setTotal(this.total(user));
		return j;
	}

	// 从数据库存中查找出用户信息并将此实体类信息转换成action专用的实体类信息，即模型转换
	private List<User> changeModel(List<Tuser> tusers) {
		List<User> users = new ArrayList<User>();
		if (tusers != null && tusers.size() > 0) {
			for (Tuser tu : tusers) {
				User u = new User();
				BeanUtils.copyProperties(tu, u);
				if(MenuInitUtils.SUPERID_ADMIN.equals(tu.getCid())){
					continue;
				}

				Set<TuserRole> tusertroles = tu.getTusertroles();
				String roleIds = "";
				String roleNames = "";
				boolean b = false;
				if (tusertroles != null && tusertroles.size() > 0) {
					for (TuserRole tusertrole : tusertroles) {
						if (tusertrole.getTrole() != null) {
							if (b) {
								roleIds += ",";
								roleNames += ",";
							}
							roleIds += tusertrole.getTrole().getCid();
							roleNames += tusertrole.getTrole().getCname();
							b = true;
						}
					}
				}
				u.setRoleIds(roleIds);
				u.setRoleNames(roleNames);
				if (ValidateUtil.isValid(tu.getSchoolArea())) {
					Torganization org = organizationDao.get(Torganization.class, tu.getSchoolArea());
					u.setSchoolArea(org.getId());
					u.setSchoolAreaName(org.getName());
				}

				users.add(u);
			}
		}
		return users;
	}

	private List<Tuser> find(User user) {
		String hql = "from Tuser t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(user, hql, values);

		if (user.getSort() != null && user.getOrder() != null) {
			hql += " order by " + user.getSort() + " " + user.getOrder();
		}
		return userDao.find(hql, values, user.getPage(), user.getRows());
	}

	private Long total(User user) {
		String hql = "select count(*) from Tuser t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(user, hql, values);
		return userDao.count(hql, values);
	}

	private String addWhere(User user, String hql, List<Object> values) {
		if (user.getCname() != null && !user.getCname().trim().equals("")) {
			hql += " and t.cname like ? ";
			values.add("%%" + user.getCname().trim() + "%%");
		}
		if (user.getCcreatedatetimeStart() != null) {
			hql += " and t.ccreatedatetime>=? ";
			values.add(user.getCcreatedatetimeStart());
		}
		if (user.getCcreatedatetimeEnd() != null) {
			hql += " and t.ccreatedatetime<=? ";
			values.add(user.getCcreatedatetimeEnd());
		}
		if (user.getCmodifydatetimeStart() != null) {
			hql += " and t.cmodifydatetime>=? ";
			values.add(user.getCmodifydatetimeStart());
		}
		if (user.getCmodifydatetimeEnd() != null) {
			hql += " and t.cmodifydatetime<=? ";
			values.add(user.getCmodifydatetimeEnd());
		}
		if (ValidateUtil.isValid(user.getSchoolArea())) {
			hql += " and t.schoolArea=? ";
			values.add(user.getSchoolArea());
		}
		return hql;
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				if (!id.trim().equals("0")) {
					Tuser u = userDao.get(Tuser.class, id.trim());
					if (u != null) {
						// 1。解除该用户捆绑的角色
						String sql = "select * from tb_user_role where CUSERID=?";
						List<TuserRole> urs = userroleDao.findAllBySQL(TuserRole.class, sql, u.getCid());
						if (ValidateUtil.isValidListObject(urs)) {
							TuserRole temp = null;
							for (TuserRole entry : urs) {
								temp = userroleDao.getSingleBySQL(TuserRole.class, sql, entry.getCid());
								if (temp != null) {
									userroleDao.delete(temp);
								}
							}
						}
						// 2.解除用户与教练的捆绑
						if(Tuser.USERTYPE_STUDENT==u.getUserType()){
							// 3.角色用户与学员的捆绑
							String studentSql="select * from tb_student where userId=?";
							Tstudent student=studentDao.getSingleBySQL(Tstudent.class, studentSql, u.getCid());
							if(student!=null){
								student.setUser(null);
								studentDao.update(student);
							}
						}else if(Tuser.USERTYPE_TRAINER==u.getUserType()){
							String trainerSql="select * from tb_trainers where userId=?";
							Ttrainer trainer=trainerDao.getSingleBySQL(Ttrainer.class, trainerSql, u.getCid());
							if(trainer!=null){
								trainer.setUser(null);
								trainerDao.update(trainer);
							}
						}
						
						userDao.delete(u);
					}
				}
			}
		}
	}

	public void roleEdit(User user) {
		if (user.getIds() != null) {
			for (String id : user.getIds().split(",")) {
				Tuser u = userDao.get(Tuser.class, id);
				this.saveUserRole(user, u);
			}
		}
	}

	public void editUserInfo(User user) {
		if (user.getCpwd() != null && !user.getCpwd().trim().equals("")) {
			Tuser t = userDao.get(Tuser.class, user.getCid());
			t.setCpwd(Encrypt.e(user.getCpwd()));
		}
	}

	public List<User> combobox(User user) {
		String q = "";
		if (user != null && user.getQ() != null) {
			q = user.getQ().trim();
		}
		return changeModel(
				userDao.find("from Tuser t where t.cname like ?", new Object[] { "%%" + q.trim() + "%%" }, 1, 10));
	}

	@Override
	public User getSingleById(String id) {
		Tuser user = userDao.get(Tuser.class, id);
		User u = new User();
		BeanUtils.copyProperties(user, u);

		return u;
	}

	@Override
	public User getUserByNamePWD(String name, String PWD) throws Exception {
		String sql = "select * from tb_user  where cname=? and cpwd=?";
		List<Tuser> retVal = userDao.executeSQLQuery(Tuser.class, sql, name, Encrypt.e(PWD.trim()));
		if (retVal != null && retVal.size() > 0) {
			Tuser u = retVal.get(0);
			User retUser = new User();
			BeanUtils.copyProperties(u, retUser);
			System.out.println("--->check user bing success!");
			return retUser;
		}
		return null;
	}

}

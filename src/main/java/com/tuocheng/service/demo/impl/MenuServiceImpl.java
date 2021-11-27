package com.tuocheng.service.demo.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuocheng.comparator.MenuComparator;
import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.Tmenu;
import com.tuocheng.model.demo.Tuser;
import com.tuocheng.pageModel.base.ComboboxJson;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.base.TreeNode;
import com.tuocheng.pageModel.demo.Menu;
import com.tuocheng.pageModel.demo.Trainer;
import com.tuocheng.pageModel.demo.User;
import com.tuocheng.service.demo.MenuServiceI;
import com.tuocheng.util.base.ValidateUtil;

import net.sf.ehcache.util.FindBugsSuppressWarnings;

@Service("demoMenuService")
public class MenuServiceImpl implements MenuServiceI {

	private BaseDaoI<Tmenu> menuDao;

	public BaseDaoI<Tmenu> getMenuDao() {
		return menuDao;
	}

	@Autowired
	public void setMenuDao(BaseDaoI<Tmenu> menuDao) {
		this.menuDao = menuDao;
	}

	public List<TreeNode> tree(Menu menu, Boolean b) {
		List<Object> param = new ArrayList<Object>();
		String hql = "from Tmenu t where t.tmenu is null order by t.cseq";
		if (menu != null && menu.getId() != null
				&& !menu.getId().trim().equals("")) {
			hql = "from Tmenu t where t.tmenu.cid = ? order by t.cseq";
			param.add(menu.getId());
		}
		List<Tmenu> l = menuDao.find(hql, param);
		List<TreeNode> tree = new ArrayList<TreeNode>();
		for (Tmenu t : l) {
			tree.add(this.tree(t, b));
		}
		return tree;
	}

	public List<TreeNode> getAuthTree(Menu menu, Boolean b, User user) {
		if (Tuser.USERTYPE_MONITOR == user.getUserType()) {
			return tree(menu, b);
		}
		List<TreeNode> retTree = new ArrayList<TreeNode>();
		for (String s : user.getMenuIds().split(",")) {
			if (!s.equals("0")) {
				Tmenu m = menuDao.get(Tmenu.class, s.trim());
				if (m != null) {
					retTree.add(this.tree(m, true));
				}
			}
		}
		return retTree;
	}

	public List<TreeNode> getSingleNode(String id, Boolean b) throws Exception {
		if (!ValidateUtil.isValid(id)) {
			return null;
		}
		List<TreeNode> retTree = new ArrayList<TreeNode>();
		Tmenu m = menuDao.get(Tmenu.class, id);
		if (m != null) {
			TreeNode node = this.tree(m, true);//将menu数据模型转换为TreeNode模型
			if (node.getChildren() != null) {
				List<TreeNode> nodes = node.getChildren();
				for (TreeNode treeNode : nodes) {
					retTree.add(treeNode);
				}
			}
		}
		return retTree;
	}

	//根据树型结构获取所有菜单信息，返回（Map(主菜单JSON,子菜单List)）
	public Map<String, List<TreeNode>> getAllMenuAndChild()throws Exception {
		Map<String, List<TreeNode>> retMap = new HashMap<String, List<TreeNode>>(
				0);
		try {
			List<ComboboxJson> mainMenus = this.getMainMenus();
			if (ValidateUtil.isValid(mainMenus)) {
				List<TreeNode> temp = null;
				for (ComboboxJson json : mainMenus) {
					temp = this.getSingleNode(json.getValue(), true);
					if (null != temp) {
						retMap.put(json.getValue(), temp);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (retMap != null && retMap.size() > 0) {
			return retMap;
		}
		return null;
	}

	public List<ComboboxJson> getMainMenus() throws Exception {
		String sql = "select * from tb_menu where mainMenuFlag=? order by cseq asc";
		List<Tmenu> retVals = menuDao.findAllBySQL(Tmenu.class, sql, 1);
		List<ComboboxJson> rets = new ArrayList<ComboboxJson>();
		ComboboxJson json = null;
		if (ValidateUtil.isValidListObject(retVals)) {
			for (Tmenu temp : retVals) {
				if (null != temp) {
					json = new ComboboxJson();
					json.setText(temp.getCname());
					json.setValue(temp.getCid());
					rets.add(json);
				}
			}
			return rets;
		}
		return null;
	}

	private TreeNode tree(Tmenu t, boolean recursive) {
		TreeNode node = new TreeNode();
		node.setId(t.getCid());
		node.setText(t.getCname());
		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("url", t.getCurl());
		node.setAttributes(attributes);
		if (t.getCiconcls() != null) {
			node.setIconCls(t.getCiconcls());
		} else {
			node.setIconCls("");
		}
		if (t.getTmenus() != null && t.getTmenus().size() > 0) {
			node.setState("closed");
			if (recursive) {// 递归查询子节点
				List<Tmenu> menus = new ArrayList<Tmenu>(t.getTmenus());
				Collections.sort(menus, new MenuComparator());// 排序
				List<TreeNode> children = new ArrayList<TreeNode>();
				for (Tmenu r : menus) {
					TreeNode tn = tree(r, true);
					children.add(tn);
				}
				node.setChildren(children);
				node.setState("open");
			}
		}
		return node;
	}

	public List<Menu> treegrid(Menu menu) {
		List<Tmenu> l;
		if (menu != null && menu.getId() != null) {
			l = menuDao.find(
					"from Tmenu t where t.tmenu.cid = ? order by t.cseq",
					new Object[] { menu.getId() });
		} else {
			l = menuDao
					.find("from Tmenu t where t.tmenu is null order by t.cseq");
		}
		return changeModel(l);
	}
	
	
	public DataGrid datagrid(Menu menu) {
		// fixTrainerToPerson();
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(this.changeModel(this.find(menu)));
		dataGrid.setTotal(this.total(menu));
		return dataGrid;
	}
	
	private Long total(Menu menu) {
		return menuDao.count(
				"select count(*) from Tmenu");
	}
	
	private  List<Tmenu> find(Menu menu){
		String sql="select * from tb_menu where 1=?";
		return menuDao.findAllBySQL(Tmenu.class, sql, 1);
	}

	private List<Menu> changeModel(List<Tmenu> Tmenus) {
		List<Menu> l = new ArrayList<Menu>();
		if (Tmenus != null && Tmenus.size() > 0) {
			for (Tmenu t : Tmenus) {
				Menu m = new Menu();
				BeanUtils.copyProperties(t, m);
				if (t.getTmenu() != null) {
					m.setPid(t.getTmenu().getCid());
					m.setPname(t.getTmenu().getCname());
				}
				if (countChildren(t.getCid()) > 0) {
					m.setState("closed");
				}
				if (t.getCiconcls() == null) {
					m.setIconCls("");
				} else {
					m.setIconCls(t.getCiconcls());
				}
				l.add(m);
			}
		}
		return l;
	}

	/**
	 * 统计有多少子节点
	 */
	private Long countChildren(String pid) {
		return menuDao.count(
				"select count(*) from Tmenu t where t.tmenu.cid = ?",
				new Object[] { pid });
	}

	public void delete(Menu menu) {
		del(menu.getCid());
	}

	private void del(String cid) {
		Tmenu t = menuDao.get(Tmenu.class, cid);
		if (t != null) {
			Set<Tmenu> menus = t.getTmenus();
			if (menus != null && !menus.isEmpty()) {
				// 说明当前菜单下面还有子菜单
				for (Tmenu tmenu : menus) {
					del(tmenu.getCid());
				}
			}
			menuDao.delete(t);
		}
	}

	public void add(Menu menu) {
		Tmenu t = new Tmenu();
		BeanUtils.copyProperties(menu, t);
		t.setCid(UUID.randomUUID().toString());
		if (menu.getPid() != null) {
			t.setTmenu(menuDao.get(Tmenu.class, menu.getPid()));
		}
		if (menu.getIconCls() != null) {
			t.setCiconcls(menu.getIconCls());
		}
		menuDao.save(t);
	}

	public void edit(Menu menu) {
		Tmenu t = menuDao.get(Tmenu.class, menu.getCid());
		BeanUtils.copyProperties(menu, t);
		if (menu.getIconCls() != null) {
			t.setCiconcls(menu.getIconCls());
		}
		if (menu.getPid() != null && !menu.getPid().equals(menu.getCid())) {
			Tmenu pt = menuDao.get(Tmenu.class, menu.getPid());
			if (pt != null) {
				if (isDown(t, pt)) {// 要将当前节点修改到当前节点的子节点中
					Set<Tmenu> tmenus = t.getTmenus();// 当前要修改的节点的所有下级节点
					if (tmenus != null && tmenus.size() > 0) {
						for (Tmenu tmenu : tmenus) {
							if (tmenu != null) {
								tmenu.setTmenu(null);
							}
						}
					}
				}
				t.setTmenu(pt);
			}

		}
	}

	/**
	 * 判断是否是将当前节点修改到当前节点的子节点
	 * 
	 * @param t
	 * @param pt
	 * @return
	 */
	private boolean isDown(Tmenu t, Tmenu pt) {
		if (pt.getTmenu() != null) {
			if (pt.getTmenu().getCid().equals(t.getCid())) {
				return true;
			} else {
				return isDown(t, pt.getTmenu());
			}
		}
		return false;
	}

}

package com.tuocheng.service.demo.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.Torganization;
import com.tuocheng.model.demo.TweixinMenu;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.WeixinMenu;
import com.tuocheng.service.demo.WeixinMenuServiceI;
import com.tuocheng.util.base.Util;

/**
 * 微信自定义菜单service实现类
 * 
 * @author 李杰
 * 
 */
@Service("weixinMenuService")
public class WeixinMenuServiceImpl implements WeixinMenuServiceI {

	private BaseDaoI<TweixinMenu> weixinMenuDao;
	private BaseDaoI<Object[]> objDao;
	
	private BaseDaoI<Torganization> orgDao;

	// 注入DAO
	@Autowired
	public void setWeixinMenuDao(BaseDaoI<TweixinMenu> weixinMenuDao) {
		this.weixinMenuDao = weixinMenuDao;
	}
	
	// 注入DAO
	@Autowired
	public void setObjectDao(BaseDaoI<Object[]> objDao) {
		this.objDao = objDao;
	}
	
	// 注入DAO
	@Autowired
	public void setTorganizationDao(BaseDaoI<Torganization> orgDao) {
		this.orgDao = orgDao;
	}

	@Override
	public WeixinMenu save(WeixinMenu weixinMenu) {
		// 1.数据模型转换
		TweixinMenu t = new TweixinMenu();
		BeanUtils.copyProperties(weixinMenu, t);
		// 3.保存数据
		weixinMenuDao.save(t);
		// 4.返回结果集
		BeanUtils.copyProperties(t, weixinMenu);
		return weixinMenu;
	}

	@Override
	public void delete(String ids) {
		// 1.判断参数是否为空
		if (ids != null) {
			// 2.对参数的字符串进行分割
			for (String id : ids.split(",")) {
				// 排除id为0的情况
				if (!id.trim().equals("0")) {
					// 3.根据ID从数据库中查找数据
					TweixinMenu t = weixinMenuDao.get(TweixinMenu.class, id);
					// 4.调用DAO层删除数据
					weixinMenuDao.delete(t);
				}
			}
		}
	}

	@Override
	public WeixinMenu update(WeixinMenu weixinMenu) {
		// 1.数据模型转换
		TweixinMenu t = new TweixinMenu();
		BeanUtils.copyProperties(weixinMenu, t, new String[] { "id" });
		// 2.设置相关属性
		t.setId(weixinMenu.getId());
		// 3.更新数据
		weixinMenuDao.update(t);
		// 4.返回结果集
		BeanUtils.copyProperties(t, weixinMenu);
		return weixinMenu;
	}

	@Override
	public DataGrid dataGrid(WeixinMenu weixinMenu) {
		DataGrid dataGrid = new DataGrid();
		//1.从数据库中查找出所有实体类信息
		//2.查找出的信息进行数据模型转换
		//3.设置展现数据
		List<Object[]> list = this.findObjctArray(weixinMenu);
		if( (list == null || list.size() == 0) && Util.isNotNull(weixinMenu.getOrganizationId())){
			// 如果菜单数据是空的，则填充数据
			Torganization torg = orgDao.get(Torganization.class, weixinMenu.getOrganizationId());
			if(torg != null && torg.getParent() != null){
				addMenuData(torg.getId()); // 初始化
				list = this.findObjctArray(weixinMenu); // 然后再重新读取
			}
		}
		dataGrid.setRows(this.changeObjectArray(list));
		dataGrid.setTotal(this.total(weixinMenu));
		return dataGrid;
	}
	
	@Override
	public List<TweixinMenu> find(WeixinMenu weixinMenu){
		String hql = " from TweixinMenu t where 1 = 1 ";
		List<Object> params = new ArrayList<Object>();
		hql = addWhere(weixinMenu, hql, params);
		
		weixinMenu.setSort("reorder");
		weixinMenu.setOrder("asc");
		weixinMenu.setRows(100);

		if (weixinMenu.getSort() != null && weixinMenu.getOrder() != null) {
			hql += " order by t." + weixinMenu.getSort() + " " + weixinMenu.getOrder();
		}

		return weixinMenuDao.find(hql, params, weixinMenu.getPage(), weixinMenu.getRows());
	}
	
	private List<Object[]> findObjctArray(WeixinMenu weixinMenu){
		
		String hql = "select t.id, t.keyname, t.nickname, t.type, t.reorder, t.content, t.cid, t.status, t.organizationId, torg.name from TweixinMenu t , Torganization torg where t.organizationId = torg.id ";
		List<Object> params = new ArrayList<Object>();
		hql = addWhere(weixinMenu, hql, params);

		weixinMenu.setSort("reorder");
		weixinMenu.setOrder("asc");
		weixinMenu.setRows(100);

		if (weixinMenu.getSort() != null && weixinMenu.getOrder() != null) {
			hql += " order by t." + weixinMenu.getSort() + " " + weixinMenu.getOrder();
		}

		return objDao.find(hql, params, weixinMenu.getPage(), weixinMenu.getRows());
	}
	
	private List<WeixinMenu> changeModel(List<TweixinMenu> ts){
		List<WeixinMenu> stcs = new ArrayList<WeixinMenu>();
		if (ts != null && ts.size() > 0) {
			for (TweixinMenu t : ts) {
				WeixinMenu weixinMenu = new WeixinMenu();
				BeanUtils.copyProperties(t, weixinMenu);
				weixinMenu.setTmpParentId(t.getCid());
				stcs.add(weixinMenu);
			}
		}
		return stcs;
	}
	
	private List<WeixinMenu> changeObjectArray(List<Object[]> objs){
		List<WeixinMenu> menus = new ArrayList<WeixinMenu>();
		if (objs != null && objs.size() > 0) {
			for (Object[] o : objs) {
				WeixinMenu wxMenu = new WeixinMenu();
				//t.id, t.keyname, tu.nickname, t.type, t.reorder, t.content, t.cid, t.status, to.organizationId, to.organizationName
				wxMenu.setId(Util.objToInt(o[0], 0));
				wxMenu.setKeyname(Util.objToString(o[1], ""));
				wxMenu.setNickname(Util.objToString(o[2], ""));
				wxMenu.setType(Util.objToString(o[3], ""));
				wxMenu.setReorder(Util.objToInt(o[4], 0));
				wxMenu.setContent(Util.objToString(o[5], ""));
				wxMenu.setCid(Util.objToInt(o[6], 0));
				wxMenu.setStatus(Util.objToInt(o[7], 0));
				wxMenu.setOrganizationId(Util.objToString(o[8], ""));
				wxMenu.setOrganizationName(Util.objToString(o[9], ""));
				wxMenu.setTmpParentId(wxMenu.getCid());
				menus.add(wxMenu);
			}
		}
		return menus;
	}

	/**
	 * 添加查询约束条件where
	 * 
	 * @param student
	 * @param hql
	 * @param values
	 * @return
	 */
	private String addWhere(WeixinMenu weixinMenu, String hql, List<Object> values) {
		if(Util.isNotNull(weixinMenu.getOrganizationId())){
			hql += " and t.organizationId = ? ";
			values.add(weixinMenu.getOrganizationId());
		}
		return hql;
	}
	
	/**
	 * 统计符合当前查询条件的总记录数
	 * 
	 * @param timerCard
	 * @return
	 */
	private Long total(WeixinMenu weixinMenu) {
		String hql = "select count(*) from TweixinMenu t where 1 = 1 ";
		List<Object> params = new ArrayList<Object>();
		hql = addWhere(weixinMenu, hql, params);
		return weixinMenuDao.count(hql, params);
	}
	
	@Override
	public TweixinMenu findByKey(String orgId, String keyname) {
		String hql = " from TweixinMenu t where organizationId = ? and keyname = ? ";
		List<Object> params = new ArrayList<Object>();
		params.add(orgId);
		params.add(keyname);

		return weixinMenuDao.get(hql, params);
	}
	
	/**
	 * 初始化微信自定义菜单数据
	 * @return
	 */
	private void addMenuData(String organizationId){
		TweixinMenu top1 = new TweixinMenu("在线报名", "view", 1, 0, 1, organizationId, "ZXBM", "/m/ad1/ad1.jsp");
		weixinMenuDao.save(top1);
		TweixinMenu btn11 = new TweixinMenu("-", "click", 2, top1.getId(), 0, organizationId, "", "");
		TweixinMenu btn12 = new TweixinMenu("-", "click", 3, top1.getId(), 0, organizationId, "", "");
		TweixinMenu btn13 = new TweixinMenu("-", "click", 4, top1.getId(), 0, organizationId, "", "");
		TweixinMenu btn14 = new TweixinMenu("-", "click", 5, top1.getId(), 0, organizationId, "", "");
		TweixinMenu btn15 = new TweixinMenu("-", "click", 6, top1.getId(), 0, organizationId, "", "");
		weixinMenuDao.save(btn11);
		weixinMenuDao.save(btn12);
		weixinMenuDao.save(btn13);
		weixinMenuDao.save(btn14);
		weixinMenuDao.save(btn15);
		
		
		TweixinMenu top2 = new TweixinMenu("学员服务", "view", 7, 0, 1, organizationId, "XYFW", "/m/articleAction!index.action");
		weixinMenuDao.save(top2);
		TweixinMenu btn21 = new TweixinMenu("-", "click", 8, top2.getId(), 0, organizationId, "", "");
		TweixinMenu btn22 = new TweixinMenu("-", "click", 9, top2.getId(), 0, organizationId, "", "");
		TweixinMenu btn23 = new TweixinMenu("-", "click", 10, top2.getId(), 0, organizationId, "", "");
		TweixinMenu btn24 = new TweixinMenu("-", "click", 11, top2.getId(), 0, organizationId, "", "");
		TweixinMenu btn25 = new TweixinMenu("-", "click", 12, top2.getId(), 0, organizationId, "", "");
		weixinMenuDao.save(btn21);
		weixinMenuDao.save(btn22);
		weixinMenuDao.save(btn23);
		weixinMenuDao.save(btn24);
		weixinMenuDao.save(btn25);
		
		TweixinMenu top3 = new TweixinMenu("更多", "menu", 13, 0, 1, organizationId, "", "");
		weixinMenuDao.save(top3);
		TweixinMenu btn31 = new TweixinMenu("关于我们", "click", 14, top3.getId(), 1, organizationId, "GYWM", "");
		TweixinMenu btn32 = new TweixinMenu("联系我们", "click", 15, top3.getId(), 1, organizationId, "LXWM", "");
		TweixinMenu btn33 = new TweixinMenu("关注公众号", "view", 16, top3.getId(), 1, organizationId, "GZGZH", "/m/share.jsp");
		TweixinMenu btn34 = new TweixinMenu("教练服务", "view", 17, top3.getId(), 1, organizationId, "JLFW", "/m/trainerIndex.jsp");
		TweixinMenu btn35 = new TweixinMenu("校长服务", "view", 18, top3.getId(), 1, organizationId, "MINITOR", "m/articleAction!monitorIndex.action");
		weixinMenuDao.saveOrUpdate(btn31);
		weixinMenuDao.saveOrUpdate(btn32);
		weixinMenuDao.saveOrUpdate(btn33);
		weixinMenuDao.saveOrUpdate(btn34);
		weixinMenuDao.saveOrUpdate(btn35);
	}

}

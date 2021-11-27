package com.tuocheng.service.demo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.TweixinMessage;
import com.tuocheng.model.demo.TweixinUser;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.WeixinMenu;
import com.tuocheng.pageModel.demo.WeixinUser;
import com.tuocheng.service.demo.WeixinUserServiceI;
import com.tuocheng.util.base.Util;

/**
 * 微信用户信息service实现类
 * 
 * @author 李杰
 * 
 */
@Service("weixinUserService")
public class WeixinUserServiceImpl implements WeixinUserServiceI {

	private BaseDaoI<TweixinUser> weixinUserDao;
	private BaseDaoI<Object[]> objDao;

	// 注入DAO
	@Autowired
	public void setWeixinUserDao(BaseDaoI<TweixinUser> weixinUserDao) {
		this.weixinUserDao = weixinUserDao;
	}
	
	// 注入DAO
	@Autowired
	public void setObjectDao(BaseDaoI<Object[]> objDao) {
		this.objDao = objDao;
	}

	@Override
	public TweixinUser save(TweixinMessage tmsg, String orgId) {
		if(tmsg != null){
			TweixinUser t = getByOpenId(tmsg.getFromUserName());
			if(t != null){
				if(tmsg.getMsgType() < 10){
					t.setLastMsgContent(tmsg.getMsgContent());
					t.setLastMsgTime(tmsg.getCreateTime());
					t.setMsgUnread(Util.objToInt(t.getMsgUnread(), 0) + 1);
					t.setMsgSum(Util.objToInt(t.getMsgSum(), 0) + 1);
				}
				t.setVisit(Util.objToInt(t.getVisit(), 0) + 1);
				t.setLastVisitTime(tmsg.getCreateTime());
			} else {
				t = new TweixinUser();
				t.setId(UUID.randomUUID().toString());
				t.setOpenId(tmsg.getFromUserName());
				if(tmsg.getMsgType() < 10){
					t.setLastMsgContent(tmsg.getMsgContent());
					t.setLastMsgTime(tmsg.getCreateTime());
					t.setMsgUnread(1);
					t.setMsgSum(1);
				} 
				t.setVisit(1);
				t.setLastVisitTime(tmsg.getCreateTime());
				t.setCreateTime(tmsg.getCreateTime());
				t.setStatus(1);
			}
			t.setOrganizationId(orgId);
			weixinUserDao.saveOrUpdate(t);
			return t;
		}
		return null;
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
					TweixinUser t = weixinUserDao.get(TweixinUser.class, id);
					// 4.调用DAO层删除数据
					weixinUserDao.delete(t);
				}
			}
		}
	}

	@Override
	public TweixinUser update(TweixinUser t) {
		weixinUserDao.update(t);
		return t;
	}

	@Override
	public DataGrid dataGrid(WeixinUser weixinUser) {
		DataGrid dataGrid = new DataGrid();
		//1.从数据库中查找出所有实体类信息
		//2.查找出的信息进行数据模型转换
		//3.设置展现数据
		dataGrid.setRows(this.changeObjectArray(this.findObjctArray(weixinUser)));
		dataGrid.setTotal(this.total(weixinUser));
		return dataGrid;
	}
	
	private List<TweixinUser> find(WeixinUser weixinUser){
		String hql = " from TweixinUser t where 1 = 1 ";
		List<Object> params = new ArrayList<Object>();
		hql = addWhere(weixinUser, hql, params);

		if (weixinUser.getSort() != null && weixinUser.getOrder() != null) {
			hql += " order by " + weixinUser.getSort() + " " + weixinUser.getOrder();
		}

		return weixinUserDao.find(hql, params, weixinUser.getPage(), weixinUser.getRows());
	}
	
	private List<Object[]> findObjctArray(WeixinUser weixinUser){
		
		String hql = "select t.id, t.name, t.openId, t.unionId, t.studentId, t.password, t.mobile, t.email, t.icon, t.sex, t.city, t.country, "
				+ "t.province, t.language, t.lastMsgTime, t.lastMsgContent, t.msgSum, t.msgUnread, t.visit, t.lastVisitTime, t.createTime, t.ext,"
				+ " t.fromType, t.status, t.organizationId,t.realName, torg.name from TweixinUser t , Torganization torg where t.organizationId = torg.id ";
		List<Object> params = new ArrayList<Object>();
		hql = addWhere(weixinUser, hql, params); 

		if (weixinUser.getSort() != null && weixinUser.getOrder() != null) {
			hql += " order by t." + weixinUser.getSort() + " " + weixinUser.getOrder();
		}

		return objDao.find(hql, params, weixinUser.getPage(), weixinUser.getRows());
	}
	
	private List<WeixinUser> changeModel(List<TweixinUser> ts){
		List<WeixinUser> stcs = new ArrayList<WeixinUser>();
		if (ts != null && ts.size() > 0) {
			for (TweixinUser t : ts) {
				WeixinUser weixinUser = new WeixinUser();
				BeanUtils.copyProperties(t, weixinUser);
				stcs.add(weixinUser);
			}
		}
		return stcs;
	}
	
	private List<WeixinUser> changeObjectArray(List<Object[]> objs){
		List<WeixinUser> users = new ArrayList<WeixinUser>();
		if (objs != null && objs.size() > 0) {
			for (Object[] o : objs) {
				WeixinUser u = new WeixinUser();
				//select t.id, t.name, t.openId, t.unionId, t.studentId, t.password, t.mobile, t.email, t.icon, t.sex, t.city, t.country, "
				//+ "t.province, t.language, t.lastMsgTime, t.lastMsgContent, t.msgSum, t.msgUnread, t.visit, t.lastVisitTime, t.createTime
				//, t.ext, t.fromType, t.status, t.organizationId, torg.name
				u.setId(Util.objToString(o[0], ""));
				u.setName(Util.objToString(o[1], ""));
				u.setOpenId(Util.objToString(o[2], ""));
				u.setUnionId(Util.objToString(o[3], ""));
				u.setStudentId(Util.objToString(o[4], ""));
				u.setPassword(Util.objToString(o[5], ""));
				u.setMobile(Util.objToString(o[6], ""));
				u.setEmail(Util.objToString(o[7], ""));
				u.setIcon(Util.objToString(o[8], ""));
				u.setSex(Util.objToInt(o[9], 0));
				u.setCity(Util.objToString(o[10], ""));
				u.setCountry(Util.objToString(o[11], ""));
				u.setProvince(Util.objToString(o[12], ""));
				u.setLanguage(Util.objToString(o[13], ""));
				u.setLastMsgTime((Date)o[14]);
				u.setLastMsgContent(Util.objToString(o[15], ""));
				u.setMsgSum(Util.objToInt(o[16], 0));
				u.setMsgUnread(Util.objToInt(o[17], 0));
				u.setVisit(Util.objToInt(o[18], 0));
				u.setLastVisitTime((Date)o[19]);
				u.setCreateTime((Date)o[20]);
				u.setExt(Util.objToString(o[21], ""));
				u.setFromType(Util.objToInt(o[22], 0));
				u.setStatus(Util.objToInt(o[23], 0));
				u.setOrganizationId(Util.objToString(o[24], ""));
				u.setRealName(Util.objToString(o[25], ""));
				u.setOrganizationName(Util.objToString(o[26], ""));
				users.add(u);
			}
		}
		return users;
	}

	/**
	 * 添加查询约束条件where
	 * 
	 * @param student
	 * @param hql
	 * @param values
	 * @return
	 */
	private String addWhere(WeixinUser weixinUser, String hql, List<Object> values) {
		// 改名模糊查询
		if (weixinUser.getName() != null && !weixinUser.getName().trim().equals("")) {
			hql += " and t.name like ? ";
			values.add("%%" + weixinUser.getName().trim() + "%%");
		}
		// 创建时间（范围查询）
		if (weixinUser.getCreateTimeStart() != null) {
			hql += " and t.createTime >= ? ";
			values.add(weixinUser.getCreateTimeStart());
		}
		if (weixinUser.getCreateTimeEnd() != null) {
			hql += " and t.createTime <= ? ";
			values.add(weixinUser.getCreateTimeEnd());
		}
		
		if(Util.isNotNull(weixinUser.getOrganizationId())){
			hql += " and t.organizationId = ? ";
			values.add(weixinUser.getOrganizationId());
		}
		if(Util.isNotNull(weixinUser.getRealName())){
			hql += " and t.realName like ? ";
			values.add("%%" +weixinUser.getRealName()+"%%");
		}
		return hql;
	}
	
	/**
	 * 统计符合当前查询条件的总记录数
	 * 
	 * @param timerCard
	 * @return
	 */
	private Long total(WeixinUser weixinUser) {
		String hql = "select count(*) from TweixinUser t where 1 = 1 ";
		List<Object> params = new ArrayList<Object>();
		hql = addWhere(weixinUser, hql, params);
		return weixinUserDao.count(hql, params);
	}

	@Override
	public TweixinUser getByOpenId(String openId) {
		if(Util.isNotNull(openId)){
			String hql = " from TweixinUser t where openId = ? ";
			List<Object> params = new ArrayList<Object>();
			params.add(openId);
			return weixinUserDao.get(hql, params);
		} else {
			return null;
		}
	}
	
	@Override
	public TweixinUser getByStudentId(String studentId) {
		if(Util.isNotNull(studentId)){
			String hql = " from TweixinUser t where studentId = ? ";
			List<Object> params = new ArrayList<Object>();
			params.add(studentId);
			return weixinUserDao.get(hql, params);
		} else {
			return null;
		}
	}

	@Override
	public TweixinUser getByTrainerId(String trainerId) {
		if(Util.isNotNull(trainerId)){
			String hql = " from TweixinUser t where trainerId = ? ";
			List<Object> params = new ArrayList<Object>();
			params.add(trainerId);
			return weixinUserDao.get(hql, params);
		} else {
			return null;
		}
	}

}

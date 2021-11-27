package com.tuocheng.service.demo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.Tonline;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.Online;
import com.tuocheng.service.demo.OnlineServiceI;
import com.tuocheng.util.base.MenuInitUtils;
import com.tuocheng.util.base.ValidateUtil;

@Service("demoOnlineService")
public class OnlineServiceImpl implements OnlineServiceI {

	private BaseDaoI<Tonline> onlineDao;

	@Autowired
	public void setOnlineDao(BaseDaoI<Tonline> onlineDao) {
		this.onlineDao = onlineDao;
	}

	public void saveOrUpdateTonlineByLoginNameAndIp(String loginName,
			String ip, String schoolArea) {
		Tonline t = onlineDao.get(
				"from Tonline t where t.cname = ? and t.cip = ?", new Object[] {
						loginName, ip });
		if (t != null) {
			t.setCdatetime(new Date());
		} else {
			Tonline o = new Tonline();
			o.setCid(UUID.randomUUID().toString());
			o.setCdatetime(new Date());
			o.setCname(loginName);
			o.setCip(ip);
			if (ValidateUtil.isValid(schoolArea)) {
				o.setSchoolArea(schoolArea);
			} else {
				o.setSchoolArea("超级用户");
			}

			onlineDao.save(o);
		}
	}

	public void deleteTonlineByLoginNameAndIp(String loginName, String ip) {
		Tonline t = onlineDao.get(
				"from Tonline t where t.cname = ? and t.cip = ?", new Object[] {
						loginName, ip });
		if (t != null) {
			onlineDao.delete(t);
		}
	}

	public DataGrid datagrid(Online online) {
		DataGrid j = new DataGrid();
		j.setRows(find(online));
		j.setTotal(total(online));
		return j;
	}

	private List<Tonline> find(Online online) {
		Date date = new Date();
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		String hql = "from Tonline t where 1=1 and cdatetime>=? and cname!='driverschool'";

		List<Object> parms = new ArrayList<Object>();
		parms.add(date);
		hql = addWhere(online, hql, parms);

		if (online.getSort() != null && online.getOrder() != null) {
			hql += " order by " + online.getSort() + " " + online.getOrder();
		}
		return onlineDao.find(hql, parms, online.getPage(), online.getRows());
	}

	private Long total(Online online) {
		Date date = new Date();
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		String hql = "select count(*) from Tonline t where 1=1 and cdatetime>=? and cname!='driverschool'";
		List<Object> parms = new ArrayList<Object>();
		parms.add(date);
		hql = addWhere(online, hql, parms);
		return onlineDao.count(hql, parms);
	}

	private String addWhere(Online online, String hql, List<Object> parms) {
		return hql;
	}

}

package com.tuocheng.service.demo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.Tsalesman;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.Salesman;
import com.tuocheng.service.demo.SalesmanServiceI;
import com.tuocheng.util.base.Util;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 微信业务员评论service实现类
 * 
 * @author MEI702
 * 
 */
@Service("salesmanService")
public class SalesmanServiceImpl implements SalesmanServiceI {

	private BaseDaoI<Tsalesman> salesmanDao;
	private BaseDaoI<Object[]> objDao;

	@Autowired
	public void setSalesmanDao(BaseDaoI<Tsalesman> salesmanDao) {
		this.salesmanDao = salesmanDao;
	}

	@Autowired
	public void setObjDao(BaseDaoI<Object[]> objDao) {
		this.objDao = objDao;
	}

	@Override
	public Salesman save(Salesman salesman) {
		// 1.数据模型转换
		Tsalesman t = new Tsalesman();
		BeanUtils.copyProperties(salesman, t);
		// 2.设置相关属性
		t.setId(UUID.randomUUID().toString());
		t.setCreateTime(new Date());
		// 3.保存数据
		salesmanDao.save(t);
		// 4.返回结果集
		BeanUtils.copyProperties(t, salesman);
		return salesman;
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
					Tsalesman t = salesmanDao.get(Tsalesman.class, id);
					// 4.调用DAO层删除数据
					salesmanDao.delete(t);
				}
			}
		}
	}

	@Override
	public Salesman update(Salesman salesman) {
		// 1.数据模型转换
		Tsalesman t = new Tsalesman();
		BeanUtils.copyProperties(salesman, t, new String[] { "id" });
		// 2.设置相关属性
		t.setId(salesman.getId());
		// 3.更新数据
		salesmanDao.update(t);
		// 4.返回结果集
		BeanUtils.copyProperties(t, salesman);
		return salesman;
	}

	@Override
	public DataGrid dataGrid(Salesman salesman) {
		DataGrid dataGrid = new DataGrid();
		// 1.从数据库中查找出所有实体类信息
		// 2.查找出的信息进行数据模型转换
		// 3.设置展现数据
		dataGrid.setRows(this.changeObjectArray(this.findObjctArray(salesman)));
		dataGrid.setTotal(this.total(salesman));
		return dataGrid;
	}

	@Override
	public List<Salesman> listData(Salesman salesman) throws Exception {
		List<Salesman> list = changeObjectArray(findObjctArray(salesman));
		return list;
	}

	private List<Object[]> findObjctArray(Salesman salesman) {
		String hql = "select t.id, t.openId, tu.name, t.personId, ta.name, t.stars, t.evaluate, t.createTime, t.comments, t.status, torg.id, torg.name from Tsalesman t , TweixinUser tu, Tperson ta, Torganization torg where t.openId = tu.openId and t.personId = ta.id and tu.organizationId = torg.id ";
		List<Object> params = new ArrayList<Object>();
		hql = addWhere(salesman, hql, params);

		if (salesman.getSort() != null && salesman.getOrder() != null) {
			hql += " order by t." + salesman.getSort() + " " + salesman.getOrder();
		}

		return objDao.find(hql, params, salesman.getPage(), salesman.getRows());
	}

	private List<Salesman> changeObjectArray(List<Object[]> objs) {
		List<Salesman> retValue = new ArrayList<Salesman>();
		if (objs != null && objs.size() > 0) {
			for (Object[] obj : objs) {
				Salesman salesman = new Salesman();
				salesman.setId(Util.objToString(obj[0], ""));
				salesman.setOpenId(Util.objToString(obj[1], ""));
				salesman.setNickname(Util.objToString(obj[2], ""));
				salesman.setPersonId(Util.objToString(obj[3], ""));
				salesman.setPersonName(Util.objToString(obj[4], ""));
				salesman.setStars(Util.objToInt(obj[5], 0));
				salesman.setEvaluate(Util.objToString(obj[6], ""));
				salesman.setCreateTime((Date) obj[7]);
				salesman.setComments(Util.objToString(obj[8], ""));
				salesman.setStatus(Util.objToInt(obj[9], 0));
				salesman.setOrganizationId(Util.objToString(obj[10], ""));
				salesman.setOrganizationName(Util.objToString(obj[11], ""));
				retValue.add(salesman);
			}
		}
		return retValue;
	}

	/**
	 * 添加查询约束条件where
	 * 
	 * @param student
	 * @param hql
	 * @param values
	 * @return
	 */
	private String addWhere(Salesman salesman, String hql, List<Object> values) {

		//
		if (Util.isNotNull(salesman.getOpenId())) {
			hql += " and t.openId = ? ";
			values.add(salesman.getOpenId());
		}

		if (Util.isNotNull(salesman.getOrganizationId())) {
			hql += " and tu.organizationId = ? ";
			values.add(salesman.getOrganizationId());
		}
		if (ValidateUtil.isValid(salesman.getPersonName())) {
			hql += " and ta.name = ? ";
			values.add(salesman.getPersonName().trim());
		}

		if (ValidateUtil.isValid(salesman.getCreateTimeStart())) {
			hql += " and t.createTime >= ? ";
			values.add(salesman.getCreateTimeStart());
		}
		if (ValidateUtil.isValid(salesman.getCreateTimeEnd())) {
			hql += " and t.createTime <= ? ";
			values.add(salesman.getCreateTimeEnd());
		}
		return hql;
	}

	/**
	 * 统计符合当前查询条件的总记录数
	 * 
	 * @param difficulty
	 * @return
	 */
	private Long total(Salesman assess) {
		String hql = "select count(*) from Tsalesman t , TweixinUser tu, Tperson ta, Torganization torg where t.openId = tu.openId and t.personId = ta.id and tu.organizationId = torg.id ";
		List<Object> params = new ArrayList<Object>();
		hql = addWhere(assess, hql, params);
		return salesmanDao.count(hql, params);
	}

}

package com.tuocheng.service.demo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.Tassess;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.Assess;
import com.tuocheng.service.demo.AssessServiceI;
import com.tuocheng.util.base.Util;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 微信教练评论service实现类
 * 
 * @author 李杰
 * 
 */
@Service("assessService")
public class AssessServiceImpl implements AssessServiceI {

	private BaseDaoI<Tassess> assessDao;
	private BaseDaoI<Object[]> objDao;

	// 注入DAO
	@Autowired
	public void setAssessDao(BaseDaoI<Tassess> assessDao) {
		this.assessDao = assessDao;
	}

	// 注入DAO
	@Autowired
	public void setObjectDao(BaseDaoI<Object[]> objDao) {
		this.objDao = objDao;
	}

	@Override
	public Assess save(Assess assess) {
		// 1.数据模型转换
		Tassess t = new Tassess();
		BeanUtils.copyProperties(assess, t);
		// 2.设置相关属性
		t.setId(UUID.randomUUID().toString());
		t.setCreateTime(new Date());
		// 3.保存数据
		assessDao.save(t);
		// 4.返回结果集
		BeanUtils.copyProperties(t, assess);
		return assess;
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
					Tassess t = assessDao.get(Tassess.class, id);
					// 4.调用DAO层删除数据
					assessDao.delete(t);
				}
			}
		}
	}

	@Override
	public Assess update(Assess assess) {
		// 1.数据模型转换
		Tassess t = new Tassess();
		BeanUtils.copyProperties(assess, t, new String[] { "id" });
		// 2.设置相关属性
		t.setId(assess.getId());
		// 3.更新数据
		assessDao.update(t);
		// 4.返回结果集
		BeanUtils.copyProperties(t, assess);
		return assess;
	}

	@Override
	public DataGrid dataGrid(Assess assess) {
		DataGrid dataGrid = new DataGrid();
		// 1.从数据库中查找出所有实体类信息
		// 2.查找出的信息进行数据模型转换
		// 3.设置展现数据
		dataGrid.setRows(this.changeObjectArray(this.findObjctArray(assess)));
		dataGrid.setTotal(this.total(assess));
		return dataGrid;
	}

	@Override
	public List<Assess> listData(Assess assess) throws Exception {
		List<Assess> list = changeObjectArray(findObjctArray(assess));
		return list;
	}

	private List<Object[]> findObjctArray(Assess assess) {
		String hql = "select t.id, t.openId, tu.name, t.trainerId, ta.name, t.stars, t.evaluate, t.createTime, t.comments, t.status, torg.id, torg.name,t.serviceMark,t.efficientMark,t.dutyMark,t.groomingMark from Tassess t , TweixinUser tu, Ttrainer ta, Torganization torg where t.openId = tu.openId and t.trainerId = ta.id and tu.organizationId = torg.id ";
		List<Object> params = new ArrayList<Object>();
		hql = addWhere(assess, hql, params);

		if (assess.getSort() != null && assess.getOrder() != null) {
			hql += " order by t." + assess.getSort() + " " + assess.getOrder();
		}

		return objDao.find(hql, params, assess.getPage(), assess.getRows());
	}

	private List<Assess> changeObjectArray(List<Object[]> objs) {
		List<Assess> as = new ArrayList<Assess>();
		if (objs != null && objs.size() > 0) {
			for (Object[] o : objs) {
				Assess assess = new Assess();
				assess.setId(Util.objToString(o[0], ""));
				assess.setOpenId(Util.objToString(o[1], ""));
				assess.setNickname(Util.objToString(o[2], ""));
				assess.setTrainerId(Util.objToString(o[3], ""));
				assess.setTrainerName(Util.objToString(o[4], ""));
				assess.setStars(Util.objToInt(o[5], 0));
				assess.setEvaluate(Util.objToString(o[6], ""));
				assess.setCreateTime((Date) o[7]);
				assess.setComments(Util.objToString(o[8], ""));
				assess.setStatus(Util.objToInt(o[9], 0));
				assess.setOrganizationId(Util.objToString(o[10], ""));
				assess.setOrganizationName(Util.objToString(o[11], ""));
				assess.setServiceMark(Util.objToInt(o[12], 0));
				assess.setEfficientMark(Util.objToInt(o[13], 0));
				assess.setDutyMark(Util.objToInt(o[14], 0));
				assess.setGroomingMark(Util.objToInt(o[15], 0));
				assess.setTotalMark(Util.objToInt(o[12], 0) + Util.objToInt(o[13], 0) + Util.objToInt(o[14], 0)
						+ Util.objToInt(o[15], 0));
				as.add(assess);
			}
		}
		return as;
	}

	/**
	 * 添加查询约束条件where
	 * 
	 * @param student
	 * @param hql
	 * @param values
	 * @return
	 */
	private String addWhere(Assess assess, String hql, List<Object> values) {

		//
		if (Util.isNotNull(assess.getOpenId())) {
			hql += " and t.openId = ? ";
			values.add(assess.getOpenId());
		}

		if (Util.isNotNull(assess.getOrganizationId())) {
			hql += " and tu.organizationId = ? ";
			values.add(assess.getOrganizationId());
		}
		if (ValidateUtil.isValid(assess.getTrainerName())) {
			hql += " and ta.name = ? ";
			values.add(assess.getTrainerName().trim());
		}

		if (ValidateUtil.isValid(assess.getCreateTimeStart())) {
			hql += " and t.createTime >= ? ";
			values.add(assess.getCreateTimeStart());
		}
		if (ValidateUtil.isValid(assess.getCreateTimeEnd())) {
			hql += " and t.createTime <= ? ";
			values.add(assess.getCreateTimeEnd());
		}
		return hql;
	}

	/**
	 * 统计符合当前查询条件的总记录数
	 * 
	 * @param difficulty
	 * @return
	 */
	private Long total(Assess assess) {
		String hql = "select count(*) from Tassess t , TweixinUser tu, Ttrainer ta, Torganization torg where t.openId = tu.openId and t.trainerId = ta.id and tu.organizationId = torg.id ";
		List<Object> params = new ArrayList<Object>();
		hql = addWhere(assess, hql, params);
		return assessDao.count(hql, params);
	}

}

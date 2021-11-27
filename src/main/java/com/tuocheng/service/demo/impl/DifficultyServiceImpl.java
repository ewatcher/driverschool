package com.tuocheng.service.demo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.Tdifficulty;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.Difficulty;
import com.tuocheng.service.demo.DifficultyServiceI;
import com.tuocheng.util.base.Util;

/**
 * 微信报名service实现类
 * 
 * @author 李杰
 * 
 */
@Service("difficultyService")
public class DifficultyServiceImpl implements DifficultyServiceI {

	private BaseDaoI<Tdifficulty> difficultyDao;
	private BaseDaoI<Object[]> objDao;

	// 注入DAO
	@Autowired
	public void setDifficultyDao(BaseDaoI<Tdifficulty> difficultyDao) {
		this.difficultyDao = difficultyDao;
	}
	
	// 注入DAO
	@Autowired
	public void setObjectDao(BaseDaoI<Object[]> objDao) {
		this.objDao = objDao;
	}

	@Override
	public Difficulty save(Difficulty difficulty) {
		// 1.数据模型转换
		Tdifficulty t = new Tdifficulty();
		BeanUtils.copyProperties(difficulty, t);
		// 2.设置相关属性
		t.setId(UUID.randomUUID().toString());
		t.setCreateTime(new Date());
		// 3.保存数据
		difficultyDao.save(t);
		// 4.返回结果集
		BeanUtils.copyProperties(t, difficulty);
		return difficulty;
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
					Tdifficulty t = difficultyDao.get(Tdifficulty.class, id);
					// 4.调用DAO层删除数据
					difficultyDao.delete(t);
				}
			}
		}
	}

	@Override
	public Difficulty update(Difficulty difficulty) {
		// 1.数据模型转换
		Tdifficulty t = new Tdifficulty();
		BeanUtils.copyProperties(difficulty, t, new String[] { "id" });
		// 2.设置相关属性
		t.setId(difficulty.getId());
		// 3.更新数据
		difficultyDao.update(t);
		// 4.返回结果集
		BeanUtils.copyProperties(t, difficulty);
		return difficulty;
	}

	@Override
	public DataGrid dataGrid(Difficulty difficulty) {
		DataGrid dataGrid = new DataGrid();
		//1.从数据库中查找出所有实体类信息
		//2.查找出的信息进行数据模型转换
		//3.设置展现数据
		dataGrid.setRows(this.changeObjectArray(this.findObjctArray(difficulty)));
		dataGrid.setTotal(this.total(difficulty));
		return dataGrid;
	}
	
	@Override
	public List<Difficulty> listData(Difficulty difficulty) throws Exception {
		List<Difficulty> list = changeObjectArray(findObjctArray(difficulty));
		return list;
	}
	
	private List<Object[]> findObjctArray(Difficulty difficulty){
		String hql = "select t.id, t.openId, tu.name, t.difficulty, t.createTime, t.comments, t.status, torg.id, torg.name from Tdifficulty t , TweixinUser tu, Torganization torg where t.openId = tu.openId and tu.organizationId = torg.id ";
		List<Object> params = new ArrayList<Object>();
		hql = addWhere(difficulty, hql, params);

		if (difficulty.getSort() != null && difficulty.getOrder() != null) {
			hql += " order by t." + difficulty.getSort() + " " + difficulty.getOrder();
		}

		return objDao.find(hql, params, difficulty.getPage(), difficulty.getRows());
	}
	
	
	
	private List<Difficulty> changeObjectArray(List<Object[]> objs){
		List<Difficulty> stcs = new ArrayList<Difficulty>();
		if (objs != null && objs.size() > 0) {
			for (Object[] o : objs) {
				Difficulty difficulty = new Difficulty();
				difficulty.setId(Util.objToString(o[0], ""));
				difficulty.setOpenId(Util.objToString(o[1], ""));
				difficulty.setNickname(Util.objToString(o[2], ""));
				difficulty.setDifficulty(Util.objToString(o[3], ""));
				difficulty.setCreateTime((Date)o[4]);
				difficulty.setComments(Util.objToString(o[5], ""));
				difficulty.setStatus(Util.objToInt(o[6], 0));
				difficulty.setOrganizationId(Util.objToString(o[7], ""));
				difficulty.setOrganizationName(Util.objToString(o[8], ""));
				stcs.add(difficulty);
			}
		}
		return stcs;
	}
	
	/**
	 * 添加查询约束条件where
	 * 
	 * @param student
	 * @param hql
	 * @param values
	 * @return
	 */
	private String addWhere(Difficulty difficulty, String hql, List<Object> values) {
		
		if(Util.isNotNull(difficulty.getOpenId())){
			hql += " and t.openId = ? ";
			values.add(difficulty.getOpenId());
		}
		
		if(Util.isNotNull(difficulty.getOrganizationId())){
			hql += " and tu.organizationId = ? ";
			values.add(difficulty.getOrganizationId());
		}
		return hql;
	}
	
	/**
	 * 统计符合当前查询条件的总记录数
	 * 
	 * @param difficulty
	 * @return
	 */
	private Long total(Difficulty difficulty) {
		String hql = "select count(*) from Tdifficulty t , TweixinUser tu, Torganization torg where t.openId = tu.openId and tu.organizationId = torg.id ";
		List<Object> params = new ArrayList<Object>();
		hql = addWhere(difficulty, hql, params);
		return difficultyDao.count(hql, params);
	}

}

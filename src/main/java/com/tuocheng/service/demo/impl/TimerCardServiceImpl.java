package com.tuocheng.service.demo.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.TtimerCard;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.TimerCard;
import com.tuocheng.service.demo.TimerCardServiceI;

/**
 * 计时卡service实现类
 * 
 * @author 李杰
 * 
 */
@Service("timerCardService")
public class TimerCardServiceImpl implements TimerCardServiceI {

	private BaseDaoI<TtimerCard> timerCardDao;

	// 注入DAO
	@Autowired
	public void setTimerCardDao(BaseDaoI<TtimerCard> timerCardDao) {
		this.timerCardDao = timerCardDao;
	}
	
	@Override
	public TimerCard save(TimerCard timerCard) {
		// 1.数据模型转换
		TtimerCard t = new TtimerCard();
		BeanUtils.copyProperties(timerCard, t);
		// 2.设置相关属性
		t.setId(UUID.randomUUID().toString());
		// 3.保存数据
		timerCardDao.save(t);
		// 4.返回结果集
		BeanUtils.copyProperties(t, timerCard);
		return timerCard;
	}

	@Override
	public void delete(String ids) {
		// 1.判断参数是否为空
		if (ids != null) {
			// 2.对参数的字符串进行分割
			for (String id : ids.split(",")) {
				if (!id.equals("0")) {
					// 3.根据ID从数据库中查找数据
					TtimerCard t = timerCardDao.get(TtimerCard.class, id);
					// 4.调用DAO层删除数据
					timerCardDao.delete(t);
				}
			}
		}
	}

	@Override
	public TimerCard update(TimerCard timerCard) {
		// 1.数据模型转换
		TtimerCard t = new TtimerCard();
		BeanUtils.copyProperties(timerCard, t, new String[] { "id" });
		// 2.设置相关属性
		t.setId(timerCard.getId());
		// 3.更新数据
		timerCardDao.update(t);
		// 4.返回结果集
		BeanUtils.copyProperties(t, timerCard);
		return timerCard;
	}

	@Override
	public DataGrid dataGrid(TimerCard timerCard) {
		DataGrid dataGrid = new DataGrid();
		//1.从数据库中查找出所有实体类信息
		//2.查找出的信息进行数据模型转换
		//3.设置展现数据
		dataGrid.setRows(this.changeModel(this.find(timerCard)));
		dataGrid.setTotal(this.total(timerCard));
		return dataGrid;
	}

	/**
	 * 从数据库存中查找出该实体信息并将此实体类信息转换成action专用的实体类信息，即模型转换
	 * 
	 * @param ts
	 * @return
	 */
	private List<TimerCard> changeModel(List<TtimerCard> ts) {
		List<TimerCard> retTimerCards = new ArrayList<TimerCard>();
		if (ts != null && ts.size() > 0) {
			for (TtimerCard t : ts) {
				TimerCard timerCard = new TimerCard();
				BeanUtils.copyProperties(t, timerCard);
				retTimerCards.add(timerCard);
			}
		}
		return retTimerCards;
	}

	/**
	 * 根据action专用实体类的信息查询所有dao专用的实体类信息
	 * 
	 * @param timerCard
	 * @return
	 */
	private List<TtimerCard> find(TimerCard timerCard) {
		String hql = " from TtimerCard t where 1=1 ";
		List<Object> params = new ArrayList<Object>();

		if (timerCard.getSort() != null && timerCard.getOrder() != null) {
			hql += " order by " + timerCard.getSort() + " " + timerCard.getOrder();
		}

		return timerCardDao.find(hql, params, timerCard.getPage(), timerCard.getRows());
	}

	/**
	 * 统计符合当前查询条件的总记录数
	 * 
	 * @param timerCard
	 * @return
	 */
	private Long total(TimerCard timerCard) {
		String hql = "select count(*) from TtimerCard t where 1=1";
		List<Object> params = new ArrayList<Object>();
		return timerCardDao.count(hql, params);
	}

	

}

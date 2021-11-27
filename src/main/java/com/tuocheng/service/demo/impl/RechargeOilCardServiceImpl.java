package com.tuocheng.service.demo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.ToilCard;
import com.tuocheng.model.demo.TrechargeOilCard;
import com.tuocheng.model.demo.Torganization;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.RechargeOilCard;
import com.tuocheng.service.demo.RechargeOilCardServiceI;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 油卡充值明细实体类service实现类
 * 
 * @author MEI702
 * 
 */
@Service("rechargeOilCardService")
public class RechargeOilCardServiceImpl implements RechargeOilCardServiceI {

	private BaseDaoI<TrechargeOilCard> rechargeOilcardDao;
	private BaseDaoI<ToilCard> oilcardDao;
	private BaseDaoI<Torganization> organizationDao;

	@Autowired
	public void setRechargeOilcardDao(
			BaseDaoI<TrechargeOilCard> rechargeOilcardDao) {
		this.rechargeOilcardDao = rechargeOilcardDao;
	}

	@Autowired
	public void setOilcardDao(BaseDaoI<ToilCard> oilcardDao) {
		this.oilcardDao = oilcardDao;
	}

	@Autowired
	public void setOrganizationDao(BaseDaoI<Torganization> organizationDao) {
		this.organizationDao = organizationDao;
	}

	@Override
	public RechargeOilCard save(RechargeOilCard rechargeOilcard) {
		// 1.数据模型转换
		TrechargeOilCard entry = new TrechargeOilCard();
		BeanUtils.copyProperties(rechargeOilcard, entry, new String[] { "id" });
		entry.setId(UUID.randomUUID().toString());
		// 修改卡的关联信息
		if (ValidateUtil.isValid(rechargeOilcard.getOilCardId())) {
			ToilCard oilCard = oilcardDao.get(ToilCard.class,
					rechargeOilcard.getOilCardId());
			if (oilCard != null) {
				// 修改余额
				oilCard.setBalance(rechargeOilcard.getAfterBalance());
				// 修改充值日期
				oilCard.setPrevateDate(rechargeOilcard.getRechargeDate());
				oilcardDao.update(oilCard);
				entry.setOilCard(oilCard);
			}
		}
		// 2.保存数据
		rechargeOilcardDao.save(entry);
		// 3.将保存后数据模型转换
		BeanUtils.copyProperties(entry, rechargeOilcard);
		// 4.将转换后的数据返回给前台
		return rechargeOilcard;
	}

	@Override
	public RechargeOilCard udpate(RechargeOilCard oilcard) {
		// 1.数据模型转换
		TrechargeOilCard entry = new TrechargeOilCard();
		BeanUtils.copyProperties(oilcard, entry, new String[] { "id" });
		entry.setId(UUID.randomUUID().toString());

		// 2.保存数据
		rechargeOilcardDao.update(entry);
		// 3.将保存后数据模型转换
		BeanUtils.copyProperties(entry, oilcard);
		// 4.将转换后的数据返回给前台
		return oilcard;
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
					TrechargeOilCard rechargeOilcard = rechargeOilcardDao.get(
							TrechargeOilCard.class, id);
					// 4.调用DAO层删除数据
					rechargeOilcardDao.delete(rechargeOilcard);
				}
			}

		}

	}

	@Override
	public DataGrid dataGrid(RechargeOilCard rechargeOilcard) {
		DataGrid dataGrid = new DataGrid();
		// 设置返回前台的数据
		dataGrid.setRows(this.changedModel(this.find(rechargeOilcard)));
		// 设置总记录数
		dataGrid.setTotal(this.total(rechargeOilcard));
		return dataGrid;
	}

	/**
	 * 从数据库存中查找出该实体信息并将此实体类信息转换成action专用的实体类信息，即模型转换
	 * 
	 * @param students
	 * @return
	 */
	private List<RechargeOilCard> changedModel(
			List<TrechargeOilCard> oilCardUsingList) {
		List<RechargeOilCard> retOilcards = new ArrayList<RechargeOilCard>();
		if (oilCardUsingList != null && oilCardUsingList.size() > 0) {
			RechargeOilCard entry = null;
			for (TrechargeOilCard rechargeOilcard : oilCardUsingList) {
				entry = new RechargeOilCard();
				BeanUtils.copyProperties(rechargeOilcard, entry);
				entry.setSchoolAreaName(organizationDao.get(
						Torganization.class, rechargeOilcard.getSchoolArea())
						.getName());
				if (rechargeOilcard.getOilCard() != null) {
					entry.setOilCardId(rechargeOilcard.getOilCard().getId());
					entry.setOilCardNo(rechargeOilcard.getOilCard().getCardNo());
				}
				retOilcards.add(entry);
			}
		}
		return retOilcards;
	}

	/**
	 * 根据action专用实体类的信息查询所有dao专用的实体类信息
	 * 
	 * @param student
	 * @return
	 */
	private List<TrechargeOilCard> find(RechargeOilCard oilcard) {
		String hql = "from TrechargeOilCard t where 1=1";
		List<Object> paramList = new ArrayList<Object>();
		hql = this.addWhere(oilcard, hql, paramList);
		if (oilcard.getSort() != null && oilcard.getOrder() != null) {
			hql += " order by " + oilcard.getSort() + " " + oilcard.getOrder();
		}
		return rechargeOilcardDao.find(hql, paramList, oilcard.getPage(),
				oilcard.getRows());
	}

	/**
	 * 统计符合当前查询条件的总记录数
	 * 
	 * @param student
	 * @return
	 */
	private Long total(RechargeOilCard person) {
		// 拼接查询条件
		String hql = "select count(*) from TrechargeOilCard t where 1=1 ";
		List<Object> paramsList = new ArrayList<Object>();
		hql = this.addWhere(person, hql, paramsList);
		return rechargeOilcardDao.count(hql, paramsList);
	}

	/**
	 * 添加查询约束条件
	 * 
	 * @param rechargeOilCard
	 * @param hql
	 * @param params
	 * @return
	 */
	private String addWhere(RechargeOilCard rechargeOilCard, String hql,
			List<Object> params) {
		// 校区精确查询
		if (ValidateUtil.isValid(rechargeOilCard.getSchoolArea())) {
			hql += " and t.schoolArea = ?";
			params.add(rechargeOilCard.getSchoolArea().trim());
		}
		// 充值日期范围查询
		if (ValidateUtil.isValid(rechargeOilCard.getRechargeDateStart())) {
			hql += " and t.rechargeDate >=?";
			params.add(rechargeOilCard.getRechargeDateStart());
		}
		if (ValidateUtil.isValid(rechargeOilCard.getRechargeDateEnd())) {
			hql += " and t.rechargeDate <=?";
			params.add(rechargeOilCard.getRechargeDateEnd());
		}
		// 卡号查询
		if (ValidateUtil.isValid(rechargeOilCard.getOilCardNo())) {
			hql += " and t.oilCard.cardNo =?";
			params.add(rechargeOilCard.getOilCardNo().trim());
		}
		if (ValidateUtil.isValid(rechargeOilCard.getOilCardId())) {
			hql += " and t.oilCard.id =?";
			params.add(rechargeOilCard.getOilCardId().trim());
		}

		return hql;
	}

}

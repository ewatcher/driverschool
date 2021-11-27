package com.tuocheng.service.demo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.Torganization;
import com.tuocheng.model.demo.ToilCard;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.OilCard;
import com.tuocheng.service.demo.OilCardServiceI;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 油卡信息管理实体类service实现类
 * 
 * @author MEI702
 * 
 */
@Service("oilCardService")
public class OilCardServiceImpl implements OilCardServiceI {

	private BaseDaoI<ToilCard> oilcardDao;
	private BaseDaoI<Torganization> organizationDao;

	@Autowired
	public void setOilcardDao(BaseDaoI<ToilCard> oilcardDao) {
		this.oilcardDao = oilcardDao;
	}

	@Autowired
	public void setOrganizationDao(BaseDaoI<Torganization> organizationDao) {
		this.organizationDao = organizationDao;
	}

	@Override
	public OilCard save(OilCard oilcard) {
		// 1.数据模型转换
		ToilCard entry = new ToilCard();
		BeanUtils.copyProperties(oilcard, entry, new String[] { "id" });
		entry.setId(UUID.randomUUID().toString());
		// 默认配置
		if (oilcard.getCreateTime() == null) {
			entry.setCreateTime(new Date());
		}
		entry.setState(ToilCard.OilCardState_NOMAL);
		entry.setBalance(0.00);

		// 2.保存数据
		oilcardDao.save(entry);
		// 3.将保存后数据模型转换
		BeanUtils.copyProperties(entry, oilcard);
		// 4.将转换后的数据返回给前台
		return oilcard;
	}

	@Override
	public OilCard udpate(OilCard oilcard) {
		// 1.数据模型转换
		ToilCard entry = new ToilCard();
		BeanUtils.copyProperties(oilcard, entry, new String[] { "id" });
		entry.setId(oilcard.getId());
		// 默认配置
		if (oilcard.getCreateTime() == null) {
			entry.setCreateTime(new Date());
		}

		// 2.保存数据
		oilcardDao.update(entry);
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
					ToilCard oilcard = oilcardDao.get(ToilCard.class, id);
					// 4.调用DAO层删除数据
					oilcardDao.delete(oilcard);
				}
			}

		}

	}

	@Override
	public DataGrid dataGrid(OilCard oilcard) {
		DataGrid dataGrid = new DataGrid();
		// 设置返回前台的数据
		dataGrid.setRows(this.changedModel(this.find(oilcard)));
		// 设置总记录数
		dataGrid.setTotal(this.total(oilcard));
		return dataGrid;
	}

	/**
	 * 从数据库存中查找出该实体信息并将此实体类信息转换成action专用的实体类信息，即模型转换
	 * 
	 * @param students
	 * @return
	 */
	private List<OilCard> changedModel(List<ToilCard> oilCardList) {
		List<OilCard> retOilcards = new ArrayList<OilCard>();
		if (oilCardList != null && oilCardList.size() > 0) {
			OilCard entry = null;
			for (ToilCard oilcar : oilCardList) {
				entry = new OilCard();
				BeanUtils.copyProperties(oilcar, entry);
				entry.setSchoolAreaName(organizationDao.get(
						Torganization.class, oilcar.getSchoolArea()).getName());
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
	private List<ToilCard> find(OilCard oilcard) {
		String hql = "from ToilCard t where 1=1";
		List<Object> paramList = new ArrayList<Object>();
		hql = this.addWhere(oilcard, hql, paramList);
		if (oilcard.getSort() != null && oilcard.getOrder() != null) {
			hql += " order by " + oilcard.getSort() + " " + oilcard.getOrder();
		}
		return oilcardDao.find(hql, paramList, oilcard.getPage(),
				oilcard.getRows());
	}

	/**
	 * 统计符合当前查询条件的总记录数
	 * 
	 * @param student
	 * @return
	 */
	private Long total(OilCard person) {
		// 拼接查询条件
		String hql = "select count(*) from ToilCard t where 1=1 ";
		List<Object> paramsList = new ArrayList<Object>();
		hql = this.addWhere(person, hql, paramsList);
		return oilcardDao.count(hql, paramsList);
	}

	/**
	 * 添加查询约束条件
	 * 
	 * @param oilcard
	 * @param hql
	 * @param params
	 * @return
	 */
	private String addWhere(OilCard oilcard, String hql, List<Object> params) {
		// 校区精确查询
		if (ValidateUtil.isValid(oilcard.getSchoolArea())) {
			hql += " and t.schoolArea = ?";
			params.add(oilcard.getSchoolArea().trim());
		}
		// 卡号精确查询
		if (ValidateUtil.isValid(oilcard.getCardNo())) {
			hql += " and t.cardNo =?";
			params.add(oilcard.getCardNo().trim());
		}
		if (ValidateUtil.isValid(oilcard.getState())) {
			hql += " and t.state =?";
			params.add(oilcard.getState());
		}
		// 范围查询
		if (ValidateUtil.isValid(oilcard.getCreateTimeStart())) {
			hql += " and t.createTime >=?";
			params.add(oilcard.getCreateTimeStart());
		}
		if (ValidateUtil.isValid(oilcard.getCreateTimeEnd())) {
			hql += " and t.createTime <=?";
			params.add(oilcard.getCreateTimeEnd());
		}
		return hql;
	}

	public Double getLatelyBalanceByOilcardId(String id) throws Exception{
		if(!ValidateUtil.isValid(id)){
			return null;
		}
		ToilCard oilCard=oilcardDao.get(ToilCard.class, id);
		if(oilCard!=null){
			return oilCard.getBalance();
		}
		return null;
	}
}

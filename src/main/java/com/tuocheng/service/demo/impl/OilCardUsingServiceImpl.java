package com.tuocheng.service.demo.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.ToilCard;
import com.tuocheng.model.demo.ToilCardUsing;
import com.tuocheng.model.demo.Torganization;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.OilCardUsing;
import com.tuocheng.service.demo.OilCardUsingServiceI;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 油卡使用明细实体类service实现类
 * 
 * @author MEI702
 * 
 */
@Service("oilCardUsingService")
public class OilCardUsingServiceImpl implements OilCardUsingServiceI {

	private BaseDaoI<ToilCardUsing> oilcardUsingDao;
	private BaseDaoI<ToilCard> oilcardDao;
	private BaseDaoI<Torganization> organizationDao;

	@Autowired
	public void setOilcardUsingDao(BaseDaoI<ToilCardUsing> oilcardUsingDao) {
		this.oilcardUsingDao = oilcardUsingDao;
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
	public OilCardUsing save(OilCardUsing oilcardUsing) {
		// 1.数据模型转换
		ToilCardUsing entry = new ToilCardUsing();
		BeanUtils.copyProperties(oilcardUsing, entry, new String[] { "id" });
		entry.setId(UUID.randomUUID().toString());
		//修改卡的余额
		if(ValidateUtil.isValid(oilcardUsing.getOilCardId())){
			ToilCard oilCard=oilcardDao.get(ToilCard.class, oilcardUsing.getOilCardId());
			if(oilCard!=null){
				oilCard.setBalance(oilCard.getBalance()-oilcardUsing.getMoneyTotal());
				oilcardDao.update(oilCard);
				entry.setOilCard(oilCard);
			}
		}
		// 2.保存数据
		oilcardUsingDao.save(entry);
		// 3.将保存后数据模型转换
		BeanUtils.copyProperties(entry, oilcardUsing);
		// 4.将转换后的数据返回给前台
		return oilcardUsing;
	}

	@Override
	public OilCardUsing udpate(OilCardUsing oilcard) {
		// 1.数据模型转换
		ToilCardUsing entry = new ToilCardUsing();
		BeanUtils.copyProperties(oilcard, entry, new String[] { "id" });
		entry.setId(UUID.randomUUID().toString());

		// 2.保存数据
		oilcardUsingDao.update(entry);
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
					ToilCardUsing oilcardUsing = oilcardUsingDao.get(
							ToilCardUsing.class, id);
					// 4.调用DAO层删除数据
					oilcardUsingDao.delete(oilcardUsing);
				}
			}

		}

	}

	@Override
	public DataGrid dataGrid(OilCardUsing oilcardUsing) {
		DataGrid dataGrid = new DataGrid();
		// 设置返回前台的数据
		dataGrid.setRows(this.changedModel(this.find(oilcardUsing)));
		// 设置总记录数
		dataGrid.setTotal(this.total(oilcardUsing));
		return dataGrid;
	}

	/**
	 * 从数据库存中查找出该实体信息并将此实体类信息转换成action专用的实体类信息，即模型转换
	 * 
	 * @param students
	 * @return
	 */
	private List<OilCardUsing> changedModel(List<ToilCardUsing> oilCardUsingList) {
		List<OilCardUsing> retOilcards = new ArrayList<OilCardUsing>();
		if (oilCardUsingList != null && oilCardUsingList.size() > 0) {
			OilCardUsing entry = null;
			ToilCard oilcard=null;
			for (ToilCardUsing oilcardUsing : oilCardUsingList) {
				entry = new OilCardUsing();
				BeanUtils.copyProperties(oilcardUsing, entry);
				entry.setSchoolAreaName(organizationDao.get(
						Torganization.class, oilcardUsing.getSchoolArea()).getName());
				if(oilcardUsing.getOilCard()!=null){
					entry.setOilCardId(oilcardUsing.getOilCard().getId());
					entry.setOilCardNo(oilcardUsing.getOilCard().getCardNo());
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
	private List<ToilCardUsing> find(OilCardUsing oilcard) {
		String hql = "from ToilCardUsing t where 1=1";
		List<Object> paramList = new ArrayList<Object>();
		hql = this.addWhere(oilcard, hql, paramList);
		if (oilcard.getSort() != null && oilcard.getOrder() != null) {
			hql += " order by " + oilcard.getSort() + " " + oilcard.getOrder();
		}
		return oilcardUsingDao.find(hql, paramList, oilcard.getPage(),
				oilcard.getRows());
	}

	/**
	 * 统计符合当前查询条件的总记录数
	 * 
	 * @param student
	 * @return
	 */
	private Long total(OilCardUsing person) {
		// 拼接查询条件
		String hql = "select count(*) from ToilCardUsing t where 1=1 ";
		List<Object> paramsList = new ArrayList<Object>();
		hql = this.addWhere(person, hql, paramsList);
		return oilcardUsingDao.count(hql, paramsList);
	}

	/**
	 * 添加查询约束条件
	 * 
	 * @param oilcardUsing
	 * @param hql
	 * @param params
	 * @return
	 */
	private String addWhere(OilCardUsing oilcardUsing, String hql,
			List<Object> params) {
		// 校区精确查询
		if (ValidateUtil.isValid(oilcardUsing.getSchoolArea())) {
			hql += " and t.schoolArea = ?";
			params.add(oilcardUsing.getSchoolArea().trim());
		}
		// 车辆精确查询
		if (ValidateUtil.isValid(oilcardUsing.getCarNo())) {
			hql += " and t.carNo =?";
			params.add(oilcardUsing.getCarNo().trim());
		}
		//教练用油查询
		if(ValidateUtil.isValid(oilcardUsing.getUsingPerson())){
			hql += " and t.usingPerson =?";
			params.add(oilcardUsing.getUsingPerson().trim());
		}
		// 范围查询
		if (ValidateUtil.isValid(oilcardUsing.getUsingDateStart())) {
			hql += " and t.usingDate >=?";
			params.add(oilcardUsing.getUsingDateStart());
		}
		if (ValidateUtil.isValid(oilcardUsing.getUsingDateEnd())) {
			hql += " and t.usingDate <=?";
			params.add(oilcardUsing.getUsingDateEnd());
		}
		// 卡号查询
		if (ValidateUtil.isValid(oilcardUsing.getOilCardNo())) {
			hql += " and t.oilCard.cardNo =?";
			params.add(oilcardUsing.getOilCardNo());
		}
		if (ValidateUtil.isValid(oilcardUsing.getOilCardId())) {
			hql += " and t.oilCard.id =?";
			params.add(oilcardUsing.getOilCardId());
		}
		// 油号查询
		if (ValidateUtil.isValid(oilcardUsing.getOilType())) {
			hql += " and t.oilType =?";
			params.add(oilcardUsing.getOilType());
		}
		return hql;
	}

}

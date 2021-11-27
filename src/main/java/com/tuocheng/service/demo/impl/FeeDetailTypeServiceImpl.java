package com.tuocheng.service.demo.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.Torganization;
import com.tuocheng.model.demo.Tstudent;
import com.tuocheng.model.demo.TfeeDetailType;
import com.tuocheng.pageModel.base.ComboboxJson;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.demo.FeeDetailType;
import com.tuocheng.service.demo.FeeDetailTypeServiceI;
import com.tuocheng.util.base.StringUtil;
import com.tuocheng.util.base.Util;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 收费类别实体实体类service实现类
 * 
 * @author MEI702
 * 
 */
@Service("feeDetailTypeService")
public class FeeDetailTypeServiceImpl implements FeeDetailTypeServiceI {

	private BaseDaoI<TfeeDetailType> feeDetailTypeDao;
	private BaseDaoI<Torganization> organizationDao;

	@Autowired
	public void setFeeDetailTypeDao(BaseDaoI<TfeeDetailType> feeDetailTypeDao) {
		this.feeDetailTypeDao = feeDetailTypeDao;
	}

	@Autowired
	public void setOrganizationDao(BaseDaoI<Torganization> organizationDao) {
		this.organizationDao = organizationDao;
	}

	@Override
	public FeeDetailType save(FeeDetailType feeDetailType) {
		// 1.数据模型转换
		TfeeDetailType entry = new TfeeDetailType();
		BeanUtils.copyProperties(feeDetailType, entry, new String[] { "id" });
		entry.setId(UUID.randomUUID().toString());
		// 2.保存数据
		feeDetailTypeDao.save(entry);
		// 3.将保存后数据模型转换
		BeanUtils.copyProperties(entry, feeDetailType);
		// 4.将转换后的数据返回给前台
		return feeDetailType;

	}

	@Override
	public FeeDetailType udpate(FeeDetailType feeDetailType) {
		// 1.数据模型转换
		TfeeDetailType t = new TfeeDetailType();
		BeanUtils.copyProperties(feeDetailType, t, new String[] { "id" });
		t.setId(feeDetailType.getId());
		// 2.保存数据
		feeDetailTypeDao.update(t);
		// 3.将保存后数据模型转换
		BeanUtils.copyProperties(t, feeDetailType);
		// 4.将转换后的数据返回给前台
		return feeDetailType;
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
					TfeeDetailType feeDetailType = feeDetailTypeDao.get(TfeeDetailType.class, id);
					// 4.调用DAO层删除数据
					feeDetailTypeDao.delete(feeDetailType);
				}
			}

		}

	}

	@Override
	public DataGrid dataGrid(FeeDetailType feeDetailType) {
		DataGrid dataGrid = new DataGrid();
		// 设置返回前台的数据
		dataGrid.setRows(this.changedModel(this.find(feeDetailType)));
		// 设置总记录数
		dataGrid.setTotal(this.total(feeDetailType));
		return dataGrid;
	}

	/**
	 * 从数据库存中查找出该实体信息并将此实体类信息转换成action专用的实体类信息，即模型转换
	 * 
	 * @param students
	 * @return
	 */
	private List<FeeDetailType> changedModel(List<TfeeDetailType> feeDetailTypeList) {
		List<FeeDetailType> retVals = new ArrayList<FeeDetailType>();
		if (feeDetailTypeList != null && feeDetailTypeList.size() > 0) {
			for (TfeeDetailType temp : feeDetailTypeList) {
				FeeDetailType entry = new FeeDetailType();
				entry.setSchoolAreaName(organizationDao.get(Torganization.class, temp.getSchoolArea()).getName());
				BeanUtils.copyProperties(temp, entry);
				retVals.add(entry);
			}
		}
		return retVals;
	}

	/**
	 * 根据action专用实体类的信息查询所有dao专用的实体类信息
	 * 
	 * @param student
	 * @return
	 */
	private List<TfeeDetailType> find(FeeDetailType feeDetailType) {
		String hql = "from TfeeDetailType t where 1=1";
		List<Object> paramList = new ArrayList<Object>();
		hql = this.addWhere(feeDetailType, hql, paramList);
		if (feeDetailType.getSort() != null && feeDetailType.getOrder() != null) {
			hql += " order by " + feeDetailType.getSort() + " " + feeDetailType.getOrder();
		}
		return feeDetailTypeDao.find(hql, paramList, feeDetailType.getPage(), feeDetailType.getRows());
	}

	/**
	 * 统计符合当前查询条件的总记录数
	 * 
	 * @param student
	 * @return
	 */
	private Long total(FeeDetailType feeDetailType) {
		// 拼接查询条件
		String hql = "select count(*) from TfeeDetailType t where 1=1 ";
		List<Object> paramsList = new ArrayList<Object>();
		hql = this.addWhere(feeDetailType, hql, paramsList);
		return feeDetailTypeDao.count(hql, paramsList);
	}

	/**
	 * 添加查询约束条件
	 * 
	 * @param feeDetailType
	 * @param hql
	 * @param params
	 * @return
	 */
	private String addWhere(FeeDetailType feeDetailType, String hql, List<Object> params) {
		// 姓名模糊查询
		if (ValidateUtil.isValid(feeDetailType.getItemName())) {
			hql += " and t.itemName = ?";
			params.add(feeDetailType.getItemName().trim());
		}
		// 所属部门精确查询
		if (ValidateUtil.isValid(feeDetailType.getSchoolArea())) {
			hql += " and t.schoolArea =?";
			params.add(feeDetailType.getSchoolArea().trim());
		}
		// 日期
		if (ValidateUtil.isValid(feeDetailType.getCreateTimeStart())) {
			hql += " and t.createTimeStart >=?";
			params.add(feeDetailType.getCreateTimeStart());
		}
		if (ValidateUtil.isValid(feeDetailType.getCreateTimeEnd())) {
			hql += " and t.createTimeEnd <=?";
			params.add(feeDetailType.getCreateTimeEnd());
		}
		// 操作者
		if (ValidateUtil.isValid(feeDetailType.getOperator())) {
			hql += " and t.operator =?";
			params.add(feeDetailType.getOperator().trim());
		}

		return hql;
	}

	public Integer getOrderNoBySchoolArea(String schoolArea) throws Exception {
		String sql = "select * from tb_feeDetailTypes where schoolArea=?  order by orderNo desc";
		List<TfeeDetailType> retList = feeDetailTypeDao.findAllBySQL(TfeeDetailType.class, sql, schoolArea);
		Integer orderNo = 0;
		if (ValidateUtil.isValidListObject(retList)) {
			orderNo = retList.get(0).getOrderNo();
		}
		// 2.2系统不存在当前相关记录，
		return orderNo + 1;
	}

	@Override
	public List<ComboboxJson> getAllFeeDetailType(String schoolArea) throws Exception {
		// 1.参数校验
		if (!ValidateUtil.isValid(schoolArea)) {
			return null;
		}
		// 2.获取数数据，并做模型转换
		String sql = "select * from tb_feeDetailTypes where schoolArea=? order by orderNo asc";
		List<TfeeDetailType> retLists = feeDetailTypeDao.findAllBySQL(TfeeDetailType.class, sql, schoolArea);
		List<ComboboxJson> rets = new ArrayList<ComboboxJson>();
		if (ValidateUtil.isValidListObject(retLists)) {
			ComboboxJson json = null;
			for (TfeeDetailType entry : retLists) {
				json = new ComboboxJson();
				json.setValue(entry.getId());
				json.setText(entry.getItemName());
				rets.add(json);
			}
		}
		// 3.返回结果集
		if (ValidateUtil.isValidListObject(rets)) {
			return rets;
		}
		return null;
	}

	public Double getMoneyById(String schoolArea, String id) throws Exception{
		// 1.参数校验
		if (!ValidateUtil.isValid(schoolArea) || !ValidateUtil.isValid(id)) {
			return null;
		}
		// 2.获取数数据，并做模型转换
		String sql = "select * from tb_feeDetailTypes where schoolArea=? and id=?";
		TfeeDetailType entry = feeDetailTypeDao.getSingleBySQL(TfeeDetailType.class, sql, schoolArea, id);
		// 3.返回结果集
		if (null != entry) {
			return entry.getMoney();
		}
		return null;

	}

}

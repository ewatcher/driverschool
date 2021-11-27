package com.tuocheng.service.demo.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.Torganization;
import com.tuocheng.model.demo.Tstudent;
import com.tuocheng.model.demo.TcollectFee;
import com.tuocheng.model.demo.TfeeDetailType;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.CollectFee;
import com.tuocheng.service.demo.CollectFeeServiceI;
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
@Service("collectFeeService")
public class CollectFeeServiceImpl implements CollectFeeServiceI {

	private BaseDaoI<TfeeDetailType> feeDetailTypeDao;
	private BaseDaoI<Torganization> organizationDao;
	private BaseDaoI<TcollectFee> collectFeeDao;
	private BaseDaoI<Tstudent> studentDao;

	@Autowired
	public void setStudentDao(BaseDaoI<Tstudent> studentDao) {
		this.studentDao = studentDao;
	}

	@Autowired
	public void setCollectFeeDao(BaseDaoI<TcollectFee> collectFeeDao) {
		this.collectFeeDao = collectFeeDao;
	}

	@Autowired
	public void setFeeDetailTypeDao(BaseDaoI<TfeeDetailType> feeDetailTypeDao) {
		this.feeDetailTypeDao = feeDetailTypeDao;
	}

	@Autowired
	public void setOrganizationDao(BaseDaoI<Torganization> organizationDao) {
		this.organizationDao = organizationDao;
	}

	@Override
	public CollectFee save(CollectFee collectFee) {
		// 1.数据模型转换
		TcollectFee entry = new TcollectFee();
		BeanUtils.copyProperties(collectFee, entry, new String[] { "id" });
		entry.setId(UUID.randomUUID().toString());
		entry.setCreateDate(new Date());
		// 2.保存数据
		collectFeeDao.save(entry);
		// 3.将保存后数据模型转换
		BeanUtils.copyProperties(entry, collectFee);
		// 4.将转换后的数据返回给前台
		return collectFee;

	}

	@Override
	public CollectFee udpate(CollectFee collectFee) {
		// 1.数据模型转换
		TcollectFee t = new TcollectFee();
		BeanUtils.copyProperties(collectFee, t, new String[] { "id" });
		t.setId(collectFee.getId());
		// 2.保存数据
		collectFeeDao.update(t);
		// 3.将保存后数据模型转换
		BeanUtils.copyProperties(t, collectFee);
		// 4.将转换后的数据返回给前台
		return collectFee;
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
					TcollectFee collectFee = collectFeeDao.get(
							TcollectFee.class, id);
					// 4.调用DAO层删除数据
					collectFeeDao.delete(collectFee);
				}
			}

		}

	}

	@Override
	public DataGrid dataGrid(CollectFee collectFee) {
		DataGrid dataGrid = new DataGrid();
		// 设置返回前台的数据
		dataGrid.setRows(this.changedModel(this.find(collectFee)));
		// 设置总记录数
		dataGrid.setTotal(this.total(collectFee));
		return dataGrid;
	}

	/**
	 * 从数据库存中查找出该实体信息并将此实体类信息转换成action专用的实体类信息，即模型转换
	 * 
	 * @param students
	 * @return
	 */
	private List<CollectFee> changedModel(List<TcollectFee> collectFeeList) {
		List<CollectFee> retVals = new ArrayList<CollectFee>();
		if (collectFeeList != null && collectFeeList.size() > 0) {
			// TODO(交费人员类型，1：学员，只做学员，教练，职员后期处理)
			Tstudent student = null;
			for (TcollectFee temp : collectFeeList) {
				CollectFee entry = new CollectFee();
				entry.setSchoolAreaName(organizationDao.get(
						Torganization.class, temp.getSchoolArea()).getName());
				// 初始化交费人信息
				if (ValidateUtil.isValid(temp.getHandupMan())) {
					student = studentDao.get(Tstudent.class,
							temp.getHandupMan());
				}
				if (null != student) {
					entry.setHandupManName(student.getName());
					entry.setHandupManIdentity(student.getIdentityId());
					entry.setHandupManPhone(student.getPhone());
					entry.setStudentDriverType(student.getDriverType());
					entry.setHandupManNo(student.getStudentNo());
					entry.setStudentSex(student.getSex());
					entry.setApplyType(student.getApplyType());
					entry.setQualificationFlag(student.getQualificationFlag());
				}
				// 初始化收费项目名称
				if (ValidateUtil.isValid(temp.getFeeDetailType())) {
					TfeeDetailType feedDetailType = feeDetailTypeDao.get(
							TfeeDetailType.class, temp.getFeeDetailType());
					if (null != feedDetailType) {
						entry.setFeeDetailTypeName(feedDetailType.getItemName());
					}
				}
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
	private List<TcollectFee> find(CollectFee collectFee) {
		String hql = "from TcollectFee t where 1=1";
		List<Object> paramList = new ArrayList<Object>();
		hql = this.addWhere(collectFee, hql, paramList);
		if (collectFee.getSort() != null && collectFee.getOrder() != null) {
			hql += " order by " + collectFee.getSort() + " "
					+ collectFee.getOrder();
		}
		return collectFeeDao.find(hql, paramList, collectFee.getPage(),
				collectFee.getRows());
	}

	/**
	 * 统计符合当前查询条件的总记录数
	 * 
	 * @param student
	 * @return
	 */
	private Long total(CollectFee collectFee) {
		// 拼接查询条件
		String hql = "select count(*) from TcollectFee t where 1=1 ";
		List<Object> paramsList = new ArrayList<Object>();
		hql = this.addWhere(collectFee, hql, paramsList);
		return collectFeeDao.count(hql, paramsList);
	}

	/**
	 * 添加查询约束条件
	 * 
	 * @param collectFee
	 * @param hql
	 * @param params
	 * @return
	 */
	private String addWhere(CollectFee collectFee, String hql,
			List<Object> params) {
		// 交费人
		if (ValidateUtil.isValid(collectFee.getHandupMan())) {
			hql += " and t.handupMan like ?";
			params.add("%%" + collectFee.getHandupMan().trim() + "%%");
		}
		// 交费人类型
		if (ValidateUtil.isValid(collectFee.getHandupManType())) {
			hql += " and t.handupManType =?";
			params.add(collectFee.getHandupManType());
		}
		// 收费项目查询
		if (ValidateUtil.isValid(collectFee.getFeeDetailType())) {
			hql += " and t.feeDetailType =?";
			params.add(collectFee.getFeeDetailType());
		}
		// 所属部门精确查询
		if (ValidateUtil.isValid(collectFee.getSchoolArea())) {
			hql += " and t.schoolArea =?";
			params.add(collectFee.getSchoolArea().trim());
		}
		// 日期
		if (ValidateUtil.isValid(collectFee.getCreateTimeStart())) {
			hql += " and t.createTimeStart >=?";
			params.add(collectFee.getCreateTimeStart());
		}
		if (ValidateUtil.isValid(collectFee.getCreateTimeEnd())) {
			hql += " and t.createTimeEnd <=?";
			params.add(collectFee.getCreateTimeEnd());
		}
		// 收费员
		if (ValidateUtil.isValid(collectFee.getOperator())) {
			hql += " and t.operator =?";
			params.add(collectFee.getOperator().trim());
		}

		return hql;
	}

}

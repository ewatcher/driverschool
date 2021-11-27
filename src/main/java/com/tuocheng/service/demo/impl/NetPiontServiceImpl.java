package com.tuocheng.service.demo.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.Torganization;
import com.tuocheng.model.demo.TnetPiont;
import com.tuocheng.pageModel.base.ComboboxJson;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.NetPiont;
import com.tuocheng.service.demo.NetPiontServiceI;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 收费类别实体实体类service实现类
 * 
 * @author MEI702
 * 
 */
@Service("netPiontService")
public class NetPiontServiceImpl implements NetPiontServiceI {

	private BaseDaoI<TnetPiont> netPiontDao;
	private BaseDaoI<Torganization> organizationDao;

	@Autowired
	public void setNetPiontDao(BaseDaoI<TnetPiont> netPiontDao) {
		this.netPiontDao = netPiontDao;
	}

	@Autowired
	public void setOrganizationDao(BaseDaoI<Torganization> organizationDao) {
		this.organizationDao = organizationDao;
	}

	@Override
	public NetPiont save(NetPiont netPiont) {
		// 1.数据模型转换
		TnetPiont entry = new TnetPiont();
		BeanUtils.copyProperties(netPiont, entry, new String[] { "id" });
		entry.setId(UUID.randomUUID().toString());
		// 2.保存数据
		netPiontDao.save(entry);
		// 3.将保存后数据模型转换
		BeanUtils.copyProperties(entry, netPiont);
		// 4.将转换后的数据返回给前台
		return netPiont;

	}

	@Override
	public NetPiont udpate(NetPiont netPiont) {
		// 1.数据模型转换
		TnetPiont t = new TnetPiont();
		BeanUtils.copyProperties(netPiont, t, new String[] { "id" });
		t.setId(netPiont.getId());
		// 2.保存数据
		netPiontDao.update(t);
		// 3.将保存后数据模型转换
		BeanUtils.copyProperties(t, netPiont);
		// 4.将转换后的数据返回给前台
		return netPiont;
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
					TnetPiont feeDetailType = netPiontDao.get(TnetPiont.class, id);
					// 4.调用DAO层删除数据
					netPiontDao.delete(feeDetailType);
				}
			}

		}

	}

	@Override
	public DataGrid dataGrid(NetPiont netPiont) {
		DataGrid dataGrid = new DataGrid();
		// 设置返回前台的数据
		dataGrid.setRows(this.changedModel(this.find(netPiont)));
		// 设置总记录数
		dataGrid.setTotal(this.total(netPiont));
		return dataGrid;
	}

	/**
	 * 从数据库存中查找出该实体信息并将此实体类信息转换成action专用的实体类信息，即模型转换
	 * 
	 * @param students
	 * @return
	 */
	private List<NetPiont> changedModel(List<TnetPiont> netPiontLists) {
		List<NetPiont> retVals = new ArrayList<NetPiont>();
		if (netPiontLists != null && netPiontLists.size() > 0) {
			for (TnetPiont temp : netPiontLists) {
				NetPiont entry = new NetPiont();
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
	private List<TnetPiont> find(NetPiont netPiont) {
		String hql = "from TnetPiont t where 1=1";
		List<Object> paramList = new ArrayList<Object>();
		hql = this.addWhere(netPiont, hql, paramList);
		if (netPiont.getSort() != null && netPiont.getOrder() != null) {
			hql += " order by " + netPiont.getSort() + " " + netPiont.getOrder();
		}
		return netPiontDao.find(hql, paramList, netPiont.getPage(), netPiont.getRows());
	}

	/**
	 * 统计符合当前查询条件的总记录数
	 * 
	 * @param student
	 * @return
	 */
	private Long total(NetPiont feeDetailType) {
		// 拼接查询条件
		String hql = "select count(*) from TnetPiont t where 1=1 ";
		List<Object> paramsList = new ArrayList<Object>();
		hql = this.addWhere(feeDetailType, hql, paramsList);
		return netPiontDao.count(hql, paramsList);
	}

	/**
	 * 添加查询约束条件
	 * 
	 * @param netPiont
	 * @param hql
	 * @param params
	 * @return
	 */
	private String addWhere(NetPiont netPiont, String hql, List<Object> params) {
		// 姓名模糊查询
		if (ValidateUtil.isValid(netPiont.getName())) {
			hql += " and t.name = ?";
			params.add(netPiont.getName().trim());
		}
		// 所属部门精确查询
		if (ValidateUtil.isValid(netPiont.getSchoolArea())) {
			hql += " and t.schoolArea =?";
			params.add(netPiont.getSchoolArea());
		}
		// 日期
		if (ValidateUtil.isValid(netPiont.getCreateTimeStart())) {
			hql += " and t.createTimeStart >=?";
			params.add(netPiont.getCreateTimeStart());
		}
		if (ValidateUtil.isValid(netPiont.getCreateTimeEnd())) {
			hql += " and t.createTimeEnd <=?";
			params.add(netPiont.getCreateTimeEnd());
		}
		// 操作者
		if (ValidateUtil.isValid(netPiont.getOperator())) {
			hql += " and t.operator =?";
			params.add(netPiont.getOperator().trim());
		}

		return hql;
	}

	public Integer getOrderNoBySchoolArea(String schoolArea) throws Exception {
		String sql = "select * from tb_netPionts where schoolArea=?  order by orderNo desc";
		List<TnetPiont> retList = netPiontDao.findAllBySQL(TnetPiont.class, sql, schoolArea);
		Integer orderNo = 0;
		if (ValidateUtil.isValidListObject(retList)) {
			orderNo = retList.get(0).getOrderNo();
		}
		// 2.2系统不存在当前相关记录，
		return orderNo + 1;
	}

	@Override
	public List<ComboboxJson> getMyNetPiont(String schoolArea) throws Exception {
		// 1.参数校验
		if (!ValidateUtil.isValid(schoolArea)) {
			return null;
		}
		// 2.获取数数据，并做模型转换
		String sql = "select * from tb_netPionts where schoolArea=? order by orderNo asc";
		List<TnetPiont> retLists = netPiontDao.findAllBySQL(TnetPiont.class, sql, schoolArea);
		List<ComboboxJson> rets = new ArrayList<ComboboxJson>();
		if (ValidateUtil.isValidListObject(retLists)) {
			ComboboxJson json = null;
			for (TnetPiont entry : retLists) {
				json = new ComboboxJson();
				json.setValue(entry.getId());
				json.setText(entry.getName());
				rets.add(json);
			}
		}
		// 3.返回结果集
		if (ValidateUtil.isValidListObject(rets)) {
			return rets;
		}
		return null;
	}

	@Override
	public TnetPiont getSingleById(String schoolArea, String id) throws Exception {
		// 1.参数校验
		if (!ValidateUtil.isValid(id)) {
			return null;
		}
		// 2.获取数数据，并做模型转换
		String sql = "select * from tb_netPionts where id=?";
		TnetPiont entry = netPiontDao.getSingleBySQL(TnetPiont.class, sql, id);
		// 3.返回结果集
		if (null != entry) {
			return entry;
		}
		return null;

	}

}

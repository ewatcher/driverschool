package com.tuocheng.service.demo.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.Tclass;
import com.tuocheng.model.demo.Torganization;
import com.tuocheng.pageModel.base.ComboboxJson;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.ClassDesign;
import com.tuocheng.service.demo.ClassServiceI;
import com.tuocheng.util.base.GeneraterIdByDateUtil;

/**
 * 班级开设管理实体类service实现类
 * 
 * @author MEI702
 * 
 */
@Service("classService")
public class ClassServiceImpl implements ClassServiceI {

	private BaseDaoI<Tclass> classDao;
	private BaseDaoI<Torganization> organizationDao;

	@Autowired
	public void setClassDao(BaseDaoI<Tclass> classDao) {
		this.classDao = classDao;
	}

	@Autowired
	public void setOrganizationDao(BaseDaoI<Torganization> organizationDao) {
		this.organizationDao = organizationDao;
	}

	@Override
	public ClassDesign save(ClassDesign clazz) {
		// 1.数据模型转换
		Tclass t = new Tclass();
		BeanUtils.copyProperties(clazz, t, new String[] { "id" });
		t.setId(UUID.randomUUID().toString());
		t.setOrderNo(GeneraterIdByDateUtil.getGeneraterIdByDate());
		// 2.保存数据
		classDao.save(t);
		// 3.将保存后数据模型转换
		BeanUtils.copyProperties(t, clazz);
		// 4.将转换后的数据返回给前台
		return clazz;
	}

	@Override
	public ClassDesign udpate(ClassDesign clazz) {
		// 1.数据模型转换
		Tclass t = new Tclass();
		BeanUtils.copyProperties(clazz, t, new String[] { "id" });
		t.setId(clazz.getId());
		// 2.保存数据
		classDao.update(t);
		// 3.将保存后数据模型转换
		BeanUtils.copyProperties(t, clazz);
		// 4.将转换后的数据返回给前台
		return clazz;
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
					Tclass clazz = classDao.get(Tclass.class, id);
					// 4.调用DAO层删除数据
					classDao.delete(clazz);
				}
			}

		}

	}

	@Override
	public DataGrid dataGrid(ClassDesign person) {
		DataGrid dataGrid = new DataGrid();
		// 设置返回前台的数据
		dataGrid.setRows(this.changedModel(this.find(person)));
		// 设置总记录数
		dataGrid.setTotal(this.total(person));
		return dataGrid;
	}

	/**
	 * 从数据库存中查找出该实体信息并将此实体类信息转换成action专用的实体类信息，即模型转换
	 * 
	 * @param students
	 * @return
	 */
	private List<ClassDesign> changedModel(List<Tclass> clazzList) {
		List<ClassDesign> retClazz = new ArrayList<ClassDesign>();
		if (clazzList != null && clazzList.size() > 0) {
			for (Tclass t : clazzList) {
				ClassDesign clazz = new ClassDesign();
				BeanUtils.copyProperties(t, clazz);
				if (clazz.getSchoolArea() != null
						&& !clazz.getSchoolArea().trim().equals("")) {
					Torganization organization = organizationDao.get(
							Torganization.class, clazz.getSchoolArea());
					clazz.setSchoolAreaName(organization.getName());
					clazz.setSchoolAreaId(organization.getId());
				}

				retClazz.add(clazz);
			}
		}
		return retClazz;
	}

	/**
	 * 根据action专用实体类的信息查询所有dao专用的实体类信息
	 * 
	 * @param student
	 * @return
	 */
	private List<Tclass> find(ClassDesign clazz) {
		String hql = "from Tclass t where 1=1";
		List<Object> paramList = new ArrayList<Object>();
		hql = this.addWhere(clazz, hql, paramList);
		if (clazz.getSort() != null && clazz.getOrder() != null) {
			hql += " order by " + clazz.getSort() + " " + clazz.getOrder();
		}
		return classDao.find(hql, paramList, clazz.getPage(), clazz.getRows());
	}

	/**
	 * 统计符合当前查询条件的总记录数
	 * 
	 * @param student
	 * @return
	 */
	private Long total(ClassDesign clazz) {
		// 拼接查询条件
		String hql = "select count(*) from Tclass t where 1=1 ";
		List<Object> paramsList = new ArrayList<Object>();
		hql = this.addWhere(clazz, hql, paramsList);
		return classDao.count(hql, paramsList);
	}

	/**
	 * 添加查询约束条件
	 * 
	 * @param clazz
	 * @param hql
	 * @param params
	 * @return
	 */
	private String addWhere(ClassDesign clazz, String hql, List<Object> params) {
		// 姓名模糊查询
		if (clazz.getName() != null && !clazz.getName().trim().equals("")) {
			hql += " and t.name like ?";
			params.add("%%" + clazz.getName().trim() + "%%");
		}
		//业务类型查询
		if (clazz.getType() != null && clazz.getType() > 0) {
			hql += "and t.type =?";
			params.add(clazz.getType());
		}
		// 所属校区查询
		if (clazz.getSchoolArea() != null
				&& !clazz.getSchoolArea().trim().equals("")) {
			hql += "and t.schoolArea =?";
			params.add(clazz.getSchoolArea().trim());
		}
		return hql;
	}

	@Override
	public List<ComboboxJson> getAllClass() {
		List<ComboboxJson> retJson = new ArrayList<ComboboxJson>();
		List<Tclass> clazzList = classDao.find("from Tclass");
		if (clazzList != null && clazzList.size() > 0) {
			for (Tclass t : clazzList) {
				ComboboxJson json = new ComboboxJson();
				json.setValue(t.getId());
				String tempString = null;
				if (t.getType() == 1) {
					tempString = "A1";
				} else if (t.getType() == 2) {
					tempString = "A2";
				} else if (t.getType() == 3) {
					tempString = "A3";
				} else if (t.getType() == 4) {
					tempString = "B1";
				} else if (t.getType() == 5) {
					tempString = "B2";
				} else if (t.getType() == 6) {
					tempString = "C1";
				} else if (t.getType() == 7) {
					tempString = "C2";
				} else if (t.getType() == 8) {
					tempString = "C3";
				} else if (t.getType() == 9) {
					tempString = "C4";
				} else if (t.getType() == 10) {
					tempString = "D";
				} else if (t.getType() == 11) {
					tempString = "E";
				} else if (t.getType() == 12) {
					tempString = "F";
				} else if (t.getType() == 13) {
					tempString = "M";
				} else if (t.getType() == 14) {
					tempString = "N";
				}
				json.setText(tempString + "-" + t.getName());
				retJson.add(json);
			}
		}
		return retJson;
	}
}

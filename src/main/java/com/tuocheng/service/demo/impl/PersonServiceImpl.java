package com.tuocheng.service.demo.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.Torganization;
import com.tuocheng.model.demo.Tperson;
import com.tuocheng.model.demo.Ttrainer;
import com.tuocheng.pageModel.base.ComboboxJson;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.Log;
import com.tuocheng.pageModel.demo.Person;
import com.tuocheng.service.demo.PersonServiceI;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 人员管理实体类service实现类
 * 
 * @author MEI702
 * 
 */
@Service("personService")
public class PersonServiceImpl implements PersonServiceI {

	private BaseDaoI<Tperson> personDao;
	private BaseDaoI<Torganization> organizationDao;

	public BaseDaoI<Tperson> getPersonDao() {
		return personDao;
	}

	// 注入DAO
	@Autowired
	public void setPersonDao(BaseDaoI<Tperson> personDao) {
		this.personDao = personDao;
	}

	@Autowired
	public void setOrganizationDao(BaseDaoI<Torganization> organizationDao) {
		this.organizationDao = organizationDao;
	}

	@Override
	public Person save(Person person) {
		// 1.数据模型转换
		Tperson entry = new Tperson();
		BeanUtils.copyProperties(person, entry, new String[] { "id" });
		entry.setId(UUID.randomUUID().toString());
		if (person.getDepartmentId() != null
				&& !person.getDepartmentId().trim().equals("")) {
			Torganization org = organizationDao.get(Torganization.class,
					person.getDepartmentId());
			entry.setOrganization(org);
		}
		entry.setPropertyType(0);
		// 2.保存数据
		personDao.save(entry);
		// 3.将保存后数据模型转换
		BeanUtils.copyProperties(entry, person);
		person.setDepartmentId(entry.getOrganization().getName());
		// 4.将转换后的数据返回给前台
		return person;
	}

	@Override
	public Person udpate(Person person) {
		// 1.数据模型转换
		Tperson t = new Tperson();
		BeanUtils.copyProperties(person, t, new String[] { "id" });
		t.setId(person.getId());
		if (person.getDepartmentId() != null
				&& !person.getDepartmentId().trim().equals("")) {
			Torganization org = organizationDao.get(Torganization.class,
					person.getDepartmentId());
			t.setOrganization(org);
		}
		// 2.保存数据
		personDao.update(t);
		// 3.将保存后数据模型转换
		BeanUtils.copyProperties(t, person);
		person.setDepartmentId(t.getOrganization().getName());
		// 4.将转换后的数据返回给前台
		return person;
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
					Tperson person = personDao.get(Tperson.class, id);
					// 4.调用DAO层删除数据
					personDao.delete(person);
				}
			}

		}

	}

	@Override
	public DataGrid dataGrid(Person person) {
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
	private List<Person> changedModel(List<Tperson> personList) {
		List<Person> retPersons = new ArrayList<Person>();
		if (personList != null && personList.size() > 0) {
			for (Tperson t : personList) {
				Person person = new Person();
				BeanUtils.copyProperties(t, person);
				if(1==t.getPropertyType()){
					person.setDuty("教练员");
				}
				person.setDepartmentId(t.getOrganization().getName());
				person.setSchoolAreaId(t.getOrganization().getId());
				retPersons.add(person);
			}
		}
		return retPersons;
	}

	/**
	 * 根据action专用实体类的信息查询所有dao专用的实体类信息
	 * 
	 * @param student
	 * @return
	 */
	private List<Tperson> find(Person person) {
		String hql = "from Tperson t where 1=1";
		List<Object> paramList = new ArrayList<Object>();
		hql = this.addWhere(person, hql, paramList);
		if (person.getSort() != null && person.getOrder() != null) {
			hql += " order by " + person.getSort() + " " + person.getOrder();
		}
		return personDao.find(hql, paramList, person.getPage(),
				person.getRows());
	}

	/**
	 * 统计符合当前查询条件的总记录数
	 * 
	 * @param student
	 * @return
	 */
	private Long total(Person person) {
		// 拼接查询条件
		String hql = "select count(*) from Tperson t where 1=1 ";
		List<Object> paramsList = new ArrayList<Object>();
		hql = this.addWhere(person, hql, paramsList);
		return personDao.count(hql, paramsList);
	}

	/**
	 * 添加查询约束条件
	 * 
	 * @param person
	 * @param hql
	 * @param params
	 * @return
	 */
	private String addWhere(Person person, String hql, List<Object> params) {
		// 姓名模糊查询
		if (person.getName() != null && !person.getName().trim().equals("")) {
			hql += " and t.name like ?";
			params.add("%%" + person.getName().trim() + "%%");
		}
		// 身份证精确查询
		if (person.getIdentityId() != null
				&& !person.getIdentityId().trim().equals("")) {
			hql += " and t.identityId =?";
			params.add(person.getIdentityId().trim());
		}
		// 所属部门精确查询
		if (person.getDepartmentId() != null
				&& !person.getDepartmentId().trim().equals("")) {
			hql += " and t.organization.id =?";
			params.add(person.getDepartmentId().trim());
		}
		// 职务
		if (person.getDuty() != null && !person.getDuty().trim().equals("")) {
			hql += " and t.duty =?";
			params.add(person.getDuty().trim());
		}
		//业务类别属性0：非教练员，1教练员
		if(ValidateUtil.isValid(person.getPropertyType())){
			hql += " and t.propertyType =?";
			params.add(person.getPropertyType());
		}
		
		return hql;
	}

	public List<ComboboxJson> getAllPersonsForCombobox() {
		List<ComboboxJson> retJson = new ArrayList<ComboboxJson>();
		List<Tperson> persons = personDao.find("from Tperson");
		if (persons != null && persons.size() > 0) {
			for (Tperson t : persons) {
				ComboboxJson json = new ComboboxJson();
				json.setValue(t.getId());
				json.setText(t.getName());
				retJson.add(json);
			}
		}
		return retJson;
	}

	public boolean identityIdExistOrNot(String identityId) {
		String hql = "select count(*) from Tperson t where t.identityId=?";
		Long temp = (Long) personDao.uniqueResult(hql, identityId);
		return temp != 0;
	}
}

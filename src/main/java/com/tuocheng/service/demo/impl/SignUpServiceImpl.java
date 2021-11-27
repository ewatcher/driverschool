package com.tuocheng.service.demo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.TsignUp;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.Difficulty;
import com.tuocheng.pageModel.demo.SignUp;
import com.tuocheng.service.demo.SignUpServiceI;
import com.tuocheng.util.base.Util;

/**
 * 微信报名service实现类
 * 
 * @author 李杰
 * 
 */
@Service("signUpService")
public class SignUpServiceImpl implements SignUpServiceI {

	private BaseDaoI<TsignUp> signUpDao;
	private BaseDaoI<Object[]> objDao;

	// 注入DAO
	@Autowired
	public void setSignUpDao(BaseDaoI<TsignUp> signUpDao) {
		this.signUpDao = signUpDao;
	}
	
	// 注入DAO
	@Autowired
	public void setObjectDao(BaseDaoI<Object[]> objDao) {
		this.objDao = objDao;
	}

	@Override
	public SignUp save(SignUp signUp) {
		// 1.数据模型转换
		TsignUp t = new TsignUp();
		BeanUtils.copyProperties(signUp, t);
		// 2.设置相关属性
		t.setId(UUID.randomUUID().toString());
		t.setUpTime(new Date());
		// 3.保存数据
		signUpDao.save(t);
		// 4.返回结果集
		BeanUtils.copyProperties(t, signUp);
		return signUp;
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
					TsignUp t = signUpDao.get(TsignUp.class, id);
					// 4.调用DAO层删除数据
					signUpDao.delete(t);
				}
			}
		}
	}

	@Override
	public SignUp update(SignUp signUp) {
		// 1.数据模型转换
		TsignUp t = new TsignUp();
		BeanUtils.copyProperties(signUp, t, new String[] { "id" });
		// 2.设置相关属性
		t.setId(signUp.getId());
		// 3.更新数据
		signUpDao.update(t);
		// 4.返回结果集
		BeanUtils.copyProperties(t, signUp);
		return signUp;
	}

	@Override
	public DataGrid dataGrid(SignUp signUp) {
		DataGrid dataGrid = new DataGrid();
		//1.从数据库中查找出所有实体类信息
		//2.查找出的信息进行数据模型转换
		//3.设置展现数据
		dataGrid.setRows(this.changeObjectArray(this.findObjctArray(signUp)));
		dataGrid.setTotal(this.total(signUp));
		return dataGrid;
	}
	
	private List<Object[]> findObjctArray(SignUp signUp){
		
		String hql = " select t.id, t.openId, t.upMobile, t.upAge, t.upName, t.upCity, t.upTime, t.comments, t.status, torg.id, torg.name from TsignUp t, Torganization torg where t.organizationId = torg.id ";
		List<Object> params = new ArrayList<Object>();
		hql = addWhere(signUp, hql, params);

		if (signUp.getSort() != null && signUp.getOrder() != null) {
			hql += " order by t." + signUp.getSort() + " " + signUp.getOrder();
		}

		return objDao.find(hql, params, signUp.getPage(), signUp.getRows());
	}
	
	private List<SignUp> changeObjectArray(List<Object[]> objs){
		List<SignUp> list = new ArrayList<SignUp>();
		if (objs != null && objs.size() > 0) {
			for (Object[] o : objs) {
				//t.id, t.openId, t.upMobile, t.upAge, t.upName, t.upCity, t.upTime, t.comments, t.status, torg.id, torg.name 
				SignUp s = new SignUp();
				s.setId(Util.objToString(o[0], ""));
				s.setOpenId(Util.objToString(o[1], ""));
				s.setUpMobile(Util.objToString(o[2], ""));
				s.setUpAge(Util.objToInt(o[3], 0));
				s.setUpName(Util.objToString(o[4], ""));
				s.setUpCity(Util.objToString(o[5], ""));
				s.setUpTime((Date)o[6]);
				s.setComments(Util.objToString(o[7], ""));
				s.setStatus(Util.objToInt(o[8], 0));
				s.setOrganizationId(Util.objToString(o[9], ""));
				s.setOrganizationName(Util.objToString(o[10], ""));
				list.add(s);
			}
		}
		return list;
	}

	/**
	 * 添加查询约束条件where
	 * 
	 * @param student
	 * @param hql
	 * @param values
	 * @return
	 */
	private String addWhere(SignUp signUp, String hql, List<Object> values) {
		// 
		if(Util.isNotNull(signUp.getOrganizationId())){
			hql += " and t.organizationId = ? ";
			values.add(signUp.getOrganizationId());
		}
		return hql;
	}
	
	/**
	 * 统计符合当前查询条件的总记录数
	 * 
	 * @param timerCard
	 * @return
	 */
	private Long total(SignUp signUp) {
		String hql = "select count(*) from TsignUp t where 1 = 1 ";
		List<Object> params = new ArrayList<Object>();
		hql = addWhere(signUp, hql, params);
		return signUpDao.count(hql, params);
	}

}

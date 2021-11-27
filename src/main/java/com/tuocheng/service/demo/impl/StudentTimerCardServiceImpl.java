package com.tuocheng.service.demo.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.Tstudent;
import com.tuocheng.model.demo.TstudentTimerCard;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.Student;
import com.tuocheng.pageModel.demo.StudentTimerCard;
import com.tuocheng.service.demo.StudentTimerCardServiceI;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 计时卡service实现类
 * 
 * @author 李杰
 * 
 */
@Service("studentTimerCardService")
public class StudentTimerCardServiceImpl implements StudentTimerCardServiceI {

	private BaseDaoI<TstudentTimerCard> ttcDao;
	private BaseDaoI<Tstudent> studentDao;
	private BaseDaoI<Object[]> objDao;

	// 注入DAO
	@Autowired
	public void setStudentTimerCardDao(BaseDaoI<TstudentTimerCard> ttcDao) {
		this.ttcDao = ttcDao;
	}
	
	// 注入DAO
	@Autowired
	public void setStudentDao(BaseDaoI<Tstudent> studentDao) {
		this.studentDao = studentDao;
	}
	
	// 注入DAO
	@Autowired
	public void setObjectDao(BaseDaoI<Object[]> objDao) {
		this.objDao = objDao;
	}
	
	@Override
	public StudentTimerCard save(StudentTimerCard stc) {
		// 1.数据模型转换
		TstudentTimerCard t = new TstudentTimerCard();
		BeanUtils.copyProperties(stc, t);
		// 2.设置相关属性
		t.setId(UUID.randomUUID().toString());
		// 3.保存数据
		ttcDao.save(t);
		// 4.返回结果集
		BeanUtils.copyProperties(t, stc);
		return stc;
	}

	@Override
	public StudentTimerCard update(StudentTimerCard stc) {
		// 1.数据模型转换
		TstudentTimerCard t = new TstudentTimerCard();
		BeanUtils.copyProperties(stc, t, new String[] { "id" });
		// 2.设置相关属性
		t.setId(stc.getId());
		// 3.更新数据
		ttcDao.update(t);
		// 4.返回结果集
		BeanUtils.copyProperties(t, stc);
		return stc;
	}

	@Override
	public DataGrid dataGrid(StudentTimerCard stc) {
		DataGrid dataGrid = new DataGrid();
		//1.从数据库中查找出所有实体类信息
		//2.查找出的信息进行数据模型转换
		//3.设置展现数据
		dataGrid.setRows(this.changeModel(this.find(stc)));
		dataGrid.setTotal(this.total(stc));
		return dataGrid;
	} 
	
	public DataGrid personalDataGrid(StudentTimerCard stc) {
		DataGrid dataGrid = new DataGrid();
		if(ValidateUtil.isValid(stc.getIdentityId())){
			//1.根据用登录信息查找学员对象
			String hql="from Tstudent t where t.identityId=?";
			Tstudent stu=(Tstudent) studentDao.uniqueResult(hql, stc.getIdentityId());
			if(stu!=null){
				//2.组织返回参数
				TstudentTimerCard studentTC=stu.getStudentTimerCard();
				StudentTimerCard studentTemp=new StudentTimerCard();
				BeanUtils.copyProperties(studentTC, studentTemp);
				List<StudentTimerCard> retStudents=new ArrayList<StudentTimerCard>();
				studentTemp.setSchoolArea(stu.getOrganization().getName());
				studentTemp.setStudentNo(stu.getStudentNo());
				studentTemp.setStudentName(stu.getName());
				studentTemp.setDriverTypeId(stu.getDriverType());
				retStudents.add(studentTemp);
				//3.将参数组成Datagrid数据模型传递给前台
				dataGrid.setRows(retStudents);
				dataGrid.setTotal(1L);
				return dataGrid;
			}
		}
		return null;
	} 

	/**
	 * 从数据库存中查找出该实体信息并将此实体类信息转换成action专用的实体类信息，即模型转换
	 * 
	 * @param ts
	 * @return
	 */
	private List<StudentTimerCard> changeModel(List<Tstudent> ts) {
		List<StudentTimerCard> stcs = new ArrayList<StudentTimerCard>();
		if (ts != null && ts.size() > 0) {
			for (Tstudent t : ts) {
				StudentTimerCard stc = new StudentTimerCard();
				if(t.getStudentTimerCard() != null){
					BeanUtils.copyProperties(t.getStudentTimerCard(), stc);
					stc.setId(t.getStudentTimerCard().getId());
				}
				stc.setDriverTypeId(t.getDriverType());
				stc.setStudentId(t.getId());
				stc.setStudentName(t.getName());
				stc.setStudentNo(t.getStudentNo());
				stc.setSchoolArea(t.getOrganization().getName());
				stcs.add(stc);
			}
		}
		return stcs;
	}

	/**
	 * 根据action专用实体类的信息查询所有dao专用的实体类信息
	 * 
	 * @param timerCard 
	 * @return
	 */
	private List<Tstudent> find(StudentTimerCard stc) {
		//String hql = " select tc.id, tc.totalHours, tc.oneHours, t.driverType from Tstudent t, TstudentTimerCard tc where t.studentTimerCard = tc.id ";
		String hql = " from Tstudent t where 1 = 1 ";
		List<Object> params = new ArrayList<Object>();
		hql = addWhere(stc, hql, params);

		if (stc.getSort() != null && stc.getOrder() != null) {
			hql += " order by t." + stc.getSort() + " " + stc.getOrder();
		}

		return studentDao.find(hql, params, stc.getPage(), stc.getRows());
	}
	

	/**
	 * 统计符合当前查询条件的总记录数
	 * 
	 * @param timerCard
	 * @return
	 */
	private Long total(StudentTimerCard stc) {
		String hql = "select count(*) from Tstudent t where 1 = 1 ";
		List<Object> params = new ArrayList<Object>();
		hql = addWhere(stc, hql, params);
		return studentDao.count(hql, params);
	}

	/**
	 * 添加查询约束条件where
	 * 
	 * @param student
	 * @param hql
	 * @param values
	 * @return
	 */
	private String addWhere(StudentTimerCard stc, String hql, List<Object> values) {
		// 改名模糊查询
		if (stc.getName() != null && !stc.getName().trim().equals("")) {
			hql += " and t.name like ? ";
			values.add("%%" + stc.getName().trim() + "%%");
		}
		// 注册时间（范围查询）
		if (stc.getCreateTimeStart() != null) {
			hql += " and t.createTime >= ? ";
			values.add(stc.getCreateTimeStart());
		}
		if (stc.getCreateTimeEnd() != null) {
			hql += " and t.createTime <= ? ";
			values.add(stc.getCreateTimeEnd());
		}
		// 身份证（精确查询）
		if (stc.getIdentityId() != null
				&& !stc.getIdentityId().trim().equals("")) {
			hql += " and t.identityId = ? ";
			values.add(stc.getIdentityId());
		}
		// 状态：精确查询
		if (stc.getNowState() != null ) {
			hql += " and t.nowState = ? ";
			values.add(stc.getNowState());
		}
		// 所属校区：精确查询
		if (stc.getOrganizationId() != null
				&& !stc.getOrganizationId().trim().equals("")) {
			hql += " and t.organization.id = ?";
			values.add(stc.getOrganizationId().trim());
		}
		
		//考试类型精确查询(类型：C1，A1...)
		if(stc.getDriverTypeId()!=null){
			hql += " and t.driverType = ?";
			values.add(stc.getDriverTypeId());
		}
		return hql;
	}


}

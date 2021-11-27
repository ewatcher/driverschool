package com.tuocheng.service.demo.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.Tprogress;
import com.tuocheng.model.demo.Tstudent;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.Progress;
import com.tuocheng.service.demo.ProgressServiceI;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 预约管理实体类service实现类
 * 
 * @author 农峰
 * 
 */
@Service("progressService")
public class ProgressServiceImpl implements ProgressServiceI {

	private BaseDaoI<Tprogress> progressDao;
	private BaseDaoI<Tstudent> studentDao;

	@Autowired
	public void setProgressDao(BaseDaoI<Tprogress> progressDao) {
		this.progressDao = progressDao;
	}

	@Autowired
	public void setStudentDao(BaseDaoI<Tstudent> studentDao) {
		this.studentDao = studentDao;
	}

	@Override
	public Progress udpate(Progress progress) {
		// 1.数据模型转换
		Tprogress t = new Tprogress();
		BeanUtils.copyProperties(progress, t, new String[] { "id" });
		t.setId(progress.getId());
		if (progress.getStudentId() != null) {
			Tstudent student = studentDao.get(Tstudent.class, progress.getStudentId());
			t.setSchoolArea(progress.getStudentOrganization());
		}

		// 2.保存数据
		progressDao.update(t);

		// 3.将保存后数据模型转换
		BeanUtils.copyProperties(t, progress);
		// 4.将转换后的数据返回给前台
		return progress;
	}

	//更新学员状态
	public boolean updateProsessState(String id, Integer stateVal)throws Exception {
		if (!ValidateUtil.isValid(id) || !ValidateUtil.isValid(stateVal)) {
			return false;
		}
		Tprogress progress=progressDao.get(Tprogress.class, id);
		if(progress!=null){
			progress.setState(stateVal);
			progressDao.update(progress);
			return true;
		}
		return false;
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
					Tprogress person = progressDao.get(Tprogress.class, id);
					// TODO (将删除学员所有信息)
					// 4.调用DAO层删除数据
					progressDao.delete(person);
				}
			}

		}

	}

	@Override
	public DataGrid dataGrid(Progress studentFile) throws Exception {
		DataGrid dataGrid = new DataGrid();
		// 设置返回前台的数据
		dataGrid.setRows(this.changedModel(this.find(studentFile)));
		// 设置总记录数
		dataGrid.setTotal(this.total(studentFile));
		return dataGrid;
	}

	public DataGrid personalDataGrid(Progress progress) throws Exception {
		DataGrid dataGrid = new DataGrid();
		if (ValidateUtil.isValid(progress.getStudentIdentityId())) {
			String hql = "from Tstudent t where t.identityId=?";
			Tstudent stu = (Tstudent) studentDao.uniqueResult(hql, progress.getStudentIdentityId());
			if (stu != null) {
				Tprogress t = stu.getProgress();
				Progress entry = new Progress();
				BeanUtils.copyProperties(t, entry);
				List<Progress> retStudents = new ArrayList<Progress>();
				entry.setSchoolArea(stu.getOrganization().getName());
				entry.setStudentNo(stu.getStudentNo());
				entry.setStudentName(stu.getName());
				entry.setStudentSex(stu.getSex());
				entry.setStudentIdentityId(stu.getIdentityId());
				retStudents.add(entry);
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
	 * @param students
	 * @return
	 */
	private List<Progress> changedModel(List<Tprogress> progresses) {
		List<Progress> retProgress = new ArrayList<Progress>();
		if (progresses != null && progresses.size() > 0) {
			for (Tprogress t : progresses) {
				Progress entry = new Progress();
				BeanUtils.copyProperties(t, entry);
				// 给页面展现学员属性信息
				Tstudent student = t.getStudent();
				if (student != null) {
					entry.setStudentId(student.getId());
					entry.setStudentName(student.getName());
					entry.setStudentSex(student.getSex());
					entry.setStudentCreateTime(student.getCreateTime());
					entry.setStudentIdentityId(student.getIdentityId());
					entry.setStudentOrganization(student.getOrganization().getId());
					entry.setStudentAddress(student.getAddress());
					entry.setStudentBirthdate(student.getBirthdate());
					entry.setStudentPhone(student.getPhone());
					entry.setStudentEmail(student.getEmail());
					entry.setStudentTelephone(student.getTelephone());
					entry.setStudentMailCode(student.getMailCode());
					entry.setStudentContry(student.getContry());
					entry.setStudentNativeNation(student.getNativeNation());
					entry.setStudentResidenceId(student.getResidenceId());
					entry.setStudentResidenceAddr(student.getResidenceAddr());
					entry.setStudentImageId(student.getImageId());
					entry.setStudentNowstate(student.getNowState());
					if (student.getClazz() != null) {
						entry.setStudentClazz(student.getClazz().getName());
					}
					entry.setStudentApplyType(student.getApplyType());
					entry.setStudentDriverType(student.getDriverType());
					entry.setSchoolArea(student.getOrganization().getName());
					entry.setStudentNo(student.getStudentNo());

					retProgress.add(entry);
				}

			}
		}
		return retProgress;
	}

	/**
	 * 根据action专用实体类的信息查询所有dao专用的实体类信息
	 * 
	 * @param student
	 * @return
	 */
	private List<Tprogress> find(Progress studentFile) {
		String hql = "from Tprogress t where 1=1";
		List<Object> paramList = new ArrayList<Object>();
		hql = this.addWhere(studentFile, hql, paramList);
		if (studentFile.getSort() != null && studentFile.getOrder() != null) {
			hql += " order by " + studentFile.getSort() + " " + studentFile.getOrder();
		}
		return progressDao.find(hql, paramList, studentFile.getPage(), studentFile.getRows());
	}

	/**
	 * 统计符合当前查询条件的总记录数
	 * 
	 * @param student
	 * @return
	 */
	private Long total(Progress driverLicense) {
		// 拼接查询条件
		String hql = "select count(*) from Tprogress t where 1=1 ";
		List<Object> paramsList = new ArrayList<Object>();
		hql = this.addWhere(driverLicense, hql, paramsList);
		return progressDao.count(hql, paramsList);
	}

	/**
	 * 添加查询约束条件
	 * 
	 * @param progress
	 * @param hql
	 * @param params
	 * @return
	 */
	private String addWhere(Progress progress, String hql, List<Object> params) {
		// 姓名模糊查询
		if (progress.getStudentName() != null

				&& !progress.getStudentName().trim().equals("")) {
			hql += " and t.student.name like ?";
			params.add("%%" + progress.getStudentName().trim() + "%%");
		}
		// 身份证精确查询
		if (progress.getStudentIdentityId() != null && !progress.getStudentIdentityId().trim().equals("")) {
			hql += " and t.student.identityId =?";
			params.add(progress.getStudentIdentityId().trim());
		}
		// 学员编号查询
		if (progress.getStudentNo() != null && !progress.getStudentNo().trim().equals("")) {
			hql += " and t.student.studentNo like ?";
			params.add("%" + progress.getStudentNo().trim());
		}
		// 所属校区精确查询
		if (ValidateUtil.isValid(progress.getSchoolArea())) {
			hql += " and t.schoolArea =?";
			params.add(progress.getSchoolArea().trim());
		}
		// 进度状态查询
		if (progress.getState() != null) {
			hql += " and t.state =?";
			params.add(progress.getState());
		}

		return hql;
	}

	@Override
	public Tprogress getSingleByStudentId(String schoolArea, String studentId) throws Exception {
		if (!ValidateUtil.isValid(studentId) || !ValidateUtil.isValid(schoolArea)) {
			return null;
		}
		String sql = "select * from tb_progress where schoolArea=? and studentId=?";
		List<Tprogress> progressLists = progressDao.findAllBySQL(Tprogress.class, sql, schoolArea, studentId);
		if (ValidateUtil.isValidListObject(progressLists)) {
			return progressLists.get(0);
		}
		return null;
	}

}

package com.tuocheng.service.demo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.TcommentStudent;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.CommentStudent;
import com.tuocheng.service.demo.CommentStudentServiceI;
import com.tuocheng.util.base.Util;

/**
 * 学员点评service实现类
 * 
 * @author 李杰
 * 
 */
@Service("commentStudentService") 
public class CommentStudentServiceImpl implements CommentStudentServiceI {

	private BaseDaoI<TcommentStudent> commStudentDao;
	private BaseDaoI<Object[]> objDao;

	// 注入DAO
	@Autowired
	public void setCommentStudentDao(BaseDaoI<TcommentStudent> commStudentDao) {
		this.commStudentDao = commStudentDao;
	}
	
	// 注入DAO
	@Autowired
	public void setObjectDao(BaseDaoI<Object[]> objDao) {
		this.objDao = objDao;
	}
	
	@Override
	public CommentStudent save(CommentStudent commStudent) {
		// 1.数据模型转换
		TcommentStudent t = new TcommentStudent();
		BeanUtils.copyProperties(commStudent, t);
		// 2.设置相关属性
		t.setId(UUID.randomUUID().toString());
		t.setCommentTime(new Date());
		// 3.保存数据
		commStudentDao.save(t);
		// 4.返回结果集
		BeanUtils.copyProperties(t, commStudent);
		return commStudent;
	}

	@Override
	public CommentStudent update(CommentStudent commStudent) {
		// 1.数据模型转换
		TcommentStudent t = new TcommentStudent();
		BeanUtils.copyProperties(commStudent, t, new String[] { "id" });
		// 2.设置相关属性
		t.setId(commStudent.getId());
		// 3.更新数据
		commStudentDao.update(t);
		// 4.返回结果集
		BeanUtils.copyProperties(t, commStudent);
		return commStudent;
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
					TcommentStudent t = commStudentDao.get(TcommentStudent.class, id);
					// 4.调用DAO层删除数据
					commStudentDao.delete(t);
				}
			}
		}
	}

	@Override
	public DataGrid dataGrid(CommentStudent commStd) {
		DataGrid dataGrid = new DataGrid();
		//1.从数据库中查找出所有实体类信息
		//2.查找出的信息进行数据模型转换
		//3.设置展现数据
		dataGrid.setRows(this.changeModel(this.find(commStd)));
		dataGrid.setTotal(this.total(commStd));
		return dataGrid;
	}

	/**
	 * 从数据库存中查找出该实体信息并将此实体类信息转换成action专用的实体类信息，即模型转换
	 * 
	 * @param ts
	 * @return
	 */
	private List<CommentStudent> changeModel(List<Object[]> objs) {
		List<CommentStudent> commStds = new ArrayList<CommentStudent>();
		if (objs != null && objs.size() > 0) {
			for (Object[] o : objs) {
				CommentStudent commStd = new CommentStudent();
				commStd.setId(Util.objToString(o[0], ""));
				commStd.setStudentId(Util.objToString(o[1], ""));
				commStd.setStudentName(Util.objToString(o[2], ""));
				commStd.setTrainId(Util.objToString(o[3], ""));
				commStd.setTrainName(Util.objToString(o[4], ""));
				commStd.setCommentTime((Date)o[5]);
				commStd.setComments(Util.objToString(o[6], ""));
				commStd.setIsRead(Util.objToInt(o[7], 0));
				commStd.setStatus(Util.objToInt(o[8], 0));
				commStds.add(commStd);
			}
		}
		return commStds;
	}

	/**
	 * 根据action专用实体类的信息查询所有dao专用的实体类信息
	 * 
	 * @param timerCard 
	 * @return
	 */
	private List<Object[]> find(CommentStudent commStudent) {
		String hql = " select t.id, t.studentId, ts.name, t.trainId, tr.name, t.commentTime, t.comments, t.isRead, t.status from TcommentStudent t, Tstudent ts, Ttrainer tr where t.studentId = ts.id and t.trainId = tr.id ";
		List<Object> params = new ArrayList<Object>();
		hql = addWhere(commStudent, hql, params);

		if (commStudent.getSort() != null && commStudent.getOrder() != null) {
			hql += " order by t." + commStudent.getSort() + " " + commStudent.getOrder();
		}

		return objDao.find(hql, params, commStudent.getPage(), commStudent.getRows());
	}
	

	/**
	 * 统计符合当前查询条件的总记录数
	 * 
	 * @param timerCard
	 * @return
	 */
	private Long total(CommentStudent commStudent) {
		String hql = "select count(*) from TcommentStudent t where 1 = 1 ";
		List<Object> params = new ArrayList<Object>();
		hql = addWhere(commStudent, hql, params);
		return commStudentDao.count(hql, params);
	}

	/**
	 * 添加查询约束条件where
	 * 
	 * @param student
	 * @param hql
	 * @param values
	 * @return
	 */
	private String addWhere(CommentStudent commStudent, String hql, List<Object> values) {
		
		if (Util.isNotNull(commStudent.getStudentId())) {
			hql += " and t.studentId = ? ";
			values.add(commStudent.getStudentId());
		}
		
		if (Util.isNotNull(commStudent.getTrainId())) {
			hql += " and t.trainId = ? ";
			values.add(commStudent.getTrainId());
		}
		
		if (commStudent.getCommentTimeStart() != null) {
			hql += " and t.commentTime >= ? ";
			values.add(commStudent.getCommentTimeStart());
		}
		
		if (commStudent.getCommentTimeEnd() != null) {
			hql += " and t.commentTime <= ? ";
			values.add(commStudent.getCommentTimeEnd());
		}
		
		return hql;
	}

}

package com.tuocheng.service.demo.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.Tlog;
import com.tuocheng.model.demo.Torganization;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.Log;
import com.tuocheng.service.demo.LogServiceI;
import com.tuocheng.util.base.DateUtil;
import com.tuocheng.util.base.LogUtil;
import com.tuocheng.util.base.ValidateUtil;

/**
 * 
 * @author 农峰
 * 
 */
@Service("logService")
public class LogServiceImpl implements LogServiceI {
	private BaseDaoI<Tlog> logDao;
	private BaseDaoI<Torganization> organizationDao;

	@Autowired
	public void setLogDao(BaseDaoI<Tlog> logDao) {
		this.logDao = logDao;
	}

	@Autowired
	public void setOrganizationDao(BaseDaoI<Torganization> organizationDao) {
		this.organizationDao = organizationDao;
	}

	@Override
	public Log save(Log log) throws Exception {
		Tlog t = new Tlog();
		BeanUtils.copyProperties(log, t, new String[] { "id" });
		t.setId(UUID.randomUUID().toString());
		logDao.save(t);
		BeanUtils.copyProperties(t, log);
		return log;
	}

	@Override
	public Log update(Log log) throws Exception {
		// 1.数据模型转换
		Tlog t = new Tlog();
		BeanUtils.copyProperties(log, t, new String[] { "id" });
		t.setId(log.getId());
		// 2.保存数据
		logDao.update(t);
		// 3.将保存后数据模型转换
		BeanUtils.copyProperties(t, log);
		// 4.将转换后的数据返回给前台
		return log;
	}

	@Override
	public void delete(String ids) throws Exception {
		// 1.判断参数是否为空
		if (ids != null) {
			// 2.对参数的字符串进行分割
			for (String id : ids.split(",")) {
				// 排除id为0的情况
				if (!id.trim().equals("0")) {
					// 3.根据ID从数据库中查找数据
					Tlog log = logDao.get(Tlog.class, id);
					// 4.调用DAO层删除数据
					logDao.delete(log);
				}
			}

		}
	}

	@Override
	public DataGrid datagrid(Log log) throws Exception {
		// 显示最近三个月的日志信息
		DataGrid dataGrid = new DataGrid();
		// 设置返回前台的数据
		/* dataGrid.setRows(this.changedModel(this.find(log))); */
		dataGrid.setRows(this.changedModel(this.findNearestLogs(log, 3)));
		// 设置总记录数
		dataGrid.setTotal(this.getTotalBySql(log, 3));
		return dataGrid;
	}

	@Override
	public DataGrid personalDatagrid(Log log) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Log get(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	// 通过表明创建日志表
	@Override
	public void createLogTable(String tableName) {
		String sql = "create table if not exists " + tableName + " like tb_log";
		logDao.executeSQL(sql);
	}

	public void saveEntity(Log log) {
		// insert into logs_2016_09
		String sql = "insert into "
				+ LogUtil.generateLogTableName(0)
				+ "(id,operator,opername,operparams,operresult,resultmsg,opertime,schoolArea) "
				+ "values(?,?,?,?,?,?,?,?)";
		String id = UUID.randomUUID().toString();
		logDao.executeSQL(sql, id, log.getOperator(), log.getOperName(),
				log.getOperParams(), log.getOperResult(), log.getResultMsg(),
				log.getOperTime(), log.getSchoolArea());
	}

	/**
	 * 查询最近指定月份数的日志
	 */
	public List<Tlog> findNearestLogs(Log log, int n) {
		String tableName = LogUtil.generateLogTableName(0);
		// 查询出最近的日志表名称
		String sql = "select table_name from information_schema.tables "
				+ "where table_schema='driverschool_main' "
				+ "and table_name like 'logs_%' " + "and table_name <= '"
				+ tableName + "' " + "order by table_name desc limit 0," + n;
		List list = logDao.executeSQLQuery(null, sql);
		// 查询最近若干月内的日志
		String logSql = "";
		String logName = null;
		for (int i = 0; i < list.size(); i++) {
			logName = (String) list.get(i);
			logSql = logSql + " select * from " + logName + " where 1=1 ";
			if (i == (list.size() - 1)) {
				logSql=this.addFindWhere(log, logSql);
			} else {
				logSql=this.addFindWhere(log, logSql)+" union";
			}
		}
		// 指定Log实体类
		return logDao.executeSQLQuery(Tlog.class, logSql);
	}

	private String addFindWhere(Log log, String sql) {

		if (ValidateUtil.isValid(log.getSchoolArea())) {
			sql += " and schoolArea='" + log.getSchoolArea() + "'";
		}
		if (log.getStartTime() != null) {
			sql += " and operTime >'" + DateUtil.DateToString(log.getStartTime()) + "%'";
		}
		if (log.getEndTime() != null) {
			sql += " and operTime <'" + DateUtil.DateToString(log.getEndTime()) + "'";
		}
		if(ValidateUtil.isValid(log.getOperator())){
			sql+=" and operator like '"+log.getOperator().trim()+"%'";
		}
		return sql;
	}

	/**
	 * 从数据库存中查找出该实体信息并将此实体类信息转换成action专用的实体类信息，即模型转换
	 * 
	 * @param students
	 * @return
	 */
	private List<Log> changedModel(List<Tlog> logs) {
		List<Log> retDriverLicenses = new ArrayList<Log>();
		if (logs != null && logs.size() > 0) {
			for (Tlog t : logs) {
				Log entry = new Log();
				BeanUtils.copyProperties(t, entry);
				if (ValidateUtil.isValid(t.getSchoolArea())) {
					if (organizationDao.get(Torganization.class,
							entry.getSchoolArea()) != null) {
						entry.setSchoolAreaName(organizationDao.get(
								Torganization.class,
								entry.getSchoolArea().trim()).getName());
					}
				}
				retDriverLicenses.add(entry);
				// 给页面展现学员属性信息
			}
		}
		return retDriverLicenses;
	}

	/**
	 * 根据action专用实体类的信息查询所有dao专用的实体类信息
	 * 
	 * @param student
	 * @return
	 */
	private List<Tlog> find(Log log) {
		// 当前月日志
		String tableName = LogUtil.generateLogTableName(0);

		String hql = "from Tlog t where 1=1";
		List<Object> paramList = new ArrayList<Object>();
		hql = this.addWhere(log, hql, paramList);
		if (log.getSort() != null && log.getOrder() != null) {
			hql += " order by " + log.getSort() + " " + log.getOrder();
		}
		return logDao.find(hql, paramList, log.getPage(), log.getRows());
	}

	/**
	 * 统计符合当前查询条件的总记录数
	 * 
	 * @param student
	 * @return
	 */
	private Long total(Log log) {
		// 当前月日志
		String tableName = LogUtil.generateLogTableName(0);
		// 拼接查询条件
		String hql = "select count(*) from Tlog t where 1=1 ";
		List<Object> paramsList = new ArrayList<Object>();
		hql = this.addWhere(log, hql, paramsList);
		return logDao.count(hql, paramsList);
	}

	private Long getTotalBySql(Log log, int n) {
		int retData = 0;
		for (int i = 0; i < n; i++) {
			// 当前月日志
			String tableName = LogUtil.generateLogTableName(0 - i);
			// 拼接查询条件
			String sqltemp = "select count(*) from " + tableName
					+ " where 1=1 ";
			List<Object> paramsList = new ArrayList<Object>();
			sqltemp = this.addWhere(log, sqltemp, paramsList);
			Long temp = logDao.countBySQL(sqltemp, paramsList).longValue();
			if (temp != null) {
				retData += temp;
			}
		}

		return (long) retData;
	}

	/**
	 * 添加查询约束条件
	 * 
	 * @param log
	 * @param hql
	 * @param params
	 * @return
	 */
	private String addWhere(Log log, String sql, List<Object> params) {
		// 操作人（精确查询）
		if (ValidateUtil.isValid(log.getOperator())) {
			sql += " and operator =?";
			params.add(log.getOperator().trim());
		}
		// 校区标识查询（精确查询）
		if (ValidateUtil.isValid(log.getSchoolArea())) {
			sql += " and schoolArea =?";
			params.add(log.getSchoolArea().trim());
		}
		// 操作時間範圍查詢
		if (log.getStartTime() != null) {
			sql += " and operTime >=?";
			params.add(log.getStartTime());
		}
		if (log.getEndTime() != null) {
			sql += " and operTime <=?";
			params.add(log.getEndTime());
		}
		return sql;
	}

}

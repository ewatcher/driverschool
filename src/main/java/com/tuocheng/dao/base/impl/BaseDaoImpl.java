package com.tuocheng.dao.base.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.naming.event.ObjectChangeListener;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.util.base.ValidateUtil;

@Repository("baseDao")
public class BaseDaoImpl<T> implements BaseDaoI<T> {

	@Resource(name = "sessionFactory")
	protected SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public Serializable save(T o) {
		return this.getCurrentSession().save(o);
	}

	public void delete(T o) {
		this.getCurrentSession().delete(o);
	}

	public void update(T o) {
		this.getCurrentSession().update(o);
	}

	public void saveOrUpdate(T o) {
		this.getCurrentSession().saveOrUpdate(o);
	}

	public List<T> find(String hql) {
		return this.getCurrentSession().createQuery(hql).list();
	}

	public List<T> find(String hql, Object[] param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.list();
	}

	public List<T> find(String hql, List<Object> param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return q.list();
	}

	public List<T> findBySQL(Class clazz, String sql, Object... objects) {
		SQLQuery q = sessionFactory.getCurrentSession().createSQLQuery(sql);
		// 添加实体类
		if (clazz != null) {
			q.addEntity(clazz);
		}
		for (int i = 0; i < objects.length; i++) {
			q.setParameter(i, objects[i]);
		}
		return q.list();

	}

	public List<T> find(String hql, Object[] param, Integer page, Integer rows) {
		if (page == null || page < 1) {
			page = 1;
		}
		if (rows == null || rows < 1) {
			rows = 10;
		}
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	public List<T> find(String hql, List<Object> param, Integer page,
			Integer rows) {
		if (page == null || page < 1) {
			page = 1;
		}
		if (rows == null || rows < 1) {
			rows = 10;
		}
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	public T get(Class<T> c, Serializable id) {
		return (T) this.getCurrentSession().get(c, id);
	}

	public T get(String hql, Object[] param) {
		List<T> l = this.find(hql, param);
		if (l != null && l.size() > 0) {
			return l.get(0);
		} else {
			return null;
		}
	}

	public T get(String hql, List<Object> param) {
		List<T> l = this.find(hql, param);
		if (l != null && l.size() > 0) {
			return l.get(0);
		} else {
			return null;
		}
	}

	public Long count(String hql) {
		return (Long) this.getCurrentSession().createQuery(hql).uniqueResult();
	}

	public Long count(String hql, Object[] param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return (Long) q.uniqueResult();
	}

	public Long count(String hql, List<Object> param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return (Long) q.uniqueResult();
	}

	public BigInteger countBySql(String sql, List<Object> param) {
		Query q = this.getCurrentSession().createSQLQuery(sql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return (BigInteger) q.uniqueResult();
	}

	public Integer executeHql(String hql) {
		return this.getCurrentSession().createQuery(hql).executeUpdate();
	}

	public Integer executeHql(String hql, Object[] param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.executeUpdate();
	}
	
	
	public List<T> getAllByWhereIn(Class clazz,String tableName,String condition,Collection ids){
		String sql="select * from "+tableName+" where "+condition+" in (:ids)";
		
		SQLQuery q = sessionFactory.getCurrentSession().createSQLQuery(sql);
		if (clazz != null) {
			q.addEntity(clazz);
		}
		q.setParameterList("ids", ids);
		return q.list();
	}

	@Transactional("transactionManager")
	public void executeSQL(String sql, Object... objects) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		for (int i = 0; i < objects.length; i++) {
			q.setParameter(i, objects[i]);
		}
		q.executeUpdate();
	}

	public Integer executeHql(String hql, List<Object> param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return q.executeUpdate();
	}

	public Object uniqueResult(String hql, Object... objects) {
		Query q = this.getCurrentSession().createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			q.setParameter(i, objects[i]);
		}
		return q.uniqueResult();
	}

	public List executeSQLQuery(Class clazz, String sql, Object... objects) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		// 添加实体类
		if (clazz != null) {
			q.addEntity(clazz);
		}
		for (int i = 0; i < objects.length; i++) {
			q.setParameter(i, objects[i]);
		}
		return q.list();
	}

	@Override
	public List<T> findAllBySQL(Class clazz, String sql, Object... objects) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		// 添加实体类
		if (clazz != null) {
			q.addEntity(clazz);
		}
		for (int i = 0; i < objects.length; i++) {
			q.setParameter(i, objects[i]);
		}
		return q.list();
	}
	
	public T getSingleBySQL(Class clazz, String sql, Object... objects){
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		// 添加实体类
		if (clazz != null) {
			q.addEntity(clazz);
		}
		for (int i = 0; i < objects.length; i++) {
			q.setParameter(i, objects[i]);
		}
		List<T> reVaList=q.list();
		if(ValidateUtil.isValidListObject(reVaList)){
			return reVaList.get(0);
		}
		return null;
	}

	
	@Override
	public Long countBySQL(String sql, Object... objects) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		for (int i = 0; i < objects.length; i++) {
			q.setParameter(i, objects[i]);
		}
		return ((BigInteger) q.uniqueResult()).longValue();
	}

	public Double columSum(String sql, Object... objects) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		for (int i = 0; i < objects.length; i++) {
			q.setParameter(i, objects[i]);
		}
		List retVal = q.list();
		if (retVal == null || retVal.isEmpty()) {
			return 0.0;
		} else {
			if (retVal.get(0) == null) {
				return 0.0;
			}
			return (Double) retVal.get(0);
		}
	}

	@Override
	public List<T> getAllBySQL(Class clazz, String sql) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		// 添加实体类
		if (clazz != null) {
			q.addEntity(clazz);
		}
		return q.list();
	}
}

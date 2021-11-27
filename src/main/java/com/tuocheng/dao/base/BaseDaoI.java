package com.tuocheng.dao.base;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

import com.tuocheng.model.demo.TexamAnalys;

/**
 * 基础数据库操作类
 * 
 * @author 农峰
 * 
 */
public interface BaseDaoI<T> {

	/**
	 * 保存一个对象
	 * 
	 * @param o
	 * @return
	 */
	public Serializable save(T o);

	/**
	 * 删除一个对象
	 * 
	 * @param o
	 */
	public void delete(T o);

	/**
	 * 更新一个对象
	 * 
	 * @param o
	 */
	public void update(T o);

	/**
	 * 保存或更新对象
	 * 
	 * @param o
	 */
	public void saveOrUpdate(T o);

	/**
	 * 查询
	 * 
	 * @param hql
	 * @return
	 */
	public List<T> find(String hql);

	/**
	 * 查询集合
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public List<T> find(String hql, Object[] param);

	/**
	 * 查询集合
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public List<T> find(String hql, List<Object> param);

	/**
	 * 查询集合(带分页)
	 * 
	 * @param hql
	 * @param param
	 * @param page
	 *            查询第几页
	 * @param rows
	 *            每页显示几条记录
	 * @return
	 */
	public List<T> find(String hql, Object[] param, Integer page, Integer rows);

	/**
	 * 查询集合(带分页)
	 * 
	 * @param hql
	 * @param param
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<T> find(String hql, List<Object> param, Integer page, Integer rows);

	/**
	 * 获得一个对象
	 * 
	 * @param c
	 *            对象类型
	 * @param id
	 * @return Object
	 */
	public T get(Class<T> c, Serializable id);

	/**
	 * 获得一个对象
	 * 
	 * @param hql
	 * @param param
	 * @return Object
	 */
	public T get(String hql, Object[] param);

	/**
	 * 获得一个对象
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public T get(String hql, List<Object> param);

	/**
	 * select count(*) from 类
	 * 
	 * @param hql
	 * @return
	 */
	public Long count(String hql);

	/**
	 * select count(*) from 类
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public Long count(String hql, Object[] param);

	/**
	 * select count(*) from 类
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public Long count(String hql, List<Object> param);

	/**
	 * 执行HQL语句
	 * 
	 * @param hql
	 * @return 响应数目
	 */
	public Integer executeHql(String hql);

	/**
	 * 执行SQL语句
	 * 
	 * @param sql
	 * @param param
	 */
	public void executeSQL(String sql, Object... object);

	/**
	 * 执行HQL语句
	 * 
	 * @param hql
	 * @param param
	 * @return 响应数目
	 */
	public Integer executeHql(String hql, Object[] param);

	/**
	 * 执行HQL语句
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public Integer executeHql(String hql, List<Object> param);

	/**
	 * 单值检索,确保查询结果有且只有一条记录
	 * 
	 * @param hql
	 * @param objects
	 * @return
	 */
	public Object uniqueResult(String hql, Object... objects);

	/**
	 * 执行原生的sql查询(可以指定是否封装成实体)
	 * 
	 * @param clazz
	 * @param sql
	 * @param objects
	 * @return
	 */
	public List executeSQLQuery(Class clazz, String sql, Object... objects);

	/**
	 * 执行原生的sql查询
	 * 
	 * @param sql
	 * @param param
	 * @return
	 */
	public Long countBySQL(String sql, Object... objects);

	/**
	 * 根据SQL查找实体，返回集合
	 * 
	 * @param clazz
	 * @param sql
	 * @param objects
	 * @return
	 */
	public List<T> findBySQL(Class clazz, String sql, Object... objects);

	/**
	 * 根据sql查找出所有的实体集合
	 * 
	 * @param sql
	 * @return
	 */
	public List<T> findAllBySQL(Class clazz, String sql, Object... objects);

	/**
	 * 统计某列数值，返回Double
	 * 
	 * @param sql
	 * @param objects
	 * @return
	 */
	public Double columSum(String sql, Object... objects);

	public List<T> getAllBySQL(Class clazz, String sql);

	/**
	 * 查询单个实体对象
	 * @param clazz
	 * @param sql
	 * @param objects
	 * @return
	 */
	public T getSingleBySQL(Class clazz, String sql, Object... objects);
	
	/**
	 * 用in查询，查找出所有实体类信息
	 * @param clazz
	 * @param tableName
	 * @param condition
	 * @param ids
	 * @return
	 */
	public List<T> getAllByWhereIn(Class clazz,String tableName,String condition,Collection ids);

}

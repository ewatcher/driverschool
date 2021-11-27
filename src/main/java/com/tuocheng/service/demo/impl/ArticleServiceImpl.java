package com.tuocheng.service.demo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuocheng.dao.base.BaseDaoI;
import com.tuocheng.model.demo.Tarticle;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.Article;
import com.tuocheng.service.demo.ArticleServiceI;

/**
 * 门户网站文章service实现类
 * 
 * @author 李杰
 * 
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleServiceI {

	private BaseDaoI<Tarticle> articleDao;

	// 注入DAO
	@Autowired
	public void setArticleDao(BaseDaoI<Tarticle> articleDao) {
		this.articleDao = articleDao;
	} 

	@Override
	public Article save(Article article) {
		// 1.数据模型转换
		Tarticle t = new Tarticle();
		BeanUtils.copyProperties(article, t);
		// 2.设置相关属性
		t.setId(UUID.randomUUID().toString());
		t.setCreateTime(new Date());
		t.setUpdateTime(new Date());
		// 3.保存数据
		articleDao.save(t);
		// 4.返回结果集
		BeanUtils.copyProperties(t, article);
		return article;
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
					Tarticle t = articleDao.get(Tarticle.class, id);
					// 4.调用DAO层删除数据
					articleDao.delete(t);
				}
			}
		}
	}

	@Override
	public Article update(Article article) {
		// 1.数据模型转换
		Tarticle t = new Tarticle();
		BeanUtils.copyProperties(article, t, new String[] { "id" });
		// 2.设置相关属性
		t.setId(article.getId());
		t.setUpdateTime(new Date());
		// 3.更新数据
		articleDao.update(t);
		// 4.返回结果集
		BeanUtils.copyProperties(t, article);
		return article;
	}

	@Override
	public DataGrid dataGrid(Article article) {
		DataGrid dataGrid = new DataGrid();
		//1.从数据库中查找出所有实体类信息
		//2.查找出的信息进行数据模型转换
		//3.设置展现数据
		dataGrid.setRows(this.changeModel(this.find(article)));
		dataGrid.setTotal(this.total(article));
		return dataGrid;
	}
	
	private List<Tarticle> find(Article article){
		String hql = " from Tarticle t where 1 = 1 ";
		List<Object> params = new ArrayList<Object>();
		hql = addWhere(article, hql, params);

		if (article.getSort() != null && article.getOrder() != null) {
			hql += " order by " + article.getSort() + " " + article.getOrder();
		} else {
			hql += " order by sortNo desc, createTime desc ";
		}

		return articleDao.find(hql, params, article.getPage(), article.getRows());
	}
	
	private List<Article> changeModel(List<Tarticle> ts){
		List<Article> list = new ArrayList<Article>();
		if (ts != null && ts.size() > 0) {
			for (Tarticle t : ts) {
				Article article = new Article();
				BeanUtils.copyProperties(t, article);
				list.add(article);
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
	private String addWhere(Article article, String hql, List<Object> values) {
		
		//类型编号查询
		if (article.getTypeNo() != null && article.getTypeNo() > 0) {
			hql += " and t.typeNo = ? ";
			values.add(article.getTypeNo());
		}
		
		return hql;
	}
	
	/**
	 * 统计符合当前查询条件的总记录数
	 * 
	 * @param difficulty
	 * @return
	 */
	private Long total(Article article) {
		String hql = "select count(*) from Tarticle t where 1 = 1 ";
		List<Object> params = new ArrayList<Object>();
		hql = addWhere(article, hql, params);
		return articleDao.count(hql, params);
	}

	@Override
	public Article get(String id) throws Exception {
		Tarticle t = articleDao.get(Tarticle.class, id);
		if(t != null){
			Article article = new Article();
			BeanUtils.copyProperties(t, article);
			return article;
		}
		return null;
	}

	@Override
	public List<Article> list(Article article) throws Exception {
		return this.changeModel(this.find(article));
	}

}

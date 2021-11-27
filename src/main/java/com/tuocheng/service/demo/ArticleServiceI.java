package com.tuocheng.service.demo;

import java.util.List;

import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.Article;

/**
 * 门户网站文章实体类Service接口
 * @author 李杰
 *
 */
public interface ArticleServiceI {
	/**
	 * 添加实体类信息
	 * @param article
	 * @return
	 */
	public Article save(Article article)throws Exception;
	/**
	 * 删除实体类信息
	 * @param ids
	 */
	public void delete(String ids)throws Exception;
	
	/**
	 * 更新实体类信息
	 * @param article
	 * @return
	 */
	public Article update(Article article)throws Exception;
	
	/**
	 * 查找出后台所有该实体类的信息，并以datagrid形式传递给前台
	 * @param article
	 * @return
	 */
	public DataGrid dataGrid(Article article)throws Exception;
	
	/**
	 * 获取实体类列表
	 * @param article
	 * @return
	 * @throws Exception
	 */
	public List<Article> list(Article article) throws Exception; 
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Article get(String id) throws Exception;
	
	
}

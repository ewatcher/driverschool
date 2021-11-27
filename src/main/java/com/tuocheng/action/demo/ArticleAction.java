package com.tuocheng.action.demo;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.BaseAction;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.base.SessionInfo;
import com.tuocheng.pageModel.demo.Article;
import com.tuocheng.service.demo.ArticleServiceI;
import com.tuocheng.util.base.ExceptionUtil;
import com.tuocheng.util.base.ResourceUtil;
import com.tuocheng.util.base.Util;

/**
 * 门户网站文章action
 * 
 * @author 李杰
 * 
 */

@Namespace("/demo")
@Action(value = "articleAction", results = {
		@Result(name = "article", location = "/demo/web/article.jsp"),
		@Result(name = "articleAdd", location = "/demo/web/articleAdd.jsp"), 
		@Result(name = "articleEdit", location = "/demo/web/articleEdit.jsp") })
public class ArticleAction extends BaseAction<Article> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 82347590280981L;

	/**
	 * 序列号
	 */
	private static final Logger log = Logger.getLogger(ArticleAction.class);
	
	private ArticleServiceI articleService;

	@Autowired
	public void setArticleService(ArticleServiceI articleService) {
		this.articleService = articleService;
	}

	// 文章列表页面
	public String article() {
		return "article";
	}
	
	// 到文章新增页面
	public String articleAdd() {
		return "articleAdd";
	}
	
	// 到文章编辑页面
	public String articleEdit() {
		return "articleEdit";
	}

	// 从后台中查找出所有实体类信息
	public void datagrid() throws Exception {
		super.writeJson(articleService.dataGrid(model));
	} 

	// 添加实体类信息
	public void save() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		SessionInfo sessionInfo = null;
		if(session.getAttribute(ResourceUtil.getSessionInfoName()) != null){
			sessionInfo = (SessionInfo)session.getAttribute(ResourceUtil.getSessionInfoName());
		}
		model.setAuthor(sessionInfo != null ? sessionInfo.getLoginName() : "未知");
		Json json = new Json();
		try {
			System.out.println(model);
			if(Util.isNotNull(model.getId())){
				// 如果ID 不是空的，则修改
				Article article = articleService.update(model);
				json.setSuccess(true);
				json.setMsg("修改数据成功！");
				//json.setObj(article);
			} else {
				Article article = articleService.save(model);
				json.setSuccess(true);
				json.setMsg("添加数据成功！");
				//json.setObj(article);
			}
		} catch (Exception e) {
			json.setMsg("数据 添加/修改 失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		// 将数据返回给前台
		super.writeJson(json);
	}

	// 删除实体类信息
	public void delete() {
		Json json = new Json();
		try {
			articleService.delete(model.getIds());
			json.setSuccess(true);
			json.setMsg("删除数据成功！");
		} catch (Exception e) {
			json.setMsg("删除数据失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

}

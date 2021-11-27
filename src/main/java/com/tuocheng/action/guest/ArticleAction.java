package com.tuocheng.action.guest;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.GuestAction;
import com.tuocheng.model.demo.TweixinUser;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.Article;
import com.tuocheng.service.demo.ArticleServiceI;
import com.tuocheng.service.demo.StudentServiceI;
import com.tuocheng.service.demo.WeixinUserServiceI;
import com.tuocheng.util.base.Util;

/**
 * 微信报名action
 * 
 * @author 李杰
 * 
 */

@Namespace("/web")
@Action(value = "articleAction", results = {
		@Result(name = "info", location = "/web/blog.jsp"),
		@Result(name = "news", location = "/web/news.jsp"),
		@Result(name = "berkeley", location = "/web/berkeley.jsp"),
		@Result(name = "enterprise", location = "/web/enterprise.jsp"),
		@Result(name = "service", location = "/web/service.jsp"),
		@Result(name = "building", location = "/web/building.jsp"),
		@Result(name = "contact", location = "/web/contact.jsp"),
		@Result(name = "notify", location = "/web/notify.jsp"),
		@Result(name = "newslist", location = "/web/newslist.jsp"),
		@Result(name = "buildinglist", location = "/web/buildinglist.jsp"),
		@Result(name = "index", location = "/web/index.jsp") })
public class ArticleAction extends GuestAction<Article> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 82347590280981L;

	/**
	 * 序列号
	 */
	private static final Logger log = Logger.getLogger(ArticleAction.class);

	private ArticleServiceI articleService;
	private StudentServiceI studentService;
	private WeixinUserServiceI weixinUserService;

	@Autowired
	public void setStudentService(StudentServiceI studentService) {
		this.studentService = studentService;
	}

	@Autowired
	public void setWeixinUserService(WeixinUserServiceI weixinUserService) {
		this.weixinUserService = weixinUserService;
	}

	@Autowired
	public void setArticleService(ArticleServiceI articleService) {
		this.articleService = articleService;
	} 
	
	public void toIndexPage() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.sendRedirect(request.getContextPath() + "/web/articleAction!index.action");
	}
	
	/**
	 * 增加微信难点反馈信息。
	 * @return
	 * @throws Exception 
	 */
	public String info() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String articleId = Util.objToString(request.getParameter("id"), "");
		Article article = articleService.get(articleId);
		if(article == null){
			article = new Article();
		}
		request.setAttribute("title", article.getTitle());
		request.setAttribute("typeNo", article.getTypeNo());
		request.setAttribute("content", article.getContent());
		
		switch(article.getTypeNo()){
		case 1:{
			Article a = new Article();
			a.setTypeNo(1);
			a.setRows(5);
			DataGrid dataGrid = articleService.dataGrid(a);
			request.setAttribute("dataGrid", dataGrid);
			return "news"; // 新闻中心
		}
		case 2:
			return "berkeley"; // 分校
		case 3:
			return "enterprise"; // 集团介绍
		case 4:
			return "service"; // 为您服务
		case 5:{
			Article a = new Article();
			a.setTypeNo(5);
			a.setRows(5);
			DataGrid dataGrid = articleService.dataGrid(a);
			request.setAttribute("dataGrid", dataGrid);
			return "building"; // 党团建设
		}
		case 6:
			return "contact"; // 联系我们
		case 7:
			return "notify"; // 通知
		default:
			return "info";
		}
	}
	
	public String newslist() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		if(model == null) model = new Article();
		model.setTypeNo(1);
		DataGrid dataGrid = articleService.dataGrid(model);
		request.setAttribute("typeNo", 1);
		request.setAttribute("dataGrid", dataGrid);
		return "newslist";
	}
	
	public String buildinglist() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		if(model == null) model = new Article();
		model.setTypeNo(5);
		DataGrid dataGrid = articleService.dataGrid(model);
		request.setAttribute("typeNo", 5);
		request.setAttribute("dataGrid", dataGrid);
		return "buildinglist";
	}
	
	public String index() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		if(model == null) model = new Article();
		model.setTypeNo(5);
		DataGrid dataGrid = articleService.dataGrid(model);
		request.setAttribute("typeNo", 0);
		request.setAttribute("dataGrid", dataGrid);
		
		return "index";
	}
	
}

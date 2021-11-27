package com.tuocheng.action.guest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.GuestAction;
import com.tuocheng.model.demo.Torganization;
import com.tuocheng.model.demo.Tstudent;
import com.tuocheng.model.demo.TweixinUser;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.Article;
import com.tuocheng.pageModel.demo.Assess;
import com.tuocheng.pageModel.demo.CommentStudent;
import com.tuocheng.pageModel.demo.Difficulty;
import com.tuocheng.pageModel.demo.Organization;
import com.tuocheng.pageModel.demo.StudentTimerCard;
import com.tuocheng.service.demo.ArticleServiceI;
import com.tuocheng.service.demo.AssessServiceI;
import com.tuocheng.service.demo.CommentStudentServiceI;
import com.tuocheng.service.demo.DifficultyServiceI;
import com.tuocheng.service.demo.OrganizationServiceI;
import com.tuocheng.service.demo.StudentServiceI;
import com.tuocheng.service.demo.WeixinUserServiceI;
import com.tuocheng.util.base.ValidateUtil;
import com.tuocheng.util.base.WeixinUtil;
import com.tuocheng.util.base.Util;
import com.tuocheng.weixin.base.Weixin;
import com.tuocheng.weixin.web.WeixinJsBean;
import com.tuocheng.weixin.web.WeixinJsService;

/**
 * 微信报名action
 * 
 * @author 李杰
 * 
 */

@Namespace("/m")
@Action(value = "articleAction", results = {
		@Result(name = "info", location = "/m/info.jsp"),
		@Result(name = "images", location = "/m/images.jsp"),
		@Result(name = "newslist", location = "/m/newslist.jsp"),
		@Result(name = "secretslist", location = "/m/secretslist.jsp"),
		@Result(name = "student", location = "/m/student.jsp"),
		@Result(name = "notice", location = "/m/notice.jsp"),
		@Result(name = "tariff", location = "/m/tariff.jsp"),
		@Result(name = "index", location = "/m/index.jsp"),
		@Result(name = "myDifficulty", location = "/m/my_difficulty.jsp"),
		@Result(name = "myAssess", location = "/m/my_assess.jsp"),
		@Result(name = "myEvaluate", location = "/m/my_evaluate.jsp"),
		@Result(name = "studySchedule", location = "/m/study_schedule.jsp"),
		@Result(name = "monitorIndex", location = "/m/monitorIndex.jsp")})
public class MobileArticleAction extends GuestAction<Article> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 82347590280981L;

	/**
	 * 序列号
	 */
	private static final Logger log = Logger
			.getLogger(MobileArticleAction.class);

	private ArticleServiceI articleService;
	private WeixinUserServiceI weixinUserService;
	private StudentServiceI studentService;
	private DifficultyServiceI difficultyService;
	private AssessServiceI assessService;
	private CommentStudentServiceI commentStudentService;
	private OrganizationServiceI orgService;

	@Autowired
	public void setArticleService(ArticleServiceI articleService) {
		this.articleService = articleService;
	}

	@Autowired
	public void setWeixinUserService(WeixinUserServiceI weixinUserService) {
		this.weixinUserService = weixinUserService;
	}

	@Autowired
	public void setStudentService(StudentServiceI studentService) {
		this.studentService = studentService;
	}

	@Autowired
	public void setDifficultyService(DifficultyServiceI difficultyService) {
		this.difficultyService = difficultyService;
	}

	@Autowired
	public void setAssessService(AssessServiceI assessService) {
		this.assessService = assessService;
	}

	@Autowired
	public void setCommentStudentService(
			CommentStudentServiceI commentStudentService) {
		this.commentStudentService = commentStudentService;
	}

	@Autowired
	public void setOrgService(OrganizationServiceI orgService) {
		this.orgService = orgService;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public String info() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String articleId = Util.objToString(request.getParameter("id"), "");
		Article article = articleService.get(articleId);
		if (article == null) {
			article = new Article();
		}
		request.setAttribute("title", article.getTitle());
		request.setAttribute("createTime", article.getCreateTime());
		request.setAttribute("content", article.getContent());
		return "info";
	}

	public String images() throws Exception {
		return "images";
	}

	public String newslist() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (model == null)
			model = new Article();
		model.setRows(50);
		model.setTypeNo(1);
		DataGrid newsData = articleService.dataGrid(model);
		request.setAttribute("newsData", newsData);
		return "newslist";
	}

	public String secretslist() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (model == null)
			model = new Article();
		model.setRows(50);
		model.setTypeNo(7);
		DataGrid secretsData = articleService.dataGrid(model);
		request.setAttribute("secretsData", secretsData);
		return "secretslist";
	}

	public String index() throws Exception {

		ServletContext application = ServletActionContext.getServletContext();
		HttpSession session = ServletActionContext.getRequest().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();

		String orgId = Util.objToString(request.getParameter("orgId"), "");
		if (Util.isNotNull(orgId) && orgId.length() > 20) {
			session.setAttribute("orgId", orgId);
			Torganization org = orgService.getSingleById(orgId);
			session.setAttribute("schoolAreaName", org.getName());
		}

		Weixin weixin = new WeixinUtil().getWeixinByOrganizationId(orgId);
		if (weixin != null) {
			WeixinJsService wJsService = new WeixinJsService(weixin);
			WeixinJsBean wJsBean = wJsService.getJsParams(application, request);
			request.setAttribute("appId", wJsBean.getAppId());
			request.setAttribute("timestamp", wJsBean.getTimestamp());
			request.setAttribute("nonceStr", wJsBean.getNonceStr());
			request.setAttribute("signature", wJsBean.getSignature());
		}

		if (model == null)
			model = new Article();
		model.setRows(6);
		model.setTypeNo(1);
		DataGrid newsData = articleService.dataGrid(model);
		request.setAttribute("newsData", newsData);

		model.setTypeNo(7);
		DataGrid secretsData = articleService.dataGrid(model);
		request.setAttribute("secretsData", secretsData);
		return "index";
	}
	
	public String monitorIndex() throws Exception {

		ServletContext application = ServletActionContext.getServletContext();
		HttpSession session = ServletActionContext.getRequest().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();

		String orgId = Util.objToString(request.getParameter("orgId"), "");
		if (Util.isNotNull(orgId) && orgId.length() > 20) {
			session.setAttribute("orgId", orgId);
			Torganization org = orgService.getSingleById(orgId);
			session.setAttribute("schoolAreaName", org.getName());
		}

		Weixin weixin = new WeixinUtil().getWeixinByOrganizationId(orgId);
		if (weixin != null) {
			WeixinJsService wJsService = new WeixinJsService(weixin);
			WeixinJsBean wJsBean = wJsService.getJsParams(application, request);
			request.setAttribute("appId", wJsBean.getAppId());
			request.setAttribute("timestamp", wJsBean.getTimestamp());
			request.setAttribute("nonceStr", wJsBean.getNonceStr());
			request.setAttribute("signature", wJsBean.getSignature());
		}

		if (model == null)
			model = new Article();
		model.setRows(6);
		model.setTypeNo(1);
		DataGrid newsData = articleService.dataGrid(model);
		request.setAttribute("newsData", newsData);
		return "monitorIndex";
	}

	public String student() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = ServletActionContext.getRequest().getSession();
		String openId = Util.objToString(session.getAttribute("weixinOpenId"),
				"");
		String orgId = Util.objToString(request.getParameter("orgId"), "");
		if (Util.isNotNull(orgId) && orgId.length() > 20) {
			session.setAttribute("orgId", orgId);
		}

		boolean flag = false;
		if (Util.isNotNull(openId)) {
			TweixinUser tUser = weixinUserService.getByOpenId(openId);
			if (tUser != null && Util.isNotNull(tUser.getStudentId())) {
				Tstudent student = studentService.getSingleById(tUser
						.getStudentId());
				if (student != null) {
					request.setAttribute("studentName", student.getName());
					request.setAttribute("weixinUser", tUser.getName());
					request.setAttribute("visit", tUser.getVisit());
					flag = true;
				}
			}
		}

		if (!flag) {
			request.setAttribute("studentName", "未绑定学员信息");
			request.setAttribute("weixinUser", "未绑定学员信息");
			request.setAttribute("visit", 0);
		}
		if (model == null)
			model = new Article();
		return "student";
	}

	public String studySchedule() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = ServletActionContext.getRequest().getSession();
		String openId = Util.objToString(session.getAttribute("weixinOpenId"),
				"");
		String orgId = Util.objToString(request.getParameter("orgId"), "");
		if (Util.isNotNull(orgId) && orgId.length() > 20) {
			session.setAttribute("orgId", orgId);
		}
		boolean flag = false;
		if (Util.isNotNull(openId)) {
			TweixinUser tUser = weixinUserService.getByOpenId(openId);
			if (tUser != null && Util.isNotNull(tUser.getStudentId())) {
				Tstudent student = studentService.getSingleById(tUser
						.getStudentId());
				if (student != null) {
					request.setAttribute("studentName", student.getName());
					request.setAttribute("weixinUser", tUser.getName());
					request.setAttribute("visit", tUser.getVisit());
					request.setAttribute(
							"timerCard",
							student.getStudentTimerCard() != null ? student
									.getStudentTimerCard()
									: new StudentTimerCard());
					flag = true;
				}
			}
		}

		if (!flag) {
			request.setAttribute("studentName", "未绑定学员信息");
			request.setAttribute("weixinUser", "未绑定学员信息");
			request.setAttribute("visit", 0);
			request.setAttribute("timerCard", new StudentTimerCard());
		}

		return "studySchedule";
	}

	public String myDifficulty() throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = ServletActionContext.getRequest().getSession();
		String openId = Util.objToString(session.getAttribute("weixinOpenId"),
				"");
		String orgId = Util.objToString(request.getParameter("orgId"), "");
		if (Util.isNotNull(orgId) && orgId.length() > 20) {
			session.setAttribute("orgId", orgId);
			Torganization org = orgService.getSingleById(orgId);
			session.setAttribute("schoolAreaName", org.getName());
		}
		boolean flag = false;
		if (Util.isNotNull(openId)) {
			TweixinUser tUser = weixinUserService.getByOpenId(openId);
			if (tUser != null && Util.isNotNull(tUser.getStudentId())) {
				Tstudent student = studentService.getSingleById(tUser
						.getStudentId());
				if (student != null) {
					Difficulty difficulty = new Difficulty();
					difficulty.setOpenId(openId);
					difficulty.setRows(20);
					difficulty.setSort("createTime");
					difficulty.setOrder("desc");
					List<Difficulty> list = difficultyService
							.listData(difficulty);

					request.setAttribute("studentName", student.getName());
					request.setAttribute("weixinUser", tUser.getName());
					request.setAttribute("visit", tUser.getVisit());
					request.setAttribute("difList", list != null
							&& list.size() > 0 ? list : null);
					flag = true;
				}
			}
		}

		if (!flag) {
			request.setAttribute("studentName", "未绑定学员信息");
			request.setAttribute("weixinUser", "未绑定学员信息");
			request.setAttribute("visit", 0);
			request.setAttribute("difList", null);
		}

		return "myDifficulty";
	}

	public String myAssess() throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = ServletActionContext.getRequest().getSession();
		String openId = Util.objToString(session.getAttribute("weixinOpenId"),
				"");

		boolean flag = false;
		if (Util.isNotNull(openId)) {
			TweixinUser tUser = weixinUserService.getByOpenId(openId);
			if (tUser != null && Util.isNotNull(tUser.getStudentId())) {
				Tstudent student = studentService.getSingleById(tUser
						.getStudentId());
				if (student != null) {
					Assess assess = new Assess();
					assess.setOpenId(openId);
					assess.setRows(20);
					assess.setSort("createTime");
					assess.setOrder("desc");
					DataGrid dataGrid = assessService.dataGrid(assess);

					request.setAttribute("studentName", student.getName());
					request.setAttribute("weixinUser", tUser.getName());
					request.setAttribute("visit", tUser.getVisit());
					request.setAttribute(
							"assessList",
							dataGrid != null && dataGrid.getRows().size() > 0 ? dataGrid
									.getRows() : null);
					flag = true;
				}
			}
		}

		if (!flag) {
			request.setAttribute("studentName", "未绑定学员信息");
			request.setAttribute("weixinUser", "未绑定学员信息");
			request.setAttribute("visit", 0);
			request.setAttribute("assessList", null);
		}

		return "myAssess";
	}

	public String myEvaluate() throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = ServletActionContext.getRequest().getSession();
		String openId = Util.objToString(session.getAttribute("weixinOpenId"),
				"");

		boolean flag = false;
		if (Util.isNotNull(openId)) {
			TweixinUser tUser = weixinUserService.getByOpenId(openId);
			if (tUser != null && Util.isNotNull(tUser.getStudentId())) {
				Tstudent student = studentService.getSingleById(tUser
						.getStudentId());
				if (student != null) {
					CommentStudent cs = new CommentStudent();
					cs.setStudentId(tUser.getStudentId());
					cs.setRows(20);
					cs.setSort("commentTime");
					cs.setOrder("desc");
					DataGrid dataGrid = commentStudentService.dataGrid(cs);

					request.setAttribute("studentName", student.getName());
					request.setAttribute("weixinUser", tUser.getName());
					request.setAttribute("visit", tUser.getVisit());
					request.setAttribute(
							"commentList",
							dataGrid != null && dataGrid.getRows().size() > 0 ? dataGrid
									.getRows() : null);
					flag = true;
				}
			}
		}

		if (!flag) {
			request.setAttribute("studentName", "未绑定学员信息");
			request.setAttribute("weixinUser", "未绑定学员信息");
			request.setAttribute("visit", 0);
			request.setAttribute("commentList", null);
		}

		return "myEvaluate";
	}

	public String tariff() throws Exception {
		// 根据校区ＩＤ标识显示学校名称
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = ServletActionContext.getRequest().getSession();
		String orgId = Util.objToString(request.getParameter("orgId"), "");
		if (Util.isNotNull(orgId) && orgId.length() > 20) {
			session.setAttribute("orgId", orgId);
			Torganization org = orgService.getSingleById(orgId);
			session.setAttribute("schoolAreaName", org.getName());
		}
		return "tariff";
	}

	public String notice() throws Exception {
		// 根据校区ＩＤ标识显示学校名称
		HttpServletRequest request = ServletActionContext.getRequest();
		String orgId = Util.objToString(request.getParameter("orgId"), "");
		if (ValidateUtil.isValid(orgId)) {
			Torganization org = orgService.getSingleById(orgId);
			request.setAttribute("schoolAreaName", org.getName());
		}
		return "notice";
	}
}

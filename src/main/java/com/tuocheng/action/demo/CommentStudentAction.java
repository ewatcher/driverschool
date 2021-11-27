package com.tuocheng.action.demo; 

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.BaseAction;
import com.tuocheng.model.demo.TweixinUser;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.demo.CommentStudent;
import com.tuocheng.pageModel.demo.Student;
import com.tuocheng.service.demo.CommentStudentServiceI;
import com.tuocheng.service.demo.StudentServiceI;
import com.tuocheng.service.demo.WeixinUserServiceI;
import com.tuocheng.util.base.WeixinUtil;
import com.tuocheng.util.base.ExceptionUtil;
import com.tuocheng.util.base.Util;
import com.tuocheng.util.base.ValidateUtil;
import com.tuocheng.weixin.base.Weixin;
import com.tuocheng.weixin.message.WeixinMessageService;

/**
 * 点评学员action
 * 
 * @author 李杰
 * 
 */

@Namespace("/demo")
@Action(value = "commentStudentAction", results = {
		@Result(name = "studentList", location = "/demo/commentStudent/studentList.jsp") })
public class CommentStudentAction extends BaseAction<CommentStudent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 82347590280981L;

	/**
	 * 序列号
	 */
	private static final Logger log = Logger.getLogger(CommentStudentAction.class);

	private StudentServiceI studentService;
	private CommentStudentServiceI commentStudentService;
	private WeixinUserServiceI wUserService;

	@Autowired
	public void setStudentService(StudentServiceI studentService) {
		this.studentService = studentService;
	}
	
	@Autowired
	public void setCommentStudentService(CommentStudentServiceI commentStudentService){
		this.commentStudentService = commentStudentService;
	}
	
	@Autowired
	public void setWeixinUserService(WeixinUserServiceI wUserService){
		this.wUserService = wUserService;
	}

	// 到学员列表页面
	public String studentList() {
		return "studentList";
	}
	
	public void getStudentDatagrid(){
		try {
			Student student = new Student();
			
			if(Util.isNotNull(model.getOrganizationName())){
				student.setOrganizationName(model.getOrganizationName());
			}
			if(Util.isNotNull(model.getStudentNo())){
				student.setStudentNo(model.getStudentNo());
			}
			if(Util.isNotNull(model.getStudentName())){
				student.setName(model.getStudentName());
			}
			if(Util.isNotNull(model.getStudentIdentityId())){
				student.setIdentityId(model.getStudentIdentityId());
			}
			
			student.setPage(model.getPage());
			student.setRows(model.getRows());
			student.setSort(model.getSort());
			student.setOrder(model.getOrder());
			
			if (ValidateUtil.isValid(user.getSchoolArea())) {
				student.setOrganizationName(user.getSchoolArea());
			}
			
			super.writeJson(studentService.datagrid(student));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addComment(){
		Json json = new Json();
		try {
			//System.out.println(model);
			CommentStudent commStd = commentStudentService.save(model);
			json.setSuccess(true);
			json.setMsg("添加数据成功！");
			json.setObj(commStd);
			
			// wUserService
			if(Util.isNotNull(model.getStudentId())){
				TweixinUser wUser = wUserService.getByStudentId(model.getStudentId());
				if(wUser != null){
					Weixin weixin = new WeixinUtil().getWeixinByOrganizationId(wUser.getOrganizationId());
		        	WeixinMessageService sm = new WeixinMessageService(weixin);
			        sm.postCustomServiceMessage(ServletActionContext.getServletContext(), wUser.getOpenId(), "教练已对您做出了最新的点评,进入驾校微信公众号-学员服务-我的点评中查看!");
				}
			}
		} catch (Exception e) {
			json.setMsg("添加数据失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		// 将数据返回给前台
		super.writeJson(json);
	}

	// 从后台中查找出所有实体类信息
	public void datagrid() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String studentId = Util.objToString(request.getParameter("studentId"), "");
		model.setStudentId(studentId);
		if(!Util.isNotNull(model.getSort())){
			model.setSort("commentTime");
			model.setOrder("desc");
		}
		DataGrid dataGrid = commentStudentService.dataGrid(model);
		super.writeJson(dataGrid);
	}

	// 更新实体类信息
	public void edit() {
		Json json = new Json();
		try {
			CommentStudent commStd = commentStudentService.update(model);
			json.setSuccess(true);
			json.setMsg("修改数据成功！");
			json.setObj(commStd);
		} catch (Exception e) {
			json.setMsg("修改数据失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		// 将数据返回给前台
		super.writeJson(json);
	}

	// 删除实体类信息
	public void delete() {
		Json json = new Json();
		try {
			commentStudentService.delete(model.getIds());
			json.setSuccess(true);
			json.setMsg("删除数据成功！");
		} catch (Exception e) {
			json.setMsg("删除数据失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

}

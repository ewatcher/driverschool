package com.tuocheng.action.guest;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.SimpleAction;
import com.tuocheng.model.demo.TweixinUser;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.demo.CommentStudent;
import com.tuocheng.service.demo.CommentStudentServiceI;
import com.tuocheng.service.demo.WeixinUserServiceI;
import com.tuocheng.util.base.Util;

/**
 * 微信预约action
 * 
 * @author 李杰
 * 
 */

@Namespace("/guest")
@Action(value = "commentStudentAction", results = {
		@Result(name = "addSuccess", location = "/m/success.jsp"),
		@Result(name = "addErr", location = "/m/error.jsp") } )
public class commentStudentAction extends SimpleAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 82347590280981L;

	/**
	 * 序列号
	 */
	private static final Logger log = Logger.getLogger(commentStudentAction.class);

	private CommentStudentServiceI commentStudentService;
	private WeixinUserServiceI weixinUserService;
	
	@Autowired
	public void setCommentStudentService(CommentStudentServiceI commentStudentService) {
		this.commentStudentService = commentStudentService;
	} 
	
	@Autowired
	public void setWeixinUserService(WeixinUserServiceI weixinUserService) {
		this.weixinUserService = weixinUserService;
	}
	
	/**
	 * 获取某学员的点评
	 */
	public void getCommentByStudentList(){
		HashMap<String, Object> hm = new HashMap<String, Object>();
		try{
			HttpSession session = ServletActionContext.getRequest().getSession();
			String openId = Util.objToString(session.getAttribute("weixinOpenId"), "");
			if(Util.isNotNull(openId)){
				TweixinUser tUser = weixinUserService.getByOpenId(openId);
				if(tUser != null && Util.isNotNull(tUser.getStudentId())){
					hm.put("code", 1);
					hm.put("msg", "学员已绑定微信号！");
					
					CommentStudent cs = new CommentStudent();
					cs.setStudentId(tUser.getStudentId());
					cs.setRows(100);
					cs.setSort("commentTime");
					cs.setOrder("desc");
					DataGrid dataGrid = commentStudentService.dataGrid(cs);
					hm.put("rows", dataGrid != null ? dataGrid.getRows() : null);
				} else {
					hm.put("code", -2);
					hm.put("msg", "该学员未绑定微信号！");
				}
			} else {
				hm.put("code", -1);
				hm.put("msg", "读取openId失败！");
			}
		} catch(Exception ex){
			hm.put("code", -10);
			hm.put("msg", "错误:" + ex.toString());
		}
		super.writeJson(hm);
	}
	
	
	
}

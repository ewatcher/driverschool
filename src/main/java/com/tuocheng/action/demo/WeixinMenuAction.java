package com.tuocheng.action.demo;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.tuocheng.action.base.BaseAction;
import com.tuocheng.model.demo.TweixinMenu;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.demo.WeixinMenu;
import com.tuocheng.service.demo.WeixinMenuServiceI;
import com.tuocheng.util.base.WeixinUtil;
import com.tuocheng.util.base.WeixinUtil.WeixinParams;
import com.tuocheng.util.base.ExceptionUtil;
import com.tuocheng.util.base.ResourceUtil;
import com.tuocheng.util.base.Util;
import com.tuocheng.util.base.ValidateUtil;
import com.tuocheng.weixin.base.Weixin;
import com.tuocheng.weixin.menu.WeixinMenuService;

/**
 * 微信用户action
 * 
 * @author 李杰
 * 
 */

@Namespace("/demo")
@Action(value = "weixinMenuAction", results = {
		@Result(name = "menu", location = "/demo/weixin/menu.jsp") })
public class WeixinMenuAction extends BaseAction<WeixinMenu> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 82347590280981L;

	/**
	 * 序列号
	 */
	private static final Logger log = Logger.getLogger(WeixinMenuAction.class);
	
	private WeixinMenuServiceI weixinMenuService;

	@Autowired
	public void setWeixinMenuService(WeixinMenuServiceI weixinMenuService) {
		this.weixinMenuService = weixinMenuService;
	} 

	// 到微信自定义菜单页面
	public String menu() {
		return "menu";
	}

	// 从后台中查找出所有实体类信息
	public void datagrid() {
 		String json = "";
		
		if (Util.isNotNull(user.getSchoolArea())) {
			model.setOrganizationId(user.getSchoolArea());
		} else {
			if(!Util.isNotNull(model.getOrganizationId())) model.setOrganizationId("0000");
		}
		
		DataGrid dataGrid = weixinMenuService.dataGrid(model);
		if(dataGrid != null){
			String str = JSON.toJSONStringWithDateFormat(dataGrid, "yyyy-MM-dd HH:mm:ss");
			//System.out.println(str);
			json = str.replaceAll("tmpParentId", "_parentId");
			json = json.replaceAll("\"_parentId\":0,", "");
			//System.out.println(json);
			//json = str;
		}
		
		try {
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(json);
			ServletActionContext.getResponse().getWriter().flush();
			ServletActionContext.getResponse().getWriter().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	// 更新实体类信息
	public void edit() {
		Json json = new Json();
		if(model.getNickname().length() > 0 && model.getNickname().length() < 6){
			try {
				WeixinMenu weixinMenu = weixinMenuService.update(model);
				json.setSuccess(true);
				json.setMsg("修改数据成功！");
				json.setObj(weixinMenu);
			} catch (Exception e) {
				json.setMsg("修改数据失败！");
				ExceptionUtil.getExceptionMessage(e);
			}
		} else {
			json.setMsg("名称长度不符！只能输入1-5个字符！");
		}
		
		// 将数据返回给前台
		super.writeJson(json);
	}
	
	/**
	 * 同步自定义菜单到微信
	 */
	public void push(){
		Json json = new Json();
		try{
			if(Util.isNotNull(model.getOrganizationId())){
				List<TweixinMenu> list = weixinMenuService.find(model);
				
				if(list != null){
					WeixinUtil appUtil = new WeixinUtil();
					WeixinParams wxParams = appUtil.getWeixinParamsByOrganizationId(model.getOrganizationId());
					HashMap<String, Object> hm = new HashMap<String, Object>();
					List<HashMap<String, Object>> listMap = getListByCidOrderBy(wxParams, list, null);
					hm.put("button", listMap);
					
					String menuJson = JSON.toJSONString(hm);
					System.out.println(menuJson);
					
					ServletContext application = ServletActionContext.getServletContext();
					Weixin weixin = appUtil.getWeixinByOrganizationId(model.getOrganizationId());
					WeixinMenuService menu = new WeixinMenuService(weixin);
					int code = menu.createMenu(application, menuJson);
					if(code > 0){
						json.setSuccess(true);
						json.setMsg("设置成功：大概5分钟后新菜单生效！");
					} else {
						json.setMsg("设置失败：错误代码 " + code);
					}
				} else {
					json.setMsg("读取数据失败！");
				}
			} else {
				json.setMsg("机构信息不明确！");
			}
		} catch(Exception ex){
			json.setMsg("错误:" + ex.toString());
		}
		// 将数据返回给前台
		super.writeJson(json);
	}
	
	private List<HashMap<String, Object>> getListByCidOrderBy(WeixinParams wxParams, List<TweixinMenu> list, TweixinMenu tm){
		
		List<HashMap<String, Object>> listMap = new ArrayList<HashMap<String, Object>>();
		
		int cid = tm != null ? tm.getId() : 0;
		
		List<TweixinMenu> arry = new ArrayList<TweixinMenu>();
		for(int i = 0; list != null && i < list.size(); i++){
			TweixinMenu t = list.get(i);
			if(t.getStatus() > 0 && t.getCid() == cid){
				arry.add(t);
			}
		}
		
		if(arry != null){
			Collections.sort(arry, new Comparator<TweixinMenu>() {
				@Override
				public int compare(TweixinMenu o1, TweixinMenu o2) {
					return o1.getReorder() > o2.getReorder() ? 1 : -1;
				}
			});
			
			for(int i = 0; i < arry.size(); i++ ){
				HashMap<String, Object> hm = new HashMap<String, Object>();
				TweixinMenu t = arry.get(i);
				if("menu".equalsIgnoreCase(t.getType())){
					hm.put("name", t.getNickname());
					hm.put("sub_button", getListByCidOrderBy(wxParams, list, t));
				} else if("click".equalsIgnoreCase(t.getType())) {
					hm.put("type", "click");
					hm.put("name", t.getNickname());
					hm.put("key", t.getKeyname());
				} else if("view".equalsIgnoreCase(t.getType())) {
					hm.put("type", "view");
					hm.put("name", t.getNickname());
					hm.put("url", getUrl(wxParams, t.getContent()));
				}
				listMap.add(hm);
			}
		}
		
		
		return listMap;
	}
	
	/**
	 * 得到完整URL地址
	 * @param sysUrl
	 * @return
	 */
	private String getUrl(WeixinParams wxParams, String sysUrl){
		String resultUrl = "";
		if(sysUrl == null) return "";
		if(!sysUrl.startsWith("/")) return sysUrl;
		try {
			URL url = new URL(wxParams.getDomainName());
			String contextPath = ServletActionContext.getRequest().getContextPath();
			if(url != null){
				resultUrl = url.getProtocol() + "://" + url.getAuthority() + contextPath + sysUrl + "?orgId=" + wxParams.getOrganizationId();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultUrl;
	}

}

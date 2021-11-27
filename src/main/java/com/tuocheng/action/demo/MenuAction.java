package com.tuocheng.action.demo;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.BaseAction;
import com.tuocheng.pageModel.base.ComboboxJson;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.base.TreeNode;
import com.tuocheng.pageModel.demo.Menu;
import com.tuocheng.service.demo.MenuServiceI;
import com.tuocheng.util.base.ExceptionUtil;

/**
 * 菜单ACTION
 * 
 * @author 农峰
 * 
 */
@Namespace("/demo")
@Action(value = "menuAction", results = { @Result(name = "menu", location = "/demo/admin/menu.jsp"),
		@Result(name = "menuAdd", location = "/demo/admin/menuAdd.jsp"),
		@Result(name = "menuEdit", location = "/demo/admin/menuEdit.jsp") })
public class MenuAction extends BaseAction<Menu> {

	/**
	 * 序列化标识符
	 */
	private static final long serialVersionUID = 1098257149252975699L;

	private static final Logger logger = Logger.getLogger(MenuAction.class);

	private MenuServiceI demoMenuService;

	public MenuServiceI getDemoMenuService() {
		return demoMenuService;
	}

	@Autowired
	public void setDemoMenuService(MenuServiceI demoMenuService) {
		this.demoMenuService = demoMenuService;
	}

	/**
	 * 获取主菜单
	 */
	public void toGainMainMenus() {
		try {
			super.writeJson(demoMenuService.getMainMenus());
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	/**
	 * 根据节点标识获取节点信息
	 */
	public void toGainSingleNode() {
		try {
			List<TreeNode> nodes = demoMenuService.getSingleNode(model.getId(), true);
			super.writeJson(nodes);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}

	/**
	 * 首页获得菜单树
	 * 
	 * @throws Exception
	 */
	public void doNotNeedSession_tree() throws Exception {
		super.writeJson(demoMenuService.getAuthTree(model, false, user));
	}

	public void doNotNeedSession_treeRecursive() throws Exception {
		super.writeJson(demoMenuService.tree(model, true));
	}

	public String menu() {
		return "menu";
	}

	/**
	 * 获得菜单treegrid
	 * 
	 * @throws Exception
	 */
	public void treegrid() throws Exception {
		super.writeJson(demoMenuService.treegrid(model));
	}
	
	public void datagrid(){
		super.writeJson(demoMenuService.datagrid(model));
	}

	/**
	 * 删除一个菜单
	 */
	public void delete() {
		Json j = new Json();
		try {
			demoMenuService.delete(model);
			j.setSuccess(true);
			j.setMsg("删除成功！");
			j.setObj(model.getCid());
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("删除失败！");
		}
		super.writeJson(j);
	}

	public String menuAdd() {
		return "menuAdd";
	}

	public void add() {
		Json j = new Json();
		try {
			demoMenuService.add(model);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("添加失败！");
		}
		super.writeJson(j);
	}

	public String menuEdit() {
		return "menuEdit";
	}

	/**
	 * 编辑菜单
	 */
	public void edit() {
		Json j = new Json();
		try {
			demoMenuService.edit(model);
			j.setSuccess(true);
			j.setMsg("编辑成功!");
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("编辑失败！");
		}
		writeJson(j);
	}

	public void toGainAuthorizationMenus() {
		try {
			Map<String, List<TreeNode>> retMap = demoMenuService.getAllMenuAndChild();
			if(retMap!=null&&retMap.size()>0){
				super.writeJson(retMap);
			}
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
	}

}

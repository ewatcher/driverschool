package com.tuocheng.action.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuocheng.action.base.BaseAction;
import com.tuocheng.model.demo.TaccessControlList;
import com.tuocheng.model.demo.Ttrainer;
import com.tuocheng.pageModel.base.ComboboxJson;
import com.tuocheng.pageModel.base.DataGrid;
import com.tuocheng.pageModel.base.Json;
import com.tuocheng.pageModel.base.TreeNode;
import com.tuocheng.pageModel.demo.AccessControlList;
import com.tuocheng.service.demo.AccessControlListServiceI;
import com.tuocheng.service.demo.MenuServiceI;
import com.tuocheng.util.base.ExceptionUtil;
import com.tuocheng.util.base.Permission;
import com.tuocheng.util.base.ValidateUtil;
import com.tuocheng.weixin.message.VoiceMessage;

import antlr.debug.ParserListener;

/**
 * 权限控制管理action
 * 
 * @author MEI702
 * 
 */
@Namespace("/demo")
@Action(value = "aclAction", results = { @Result(name = "acl", location = "/demo/acl/acl.jsp"),
		@Result(name = "userAuthorization", location = "/demo/acl/userAuthorization.jsp"),
		@Result(name = "roleAuthorization", location = "/demo/acl/roleAuthorization.jsp"),})
public class AccessControlListAction extends BaseAction<AccessControlList> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2775192129436210502L;

	private static final Logger logger = Logger.getLogger(AccessControlListAction.class);

	private AccessControlListServiceI aclService;
	private MenuServiceI menuService;

	@Autowired
	public void setMenuService(MenuServiceI menuService) {
		this.menuService = menuService;
	}

	@Autowired
	public void setAclService(AccessControlListServiceI aclService) {
		this.aclService = aclService;
	}

	// 到达权限控制管理主页面
	public String toACLPage() {
		return "acl";
	}

	// 到达用户授权页面
	public String toUserAuthorizationPage() {
		return "userAuthorization";
	}

	// 到达角色授权页面
	public String toRoleAuthorizationPage() {
		return "roleAuthorization";
	}

	public void datagrid() throws Exception {
		// 超级管理员有查找所有校区选项
		if (ValidateUtil.isValid(user.getSchoolArea())) {
			this.model.setSchoolArea(user.getSchoolArea());
		}
		super.writeJson(aclService.dataGrid(model));
	}

	// 添加信息
	public void addOrEdit() {
		Json json = new Json();
		try {
			if (ValidateUtil.isValid(user.getSchoolArea())) {
				this.model.setSchoolArea(user.getSchoolArea());
			}
			model.setOperator(user.getCname().trim());
			Boolean flag = aclService.addOrUpdatePermission(model.getPrincipalType(), model.getPrincipalSn(),
					model.getResourceSn(), model.getPermission(), model.getAclTriStateFlag());
			json.setMsg("添加权限控制信息成功！");
			json.setSuccess(flag);
		} catch (Exception e) {
			json.setMsg("添加权限控制信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	// 删除信息
	public void delete() {
		Json json = new Json();
		try {
			aclService.deletePermission(model.getPrincipalType(), model.getPrincipalSn(), model.getResourceSn());
			json.setMsg("删除权限控制信息成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("删除权限控制信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);

	}

	// 修改用户继承性
	public void addOrUpdateUserExtends() {
		Json json = new Json();
		try {
			if (ValidateUtil.isValid(user.getSchoolArea())) {
				this.model.setSchoolArea(user.getSchoolArea());
			}
			model.setOperator(user.getCname().trim());
			Boolean flag = aclService.addOrUpdateUserExtends(model.getPrincipalSn(), model.getResourceSn(),
					model.getAclTriStateFlag());
			json.setMsg("修改信息成功！");
			json.setSuccess(flag);
		} catch (Exception e) {
			json.setMsg("修改信息失败！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(json);
	}

	public void haspermission() {
		try {
			super.writeJson(
					aclService.haspermission(model.getPrincipalType(), model.getResourceSn(), model.getPermission()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void toGainMainMenus() {
		try {
			super.writeJson(aclService.getMainMenuByUserId(user.getCid()));
		} catch (Exception e) {
		}
	}

	public void toGainSingleNode() {
		try {
			super.writeJson(aclService.getTreeNodeByMainMenuId(model.getMainMenuId(), user.getCid()));
		} catch (Exception e) {
		}
	}

	public void toInitAclMenu() {
		List<AccessControlList> retList = new ArrayList<AccessControlList>(0);
		try {
			List<ComboboxJson> mainLists = menuService.getMainMenus();
			Map<String, List<TreeNode>> childMap = menuService.getAllMenuAndChild();
			if (ValidateUtil.isValidListObject(mainLists)) {
				AccessControlList acl = null;
				List<TreeNode> childdrens = null;
				for (ComboboxJson json : mainLists) {
					acl = new AccessControlList();
					acl.setId(json.getValue());
					acl.setMainMenuId(json.getValue());
					acl.setMainMenuName(json.getText());
					if (ValidateUtil.isValid(model.getPrincipalType())&&ValidateUtil.isValid(model.getPrincipalSn())) {
						acl.setAclStateR(aclService.haspermissionByResouceSn(model.getPrincipalType(),model.getPrincipalSn(), json.getValue(),
								Permission.READ));
					}
					retList.add(acl);
					if (childMap != null && childMap.size() > 0) {
						childdrens = childMap.get(json.getValue());
						if (ValidateUtil.isValidListObject(childdrens)) {
							for (TreeNode node : childdrens) {
								acl = new AccessControlList();
								acl.setId(node.getId());
								acl.setSecondMenuId(node.getId());
								acl.setSecondMenuName(node.getText());
								// 授权情况
								if (ValidateUtil.isValid(model.getPrincipalType())&&ValidateUtil.isValid(model.getPrincipalSn())) {
									acl.setAclStateC(aclService.haspermissionByResouceSn(model.getPrincipalType(),model.getPrincipalSn(),
											node.getId(), Permission.CREATE));
									acl.setAclStateR(aclService.haspermissionByResouceSn(model.getPrincipalType(),model.getPrincipalSn(),
											node.getId(), Permission.READ));
									acl.setAclStateU(aclService.haspermissionByResouceSn(model.getPrincipalType(),model.getPrincipalSn(),
											node.getId(), Permission.UPDATE));
									acl.setAclStateD(aclService.haspermissionByResouceSn(model.getPrincipalType(),model.getPrincipalSn(),
											node.getId(), Permission.DELETE));
									//设置启用ckecked状态，checkedFlag选中则必须且有CRUD四种权限
									if(aclService.haspermissionByResouceSn(model.getPrincipalType(),model.getPrincipalSn(),
											node.getId(), Permission.CREATE)&&aclService.haspermissionByResouceSn(model.getPrincipalType(),model.getPrincipalSn(),
													node.getId(), Permission.READ)&&aclService.haspermissionByResouceSn(model.getPrincipalType(),model.getPrincipalSn(),
															node.getId(), Permission.UPDATE)&&aclService.haspermissionByResouceSn(model.getPrincipalType(),model.getPrincipalSn(),
																	node.getId(), Permission.DELETE)){
										acl.setEnableFlag(true);
									}else{
										acl.setEnableFlag(false);
									}
									if("USER".equals(model.getPrincipalType())){
										TaccessControlList aclTemp=aclService.getACL(model.getPrincipalType(), model.getPrincipalSn(), node.getId());
										if(aclTemp!=null){
											if(0==aclTemp.getAclTriState()){
												acl.setAclStateT(false);
											}else if(-1==aclTemp.getAclTriState()){
												acl.setAclStateT(true);
											}
											
										}
										
									}
								}

								retList.add(acl);
							}
						}
					}
				}
			}
			DataGrid dataGrid = new DataGrid();
			// 设置返回前台的数据
			if (ValidateUtil.isValidListObject(retList)) {
				dataGrid.setRows(retList);
				// 设置总记录数
			} else {
				dataGrid.setRows(null);
			}

			super.writeJson(dataGrid);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getUserPermissions(){
		List<Boolean> rets=new ArrayList<Boolean>();
		try {
			if(ValidateUtil.isValid(user.getCid())){
				boolean C=aclService.haspermission(user.getCid(), model.getResourceSn(), Permission.CREATE);
				boolean R=aclService.haspermission(user.getCid(), model.getResourceSn(), Permission.READ);
				boolean U=aclService.haspermission(user.getCid(), model.getResourceSn(), Permission.UPDATE);
				boolean D=aclService.haspermission(user.getCid(), model.getResourceSn(), Permission.DELETE);
				rets.add(C);
				rets.add(R);
				rets.add(U);
				rets.add(D);
			}
			if(ValidateUtil.isValidListObject(rets)){
				super.writeJson(rets);
			}else{
				super.writeJson(null);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

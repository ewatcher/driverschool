package com.tuocheng.pageModel.demo;

public class AccessControlList extends PageModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8513162986979661174L;

	public String id;// 标识
	private String principalType;// 主体类型：用户或角色
	private String principalSn;// 主体标识：用户标识或角色标识
	private String resourceSn;// 资源标识（也就是模块标识）
	private int aclState;// 授权状态：用其后4位表示CRUD操作
	private int aclTriState;// 授权状态掩码：表示是否继承，0表示不继承，-1表示继承
	private String schoolArea;// 校区标识
	private String operator;// 创建人

	private String ids;
	private String userId;
	private String roleId;
	private int permission;
	private String schoolAreaName;
	private boolean aclTriStateFlag;

	private String mainMenuId;
	private String mainMenuName;
	private String secondMenuId;
	private String secondMenuName;
	private boolean aclStateC;
	private boolean aclStateR;
	private boolean aclStateU;
	private boolean aclStateD;
	private boolean aclStateT;
	private String parentId;

	private boolean enableFlag;// 启用标记，用户ckecked,表示当前资源进行CRUD授权

	public boolean getEnableFlag() {
		return enableFlag;
	}

	public void setEnableFlag(boolean enableFlag) {
		this.enableFlag = enableFlag;
	}

	public String getMainMenuId() {
		return mainMenuId;
	}

	public void setMainMenuId(String mainMenuId) {
		this.mainMenuId = mainMenuId;
	}

	public String getMainMenuName() {
		return mainMenuName;
	}

	public void setMainMenuName(String mainMenuName) {
		this.mainMenuName = mainMenuName;
	}

	public String getSecondMenuId() {
		return secondMenuId;
	}

	public void setSecondMenuId(String secondMenuId) {
		this.secondMenuId = secondMenuId;
	}

	public String getSecondMenuName() {
		return secondMenuName;
	}

	public void setSecondMenuName(String secondMenuName) {
		this.secondMenuName = secondMenuName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public boolean getAclStateC() {
		return aclStateC;
	}

	public void setAclStateC(boolean aclStateC) {
		this.aclStateC = aclStateC;
	}

	public boolean getAclStateR() {
		return aclStateR;
	}

	public void setAclStateR(boolean aclStateR) {
		this.aclStateR = aclStateR;
	}

	public boolean getAclStateU() {
		return aclStateU;
	}

	public void setAclStateU(boolean aclStateU) {
		this.aclStateU = aclStateU;
	}

	public boolean getAclStateD() {
		return aclStateD;
	}

	public void setAclStateD(boolean aclStateD) {
		this.aclStateD = aclStateD;
	}

	public boolean getAclStateT() {
		return aclStateT;
	}

	public void setAclStateT(boolean aclStateT) {
		this.aclStateT = aclStateT;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean getAclTriStateFlag() {
		return aclTriStateFlag;
	}

	public void setAclTriStateFlag(boolean aclTriStateFlag) {
		this.aclTriStateFlag = aclTriStateFlag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPrincipalType() {
		return principalType;
	}

	public void setPrincipalType(String principalType) {
		this.principalType = principalType;
	}

	public String getPrincipalSn() {
		return principalSn;
	}

	public void setPrincipalSn(String principalSn) {
		this.principalSn = principalSn;
	}

	public String getResourceSn() {
		return resourceSn;
	}

	public void setResourceSn(String resourceSn) {
		this.resourceSn = resourceSn;
	}

	public int getAclState() {
		return aclState;
	}

	public void setAclState(int aclState) {
		this.aclState = aclState;
	}

	public int getAclTriState() {
		return aclTriState;
	}

	public void setAclTriState(int aclTriState) {
		this.aclTriState = aclTriState;
	}

	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public int getPermission() {
		return permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}

	public String getSchoolAreaName() {
		return schoolAreaName;
	}

	public void setSchoolAreaName(String schoolAreaName) {
		this.schoolAreaName = schoolAreaName;
	}

}

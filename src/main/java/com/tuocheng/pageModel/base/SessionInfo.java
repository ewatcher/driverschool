package com.tuocheng.pageModel.base;

import com.tuocheng.model.demo.Tuser;
import com.tuocheng.pageModel.demo.User;

/**
 * sessionInfo模型
 * 
 * @author 农峰
 * 
 */
public class SessionInfo implements java.io.Serializable {

	/**
	 * 序列化标识
	 */
	private static final long serialVersionUID = -3351685899831780735L;
	private String userId;// 用户ID
	private String loginName;// 用户登录名称
	private String loginPassword;// 登录密码
	private String schoolArea;// 校区标识
	private String schoolAreaName;// 校区标识
	private String ip;// IP地址
	private String authIds;// 拥有的权限ID集合
	private String authNames;
	private String authUrls;
	private String roleIds;
	private String roleNames;
	private String menuIds;
	private User user;

	public String getAuthUrls() {
		return authUrls;
	}

	public void setAuthUrls(String authUrls) {
		this.authUrls = authUrls;
	}

	public String getAuthNames() {
		return authNames;
	}

	public void setAuthNames(String authNames) {
		this.authNames = authNames;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public String getSchoolAreaName() {
		return schoolAreaName;
	}

	public void setSchoolAreaName(String schoolAreaName) {
		this.schoolAreaName = schoolAreaName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	public String getAuthIds() {
		return authIds;
	}

	public void setAuthIds(String authIds) {
		this.authIds = authIds;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	@Override
	public String toString() {
		return loginName;
	}

}

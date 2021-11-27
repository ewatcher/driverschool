package com.tuocheng.pageModel.demo;

import java.util.HashSet;
import java.util.Set;

import com.tuocheng.model.demo.Trole;

public class Right extends PageModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3287946962114386752L;
	private String id;
	private String rightName = "未命名";
	private String rightUrl;
	private String rightDesc;
	private long rightCode;// 权限码,1<<n
	private int rightPos; // 权限位,相当于对权限分组,从0开始
	private Set<Trole> roles = new HashSet<Trole>();
	private String comment;// 备注信息
	// 是否是公共资源
	private boolean common;
	private String ids;

	private Trole roleId;// 一个角色拥有多个权限
	private Trole roleName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Trole getRoleId() {
		return roleId;
	}

	public void setRoleId(Trole roleId) {
		this.roleId = roleId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Trole getRoleName() {
		return roleName;
	}

	public void setRoleName(Trole roleName) {
		this.roleName = roleName;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Set<Trole> getRoles() {
		return roles;
	}

	public void setRoles(Set<Trole> roles) {
		this.roles = roles;
	}

	public String getRightName() {
		return rightName;
	}

	public void setRightName(String rightName) {
		this.rightName = rightName;
	}

	public String getRightUrl() {
		return rightUrl;
	}

	public void setRightUrl(String rightUrl) {
		this.rightUrl = rightUrl;
	}

	public String getRightDesc() {
		return rightDesc;
	}

	public void setRightDesc(String rightDesc) {
		this.rightDesc = rightDesc;
	}

	public long getRightCode() {
		return rightCode;
	}

	public void setRightCode(long rightCode) {
		this.rightCode = rightCode;
	}

	public int getRightPos() {
		return rightPos;
	}

	public void setRightPos(int rightPos) {
		this.rightPos = rightPos;
	}

	public boolean isCommon() {
		return common;
	}

	public void setCommon(boolean common) {
		this.common = common;
	}

}

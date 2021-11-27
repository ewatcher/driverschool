package com.tuocheng.model.demo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 访问权限制列表实体类
 * 
 * @author MEI702
 *
 */
@Entity
@Table(name = "tb_accessControlLists", schema = "")
public class TaccessControlList implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 220871657730718478L;
	public static final String TYPE_ROLE = "ROLE";// 主体类型：角色
	public static final String TYPE_USER = "USER";// 主体类型：用户
	public static final int ACL_YES = 1;// 授权允许
	public static final int ACL_NO = 0;// 授权不允许
	public static final int ACL_NEUTRAL = -1;// 授权不确定
	public String id;// 标识
	private String principalType;// 主体类型：用户或角色
	private String principalSn;// 主体标识：用户标识或角色标识
	private String resourceSn;// 资源标识（也就是模块标识）
	private int aclState;// 授权状态：用其后4位表示CRUD操作
	private int aclTriState;// 授权状态掩码：表示是否继承，0表示不继承，-1表示继承
	private String schoolArea;// 校区标识

	private String operator;// 创建人

	public TaccessControlList() {
	}

	public TaccessControlList(String id) {
		this.id = id;
	}

	public TaccessControlList(String id, String principalType, String principalSn, String resourceSn, int aclState,
			int aclTriState, String schoolArea, String operator) {
		this.id = id;
		this.principalType = principalType;
		this.principalSn = principalSn;
		this.resourceSn = resourceSn;
		this.aclState = aclState;
		this.aclTriState = aclTriState;
		this.schoolArea = schoolArea;
		this.operator = operator;
	}

	/**
	 * acl实例根主体和资源关联<br>
	 * （对主体进行授权 ） 对ACL实例进行授权，某种操作是否允许，就是修改授权状态值
	 * 
	 * @param permission取值范围
	 *            (0,1,2,3)
	 * @param yes
	 *            true表示允许，false表示不允许
	 */
	public void setPermission(int permission, boolean yes) {
		int temp = 1;
		temp = temp << permission;
		if (yes) {
			aclState |= temp;
		} else {
			aclState &= ~temp;
		}

	}

	/**
	 * 获取ACL授权
	 * 
	 * @param permission
	 *            C/R/U/D权限
	 * @return 授权标识；(允许、不允许、不确定)
	 */
	public int getPermission(int permission) {
		if (aclTriState == -1) {
			return this.ACL_NEUTRAL;
		}

		int temp = 1;
		temp = temp << permission;
		temp &= aclState;
		if (temp != 0) {
			return this.ACL_YES;
		}

		return this.ACL_NO;
	}

	/**
	 * 设置本授权是否是继承的
	 * 
	 * @param yes
	 *            true表示继承，false表示不继承
	 */
	public void setExtends(boolean yes) {
		if (yes) {
			this.aclTriState = 0xFFFFFFFF;
		}else{
			this.aclTriState=0;
		}
	}

	//////////// ================generating=================================

	@Id
	@Column(name = "id", nullable = false, updatable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "principalType", length = 36)
	public String getPrincipalType() {
		return principalType;
	}

	public void setPrincipalType(String principalType) {
		this.principalType = principalType;
	}

	@Column(name = "principalSn", length = 36)
	public String getPrincipalSn() {
		return principalSn;
	}

	public void setPrincipalSn(String principalSn) {
		this.principalSn = principalSn;
	}

	@Column(name = "resourceSn", length = 36)
	public String getResourceSn() {
		return resourceSn;
	}

	public void setResourceSn(String resourceSn) {
		this.resourceSn = resourceSn;
	}

	@Column(name = "aclState", columnDefinition = "INT DEFAULT 0")
	public int getAclState() {
		return aclState;
	}

	public void setAclState(int aclState) {
		this.aclState = aclState;
	}

	@Column(name = "aclTriState", columnDefinition = "INT DEFAULT 0")
	public int getAclTriState() {
		return aclTriState;
	}

	public void setAclTriState(int aclTriState) {
		this.aclTriState = aclTriState;
	}

	@Column(name = "schoolArea")
	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	@Column(name = "operator")
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaccessControlList other = (TaccessControlList) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TaccessControlList [id=" + id + ", principalType=" + principalType + ", principalSn=" + principalSn
				+ ", resourceSn=" + resourceSn + ", aclState=" + aclState + ", aclTriState=" + aclTriState + "]";
	}

}

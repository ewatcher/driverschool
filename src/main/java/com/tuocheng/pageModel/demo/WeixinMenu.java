package com.tuocheng.pageModel.demo;

/**
 * 微信自定义菜单实体类(action专用)
 * 
 * @author 李杰
 * 
 */
public class WeixinMenu extends PageModel {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 365741094971384357L;

	private Integer id;
	private String keyname; // 用户名
	private String nickname; // 名称
	private String type; // 类型（click：点击 view：打开URL）
	private Integer reorder; // 排序
	private String content; // 内容
	private Integer cid; // 上级ID
	private Integer status; // 状态
	private String organizationId; // 机构ID,分校ID
	private String organizationName; // 机构名称

	private Integer tmpParentId; // 上级ID，
	private String ids;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKeyname() {
		return keyname;
	}

	public void setKeyname(String keyname) {
		this.keyname = keyname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getReorder() {
		return reorder;
	}

	public void setReorder(Integer reorder) {
		this.reorder = reorder;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public Integer getTmpParentId() {
		return tmpParentId;
	}

	public void setTmpParentId(Integer tmpParentId) {
		this.tmpParentId = tmpParentId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}

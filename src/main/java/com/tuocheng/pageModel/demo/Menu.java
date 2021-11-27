package com.tuocheng.pageModel.demo;

import java.math.BigDecimal;

import com.tuocheng.model.demo.Tmenu;

public class Menu extends PageModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5709062243611993607L;
	private String cid;
	private Tmenu tmenu;
	private String ciconcls;
	private String cname;
	private BigDecimal cseq;
	private String curl;
	private Integer mainMenuFlag;

	private String id;
	private String pid;
	private String pname;
	private String state = "open";// 是否展开(open,closed)
	private String iconCls;// 前面的小图标样式

	private String menuIds;

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getMenuIds() {
		return menuIds;
	}

	public Integer getMainMenuFlag() {
		return mainMenuFlag;
	}

	public void setMainMenuFlag(Integer mainMenuFlag) {
		this.mainMenuFlag = mainMenuFlag;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public Tmenu getTmenu() {
		return tmenu;
	}

	public void setTmenu(Tmenu tmenu) {
		this.tmenu = tmenu;
	}

	public String getCiconcls() {
		return ciconcls;
	}

	public void setCiconcls(String ciconcls) {
		this.ciconcls = ciconcls;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public BigDecimal getCseq() {
		return cseq;
	}

	public void setCseq(BigDecimal cseq) {
		this.cseq = cseq;
	}

	public String getCurl() {
		return curl;
	}

	public void setCurl(String curl) {
		this.curl = curl;
	}

}

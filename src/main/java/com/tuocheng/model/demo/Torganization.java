package com.tuocheng.model.demo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 机构管理实体类(dao专用实体类)
 * 
 * @author MEI702
 * 
 */
@Entity
@Table(name = "tb_organizations", schema = "")
public class Torganization implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 8678372697626004418L;
	
	public static final String BS_SCHOOLAREA="b78ffef2-7c54-40fe-be4b-1910a87c8bbs";
	public static final String TY_SCHOOLAREA="c0510169-8edc-470e-b038-c299c7735bty";
	public static final String TD_SCHOOLAREA="7d15f11b-2928-41e8-8406-8b49cf3939td";
	public static final String PG_SCHOOLAREA="606437a6-574c-4b0b-b79f-1a98bd4a45pg";
	public static final String DB_SCHOOLAREA="357c1d29-c991-4eed-a331-949f3eb4e9db";
	public static final String JX_SCHOOLAREA="6b698583-3aad-4702-8aa5-6e55b27df8jx";
	public static final String NP_SCHOOLAREA="357c1d29-c991-4eed-a331-949f3eb4e9np";
	public static final String TL_SCHOOLAREA="357c1d29-c991-4eed-a331-949f3eb4e9tl";
	public static final String XL_SCHOOLAREA="357c1d29-c991-4eed-a331-949f3eb4e9xl";
	public static final String LL_SCHOOLAREA="357c1d29-c991-4eed-a331-949f3eb4e9ll";
	public static final String LY_SCHOOLAREA="357c1d29-c991-4eed-a881-949f3eb008ly";
	public static final String LEY_SCHOOLAREA="357c1d29-c991-4eed-a331-949f3eb888ly";

	private String id;// 机构标识
	private String name;// 机构名称
	private BigDecimal sequence;// 排序
	private String url;// 连接地址
	private String iconcls;// 图标
	private Torganization parent;// 父级机构
	private Set<Torganization> childrens = new HashSet<Torganization>();// 子级机构

	private Set<Tperson> persons = new HashSet<Tperson>();// 一对多：一个组织机构拥有多个人员
	private Set<Tstudent> studens = new HashSet<Tstudent>();// 一对多：一个校区拥有多个学员

	public Torganization() {
	}

	public Torganization(String id, String name, BigDecimal sequence,
			String url, String iconcls, Torganization parent,
			Set<Torganization> childrens, Set<Tperson> persons,
			Set<Tstudent> studens) {
		this.id = id;
		this.name = name;
		this.sequence = sequence;
		this.url = url;
		this.iconcls = iconcls;
		this.parent = parent;
		this.childrens = childrens;
		this.persons = persons;
		this.studens = studens;
	}

	@Id
	@Column(name = "id", nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "sequence", precision = 22, scale = 0)
	public BigDecimal getSequence() {
		return sequence;
	}

	public void setSequence(BigDecimal sequence) {
		this.sequence = sequence;
	}

	@Column(name = "url", length = 200)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prentId")
	public Torganization getParent() {
		return parent;
	}

	public void setParent(Torganization parent) {
		this.parent = parent;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parent")
	public Set<Torganization> getChildrens() {
		return childrens;
	}

	public void setChildrens(Set<Torganization> childrens) {
		this.childrens = childrens;
	}

	@Column(name = "iconcls", length = 200)
	public String getIconcls() {
		return iconcls;
	}

	public void setIconcls(String iconcls) {
		this.iconcls = iconcls;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
	public Set<Tperson> getPersons() {
		return persons;
	}

	public void setPersons(Set<Tperson> persons) {
		this.persons = persons;
	}
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="organization")
	public Set<Tstudent> getStudens() {
		return studens;
	}

	public void setStudens(Set<Tstudent> studens) {
		this.studens = studens;
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
		Torganization other = (Torganization) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Torganization [id=" + id + ", name=" + name + ", sequence="
				+ sequence + ", url=" + url + ", parent=" + parent
				+ ", childrens=" + childrens + "]";
	}

}

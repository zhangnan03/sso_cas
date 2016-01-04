package cn.symdata.entity;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Lists;

/**
 * 
 * @ClassName: Menu 
 * @Description: 菜单实体类
 * @author guoxuelian@symdata.cn
 * @date 2015年9月8日 下午2:44:05 
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "auth_menu")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Menu extends BaseEntity implements Comparable<Object> {
	/**
	 * 菜单名称
	 */
	private String name="";
	/**
	 * 菜单编码
	 */
	private String code="";
	/**
	 * 菜单URL
	 */
	private String url="";
	/**
	 * 权限类型表(0链接,1操作按钮)
	 */
	private String type="0";
	
	/**
	 * 菜单标识
	 */
	private String menuFlag="";
	
	/**
	 * 排序
	 */
	private Integer menuSort=0;
	
	/**
	 * 上级ID(如果为0，则为顶级)
	 */
	private Menu parent;
	/**
	 * 描述
	 */
	private String description="";
	/**
	 * 系统标识
	 */
	private String systemMark="0";
	/**
	 * 临时标记，判断是否选中
	 */
	private Integer isChecked;
	/**
	 * 是否删除0否1删除
	 */
	private int isEnable=0;
	/**
	 * 角色列表
	 */
	private List<Role> roleList = Lists.newArrayList();
	/**
	 * 字段列表
	 */
	private List<DataField> dataFieldList = Lists.newArrayList();
	/**
	 * 控件列表
	 */
	private List<Power> powerList = Lists.newArrayList();
	/**
	 * 子节点集合
	 */
	private Set<Menu> children = new TreeSet<Menu>();
	/**
	 * 临时子节点结合
	 */
	private Set<Menu> childrenTmp = new TreeSet<Menu>();
	
	@Column(nullable = false, unique = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = false, unique = true)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(nullable = false)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(nullable = false)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToMany(cascade = { CascadeType.PERSIST })
	@JoinTable(name = "auth_role_menu_rel", joinColumns = { @JoinColumn(name = "menu_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("id")
	@JsonIgnore
	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	@Transient
	public Integer getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(Integer isChecked) {
		this.isChecked = isChecked;
	}

	@Override
	public int compareTo(Object o) {
		Menu resource = (Menu) o;
		return new CompareToBuilder().append(getMenuSort(),resource.getMenuSort())
				.append(getId(),resource.getId()).toComparison();
	}

	@Column(nullable = false)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(nullable = false)
	public String getSystemMark() {
		return systemMark;
	}

	public void setSystemMark(String systemMark) {
		this.systemMark = systemMark;
	}

	@OneToMany(cascade = { CascadeType.REFRESH },mappedBy ="menu")
	@JsonIgnore
	public List<DataField> getDataFieldList() {
		return dataFieldList;
	}

	public void setDataFieldList(List<DataField> dataFieldList) {
		this.dataFieldList = dataFieldList;
	}
	
	@OneToMany(cascade = { CascadeType.REFRESH },mappedBy ="menu")
	@JsonIgnore
	public List<Power> getPowerList() {
		return powerList;
	}

	public void setPowerList(List<Power> powerList) {
		this.powerList = powerList;
	}

	@Column(nullable = false)
	public int getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(int isEnable) {
		this.isEnable = isEnable;
	}
	/**
	 * 获取上级菜单
	 * 
	 */
	@ManyToOne(cascade={CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JoinColumn(name="parent_id")
	@JsonIgnore
	@NotFound(action=NotFoundAction.IGNORE)
	public Menu getParent() {
		return parent;
	}

	/**
	 * 设置上级分类
	 * 
	 * @param parent
	 *            上级分类
	 */
	public void setParent(Menu parent) {
		this.parent = parent;
	}

	/**
	 * 获取下级菜单
	 * 
	 */
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	@OrderBy("id asc")
	@JsonIgnore
	public Set<Menu> getChildren() {
		return children;
	}

	/**
	 * 设置下级分类
	 * 
	 * @param children
	 *            下级分类
	 */
	public void setChildren(Set<Menu> children) {
		this.children = children;
	}

	@Transient
	public String getParentId() {
		return this.parent!=null?this.parent.getId():null;
	}
	
	@Column(nullable = false)
	public String getMenuFlag() {
		return menuFlag;
	}

	public void setMenuFlag(String menuFlag) {
		this.menuFlag = menuFlag;
	}

	public Integer getMenuSort() {
		return menuSort;
	}

	public void setMenuSort(Integer menuSort) {
		this.menuSort = menuSort;
	}
	@Transient
	public Set<Menu> getChildrenTmp() {
		return childrenTmp;
	}

	public void setChildrenTmp(Set<Menu> childrenTmp) {
		this.childrenTmp = childrenTmp;
	}
}

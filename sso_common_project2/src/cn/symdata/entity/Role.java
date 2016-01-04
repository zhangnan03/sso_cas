package cn.symdata.entity;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import cn.symdata.common.DataEnum.DeleteStatus;
import cn.symdata.common.utils.Collections3;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

/**
 * 
 * @ClassName: Role 
 * @Description: 角色实体类 
 * @author guoxuelian@symdata.cn
 * @date 2015年9月8日 下午2:57:25 
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "auth_role")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Role extends BaseEntity {
	/**
	 * 角色唯一标识英文
	 */
	private String code;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 临时标记，判断是否选中
	 */
	private Integer isChecked;
	/**
	 * 是否删除0否1删除
	 */
	private Integer isEnable=0;
	/**
	 * 用户列表
	 */
	private List<User> userList = Lists.newArrayList();
	/**
	 * 菜单列表
	 */
	private List<Menu> menuList = Lists.newArrayList();

	@Column(nullable = false, unique = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = false)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToMany(cascade = CascadeType.REFRESH)
	@JoinTable(name = "auth_role_menu_rel", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "menu_id") })
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("menuSort")
	@JsonIgnore
	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}
	
	
	@Transient
	public Integer getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(Integer isChecked) {
		this.isChecked = isChecked;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@ManyToMany(cascade = CascadeType.REFRESH)
	@JoinTable(name = "auth_user_role_rel", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("id")
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	@Column(nullable = false, unique = true)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	@Column(nullable = false)
	public Integer getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}
	
	/**
	 * 
	 * @Title: getMenus 
	 * @Description: 获取某角色所拥有的菜单
	 * @Autohr guoxuelian#symdata.cn
	 * @param @return   
	 * @return List<String>  
	 * @throws 
	 * 2015年9月15日下午3:40:09
	 */
	@Transient
	public String getMenus() {
		Collection<Menu> menus = Collections2.filter(
				getMenuList(), new Predicate<Menu>() {
					@Override
					public boolean apply(Menu menu) {
						if(!StringUtils.isNotBlank(menu.getCode())){
							return false;
						}
						if(menu.getIsEnable()==DeleteStatus.DELETE.getStatusCode()){
							return false;
						}
						return true;
					}
				});
		return Collections3.extractToString(menus, "id", ",");
	}
	
	/**
	 * 
	 * @Title: getMenus 
	 * @Description: TODO
	 * @Autohr guoxuelian#symdata.cn
	 * @param @return   
	 * @return String  
	 * @throws 
	 * 2015年9月15日下午5:03:09
	 */
	@Transient
	public String getUsers() {
		Collection<User> users = Collections2.filter(
				getUserList(), new Predicate<User>() {
					@Override
					public boolean apply(User user) {
						if(user.getIsEnable()==DeleteStatus.DELETE.getStatusCode()){
							return false;
						}
						return true;
					}
				});
		return Collections3.extractToString(users, "id", ",");
	}
}

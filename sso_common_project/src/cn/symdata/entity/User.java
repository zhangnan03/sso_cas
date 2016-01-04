package cn.symdata.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import cn.symdata.common.DataEnum.DeleteStatus;
import cn.symdata.common.utils.Collections3;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * @Copyright:Copyright (c) 2012-2014
 * @Company:zplay.cn
 * @Title:
 * @Description:用户表
 * @Author:wangdezhen#zplay.cn
 * @Since:2015年1月13日 上午11:02:56
 * @Version:1.0
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "auth_user")
public class User extends BaseEntity {
	/**
	 * "身份信息"参数名称
	 */
	public static final String PRINCIPAL_ATTRIBUTE_NAME = "USER.PRINCIPAL";
	/**
	 * "用户名"Cookie名称 
	 */
	public static final String USERNAME_COOKIE_NAME = "username";
	/**
	 * "用户名"Cookie名称
	 */
	public static final String USERRANK_COOKIE_NAME = "user_rank";
	/**
	 * 用户名
	 */
	private String username="";
	/**
	 * 密码
	 */
	private String password = "";
	/**
	 * 真实姓名
	 */
	private String realName = "";
	/**
	 * 状态(0:禁用,1:正常)
	 */
	private int status = 1;
	/**
	 * E-mail
	 */
	private String email = "";
	/**
	 * 是否锁定0：否；1：是
	 */
	private int isLocked = 0;
	/**
	 * 连续登录失败次数
	 */
	private Integer loginFailureCount = 0;
	/**
	 * 锁定日期
	 */
	private Date lockedDate;
	/**
	 * 最后登录日期
	 */
	private Date loginDate;
	/**
	 *  最后登录IP
	 */
	private String loginIp = "";
	/**
	 * 是否删除0否1删除
	 */
	private int isEnable = 0;
	/**
	 * 是否修改密码：0：否；1：修改
	 */
	private int isUpdatePwd = 0;
	/**
	 * 角色列表
	 */
	private List<Role> roleList = Lists.newArrayList();
	/**
	 * 菜单列表
	 */
	
	/**
	 * 控件列表
	 */
	private List<Power> powerList = Lists.newArrayList();
	/**
	 * 字段列表
	 */
	private List<DataField> dataFieldList = Lists.newArrayList();

	@Column(nullable = false, unique = true)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(int isLocked) {
		this.isLocked = isLocked;
	}

	public Integer getLoginFailureCount() {
		return loginFailureCount;
	}

	public void setLoginFailureCount(Integer loginFailureCount) {
		this.loginFailureCount = loginFailureCount;
	}

	public Date getLockedDate() {
		return lockedDate;
	}

	public void setLockedDate(Date lockedDate) {
		this.lockedDate = lockedDate;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "auth_user_role_rel", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("id")
	@JsonIgnore
	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	
	@ManyToMany(cascade = CascadeType.REFRESH)
	@JoinTable(name = "auth_user_power_rel", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "power_id") })
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("id")
	@JsonIgnore
	public List<Power> getPowerList() {
		return powerList;
	}

	public void setPowerList(List<Power> powerList) {
		this.powerList = powerList;
	}
	
	@ManyToMany(cascade = CascadeType.REFRESH)
	@JoinTable(name = "auth_user_data_field_rel", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "data_field_id") })
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("id")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@JsonIgnore
	public List<DataField> getDataFieldList() {
		return dataFieldList;
	}

	public void setDataFieldList(List<DataField> dataFieldList) {
		this.dataFieldList = dataFieldList;
	}
	
	@Column(nullable = false)
	public int getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(int isEnable) {
		this.isEnable = isEnable;
	}
	
	@Column(nullable = false)
	public int getIsUpdatePwd() {
		return isUpdatePwd;
	}

	public void setIsUpdatePwd(int isUpdatePwd) {
		this.isUpdatePwd = isUpdatePwd;
	}
	
	/**
	 * 
	 * @Title: getRoles 
	 * @Description: 获取该用户的角色拼接
	 * @Autohr guoxuelian#symdata.cn
	 * @param @return   
	 * @return List<String>  
	 * @throws 
	 * 2015年9月14日下午2:08:46
	 */
	@SuppressWarnings("unchecked")
	@Transient
	public List<String> getRoles() {
		Collection<Role> roles = Collections2.filter(
				getRoleList(), new Predicate<Role>() {
					@Override
					public boolean apply(Role role) {
						if(!StringUtils.isNotBlank(role.getCode())){
							return false;
						}
						if(role.getIsEnable()==DeleteStatus.DELETE.getStatusCode()){
							return false;
						}
						return true;
					}
				});
		return Collections3.extractToList(roles, "code");
	}
	
	/**
	 * 
	 * @Title: getPowers 
	 * @Description: 获取该用户的按钮权限拼接
	 * @Autohr guoxuelian#symdata.cn
	 * @param @return   
	 * @return List<String>  
	 * @throws 
	 * 2015年9月14日下午2:07:43
	 */
	@SuppressWarnings("unchecked")
	@Transient
	public List<String> getPowers() {
		Collection<Power> powers = Collections2.filter(
				getPowerList(), new Predicate<Power>() {
					@Override
					public boolean apply(Power power) {
						if(!StringUtils.isNotBlank(power.getCode())){
							return false;
						}
						if(power.getIsEnable()==DeleteStatus.DELETE.getStatusCode()){
							return false;
						}
						return true;
					}
				});
		return Collections3.extractToList(powers, "code");
	}
	
	/**
	 * 
	 * @Title: getDataFields 
	 * @Description: 获取该用户的字段权限字符串拼接
	 * @Autohr guoxuelian#symdata.cn
	 * @param @return   
	 * @return List<String>  
	 * @throws 
	 * 2015年9月14日下午2:07:10
	 */
	@SuppressWarnings("unchecked")
	@Transient
	public List<String> getDataFields() {
		Collection<DataField> dataFields = Collections2.filter(
				getDataFieldList(), new Predicate<DataField>() {
					@Override
					public boolean apply(DataField dataField) {
						if(!StringUtils.isNotBlank(dataField.getCode())){
							return false;
						}
						if(dataField.getIsEnable()==DeleteStatus.DELETE.getStatusCode()){
							return false;
						}
						return true;
					}
				});
		return Collections3.extractToList(dataFields, "code");
	}
	
	/**
	 * 
	 * @Title: getMenuList 
	 * @Description: 获取该用户的菜单列表
	 * @Autohr guoxuelian#symdata.cn
	 * @param @return   
	 * @return List<Menu>  
	 * @throws 
	 * 2015年9月14日下午2:11:55
	 */
	@Transient
	@JsonIgnore
	public List<Menu> getMenuList(String systemId) {
		Set<Menu> menuList = Sets.newHashSet();
		for (Role role : roleList) {
			List<Menu> mList = role.getMenuList();
			for (Menu menu : mList) {
				if(menu.getParent()!=null){
					if(StringUtils.isNotBlank(systemId)){
						if(menu.getIsEnable()==DeleteStatus.NORMAL.getStatusCode()&&systemId.equals(menu.getSystemMark())){
							menuList.add(menu);
						}
					}else{
						if(menu.getIsEnable()==DeleteStatus.NORMAL.getStatusCode()){
							menuList.add(menu);
						}
					}
				}
			}
		}
		
		Set<Menu> parentSet = new HashSet<Menu>();
		//主菜单
		for(Menu menu:menuList){
			if(menu.getParent().getParent()==null){
				parentSet.add(menu);
			}
		}
		//子菜单
		for (Menu menuP : parentSet) {
			for (Menu menuC : menuList) {
				if(menuC.getParent().getId().equals(menuP.getId())){
					menuP.getChildrenTmp().add(menuC);
				}
			}
		}
		List<Menu> list = Lists.newArrayList();
		list.addAll(parentSet);
		Collections.sort(list);
		return list;
	}
}

package cn.symdata.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.apache.commons.lang3.builder.ToStringBuilder;

import cn.symdata.common.DataEnum.UserStatus;
/**
 *@Copyright:Copyright (c) 2012-2015
 *@Company:symdata
 *@Title:
 *@Description:用户信息
 *@Author:zhangnan#symdata
 *@Since:2015年9月7日  上午10:08:06
 *@Version:1.0
 */
@SuppressWarnings("serial")
@Entity
public class Admin extends BaseEntity {
	/** "身份信息"参数名称 */
	public static final String PRINCIPAL_ATTRIBUTE_NAME = "USER.PRINCIPAL";

	/** "用户名"Cookie名称 */
	public static final String USERNAME_COOKIE_NAME = "username";
	/** "用户名"Cookie名称 */
	public static final String USERRANK_COOKIE_NAME = "user_rank";

	private String username; // 用户名
	private String password; // 密码
	private String realName; // 真实姓名
	private UserStatus userStatus; // 状态(1：正常 2：禁用)
	
	/** E-mail */
	private String email;

	/** 是否锁定 */
	private Boolean isLocked;
	/** 连续登录失败次数 */
	private Integer loginFailureCount;

	/** 锁定日期 */
	private Date lockedDate;

	/** 最后登录日期 */
	private Date loginDate;

	/** 最后登录IP */
	private String loginIp;

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

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status")
	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Boolean isLocked) {
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

}

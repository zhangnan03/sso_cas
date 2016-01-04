package cn.symdata.service;

import java.util.List;

import cn.symdata.entity.Admin;

/**
 *@Copyright:Copyright (c) 2012-2015
 *@Company:symdata
 *@Title:操作用户的业务逻辑层
 *@Description:
 *@Author:zhangnan#symdata
 *@Since:2015年9月1日  下午3:23:23
 *@Version:1.0
 */
public interface AdminService {
	/**
	 *@param username
	 *@return 用户信息
	 *@Description:根据用户名查询用户信息
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月1日  下午3:25:04
	 *@Version:1.0
	 */
	public abstract Admin findByUsername(String username);
	/**
	 *@param user
	 *@return 
	 *@Description:保存用户信息
	 *@Author:zhangnan#symdata
	 *@Since:2015年8月31日  下午6:59:09
	 *@Version:1.0
	 */
	public abstract Admin save(Admin admin);
	/**
	 *@param username
	 *@return 权限,若不存在则返回null
	 *@Description:根据ID查找权限
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月1日  下午3:24:50
	 *@Version:1.0
	 */
	List<String> findAuthorities(String username);
	/**
	 *@return 用户是否登录
	 *@Description: 判断用户是否登录
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月1日  下午3:24:25
	 *@Version:1.0
	 */
	boolean isAuthenticated();
	/**
	 *@return 当前登录用户,若不存在则返回null
	 *@Description:获取当前登录用户
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月1日  下午3:24:05
	 *@Version:1.0
	 */
	Admin getCurrent();
	/**
	 * 
	 *@return 当前登录用户名,若不存在则返回null
	 *@Description:获取当前登录用户名
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月1日  下午3:25:25
	 *@Version:1.0
	 */
	String getCurrentUsername();
	/**
	 *@param username
	 *@param password
	 *@return	用户信息
	 *@Description:根据用户名和密码查询用户信息
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月1日  下午6:00:17
	 *@Version:1.0
	 */
	Admin findAdminByUsernameAndPassword(String username,String password);
	/**
	 *@param newPwd 新密码
	 *@param oldPwd 旧密码
	 *@param username 用户名
	 *@return 返回的结果
	 *@Description: 修改用户的密码
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月1日  下午7:45:43
	 *@Version:1.0
	 */
	String updateAdminPassword(String newPwd,String oldPwd,String username);
}

package cn.symdata.service;

import java.util.List;

import org.springframework.data.domain.Page;

import cn.symdata.common.exception.DatabaseException;
import cn.symdata.entity.User;

/**
 *@Copyright:Copyright (c) 2012-2015
 *@Company:symdata
 *@Title:操作用户的业务逻辑层
 *@Description:
 *@Author:zhangnan#symdata
 *@Since:2015年9月1日  下午3:23:23
 *@Version:1.0
 */
public interface UserService {
	
	/**
	 * 
	 * @Title: findUserBySql 
	 * @Description: 查询用户列表分页根据不同的条件
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param params
	 * @param @param pageable
	 * @param @return   
	 * @return Page<User>  
	 * @throws 
	 * 2015年9月9日下午5:43:59
	 */
	public Page<User> findUserByHql(User user,Integer page) throws DatabaseException;
	
	/**
	 * 
	 * @Title: findOne 
	 * @Description:根据用户ID查询用户信息
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param id
	 * @param @return   
	 * @return User  
	 * @throws 
	 * 2015年9月10日下午5:00:25
	 */
	public User findOne(String id) throws DatabaseException;
	
	/**
	 *@param username
	 *@return 用户信息
	 *@Description:根据用户名查询用户信息
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月1日  下午3:25:04
	 *@Version:1.0
	 */
	public User findByUsername(String username) throws DatabaseException;
	
	/**
	 *@param username
	 *@param password
	 *@return	用户信息
	 *@Description:根据用户名和密码查询用户信息
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月1日  下午6:00:17
	 *@Version:1.0
	 */
	User findUserByUsernameAndPassword(String username,String password) throws DatabaseException;
	
	/**
	 *@param id
	 *@return 权限,若不存在则返回null
	 *@Description:根据ID查找权限
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月1日  下午3:24:50
	 *@Version:1.0
	 */
	List<String> findAuthorities(String id) throws DatabaseException;
	
	/**
	 *@return 当前登录用户,若不存在则返回null
	 *@Description:获取当前登录用户
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月1日  下午3:24:05
	 *@Version:1.0
	 */
	User getCurrent() throws DatabaseException;
	/**
	 * 
	 *@return 当前登录用户名,若不存在则返回null
	 *@Description:获取当前登录用户名
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月1日  下午3:25:25
	 *@Version:1.0
	 */
	String getCurrentUsername() throws DatabaseException;
	
	/**
	 * 
	 * @Title: isAuthenticated 
	 * @Description:判断当前用户是否登陆
	 * @Autohr guoxuelian#symdata.cn
	 * @param @return   
	 * @return boolean  
	 * @throws 
	 * 2015年9月12日下午11:44:33
	 */
	public boolean isAuthenticated() throws DatabaseException;
	
	/**
	 *@param user
	 *@return 
	 *@Description:保存用户信息
	 *@Author:zhangnan#symdata
	 *@Since:2015年8月31日  下午6:59:09
	 *@Version:1.0
	 */
	public User save(User user) throws DatabaseException;
	/**
	 *@param user
	 *@return 
	 *@Description:修改用户信息
	 *@Author:zhangnan#symdata
	 *@Since:2015年8月31日  下午6:59:09
	 *@Version:1.0
	 */
	public User update(User user) throws DatabaseException;
	
	/**
	 *@param password 密码
	 *@param username 用户名
	 *@return 修改的行数
	 *@Description: 修改用户的密码
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月1日  下午7:45:43
	 *@Version:1.0
	 */
	Integer updateUserPassword(String username,String newPwd,String olePwd) throws DatabaseException;
	
	/**
	 * 
	 * @Title: updateUsernameAndRealNameAndEmailAndIsEnable 
	 * @Description: 修改用户信息
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param username
	 * @param @param realName
	 * @param @param email
	 * @param @param isEnable
	 * @param @param id
	 * @param @return   
	 * @return int  
	 * @throws 
	 * 2015年9月12日下午11:39:58
	 */
	public Integer updateUsernameAndRealNameAndEmailAndIsEnable(User user) throws DatabaseException;
	
	/**
	 * 
	 * @Title: updateUserIsEnable 
	 * @Description: 删除用户改为1
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param isEnable
	 * @param @param id
	 * @param @return   
	 * @return int  
	 * @throws 
	 * 2015年9月10日下午7:37:09
	 */
	public Integer updateUserIsEnable(String id) throws DatabaseException;
	
	/**
	 * 
	 * @Title: updateStatus 
	 * @Description: TODO
	 * @Autohr guoxuelian#zymdata.cn
	 * @param @param id
	 * @param @return
	 * @param @throws DatabaseException   
	 * @return Integer  
	 * @throws 
	 * 2015年9月17日下午10:12:13
	 */
	public Integer updateStatus(int status,String id) throws DatabaseException;
	
	/**
	 * 
	 * @Title: authorizeRoleOperate 
	 * @Description:给用户授权角色操作
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param userId 用户ID
	 * @param @param roleIds 角色ID
	 * @param @throws DatabaseException   
	 * @return void  
	 * @throws 
	 * 2015年9月15日上午11:45:42
	 */
	public User authorizeRoleOperate(User user,String roleIds) throws DatabaseException;
	
	/**
	 * 
	 * @Title: authorizePowerOperate 
	 * @Description: 给用户授权按钮
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param userId 用户ID
	 * @param @param powerIds 按钮ID
	 * @param @throws DatabaseException   
	 * @return void  
	 * @throws 
	 * 2015年9月15日上午11:45:59
	 */
	public User authorizePowerOperate(User user,String powerIds,String menuIds) throws DatabaseException;
	
	/**
	 * 
	 * @Title: authorizeDataFieldOperate 
	 * @Description: 给用户授权字段
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param user 用户
	 * @param @param dataFieldIds 字段ID
	 * @param @throws DatabaseException   
	 * @return void  
	 * @throws 
	 * 2015年9月15日上午11:46:28
	 */
	public User authorizeDataFieldOperate(User user,String dataFieldIds,String menuIds) throws DatabaseException;
	
	/**
	 * 
	 * @Title: findByUserNameAndId 
	 * @Description: 编辑时，校验用户名是否重复，排除当前
	 * @Autohr guoxuelian#zymdata.cn
	 * @param @param username
	 * @param @param id
	 * @param @return
	 * @param @throws DatabaseException   
	 * @return User  
	 * @throws 
	 * 2015年9月17日下午2:13:01
	 */
	public boolean findByUserNameAndId(String username,String id) throws DatabaseException;
}

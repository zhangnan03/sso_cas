package cn.symdata.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.symdata.common.exception.DatabaseException;
import cn.symdata.entity.Menu;
import cn.symdata.entity.Role;
import cn.symdata.entity.User;

/**
 *@Copyright:Copyright (c) 2012-2015
 *@Company:symdata
 *@Title:
 *@Description:角色业务操作
 *@Author:zhangnan#symdata
 *@Since:2015年9月8日  下午2:15:35
 *@Version:1.0
 */
public interface RoleService {
	/**
	 *@param id
	 *@return
	 *@Description:根据角色编号，查询角色信息
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月8日  下午2:15:56
	 *@Version:1.0
	 */
	public Role findOne(String id) throws DatabaseException;
	/**
	 * 
	 *@return
	 *@Description:查询所有的角色
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月8日  下午2:16:01
	 *@Version:1.0
	 */
	public List<Role> findAll() throws DatabaseException;

	/**
	 * 
	 * @Title: findByEnable 
	 * @Description: 查找所有可用的角色
	 * @Autohr guoxuelian#symdata.cn
	 * @param @return
	 * @param @throws DatabaseException   
	 * @return List<Role>  
	 * @throws 
	 * 2015年9月15日下午4:54:17
	 */
	List<Role> authorizeRoleList(User user) throws DatabaseException;
	
	/**
	 * 
	 *@param name
	 *@return
	 *@Description:根据角色名称查询角色信息
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月8日  下午2:16:08
	 *@Version:1.0
	 */
	public Role findByName(String name) throws DatabaseException;
	/**
	 *@param code
	 *@return
	 *@Description:根据角色编码查询角色信息
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月10日  上午11:53:39
	 *@Version:1.0
	 */
	public Role findByCode(String code) throws DatabaseException;
	/**
	 * 
	 *@param role
	 *@return
	 *@Description:保存角色信息
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月8日  下午2:16:11
	 *@Version:1.0
	 */
	public Role save(Role role) throws DatabaseException;
	/**
	 * 
	 *@param role
	 *@return
	 *@Description:修改角色信息
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月8日  下午2:16:13
	 *@Version:1.0
	 */
	public Integer update(Role role) throws DatabaseException;
	
	/**
	 * 
	 * @Title: updateIsEnable 
	 * @Description: 删除角色,改为不可用
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param id
	 * @param @return   
	 * @return int  
	 * @throws 
	 * 2015年9月10日下午9:33:28
	 */
	public Integer updateIsEnable(String id) throws DatabaseException;
	
	/**
	 * 
	 * @Title: findUserByHql 
	 * @Description: 根据条件查找角色分页
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param user
	 * @param @param pageable
	 * @param @return   
	 * @return Page<Role>  
	 * @throws 
	 * 2015年9月11日上午10:38:56
	 */
	public Page<Role> findRoleByHql(Role role, Pageable pageable) throws DatabaseException;
	
	/**
	 * 
	 * @Title: findByCodeAndId 
	 * @Description: 编辑时，校验用户名是否重复，排除当前
	 * @Autohr guoxuelian#zymdata.cn
	 * @param @param username
	 * @param @param id
	 * @param @return
	 * @param @throws DatabaseException   
	 * @return Page<User>  
	 * @throws 
	 * 2015年9月17日下午2:11:23
	 */
	public boolean findByCodeAndId(String code,String id) throws DatabaseException;
	
	/**
	 * 
	 * @Title: showMenuPowerList 
	 * @Description: 根据角色ID查看菜单功能
	 * @Autohr guoxuelian#zymdata.cn
	 * @param @param roleId
	 * @param @return
	 * @param @throws DatabaseException   
	 * @return List<Menu>  
	 * @throws 
	 * 2015年9月21日下午3:22:28
	 */
	List<Menu> showMenuPowerList(String roleId) throws DatabaseException;
	
	/**
	 * 
	 * @Title: authorizeMenuPowerOperator 
	 * @Description: 角色菜单授权
	 * @Autohr guoxuelian#zymdata.cn
	 * @param @param menuIds
	 * @param @param roleIds
	 * @param @return
	 * @param @throws DatabaseException   
	 * @return Message  
	 * @throws 
	 * 2015年9月22日上午11:26:22
	 */
	public void authorizeMenuPowerOperator(String menuIds,String roleIds) throws DatabaseException;
}

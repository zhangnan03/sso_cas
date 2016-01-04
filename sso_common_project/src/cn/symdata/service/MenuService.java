package cn.symdata.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.symdata.common.exception.DatabaseException;
import cn.symdata.entity.Menu;
import cn.symdata.entity.Role;
/**
 *@Copyright:Copyright (c) 2012-2015
 *@Company:symdata
 *@Title:
 *@Description:菜单业务操作
 *@Author:zhangnan#symdata
 *@Since:2015年9月8日  下午2:25:39
 *@Version:1.0
 */
public interface MenuService {
	/**
	 *@param pageable
	 *@return
	 *@Description:根据分页对象，查询菜单的分页信息
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月8日  下午2:26:15
	 *@Version:1.0
	 */
	public Page<Menu> findAll(Pageable pageable) throws DatabaseException;
	/**
	 *@return
	 *@Description:查询所有的菜单信息
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月8日  下午2:26:19
	 *@Version:1.0
	 */
	public List<Menu> findAll() throws DatabaseException;
	/**
	 * 
	 *@param id
	 *@return
	 *@Description:根据菜单编号查询菜单信息
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月8日  下午2:26:23
	 *@Version:1.0
	 */
	public Menu findOne(String id) throws DatabaseException;
	
	/**
	 * 
	 * @Title: findBySystemMark 
	 * @Description: 根据系统标识查询菜单信息
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param name
	 * @param @return   
	 * @return Menu  
	 * @throws 
	 * 2015年9月11日下午3:12:02
	 */
	public List<Menu> findBySystemMark(String systemMark) throws DatabaseException;
	
	/**
	 * 
	 *@param code
	 *@return
	 *@Description:根据菜单编码，查询菜单信息
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月8日  下午2:26:32
	 *@Version:1.0
	 */
	public Menu findByCode(String code) throws DatabaseException;
	
	/**
	 * 
	 *@param menu
	 *@return
	 *@Description:修改菜单信息
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月8日  下午2:26:29
	 *@Version:1.0
	 */
	public Menu update(Menu menu) throws DatabaseException;
	
	/**
	 * 
	 * @Title: updateMenuIsEnable 
	 * @Description: 删除菜单，改为不可用
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param id
	 * @param @return   
	 * @return int  
	 * @throws 
	 * 2015年9月11日下午12:38:20
	 */
	public Integer updateMenuIsEnable(String id) throws DatabaseException;
	
	/**
	 * 
	 * @Title: save 
	 * @Description: 添加菜单
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param user
	 * @param @return   
	 * @return User  
	 * @throws 
	 * 2015年9月11日下午3:38:05
	 */
	public Menu save(Menu menu) throws DatabaseException;
	
	/**
	 * 
	 * @Title: authorizeMenuRoleList 
	 * @Description: 判断当前角色是否拥有该菜单的角色
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param menuId
	 * @param @return
	 * @param @throws DatabaseException   
	 * @return List<Role>  
	 * @throws 
	 * 2015年9月15日下午4:35:29
	 */
	public List<Role> authorizeMenuRoleList(String menuId) throws DatabaseException;
	
	/**
	 * 
	 * @Title: authorizeOperate 
	 * @Description:给菜单授权角色操作
	 * @Autohr guoxuelian#symdata.cn
	 * @param    
	 * @return void  
	 * @throws 
	 * 2015年9月12日上午10:10:06
	 */
	public void authorizeMenuRoleOperate(String id,String ids) throws DatabaseException;
	
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
}

package cn.symdata.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cn.symdata.common.exception.DatabaseException;
import cn.symdata.entity.Role;
/**
 *@Copyright:Copyright (c) 2012-2015
 *@Company:symdata
 *@Title:
 *@Description:角色操作
 *@Author:zhangnan#symdata
 *@Since:2015年9月8日  下午2:05:01
 *@Version:1.0
 */
public interface RoleDao  extends BaseDao<Role>{
	/**
	 * 
	 * @Title: findByName 
	 * @Description: 根据角色名字查询角色信息
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param name
	 * @param @return   
	 * @return Role  
	 * @throws 
	 * 2015年9月10日下午1:46:46
	 */
	Role findByName(String name) throws DatabaseException;
	
	/**
	 * 
	 * @Title: findByCode 
	 * @Description: 根据角色CODE查询角色信息
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param code
	 * @param @return   
	 * @return Role  
	 * @throws 
	 * 2015年9月10日下午1:47:02
	 */
	@Query("from Role r where r.isEnable = 0 and r.code=?1")
	Role findByCode(String code) throws DatabaseException;
	
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
	@Query("from Role where isEnable = 0")
	List<Role> findByEnable() throws DatabaseException;
	
	/**
	 * 
	 * @Title: updateCodeAndNameAndDescription 
	 * @Description: 修改角色信息
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param code
	 * @param @param name
	 * @param @param description
	 * @param @return   
	 * @return int  
	 * @throws 
	 * 2015年9月10日下午1:46:26
	 */
	@Modifying
	@Query("update Role set code =?1,name=?2,description=?3 where isEnable=0 and id=?4")
	public int updateCodeAndNameAndDescription(String code,String name,String description,String id) throws DatabaseException;
	
	/**
	 * 
	 * @Title: updateIsEnable 
	 * @Description: 删除角色
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param id
	 * @param @return   
	 * @return int  
	 * @throws 
	 * 2015年9月10日下午9:33:28
	 */
	@Modifying
	@Query("update Role set isEnable =1 where id=?1")
	public int updateIsEnable(String id) throws DatabaseException;
	
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
	@Query("from Role r where r.isEnable=0 and r.code =?1 and r.id !=?2")//注：查询对象需要起别名，否则报错
	public Role findByCodeAndId(String code,String id) throws DatabaseException;
	/**
	 *@param ids
	 *@return
	 *@throws DatabaseException
	 *@Description:查询指定编号区域的角色集合
	 *@Author:zhangnan#symdata
	 *@Since:2015年12月8日  上午11:13:00
	 *@Version:1.0
	 */
	@Query("from Role where isEnable = 0 and id in(?1)")
	List<Role> findByIds(List<String> ids) throws DatabaseException;
}

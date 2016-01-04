package cn.symdata.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cn.symdata.common.exception.DatabaseException;
import cn.symdata.entity.Menu;
/**
 *@Copyright:Copyright (c) 2012-2015
 *@Company:symdata
 *@Title:
 *@Description:菜单操作
 *@Author:zhangnan#symdata
 *@Since:2015年9月8日  下午2:13:57
 *@Version:1.0
 */
public interface MenuDao extends BaseDao<Menu>{
	/**
	 * 
	 * @Title: findByCode 
	 * @Description: 根据菜单code查询菜单信息
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param code
	 * @param @return   
	 * @return Menu  
	 * @throws 
	 * 2015年9月11日下午1:18:35
	 */
	@Query("from Menu m where m.isEnable = 0 and m.code=?1")
	public abstract Menu findByCode(String code) throws DatabaseException;
	
	/**
	 * 
	 * @Title: findByName 
	 * @Description: 根据菜单中文名字查询菜单信息
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param name
	 * @param @return   
	 * @return Menu  
	 * @throws 
	 * 2015年9月11日下午3:12:02
	 */
	public abstract Menu findByName(String name) throws DatabaseException;
	
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
	@Query("from Menu where systemMark = ?1 and isEnable=0")
	public List<Menu> findBySystemMark(String systemMark) throws DatabaseException;
	
	/**
	 * 
	 * @Title: findByAll 
	 * @Description: 查询所有可用菜单
	 * @Autohr guoxuelian#symdata.cn
	 * @param @return
	 * @param @throws DatabaseException   
	 * @return List<Menu>  
	 * @throws 
	 * 2015年9月14日下午5:34:46
	 */
	@Query("from Menu where isEnable=0")
	public List<Menu> findByAll() throws DatabaseException;
	
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
	@Modifying
	@Query("update Menu set isEnable =1 where id = ?1")
	public int updateMenuIsEnable(String id) throws DatabaseException;
	
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
	@Query("from Menu m where m.isEnable=0 and m.code =?1 and m.id !=?2")//注：查询对象需要起别名，否则报错
	public Menu findByCodeAndId(String code,String id) throws DatabaseException;
}

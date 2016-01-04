package cn.symdata.service;

import java.util.List;

import org.springframework.data.domain.Page;

import cn.symdata.common.exception.DatabaseException;
import cn.symdata.entity.Power;
import cn.symdata.entity.User;

/**
 *@Copyright:Copyright (c) 2012-2015
 *@Company:symdata
 *@Title:
 *@Description:按钮控件业务操作
 *@Author:zhangnan#symdata
 *@Since:2015年9月8日  下午2:07:24
 *@Version:1.0
 */
public interface PowerService {
	/**
	 * 
	 * @Title: findAll 
	 * @Description: 查询所有按钮
	 * @Autohr guoxuelian#symdata.cn
	 * @param @return
	 * @param @throws ServiceException   
	 * @return List<Role>  
	 * @throws 
	 * 2015年9月12日下午5:03:26
	 */
	public List<Power> findAll() throws DatabaseException;
	/**
	 * 
	 *@param id
	 *@return
	 *@Description:根据编号查询控件信息
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月8日  下午2:38:14
	 *@Version:1.0
	 */
	public Power findOne(String id) throws DatabaseException;
	/**
	 * 
	 *@param code
	 *@return
	 *@Description:根据编码，查询控件信息
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月8日  下午2:38:23
	 *@Version:1.0
	 */
	public Power findByCode(String code) throws DatabaseException;
	/**
	 * 
	 *@param control
	 *@return
	 *@Description:保存控件信息
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月8日  下午2:38:29
	 *@Version:1.0
	 */
	public Power save(Power power) throws DatabaseException;
	
	/**
	 * 
	 * @Title: updateUserIsEnable 
	 * @Description: 删除按钮
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param id
	 * @param @return   
	 * @return int  
	 * @throws 
	 * 2015年9月12日上午9:55:50
	 */
	public Integer updatePowerIsEnable(String id) throws DatabaseException;
	
	/**
	 * 
	 * @Title: findPowerByHql 
	 * @Description: 按钮分页
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param user
	 * @param @param pageable
	 * @param @return   
	 * @return Page<User>  
	 * @throws 
	 * 2015年9月12日下午2:40:21
	 */
	public Page<Power> findPowerByHql(Power power, Integer page) throws DatabaseException;
	
	/**
	 * 
	 * @Title: update 
	 * @Description: 修改按钮信息
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param power
	 * @param @return   
	 * @return Power  
	 * @throws 
	 * 2015年9月12日下午4:44:52
	 */
	public Power update(Power power) throws DatabaseException;
	
	/**
	 * 
	 * @Title: findPowersByEnable 
	 * @Description: 获取所有可用的按钮
	 * @Autohr guoxuelian#symdata.cn
	 * @param @return
	 * @param @throws DatabaseException   
	 * @return List<Power>  
	 * @throws 
	 * 2015年9月15日下午5:13:48
	 */
	public List<Power> findPowersByEnable(User user,String menuIds) throws DatabaseException;
	
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

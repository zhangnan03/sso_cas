package cn.symdata.dao;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cn.symdata.common.exception.DatabaseException;
import cn.symdata.entity.User;

public interface UserDao extends BaseDao<User>{
	/**
	 *@param username 用户名
	 *@return 用户信息
	 *@Description:根据用户名查询用户信息
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月1日  下午7:32:18
	 *@Version:1.0
	 */
	@Query("from User u where u.isEnable=0 and u.username =?1")
	User findByUsername(String username) throws DatabaseException;
	/**
	 *@param username 用户名
	 *@param password 密码
	 *@return
	 *@Description:根据用户名和密码查询用户信息
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月1日  下午7:32:45
	 *@Version:1.0
	 */
	@Query("from User u where u.isEnable=0 and u.username=?1 and u.password=?2") //效果等同于下面
	User findUserByUsernameAndPassword(String username,String password) throws DatabaseException;
	/**
	 *@param startTime 起始时间
	 *@param endTime 截止时间
	 *@param pageable
	 *@return 分页的用户数据
	 *@Description:查询一段时间内的用户信息
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月1日  下午7:31:10
	 *@Version:1.0
	 */
	public Page<User> findByCreateTimeBetween(Date startTime,Date endTime,Pageable pageable) throws DatabaseException;
	
	/**
	 * 
	 * @Title: findUser 
	 * @Description: 
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param startTime
	 * @param @param endTime
	 * @param @param pageable
	 * @param @return
	 * @param @throws DatabaseException   
	 * @return Page<User>  
	 * @throws 
	 * 2015年9月14日上午11:31:00
	 */
	@Query("from User u where u.createTime >=?1 and u.createTime <=?2")//注：查询对象需要起别名，否则报错
	public Page<User> findUser(Date startTime,Date endTime,Pageable pageable) throws DatabaseException;
	/**
	 *@param password 新密码
	 *@param username 用户名
	 *@return 修改的行数
	 *@Description:修改用户的密码
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月1日  下午7:49:29
	 *@Version:1.0
	 */
	@Modifying
	@Query("update User set password =?1,isUpdatePwd=1 where isEnable=0 and username = ?2")
	public int updateUserPassword(String password,String username) throws DatabaseException;
	
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
	@Modifying
	@Query("update User u set u.isEnable =1 where u.id = ?1")
	public int updateUserIsEnable(String id) throws DatabaseException;
	
	/**
	 * 
	 * @Title: updateUsernameAndRealNameAndEmailAndIsEnable 
	 * @Description: 修改用户信息
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param username
	 * @param @param realName
	 * @param @param email
	 * @param @param isEnable
	 * @param @return   
	 * @return int  
	 * @throws 
	 * 2015年9月12日下午11:37:01
	 */
	@Modifying
	@Query("update User u set u.username =?1,u.realName=?2,u.email=?3,u.status=?4 where u.id = ?5")
	public int updateUsernameAndRealNameAndEmailAndIsEnable(String username,String realName,String email,int status,String id) throws DatabaseException;
	
	/**
	 * 
	 * @Title: updateStatus 
	 * @Description: 修改用户状态
	 * @Autohr guoxuelian#zymdata.cn
	 * @param @param id
	 * @param @return
	 * @param @throws DatabaseException   
	 * @return int  
	 * @throws 
	 * 2015年9月17日下午10:11:08
	 */
	@Modifying
	@Query("update User u set u.status =?1 where u.id = ?2")
	public int updateStatus(int status,String id) throws DatabaseException;
	
	/**
	 * 
	 * @Title: findByUserNameAndId 
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
	@Query("from User u where u.isEnable=0 and u.username =?1 and u.id !=?2")//注：查询对象需要起别名，否则报错
	public User findByUserNameAndId(String username,String id) throws DatabaseException;
}

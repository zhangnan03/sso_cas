package cn.symdata.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cn.symdata.common.exception.DatabaseException;
import cn.symdata.entity.Power;

/**
 * 
 * @ClassName: PowerDao 
 * @Description: 控件按钮操作 
 * @author guoxuelian@symdata.cn
 * @date 2015年9月15日 下午5:35:08 
 *
 */
public interface PowerDao extends BaseDao<Power>{
	
	/**
	 * 
	 * @Title: findPowersByEnable 
	 * @Description: 获取所有可用的按钮
	 * @Autohr guoxuelian#symdata.cn
	 * @param @return
	 * @param @throws DatabaseException   
	 * @return int  
	 * @throws 
	 * 2015年9月15日下午5:12:38
	 */
	@Query("from Power where isEnable = 0")
	public List<Power> findPowersByEnable() throws DatabaseException;
	
	/**
	 * 
	 * @Title: findByCode 
	 * @Description: 根据code码查找按钮
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param code
	 * @param @return
	 * @param @throws DatabaseException   
	 * @return Power  
	 * @throws 
	 * 2015年9月15日下午5:34:48
	 */
	@Query("from Power p where p.isEnable=0 and p.code =?1")
	Power findByCode(String code) throws DatabaseException;
	
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
	@Modifying
	@Query("update Power set isEnable =1 where id = ?1")
	public int updatePowerIsEnable(String id) throws DatabaseException;
	
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
	@Query("from Power p where p.isEnable=0 and p.code =?1 and p.id !=?2")//注：查询对象需要起别名，否则报错
	public Power findByCodeAndId(String code,String id) throws DatabaseException;
	/**
	 *@param menuId
	 *@return
	 *@throws DatabaseException
	 *@Description:根据按钮编号查询按钮集合
	 *@Author:zhangnan#symdata
	 *@Since:2015年12月7日  下午9:39:00
	 *@Version:1.0
	 */
	@Query("from Power where isEnable = 0 and id in(?1)")
	public List<Power> findPowersByIds(List<String> ids) throws DatabaseException;
}

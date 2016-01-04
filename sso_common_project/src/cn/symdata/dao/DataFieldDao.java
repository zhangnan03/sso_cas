package cn.symdata.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cn.symdata.common.exception.DatabaseException;
import cn.symdata.entity.DataField;

/**
 *@Copyright:Copyright (c) 2012-2015
 *@Company:symdata
 *@Title:
 *@Description:控件按钮操作
 *@Author:zhangnan#symdata
 *@Since:2015年9月8日  下午2:03:06
 *@Version:1.0
 */
public interface DataFieldDao extends BaseDao<DataField>{
	
	/**
	 * 
	 * @Title: findDataFieldByEnable 
	 * @Description: 获取所有可用字段
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param user
	 * @param @return
	 * @param @throws DatabaseException   
	 * @return List<DataField>  
	 * @throws 
	 * 2015年9月15日下午5:31:24
	 */
	@Query("from DataField where isEnable = 0")
	public List<DataField> findDataFieldByEnable() throws DatabaseException;
	
	/**
	 * 
	 * @Title: findByCode 
	 * @Description: 根据code查询字段
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param code
	 * @param @return   
	 * @return DataField  
	 * @throws 
	 * 2015年9月12日下午3:06:43
	 */
	@Query("from DataField d where d.isEnable = 0 and d.code=?1")
	DataField findByCode(String code) throws DatabaseException;
	
	/**
	 * 
	 * @Title: findByDescription 
	 * @Description: 根据
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param name
	 * @param @return   
	 * @return DataField  
	 * @throws 
	 * 2015年9月12日下午3:07:00
	 */
	DataField findByName(String name) throws DatabaseException;
	
	@Modifying
	@Query("update DataField set isEnable =1 where id = ?1")
	public int updateDataFieldIsEnable(String id) throws DatabaseException;
	
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
	@Query("from DataField d where d.isEnable=0 and d.code =?1 and d.id !=?2")//注：查询对象需要起别名，否则报错
	public DataField findByCodeAndId(String code,String id) throws DatabaseException;
	/**
	 *@param ids
	 *@return
	 *@throws DatabaseException
	 *@Description:查询指定编号的字段集合
	 *@Author:zhangnan#symdata
	 *@Since:2015年12月8日  上午11:16:04
	 *@Version:1.0
	 */
	@Query("from DataField where isEnable = 0 and id in(?1)")
	public List<DataField> findDataFieldByIds(List<String> ids) throws DatabaseException;
}

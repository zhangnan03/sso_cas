package cn.symdata.service;

import java.util.List;

import org.springframework.data.domain.Page;

import cn.symdata.common.exception.DatabaseException;
import cn.symdata.entity.DataField;
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
public interface DataFieldService {
	/**
	 * 
	 * @Title: findAll 
	 * @Description: 查询所有字段
	 * @Autohr guoxuelian#symdata.cn
	 * @param @return
	 * @param @throws ServiceException   
	 * @return List<DataField>  
	 * @throws 
	 * 2015年9月12日下午5:06:41
	 */
	public List<DataField> findAll() throws DatabaseException;
	
	/**
	 * 
	 * @Title: findPowersByEnable 
	 * @Description:获取所有可用字段
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param user
	 * @param @return
	 * @param @throws DatabaseException   
	 * @return List<Power>  
	 * @throws 
	 * 2015年9月15日下午5:30:29
	 */
	public List<DataField> findDataFieldByEnable(User user,String menuIds) throws DatabaseException;
	
	/**
	 * 
	 *@param id
	 *@return
	 *@Description:根据编号查询控件信息
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月8日  下午2:38:14
	 *@Version:1.0
	 */
	public DataField findOne(String id) throws DatabaseException;
	
	/**
	 * 
	 *@param code
	 *@return
	 *@Description:根据编码，查询控件信息
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月8日  下午2:38:23
	 *@Version:1.0
	 */
	public DataField findByCode(String code) throws DatabaseException;
	/**
	 * 
	 *@param control
	 *@return
	 *@Description:保存控件信息
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月8日  下午2:38:29
	 *@Version:1.0
	 */
	public DataField save(DataField dataField) throws DatabaseException;
		
	/**
	 * 
	 * @Title: findDataFieldByHql 
	 * @Description: 字段分页
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param dataField
	 * @param @param pageable
	 * @param @return   
	 * @return Page<DataField>  
	 * @throws 
	 * 2015年9月12日下午2:50:20
	 */
	public Page<DataField> findDataFieldByHql(DataField dataField ,Integer page) throws DatabaseException;
	
	/**
	 * 
	 * @Title: update 
	 * @Description: 修改字段
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param user
	 * @param @return   
	 * @return DataField  
	 * @throws 
	 * 2015年9月12日下午3:15:36
	 */
	public DataField update(DataField dataField) throws DatabaseException;
	
	/**
	 * 
	 * @Title: updateDataFieldIsEnable 
	 * @Description: 删除字段
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param id
	 * @param @return   
	 * @return int  
	 * @throws 
	 * 2015年9月12日下午3:21:06
	 */
	public Integer updateDataFieldIsEnable(String id) throws DatabaseException;
	
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

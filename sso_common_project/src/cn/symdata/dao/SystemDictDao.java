package cn.symdata.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.symdata.entity.SystemDict;

public interface SystemDictDao  extends BaseDao<SystemDict>{

	/**
	 * 
	 * @Title: findByDictType 
	 * @Description: 根据字典类别查询数据字典
	 * @Autohr guoxuelian#zymdata.cn
	 * @param @return   
	 * @return List<SystemDict>  
	 * @throws 
	 * 2015年9月24日下午6:13:11
	 */
	@Query("from SystemDict s where s.status=1 and s.dictType=? order by orderId asc")
	List<SystemDict> findByDictType(String dictType);
	
	/**
	 * 
	 * @Title: findByDictTypeAndDictCode 
	 * @Description: 根据字典类别和字典编码查询对象
	 * @Autohr guoxuelian#zymdata.cn
	 * @param @param dictType
	 * @param @param dictCode
	 * @param @return   
	 * @return SystemDict  
	 * @throws 
	 * 2015年9月25日下午4:11:04
	 */
	@Query("from SystemDict s where s.status=1 and s.dictType=? and s.dictCode=?")
	SystemDict findByDictTypeAndDictCode(String dictType,String dictCode);

}

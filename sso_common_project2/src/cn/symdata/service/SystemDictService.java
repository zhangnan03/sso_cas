package cn.symdata.service;

import java.util.List;

import org.springframework.data.domain.Page;

import cn.symdata.entity.SystemDict;
import cn.symdata.vo.SystemDictVo;

/**
 * 数据字典服务接口
 * @ClassName: SystemDictService 
 * @Description: TODO 
 * @author guoxuelian@zymdata.cn
 * @date 2015年9月24日 下午6:08:42 
 *
 */
public interface SystemDictService {

	/**
	 * 
	 * @Title: findAllList 
	 * @Description: 根据条件分页查找所有的数据字典
	 * @Autohr guoxuelian#zymdata.cn
	 * @param @return   
	 * @return Page<SystemDict>  
	 * @throws 
	 * 2015年9月24日下午6:04:00
	 */
	public Page<SystemDict> findAllList(SystemDictVo systemDictVo, Integer page);
	
	/**
	 * 
	 * @Title: findOne 
	 * @Description: 根据字典类别查找数据字典
	 * @Autohr guoxuelian#zymdata.cn
	 * @param @return   
	 * @return SystemDict  
	 * @throws 
	 * 2015年9月24日下午6:04:30
	 */
	List<SystemDictVo> findByDictType(String dictType);
	
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
	 * 2015年9月25日下午4:11:43
	 */
	SystemDictVo findByDictTypeAndDictCode(String dictType,String dictCode);
	
	/**
	 * 
	 * @Title: findOne 
	 * @Description: 根据ID查询数据字典对象
	 * @Autohr guoxuelian#zymdata.cn
	 * @param @param id
	 * @param @return   
	 * @return SystemDict  
	 * @throws 
	 * 2015年9月28日上午11:44:57
	 */
	SystemDict findOne(String id);
	/**
	 * 
	 * @Title: updateSystemDict 
	 * @Description: 修改数据字典
	 * @Autohr guoxuelian#zymdata.cn
	 * @param @param systemDictVo   
	 * @return void  
	 * @throws 
	 * 2015年9月28日上午11:25:23
	 */
	SystemDict updateSystemDict(SystemDict dict);
	
	/**
	 * 
	 * @Title: saveSystemDict 
	 * @Description: 添加数据字典
	 * @Autohr guoxuelian#zymdata.cn
	 * @param @param systemDictVo   
	 * @return void  
	 * @throws 
	 * 2015年9月28日上午11:26:23
	 */
	SystemDict saveSystemDict(SystemDict systemDict);
}

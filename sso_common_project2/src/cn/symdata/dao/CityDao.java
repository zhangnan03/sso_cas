package cn.symdata.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.symdata.entity.City;

public interface CityDao  extends BaseDao<City>{

	/**
	 * 
	 * @Title: queryProvinceAndCity 
	 * @Description: 查询所有的省或城市
	 * @Autohr panpengxu#zymdata.cn
	 * @param @return   
	 * @return List<City>  
	 * @throws 
	 * 2015年11月30日下午4:13:11
	 */
	@Query("from City c where c.parentId=?1")
	List<City> queryProvinceAndCity(String cityCode);
	

}

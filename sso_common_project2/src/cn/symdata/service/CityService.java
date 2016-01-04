package cn.symdata.service;

import java.util.List;

import cn.symdata.entity.City;

/**
 * 城市接口
 * @ClassName: queryProvinceAndCity 
 * @Description: TODO 
 * @author panpengxu@zymdata.cn
 * @date 2015年11月30日 下午4:08:42 
 *
 */
public interface CityService {

	/**
	 * 
	 * @Title: queryProvinceAndCity 
	 * @Description: 查询省市
	 * @Autohr panpengxu#zymdata.cn
	 * @param @param CityVo   
	 * @return void  
	 * @throws 
	 * 2015年11月30日下午16:26:23
	 */
	List<City> queryProvinceAndCity(String cityCode);
}

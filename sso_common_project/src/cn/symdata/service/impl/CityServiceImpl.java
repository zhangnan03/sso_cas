package cn.symdata.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.symdata.dao.CityDao;
import cn.symdata.entity.City;
import cn.symdata.service.CityService;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityDao cityDao;

	@Override
	public List<City> queryProvinceAndCity(String cityCode) {
		return cityDao.queryProvinceAndCity(cityCode.trim());
	}

}

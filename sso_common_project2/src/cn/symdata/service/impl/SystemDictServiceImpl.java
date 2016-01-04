package cn.symdata.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.symdata.common.exception.DatabaseException;
import cn.symdata.common.utils.JsonMapper;
import cn.symdata.dao.SystemDictDao;
import cn.symdata.dao.impl.SystemDictDaoImpl;
import cn.symdata.entity.SystemDict;
import cn.symdata.service.SystemDictService;
import cn.symdata.vo.SystemDictVo;

import com.google.common.collect.Lists;

@Service
public class SystemDictServiceImpl implements SystemDictService {

	@Autowired
	private SystemDictDao systemDictDao;
	@Autowired
	private SystemDictDaoImpl systemDictDaoImpl;

	@Autowired
//	private JedisTemplate jedisTemplate;
	private static JsonMapper json = JsonMapper.nonDefaultMapper();

	@Override
	public List<SystemDictVo> findByDictType(String dictType) {
		SystemDictVo systemDictVo = null;
		List<SystemDict> dictList = systemDictDao.findByDictType(dictType);
		List<SystemDictVo> listVo = Lists.newArrayList();
		for (SystemDict systemDict : dictList) {
			systemDictVo = new SystemDictVo();
			systemDictVo.setDictCode(systemDict.getDictCode());
			systemDictVo.setDictName(systemDict.getDictName());
			systemDictVo.setDictType(systemDict.getDictType());
			systemDictVo.setDictTypeName(systemDict.getDictTypeName());
			listVo.add(systemDictVo);
		}
		return listVo;
	}

	@Override
	public SystemDictVo findByDictTypeAndDictCode(String dictType,
			String dictCode) {
		SystemDictVo systemDictVo = null;
		SystemDict systemDict = systemDictDao.findByDictTypeAndDictCode(
				dictType, dictCode);
		if (systemDict != null) {
			systemDictVo = new SystemDictVo();
			systemDictVo.setDictCode(systemDict.getDictCode());
			systemDictVo.setDictName(systemDict.getDictName());
			systemDictVo.setDictType(systemDict.getDictType());
			systemDictVo.setDictTypeName(systemDict.getDictTypeName());
		}

		return systemDictVo;
	}

	@Override
	public Page<SystemDict> findAllList(SystemDictVo systemDictVo, Integer page) {
		Pageable pageable = new PageRequest(page != null ? page.intValue() - 1
				: 0, 10);
		Page<SystemDict> list = null;
		try {
			list = systemDictDaoImpl.findAllList(systemDictVo, pageable);
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	@Transactional(readOnly = false)
	public SystemDict updateSystemDict(SystemDict systemDict) {
//		try {
			systemDict = systemDictDao.save(systemDict);
//			if (systemDict != null) {//修改成功
//				String key = RedisObjectType.SYSTEM_DICT.getPrefix()+ systemDict.getDictType();
//				String keyValue = jedisTemplate.get(key);
//				if (!Strings.isNullOrEmpty(keyValue)) {
//					jedisTemplate.del(key);
//				}
//				List<SystemDict> dictList = (List<SystemDict>) systemDictDao.findByDictType(systemDict.getDictType());
//				jedisTemplate.setex(key, json.toJson(dictList),RedisObjectType.SYSTEM_DICT.getExpiredTime());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return systemDict;
	}

	@Override
	@Transactional(readOnly = false)
	public SystemDict saveSystemDict(SystemDict systemDict) {
		SystemDict dict = systemDictDao.save(systemDict);
//		try {
//			if (dict != null) {//保存成功
//				String key = RedisObjectType.SYSTEM_DICT.getPrefix()+ systemDict.getDictType();
//				String keyValue = jedisTemplate.get(key);
//				if (!Strings.isNullOrEmpty(keyValue)) {
//					jedisTemplate.del(key);
//				}
//				List<SystemDict> dictList = (List<SystemDict>) systemDictDao.findByDictType(systemDict.getDictType());
//				jedisTemplate.setex(key, json.toJson(dictList),RedisObjectType.SYSTEM_DICT.getExpiredTime());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return dict;
	}

	@Override
	public SystemDict findOne(String id) {
		return systemDictDao.findOne(id);
	}

}

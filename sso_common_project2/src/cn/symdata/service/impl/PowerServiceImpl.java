package cn.symdata.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.symdata.common.exception.DatabaseException;
import cn.symdata.dao.PowerDao;
import cn.symdata.dao.UserDao;
import cn.symdata.dao.impl.PowerDaoImpl;
import cn.symdata.entity.Power;
import cn.symdata.entity.User;
import cn.symdata.service.PowerService;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
/**
 * 
 * @ClassName: PowerServiceImpl 
 * @Description: 按钮操作
 * @author guoxuelian@symdata.cn
 * @date 2015年9月15日 下午5:36:23 
 *
 */
@Service
@Transactional(readOnly = true)
public class PowerServiceImpl implements PowerService{
	@Autowired
	private PowerDaoImpl powerDaoImpl;
	
	@Autowired
	private PowerDao powerDao;
	
	@Autowired
	private UserDao userDao;
	
	public Power findOne(String id) throws DatabaseException{
		return powerDao.findOne(id);
	}
	
	public Power findByCode(String code) throws DatabaseException{
		return powerDao.findByCode(code);
	}
	@Transactional(readOnly = false)
	public Power save(Power power) throws DatabaseException{
		return powerDao.save(power);
	}

	@Override
	@Transactional(readOnly = false)
	public Integer updatePowerIsEnable(String id) throws DatabaseException {
		return powerDao.updatePowerIsEnable(id);
	}
	
	@Transactional(readOnly = false)
	public Power update(Power power){
		return powerDao.save(power);
	}

	@Override
	public Page<Power> findPowerByHql(Power power, Integer page) throws DatabaseException {
		Pageable pageable    = new PageRequest(page != null ? page.intValue() - 1 : 0, 10);
		return powerDaoImpl.findPowerByHql(power,pageable);
	}
	
	@Override
	public List<Power> findAll() throws DatabaseException{
		return (List<Power>) powerDao.findAll(new Sort(Direction.ASC, "id"));
	}

	@Override
	public List<Power> findPowersByEnable(User user,String menuIds) throws DatabaseException {
		List<Power> resultList = Lists.newArrayList();
		List<Power> powerList = powerDao.findPowersByEnable();
		for (Power power : powerList) {
			String users = power.getUsers();
			if(users.contains(user.getId())){
				power.setIsChecked(1);
			}
			
			if(StringUtils.isNotBlank(menuIds)&&power.getMenu()!=null&&StringUtils.isNotBlank(power.getMenu().getId())){
				if(!menuIds.contains(power.getMenu().getId())){//过滤所有包含该菜单ID的按钮
					resultList.add(power);
				}
			}
		}
		
		if(resultList!=null&&resultList.size()>0){
			powerList.removeAll(resultList);
		}
		return powerList;
	}

	@Override
	public boolean findByCodeAndId(String code, String id) throws DatabaseException {
		Power power = null;
		code  = StringUtils.trimToEmpty(code);
		if(Strings.isNullOrEmpty(code)){
			return false;
		}
		if(StringUtils.isNotBlank(id)){
			power = powerDao.findByCodeAndId(code,id);
		}else{
			power = powerDao.findByCode(code);
		}
		if(power!=null){
			return false;
		}
		return true;
	}
	
}

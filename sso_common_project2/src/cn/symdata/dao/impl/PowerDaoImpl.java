package cn.symdata.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.symdata.common.exception.DatabaseException;
import cn.symdata.dao.CommonDaoCustom;
import cn.symdata.entity.Power;

import com.google.common.collect.Lists;

public class PowerDaoImpl {
	@Autowired
	private CommonDaoCustom<Power> commonDaoCustom;
	/**
	 * 
	 * @Title: queryBySql 
	 * @Description: 用户分页
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param sql
	 * @param @param params
	 * @param @param pageable
	 * @param @return   
	 * @return Page<T>  
	 * @throws 
	 * 2015年9月9日下午2:11:34
	 */
	public Page<Power> findPowerByHql(Power power, Pageable pageable) throws DatabaseException {
		List<String> params = Lists.newArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("FROM Power p where p.isEnable=0 ");
		if(StringUtils.isNotBlank(power.getCode())){
			sql.append("and p.code like ?");
			params.add("%"+power.getCode()+"%");
		}
		if(StringUtils.isNotBlank(power.getName())){
			sql.append("and p.name like ?");
			params.add("%"+power.getName()+"%");
		}
		if(power.getMenu()!=null&&StringUtils.isNotBlank(power.getMenu().getId())){
			sql.append("and p.menu.id in ("+power.getMenu().getId()+")");
		}
		return commonDaoCustom.getListByHsql(sql.toString(), params,pageable);
	}
}

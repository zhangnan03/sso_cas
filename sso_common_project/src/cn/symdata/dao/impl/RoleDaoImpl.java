package cn.symdata.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.symdata.common.exception.DatabaseException;
import cn.symdata.dao.CommonDaoCustom;
import cn.symdata.entity.Role;

import com.google.common.collect.Lists;

public class RoleDaoImpl {
	@Autowired
	private CommonDaoCustom<Role> commonDaoCustom;
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
	public Page<Role> findRoleByHql(Role role, Pageable pageable) throws DatabaseException {
		List<String> params = Lists.newArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("FROM Role r where r.isEnable=0 ");
		if(StringUtils.isNotBlank(role.getCode())){
			sql.append("and r.code like ?");
			params.add("%"+role.getCode()+"%");
		}
		if(StringUtils.isNotBlank(role.getName())){
			sql.append("and r.name like ?");
			params.add("%"+role.getName()+"%");
		}
		return commonDaoCustom.getListByHsql(sql.toString(), params,pageable);
	}
}

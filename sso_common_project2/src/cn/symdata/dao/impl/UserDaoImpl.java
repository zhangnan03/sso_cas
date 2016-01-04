package cn.symdata.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.symdata.common.exception.DatabaseException;
import cn.symdata.dao.CommonDaoCustom;
import cn.symdata.entity.User;

import com.google.common.collect.Lists;

public class UserDaoImpl {
	@Autowired
	private CommonDaoCustom<User> commonDaoCustom;
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
	public Page<User> findUserByHql(User user, Pageable pageable) throws DatabaseException {
		List<String> params = Lists.newArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("FROM User u where u.isEnable=0 ");
		if(StringUtils.isNotBlank(user.getRealName())){
			sql.append("and u.realName like ?");
			params.add("%"+user.getRealName()+"%");
		}
		if(StringUtils.isNotBlank(user.getUsername())){
			sql.append("and u.username like ?");
			params.add("%"+user.getUsername()+"%");
		}
		return commonDaoCustom.getListByHsql(sql.toString(), params,pageable);
	}
}

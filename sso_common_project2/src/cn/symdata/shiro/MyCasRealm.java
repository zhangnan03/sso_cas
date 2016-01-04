package cn.symdata.shiro;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import cn.symdata.common.exception.DatabaseException;
import cn.symdata.dao.UserDao;
import cn.symdata.entity.User;

import com.google.common.collect.Lists;

public class MyCasRealm extends CasRealm{
	@Autowired
	private UserDao userDao;
	@Override
	protected AuthorizationInfo getAuthorizationInfo(PrincipalCollection principals) {
		String username = (String)principals.getPrimaryPrincipal(); 
		if (StringUtils.isNotBlank(username)) {
			List<String> authorities = Lists.newArrayList();
			try {
				User user = userDao.findByUsername(username);
				List<String> rolesList     = user.getRoles();      //查询该用户的角色
				List<String> powerList     = user.getPowers();     //查询该用户的按钮
				List<String> dataFieldList = user.getDataFields(); //查询该用户的字段
				authorities.addAll(rolesList);
				authorities.addAll(powerList);
				authorities.addAll(dataFieldList);
				if (authorities != null) {
					SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
					authorizationInfo.addStringPermissions(authorities);
					return authorizationInfo;
				}
			} catch (DatabaseException e) {
				throw new UnknownAccountException();
			}
		}
		return null;
	}

}

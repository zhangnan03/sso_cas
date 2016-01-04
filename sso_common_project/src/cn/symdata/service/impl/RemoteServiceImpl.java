package cn.symdata.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.symdata.common.exception.DatabaseException;
import cn.symdata.dao.UserDao;
import cn.symdata.entity.Menu;
import cn.symdata.entity.User;
import cn.symdata.service.RemoteService;

import com.google.common.collect.Lists;

/**
 * 
 * @ClassName: RemoteInterfaceImpl 
 * @Description: 外部系统接口信息处理 
 * @author guoxuelian@symdata.cn
 * @date 2015年9月16日 下午5:09:18 
 *
 */
@Service
public class RemoteServiceImpl implements RemoteService{
	@Autowired
	private UserDao userDao;
	@Override
	public List<String> permission(User user,String systemId) throws DatabaseException {
		List<String> permissions = Lists.newArrayList();
		List<String> rolesList     = user.getRoles();      //查询该用户的角色
		permissions.addAll(rolesList);
		List<String> powerList     = user.getPowers();     //查询该用户的按钮
		permissions.addAll(powerList);
		List<String> dataFieldList = user.getDataFields(); //查询该用户的字段
		permissions.addAll(dataFieldList);
		return permissions;
	}

	@Override
	public List<Menu> getMenu(User user,String systemId) throws DatabaseException {
		List<Menu> menuList = user.getMenuList(systemId); //查询该用户的菜单
		return menuList;
	}

	@Transactional(readOnly=false)
	@Override
	public void updatePassword(String username, String newPwd) throws DatabaseException {
		userDao.updateUserPassword(newPwd, username);
	}

}

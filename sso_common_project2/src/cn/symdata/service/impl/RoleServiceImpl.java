package cn.symdata.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.symdata.common.exception.DatabaseException;
import cn.symdata.dao.MenuDao;
import cn.symdata.dao.RoleDao;
import cn.symdata.dao.impl.RoleDaoImpl;
import cn.symdata.entity.Menu;
import cn.symdata.entity.Role;
import cn.symdata.entity.User;
import cn.symdata.service.RoleService;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private MenuDao menuDao;
	@Autowired
	private RoleDaoImpl roleDaoImpl;
	
	public Role findOne(String id) throws DatabaseException{
		return roleDao.findOne(id);
	}
	
	public List<Role> findAll() throws DatabaseException{
		return (List<Role>) roleDao.findAll(new Sort(Direction.ASC, "id"));
	}
	
	public Role findByName(String name) throws DatabaseException{
		return roleDao.findByName(name);
	}
	public Role findByCode(String code) throws DatabaseException{
		return roleDao.findByCode(code);
	}
	@Transactional(readOnly = false)
	public Role save(Role role) throws DatabaseException{
		role.setCreateTime(new Date());
		return roleDao.save(role);
	}
	@Transactional(readOnly = false)
	public Integer update(Role role) throws DatabaseException{
		return roleDao.updateCodeAndNameAndDescription(role.getCode(), role.getName(),role.getDescription(),role.getId());
	}
	
	@Override
	public Integer updateIsEnable(String id) throws DatabaseException {
		return roleDao.updateIsEnable(id);
	}
	
	@Override
	public Page<Role> findRoleByHql(Role role, Pageable pageable) throws DatabaseException {
		return roleDaoImpl.findRoleByHql(role, pageable);
	}

	@Override
	public List<Role> authorizeRoleList(User user) throws DatabaseException {
		List<Role> roleList = roleDao.findByEnable();
		for (Role role : roleList) {
			String users = role.getUsers();
			if(users.contains(user.getId())){
				role.setIsChecked(1);
			}
		}
		return roleList;
	}

	@Override
	public boolean findByCodeAndId(String code, String id) throws DatabaseException {
		Role role = null;
		code  = StringUtils.trimToEmpty(code);
		if(Strings.isNullOrEmpty(code)){
			return false;
		}
		if(StringUtils.isNotBlank(id)){
			role = roleDao.findByCodeAndId(code,id);
		}else{
			role = roleDao.findByCode(code);
		}
		if(role!=null){
			return false;
		}
		return true;
	}

	@Override
	public List<Menu> showMenuPowerList(String roleId) throws DatabaseException {
		List<Menu> menu = null;
		Role role = roleDao.findOne(roleId);
		if(role!=null){
			menu = role.getMenuList();
		}
		return menu;
	}

	@Override
	@Transactional(readOnly = false)
	public void authorizeMenuPowerOperator(String menuIds, String roleIds) throws DatabaseException {
		List<String> roleIdList = Splitter.on(",").trimResults().splitToList(roleIds.substring(1));
		for (String roleId : roleIdList) {
			List<Menu> menuList = Lists.newArrayList();
			Role role = roleDao.findOne(roleId);
			if(role!=null){
				if(StringUtils.isNotBlank(menuIds)){
					List<String> list = Splitter.on(",").trimResults().splitToList(menuIds.substring(1));
					for (String string : list) {
						if(StringUtils.isNotBlank(string)){
							Menu menu = menuDao.findOne(string);
							if(role!=null) menuList.add(menu);
						}
					}
				}
				role.setMenuList(menuList);
				roleDao.save(role);
			}
		}
	}
}

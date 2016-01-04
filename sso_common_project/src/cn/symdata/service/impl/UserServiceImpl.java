package cn.symdata.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.symdata.common.SystemConfig;
import cn.symdata.common.exception.DatabaseException;
import cn.symdata.common.utils.Collections3;
import cn.symdata.dao.DataFieldDao;
import cn.symdata.dao.MenuDao;
import cn.symdata.dao.PowerDao;
import cn.symdata.dao.RoleDao;
import cn.symdata.dao.UserDao;
import cn.symdata.dao.impl.UserDaoImpl;
import cn.symdata.entity.DataField;
import cn.symdata.entity.Power;
import cn.symdata.entity.Role;
import cn.symdata.entity.User;
import cn.symdata.service.DataFieldService;
import cn.symdata.service.PowerService;
import cn.symdata.service.UserService;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private MenuDao menuDao;
	@Autowired
	private UserDaoImpl userDaoImpl;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private PowerDao powerDao;
	@Autowired
	private DataFieldDao dataFieldDao;
	@Autowired
	private SystemConfig systemConfig;
	@Autowired
	private PowerService powerService;
	@Autowired
	private DataFieldService dataFieldService;
	
	@Override
	public Page<User> findUserByHql(User user,Integer page) throws DatabaseException {
		Pageable pageable = new PageRequest(page != null ? page.intValue() - 1 : 0, 10);
		return userDaoImpl.findUserByHql(user, pageable);
	}
	
	@Override
	public User findOne(String id) throws DatabaseException {
		return userDao.findOne(id);
	}
	
	@Override
	public User findByUsername(String username) throws DatabaseException {
		return userDao.findByUsername(username);
	}
	
	@Override
	public User findUserByUsernameAndPassword(String username, String password) throws DatabaseException {
		return userDao.findUserByUsernameAndPassword(username, password);
	}

	@Override
	public List<String> findAuthorities(String id) throws DatabaseException {
		List<String> authorities = new ArrayList<String>();
		User user = userDao.findOne(id);
		if (user != null) {
			authorities = user.getRoles();
		}
		return authorities;
	}
	
	@Override
	public User getCurrent() throws DatabaseException {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			String userName = (String) subject.getPrincipal();
			if (userName != null) {
				return userDao.findByUsername(userName);
			}
		}
		return null;
	}
	
	@Override
	public String getCurrentUsername() throws DatabaseException {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			return (String)subject.getPrincipal();
		}
		return null;
	}
	
	@Override
	public boolean isAuthenticated() throws DatabaseException {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			return subject.isAuthenticated();
		}
		return false;
	}
	
	@Override
	@Transactional(readOnly = false)
	public User save(User user) throws DatabaseException{
		user.setPassword(DigestUtils.md5Hex(systemConfig.getDefaultPassword()));
		return userDao.save(user);
	}
	
	@Override
	@Transactional(readOnly = false)
	public User update(User user) throws DatabaseException{
		return userDao.save(user);
	}
	
	@Override
	@Transactional(readOnly = false)
	public Integer updateUserPassword(String username,String newPwd,String oldPwd) throws DatabaseException {
		return userDao.updateUserPassword(DigestUtils.md5Hex(newPwd), username);
	}
	
	@Override
	@Transactional(readOnly = false)
	public Integer updateUserIsEnable(String id) throws DatabaseException {
		return userDao.updateUserIsEnable(id);
	}
	
	@Override
	@Transactional(readOnly = false)
	public Integer updateUsernameAndRealNameAndEmailAndIsEnable(User user) throws DatabaseException {
		return userDao.updateUsernameAndRealNameAndEmailAndIsEnable(user.getUsername(), user.getRealName(), user.getEmail(), user.getStatus(), user.getId());
	}
	
	@Override
	@Transactional(readOnly = false)
	public User authorizeRoleOperate(User user,String roleIds) throws DatabaseException {
		List<Role> roleList = Lists.newArrayList();
		if(StringUtils.isNotBlank(roleIds)){
			List<String> idList = Splitter.on(",").trimResults().splitToList(roleIds.substring(1));
			roleList = roleDao.findByIds(idList);
		}
		user.setRoleList(roleList);
		return userDao.save(user);
	}
	
	@Override
	@Transactional(readOnly = false)
	public User authorizePowerOperate(User user,String powerIds,String menuIds) throws DatabaseException {
		List<Power> powerList = Lists.newArrayList();
		if(StringUtils.isNotBlank(powerIds)){
			List<String> idList = Splitter.on(",").trimResults().splitToList(powerIds.substring(1));
			powerList = powerDao.findPowersByIds(idList);
		}
		List<Power> menuPowerList =  powerService.findPowersByEnable(user,menuIds);
		if(Collections3.isNotEmpty(menuPowerList)){
			List<Power> userMenuPowerList = user.getPowerList();
			//去除用户拥有所属菜单的按钮
			List<Power> notMenuPowerList= Collections3.subtract(userMenuPowerList, menuPowerList);
			//给用户授权的菜单按钮
			List<Power> isMenuPowerList = Collections3.intersection(menuPowerList,powerList);
			user.setPowerList(Collections3.union(notMenuPowerList,isMenuPowerList));
		}else{
			user.setPowerList(powerList);
		}
		
		return userDao.save(user);
	}
	
	@Override
	@Transactional(readOnly = false)
	public User authorizeDataFieldOperate(User user,String dataFieldIds,String menuIds) throws DatabaseException {
		List<DataField> dataFieldList = Lists.newArrayList();
		if(StringUtils.isNotBlank(dataFieldIds)){
			List<String> idList = Splitter.on(",").trimResults().splitToList(dataFieldIds.substring(1));
			dataFieldList = dataFieldDao.findDataFieldByIds(idList);
		}
		
		List<DataField> menuDataFieldList =  dataFieldService.findDataFieldByEnable(user, menuIds);
		if(Collections3.isNotEmpty(menuDataFieldList)){
			List<DataField> userDataFieldList = user.getDataFieldList();
			//去除用户拥有所属菜单的字段
			List<DataField> notMenuDataFieldList= Collections3.subtract(userDataFieldList, menuDataFieldList);
			//给用户授权的菜单字段
			List<DataField> isMenuDataFieldList = Collections3.intersection(menuDataFieldList,dataFieldList);
			user.setDataFieldList(Collections3.union(notMenuDataFieldList,isMenuDataFieldList));
		}else{
			user.setDataFieldList(dataFieldList);
		}
		return userDao.save(user);
	}

	@Override
	public boolean findByUserNameAndId(String username, String id) throws DatabaseException {
		User user = null;
		username  = StringUtils.trimToEmpty(username);
		if(Strings.isNullOrEmpty(username)){
			return false;
		}
		if(StringUtils.isNotBlank(id)){
			user = userDao.findByUserNameAndId(username,id);
		}else{
			user = userDao.findByUsername(username);
		}
		if(user!=null){
			return false;
		}
		return true;
	}

	@Override
	@Transactional(readOnly = false)
	public Integer updateStatus(int status,String id) throws DatabaseException {
		return userDao.updateStatus(status,id);
	}
}

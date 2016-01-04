
package cn.symdata.web;

import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.symdata.common.DataEnum.ErrorCode;
import cn.symdata.common.Message;
import cn.symdata.common.exception.DatabaseException;
import cn.symdata.common.utils.JsonMapper;
import cn.symdata.entity.DataField;
import cn.symdata.entity.Menu;
import cn.symdata.entity.Power;
import cn.symdata.entity.Role;
import cn.symdata.entity.User;
import cn.symdata.service.DataFieldService;
import cn.symdata.service.MenuService;
import cn.symdata.service.PowerService;
import cn.symdata.service.RoleService;
import cn.symdata.service.UserService;

import com.google.common.collect.Maps;
/**
 *@Copyright:Copyright (c) 2012-2015
 *@Company:symdata
 *@Title:
 *@Description:用户管理
 *@Author:zhangnan#symdata
 *@Since:2015年9月8日  下午3:55:25
 *@Version:1.0
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController{
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PowerService powerService;
	@Autowired
	private DataFieldService dataFieldService;
	@Autowired
	private MenuService menuService;
	/**
	 * 
	 * @Title: list 
	 * @Description: 用户列表
	 * @Autohr guoxuelian#symdata.cn
	 * @param  username 
	 * @param  status
	 * @param  page
	 * @param  model
	 * @return String  
	 * @throws 
	 * 2015年9月9日下午1:55:46
	 */
	@RequiresPermissions("power:user:view")
	@RequestMapping(value = "/list")
	public String list(User user,Integer page, ModelMap model) throws DatabaseException {
		Page<User> pageList = userService.findUserByHql(user,page);
		model.addAttribute("page",pageList);
		model.addAttribute("user", user);
		return "user/list";
	}
	/**
	 *@param model
	 *@return
	 *@Description:新增用户
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月8日  下午3:45:17
	 *@Version:1.0
	 */
	@RequiresPermissions("power:user:add")
	@RequestMapping(value = "/preAdd")
	public String preAdd(Model model) throws DatabaseException {
		return "user/add";
	}
	/**
	 * 
	 * @Title: add 
	 * @Description: 保存用户信息
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param user
	 * @param @param model
	 * @param @return   
	 * @return Map<String,Object>  
	 * @throws 
	 * 2015年9月10日下午5:02:39
	 */
	@RequiresPermissions("power:user:add")
	@RequestMapping(value = "/add")
	public @ResponseBody Map<String, Object> add(User user) throws DatabaseException {
		Map<String, Object> data = Maps.newConcurrentMap();
		if(StringUtils.isBlank(user.getEmail())||StringUtils.isBlank(user.getRealName())||StringUtils.isBlank(user.getUsername())){
			data.put("message",Message.error(ErrorCode.ERR1001.getDescription()));
			return data;
		}
		userService.save(user);
		data.put("message",SUCCESS_MESSAGE);
		return data;
		
	}
	/**
	 *@param id
	 *@param model
	 *@return
	 *@Description:编辑用户信息
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月8日  下午3:47:20
	 *@Version:1.0
	 */
	@RequiresPermissions("power:user:edit")
	@RequestMapping(value = "/edit")
	public String perModify(User user, ModelMap model) throws DatabaseException {
		user = userService.findOne(user.getId());
		model.addAttribute("user", user);
		return "user/edit";
	}
	/**
	 *@param user
	 *@param model
	 *@return
	 *@Description:更新用户信息
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月8日  下午3:47:52
	 *@Version:1.0
	 */
	@RequiresPermissions("power:user:edit")
	@RequestMapping(value = "/update")
	public @ResponseBody Map<String, Object> update(User user) throws DatabaseException {
		Map<String, Object> data = Maps.newConcurrentMap();
		if(user==null||!StringUtils.isNotBlank(user.getRealName())||!StringUtils.isNotBlank(user.getId())){
			data.put("message",Message.error(ErrorCode.ERR1001.getDescription()));
			return data;
		}
		userService.updateUsernameAndRealNameAndEmailAndIsEnable(user);
		data.put("message",SUCCESS_MESSAGE);
		return data;
	}
	
	/**
	 * 
	 *@param oldPassword
	 *@param password
	 *@param checkPass
	 *@return
	 *@Description:更新用户密码信息
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月8日  下午3:49:31
	 *@Version:1.0
	 */
	@RequestMapping(value = "/updatePassword")
	public @ResponseBody Map<String, Object> modifyPsw(String newPassword,String oldPassword,ModelMap model) throws DatabaseException {
		Map<String, Object> data = Maps.newConcurrentMap();
		if(!StringUtils.isNotBlank(newPassword)||!StringUtils.isNotBlank(oldPassword)){
			data.put("message",Message.error(ErrorCode.ERR1001.getDescription()));
			return data;
		}
		User user = userService.getCurrent();
		if(user==null){
			data.put("message",Message.error(ErrorCode.ERR1002.getDescription()));
			return data;
		}
		if(!DigestUtils.md5Hex(oldPassword).equals(user.getPassword())){
			data.put("message",Message.error(ErrorCode.ERR1003.getDescription()));
			return data;
		}
		userService.updateUserPassword(user.getUsername(),newPassword,oldPassword);
		data.put("message",SUCCESS_MESSAGE);
		return data;
	}

	/**
	 *@param id
	 *@param redirectAttributes
	 *@return
	 *@Description:删除用户，改为不可用
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月8日  下午3:52:05
	 *@Version:1.0
	 */
	@RequiresPermissions("power:user:delete")
	@RequestMapping(value = "/enable")
	public @ResponseBody Map<String, Object> enable(User user) throws DatabaseException {
		Map<String, Object> data = Maps.newConcurrentMap();
		if(!StringUtils.isNotBlank(user.getId())){
			data.put("message",Message.error(ErrorCode.ERR1001.getDescription()));
			return data;
		}
		userService.updateUserIsEnable(user.getId());
		data.put("message",SUCCESS_MESSAGE);
		return data;
	}
	/**
	 *@param username
	 *@param id
	 *@return
	 *@Description:检查用户名是否重复
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月8日  下午3:52:58
	 *@Version:1.0
	 */
	@RequestMapping(value = "/checkUsername")
	public @ResponseBody boolean checkUsername(String id,String username) throws DatabaseException {
		return userService.findByUserNameAndId(username,id);
	}
	
	
	/**
	 * 
	 * @Title: powerOperate 
	 * @Description: 角色授权操作页面
	 * @Autohr guoxuelian#symdata.cn
	 * @param @return   
	 * @return String  
	 * @throws 
	 * 2015年9月10日下午4:13:46
	 */
	@RequiresPermissions("power:user:role:authorize")
	@RequestMapping(value = "/authorizeRoleList")
	public String authorizeRoleList(User user,ModelMap model) throws DatabaseException{
		List<Role> pageInfo = roleService.authorizeRoleList(user);
		model.addAttribute("page",pageInfo);
		model.addAttribute("user",user);
		return "user/role_operate";
	}
	
	/**
	 * 
	 * @Title: authorizeOperate 
	 * @Description: 角色授权操作
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param id 用户ID
	 * @param @param ids 角色id列表
	 * @param @param model
	 * @param @param dto
	 * @param @return   
	 * @return Map<String, Object>  
	 * @throws 
	 * 2015年9月12日上午10:17:47
	 */
	@RequiresPermissions("power:user:role:authorize")
	@RequestMapping(value = "/authorizeRoleOperate")
	public @ResponseBody Map<String, Object> authorizeRoleOperate(String id,String ids,ModelMap model) throws DatabaseException{
		Map<String, Object> data = Maps.newConcurrentMap();
		if(!StringUtils.isNotBlank(id)){//参数不正确
			data.put("message",Message.error(ErrorCode.ERR1001.getDescription()));
			return data;
		}
		User user = userService.findOne(id);
		if(user==null){//用户不存在
			data.put("message",Message.error(ErrorCode.ERR1002.getDescription()));
			return data;
		}
		userService.authorizeRoleOperate(user,ids);
		data.put("message",SUCCESS_MESSAGE);
		return data;
	}
	
	/**
	 * 
	 * @Title: authorizePowerList 
	 * @Description: 按钮授权操作页面
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param user 用户信息
	 * @param @param menuIds 当前选择菜单及子菜单的id集合
	 * @param @param menuName 菜单名称
	 * @param @param model
	 * @param @return
	 * @param @throws DatabaseException   
	 * @return String  
	 * @throws 
	 * 2015年9月16日下午7:03:59
	 */
	@RequiresPermissions("power:user:button:authorize")
	@RequestMapping(value = "/authorizePowerList")
	public String authorizePowerList(User user,String menuIds,String menuName,ModelMap model) throws DatabaseException{
		List<Menu> menuList  = menuService.findAll();
		List<Power> pageInfo = powerService.findPowersByEnable(user,menuIds);
		model.addAttribute("pageInfo",     pageInfo);
		model.addAttribute("user",     user);
		model.addAttribute("menuName", menuName);
		model.addAttribute("menuIds",  menuIds);
		model.addAttribute("menuList", JsonMapper.nonDefaultMapper().toJson(menuList));//菜单列表
		return "user/button_operate";
	}
	
	/**
	 * 
	 * @Title: authorizeOperate 
	 * @Description: 按钮授权操作
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param id 用户ID
	 * @param @param ids 角色id列表
	 * @param @param model
	 * @param @param dto
	 * @param @return   
	 * @return Map<String, Object>  
	 * @throws 
	 * 2015年9月12日上午10:17:47
	 */
	@RequiresPermissions("power:user:button:authorize")
	@RequestMapping(value = "/authorizePowerOperate")
	public @ResponseBody Map<String, Object> authorizePowerOperate(String userId,String powerIds,String menuIds,ModelMap model) throws DatabaseException{
		Map<String, Object> data = Maps.newConcurrentMap();
		if(!StringUtils.isNotBlank(userId)){//参数不正确
			data.put("message",Message.error(ErrorCode.ERR1001.getDescription()));
			return data;
		}
		User user = userService.findOne(userId);
		if(user==null){//用户不存在
			data.put("message",Message.error(ErrorCode.ERR1002.getDescription()));
			return data;
		}
		userService.authorizePowerOperate(user,powerIds,menuIds);
		data.put("message",SUCCESS_MESSAGE);
		return data;
	}
	
	/**
	 * 
	 * @Title: authorizeDataFieldList 
	 * @Description: 字段授权操作
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param user
	 * @param @param model
	 * @param @return   
	 * @return String  
	 * @throws 
	 * 2015年9月12日下午2:25:28
	 */
	@RequiresPermissions("power:user:datafield:authorize")
	@RequestMapping(value = "/authorizeDataFieldList")
	public String authorizeDataFieldList(User user,String menuIds,String menuName,ModelMap model) throws DatabaseException{
		List<Menu> menuList  = menuService.findAll();
		List<DataField> pageInfo = dataFieldService.findDataFieldByEnable(user,menuIds);
		model.addAttribute("pageInfo",     pageInfo);
		model.addAttribute("user",     user);
		model.addAttribute("menuName", menuName);
		model.addAttribute("menuIds",  menuIds);
		model.addAttribute("menuList", JsonMapper.nonDefaultMapper().toJson(menuList));//菜单列表
		return "user/datafield_operate";
	}
	
	/**
	 * 
	 * @Title: authorizeDataFieldOperate 
	 * @Description: 字段授权操作
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param id
	 * @param @param ids
	 * @param @param model
	 * @param @return   
	 * @return Map<String, Object>  
	 * @throws 
	 * 2015年9月12日下午2:26:15
	 */
	@RequiresPermissions("power:user:datafield:authorize")
	@RequestMapping(value = "/authorizeDataFieldOperate")
	public @ResponseBody Map<String, Object> authorizeDataFieldOperate(String userId,String dataFieldIds,String menuIds,ModelMap model) throws DatabaseException{
		Map<String, Object> data = Maps.newConcurrentMap();
		if(!StringUtils.isNotBlank(userId)){//参数不正确
			data.put("message",Message.error(ErrorCode.ERR1001.getDescription()));
			return data;
		}
		User user = userService.findOne(userId);
		if(user==null){//用户不存在
			data.put("message",Message.error(ErrorCode.ERR1002.getDescription()));
			return data;
		}
		userService.authorizeDataFieldOperate(user,dataFieldIds,menuIds);
		data.put("message",SUCCESS_MESSAGE);
		return data;
	}
	
	/**
	 * 
	 * @Title: updateStatus 
	 * @Description: 修改用户
	 * @Autohr guoxuelian#zymdata.cn
	 * @param @param status
	 * @param @param id
	 * @param @param model
	 * @param @return
	 * @param @throws DatabaseException
	 * @return Map<String, Object>  
	 * @throws 
	 * 2015年9月17日下午10:15:22
	 */
	@RequiresPermissions("power:user:edit")
	@RequestMapping(value = "/updateStatus")
	public @ResponseBody Map<String, Object> updateStatus(int status,String id,ModelMap model) throws DatabaseException{
		Map<String, Object> data = Maps.newConcurrentMap();
		if(!StringUtils.isNotBlank(id)){//参数不正确
			data.put("message",Message.error(ErrorCode.ERR1001.getDescription()));
			return data;
		}
		userService.updateStatus(status,id);
		data.put("message",SUCCESS_MESSAGE);
		return data;
	} 
}

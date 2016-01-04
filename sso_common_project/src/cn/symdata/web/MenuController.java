
package cn.symdata.web;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.symdata.common.DataEnum.ErrorCode;
import cn.symdata.common.Message;
import cn.symdata.common.exception.DatabaseException;
import cn.symdata.common.utils.JsonMapper;
import cn.symdata.entity.Menu;
import cn.symdata.entity.User;
import cn.symdata.service.MenuService;
import cn.symdata.service.RoleService;

import com.google.common.collect.Maps;
/**
 *@Copyright:Copyright (c) 2012-2015
 *@Company:symdata
 *@Title:
 *@Description:菜单管理
 *@Author:zhangnan#symdata
 *@Since:2015年9月8日  下午3:16:26
 *@Version:1.0
 */
@Controller
@RequestMapping(value = "/menu")
public class MenuController extends BaseController{
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private RoleService roleService;
	/**
	 *@param page
	 *@param model
	 *@return
	 *@Description:菜单列表
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月8日  下午3:23:22
	 *@Version:1.0
	 */
	@RequiresPermissions("power:menu:view")
	@RequestMapping(value = "/list")
	public String list(Integer page, ModelMap model) throws DatabaseException {
		List<Menu> pageList = menuService.findAll();
		model.addAttribute("page",JsonMapper.nonDefaultMapper().toJson(pageList));
		return "menu/list";
	}
	
	/**
	 *@param menu
	 *@param redirectAttributes
	 *@param model
	 *@return
	 *@Description:保存菜单信息
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月8日  下午3:25:53
	 *@Version:1.0
	 */
	@RequiresPermissions("power:menu:add")
	@RequestMapping(value = "/add")
	public @ResponseBody Map<String, Object> add(Menu menu) throws DatabaseException {
		Map<String, Object> data = Maps.newConcurrentMap();
		if(!StringUtils.isNotBlank(menu.getId())||!StringUtils.isNotBlank(menu.getCode())||!StringUtils.isNotBlank(menu.getName())||!StringUtils.isNotBlank(menu.getSystemMark())){
			data.put("message",Message.error(ErrorCode.ERR1001.getDescription()));
			return data;
		}
		menuService.save(menu);
		data.put("message",SUCCESS_MESSAGE);
		return data;
	}
	
	/**
	 *@param menu
	 *@param redirectAttributes
	 *@param model
	 *@return
	 *@Description:更新菜单信息
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月8日  下午3:26:21
	 *@Version:1.0
	 */
	@RequiresPermissions("power:menu:edit")
	@RequestMapping(value = "/update")
	public @ResponseBody Map<String, Object> modify(Menu menu) throws DatabaseException {
		Map<String, Object> data = Maps.newConcurrentMap();
		if(!StringUtils.isNotBlank(menu.getId())||!StringUtils.isNotBlank(menu.getCode())||!StringUtils.isNotBlank(menu.getName())||!StringUtils.isNotBlank(menu.getSystemMark())){
			data.put("message",Message.error(ErrorCode.ERR1001.getDescription()));
			return data;
		}
		menuService.update(menu);
		data.put("message",SUCCESS_MESSAGE);
		return data;
	}
	/**
	 *@param code
	 *@return
	 *@Description:验证编码是否重复
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月8日  下午3:26:37
	 *@Version:1.0
	 */
	@RequestMapping(value = "/checkCode")
	public @ResponseBody boolean checkCode(String id,String code) throws DatabaseException {
		return menuService.findByCodeAndId(code, id);
	}
	/**
	 *@param id
	 *@param redirectAttributes
	 *@return
	 *@Description:删除菜单
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月8日  下午3:20:30
	 *@Version:1.0
	 */
	@RequiresPermissions("power:menu:delete")
	@RequestMapping(value = "/enable")
	public @ResponseBody Map<String, Object> enable(User user) throws DatabaseException {
		Map<String, Object> data = Maps.newConcurrentMap();
		if(!StringUtils.isNotBlank(user.getId())){
			data.put("message",Message.error(ErrorCode.ERR1001.getDescription()));
			return data;
		}
		menuService.updateMenuIsEnable(user.getId());
		data.put("message",SUCCESS_MESSAGE);
		return data;
	}
	/**
	 *@param id 菜单id
	 *@param ids 角色id
	 *@param model
	 *@return
	 *@throws DatabaseException
	 *@Description:
	 *@Author:zhangnan#symdata
	 *@Since:2015年12月22日  下午2:22:52
	 *@Version:1.0
	 */
	@RequiresPermissions("power:menu:authorize")
	@RequestMapping(value = "/authorizeMenuRoleOperate")
	public @ResponseBody Map<String, Object> authorizeMenuRoleOperate(String id,String ids,ModelMap model) throws DatabaseException{
		Map<String, Object> data = Maps.newConcurrentMap();
		if(!StringUtils.isNotBlank(id)){
			data.put("message",Message.error(ErrorCode.ERR1001.getDescription()));
			return data;
		}
		menuService.authorizeMenuRoleOperate(id,ids);
		data.put("message",SUCCESS_MESSAGE);
		return data;
	}
}

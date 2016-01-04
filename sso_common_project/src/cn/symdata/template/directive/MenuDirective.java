package cn.symdata.template.directive;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import cn.symdata.common.SystemConfig;
import cn.symdata.common.exception.DatabaseException;
import cn.symdata.entity.Menu;
import cn.symdata.entity.User;
import cn.symdata.service.UserService;

import com.google.common.collect.Lists;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 *@Copyright:Copyright (c) 2012-2015
 *@Company:symdata
 *@Title:
 *@Description:菜单指令
 *@Author:zhangnan#symdata
 *@Since:2015年9月15日  下午7:37:02
 *@Version:1.0
 */
@Component("menuDirective")
public class MenuDirective extends BaseDirective {
	/** 变量名称 */
	private static final String VARIABLE_NAME = "menus";
	@Autowired
	private SystemConfig config;
	@Autowired
	private UserService userService;
	@Resource(name = "freeMarkerConfigurer")
	private FreeMarkerConfigurer freeMarkerConfigurer;

	@SuppressWarnings({"rawtypes" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		List<Menu> menus = Lists.newArrayList();
		String username =null;
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			username = (String) subject.getPrincipal();
		}
		try {
			if(username!=null){
				User user = userService.findByUsername(username);
				menus = user.getMenuList(config.getSystemId());
			}
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		setLocalVariable(VARIABLE_NAME, menus, env, body);
	}
}
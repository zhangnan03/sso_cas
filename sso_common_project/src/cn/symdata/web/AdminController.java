package cn.symdata.web;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.symdata.common.DataEnum.ErrorCode;
import cn.symdata.common.Message;
import cn.symdata.common.utils.JsonMapper;
import cn.symdata.common.utils.SpringUtils;
import cn.symdata.dto.TransDto;
import cn.symdata.service.AdminService;

import com.google.common.collect.Maps;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController{
	@Autowired
	private AdminService adminService;
	private final JsonMapper binder = JsonMapper.nonDefaultMapper();
	/**
	 *@param userVo
	 *@param page
	 *@param model
	 *@return
	 *@Description:用户列表查询
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月12日  下午12:11:41
	 *@Version:1.0
	 */
	@RequestMapping(value = "/updatePwd")
	public @ResponseBody Map<String, Object> updatePwd(String newPassword,String oldPassword,ModelMap model) {
		Map<String, Object> data = Maps.newConcurrentMap();
		if(StringUtils.isBlank(newPassword)||StringUtils.isBlank(oldPassword)){
			data.put("message", Message.warn(SpringUtils.getMessage("admin.password.null")));
			return data;
		}
		String username=null;
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			username = (String) subject.getPrincipal();
		}
		String result = adminService.updateAdminPassword(newPassword,oldPassword, username);
		TransDto dto = binder.fromJson(result, TransDto.class);
		if(dto.getCode().equals(ErrorCode.ERR1003.getCode())){
			data.put("message", Message.warn(SpringUtils.getMessage("admin.old.password.not.right")));
			return data;
		}
		if(dto.getCode().equals(ErrorCode.ERR1002.getCode())){
			data.put("message", Message.warn(SpringUtils.getMessage("admin.is.not.exists")));
			return data;
		}
		data.put("message", Message.success(SpringUtils.getMessage("admin.password.update.success")));
		return data;
	}
}
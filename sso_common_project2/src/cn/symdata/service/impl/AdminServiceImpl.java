package cn.symdata.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.symdata.common.DataEnum.ErrorCode;
import cn.symdata.common.SystemConfig;
import cn.symdata.common.utils.JsonMapper;
import cn.symdata.common.utils.RemoteContent;
import cn.symdata.dto.TransDto;
import cn.symdata.entity.Admin;
import cn.symdata.service.AdminService;
import cn.symdata.vo.PermissionVo;

import com.google.common.collect.Maps;
@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private SystemConfig config;
	private final JsonMapper binder = JsonMapper.nonDefaultMapper();
	@Override
	public Admin findByUsername(String username) {
		Map<String,String> paramsMap = Maps.newConcurrentMap();
		paramsMap.put("username", username);
		String result = RemoteContent.sendPostByParams(paramsMap, config.getCheckUserServerName()+"/remote/queryUser.jhtml");
		TransDto dto = binder.fromJson(result, TransDto.class);
		if (dto!=null&&dto.getCode().equals(ErrorCode.SUCCESS.getCode()) && dto.getData() != null) {
			return binder.fromJson(binder.toJson(dto.getData()), Admin.class);
		}
		return null;
	}

	@Transactional(readOnly = false)
	public Admin save(Admin admin){
		String result = RemoteContent.sendPost(admin.toString(), config.getCheckUserServerName()+"/remote/save.jhtml");
		TransDto dto = binder.fromJson(result, TransDto.class);
		if (dto.getCode().equals(ErrorCode.SUCCESS.getCode()) && dto.getData() != null) {
			return binder.fromJson(binder.toJson(dto.getData()), Admin.class);
		}
		return admin;
	}

	@Override
	public List<String> findAuthorities(String username) {
		List<String> authorities = new ArrayList<String>();
		Map<String,String> paramsMap = Maps.newConcurrentMap();
		paramsMap.put("username", username);
		paramsMap.put("systemId", config.getSystemId());
		String result = RemoteContent.sendPostByParams(paramsMap, config.getCheckUserServerName()+"/remote/permission.jhtml");
		TransDto dto = binder.fromJson(result, TransDto.class);
		if (dto.getCode().equals(ErrorCode.SUCCESS.getCode()) && dto.getData() != null) {
			PermissionVo permissionVo = binder.fromJson(binder.toJson(dto.getData()),PermissionVo.class);
			authorities.addAll(permissionVo.getDataFieldList());
			authorities.addAll(permissionVo.getPowerList());
			authorities.addAll(permissionVo.getRolesList());
		}
		return authorities;
	}

	public boolean isAuthenticated() {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			return subject.isAuthenticated();
		}
		return false;
	}

	public Admin getCurrent() {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			String username = (String) subject.getPrincipal();
			if (username != null) {
				return this.findByUsername(username);
			}
		}
		return null;
	}

	public String getCurrentUsername() {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			return (String) subject.getPrincipal();
		}
		return null;
	}

	@Override
	public Admin findAdminByUsernameAndPassword(String username, String password) {
		Map<String,String> paramsMap = Maps.newConcurrentMap();
		paramsMap.put("username", username);
		paramsMap.put("password", password);
		String result = RemoteContent.sendPostByParams(paramsMap, config.getCheckUserServerName()+"/remote/findUserByUsernameAndPassword.jhtml");
		TransDto dto = binder.fromJson(result, TransDto.class);
		if (dto.getCode().equals(ErrorCode.SUCCESS.getCode()) && dto.getData() != null) {
			Admin admin = binder.fromJson(binder.toJson(dto.getData()), Admin.class);
			return admin;
		}
		return null;
	}

	@Override
	public String updateAdminPassword(String newPwd,String oldPwd, String username) {
		Map<String,String> paramsMap = Maps.newConcurrentMap();
		paramsMap.put("username", username);
		paramsMap.put("newPwd", DigestUtils.md5Hex(newPwd));
		paramsMap.put("oldPwd", DigestUtils.md5Hex(oldPwd));
		return RemoteContent.sendPostByParams(paramsMap, config.getCheckUserServerName()+"/remote/modifyPassword.jhtml");
	}
}

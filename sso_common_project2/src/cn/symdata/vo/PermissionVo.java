package cn.symdata.vo;

import java.util.List;

public class PermissionVo {

	/**
	 * 用户名
	 */
	public String username;
	/**
	 * 真实姓名
	 */
	public String realName;
	/**
	 * 角色列表
	 */
	public List<String> rolesList;
	/**
	 * 菜单列表
	 */
	public List<MenuVo> menuList;
	/**
	 * 按钮列表
	 */
	public List<String> powerList;
	
	/**
	 * 字段列表
	 */
	public List<String> dataFieldList;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public List<String> getRolesList() {
		return rolesList;
	}
	public void setRolesList(List<String> rolesList) {
		this.rolesList = rolesList;
	}
	public List<MenuVo> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<MenuVo> menuList) {
		this.menuList = menuList;
	}
	public List<String> getPowerList() {
		return powerList;
	}
	public void setPowerList(List<String> powerList) {
		this.powerList = powerList;
	}
	public List<String> getDataFieldList() {
		return dataFieldList;
	}
	public void setDataFieldList(List<String> dataFieldList) {
		this.dataFieldList = dataFieldList;
	}
}

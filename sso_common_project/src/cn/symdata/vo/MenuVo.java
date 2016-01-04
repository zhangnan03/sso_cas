package cn.symdata.vo;

import java.util.List;

import com.google.common.collect.Lists;

public class MenuVo {

	/**
	 * 菜单名称
	 */
	public String name;
	/**
	 * 菜单code码
	 */
	public String code;
	/**
	 * 父id
	 */
	public String parentId;
	/**
	 * 系统标识
	 */
	public String systemMark;
	/**
	 * 路径
	 */
	public String url;
	
	/**
	 * 菜单标识
	 */
	private String menuFlag;
	
	private List<MenuVo> children = Lists.newArrayList();
	
	private MenuVo parent;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getSystemMark() {
		return systemMark;
	}
	public void setSystemMark(String systemMark) {
		this.systemMark = systemMark;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public MenuVo getParent() {
		return parent;
	}
	public void setParent(MenuVo parent) {
		this.parent = parent;
	}
	public String getMenuFlag() {
		return menuFlag;
	}
	public void setMenuFlag(String menuFlag) {
		this.menuFlag = menuFlag;
	}
	public List<MenuVo> getChildren() {
		return children;
	}
	public void setChildren(List<MenuVo> children) {
		this.children = children;
	}
	
}

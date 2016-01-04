package cn.symdata.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @ClassName: City 
 * @Description: 城市基类 
 * @author panpengxu@zymdata.cn
 * @date 2015年11月30日 下午4:36:40 
 *
 */
@Entity
@Table(name = "auth_area")
public class City extends BaseEntity{
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -4516617083418203496L;
	
	/**
	 * 城市名称
	 */
	private String name;
	/**
	 * 城市code
	 */
	private String code;
	/**
	 * 级别
	 */
	private Integer level;
	/**
	 * parentId
	 */
	private String parentId;
	/**
	 * 排序
	 */
	private Integer sort;
	
	
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
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
	
	
}

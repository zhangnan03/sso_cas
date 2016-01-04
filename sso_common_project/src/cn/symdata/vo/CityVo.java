package cn.symdata.vo;

/**
 * 
 * @ClassName: SystemDictDto 
 * @Description: TODO 
 * @author guoxuelian@zymdata.cn
 * @date 2015年9月24日 下午6:40:42 
 *
 */
public class CityVo {
	/**
	 * 城市code
	 */
	private String code;
	/**
	 * 城市名称
	 */
	private String name;
	/**
	 * 层级
	 */
	private Integer level;
	/**
	 * parentId
	 */
	private String parentId;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer integer) {
		this.level = integer;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	
	
}

package cn.symdata.vo;

/**
 * 
 * @ClassName: SystemDictDto 
 * @Description: TODO 
 * @author guoxuelian@zymdata.cn
 * @date 2015年9月24日 下午6:40:42 
 *
 */
public class SystemDictVo {
	/**
	 * 字典类别
	 */
	private String dictType;
	/**
	 * 字典类别名称
	 */
	private String dictTypeName;
	/**
	 * 字典名称
	 */
	private String dictName;
	/**
	 * 字典代码
	 */
	private String dictCode;
	
	private String status;
	
	public String getDictType() {
		return dictType;
	}
	public void setDictType(String dictType) {
		this.dictType = dictType;
	}
	public String getDictTypeName() {
		return dictTypeName;
	}
	public void setDictTypeName(String dictTypeName) {
		this.dictTypeName = dictTypeName;
	}
	public String getDictName() {
		return dictName;
	}
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
	public String getDictCode() {
		return dictCode;
	}
	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}

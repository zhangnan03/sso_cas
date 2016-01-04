package cn.symdata.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @ClassName: SystemDict 
 * @Description: 数据字典基类 
 * @author guoxuelian@zymdata.cn
 * @date 2015年9月24日 下午5:36:40 
 *
 */
@Entity
@Table(name = "auth_dict")
public class SystemDict extends BaseEntity {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -4516617083418203496L;
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
	/**
	 * 状态
	 */
	private String status="1";
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 排序号
	 */
	private Integer orderId=0;
	
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
}

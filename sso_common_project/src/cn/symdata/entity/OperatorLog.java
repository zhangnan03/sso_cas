package cn.symdata.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @ClassName: OperatorLog 
 * @Description: 操作日志
 * @author guoxuelian@zymdata.cn
 * @date 2015年9月8日 下午3:43:12 
 *
 */
@Entity
@Table(name = "auth_operator_log")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class OperatorLog extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5149744341271549420L;
	/**
	 * 所属功能模块
	 */
	private String  operatorModule;
	/**
	 * 操作方法
	 */
	private String operatorMethod;
	/**
	 * 传输数据
	 */
	private String operatorData;
	/**
	 * 操作类型（0：添加；1：编辑;2:删除）
	 */
	private Integer operatorType;
	
	@Column(nullable = false)
	public String getOperatorMethod() {
		return operatorMethod;
	}
	public void setOperatorMethod(String operatorMethod) {
		this.operatorMethod = operatorMethod;
	}
	@Column(nullable = false)
	public String getOperatorData() {
		return operatorData;
	}
	public void setOperatorData(String operatorData) {
		this.operatorData = operatorData;
	}
	@Column(nullable = false)
	public Integer getOperatorType() {
		return operatorType;
	}
	public void setOperatorType(Integer operatorType) {
		this.operatorType = operatorType;
	}
	@Column(nullable = false)
	public String getOperatorModule() {
		return operatorModule;
	}
	public void setOperatorModule(String operatorModule) {
		this.operatorModule = operatorModule;
	}
}

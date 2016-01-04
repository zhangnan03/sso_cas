package cn.symdata.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import cn.symdata.common.utils.UUIDGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *@Copyright:Copyright (c) 2012-2015
 *@Company:symdata
 *@Title:
 *@Description:实体基类
 *@Author:zhangnan#symdata
 *@Since:2015年9月10日  下午3:31:38
 *@Version:1.0
 */
@MappedSuperclass
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = -67188388306700736L;

	/**
	 * "ID"属性名称
	 */
	public static final String ID_PROPERTY_NAME = "id";

	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建人
	 */
	private String creator;

	@JsonProperty
	@Id
	public String getId() {
		return id;
	}

	/**
	 * 设置ID
	 * 
	 * @param id
	 *            ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	@Temporal(value=TemporalType.TIMESTAMP )
	@Column(nullable = false,updatable = false)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(nullable = false,updatable = false)
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * 重写equals方法
	 * 
	 * @param obj
	 *            对象
	 * @return 是否相等
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!BaseEntity.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		BaseEntity other = (BaseEntity) obj;
		return getId() != null ? getId().equals(other.getId()) : false;
	}

	/**
	 * 重写hashCode方法
	 * 
	 * @return hashCode
	 */
	@Override
	public int hashCode() {
		int hashCode = 17;
		hashCode += null == getId() ? 0 : getId().hashCode() * 31;
		return hashCode;
	}
	@PrePersist
	public void prePersist() {
		this.setId(UUIDGenerator.getUUID(16));
		this.setCreateTime(new Date());
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			String username = (String) subject.getPrincipal();
			if(username!=null){
				this.setCreator(username);
			}else{
				this.setCreator("-1");
			}
		}
	}


}
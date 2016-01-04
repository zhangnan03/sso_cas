package cn.symdata.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import cn.symdata.common.DataEnum.DeleteStatus;
import cn.symdata.common.utils.Collections3;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

/**
 * 
 * @ClassName: Control 
 * @Description: 控件实体类
 * @author guoxuelian@symdata.cn
 * @date 2015年9月8日 下午2:42:55 
 *
 */
@Entity
@Table(name = "auth_power")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Power extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 控件唯一标识
	 */
	private String code="";
	/**
	 * 控件名称
	 */
	private String name="";
	/**
	 * 描述
	 */
	private String description="";
	
	/**
	 * 所属菜单
	 */
	private Menu menu;
	/**
	 * 临时标记，判断是否选中
	 */
	private Integer isChecked;
	
	/**
	 * 是否删除0否1删除
	 */
	private Integer isEnable=0;
	
	/**
	 * 该按钮所对应的用户
	 */
	private List<User> userList = Lists.newArrayList();
	
	@Column(nullable = false, unique = true)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(nullable = false)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@ManyToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_id")
	@JsonIgnore
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
	@Transient
	public Integer getIsChecked() {
		return isChecked;
	}
	public void setIsChecked(Integer isChecked) {
		this.isChecked = isChecked;
	}
	
	@Column(nullable = false, unique = true)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = false)
	public Integer getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}
	/**
	 * 
	 * @Title: getUserList 
	 * @Description: 该按钮所对应的用户
	 * @Autohr guoxuelian#symdata.cn
	 * @param @return   
	 * @return List<User>  
	 * @throws 
	 * 2015年9月15日下午5:23:38
	 */
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "auth_user_power_rel", joinColumns = { @JoinColumn(name = "power_id") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("id")
	@JsonIgnore
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	/**
	 * 
	 * @Title: getUsers 
	 * @Description:获取用户
	 * @Autohr guoxuelian#symdata.cn
	 * @param @return   
	 * @return String  
	 * @throws 
	 * 2015年9月15日下午5:27:07
	 */
	@Transient
	public String getUsers() {
		Collection<User> users = Collections2.filter(
				getUserList(), new Predicate<User>() {
					@Override
					public boolean apply(User user) {
						if(user.getIsEnable()==DeleteStatus.DELETE.getStatusCode()){
							return false;
						}
						return true;
					}
				});
		return Collections3.extractToString(users, "id", ",");
	}
}

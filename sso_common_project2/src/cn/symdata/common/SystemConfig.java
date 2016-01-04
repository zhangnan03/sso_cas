
package cn.symdata.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
/**
 *@Copyright:Copyright (c) 2012-2015
 *@Company:symdata
 *@Title:
 *@Description:系统配置信息
 *@Author:zhangnan#symdata
 *@Since:2015年9月6日  下午9:12:21
 *@Version:1.0
 */
@Configuration
public class SystemConfig {
	/**
	 * 用户验证服务器
	 */
	@Value("#{system.CHECK_USER_SERVER_NAME}")
	private String checkUserServerName;
	/**
	 * 货币精度取舍方式
	 */
	@Value("#{system.PRICE_ROUND_TYPE}")
	private String priceRoundType;
	/**
	 * 系统标识
	 */
	@Value("#{system.SYSTEM_ID}")
	private String systemId;
	/**
	 * 货币精度
	 */
	@Value("#{system.PRICE_SCALE}")
	private Integer priceScale;
	/** 
	 * 文件上传路径
	*/
	@Value("#{system.FILE_UPLOAD_PATH}")
	private String fileUploadPath;
	/** 
	 * 上传文件最大限制 
	*/
	@Value("#{system.UPLOAD_MAX_SIZE}")
	private Integer uploadMaxSize;
	
	@Value("#{system.SHOW_UPLOAD_PATH}")
	private String showUploadPath;
	/**
	 * 系统默认密码
	 */
	@Value("#{system.DEFAULT_PASSWORD}")
	private String defaultPassword;
	public String getCheckUserServerName() {
		return checkUserServerName;
	}

	public void setCheckUserServerName(String checkUserServerName) {
		this.checkUserServerName = checkUserServerName;
	}

	public String getPriceRoundType() {
		return priceRoundType;
	}

	public void setPriceRoundType(String priceRoundType) {
		this.priceRoundType = priceRoundType;
	}

	public Integer getPriceScale() {
		return priceScale;
	}

	public void setPriceScale(Integer priceScale) {
		this.priceScale = priceScale;
	}

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getFileUploadPath() {
		return fileUploadPath;
	}

	public void setFileUploadPath(String fileUploadPath) {
		this.fileUploadPath = fileUploadPath;
	}

	public Integer getUploadMaxSize() {
		return uploadMaxSize;
	}

	public void setUploadMaxSize(Integer uploadMaxSize) {
		this.uploadMaxSize = uploadMaxSize;
	}

	public String getShowUploadPath() {
		return showUploadPath;
	}

	public void setShowUploadPath(String showUploadPath) {
		this.showUploadPath = showUploadPath;
	}

	public String getDefaultPassword() {
		return defaultPassword;
	}

	public void setDefaultPassword(String defaultPassword) {
		this.defaultPassword = defaultPassword;
	}
	
	
}

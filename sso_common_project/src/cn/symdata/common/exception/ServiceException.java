package cn.symdata.common.exception;


/**
 *@Copyright:Copyright (c) 2012-2015
 *@Company:symdata
 *@Title:
 *@Description:运行时异常进行封装
 *@Author:zhangnan#symdata
 *@Since:2015年9月9日  下午9:00:47
 *@Version:1.0
 */
public class ServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String status = "0000";
	
	public ServiceException() {
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String status, String message) {
		super(message);
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

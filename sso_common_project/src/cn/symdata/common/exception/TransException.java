package cn.symdata.common.exception;

import java.io.IOException;



/**
 *@Copyright:Copyright (c) 2012-2015
 *@Company:zplay.cn
 *@Title:
 *@Description:
 *@Author:wangdezhen#zplay.cn
 *@Since:2015年3月12日  下午4:44:50
 *@Version:1.0
 */
public class TransException extends IOException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public int status = 9999;
	
	public TransException() {
	}

	public TransException(int status) {
		this.status = status;
	}

	public TransException(String message) {
		super(message);
	}

	public TransException(int status, String message) {
		super(message);
		this.status = status;
	}
}

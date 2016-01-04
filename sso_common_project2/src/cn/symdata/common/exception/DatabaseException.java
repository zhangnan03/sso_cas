package cn.symdata.common.exception;

import java.sql.SQLException;


/**
 *@Copyright:Copyright (c) 2012-2015
 *@Company:zplay.cn
 *@Title:
 *@Description:
 *@Author:wangdezhen#zplay.cn
 *@Since:2015年3月12日  下午4:44:50
 *@Version:1.0
 */
public class DatabaseException extends SQLException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public int status = 2004;
	
	public DatabaseException() {
	}

	public DatabaseException(int status) {
		this.status = status;
	}

	public DatabaseException(String message) {
		super(message);
	}

	public DatabaseException(int status, String message) {
		super(message);
		this.status = status;
	}
}

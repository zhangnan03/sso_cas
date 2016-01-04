package cn.symdata.dto;

import java.io.Serializable;

import cn.symdata.common.DataEnum.ErrorCode;
/**
 *@Copyright:Copyright (c) 2012-2015
 *@Company:symdata
 *@Title:
 *@Description:
 *@Author:zhangnan#symdata
 *@Since:2015年9月6日  下午8:03:48
 *@Version:1.0
 */
public class TransDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2490301309324385398L;
	private String code;				//状态码
	private String message;				//消息
	private Object data;				//Json
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public void setError(String code,String message,Object data){
		this.code = code;
		this.message = message;
		this.data = data;
	}
	public void setDefaultSuccess(Object data) {
		setError(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getDescription(),data);
	}

}

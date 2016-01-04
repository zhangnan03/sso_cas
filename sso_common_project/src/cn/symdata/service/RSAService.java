package cn.symdata.service;

import java.security.interfaces.RSAPublicKey;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 *@Copyright:Copyright (c) 2012-2015
 *@Company:symdata
 *@Title:RSA安全
 *@Description:
 *@Author:zhangnan#symdata
 *@Since:2015年9月1日  下午3:22:56
 *@Version:1.0
 */
public interface RSAService {

	/**
	 * 生成密钥(添加私钥至Session并返回公钥)
	 * 
	 * @param request
	 *            httpServletRequest
	 * @return 公钥
	 */
	RSAPublicKey generateKey(HttpServletRequest request);

	/**
	 * 移除私钥
	 * 
	 * @param request
	 *            httpServletRequest
	 */
	void removePrivateKey(HttpServletRequest request);

	/**
	 * 解密参数
	 * 
	 * @param name
	 *            参数名称
	 * @param request
	 *            httpServletRequest
	 * @return 解密内容
	 */
	String decryptParameter(String name, HttpServletRequest request);

}
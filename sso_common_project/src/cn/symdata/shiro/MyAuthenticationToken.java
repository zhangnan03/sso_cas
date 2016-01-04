package cn.symdata.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 *@Copyright:Copyright (c) 2012-2015
 *@Company:symdata
 *@Title:登陆令牌
 *@Description:
 *@Author:zhangnan#symdata
 *@Since:2015年9月1日  下午3:21:30
 *@Version:1.0
 */
public class MyAuthenticationToken extends UsernamePasswordToken {

	private static final long serialVersionUID = 5898441540965086534L;

	/**
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param captchaId
	 *            验证码ID
	 * @param captcha
	 *            验证码
	 * @param rememberMe
	 *            记住我
	 * @param host
	 *            IP
	 */
	public MyAuthenticationToken(String username, String password) {
		super(username, password);
	}
}
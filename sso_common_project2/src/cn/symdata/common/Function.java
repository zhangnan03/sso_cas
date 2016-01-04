package cn.symdata.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
/**
 *@Copyright:Copyright (c) 2012-2015
 *@Company:symdata
 *@Title:
 *@Description:一些常用的工具类
 *@Author:zhangnan#symdata
 *@Since:2015年9月9日  下午9:07:30
 *@Version:1.0
 */
public class Function {
	/**
	 * 随机数
	 * 
	 * @param n
	 *            0-n区间,不包括n
	 * @return
	 */
	public static int randomInt(int n) {
		if (n == 0) {
			return 0;
		}
		return java.util.concurrent.ThreadLocalRandom.current().nextInt(n);
	}

	/**
	 * 获取一定范围内的随机整数
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getRandomIntByRange(int min, int max) {
		return (int) (Math.random() * (max - min)) + min;
	}

	/**
	 * 获取一个28位串
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static String getRandomString() {
		StringBuffer str = new StringBuffer("w");

		for (int i = 0; i < 26; i++) {
			char c = 'a';
			c = (char) (c + (int) (Math.random() * 26));
			str.append(c);
		}
		str.append("y");
		return str.toString();
	}

	public static String toHexString(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			String s4 = Integer.toHexString(ch);
			str = str + s4;
		}
		return str;// 0x表示十六进制
	}

	// 转换十六进制编码为字符串
	public static String toStringHex(String s) {

		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(
						s.substring(i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		try {
			s = new String(baKeyword, "utf-8");// UTF-16le:Not
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s;
	}

	/**
	 * 随机指定范围内N个不重复的数 利用HashSet的特征，只能存放不同的值
	 * 
	 * @param min
	 *            指定范围最小值
	 * @param max
	 *            指定范围最大值
	 * @param n
	 *            随机数个数
	 * @param HashSet
	 *            <Integer> set 随机数结果集
	 */
	public static void randomSet(int min, int max, int n, HashSet<Integer> set) {// 1
																					// 7000
																					// 1038
		if (n > (max - min + 1) || max < min) {
			return;
		}
		for (int i = 0; i < n; i++) {
			// 调用Math.random()方法
			int num = (int) (Math.random() * (max - min)) + min;
			set.add(num);// 将不同的数存入HashSet中
		}
		int setSize = set.size();
		// 如果存入的数小于指定生成的个数，则调用递归再生成剩余个数的随机数，如此循环，直到达到指定大小
		if (setSize < n) {
			randomSet(min, max, n - setSize, set);// 递归
		}
	}

	public static String randomUUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		// 去掉"-"符号
		String temp = str.substring(0, 8) + str.substring(9, 13)
				+ str.substring(14, 18) + str.substring(19, 23)
				+ str.substring(24);
		return temp;
	}
	/**
	 * 判断何时在sql语句中添加"where"或"and"
	 * 
	 * @author AkaruiWang
	 * @param sql 
	 * @param hasWhere
	 * @return
	 */
	public static boolean appendWhereIfNeed(StringBuffer sql, boolean hasWhere) {
		if (hasWhere == false)
			sql.append(" WHERE ");
		 else 
			sql.append(" AND ");
		return true;
	}
	
	/**
	 * 
	 *@param email
	 *@return
	 *@Description:
	 *@Author:wangdezhen#zplay.cn
	 *@Since:2015年1月20日  下午3:09:52
	 *@Version:1.0
	 */
	public static boolean checkEmail(String email) { 
	     boolean tag = true; 
	     final String pattern1 = "^([a-z0-9A-Z]+[-_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$"; 
	     final Pattern pattern = Pattern.compile(pattern1); 
	     final Matcher mat = pattern.matcher(email); 
	     if(!mat.find()) { 
	         tag = false; 
	     } 
	     return tag; 
	 }
	/**
	 *@param mobilePhone
	 *@return
	 *@Description:验证电话号
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月9日  下午9:08:01
	 *@Version:1.0
	 */
	public static boolean checkMobilePhone(String mobilePhone) { 
		boolean tag = true; 
		final String pattern1 = "^([0-9.]+)$"; 
		final Pattern pattern = Pattern.compile(pattern1); 
		final Matcher mat = pattern.matcher(mobilePhone); 
		if(!mat.find()) { 
			tag = false; 
		} 
		return tag; 
	}
	/**
	 *@param websiteUrl
	 *@return
	 *@Description:验证网址
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月9日  下午9:08:30
	 *@Version:1.0
	 */
	public static boolean checkWebsiteUrl(String websiteUrl) { 
		boolean tag = true; 
		final String pattern1 = "^((https|http|ftp|rtsp|mms)?://)"
			     + "+(([0-9a-zA-Z_!~*'().&=+$%-]+: )?[0-9a-zA-Z_!~*'().&=+$%-]+@)?"
			     + "(([0-9]{1,3}\\.){3}[0-9]{1,3}"
			     + "|"
			     + "([0-9a-zA-Z_!~*'()-]+\\.)*"
			     + "([0-9a-zA-Z][0-9a-zA-Z-]{0,61})?[0-9a-zA-Z]\\."
			     + "[a-z]{2,6})"
			     + "(:[0-9]{1,4})?"
			     + "((/?)|"
			     + "(/{1,}[0-9a-zA-Z_!~*'().;?:@&=+$,%#-]+)+/?)$"; 
		
		final Pattern pattern = Pattern.compile(pattern1); 
		final Matcher mat = pattern.matcher(websiteUrl); 
		if(!mat.find()) { 
			tag = false; 
		} 
		return tag; 
	}
	
	/**
	 *@param pwd
	 *@return
	 *@Description:验证密码
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月9日  下午9:08:44
	 *@Version:1.0
	 */
	 public static boolean checkPwd(String pwd){
		 boolean tag = true; 
		  final String pattern1 = "[a-zA-Z_0-9]{6,}"; 
	     final Pattern pattern = Pattern.compile(pattern1); 
	     final Matcher mat = pattern.matcher(pwd); 
	     if(!mat.find()) { 
	         tag = false; 
	     } 
	     return tag; 
	 }
	/**
	 *@param str
	 *@param response
	 *@Description:
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月9日  下午9:09:04
	 *@Version:1.0
	 */
	public static void outPrint(String str, HttpServletResponse response) {
		try {
			PrintWriter writer = response.getWriter();
			writer.write(str);
			writer.flush();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * 
	 *@param str
	 *@return
	 *@Description:把string中的'替换成''
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月9日  下午9:11:07
	 *@Version:1.0
	 */
	public static String replaceColon(String str){
		
		String repleceColon = StringUtils.replace(str, "'", "''");
		String replece100 =  StringUtils.replace(repleceColon,"%","//%");
		
		return replece100;
	}
	
	public static void main(String[] args) {
		System.out.println(checkPwd("12345678a"));
	}
	/**
	 *@param average 定义要产生的平均数
	 *@param relocate 平均数的上下浮度
	 *@return
	 *@Description:产生一个符合高斯正太分布的数字
	 *@Author:zhangnan#symdata
	 *@Since:2015年12月9日  下午6:50:10
	 *@Version:1.0
	 */
	public static BigDecimal getGaussianNumber(BigDecimal average,BigDecimal relocate){
		Random random = new Random();
		return average.add(relocate.multiply(new BigDecimal(random.nextGaussian())));
	}
}

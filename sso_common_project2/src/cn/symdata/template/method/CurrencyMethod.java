package cn.symdata.template.method;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.symdata.common.SystemConfig;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * 
 *@Copyright:Copyright (c) 2012-2015
 *@Company:symdata
 *@Title:模板方法 - 货币格式化
 *@Description:
 *@Author:zhangnan#symdata
 *@Since:2015年9月11日  下午5:22:01
 *@Version:1.0
 */
@Component("currencyMethod")
public class CurrencyMethod implements TemplateMethodModel {
	@Autowired
	private SystemConfig config;
	/**
	 * 四舍五入 :roundHalfUp
	 * 向上取整:roundUp
	 * 向下取整:roundDown
	 */
	private final static String PriceRoundType = "roundHalfUp";
	@SuppressWarnings("rawtypes")
	public Object exec(List arguments) throws TemplateModelException {
		if (arguments != null && !arguments.isEmpty() && arguments.get(0) != null && StringUtils.isNotEmpty(arguments.get(0).toString())) {
			BigDecimal amount = new BigDecimal(arguments.get(0).toString());
			String price = setScale(amount).toString();
			return new SimpleScalar(price);
		}
		return null;
	}
	/**
	 * 设置精度
	 * 
	 * @param amount
	 *            数值
	 * @return 数值
	 */
	public BigDecimal setScale(BigDecimal amount) {
		if (amount == null) {
			return null;
		}
		int roundingMode;
		if (config.getPriceRoundType().equals("roundUp")) {
			roundingMode = BigDecimal.ROUND_UP;
		} else if (config.getPriceRoundType().equals("roundDown")) {
			roundingMode = BigDecimal.ROUND_DOWN;
		} else {
			roundingMode = BigDecimal.ROUND_HALF_UP;
		}
		return amount.setScale(config.getPriceScale(), roundingMode);
	}
}
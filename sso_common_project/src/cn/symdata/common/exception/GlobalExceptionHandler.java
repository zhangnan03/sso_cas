package cn.symdata.common.exception;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
/**
 *@Copyright:Copyright (c) 2012-2015
 *@Company:symdata
 *@Title:
 *@Description:
 *@Author:zhangnan#symdata
 *@Since:2015年9月10日  下午2:06:27
 *@Version:1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler{
	private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	@ExceptionHandler(value = {ServiceException.class})
	public ModelAndView  handleException(ServiceException ex) {
		logger.error("错误代码{}:错误信息{}",ex.getStatus(),ex.getMessage());
		Map<String, Object> model = Maps.newHashMap();
		model.put("error", ex);
		return new ModelAndView("error", model);
	}
}

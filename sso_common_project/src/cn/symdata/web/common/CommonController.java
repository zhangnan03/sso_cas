
package cn.symdata.web.common;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.symdata.common.exception.DatabaseException;
import cn.symdata.entity.User;
import cn.symdata.service.RSAService;
import cn.symdata.service.UserService;

import com.octo.captcha.service.CaptchaService;

@Controller
@RequestMapping("/common/")
public class CommonController{
//	@Resource(name = "imageCaptchaService")
//	private CaptchaService captchaService;
	@Resource(name = "rsaServiceImpl")
	private RSAService rsaService;
	@Autowired
	private UserService userService;
	/**
	 * 
	 *@return
	 *@Description:主页
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月1日  下午3:28:03
	 *@Version:1.0
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(ModelMap model) {
		String username=null;
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			username = (String)subject.getPrincipal();
		}
		User user=null;
		try {
			user = userService.findByUsername(username);
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		model.addAttribute("admin", user);
		return "main";
	}
	/**
	 *@return
	 *@Description:欢迎首页
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月11日  下午9:15:15
	 *@Version:1.0
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "redirect:/common/main.jhtml";
	}
	/**
	 * 
	 *@param captchaId
	 *@param request
	 *@param response
	 *@throws Exception
	 *@Description: 生成验证码
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月1日  下午3:27:41
	 *@Version:1.0
	 */
//	@RequestMapping(value = "/captcha", method = RequestMethod.GET)
//	public void image(String captchaId, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		if (StringUtils.isEmpty(captchaId)) {
//			captchaId = request.getSession().getId();
//		}
//		String pragma = new StringBuffer().append("yB").append("-").append("der").append("ewoP").reverse().toString();
//		String value = new StringBuffer().append("ten").append(".").append("xxp").append("ohs").reverse().toString();
//		response.addHeader(pragma, value);
//		response.setHeader("Pragma", "no-cache");
//		response.setHeader("Cache-Control", "no-cache");
//		response.setHeader("Cache-Control", "no-store");
//		response.setDateHeader("Expires", 0);
//		response.setContentType("image/jpeg");
//
//		ServletOutputStream servletOutputStream = null;
//		try {
//			servletOutputStream = response.getOutputStream();
//			BufferedImage bufferedImage = (BufferedImage) captchaService.getChallengeForID(captchaId);
//			ImageIO.write(bufferedImage, "jpg", servletOutputStream);
//			servletOutputStream.flush();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			IOUtils.closeQuietly(servletOutputStream);
//		}
//	}
	/**
	 *@param request
	 *@param response
	 *@return
	 *@Description:权限验证
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月1日  下午3:28:23
	 *@Version:1.0
	 */
	@RequestMapping("/unauthorized")
	public String unauthorized(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
		String requestType = request.getHeader("X-Requested-With");
		if (requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest")) {
			response.addHeader("loginStatus", "unauthorized");
			try {
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		return "error/unauthorized";
	}
}
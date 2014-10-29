package com.igame.security.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

/**
 * 未登录切入点
 * 
 * @author Allen
 * 
 */
public class MyAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {
	private static final Logger log = LoggerFactory.getLogger(MyAuthenticationEntryPoint.class);

	public MyAuthenticationEntryPoint(String loginFormUrl) {
		super(loginFormUrl);
	}

	@Deprecated
	public MyAuthenticationEntryPoint() {

	}

	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
		// if (request.getHeader("X-AjaxRequest") != null &&
		// request.getHeader("X-AjaxRequest").equals("1")) {
		if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
			super.commence(request, response, authException);
			log.debug("Ajax parameter: " + request.getHeader("X-AjaxRequest"));
		} else {// ajax请求未登录 返回606 错误
			((HttpServletResponse) response).sendError(606, "");
			log.debug("Ajax parameter: " + request.getHeader("X-AjaxRequest"));
		}
	}
}

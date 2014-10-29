package com.igame.security.manager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
/**
 * 权限认证失败处理(403 处理)
 * @author Allen
 */
public class MyAccessDeniedHandlerImpl implements AccessDeniedHandler {
	private String accessDeniedUrl;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException reason) throws ServletException, IOException {
		boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));

		// 如果是ajax请求
		if (isAjax) {
			String jsonObject = "403 error... 权限不够，请联系系统管理员.";
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jsonObject);
			out.flush();
			out.close();
			return;
		} else {
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
			response.sendRedirect(basePath + accessDeniedUrl);
		}

	}

	public String getAccessDeniedUrl() {
		return accessDeniedUrl;
	}

	public void setAccessDeniedUrl(String accessDeniedUrl) {
		this.accessDeniedUrl = accessDeniedUrl;
	}

}

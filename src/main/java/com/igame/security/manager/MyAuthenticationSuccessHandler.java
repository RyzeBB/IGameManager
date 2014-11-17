package com.igame.security.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.igame.security.entity.SecModule;
import com.igame.security.entity.SecUser;
import com.igame.security.service.SecUserService;

/**
 * 登录成功后 业务处理（代替了loginController,可考虑采用controller）
 * 
 * @author Allen
 */
public class MyAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	private SecUserService secUserService;

	private static final Logger logger = LoggerFactory.getLogger(MyAuthenticationSuccessHandler.class);

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		MyUser myUser = (MyUser) authentication.getPrincipal();
		SecUser secUser = myUser.getSecUser();
		if (secUser != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", secUser);
			List<SecModule> secModules = secUserService.getModuleByUserId(myUser.getUserId());
			logger.debug("===================== 可访问资源的个数:{}================= ", secModules.size());
			session.setAttribute("modules", secModules);
		}
		logger.debug("======= MyAuthenticationSuccessHandler 登录成功处理 =========== username = " + myUser.getUsername() + " , userid = " + myUser.getUserId());
		super.onAuthenticationSuccess(request, response, authentication);
	}

	public void setSecUserService(SecUserService secUserService) {
		this.secUserService = secUserService;
	}
}

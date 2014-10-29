package com.igame.security.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.TextEscapeUtils;

/**
 * 带验证码校验功能的用户名、密码认证过滤器
 * 
 * 支持不输入验证码；支持验证码忽略大小写。
 * 
 * @author Allen
 * 
 */
/**
 * 验证用户信息 包括验证码
 * 
 * @author Allen
 */
// @Component("validateCodeUsernamePasswordAuthenticationFilter")
public class ValidateCodeUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private static final Logger logger = LoggerFactory.getLogger(ValidateCodeUsernamePasswordAuthenticationFilter.class);

	private boolean postOnly = true;
	private boolean allowEmptyValidateCode = true; // 是否允许验证码为空
	private String sessionvalidateCodeField = DEFAULT_SESSION_VALIDATE_CODE_FIELD;
	private String validateCodeParameter = DEFAULT_VALIDATE_CODE_PARAMETER;
	public static final String SPRING_SECURITY_LAST_USERNAME_KEY = "SPRING_SECURITY_LAST_USERNAME";
	// session中保存的验证码
	public static final String DEFAULT_SESSION_VALIDATE_CODE_FIELD = "rand";
	// 输入的验证码
	public static final String DEFAULT_VALIDATE_CODE_PARAMETER = "code";

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		try {
			logger.debug("===== 登陆验证:  validateCodeUsernamePasswordAuthenticationFilter");
			if (postOnly && !request.getMethod().equals("POST")) {
				logger.debug("=========== 登陆验证: " + request.getMethod() + " ============= ");
				throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
			}

			String username = obtainUsername(request);
			String password = obtainPassword(request);

			if (username == null) {
				username = "";
			}
			if (password == null) {
				password = "";
			}
			username = username.trim();

			UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

			// Place the last username attempted into HttpSession for views
			HttpSession session = request.getSession();
			if (session != null || getAllowSessionCreation()) {
				session.setAttribute(SPRING_SECURITY_LAST_USERNAME_KEY, TextEscapeUtils.escapeEntities(username));
			}
			// Allow subclasses to set the "details" property
			setDetails(request, authRequest);
			// check validate code
			if (!isAllowEmptyValidateCode())
				checkValidateCode(request);
			return this.getAuthenticationManager().authenticate(authRequest);
		} catch (AuthenticationException e) {
			logger.error(" ValidateCodeUsernamePasswordAuthenticationFilter error " + e);
			throw e;
		}
		// return null;
	}

	/**
	 * 
	 * <li>比较session中的验证码和用户输入的验证码是否相等</li>
	 * 
	 */
	protected void checkValidateCode(HttpServletRequest request) {
		String sessionValidateCode = obtainSessionValidateCode(request);
		String validateCodeParameter = obtainValidateCodeParameter(request);
		if (StringUtils.isEmpty(validateCodeParameter) || !sessionValidateCode.equalsIgnoreCase(validateCodeParameter)) {
			logger.error("验证码错误！");
			throw new AuthenticationServiceException("验证码错误！");
		}
	}

	private String obtainValidateCodeParameter(HttpServletRequest request) {
		Object obj = request.getParameter(validateCodeParameter);
		return null == obj ? "" : obj.toString();
	}

	/**
	 * 获取session中的验证码
	 * 
	 * @param request
	 * @return
	 */
	protected String obtainSessionValidateCode(HttpServletRequest request) {
		Object obj = request.getSession().getAttribute(sessionvalidateCodeField);
		return null == obj ? "" : obj.toString();
	}

	public boolean isPostOnly() {
		return postOnly;
	}

	@Override
	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}

	public String getValidateCodeName() {
		return sessionvalidateCodeField;
	}

	public void setValidateCodeName(String validateCodeName) {
		this.sessionvalidateCodeField = validateCodeName;
	}

	public boolean isAllowEmptyValidateCode() {
		return allowEmptyValidateCode;
	}

	public void setAllowEmptyValidateCode(boolean allowEmptyValidateCode) {
		this.allowEmptyValidateCode = allowEmptyValidateCode;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		// TODO Auto-generated method stub
		super.setAuthenticationManager(authenticationManager);
	}

	public void setAuthenticationFailureHandler(AuthenticationFailureHandler failureHandler) {
		super.setAuthenticationFailureHandler(failureHandler);
	}

	public void setAuthenticationSuccessHandler(AuthenticationSuccessHandler successHandler) {
		super.setAuthenticationSuccessHandler(successHandler);
	}
	// @Value("username")
	// public void setUsernameParameter(String usernameParameter) {
	// // TODO Auto-generated method stub
	// super.setUsernameParameter(usernameParameter);
	// }
	// @Value("password")
	// public void setPasswordParameter(String passwordParameter) {
	// super.setPasswordParameter(passwordParameter);
	// }
	// @Value("/login.action")
	// public void setFilterProcessesUrl(String filterProcessesUrl) {
	// // TODO Auto-generated method stub
	// super.setFilterProcessesUrl(filterProcessesUrl);
	// }

}
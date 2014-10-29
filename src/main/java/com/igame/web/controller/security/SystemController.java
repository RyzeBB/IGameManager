//package com.igame.web.controller.security;
//
//import javax.annotation.Resource;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import com.igame.security.manager.MyInvocationSecurityMetadataSource;
//
//@Controller
//@RequestMapping("/sys")
//public class SystemController {
//	private static final Logger logger = LoggerFactory.getLogger(SystemController.class);
//
//	private MyInvocationSecurityMetadataSource invocationSecurityMetadataSource;
//	@Resource
//	public void setInvocationSecurityMetadataSource(MyInvocationSecurityMetadataSource invocationSecurityMetadataSource) {
//		this.invocationSecurityMetadataSource = invocationSecurityMetadataSource;
//	}
//
//	@RequestMapping(method = RequestMethod.GET)
//	public String initAuth(Model model) throws Exception {
//		logger.debug(" ============ 初始化权限  =================");
//		invocationSecurityMetadataSource.loadResourceDefine();
//		return "security/sys/index";
//	}
//
//}

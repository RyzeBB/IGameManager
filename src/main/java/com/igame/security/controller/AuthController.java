package com.igame.security.controller;
//package com.igame.web.controller.security;
//
//import javax.annotation.Resource;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import com.igame.commons.util.SystemContext;
//import com.igame.commons.util.SystemException;
//import com.igame.security.entity.ACL;
//import com.igame.security.service.ACLService;
//import com.igame.security.service.SecModuleService;
//import com.igame.security.service.SecRoleService;
//import com.igame.security.service.SecUserService;
//import com.igame.web.vo.ACLVo;
//
//@Controller
//@RequestMapping("/acl") 
//public class AuthController {
//	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
//	private ACLService aclService;
//	private SecUserService secUserService;
//	private SecRoleService secRoleService;
//	private SecModuleService secModuleService;
//	@Resource
//	public void setAclService(ACLService aclService) {
//		this.aclService = aclService;
//	}
//	@Resource
//	public void setSecUserService(SecUserService secUserService) {
//		this.secUserService = secUserService;
//	}
//	@Resource
//	public void setSecRoleService(SecRoleService secRoleService) {
//		this.secRoleService = secRoleService;
//	}
//	@Resource
//	public void setSecModuleService(SecModuleService secModuleService) {
//		this.secModuleService = secModuleService;
//	}
//
//
//	@RequestMapping(params="acl", method=RequestMethod.GET)
//	public String acl(@ModelAttribute ACLVo aclVo,Model model) throws Exception {
//		if (ACL.TYPE_USER.equals(aclVo.getPrincipalType())) {
//			model.addAttribute("user",secUserService.findUserByUserId(aclVo.getPrincipalSn()));
//		} else if (ACL.TYPE_ROLE.equals(aclVo.getPrincipalType())) {
//			model.addAttribute("role", secRoleService.findRoleById(aclVo.getPrincipalSn()));
//		}else {
//			throw new SystemException("无效的主体类型！","exception.acltype.error");
//		}
//	
//		SystemContext.setOffset(0);
//		SystemContext.setPageSize(Integer.MAX_VALUE);
//		model.addAttribute("modules", secModuleService.listModulesByPid(0).getRows());
//		model.addAttribute("principalType",aclVo.getPrincipalType());
//		model.addAttribute("principalSn",aclVo.getPrincipalSn());
//		
//		return "security/acl/index";
//	}
//
//}

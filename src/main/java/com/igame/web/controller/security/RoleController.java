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
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.igame.commons.util.PageModel;
//import com.igame.security.entity.SecRole;
//import com.igame.security.service.SecRoleService;
//
//@Controller
//@RequestMapping("/role")
//public class RoleController {
//	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
//	private SecRoleService secRoleService;
//
//	@Resource
//	public void setSecRoleService(SecRoleService secRoleService) {
//		this.secRoleService = secRoleService;
//	}
//
//	@RequestMapping(method = RequestMethod.GET)
//	public String listRoles(Model model) throws Exception {
//		logger.debug(" ============ RoleController  listOrgs =================");
//		PageModel<SecRole> pm = secRoleService.listRoles();
//		model.addAttribute("pm", pm);
//		return "security/role/index";
//	}
//
//	@RequestMapping(params = "del", method = RequestMethod.GET)
//	@ResponseBody
//	public String removeRole(int id, Model model) throws Exception {
//		logger.debug(" ============ RoleController  removeRole =================");
//		secRoleService.removeRole(id);
//		return "删除角色成功...";
//	}
//
//	@RequestMapping(params = "add", method = RequestMethod.POST)
//	@ResponseBody
//	public String addRole(String name,String code, Model model) throws Exception {
//		logger.debug(" ============ RoleController  addOrg =================");
//		SecRole secRole = new SecRole();
//		secRole.setName(name);
//		secRole.setCode(code);
//		secRoleService.createRole(secRole);
//		return "添加角色成功...";
//	}
//
//}

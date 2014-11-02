package com.igame.security.controller;
//package com.igame.web.controller.security;
//
//import java.util.Date;
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
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.igame.commons.util.PageModel;
//import com.igame.security.entity.Person;
//import com.igame.security.entity.SecUser;
//import com.igame.security.service.SecPersonService;
//import com.igame.security.service.SecRoleService;
//import com.igame.security.service.SecUserService;
//import com.igame.web.vo.UserVo;
//
//@Controller
//@RequestMapping("/user")
//public class UserController {
//	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
//	private SecPersonService personService;
//	private SecUserService secUserService;
//	private SecRoleService secRoleService;
//
//	@Resource
//	public void setPersonService(SecPersonService personService) {
//		this.personService = personService;
//	}
//
//	@Resource
//	public void setSecUserService(SecUserService secUserService) {
//		this.secUserService = secUserService;
//	}
//	@Resource
//	public void setSecRoleService(SecRoleService secRoleService) {
//		this.secRoleService = secRoleService;
//	}
//
//	@RequestMapping(method = RequestMethod.GET)
//	public String listPersons(Model model) throws Exception {
//		logger.debug(" ============ UserController  listPersons =================");
//		PageModel<Person> pm = personService.listAll();
//		model.addAttribute("pm", pm);
//		return "security/user/index";
//	}
//
//	@RequestMapping(params = "add", method = RequestMethod.POST)
//	@ResponseBody
//	public String addUser(@ModelAttribute UserVo userVo, Model model) throws Exception {
//		logger.debug(" ============ UserController  addUser =================");
//		SecUser user = new SecUser();
//		user.setUsername(userVo.getUsername());
//		user.setPassword(userVo.getPassword());
//		user.setExpireTime(userVo.getExpireTime());
//		user.setCreateTime(new Date());
//		secUserService.createUser(user, userVo.getPersonId());
//
//		return "分配账号成功!";
//	}
//
//	@RequestMapping(params = "show", method = RequestMethod.GET)
//	public String showUser(int id, Model model) throws Exception {
//		logger.debug(" ============ UserController  showUser =================");
//		model.addAttribute("user", secUserService.findUserByUserId(id));
//		return "security/user/modify_input";
//	}
//
//	@RequestMapping(params = "preadd", method = RequestMethod.GET)
//	public String preAddUser(int id, Model model) throws Exception {
//		logger.debug(" ============ UserController  preAddUser =================");
//		model.addAttribute("personId", id);
//		return "security/user/add_input";
//	}
//
//	@RequestMapping(params = "del", method = RequestMethod.GET)
//	@ResponseBody
//	public String removeUser(int id, Model model) throws Exception {
//		logger.debug(" ============ UserController  removeUser =================");
//		secUserService.removeUser(id);
//		return "删除账号成功!";
//	}
//
//	//
//	@RequestMapping(params = "modify", method = RequestMethod.POST)
//	@ResponseBody
//	public String modifyUser(@ModelAttribute UserVo userVo, Model model) throws Exception {
//		logger.debug(" ============ UserController  modifyPerson =================");
//		SecUser user = new SecUser();
//		user.setId(userVo.getId());
//		user.setUsername(userVo.getUsername());
//		user.setPassword(userVo.getPassword());
//		user.setExpireTime(userVo.getExpireTime());
//		System.out.println(userVo.getExpireTime());
//		secUserService.modifyUser(user);
//
//		return "更新人员成功!";
//	}
//	
//	//给用户分配角色
//	@RequestMapping(params = "adduserrole", method = RequestMethod.POST)
//	@ResponseBody
//	public String adduserRole(@ModelAttribute UserVo userVo) throws Exception {
//		secUserService.createRoleAddOrUpdate(userVo.getId(),userVo.getRoleId(), userVo.getOrderNo());
//		return "添加角色成功!";
//	}
//		
//	@RequestMapping(params = "preaddrole", method = RequestMethod.GET)
//	public String userRoleInput(int id,Model model) throws Exception {
//		model.addAttribute("id",id);
//		model.addAttribute("pm",secRoleService.listRoles());
//		return "security/user/user_role_input";
//	}
//	
//	@RequestMapping(params = "listroles", method = RequestMethod.GET)
//	public String listUserRoles(int id,Model model) throws Exception {
//		model.addAttribute("user", secUserService.findUserByUserId(id));
//		model.addAttribute("urs",secUserService.listUserRoles(id));
//		return "security/user/user_role_list";
//	}
//	
//	@RequestMapping(params = "delrole", method = RequestMethod.GET)
//	@ResponseBody
//	public String delUserRole(int id,int roleId) throws Exception {
//		secUserService.removeUserRole(id,roleId);
//		return "删除角色成功!";
//	}
//
//}

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
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.igame.commons.util.PageModel;
//import com.igame.security.entity.SecOrg;
//import com.igame.security.service.SecOrgService;
//import com.igame.web.vo.OrgVo;
//
//@Controller
//@RequestMapping("/org")  
//public class OrgController {
//	private static final Logger logger = LoggerFactory.getLogger(OrgController.class);
//	private SecOrgService secOrgService;
//	
//	@Resource
//	public void setSecOrgService(SecOrgService secOrgService) {
//		this.secOrgService = secOrgService;
//	}
//	
//	@RequestMapping(method=RequestMethod.GET)
//	public String listOrgs(@RequestParam(value="pid", required=false) Integer pid,boolean select ,Model model) throws Exception {
//		logger.debug(" ============ PersonController  listOrgs =================");
//		if(pid == null){
//			pid = 0;
//		}
//		PageModel<SecOrg> pm = secOrgService.listOrgByPid(pid);
//		SecOrg secOrg = secOrgService.findOrgById(pid);
//		if(secOrg!=null && secOrg.getPid()!=0){
//			model.addAttribute("ppid", secOrg.getPid());
//		}else{
//			model.addAttribute("ppid", 0);
//		}
//		model.addAttribute("pid", pid);
//		model.addAttribute("pm", pm);
//		if(true == select){
//			return "security/org/select_org";
//		}else{
//			return "security/org/index";
//		}
//	}
//	
//	@RequestMapping(params="del", method=RequestMethod.GET)
//	@ResponseBody
//	public String removeOrgs(int id,Model model) throws Exception {
//		logger.debug(" ============ PersonController  removeOrgs =================");
//		secOrgService.removeOrgById(id);
//		return "删除组织机构成功...";
//	}
//	
//	@RequestMapping(params="preadd", method=RequestMethod.GET)
//	public String preAddOrg(int pid,Model model) throws Exception {
//		logger.debug(" ============ PersonController  addOrg =================");
//		model.addAttribute("parentId",pid);
//		return "security/org/add_input";
//	}
//	
//	@RequestMapping(params="add", method=RequestMethod.POST)
//	@ResponseBody
//	public String addOrg(@ModelAttribute OrgVo orgVo,Model model) throws Exception {
//		logger.debug(" ============ PersonController  addOrg =================");
//		logger.debug(" ======  "+orgVo.toString());
//		
//		SecOrg secOrg = new SecOrg();
//		secOrg.setName(orgVo.getName());
//		secOrg.setDescription(orgVo.getDescription());
//		secOrgService.createOrg(secOrg, orgVo.getParentId());
//		
//		return "添加组织成功...";
//	}
//}

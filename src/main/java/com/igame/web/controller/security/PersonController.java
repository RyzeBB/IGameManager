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
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.igame.commons.util.PageModel;
//import com.igame.security.entity.Person;
//import com.igame.security.service.SecPersonService;
//import com.igame.web.vo.PersonVo;
//
//@Controller
//@RequestMapping("/person")  
//public class PersonController {
//	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);
//	private SecPersonService personService;
//	@Resource
//	public void setPersonService(SecPersonService personService) {
//		this.personService = personService;
//	}
//
//	@RequestMapping(method=RequestMethod.GET)
//	public String listPersons(Model model) throws Exception {
//		logger.debug(" ============ PersonController  listPersons =================");
//		PageModel<Person> pm = personService.listAll();
//		model.addAttribute("pm", pm);
//		return "security/person/index";
//	}
//	@RequestMapping(params="add", method=RequestMethod.POST)
//	@ResponseBody
//	public String addPerson(@ModelAttribute PersonVo personVo,Model model) throws Exception {
//		logger.debug(" ============ PersonController  addPerson =================");
//			Person person = new Person();
//			person.setName(personVo.getName());
//			person.setSex(personVo.getSex());
//			person.setAge(personVo.getAge());
//			person.setPhone(personVo.getPhone());
//			person.setAddress(personVo.getAddress());
//			person.setDescription(personVo.getDescription());
//			person.setOrgid(personVo.getOrgId());
//			personService.createPerson(person);
//			return "添加人员成功!";
//	}
//	
//	@RequestMapping(params="show", method=RequestMethod.GET)
//	public String personShow(int id,Model model) throws Exception {
//		logger.debug(" ============ PersonController  personShow =================");
//		model.addAttribute("person", personService.findById(id));
//		return "security/person/update_input";
//	}
//	
////
////	public String delPerson() throws Exception {
////		personService.personDeleteById(personVo.getId());
////		return "delSuc";
////	}
//	@RequestMapping(params="del", method=RequestMethod.GET)
//	@ResponseBody
//	public String removePerson(int id,Model model) throws Exception {
//		logger.debug(" ============ PersonController  removeOrgs =================");
//		personService.removePersonById(id);
//		return "删除人员成功!";
//	}
////
//	@RequestMapping(params="modify", method=RequestMethod.POST)
//	@ResponseBody
//	public String modifyPerson(@ModelAttribute PersonVo personVo,Model model) throws Exception {
//		logger.debug(" ============ PersonController  modifyPerson =================");
//			Person person = new Person();
//			person.setId(personVo.getId());
//			person.setName(personVo.getName());
//			person.setSex(personVo.getSex());
//			person.setAge(personVo.getAge());
//			person.setPhone(personVo.getPhone());
//			person.setAddress(personVo.getAddress());
//			person.setDescription(personVo.getDescription());
//			person.setOrgid(personVo.getOrgId());
//			personService.modifyPerson(person);
//			return "更新人员成功!";
//	}
//
//}

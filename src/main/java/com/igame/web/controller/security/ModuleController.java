package com.igame.web.controller.security;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.igame.security.entity.SecModule;
import com.igame.security.service.SecModuleService;
import com.igame.web.vo.ModuleVo;

@Controller
@RequestMapping("/module")
public class ModuleController {
	private static final Logger logger = LoggerFactory.getLogger(ModuleController.class);
	private SecModuleService secModuleService;

	@Resource
	public void setSecModuleService(SecModuleService secModuleService) {
		this.secModuleService = secModuleService;
	}

	@RequestMapping
	@ResponseBody
	public Object listMoudles(@RequestParam(value = "pid", required = false) Integer pid) throws Exception {
		logger.debug(" ============ ModuleController  listMoudles =================");
		if (pid == null) {
			pid = 0;
		}
		List<SecModule> list = secModuleService.listModules();
		JSONObject object = new JSONObject();
		object.put("total", list.size());
		
		JSONArray allArr = new JSONArray();
		
		JSONObject all = new JSONObject();
		allArr.add(all);
		all.put("id", 0);
		all.put("name", "所有模块");
		JSONArray array = new JSONArray();
		all.put("children", array);
		if (list != null && !list.isEmpty()) {
			for (SecModule secModule : list) {
				if (secModule.getPid() == 0) {
					JSONObject object2 = new JSONObject();
					object2.put("id", secModule.getId());
					object2.put("name", secModule.getName());
					object2.put("sn", secModule.getSn());
					object2.put("url", secModule.getUrl());
					object2.put("orderNo", secModule.getOrderNo());
					object2.put("menustate", secModule.getState());
					JSONArray array2 = new JSONArray();
					for (SecModule secModule2 : list) {
						if(secModule2.getPid() ==secModule.getId() ){
							JSONObject object3 = new JSONObject();
							object3.put("id", secModule2.getId());
							object3.put("name", secModule2.getName());
							object3.put("sn", secModule2.getSn());
							object3.put("url", secModule2.getUrl());
							object3.put("orderNo", secModule2.getOrderNo());
							object3.put("menustate", secModule2.getState());
							array2.add(object3);
						}
					}
					object2.put("children", array2);
					array.add(object2);
				}
			}
		}
		object.put("rows", allArr);
		return object;
	}

	@RequestMapping(params = "del", method = RequestMethod.GET)
	@ResponseBody
	public String removeModule(int id, Model model) throws Exception {
		logger.debug(" ============ ModuleController  removeModule =================");
		secModuleService.removeModule(id);
		return "删除模块资源成功...";
	}

	@RequestMapping(params = "preadd", method = RequestMethod.GET)
	public String preAddModule(int pid, Model model) throws Exception {
		logger.debug(" ============ ModuleController  preAddModule =================");
		model.addAttribute("parentId", pid);
		return "security/module/add_input";
	}

	@RequestMapping(params = "add", method = RequestMethod.POST)
	@ResponseBody
	public String addMoudle(@ModelAttribute ModuleVo moduleVo, Model model) throws Exception {
		logger.debug(" ============ ModuleController  addMoudle =================");
		logger.debug(" ======  " + moduleVo.toString());
		SecModule module = new SecModule();
		module.setName(moduleVo.getName());
		module.setOrderNo(moduleVo.getOrderNo());
		module.setSn(moduleVo.getSn());
		module.setUrl(moduleVo.getUrl());
		secModuleService.createModule(module, moduleVo.getParentId());
		return "添加模块资源成功...";
	}

	@RequestMapping(params = "show", method = RequestMethod.GET)
	public String moduleShow(int id, Model model) throws Exception {
		logger.debug(" ============ ModuleController  moduleShow =================");
		model.addAttribute("module", secModuleService.findMoudleById(id));
		return "security/module/modify_input";
	}

	@RequestMapping(params = "modify", method = RequestMethod.POST)
	@ResponseBody
	public String modifyModule(@ModelAttribute ModuleVo moduleVo, Model model) throws Exception {
		logger.debug(" ============ ModuleController  modifyModule =================");
		SecModule secModule = new SecModule();
		secModule.setId(moduleVo.getId());
		secModule.setName(moduleVo.getName());
		secModule.setOrderNo(moduleVo.getOrderNo());
		secModule.setSn(moduleVo.getSn());
		secModule.setUrl(moduleVo.getUrl());
		secModuleService.modifyModule(secModule);
		return "更新资源模块成功!";
	}

}

package com.igame.security.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.igame.security.entity.SecModule;
import com.igame.security.mapper.SecModuleMapper;

/**
 * 资源模块
 * 
 * @author Allen
 *
 */
@Service("secModuleService")
public class SecModuleService {
	private SecModuleMapper secModuleMapper;

	@Resource
	public void setSecModuleMapper(SecModuleMapper secModuleMapper) {
		this.secModuleMapper = secModuleMapper;
	}

	/**
	 * 查询所有的模块信息
	 * 
	 * @param parentId
	 * @return
	 */
	public List<SecModule> listModules() {
		return secModuleMapper.selectlist();
	}
	
	/**
	 * 根据id查询模块
	 * 
	 * @param moduleId
	 * @return
	 */
	public SecModule findMoudleById(int moduleId) {
		return secModuleMapper.selectOneById(moduleId);
	}

	/**
	 * 新增模块
	 * 
	 * @param module
	 */
	public void createModule(SecModule module, int pid) {
		if (pid != 0) {
			module.setPid(pid);
		}
		secModuleMapper.insertOne(module);
	}

	/**
	 * 根据id 移除模块
	 * 
	 * @param moduleId
	 */
	public void removeModule(int moduleId) {
		secModuleMapper.deleteOne(moduleId);
	}

	/**
	 * 更新模块信息
	 * 
	 * @param module
	 */
	public void modifyModule(SecModule module) {
		secModuleMapper.updateOne(module);
	}

}

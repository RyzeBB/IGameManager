package com.igame.security.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.igame.security.entity.SecACL;
import com.igame.security.mapper.SecACLMapper;

@Repository
public class ACLService {
	private SecACLMapper secACLMapper;

	/**
	 * 根据角色 获取acl
	 * 
	 * @param roleIds
	 * @return
	 */
	public List<SecACL> getACLsByRoleIds(int... roleIds) {
		return secACLMapper.selectlistByRoleIds(roleIds);
	}

	/**
	 * 根据资源id获取相关授权信息
	 * 
	 * @param moduleId
	 * @return
	 */
	public List<SecACL> getACLsByModuleId(int moduleId) {
		return secACLMapper.selectlistByModuId(moduleId);
	}

}

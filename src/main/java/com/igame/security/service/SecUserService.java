package com.igame.security.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.igame.security.entity.SecModule;
import com.igame.security.entity.SecRole;
import com.igame.security.entity.SecUser;
import com.igame.security.mapper.SecModuleMapper;
import com.igame.security.mapper.SecUserMapper;
import com.igame.security.mapper.SecUserRolesMapper;

/**
 * 用户管理（账号）
 * 
 * @author Allen
 *
 */
@Service
public class SecUserService {
	private static Logger logger = LoggerFactory.getLogger(SecUserService.class);

	private SecUserMapper secUserMapper;
	private SecUserRolesMapper secUserRolesMapper;
	private SecModuleMapper secModuleMapper;

	/**
	 * 根据账号名查找账号
	 * 
	 * @param userName
	 * @return
	 */
	public SecUser getUserByName(String userName) {
		return secUserMapper.selectOneByUserName(userName);
	}

	/**
	 * 查询用户拥有的角色
	 * 
	 * @param userId
	 * @return
	 */
	public List<SecRole> getRolesByUserId(int userId) {
		return secUserRolesMapper.selectlistByUserId(userId);
	}

	public List<SecModule> getModuleByUserId(int userId) {
		List<SecRole> roles = secUserRolesMapper.selectlistByUserId(userId);
		if (roles != null && !roles.isEmpty()) {
			List<Integer> roleIds = new ArrayList<Integer>();
			for (SecRole role : roles) {
				roleIds.add(role.getId());
			}
			return secModuleMapper.selectlistModuleByRoleIds(roleIds);
		}
		return null;

	}

	@Resource
	public void setSecUserMapper(SecUserMapper secUserMapper) {
		this.secUserMapper = secUserMapper;
	}

	@Resource
	public void setSecUserRolesMapper(SecUserRolesMapper secUserRolesMapper) {
		this.secUserRolesMapper = secUserRolesMapper;
	}

	@Resource
	public void setSecModuleMapper(SecModuleMapper secModuleMapper) {
		this.secModuleMapper = secModuleMapper;
	}

}

package com.igame.security.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.igame.security.entity.SecRole;
import com.igame.security.mapper.SecRoleMapper;

/**
 * 角色管理
 * 
 * @author Allen
 */
@Service
public class SecRoleService {
	private SecRoleMapper secRoleMapper;

	public List<SecRole> getRoles() {
		return secRoleMapper.selectlist();
	}

	@Resource
	public void setSecRoleMapper(SecRoleMapper secRoleMapper) {
		this.secRoleMapper = secRoleMapper;
	}

}

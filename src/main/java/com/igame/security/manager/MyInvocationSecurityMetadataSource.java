package com.igame.security.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import com.igame.security.entity.SecACL;
import com.igame.security.entity.SecModule;
import com.igame.security.entity.SecRole;
import com.igame.security.service.ACLService;
import com.igame.security.service.SecModuleService;
import com.igame.security.service.SecRoleService;

/**
 * 系统初始化时，加载所有资源及其对应角色的定义
 * 
 * @author Allen
 */
@Component("myInvocationSecurityMetadataSource")
public class MyInvocationSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {
	private static final Logger logger = LoggerFactory
			.getLogger(MyInvocationSecurityMetadataSource.class);
	private SecRoleService secRoleService;
	private ACLService aclService;
	private SecModuleService secModuleService;

	/* 保存资源和权限的对应关系 key-资源url value-权限(role.code) */
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

//	@PostConstruct
	public void loadResourceDefine() {
		logger.debug("=============== loadResourceDefine 开始加载资源列表数据 ===============");
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		List<SecRole> secRoles = secRoleService.getRoles();
		if (secRoles == null)
			return;
		List<SecModule> modules = secModuleService.listModules();
		if (modules == null)
			return;
		for (SecModule secModule : modules) {
			Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
			// 根据资源id获取针对该资源的所有授权
			List<SecACL> acls = aclService.getACLsByModuleId(secModule.getId());
			if (acls == null)
				continue;
			for (SecACL secACL : acls) {
				SecRole acl_secRole = null;
				for (SecRole secRole : secRoles) {
					if (secRole.getId() == secACL.getPrincipalSn()) {
						acl_secRole = secRole;
						break;
					}
				}
				ConfigAttribute configAttribute = new SecurityAclsConfig(
						acl_secRole.getCode(), secACL);
				configAttributes.add(configAttribute);
			}

			String url = secModule.getUrl();
			if (url != null && url.length() > 0) {
				url = "/" + url;
				int position = url.indexOf("?");
				if (position != -1) {// 只要action和method 不需要初始化参数
					url = url.substring(0, position);
				}
				resourceMap.put(url, configAttributes);
			}
		}
		logger.debug(" ======= resourceMap size = " + resourceMap.size());
	}

	/*
	 * 根据请求的资源地址，获取它所拥有的权限
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		logger.debug("=============== loadResourceDefine getAttributes===============");
		// 获取请求的url地址
		String url = ((FilterInvocation) object).getRequestUrl();
		if (resourceMap == null) {
			return null;
		}
		Iterator<String> ite = resourceMap.keySet().iterator();
		while (ite.hasNext()) {
			String resURL = ite.next();
			if (url.startsWith(resURL)) {
				return resourceMap.get(resURL);
			}
		}
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	public boolean supports(Class<?> clazz) {
		logger.debug("===============  supports ===============");
		return true;
	}

	@Resource
	public void setSecRoleService(SecRoleService secRoleService) {
		this.secRoleService = secRoleService;
	}

	@Resource
	public void setAclService(ACLService aclService) {
		this.aclService = aclService;
	}

	@Resource
	public void setSecModuleService(SecModuleService secModuleService) {
		this.secModuleService = secModuleService;
	}

}

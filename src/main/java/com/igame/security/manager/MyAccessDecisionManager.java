package com.igame.security.manager;

import java.util.Collection;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.igame.commons.util.PermissionEmun;
import com.igame.security.entity.SecACL;

/**
 * spring security 权限验证
 * 
 * @author Allen
 */
@Component("myAccessDecisionManager")
public class MyAccessDecisionManager implements AccessDecisionManager {
	private static final Logger logger = LoggerFactory.getLogger(MyAccessDecisionManager.class);

	/*
	 * 如果不存在对该资源的定义，直接放行； 否则，如果找到正确的角色，即认为拥有权限，并放行； 否则throw new
	 * AccessDeniedException("no right");这样，就会进入403.jsp页面。
	 */
	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {

		if (configAttributes == null) {
			return;
		}
		logger.debug("================ 正在访问的url是：" + object.toString() + "==================");

		// 超级用户都有权限
		if (authentication.getPrincipal() instanceof MyUser) {
			MyUser myUser = (MyUser) authentication.getPrincipal();
			if (myUser.isSuperAdmin())//超级管理员 不用验证任何权限
				return;
		}
		Iterator<ConfigAttribute> ite = configAttributes.iterator();
		while (ite.hasNext()) {
			ConfigAttribute ca = ite.next();
			logger.debug("needRole is：" + ca.getAttribute());
			String needRole = ((SecurityAclsConfig) ca).getAttribute();//角色代号 code
			for (GrantedAuthority ga : authentication.getAuthorities()) {//用户拥有的角色
				logger.debug("/t授权信息是：" + ga.getAuthority());
				if (needRole.equals(ga.getAuthority())) { // ga is user's role.code
					//用户拥有角色，验证角色是否拥有足够的权限
					
					int permission = getPerssionByUrl(object.toString());
					//验证用户的权限
					SecACL acl = ((SecurityAclsConfig) ca).getAcl();
					if(acl!=null && acl.getPermission(permission) == SecACL.ACL_YES){//TODO 验证权限
						logger.debug("判断到，needRole 是" + needRole + ",用户的角色是:" + ga.getAuthority() + "，授权数据相匹配");
						return;
					}
				}
			}
		}
		logger.debug("没有权限 no right");
		throw new AccessDeniedException("没有权限 no right");
	}
	/**
	 * 根据url获取用户需要验证的权限位
	 * @param url
	 * @return
	 */
	public int getPerssionByUrl(String url){
		if(url.contains("add")){
			return PermissionEmun.CREATE.getPermission();
		}else if(url.contains("del")){
			return PermissionEmun.DELETE.getPermission();
		}else if(url.contains("modify")){
			return PermissionEmun.UPDATE.getPermission();
		}else if(url.contains("update")){
			return PermissionEmun.UPDATE.getPermission();
		}else{
			return PermissionEmun.READ.getPermission();
		}
	}
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

}

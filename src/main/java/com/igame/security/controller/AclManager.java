package com.igame.security.controller;
//package com.igame.web.controller.security;
//
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.directwebremoting.annotations.RemoteMethod;
//import org.directwebremoting.annotations.RemoteProxy;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.igame.security.service.ACLService;
//
//@RemoteProxy(name="aclManager")
//public class AclManager {
//	private static final Logger logger = LoggerFactory.getLogger(AclManager.class);
//
//	private ACLService aclService;
//
//	@Resource(name = "aclService")
//	public void setAclService(ACLService aclService) {
//		this.aclService = aclService;
//	}
//
//	/**
//	 * c/r/u/d权限的增加或修改
//	 * 
//	 * @param principalType
//	 * @param principalSn
//	 * @param resourceSn
//	 * @param permission
//	 * @param yes
//	 */
//	@RemoteMethod
//	public void addOrUpdatePermission(String principalType, int principalSn, int resourceSn, int permission, boolean yes) {
//		System.err.println(" ======= String principalType = "+principalType+"   int principalSn "+principalSn+"   int resourceSn "+resourceSn+" int permission "+permission+" boolean yes "+yes);
//		aclService.createOrUpdatePermission(principalType, principalSn, resourceSn, permission, yes);
//		
//	}
//
//	/**
//	 * 继承关系
//	 * 
//	 * @param userId
//	 * @param resourceSn
//	 * @param yes
//	 */
//	@RemoteMethod  
//	public void addOrUpdateExtends(int userId, int resourceSn, boolean yes) {
//		aclService.createOrUpdateExtends(userId, resourceSn, yes);
//	}
//
//	/**
//	 * 删除acl记录
//	 * 
//	 * @param principalType
//	 * @param principalSn
//	 * @param resourceSn
//	 */
//	@RemoteMethod  
//	public void delPermission(String principalType, int principalSn, int resourceSn) {
//		aclService.removePermission(principalType, principalSn, resourceSn);
//	}
//
//	/**
//	 * 根据主体的类型和主题的标识查询主题的acl授权记录
//	 * 
//	 * @param principalType
//	 * @param principalSn
//	 * @return
//	 */
//	@RemoteMethod  
//	public List searchAclRecord(String principalType, int principalSn) {
//		return aclService.findAclRecord(principalType, principalSn);
//	}
//
//}

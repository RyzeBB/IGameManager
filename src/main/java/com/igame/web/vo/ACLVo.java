package com.igame.web.vo;

public class ACLVo {
	private int id;
	//权限分配给予者的类型
	private String principalType;
	//主体的标识
	private int principalSn;
	
	//资源的标识
	private int resourceSn;	
	
	/**
	 * 权限的状态
	 *    是否拥有C / R / U / D操作
	 */
	private int aclState;
	//权限的继承性
	private int aclTriState;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPrincipalType() {
		return principalType;
	}
	public void setPrincipalType(String principalType) {
		this.principalType = principalType;
	}
	public int getPrincipalSn() {
		return principalSn;
	}
	public void setPrincipalSn(int principalSn) {
		this.principalSn = principalSn;
	}
	public int getResourceSn() {
		return resourceSn;
	}
	public void setResourceSn(int resourceSn) {
		this.resourceSn = resourceSn;
	}
	public int getAclState() {
		return aclState;
	}
	public void setAclState(int aclState) {
		this.aclState = aclState;
	}
	public int getAclTriState() {
		return aclTriState;
	}
	public void setAclTriState(int aclTriState) {
		this.aclTriState = aclTriState;
	}

}

package com.igame.security.entity;

import java.io.Serializable;

/**
 * 权限分配（授权、认证）
 */
public class SecACL implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 授权是否允许 "1"表示允许 "0"表示不允许 "-1"表示不确定
	 */
	public static final int ACL_YES = 1;
	public static final int ACL_NO = 0;

	// id
	private int id;

	// 主体的标识
	private int principalSn;

	// 资源的标识
	private int resourceSn;

	/**
	 * 权限的状态 是否拥有C / R / U / D操作
	 */
	private int aclState;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	/**
	 * ACL是咧跟主体和资源的关系 针对此时列进行授权:某种操作是否允许
	 */
	public void setPermission(int permission, boolean yes) {
		int temp = 1;
		temp = temp << permission;
		if (yes) {
			aclState |= temp;
		} else {
			aclState &= ~temp;
		}
	}

	/**
	 * 获取ACL授权
	 * 
	 * @param permission
	 *            C/R/U/D
	 * @return 允许/不允许/不确定
	 */
	public int getPermission(int permission) {
		int temp = 1;
		temp = temp << permission;
		// 只要C/R/U/D中不全部为没有权限(0000),那么与刚刚传入的权限相"&"是不会出现等于0的.
		temp &= aclState;
		if (temp != 0) {
			return ACL_YES;
		}
		return ACL_NO;
	}

}

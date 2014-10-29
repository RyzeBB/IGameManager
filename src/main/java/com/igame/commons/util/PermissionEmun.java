package com.igame.commons.util;

public enum PermissionEmun {
	//Create, read, update and delete
	CREATE("新增", 0), READ("读取", 1), UPDATE("更新", 2), DELETE("删除", 3);  
	private String name;
	private int permission;
	
	private PermissionEmun(String name, int permission) {
		this.name = name;
		this.permission = permission;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPermission() {
		return permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}
	
	 
}

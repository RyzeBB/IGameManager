package com.igame.web.vo;

public class OrgVo {
	private int id;
	private String name;
	private String sn;
	private String description;
	private int parentId;
	public int ppid;
	private boolean select;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public boolean isSelect() {
		return select;
	}

	public void setSelect(boolean select) {
		this.select = select;
	}

	public int getPpid() {
		return ppid;
	}

	public void setPpid(int ppid) {
		this.ppid = ppid;
	}
	@Override
	public String toString() {
		return "id = "+id+              
				"  name = "+ name +         
				"  sn = "+ sn+           
				"  description = "+ description+  
				"  parentId = "+ parentId+       
				"  parentId = "+ ppid+             
				"  select = " + select;
	}
}

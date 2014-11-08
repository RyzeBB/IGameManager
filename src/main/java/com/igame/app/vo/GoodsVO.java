package com.igame.app.vo;

import java.util.List;

public class GoodsVO {
	private String name;

	private String type;

	private List<String> attrName;

	private List<String> attrValue;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getAttrName() {
		return attrName;
	}

	public void setAttrName(List<String> attrName) {
		this.attrName = attrName;
	}

	public List<String> getAttrValue() {
		return attrValue;
	}

	public void setAttrValue(List<String> attrValue) {
		this.attrValue = attrValue;
	}

}

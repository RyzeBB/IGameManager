package com.igame.app.entity;

import java.util.List;
import java.util.Map;

public class MulVal {
	private String mulValName;
	private List<Map<String, String>> attr;

	public String getMulValName() {
		return mulValName;
	}

	public void setMulValName(String mulValName) {
		this.mulValName = mulValName;
	}

	public List<Map<String, String>> getAttr() {
		return attr;
	}

	public void setAttr(List<Map<String, String>> attr) {
		this.attr = attr;
	}

}

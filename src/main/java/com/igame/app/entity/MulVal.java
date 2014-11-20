package com.igame.app.entity;

import java.util.List;
import java.util.Map;

/**
 * 多属性栏
 * @author Allen
 *
 */
public class MulVal {
	private Map<String, String> values;//多属性 key为属性名称 value为属性值
	private List<String> img;//改属性下图片列表

	public Map<String, String> getValues() {
		return values;
	}

	public void setValues(Map<String, String> values) {
		this.values = values;
	}

	public List<String> getImg() {
		return img;
	}

	public void setImg(List<String> img) {
		this.img = img;
	}

}

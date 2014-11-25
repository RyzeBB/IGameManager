package com.igame.app.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author Allen 店铺分类
 */
public class GoodsTypeEntity {
	private int id;
	@JSONField(serialize = false)
	private Long appid;
	private String name;
	private String pic_url;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Long getAppid() {
		return appid;
	}

	public void setAppid(Long appid) {
		this.appid = appid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPic_url() {
		return pic_url;
	}

	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}

}

package com.igame.app.vo;

import java.util.List;

/**
 * 根据id列表获取商品列表
 * 
 * @author Allen
 *
 */
public class GoodsIdsRequest extends RequestVO {
	private List<Long> ids;

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

}

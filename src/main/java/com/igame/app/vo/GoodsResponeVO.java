package com.igame.app.vo;

import java.util.List;

import com.igame.app.entity.GoodsEntity;

/**
 * @author Allen 商品列表下行
 */
public class GoodsResponeVO extends ResponseVO {
	private List<GoodsEntity> productList;

	public List<GoodsEntity> getProductList() {
		return productList;
	}

	public void setProductList(List<GoodsEntity> productList) {
		this.productList = productList;
	}

}

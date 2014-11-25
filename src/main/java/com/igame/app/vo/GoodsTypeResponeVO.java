package com.igame.app.vo;

import java.util.List;

import com.igame.app.entity.GoodsTypeEntity;

/**
 * 商品分类下行
 * 
 * @author Allen
 */
public class GoodsTypeResponeVO extends ResponseVO {
	private List<GoodsTypeEntity> typeList;

	public List<GoodsTypeEntity> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<GoodsTypeEntity> typeList) {
		this.typeList = typeList;
	}

}

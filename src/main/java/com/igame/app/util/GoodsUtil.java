/*package com.igame.app.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.igame.app.entity.GoodsEntity;
import com.igame.app.entity.MulVal;
import com.igame.app.vo.GoodsVO;
import com.igame.app.vo.MutiAttr;

public class GoodsUtil {
	public GoodsVO toGoodsVO(GoodsEntity entity){
		GoodsEntity entity = new GoodsEntity();
		entity.setAppid(appid);
		// entity.setId;// 商品ID
		entity.setType(goodsVO.getType());// 商品类型ID
		// entity.setTypeName(typeName);;// 商品类型名称
		entity.setName(goodsVO.getName());// 商品名称
		entity.setIntroduce(goodsVO.getIntroduce());// 商品简介
		entity.setPrice(goodsVO.getPrice());// 商品原始价格
		entity.setOffer(goodsVO.getOffer());// 商品折扣(单位%，例如20，表示折扣20%)
		entity.setUnit(goodsVO.getUnit());// 价格单位
		// entity.setMulVa1Json;// 多属性
		entity.setIcon(goodsVO.getIcon());// 商品icon(根据排版风格1，风格2， 该Icon的 尺寸要求不一样)
		// entity.setTitlePicJson;// /商品主图片下载URL(可以有12张图片)
		// entity.setDetailePicJson;// 商品详情图片下载URL
		// entity.setParamsJson;// 商品详细参数介绍
		entity.setAddress(goodsVO.getAddress());// 商品发货地
		entity.setStock(goodsVO.getStock());// 商品库存
		entity.setSaleCount(goodsVO.getSaleCount());// 已出售商品个数
		entity.setShippingType(goodsVO.getShippingType());// 快递方式(如果这个值为null，表示免运费)
		entity.setShippingCost(goodsVO.getShippingCost());// 快递费
		entity.setDisStyle(goodsVO.getDisStyle());// 显示风格
		entity.setServiecType(goodsVO.getServiecType());// 客服方式，1为电话，2为微信号，3为QQ号
		entity.setServiecTel(goodsVO.getServiecTel());// 客服电话/或微信号/或QQ号
		entity.setTitlePic(goodsVO.getTitlePic());
		entity.setDetailePic(goodsVO.getDetailePic());
		entity.setParams(goodsVO.getParams());
		List<MulVal> mulVals = new ArrayList<MulVal>();
		if (goodsVO.getMulVal() != null && goodsVO.getMulVal().size() > 0) {
			for (MutiAttr mutiAttr : goodsVO.getMulVal()) {
				MulVal mulVal = new MulVal();
				String[] values = mutiAttr.getValues();
				Map<String, String> attr_map = new HashMap<String, String>();
				for (int i = 0; i < values.length; i++) {
					String key = goodsVO.getMulName().get(i);
					attr_map.put(key, values[i]);
				}
				mulVal.setValues(attr_map);
				values = mutiAttr.getImg();
				if (values != null && values.length > 0) {
					List<String> images = new ArrayList<String>();
					for (String img : values) {
						images.add(img);
					}
					mulVal.setImg(images);
				}
				mulVals.add(mulVal);
			}
		}
		entity.setMulVal(mulVals);
		entity.encode();
	}
}
*/
package com.igame.app.entity;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

public class GoodsEntity implements JsonEntity,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;// 商品ID
	@JSONField(serialize = false)
	private long appid;// 商家id
	//是否为人气王商品
//	private int hot;
	private int type;// 商品类型ID
	private String typeName;// 商品类型名称
	private String name;// 商品名称
	private String introduce;// 商品简介
	private double price;// 商品原始价格
	private int offer;// 商品折扣(单位%，例如20，表示折扣20%)
	private String unit;// 价格单位
	@JSONField(serialize = false)
	private String mulVa1Json;// 多属性
	private String icon;// 商品icon(根据排版风格1，风格2， 该Icon的 尺寸要求不一样)
	@JSONField(serialize = false)
	private String titlePicJson;// /商品主图片下载URL(可以有12张图片)
	@JSONField(serialize = false)
	private String detailePicJson;// 商品详情图片下载URL
	@JSONField(serialize = false)
	private String paramsJson;// 商品详细参数介绍
	private String address;// 商品发货地
	private int stock;// 商品库存
	private int saleCount;// 已出售商品个数
	private String shippingType;// 快递方式(如果这个值为null，表示免运费)
	private int shippingCost;// 快递费
	private int disStyle;// 显示风格
	private String serviecType;// 客服方式，1为电话，2为微信号，3为QQ号
	private String serviecTel;// 客服电话/或微信号/或QQ号

	// 原始对象操作
	private List<MulVal> mulVal;
	private List<String> titlePic;
	private List<String> detailePic;
	private List<String> params;

	@Override
	public void decode() {
		if (StringUtils.isNotEmpty(mulVa1Json)) {
			// List<JSONObject> array = JSON.parseArray(mulVa1Json,
			// JSONObject.class);
			// mulName = new ArrayList<String>();
			// if (array.size() > 0) {
			// int i = 0;
			// for (JSONObject jsonObject : array) {
			// if (i == 0) {
			// Set<String> keys = jsonObject.keySet();
			// for (String key : keys) {
			// mulName.add(key);
			// }
			// }
			// MulVal mulVal2 = new MulVal();
			// List<String> vals = new ArrayList<String>();
			// Collection<Object> obj = jsonObject.values();
			// for (Object object : obj) {
			// vals.add((String) object);
			// }
			// mulVal2.setValues(vals);
			// }
			// }
			mulVal = JSON.parseArray(mulVa1Json, MulVal.class);
		}

		if (StringUtils.isNotEmpty(titlePicJson)) {
			titlePic = JSON.parseArray(titlePicJson, String.class);
		}
		if (StringUtils.isNotEmpty(detailePicJson)) {
			detailePic = JSON.parseArray(detailePicJson, String.class);
		}
		if (StringUtils.isNotEmpty(paramsJson)) {
			params = JSON.parseArray(paramsJson, String.class);
		}

	}

	@Override
	public void encode() {
		if (mulVal != null && !mulVal.isEmpty()) {
			mulVa1Json = JSON.toJSONString(mulVal);
		}
		if (titlePic != null && !titlePic.isEmpty()) {
			titlePicJson = JSON.toJSONString(titlePic);
		}
		if (detailePic != null && !detailePic.isEmpty()) {
			detailePicJson = JSON.toJSONString(detailePic);
		}
		if (params != null && !params.isEmpty()) {
			paramsJson = JSON.toJSONString(params);
		}
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getOffer() {
		return offer;
	}

	public void setOffer(int offer) {
		this.offer = offer;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getMulVa1Json() {
		return mulVa1Json;
	}

	public void setMulVa1Json(String mulVa1Json) {
		this.mulVa1Json = mulVa1Json;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTitlePicJson() {
		return titlePicJson;
	}

	public void setTitlePicJson(String titlePicJson) {
		this.titlePicJson = titlePicJson;
	}

	public String getDetailePicJson() {
		return detailePicJson;
	}

	public void setDetailePicJson(String detailePicJson) {
		this.detailePicJson = detailePicJson;
	}

	public String getParamsJson() {
		return paramsJson;
	}

	public void setParamsJson(String paramsJson) {
		this.paramsJson = paramsJson;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getSaleCount() {
		return saleCount;
	}

	public void setSaleCount(int saleCount) {
		this.saleCount = saleCount;
	}

	public String getShippingType() {
		return shippingType;
	}

	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}

	public int getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(int shippingCost) {
		this.shippingCost = shippingCost;
	}

	public int getDisStyle() {
		return disStyle;
	}

	public void setDisStyle(int disStyle) {
		this.disStyle = disStyle;
	}

	public String getServiecType() {
		return serviecType;
	}

	public void setServiecType(String serviecType) {
		this.serviecType = serviecType;
	}

	public String getServiecTel() {
		return serviecTel;
	}

	public void setServiecTel(String serviecTel) {
		this.serviecTel = serviecTel;
	}

	public List<MulVal> getMulVal() {
		return mulVal;
	}

	public void setMulVal(List<MulVal> mulVal) {
		this.mulVal = mulVal;
	}

	public List<String> getTitlePic() {
		return titlePic;
	}

	public void setTitlePic(List<String> titlePic) {
		this.titlePic = titlePic;
	}

	public List<String> getDetailePic() {
		return detailePic;
	}

	public void setDetailePic(List<String> detailePic) {
		this.detailePic = detailePic;
	}

	public List<String> getParams() {
		return params;
	}

	public void setParams(List<String> params) {
		this.params = params;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAppid() {
		return appid;
	}

	public void setAppid(long appid) {
		this.appid = appid;
	}

}

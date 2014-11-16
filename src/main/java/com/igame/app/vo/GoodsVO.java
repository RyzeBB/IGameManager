package com.igame.app.vo;

import java.util.List;

public class GoodsVO {
	private int id;// 商品ID
	private int type;// 商品类型ID
	private String typeName;// 商品类型名称
	private String name;// 商品名称
	private String introduce;// 商品简介
	private int price;// 商品原始价格
	private int offer;// 商品折扣(单位%，例如20，表示折扣20%)
	private String unit = "元";// 价格单位
	private MutiAttr[] muti_att;// 多属性
	private String icon;// 商品icon(根据排版风格1，风格2， 该Icon的 尺寸要求不一样)
	private List<String> titlePic;// /商品主图片下载URL(可以有12张图片)
	private List<String> detailePic;// 商品详情图片下载URL
	private List<String> params;// 商品详细参数介绍
	private String address;// 商品发货地
	private int stock;// 商品库存
	private int saleCount;// 已出售商品个数
	private String shippingType;// 快递方式(如果这个值为null，表示免运费)
	private int shippingCost;// 快递费
	private int disStyle;// 显示风格
	private String serviecType;// 客服方式，1为电话，2为微信号，3为QQ号
	private String serviecTel;// 客服电话/或微信号/或QQ号

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
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

	public MutiAttr[] getMuti_att() {
		return muti_att;
	}

	public void setMuti_att(MutiAttr[] muti_att) {
		this.muti_att = muti_att;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
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

}

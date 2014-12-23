package com.igame.app.entity;

import java.util.Date;

/**
 * 订单
 * 
 * @author Allen
 *
 */
public class OrderEntity {
	private long id;
	private String deviceId;
	private long appid;
	private long billno;
	private String payitem;// 物品id*price*num=sku#id*price*num=sku
	private String goods_url;// id*url#id*url 商品图片
	private String descrip;// 备注
	private int price;// 价格
	private int coupon;// 优惠卷金额
	private String addr;// 收货地址
	private Date create_time;// 下单时间
	private Date pay_time;// 支付时间
	private int state;// 状态 0表示正常 1表示关闭 2表示支付成功 3表示退款中 4表示退款成功
	private String msg;// 留言
	private String back_goods;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public long getAppid() {
		return appid;
	}

	public void setAppid(long appid) {
		this.appid = appid;
	}

	public long getBillno() {
		return billno;
	}

	public void setBillno(long billno) {
		this.billno = billno;
	}

	public String getPayitem() {
		return payitem;
	}

	public void setPayitem(String payitem) {
		this.payitem = payitem;
	}

	public String getGoods_url() {
		return goods_url;
	}

	public void setGoods_url(String goods_url) {
		this.goods_url = goods_url;
	}

	public String getDescrip() {
		return descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCoupon() {
		return coupon;
	}

	public void setCoupon(int coupon) {
		this.coupon = coupon;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getPay_time() {
		return pay_time;
	}

	public void setPay_time(Date pay_time) {
		this.pay_time = pay_time;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getBack_goods() {
		return back_goods;
	}

	public void setBack_goods(String back_goods) {
		this.back_goods = back_goods;
	}

}

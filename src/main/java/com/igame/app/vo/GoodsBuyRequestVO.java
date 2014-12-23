package com.igame.app.vo;

import java.util.List;

/**
 * 购买商品
 * 
 * @author Allen
 */
public class GoodsBuyRequestVO extends RequestVO {
	private List<Item> items;// 购买商品列表
	private long cid;// 优惠卷id
	private String msg;// 留言
	private String addr;

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public long getCid() {
		return cid;
	}

	public void setCid(long cid) {
		this.cid = cid;
	}

	public static class Item {
		private long gid;
		private String sku;
		private int num;

		public long getGid() {
			return gid;
		}

		public void setGid(long gid) {
			this.gid = gid;
		}

		public String getSku() {
			return sku;
		}

		public void setSku(String sku) {
			this.sku = sku;
		}

		public int getNum() {
			return num;
		}

		public void setNum(int num) {
			this.num = num;
		}

	}
}

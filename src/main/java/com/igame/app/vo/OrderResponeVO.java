package com.igame.app.vo;

import java.util.List;

import com.igame.app.entity.OrderEntity;

/**
 * @author 订单详情
 */
public class OrderResponeVO extends ResponseVO {
	private List<OrderEntity> orders;

	public List<OrderEntity> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderEntity> orders) {
		this.orders = orders;
	}

}

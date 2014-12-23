package com.igame.app.vo;

import java.util.List;

import com.igame.app.entity.BuyerCouponEntity;

/**
 * 买家信息
 * 
 * @author Allen
 */
public class BuyerResponeVO extends ResponseVO {
	// 买家优惠卷信息
	private List<BuyerCouponEntity> coupon;

	public List<BuyerCouponEntity> getCoupon() {
		return coupon;
	}

	public void setCoupon(List<BuyerCouponEntity> coupon) {
		this.coupon = coupon;
	}

}

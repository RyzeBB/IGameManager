package com.igame.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igame.app.entity.CouponEntity;
import com.igame.app.mapper.CouponMapper;

@Service
public class CouponService {
	@Autowired
	private CouponMapper couponMapper;

	/**
	 * 获取当前店铺优惠卷
	 * 
	 * @param appid
	 * @return
	 */
	public List<CouponEntity> getCoupon(long appid) {
		return couponMapper.listCoupon(appid);
	}

	/**
	 * 更新店铺优惠卷
	 * 
	 * @param couponEntity
	 */
	public void modifyCoupon(long appid,CouponEntity couponEntity) {
		couponEntity.setAppid(appid);
		couponMapper.updateCoupon(couponEntity);
	}

	/**
	 * 新增店铺优惠卷
	 * 
	 * @param couponEntity
	 */
	public void addCoupon(long appid,CouponEntity couponEntity) {
		couponEntity.setAppid(appid);
		couponMapper.insertCoupon(couponEntity);
	}

	/**
	 * 删除店铺优惠卷
	 * 
	 * @param appid
	 * @param id
	 */
	public void delCoupon(long appid, long id) {
		couponMapper.deleteCoupon(id);
	}

}

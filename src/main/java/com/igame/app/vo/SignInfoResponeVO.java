package com.igame.app.vo;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 获取签到信息
 */
public class SignInfoResponeVO extends ResponseVO {
	private int sign;// 是否已经签到 1：已签到；0：尚未签到
	private long totalScore;// 签到总分数
	private int signScore;// 本次签到分数
	private int couponPrice;// 优惠卷价格
	private int couponScore;// 获取优惠劵所需要的分数
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date lastDate;// 最后签到日期

	public int getSign() {
		return sign;
	}

	public void setSign(int sign) {
		this.sign = sign;
	}

	public long getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(long totalScore) {
		this.totalScore = totalScore;
	}

	public int getSignScore() {
		return signScore;
	}

	public void setSignScore(int signScore) {
		this.signScore = signScore;
	}

	public int getCouponPrice() {
		return couponPrice;
	}

	public void setCouponPrice(int couponPrice) {
		this.couponPrice = couponPrice;
	}

	public int getCouponScore() {
		return couponScore;
	}

	public void setCouponScore(int couponScore) {
		this.couponScore = couponScore;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

}

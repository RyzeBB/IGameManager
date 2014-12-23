package com.igame.app.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 用户优惠卷
 * 
 * @author Allen
 *
 */
public class BuyerCouponEntity {
	private long id;
	@JSONField(serialize = false)
	private long appid;
	@JSONField(serialize = false)
	private String deviceId;
	private String name;
	private int rmb;
	@JSONField(serialize = false)
	private int score;
	@JSONField(serialize = false)
	private int state;
	@JSONField(serialize = false)
	private Date create_time;
	@JSONField(serialize = false)
	private Date used_time;

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

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRmb() {
		return rmb;
	}

	public void setRmb(int rmb) {
		this.rmb = rmb;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getUsed_time() {
		return used_time;
	}

	public void setUsed_time(Date used_time) {
		this.used_time = used_time;
	}

}

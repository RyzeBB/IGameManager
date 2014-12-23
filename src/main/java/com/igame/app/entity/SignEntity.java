package com.igame.app.entity;

import java.util.Date;

/**
 * 用户签到信息
 * 
 * @author Allen
 *
 */
public class SignEntity {
	private long id;
	private String deviceId;
	private long appid;
	private long score;// 积分
	private Date last_sign;// 最后签到时间

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}

	public Date getLast_sign() {
		return last_sign;
	}

	public void setLast_sign(Date last_sign) {
		this.last_sign = last_sign;
	}

}

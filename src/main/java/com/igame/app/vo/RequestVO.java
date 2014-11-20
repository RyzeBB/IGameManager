package com.igame.app.vo;

/**
 * @author Allen 1.上行基类
 */
public class RequestVO {
	private String deviceId;// 设备Id(手机序列号)
	private String systemName;// 系统名称：iOS/Android
	private String systemVersion;// 当前客户端设备系统版本
	private String appVersionCode;// 应用程序版本号
	private String deviceModel;// 客户端设备型号，如(iPhone5)
	private Long appid; // 商家id 这个是最终更要的
	private String channel;// 渠道号 (预留--考虑到商家可能有需求知道他哪个渠道访问量高)
	private int actionCode;// actionCode

	public int getActionCode() {
		return actionCode;
	}

	public void setActionCode(int actionCode) {
		this.actionCode = actionCode;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getSystemVersion() {
		return systemVersion;
	}

	public void setSystemVersion(String systemVersion) {
		this.systemVersion = systemVersion;
	}

	public String getAppVersionCode() {
		return appVersionCode;
	}

	public void setAppVersionCode(String appVersionCode) {
		this.appVersionCode = appVersionCode;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public Long getAppid() {
		return appid;
	}

	public void setAppid(Long appid) {
		this.appid = appid;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

}

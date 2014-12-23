package com.igame.app.vo;

/**
 * @author Allen 下行基类
 */
public class ResponseVO {
	public static final int CODE_SUCCESS = 0;
	public static final int CODE_ERROR = 1;

	private int actionCode;// 上行指令码
	private int resultCode = CODE_SUCCESS;// 返回结果代码 0：成功 1：失败
	private String errorInfo;// 返回错误描述

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public int getActionCode() {
		return actionCode;
	}

	public void setActionCode(int actionCode) {
		this.actionCode = actionCode;
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

}

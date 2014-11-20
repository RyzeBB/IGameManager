package com.igame.app.exception;

public class AppException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int actionCode;// 上行指令码

	public AppException(int actionCode, String errorInfo) {
		super(errorInfo);
		this.actionCode = actionCode;
	}

	public AppException(int actionCode, Throwable cause) {
		super(cause);
		this.actionCode = actionCode;
	}

	public AppException(int actionCode, String message, Throwable cause) {
		super(message, cause);
		this.actionCode = actionCode;
	}

	public int getActionCode() {
		return actionCode;
	}

	public void setActionCode(int actionCode) {
		this.actionCode = actionCode;
	}

}
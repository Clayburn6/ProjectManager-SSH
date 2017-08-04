package com.pgb.utils;

public class Result {
	private int code;
	private String message;
	private Object data;
	public Result() {}
	
	public Result(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public Result(int code, String message, Object data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}

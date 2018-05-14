package com.cafe24.mysite.dto;

public class JSONResult {
	private String result; //"success" or "fail"
	private String message; //result가 "fail"이면 원인 메세지
	private Object data; //result가 "success"이면 전달할 데이터
	
	
	private JSONResult(String result, String message, Object data) {
		this.result = result;
		this.message = message;
		this.data = data;
	}
	
	static public  JSONResult success(Object data) {
		return new JSONResult("success",null,data);
	}
	static public JSONResult fail(String message) {
		return new JSONResult("fail",message,null);
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
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

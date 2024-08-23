package com.Json;

public class JsonResponce {
	
	private String Message;
	private String Status;
	private String Result;
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getResult() {
		return Result;
	}
	public void setResult(String result) {
		Result = result;
	}
	@Override
	public String toString() {
		return "JsonResponce [Message=" + Message + ", Status=" + Status + ", Result=" + Result + "]";
	}
	public JsonResponce(String message, String status, String result) {
		super();
		Message = message;
		Status = status;
		Result = result;
	}
	public JsonResponce() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}

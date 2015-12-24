package com.ane.framework.Base.network;

public class ResultMsg {
	
	private Boolean result;//返回结果
	private String resultCode;//返回code
	private Object resultInfo;//返回信息
	private String reason;//原因
	 
	public Boolean getResult() {
		return result;
	}
	public void setResult(Boolean result) {
		this.result = result;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public Object getResultInfo() {
		return resultInfo;
	}
	public void setResultInfo(Object resultInfo) {
		this.resultInfo = resultInfo;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
}

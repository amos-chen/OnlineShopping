package com.taotao.dto;

/**
 * Created by chenlunwei on 2017/5/5.
 */
public class ExecuteJsonResult<T> {

	private boolean success;

	private String error;

	private T data;

	public ExecuteJsonResult(boolean success, String error) {
		this.success = success;
		this.error = error;
	}

	public ExecuteJsonResult(boolean success, T data) {
		this.success = success;
		this.data = data;
	}

	public boolean isSuccess() {

		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}

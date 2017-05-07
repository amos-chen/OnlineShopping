package com.taotao.dto;

/**
 * Created by chenlunwei on 2017/5/5.
 */
public class ExecuteItemsJsonResult<T> {

	private boolean success;

	private long total;

	private String error;

	private T rows;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public T getRows() {
		return rows;
	}

	public void setRows(T rows) {
		this.rows = rows;
	}

	//成功访问的构造方法
	public ExecuteItemsJsonResult(boolean success, long total, T rows) {
		this.success = success;
		this.total = total;
		this.rows = rows;
	}

	//失败的构造方法
	public ExecuteItemsJsonResult(boolean success, String error) {
		this.success = success;
		this.error = error;
	}
}

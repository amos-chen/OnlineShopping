package com.taotao.rest.pojo;

import java.util.List;

/**
 * Created by chenlunwei on 2017/6/18.
 */
public class CatResult {

	private List<?> data;

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "CatResult{" +
				"data=" + data +
				'}';
	}
}

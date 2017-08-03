package com.taotao.dto;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by chenlunwei on 2017/5/5.
 */
public class ExecuteJsonResult<T> {

	// 定义jackson对象
	private static final ObjectMapper MAPPER = new ObjectMapper();

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

	public ExecuteJsonResult(boolean success,String error, T data) {
		this.success = success;
		this.error =error;
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

	public static <T>ExecuteJsonResult<T> parseJson(String jsonString,Class<T> beanType) {
		try {
			JsonNode jsonNode = MAPPER.readTree(jsonString);
			JsonNode data = jsonNode.path("data");
			//允许出现转义字符
			//MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true) ;
			T obj = null;
			if (data.size() > 0) {
				obj = MAPPER.readValue(data.toString(), beanType);
			}
			if (jsonNode.get("success").toString() == "true") {
				return new ExecuteJsonResult<>(true,obj);
			} else {
				return new ExecuteJsonResult<>(false, jsonNode.get("error").asText());
			}
		} catch (Exception e) {
			return null;
		}
	}

}

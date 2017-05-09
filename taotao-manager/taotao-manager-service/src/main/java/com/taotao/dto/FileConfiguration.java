package com.taotao.dto;

import java.util.Map;

/**
 * Created by chenlunwei on 2017/5/8.
 */
public class FileConfiguration {
	//回调缩略图的标题
	private String caption;
	//缩略图的宽度
	private String width;
	//缩略图的删除ajax的url
	private String url;
	//用于ajax删除已上传文件
	private String key;
	//extra用于删除上传成功的图片时，向springmvc传递一些额外的值
	private Map<String,String> extra;

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Map<String, String> getExtra() {
		return extra;
	}

	public void setExtra(Map<String, String> extra) {
		this.extra = extra;
	}
}

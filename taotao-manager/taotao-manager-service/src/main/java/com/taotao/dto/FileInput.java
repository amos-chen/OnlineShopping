package com.taotao.dto;

import java.util.List;

/**
 * Created by chenlunwei on 2017/5/8.
 */
public class FileInput {
	//文件上传出现错误是显示的错误信息
	private String error;
	//上传成功后返回的HTML代码，用于生成预览缩略图
	private List<String> initialPreview;
	//设置预览缩略图的一些信息，例如标题，尺寸等...
	private List<String> initialPreviewConfig;
	//缩略图生成前所显示的缩略图标签
	private List<String> initialPreviewThumbTags;
	//如果在初始化fileinput对象时已经设置了initialPreview，是否用json里的initialPreview替代，默认为true
	//private boolean append;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public List<String> getInitialPreview() {
		return initialPreview;
	}

	public void setInitialPreview(List<String> initialPreview) {
		this.initialPreview = initialPreview;
	}

	public List<String> getInitialPreviewConfig() {
		return initialPreviewConfig;
	}

	public void setInitialPreviewConfig(List<String> initialPreviewConfig) {
		this.initialPreviewConfig = initialPreviewConfig;
	}

	public List<String> getInitialPreviewThumbTags() {
		return initialPreviewThumbTags;
	}

	public void setInitialPreviewThumbTags(List<String> initialPreviewThumbTags) {
		this.initialPreviewThumbTags = initialPreviewThumbTags;
	}
}

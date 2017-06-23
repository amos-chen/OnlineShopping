package com.taotao.dto;


/**
 * Created by chenlunwei on 2017/5/6.
 */
public class JSTreeAjax {

	private String id;

	private String text;

	private String icon;

	private boolean children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public boolean isChildren() {
		return children;
	}

	public void setChildren(boolean children) {
		this.children = children;
	}

	public JSTreeAjax(String id, String text, String icon, boolean children) {
		this.id = id;
		this.text = text;
		this.icon = icon;
		this.children = children;
	}

	@Override
	public String toString() {
		return "JSTree{" +
				"id='" + id + '\'' +
				", text='" + text + '\'' +
				", icon='" + icon + '\'' +
				", state=" + children +
				'}';
	}
}

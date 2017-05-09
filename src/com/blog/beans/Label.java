package com.blog.beans;

public class Label {
	private Integer labelId;
	private String labelTitle;
	private Integer userId;
	public void setLabelId(Integer labelId) {
		this.labelId = labelId;
	}
	public void setLabelTitle(String labelTitle) {
		this.labelTitle = labelTitle;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getLabelId() {
		return labelId;
	}
	public String getLabelTitle() {
		return labelTitle;
	}
	public Integer getUserId() {
		return userId;
	}
}

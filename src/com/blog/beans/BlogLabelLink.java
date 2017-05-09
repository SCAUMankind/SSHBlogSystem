package com.blog.beans;

public class BlogLabelLink {
	private Integer id;
	private Integer blogId;
	private Integer labelId;
	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}
	public void setLabelId(Integer labelId) {
		this.labelId = labelId;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBlogId() {
		return blogId;
	}
	public Integer getLabelId() {
		return labelId;
	}
	public Integer getId() {
		return id;
	}
}

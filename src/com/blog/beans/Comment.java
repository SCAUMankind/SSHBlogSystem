package com.blog.beans;

import java.util.Date;

public class Comment {
	private Integer id;
	private Integer fatherId;
	private Integer blogId;
	private Integer userId;
	private String content;
	private Boolean haveReply;
	private Date date;
	public void setId(Integer id) {
		this.id = id;
	}
	public void setFatherId(Integer fatherId) {
		this.fatherId = fatherId;
	}
	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setHaveReply(Boolean haveReply) {
		this.haveReply = haveReply;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getId() {
		return id;
	}
	public Integer getFatherId() {
		return fatherId;
	}
	public Integer getBlogId() {
		return blogId;
	}
	public Integer getUserId() {
		return userId;
	}
	public String getContent() {
		return content;
	}
	public Boolean getHaveReply() {
		return haveReply;
	}
	public Date getDate() {
		return date;
	}
}

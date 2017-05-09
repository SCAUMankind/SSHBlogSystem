package com.blog.beans;

import java.util.Date;

public class Fans {
	private Integer id;
	private Integer userId;
	private Integer noticerId;
	private Date date;
	public void setDate(Date date) {
		this.date = date;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public void setNoticerId(Integer noticerId) {
		this.noticerId = noticerId;
	}
	public Integer getId() {
		return id;
	}
	public Integer getUserId() {
		return userId;
	}
	public Integer getNoticerId() {
		return noticerId;
	}
	public Date getDate() {
		return date;
	}
}

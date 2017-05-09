package com.blog.beans;

import java.util.Date;

public class Blog {
	private Integer blogId;
	private String title;
	private String text;
	private Integer visit;
	private Integer userId;
	private Date date;
	private Integer blogPV;
	public void setBlogPV(Integer blogPV) {
		this.blogPV = blogPV;
	}
	public Integer getBlogPV() {
		return blogPV;
	}
	public void setBlogId(Integer blogId){
		this.blogId=blogId;
	}
	public Integer getBlogId(){
		return blogId;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public void setText(String text){
		this.text=text;
	}
	public String getText(){
		return text;
	}
	public void setVisit(Integer visit){
		this.visit=visit;
	}
	public Integer getVisit(){
		return visit;
	}
	public void setUserId(Integer userId){
		this.userId=userId;
	}
	public Integer getUserId(){
		return userId;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getDate() {
		return date;
	}
}

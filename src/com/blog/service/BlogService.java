package com.blog.service;

import java.util.List;

import com.blog.beans.Blog;

public interface BlogService extends BaseService<Blog>{
	public List showBlogByPage(Integer userId,Integer pageNum,Integer size);
	public Integer getTotalPages(Integer userId,Integer size);
	public void saveBlog(Integer userId,String title,String text);
	public Integer getBlogId(Integer userId,String title);
	public Integer getTotalNum(Integer userId);
	public List<Blog> getBlogTop10(Integer userId);
}

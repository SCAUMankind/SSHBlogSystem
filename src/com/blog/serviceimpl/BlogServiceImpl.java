package com.blog.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.blog.beans.Blog;
import com.blog.dao.BlogDao;
import com.blog.service.BlogService;

public class BlogServiceImpl extends BaseServiceImpl<Blog> implements BlogService{
	private BlogDao blogDao;
	public void setBlogDao(BlogDao blogDao) {
		this.blogDao = blogDao;
	}
	public BlogDao getBlogDao() {
		return blogDao;
	}
	@Override
	public List showBlogByPage(Integer userId, Integer pageNum, Integer size) {
		return blogDao.queryByPage(userId, pageNum, size);
	}
	@Override
	public Integer getTotalPages(Integer userId,Integer size) {
		if(blogDao.getTotalNum(userId)==0)return 0;
		return (blogDao.getTotalNum(userId)-1)/size+1;
	}
	public void saveBlog(Integer userId,String title,String text){
		Blog blog=new Blog();
		blog.setUserId(userId);
		blog.setText(text);
		blog.setTitle(title);
		blog.setDate(new Date());
		save(blog);
	}
	public Integer getBlogId(Integer userId,String title){
		return blogDao.getBlogIdByTitle(userId, title);
	}
	public Integer getTotalNum(Integer userId){
		return blogDao.getTotalNum(userId);
	}
	@Override
	public List<Blog> getBlogTop10(Integer userId) {
		List<Blog> blogList=new ArrayList<Blog>();
		List<Blog> blog=blogDao.getBlogByPV(userId);
		if(blog.size()>10){
			for(int i=0;i<10;i++){
				blogList.add(blog.get(i));
			}
		}else{
			for(int i=0;i<blog.size();i++){
				blogList.add(blog.get(i));
			}
		}
		return blogList;
	}
}

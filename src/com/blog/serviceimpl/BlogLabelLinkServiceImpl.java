package com.blog.serviceimpl;

import java.util.List;

import com.blog.beans.BlogLabelLink;
import com.blog.dao.BlogLabelLinkDao;
import com.blog.service.BlogLabelLinkService;

public class BlogLabelLinkServiceImpl extends BaseServiceImpl<BlogLabelLink> implements BlogLabelLinkService{
	private BlogLabelLinkDao blogLabelLinkDao;
	public void setBlogLabelLinkDao(BlogLabelLinkDao blogLabelLinkDao) {
		this.blogLabelLinkDao = blogLabelLinkDao;
	}
	public BlogLabelLinkDao getBlogLabelLinkDao() {
		return blogLabelLinkDao;
	}
	@Override
	public void saveLink(Integer blogId, Integer labelId) {
		BlogLabelLink blogLabelLink=new BlogLabelLink();
		blogLabelLink.setBlogId(blogId);
		blogLabelLink.setLabelId(labelId);
		save(blogLabelLink);
	}

	@Override
	public void deleteLabel(Integer labelId) {
		blogLabelLinkDao.deleteByLabelId(labelId);
	}
	public List<BlogLabelLink> getBlogListByLabelId(Integer labelId){
		return blogLabelLinkDao.getBlogList(labelId);
	}
	@Override
	public List<BlogLabelLink> getBlogListByPage(Integer labelId, Integer pageNum, Integer size) {
		return blogLabelLinkDao.getBlogListByPage(labelId, pageNum, size);
	}
	@Override
	public Integer getTotalPages(Integer labelId, Integer size) {
		Integer totalNum=blogLabelLinkDao.getTotalNum(labelId);
		if(totalNum!=0){
			return totalNum/size+1;
		}
		return 0;
	}
	@Override
	public List<BlogLabelLink> getLabelListByBlogId(Integer blogId) {
		return blogLabelLinkDao.getLabelList(blogId);
	}
	@Override
	public void deleteBlog(Integer blogId) {
		blogLabelLinkDao.deleteByBolgId(blogId);
	}
	
}

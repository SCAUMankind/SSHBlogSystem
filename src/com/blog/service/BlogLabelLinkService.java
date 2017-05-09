package com.blog.service;

import java.util.List;

import com.blog.beans.BlogLabelLink;

public interface BlogLabelLinkService extends BaseService<BlogLabelLink>{
	public void saveLink(Integer blogId,Integer labelId);
	public void deleteLabel(Integer labelId);
	public void deleteBlog(Integer blogId);
	public List<BlogLabelLink> getBlogListByLabelId(Integer labelId);
	public List<BlogLabelLink> getBlogListByPage(Integer labelId,Integer pageNum,Integer size);
	public Integer getTotalPages(Integer labelId,Integer size);
	public List<BlogLabelLink> getLabelListByBlogId(Integer blogId);
}

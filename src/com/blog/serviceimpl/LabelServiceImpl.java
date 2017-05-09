package com.blog.serviceimpl;

import java.util.List;

import com.blog.beans.BlogLabelLink;
import com.blog.beans.Label;
import com.blog.common.Constant;
import com.blog.dao.BlogLabelLinkDao;
import com.blog.dao.LabelDao;
import com.blog.service.LabelService;

public class LabelServiceImpl extends BaseServiceImpl<Label> implements LabelService{
	private LabelDao labelDao;
	private BlogLabelLinkDao blogLabelLinkDao;
	public void setBlogLabelLinkDao(BlogLabelLinkDao blogLabelLinkDao) {
		this.blogLabelLinkDao = blogLabelLinkDao;
	}
	public BlogLabelLinkDao getBlogLabelLinkDao() {
		return blogLabelLinkDao;
	}
	public void setLabelDao(LabelDao labelDao) {
		this.labelDao = labelDao;
	}
	public LabelDao getLabelDao() {
		return labelDao;
	}
	@Override
	public List<Label> getLabelList(Integer userId, Integer pageNum, Integer size) {
		return labelDao.getLabelList(userId, pageNum, size);
	}

	@Override
	public Integer getLabelTotalPages(Integer userId) {
		if(labelDao.getTotalNum(userId)==0) return 0;
		return (labelDao.getTotalNum(userId)-1)/Constant.LABEL_SIZE+1;
	}
	public Integer getMarkedBlogSize(Integer labelId){
		return blogLabelLinkDao.getBlogList(labelId).size();
	}
	@Override
	public List<Label> getAllLabel(Integer userId) {
		return labelDao.getAllLabelById(userId);
	}
	@Override
	public void saveLabel(Integer userId, String title) {
		Label label=new Label();
		label.setLabelTitle(title);
		label.setUserId(userId);
		save(label);
	}
	@Override
	public void updateLabel(Integer labelId, String title,Integer userId) {
		Label label=new Label();
		label.setLabelId(labelId);
		label.setLabelTitle(title);
		label.setUserId(userId);
		update(label);
	}
}

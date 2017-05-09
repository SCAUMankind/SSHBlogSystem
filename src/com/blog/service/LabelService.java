package com.blog.service;

import java.util.List;
import com.blog.beans.Label;
public interface LabelService extends BaseService<Label>{
	public List<Label> getLabelList(Integer userId,Integer pageNum,Integer size);
	public Integer getLabelTotalPages(Integer userId);
	public Integer getMarkedBlogSize(Integer labelId);
	public List<Label> getAllLabel(Integer userId);
	public void saveLabel(Integer userId,String title);
	public void updateLabel(Integer labelId, String title,Integer userId);
}

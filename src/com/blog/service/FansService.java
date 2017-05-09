package com.blog.service;

import java.util.List;

import com.blog.beans.Fans;

public interface FansService extends BaseService<Fans>{
	public List getFansList(Integer userId,Integer pageNum,Integer size);
	public List getNoticerList(Integer userId,Integer pageNum,Integer size);
	public Integer getFansTotalPages(Integer userId);
	public Integer getNoticerTotalPages(Integer userId);
	public void addFans(Integer userId,Integer noticerId);
	public void deleteFans(Integer userId,Integer noticerId);
	public Integer getFansNumber(Integer userId);
	public Integer getNoticerNumber(Integer userId);
	public Boolean exist(Integer userId,Integer noticerId);
	
}

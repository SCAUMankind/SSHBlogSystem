package com.blog.serviceimpl;

import java.util.Date;
import java.util.List;

import com.blog.beans.Fans;
import com.blog.common.Constant;
import com.blog.dao.FansDao;
import com.blog.service.FansService;

public class FansServiceImpl extends BaseServiceImpl<Fans> implements FansService{
	private FansDao fansDao;
	public FansDao getFansDao() {
		return fansDao;
	}
	public void setFansDao(FansDao fansDao) {
		this.fansDao = fansDao;
	}
	@Override
	public List getFansList(Integer userId, Integer pageNum, Integer size) {
		return fansDao.loadFansList(userId, pageNum, size);
	}

	@Override
	public List getNoticerList(Integer userId, Integer pageNum, Integer size) {
		return fansDao.loadNoticerList(userId, pageNum, size);
	}
	@Override
	public Integer getFansTotalPages(Integer userId) {
		if(fansDao.getFansTotalNum(userId)==0)return 0;
		return (fansDao.getFansTotalNum(userId)-1)/Constant.FANS_SIZE+1;
	}
	@Override
	public Integer getNoticerTotalPages(Integer userId) {
		if(fansDao.getNoticerTotalNum(userId)==0)return 0;
		return (fansDao.getNoticerTotalNum(userId)-1)/Constant.NOTICER_SIZE+1;
	}
	@Override
	public void addFans(Integer userId, Integer noticerId) {
		Fans fans =new Fans();
		fans.setUserId(userId);
		fans.setNoticerId(noticerId);
		fans.setDate(new Date());
		save(fans);
	}
	@Override
	public void deleteFans(Integer userId, Integer noticerId) {
		fansDao.deleteFans(userId, noticerId);
	}
	@Override
	public Integer getFansNumber(Integer userId) {
		return fansDao.getFansTotalNum(userId);
	}
	@Override
	public Integer getNoticerNumber(Integer userId) {
		return fansDao.getNoticerTotalNum(userId);
	}
	@Override
	public Boolean exist(Integer userId, Integer noticerId) {
		return fansDao.exist(userId, noticerId);
	}
}

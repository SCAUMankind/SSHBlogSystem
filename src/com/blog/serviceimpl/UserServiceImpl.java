package com.blog.serviceimpl;

import com.blog.beans.User;
import com.blog.dao.UserDao;
import com.blog.service.UserService;

public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public UserDao getUserDao(){
		return userDao;
	}
	@Override
	public Boolean loginCheck(String account,String password) {
		User user=userDao.loadByAccount(account);
		if(user!=null&&user.getPassword().equals(password)){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public Boolean registerCheck(String account,String password) {
		User user=userDao.loadByAccount(account);
		if(user==null){
			return true;
		}
		else 
			return false;
	}
	@Override
	public Integer loadUserId(String account) {
		User user=userDao.loadByAccount(account);
		return user.getId();
	}
	@Override
	public Boolean existCheck(String account) {
		User user=userDao.loadByAccount(account);
		if(user==null){
			return true;
		}
		return false;
	}
	@Override
	public Boolean loginCheck(String account) {
		User user=userDao.loadByAccount(account);
		if(user==null){
			return false;
		}
		return true;
	}
}

package com.blog.service;

import java.util.Date;

import com.blog.beans.UserInfo;

public interface UserInfoService extends BaseService<UserInfo> {
	public void saveUserInfo(Integer userId,String userName,Boolean sex,
			Date userBirthday,String userJob,String post,String userIntroduction,
			Integer userPlace,String userImage);
	public void updateDetail(Integer userId,String userName,Boolean sex,Date userBirthday,String userJob,
			String post,String userIntroduction,Integer userPlace);
	public void updateUserImage(Integer userId,String userImageURL);
	public String loadUserNameById(Integer userId);
	public UserInfo getUserInfoByUserId(Integer userId);
	public Integer getUserIdRandomly(Integer userId);
}

package com.blog.beans;

import java.util.Date;
import java.util.Set;

public class UserInfo {
	private Integer userId;
	private String userName;
	private Boolean sex;
	private Date userBirthday;
	private String userJob;
	private String userIntroduction;
	private Integer userPlace;
	private String userImage;
	private String post;
	private Integer userInfoId;
	private Date userRecentLoginTime;
	private Integer userPV;
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserName() {
		return userName;
	}
	public void setSex(Boolean sex) {
		this.sex = sex;
	}
	public Boolean getSex() {
		return sex;
	}
	public void setUserBirthday(Date userBirthday) {
		this.userBirthday = userBirthday;
	}
	public Date getUserBirthday() {
		return userBirthday;
	}
	public void setUserJob(String userJob) {
		this.userJob = userJob;
	}
	public String getUserJob() {
		return userJob;
	}
	public void setUserIntroduction(String userIntroduction) {
		this.userIntroduction = userIntroduction;
	}
	public String getUserIntroduction() {
		return userIntroduction;
	}
	public void setUserPlace(Integer userPlace) {
		this.userPlace = userPlace;
	}
	public Integer getUserPlace() {
		return userPlace;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public void setUserInfoId(Integer userInfoId) {
		this.userInfoId = userInfoId;
	}
	public Integer getUserInfoId() {
		return userInfoId;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getPost() {
		return post;
	}
	public void setUserRecentLoginTime(Date userRecentLoginTime) {
		this.userRecentLoginTime = userRecentLoginTime;
	}
	public Date getUserRecentLoginTime() {
		return userRecentLoginTime;
	}
	public void setUserPV(Integer userPV) {
		this.userPV = userPV;
	}
	public Integer getUserPV() {
		return userPV;
	}
}

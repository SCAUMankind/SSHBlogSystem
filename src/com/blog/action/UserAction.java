package com.blog.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.blog.beans.UserInfo;
import com.blog.common.Constant;
import com.blog.service.UserInfoService;
import com.blog.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport{
	private UserService userService;
	private UserInfoService userInfoService;
	private Map<String, Object> jsonMap=null;
	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}
	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}
	public void setUserService(UserService userService){
		this.userService=userService;
	}
	public UserService getUserService(){
		return userService;
	}
	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}
	public UserInfoService getUserInfoService() {
		return userInfoService;
	}	
	public String login() {
		System.out.println("inLogin!!!");
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		String account = request.getParameter("account");  
        String password = request.getParameter("password"); 
		if(userService.loginCheck(account,password)==true){
			Integer userId=userService.loadUserId(account);
			session.setAttribute("userId", userId);
			UserInfo userInfo=userInfoService.getUserInfoByUserId(userId);
			userInfo.setUserRecentLoginTime(new Date());
			userInfoService.update(userInfo);
			return Constant.SUCCESS;
		}else {
			return Constant.FAIL;
		}
	}
	public String passwordCheck(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		jsonMap=new HashMap<String, Object>();
		String password=request.getParameter("password");
		String account=request.getParameter("account");
		Boolean match=userService.loginCheck(account,password);
		jsonMap.put("valid", match);
		return "jsonMap";
	}
	public String execute(){
		System.out.println("111222333");
		return Constant.SUCCESS;
	}
}
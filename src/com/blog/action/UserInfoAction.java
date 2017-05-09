package com.blog.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.blog.beans.Place;
import com.blog.beans.UserInfo;
import com.blog.common.Constant;
import com.blog.service.PlaceService;
import com.blog.service.UserInfoService;
import com.blog.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class UserInfoAction extends ActionSupport{
	private UserInfoService userInfoService;
	private PlaceService placeService;
	private Map<String, Object> jsonMap=null;
	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}
	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}
	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}
	public UserInfoService getUserInfoService() {
		return userInfoService;
	}
	public void setPlaceService(PlaceService placeService) {
		this.placeService = placeService;
	}
	public PlaceService getPlaceService() {
		return placeService;
	}
	public String createUserInfo(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		//获取生日
		String date=request.getParameter("date");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date birthday = null;
		if(date!=null&&date!=""){
			try {	
			birthday = dateFormat.parse(date);
			} catch (ParseException e) {
			e.printStackTrace();
			}
		}
		//获取性别
		String sexName=request.getParameter("sex");
		Boolean sex=null;
		if(sexName!=null&&sexName!=""&&sexName.equals("male")){
			sex=true;
		}else if(sexName!=null&&sexName!=""&&sexName.equals("female")){
			sex=false;
		}
		//获取职位
		String job=request.getParameter("job");
		//获取邮箱
        String post = request.getParameter("post"); 
        //获取省市区
        String province=request.getParameter("province");
        String city=request.getParameter("city");
        String area=request.getParameter("area");
        //获得省市区对应的id
        Integer placeId=placeService.getPlaceId(province, city, area);
        Integer userId=(Integer)session.getAttribute("userId");
        String userName=(String)session.getAttribute("username");
        userInfoService.updateDetail(userId, userName, sex, birthday, job, post, null, placeId);
        return Constant.SUCCESS;
	}
	public String updateUserInfo(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		String name=request.getParameter("name");
		String sexName=request.getParameter("sex");
		Boolean sex=null;
		if(sexName!=null&&sexName!=""&&sexName.equals("male")){
			sex=true;
		}else if(sexName!=null&&sexName!=""&&sexName.equals("female")){
			sex=false;
		}
		String province=request.getParameter("province");
        String city=request.getParameter("city");
        String area=request.getParameter("area");
        String post = request.getParameter("post"); 
        String date=request.getParameter("date");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(date);
		Date birthday = null;
		if(date!=null&&date!=""){
			try {	
			birthday = dateFormat.parse(date);
			} catch (ParseException e) {
			e.printStackTrace();
			}
		}
		String job=request.getParameter("job");
		String introduction=request.getParameter("introduction");
		Integer userId=(Integer)session.getAttribute("userId");
		Integer placeId=placeService.getPlaceId(province, city, area);
		System.out.println("userId:"+userId+"name:"+name+"sex"+sex+"birthday"+birthday
				+ "job"+job+"introduction"+introduction+"placeId"+placeId);
		userInfoService.updateDetail(userId, name, sex, birthday, job,post, introduction, placeId);
		return Constant.SUCCESS;
	}
	public String getUserInfo(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		jsonMap = new HashMap<String, Object>();  
		Integer userId=null;
		String stringUserId=request.getParameter("userId");
		if(stringUserId=="null"||stringUserId==null){//不是访客
			userId=(Integer)session.getAttribute("userId");
		}else{//是访客
			userId=Integer.parseInt(stringUserId);
		}
		UserInfo userInfo=userInfoService.getUserInfoByUserId(userId);
		if(stringUserId=="null"||stringUserId==null){
			//是访客的话增加一个PV
			userInfo.setUserRecentLoginTime(new Date());
			userInfoService.update(userInfo);
		}else{//是本人的话更新最近登录时间	
			userInfo.setUserPV(userInfo.getUserPV()+1);
			userInfoService.update(userInfo);
		}
		String province=null;
		String city=null;
		String area=null;
		if(userInfo.getUserPlace()!=null){
			Place place=placeService.get(userInfo.getUserPlace());
			province=place.getProvince();
			city=place.getCity();
			area=place.getArea();
		}
		String ImageURL=userInfo.getUserImage();
		System.out.println(ImageURL);
		jsonMap.put("userInfo", userInfo);
		jsonMap.put("province", province);
		jsonMap.put("city", city);
		jsonMap.put("area", area);
		jsonMap.put("ImageURL", ImageURL);
		return "jsonMap";
	}
	public String getUserIdRandomly(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		jsonMap = new HashMap<String, Object>(); 
		Integer userId=(Integer)session.getAttribute("userId");
		Integer id=userInfoService.getUserIdRandomly(userId);
		jsonMap.put("userId", id);
		return "jsonMap";
	}
}

package com.blog.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.blog.beans.Fans;
import com.blog.beans.UserInfo;
import com.blog.common.Constant;
import com.blog.service.FansService;
import com.blog.service.UserInfoService;
import com.opensymphony.xwork2.ActionSupport;

public class FansAction extends ActionSupport{
	private FansService fansService;
	private UserInfoService userInfoService;
	private Map<String, Object> jsonMap=null;
	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}
	public UserInfoService getUserInfoService() {
		return userInfoService;
	}
	public FansService getFansService() {
		return fansService;
	}
	public void setFansService(FansService fansService) {
		this.fansService = fansService;
	}
	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}
	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}
	public String getFans(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		jsonMap = new HashMap<String, Object>();  
		Integer userId=(Integer)session.getAttribute("userId");
		Integer pageNum=Integer.parseInt(request.getParameter("Page"));
		List<Fans> fansList=fansService.getFansList(userId, 
				pageNum, Constant.FANS_SIZE);
		List<UserInfo> fans=new ArrayList<UserInfo>();
		List<Boolean> beNoticed=new ArrayList<Boolean>();
		Integer showSize=fansList.size();//读取到的数量
		for(int i=0;i<showSize;i++){
			//System.out.println(userInfoService.loadUserNameById(fansList.get(i).getUserId()));
			fans.add(userInfoService.getUserInfoByUserId(fansList.get(i).getUserId()));
			beNoticed.add(fansService.exist(userId,fansList.get(i).getUserId() ));
		}
		jsonMap.put("showSize", showSize);
		jsonMap.put("fansList", fansList);
		jsonMap.put("fans", fans);
		jsonMap.put("beNoticed", beNoticed);
		return "jsonMap";
	}
	public String getNoticer(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		jsonMap = new HashMap<String, Object>();  
		Integer userId=(Integer)session.getAttribute("userId");
		Integer pageNum=Integer.parseInt(request.getParameter("Page"));
		List<Fans> noticerList=fansService.getNoticerList(userId, pageNum, Constant.NOTICER_SIZE);
		List<UserInfo> noticer=new ArrayList<UserInfo>();
		Integer showSize=noticerList.size();
		for(int i=0;i<showSize;i++){
			noticer.add(userInfoService.getUserInfoByUserId(noticerList.get(i).getNoticerId()));
		}
		jsonMap.put("showSize", showSize);
		jsonMap.put("noticerList", noticerList);
		jsonMap.put("noticer", noticer);
		return "jsonMap";
	}
	public String getNoticerTotalPages(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		jsonMap = new HashMap<String, Object>();  
		Integer userId=(Integer)session.getAttribute("userId");
		Integer totalPages=fansService.getNoticerTotalPages(userId);
		System.out.println("totalpages"+totalPages);
		jsonMap.put("totalPages", totalPages);
		return "jsonMap";
	}
	public String getFansTotalPages(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		jsonMap = new HashMap<String, Object>();  
		Integer userId=(Integer)session.getAttribute("userId");
		Integer totalPages=fansService.getFansTotalPages(userId);
		System.out.println("totalpages"+totalPages);
		jsonMap.put("totalPages", totalPages);
		return "jsonMap";
	}
	public void createNoticer(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		Integer userId=(Integer)session.getAttribute("userId");
		Integer fansId=Integer.parseInt(request.getParameter("userId"));
		fansService.addFans(userId, fansId);
	}
	public void deleteFans(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		Integer userId=(Integer)session.getAttribute("userId");
		Integer fansId=Integer.parseInt(request.getParameter("userId"));
		fansService.deleteFans(userId, fansId);
	}
	public String getNumber(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		Integer userId=null;
		String stringUserId=request.getParameter("userId");
		if(stringUserId=="null"||stringUserId==null){//不是访客
			userId=(Integer)session.getAttribute("userId");
		}else{//是访客
			userId=Integer.parseInt(stringUserId);
		}
		Integer fansNumber=fansService.getFansNumber(userId);
		Integer noticerNumber=fansService.getNoticerNumber(userId);
		jsonMap = new HashMap<String, Object>();  
		jsonMap.put("fansNumber", fansNumber);
		jsonMap.put("noticerNumber", noticerNumber);
		return "jsonMap";
	}
	public String isFans(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		jsonMap = new HashMap<String, Object>();  
		Integer userId=Integer.parseInt(request.getParameter("userId"));
		Integer vistorId=(Integer)session.getAttribute("userId");
		Boolean flag=fansService.exist(vistorId,userId);
		jsonMap.put("flag", flag);
		return "jsonMap";
	}
}

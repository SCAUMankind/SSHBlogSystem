package com.blog.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.blog.beans.Comment;
import com.blog.beans.UserInfo;
import com.blog.service.CommentService;
import com.blog.service.UserInfoService;
import com.opensymphony.xwork2.ActionSupport;

public class CommentAction extends ActionSupport{
	private CommentService commentService;
	private UserInfoService  userInfoService;
	private Map<String, Object> jsonMap=null;
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
	public CommentService getCommentService() {
		return commentService;
	}
	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}
	public UserInfoService getUserInfoService() {
		return userInfoService;
	}
	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}
	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}
	public String getCommentList(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		jsonMap = new HashMap<String, Object>();  
		Integer blogId=Integer.parseInt(request.getParameter("blogId"));
		List<Comment> commentList=commentService.getCommentByBlogId(blogId);
		List<UserInfo> userInfoList=new ArrayList<UserInfo>();
		for(int i=0;i<commentList.size();i++){
			userInfoList.add(userInfoService.getUserInfoByUserId(commentList.get(i).getUserId()));
		}
		jsonMap.put("commentList", commentList);
		jsonMap.put("userInfoList", userInfoList);
		return "jsonMap";
	}
	public String reply(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		jsonMap = new HashMap<String, Object>();  
		Integer blogId=Integer.parseInt(request.getParameter("blogId"));
		Integer fatherId=Integer.parseInt(request.getParameter("commentId"));
		Integer userId=(Integer)session.getAttribute("userId");
		String content=request.getParameter("content");
		commentService.reply(fatherId, blogId, userId, content);
		commentService.setReply(fatherId);
		return "jsonMap";
	}
	public void addComment(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		Integer blogId=Integer.parseInt(request.getParameter("blogId"));
		Integer userId=(Integer)session.getAttribute("userId");
		String content=request.getParameter("content");
		commentService.addComment(blogId, userId, content);
	}
}

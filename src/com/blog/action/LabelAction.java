package com.blog.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.blog.beans.BlogLabelLink;
import com.blog.beans.Label;
import com.blog.common.Constant;
import com.blog.service.BlogLabelLinkService;
import com.blog.service.LabelService;
import com.opensymphony.xwork2.ActionSupport;

public class LabelAction extends ActionSupport{
	private LabelService labelService;
	private BlogLabelLinkService blogLabelLinkService;
	public void setBlogLabelLinkService(BlogLabelLinkService blogLabelLinkService) {
		this.blogLabelLinkService = blogLabelLinkService;
	}
	public BlogLabelLinkService getBlogLabelLinkService() {
		return blogLabelLinkService;
	}
	private Map<String, Object> jsonMap=null;
	public void setLabelService(LabelService labelService) {
		this.labelService = labelService;
	}
	public LabelService getLabelService() {
		return labelService;
	}
	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}
	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}
	public String getLabel(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		jsonMap=new HashMap<String,Object>();
		Integer pageNum=Integer.parseInt(request.getParameter("Page"));
		Integer userId=(Integer)session.getAttribute("userId");
		List<Label> labelList=labelService.getLabelList(userId, pageNum, Constant.LABEL_SIZE);
	    Integer showSize=labelList.size();
	    List<Integer> markNum=new ArrayList<Integer>();
	    for(int i=0;i<showSize;i++){
	    	markNum.add(labelService.getMarkedBlogSize(labelList.get(i).getLabelId()));
	    }
	    jsonMap.put("showSize", showSize);	
	    jsonMap.put("labelList", labelList);
	    jsonMap.put("markNum", markNum);
		return "jsonMap";
	}
	public String getLabelTotalPages(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		jsonMap=new HashMap<String,Object>();
		Integer userId=(Integer)session.getAttribute("userId");
		Integer totalPages=labelService.getLabelTotalPages(userId);
		System.out.println("userId:"+userId+"totalPages"+totalPages);
		jsonMap.put("totalPages", totalPages);
		return "jsonMap";
	}
	public String getMultipleLabels(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		jsonMap=new HashMap<String,Object>();
		Integer userId=(Integer)session.getAttribute("userId");
		List<Label> labelList=labelService.getAllLabel(userId);
		jsonMap.put("size", labelList.size());
		jsonMap.put("labelList", labelList);
		return "jsonMap";
	}
	public void createLabel(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		jsonMap=new HashMap<String,Object>();
		Integer userId=(Integer)session.getAttribute("userId");
		String title=request.getParameter("title");
		labelService.saveLabel(userId, title);
	}
	public void deleteLabel(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		Integer userId=(Integer)session.getAttribute("userId");
		Integer labelId=Integer.parseInt(request.getParameter("labelId"));
		blogLabelLinkService.deleteLabel(labelId);
		labelService.delete(labelId);
	}
	//获取主页标签列表
	public String getLabelList(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		Integer userId=null;
		String stringUserId=request.getParameter("userId");
		if(stringUserId=="null"||stringUserId==null){//不是访客
			userId=(Integer)session.getAttribute("userId");
		}else{//是访客
			userId=Integer.parseInt(stringUserId);
		}
		List<Label> labelList=labelService.getAllLabel(userId);
		Integer showSize=labelList.size();
		List<Integer> markNum=new ArrayList<Integer>();
	    for(int i=0;i<showSize;i++){
	    	markNum.add(labelService.getMarkedBlogSize(labelList.get(i).getLabelId()));
	    }
	    System.out.println("getLabelList:showSize"+showSize);
	    jsonMap = new HashMap<String, Object>();  
	    jsonMap.put("showSize", showSize);
	    jsonMap.put("labelList",labelList);
	    jsonMap.put("markedBlogNum", markNum);
		return "jsonMap";
	}
	public String getLabelListByBlogId(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		Integer blogId=Integer.parseInt(request.getParameter("blogId"));
		jsonMap = new HashMap<String, Object>();  
		List<BlogLabelLink> blogLabelLinks=blogLabelLinkService.getLabelListByBlogId(blogId);
		List<Label> labelList=new ArrayList<Label>();
		for(int i=0;i<blogLabelLinks.size();i++){
			labelList.add(labelService.get(blogLabelLinks.get(i).getLabelId()));
		}
		jsonMap.put("labelList", labelList);
		return "jsonMap";
	}
	public void updateLabel(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		Integer userId=(Integer)session.getAttribute("userId");
		Integer labelId=Integer.parseInt(request.getParameter("labelId"));
		String labelTitle=request.getParameter("labelTitle");
		System.out.println(userId+" "+labelId+" "+labelTitle);
		labelService.updateLabel(labelId,labelTitle,userId);
	}
}

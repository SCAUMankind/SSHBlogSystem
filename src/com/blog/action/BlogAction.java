package com.blog.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.blog.beans.Blog;
import com.blog.beans.BlogLabelLink;
import com.blog.beans.Label;
import com.blog.common.Constant;
import com.blog.service.BlogLabelLinkService;
import com.blog.service.BlogService;
import com.blog.service.LabelService;
import com.opensymphony.xwork2.ActionSupport;
public class BlogAction extends ActionSupport{
	private BlogService blogService;
	private LabelService labelService;
	private BlogLabelLinkService blogLabelLinkService;
	public BlogLabelLinkService getBlogLabelLinkService() {
		return blogLabelLinkService;
	}
	public void setBlogLabelLinkService(BlogLabelLinkService blogLabelLinkService) {
		this.blogLabelLinkService = blogLabelLinkService;
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
	public void setBlogService(BlogService blogService) {
		this.blogService = blogService;
	}
	public BlogService getBlogService() {
		return blogService;
	}
	public String getBlog(){
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
		Integer pageNum=Integer.parseInt(request.getParameter("Page"));
		System.out.println("userId"+userId+" pageNum"+pageNum);
		//Integer size=3;//每次读取的最大博客数量
		List<Blog> blogList=blogService.showBlogByPage(userId, pageNum, Constant.BLOG_SIZE);
		Integer showSize=blogList.size();//读取到的数量
		for(int i=0;i<showSize;i++){
			String text=blogList.get(i).getText();
			if(text.length()>150){
				text=text.substring(0, 150);
				blogList.get(i).setText(text);
			}
		}
		jsonMap.put("showSize", showSize);
		jsonMap.put("blogList", blogList);
		System.out.println("showSize:"+showSize);
		System.out.println("blogList:"+blogList);
		return "jsonMap";  
	}
	public String getBlogTotalNum(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		Integer userId=null;
		String stringUserId=request.getParameter("userId");
		if(stringUserId=="null"||stringUserId==null){//不是访客
			userId=(Integer)session.getAttribute("userId");
		}else{//是访客
			userId=Integer.parseInt(stringUserId);
		}
		Integer blogTotalNum=blogService.getTotalNum(userId);
		jsonMap = new HashMap<String, Object>();  
		jsonMap.put("blogTotalNum", blogTotalNum);
		return "jsonMap";
	}
	public String getTotalPages(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		Integer userId=null;
		String stringUserId=request.getParameter("userId");
		if(stringUserId=="null"||stringUserId==null){//不是访客
			userId=(Integer)session.getAttribute("userId");
		}else{//是访客
			userId=Integer.parseInt(stringUserId);
		}
		Integer totalPages=blogService.getTotalPages(userId,Constant.BLOG_SIZE);
		System.out.println("totalpages"+totalPages);
		jsonMap = new HashMap<String, Object>();  
		jsonMap.put("totalPages", totalPages);
		return "jsonMap";
	}
	public String getModifyTotalPages(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		Integer userId=(Integer)session.getAttribute("userId");
		Integer totalPages=blogService.getTotalPages(userId,Constant.BLOG_MODIFY_SIZE);
		System.out.println("博客編輯：totalpages"+totalPages);
		jsonMap = new HashMap<String, Object>();  
		jsonMap.put("totalPages", totalPages);
		return "jsonMap";
	}
	public String getModifyBlog(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		jsonMap = new HashMap<String, Object>();  
		Integer userId=(Integer)session.getAttribute("userId");
		Integer pageNum=Integer.parseInt(request.getParameter("Page"));
		System.out.println("userId"+userId+" pageNum"+pageNum);
		List<Blog> blogList=blogService.showBlogByPage(userId, pageNum, Constant.BLOG_MODIFY_SIZE);
		Integer showSize=blogList.size();//读取到的数量
		jsonMap.put("showSize", showSize);
		jsonMap.put("blogList", blogList);
		System.out.println("showSize:"+showSize);
		System.out.println("blogList:"+blogList);
		return "jsonMap";  
	}
	public String getClassifyBlog(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		jsonMap = new HashMap<String, Object>();  
		Integer userId=(Integer)session.getAttribute("userId");
		Integer pageNum=Integer.parseInt(request.getParameter("Page"));
		System.out.println("userId"+userId+" pageNum"+pageNum);
		List<Blog> blogList=blogService.showBlogByPage(userId, pageNum, Constant.BLOG_CLASSIFY_SIZE);
		Integer showSize=blogList.size();//读取到的数量
		List<Label> labelList=labelService.getAllLabel(userId);
		jsonMap.put("showSize", showSize);
		jsonMap.put("blogList", blogList);
		jsonMap.put("labelList",labelList);
		System.out.println("showSize:"+showSize);
		System.out.println("blogList:"+blogList);
		return "jsonMap";  
	}
	public String createBlog(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		jsonMap = new HashMap<String, Object>();  
		Integer userId=(Integer)session.getAttribute("userId");
		String title = request.getParameter("blog-title");
		String[] label =request.getParameterValues("label");
		String text =request.getParameter("text");
		blogService.saveBlog(userId, title, text);
		Integer blogId=blogService.getBlogId(userId, title);
		for(String str:label){
			Integer labelId=Integer.parseInt(str);
			blogLabelLinkService.saveLink(blogId, labelId);
		}
		return "jsonMap";
	}
	public String getBlogTotalPagesByLabel(){
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
		Integer labelId=Integer.parseInt(request.getParameter("labelId"));
		Integer totalPages=blogLabelLinkService.getTotalPages(labelId, Constant.BLOG_SIZE);
		jsonMap.put("totalPages", totalPages);
		return "jsonMap";
	}
	public String getBlogListByLabel(){
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
		Integer page=Integer.parseInt(request.getParameter("Page"));
		Integer labelId=Integer.parseInt(request.getParameter("labelId"));
		List<BlogLabelLink> blogList=blogLabelLinkService.getBlogListByPage(labelId,page,Constant.BLOG_SIZE);
		List<String> blogTitle=new ArrayList<String>();
		List<String> blogText=new ArrayList<String>();
		List<Integer> blogId=new ArrayList<Integer>();
		for(int i=0;i<blogList.size();i++){
			Blog blog=blogService.get(blogList.get(i).getBlogId());
			blogTitle.add(blog.getTitle());
			String text=blog.getText();
			if(text.length()>150){
				text=text.substring(0, 150);
				blog.setText(text);
			}
			blogText.add(blog.getText());
			blogId.add(blog.getBlogId());
		}
		jsonMap.put("blogTitle", blogTitle);
		jsonMap.put("blogText", blogText);
		jsonMap.put("blogId", blogId);
		jsonMap.put("showSize", blogList.size());
		return "jsonMap";
	}
	public String loadBlog(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		jsonMap = new HashMap<String, Object>();  	
		Integer blogId=Integer.parseInt(request.getParameter("blogId"));
		Blog blog =blogService.get(blogId);
		Integer userId=null;
		String stringUserId=request.getParameter("userId");
		if(stringUserId=="null"||stringUserId==null){//不是访客
			userId=(Integer)session.getAttribute("userId");
		}else{//是访客
			userId=Integer.parseInt(stringUserId);
			blog.setBlogPV(blog.getBlogPV()+1);
		}
		blogService.update(blog);
		jsonMap.put("blog", blog);
		return "jsonMap";
	}
	public void deleteBlog(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		jsonMap = new HashMap<String, Object>();  
		Integer userId=(Integer)session.getAttribute("userId");
		Integer blogId=Integer.parseInt(request.getParameter("blogId"));
		blogService.delete(blogId);
		blogLabelLinkService.deleteBlog(blogId);
	}
	public String getTop10(){
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
		List<Blog> blogList=blogService.getBlogTop10(userId);
		jsonMap.put("blogList", blogList);
		jsonMap.put("size", blogList.size());
		return "jsonMap";
	}
}

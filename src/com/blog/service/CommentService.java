package com.blog.service;

import java.util.List;

import com.blog.beans.Comment;

public interface CommentService extends BaseService<Comment>{
	public List<Comment> getCommentByBlogId(Integer blogId);
	public void reply(Integer fatherId,Integer blogId,Integer userId,String content);
	public void setReply(Integer commentId);
	public void addComment(Integer blogId,Integer userId,String content);
}


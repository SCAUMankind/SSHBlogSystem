package com.blog.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.blog.beans.Blog;
import com.blog.beans.Comment;

public class CommentDao {
	private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {  
        this.sessionFactory = sessionFactory;  
    }    
    protected Session getSession() {   
        return sessionFactory.getCurrentSession();  
    }
    public void saveComment(Comment comment) {
		getSession().save(comment);
	}
	public Comment loadComment(Integer id) {
		return (Comment)getSession().get(Comment.class, id);
	}
	public void updateComment(Comment comment) {
		getSession().update(comment);
	}
	public List getAllCommentByBlog(Integer blogId) {
		String hql="from Comment comment where comment.blogId=:blogId order by comment.date asc";
		Query query=getSession().createQuery(hql);
		query.setParameter("blogId", blogId);
		return query.list();
	}
}

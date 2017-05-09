package com.blog.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.blog.beans.Blog;
public class BlogDao {
	private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {  
        this.sessionFactory = sessionFactory;  
    }    
    protected Session getSession() {   
        return sessionFactory.getCurrentSession();  
    }
    public void saveBlog(Blog blog) {
		getSession().save(blog);
	}
	public Blog loadBlog(Integer id) {
		return (Blog)getSession().get(Blog.class, id);
	}
	public void updateBlog(Blog blog) {
		getSession().update(blog);
	}
	public List allBlog(Integer id) {
		String hql="from Blog blog where blog.id=:id";
		Query query=getSession().createQuery(hql);
		query.setParameter("id", id);
		return getSession().createQuery(hql).list();
	}
	public List<Blog> queryByPage(Integer id,Integer pageNum,Integer size){
		/*
		 * 查询第pageNum页的size条记录
		 */
		String hql="from Blog blog where blog.userId=:id order by blog.id";
		Query query=getSession().createQuery(hql);
		query.setParameter("id", id);
		query.setFirstResult((pageNum-1)*size);
		query.setMaxResults(size);
		return query.list();
	}
	public Integer getTotalNum(Integer id){
		String hql="from Blog blog where blog.userId=:id";
		Query query=getSession().createQuery(hql);
		query.setParameter("id", id);
		return query.list().size();
	}
	public Integer getBlogIdByTitle(Integer userId,String title){
		String hql="from Blog blog where blog.userId=:userId and blog.title=:title";
		Query query=getSession().createQuery(hql);
		query.setParameter("userId", userId);
		query.setParameter("title", title);
		return ((Blog)(query.list().get(0))).getBlogId();
	}
	public List<Blog> getBlogByPV(Integer userId){
		String hql="from Blog blog where blog.userId=:userId order by blog.blogPV desc";
		Query query=getSession().createQuery(hql);
		query.setParameter("userId", userId);
		return query.list();
	}
}

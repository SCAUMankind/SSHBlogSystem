package com.blog.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.blog.beans.BlogLabelLink;
import com.blog.beans.Fans;

public class BlogLabelLinkDao {
	private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {  
        this.sessionFactory = sessionFactory;  
    }    
    protected Session getSession() {   
        return sessionFactory.getCurrentSession();  
    }
	public void saveBlogLabelLink(BlogLabelLink blogLabelLink) {
		getSession().save(blogLabelLink);
	}
	public BlogLabelLink loadBlogLabelLink(Integer id) {
		return (BlogLabelLink)getSession().get(BlogLabelLink.class, id);
	}
	public void updateBlogLabelLink(BlogLabelLink blogLabelLink) {
		getSession().update(blogLabelLink);
	}
	public List<BlogLabelLink> getLabelList(Integer blogId){
		String hql="from BlogLabelLink b where b.blogId=:blogId";
		Query query=getSession().createQuery(hql);
		query.setParameter("blogId", blogId);
		return query.list();
	}
	public List<BlogLabelLink> getBlogList(Integer labelId){
		String hql="from BlogLabelLink blogLabelLink where blogLabelLink.labelId=:labelId";
		Query query=getSession().createQuery(hql);
		query.setParameter("labelId", labelId);
		return query.list();
	}
	//labelId 是对应userId得到的，所以不用在连接表上再加一个userId
	public List<BlogLabelLink> getBlogListByPage(Integer labelId,Integer pageNum,Integer size){
		String hql="from BlogLabelLink b where b.labelId=:labelId";
		Query query=getSession().createQuery(hql);
		query.setParameter("labelId", labelId);
		query.setFirstResult((pageNum-1)*size);
		query.setMaxResults(size);
		return query.list();
	}
	public void deleteByLabelId(Integer labelId){
		String hql="delete BlogLabelLink b where b.labelId=:labelId";
		Query query=getSession().createQuery(hql);
		query.setParameter("labelId", labelId);
		query.executeUpdate();
	}
	public void deleteByBolgId(Integer blogId){
		String hql="delete BlogLabelLink b where b.blogId=:blogId";
		Query query=getSession().createQuery(hql);
		query.setParameter("blogId", blogId);
		query.executeUpdate();
	}
	public Integer getTotalNum(Integer labelId){
		String hql="from BlogLabelLink b where b.labelId=:labelId";
		Query query=getSession().createQuery(hql);
	    query.setParameter("labelId", labelId);
	    return query.list().size();
	}
}

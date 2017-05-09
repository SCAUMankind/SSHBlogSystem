package com.blog.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.blog.beans.Label;

public class LabelDao {

	private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {  
        this.sessionFactory = sessionFactory;  
    }    
    protected Session getSession() {   
        return sessionFactory.getCurrentSession();  
    }
	public void saveLabel(Label label) {
		getSession().save(label);
	}
	public Label loadLabel(Integer id) {
		return (Label)getSession().get(Label.class, id);
	}
	public void updateLabel(Label label) {
		getSession().update(label);
	}
	public List<Label> getAllLabelById(Integer userId) {
		String hql="from Label label where label.userId=:userId";
		Query query=getSession().createQuery(hql);
		query.setParameter("userId", userId);
		return query.list();
	}  
	public List<Label> getLabelList(Integer userId,Integer pageNum,Integer size){
		String hql="from Label label where label.userId=:userId";
		Query query=getSession().createQuery(hql);
		query.setParameter("userId", userId);
		query.setFirstResult((pageNum-1)*size);
		query.setMaxResults(size);
		return query.list();
	}
	public Integer getTotalNum(Integer userId){
		String hql="from Label label where label.userId=:userId";
		Query query=getSession().createQuery(hql);
	    query.setParameter("userId", userId);
	    return query.list().size();
	}
}

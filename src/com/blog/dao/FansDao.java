package com.blog.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.blog.beans.Fans;
import com.blog.beans.User;

public class FansDao {
	private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {  
        this.sessionFactory = sessionFactory;  
    }    
    protected Session getSession() {   
        return sessionFactory.getCurrentSession();  
    }
	public void saveFans(Fans fans) {
		getSession().save(fans);
	}
	public Fans loadFans(Integer id) {
		return (Fans)getSession().get(Fans.class, id);
	}
	public void updateUser(Fans fans) {
		getSession().update(fans);
	}
	public List<Fans> loadFansList(Integer userId,Integer pageNum,Integer size){
		String hql="from Fans fans where fans.noticerId=:noticerId";
		Query query=getSession().createQuery(hql);
		query.setParameter("noticerId", userId);
		query.setFirstResult((pageNum-1)*size);
		query.setMaxResults(size);
		return query.list();
	}
	public List<Fans> loadNoticerList(Integer userId,Integer pageNum,Integer size){
		String hql="from Fans fans where fans.userId=:userId";
		Query query=getSession().createQuery(hql);
		query.setParameter("userId", userId);
		query.setFirstResult((pageNum-1)*size);
		query.setMaxResults(size);
		return query.list();
	}
	public Integer getNoticerTotalNum(Integer userId){
		String hql="from Fans fans where fans.userId=:userId";
		Query query=getSession().createQuery(hql);
		query.setParameter("userId", userId);
		return query.list().size();
	}
	public Integer getFansTotalNum(Integer userId){
		String hql="from Fans fans where fans.noticerId=:noticerId";
		Query query=getSession().createQuery(hql);
		query.setParameter("noticerId", userId);
		return query.list().size();
	}
	public void deleteFans(Integer userId,Integer noticerId){
		String hql="delete from Fans fans where fans.userId=:userId and fans.noticerId=:noticerId";
		Query query=getSession().createQuery(hql);
		query.setParameter("userId", userId);
		query.setParameter("noticerId", noticerId);
		query.executeUpdate();
	}
	public Boolean exist(Integer userId,Integer noticerId){
		String hql="from Fans fans where fans.userId=:userId and noticerId=:noticerId" ;
		Query query=getSession().createQuery(hql);
		query.setParameter("userId", userId);
		query.setParameter("noticerId", noticerId);
		List list=query.list();
		if(list.size()==0)return false;
		return true;
	}
}

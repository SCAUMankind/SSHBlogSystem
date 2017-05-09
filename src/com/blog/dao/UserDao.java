package com.blog.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.validator.PublicClassValidator;

import com.blog.beans.User;
import com.blog.dao.UserDao;

public class UserDao { 
	private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {  
        this.sessionFactory = sessionFactory;  
    }    
    protected Session getSession() {   
        return sessionFactory.getCurrentSession();  
    }
	public void saveUser(User user) {
		getSession().save(user);
	}
	public User loadUser(Integer id) {
		return (User)getSession().get(User.class, id);
	}
	public void updateUser(User user) {
		getSession().update(user);
	}
	public List allUser() {
		String hql="from User";
		return getSession().createQuery(hql).list();
	}  
	public User loadByAccount(String account){
		String hql="from User u where u.account=:account";
		Query query=getSession().createQuery(hql);
		query.setParameter("account", account);
		List userList=query.list();
		if(userList.size()<1){
			return null;
		}
		User user=(User)userList.get(0);
		return user;
	}
}
package com.blog.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.blog.beans.User;
import com.blog.beans.UserInfo;

public class UserInfoDao {
	private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {  
        this.sessionFactory = sessionFactory;  
    }    
    protected Session getSession() {   
        return sessionFactory.getCurrentSession();  
    }
    public void saveUserInfo(UserInfo userInfo) {
		getSession().save(userInfo);
	}
	public UserInfo loadUserInfo(Integer id) {
		return (UserInfo)getSession().get(UserInfo.class, id);
	}
	public UserInfo loadUserInfoByUserId(Integer userId) {
		String hql="from UserInfo u where u.userId=:userId";
		Query query=getSession().createQuery(hql);
		query.setParameter("userId", userId);
		return (UserInfo)(query.list().get(0));
	}
	public void updateUserInto(UserInfo userInfo) {
		getSession().update(userInfo);
	}
	public void setUserInfo(Integer id,Integer userInfoId){
		String hql="Update User user set user.userInfoId=:userInfoId where user.id=:id";
		Query query=getSession().createQuery(hql);
		query.setParameter("userInfoId", userInfoId);
		query.setParameter("id", id);
		query.executeUpdate();
	}
	public void updateImageURL(Integer userId,String url){
		String hql="Update UserInfo userInfo set userInfo.userImage=:url where userInfo.userId=:userId";
		Query query=getSession().createQuery(hql);
		query.setParameter("url", url);
		query.setParameter("userId", userId);
		query.executeUpdate();
	}
	public List<UserInfo> allOtherUserInfo(Integer userId) {
		String hql="from UserInfo userInfo where userInfo.userId<>:userId";
		Query query=getSession().createQuery(hql);
		query.setParameter("userId", userId);
		return query.list();
	} 
}

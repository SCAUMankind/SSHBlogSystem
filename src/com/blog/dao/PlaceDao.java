package com.blog.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.blog.beans.Place;
import com.blog.beans.User;

public class PlaceDao {
	private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {  
        this.sessionFactory = sessionFactory;  
    }    
    protected Session getSession() {   
        return sessionFactory.getCurrentSession();  
    }
    public void savePlace(String province,String city,String area) {
    	Place place=new Place(province,city,area);
		getSession().save(place);
	}
	public Place loadPlace(String province,String city,String area) {
		String hql="from Place p where p.province=:province"
				+ " and p.city=:city and p.area=:area";
		Query query=getSession().createQuery(hql);
		query.setParameter("province", province);
		query.setParameter("city", city);
		query.setParameter("area", area);
		List placeList=query.list();
		if(placeList.size()<1){
			return null;
		}
		return (Place)placeList.get(0);
	}
}

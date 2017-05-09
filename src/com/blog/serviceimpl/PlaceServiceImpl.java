package com.blog.serviceimpl;

import com.blog.beans.Place;
import com.blog.dao.PlaceDao;
import com.blog.service.PlaceService;

public class PlaceServiceImpl extends BaseServiceImpl<Place> implements PlaceService{
	private PlaceDao placeDao;
	public void setPlaceDao(PlaceDao placeDao) {
		this.placeDao = placeDao;
	}
	public PlaceDao getPlaceDao() {
		return placeDao;
	}
	@Override
	public Place loadPlace(String province, String city, String area) {
		// TODO Auto-generated method stub
		Place place=placeDao.loadPlace(province, city, area);
		if(place==null){
			placeDao.savePlace(province, city, area);
		}
		place=placeDao.loadPlace(province, city, area);
		return place;
	}
	@Override
	public Integer getPlaceId(String province, String city, String area) {
		Place place=loadPlace(province, city, area);
		return place.getPlaceId();
	}
	
}
